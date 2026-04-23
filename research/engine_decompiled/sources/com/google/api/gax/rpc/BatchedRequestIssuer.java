/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.BatchedFuture;
import com.google.common.base.Preconditions;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class BatchedRequestIssuer<ResponseT> {
    private final BatchedFuture<ResponseT> batchedFuture;
    private final long messageCount;
    private ResponseT responseToSend;
    private boolean hasResponse;
    private Throwable throwableToSend;

    public BatchedRequestIssuer(BatchedFuture<ResponseT> batchedFuture, long messageCount) {
        this.batchedFuture = batchedFuture;
        this.messageCount = messageCount;
        this.responseToSend = null;
        this.throwableToSend = null;
    }

    public long getMessageCount() {
        return this.messageCount;
    }

    public void setResponse(ResponseT response) {
        Preconditions.checkState(this.throwableToSend == null, "Cannot set both exception and response");
        this.hasResponse = true;
        this.responseToSend = response;
    }

    public void setException(Throwable throwable) {
        Preconditions.checkState(!this.hasResponse, "Cannot set both exception and response");
        this.throwableToSend = throwable;
    }

    public void sendResult() {
        if (this.hasResponse) {
            this.batchedFuture.set(this.responseToSend);
        } else if (this.throwableToSend != null) {
            this.batchedFuture.setException(this.throwableToSend);
        } else {
            throw new IllegalStateException("Neither response nor exception were set in BatchedRequestIssuer");
        }
    }
}

