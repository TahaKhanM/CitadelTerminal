/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.internal.CallTracer;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.MoreThrowables;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServerCallImpl<ReqT, RespT>
extends ServerCall<ReqT, RespT> {
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    @VisibleForTesting
    static final String TOO_MANY_RESPONSES = "Too many responses";
    @VisibleForTesting
    static final String MISSING_RESPONSE = "Completed without a response";
    private final ServerStream stream;
    private final MethodDescriptor<ReqT, RespT> method;
    private final Context.CancellableContext context;
    private final byte[] messageAcceptEncoding;
    private final DecompressorRegistry decompressorRegistry;
    private final CompressorRegistry compressorRegistry;
    private CallTracer serverCallTracer;
    private volatile boolean cancelled;
    private boolean sendHeadersCalled;
    private boolean closeCalled;
    private Compressor compressor;
    private boolean messageSent;

    ServerCallImpl(ServerStream stream, MethodDescriptor<ReqT, RespT> method, Metadata inboundHeaders, Context.CancellableContext context, DecompressorRegistry decompressorRegistry, CompressorRegistry compressorRegistry, CallTracer serverCallTracer) {
        this.stream = stream;
        this.method = method;
        this.context = context;
        this.messageAcceptEncoding = inboundHeaders.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry;
        this.compressorRegistry = compressorRegistry;
        this.serverCallTracer = serverCallTracer;
        this.serverCallTracer.reportCallStarted();
    }

    @Override
    public void request(int numMessages) {
        this.stream.request(numMessages);
    }

    @Override
    public void sendHeaders(Metadata headers) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        headers.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (this.messageAcceptEncoding != null) {
            if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
                this.compressor = Codec.Identity.NONE;
            }
        } else {
            this.compressor = Codec.Identity.NONE;
        }
        headers.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        headers.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] advertisedEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (advertisedEncodings.length != 0) {
            headers.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, advertisedEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(headers);
    }

    @Override
    public void sendMessage(RespT message) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (this.method.getType().serverSendsOneMessage() && this.messageSent) {
            this.internalClose(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES));
            return;
        }
        this.messageSent = true;
        try {
            InputStream resp = this.method.streamResponse(message);
            this.stream.writeMessage(resp);
            this.stream.flush();
        }
        catch (RuntimeException e) {
            this.close(Status.fromThrowable(e), new Metadata());
        }
        catch (Error e) {
            this.close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
            throw e;
        }
    }

    @Override
    public void setMessageCompression(boolean enable) {
        this.stream.setMessageCompression(enable);
    }

    @Override
    public void setCompression(String compressorName) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        this.compressor = this.compressorRegistry.lookupCompressor(compressorName);
        Preconditions.checkArgument(this.compressor != null, "Unable to find compressor by name %s", (Object)compressorName);
    }

    @Override
    public boolean isReady() {
        return this.stream.isReady();
    }

    @Override
    public void close(Status status, Metadata trailers) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (status.isOk() && this.method.getType().serverSendsOneMessage() && !this.messageSent) {
                this.internalClose(Status.INTERNAL.withDescription(MISSING_RESPONSE));
                return;
            }
            this.stream.close(status, trailers);
        }
        finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    ServerStreamListener newServerStreamListener(ServerCall.Listener<ReqT> listener) {
        return new ServerStreamListenerImpl<ReqT>(this, listener, this.context);
    }

    @Override
    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    @Override
    public String getAuthority() {
        return this.stream.getAuthority();
    }

    @Override
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    private void internalClose(Status internalError) {
        log.log(Level.WARNING, "Cancelling the stream with status {0}", new Object[]{internalError});
        this.stream.cancel(internalError);
        this.serverCallTracer.reportCallEnded(internalError.isOk());
    }

    @VisibleForTesting
    static final class ServerStreamListenerImpl<ReqT>
    implements ServerStreamListener {
        private final ServerCallImpl<ReqT, ?> call;
        private final ServerCall.Listener<ReqT> listener;
        private final Context.CancellableContext context;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> call, ServerCall.Listener<ReqT> listener, Context.CancellableContext context) {
            this.call = Preconditions.checkNotNull(call, "call");
            this.listener = Preconditions.checkNotNull(listener, "listener must not be null");
            this.context = Preconditions.checkNotNull(context, "context");
            this.context.addListener(new Context.CancellationListener(){

                @Override
                public void cancelled(Context context) {
                    ServerStreamListenerImpl.this.call.cancelled = true;
                }
            }, MoreExecutors.directExecutor());
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            if (((ServerCallImpl)this.call).cancelled) {
                GrpcUtil.closeQuietly(producer);
                return;
            }
            try {
                InputStream message;
                while ((message = producer.next()) != null) {
                    try {
                        this.listener.onMessage(((ServerCallImpl)this.call).method.parseRequest(message));
                    }
                    catch (Throwable t) {
                        GrpcUtil.closeQuietly(message);
                        throw t;
                    }
                    message.close();
                }
            }
            catch (Throwable t) {
                GrpcUtil.closeQuietly(producer);
                MoreThrowables.throwIfUnchecked(t);
                throw new RuntimeException(t);
            }
        }

        @Override
        public void halfClosed() {
            if (((ServerCallImpl)this.call).cancelled) {
                return;
            }
            this.listener.onHalfClose();
        }

        @Override
        public void closed(Status status) {
            try {
                if (status.isOk()) {
                    this.listener.onComplete();
                } else {
                    ((ServerCallImpl)this.call).cancelled = true;
                    this.listener.onCancel();
                }
            }
            finally {
                this.context.cancel(null);
            }
        }

        @Override
        public void onReady() {
            if (((ServerCallImpl)this.call).cancelled) {
                return;
            }
            this.listener.onReady();
        }
    }
}

