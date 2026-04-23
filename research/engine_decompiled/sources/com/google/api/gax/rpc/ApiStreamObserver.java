/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface ApiStreamObserver<V> {
    public void onNext(V var1);

    public void onError(Throwable var1);

    public void onCompleted();
}

