/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.AbortedException;
import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.CancelledException;
import com.google.api.gax.rpc.DataLossException;
import com.google.api.gax.rpc.DeadlineExceededException;
import com.google.api.gax.rpc.FailedPreconditionException;
import com.google.api.gax.rpc.InternalException;
import com.google.api.gax.rpc.InvalidArgumentException;
import com.google.api.gax.rpc.NotFoundException;
import com.google.api.gax.rpc.OutOfRangeException;
import com.google.api.gax.rpc.PermissionDeniedException;
import com.google.api.gax.rpc.ResourceExhaustedException;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnauthenticatedException;
import com.google.api.gax.rpc.UnavailableException;
import com.google.api.gax.rpc.UnimplementedException;
import com.google.api.gax.rpc.UnknownException;

public class ApiExceptionFactory {
    private ApiExceptionFactory() {
    }

    public static ApiException createException(Throwable cause, StatusCode statusCode, boolean retryable) {
        switch (statusCode.getCode()) {
            case CANCELLED: {
                return new CancelledException(cause, statusCode, retryable);
            }
            case NOT_FOUND: {
                return new NotFoundException(cause, statusCode, retryable);
            }
            case UNKNOWN: {
                return new UnknownException(cause, statusCode, retryable);
            }
            case INVALID_ARGUMENT: {
                return new InvalidArgumentException(cause, statusCode, retryable);
            }
            case DEADLINE_EXCEEDED: {
                return new DeadlineExceededException(cause, statusCode, retryable);
            }
            case ALREADY_EXISTS: {
                return new AlreadyExistsException(cause, statusCode, retryable);
            }
            case PERMISSION_DENIED: {
                return new PermissionDeniedException(cause, statusCode, retryable);
            }
            case RESOURCE_EXHAUSTED: {
                return new ResourceExhaustedException(cause, statusCode, retryable);
            }
            case FAILED_PRECONDITION: {
                return new FailedPreconditionException(cause, statusCode, retryable);
            }
            case ABORTED: {
                return new AbortedException(cause, statusCode, retryable);
            }
            case OUT_OF_RANGE: {
                return new OutOfRangeException(cause, statusCode, retryable);
            }
            case UNIMPLEMENTED: {
                return new UnimplementedException(cause, statusCode, retryable);
            }
            case INTERNAL: {
                return new InternalException(cause, statusCode, retryable);
            }
            case UNAVAILABLE: {
                return new UnavailableException(cause, statusCode, retryable);
            }
            case DATA_LOSS: {
                return new DataLossException(cause, statusCode, retryable);
            }
            case UNAUTHENTICATED: {
                return new UnauthenticatedException(cause, statusCode, retryable);
            }
        }
        return new UnknownException(cause, statusCode, retryable);
    }

    public static ApiException createException(String message, Throwable cause, StatusCode statusCode, boolean retryable) {
        switch (statusCode.getCode()) {
            case CANCELLED: {
                return new CancelledException(message, cause, statusCode, retryable);
            }
            case NOT_FOUND: {
                return new NotFoundException(message, cause, statusCode, retryable);
            }
            case UNKNOWN: {
                return new UnknownException(message, cause, statusCode, retryable);
            }
            case INVALID_ARGUMENT: {
                return new InvalidArgumentException(message, cause, statusCode, retryable);
            }
            case DEADLINE_EXCEEDED: {
                return new DeadlineExceededException(message, cause, statusCode, retryable);
            }
            case ALREADY_EXISTS: {
                return new AlreadyExistsException(message, cause, statusCode, retryable);
            }
            case PERMISSION_DENIED: {
                return new PermissionDeniedException(message, cause, statusCode, retryable);
            }
            case RESOURCE_EXHAUSTED: {
                return new ResourceExhaustedException(message, cause, statusCode, retryable);
            }
            case FAILED_PRECONDITION: {
                return new FailedPreconditionException(message, cause, statusCode, retryable);
            }
            case ABORTED: {
                return new AbortedException(message, cause, statusCode, retryable);
            }
            case OUT_OF_RANGE: {
                return new OutOfRangeException(message, cause, statusCode, retryable);
            }
            case UNIMPLEMENTED: {
                return new UnimplementedException(message, cause, statusCode, retryable);
            }
            case INTERNAL: {
                return new InternalException(message, cause, statusCode, retryable);
            }
            case UNAVAILABLE: {
                return new UnavailableException(message, cause, statusCode, retryable);
            }
            case DATA_LOSS: {
                return new DataLossException(message, cause, statusCode, retryable);
            }
            case UNAUTHENTICATED: {
                return new UnauthenticatedException(message, cause, statusCode, retryable);
            }
        }
        return new UnknownException(cause, statusCode, retryable);
    }
}

