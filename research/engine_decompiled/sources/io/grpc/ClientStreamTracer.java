/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.CallOptions;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata;
import io.grpc.StreamTracer;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2861")
@ThreadSafe
public abstract class ClientStreamTracer
extends StreamTracer {
    public void outboundHeaders() {
    }

    public void inboundHeaders() {
    }

    public static abstract class Factory {
        @Deprecated
        public ClientStreamTracer newClientStreamTracer(Metadata headers) {
            throw new UnsupportedOperationException("This method will be deleted. Do not call.");
        }

        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata headers) {
            return this.newClientStreamTracer(headers);
        }
    }
}

