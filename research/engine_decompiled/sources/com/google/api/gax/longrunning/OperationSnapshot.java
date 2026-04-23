/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.StatusCode;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public interface OperationSnapshot {
    public String getName();

    public Object getMetadata();

    public boolean isDone();

    public Object getResponse();

    public StatusCode getErrorCode();
}

