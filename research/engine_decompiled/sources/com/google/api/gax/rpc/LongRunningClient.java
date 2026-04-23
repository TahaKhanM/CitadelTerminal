/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.UnaryCallable;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public interface LongRunningClient {
    public UnaryCallable<String, OperationSnapshot> getOperationCallable();

    public UnaryCallable<String, Void> cancelOperationCallable();

    public UnaryCallable<String, Void> deleteOperationCallable();
}

