/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.grpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.auth.Credentials;
import com.google.cloud.NoCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.TransportOptions;
import com.google.common.base.MoreObjects;
import io.grpc.internal.SharedResourceHolder;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GrpcTransportOptions
implements TransportOptions {
    private static final long serialVersionUID = -9049538465533951165L;
    private final String executorFactoryClassName;
    private transient ExecutorFactory<ScheduledExecutorService> executorFactory;
    private static final SharedResourceHolder.Resource<ScheduledExecutorService> EXECUTOR = new SharedResourceHolder.Resource<ScheduledExecutorService>(){

        @Override
        public ScheduledExecutorService create() {
            ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(8);
            service.setKeepAliveTime(5L, TimeUnit.SECONDS);
            service.allowCoreThreadTimeOut(true);
            service.setRemoveOnCancelPolicy(true);
            return service;
        }

        @Override
        public void close(ScheduledExecutorService instance) {
            instance.shutdown();
        }
    };

    private GrpcTransportOptions(Builder builder) {
        this.executorFactory = MoreObjects.firstNonNull(builder.executorFactory, ServiceOptions.getFromServiceLoader(ExecutorFactory.class, DefaultExecutorFactory.INSTANCE));
        this.executorFactoryClassName = this.executorFactory.getClass().getName();
    }

    public ExecutorFactory<ScheduledExecutorService> getExecutorFactory() {
        return this.executorFactory;
    }

    @Deprecated
    public UnaryCallSettings.Builder getApiCallSettings(RetrySettings retrySettings) {
        return UnaryCallSettings.newUnaryCallSettingsBuilder().setRetrySettings(retrySettings);
    }

    @BetaApi
    public static TransportChannelProvider setUpChannelProvider(InstantiatingGrpcChannelProvider.Builder providerBuilder, ServiceOptions<?, ?> serviceOptions) {
        providerBuilder.setEndpoint(serviceOptions.getHost());
        return providerBuilder.build();
    }

    public static CredentialsProvider setUpCredentialsProvider(ServiceOptions<?, ?> serviceOptions) {
        Credentials scopedCredentials = serviceOptions.getScopedCredentials();
        if (scopedCredentials != null && scopedCredentials != NoCredentials.getInstance()) {
            return FixedCredentialsProvider.create(scopedCredentials);
        }
        return NoCredentialsProvider.create();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public int hashCode() {
        return Objects.hash(this.executorFactoryClassName);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        GrpcTransportOptions other = (GrpcTransportOptions)obj;
        return Objects.equals(this.executorFactoryClassName, other.executorFactoryClassName);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.executorFactory = (ExecutorFactory)ServiceOptions.newInstance(this.executorFactoryClassName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ExecutorFactory executorFactory;

        private Builder() {
        }

        private Builder(GrpcTransportOptions options) {
            this.executorFactory = options.executorFactory;
        }

        public GrpcTransportOptions build() {
            return new GrpcTransportOptions(this);
        }

        public Builder setExecutorFactory(ExecutorFactory<ScheduledExecutorService> executorFactory) {
            this.executorFactory = executorFactory;
            return this;
        }
    }

    @InternalApi
    public static class DefaultExecutorFactory
    implements ExecutorFactory<ScheduledExecutorService> {
        private static final DefaultExecutorFactory INSTANCE = new DefaultExecutorFactory();

        @Override
        public ScheduledExecutorService get() {
            return (ScheduledExecutorService)SharedResourceHolder.get(EXECUTOR);
        }

        @Override
        public synchronized void release(ScheduledExecutorService executor) {
            SharedResourceHolder.release(EXECUTOR, executor);
        }
    }

    public static interface ExecutorFactory<T extends ExecutorService> {
        public T get();

        public void release(T var1);
    }
}

