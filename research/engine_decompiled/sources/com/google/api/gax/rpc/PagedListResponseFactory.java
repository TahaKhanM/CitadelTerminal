/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;

public interface PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> {
    public ApiFuture<PagedListResponseT> getFuturePagedResponse(UnaryCallable<RequestT, ResponseT> var1, RequestT var2, ApiCallContext var3, ApiFuture<ResponseT> var4);
}

