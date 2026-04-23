/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.Preconditions;

public class ApiException
extends RuntimeException {
    private static final long serialVersionUID = -4375114339928877996L;
    private final StatusCode statusCode;
    private final boolean retryable;

    public ApiException(Throwable cause, StatusCode statusCode, boolean retryable) {
        super(cause);
        this.statusCode = Preconditions.checkNotNull(statusCode);
        this.retryable = retryable;
    }

    public ApiException(String message, Throwable cause, StatusCode statusCode, boolean retryable) {
        super(message, cause);
        this.statusCode = Preconditions.checkNotNull(statusCode);
        this.retryable = retryable;
    }

    public boolean isRetryable() {
        return this.retryable;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}

