/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Internal;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.util.AsciiString;

@Internal
public interface ProtocolNegotiator {
    public Handler newHandler(GrpcHttp2ConnectionHandler var1);

    public static interface Handler
    extends ChannelHandler {
        public AsciiString scheme();
    }
}

