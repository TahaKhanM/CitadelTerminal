/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ApiStreamObserverAdapter;
import com.google.api.gax.rpc.BidiStream;
import com.google.api.gax.rpc.BidiStreamObserver;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public abstract class BidiStreamingCallable<RequestT, ResponseT> {
    protected BidiStreamingCallable() {
    }

    public abstract ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> var1, ClientStreamReadyObserver<RequestT> var2, ApiCallContext var3);

    public void call(BidiStreamObserver<RequestT, ResponseT> bidiObserver) {
        this.call(bidiObserver, null);
    }

    public void call(final BidiStreamObserver<RequestT, ResponseT> bidiObserver, ApiCallContext context) {
        this.internalCall(bidiObserver, new ClientStreamReadyObserver<RequestT>(){

            @Override
            public void onReady(ClientStream<RequestT> stream) {
                bidiObserver.onReady(stream);
            }
        }, context);
    }

    public BidiStream<RequestT, ResponseT> call() {
        return this.call((ApiCallContext)null);
    }

    public BidiStream<RequestT, ResponseT> call(ApiCallContext context) {
        BidiStream stream = new BidiStream();
        ClientStream<RequestT> clientStream = this.splitCall(stream.observer(), context);
        stream.setClientStream(clientStream);
        return stream;
    }

    public ClientStream<RequestT> splitCall(ResponseObserver<ResponseT> responseObserver) {
        return this.splitCall(responseObserver, null);
    }

    public ClientStream<RequestT> splitCall(ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        return this.internalCall(responseObserver, new ClientStreamReadyObserver<RequestT>(){

            @Override
            public void onReady(ClientStream<RequestT> stream) {
            }
        }, context);
    }

    @Deprecated
    public ApiStreamObserver<RequestT> bidiStreamingCall(ApiStreamObserver<ResponseT> responseObserver, ApiCallContext context) {
        final ClientStream<RequestT> stream = this.splitCall(new ApiStreamObserverAdapter<ResponseT>(responseObserver), context);
        return new ApiStreamObserver<RequestT>(){

            @Override
            public void onNext(RequestT request) {
                stream.send(request);
            }

            @Override
            public void onError(Throwable t) {
                stream.closeSendWithError(t);
            }

            @Override
            public void onCompleted() {
                stream.closeSend();
            }
        };
    }

    @Deprecated
    public ApiStreamObserver<RequestT> bidiStreamingCall(ApiStreamObserver<ResponseT> responseObserver) {
        return this.bidiStreamingCall(responseObserver, null);
    }

    public BidiStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext defaultCallContext) {
        return new BidiStreamingCallable<RequestT, ResponseT>(){

            @Override
            public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> onReady, ApiCallContext thisCallContext) {
                return BidiStreamingCallable.this.internalCall(responseObserver, onReady, defaultCallContext.merge(thisCallContext));
            }
        };
    }
}

