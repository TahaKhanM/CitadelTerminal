/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;
import io.grpc.stub.CallStreamObserver;

@DoNotMock
@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1788")
public abstract class ServerCallStreamObserver<V>
extends CallStreamObserver<V> {
    public abstract boolean isCancelled();

    public abstract void setOnCancelHandler(Runnable var1);

    public abstract void setCompression(String var1);
}

