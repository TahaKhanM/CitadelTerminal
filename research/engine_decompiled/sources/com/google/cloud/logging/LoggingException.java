/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.grpc.BaseGrpcServiceException;
import java.io.IOException;

public final class LoggingException
extends BaseGrpcServiceException {
    private static final long serialVersionUID = 449689219311927047L;

    public LoggingException(IOException ex, boolean idempotent) {
        super(ex, idempotent);
    }

    @BetaApi
    public LoggingException(ApiException apiException) {
        super(apiException);
    }
}

