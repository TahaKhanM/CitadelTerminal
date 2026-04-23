/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.grpc.CallOptionsUtil;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.grpc.ResponseMetadataHandler;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.common.base.Preconditions;
import io.grpc.Metadata;

@BetaApi(value="The surface for response metadata is not stable yet and may change in the future.")
public class GrpcResponseMetadata
implements ResponseMetadataHandler {
    private volatile Metadata responseMetadata;
    private volatile Metadata trailingMetadata;

    public GrpcCallContext addHandlers(ApiCallContext apiCallContext) {
        if (Preconditions.checkNotNull(apiCallContext) instanceof GrpcCallContext) {
            return this.addHandlers((GrpcCallContext)apiCallContext);
        }
        throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + apiCallContext.getClass().getName());
    }

    public GrpcCallContext createContextWithHandlers() {
        return this.addHandlers(GrpcCallContext.createDefault());
    }

    private GrpcCallContext addHandlers(GrpcCallContext grpcCallContext) {
        return Preconditions.checkNotNull(grpcCallContext).withCallOptions(CallOptionsUtil.putMetadataHandlerOption(grpcCallContext.getCallOptions(), this));
    }

    public Metadata getMetadata() {
        return this.responseMetadata;
    }

    public Metadata getTrailingMetadata() {
        return this.trailingMetadata;
    }

    @Override
    public void onHeaders(Metadata metadata) {
        this.responseMetadata = metadata;
    }

    @Override
    public void onTrailers(Metadata metadata) {
        this.trailingMetadata = metadata;
    }
}

