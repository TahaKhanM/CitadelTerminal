/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiClock;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public final class CurrentMillisClock
implements ApiClock,
Serializable {
    private static final long serialVersionUID = -6019259882852183285L;
    private static final ApiClock DEFAULT_CLOCK = new CurrentMillisClock();

    public static ApiClock getDefaultClock() {
        return DEFAULT_CLOCK;
    }

    private CurrentMillisClock() {
    }

    @Override
    public long nanoTime() {
        return TimeUnit.NANOSECONDS.convert(this.millisTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public long millisTime() {
        return System.currentTimeMillis();
    }

    private Object readResolve() throws ObjectStreamException {
        return DEFAULT_CLOCK;
    }
}

