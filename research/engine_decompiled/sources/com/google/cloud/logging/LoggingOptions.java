/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.InternalApi;
import com.google.cloud.ServiceDefaults;
import com.google.cloud.ServiceOptions;
import com.google.cloud.ServiceRpc;
import com.google.cloud.TransportOptions;
import com.google.cloud.grpc.GrpcTransportOptions;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingException;
import com.google.cloud.logging.LoggingFactory;
import com.google.cloud.logging.LoggingImpl;
import com.google.cloud.logging.spi.LoggingRpcFactory;
import com.google.cloud.logging.spi.v2.GrpcLoggingRpc;
import com.google.cloud.logging.spi.v2.LoggingRpc;
import com.google.cloud.logging.v2.LoggingSettings;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.util.Set;

public class LoggingOptions
extends ServiceOptions<Logging, LoggingOptions> {
    private static final String API_SHORT_NAME = "Logging";
    private static final String LOGGING_SCOPE = "https://www.googleapis.com/auth/logging.admin";
    private static final Set<String> SCOPES = ImmutableSet.of("https://www.googleapis.com/auth/logging.admin");
    private static final String DEFAULT_HOST = LoggingSettings.getDefaultEndpoint();
    private static final long serialVersionUID = 5753499510627426717L;

    public static LoggingOptions getDefaultInstance() {
        return LoggingOptions.newBuilder().build();
    }

    @Override
    protected String getDefaultHost() {
        return DEFAULT_HOST;
    }

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected LoggingOptions(Builder builder) {
        super(LoggingFactory.class, LoggingRpcFactory.class, builder, new LoggingDefaults());
    }

    public static GrpcTransportOptions getDefaultGrpcTransportOptions() {
        return GrpcTransportOptions.newBuilder().build();
    }

    @Override
    protected Set<String> getScopes() {
        return SCOPES;
    }

    protected LoggingRpc getLoggingRpcV2() {
        return (LoggingRpc)this.getRpc();
    }

    public boolean equals(Object obj) {
        return obj instanceof LoggingOptions && this.baseEquals((LoggingOptions)obj);
    }

    public int hashCode() {
        return this.baseHashCode();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private static class LoggingDefaults
    implements ServiceDefaults<Logging, LoggingOptions> {
        private LoggingDefaults() {
        }

        public LoggingFactory getDefaultServiceFactory() {
            return DefaultLoggingFactory.INSTANCE;
        }

        public LoggingRpcFactory getDefaultRpcFactory() {
            return DefaultLoggingRpcFactory.INSTANCE;
        }

        @Override
        public TransportOptions getDefaultTransportOptions() {
            return LoggingOptions.getDefaultGrpcTransportOptions();
        }
    }

    public static class Builder
    extends ServiceOptions.Builder<Logging, LoggingOptions, Builder> {
        private Builder() {
        }

        private Builder(LoggingOptions options) {
            super(options);
        }

        @Override
        public Builder setTransportOptions(TransportOptions transportOptions) {
            if (!(transportOptions instanceof GrpcTransportOptions)) {
                throw new IllegalArgumentException("Only grpc transport is allowed for Logging.");
            }
            return (Builder)super.setTransportOptions(transportOptions);
        }

        public LoggingOptions build() {
            return new LoggingOptions(this);
        }
    }

    public static class DefaultLoggingRpcFactory
    implements LoggingRpcFactory {
        private static final LoggingRpcFactory INSTANCE = new DefaultLoggingRpcFactory();

        @Override
        public ServiceRpc create(LoggingOptions options) {
            try {
                return new GrpcLoggingRpc(options);
            }
            catch (IOException e) {
                throw new LoggingException(e, true);
            }
        }
    }

    public static class DefaultLoggingFactory
    implements LoggingFactory {
        private static final LoggingFactory INSTANCE = new DefaultLoggingFactory();

        @Override
        public Logging create(LoggingOptions options) {
            return new LoggingImpl(options);
        }
    }
}

