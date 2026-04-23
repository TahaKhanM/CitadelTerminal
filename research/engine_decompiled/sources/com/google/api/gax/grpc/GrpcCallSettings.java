/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.RequestParamsExtractor;
import io.grpc.MethodDescriptor;

@BetaApi(value="The surface for use by generated code is not stable yet and may change in the future.")
public class GrpcCallSettings<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> methodDescriptor;
    private final RequestParamsExtractor<RequestT> paramsExtractor;

    private GrpcCallSettings(MethodDescriptor<RequestT, ResponseT> methodDescriptor, RequestParamsExtractor<RequestT> paramsExtractor) {
        this.methodDescriptor = methodDescriptor;
        this.paramsExtractor = paramsExtractor;
    }

    public MethodDescriptor<RequestT, ResponseT> getMethodDescriptor() {
        return this.methodDescriptor;
    }

    @BetaApi
    public RequestParamsExtractor<RequestT> getParamsExtractor() {
        return this.paramsExtractor;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder();
    }

    public static <RequestT, ResponseT> GrpcCallSettings<RequestT, ResponseT> create(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        return GrpcCallSettings.newBuilder().setMethodDescriptor(methodDescriptor).build();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder<RequestT, ResponseT> {
        private MethodDescriptor<RequestT, ResponseT> methodDescriptor;
        private RequestParamsExtractor<RequestT> paramsExtractor;

        private Builder() {
        }

        private Builder(GrpcCallSettings<RequestT, ResponseT> settings) {
            this.methodDescriptor = ((GrpcCallSettings)settings).methodDescriptor;
        }

        public Builder<RequestT, ResponseT> setMethodDescriptor(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
            this.methodDescriptor = methodDescriptor;
            return this;
        }

        @BetaApi
        public Builder<RequestT, ResponseT> setParamsExtractor(RequestParamsExtractor<RequestT> paramsExtractor) {
            this.paramsExtractor = paramsExtractor;
            return this;
        }

        public GrpcCallSettings<RequestT, ResponseT> build() {
            return new GrpcCallSettings(this.methodDescriptor, this.paramsExtractor);
        }
    }
}

