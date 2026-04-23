/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.ServerProvider;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

@Internal
public final class NettyServerProvider
extends ServerProvider {
    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    protected NettyServerBuilder builderForPort(int port) {
        return NettyServerBuilder.forPort(port);
    }
}

