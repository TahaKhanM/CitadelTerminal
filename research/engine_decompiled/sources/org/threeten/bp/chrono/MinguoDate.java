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
import org.threeten.bp.chrono.MinguoChronology;
import org.threeten.bp.chrono.MinguoEra;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class MinguoDate
extends ChronoDateImpl<MinguoDate>
implements Serializable {
    private static final long serialVersionUID = 1300372329181994526L;
    private final LocalDate isoDate;

    public static MinguoDate now() {
        return MinguoDate.now(Clock.systemDefaultZone());
    }

    public static MinguoDate now(ZoneId zone) {
        return MinguoDate.now(Clock.system(zone));
    }

    public static MinguoDate now(Clock clock) {
        return new MinguoDate(LocalDate.now(clock));
    }

    public static MinguoDate of(int prolepticYear, int month, int dayOfMonth) {
        return MinguoChronology.INSTANCE.date(prolepticYear, month, dayOfMonth);
    }

    public static MinguoDate from(TemporalAccessor temporal) {
        return MinguoChronology.INSTANCE.date(temporal);
    }

    MinguoDate(LocalDate date) {
        Jdk8Methods.requireNonNull(date, "date");
        this.isoDate = date;
    }

    @Override
    public MinguoChronology getChronology() {
        return MinguoChronology.INSTANCE;
    }

    @Override
    public MinguoEra getEra() {
        return (MinguoEra)super.getEra();
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
                        long max2 = this.getProlepticYear() <= 0 ? -range2.getMinimum() + 1L + 1911L : range2.getMaximum() - 1911L;
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
        return this.isoDate.getYear() - 1911;
    }

    @Override
    public MinguoDate with(TemporalAdjuster adjuster) {
        return (MinguoDate)super.with(adjuster);
    }

    @Override
    public MinguoDate with(TemporalField field2, long newValue) {
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
                            return this.with(this.isoDate.withYear(this.getProlepticYear() >= 1 ? nvalue + 1911 : 1 - nvalue + 1911));
                        }
                        case YEAR: {
                            return this.with(this.isoDate.withYear(nvalue + 1911));
                        }
                        case ERA: {
                            return this.with(this.isoDate.withYear(1 - this.getProlepticYear() + 1911));
                        }
                    }
                }
            }
            return this.with(this.isoDate.with(field2, newValue));
        }
        return field2.adjustInto(this, newValue);
    }

    @Override
    public MinguoDate plus(TemporalAmount amount) {
        return (MinguoDate)super.plus(amount);
    }

    @Override
    public MinguoDate plus(long amountToAdd, TemporalUnit unit) {
        return (MinguoDate)super.plus(amountToAdd, unit);
    }

    @Override
    public MinguoDate minus(TemporalAmount amount) {
        return (MinguoDate)super.minus(amount);
    }

    @Override
    public MinguoDate minus(long amountToAdd, TemporalUnit unit) {
        return (MinguoDate)super.minus(amountToAdd, unit);
    }

    MinguoDate plusYears(long years) {
        return this.with(this.isoDate.plusYears(years));
    }

    MinguoDate plusMonths(long months) {
        return this.with(this.isoDate.plusMonths(months));
    }

    MinguoDate plusDays(long days) {
        return this.with(this.isoDate.plusDays(days));
    }

    private MinguoDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new MinguoDate(newDate);
    }

    @Override
    public final ChronoLocalDateTime<MinguoDate> atTime(LocalTime localTime) {
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
        if (obj instanceof MinguoDate) {
            MinguoDate otherDate = (MinguoDate)obj;
            return this.isoDate.equals(otherDate.isoDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private Object writeReplace() {
        return new Ser(5, this);
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
        return MinguoChronology.INSTANCE.date(year, month, dayOfMonth);
    }
}

