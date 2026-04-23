/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.FirstElementResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;

final class FirstElementCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> streamingCallable;

    FirstElementCallable(ServerStreamingCallable<RequestT, ResponseT> streamingCallable) {
        this.streamingCallable = streamingCallable;
    }

    @Override
    public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext context) {
        FirstElementResponseObserver observer = new FirstElementResponseObserver();
        this.streamingCallable.call(request, observer, context);
        return observer.getFuture();
    }
}

