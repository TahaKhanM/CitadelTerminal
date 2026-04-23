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
import java.util.Comparator;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.OffsetTime;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
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
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.zone.ZoneRules;

public final class OffsetDateTime
extends DefaultInterfaceTemporal
implements Temporal,
TemporalAdjuster,
Comparable<OffsetDateTime>,
Serializable {
    public static final OffsetDateTime MIN = LocalDateTime.MIN.atOffset(ZoneOffset.MAX);
    public static final OffsetDateTime MAX = LocalDateTime.MAX.atOffset(ZoneOffset.MIN);
    public static final TemporalQuery<OffsetDateTime> FROM = new TemporalQuery<OffsetDateTime>(){

        @Override
        public OffsetDateTime queryFrom(TemporalAccessor temporal) {
            return OffsetDateTime.from(temporal);
        }
    };
    private static final Comparator<OffsetDateTime> INSTANT_COMPARATOR = new Comparator<OffsetDateTime>(){

        @Override
        public int compare(OffsetDateTime datetime1, OffsetDateTime datetime2) {
            int cmp = Jdk8Methods.compareLongs(datetime1.toEpochSecond(), datetime2.toEpochSecond());
            if (cmp == 0) {
                cmp = Jdk8Methods.compareLongs(datetime1.getNano(), datetime2.getNano());
            }
            return cmp;
        }
    };
    private static final long serialVersionUID = 2287754244819255394L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;

    public static Comparator<OffsetDateTime> timeLineOrder() {
        return INSTANT_COMPARATOR;
    }

    public static OffsetDateTime now() {
        return OffsetDateTime.now(Clock.systemDefaultZone());
    }

    public static OffsetDateTime now(ZoneId zone) {
        return OffsetDateTime.now(Clock.system(zone));
    }

    public static OffsetDateTime now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        return OffsetDateTime.ofInstant(now, clock.getZone().getRules().getOffset(now));
    }

    public static OffsetDateTime of(LocalDate date, LocalTime time, ZoneOffset offset) {
        LocalDateTime dt = LocalDateTime.of(date, time);
        return new OffsetDateTime(dt, offset);
    }

    public static OffsetDateTime of(LocalDateTime dateTime, ZoneOffset offset) {
        return new OffsetDateTime(dateTime, offset);
    }

    public static OffsetDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneOffset offset) {
        LocalDateTime dt = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
        return new OffsetDateTime(dt, offset);
    }

    public static OffsetDateTime ofInstant(Instant instant, ZoneId zone) {
        Jdk8Methods.requireNonNull(instant, "instant");
        Jdk8Methods.requireNonNull(zone, "zone");
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instant);
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset);
        return new OffsetDateTime(ldt, offset);
    }

    public static OffsetDateTime from(TemporalAccessor temporal) {
        if (temporal instanceof OffsetDateTime) {
            return (OffsetDateTime)temporal;
        }
        try {
            ZoneOffset offset = ZoneOffset.from(temporal);
            try {
                LocalDateTime ldt = LocalDateTime.from(temporal);
                return OffsetDateTime.of(ldt, offset);
            }
            catch (DateTimeException ignore) {
                Instant instant = Instant.from(temporal);
                return OffsetDateTime.ofInstant(instant, offset);
            }
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
    }

    public static OffsetDateTime parse(CharSequence text) {
        return OffsetDateTime.parse(text, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static OffsetDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private OffsetDateTime(LocalDateTime dateTime, ZoneOffset offset) {
        this.dateTime = Jdk8Methods.requireNonNull(dateTime, "dateTime");
        this.offset = Jdk8Methods.requireNonNull(offset, "offset");
    }

    private OffsetDateTime with(LocalDateTime dateTime, ZoneOffset offset) {
        if (this.dateTime == dateTime && this.offset.equals(offset)) {
            return this;
        }
        return new OffsetDateTime(dateTime, offset);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        return field2 instanceof ChronoField || field2 != null && field2.isSupportedBy(this);
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
            if (field2 == ChronoField.INSTANT_SECONDS || field2 == ChronoField.OFFSET_SECONDS) {
                return field2.range();
            }
            return this.dateTime.range(field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case INSTANT_SECONDS: {
                    throw new DateTimeException("Field too large for an int: " + field2);
                }
                case OFFSET_SECONDS: {
                    return this.getOffset().getTotalSeconds();
                }
            }
            return this.dateTime.get(field2);
        }
        return super.get(field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case INSTANT_SECONDS: {
                    return this.toEpochSecond();
                }
                case OFFSET_SECONDS: {
                    return this.getOffset().getTotalSeconds();
                }
            }
            return this.dateTime.getLong(field2);
        }
        return field2.getFrom(this);
    }

    public ZoneOffset getOffset() {
        return this.offset;
    }

    public OffsetDateTime withOffsetSameLocal(ZoneOffset offset) {
        return this.with(this.dateTime, offset);
    }

    public OffsetDateTime withOffsetSameInstant(ZoneOffset offset) {
        if (offset.equals(this.offset)) {
            return this;
        }
        int difference = offset.getTotalSeconds() - this.offset.getTotalSeconds();
        LocalDateTime adjusted = this.dateTime.plusSeconds(difference);
        return new OffsetDateTime(adjusted, offset);
    }

    public int getYear() {
        return this.dateTime.getYear();
    }

    public int getMonthValue() {
        return this.dateTime.getMonthValue();
    }

    public Month getMonth() {
        return this.dateTime.getMonth();
    }

    public int getDayOfMonth() {
        return this.dateTime.getDayOfMonth();
    }

    public int getDayOfYear() {
        return this.dateTime.getDayOfYear();
    }

    public DayOfWeek getDayOfWeek() {
        return this.dateTime.getDayOfWeek();
    }

    public int getHour() {
        return this.dateTime.getHour();
    }

    public int getMinute() {
        return this.dateTime.getMinute();
    }

    public int getSecond() {
        return this.dateTime.getSecond();
    }

    public int getNano() {
        return this.dateTime.getNano();
    }

    @Override
    public OffsetDateTime with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalDate || adjuster instanceof LocalTime || adjuster instanceof LocalDateTime) {
            return this.with(this.dateTime.with(adjuster), this.offset);
        }
        if (adjuster instanceof Instant) {
            return OffsetDateTime.ofInstant((Instant)adjuster, this.offset);
        }
        if (adjuster instanceof ZoneOffset) {
            return this.with(this.dateTime, (ZoneOffset)adjuster);
        }
        if (adjuster instanceof OffsetDateTime) {
            return (OffsetDateTime)adjuster;
        }
        return (OffsetDateTime)adjuster.adjustInto(this);
    }

    @Override
    public OffsetDateTime with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            switch (f) {
                case INSTANT_SECONDS: {
                    return OffsetDateTime.ofInstant(Instant.ofEpochSecond(newValue, this.getNano()), this.offset);
                }
                case OFFSET_SECONDS: {
                    return this.with(this.dateTime, ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue)));
                }
            }
            return this.with(this.dateTime.with(field2, newValue), this.offset);
        }
        return field2.adjustInto(this, newValue);
    }

    public OffsetDateTime withYear(int year) {
        return this.with(this.dateTime.withYear(year), this.offset);
    }

    public OffsetDateTime withMonth(int month) {
        return this.with(this.dateTime.withMonth(month), this.offset);
    }

    public OffsetDateTime withDayOfMonth(int dayOfMonth) {
        return this.with(this.dateTime.withDayOfMonth(dayOfMonth), this.offset);
    }

    public OffsetDateTime withDayOfYear(int dayOfYear) {
        return this.with(this.dateTime.withDayOfYear(dayOfYear), this.offset);
    }

    public OffsetDateTime withHour(int hour) {
        return this.with(this.dateTime.withHour(hour), this.offset);
    }

    public OffsetDateTime withMinute(int minute) {
        return this.with(this.dateTime.withMinute(minute), this.offset);
    }

    public OffsetDateTime withSecond(int second) {
        return this.with(this.dateTime.withSecond(second), this.offset);
    }

    public OffsetDateTime withNano(int nanoOfSecond) {
        return this.with(this.dateTime.withNano(nanoOfSecond), this.offset);
    }

    public OffsetDateTime truncatedTo(TemporalUnit unit) {
        return this.with(this.dateTime.truncatedTo(unit), this.offset);
    }

    @Override
    public OffsetDateTime plus(TemporalAmount amount) {
        return (OffsetDateTime)amount.addTo(this);
    }

    @Override
    public OffsetDateTime plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return this.with(this.dateTime.plus(amountToAdd, unit), this.offset);
        }
        return unit.addTo(this, amountToAdd);
    }

    public OffsetDateTime plusYears(long years) {
        return this.with(this.dateTime.plusYears(years), this.offset);
    }

    public OffsetDateTime plusMonths(long months) {
        return this.with(this.dateTime.plusMonths(months), this.offset);
    }

    public OffsetDateTime plusWeeks(long weeks) {
        return this.with(this.dateTime.plusWeeks(weeks), this.offset);
    }

    public OffsetDateTime plusDays(long days) {
        return this.with(this.dateTime.plusDays(days), this.offset);
    }

    public OffsetDateTime plusHours(long hours) {
        return this.with(this.dateTime.plusHours(hours), this.offset);
    }

    public OffsetDateTime plusMinutes(long minutes) {
        return this.with(this.dateTime.plusMinutes(minutes), this.offset);
    }

    public OffsetDateTime plusSeconds(long seconds) {
        return this.with(this.dateTime.plusSeconds(seconds), this.offset);
    }

    public OffsetDateTime plusNanos(long nanos) {
        return this.with(this.dateTime.plusNanos(nanos), this.offset);
    }

    @Override
    public OffsetDateTime minus(TemporalAmount amount) {
        return (OffsetDateTime)amount.subtractFrom(this);
    }

    @Override
    public OffsetDateTime minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public OffsetDateTime minusYears(long years) {
        return years == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-years);
    }

    public OffsetDateTime minusMonths(long months) {
        return months == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-months);
    }

    public OffsetDateTime minusWeeks(long weeks) {
        return weeks == Long.MIN_VALUE ? this.plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : this.plusWeeks(-weeks);
    }

    public OffsetDateTime minusDays(long days) {
        return days == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-days);
    }

    public OffsetDateTime minusHours(long hours) {
        return hours == Long.MIN_VALUE ? this.plusHours(Long.MAX_VALUE).plusHours(1L) : this.plusHours(-hours);
    }

    public OffsetDateTime minusMinutes(long minutes) {
        return minutes == Long.MIN_VALUE ? this.plusMinutes(Long.MAX_VALUE).plusMinutes(1L) : this.plusMinutes(-minutes);
    }

    public OffsetDateTime minusSeconds(long seconds) {
        return seconds == Long.MIN_VALUE ? this.plusSeconds(Long.MAX_VALUE).plusSeconds(1L) : this.plusSeconds(-seconds);
    }

    public OffsetDateTime minusNanos(long nanos) {
        return nanos == Long.MIN_VALUE ? this.plusNanos(Long.MAX_VALUE).plusNanos(1L) : this.plusNanos(-nanos);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)IsoChronology.INSTANCE;
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.offset() || query == TemporalQueries.zone()) {
            return (R)this.getOffset();
        }
        if (query == TemporalQueries.localDate()) {
            return (R)this.toLocalDate();
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this.toLocalTime();
        }
        if (query == TemporalQueries.zoneId()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, this.toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, this.toLocalTime().toNanoOfDay()).with(ChronoField.OFFSET_SECONDS, this.getOffset().getTotalSeconds());
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        OffsetDateTime end = OffsetDateTime.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            end = end.withOffsetSameInstant(this.offset);
            return this.dateTime.until(end.dateTime, unit);
        }
        return unit.between(this, end);
    }

    public ZonedDateTime atZoneSameInstant(ZoneId zone) {
        return ZonedDateTime.ofInstant(this.dateTime, this.offset, zone);
    }

    public ZonedDateTime atZoneSimilarLocal(ZoneId zone) {
        return ZonedDateTime.ofLocal(this.dateTime, zone, this.offset);
    }

    public LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    public LocalDate toLocalDate() {
        return this.dateTime.toLocalDate();
    }

    public LocalTime toLocalTime() {
        return this.dateTime.toLocalTime();
    }

    public OffsetTime toOffsetTime() {
        return OffsetTime.of(this.dateTime.toLocalTime(), this.offset);
    }

    public ZonedDateTime toZonedDateTime() {
        return ZonedDateTime.of(this.dateTime, this.offset);
    }

    public Instant toInstant() {
        return this.dateTime.toInstant(this.offset);
    }

    public long toEpochSecond() {
        return this.dateTime.toEpochSecond(this.offset);
    }

    @Override
    public int compareTo(OffsetDateTime other) {
        if (this.getOffset().equals(other.getOffset())) {
            return this.toLocalDateTime().compareTo(other.toLocalDateTime());
        }
        int cmp = Jdk8Methods.compareLongs(this.toEpochSecond(), other.toEpochSecond());
        if (cmp == 0 && (cmp = this.toLocalTime().getNano() - other.toLocalTime().getNano()) == 0) {
            cmp = this.toLocalDateTime().compareTo(other.toLocalDateTime());
        }
        return cmp;
    }

    public boolean isAfter(OffsetDateTime other) {
        long otherEpochSec;
        long thisEpochSec = this.toEpochSecond();
        return thisEpochSec > (otherEpochSec = other.toEpochSecond()) || thisEpochSec == otherEpochSec && this.toLocalTime().getNano() > other.toLocalTime().getNano();
    }

    public boolean isBefore(OffsetDateTime other) {
        long otherEpochSec;
        long thisEpochSec = this.toEpochSecond();
        return thisEpochSec < (otherEpochSec = other.toEpochSecond()) || thisEpochSec == otherEpochSec && this.toLocalTime().getNano() < other.toLocalTime().getNano();
    }

    public boolean isEqual(OffsetDateTime other) {
        return this.toEpochSecond() == other.toEpochSecond() && this.toLocalTime().getNano() == other.toLocalTime().getNano();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OffsetDateTime) {
            OffsetDateTime other = (OffsetDateTime)obj;
            return this.dateTime.equals(other.dateTime) && this.offset.equals(other.offset);
        }
        return false;
    }

    public int hashCode() {
        return this.dateTime.hashCode() ^ this.offset.hashCode();
    }

    public String toString() {
        return this.dateTime.toString() + this.offset.toString();
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    private Object writeReplace() {
        return new Ser(69, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        this.dateTime.writeExternal(out);
        this.offset.writeExternal(out);
    }

    static OffsetDateTime readExternal(DataInput in) throws IOException {
        LocalDateTime dateTime = LocalDateTime.readExternal(in);
        ZoneOffset offset = ZoneOffset.readExternal(in);
        return OffsetDateTime.of(dateTime, offset);
    }
}

