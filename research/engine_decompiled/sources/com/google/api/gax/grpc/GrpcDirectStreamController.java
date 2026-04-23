/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.concurrent.CancellationException;

class GrpcDirectStreamController<RequestT, ResponseT>
implements StreamController {
    private static final Runnable NOOP_RUNNABLE = new Runnable(){

        @Override
        public void run() {
        }
    };
    private final ClientCall<RequestT, ResponseT> clientCall;
    private final ResponseObserver<ResponseT> responseObserver;
    private final Runnable onReady;
    private boolean hasStarted;
    private boolean autoflowControl = true;
    private int numRequested;
    private volatile CancellationException cancellationException;

    GrpcDirectStreamController(ClientCall<RequestT, ResponseT> clientCall, ResponseObserver<ResponseT> responseObserver) {
        this(clientCall, responseObserver, NOOP_RUNNABLE);
    }

    GrpcDirectStreamController(ClientCall<RequestT, ResponseT> clientCall, ResponseObserver<ResponseT> responseObserver, Runnable onReady) {
        this.clientCall = clientCall;
        this.responseObserver = responseObserver;
        this.onReady = onReady;
    }

    @Override
    public void cancel() {
        this.cancellationException = new CancellationException("User cancelled stream");
        this.clientCall.cancel(null, this.cancellationException);
    }

    @Override
    public void disableAutoInboundFlowControl() {
        Preconditions.checkState(!this.hasStarted, "Can't disable automatic flow control after the stream has started.");
        this.autoflowControl = false;
    }

    @Override
    public void request(int count2) {
        Preconditions.checkState(!this.autoflowControl, "Autoflow control is enabled.");
        if (!this.hasStarted) {
            this.numRequested += count2;
        } else {
            this.clientCall.request(count2);
        }
    }

    void start(RequestT request) {
        this.startCommon();
        this.clientCall.sendMessage(request);
        this.clientCall.halfClose();
    }

    void startBidi() {
        this.startCommon();
    }

    private void startCommon() {
        this.responseObserver.onStart(this);
        this.hasStarted = true;
        this.clientCall.start(new ResponseObserverAdapter(), new Metadata());
        if (this.autoflowControl) {
            this.clientCall.request(1);
        } else if (this.numRequested > 0) {
            this.clientCall.request(this.numRequested);
        }
    }

    private class ResponseObserverAdapter
    extends ClientCall.Listener<ResponseT> {
        private ResponseObserverAdapter() {
        }

        @Override
        public void onMessage(ResponseT message) {
            GrpcDirectStreamController.this.responseObserver.onResponse(message);
            if (GrpcDirectStreamController.this.autoflowControl) {
                GrpcDirectStreamController.this.clientCall.request(1);
            }
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            if (status.isOk()) {
                GrpcDirectStreamController.this.responseObserver.onComplete();
            } else if (GrpcDirectStreamController.this.cancellationException != null) {
                GrpcDirectStreamController.this.responseObserver.onError(GrpcDirectStreamController.this.cancellationException);
            } else {
                GrpcDirectStreamController.this.responseObserver.onError(status.asRuntimeException(trailers));
            }
        }

        @Override
        public void onReady() {
            GrpcDirectStreamController.this.onReady.run();
        }
    }
}

