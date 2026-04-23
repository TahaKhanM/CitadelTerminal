/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;

public class NotFoundException
extends ApiException {
    public NotFoundException(Throwable cause, StatusCode statusCode, boolean retryable) {
        super(cause, statusCode, retryable);
    }

    public NotFoundException(String message, Throwable cause, StatusCode statusCode, boolean retryable) {
        super(message, cause, statusCode, retryable);
    }
}

