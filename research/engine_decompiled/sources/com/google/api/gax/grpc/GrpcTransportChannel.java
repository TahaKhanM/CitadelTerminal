/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.grpc.AutoValue_GrpcTransportChannel;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.rpc.TransportChannel;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import java.util.concurrent.TimeUnit;

@InternalExtensionOnly
public abstract class GrpcTransportChannel
implements TransportChannel {
    public static String getGrpcTransportName() {
        return "grpc";
    }

    @Override
    public String getTransportName() {
        return GrpcTransportChannel.getGrpcTransportName();
    }

    @Override
    public GrpcCallContext getEmptyCallContext() {
        return GrpcCallContext.createDefault();
    }

    abstract ManagedChannel getManagedChannel();

    public Channel getChannel() {
        return this.getManagedChannel();
    }

    @Override
    public void shutdown() {
        this.getManagedChannel().shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.getManagedChannel().isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.getManagedChannel().isTerminated();
    }

    @Override
    public void shutdownNow() {
        this.getManagedChannel().shutdownNow();
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return this.getManagedChannel().awaitTermination(duration, unit);
    }

    @Override
    public void close() {
        this.getManagedChannel().shutdown();
    }

    public static Builder newBuilder() {
        return new AutoValue_GrpcTransportChannel.Builder();
    }

    public static GrpcTransportChannel create(ManagedChannel channel) {
        return GrpcTransportChannel.newBuilder().setManagedChannel(channel).build();
    }

    public static abstract class Builder {
        public abstract Builder setManagedChannel(ManagedChannel var1);

        public abstract GrpcTransportChannel build();
    }
}

