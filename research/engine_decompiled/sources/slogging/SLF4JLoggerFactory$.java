/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import org.slf4j.LoggerFactory;
import slogging.SLF4JLoggerFactory;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;
import slogging.UnderlyingLoggerFactory$class;

public final class SLF4JLoggerFactory$
implements UnderlyingLoggerFactory {
    public static final SLF4JLoggerFactory$ MODULE$;

    static {
        new SLF4JLoggerFactory$();
    }

    @Override
    public UnderlyingLoggerFactory apply() {
        return UnderlyingLoggerFactory$class.apply(this);
    }

    @Override
    public UnderlyingLogger getUnderlyingLogger(String name) {
        return new SLF4JLoggerFactory.SLF4JLogger(LoggerFactory.getLogger(name));
    }

    private SLF4JLoggerFactory$() {
        MODULE$ = this;
        UnderlyingLoggerFactory$class.$init$(this);
    }
}

