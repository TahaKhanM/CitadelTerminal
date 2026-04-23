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
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.SignStyle;
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

public final class YearMonth
extends DefaultInterfaceTemporalAccessor
implements Temporal,
TemporalAdjuster,
Comparable<YearMonth>,
Serializable {
    public static final TemporalQuery<YearMonth> FROM = new TemporalQuery<YearMonth>(){

        @Override
        public YearMonth queryFrom(TemporalAccessor temporal) {
            return YearMonth.from(temporal);
        }
    };
    private static final long serialVersionUID = 4183400860270640070L;
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).toFormatter();
    private final int year;
    private final int month;

    public static YearMonth now() {
        return YearMonth.now(Clock.systemDefaultZone());
    }

    public static YearMonth now(ZoneId zone) {
        return YearMonth.now(Clock.system(zone));
    }

    public static YearMonth now(Clock clock) {
        LocalDate now = LocalDate.now(clock);
        return YearMonth.of(now.getYear(), now.getMonth());
    }

    public static YearMonth of(int year, Month month) {
        Jdk8Methods.requireNonNull(month, "month");
        return YearMonth.of(year, month.getValue());
    }

    public static YearMonth of(int year, int month) {
        ChronoField.YEAR.checkValidValue(year);
        ChronoField.MONTH_OF_YEAR.checkValidValue(month);
        return new YearMonth(year, month);
    }

    public static YearMonth from(TemporalAccessor temporal) {
        if (temporal instanceof YearMonth) {
            return (YearMonth)temporal;
        }
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return YearMonth.of(temporal.get(ChronoField.YEAR), temporal.get(ChronoField.MONTH_OF_YEAR));
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain YearMonth from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
    }

    public static YearMonth parse(CharSequence text) {
        return YearMonth.parse(text, PARSER);
    }

    public static YearMonth parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    private YearMonth(int year, int month) {
        this.year = year;
        this.month = month;
    }

    private YearMonth with(int newYear, int newMonth) {
        if (this.year == newYear && this.month == newMonth) {
            return this;
        }
        return new YearMonth(newYear, newMonth);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.YEAR || field2 == ChronoField.MONTH_OF_YEAR || field2 == ChronoField.PROLEPTIC_MONTH || field2 == ChronoField.YEAR_OF_ERA || field2 == ChronoField.ERA;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit == ChronoUnit.MONTHS || unit == ChronoUnit.YEARS || unit == ChronoUnit.DECADES || unit == ChronoUnit.CENTURIES || unit == ChronoUnit.MILLENNIA || unit == ChronoUnit.ERAS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.YEAR_OF_ERA) {
            return this.getYear() <= 0 ? ValueRange.of(1L, 1000000000L) : ValueRange.of(1L, 999999999L);
        }
        return super.range(field2);
    }

    @Override
    public int get(TemporalField field2) {
        return this.range(field2).checkValidIntValue(this.getLong(field2), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case MONTH_OF_YEAR: {
                    return this.month;
                }
                case PROLEPTIC_MONTH: {
                    return this.getProlepticMonth();
                }
                case YEAR_OF_ERA: {
                    return this.year < 1 ? 1 - this.year : this.year;
                }
                case YEAR: {
                    return this.year;
                }
                case ERA: {
                    return this.year < 1 ? 0 : 1;
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.getFrom(this);
    }

    private long getProlepticMonth() {
        return (long)this.year * 12L + (long)(this.month - 1);
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

    public boolean isLeapYear() {
        return IsoChronology.INSTANCE.isLeapYear(this.year);
    }

    public boolean isValidDay(int dayOfMonth) {
        return dayOfMonth >= 1 && dayOfMonth <= this.lengthOfMonth();
    }

    public int lengthOfMonth() {
        return this.getMonth().length(this.isLeapYear());
    }

    public int lengthOfYear() {
        return this.isLeapYear() ? 366 : 365;
    }

    @Override
    public YearMonth with(TemporalAdjuster adjuster) {
        return (YearMonth)adjuster.adjustInto(this);
    }

    @Override
    public YearMonth with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            switch (f) {
                case MONTH_OF_YEAR: {
                    return this.withMonth((int)newValue);
                }
                case PROLEPTIC_MONTH: {
                    return this.plusMonths(newValue - this.getLong(ChronoField.PROLEPTIC_MONTH));
                }
                case YEAR_OF_ERA: {
                    return this.withYear((int)(this.year < 1 ? 1L - newValue : newValue));
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

    public YearMonth withYear(int year) {
        ChronoField.YEAR.checkValidValue(year);
        return this.with(year, this.month);
    }

    public YearMonth withMonth(int month) {
        ChronoField.MONTH_OF_YEAR.checkValidValue(month);
        return this.with(this.year, month);
    }

    @Override
    public YearMonth plus(TemporalAmount amount) {
        return (YearMonth)amount.addTo(this);
    }

    @Override
    public YearMonth plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            switch ((ChronoUnit)unit) {
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

    public YearMonth plusYears(long yearsToAdd) {
        if (yearsToAdd == 0L) {
            return this;
        }
        int newYear = ChronoField.YEAR.checkValidIntValue((long)this.year + yearsToAdd);
        return this.with(newYear, this.month);
    }

    public YearMonth plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0L) {
            return this;
        }
        long monthCount = (long)this.year * 12L + (long)(this.month - 1);
        long calcMonths = monthCount + monthsToAdd;
        int newYear = ChronoField.YEAR.checkValidIntValue(Jdk8Methods.floorDiv(calcMonths, 12L));
        int newMonth = Jdk8Methods.floorMod(calcMonths, 12) + 1;
        return this.with(newYear, newMonth);
    }

    @Override
    public YearMonth minus(TemporalAmount amount) {
        return (YearMonth)amount.subtractFrom(this);
    }

    @Override
    public YearMonth minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public YearMonth minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
    }

    public YearMonth minusMonths(long monthsToSubtract) {
        return monthsToSubtract == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-monthsToSubtract);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)IsoChronology.INSTANCE;
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.MONTHS;
        }
        if (query == TemporalQueries.localDate() || query == TemporalQueries.localTime() || query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.with(ChronoField.PROLEPTIC_MONTH, this.getProlepticMonth());
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        YearMonth end = YearMonth.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            long monthsUntil = end.getProlepticMonth() - this.getProlepticMonth();
            switch ((ChronoUnit)unit) {
                case MONTHS: {
                    return monthsUntil;
                }
                case YEARS: {
                    return monthsUntil / 12L;
                }
                case DECADES: {
                    return monthsUntil / 120L;
                }
                case CENTURIES: {
                    return monthsUntil / 1200L;
                }
                case MILLENNIA: {
                    return monthsUntil / 12000L;
                }
                case ERAS: {
                    return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    public LocalDate atDay(int dayOfMonth) {
        return LocalDate.of(this.year, this.month, dayOfMonth);
    }

    public LocalDate atEndOfMonth() {
        return LocalDate.of(this.year, this.month, this.lengthOfMonth());
    }

    @Override
    public int compareTo(YearMonth other) {
        int cmp = this.year - other.year;
        if (cmp == 0) {
            cmp = this.month - other.month;
        }
        return cmp;
    }

    public boolean isAfter(YearMonth other) {
        return this.compareTo(other) > 0;
    }

    public boolean isBefore(YearMonth other) {
        return this.compareTo(other) < 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YearMonth) {
            YearMonth other = (YearMonth)obj;
            return this.year == other.year && this.month == other.month;
        }
        return false;
    }

    public int hashCode() {
        return this.year ^ this.month << 27;
    }

    public String toString() {
        int absYear = Math.abs(this.year);
        StringBuilder buf = new StringBuilder(9);
        if (absYear < 1000) {
            if (this.year < 0) {
                buf.append(this.year - 10000).deleteCharAt(1);
            } else {
                buf.append(this.year + 10000).deleteCharAt(0);
            }
        } else {
            buf.append(this.year);
        }
        return buf.append(this.month < 10 ? "-0" : "-").append(this.month).toString();
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    private Object writeReplace() {
        return new Ser(68, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.year);
        out.writeByte(this.month);
    }

    static YearMonth readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        byte month = in.readByte();
        return YearMonth.of(year, month);
    }
}

