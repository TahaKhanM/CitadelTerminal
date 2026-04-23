/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public abstract class ClientStreamingCallable<RequestT, ResponseT> {
    protected ClientStreamingCallable() {
    }

    public abstract ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> var1, ApiCallContext var2);

    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> responseObserver) {
        return this.clientStreamingCall(responseObserver, null);
    }

    public ClientStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext defaultCallContext) {
        return new ClientStreamingCallable<RequestT, ResponseT>(){

            @Override
            public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> responseObserver, ApiCallContext thisCallContext) {
                return ClientStreamingCallable.this.clientStreamingCall(responseObserver, defaultCallContext.merge(thisCallContext));
            }
        };
    }
}

