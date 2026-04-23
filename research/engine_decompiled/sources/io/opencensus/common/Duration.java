/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import com.google.common.primitives.Longs;
import io.opencensus.common.AutoValue_Duration;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Duration
implements Comparable<Duration> {
    private static final Duration ZERO = Duration.create(0L, 0);

    public static Duration create(long seconds, int nanos) {
        if (seconds < -315576000000L || seconds > 315576000000L) {
            return ZERO;
        }
        if (nanos < -999999999 || nanos > 999999999) {
            return ZERO;
        }
        if (seconds < 0L && nanos > 0 || seconds > 0L && nanos < 0) {
            return ZERO;
        }
        return new AutoValue_Duration(seconds, nanos);
    }

    public static Duration fromMillis(long millis) {
        long seconds = millis / 1000L;
        int nanos = (int)(millis % 1000L * 1000000L);
        return Duration.create(seconds, nanos);
    }

    public abstract long getSeconds();

    public abstract int getNanos();

    @Override
    public int compareTo(Duration otherDuration) {
        int cmp = Longs.compare(this.getSeconds(), otherDuration.getSeconds());
        if (cmp != 0) {
            return cmp;
        }
        return Longs.compare(this.getNanos(), otherDuration.getNanos());
    }

    Duration() {
    }
}

