/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.grpc;

import com.google.api.client.http.HttpResponseException;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.BaseServiceException;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.util.Collections;

public class BaseGrpcServiceException
extends BaseServiceException {
    private static final long serialVersionUID = -2685197215731335549L;

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseGrpcServiceException(String message, Throwable cause, int code, boolean retryable) {
        super(BaseServiceException.ExceptionData.newBuilder().setMessage(message).setCause(cause).setCode(code).setRetryable(retryable).build());
    }

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseGrpcServiceException(IOException exception, boolean idempotent) {
        super(BaseGrpcServiceException.makeExceptionData(exception, idempotent));
    }

    private static BaseServiceException.ExceptionData makeExceptionData(IOException exception, boolean idempotent) {
        int code = 0;
        Boolean retryable = null;
        if (exception instanceof HttpResponseException) {
            code = ((HttpResponseException)exception).getStatusCode();
            retryable = BaseServiceException.isRetryable(code, null, idempotent, Collections.emptySet());
        }
        return BaseServiceException.ExceptionData.newBuilder().setMessage(exception.getMessage()).setCause(exception).setRetryable(MoreObjects.firstNonNull(retryable, BaseServiceException.isRetryable(idempotent, exception))).setCode(code).setReason(null).setLocation(null).setDebugInfo(null).build();
    }

    @BetaApi
    public BaseGrpcServiceException(ApiException apiException) {
        super(BaseServiceException.ExceptionData.newBuilder().setMessage(apiException.getMessage()).setCause(apiException).setRetryable(apiException.isRetryable()).setCode(apiException.getStatusCode().getCode().getHttpStatusCode()).setReason(apiException.getStatusCode().getCode().name()).setLocation(null).setDebugInfo(null).build());
    }
}

