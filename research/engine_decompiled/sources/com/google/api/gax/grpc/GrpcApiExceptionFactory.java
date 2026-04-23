/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.ApiExceptionFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.Set;

class GrpcApiExceptionFactory {
    private final ImmutableSet<StatusCode.Code> retryableCodes;

    GrpcApiExceptionFactory(Set<StatusCode.Code> retryCodes) {
        this.retryableCodes = ImmutableSet.copyOf(retryCodes);
    }

    ApiException create(Throwable throwable) {
        if (throwable instanceof StatusException) {
            StatusException e = (StatusException)throwable;
            return this.create(throwable, e.getStatus().getCode());
        }
        if (throwable instanceof StatusRuntimeException) {
            StatusRuntimeException e = (StatusRuntimeException)throwable;
            return this.create(throwable, e.getStatus().getCode());
        }
        if (throwable instanceof ApiException) {
            return (ApiException)throwable;
        }
        return ApiExceptionFactory.createException(throwable, GrpcStatusCode.of(Status.Code.UNKNOWN), false);
    }

    private ApiException create(Throwable throwable, Status.Code statusCode) {
        boolean canRetry = this.retryableCodes.contains((Object)GrpcStatusCode.grpcCodeToStatusCode(statusCode));
        return ApiExceptionFactory.createException(throwable, GrpcStatusCode.of(statusCode), canRetry);
    }
}

