/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Set;
import java.util.concurrent.CancellationException;

class GrpcExceptionCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final GrpcApiExceptionFactory exceptionFactory;

    GrpcExceptionCallable(UnaryCallable<RequestT, ResponseT> callable, Set<StatusCode.Code> retryableCodes) {
        this.callable = Preconditions.checkNotNull(callable);
        this.exceptionFactory = new GrpcApiExceptionFactory(retryableCodes);
    }

    @Override
    public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext inputContext) {
        GrpcCallContext context = GrpcCallContext.createDefault().nullToSelf(inputContext);
        ApiFuture<ResponseT> innerCallFuture = this.callable.futureCall(request, context);
        ExceptionTransformingFuture transformingFuture = new ExceptionTransformingFuture(innerCallFuture);
        ApiFutures.addCallback(innerCallFuture, transformingFuture, MoreExecutors.directExecutor());
        return transformingFuture;
    }

    private class ExceptionTransformingFuture
    extends AbstractApiFuture<ResponseT>
    implements ApiFutureCallback<ResponseT> {
        private ApiFuture<ResponseT> innerCallFuture;
        private volatile boolean cancelled = false;

        public ExceptionTransformingFuture(ApiFuture<ResponseT> innerCallFuture) {
            this.innerCallFuture = innerCallFuture;
        }

        @Override
        protected void interruptTask() {
            this.cancelled = true;
            this.innerCallFuture.cancel(true);
        }

        @Override
        public void onSuccess(ResponseT r) {
            super.set(r);
        }

        @Override
        public void onFailure(Throwable throwable) {
            if (throwable instanceof CancellationException && this.cancelled) {
                return;
            }
            this.setException(GrpcExceptionCallable.this.exceptionFactory.create(throwable));
        }
    }
}

