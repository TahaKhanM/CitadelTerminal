/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public abstract class StateCheckingResponseObserver<V>
implements ResponseObserver<V> {
    private boolean isStarted;
    private boolean isClosed;

    @Override
    public final void onStart(StreamController controller) {
        Preconditions.checkState(!this.isStarted, this.getClass() + " is already started.");
        this.isStarted = true;
        this.onStartImpl(controller);
    }

    @Override
    public final void onResponse(V response) {
        Preconditions.checkState(!this.isClosed, this.getClass() + " received a response after being closed.");
        this.onResponseImpl(response);
    }

    @Override
    public final void onComplete() {
        Preconditions.checkState(!this.isClosed, this.getClass() + " tried to double close.");
        this.isClosed = true;
        this.onCompleteImpl();
    }

    @Override
    public final void onError(Throwable t) {
        Preconditions.checkState(!this.isClosed, this.getClass() + " received error after being closed", (Object)t);
        this.isClosed = true;
        this.onErrorImpl(t);
    }

    protected abstract void onStartImpl(StreamController var1);

    protected abstract void onResponseImpl(V var1);

    protected abstract void onErrorImpl(Throwable var1);

    protected abstract void onCompleteImpl();
}

