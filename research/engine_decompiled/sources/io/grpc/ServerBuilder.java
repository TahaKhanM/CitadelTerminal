/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.BinaryLog;
import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ExperimentalApi;
import io.grpc.HandlerRegistry;
import io.grpc.Server;
import io.grpc.ServerInterceptor;
import io.grpc.ServerProvider;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public abstract class ServerBuilder<T extends ServerBuilder<T>> {
    public static ServerBuilder<?> forPort(int port) {
        return ServerProvider.provider().builderForPort(port);
    }

    public abstract T directExecutor();

    public abstract T executor(@Nullable Executor var1);

    public abstract T addService(ServerServiceDefinition var1);

    public abstract T addService(BindableService var1);

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/3117")
    public T intercept(ServerInterceptor interceptor) {
        throw new UnsupportedOperationException();
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2132")
    public T addTransportFilter(ServerTransportFilter filter2) {
        throw new UnsupportedOperationException();
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2861")
    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        throw new UnsupportedOperationException();
    }

    public abstract T fallbackHandlerRegistry(@Nullable HandlerRegistry var1);

    public abstract T useTransportSecurity(File var1, File var2);

    public T useTransportSecurity(InputStream certChain, InputStream privateKey) {
        throw new UnsupportedOperationException();
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public abstract T decompressorRegistry(@Nullable DecompressorRegistry var1);

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public abstract T compressorRegistry(@Nullable CompressorRegistry var1);

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/3706")
    public T handshakeTimeout(long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public T maxInboundMessageSize(int bytes2) {
        Preconditions.checkArgument(bytes2 >= 0, "bytes must be >= 0");
        return this.thisT();
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/4017")
    public T setBinaryLog(BinaryLog binaryLog) {
        throw new UnsupportedOperationException();
    }

    public abstract Server build();

    private T thisT() {
        ServerBuilder thisT = this;
        return (T)thisT;
    }
}

