/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.internal.ProxyParameters;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import java.net.SocketAddress;

@Internal
public final class InternalNettyChannelBuilder {
    public static void overrideAuthorityChecker(NettyChannelBuilder channelBuilder, OverrideAuthorityChecker authorityChecker) {
        channelBuilder.overrideAuthorityChecker(authorityChecker);
    }

    public static void setDynamicTransportParamsFactory(NettyChannelBuilder builder, TransportCreationParamsFilterFactory factory) {
        builder.setDynamicParamsFactory(factory);
    }

    public static void setStatsEnabled(NettyChannelBuilder builder, boolean value) {
        builder.setStatsEnabled(value);
    }

    public static void setTracingEnabled(NettyChannelBuilder builder, boolean value) {
        builder.setTracingEnabled(value);
    }

    public static void setStatsRecordStartedRpcs(NettyChannelBuilder builder, boolean value) {
        builder.setStatsRecordStartedRpcs(value);
    }

    private InternalNettyChannelBuilder() {
    }

    public static interface TransportCreationParamsFilter
    extends NettyChannelBuilder.TransportCreationParamsFilter {
    }

    public static interface TransportCreationParamsFilterFactory
    extends NettyChannelBuilder.TransportCreationParamsFilterFactory {
        @Override
        public TransportCreationParamsFilter create(SocketAddress var1, String var2, String var3, ProxyParameters var4);
    }

    public static interface OverrideAuthorityChecker
    extends NettyChannelBuilder.OverrideAuthorityChecker {
    }
}

