/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.Logger$;
import slogging.LoggerConfig$;
import slogging.UnderlyingLogger;

public final class LoggerFactory$ {
    public static final LoggerFactory$ MODULE$;

    static {
        new LoggerFactory$();
    }

    public UnderlyingLogger getLogger(String name) {
        return Logger$.MODULE$.apply(LoggerConfig$.MODULE$.factory().getUnderlyingLogger(name));
    }

    private LoggerFactory$() {
        MODULE$ = this;
    }
}

