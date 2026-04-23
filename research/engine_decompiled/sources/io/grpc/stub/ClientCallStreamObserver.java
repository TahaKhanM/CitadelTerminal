/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;
import io.grpc.stub.CallStreamObserver;
import javax.annotation.Nullable;

@DoNotMock
@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1788")
public abstract class ClientCallStreamObserver<V>
extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String var1, @Nullable Throwable var2);
}

