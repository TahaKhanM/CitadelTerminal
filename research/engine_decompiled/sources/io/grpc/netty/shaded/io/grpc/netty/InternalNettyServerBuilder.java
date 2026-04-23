/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

@Internal
public final class InternalNettyServerBuilder {
    public static void setStatsEnabled(NettyServerBuilder builder, boolean value) {
        builder.setStatsEnabled(value);
    }

    public static void setStatsRecordStartedRpcs(NettyServerBuilder builder, boolean value) {
        builder.setStatsRecordStartedRpcs(value);
    }

    public static void setTracingEnabled(NettyServerBuilder builder, boolean value) {
        builder.setTracingEnabled(value);
    }

    private InternalNettyServerBuilder() {
    }
}

