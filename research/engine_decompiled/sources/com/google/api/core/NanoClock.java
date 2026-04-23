/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiClock;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public final class NanoClock
implements ApiClock,
Serializable {
    private static final ApiClock DEFAULT_CLOCK = new NanoClock();
    private static final long serialVersionUID = 5541462688633944865L;

    public static ApiClock getDefaultClock() {
        return DEFAULT_CLOCK;
    }

    private NanoClock() {
    }

    @Override
    public final long nanoTime() {
        return System.nanoTime();
    }

    @Override
    public final long millisTime() {
        return TimeUnit.MILLISECONDS.convert(this.nanoTime(), TimeUnit.NANOSECONDS);
    }

    private Object readResolve() throws ObjectStreamException {
        return DEFAULT_CLOCK;
    }
}

