/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface ClientStream<RequestT> {
    public void send(RequestT var1);

    public void closeSendWithError(Throwable var1);

    public void closeSend();

    public boolean isSendReady();
}

