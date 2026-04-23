/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.StatusCode;
import com.google.longrunning.Operation;
import io.grpc.Status;

class GrpcOperationSnapshot
implements OperationSnapshot {
    private final Operation operation;

    public GrpcOperationSnapshot(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String getName() {
        return this.operation.getName();
    }

    @Override
    public Object getMetadata() {
        return this.operation.getMetadata();
    }

    @Override
    public boolean isDone() {
        return this.operation.getDone();
    }

    @Override
    public Object getResponse() {
        return this.operation.getResponse();
    }

    @Override
    public StatusCode getErrorCode() {
        return GrpcStatusCode.of(Status.fromCodeValue(this.operation.getError().getCode()).getCode());
    }

    public static GrpcOperationSnapshot create(Operation operation) {
        return new GrpcOperationSnapshot(operation);
    }
}

