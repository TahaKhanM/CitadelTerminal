/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf.util;

import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Durations;
import com.google.protobuf.util.Timestamps;
import java.math.BigInteger;
import java.text.ParseException;

@Deprecated
public final class TimeUtil {
    public static final long TIMESTAMP_SECONDS_MIN = -62135596800L;
    public static final long TIMESTAMP_SECONDS_MAX = 253402300799L;
    public static final long DURATION_SECONDS_MIN = -315576000000L;
    public static final long DURATION_SECONDS_MAX = 315576000000L;
    private static final long NANOS_PER_SECOND = 1000000000L;
    private static final BigInteger NANOS_PER_SECOND_BIG_INTEGER = new BigInteger(String.valueOf(1000000000L));

    private TimeUtil() {
    }

    @Deprecated
    public static String toString(Timestamp timestamp) {
        return Timestamps.toString(timestamp);
    }

    @Deprecated
    public static Timestamp parseTimestamp(String value) throws ParseException {
        return Timestamps.parse(value);
    }

    @Deprecated
    public static String toString(Duration duration) {
        return Durations.toString(duration);
    }

    @Deprecated
    public static Duration parseDuration(String value) throws ParseException {
        return Durations.parse(value);
    }

    @Deprecated
    public static Timestamp createTimestampFromMillis(long milliseconds) {
        return Timestamps.fromMillis(milliseconds);
    }

    @Deprecated
    public static Duration createDurationFromMillis(long milliseconds) {
        return Durations.fromMillis(milliseconds);
    }

    @Deprecated
    public static long toMillis(Timestamp timestamp) {
        return Timestamps.toMillis(timestamp);
    }

    @Deprecated
    public static long toMillis(Duration duration) {
        return Durations.toMillis(duration);
    }

    @Deprecated
    public static Timestamp createTimestampFromMicros(long microseconds) {
        return Timestamps.fromMicros(microseconds);
    }

    @Deprecated
    public static Duration createDurationFromMicros(long microseconds) {
        return Durations.fromMicros(microseconds);
    }

    @Deprecated
    public static long toMicros(Timestamp timestamp) {
        return Timestamps.toMicros(timestamp);
    }

    @Deprecated
    public static long toMicros(Duration duration) {
        return Durations.toMicros(duration);
    }

    @Deprecated
    public static Timestamp createTimestampFromNanos(long nanoseconds) {
        return Timestamps.fromNanos(nanoseconds);
    }

    @Deprecated
    public static Duration createDurationFromNanos(long nanoseconds) {
        return Durations.fromNanos(nanoseconds);
    }

    @Deprecated
    public static long toNanos(Timestamp timestamp) {
        return Timestamps.toNanos(timestamp);
    }

    @Deprecated
    public static long toNanos(Duration duration) {
        return Durations.toNanos(duration);
    }

    @Deprecated
    public static Timestamp getCurrentTime() {
        return Timestamps.fromMillis(System.currentTimeMillis());
    }

    @Deprecated
    public static Timestamp getEpoch() {
        return Timestamp.getDefaultInstance();
    }

    @Deprecated
    public static Duration distance(Timestamp from2, Timestamp to2) {
        return Timestamps.between(from2, to2);
    }

    @Deprecated
    public static Timestamp add(Timestamp start, Duration length) {
        return Timestamps.add(start, length);
    }

    @Deprecated
    public static Timestamp subtract(Timestamp start, Duration length) {
        return Timestamps.subtract(start, length);
    }

    @Deprecated
    public static Duration add(Duration d1, Duration d2) {
        return Durations.add(d1, d2);
    }

    @Deprecated
    public static Duration subtract(Duration d1, Duration d2) {
        return Durations.subtract(d1, d2);
    }

    public static Duration multiply(Duration duration, double times2) {
        double result2 = (double)duration.getSeconds() * times2 + (double)duration.getNanos() * times2 / 1.0E9;
        if (result2 < -9.223372036854776E18 || result2 > 9.223372036854776E18) {
            throw new IllegalArgumentException("Result is out of valid range.");
        }
        long seconds = (long)result2;
        int nanos = (int)((result2 - (double)seconds) * 1.0E9);
        return TimeUtil.normalizedDuration(seconds, nanos);
    }

    public static Duration divide(Duration duration, double value) {
        return TimeUtil.multiply(duration, 1.0 / value);
    }

    public static Duration multiply(Duration duration, long times2) {
        return TimeUtil.createDurationFromBigInteger(TimeUtil.toBigInteger(duration).multiply(TimeUtil.toBigInteger(times2)));
    }

    public static Duration divide(Duration duration, long times2) {
        return TimeUtil.createDurationFromBigInteger(TimeUtil.toBigInteger(duration).divide(TimeUtil.toBigInteger(times2)));
    }

    public static long divide(Duration d1, Duration d2) {
        return TimeUtil.toBigInteger(d1).divide(TimeUtil.toBigInteger(d2)).longValue();
    }

    public static Duration remainder(Duration d1, Duration d2) {
        return TimeUtil.createDurationFromBigInteger(TimeUtil.toBigInteger(d1).remainder(TimeUtil.toBigInteger(d2)));
    }

    private static BigInteger toBigInteger(Duration duration) {
        return TimeUtil.toBigInteger(duration.getSeconds()).multiply(NANOS_PER_SECOND_BIG_INTEGER).add(TimeUtil.toBigInteger(duration.getNanos()));
    }

    private static BigInteger toBigInteger(long value) {
        return new BigInteger(String.valueOf(value));
    }

    private static Duration createDurationFromBigInteger(BigInteger value) {
        long seconds = value.divide(new BigInteger(String.valueOf(1000000000L))).longValue();
        int nanos = value.remainder(new BigInteger(String.valueOf(1000000000L))).intValue();
        return TimeUtil.normalizedDuration(seconds, nanos);
    }

    private static Duration normalizedDuration(long seconds, int nanos) {
        if ((long)nanos <= -1000000000L || (long)nanos >= 1000000000L) {
            seconds += (long)nanos / 1000000000L;
            nanos = (int)((long)nanos % 1000000000L);
        }
        if (seconds > 0L && nanos < 0) {
            nanos = (int)((long)nanos + 1000000000L);
            --seconds;
        }
        if (seconds < 0L && nanos > 0) {
            nanos = (int)((long)nanos - 1000000000L);
            ++seconds;
        }
        if (seconds < -315576000000L || seconds > 315576000000L) {
            throw new IllegalArgumentException("Duration is out of valid range.");
        }
        return Duration.newBuilder().setSeconds(seconds).setNanos(nanos).build();
    }
}

