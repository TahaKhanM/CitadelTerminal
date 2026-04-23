/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.LongRunningClient;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;

class OperationCheckingCallable<RequestT>
extends UnaryCallable<RequestT, OperationSnapshot> {
    private final LongRunningClient longRunningClient;
    private final ApiFuture<OperationSnapshot> initialFuture;

    OperationCheckingCallable(LongRunningClient longRunningClient, ApiFuture<OperationSnapshot> initialFuture) {
        this.longRunningClient = Preconditions.checkNotNull(longRunningClient);
        this.initialFuture = Preconditions.checkNotNull(initialFuture);
    }

    @Override
    public ApiFuture<OperationSnapshot> futureCall(RequestT request, ApiCallContext context) {
        try {
            if (!this.initialFuture.isDone() || this.initialFuture.isCancelled()) {
                return this.initialFuture;
            }
            OperationSnapshot initialOperation = (OperationSnapshot)this.initialFuture.get();
            if (initialOperation.isDone()) {
                return this.initialFuture;
            }
            return this.longRunningClient.getOperationCallable().futureCall(initialOperation.getName(), null);
        }
        catch (ExecutionException e) {
            return ApiFutures.immediateFailedFuture(e.getCause());
        }
        catch (InterruptedException e) {
            return ApiFutures.immediateFailedFuture(e);
        }
    }
}

