/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf.util;

import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Durations;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public final class Timestamps {
    static final long TIMESTAMP_SECONDS_MIN = -62135596800L;
    static final long TIMESTAMP_SECONDS_MAX = 253402300799L;
    static final long NANOS_PER_SECOND = 1000000000L;
    static final long NANOS_PER_MILLISECOND = 1000000L;
    static final long NANOS_PER_MICROSECOND = 1000L;
    static final long MILLIS_PER_SECOND = 1000L;
    static final long MICROS_PER_SECOND = 1000000L;
    public static final Timestamp MIN_VALUE = Timestamp.newBuilder().setSeconds(-62135596800L).setNanos(0).build();
    public static final Timestamp MAX_VALUE = Timestamp.newBuilder().setSeconds(253402300799L).setNanos(999999999).build();
    public static final Timestamp EPOCH = Timestamp.newBuilder().setSeconds(0L).setNanos(0).build();
    private static final ThreadLocal<SimpleDateFormat> timestampFormat = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return Timestamps.createTimestampFormat();
        }
    };
    private static final Comparator<Timestamp> COMPARATOR = new Comparator<Timestamp>(){

        @Override
        public int compare(Timestamp t1, Timestamp t2) {
            Timestamps.checkValid(t1);
            Timestamps.checkValid(t2);
            int secDiff = Long.compare(t1.getSeconds(), t2.getSeconds());
            return secDiff != 0 ? secDiff : Integer.compare(t1.getNanos(), t2.getNanos());
        }
    };

    private static SimpleDateFormat createTimestampFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.setGregorianChange(new Date(Long.MIN_VALUE));
        sdf.setCalendar(calendar);
        return sdf;
    }

    private Timestamps() {
    }

    public static Comparator<Timestamp> comparator() {
        return COMPARATOR;
    }

    public static int compare(Timestamp x, Timestamp y) {
        return COMPARATOR.compare(x, y);
    }

    public static boolean isValid(Timestamp timestamp) {
        return Timestamps.isValid(timestamp.getSeconds(), timestamp.getNanos());
    }

    public static boolean isValid(long seconds, int nanos) {
        if (seconds < -62135596800L || seconds > 253402300799L) {
            return false;
        }
        return nanos >= 0 && (long)nanos < 1000000000L;
    }

    public static Timestamp checkValid(Timestamp timestamp) {
        int nanos;
        long seconds = timestamp.getSeconds();
        if (!Timestamps.isValid(seconds, nanos = timestamp.getNanos())) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", seconds, nanos));
        }
        return timestamp;
    }

    public static String toString(Timestamp timestamp) {
        Timestamps.checkValid(timestamp);
        long seconds = timestamp.getSeconds();
        int nanos = timestamp.getNanos();
        StringBuilder result2 = new StringBuilder();
        Date date = new Date(seconds * 1000L);
        result2.append(timestampFormat.get().format(date));
        if (nanos != 0) {
            result2.append(".");
            result2.append(Timestamps.formatNanos(nanos));
        }
        result2.append("Z");
        return result2.toString();
    }

    public static Timestamp parse(String value) throws ParseException {
        int nanos;
        String timeValue;
        int dayOffset = value.indexOf(84);
        if (dayOffset == -1) {
            throw new ParseException("Failed to parse timestamp: invalid timestamp \"" + value + "\"", 0);
        }
        int timezoneOffsetPosition = value.indexOf(90, dayOffset);
        if (timezoneOffsetPosition == -1) {
            timezoneOffsetPosition = value.indexOf(43, dayOffset);
        }
        if (timezoneOffsetPosition == -1) {
            timezoneOffsetPosition = value.indexOf(45, dayOffset);
        }
        if (timezoneOffsetPosition == -1) {
            throw new ParseException("Failed to parse timestamp: missing valid timezone offset.", 0);
        }
        String secondValue = timeValue = value.substring(0, timezoneOffsetPosition);
        String nanoValue = "";
        int pointPosition = timeValue.indexOf(46);
        if (pointPosition != -1) {
            secondValue = timeValue.substring(0, pointPosition);
            nanoValue = timeValue.substring(pointPosition + 1);
        }
        Date date = timestampFormat.get().parse(secondValue);
        long seconds = date.getTime() / 1000L;
        int n = nanos = nanoValue.isEmpty() ? 0 : Timestamps.parseNanos(nanoValue);
        if (value.charAt(timezoneOffsetPosition) == 'Z') {
            if (value.length() != timezoneOffsetPosition + 1) {
                throw new ParseException("Failed to parse timestamp: invalid trailing data \"" + value.substring(timezoneOffsetPosition) + "\"", 0);
            }
        } else {
            String offsetValue = value.substring(timezoneOffsetPosition + 1);
            long offset = Timestamps.parseTimezoneOffset(offsetValue);
            seconds = value.charAt(timezoneOffsetPosition) == '+' ? (seconds -= offset) : (seconds += offset);
        }
        try {
            return Timestamps.normalizedTimestamp(seconds, nanos);
        }
        catch (IllegalArgumentException e) {
            throw new ParseException("Failed to parse timestamp: timestamp is out of range.", 0);
        }
    }

    public static Timestamp fromSeconds(long seconds) {
        return Timestamps.normalizedTimestamp(seconds, 0);
    }

    public static long toSeconds(Timestamp timestamp) {
        return Timestamps.checkValid(timestamp).getSeconds();
    }

    public static Timestamp fromMillis(long milliseconds) {
        return Timestamps.normalizedTimestamp(milliseconds / 1000L, (int)(milliseconds % 1000L * 1000000L));
    }

    public static long toMillis(Timestamp timestamp) {
        Timestamps.checkValid(timestamp);
        return LongMath.checkedAdd(LongMath.checkedMultiply(timestamp.getSeconds(), 1000L), (long)timestamp.getNanos() / 1000000L);
    }

    public static Timestamp fromMicros(long microseconds) {
        return Timestamps.normalizedTimestamp(microseconds / 1000000L, (int)(microseconds % 1000000L * 1000L));
    }

    public static long toMicros(Timestamp timestamp) {
        Timestamps.checkValid(timestamp);
        return LongMath.checkedAdd(LongMath.checkedMultiply(timestamp.getSeconds(), 1000000L), (long)timestamp.getNanos() / 1000L);
    }

    public static Timestamp fromNanos(long nanoseconds) {
        return Timestamps.normalizedTimestamp(nanoseconds / 1000000000L, (int)(nanoseconds % 1000000000L));
    }

    public static long toNanos(Timestamp timestamp) {
        Timestamps.checkValid(timestamp);
        return LongMath.checkedAdd(LongMath.checkedMultiply(timestamp.getSeconds(), 1000000000L), timestamp.getNanos());
    }

    public static Duration between(Timestamp from2, Timestamp to2) {
        Timestamps.checkValid(from2);
        Timestamps.checkValid(to2);
        return Durations.normalizedDuration(LongMath.checkedSubtract(to2.getSeconds(), from2.getSeconds()), IntMath.checkedSubtract(to2.getNanos(), from2.getNanos()));
    }

    public static Timestamp add(Timestamp start, Duration length) {
        Timestamps.checkValid(start);
        Durations.checkValid(length);
        return Timestamps.normalizedTimestamp(LongMath.checkedAdd(start.getSeconds(), length.getSeconds()), IntMath.checkedAdd(start.getNanos(), length.getNanos()));
    }

    public static Timestamp subtract(Timestamp start, Duration length) {
        Timestamps.checkValid(start);
        Durations.checkValid(length);
        return Timestamps.normalizedTimestamp(LongMath.checkedSubtract(start.getSeconds(), length.getSeconds()), IntMath.checkedSubtract(start.getNanos(), length.getNanos()));
    }

    static Timestamp normalizedTimestamp(long seconds, int nanos) {
        if ((long)nanos <= -1000000000L || (long)nanos >= 1000000000L) {
            seconds = LongMath.checkedAdd(seconds, (long)nanos / 1000000000L);
            nanos = (int)((long)nanos % 1000000000L);
        }
        if (nanos < 0) {
            nanos = (int)((long)nanos + 1000000000L);
            seconds = LongMath.checkedSubtract(seconds, 1L);
        }
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(seconds).setNanos(nanos).build();
        return Timestamps.checkValid(timestamp);
    }

    private static long parseTimezoneOffset(String value) throws ParseException {
        int pos = value.indexOf(58);
        if (pos == -1) {
            throw new ParseException("Invalid offset value: " + value, 0);
        }
        String hours = value.substring(0, pos);
        String minutes = value.substring(pos + 1);
        return (Long.parseLong(hours) * 60L + Long.parseLong(minutes)) * 60L;
    }

    static int parseNanos(String value) throws ParseException {
        int result2 = 0;
        for (int i = 0; i < 9; ++i) {
            result2 *= 10;
            if (i >= value.length()) continue;
            if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                throw new ParseException("Invalid nanoseconds.", 0);
            }
            result2 += value.charAt(i) - 48;
        }
        return result2;
    }

    static String formatNanos(int nanos) {
        if ((long)nanos % 1000000L == 0L) {
            return String.format(Locale.ENGLISH, "%1$03d", (long)nanos / 1000000L);
        }
        if ((long)nanos % 1000L == 0L) {
            return String.format(Locale.ENGLISH, "%1$06d", (long)nanos / 1000L);
        }
        return String.format(Locale.ENGLISH, "%1$09d", nanos);
    }
}

