/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.common.base.Preconditions;
import com.google.protobuf.util.Timestamps;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;

public final class Timestamp
implements Comparable<Timestamp>,
Serializable {
    private static final long serialVersionUID = 5152143600571559844L;
    public static final Timestamp MIN_VALUE = new Timestamp(-62135596800L, 0);
    public static final Timestamp MAX_VALUE = new Timestamp(253402300799L, (int)TimeUnit.SECONDS.toNanos(1L) - 1);
    private static final DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withChronology(IsoChronology.INSTANCE);
    private final long seconds;
    private final int nanos;

    private Timestamp(long seconds, int nanos) {
        this.seconds = seconds;
        this.nanos = nanos;
    }

    public static Timestamp ofTimeSecondsAndNanos(long seconds, int nanos) {
        Preconditions.checkArgument(Timestamps.isValid(seconds, nanos), "timestamp out of range: %s, %s", seconds, nanos);
        return new Timestamp(seconds, nanos);
    }

    public static Timestamp ofTimeMicroseconds(long microseconds) {
        long seconds = microseconds / 1000000L;
        int nanos = (int)(microseconds % 1000000L * 1000L);
        if (nanos < 0) {
            --seconds;
            nanos += 1000000000;
        }
        Preconditions.checkArgument(Timestamps.isValid(seconds, nanos), "timestamp out of range: %s, %s", seconds, nanos);
        return new Timestamp(seconds, nanos);
    }

    public static Timestamp of(Date date) {
        return Timestamp.ofTimeMicroseconds(TimeUnit.MILLISECONDS.toMicros(date.getTime()));
    }

    public static Timestamp now() {
        java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
        return Timestamp.of(date);
    }

    public static Timestamp of(java.sql.Timestamp timestamp) {
        return Timestamp.ofTimeSecondsAndNanos(timestamp.getTime() / 1000L, timestamp.getNanos());
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNanos() {
        return this.nanos;
    }

    public java.sql.Timestamp toSqlTimestamp() {
        java.sql.Timestamp ts = new java.sql.Timestamp(this.seconds * 1000L);
        ts.setNanos(this.nanos);
        return ts;
    }

    public Date toDate() {
        long secondsInMilliseconds = TimeUnit.SECONDS.toMillis(this.seconds);
        long nanosInMilliseconds = TimeUnit.NANOSECONDS.toMillis(this.nanos);
        return new Date(secondsInMilliseconds + nanosInMilliseconds);
    }

    @Override
    public int compareTo(Timestamp other) {
        int r = Long.compare(this.seconds, other.seconds);
        if (r == 0) {
            r = Integer.compare(this.nanos, other.nanos);
        }
        return r;
    }

    public static Timestamp fromProto(com.google.protobuf.Timestamp proto) {
        return new Timestamp(proto.getSeconds(), proto.getNanos());
    }

    public com.google.protobuf.Timestamp toProto() {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(this.seconds).setNanos(this.nanos).build();
    }

    public static Timestamp parseTimestamp(String timestamp) {
        Instant instant = Instant.parse(timestamp);
        return Timestamp.ofTimeSecondsAndNanos(instant.getEpochSecond(), instant.getNano());
    }

    private StringBuilder toString(StringBuilder b) {
        format.formatTo(LocalDateTime.ofEpochSecond(this.seconds, 0, ZoneOffset.UTC), b);
        if (this.nanos != 0) {
            b.append(String.format(".%09d", this.nanos));
        }
        b.append('Z');
        return b;
    }

    public String toString() {
        return this.toString(new StringBuilder()).toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Timestamp that = (Timestamp)o;
        return this.seconds == that.seconds && this.nanos == that.nanos;
    }

    public int hashCode() {
        return Objects.hash(this.seconds, this.nanos);
    }
}

