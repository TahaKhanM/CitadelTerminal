/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingConfig;
import com.google.cloud.logging.LoggingEnhancer;
import com.google.cloud.logging.LoggingLevel;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.MonitoredResourceUtil;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.Synchronicity;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingHandler
extends Handler {
    private static final String HANDLERS_PROPERTY = "handlers";
    private static final String ROOT_LOGGER_NAME = "";
    private static final String[] NO_HANDLERS = new String[0];
    private static final String LEVEL_NAME_KEY = "levelName";
    private static final String LEVEL_VALUE_KEY = "levelValue";
    private final List<LoggingEnhancer> enhancers;
    private final LoggingOptions loggingOptions;
    private volatile Logging logging;
    private final Level baseLevel;
    private volatile Level flushLevel;
    private Logging.WriteOption[] defaultWriteOptions;

    public LoggingHandler() {
        this(null, null, null);
    }

    public LoggingHandler(String log) {
        this(log, null, null);
    }

    public LoggingHandler(String log, LoggingOptions options) {
        this(log, options, null);
    }

    public LoggingHandler(String log, LoggingOptions options, MonitoredResource monitoredResource) {
        this(log, options, monitoredResource, null);
    }

    public LoggingHandler(String log, LoggingOptions options, MonitoredResource monitoredResource, List<LoggingEnhancer> enhancers) {
        try {
            this.loggingOptions = options != null ? options : LoggingOptions.getDefaultInstance();
            LoggingConfig config = new LoggingConfig(this.getClass().getName());
            this.setFilter(config.getFilter());
            this.setFormatter(config.getFormatter());
            Level level = config.getLogLevel();
            this.setLevel(level);
            this.baseLevel = level.equals(Level.ALL) ? Level.FINEST : level;
            this.flushLevel = config.getFlushLevel();
            String logName = log != null ? log : config.getLogName();
            MonitoredResource resource = MoreObjects.firstNonNull(monitoredResource, config.getMonitoredResource(this.loggingOptions.getProjectId()));
            this.defaultWriteOptions = new Logging.WriteOption[]{Logging.WriteOption.logName(logName), Logging.WriteOption.resource(resource), Logging.WriteOption.labels(ImmutableMap.of(LEVEL_NAME_KEY, this.baseLevel.getName(), LEVEL_VALUE_KEY, String.valueOf(this.baseLevel.intValue())))};
            this.getLogging().setFlushSeverity(LoggingHandler.severityFor(this.flushLevel));
            this.getLogging().setWriteSynchronicity(config.getSynchronicity());
            this.enhancers = new LinkedList<LoggingEnhancer>();
            List enhancersParam = MoreObjects.firstNonNull(enhancers, MoreObjects.firstNonNull(config.getEnhancers(), Collections.emptyList()));
            this.enhancers.addAll(enhancersParam);
            List<LoggingEnhancer> loggingEnhancers = MonitoredResourceUtil.getResourceEnhancers();
            if (loggingEnhancers != null) {
                this.enhancers.addAll(loggingEnhancers);
            }
        }
        catch (Exception ex) {
            this.reportError(null, ex, 4);
            throw ex;
        }
    }

    @Override
    public void publish(LogRecord record) {
        LogEntry logEntry;
        if (!this.isLoggable(record)) {
            return;
        }
        if ("io.netty.handler.codec.http2.Http2FrameLogger".equals(record.getSourceClassName())) {
            return;
        }
        try {
            logEntry = this.logEntryFor(record);
        }
        catch (Exception ex) {
            this.getErrorManager().error(null, ex, 5);
            return;
        }
        if (logEntry != null) {
            try {
                this.getLogging().write(ImmutableList.of(logEntry), this.defaultWriteOptions);
            }
            catch (Exception ex) {
                this.getErrorManager().error(null, ex, 1);
            }
        }
    }

    private LogEntry logEntryFor(LogRecord record) throws Exception {
        String payload = this.getFormatter().format(record);
        Level level = record.getLevel();
        LogEntry.Builder builder = LogEntry.newBuilder(Payload.StringPayload.of(payload)).setTimestamp(record.getMillis()).setSeverity(LoggingHandler.severityFor(level));
        if (!this.baseLevel.equals(level)) {
            builder.addLabel(LEVEL_NAME_KEY, level.getName()).addLabel(LEVEL_VALUE_KEY, String.valueOf(level.intValue()));
        }
        for (LoggingEnhancer enhancer : this.enhancers) {
            enhancer.enhanceLogEntry(builder);
        }
        return builder.build();
    }

    @Override
    public void flush() {
        try {
            this.getLogging().flush();
        }
        catch (Exception ex) {
            this.getErrorManager().error(null, ex, 2);
        }
    }

    @Override
    public synchronized void close() throws SecurityException {
        if (this.logging != null) {
            try {
                this.logging.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.logging = null;
    }

    public Level getFlushLevel() {
        return this.flushLevel;
    }

    public void setFlushLevel(Level flushLevel) {
        this.flushLevel = flushLevel;
        this.getLogging().setFlushSeverity(LoggingHandler.severityFor(flushLevel));
    }

    public void setSynchronicity(Synchronicity synchronicity) {
        this.getLogging().setWriteSynchronicity(synchronicity);
    }

    public Synchronicity getSynchronicity() {
        return this.getLogging().getWriteSynchronicity();
    }

    public static void addHandler(Logger logger, LoggingHandler handler) {
        logger.addHandler(handler);
    }

    private static Severity severityFor(Level level) {
        if (level instanceof LoggingLevel) {
            return ((LoggingLevel)level).getSeverity();
        }
        switch (level.intValue()) {
            case 300: {
                return Severity.DEBUG;
            }
            case 400: {
                return Severity.DEBUG;
            }
            case 500: {
                return Severity.DEBUG;
            }
            case 700: {
                return Severity.INFO;
            }
            case 800: {
                return Severity.INFO;
            }
            case 900: {
                return Severity.WARNING;
            }
            case 1000: {
                return Severity.ERROR;
            }
        }
        return Severity.DEFAULT;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Logging getLogging() {
        if (this.logging == null) {
            LoggingHandler loggingHandler = this;
            synchronized (loggingHandler) {
                if (this.logging == null) {
                    this.logging = (Logging)this.loggingOptions.getService();
                }
            }
        }
        return this.logging;
    }
}

