/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoDateImpl;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.chrono.ThaiBuddhistChronology;
import org.threeten.bp.chrono.ThaiBuddhistEra;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class ThaiBuddhistDate
extends ChronoDateImpl<ThaiBuddhistDate>
implements Serializable {
    private static final long serialVersionUID = -8722293800195731463L;
    private final LocalDate isoDate;

    public static ThaiBuddhistDate now() {
        return ThaiBuddhistDate.now(Clock.systemDefaultZone());
    }

    public static ThaiBuddhistDate now(ZoneId zone) {
        return ThaiBuddhistDate.now(Clock.system(zone));
    }

    public static ThaiBuddhistDate now(Clock clock) {
        return new ThaiBuddhistDate(LocalDate.now(clock));
    }

    public static ThaiBuddhistDate of(int prolepticYear, int month, int dayOfMonth) {
        return ThaiBuddhistChronology.INSTANCE.date(prolepticYear, month, dayOfMonth);
    }

    public static ThaiBuddhistDate from(TemporalAccessor temporal) {
        return ThaiBuddhistChronology.INSTANCE.date(temporal);
    }

    ThaiBuddhistDate(LocalDate date) {
        Jdk8Methods.requireNonNull(date, "date");
        this.isoDate = date;
    }

    @Override
    public ThaiBuddhistChronology getChronology() {
        return ThaiBuddhistChronology.INSTANCE;
    }

    @Override
    public ThaiBuddhistEra getEra() {
        return (ThaiBuddhistEra)super.getEra();
    }

    @Override
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (this.isSupported(field2)) {
                ChronoField f = (ChronoField)field2;
                switch (f) {
                    case DAY_OF_MONTH: 
                    case DAY_OF_YEAR: 
                    case ALIGNED_WEEK_OF_MONTH: {
                        return this.isoDate.range(field2);
                    }
                    case YEAR_OF_ERA: {
                        ValueRange range2 = ChronoField.YEAR.range();
                        long max2 = this.getProlepticYear() <= 0 ? -(range2.getMinimum() + 543L) + 1L : range2.getMaximum() + 543L;
                        return ValueRange.of(1L, max2);
                    }
                }
                return this.getChronology().range(f);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case PROLEPTIC_MONTH: {
                    return this.getProlepticMonth();
                }
                case YEAR_OF_ERA: {
                    int prolepticYear = this.getProlepticYear();
                    return prolepticYear >= 1 ? prolepticYear : 1 - prolepticYear;
                }
                case YEAR: {
                    return this.getProlepticYear();
                }
                case ERA: {
                    return this.getProlepticYear() >= 1 ? 1 : 0;
                }
            }
            return this.isoDate.getLong(field2);
        }
        return field2.getFrom(this);
    }

    private long getProlepticMonth() {
        return (long)this.getProlepticYear() * 12L + (long)this.isoDate.getMonthValue() - 1L;
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() + 543;
    }

    @Override
    public ThaiBuddhistDate with(TemporalAdjuster adjuster) {
        return (ThaiBuddhistDate)super.with(adjuster);
    }

    @Override
    public ThaiBuddhistDate with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            if (this.getLong(f) == newValue) {
                return this;
            }
            switch (f) {
                case PROLEPTIC_MONTH: {
                    this.getChronology().range(f).checkValidValue(newValue, f);
                    return this.plusMonths(newValue - this.getProlepticMonth());
                }
                case YEAR_OF_ERA: 
                case YEAR: 
                case ERA: {
                    int nvalue = this.getChronology().range(f).checkValidIntValue(newValue, f);
                    switch (f) {
                        case YEAR_OF_ERA: {
                            return this.with(this.isoDate.withYear((this.getProlepticYear() >= 1 ? nvalue : 1 - nvalue) - 543));
                        }
                        case YEAR: {
                            return this.with(this.isoDate.withYear(nvalue - 543));
                        }
                        case ERA: {
                            return this.with(this.isoDate.withYear(1 - this.getProlepticYear() - 543));
                        }
                    }
                }
            }
            return this.with(this.isoDate.with(field2, newValue));
        }
        return field2.adjustInto(this, newValue);
    }

    @Override
    public ThaiBuddhistDate plus(TemporalAmount amount) {
        return (ThaiBuddhistDate)super.plus(amount);
    }

    @Override
    public ThaiBuddhistDate plus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate)super.plus(amountToAdd, unit);
    }

    @Override
    public ThaiBuddhistDate minus(TemporalAmount amount) {
        return (ThaiBuddhistDate)super.minus(amount);
    }

    @Override
    public ThaiBuddhistDate minus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate)super.minus(amountToAdd, unit);
    }

    ThaiBuddhistDate plusYears(long years) {
        return this.with(this.isoDate.plusYears(years));
    }

    ThaiBuddhistDate plusMonths(long months) {
        return this.with(this.isoDate.plusMonths(months));
    }

    ThaiBuddhistDate plusDays(long days) {
        return this.with(this.isoDate.plusDays(days));
    }

    private ThaiBuddhistDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new ThaiBuddhistDate(newDate);
    }

    @Override
    public final ChronoLocalDateTime<ThaiBuddhistDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override
    public ChronoPeriod until(ChronoLocalDate endDate) {
        Period period = this.isoDate.until(endDate);
        return this.getChronology().period(period.getYears(), period.getMonths(), period.getDays());
    }

    @Override
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ThaiBuddhistDate) {
            ThaiBuddhistDate otherDate = (ThaiBuddhistDate)obj;
            return this.isoDate.equals(otherDate.isoDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private Object writeReplace() {
        return new Ser(7, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.get(ChronoField.YEAR));
        out.writeByte(this.get(ChronoField.MONTH_OF_YEAR));
        out.writeByte(this.get(ChronoField.DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        byte month = in.readByte();
        byte dayOfMonth = in.readByte();
        return ThaiBuddhistChronology.INSTANCE.date(year, month, dayOfMonth);
    }
}

