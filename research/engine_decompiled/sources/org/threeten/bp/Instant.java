/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Duration;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class Instant
extends DefaultInterfaceTemporalAccessor
implements Temporal,
TemporalAdjuster,
Comparable<Instant>,
Serializable {
    public static final Instant EPOCH = new Instant(0L, 0);
    private static final long MIN_SECOND = -31557014167219200L;
    private static final long MAX_SECOND = 31556889864403199L;
    public static final Instant MIN = Instant.ofEpochSecond(-31557014167219200L, 0L);
    public static final Instant MAX = Instant.ofEpochSecond(31556889864403199L, 999999999L);
    public static final TemporalQuery<Instant> FROM = new TemporalQuery<Instant>(){

        @Override
        public Instant queryFrom(TemporalAccessor temporal) {
            return Instant.from(temporal);
        }
    };
    private static final long serialVersionUID = -665713676816604388L;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final int NANOS_PER_MILLI = 1000000;
    private static final long MILLIS_PER_SEC = 1000L;
    private final long seconds;
    private final int nanos;

    public static Instant now() {
        return Clock.systemUTC().instant();
    }

    public static Instant now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return clock.instant();
    }

    public static Instant ofEpochSecond(long epochSecond) {
        return Instant.create(epochSecond, 0);
    }

    public static Instant ofEpochSecond(long epochSecond, long nanoAdjustment) {
        long secs = Jdk8Methods.safeAdd(epochSecond, Jdk8Methods.floorDiv(nanoAdjustment, 1000000000L));
        int nos = Jdk8Methods.floorMod(nanoAdjustment, 1000000000);
        return Instant.create(secs, nos);
    }

    public static Instant ofEpochMilli(long epochMilli) {
        long secs = Jdk8Methods.floorDiv(epochMilli, 1000L);
        int mos = Jdk8Methods.floorMod(epochMilli, 1000);
        return Instant.create(secs, mos * 1000000);
    }

    public static Instant from(TemporalAccessor temporal) {
        try {
            long instantSecs = temporal.getLong(ChronoField.INSTANT_SECONDS);
            int nanoOfSecond = temporal.get(ChronoField.NANO_OF_SECOND);
            return Instant.ofEpochSecond(instantSecs, nanoOfSecond);
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Instant from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName(), ex);
        }
    }

    public static Instant parse(CharSequence text) {
        return DateTimeFormatter.ISO_INSTANT.parse(text, FROM);
    }

    private static Instant create(long seconds, int nanoOfSecond) {
        if ((seconds | (long)nanoOfSecond) == 0L) {
            return EPOCH;
        }
        if (seconds < -31557014167219200L || seconds > 31556889864403199L) {
            throw new DateTimeException("Instant exceeds minimum or maximum instant");
        }
        return new Instant(seconds, nanoOfSecond);
    }

    private Instant(long epochSecond, int nanos) {
        this.seconds = epochSecond;
        this.nanos = nanos;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.INSTANT_SECONDS || field2 == ChronoField.NANO_OF_SECOND || field2 == ChronoField.MICRO_OF_SECOND || field2 == ChronoField.MILLI_OF_SECOND;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isTimeBased() || unit == ChronoUnit.DAYS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        return super.range(field2);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case NANO_OF_SECOND: {
                    return this.nanos;
                }
                case MICRO_OF_SECOND: {
                    return this.nanos / 1000;
                }
                case MILLI_OF_SECOND: {
                    return this.nanos / 1000000;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return this.range(field2).checkValidIntValue(field2.getFrom(this), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case NANO_OF_SECOND: {
                    return this.nanos;
                }
                case MICRO_OF_SECOND: {
                    return this.nanos / 1000;
                }
                case MILLI_OF_SECOND: {
                    return this.nanos / 1000000;
                }
                case INSTANT_SECONDS: {
                    return this.seconds;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    public long getEpochSecond() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    @Override
    public Instant with(TemporalAdjuster adjuster) {
        return (Instant)adjuster.adjustInto(this);
    }

    @Override
    public Instant with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            switch (f) {
                case MILLI_OF_SECOND: {
                    int nval = (int)newValue * 1000000;
                    return nval != this.nanos ? Instant.create(this.seconds, nval) : this;
                }
                case MICRO_OF_SECOND: {
                    int nval = (int)newValue * 1000;
                    return nval != this.nanos ? Instant.create(this.seconds, nval) : this;
                }
                case NANO_OF_SECOND: {
                    return newValue != (long)this.nanos ? Instant.create(this.seconds, (int)newValue) : this;
                }
                case INSTANT_SECONDS: {
                    return newValue != this.seconds ? Instant.create(newValue, this.nanos) : this;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.adjustInto(this, newValue);
    }

    public Instant truncatedTo(TemporalUnit unit) {
        if (unit == ChronoUnit.NANOS) {
            return this;
        }
        Duration unitDur = unit.getDuration();
        if (unitDur.getSeconds() > 86400L) {
            throw new DateTimeException("Unit is too large to be used for truncation");
        }
        long dur = unitDur.toNanos();
        if (86400000000000L % dur != 0L) {
            throw new DateTimeException("Unit must divide into a standard day without remainder");
        }
        long nod = this.seconds % 86400L * 1000000000L + (long)this.nanos;
        long result2 = Jdk8Methods.floorDiv(nod, dur) * dur;
        return this.plusNanos(result2 - nod);
    }

    @Override
    public Instant plus(TemporalAmount amount) {
        return (Instant)amount.addTo(this);
    }

    @Override
    public Instant plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            switch ((ChronoUnit)unit) {
                case NANOS: {
                    return this.plusNanos(amountToAdd);
                }
                case MICROS: {
                    return this.plus(amountToAdd / 1000000L, amountToAdd % 1000000L * 1000L);
                }
                case MILLIS: {
                    return this.plusMillis(amountToAdd);
                }
                case SECONDS: {
                    return this.plusSeconds(amountToAdd);
                }
                case MINUTES: {
                    return this.plusSeconds(Jdk8Methods.safeMultiply(amountToAdd, 60));
                }
                case HOURS: {
                    return this.plusSeconds(Jdk8Methods.safeMultiply(amountToAdd, 3600));
                }
                case HALF_DAYS: {
                    return this.plusSeconds(Jdk8Methods.safeMultiply(amountToAdd, 43200));
                }
                case DAYS: {
                    return this.plusSeconds(Jdk8Methods.safeMultiply(amountToAdd, 86400));
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.addTo(this, amountToAdd);
    }

    public Instant plusSeconds(long secondsToAdd) {
        return this.plus(secondsToAdd, 0L);
    }

    public Instant plusMillis(long millisToAdd) {
        return this.plus(millisToAdd / 1000L, millisToAdd % 1000L * 1000000L);
    }

    public Instant plusNanos(long nanosToAdd) {
        return this.plus(0L, nanosToAdd);
    }

    private Instant plus(long secondsToAdd, long nanosToAdd) {
        if ((secondsToAdd | nanosToAdd) == 0L) {
            return this;
        }
        long epochSec = Jdk8Methods.safeAdd(this.seconds, secondsToAdd);
        epochSec = Jdk8Methods.safeAdd(epochSec, nanosToAdd / 1000000000L);
        long nanoAdjustment = (long)this.nanos + (nanosToAdd %= 1000000000L);
        return Instant.ofEpochSecond(epochSec, nanoAdjustment);
    }

    @Override
    public Instant minus(TemporalAmount amount) {
        return (Instant)amount.subtractFrom(this);
    }

    @Override
    public Instant minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public Instant minusSeconds(long secondsToSubtract) {
        if (secondsToSubtract == Long.MIN_VALUE) {
            return this.plusSeconds(Long.MAX_VALUE).plusSeconds(1L);
        }
        return this.plusSeconds(-secondsToSubtract);
    }

    public Instant minusMillis(long millisToSubtract) {
        if (millisToSubtract == Long.MIN_VALUE) {
            return this.plusMillis(Long.MAX_VALUE).plusMillis(1L);
        }
        return this.plusMillis(-millisToSubtract);
    }

    public Instant minusNanos(long nanosToSubtract) {
        if (nanosToSubtract == Long.MIN_VALUE) {
            return this.plusNanos(Long.MAX_VALUE).plusNanos(1L);
        }
        return this.plusNanos(-nanosToSubtract);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.localDate() || query == TemporalQueries.localTime() || query == TemporalQueries.chronology() || query == TemporalQueries.zoneId() || query == TemporalQueries.zone() || query == TemporalQueries.offset()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.INSTANT_SECONDS, this.seconds).with(ChronoField.NANO_OF_SECOND, this.nanos);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        Instant end = Instant.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case NANOS: {
                    return this.nanosUntil(end);
                }
                case MICROS: {
                    return this.nanosUntil(end) / 1000L;
                }
                case MILLIS: {
                    return Jdk8Methods.safeSubtract(end.toEpochMilli(), this.toEpochMilli());
                }
                case SECONDS: {
                    return this.secondsUntil(end);
                }
                case MINUTES: {
                    return this.secondsUntil(end) / 60L;
                }
                case HOURS: {
                    return this.secondsUntil(end) / 3600L;
                }
                case HALF_DAYS: {
                    return this.secondsUntil(end) / 43200L;
                }
                case DAYS: {
                    return this.secondsUntil(end) / 86400L;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    private long nanosUntil(Instant end) {
        long secsDiff = Jdk8Methods.safeSubtract(end.seconds, this.seconds);
        long totalNanos = Jdk8Methods.safeMultiply(secsDiff, 1000000000);
        return Jdk8Methods.safeAdd(totalNanos, (long)(end.nanos - this.nanos));
    }

    private long secondsUntil(Instant end) {
        long secsDiff = Jdk8Methods.safeSubtract(end.seconds, this.seconds);
        long nanosDiff = end.nanos - this.nanos;
        if (secsDiff > 0L && nanosDiff < 0L) {
            --secsDiff;
        } else if (secsDiff < 0L && nanosDiff > 0L) {
            ++secsDiff;
        }
        return secsDiff;
    }

    public OffsetDateTime atOffset(ZoneOffset offset) {
        return OffsetDateTime.ofInstant(this, offset);
    }

    public ZonedDateTime atZone(ZoneId zone) {
        return ZonedDateTime.ofInstant(this, zone);
    }

    public long toEpochMilli() {
        if (this.seconds >= 0L) {
            long millis = Jdk8Methods.safeMultiply(this.seconds, 1000L);
            return Jdk8Methods.safeAdd(millis, (long)(this.nanos / 1000000));
        }
        long millis = Jdk8Methods.safeMultiply(this.seconds + 1L, 1000L);
        return Jdk8Methods.safeSubtract(millis, 1000L - (long)(this.nanos / 1000000));
    }

    @Override
    public int compareTo(Instant otherInstant) {
        int cmp = Jdk8Methods.compareLongs(this.seconds, otherInstant.seconds);
        if (cmp != 0) {
            return cmp;
        }
        return this.nanos - otherInstant.nanos;
    }

    public boolean isAfter(Instant otherInstant) {
        return this.compareTo(otherInstant) > 0;
    }

    public boolean isBefore(Instant otherInstant) {
        return this.compareTo(otherInstant) < 0;
    }

    public boolean equals(Object otherInstant) {
        if (this == otherInstant) {
            return true;
        }
        if (otherInstant instanceof Instant) {
            Instant other = (Instant)otherInstant;
            return this.seconds == other.seconds && this.nanos == other.nanos;
        }
        return false;
    }

    public int hashCode() {
        return (int)(this.seconds ^ this.seconds >>> 32) + 51 * this.nanos;
    }

    public String toString() {
        return DateTimeFormatter.ISO_INSTANT.format(this);
    }

    private Object writeReplace() {
        return new Ser(2, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeLong(this.seconds);
        out.writeInt(this.nanos);
    }

    static Instant readExternal(DataInput in) throws IOException {
        long seconds = in.readLong();
        int nanos = in.readInt();
        return Instant.ofEpochSecond(seconds, nanos);
    }
}

