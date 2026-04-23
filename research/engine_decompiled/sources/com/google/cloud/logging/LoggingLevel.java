/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.logging.Severity;
import java.util.logging.Level;

public final class LoggingLevel
extends Level {
    private static final long serialVersionUID = -6455416241709366337L;
    public static final LoggingLevel DEBUG = new LoggingLevel("DEBUG", 250, Severity.DEBUG);
    public static final LoggingLevel NOTICE = new LoggingLevel("NOTICE", 850, Severity.NOTICE);
    public static final LoggingLevel ERROR = new LoggingLevel("ERROR", 950, Severity.ERROR);
    public static final LoggingLevel CRITICAL = new LoggingLevel("CRITICAL", 1050, Severity.CRITICAL);
    public static final LoggingLevel ALERT = new LoggingLevel("ALERT", 1100, Severity.ALERT);
    public static final LoggingLevel EMERGENCY = new LoggingLevel("EMERGENCY", 1150, Severity.EMERGENCY);
    private final Severity severity;

    private LoggingLevel(String name, int value, Severity severity) {
        super(name, value);
        this.severity = severity;
    }

    public Severity getSeverity() {
        return this.severity;
    }
}

