/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiExceptionFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

@BetaApi(value="The surface for use by generated code is not stable yet and may change in the future.")
public class ProtoOperationTransformers {
    private ProtoOperationTransformers() {
    }

    static class AnyTransformer<PackedT extends Message>
    implements ApiFunction<Any, PackedT> {
        private final Class<PackedT> packedClass;

        public AnyTransformer(Class<PackedT> packedClass) {
            this.packedClass = packedClass;
        }

        @Override
        public PackedT apply(Any input2) {
            try {
                return input2 == null || this.packedClass == null ? null : (PackedT)input2.unpack(this.packedClass);
            }
            catch (InvalidProtocolBufferException | ClassCastException e) {
                throw new IllegalStateException("Failed to unpack object from 'any' field. Expected " + this.packedClass.getName() + ", found " + input2.getTypeUrl());
            }
        }
    }

    public static class MetadataTransformer<MetadataT extends Message>
    implements ApiFunction<OperationSnapshot, MetadataT> {
        private final AnyTransformer<MetadataT> transformer;

        private MetadataTransformer(Class<MetadataT> packedClass) {
            this.transformer = new AnyTransformer<MetadataT>(packedClass);
        }

        @Override
        public MetadataT apply(OperationSnapshot operationSnapshot) {
            try {
                return this.transformer.apply(operationSnapshot.getMetadata() != null ? (Any)operationSnapshot.getMetadata() : null);
            }
            catch (RuntimeException e) {
                throw ApiExceptionFactory.createException("Polling operation with name \"" + operationSnapshot.getName() + "\" succeeded, but encountered a problem unpacking it.", e, operationSnapshot.getErrorCode(), false);
            }
        }

        public static <ResponseT extends Message> MetadataTransformer<ResponseT> create(Class<ResponseT> packedClass) {
            return new MetadataTransformer<ResponseT>(packedClass);
        }
    }

    public static class ResponseTransformer<ResponseT extends Message>
    implements ApiFunction<OperationSnapshot, ResponseT> {
        private final AnyTransformer<ResponseT> transformer;

        private ResponseTransformer(Class<ResponseT> packedClass) {
            this.transformer = new AnyTransformer<ResponseT>(packedClass);
        }

        @Override
        public ResponseT apply(OperationSnapshot operationSnapshot) {
            if (!operationSnapshot.getErrorCode().getCode().equals((Object)StatusCode.Code.OK)) {
                throw ApiExceptionFactory.createException("Operation with name \"" + operationSnapshot.getName() + "\" failed with status = " + operationSnapshot.getErrorCode(), null, operationSnapshot.getErrorCode(), false);
            }
            try {
                return this.transformer.apply((Any)operationSnapshot.getResponse());
            }
            catch (RuntimeException e) {
                throw ApiExceptionFactory.createException("Operation with name \"" + operationSnapshot.getName() + "\" succeeded, but encountered a problem unpacking it.", e, operationSnapshot.getErrorCode(), false);
            }
        }

        public static <ResponseT extends Message> ResponseTransformer<ResponseT> create(Class<ResponseT> packedClass) {
            return new ResponseTransformer<ResponseT>(packedClass);
        }
    }
}

