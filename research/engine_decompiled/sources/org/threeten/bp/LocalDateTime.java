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
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
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

public final class LocalDateTime
extends ChronoLocalDateTime<LocalDate>
implements Temporal,
TemporalAdjuster,
Serializable {
    public static final LocalDateTime MIN = LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);
    public static final LocalDateTime MAX = LocalDateTime.of(LocalDate.MAX, LocalTime.MAX);
    public static final TemporalQuery<LocalDateTime> FROM = new TemporalQuery<LocalDateTime>(){

        @Override
        public LocalDateTime queryFrom(TemporalAccessor temporal) {
            return LocalDateTime.from(temporal);
        }
    };
    private static final long serialVersionUID = 6207766400415563566L;
    private final LocalDate date;
    private final LocalTime time;

    public static LocalDateTime now() {
        return LocalDateTime.now(Clock.systemDefaultZone());
    }

    public static LocalDateTime now(ZoneId zone) {
        return LocalDateTime.now(Clock.system(zone));
    }

    public static LocalDateTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        return LocalDateTime.ofEpochSecond(now.getEpochSecond(), now.getNano(), offset);
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute, second);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute, second, nanoOfSecond);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute, second);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        LocalTime time = LocalTime.of(hour, minute, second, nanoOfSecond);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime of(LocalDate date, LocalTime time) {
        Jdk8Methods.requireNonNull(date, "date");
        Jdk8Methods.requireNonNull(time, "time");
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime ofInstant(Instant instant, ZoneId zone) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zone, "zone");
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instant);
        return LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset);
    }

    public static LocalDateTime ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset) {
        Jdk8Methods.requireNonNull(offset, "offset");
        long localSecond = epochSecond + (long)offset.getTotalSeconds();
        long localEpochDay = Jdk8Methods.floorDiv(localSecond, 86400L);
        int secsOfDay = Jdk8Methods.floorMod(localSecond, 86400);
        LocalDate date = LocalDate.ofEpochDay(localEpochDay);
        LocalTime time = LocalTime.ofSecondOfDay(secsOfDay, nanoOfSecond);
        return new LocalDateTime(date, time);
    }

    public static LocalDateTime from(TemporalAccessor temporal) {
        if (temporal instanceof LocalDateTime) {
            return (LocalDateTime)temporal;
        }
        if (temporal instanceof ZonedDateTime) {
            return ((ZonedDateTime)temporal).toLocalDateTime();
        }
        try {
            LocalDate date = LocalDate.from(temporal);
            LocalTime time = LocalTime.from(temporal);
            return new LocalDateTime(date, time);
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
    }

    public static LocalDateTime parse(CharSequence text) {
        return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private LocalDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    private LocalDateTime with(LocalDate newDate, LocalTime newTime) {
        if (this.date == newDate && this.time == newTime) {
            return this;
        }
        return new LocalDateTime(newDate, newTime);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isDateBased() || field2.isTimeBased();
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isDateBased() || unit.isTimeBased();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() ? this.time.range(field2) : this.date.range(field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() ? this.time.get(field2) : this.date.get(field2);
        }
        return super.get(field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() ? this.time.getLong(field2) : this.date.getLong(field2);
        }
        return field2.getFrom(this);
    }

    public int getYear() {
        return this.date.getYear();
    }

    public int getMonthValue() {
        return this.date.getMonthValue();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }

    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    public int getDayOfYear() {
        return this.date.getDayOfYear();
    }

    public DayOfWeek getDayOfWeek() {
        return this.date.getDayOfWeek();
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
    public LocalDateTime with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalDate) {
            return this.with((LocalDate)adjuster, this.time);
        }
        if (adjuster instanceof LocalTime) {
            return this.with(this.date, (LocalTime)adjuster);
        }
        if (adjuster instanceof LocalDateTime) {
            return (LocalDateTime)adjuster;
        }
        return (LocalDateTime)adjuster.adjustInto(this);
    }

    @Override
    public LocalDateTime with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            if (field2.isTimeBased()) {
                return this.with(this.date, this.time.with(field2, newValue));
            }
            return this.with(this.date.with(field2, newValue), this.time);
        }
        return field2.adjustInto(this, newValue);
    }

    public LocalDateTime withYear(int year) {
        return this.with(this.date.withYear(year), this.time);
    }

    public LocalDateTime withMonth(int month) {
        return this.with(this.date.withMonth(month), this.time);
    }

    public LocalDateTime withDayOfMonth(int dayOfMonth) {
        return this.with(this.date.withDayOfMonth(dayOfMonth), this.time);
    }

    public LocalDateTime withDayOfYear(int dayOfYear) {
        return this.with(this.date.withDayOfYear(dayOfYear), this.time);
    }

    public LocalDateTime withHour(int hour) {
        LocalTime newTime = this.time.withHour(hour);
        return this.with(this.date, newTime);
    }

    public LocalDateTime withMinute(int minute) {
        LocalTime newTime = this.time.withMinute(minute);
        return this.with(this.date, newTime);
    }

    public LocalDateTime withSecond(int second) {
        LocalTime newTime = this.time.withSecond(second);
        return this.with(this.date, newTime);
    }

    public LocalDateTime withNano(int nanoOfSecond) {
        LocalTime newTime = this.time.withNano(nanoOfSecond);
        return this.with(this.date, newTime);
    }

    public LocalDateTime truncatedTo(TemporalUnit unit) {
        return this.with(this.date, this.time.truncatedTo(unit));
    }

    @Override
    public LocalDateTime plus(TemporalAmount amount) {
        return (LocalDateTime)amount.addTo(this);
    }

    @Override
    public LocalDateTime plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case NANOS: {
                    return this.plusNanos(amountToAdd);
                }
                case MICROS: {
                    return this.plusDays(amountToAdd / 86400000000L).plusNanos(amountToAdd % 86400000000L * 1000L);
                }
                case MILLIS: {
                    return this.plusDays(amountToAdd / 86400000L).plusNanos(amountToAdd % 86400000L * 1000000L);
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
                    return this.plusDays(amountToAdd / 256L).plusHours(amountToAdd % 256L * 12L);
                }
            }
            return this.with(this.date.plus(amountToAdd, unit), this.time);
        }
        return unit.addTo(this, amountToAdd);
    }

    public LocalDateTime plusYears(long years) {
        LocalDate newDate = this.date.plusYears(years);
        return this.with(newDate, this.time);
    }

    public LocalDateTime plusMonths(long months) {
        LocalDate newDate = this.date.plusMonths(months);
        return this.with(newDate, this.time);
    }

    public LocalDateTime plusWeeks(long weeks) {
        LocalDate newDate = this.date.plusWeeks(weeks);
        return this.with(newDate, this.time);
    }

    public LocalDateTime plusDays(long days) {
        LocalDate newDate = this.date.plusDays(days);
        return this.with(newDate, this.time);
    }

    public LocalDateTime plusHours(long hours) {
        return this.plusWithOverflow(this.date, hours, 0L, 0L, 0L, 1);
    }

    public LocalDateTime plusMinutes(long minutes) {
        return this.plusWithOverflow(this.date, 0L, minutes, 0L, 0L, 1);
    }

    public LocalDateTime plusSeconds(long seconds) {
        return this.plusWithOverflow(this.date, 0L, 0L, seconds, 0L, 1);
    }

    public LocalDateTime plusNanos(long nanos) {
        return this.plusWithOverflow(this.date, 0L, 0L, 0L, nanos, 1);
    }

    @Override
    public LocalDateTime minus(TemporalAmount amount) {
        return (LocalDateTime)amount.subtractFrom(this);
    }

    @Override
    public LocalDateTime minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public LocalDateTime minusYears(long years) {
        return years == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-years);
    }

    public LocalDateTime minusMonths(long months) {
        return months == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-months);
    }

    public LocalDateTime minusWeeks(long weeks) {
        return weeks == Long.MIN_VALUE ? this.plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : this.plusWeeks(-weeks);
    }

    public LocalDateTime minusDays(long days) {
        return days == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-days);
    }

    public LocalDateTime minusHours(long hours) {
        return this.plusWithOverflow(this.date, hours, 0L, 0L, 0L, -1);
    }

    public LocalDateTime minusMinutes(long minutes) {
        return this.plusWithOverflow(this.date, 0L, minutes, 0L, 0L, -1);
    }

    public LocalDateTime minusSeconds(long seconds) {
        return this.plusWithOverflow(this.date, 0L, 0L, seconds, 0L, -1);
    }

    public LocalDateTime minusNanos(long nanos) {
        return this.plusWithOverflow(this.date, 0L, 0L, 0L, nanos, -1);
    }

    private LocalDateTime plusWithOverflow(LocalDate newDate, long hours, long minutes, long seconds, long nanos, int sign) {
        if ((hours | minutes | seconds | nanos) == 0L) {
            return this.with(newDate, this.time);
        }
        long totDays = nanos / 86400000000000L + seconds / 86400L + minutes / 1440L + hours / 24L;
        totDays *= (long)sign;
        long totNanos = nanos % 86400000000000L + seconds % 86400L * 1000000000L + minutes % 1440L * 60000000000L + hours % 24L * 3600000000000L;
        long curNoD = this.time.toNanoOfDay();
        totNanos = totNanos * (long)sign + curNoD;
        long newNoD = Jdk8Methods.floorMod(totNanos, 86400000000000L);
        LocalTime newTime = newNoD == curNoD ? this.time : LocalTime.ofNanoOfDay(newNoD);
        return this.with(newDate.plusDays(totDays += Jdk8Methods.floorDiv(totNanos, 86400000000000L)), newTime);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.localDate()) {
            return (R)this.toLocalDate();
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return super.adjustInto(temporal);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        LocalDateTime end = LocalDateTime.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            if (f.isTimeBased()) {
                long daysUntil = this.date.daysUntil(end.date);
                long timeUntil = end.time.toNanoOfDay() - this.time.toNanoOfDay();
                if (daysUntil > 0L && timeUntil < 0L) {
                    --daysUntil;
                    timeUntil += 86400000000000L;
                } else if (daysUntil < 0L && timeUntil > 0L) {
                    ++daysUntil;
                    timeUntil -= 86400000000000L;
                }
                long amount = daysUntil;
                switch (f) {
                    case NANOS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000000000L);
                        return Jdk8Methods.safeAdd(amount, timeUntil);
                    }
                    case MICROS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000000L);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 1000L);
                    }
                    case MILLIS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000L);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 1000000L);
                    }
                    case SECONDS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 1000000000L);
                    }
                    case MINUTES: {
                        amount = Jdk8Methods.safeMultiply(amount, 1440);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 60000000000L);
                    }
                    case HOURS: {
                        amount = Jdk8Methods.safeMultiply(amount, 24);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 3600000000000L);
                    }
                    case HALF_DAYS: {
                        amount = Jdk8Methods.safeMultiply(amount, 2);
                        return Jdk8Methods.safeAdd(amount, timeUntil / 43200000000000L);
                    }
                }
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
            }
            LocalDate endDate = end.date;
            if (endDate.isAfter(this.date) && end.time.isBefore(this.time)) {
                endDate = endDate.minusDays(1L);
            } else if (endDate.isBefore(this.date) && end.time.isAfter(this.time)) {
                endDate = endDate.plusDays(1L);
            }
            return this.date.until(endDate, unit);
        }
        return unit.between(this, end);
    }

    public OffsetDateTime atOffset(ZoneOffset offset) {
        return OffsetDateTime.of(this, offset);
    }

    public ZonedDateTime atZone(ZoneId zone) {
        return ZonedDateTime.of(this, zone);
    }

    @Override
    public LocalDate toLocalDate() {
        return this.date;
    }

    @Override
    public LocalTime toLocalTime() {
        return this.time;
    }

    @Override
    public int compareTo(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return this.compareTo0((LocalDateTime)other);
        }
        return super.compareTo(other);
    }

    private int compareTo0(LocalDateTime other) {
        int cmp = this.date.compareTo0(other.toLocalDate());
        if (cmp == 0) {
            cmp = this.time.compareTo(other.toLocalTime());
        }
        return cmp;
    }

    @Override
    public boolean isAfter(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return this.compareTo0((LocalDateTime)other) > 0;
        }
        return super.isAfter(other);
    }

    @Override
    public boolean isBefore(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return this.compareTo0((LocalDateTime)other) < 0;
        }
        return super.isBefore(other);
    }

    @Override
    public boolean isEqual(ChronoLocalDateTime<?> other) {
        if (other instanceof LocalDateTime) {
            return this.compareTo0((LocalDateTime)other) == 0;
        }
        return super.isEqual(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDateTime) {
            LocalDateTime other = (LocalDateTime)obj;
            return this.date.equals(other.date) && this.time.equals(other.time);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.date.hashCode() ^ this.time.hashCode();
    }

    @Override
    public String toString() {
        return this.date.toString() + 'T' + this.time.toString();
    }

    @Override
    public String format(DateTimeFormatter formatter) {
        return super.format(formatter);
    }

    private Object writeReplace() {
        return new Ser(4, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        this.date.writeExternal(out);
        this.time.writeExternal(out);
    }

    static LocalDateTime readExternal(DataInput in) throws IOException {
        LocalDate date = LocalDate.readExternal(in);
        LocalTime time = LocalTime.readExternal(in);
        return LocalDateTime.of(date, time);
    }
}

