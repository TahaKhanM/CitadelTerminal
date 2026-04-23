/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.common.collect.ImmutableList;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ChannelPool
extends ManagedChannel {
    private final ImmutableList<ManagedChannel> channels;
    private final AtomicInteger indexTicker = new AtomicInteger();
    private final String authority;

    ChannelPool(List<ManagedChannel> channels) {
        this.channels = ImmutableList.copyOf(channels);
        this.authority = channels.get(0).authority();
    }

    @Override
    public String authority() {
        return this.authority;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.getNextChannel().newCall(methodDescriptor, callOptions);
    }

    @Override
    public ManagedChannel shutdown() {
        for (ManagedChannel channelWrapper : this.channels) {
            channelWrapper.shutdown();
        }
        return this;
    }

    @Override
    public boolean isShutdown() {
        for (ManagedChannel channel : this.channels) {
            if (channel.isShutdown()) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean isTerminated() {
        for (ManagedChannel channel : this.channels) {
            if (channel.isTerminated()) continue;
            return false;
        }
        return true;
    }

    @Override
    public ManagedChannel shutdownNow() {
        for (ManagedChannel channel : this.channels) {
            channel.shutdownNow();
        }
        return this;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long endTimeNanos = System.nanoTime() + unit.toNanos(timeout);
        for (ManagedChannel channel : this.channels) {
            long awaitTimeNanos = endTimeNanos - System.nanoTime();
            if (awaitTimeNanos <= 0L) break;
            channel.awaitTermination(awaitTimeNanos, TimeUnit.NANOSECONDS);
        }
        return this.isTerminated();
    }

    private ManagedChannel getNextChannel() {
        return this.getChannel(this.indexTicker.getAndIncrement());
    }

    ManagedChannel getChannel(int affinity) {
        int index = affinity % this.channels.size();
        if ((index = Math.abs(index)) < 0) {
            index = 0;
        }
        return (ManagedChannel)this.channels.get(index);
    }
}

