/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.grpc.GrpcOperationSnapshot;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.longrunning.Operation;

class GrpcOperationSnapshotCallable<RequestT>
extends UnaryCallable<RequestT, OperationSnapshot> {
    private final UnaryCallable<RequestT, Operation> innerCallable;

    public GrpcOperationSnapshotCallable(UnaryCallable<RequestT, Operation> innerCallable) {
        this.innerCallable = innerCallable;
    }

    @Override
    public ApiFuture<OperationSnapshot> futureCall(RequestT request, ApiCallContext context) {
        return ApiFutures.transform(this.innerCallable.futureCall(request, context), new ApiFunction<Operation, OperationSnapshot>(){

            @Override
            public OperationSnapshot apply(Operation operation) {
                return new GrpcOperationSnapshot(operation);
            }
        }, MoreExecutors.directExecutor());
    }
}

