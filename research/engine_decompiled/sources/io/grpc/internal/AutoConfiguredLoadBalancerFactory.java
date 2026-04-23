/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.PickFirstBalancerFactory;
import io.grpc.Status;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.ServiceConfigUtil;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

final class AutoConfiguredLoadBalancerFactory
extends LoadBalancer.Factory {
    @VisibleForTesting
    static final String ROUND_ROUND_LOAD_BALANCER_FACTORY_NAME = "io.grpc.util.RoundRobinLoadBalancerFactory";
    @VisibleForTesting
    static final String GRPCLB_LOAD_BALANCER_FACTORY_NAME = "io.grpc.grpclb.GrpclbLoadBalancerFactory";

    AutoConfiguredLoadBalancerFactory() {
    }

    @Override
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    @VisibleForTesting
    static final class AutoConfiguredLoadBalancer
    extends LoadBalancer {
        private final LoadBalancer.Helper helper;
        private LoadBalancer delegate;
        private LoadBalancer.Factory delegateFactory;

        AutoConfiguredLoadBalancer(LoadBalancer.Helper helper) {
            this.helper = helper;
            this.setDelegateFactory(PickFirstBalancerFactory.getInstance());
            this.setDelegate(this.getDelegateFactory().newLoadBalancer(helper));
        }

        @Override
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> servers, Attributes attributes) {
            LoadBalancer.Factory newlbf;
            Map<String, Object> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
            if (configMap != null && (newlbf = AutoConfiguredLoadBalancer.decideLoadBalancerFactory(servers, configMap)) != null && newlbf != this.delegateFactory) {
                this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptySubchannelPicker());
                this.getDelegate().shutdown();
                this.setDelegateFactory(newlbf);
                this.setDelegate(this.getDelegateFactory().newLoadBalancer(this.helper));
            }
            this.getDelegate().handleResolvedAddressGroups(servers, attributes);
        }

        @Override
        public void handleNameResolutionError(Status error2) {
            this.getDelegate().handleNameResolutionError(error2);
        }

        @Override
        public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
            this.getDelegate().handleSubchannelState(subchannel, stateInfo);
        }

        @Override
        public void shutdown() {
            this.getDelegate().shutdown();
            this.setDelegate(null);
        }

        @VisibleForTesting
        LoadBalancer getDelegate() {
            return this.delegate;
        }

        @VisibleForTesting
        void setDelegate(LoadBalancer delegate) {
            this.delegate = delegate;
        }

        @VisibleForTesting
        LoadBalancer.Factory getDelegateFactory() {
            return this.delegateFactory;
        }

        @VisibleForTesting
        void setDelegateFactory(LoadBalancer.Factory delegateFactory) {
            this.delegateFactory = delegateFactory;
        }

        @Nullable
        @VisibleForTesting
        static LoadBalancer.Factory decideLoadBalancerFactory(List<EquivalentAddressGroup> servers, Map<String, Object> config) {
            Preconditions.checkNotNull(config);
            boolean haveBalancerAddress = false;
            for (EquivalentAddressGroup s2 : servers) {
                if (s2.getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) == null) continue;
                haveBalancerAddress = true;
                break;
            }
            if (haveBalancerAddress) {
                try {
                    Class<?> lbFactoryClass = Class.forName(AutoConfiguredLoadBalancerFactory.GRPCLB_LOAD_BALANCER_FACTORY_NAME);
                    Method getInstance = lbFactoryClass.getMethod("getInstance", new Class[0]);
                    return (LoadBalancer.Factory)getInstance.invoke(null, new Object[0]);
                }
                catch (RuntimeException e) {
                    throw e;
                }
                catch (Exception e) {
                    throw new RuntimeException("Can't get GRPCLB, but balancer addresses were present", e);
                }
            }
            String serviceConfigChoiceBalancingPolicy = ServiceConfigUtil.getLoadBalancingPolicyFromServiceConfig(config);
            if (serviceConfigChoiceBalancingPolicy != null) {
                if (serviceConfigChoiceBalancingPolicy.toUpperCase(Locale.ROOT).equals("ROUND_ROBIN")) {
                    try {
                        Class<?> lbFactoryClass = Class.forName(AutoConfiguredLoadBalancerFactory.ROUND_ROUND_LOAD_BALANCER_FACTORY_NAME);
                        Method getInstance = lbFactoryClass.getMethod("getInstance", new Class[0]);
                        return (LoadBalancer.Factory)getInstance.invoke(null, new Object[0]);
                    }
                    catch (RuntimeException e) {
                        throw e;
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Can't get Round Robin LB", e);
                    }
                }
                throw new IllegalArgumentException("Unknown service config policy: " + serviceConfigChoiceBalancingPolicy);
            }
            return PickFirstBalancerFactory.getInstance();
        }
    }

    private static final class EmptySubchannelPicker
    extends LoadBalancer.SubchannelPicker {
        private EmptySubchannelPicker() {
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return LoadBalancer.PickResult.withNoResult();
        }
    }
}

