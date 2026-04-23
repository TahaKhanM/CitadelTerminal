/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.gax.retrying.RetryingFuture;
import java.util.concurrent.Callable;

public interface RetryingExecutor<ResponseT> {
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> var1);

    public ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> var1);
}

