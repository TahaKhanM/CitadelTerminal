/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.InternalApi;

@InternalApi
public final class NonCancellableFuture<ResponseT>
extends AbstractApiFuture<ResponseT> {
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    void cancelPrivately() {
        super.cancel(false);
    }

    boolean setPrivately(ResponseT value) {
        return super.set(value);
    }

    boolean setExceptionPrivately(Throwable throwable) {
        return super.setException(throwable);
    }
}

