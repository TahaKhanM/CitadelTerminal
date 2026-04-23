/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.util.Comparator;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTimeImpl;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.Era;
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

public abstract class ChronoLocalDate
extends DefaultInterfaceTemporal
implements Temporal,
TemporalAdjuster,
Comparable<ChronoLocalDate> {
    private static final Comparator<ChronoLocalDate> DATE_COMPARATOR = new Comparator<ChronoLocalDate>(){

        @Override
        public int compare(ChronoLocalDate date1, ChronoLocalDate date2) {
            return Jdk8Methods.compareLongs(date1.toEpochDay(), date2.toEpochDay());
        }
    };

    public static Comparator<ChronoLocalDate> timeLineOrder() {
        return DATE_COMPARATOR;
    }

    public static ChronoLocalDate from(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (temporal instanceof ChronoLocalDate) {
            return (ChronoLocalDate)temporal;
        }
        Chronology chrono = temporal.query(TemporalQueries.chronology());
        if (chrono == null) {
            throw new DateTimeException("No Chronology found to create ChronoLocalDate: " + temporal.getClass());
        }
        return chrono.date(temporal);
    }

    public abstract Chronology getChronology();

    public Era getEra() {
        return this.getChronology().eraOf(this.get(ChronoField.ERA));
    }

    public boolean isLeapYear() {
        return this.getChronology().isLeapYear(this.getLong(ChronoField.YEAR));
    }

    public abstract int lengthOfMonth();

    public int lengthOfYear() {
        return this.isLeapYear() ? 366 : 365;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isDateBased();
        }
        return field2 != null && field2.isSupportedBy(this);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isDateBased();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public ChronoLocalDate with(TemporalAdjuster adjuster) {
        return this.getChronology().ensureChronoLocalDate(super.with(adjuster));
    }

    @Override
    public abstract ChronoLocalDate with(TemporalField var1, long var2);

    @Override
    public ChronoLocalDate plus(TemporalAmount amount) {
        return this.getChronology().ensureChronoLocalDate(super.plus(amount));
    }

    @Override
    public abstract ChronoLocalDate plus(long var1, TemporalUnit var3);

    @Override
    public ChronoLocalDate minus(TemporalAmount amount) {
        return this.getChronology().ensureChronoLocalDate(super.minus(amount));
    }

    @Override
    public ChronoLocalDate minus(long amountToSubtract, TemporalUnit unit) {
        return this.getChronology().ensureChronoLocalDate(super.minus(amountToSubtract, unit));
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)this.getChronology();
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.DAYS;
        }
        if (query == TemporalQueries.localDate()) {
            return (R)LocalDate.ofEpochDay(this.toEpochDay());
        }
        if (query == TemporalQueries.localTime() || query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, this.toEpochDay());
    }

    public abstract ChronoPeriod until(ChronoLocalDate var1);

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public ChronoLocalDateTime<?> atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    public long toEpochDay() {
        return this.getLong(ChronoField.EPOCH_DAY);
    }

    @Override
    public int compareTo(ChronoLocalDate other) {
        int cmp = Jdk8Methods.compareLongs(this.toEpochDay(), other.toEpochDay());
        if (cmp == 0) {
            cmp = this.getChronology().compareTo(other.getChronology());
        }
        return cmp;
    }

    public boolean isAfter(ChronoLocalDate other) {
        return this.toEpochDay() > other.toEpochDay();
    }

    public boolean isBefore(ChronoLocalDate other) {
        return this.toEpochDay() < other.toEpochDay();
    }

    public boolean isEqual(ChronoLocalDate other) {
        return this.toEpochDay() == other.toEpochDay();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoLocalDate) {
            return this.compareTo((ChronoLocalDate)obj) == 0;
        }
        return false;
    }

    public int hashCode() {
        long epDay = this.toEpochDay();
        return this.getChronology().hashCode() ^ (int)(epDay ^ epDay >>> 32);
    }

    public String toString() {
        long yoe = this.getLong(ChronoField.YEAR_OF_ERA);
        long moy = this.getLong(ChronoField.MONTH_OF_YEAR);
        long dom = this.getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder buf = new StringBuilder(30);
        buf.append(this.getChronology().toString()).append(" ").append(this.getEra()).append(" ").append(yoe).append(moy < 10L ? "-0" : "-").append(moy).append(dom < 10L ? "-0" : "-").append(dom);
        return buf.toString();
    }
}

