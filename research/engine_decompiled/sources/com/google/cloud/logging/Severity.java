/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.logging.type.LogSeverity;

public enum Severity {
    DEFAULT(LogSeverity.DEFAULT),
    DEBUG(LogSeverity.DEBUG),
    INFO(LogSeverity.INFO),
    NOTICE(LogSeverity.NOTICE),
    WARNING(LogSeverity.WARNING),
    ERROR(LogSeverity.ERROR),
    CRITICAL(LogSeverity.CRITICAL),
    ALERT(LogSeverity.ALERT),
    EMERGENCY(LogSeverity.EMERGENCY);

    private final LogSeverity versionPb;

    private Severity(LogSeverity versionPb) {
        this.versionPb = versionPb;
    }

    LogSeverity toPb() {
        return this.versionPb;
    }

    static Severity fromPb(LogSeverity severityPb) {
        switch (severityPb) {
            case DEFAULT: {
                return DEFAULT;
            }
            case DEBUG: {
                return DEBUG;
            }
            case INFO: {
                return INFO;
            }
            case NOTICE: {
                return NOTICE;
            }
            case WARNING: {
                return WARNING;
            }
            case ERROR: {
                return ERROR;
            }
            case CRITICAL: {
                return CRITICAL;
            }
            case ALERT: {
                return ALERT;
            }
            case EMERGENCY: {
                return EMERGENCY;
            }
        }
        throw new IllegalArgumentException(severityPb + " is not a valid severity");
    }
}

