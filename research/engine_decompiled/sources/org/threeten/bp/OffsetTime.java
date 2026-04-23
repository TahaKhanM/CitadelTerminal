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
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
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
import org.threeten.bp.zone.ZoneRules;

public final class OffsetTime
extends DefaultInterfaceTemporalAccessor
implements Temporal,
TemporalAdjuster,
Comparable<OffsetTime>,
Serializable {
    public static final OffsetTime MIN = LocalTime.MIN.atOffset(ZoneOffset.MAX);
    public static final OffsetTime MAX = LocalTime.MAX.atOffset(ZoneOffset.MIN);
    public static final TemporalQuery<OffsetTime> FROM = new TemporalQuery<OffsetTime>(){

        @Override
        public OffsetTime queryFrom(TemporalAccessor temporal) {
            return OffsetTime.from(temporal);
        }
    };
    private static final long serialVersionUID = 7264499704384272492L;
    private final LocalTime time;
    private final ZoneOffset offset;

    public static OffsetTime now() {
        return OffsetTime.now(Clock.systemDefaultZone());
    }

    public static OffsetTime now(ZoneId zone) {
        return OffsetTime.now(Clock.system(zone));
    }

    public static OffsetTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        return OffsetTime.ofInstant(now, clock.getZone().getRules().getOffset(now));
    }

    public static OffsetTime of(LocalTime time, ZoneOffset offset) {
        return new OffsetTime(time, offset);
    }

    public static OffsetTime of(int hour, int minute, int second, int nanoOfSecond, ZoneOffset offset) {
        return new OffsetTime(LocalTime.of(hour, minute, second, nanoOfSecond), offset);
    }

    public static OffsetTime ofInstant(Instant instant, ZoneId zone) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zone, "zone");
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instant);
        long secsOfDay = instant.getEpochSecond() % 86400L;
        secsOfDay = (secsOfDay + (long)offset.getTotalSeconds()) % 86400L;
        if (secsOfDay < 0L) {
            secsOfDay += 86400L;
        }
        LocalTime time = LocalTime.ofSecondOfDay(secsOfDay, instant.getNano());
        return new OffsetTime(time, offset);
    }

    public static OffsetTime from(TemporalAccessor temporal) {
        if (temporal instanceof OffsetTime) {
            return (OffsetTime)temporal;
        }
        try {
            LocalTime time = LocalTime.from(temporal);
            ZoneOffset offset = ZoneOffset.from(temporal);
            return new OffsetTime(time, offset);
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain OffsetTime from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
    }

    public static OffsetTime parse(CharSequence text) {
        return OffsetTime.parse(text, DateTimeFormatter.ISO_OFFSET_TIME);
    }

    public static OffsetTime parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private OffsetTime(LocalTime time, ZoneOffset offset) {
        this.time = Jdk8Methods.requireNonNull(time, "time");
        this.offset = Jdk8Methods.requireNonNull(offset, "offset");
    }

    private OffsetTime with(LocalTime time, ZoneOffset offset) {
        if (this.time == time && this.offset.equals(offset)) {
            return this;
        }
        return new OffsetTime(time, offset);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() || field2 == ChronoField.OFFSET_SECONDS;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isTimeBased();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (field2 == ChronoField.OFFSET_SECONDS) {
                return field2.range();
            }
            return this.time.range(field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        return super.get(field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (field2 == ChronoField.OFFSET_SECONDS) {
                return this.getOffset().getTotalSeconds();
            }
            return this.time.getLong(field2);
        }
        return field2.getFrom(this);
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public OffsetTime withOffsetSameLocal(ZoneOffset offset) {
        return offset != null && offset.equals(this.offset) ? this : new OffsetTime(this.time, offset);
    }

    public OffsetTime withOffsetSameInstant(ZoneOffset offset) {
        if (offset.equals(this.offset)) {
            return this;
        }
        int difference = offset.getTotalSeconds() - this.offset.getTotalSeconds();
        LocalTime adjusted = this.time.plusSeconds(difference);
        return new OffsetTime(adjusted, offset);
    }

    public int getHour() {
        return this.time.getHour();
    }

    public int getMinute() {
        return this.time.getMinute();
    }

    public int getSecond() {
        return this.time.getSecond();
    }

    public int getNano() {
        return this.time.getNano();
    }

    @Override
    public OffsetTime with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalTime) {
            return this.with((LocalTime)adjuster, this.offset);
        }
        if (adjuster instanceof ZoneOffset) {
            return this.with(this.time, (ZoneOffset)adjuster);
        }
        if (adjuster instanceof OffsetTime) {
            return (OffsetTime)adjuster;
        }
        return (OffsetTime)adjuster.adjustInto(this);
    }

    @Override
    public OffsetTime with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            if (field2 == ChronoField.OFFSET_SECONDS) {
                ChronoField f = (ChronoField)field2;
                return this.with(this.time, ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue)));
            }
            return this.with(this.time.with(field2, newValue), this.offset);
        }
        return field2.adjustInto(this, newValue);
    }

    public OffsetTime withHour(int hour) {
        return this.with(this.time.withHour(hour), this.offset);
    }

    public OffsetTime withMinute(int minute) {
        return this.with(this.time.withMinute(minute), this.offset);
    }

    public OffsetTime withSecond(int second) {
        return this.with(this.time.withSecond(second), this.offset);
    }

    public OffsetTime withNano(int nanoOfSecond) {
        return this.with(this.time.withNano(nanoOfSecond), this.offset);
    }

    public OffsetTime truncatedTo(TemporalUnit unit) {
        return this.with(this.time.truncatedTo(unit), this.offset);
    }

    @Override
    public OffsetTime plus(TemporalAmount amount) {
        return (OffsetTime)amount.addTo(this);
    }

    @Override
    public OffsetTime plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return this.with(this.time.plus(amountToAdd, unit), this.offset);
        }
        return unit.addTo(this, amountToAdd);
    }

    public OffsetTime plusHours(long hours) {
        return this.with(this.time.plusHours(hours), this.offset);
    }

    public OffsetTime plusMinutes(long minutes) {
        return this.with(this.time.plusMinutes(minutes), this.offset);
    }

    public OffsetTime plusSeconds(long seconds) {
        return this.with(this.time.plusSeconds(seconds), this.offset);
    }

    public OffsetTime plusNanos(long nanos) {
        return this.with(this.time.plusNanos(nanos), this.offset);
    }

    @Override
    public OffsetTime minus(TemporalAmount amount) {
        return (OffsetTime)amount.subtractFrom(this);
    }

    @Override
    public OffsetTime minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public OffsetTime minusHours(long hours) {
        return this.with(this.time.minusHours(hours), this.offset);
    }

    public OffsetTime minusMinutes(long minutes) {
        return this.with(this.time.minusMinutes(minutes), this.offset);
    }

    public OffsetTime minusSeconds(long seconds) {
        return this.with(this.time.minusSeconds(seconds), this.offset);
    }

    public OffsetTime minusNanos(long nanos) {
        return this.with(this.time.minusNanos(nanos), this.offset);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.offset() || query == TemporalQueries.zone()) {
            return (R)this.getOffset();
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this.time;
        }
        if (query == TemporalQueries.chronology() || query == TemporalQueries.localDate() || query == TemporalQueries.zoneId()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.NANO_OF_DAY, this.time.toNanoOfDay()).with(ChronoField.OFFSET_SECONDS, this.getOffset().getTotalSeconds());
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        OffsetTime end = OffsetTime.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            long nanosUntil = end.toEpochNano() - this.toEpochNano();
            switch ((ChronoUnit)unit) {
                case NANOS: {
                    return nanosUntil;
                }
                case MICROS: {
                    return nanosUntil / 1000L;
                }
                case MILLIS: {
                    return nanosUntil / 1000000L;
                }
                case SECONDS: {
                    return nanosUntil / 1000000000L;
                }
                case MINUTES: {
                    return nanosUntil / 60000000000L;
                }
                case HOURS: {
                    return nanosUntil / 3600000000000L;
                }
                case HALF_DAYS: {
                    return nanosUntil / 43200000000000L;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    public OffsetDateTime atDate(LocalDate date) {
        return OffsetDateTime.of(date, this.time, this.offset);
    }

    public LocalTime toLocalTime() {
        return this.time;
    }

    private long toEpochNano() {
        long nod = this.time.toNanoOfDay();
        long offsetNanos = (long)this.offset.getTotalSeconds() * 1000000000L;
        return nod - offsetNanos;
    }

    @Override
    public int compareTo(OffsetTime other) {
        if (this.offset.equals(other.offset)) {
            return this.time.compareTo(other.time);
        }
        int compare = Jdk8Methods.compareLongs(this.toEpochNano(), other.toEpochNano());
        if (compare == 0) {
            compare = this.time.compareTo(other.time);
        }
        return compare;
    }

    public boolean isAfter(OffsetTime other) {
        return this.toEpochNano() > other.toEpochNano();
    }

    public boolean isBefore(OffsetTime other) {
        return this.toEpochNano() < other.toEpochNano();
    }

    public boolean isEqual(OffsetTime other) {
        return this.toEpochNano() == other.toEpochNano();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OffsetTime) {
            OffsetTime other = (OffsetTime)obj;
            return this.time.equals(other.time) && this.offset.equals(other.offset);
        }
        return false;
    }

    public int hashCode() {
        return this.time.hashCode() ^ this.offset.hashCode();
    }

    public String toString() {
        return this.time.toString() + this.offset.toString();
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    private Object writeReplace() {
        return new Ser(66, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        this.time.writeExternal(out);
        this.offset.writeExternal(out);
    }

    static OffsetTime readExternal(DataInput in) throws IOException {
        LocalTime time = LocalTime.readExternal(in);
        ZoneOffset offset = ZoneOffset.readExternal(in);
        return OffsetTime.of(time, offset);
    }
}

