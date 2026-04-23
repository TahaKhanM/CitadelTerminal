/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingEnhancer;

public class TraceLoggingEnhancer
implements LoggingEnhancer {
    private static final String TRACE_ID = "trace_id";
    private final String traceIdLabel;
    private static final ThreadLocal<String> traceId = new ThreadLocal();

    public TraceLoggingEnhancer() {
        this.traceIdLabel = TRACE_ID;
    }

    public TraceLoggingEnhancer(String prefix) {
        this.traceIdLabel = prefix != null ? prefix + TRACE_ID : TRACE_ID;
    }

    public static void setCurrentTraceId(String id) {
        if (id == null) {
            traceId.remove();
        } else {
            traceId.set(id);
        }
    }

    public static String getCurrentTraceId() {
        return traceId.get();
    }

    @Override
    public void enhanceLogEntry(LogEntry.Builder builder) {
        String traceId = TraceLoggingEnhancer.getCurrentTraceId();
        if (traceId != null) {
            builder.setTrace(traceId);
        }
    }
}

