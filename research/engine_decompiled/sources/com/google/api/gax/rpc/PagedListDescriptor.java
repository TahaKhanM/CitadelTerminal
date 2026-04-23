/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

public interface PagedListDescriptor<RequestT, ResponseT, ResourceT> {
    public String emptyToken();

    public RequestT injectToken(RequestT var1, String var2);

    public RequestT injectPageSize(RequestT var1, int var2);

    public Integer extractPageSize(RequestT var1);

    public String extractNextToken(ResponseT var1);

    public Iterable<ResourceT> extractResources(ResponseT var1);
}

