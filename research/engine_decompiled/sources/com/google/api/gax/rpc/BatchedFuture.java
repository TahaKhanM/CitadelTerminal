/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.InternalApi;

@InternalApi
public class BatchedFuture<ResponseT>
extends AbstractApiFuture<ResponseT> {
    public static <T> BatchedFuture<T> create() {
        return new BatchedFuture();
    }

    @Override
    public boolean set(ResponseT value) {
        return super.set(value);
    }

    @Override
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }
}

