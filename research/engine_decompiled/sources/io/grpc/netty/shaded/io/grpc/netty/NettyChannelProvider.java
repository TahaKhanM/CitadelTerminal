/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.ManagedChannelProvider;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

@Internal
public final class NettyChannelProvider
extends ManagedChannelProvider {
    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public int priority() {
        return 5;
    }

    public NettyChannelBuilder builderForAddress(String name, int port) {
        return NettyChannelBuilder.forAddress(name, port);
    }

    public NettyChannelBuilder builderForTarget(String target) {
        return NettyChannelBuilder.forTarget(target);
    }
}

