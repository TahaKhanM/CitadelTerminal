/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class ClientCalls {
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());

    private ClientCalls() {
    }

    public static <ReqT, RespT> void asyncUnaryCall(ClientCall<ReqT, RespT> call, ReqT param2, StreamObserver<RespT> observer) {
        ClientCalls.asyncUnaryRequestCall(call, param2, observer, false);
    }

    public static <ReqT, RespT> void asyncServerStreamingCall(ClientCall<ReqT, RespT> call, ReqT param2, StreamObserver<RespT> responseObserver) {
        ClientCalls.asyncUnaryRequestCall(call, param2, responseObserver, true);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncClientStreamingCall(ClientCall<ReqT, RespT> call, StreamObserver<RespT> responseObserver) {
        return ClientCalls.asyncStreamingRequestCall(call, responseObserver, false);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncBidiStreamingCall(ClientCall<ReqT, RespT> call, StreamObserver<RespT> responseObserver) {
        return ClientCalls.asyncStreamingRequestCall(call, responseObserver, true);
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(ClientCall<ReqT, RespT> call, ReqT param2) {
        try {
            return ClientCalls.getUnchecked(ClientCalls.futureUnaryCall(call, param2));
        }
        catch (RuntimeException e) {
            throw ClientCalls.cancelThrow(call, e);
        }
        catch (Error e) {
            throw ClientCalls.cancelThrow(call, e);
        }
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(Channel channel, MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, ReqT param2) {
        ThreadlessExecutor executor = new ThreadlessExecutor();
        ClientCall<ReqT, RespT> call = channel.newCall(method, callOptions.withExecutor(executor));
        try {
            ListenableFuture<RespT> responseFuture = ClientCalls.futureUnaryCall(call, param2);
            while (!responseFuture.isDone()) {
                try {
                    executor.waitAndDrain();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw Status.CANCELLED.withDescription("Call was interrupted").withCause(e).asRuntimeException();
                }
            }
            return ClientCalls.getUnchecked(responseFuture);
        }
        catch (RuntimeException e) {
            throw ClientCalls.cancelThrow(call, e);
        }
        catch (Error e) {
            throw ClientCalls.cancelThrow(call, e);
        }
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(ClientCall<ReqT, RespT> call, ReqT param2) {
        BlockingResponseStream<RespT> result2 = new BlockingResponseStream<RespT>(call);
        ClientCalls.asyncUnaryRequestCall(call, param2, result2.listener(), true);
        return result2;
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(Channel channel, MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, ReqT param2) {
        ThreadlessExecutor executor = new ThreadlessExecutor();
        ClientCall<ReqT, RespT> call = channel.newCall(method, callOptions.withExecutor(executor));
        BlockingResponseStream<RespT> result2 = new BlockingResponseStream<RespT>(call, executor);
        ClientCalls.asyncUnaryRequestCall(call, param2, result2.listener(), true);
        return result2;
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> call, ReqT param2) {
        GrpcFuture<RespT> responseFuture = new GrpcFuture<RespT>(call);
        ClientCalls.asyncUnaryRequestCall(call, param2, new UnaryStreamToFuture<RespT>(responseFuture), false);
        return responseFuture;
    }

    private static <V> V getUnchecked(Future<V> future) {
        try {
            return future.get();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Call was interrupted").withCause(e).asRuntimeException();
        }
        catch (ExecutionException e) {
            throw ClientCalls.toStatusRuntimeException(e.getCause());
        }
    }

    private static StatusRuntimeException toStatusRuntimeException(Throwable t) {
        for (Throwable cause = Preconditions.checkNotNull(t, "t"); cause != null; cause = cause.getCause()) {
            if (cause instanceof StatusException) {
                StatusException se = (StatusException)cause;
                return new StatusRuntimeException(se.getStatus(), se.getTrailers());
            }
            if (!(cause instanceof StatusRuntimeException)) continue;
            StatusRuntimeException se = (StatusRuntimeException)cause;
            return new StatusRuntimeException(se.getStatus(), se.getTrailers());
        }
        return Status.UNKNOWN.withDescription("unexpected exception").withCause(t).asRuntimeException();
    }

    private static RuntimeException cancelThrow(ClientCall<?, ?> call, Throwable t) {
        try {
            call.cancel(null, t);
        }
        catch (Throwable e) {
            assert (e instanceof RuntimeException || e instanceof Error);
            logger.log(Level.SEVERE, "RuntimeException encountered while closing call", e);
        }
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
        throw new AssertionError((Object)t);
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> call, ReqT param2, StreamObserver<RespT> responseObserver, boolean streamingResponse) {
        ClientCalls.asyncUnaryRequestCall(call, param2, new StreamObserverToCallListenerAdapter<ReqT, RespT>(responseObserver, new CallToStreamObserverAdapter<ReqT>(call), streamingResponse), streamingResponse);
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> call, ReqT param2, ClientCall.Listener<RespT> responseListener, boolean streamingResponse) {
        ClientCalls.startCall(call, responseListener, streamingResponse);
        try {
            call.sendMessage(param2);
            call.halfClose();
        }
        catch (RuntimeException e) {
            throw ClientCalls.cancelThrow(call, e);
        }
        catch (Error e) {
            throw ClientCalls.cancelThrow(call, e);
        }
    }

    private static <ReqT, RespT> StreamObserver<ReqT> asyncStreamingRequestCall(ClientCall<ReqT, RespT> call, StreamObserver<RespT> responseObserver, boolean streamingResponse) {
        CallToStreamObserverAdapter<ReqT> adapter = new CallToStreamObserverAdapter<ReqT>(call);
        ClientCalls.startCall(call, new StreamObserverToCallListenerAdapter<ReqT, RespT>(responseObserver, adapter, streamingResponse), streamingResponse);
        return adapter;
    }

    private static <ReqT, RespT> void startCall(ClientCall<ReqT, RespT> call, ClientCall.Listener<RespT> responseListener, boolean streamingResponse) {
        call.start(responseListener, new Metadata());
        if (streamingResponse) {
            call.request(1);
        } else {
            call.request(2);
        }
    }

    private static final class ThreadlessExecutor
    implements Executor {
        private static final Logger log = Logger.getLogger(ThreadlessExecutor.class.getName());
        private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

        ThreadlessExecutor() {
        }

        public void waitAndDrain() throws InterruptedException {
            Runnable runnable = this.queue.take();
            while (runnable != null) {
                try {
                    runnable.run();
                }
                catch (Throwable t) {
                    log.log(Level.WARNING, "Runnable threw exception", t);
                }
                runnable = (Runnable)this.queue.poll();
            }
        }

        @Override
        public void execute(Runnable runnable) {
            this.queue.add(runnable);
        }
    }

    private static final class BlockingResponseStream<T>
    implements Iterator<T> {
        private final BlockingQueue<Object> buffer = new ArrayBlockingQueue<Object>(2);
        private final ClientCall.Listener<T> listener = new QueuingListener();
        private final ClientCall<?, T> call;
        private final ThreadlessExecutor threadless;
        private Object last;

        BlockingResponseStream(ClientCall<?, T> call) {
            this(call, null);
        }

        BlockingResponseStream(ClientCall<?, T> call, ThreadlessExecutor threadless) {
            this.call = call;
            this.threadless = threadless;
        }

        ClientCall.Listener<T> listener() {
            return this.listener;
        }

        private Object waitForNext() throws InterruptedException {
            if (this.threadless == null) {
                return this.buffer.take();
            }
            Object next2 = this.buffer.poll();
            while (next2 == null) {
                this.threadless.waitAndDrain();
                next2 = this.buffer.poll();
            }
            return next2;
        }

        @Override
        public boolean hasNext() {
            if (this.last == null) {
                try {
                    this.last = this.waitForNext();
                }
                catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw Status.CANCELLED.withDescription("interrupted").withCause(ie).asRuntimeException();
                }
            }
            if (this.last instanceof StatusRuntimeException) {
                StatusRuntimeException e = (StatusRuntimeException)this.last;
                throw e.getStatus().asRuntimeException(e.getTrailers());
            }
            return this.last != this;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            try {
                Object tmp;
                this.call.request(1);
                Object object = tmp = this.last;
                return (T)object;
            }
            finally {
                this.last = null;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private final class QueuingListener
        extends ClientCall.Listener<T> {
            private boolean done = false;

            QueuingListener() {
            }

            @Override
            public void onHeaders(Metadata headers) {
            }

            @Override
            public void onMessage(T value) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                BlockingResponseStream.this.buffer.add(value);
            }

            @Override
            public void onClose(Status status, Metadata trailers) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                if (status.isOk()) {
                    BlockingResponseStream.this.buffer.add(BlockingResponseStream.this);
                } else {
                    BlockingResponseStream.this.buffer.add(status.asRuntimeException(trailers));
                }
                this.done = true;
            }
        }
    }

    private static final class GrpcFuture<RespT>
    extends AbstractFuture<RespT> {
        private final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> call) {
            this.call = call;
        }

        @Override
        protected void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", null);
        }

        @Override
        protected boolean set(@Nullable RespT resp) {
            return super.set(resp);
        }

        @Override
        protected boolean setException(Throwable throwable) {
            return super.setException(throwable);
        }

        protected String pendingToString() {
            return MoreObjects.toStringHelper(this).add("clientCall", this.call).toString();
        }
    }

    private static final class UnaryStreamToFuture<RespT>
    extends ClientCall.Listener<RespT> {
        private final GrpcFuture<RespT> responseFuture;
        private RespT value;

        UnaryStreamToFuture(GrpcFuture<RespT> responseFuture) {
            this.responseFuture = responseFuture;
        }

        @Override
        public void onHeaders(Metadata headers) {
        }

        @Override
        public void onMessage(RespT value) {
            if (this.value != null) {
                throw Status.INTERNAL.withDescription("More than one value received for unary call").asRuntimeException();
            }
            this.value = value;
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            if (status.isOk()) {
                if (this.value == null) {
                    this.responseFuture.setException(Status.INTERNAL.withDescription("No value received for unary call").asRuntimeException(trailers));
                }
                this.responseFuture.set(this.value);
            } else {
                this.responseFuture.setException(status.asRuntimeException(trailers));
            }
        }
    }

    private static final class StreamObserverToCallListenerAdapter<ReqT, RespT>
    extends ClientCall.Listener<RespT> {
        private final StreamObserver<RespT> observer;
        private final CallToStreamObserverAdapter<ReqT> adapter;
        private final boolean streamingResponse;
        private boolean firstResponseReceived;

        StreamObserverToCallListenerAdapter(StreamObserver<RespT> observer, CallToStreamObserverAdapter<ReqT> adapter, boolean streamingResponse) {
            this.observer = observer;
            this.streamingResponse = streamingResponse;
            this.adapter = adapter;
            if (observer instanceof ClientResponseObserver) {
                ClientResponseObserver clientResponseObserver = (ClientResponseObserver)observer;
                clientResponseObserver.beforeStart(adapter);
            }
            ((CallToStreamObserverAdapter)adapter).freeze();
        }

        @Override
        public void onHeaders(Metadata headers) {
        }

        @Override
        public void onMessage(RespT message) {
            if (this.firstResponseReceived && !this.streamingResponse) {
                throw Status.INTERNAL.withDescription("More than one responses received for unary or client-streaming call").asRuntimeException();
            }
            this.firstResponseReceived = true;
            this.observer.onNext(message);
            if (this.streamingResponse && ((CallToStreamObserverAdapter)this.adapter).autoFlowControlEnabled) {
                this.adapter.request(1);
            }
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            if (status.isOk()) {
                this.observer.onCompleted();
            } else {
                this.observer.onError(status.asRuntimeException(trailers));
            }
        }

        @Override
        public void onReady() {
            if (((CallToStreamObserverAdapter)this.adapter).onReadyHandler != null) {
                ((CallToStreamObserverAdapter)this.adapter).onReadyHandler.run();
            }
        }
    }

    private static final class CallToStreamObserverAdapter<T>
    extends ClientCallStreamObserver<T> {
        private boolean frozen;
        private final ClientCall<T, ?> call;
        private Runnable onReadyHandler;
        private boolean autoFlowControlEnabled = true;

        CallToStreamObserverAdapter(ClientCall<T, ?> call) {
            this.call = call;
        }

        private void freeze() {
            this.frozen = true;
        }

        @Override
        public void onNext(T value) {
            this.call.sendMessage(value);
        }

        @Override
        public void onError(Throwable t) {
            this.call.cancel("Cancelled by client with StreamObserver.onError()", t);
        }

        @Override
        public void onCompleted() {
            this.call.halfClose();
        }

        @Override
        public boolean isReady() {
            return this.call.isReady();
        }

        @Override
        public void setOnReadyHandler(Runnable onReadyHandler) {
            if (this.frozen) {
                throw new IllegalStateException("Cannot alter onReadyHandler after call started");
            }
            this.onReadyHandler = onReadyHandler;
        }

        @Override
        public void disableAutoInboundFlowControl() {
            if (this.frozen) {
                throw new IllegalStateException("Cannot disable auto flow control call started");
            }
            this.autoFlowControlEnabled = false;
        }

        @Override
        public void request(int count2) {
            this.call.request(count2);
        }

        @Override
        public void setMessageCompression(boolean enable) {
            this.call.setMessageCompression(enable);
        }

        @Override
        public void cancel(@Nullable String message, @Nullable Throwable cause) {
            this.call.cancel(message, cause);
        }
    }
}

