/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ExperimentalApi;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.stub.AbstractStub;
import java.util.concurrent.atomic.AtomicReference;

public final class MetadataUtils {
    private MetadataUtils() {
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1789")
    public static <T extends AbstractStub<T>> T attachHeaders(T stub, Metadata extraHeaders) {
        return stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(extraHeaders));
    }

    public static ClientInterceptor newAttachHeadersInterceptor(Metadata extraHeaders) {
        return new HeaderAttachingClientInterceptor(extraHeaders);
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1789")
    public static <T extends AbstractStub<T>> T captureMetadata(T stub, AtomicReference<Metadata> headersCapture, AtomicReference<Metadata> trailersCapture) {
        return stub.withInterceptors(MetadataUtils.newCaptureMetadataInterceptor(headersCapture, trailersCapture));
    }

    public static ClientInterceptor newCaptureMetadataInterceptor(AtomicReference<Metadata> headersCapture, AtomicReference<Metadata> trailersCapture) {
        return new MetadataCapturingClientInterceptor(headersCapture, trailersCapture);
    }

    private static final class MetadataCapturingClientInterceptor
    implements ClientInterceptor {
        final AtomicReference<Metadata> headersCapture;
        final AtomicReference<Metadata> trailersCapture;

        MetadataCapturingClientInterceptor(AtomicReference<Metadata> headersCapture, AtomicReference<Metadata> trailersCapture) {
            this.headersCapture = Preconditions.checkNotNull(headersCapture, "headersCapture");
            this.trailersCapture = Preconditions.checkNotNull(trailersCapture, "trailersCapture");
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
            return new MetadataCapturingClientCall<ReqT, RespT>(next2.newCall(method, callOptions));
        }

        private final class MetadataCapturingClientCall<ReqT, RespT>
        extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            MetadataCapturingClientCall(ClientCall<ReqT, RespT> call) {
                super(call);
            }

            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                MetadataCapturingClientInterceptor.this.headersCapture.set(null);
                MetadataCapturingClientInterceptor.this.trailersCapture.set(null);
                super.start(new MetadataCapturingClientCallListener(responseListener), headers);
            }

            private final class MetadataCapturingClientCallListener
            extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
                MetadataCapturingClientCallListener(ClientCall.Listener<RespT> responseListener) {
                    super(responseListener);
                }

                @Override
                public void onHeaders(Metadata headers) {
                    MetadataCapturingClientInterceptor.this.headersCapture.set(headers);
                    super.onHeaders(headers);
                }

                @Override
                public void onClose(Status status, Metadata trailers) {
                    MetadataCapturingClientInterceptor.this.trailersCapture.set(trailers);
                    super.onClose(status, trailers);
                }
            }
        }
    }

    private static final class HeaderAttachingClientInterceptor
    implements ClientInterceptor {
        private final Metadata extraHeaders;

        HeaderAttachingClientInterceptor(Metadata extraHeaders) {
            this.extraHeaders = Preconditions.checkNotNull(extraHeaders, extraHeaders);
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
            return new HeaderAttachingClientCall<ReqT, RespT>(next2.newCall(method, callOptions));
        }

        private final class HeaderAttachingClientCall<ReqT, RespT>
        extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            HeaderAttachingClientCall(ClientCall<ReqT, RespT> call) {
                super(call);
            }

            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                headers.merge(HeaderAttachingClientInterceptor.this.extraHeaders);
                super.start(responseListener, headers);
            }
        }
    }
}

