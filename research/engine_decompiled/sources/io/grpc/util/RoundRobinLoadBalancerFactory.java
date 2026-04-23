/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.util;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.ServiceConfigUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1771")
public final class RoundRobinLoadBalancerFactory
extends LoadBalancer.Factory {
    private static final RoundRobinLoadBalancerFactory INSTANCE = new RoundRobinLoadBalancerFactory();

    private RoundRobinLoadBalancerFactory() {
    }

    public static RoundRobinLoadBalancerFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new RoundRobinLoadBalancer(helper);
    }

    @VisibleForTesting
    static final class Picker
    extends LoadBalancer.SubchannelPicker {
        private static final AtomicIntegerFieldUpdater<Picker> indexUpdater = AtomicIntegerFieldUpdater.newUpdater(Picker.class, "index");
        @Nullable
        private final Status status;
        private final List<LoadBalancer.Subchannel> list;
        @Nullable
        private final RoundRobinLoadBalancer.StickinessState stickinessState;
        private volatile int index = -1;

        Picker(List<LoadBalancer.Subchannel> list2, @Nullable Status status, @Nullable RoundRobinLoadBalancer.StickinessState stickinessState) {
            this.list = list2;
            this.status = status;
            this.stickinessState = stickinessState;
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            if (this.list.size() > 0) {
                if (this.stickinessState != null && args.getHeaders().containsKey(this.stickinessState.key)) {
                    String stickinessValue = args.getHeaders().get(this.stickinessState.key);
                    LoadBalancer.Subchannel subchannel = this.stickinessState.getSubchannel(stickinessValue);
                    if (subchannel == null || !this.list.contains(subchannel)) {
                        subchannel = this.stickinessState.maybeRegister(stickinessValue, this.nextSubchannel(), this.list);
                    }
                    return LoadBalancer.PickResult.withSubchannel(subchannel);
                }
                return LoadBalancer.PickResult.withSubchannel(this.nextSubchannel());
            }
            if (this.status != null) {
                return LoadBalancer.PickResult.withError(this.status);
            }
            return LoadBalancer.PickResult.withNoResult();
        }

        private LoadBalancer.Subchannel nextSubchannel() {
            if (this.list.isEmpty()) {
                throw new NoSuchElementException();
            }
            int size2 = this.list.size();
            int i = indexUpdater.incrementAndGet(this);
            if (i >= size2) {
                int oldi = i;
                indexUpdater.compareAndSet(this, oldi, i %= size2);
            }
            return this.list.get(i);
        }

        @VisibleForTesting
        List<LoadBalancer.Subchannel> getList() {
            return this.list;
        }

        @VisibleForTesting
        Status getStatus() {
            return this.status;
        }
    }

    @VisibleForTesting
    static final class RoundRobinLoadBalancer
    extends LoadBalancer {
        @VisibleForTesting
        static final Attributes.Key<Ref<ConnectivityStateInfo>> STATE_INFO = Attributes.Key.create("state-info");
        private static final Logger logger = Logger.getLogger(RoundRobinLoadBalancer.class.getName());
        private final LoadBalancer.Helper helper;
        private final Map<EquivalentAddressGroup, LoadBalancer.Subchannel> subchannels = new HashMap<EquivalentAddressGroup, LoadBalancer.Subchannel>();
        @Nullable
        private StickinessState stickinessState;

        RoundRobinLoadBalancer(LoadBalancer.Helper helper) {
            this.helper = Preconditions.checkNotNull(helper, "helper");
        }

        @Override
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> servers, Attributes attributes) {
            String stickinessMetadataKey;
            Set<EquivalentAddressGroup> currentAddrs = this.subchannels.keySet();
            Set<EquivalentAddressGroup> latestAddrs = RoundRobinLoadBalancer.stripAttrs(servers);
            Set<EquivalentAddressGroup> addedAddrs = RoundRobinLoadBalancer.setsDifference(latestAddrs, currentAddrs);
            Set<EquivalentAddressGroup> removedAddrs = RoundRobinLoadBalancer.setsDifference(currentAddrs, latestAddrs);
            Map<String, Object> serviceConfig = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
            if (serviceConfig != null && (stickinessMetadataKey = ServiceConfigUtil.getStickinessMetadataKeyFromServiceConfig(serviceConfig)) != null) {
                if (stickinessMetadataKey.endsWith("-bin")) {
                    logger.log(Level.FINE, "Binary stickiness header is not supported. The header '{0}' will be ignored", stickinessMetadataKey);
                } else if (this.stickinessState == null || !this.stickinessState.key.name().equals(stickinessMetadataKey)) {
                    this.stickinessState = new StickinessState(stickinessMetadataKey);
                }
            }
            for (EquivalentAddressGroup addressGroup : addedAddrs) {
                Attributes subchannelAttrs = Attributes.newBuilder().set(STATE_INFO, new Ref<ConnectivityStateInfo>(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE))).build();
                LoadBalancer.Subchannel subchannel = Preconditions.checkNotNull(this.helper.createSubchannel(addressGroup, subchannelAttrs), "subchannel");
                this.subchannels.put(addressGroup, subchannel);
                subchannel.requestConnection();
            }
            for (EquivalentAddressGroup addressGroup : removedAddrs) {
                LoadBalancer.Subchannel subchannel = this.subchannels.remove(addressGroup);
                subchannel.shutdown();
            }
            this.updateBalancingState(this.getAggregatedState(), this.getAggregatedError());
        }

        @Override
        public void handleNameResolutionError(Status error2) {
            this.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, error2);
        }

        @Override
        public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
            if (stateInfo.getState() == ConnectivityState.SHUTDOWN && this.stickinessState != null) {
                this.stickinessState.remove(subchannel);
            }
            if (this.subchannels.get(subchannel.getAddresses()) != subchannel) {
                return;
            }
            if (stateInfo.getState() == ConnectivityState.IDLE) {
                subchannel.requestConnection();
            }
            RoundRobinLoadBalancer.getSubchannelStateInfoRef((LoadBalancer.Subchannel)subchannel).value = stateInfo;
            this.updateBalancingState(this.getAggregatedState(), this.getAggregatedError());
        }

        @Override
        public void shutdown() {
            for (LoadBalancer.Subchannel subchannel : this.getSubchannels()) {
                subchannel.shutdown();
            }
        }

        private void updateBalancingState(ConnectivityState state, Status error2) {
            List<LoadBalancer.Subchannel> activeList = RoundRobinLoadBalancer.filterNonFailingSubchannels(this.getSubchannels());
            this.helper.updateBalancingState(state, new Picker(activeList, error2, this.stickinessState));
        }

        private static List<LoadBalancer.Subchannel> filterNonFailingSubchannels(Collection<LoadBalancer.Subchannel> subchannels) {
            ArrayList<LoadBalancer.Subchannel> readySubchannels = new ArrayList<LoadBalancer.Subchannel>(subchannels.size());
            for (LoadBalancer.Subchannel subchannel : subchannels) {
                if (((ConnectivityStateInfo)RoundRobinLoadBalancer.getSubchannelStateInfoRef((LoadBalancer.Subchannel)subchannel).value).getState() != ConnectivityState.READY) continue;
                readySubchannels.add(subchannel);
            }
            return readySubchannels;
        }

        private static Set<EquivalentAddressGroup> stripAttrs(List<EquivalentAddressGroup> groupList) {
            HashSet<EquivalentAddressGroup> addrs = new HashSet<EquivalentAddressGroup>(groupList.size());
            for (EquivalentAddressGroup group : groupList) {
                addrs.add(new EquivalentAddressGroup(group.getAddresses()));
            }
            return addrs;
        }

        @Nullable
        private Status getAggregatedError() {
            Status status = null;
            for (LoadBalancer.Subchannel subchannel : this.getSubchannels()) {
                ConnectivityStateInfo stateInfo = (ConnectivityStateInfo)RoundRobinLoadBalancer.getSubchannelStateInfoRef((LoadBalancer.Subchannel)subchannel).value;
                if (stateInfo.getState() != ConnectivityState.TRANSIENT_FAILURE) {
                    return null;
                }
                status = stateInfo.getStatus();
            }
            return status;
        }

        private ConnectivityState getAggregatedState() {
            EnumSet<ConnectivityState> states = EnumSet.noneOf(ConnectivityState.class);
            for (LoadBalancer.Subchannel subchannel : this.getSubchannels()) {
                states.add(((ConnectivityStateInfo)RoundRobinLoadBalancer.getSubchannelStateInfoRef((LoadBalancer.Subchannel)subchannel).value).getState());
            }
            if (states.contains((Object)ConnectivityState.READY)) {
                return ConnectivityState.READY;
            }
            if (states.contains((Object)ConnectivityState.CONNECTING)) {
                return ConnectivityState.CONNECTING;
            }
            if (states.contains((Object)ConnectivityState.IDLE)) {
                return ConnectivityState.CONNECTING;
            }
            return ConnectivityState.TRANSIENT_FAILURE;
        }

        @VisibleForTesting
        Collection<LoadBalancer.Subchannel> getSubchannels() {
            return this.subchannels.values();
        }

        private static Ref<ConnectivityStateInfo> getSubchannelStateInfoRef(LoadBalancer.Subchannel subchannel) {
            return Preconditions.checkNotNull(subchannel.getAttributes().get(STATE_INFO), "STATE_INFO");
        }

        private static <T> Set<T> setsDifference(Set<T> a, Set<T> b) {
            HashSet<T> aCopy = new HashSet<T>(a);
            aCopy.removeAll(b);
            return aCopy;
        }

        Map<String, Ref<LoadBalancer.Subchannel>> getStickinessMapForTest() {
            if (this.stickinessState == null) {
                return null;
            }
            return this.stickinessState.stickinessMap;
        }

        private static final class StickinessState {
            static final int MAX_ENTRIES = 1000;
            final Metadata.Key<String> key;
            final Map<String, Ref<LoadBalancer.Subchannel>> stickinessMap = new LinkedHashMap<String, Ref<LoadBalancer.Subchannel>>(){

                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Ref<LoadBalancer.Subchannel>> eldest) {
                    return this.size() > 1000;
                }
            };
            final Map<LoadBalancer.Subchannel, Ref<LoadBalancer.Subchannel>> subchannelRefs = new HashMap<LoadBalancer.Subchannel, Ref<LoadBalancer.Subchannel>>();

            StickinessState(@Nonnull String stickinessKey) {
                this.key = Metadata.Key.of(stickinessKey, Metadata.ASCII_STRING_MARSHALLER);
            }

            @Nonnull
            synchronized LoadBalancer.Subchannel maybeRegister(String stickinessValue, @Nonnull LoadBalancer.Subchannel subchannel, List<LoadBalancer.Subchannel> rrList) {
                LoadBalancer.Subchannel existingSubchannel = this.getSubchannel(stickinessValue);
                if (existingSubchannel != null && rrList.contains(existingSubchannel)) {
                    return existingSubchannel;
                }
                Ref<LoadBalancer.Subchannel> subchannelRef = this.subchannelRefs.get(subchannel);
                if (subchannelRef == null) {
                    subchannelRef = new Ref<LoadBalancer.Subchannel>(subchannel);
                    this.subchannelRefs.put(subchannel, subchannelRef);
                }
                this.stickinessMap.put(stickinessValue, subchannelRef);
                return subchannel;
            }

            synchronized void remove(LoadBalancer.Subchannel subchannel) {
                if (this.subchannelRefs.containsKey(subchannel)) {
                    this.subchannelRefs.get((Object)subchannel).value = null;
                    this.subchannelRefs.remove(subchannel);
                }
            }

            @Nullable
            synchronized LoadBalancer.Subchannel getSubchannel(String stickinessValue) {
                Ref<LoadBalancer.Subchannel> subchannelRef = this.stickinessMap.get(stickinessValue);
                if (subchannelRef != null) {
                    return (LoadBalancer.Subchannel)subchannelRef.value;
                }
                return null;
            }
        }
    }

    @VisibleForTesting
    static final class Ref<T> {
        T value;

        Ref(T value) {
            this.value = value;
        }
    }
}

