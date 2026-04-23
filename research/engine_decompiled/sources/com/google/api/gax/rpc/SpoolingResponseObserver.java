/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.collect.Lists;
import java.util.List;

class SpoolingResponseObserver<ResponseT>
extends StateCheckingResponseObserver<ResponseT> {
    private final MyFuture future = new MyFuture();
    private StreamController controller;
    private final List<ResponseT> buffer = Lists.newArrayList();

    SpoolingResponseObserver() {
    }

    ApiFuture<List<ResponseT>> getFuture() {
        return this.future;
    }

    @Override
    protected void onStartImpl(StreamController controller) {
        this.controller = controller;
    }

    @Override
    protected void onResponseImpl(ResponseT response) {
        this.buffer.add(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
        this.future.setException(t);
    }

    @Override
    protected void onCompleteImpl() {
        this.future.set(this.buffer);
    }

    class MyFuture
    extends AbstractApiFuture<List<ResponseT>> {
        MyFuture() {
        }

        @Override
        protected void interruptTask() {
            SpoolingResponseObserver.this.controller.cancel();
        }

        @Override
        protected boolean set(List<ResponseT> value) {
            return super.set(value);
        }

        @Override
        protected boolean setException(Throwable throwable) {
            return super.setException(throwable);
        }
    }
}

