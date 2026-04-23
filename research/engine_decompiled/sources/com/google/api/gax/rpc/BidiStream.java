/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ServerStream;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public class BidiStream<RequestT, ResponseT>
extends ServerStream<ResponseT>
implements ClientStream<RequestT> {
    private ClientStream<RequestT> clientStream;

    @InternalApi(value="For use by BidiStreamingCallable only.")
    BidiStream() {
    }

    @InternalApi(value="For use by BidiStreamingCallable only.")
    void setClientStream(ClientStream<RequestT> clientStream) {
        this.clientStream = clientStream;
    }

    @Override
    public void send(RequestT req) {
        this.clientStream.send(req);
    }

    @Override
    public boolean isSendReady() {
        return this.clientStream.isSendReady();
    }

    @Override
    public void closeSend() {
        this.clientStream.closeSend();
    }

    @Override
    public void closeSendWithError(Throwable t) {
        this.clientStream.closeSendWithError(t);
    }
}

