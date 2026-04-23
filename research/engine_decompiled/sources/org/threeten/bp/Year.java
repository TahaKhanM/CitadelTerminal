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
import org.threeten.bp.MonthDay;
import org.threeten.bp.Ser;
import org.threeten.bp.YearMonth;
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

public final class Year
extends DefaultInterfaceTemporalAccessor
implements Temporal,
TemporalAdjuster,
Comparable<Year>,
Serializable {
    public static final int MIN_VALUE = -999999999;
    public static final int MAX_VALUE = 999999999;
    public static final TemporalQuery<Year> FROM = new TemporalQuery<Year>(){

        @Override
        public Year queryFrom(TemporalAccessor temporal) {
            return Year.from(temporal);
        }
    };
    private static final long serialVersionUID = -23038383694477807L;
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).toFormatter();
    private final int year;

    public static Year now() {
        return Year.now(Clock.systemDefaultZone());
    }

    public static Year now(ZoneId zone) {
        return Year.now(Clock.system(zone));
    }

    public static Year now(Clock clock) {
        LocalDate now = LocalDate.now(clock);
        return Year.of(now.getYear());
    }

    public static Year of(int isoYear) {
        ChronoField.YEAR.checkValidValue(isoYear);
        return new Year(isoYear);
    }

    public static Year from(TemporalAccessor temporal) {
        if (temporal instanceof Year) {
            return (Year)temporal;
        }
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return Year.of(temporal.get(ChronoField.YEAR));
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Year from TemporalAccessor: " + temporal + ", type " + temporal.getClass().getName());
        }
    }

    public static Year parse(CharSequence text) {
        return Year.parse(text, PARSER);
    }

    public static Year parse(CharSequence text, DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.parse(text, FROM);
    }

    public static boolean isLeap(long year) {
        return (year & 3L) == 0L && (year % 100L != 0L || year % 400L == 0L);
    }

    private Year(int year) {
        this.year = year;
    }

    public int getValue() {
        return this.year;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2 == ChronoField.YEAR || field2 == ChronoField.YEAR_OF_ERA || field2 == ChronoField.ERA;
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit == ChronoUnit.YEARS || unit == ChronoUnit.DECADES || unit == ChronoUnit.CENTURIES || unit == ChronoUnit.MILLENNIA || unit == ChronoUnit.ERAS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.YEAR_OF_ERA) {
            return this.year <= 0 ? ValueRange.of(1L, 1000000000L) : ValueRange.of(1L, 999999999L);
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

    public boolean isLeap() {
        return Year.isLeap(this.year);
    }

    public boolean isValidMonthDay(MonthDay monthDay) {
        return monthDay != null && monthDay.isValidYear(this.year);
    }

    public int length() {
        return this.isLeap() ? 366 : 365;
    }

    @Override
    public Year with(TemporalAdjuster adjuster) {
        return (Year)adjuster.adjustInto(this);
    }

    @Override
    public Year with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            f.checkValidValue(newValue);
            switch (f) {
                case YEAR_OF_ERA: {
                    return Year.of((int)(this.year < 1 ? 1L - newValue : newValue));
                }
                case YEAR: {
                    return Year.of((int)newValue);
                }
                case ERA: {
                    return this.getLong(ChronoField.ERA) == newValue ? this : Year.of(1 - this.year);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.adjustInto(this, newValue);
    }

    @Override
    public Year plus(TemporalAmount amount) {
        return (Year)amount.addTo(this);
    }

    @Override
    public Year plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            switch ((ChronoUnit)unit) {
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

    public Year plusYears(long yearsToAdd) {
        if (yearsToAdd == 0L) {
            return this;
        }
        return Year.of(ChronoField.YEAR.checkValidIntValue((long)this.year + yearsToAdd));
    }

    @Override
    public Year minus(TemporalAmount amount) {
        return (Year)amount.subtractFrom(this);
    }

    @Override
    public Year minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }

    public Year minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)IsoChronology.INSTANCE;
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.YEARS;
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
        return temporal.with(ChronoField.YEAR, this.year);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        Year end = Year.from(endExclusive);
        if (unit instanceof ChronoUnit) {
            long yearsUntil = (long)end.year - (long)this.year;
            switch ((ChronoUnit)unit) {
                case YEARS: {
                    return yearsUntil;
                }
                case DECADES: {
                    return yearsUntil / 10L;
                }
                case CENTURIES: {
                    return yearsUntil / 100L;
                }
                case MILLENNIA: {
                    return yearsUntil / 1000L;
                }
                case ERAS: {
                    return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
                }
            }
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    public LocalDate atDay(int dayOfYear) {
        return LocalDate.ofYearDay(this.year, dayOfYear);
    }

    public YearMonth atMonth(Month month) {
        return YearMonth.of(this.year, month);
    }

    public YearMonth atMonth(int month) {
        return YearMonth.of(this.year, month);
    }

    public LocalDate atMonthDay(MonthDay monthDay) {
        return monthDay.atYear(this.year);
    }

    @Override
    public int compareTo(Year other) {
        return this.year - other.year;
    }

    public boolean isAfter(Year other) {
        return this.year > other.year;
    }

    public boolean isBefore(Year other) {
        return this.year < other.year;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Year) {
            return this.year == ((Year)obj).year;
        }
        return false;
    }

    public int hashCode() {
        return this.year;
    }

    public String toString() {
        return Integer.toString(this.year);
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    private Object writeReplace() {
        return new Ser(67, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.year);
    }

    static Year readExternal(DataInput in) throws IOException {
        return Year.of(in.readInt());
    }
}

