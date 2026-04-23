/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.util.Comparator;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
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

public abstract class ChronoLocalDateTime<D extends ChronoLocalDate>
extends DefaultInterfaceTemporal
implements Temporal,
TemporalAdjuster,
Comparable<ChronoLocalDateTime<?>> {
    private static final Comparator<ChronoLocalDateTime<?>> DATE_TIME_COMPARATOR = new Comparator<ChronoLocalDateTime<?>>(){

        @Override
        public int compare(ChronoLocalDateTime<?> datetime1, ChronoLocalDateTime<?> datetime2) {
            int cmp = Jdk8Methods.compareLongs(((ChronoLocalDate)datetime1.toLocalDate()).toEpochDay(), ((ChronoLocalDate)datetime2.toLocalDate()).toEpochDay());
            if (cmp == 0) {
                cmp = Jdk8Methods.compareLongs(datetime1.toLocalTime().toNanoOfDay(), datetime2.toLocalTime().toNanoOfDay());
            }
            return cmp;
        }
    };

    public static Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return DATE_TIME_COMPARATOR;
    }

    public static ChronoLocalDateTime<?> from(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (temporal instanceof ChronoLocalDateTime) {
            return (ChronoLocalDateTime)temporal;
        }
        Chronology chrono = temporal.query(TemporalQueries.chronology());
        if (chrono == null) {
            throw new DateTimeException("No Chronology found to create ChronoLocalDateTime: " + temporal.getClass());
        }
        return chrono.localDateTime(temporal);
    }

    public Chronology getChronology() {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology();
    }

    public abstract D toLocalDate();

    public abstract LocalTime toLocalTime();

    @Override
    public ChronoLocalDateTime<D> with(TemporalAdjuster adjuster) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoLocalDateTime(super.with(adjuster));
    }

    @Override
    public abstract ChronoLocalDateTime<D> with(TemporalField var1, long var2);

    @Override
    public ChronoLocalDateTime<D> plus(TemporalAmount amount) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoLocalDateTime(super.plus(amount));
    }

    @Override
    public abstract ChronoLocalDateTime<D> plus(long var1, TemporalUnit var3);

    @Override
    public ChronoLocalDateTime<D> minus(TemporalAmount amount) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoLocalDateTime(super.minus(amount));
    }

    @Override
    public ChronoLocalDateTime<D> minus(long amountToSubtract, TemporalUnit unit) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoLocalDateTime(super.minus(amountToSubtract, unit));
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R)this.getChronology();
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.localDate()) {
            return (R)LocalDate.ofEpochDay(((ChronoLocalDate)this.toLocalDate()).toEpochDay());
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this.toLocalTime();
        }
        if (query == TemporalQueries.zone() || query == TemporalQueries.zoneId() || query == TemporalQueries.offset()) {
            return null;
        }
        return super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, ((ChronoLocalDate)this.toLocalDate()).toEpochDay()).with(ChronoField.NANO_OF_DAY, this.toLocalTime().toNanoOfDay());
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public abstract ChronoZonedDateTime<D> atZone(ZoneId var1);

    public Instant toInstant(ZoneOffset offset) {
        return Instant.ofEpochSecond(this.toEpochSecond(offset), this.toLocalTime().getNano());
    }

    public long toEpochSecond(ZoneOffset offset) {
        Jdk8Methods.requireNonNull(offset, "offset");
        long epochDay = ((ChronoLocalDate)this.toLocalDate()).toEpochDay();
        long secs = epochDay * 86400L + (long)this.toLocalTime().toSecondOfDay();
        return secs -= (long)offset.getTotalSeconds();
    }

    @Override
    public int compareTo(ChronoLocalDateTime<?> other) {
        int cmp = ((ChronoLocalDate)this.toLocalDate()).compareTo((ChronoLocalDate)other.toLocalDate());
        if (cmp == 0 && (cmp = this.toLocalTime().compareTo(other.toLocalTime())) == 0) {
            cmp = this.getChronology().compareTo(other.getChronology());
        }
        return cmp;
    }

    public boolean isAfter(ChronoLocalDateTime<?> other) {
        long otherEpDay;
        long thisEpDay = ((ChronoLocalDate)this.toLocalDate()).toEpochDay();
        return thisEpDay > (otherEpDay = ((ChronoLocalDate)other.toLocalDate()).toEpochDay()) || thisEpDay == otherEpDay && this.toLocalTime().toNanoOfDay() > other.toLocalTime().toNanoOfDay();
    }

    public boolean isBefore(ChronoLocalDateTime<?> other) {
        long otherEpDay;
        long thisEpDay = ((ChronoLocalDate)this.toLocalDate()).toEpochDay();
        return thisEpDay < (otherEpDay = ((ChronoLocalDate)other.toLocalDate()).toEpochDay()) || thisEpDay == otherEpDay && this.toLocalTime().toNanoOfDay() < other.toLocalTime().toNanoOfDay();
    }

    public boolean isEqual(ChronoLocalDateTime<?> other) {
        return this.toLocalTime().toNanoOfDay() == other.toLocalTime().toNanoOfDay() && ((ChronoLocalDate)this.toLocalDate()).toEpochDay() == ((ChronoLocalDate)other.toLocalDate()).toEpochDay();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoLocalDateTime) {
            return this.compareTo((ChronoLocalDateTime)obj) == 0;
        }
        return false;
    }

    public int hashCode() {
        return ((ChronoLocalDate)this.toLocalDate()).hashCode() ^ this.toLocalTime().hashCode();
    }

    public String toString() {
        return ((ChronoLocalDate)this.toLocalDate()).toString() + 'T' + this.toLocalTime().toString();
    }
}

