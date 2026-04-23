/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.StreamController;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface ResponseObserver<V> {
    public void onStart(StreamController var1);

    public void onResponse(V var1);

    public void onError(Throwable var1);

    public void onComplete();
}

