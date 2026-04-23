/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Attributes;
import io.grpc.Internal;
import io.grpc.internal.Channelz;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import javax.annotation.Nullable;

@Internal
public abstract class GrpcHttp2ConnectionHandler
extends Http2ConnectionHandler {
    @Nullable
    protected final ChannelPromise channelUnused;

    public GrpcHttp2ConnectionHandler(ChannelPromise channelUnused, Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings) {
        super(decoder, encoder, initialSettings);
        this.channelUnused = channelUnused;
    }

    @Deprecated
    public void handleProtocolNegotiationCompleted(Attributes attrs) {
        this.handleProtocolNegotiationCompleted(attrs, null);
    }

    public void handleProtocolNegotiationCompleted(Attributes attrs, Channelz.Security securityInfo) {
    }

    public void notifyUnused() {
        this.channelUnused.setSuccess(null);
    }
}

