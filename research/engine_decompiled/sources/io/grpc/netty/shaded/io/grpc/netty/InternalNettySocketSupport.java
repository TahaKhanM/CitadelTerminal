/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.internal.Channelz;
import io.grpc.netty.shaded.io.grpc.netty.NettySocketSupport;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import java.util.Map;

@Internal
public final class InternalNettySocketSupport {
    public static void setHelper(InternalHelper helper) {
        NettySocketSupport.setHelper(helper);
    }

    private InternalNettySocketSupport() {
    }

    public static final class InternalNativeSocketOptions
    extends NettySocketSupport.NativeSocketOptions {
        public InternalNativeSocketOptions(Channelz.TcpInfo tcpInfo, Map<String, String> otherInfo) {
            super(tcpInfo, otherInfo);
        }
    }

    public static interface InternalHelper
    extends NettySocketSupport.Helper {
        @Override
        public InternalNativeSocketOptions getNativeSocketOptions(Channel var1);
    }
}

