/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1771")
public final class PickFirstBalancerFactory
extends LoadBalancer.Factory {
    private static final PickFirstBalancerFactory INSTANCE = new PickFirstBalancerFactory();

    private PickFirstBalancerFactory() {
    }

    public static PickFirstBalancerFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new PickFirstBalancer(helper);
    }

    @VisibleForTesting
    static final class Picker
    extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        Picker(LoadBalancer.PickResult result2) {
            this.result = Preconditions.checkNotNull(result2, "result");
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return this.result;
        }

        @Override
        public void requestConnection() {
            LoadBalancer.Subchannel subchannel = this.result.getSubchannel();
            if (subchannel != null) {
                subchannel.requestConnection();
            }
        }
    }

    @VisibleForTesting
    static final class PickFirstBalancer
    extends LoadBalancer {
        private final LoadBalancer.Helper helper;
        private LoadBalancer.Subchannel subchannel;

        PickFirstBalancer(LoadBalancer.Helper helper) {
            this.helper = Preconditions.checkNotNull(helper, "helper");
        }

        @Override
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> servers, Attributes attributes) {
            EquivalentAddressGroup newEag = PickFirstBalancer.flattenEquivalentAddressGroup(servers);
            if (this.subchannel == null) {
                this.subchannel = this.helper.createSubchannel(newEag, Attributes.EMPTY);
                this.helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(this.subchannel)));
                this.subchannel.requestConnection();
            } else {
                this.helper.updateSubchannelAddresses(this.subchannel, newEag);
            }
        }

        @Override
        public void handleNameResolutionError(Status error2) {
            if (this.subchannel != null) {
                this.subchannel.shutdown();
                this.subchannel = null;
            }
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(error2)));
        }

        @Override
        public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
            LoadBalancer.PickResult pickResult;
            ConnectivityState currentState = stateInfo.getState();
            if (subchannel != this.subchannel || currentState == ConnectivityState.SHUTDOWN) {
                return;
            }
            switch (currentState) {
                case CONNECTING: {
                    pickResult = LoadBalancer.PickResult.withNoResult();
                    break;
                }
                case READY: 
                case IDLE: {
                    pickResult = LoadBalancer.PickResult.withSubchannel(subchannel);
                    break;
                }
                case TRANSIENT_FAILURE: {
                    pickResult = LoadBalancer.PickResult.withError(stateInfo.getStatus());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unsupported state:" + (Object)((Object)currentState));
                }
            }
            this.helper.updateBalancingState(currentState, new Picker(pickResult));
        }

        @Override
        public void shutdown() {
            if (this.subchannel != null) {
                this.subchannel.shutdown();
            }
        }

        private static EquivalentAddressGroup flattenEquivalentAddressGroup(List<EquivalentAddressGroup> groupList) {
            ArrayList<SocketAddress> addrs = new ArrayList<SocketAddress>();
            for (EquivalentAddressGroup group : groupList) {
                addrs.addAll(group.getAddresses());
            }
            return new EquivalentAddressGroup(addrs);
        }
    }
}

