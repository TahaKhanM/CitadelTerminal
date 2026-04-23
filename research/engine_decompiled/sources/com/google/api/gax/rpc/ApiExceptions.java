/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.UncheckedExecutionException;

public class ApiExceptions {
    private ApiExceptions() {
    }

    public static <ResponseT> ResponseT callAndTranslateApiException(ApiFuture<ResponseT> future) {
        try {
            return Futures.getUnchecked(future);
        }
        catch (UncheckedExecutionException exception) {
            Throwables.throwIfInstanceOf(exception.getCause(), RuntimeException.class);
            throw exception;
        }
    }
}

