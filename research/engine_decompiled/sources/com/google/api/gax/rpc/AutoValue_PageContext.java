/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.PagedListDescriptor;
import com.google.api.gax.rpc.UnaryCallable;

final class AutoValue_PageContext<RequestT, ResponseT, ResourceT>
extends PageContext<RequestT, ResponseT, ResourceT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final PagedListDescriptor<RequestT, ResponseT, ResourceT> pageDescriptor;
    private final RequestT request;
    private final ApiCallContext callContext;

    AutoValue_PageContext(UnaryCallable<RequestT, ResponseT> callable, PagedListDescriptor<RequestT, ResponseT, ResourceT> pageDescriptor, RequestT request, ApiCallContext callContext) {
        if (callable == null) {
            throw new NullPointerException("Null callable");
        }
        this.callable = callable;
        if (pageDescriptor == null) {
            throw new NullPointerException("Null pageDescriptor");
        }
        this.pageDescriptor = pageDescriptor;
        if (request == null) {
            throw new NullPointerException("Null request");
        }
        this.request = request;
        if (callContext == null) {
            throw new NullPointerException("Null callContext");
        }
        this.callContext = callContext;
    }

    @Override
    public UnaryCallable<RequestT, ResponseT> getCallable() {
        return this.callable;
    }

    @Override
    public PagedListDescriptor<RequestT, ResponseT, ResourceT> getPageDescriptor() {
        return this.pageDescriptor;
    }

    @Override
    public RequestT getRequest() {
        return this.request;
    }

    @Override
    public ApiCallContext getCallContext() {
        return this.callContext;
    }

    public String toString() {
        return "PageContext{callable=" + this.callable + ", pageDescriptor=" + this.pageDescriptor + ", request=" + this.request + ", callContext=" + this.callContext + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof PageContext) {
            PageContext that = (PageContext)o;
            return this.callable.equals(that.getCallable()) && this.pageDescriptor.equals(that.getPageDescriptor()) && this.request.equals(that.getRequest()) && this.callContext.equals(that.getCallContext());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.callable.hashCode();
        h *= 1000003;
        h ^= this.pageDescriptor.hashCode();
        h *= 1000003;
        h ^= this.request.hashCode();
        h *= 1000003;
        return h ^= this.callContext.hashCode();
    }
}

