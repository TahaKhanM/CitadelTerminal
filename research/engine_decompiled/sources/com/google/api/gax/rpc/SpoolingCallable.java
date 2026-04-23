/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.SpoolingResponseObserver;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.List;

class SpoolingCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, List<ResponseT>> {
    private final ServerStreamingCallable<RequestT, ResponseT> streamingCallable;

    SpoolingCallable(ServerStreamingCallable<RequestT, ResponseT> streamingCallable) {
        this.streamingCallable = streamingCallable;
    }

    @Override
    public ApiFuture<List<ResponseT>> futureCall(RequestT request, ApiCallContext context) {
        SpoolingResponseObserver observer = new SpoolingResponseObserver();
        this.streamingCallable.call(request, observer, context);
        return observer.getFuture();
    }
}

