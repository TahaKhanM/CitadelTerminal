/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.grpc.AutoValue_GrpcStatusCode;
import com.google.api.gax.rpc.StatusCode;
import io.grpc.Status;

@InternalExtensionOnly
public abstract class GrpcStatusCode
implements StatusCode {
    public static GrpcStatusCode of(Status.Code grpcCode) {
        return new AutoValue_GrpcStatusCode(grpcCode);
    }

    static StatusCode.Code grpcCodeToStatusCode(Status.Code code) {
        switch (code) {
            case OK: {
                return StatusCode.Code.OK;
            }
            case CANCELLED: {
                return StatusCode.Code.CANCELLED;
            }
            case UNKNOWN: {
                return StatusCode.Code.UNKNOWN;
            }
            case INVALID_ARGUMENT: {
                return StatusCode.Code.INVALID_ARGUMENT;
            }
            case DEADLINE_EXCEEDED: {
                return StatusCode.Code.DEADLINE_EXCEEDED;
            }
            case NOT_FOUND: {
                return StatusCode.Code.NOT_FOUND;
            }
            case ALREADY_EXISTS: {
                return StatusCode.Code.ALREADY_EXISTS;
            }
            case PERMISSION_DENIED: {
                return StatusCode.Code.PERMISSION_DENIED;
            }
            case RESOURCE_EXHAUSTED: {
                return StatusCode.Code.RESOURCE_EXHAUSTED;
            }
            case FAILED_PRECONDITION: {
                return StatusCode.Code.FAILED_PRECONDITION;
            }
            case ABORTED: {
                return StatusCode.Code.ABORTED;
            }
            case OUT_OF_RANGE: {
                return StatusCode.Code.OUT_OF_RANGE;
            }
            case UNIMPLEMENTED: {
                return StatusCode.Code.UNIMPLEMENTED;
            }
            case INTERNAL: {
                return StatusCode.Code.INTERNAL;
            }
            case UNAVAILABLE: {
                return StatusCode.Code.UNAVAILABLE;
            }
            case DATA_LOSS: {
                return StatusCode.Code.DATA_LOSS;
            }
            case UNAUTHENTICATED: {
                return StatusCode.Code.UNAUTHENTICATED;
            }
        }
        throw new IllegalStateException("Unrecognized status code: " + (Object)((Object)code));
    }

    @Override
    public StatusCode.Code getCode() {
        return GrpcStatusCode.grpcCodeToStatusCode(this.getTransportCode());
    }

    public abstract Status.Code getTransportCode();
}

