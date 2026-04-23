/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ExperimentalApi;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.Status;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1771")
@NotThreadSafe
public abstract class LoadBalancer {
    public abstract void handleResolvedAddressGroups(List<EquivalentAddressGroup> var1, Attributes var2);

    public abstract void handleNameResolutionError(Status var1);

    public abstract void handleSubchannelState(Subchannel var1, ConnectivityStateInfo var2);

    public abstract void shutdown();

    @ThreadSafe
    public static abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper var1);
    }

    @ThreadSafe
    public static abstract class Subchannel {
        public abstract void shutdown();

        public abstract void requestConnection();

        public abstract EquivalentAddressGroup getAddresses();

        public abstract Attributes getAttributes();
    }

    @ThreadSafe
    public static abstract class Helper {
        public abstract Subchannel createSubchannel(EquivalentAddressGroup var1, Attributes var2);

        public void updateSubchannelAddresses(Subchannel subchannel, EquivalentAddressGroup addrs) {
            throw new UnsupportedOperationException();
        }

        public abstract ManagedChannel createOobChannel(EquivalentAddressGroup var1, String var2);

        public void updateOobChannelAddresses(ManagedChannel channel, EquivalentAddressGroup eag) {
            throw new UnsupportedOperationException();
        }

        public abstract void updateBalancingState(@Nonnull ConnectivityState var1, @Nonnull SubchannelPicker var2);

        public abstract void runSerialized(Runnable var1);

        public abstract NameResolver.Factory getNameResolverFactory();

        public abstract String getAuthority();
    }

    @Immutable
    public static final class PickResult {
        private static final PickResult NO_RESULT = new PickResult(null, null, Status.OK, false);
        @Nullable
        private final Subchannel subchannel;
        @Nullable
        private final ClientStreamTracer.Factory streamTracerFactory;
        private final Status status;
        private final boolean drop;

        private PickResult(@Nullable Subchannel subchannel, @Nullable ClientStreamTracer.Factory streamTracerFactory, Status status, boolean drop2) {
            this.subchannel = subchannel;
            this.streamTracerFactory = streamTracerFactory;
            this.status = Preconditions.checkNotNull(status, "status");
            this.drop = drop2;
        }

        public static PickResult withSubchannel(Subchannel subchannel, @Nullable ClientStreamTracer.Factory streamTracerFactory) {
            return new PickResult(Preconditions.checkNotNull(subchannel, "subchannel"), streamTracerFactory, Status.OK, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel) {
            return PickResult.withSubchannel(subchannel, null);
        }

        public static PickResult withError(Status error2) {
            Preconditions.checkArgument(!error2.isOk(), "error status shouldn't be OK");
            return new PickResult(null, null, error2, false);
        }

        public static PickResult withDrop(Status status) {
            Preconditions.checkArgument(!status.isOk(), "drop status shouldn't be OK");
            return new PickResult(null, null, status, true);
        }

        public static PickResult withNoResult() {
            return NO_RESULT;
        }

        @Nullable
        public Subchannel getSubchannel() {
            return this.subchannel;
        }

        @Nullable
        public ClientStreamTracer.Factory getStreamTracerFactory() {
            return this.streamTracerFactory;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isDrop() {
            return this.drop;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("subchannel", this.subchannel).add("streamTracerFactory", this.streamTracerFactory).add("status", this.status).add("drop", this.drop).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.status, this.streamTracerFactory, this.drop);
        }

        public boolean equals(Object other) {
            if (!(other instanceof PickResult)) {
                return false;
            }
            PickResult that = (PickResult)other;
            return Objects.equal(this.subchannel, that.subchannel) && Objects.equal(this.status, that.status) && Objects.equal(this.streamTracerFactory, that.streamTracerFactory) && this.drop == that.drop;
        }
    }

    public static abstract class PickSubchannelArgs {
        public abstract CallOptions getCallOptions();

        public abstract Metadata getHeaders();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();
    }

    @ThreadSafe
    public static abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs var1);

        public void requestConnection() {
        }
    }
}

