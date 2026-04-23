/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2OutboundHeaders;
import io.grpc.netty.shaded.io.grpc.netty.NettySocketSupport;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
class Utils {
    public static final AsciiString STATUS_OK = AsciiString.of("200");
    public static final AsciiString HTTP_METHOD = AsciiString.of("POST");
    public static final AsciiString HTTP_GET_METHOD = AsciiString.of("GET");
    public static final AsciiString HTTPS = AsciiString.of("https");
    public static final AsciiString HTTP = AsciiString.of("http");
    public static final AsciiString CONTENT_TYPE_HEADER = AsciiString.of(GrpcUtil.CONTENT_TYPE_KEY.name());
    public static final AsciiString CONTENT_TYPE_GRPC = AsciiString.of("application/grpc");
    public static final AsciiString TE_HEADER = AsciiString.of(GrpcUtil.TE_HEADER.name());
    public static final AsciiString TE_TRAILERS = AsciiString.of("trailers");
    public static final AsciiString USER_AGENT = AsciiString.of(GrpcUtil.USER_AGENT_KEY.name());
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_BOSS_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(1, "grpc-default-boss-ELG");
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_WORKER_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(0, "grpc-default-worker-ELG");
    @VisibleForTesting
    static boolean validateHeaders = false;

    public static Metadata convertHeaders(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders h = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders)http2Headers;
            return InternalMetadata.newMetadata(h.numHeaders(), h.namesAndValues());
        }
        return InternalMetadata.newMetadata(Utils.convertHeadersToArray(http2Headers));
    }

    private static byte[][] convertHeadersToArray(Http2Headers http2Headers) {
        byte[][] headerValues = new byte[http2Headers.size() * 2][];
        int i = 0;
        for (Map.Entry<CharSequence, CharSequence> entry : http2Headers) {
            headerValues[i++] = Utils.bytes(entry.getKey());
            headerValues[i++] = Utils.bytes(entry.getValue());
        }
        return TransportFrameUtil.toRawSerializedHeaders(headerValues);
    }

    private static byte[] bytes(CharSequence seq) {
        if (seq instanceof AsciiString) {
            AsciiString str = (AsciiString)seq;
            return str.isEntireArrayUsed() ? str.array() : str.toByteArray();
        }
        return seq.toString().getBytes(CharsetUtil.UTF_8);
    }

    public static Http2Headers convertClientHeaders(Metadata headers, AsciiString scheme, AsciiString defaultPath, AsciiString authority, AsciiString method, AsciiString userAgent) {
        Preconditions.checkNotNull(defaultPath, "defaultPath");
        Preconditions.checkNotNull(authority, "authority");
        Preconditions.checkNotNull(method, "method");
        headers.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        headers.discardAll(GrpcUtil.TE_HEADER);
        headers.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.clientRequestHeaders(TransportFrameUtil.toHttp2Headers(headers), authority, defaultPath, method, scheme, userAgent);
    }

    public static Http2Headers convertServerHeaders(Metadata headers) {
        headers.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        headers.discardAll(GrpcUtil.TE_HEADER);
        headers.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.serverResponseHeaders(TransportFrameUtil.toHttp2Headers(headers));
    }

    public static Metadata convertTrailers(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders h = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders)http2Headers;
            return InternalMetadata.newMetadata(h.numHeaders(), h.namesAndValues());
        }
        return InternalMetadata.newMetadata(Utils.convertHeadersToArray(http2Headers));
    }

    public static Http2Headers convertTrailers(Metadata trailers, boolean headersSent) {
        if (!headersSent) {
            return Utils.convertServerHeaders(trailers);
        }
        return GrpcHttp2OutboundHeaders.serverResponseTrailers(TransportFrameUtil.toHttp2Headers(trailers));
    }

    public static Status statusFromThrowable(Throwable t) {
        Status s2 = Status.fromThrowable(t);
        if (s2.getCode() != Status.Code.UNKNOWN) {
            return s2;
        }
        if (t instanceof ClosedChannelException) {
            ClosedChannelException extraT = new ClosedChannelException();
            extraT.initCause(t);
            return Status.UNKNOWN.withDescription("channel closed").withCause(extraT);
        }
        if (t instanceof IOException) {
            return Status.UNAVAILABLE.withDescription("io exception").withCause(t);
        }
        if (t instanceof Http2Exception) {
            return Status.INTERNAL.withDescription("http2 exception").withCause(t);
        }
        return s2;
    }

    static Channelz.SocketOptions getSocketOptions(Channel channel) {
        Integer timeoutMillis;
        ChannelConfig config = channel.config();
        Channelz.SocketOptions.Builder b = new Channelz.SocketOptions.Builder();
        Integer lingerSeconds = config.getOption(ChannelOption.SO_LINGER);
        if (lingerSeconds != null) {
            b.setSocketOptionLingerSeconds(lingerSeconds);
        }
        if ((timeoutMillis = config.getOption(ChannelOption.SO_TIMEOUT)) != null) {
            b.setSocketOptionTimeoutMillis(timeoutMillis);
        }
        for (Map.Entry<ChannelOption<?>, Object> opt2 : config.getOptions().entrySet()) {
            ChannelOption<?> key = opt2.getKey();
            if (key.equals(ChannelOption.SO_LINGER) || key.equals(ChannelOption.SO_TIMEOUT)) continue;
            Object value = opt2.getValue();
            b.addOption(key.name(), String.valueOf(value));
        }
        NettySocketSupport.NativeSocketOptions nativeOptions = NettySocketSupport.getNativeSocketOptions(channel);
        if (nativeOptions != null) {
            b.setTcpInfo(nativeOptions.tcpInfo);
            for (Map.Entry entry : nativeOptions.otherInfo.entrySet()) {
                b.addOption((String)entry.getKey(), (String)entry.getValue());
            }
        }
        return b.build();
    }

    private Utils() {
    }

    private static class DefaultEventLoopGroupResource
    implements SharedResourceHolder.Resource<EventLoopGroup> {
        private final String name;
        private final int numEventLoops;

        DefaultEventLoopGroupResource(int numEventLoops, String name) {
            this.name = name;
            this.numEventLoops = numEventLoops;
        }

        @Override
        public EventLoopGroup create() {
            boolean useDaemonThreads = true;
            DefaultThreadFactory threadFactory = new DefaultThreadFactory(this.name, useDaemonThreads);
            int parallelism = this.numEventLoops == 0 ? Runtime.getRuntime().availableProcessors() * 2 : this.numEventLoops;
            return new NioEventLoopGroup(parallelism, threadFactory);
        }

        @Override
        public void close(EventLoopGroup instance) {
            instance.shutdownGracefully(0L, 0L, TimeUnit.SECONDS);
        }

        public String toString() {
            return this.name;
        }
    }
}

