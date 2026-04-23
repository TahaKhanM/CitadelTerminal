/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.gax.grpc.GrpcOperationSnapshot;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.LongRunningClient;
import com.google.api.gax.rpc.TranslatingUnaryCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;

class GrpcLongRunningClient
implements LongRunningClient {
    private final OperationsStub operationsStub;

    public GrpcLongRunningClient(OperationsStub operationsStub) {
        this.operationsStub = operationsStub;
    }

    @Override
    public UnaryCallable<String, OperationSnapshot> getOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.getOperationCallable(), new ApiFunction<String, GetOperationRequest>(){

            @Override
            public GetOperationRequest apply(String request) {
                return GetOperationRequest.newBuilder().setName(request).build();
            }
        }, new ApiFunction<Operation, OperationSnapshot>(){

            @Override
            public OperationSnapshot apply(Operation operation) {
                return GrpcOperationSnapshot.create(operation);
            }
        });
    }

    @Override
    public UnaryCallable<String, Void> cancelOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.cancelOperationCallable(), new ApiFunction<String, CancelOperationRequest>(){

            @Override
            public CancelOperationRequest apply(String request) {
                return CancelOperationRequest.newBuilder().setName(request).build();
            }
        }, new ApiFunction<Empty, Void>(){

            @Override
            public Void apply(Empty empty) {
                return null;
            }
        });
    }

    @Override
    public UnaryCallable<String, Void> deleteOperationCallable() {
        return TranslatingUnaryCallable.create(this.operationsStub.deleteOperationCallable(), new ApiFunction<String, DeleteOperationRequest>(){

            @Override
            public DeleteOperationRequest apply(String request) {
                return DeleteOperationRequest.newBuilder().setName(request).build();
            }
        }, new ApiFunction<Empty, Void>(){

            @Override
            public Void apply(Empty empty) {
                return null;
            }
        });
    }

    public static GrpcLongRunningClient create(OperationsStub operationsStub) {
        return new GrpcLongRunningClient(operationsStub);
    }
}

