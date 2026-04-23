/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ApiStreamObserverAdapter;
import com.google.api.gax.rpc.FirstElementCallable;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStream;
import com.google.api.gax.rpc.SpoolingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.Iterator;
import java.util.List;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public abstract class ServerStreamingCallable<RequestT, ResponseT> {
    private final FirstElementCallable<RequestT, ResponseT> firstCallable = new FirstElementCallable(this);
    private final SpoolingCallable<RequestT, ResponseT> spoolingCallable = new SpoolingCallable(this);

    protected ServerStreamingCallable() {
    }

    public UnaryCallable<RequestT, ResponseT> first() {
        return this.firstCallable;
    }

    public UnaryCallable<RequestT, List<ResponseT>> all() {
        return this.spoolingCallable;
    }

    public ServerStream<ResponseT> call(RequestT request) {
        return this.call(request, (ApiCallContext)null);
    }

    public ServerStream<ResponseT> call(RequestT request, ApiCallContext context) {
        ServerStream stream = new ServerStream();
        this.call(request, stream.observer(), context);
        return stream;
    }

    public abstract void call(RequestT var1, ResponseObserver<ResponseT> var2, ApiCallContext var3);

    public void call(RequestT request, ResponseObserver<ResponseT> responseObserver) {
        this.call(request, responseObserver, null);
    }

    @Deprecated
    public void serverStreamingCall(RequestT request, ApiStreamObserver<ResponseT> responseObserver, ApiCallContext context) {
        this.call(request, new ApiStreamObserverAdapter<ResponseT>(responseObserver), context);
    }

    @Deprecated
    public void serverStreamingCall(RequestT request, ApiStreamObserver<ResponseT> responseObserver) {
        this.serverStreamingCall(request, responseObserver, null);
    }

    @Deprecated
    public Iterator<ResponseT> blockingServerStreamingCall(RequestT request, ApiCallContext context) {
        return this.call(request, context).iterator();
    }

    @Deprecated
    public Iterator<ResponseT> blockingServerStreamingCall(RequestT request) {
        return this.blockingServerStreamingCall(request, null);
    }

    public ServerStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext defaultCallContext) {
        return new ServerStreamingCallable<RequestT, ResponseT>(){

            @Override
            public void call(RequestT request, ResponseObserver<ResponseT> responseObserver, ApiCallContext thisCallContext) {
                ServerStreamingCallable.this.call(request, responseObserver, defaultCallContext.merge(thisCallContext));
            }
        };
    }
}

