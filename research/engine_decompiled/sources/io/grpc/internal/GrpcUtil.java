/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalMetadata;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractSubchannel;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.FailingClientTransport;
import io.grpc.internal.LogId;
import io.grpc.internal.ProxyDetector;
import io.grpc.internal.ProxyDetectorImpl;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StreamListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class GrpcUtil {
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final boolean IS_RESTRICTED_APPENGINE = System.getProperty("com.google.appengine.runtime.environment") != null && "1.7".equals(System.getProperty("java.specification.version"));
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.of("grpc-timeout", new TimeoutMarshaller());
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<byte[]> MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("grpc-accept-encoding", new AcceptEncodingMarshaller());
    public static final Metadata.Key<String> CONTENT_ENCODING_KEY = Metadata.Key.of("content-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<byte[]> CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("accept-encoding", new AcceptEncodingMarshaller());
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> TE_HEADER = Metadata.Key.of("te", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
    public static final int DEFAULT_PORT_PLAINTEXT = 80;
    public static final int DEFAULT_PORT_SSL = 443;
    public static final String CONTENT_TYPE_GRPC = "application/grpc";
    public static final String HTTP_METHOD = "POST";
    public static final String TE_TRAILERS = "trailers";
    public static final String TIMEOUT = "grpc-timeout";
    public static final String MESSAGE_ENCODING = "grpc-encoding";
    public static final String MESSAGE_ACCEPT_ENCODING = "grpc-accept-encoding";
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final String CONTENT_ACCEPT_ENCODING = "accept-encoding";
    public static final int DEFAULT_MAX_MESSAGE_SIZE = 0x400000;
    public static final int DEFAULT_MAX_HEADER_LIST_SIZE = 8192;
    public static final Splitter ACCEPT_ENCODING_SPLITTER = Splitter.on(',').trimResults();
    private static final String IMPLEMENTATION_VERSION = "1.13.1";
    public static final long DEFAULT_KEEPALIVE_TIME_NANOS = TimeUnit.MINUTES.toNanos(1L);
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20L);
    public static final long KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final long DEFAULT_SERVER_KEEPALIVE_TIME_NANOS = TimeUnit.HOURS.toNanos(2L);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20L);
    public static final long SERVER_KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
    public static final ProxyDetector NOOP_PROXY_DETECTOR = new ProxyDetector(){

        @Override
        @Nullable
        public ProxyParameters proxyFor(SocketAddress targetServerAddress) {
            return null;
        }
    };
    public static final SharedResourceHolder.Resource<ExecutorService> SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource<ExecutorService>(){
        private static final String NAME = "grpc-default-executor";

        @Override
        public ExecutorService create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        @Override
        public void close(ExecutorService instance) {
            instance.shutdown();
        }

        public String toString() {
            return NAME;
        }
    };
    public static final SharedResourceHolder.Resource<ScheduledExecutorService> TIMER_SERVICE = new SharedResourceHolder.Resource<ScheduledExecutorService>(){

        @Override
        public ScheduledExecutorService create() {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                Method method = service.getClass().getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
                method.invoke((Object)service, true);
            }
            catch (NoSuchMethodException method) {
            }
            catch (RuntimeException e) {
                throw e;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            return service;
        }

        @Override
        public void close(ScheduledExecutorService instance) {
            instance.shutdown();
        }
    };
    public static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>(){

        @Override
        public Stopwatch get() {
            return Stopwatch.createUnstarted();
        }
    };

    public static ProxyDetector getDefaultProxyDetector() {
        if (IS_RESTRICTED_APPENGINE) {
            return NOOP_PROXY_DETECTOR;
        }
        return DEFAULT_PROXY_DETECTOR;
    }

    public static Status httpStatusToGrpcStatus(int httpStatusCode) {
        return GrpcUtil.httpStatusToGrpcCode(httpStatusCode).toStatus().withDescription("HTTP status code " + httpStatusCode);
    }

    private static Status.Code httpStatusToGrpcCode(int httpStatusCode) {
        if (httpStatusCode >= 100 && httpStatusCode < 200) {
            return Status.Code.INTERNAL;
        }
        switch (httpStatusCode) {
            case 400: 
            case 431: {
                return Status.Code.INTERNAL;
            }
            case 401: {
                return Status.Code.UNAUTHENTICATED;
            }
            case 403: {
                return Status.Code.PERMISSION_DENIED;
            }
            case 404: {
                return Status.Code.UNIMPLEMENTED;
            }
            case 429: 
            case 502: 
            case 503: 
            case 504: {
                return Status.Code.UNAVAILABLE;
            }
        }
        return Status.Code.UNKNOWN;
    }

    public static boolean isGrpcContentType(String contentType) {
        if (contentType == null) {
            return false;
        }
        if (CONTENT_TYPE_GRPC.length() > contentType.length()) {
            return false;
        }
        if (!(contentType = contentType.toLowerCase()).startsWith(CONTENT_TYPE_GRPC)) {
            return false;
        }
        if (contentType.length() == CONTENT_TYPE_GRPC.length()) {
            return true;
        }
        char nextChar = contentType.charAt(CONTENT_TYPE_GRPC.length());
        return nextChar == '+' || nextChar == ';';
    }

    public static String getGrpcUserAgent(String transportName, @Nullable String applicationUserAgent) {
        StringBuilder builder = new StringBuilder();
        if (applicationUserAgent != null) {
            builder.append(applicationUserAgent);
            builder.append(' ');
        }
        builder.append("grpc-java-");
        builder.append(transportName);
        builder.append('/');
        builder.append(IMPLEMENTATION_VERSION);
        return builder.toString();
    }

    public static URI authorityToUri(String authority) {
        URI uri;
        Preconditions.checkNotNull(authority, "authority");
        try {
            uri = new URI(null, authority, null, null, null);
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Invalid authority: " + authority, ex);
        }
        return uri;
    }

    public static String checkAuthority(String authority) {
        URI uri = GrpcUtil.authorityToUri(authority);
        Preconditions.checkArgument(uri.getHost() != null, "No host in authority '%s'", (Object)authority);
        Preconditions.checkArgument(uri.getUserInfo() == null, "Userinfo must not be present on authority: '%s'", (Object)authority);
        return authority;
    }

    public static String authorityFromHostAndPort(String host, int port) {
        try {
            return new URI(null, null, host, port, null, null, null).getAuthority();
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Invalid host or port: " + host + " " + port, ex);
        }
    }

    public static ThreadFactory getThreadFactory(String nameFormat, boolean daemon) {
        if (IS_RESTRICTED_APPENGINE) {
            ThreadFactory factory = MoreExecutors.platformThreadFactory();
            return factory;
        }
        return new ThreadFactoryBuilder().setDaemon(daemon).setNameFormat(nameFormat).build();
    }

    public static String getHost(InetSocketAddress addr) {
        try {
            Method getHostStringMethod = InetSocketAddress.class.getMethod("getHostString", new Class[0]);
            return (String)getHostStringMethod.invoke((Object)addr, new Object[0]);
        }
        catch (NoSuchMethodException noSuchMethodException) {
        }
        catch (IllegalAccessException illegalAccessException) {
        }
        catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
        return addr.getHostName();
    }

    @Nullable
    static ClientTransport getTransportFromPickResult(LoadBalancer.PickResult result2, boolean isWaitForReady) {
        LoadBalancer.Subchannel subchannel = result2.getSubchannel();
        final ClientTransport transport = subchannel != null ? ((AbstractSubchannel)subchannel).obtainActiveTransport() : null;
        if (transport != null) {
            final ClientStreamTracer.Factory streamTracerFactory = result2.getStreamTracerFactory();
            if (streamTracerFactory == null) {
                return transport;
            }
            return new ClientTransport(){

                @Override
                public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
                    return transport.newStream(method, headers, callOptions.withStreamTracerFactory(streamTracerFactory));
                }

                @Override
                public void ping(ClientTransport.PingCallback callback, Executor executor) {
                    transport.ping(callback, executor);
                }

                @Override
                public LogId getLogId() {
                    return transport.getLogId();
                }

                @Override
                public ListenableFuture<Channelz.SocketStats> getStats() {
                    return transport.getStats();
                }
            };
        }
        if (!result2.getStatus().isOk()) {
            if (result2.isDrop()) {
                return new FailingClientTransport(result2.getStatus(), ClientStreamListener.RpcProgress.DROPPED);
            }
            if (!isWaitForReady) {
                return new FailingClientTransport(result2.getStatus(), ClientStreamListener.RpcProgress.PROCESSED);
            }
        }
        return null;
    }

    static void closeQuietly(StreamListener.MessageProducer producer) {
        InputStream message;
        while ((message = producer.next()) != null) {
            GrpcUtil.closeQuietly(message);
        }
    }

    public static void closeQuietly(@Nullable InputStream message) {
        if (message == null) {
            return;
        }
        try {
            message.close();
        }
        catch (IOException ioException) {
            log.log(Level.WARNING, "exception caught in closeQuietly", ioException);
        }
    }

    static <T> boolean iterableContains(Iterable<T> iterable, T item) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection)iterable;
            try {
                return collection.contains(item);
            }
            catch (NullPointerException e) {
                return false;
            }
            catch (ClassCastException e) {
                return false;
            }
        }
        for (T i : iterable) {
            if (!Objects.equal(i, item)) continue;
            return true;
        }
        return false;
    }

    private GrpcUtil() {
    }

    @VisibleForTesting
    static class TimeoutMarshaller
    implements Metadata.AsciiMarshaller<Long> {
        TimeoutMarshaller() {
        }

        @Override
        public String toAsciiString(Long timeoutNanos) {
            long cutoff = 100000000L;
            TimeUnit unit = TimeUnit.NANOSECONDS;
            if (timeoutNanos < 0L) {
                throw new IllegalArgumentException("Timeout too small");
            }
            if (timeoutNanos < cutoff) {
                return timeoutNanos + "n";
            }
            if (timeoutNanos < cutoff * 1000L) {
                return unit.toMicros(timeoutNanos) + "u";
            }
            if (timeoutNanos < cutoff * 1000L * 1000L) {
                return unit.toMillis(timeoutNanos) + "m";
            }
            if (timeoutNanos < cutoff * 1000L * 1000L * 1000L) {
                return unit.toSeconds(timeoutNanos) + "S";
            }
            if (timeoutNanos < cutoff * 1000L * 1000L * 1000L * 60L) {
                return unit.toMinutes(timeoutNanos) + "M";
            }
            return unit.toHours(timeoutNanos) + "H";
        }

        @Override
        public Long parseAsciiString(String serialized) {
            Preconditions.checkArgument(serialized.length() > 0, "empty timeout");
            Preconditions.checkArgument(serialized.length() <= 9, "bad timeout format");
            long value = Long.parseLong(serialized.substring(0, serialized.length() - 1));
            char unit = serialized.charAt(serialized.length() - 1);
            switch (unit) {
                case 'n': {
                    return value;
                }
                case 'u': {
                    return TimeUnit.MICROSECONDS.toNanos(value);
                }
                case 'm': {
                    return TimeUnit.MILLISECONDS.toNanos(value);
                }
                case 'S': {
                    return TimeUnit.SECONDS.toNanos(value);
                }
                case 'M': {
                    return TimeUnit.MINUTES.toNanos(value);
                }
                case 'H': {
                    return TimeUnit.HOURS.toNanos(value);
                }
            }
            throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", Character.valueOf(unit)));
        }
    }

    public static enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);

        private static final Http2Error[] codeMap;
        private final int code;
        private final Status status;

        private static Http2Error[] buildHttp2CodeMap() {
            Http2Error[] errors = Http2Error.values();
            int size2 = (int)errors[errors.length - 1].code() + 1;
            Http2Error[] http2CodeMap = new Http2Error[size2];
            for (Http2Error error2 : errors) {
                int index = (int)error2.code();
                http2CodeMap[index] = error2;
            }
            return http2CodeMap;
        }

        private Http2Error(int code, Status status) {
            this.code = code;
            this.status = status.augmentDescription("HTTP/2 error code: " + this.name());
        }

        public long code() {
            return this.code;
        }

        public Status status() {
            return this.status;
        }

        public static Http2Error forCode(long code) {
            if (code >= (long)codeMap.length || code < 0L) {
                return null;
            }
            return codeMap[(int)code];
        }

        public static Status statusForCode(long code) {
            Http2Error error2 = Http2Error.forCode(code);
            if (error2 == null) {
                Status.Code statusCode = INTERNAL_ERROR.status().getCode();
                return Status.fromCodeValue(statusCode.value()).withDescription("Unrecognized HTTP/2 error code: " + code);
            }
            return error2.status();
        }

        static {
            codeMap = Http2Error.buildHttp2CodeMap();
        }
    }

    private static final class AcceptEncodingMarshaller
    implements InternalMetadata.TrustedAsciiMarshaller<byte[]> {
        private AcceptEncodingMarshaller() {
        }

        @Override
        public byte[] toAsciiString(byte[] value) {
            return value;
        }

        @Override
        public byte[] parseAsciiString(byte[] serialized) {
            return serialized;
        }
    }
}

