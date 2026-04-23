/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ClientStream;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface ClientStreamReadyObserver<V> {
    public void onReady(ClientStream<V> var1);
}

