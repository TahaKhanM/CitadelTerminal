/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.channel.pool;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;

public interface ChannelHealthChecker {
    public static final ChannelHealthChecker ACTIVE = new ChannelHealthChecker(){

        @Override
        public Future<Boolean> isHealthy(Channel channel) {
            EventLoop loop2 = channel.eventLoop();
            return channel.isActive() ? loop2.newSucceededFuture(Boolean.TRUE) : loop2.newSucceededFuture(Boolean.FALSE);
        }
    };

    public Future<Boolean> isHealthy(Channel var1);
}

