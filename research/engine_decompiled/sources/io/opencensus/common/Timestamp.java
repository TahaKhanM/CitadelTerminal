/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;
import io.opencensus.common.AutoValue_Timestamp;
import io.opencensus.common.Duration;
import java.math.RoundingMode;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Timestamp
implements Comparable<Timestamp> {
    private static final Timestamp EPOCH = new AutoValue_Timestamp(0L, 0);

    Timestamp() {
    }

    public static Timestamp create(long seconds, int nanos) {
        if (seconds < -315576000000L || seconds > 315576000000L) {
            return EPOCH;
        }
        if (nanos < 0 || nanos > 999999999) {
            return EPOCH;
        }
        return new AutoValue_Timestamp(seconds, nanos);
    }

    public static Timestamp fromMillis(long epochMilli) {
        long secs = Timestamp.floorDiv(epochMilli, 1000L);
        int mos = (int)Timestamp.floorMod(epochMilli, 1000L);
        return Timestamp.create(secs, (int)((long)mos * 1000000L));
    }

    public abstract long getSeconds();

    public abstract int getNanos();

    public Timestamp addNanos(long nanosToAdd) {
        return this.plus(0L, nanosToAdd);
    }

    public Timestamp addDuration(Duration duration) {
        return this.plus(duration.getSeconds(), duration.getNanos());
    }

    public Duration subtractTimestamp(Timestamp timestamp) {
        long durationSeconds = this.getSeconds() - timestamp.getSeconds();
        int durationNanos = this.getNanos() - timestamp.getNanos();
        if (durationSeconds < 0L && durationNanos > 0) {
            ++durationSeconds;
            durationNanos = (int)((long)durationNanos - 1000000000L);
        } else if (durationSeconds > 0L && durationNanos < 0) {
            --durationSeconds;
            durationNanos = (int)((long)durationNanos + 1000000000L);
        }
        return Duration.create(durationSeconds, durationNanos);
    }

    @Override
    public int compareTo(Timestamp otherTimestamp) {
        int cmp = Longs.compare(this.getSeconds(), otherTimestamp.getSeconds());
        if (cmp != 0) {
            return cmp;
        }
        return Longs.compare(this.getNanos(), otherTimestamp.getNanos());
    }

    private Timestamp plus(long secondsToAdd, long nanosToAdd) {
        if ((secondsToAdd | nanosToAdd) == 0L) {
            return this;
        }
        long epochSec = LongMath.checkedAdd(this.getSeconds(), secondsToAdd);
        epochSec = LongMath.checkedAdd(epochSec, nanosToAdd / 1000000000L);
        long nanoAdjustment = (long)this.getNanos() + (nanosToAdd %= 1000000000L);
        return Timestamp.ofEpochSecond(epochSec, nanoAdjustment);
    }

    private static Timestamp ofEpochSecond(long epochSecond, long nanoAdjustment) {
        long secs = LongMath.checkedAdd(epochSecond, Timestamp.floorDiv(nanoAdjustment, 1000000000L));
        int nos = (int)Timestamp.floorMod(nanoAdjustment, 1000000000L);
        return Timestamp.create(secs, nos);
    }

    private static long floorDiv(long x, long y) {
        return LongMath.divide(x, y, RoundingMode.FLOOR);
    }

    private static long floorMod(long x, long y) {
        return x - Timestamp.floorDiv(x, y) * y;
    }
}

