/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.AutoValue_PageContext;
import com.google.api.gax.rpc.PagedListDescriptor;
import com.google.api.gax.rpc.UnaryCallable;

public abstract class PageContext<RequestT, ResponseT, ResourceT> {
    public abstract UnaryCallable<RequestT, ResponseT> getCallable();

    public abstract PagedListDescriptor<RequestT, ResponseT, ResourceT> getPageDescriptor();

    public abstract RequestT getRequest();

    public abstract ApiCallContext getCallContext();

    public PageContext<RequestT, ResponseT, ResourceT> withRequest(RequestT newRequest) {
        return new AutoValue_PageContext<RequestT, ResponseT, ResourceT>(this.getCallable(), this.getPageDescriptor(), newRequest, this.getCallContext());
    }

    public static <RequestT, ResponseT, ResourceT> PageContext<RequestT, ResponseT, ResourceT> create(UnaryCallable<RequestT, ResponseT> callable, PagedListDescriptor<RequestT, ResponseT, ResourceT> pageDescriptor, RequestT request, ApiCallContext callContext) {
        return new AutoValue_PageContext<RequestT, ResponseT, ResourceT>(callable, pageDescriptor, request, callContext);
    }
}

