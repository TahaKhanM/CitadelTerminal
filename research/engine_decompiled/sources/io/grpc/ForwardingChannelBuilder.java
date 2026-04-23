/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.BinaryLog;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/3363")
public abstract class ForwardingChannelBuilder<T extends ForwardingChannelBuilder<T>>
extends ManagedChannelBuilder<T> {
    protected ForwardingChannelBuilder() {
    }

    public static ManagedChannelBuilder<?> forAddress(String name, int port) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String target) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    protected abstract ManagedChannelBuilder<?> delegate();

    @Override
    public T directExecutor() {
        this.delegate().directExecutor();
        return this.thisT();
    }

    @Override
    public T executor(Executor executor) {
        this.delegate().executor(executor);
        return this.thisT();
    }

    @Override
    public T intercept(List<ClientInterceptor> interceptors) {
        this.delegate().intercept(interceptors);
        return this.thisT();
    }

    @Override
    public T intercept(ClientInterceptor ... interceptors) {
        this.delegate().intercept(interceptors);
        return this.thisT();
    }

    @Override
    public T userAgent(String userAgent) {
        this.delegate().userAgent(userAgent);
        return this.thisT();
    }

    @Override
    public T overrideAuthority(String authority) {
        this.delegate().overrideAuthority(authority);
        return this.thisT();
    }

    @Override
    @Deprecated
    public T usePlaintext(boolean skipNegotiation) {
        Object o = this.delegate().usePlaintext(skipNegotiation);
        return this.thisT();
    }

    @Override
    public T usePlaintext() {
        this.delegate().usePlaintext();
        return this.thisT();
    }

    @Override
    public T useTransportSecurity() {
        this.delegate().useTransportSecurity();
        return this.thisT();
    }

    @Override
    public T nameResolverFactory(NameResolver.Factory resolverFactory) {
        this.delegate().nameResolverFactory(resolverFactory);
        return this.thisT();
    }

    @Override
    public T loadBalancerFactory(LoadBalancer.Factory loadBalancerFactory) {
        this.delegate().loadBalancerFactory(loadBalancerFactory);
        return this.thisT();
    }

    @Override
    public T enableFullStreamDecompression() {
        this.delegate().enableFullStreamDecompression();
        return this.thisT();
    }

    @Override
    public T decompressorRegistry(DecompressorRegistry registry) {
        this.delegate().decompressorRegistry(registry);
        return this.thisT();
    }

    @Override
    public T compressorRegistry(CompressorRegistry registry) {
        this.delegate().compressorRegistry(registry);
        return this.thisT();
    }

    @Override
    public T idleTimeout(long value, TimeUnit unit) {
        this.delegate().idleTimeout(value, unit);
        return this.thisT();
    }

    @Override
    public T maxInboundMessageSize(int max2) {
        this.delegate().maxInboundMessageSize(max2);
        return this.thisT();
    }

    @Override
    public T keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        this.delegate().keepAliveTime(keepAliveTime, timeUnit);
        return this.thisT();
    }

    @Override
    public T keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        this.delegate().keepAliveTimeout(keepAliveTimeout, timeUnit);
        return this.thisT();
    }

    @Override
    public T keepAliveWithoutCalls(boolean enable) {
        this.delegate().keepAliveWithoutCalls(enable);
        return this.thisT();
    }

    @Override
    public T maxRetryAttempts(int maxRetryAttempts) {
        this.delegate().maxRetryAttempts(maxRetryAttempts);
        return this.thisT();
    }

    @Override
    public T maxHedgedAttempts(int maxHedgedAttempts) {
        this.delegate().maxHedgedAttempts(maxHedgedAttempts);
        return this.thisT();
    }

    @Override
    public T retryBufferSize(long bytes2) {
        this.delegate().retryBufferSize(bytes2);
        return this.thisT();
    }

    @Override
    public T perRpcBufferLimit(long bytes2) {
        this.delegate().perRpcBufferLimit(bytes2);
        return this.thisT();
    }

    @Override
    public T disableRetry() {
        this.delegate().disableRetry();
        return this.thisT();
    }

    @Override
    public T enableRetry() {
        this.delegate().enableRetry();
        return this.thisT();
    }

    @Override
    public T setBinaryLog(BinaryLog binaryLog) {
        this.delegate().setBinaryLog(binaryLog);
        return this.thisT();
    }

    @Override
    public T maxTraceEvents(int maxTraceEvents) {
        this.delegate().maxTraceEvents(maxTraceEvents);
        return this.thisT();
    }

    @Override
    public ManagedChannel build() {
        return this.delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.delegate()).toString();
    }

    protected final T thisT() {
        ForwardingChannelBuilder thisT = this;
        return (T)thisT;
    }
}

