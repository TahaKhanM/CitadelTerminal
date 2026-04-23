/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.collect.Queues;
import java.util.concurrent.BlockingQueue;

final class QueuingResponseObserver<V>
extends StateCheckingResponseObserver<V> {
    static final Object EOF_MARKER = new Object();
    private final BlockingQueue<Object> buffer = Queues.newArrayBlockingQueue(2);
    private StreamController controller;
    private boolean isCancelled;

    QueuingResponseObserver() {
    }

    void request() {
        this.controller.request(1);
    }

    Object getNext() throws InterruptedException {
        if (this.isCancelled) {
            return EOF_MARKER;
        }
        return this.buffer.take();
    }

    boolean isReady() {
        return this.isCancelled || !this.buffer.isEmpty();
    }

    void cancel() {
        this.isCancelled = true;
        this.controller.cancel();
    }

    @Override
    protected void onStartImpl(StreamController controller) {
        this.controller = controller;
        controller.disableAutoInboundFlowControl();
        controller.request(1);
    }

    @Override
    protected void onResponseImpl(V response) {
        this.buffer.add(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
        this.buffer.add(t);
    }

    @Override
    protected void onCompleteImpl() {
        this.buffer.add(EOF_MARKER);
    }
}

