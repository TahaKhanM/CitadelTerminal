/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;
import io.grpc.stub.StreamObserver;

@DoNotMock
@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1788")
public abstract class CallStreamObserver<V>
implements StreamObserver<V> {
    public abstract boolean isReady();

    public abstract void setOnReadyHandler(Runnable var1);

    public abstract void disableAutoInboundFlowControl();

    public abstract void request(int var1);

    public abstract void setMessageCompression(boolean var1);
}

