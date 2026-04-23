/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoDateImpl;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.JapaneseChronology;
import org.threeten.bp.chrono.JapaneseEra;
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

public final class JapaneseDate
extends ChronoDateImpl<JapaneseDate>
implements Serializable {
    private static final long serialVersionUID = -305327627230580483L;
    static final LocalDate MIN_DATE = LocalDate.of(1873, 1, 1);
    private final LocalDate isoDate;
    private transient JapaneseEra era;
    private transient int yearOfEra;

    public static JapaneseDate now() {
        return JapaneseDate.now(Clock.systemDefaultZone());
    }

    public static JapaneseDate now(ZoneId zone) {
        return JapaneseDate.now(Clock.system(zone));
    }

    public static JapaneseDate now(Clock clock) {
        return new JapaneseDate(LocalDate.now(clock));
    }

    public static JapaneseDate of(JapaneseEra era, int yearOfEra, int month, int dayOfMonth) {
        Jdk8Methods.requireNonNull(era, "era");
        if (yearOfEra < 1) {
            throw new DateTimeException("Invalid YearOfEra: " + yearOfEra);
        }
        LocalDate eraStartDate = era.startDate();
        LocalDate eraEndDate = era.endDate();
        int yearOffset = eraStartDate.getYear() - 1;
        LocalDate date = LocalDate.of(yearOfEra + yearOffset, month, dayOfMonth);
        if (date.isBefore(eraStartDate) || date.isAfter(eraEndDate)) {
            throw new DateTimeException("Requested date is outside bounds of era " + era);
        }
        return new JapaneseDate(era, yearOfEra, date);
    }

    static JapaneseDate ofYearDay(JapaneseEra era, int yearOfEra, int dayOfYear) {
        Jdk8Methods.requireNonNull(era, "era");
        if (yearOfEra < 1) {
            throw new DateTimeException("Invalid YearOfEra: " + yearOfEra);
        }
        LocalDate eraStartDate = era.startDate();
        LocalDate eraEndDate = era.endDate();
        if (yearOfEra == 1 && (dayOfYear += eraStartDate.getDayOfYear() - 1) > eraStartDate.lengthOfYear()) {
            throw new DateTimeException("DayOfYear exceeds maximum allowed in the first year of era " + era);
        }
        int yearOffset = eraStartDate.getYear() - 1;
        LocalDate isoDate = LocalDate.ofYearDay(yearOfEra + yearOffset, dayOfYear);
        if (isoDate.isBefore(eraStartDate) || isoDate.isAfter(eraEndDate)) {
            throw new DateTimeException("Requested date is outside bounds of era " + era);
        }
        return new JapaneseDate(era, yearOfEra, isoDate);
    }

    public static JapaneseDate of(int prolepticYear, int month, int dayOfMonth) {
        return new JapaneseDate(LocalDate.of(prolepticYear, month, dayOfMonth));
    }

    public static JapaneseDate from(TemporalAccessor temporal) {
        return JapaneseChronology.INSTANCE.date(temporal);
    }

    JapaneseDate(LocalDate isoDate) {
        if (isoDate.isBefore(MIN_DATE)) {
            throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
        }
        this.era = JapaneseEra.from(isoDate);
        int yearOffset = this.era.startDate().getYear() - 1;
        this.yearOfEra = isoDate.getYear() - yearOffset;
        this.isoDate = isoDate;
    }

    JapaneseDate(JapaneseEra era, int year, LocalDate isoDate) {
        if (isoDate.isBefore(MIN_DATE)) {
            throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
        }
        this.era = era;
        this.yearOfEra = year;
        this.isoDate = isoDate;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.era = JapaneseEra.from(this.isoDate);
        int yearOffset = this.era.startDate().getYear() - 1;
        this.yearOfEra = this.isoDate.getYear() - yearOffset;
    }

    @Override
    public JapaneseChronology getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    @Override
    public JapaneseEra getEra() {
        return this.era;
    }

    @Override
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override
    public int lengthOfYear() {
        Calendar jcal = Calendar.getInstance(JapaneseChronology.LOCALE);
        jcal.set(0, this.era.getValue() + 2);
        jcal.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return jcal.getActualMaximum(6);
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || field2 == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || field2 == ChronoField.ALIGNED_WEEK_OF_MONTH || field2 == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return super.isSupported(field2);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (this.isSupported(field2)) {
                ChronoField f = (ChronoField)field2;
                switch (f) {
                    case DAY_OF_YEAR: {
                        return this.actualRange(6);
                    }
                    case YEAR_OF_ERA: {
                        return this.actualRange(1);
                    }
                }
                return this.getChronology().range(f);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
        }
        return field2.rangeRefinedBy(this);
    }

    private ValueRange actualRange(int calendarField) {
        Calendar jcal = Calendar.getInstance(JapaneseChronology.LOCALE);
        jcal.set(0, this.era.getValue() + 2);
        jcal.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return ValueRange.of(jcal.getActualMinimum(calendarField), jcal.getActualMaximum(calendarField));
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case ALIGNED_DAY_OF_WEEK_IN_MONTH: 
                case ALIGNED_DAY_OF_WEEK_IN_YEAR: 
                case ALIGNED_WEEK_OF_MONTH: 
                case ALIGNED_WEEK_OF_YEAR: {
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
                }
                case YEAR_OF_ERA: {
                    return this.yearOfEra;
                }
                case ERA: {
                    return this.era.getValue();
                }
                case DAY_OF_YEAR: {
                    return this.getDayOfYear();
                }
            }
            return this.isoDate.getLong(field2);
        }
        return field2.getFrom(this);
    }

    private long getDayOfYear() {
        if (this.yearOfEra == 1) {
            return this.isoDate.getDayOfYear() - this.era.startDate().getDayOfYear() + 1;
        }
        return this.isoDate.getDayOfYear();
    }

    @Override
    public JapaneseDate with(TemporalAdjuster adjuster) {
        return (JapaneseDate)super.with(adjuster);
    }

    @Override
    public JapaneseDate with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            ChronoField f = (ChronoField)field2;
            if (this.getLong(f) == newValue) {
                return this;
            }
            switch (f) {
                case DAY_OF_YEAR: 
                case YEAR_OF_ERA: 
                case ERA: {
                    int nvalue = this.getChronology().range(f).checkValidIntValue(newValue, f);
                    switch (f) {
                        case DAY_OF_YEAR: {
                            return this.with(this.isoDate.plusDays((long)nvalue - this.getDayOfYear()));
                        }
                        case YEAR_OF_ERA: {
                            return this.withYear(nvalue);
                        }
                        case ERA: {
                            return this.withYear(JapaneseEra.of(nvalue), this.yearOfEra);
                        }
                    }
                }
            }
            return this.with(this.isoDate.with(field2, newValue));
        }
        return field2.adjustInto(this, newValue);
    }

    @Override
    public JapaneseDate plus(TemporalAmount amount) {
        return (JapaneseDate)super.plus(amount);
    }

    @Override
    public JapaneseDate plus(long amountToAdd, TemporalUnit unit) {
        return (JapaneseDate)super.plus(amountToAdd, unit);
    }

    @Override
    public JapaneseDate minus(TemporalAmount amount) {
        return (JapaneseDate)super.minus(amount);
    }

    @Override
    public JapaneseDate minus(long amountToAdd, TemporalUnit unit) {
        return (JapaneseDate)super.minus(amountToAdd, unit);
    }

    private JapaneseDate withYear(JapaneseEra era, int yearOfEra) {
        int year = JapaneseChronology.INSTANCE.prolepticYear(era, yearOfEra);
        return this.with(this.isoDate.withYear(year));
    }

    private JapaneseDate withYear(int year) {
        return this.withYear(this.getEra(), year);
    }

    JapaneseDate plusYears(long years) {
        return this.with(this.isoDate.plusYears(years));
    }

    JapaneseDate plusMonths(long months) {
        return this.with(this.isoDate.plusMonths(months));
    }

    JapaneseDate plusDays(long days) {
        return this.with(this.isoDate.plusDays(days));
    }

    private JapaneseDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new JapaneseDate(newDate);
    }

    @Override
    public final ChronoLocalDateTime<JapaneseDate> atTime(LocalTime localTime) {
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
        if (obj instanceof JapaneseDate) {
            JapaneseDate otherDate = (JapaneseDate)obj;
            return this.isoDate.equals(otherDate.isoDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private Object writeReplace() {
        return new Ser(1, this);
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
        return JapaneseChronology.INSTANCE.date(year, month, dayOfMonth);
    }
}

