/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.PagedListResponseFactory;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

@InternalApi(value="For use by transport-specific implementations")
public class PagedCallable<RequestT, ResponseT, PagedListResponseT>
extends UnaryCallable<RequestT, PagedListResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

    public PagedCallable(UnaryCallable<RequestT, ResponseT> callable, PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
        this.callable = Preconditions.checkNotNull(callable);
        this.pagedListResponseFactory = pagedListResponseFactory;
    }

    public String toString() {
        return String.format("paged(%s)", this.callable);
    }

    @Override
    public ApiFuture<PagedListResponseT> futureCall(RequestT request, ApiCallContext context) {
        ApiFuture<ResponseT> futureResponse = this.callable.futureCall(request, context);
        return this.pagedListResponseFactory.getFuturePagedResponse(this.callable.withDefaultCallContext(context), request, context, futureResponse);
    }
}

