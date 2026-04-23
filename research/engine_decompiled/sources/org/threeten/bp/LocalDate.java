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
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.OffsetTime;
import org.threeten.bp.Period;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.IsoChronology;
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
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneRules;

public final class LocalDate
extends ChronoLocalDate
implements Temporal,
TemporalAdjuster,
Serializable {
    public static final LocalDate MIN = LocalDate.of(-999999999, 1, 1);
    public static final LocalDate MAX = LocalDate.of(999999999, 12, 31);
    public static final TemporalQuery<LocalDate> FROM = new TemporalQuery<LocalDate>(){

        @Override
        public LocalDate queryFrom(TemporalAccessor temporal) {
            return LocalDate.from(temporal);
        }
    };
    private static final long serialVersionUID = 2942565459149668126L;
    private static final int DAYS_PER_CYCLE = 146097;
    static final long DAYS_0000_TO_1970 = 719528L;
    private final int year;
    private final short month;
    private final short day;

    public static LocalDate now() {
        return LocalDate.now(Clock.systemDefaultZone());
    }

    public static LocalDate now(ZoneId zone) {
        return LocalDate.now(Clock.system(zone));
    }

    public static LocalDate now(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        Instant now = clock.instant();
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        long epochSec = now.getEpochSecond() + (long)offset.getTotalSeconds();
        long epochDay = Jdk8Methods.floorDiv(epochSec, 86400L);
        return LocalDate.ofEpochDay(epochDay);
    }

    public static LocalDate of(int year, Month month, int dayOfMonth) {
        ChronoField.YEAR.checkValidValue(year);
        Jdk8Methods.requireNonNull(month, "month");
        ChronoField.DAY_OF_MONTH.checkValidValue(dayOfMonth);
        return LocalDate.create(year, month, dayOfMonth);
    }

    public static LocalDate of(int year, int month, int dayOfMonth) {
        ChronoField.YEAR.checkValidValue(year);
        ChronoField.MONTH_OF_YEAR.checkValidValue(month);
        ChronoField.DAY_OF_MONTH.checkValidValue(dayOfMonth);
        return LocalDate.create(year, Month.of(month), dayOfMonth);
    }

    public static LocalDate ofYearDay(int year, int dayOfYear) {
        ChronoField.YEAR.checkValidValue(year);
        ChronoField.DAY_OF_YEAR.checkValidValue(dayOfYear);
        boolean leap = IsoChronology.INSTANCE.isLeapYear(year);
        if (dayOfYear == 366 && !leap) {
            throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + year + "' is not a leap year");
        }
        Month moy = Month.of((dayOfYear - 1) / 31 + 1);
        int monthEnd = moy.firstDayOfYear(leap) + moy.length(leap) - 1;
        if (dayOfYear > monthEnd) {
            moy = moy.plus(1L);
        }
        int dom = dayOfYear - moy.firstDayOfYear(leap) + 1;
        return LocalDate.create(year, moy, dom);
    }

    public static LocalDate ofEpochDay(long epochDay) {
        long yearEst;
        long doyEst;
        ChronoField.EPOCH_DAY.checkValidValue(epochDay);
        long zeroDay = epochDay + 719528L;
        long adjust = 0L;
        if ((zeroDay -= 60L) < 0L) {
            long adjustCycles = (zeroDay + 1L) / 146097L - 1L;
            adjust = adjustCycles * 400L;
            zeroDay += -adjustCycles * 146097L;
        }
        if ((doyEst = zeroDay - (365L * (yearEst = (400L * zeroDay + 591L) / 146097L) + yearEst / 4L - yearEst / 100L + yearEst / 400L)) < 0L) {
            doyEst = zeroDay - (365L * --yearEst + yearEst / 4L - yearEst / 100L + yearEst / 400L);
        }
        yearEst += adjust;
        int marchDoy0 = (int)doyEst;
        int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
        int month = (marchMonth0 + 2) % 12 + 1;
        int dom = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;
        int year = ChronoField.YEAR.checkValidIntValue(yearEst += (long)(marchMonth0 / 10));
        return new LocalDate(year, month, dom);
    }

    public static LocalDate from(TemporalAccessor temporal) {
        LocalDate date = temporal.query(TemporalQueries.localDate());
        if (date == null) {
            throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
        return date;
    }

    public static LocalDate parse(CharSequence text) {
        return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalDate parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private static LocalDate create(int year, Month month, int dayOfMonth) {
        if (dayOfMonth > 28 && dayOfMonth > month.length(IsoChronology.INSTANCE.isLeapYear(year))) {
            if (dayOfMonth == 29) {
                throw new DateTimeException("Invalid date 'February 29' as '" + year + "' is not a leap year");
            }
            throw new DateTimeException("Invalid date '" + month.name() + " " + dayOfMonth + "'");
        }
        return new LocalDate(year, month.getValue(), dayOfMonth);
    }

    private static LocalDate resolvePreviousValid(int year, int month, int day) {
        switch (month) {
            case 2: {
                day = Math.min(day, IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
                break;
            }
            case 4: 
            case 6: 
            case 9: 
            case 11: {
                day = Math.min(day, 30);
            }
        }
        return LocalDate.of(year, month, day);
    }

    private LocalDate(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = (short)month;
        this.day = (short)dayOfMonth;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        return super.isSupported(field2);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            if (f.isDateBased()) {
                switch (f) {
                    case DAY_OF_MONTH: {
                        return ValueRange.of(1L, this.lengthOfMonth());
                    }
                    case DAY_OF_YEAR: {
                        return ValueRange.of(1L, this.lengthOfYear());
                    }
                    case ALIGNED_WEEK_OF_MONTH: {
                        return ValueRange.of(1L, this.getMonth() == Month.FEBRUARY && !this.isLeapYear() ? 4L : 5L);
                    }
                    case YEAR_OF_ERA: {
                        return this.getYear() <= 0 ? ValueRange.of(1L, 1000000000L) : ValueRange.of(1L, 999999999L);
                    }
                }
                return field2.range();
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
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
            if (field2 == ChronoField.EPOCH_DAY) {
                return this.toEpochDay();
            }
            if (field2 == ChronoField.PROLEPTIC_MONTH) {
                return this.getProlepticMonth();
            }
            return this.get0(field2);
        }
        return field2.getFrom(this);
    }

    private int get0(TemporalField field2) {
        switch ((ChronoField)field2) {
            case DAY_OF_WEEK: {
                return this.getDayOfWeek().getValue();
            }
            case ALIGNED_DAY_OF_WEEK_IN_MONTH: {
                return (this.day - 1) % 7 + 1;
            }
            case ALIGNED_DAY_OF_WEEK_IN_YEAR: {
                return (this.getDayOfYear() - 1) % 7 + 1;
            }
            case DAY_OF_MONTH: {
                return this.day;
            }
            case DAY_OF_YEAR: {
                return this.getDayOfYear();
            }
            case EPOCH_DAY: {
                throw new DateTimeException("Field too large for an int: " + field2);
            }
            case ALIGNED_WEEK_OF_MONTH: {
                return (this.day - 1) / 7 + 1;
            }
            case ALIGNED_WEEK_OF_YEAR: {
                return (this.getDayOfYear() - 1) / 7 + 1;
            }
            case MONTH_OF_YEAR: {
                return this.month;
            }
            case PROLEPTIC_MONTH: {
                throw new DateTimeException("Field too large for an int: " + field2);
            }
            case YEAR_OF_ERA: {
                return this.year >= 1 ? this.year : 1 - this.year;
            }
            case YEAR: {
                return this.year;
            }
            case ERA: {
                return this.year >= 1 ? 1 : 0;
            }
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
    }

    private long getProlepticMonth() {
        return (long)this.year * 12L + (long)(this.month - 1);
    }

    @Override
    public IsoChronology getChronology() {
        return IsoChronology.INSTANCE;
    }

    @Override
    public Era getEra() {
        return super.getEra();
    }

    public int getYear() {
        return this.year;
    }

    public int getMonthValue() {
        return this.month;
    }

    public Month getMonth() {
        return Month.of(this.month);
    }

    public int getDayOfMonth() {
        return this.day;
    }

    public int getDayOfYear() {
        return this.getMonth().firstDayOfYear(this.isLeapYear()) + this.day - 1;
    }

    public DayOfWeek getDayOfWeek() {
        int dow0 = Jdk8Methods.floorMod(this.toEpochDay() + 3L, 7);
        return DayOfWeek.of(dow0 + 1);
    }

    @Override
    public boolean isLeapYear() {
        return IsoChronology.INSTANCE.isLeapYear(this.year);
    }

    @Override
    public int lengthOfMonth() {
        switch (this.month) {
            case 2: {
                return this.isLeapYear() ? 29 : 28;
            }
            case 4: 
            case 6: 
            case 9: 
            case 11: {
                return 30;
            }
        }
        return 31;
    }

    @Override
    public int lengthOfYear() {
        return this.isLeapYear() ? 366 : 365;
    }

    @Override
    public LocalDate with(TemporalAdjuster adjuster) {
        if (adjuster instanceof LocalDate) {
            return (LocalDate)adjuster;
        }
        return (LocalDate)adjuster.adjustInto(this);
    }

    @Override
    public LocalDate with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            switch (f) {
                case DAY_OF_WEEK: {
                    return this.plusDays(newValue - (long)this.getDayOfWeek().getValue());
                }
                case ALIGNED_DAY_OF_WEEK_IN_MONTH: {
                    return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                }
                case ALIGNED_DAY_OF_WEEK_IN_YEAR: {
                    return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                }
                case DAY_OF_MONTH: {
                    return this.withDayOfMonth((int)newValue);
                }
                case DAY_OF_YEAR: {
                    return this.withDayOfYear((int)newValue);
                }
                case EPOCH_DAY: {
                    return LocalDate.ofEpochDay(newValue);
                }
                case ALIGNED_WEEK_OF_MONTH: {
                    return this.plusWeeks(newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_MONTH));
                }
                case ALIGNED_WEEK_OF_YEAR: {
                    return this.plusWeeks(newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_YEAR));
                }
                case MONTH_OF_YEAR: {
                    return this.withMonth((int)newValue);
                }
                case PROLEPTIC_MONTH: {
                    return this.plusMonths(newValue - this.getLong(ChronoField.PROLEPTIC_MONTH));
                }
                case YEAR_OF_ERA: {
                    return this.withYear((int)(this.year >= 1 ? newValue : 1L - newValue));
                }
                case YEAR: {
                    return this.withYear((int)newValue);
                }
                case ERA: {
                    return this.getLong(ChronoField.ERA) == newValue ? this : this.withYear(1 - this.year);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.adjustInto(this, newValue);
    }

    public LocalDate withYear(int year) {
        if (this.year == year) {
            return this;
        }
        ChronoField.YEAR.checkValidValue(year);
        return LocalDate.resolvePreviousValid(year, this.month, this.day);
    }

    public LocalDate withMonth(int month) {
        if (this.month == month) {
            return this;
        }
        ChronoField.MONTH_OF_YEAR.checkValidValue(month);
        return LocalDate.resolvePreviousValid(this.year, month, this.day);
    }

    public LocalDate withDayOfMonth(int dayOfMonth) {
        if (this.day == dayOfMonth) {
            return this;
        }
        return LocalDate.of(this.year, this.month, dayOfMonth);
    }

    public LocalDate withDayOfYear(int dayOfYear) {
        if (this.getDayOfYear() == dayOfYear) {
            return this;
        }
        return LocalDate.ofYearDay(this.year, dayOfYear);
    }

    @Override
    public LocalDate plus(TemporalAmount amount) {
        return (LocalDate)amount.addTo(this);
    }

    @Override
    public LocalDate plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case DAYS: {
                    return this.plusDays(amountToAdd);
                }
                case WEEKS: {
                    return this.plusWeeks(amountToAdd);
                }
                case MONTHS: {
                    return this.plusMonths(amountToAdd);
                }
                case YEARS: {
                    return this.plusYears(amountToAdd);
                }
                case DECADES: {
                    return this.plusYears(Jdk8Methods.safeMultiply(amountToAdd, 10));
                }
                case CENTURIES: {
                    return this.plusYears(Jdk8Methods.safeMultiply(amountToAdd, 100));
                }
                case MILLENNIA: {
                    return this.plusYears(Jdk8Methods.safeMultiply(amountToAdd, 1000));
                }
                case ERAS: {
                    return this.with(ChronoField.ERA, Jdk8Methods.safeAdd(this.getLong(ChronoField.ERA), amountToAdd));
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.addTo(this, amountToAdd);
    }

    public LocalDate plusYears(long yearsToAdd) {
        if (yearsToAdd == 0L) {
            return this;
        }
        int newYear = ChronoField.YEAR.checkValidIntValue((long)this.year + yearsToAdd);
        return LocalDate.resolvePreviousValid(newYear, this.month, this.day);
    }

    public LocalDate plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0L) {
            return this;
        }
        long monthCount = (long)this.year * 12L + (long)(this.month - 1);
        long calcMonths = monthCount + monthsToAdd;
        int newYear = ChronoField.YEAR.checkValidIntValue(Jdk8Methods.floorDiv(calcMonths, 12L));
        int newMonth = Jdk8Methods.floorMod(calcMonths, 12) + 1;
        return LocalDate.resolvePreviousValid(newYear, newMonth, this.day);
    }

    public LocalDate plusWeeks(long weeksToAdd) {
        return this.plusDays(Jdk8Methods.safeMultiply(weeksToAdd, 7));
    }

    public LocalDate plusDays(long daysToAdd) {
        if (daysToAdd == 0L) {
            return this;
        }
        long mjDay = Jdk8Methods.safeAdd(this.toEpochDay(), daysToAdd);
        return LocalDate.ofEpochDay(mjDay);
    }

    @Override
    public LocalDate minus(TemporalAmount amount) {
        return (LocalDate)amount.subtractFrom(this);
    }

    @Override
    public LocalDate minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public LocalDate minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
    }

    public LocalDate minusMonths(long monthsToSubtract) {
        return monthsToSubtract == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-monthsToSubtract);
    }

    public LocalDate minusWeeks(long weeksToSubtract) {
        return weeksToSubtract == Long.MIN_VALUE ? this.plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : this.plusWeeks(-weeksToSubtract);
    }

    public LocalDate minusDays(long daysToSubtract) {
        return daysToSubtract == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-daysToSubtract);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.localDate()) {
            return (R)this;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return super.adjustInto(temporal);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        LocalDate end = LocalDate.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            switch ((ChronoUnit)unit) {
                case DAYS: {
                    return this.daysUntil(end);
                }
                case WEEKS: {
                    return this.daysUntil(end) / 7L;
                }
                case MONTHS: {
                    return this.monthsUntil(end);
                }
                case YEARS: {
                    return this.monthsUntil(end) / 12L;
                }
                case DECADES: {
                    return this.monthsUntil(end) / 120L;
                }
                case CENTURIES: {
                    return this.monthsUntil(end) / 1200L;
                }
                case MILLENNIA: {
                    return this.monthsUntil(end) / 12000L;
                }
                case ERAS: {
                    return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    long daysUntil(LocalDate end) {
        return end.toEpochDay() - this.toEpochDay();
    }

    private long monthsUntil(LocalDate end) {
        long packed1 = this.getProlepticMonth() * 32L + (long)this.getDayOfMonth();
        long packed2 = end.getProlepticMonth() * 32L + (long)end.getDayOfMonth();
        return (packed2 - packed1) / 32L;
    }

    @Override
    public Period until(ChronoLocalDate endDate) {
        LocalDate end = LocalDate.from(endDate);
        long totalMonths = end.getProlepticMonth() - this.getProlepticMonth();
        int days = end.day - this.day;
        if (totalMonths > 0L && days < 0) {
            LocalDate calcDate = this.plusMonths(--totalMonths);
            days = (int)(end.toEpochDay() - calcDate.toEpochDay());
        } else if (totalMonths < 0L && days > 0) {
            ++totalMonths;
            days -= end.lengthOfMonth();
        }
        long years = totalMonths / 12L;
        int months = (int)(totalMonths % 12L);
        return Period.of(Jdk8Methods.safeToInt(years), months, days);
    }

    public LocalDateTime atTime(LocalTime time) {
        return LocalDateTime.of(this, time);
    }

    public LocalDateTime atTime(int hour, int minute) {
        return this.atTime(LocalTime.of(hour, minute));
    }

    public LocalDateTime atTime(int hour, int minute, int second) {
        return this.atTime(LocalTime.of(hour, minute, second));
    }

    public LocalDateTime atTime(int hour, int minute, int second, int nanoOfSecond) {
        return this.atTime(LocalTime.of(hour, minute, second, nanoOfSecond));
    }

    public OffsetDateTime atTime(OffsetTime time) {
        return OffsetDateTime.of(LocalDateTime.of(this, time.toLocalTime()), time.getOffset());
    }

    public LocalDateTime atStartOfDay() {
        return LocalDateTime.of(this, LocalTime.MIDNIGHT);
    }

    public ZonedDateTime atStartOfDay(ZoneId zone) {
        ZoneRules rules;
        ZoneOffsetTransition trans;
        Jdk8Methods.requireNonNull(zone, "zone");
        LocalDateTime ldt = this.atTime(LocalTime.MIDNIGHT);
        if (!(zone instanceof ZoneOffset) && (trans = (rules = zone.getRules()).getTransition(ldt)) != null && trans.isGap()) {
            ldt = trans.getDateTimeAfter();
        }
        return ZonedDateTime.of(ldt, zone);
    }

    @Override
    public long toEpochDay() {
        long y = this.year;
        long m = this.month;
        long total2 = 0L;
        total2 += 365L * y;
        total2 = y >= 0L ? (total2 += (y + 3L) / 4L - (y + 99L) / 100L + (y + 399L) / 400L) : (total2 -= y / -4L - y / -100L + y / -400L);
        total2 += (367L * m - 362L) / 12L;
        total2 += (long)(this.day - 1);
        if (m > 2L) {
            --total2;
            if (!this.isLeapYear()) {
                --total2;
            }
        }
        return total2 - 719528L;
    }

    @Override
    public int compareTo(ChronoLocalDate other) {
        if (other instanceof LocalDate) {
            return this.compareTo0((LocalDate)other);
        }
        return super.compareTo(other);
    }

    int compareTo0(LocalDate otherDate) {
        int cmp = this.year - otherDate.year;
        if (cmp == 0 && (cmp = this.month - otherDate.month) == 0) {
            cmp = this.day - otherDate.day;
        }
        return cmp;
    }

    @Override
    public boolean isAfter(ChronoLocalDate other) {
        if (other instanceof LocalDate) {
            return this.compareTo0((LocalDate)other) > 0;
        }
        return super.isAfter(other);
    }

    @Override
    public boolean isBefore(ChronoLocalDate other) {
        if (other instanceof LocalDate) {
            return this.compareTo0((LocalDate)other) < 0;
        }
        return super.isBefore(other);
    }

    @Override
    public boolean isEqual(ChronoLocalDate other) {
        if (other instanceof LocalDate) {
            return this.compareTo0((LocalDate)other) == 0;
        }
        return super.isEqual(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDate) {
            return this.compareTo0((LocalDate)obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int yearValue = this.year;
        short monthValue = this.month;
        short dayValue = this.day;
        return yearValue & 0xFFFFF800 ^ (yearValue << 11) + (monthValue << 6) + dayValue;
    }

    @Override
    public String toString() {
        int yearValue = this.year;
        short monthValue = this.month;
        short dayValue = this.day;
        int absYear = Math.abs(yearValue);
        StringBuilder buf = new StringBuilder(10);
        if (absYear < 1000) {
            if (yearValue < 0) {
                buf.append(yearValue - 10000).deleteCharAt(1);
            } else {
                buf.append(yearValue + 10000).deleteCharAt(0);
            }
        } else {
            if (yearValue > 9999) {
                buf.append('+');
            }
            buf.append(yearValue);
        }
        return buf.append(monthValue < 10 ? "-0" : "-").append(monthValue).append(dayValue < 10 ? "-0" : "-").append(dayValue).toString();
    }

    @Override
    public String format(DateTimeFormatter formatter) {
        return super.format(formatter);
    }

    private Object writeReplace() {
        return new Ser(3, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.year);
        out.writeByte(this.month);
        out.writeByte(this.day);
    }

    static LocalDate readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        byte month = in.readByte();
        byte dayOfMonth = in.readByte();
        return LocalDate.of(year, month, (int)dayOfMonth);
    }
}

