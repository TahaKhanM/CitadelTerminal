/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.util.concurrent.MoreExecutors;

public class TranslatingUnaryCallable<InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT>
extends UnaryCallable<OuterRequestT, OuterResponseT> {
    private final UnaryCallable<InnerRequestT, InnerResponseT> innerUnaryCallable;
    private final ApiFunction<OuterRequestT, InnerRequestT> requestTransformer;
    private final ApiFunction<InnerResponseT, OuterResponseT> responseTransformer;

    private TranslatingUnaryCallable(UnaryCallable<InnerRequestT, InnerResponseT> innerUnaryCallable, ApiFunction<OuterRequestT, InnerRequestT> requestTransformer, ApiFunction<InnerResponseT, OuterResponseT> responseTransformer) {
        this.innerUnaryCallable = innerUnaryCallable;
        this.requestTransformer = requestTransformer;
        this.responseTransformer = responseTransformer;
    }

    @Override
    public ApiFuture<OuterResponseT> futureCall(OuterRequestT request, ApiCallContext context) {
        InnerRequestT innerRequest = this.requestTransformer.apply(request);
        return ApiFutures.transform(this.innerUnaryCallable.futureCall(innerRequest, context), this.responseTransformer, MoreExecutors.directExecutor());
    }

    public static <InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT> TranslatingUnaryCallable<InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT> create(UnaryCallable<InnerRequestT, InnerResponseT> innerUnaryCallable, ApiFunction<OuterRequestT, InnerRequestT> requestTransformer, ApiFunction<InnerResponseT, OuterResponseT> responseTransformer) {
        return new TranslatingUnaryCallable<InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT>(innerUnaryCallable, requestTransformer, responseTransformer);
    }
}

