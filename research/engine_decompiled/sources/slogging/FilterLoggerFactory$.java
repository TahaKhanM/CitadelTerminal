/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.FilterLogger;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;
import slogging.UnderlyingLoggerFactory$class;

public final class FilterLoggerFactory$
implements UnderlyingLoggerFactory {
    public static final FilterLoggerFactory$ MODULE$;

    static {
        new FilterLoggerFactory$();
    }

    @Override
    public UnderlyingLoggerFactory apply() {
        return UnderlyingLoggerFactory$class.apply(this);
    }

    @Override
    public UnderlyingLogger getUnderlyingLogger(String name) {
        return new FilterLogger(name);
    }

    private FilterLoggerFactory$() {
        MODULE$ = this;
        UnderlyingLoggerFactory$class.$init$(this);
    }
}

