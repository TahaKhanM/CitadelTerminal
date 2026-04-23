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
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetTime;
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

public final class LocalTime
extends DefaultInterfaceTemporalAccessor
implements Temporal,
TemporalAdjuster,
Comparable<LocalTime>,
Serializable {
    public static final LocalTime MIN;
    public static final LocalTime MAX;
    public static final LocalTime MIDNIGHT;
    public static final LocalTime NOON;
    public static final TemporalQuery<LocalTime> FROM;
    private static final LocalTime[] HOURS;
    static final int HOURS_PER_DAY = 24;
    static final int MINUTES_PER_HOUR = 60;
    static final int MINUTES_PER_DAY = 1440;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = 3600;
    static final int SECONDS_PER_DAY = 86400;
    static final long MILLIS_PER_DAY = 86400000L;
    static final long MICROS_PER_DAY = 86400000000L;
    static final long NANOS_PER_SECOND = 1000000000L;
    static final long NANOS_PER_MINUTE = 60000000000L;
    static final long NANOS_PER_HOUR = 3600000000000L;
    static final long NANOS_PER_DAY = 86400000000000L;
    private static final long serialVersionUID = 6414437269572265201L;
    private final byte hour;
    private final byte minute;
    private final byte second;
    private final int nano;

    public static LocalTime now() {
        return LocalTime.now(Clock.systemDefaultZone());
    }

    public static LocalTime now(ZoneId zone) {
        return LocalTime.now(Clock.system(zone));
    }

    public static LocalTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        long secsOfDay = now.getEpochSecond() % 86400L;
        secsOfDay = (secsOfDay + (long)offset.getTotalSeconds()) % 86400L;
        if (secsOfDay < 0L) {
            secsOfDay += 86400L;
        }
        return LocalTime.ofSecondOfDay(secsOfDay, now.getNano());
    }

    public static LocalTime of(int hour, int minute) {
        ChronoField.HOUR_OF_DAY.checkValidValue(hour);
        if (minute == 0) {
            return HOURS[hour];
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(minute);
        return new LocalTime(hour, minute, 0, 0);
    }

    public static LocalTime of(int hour, int minute, int second) {
        ChronoField.HOUR_OF_DAY.checkValidValue(hour);
        if ((minute | second) == 0) {
            return HOURS[hour];
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(minute);
        ChronoField.SECOND_OF_MINUTE.checkValidValue(second);
        return new LocalTime(hour, minute, second, 0);
    }

    public static LocalTime of(int hour, int minute, int second, int nanoOfSecond) {
        ChronoField.HOUR_OF_DAY.checkValidValue(hour);
        ChronoField.MINUTE_OF_HOUR.checkValidValue(minute);
        ChronoField.SECOND_OF_MINUTE.checkValidValue(second);
        ChronoField.NANO_OF_SECOND.checkValidValue(nanoOfSecond);
        return LocalTime.create(hour, minute, second, nanoOfSecond);
    }

    public static LocalTime ofSecondOfDay(long secondOfDay) {
        ChronoField.SECOND_OF_DAY.checkValidValue(secondOfDay);
        int hours = (int)(secondOfDay / 3600L);
        int minutes = (int)((secondOfDay -= (long)(hours * 3600)) / 60L);
        return LocalTime.create(hours, minutes, (int)(secondOfDay -= (long)(minutes * 60)), 0);
    }

    static LocalTime ofSecondOfDay(long secondOfDay, int nanoOfSecond) {
        ChronoField.SECOND_OF_DAY.checkValidValue(secondOfDay);
        ChronoField.NANO_OF_SECOND.checkValidValue(nanoOfSecond);
        int hours = (int)(secondOfDay / 3600L);
        int minutes = (int)((secondOfDay -= (long)(hours * 3600)) / 60L);
        return LocalTime.create(hours, minutes, (int)(secondOfDay -= (long)(minutes * 60)), nanoOfSecond);
    }

    public static LocalTime ofNanoOfDay(long nanoOfDay) {
        ChronoField.NANO_OF_DAY.checkValidValue(nanoOfDay);
        int hours = (int)(nanoOfDay / 3600000000000L);
        int minutes = (int)((nanoOfDay -= (long)hours * 3600000000000L) / 60000000000L);
        int seconds = (int)((nanoOfDay -= (long)minutes * 60000000000L) / 1000000000L);
        return LocalTime.create(hours, minutes, seconds, (int)(nanoOfDay -= (long)seconds * 1000000000L));
    }

    public static LocalTime from(TemporalAccessor temporal) {
        LocalTime time = temporal.query(TemporalQueries.localTime());
        if (time == null) {
            throw new DateTimeException("Unable to obtain LocalTime from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
        return time;
    }

    public static LocalTime parse(CharSequence text) {
        return LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public static LocalTime parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private static LocalTime create(int hour, int minute, int second, int nanoOfSecond) {
        if ((minute | second | nanoOfSecond) == 0) {
            return HOURS[hour];
        }
        return new LocalTime(hour, minute, second, nanoOfSecond);
    }

    private LocalTime(int hour, int minute, int second, int nanoOfSecond) {
        this.hour = (byte)hour;
        this.minute = (byte)minute;
        this.second = (byte)second;
        this.nano = nanoOfSecond;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased();
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
        return super.range(field2);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return this.get0(field2);
        }
        return super.get(field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (field2 == ChronoField.NANO_OF_DAY) {
                return this.toNanoOfDay();
            }
            if (field2 == ChronoField.MICRO_OF_DAY) {
                return this.toNanoOfDay() / 1000L;
            }
            return this.get0(field2);
        }
        return field2.getFrom(this);
    }

    private int get0(TemporalField field2) {
        switch ((ChronoField)field2) {
            case NANO_OF_SECOND: {
                return this.nano;
            }
            case NANO_OF_DAY: {
                throw new DateTimeException("Field too large for an int: " + field2);
            }
            case MICRO_OF_SECOND: {
                return this.nano / 1000;
            }
            case MICRO_OF_DAY: {
                throw new DateTimeException("Field too large for an int: " + field2);
            }
            case MILLI_OF_SECOND: {
                return this.nano / 1000000;
            }
            case MILLI_OF_DAY: {
                return (int)(this.toNanoOfDay() / 1000000L);
            }
            case SECOND_OF_MINUTE: {
                return this.second;
            }
            case SECOND_OF_DAY: {
                return this.toSecondOfDay();
            }
            case MINUTE_OF_HOUR: {
                return this.minute;
            }
            case MINUTE_OF_DAY: {
                return this.hour * 60 + this.minute;
            }
            case HOUR_OF_AMPM: {
                return this.hour % 12;
            }
            case CLOCK_HOUR_OF_AMPM: {
                int ham = this.hour % 12;
                return ham % 12 == 0 ? 12 : ham;
            }
            case HOUR_OF_DAY: {
                return this.hour;
            }
            case CLOCK_HOUR_OF_DAY: {
                return this.hour == 0 ? 24 : (int)this.hour;
            }
            case AMPM_OF_DAY: {
                return this.hour / 12;
            }
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    public int getNano() {
        return this.nano;
    }

    @Override
    public LocalTime with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalTime) {
            return (LocalTime)adjuster;
        }
        return (LocalTime)adjuster.adjustInto(this);
    }

    @Override
    public LocalTime with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            switch (f) {
                case NANO_OF_SECOND: {
                    return this.withNano((int)newValue);
                }
                case NANO_OF_DAY: {
                    return LocalTime.ofNanoOfDay(newValue);
                }
                case MICRO_OF_SECOND: {
                    return this.withNano((int)newValue * 1000);
                }
                case MICRO_OF_DAY: {
                    return LocalTime.ofNanoOfDay(newValue * 1000L);
                }
                case MILLI_OF_SECOND: {
                    return this.withNano((int)newValue * 1000000);
                }
                case MILLI_OF_DAY: {
                    return LocalTime.ofNanoOfDay(newValue * 1000000L);
                }
                case SECOND_OF_MINUTE: {
                    return this.withSecond((int)newValue);
                }
                case SECOND_OF_DAY: {
                    return this.plusSeconds(newValue - (long)this.toSecondOfDay());
                }
                case MINUTE_OF_HOUR: {
                    return this.withMinute((int)newValue);
                }
                case MINUTE_OF_DAY: {
                    return this.plusMinutes(newValue - (long)(this.hour * 60 + this.minute));
                }
                case HOUR_OF_AMPM: {
                    return this.plusHours(newValue - (long)(this.hour % 12));
                }
                case CLOCK_HOUR_OF_AMPM: {
                    return this.plusHours((newValue == 12L ? 0L : newValue) - (long)(this.hour % 12));
                }
                case HOUR_OF_DAY: {
                    return this.withHour((int)newValue);
                }
                case CLOCK_HOUR_OF_DAY: {
                    return this.withHour((int)(newValue == 24L ? 0L : newValue));
                }
                case AMPM_OF_DAY: {
                    return this.plusHours((newValue - (long)(this.hour / 12)) * 12L);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.adjustInto(this, newValue);
    }

    public LocalTime withHour(int hour) {
        if (this.hour == hour) {
            return this;
        }
        ChronoField.HOUR_OF_DAY.checkValidValue(hour);
        return LocalTime.create(hour, this.minute, this.second, this.nano);
    }

    public LocalTime withMinute(int minute) {
        if (this.minute == minute) {
            return this;
        }
        ChronoField.MINUTE_OF_HOUR.checkValidValue(minute);
        return LocalTime.create(this.hour, minute, this.second, this.nano);
    }

    public LocalTime withSecond(int second) {
        if (this.second == second) {
            return this;
        }
        ChronoField.SECOND_OF_MINUTE.checkValidValue(second);
        return LocalTime.create(this.hour, this.minute, second, this.nano);
    }

    public LocalTime withNano(int nanoOfSecond) {
        if (this.nano == nanoOfSecond) {
            return this;
        }
        ChronoField.NANO_OF_SECOND.checkValidValue(nanoOfSecond);
        return LocalTime.create(this.hour, this.minute, this.second, nanoOfSecond);
    }

    public LocalTime truncatedTo(TemporalUnit unit) {
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
        long nod = this.toNanoOfDay();
        return LocalTime.ofNanoOfDay(nod / dur * dur);
    }

    @Override
    public LocalTime plus(TemporalAmount amount) {
        return (LocalTime)amount.addTo(this);
    }

    @Override
    public LocalTime plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case NANOS: {
                    return this.plusNanos(amountToAdd);
                }
                case MICROS: {
                    return this.plusNanos(amountToAdd % 86400000000L * 1000L);
                }
                case MILLIS: {
                    return this.plusNanos(amountToAdd % 86400000L * 1000000L);
                }
                case SECONDS: {
                    return this.plusSeconds(amountToAdd);
                }
                case MINUTES: {
                    return this.plusMinutes(amountToAdd);
                }
                case HOURS: {
                    return this.plusHours(amountToAdd);
                }
                case HALF_DAYS: {
                    return this.plusHours(amountToAdd % 2L * 12L);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.addTo(this, amountToAdd);
    }

    public LocalTime plusHours(long hoursToAdd) {
        if (hoursToAdd == 0L) {
            return this;
        }
        int newHour = ((int)(hoursToAdd % 24L) + this.hour + 24) % 24;
        return LocalTime.create(newHour, this.minute, this.second, this.nano);
    }

    public LocalTime plusMinutes(long minutesToAdd) {
        if (minutesToAdd == 0L) {
            return this;
        }
        int mofd = this.hour * 60 + this.minute;
        int newMofd = ((int)(minutesToAdd % 1440L) + mofd + 1440) % 1440;
        if (mofd == newMofd) {
            return this;
        }
        int newHour = newMofd / 60;
        int newMinute = newMofd % 60;
        return LocalTime.create(newHour, newMinute, this.second, this.nano);
    }

    public LocalTime plusSeconds(long secondstoAdd) {
        if (secondstoAdd == 0L) {
            return this;
        }
        int sofd = this.hour * 3600 + this.minute * 60 + this.second;
        int newSofd = ((int)(secondstoAdd % 86400L) + sofd + 86400) % 86400;
        if (sofd == newSofd) {
            return this;
        }
        int newHour = newSofd / 3600;
        int newMinute = newSofd / 60 % 60;
        int newSecond = newSofd % 60;
        return LocalTime.create(newHour, newMinute, newSecond, this.nano);
    }

    public LocalTime plusNanos(long nanosToAdd) {
        long newNofd;
        if (nanosToAdd == 0L) {
            return this;
        }
        long nofd = this.toNanoOfDay();
        if (nofd == (newNofd = (nanosToAdd % 86400000000000L + nofd + 86400000000000L) % 86400000000000L)) {
            return this;
        }
        int newHour = (int)(newNofd / 3600000000000L);
        int newMinute = (int)(newNofd / 60000000000L % 60L);
        int newSecond = (int)(newNofd / 1000000000L % 60L);
        int newNano = (int)(newNofd % 1000000000L);
        return LocalTime.create(newHour, newMinute, newSecond, newNano);
    }

    @Override
    public LocalTime minus(TemporalAmount amount) {
        return (LocalTime)amount.subtractFrom(this);
    }

    @Override
    public LocalTime minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public LocalTime minusHours(long hoursToSubtract) {
        return this.plusHours(-(hoursToSubtract % 24L));
    }

    public LocalTime minusMinutes(long minutesToSubtract) {
        return this.plusMinutes(-(minutesToSubtract % 1440L));
    }

    public LocalTime minusSeconds(long secondsToSubtract) {
        return this.plusSeconds(-(secondsToSubtract % 86400L));
    }

    public LocalTime minusNanos(long nanosToSubtract) {
        return this.plusNanos(-(nanosToSubtract % 86400000000000L));
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this;
        }
        if (query == TemporalQueries.chronology() || query == TemporalQueries.zoneId() || query == TemporalQueries.zone() || query == TemporalQueries.offset() || query == TemporalQueries.localDate()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.NANO_OF_DAY, this.toNanoOfDay());
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        LocalTime end = LocalTime.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            long nanosUntil = end.toNanoOfDay() - this.toNanoOfDay();
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

    public LocalDateTime atDate(LocalDate date) {
        return LocalDateTime.of(date, this);
    }

    public OffsetTime atOffset(ZoneOffset offset) {
        return OffsetTime.of(this, offset);
    }

    public int toSecondOfDay() {
        int total2 = this.hour * 3600;
        total2 += this.minute * 60;
        return total2 += this.second;
    }

    public long toNanoOfDay() {
        long total2 = (long)this.hour * 3600000000000L;
        total2 += (long)this.minute * 60000000000L;
        total2 += (long)this.second * 1000000000L;
        return total2 += (long)this.nano;
    }

    @Override
    public int compareTo(LocalTime other) {
        int cmp = Jdk8Methods.compareInts(this.hour, other.hour);
        if (cmp == 0 && (cmp = Jdk8Methods.compareInts(this.minute, other.minute)) == 0 && (cmp = Jdk8Methods.compareInts(this.second, other.second)) == 0) {
            cmp = Jdk8Methods.compareInts(this.nano, other.nano);
        }
        return cmp;
    }

    public boolean isAfter(LocalTime other) {
        return this.compareTo(other) > 0;
    }

    public boolean isBefore(LocalTime other) {
        return this.compareTo(other) < 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalTime) {
            LocalTime other = (LocalTime)obj;
            return this.hour == other.hour && this.minute == other.minute && this.second == other.second && this.nano == other.nano;
        }
        return false;
    }

    public int hashCode() {
        long nod = this.toNanoOfDay();
        return (int)(nod ^ nod >>> 32);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(18);
        byte hourValue = this.hour;
        byte minuteValue = this.minute;
        byte secondValue = this.second;
        int nanoValue = this.nano;
        buf.append(hourValue < 10 ? "0" : "").append(hourValue).append(minuteValue < 10 ? ":0" : ":").append(minuteValue);
        if (secondValue > 0 || nanoValue > 0) {
            buf.append(secondValue < 10 ? ":0" : ":").append(secondValue);
            if (nanoValue > 0) {
                buf.append('.');
                if (nanoValue % 1000000 == 0) {
                    buf.append(Integer.toString(nanoValue / 1000000 + 1000).substring(1));
                } else if (nanoValue % 1000 == 0) {
                    buf.append(Integer.toString(nanoValue / 1000 + 1000000).substring(1));
                } else {
                    buf.append(Integer.toString(nanoValue + 1000000000).substring(1));
                }
            }
        }
        return buf.toString();
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    private Object writeReplace() {
        return new Ser(5, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        if (this.nano == 0) {
            if (this.second == 0) {
                if (this.minute == 0) {
                    out.writeByte(~this.hour);
                } else {
                    out.writeByte(this.hour);
                    out.writeByte(~this.minute);
                }
            } else {
                out.writeByte(this.hour);
                out.writeByte(this.minute);
                out.writeByte(~this.second);
            }
        } else {
            out.writeByte(this.hour);
            out.writeByte(this.minute);
            out.writeByte(this.second);
            out.writeInt(this.nano);
        }
    }

    static LocalTime readExternal(DataInput in) throws IOException {
        int hour = in.readByte();
        int minute = 0;
        int second = 0;
        int nano = 0;
        if (hour < 0) {
            hour ^= 0xFFFFFFFF;
        } else {
            minute = in.readByte();
            if (minute < 0) {
                minute ^= 0xFFFFFFFF;
            } else {
                second = in.readByte();
                if (second < 0) {
                    second ^= 0xFFFFFFFF;
                } else {
                    nano = in.readInt();
                }
            }
        }
        return LocalTime.of(hour, minute, second, nano);
    }

    static {
        FROM = new TemporalQuery<LocalTime>(){

            @Override
            public LocalTime queryFrom(TemporalAccessor temporal) {
                return LocalTime.from(temporal);
            }
        };
        HOURS = new LocalTime[24];
        for (int i = 0; i < HOURS.length; ++i) {
            LocalTime.HOURS[i] = new LocalTime(i, 0, 0, 0);
        }
        MIDNIGHT = HOURS[0];
        NOON = HOURS[12];
        MIN = HOURS[0];
        MAX = new LocalTime(23, 59, 59, 999999999);
    }
}

