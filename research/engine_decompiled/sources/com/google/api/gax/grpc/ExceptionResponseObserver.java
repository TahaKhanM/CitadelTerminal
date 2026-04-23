/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import java.util.concurrent.CancellationException;

class ExceptionResponseObserver<RequestT, ResponseT>
extends StateCheckingResponseObserver<ResponseT> {
    private ResponseObserver<ResponseT> innerObserver;
    private volatile CancellationException cancellationException;
    private final GrpcApiExceptionFactory exceptionFactory;

    public ExceptionResponseObserver(ResponseObserver<ResponseT> innerObserver, GrpcApiExceptionFactory exceptionFactory) {
        this.innerObserver = innerObserver;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    protected void onStartImpl(final StreamController controller) {
        this.innerObserver.onStart(new StreamController(){

            @Override
            public void cancel() {
                ExceptionResponseObserver.this.cancellationException = new CancellationException("User cancelled stream");
                controller.cancel();
            }

            @Override
            public void disableAutoInboundFlowControl() {
                controller.disableAutoInboundFlowControl();
            }

            @Override
            public void request(int count2) {
                controller.request(count2);
            }
        });
    }

    @Override
    protected void onResponseImpl(ResponseT response) {
        this.innerObserver.onResponse(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
        t = this.cancellationException != null ? this.cancellationException : this.exceptionFactory.create(t);
        this.innerObserver.onError(t);
    }

    @Override
    protected void onCompleteImpl() {
        this.innerObserver.onComplete();
    }
}

