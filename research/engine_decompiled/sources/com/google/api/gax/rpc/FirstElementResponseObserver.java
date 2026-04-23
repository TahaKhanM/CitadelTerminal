/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;

class FirstElementResponseObserver<ResponseT>
extends StateCheckingResponseObserver<ResponseT> {
    private final MyFuture future = new MyFuture();
    private StreamController controller;

    FirstElementResponseObserver() {
    }

    @Override
    protected void onStartImpl(StreamController controller) {
        this.controller = controller;
        controller.disableAutoInboundFlowControl();
        controller.request(1);
    }

    @Override
    protected void onResponseImpl(ResponseT response) {
        this.future.set(response);
        this.controller.cancel();
    }

    @Override
    protected void onErrorImpl(Throwable t) {
        this.future.setException(t);
    }

    @Override
    protected void onCompleteImpl() {
        this.future.set(null);
    }

    ApiFuture<ResponseT> getFuture() {
        return this.future;
    }

    private class MyFuture
    extends AbstractApiFuture<ResponseT> {
        private MyFuture() {
        }

        @Override
        protected void interruptTask() {
            FirstElementResponseObserver.this.controller.cancel();
        }

        @Override
        protected boolean set(ResponseT value) {
            return super.set(value);
        }

        @Override
        protected boolean setException(Throwable throwable) {
            return super.setException(throwable);
        }
    }
}

