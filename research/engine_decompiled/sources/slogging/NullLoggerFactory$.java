/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.NullLogger$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;
import slogging.UnderlyingLoggerFactory$class;

public final class NullLoggerFactory$
implements UnderlyingLoggerFactory {
    public static final NullLoggerFactory$ MODULE$;

    static {
        new NullLoggerFactory$();
    }

    @Override
    public UnderlyingLoggerFactory apply() {
        return UnderlyingLoggerFactory$class.apply(this);
    }

    @Override
    public UnderlyingLogger getUnderlyingLogger(String name) {
        return NullLogger$.MODULE$;
    }

    private NullLoggerFactory$() {
        MODULE$ = this;
        UnderlyingLoggerFactory$class.$init$(this);
    }
}

