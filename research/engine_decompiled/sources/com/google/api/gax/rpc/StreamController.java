/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface StreamController {
    public void cancel();

    public void disableAutoInboundFlowControl();

    public void request(int var1);
}

