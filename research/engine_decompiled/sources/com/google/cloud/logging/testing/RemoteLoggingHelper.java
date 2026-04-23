/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.testing;

import com.google.api.gax.retrying.RetrySettings;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.grpc.GrpcTransportOptions;
import com.google.cloud.logging.LoggingOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.threeten.bp.Duration;

public class RemoteLoggingHelper {
    private static final Logger log = Logger.getLogger(RemoteLoggingHelper.class.getName());
    private final LoggingOptions options;

    private RemoteLoggingHelper(LoggingOptions options) {
        this.options = options;
    }

    public LoggingOptions getOptions() {
        return this.options;
    }

    public static RemoteLoggingHelper create(String projectId, InputStream keyStream) throws LoggingHelperException {
        try {
            GrpcTransportOptions transportOptions = LoggingOptions.getDefaultGrpcTransportOptions();
            LoggingOptions storageOptions = ((LoggingOptions.Builder)((LoggingOptions.Builder)((LoggingOptions.Builder)LoggingOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(keyStream))).setProjectId(projectId)).setRetrySettings(RemoteLoggingHelper.retrySettings())).setTransportOptions(transportOptions).build();
            return new RemoteLoggingHelper(storageOptions);
        }
        catch (IOException ex) {
            if (log.isLoggable(Level.WARNING)) {
                log.log(Level.WARNING, ex.getMessage());
            }
            throw LoggingHelperException.translate(ex);
        }
    }

    public static RemoteLoggingHelper create() throws LoggingHelperException {
        GrpcTransportOptions transportOptions = LoggingOptions.getDefaultGrpcTransportOptions();
        LoggingOptions loggingOptions = ((LoggingOptions.Builder)LoggingOptions.newBuilder().setRetrySettings(RemoteLoggingHelper.retrySettings())).setTransportOptions(transportOptions).build();
        return new RemoteLoggingHelper(loggingOptions);
    }

    public static String formatForTest(String name) {
        return name + "-" + UUID.randomUUID().toString();
    }

    private static RetrySettings retrySettings() {
        return RetrySettings.newBuilder().setMaxRetryDelay(Duration.ofMillis(30000L)).setTotalTimeout(Duration.ofMillis(120000L)).setInitialRetryDelay(Duration.ofMillis(250L)).setRetryDelayMultiplier(1.0).setInitialRpcTimeout(Duration.ofMillis(120000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(120000L)).build();
    }

    public static class LoggingHelperException
    extends RuntimeException {
        private static final long serialVersionUID = 2617749404172557196L;

        public LoggingHelperException(String message, Throwable cause) {
            super(message, cause);
        }

        public static LoggingHelperException translate(Exception ex) {
            return new LoggingHelperException(ex.getMessage(), ex);
        }
    }
}

