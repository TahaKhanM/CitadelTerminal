/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingFuture;
import java.util.concurrent.ExecutionException;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public interface OperationFuture<ResponseT, MetadataT>
extends ApiFuture<ResponseT> {
    public String getName() throws InterruptedException, ExecutionException;

    public ApiFuture<OperationSnapshot> getInitialFuture();

    public RetryingFuture<OperationSnapshot> getPollingFuture();

    public ApiFuture<MetadataT> peekMetadata();

    public ApiFuture<MetadataT> getMetadata();
}

