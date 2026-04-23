/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.RequestBuilder;
import com.google.api.gax.rpc.BatchedRequestIssuer;
import java.util.Collection;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public interface BatchingDescriptor<RequestT, ResponseT> {
    public PartitionKey getBatchPartitionKey(RequestT var1);

    public RequestBuilder<RequestT> getRequestBuilder();

    public void splitResponse(ResponseT var1, Collection<? extends BatchedRequestIssuer<ResponseT>> var2);

    public void splitException(Throwable var1, Collection<? extends BatchedRequestIssuer<ResponseT>> var2);

    public long countElements(RequestT var1);

    public long countBytes(RequestT var1);
}

