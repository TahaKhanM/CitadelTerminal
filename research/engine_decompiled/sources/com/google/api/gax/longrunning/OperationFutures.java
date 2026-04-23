/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public class OperationFutures {
    private OperationFutures() {
    }

    public static final <ResponseT, MetadataT> OperationFuture<ResponseT, MetadataT> immediateOperationFuture(final OperationSnapshot completedSnapshot) {
        Preconditions.checkArgument(completedSnapshot.isDone(), "given snapshot must already be completed");
        final ApiFuture<Object> metadataFuture = ApiFutures.immediateFuture(completedSnapshot.getMetadata());
        final ApiFuture<OperationSnapshot> initialFuture = ApiFutures.immediateFuture(completedSnapshot);
        return new OperationFuture<ResponseT, MetadataT>(){

            @Override
            public String getName() {
                return completedSnapshot.getName();
            }

            @Override
            public ApiFuture<MetadataT> getMetadata() {
                return metadataFuture;
            }

            @Override
            public ApiFuture<MetadataT> peekMetadata() {
                return metadataFuture;
            }

            @Override
            public ApiFuture<OperationSnapshot> getInitialFuture() {
                return initialFuture;
            }

            @Override
            public RetryingFuture<OperationSnapshot> getPollingFuture() {
                throw new UnsupportedOperationException("Not implemented: getPollingFuture().");
            }

            @Override
            public void addListener(Runnable runnable, Executor executor) {
                initialFuture.addListener(runnable, executor);
            }

            @Override
            public ResponseT get(long time, TimeUnit unit) throws ExecutionException {
                return this.get();
            }

            @Override
            public ResponseT get() throws ExecutionException {
                if (completedSnapshot.getErrorCode().getCode().equals((Object)StatusCode.Code.OK)) {
                    return completedSnapshot.getResponse();
                }
                throw new ExecutionException(new ApiException(null, completedSnapshot.getErrorCode(), false));
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean cancel(boolean b) {
                return false;
            }
        };
    }
}

