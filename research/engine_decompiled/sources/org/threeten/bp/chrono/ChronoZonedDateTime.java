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
import org.threeten.bp.chrono.ChronoLocalDateTime;
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
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public abstract class ChronoZonedDateTime<D extends ChronoLocalDate>
extends DefaultInterfaceTemporal
implements Temporal,
Comparable<ChronoZonedDateTime<?>> {
    private static Comparator<ChronoZonedDateTime<?>> INSTANT_COMPARATOR = new Comparator<ChronoZonedDateTime<?>>(){

        @Override
        public int compare(ChronoZonedDateTime<?> datetime1, ChronoZonedDateTime<?> datetime2) {
            int cmp = Jdk8Methods.compareLongs(datetime1.toEpochSecond(), datetime2.toEpochSecond());
            if (cmp == 0) {
                cmp = Jdk8Methods.compareLongs(datetime1.toLocalTime().toNanoOfDay(), datetime2.toLocalTime().toNanoOfDay());
            }
            return cmp;
        }
    };

    public static Comparator<ChronoZonedDateTime<?>> timeLineOrder() {
        return INSTANT_COMPARATOR;
    }

    public static ChronoZonedDateTime<?> from(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        if (temporal instanceof ChronoZonedDateTime) {
            return (ChronoZonedDateTime)temporal;
        }
        Chronology chrono = temporal.query(TemporalQueries.chronology());
        if (chrono == null) {
            throw new DateTimeException("No Chronology found to create ChronoZonedDateTime: " + temporal.getClass());
        }
        return chrono.zonedDateTime(temporal);
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            if (field2 == ChronoField.INSTANT_SECONDS || field2 == ChronoField.OFFSET_SECONDS) {
                return field2.range();
            }
            return this.toLocalDateTime().range(field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            switch ((ChronoField)field2) {
                case INSTANT_SECONDS: {
                    throw new UnsupportedTemporalTypeException("Field too large for an int: " + field2);
                }
                case OFFSET_SECONDS: {
                    return this.getOffset().getTotalSeconds();
                }
            }
            return this.toLocalDateTime().get(field2);
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
            return this.toLocalDateTime().getLong(field2);
        }
        return field2.getFrom(this);
    }

    public D toLocalDate() {
        return this.toLocalDateTime().toLocalDate();
    }

    public LocalTime toLocalTime() {
        return this.toLocalDateTime().toLocalTime();
    }

    public abstract ChronoLocalDateTime<D> toLocalDateTime();

    public Chronology getChronology() {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology();
    }

    public abstract ZoneOffset getOffset();

    public abstract ZoneId getZone();

    public abstract ChronoZonedDateTime<D> withEarlierOffsetAtOverlap();

    public abstract ChronoZonedDateTime<D> withLaterOffsetAtOverlap();

    public abstract ChronoZonedDateTime<D> withZoneSameLocal(ZoneId var1);

    public abstract ChronoZonedDateTime<D> withZoneSameInstant(ZoneId var1);

    @Override
    public ChronoZonedDateTime<D> with(TemporalAdjuster adjuster) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(super.with(adjuster));
    }

    @Override
    public abstract ChronoZonedDateTime<D> with(TemporalField var1, long var2);

    @Override
    public ChronoZonedDateTime<D> plus(TemporalAmount amount) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(super.plus(amount));
    }

    @Override
    public abstract ChronoZonedDateTime<D> plus(long var1, TemporalUnit var3);

    @Override
    public ChronoZonedDateTime<D> minus(TemporalAmount amount) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(super.minus(amount));
    }

    @Override
    public ChronoZonedDateTime<D> minus(long amountToSubtract, TemporalUnit unit) {
        return ((ChronoLocalDate)this.toLocalDate()).getChronology().ensureChronoZonedDateTime(super.minus(amountToSubtract, unit));
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId() || query == TemporalQueries.zone()) {
            return (R)this.getZone();
        }
        if (query == TemporalQueries.chronology()) {
            return (R)((ChronoLocalDate)this.toLocalDate()).getChronology();
        }
        if (query == TemporalQueries.precision()) {
            return (R)ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.offset()) {
            return (R)this.getOffset();
        }
        if (query == TemporalQueries.localDate()) {
            return (R)LocalDate.ofEpochDay(((ChronoLocalDate)this.toLocalDate()).toEpochDay());
        }
        if (query == TemporalQueries.localTime()) {
            return (R)this.toLocalTime();
        }
        return super.query(query);
    }

    public String format(DateTimeFormatter formatter) {
        Jdk8Methods.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public Instant toInstant() {
        return Instant.ofEpochSecond(this.toEpochSecond(), this.toLocalTime().getNano());
    }

    public long toEpochSecond() {
        long epochDay = ((ChronoLocalDate)this.toLocalDate()).toEpochDay();
        long secs = epochDay * 86400L + (long)this.toLocalTime().toSecondOfDay();
        return secs -= (long)this.getOffset().getTotalSeconds();
    }

    @Override
    public int compareTo(ChronoZonedDateTime<?> other) {
        int cmp = Jdk8Methods.compareLongs(this.toEpochSecond(), other.toEpochSecond());
        if (cmp == 0 && (cmp = this.toLocalTime().getNano() - other.toLocalTime().getNano()) == 0 && (cmp = this.toLocalDateTime().compareTo(other.toLocalDateTime())) == 0 && (cmp = this.getZone().getId().compareTo(other.getZone().getId())) == 0) {
            cmp = ((ChronoLocalDate)this.toLocalDate()).getChronology().compareTo(((ChronoLocalDate)other.toLocalDate()).getChronology());
        }
        return cmp;
    }

    public boolean isAfter(ChronoZonedDateTime<?> other) {
        long otherEpochSec;
        long thisEpochSec = this.toEpochSecond();
        return thisEpochSec > (otherEpochSec = other.toEpochSecond()) || thisEpochSec == otherEpochSec && this.toLocalTime().getNano() > other.toLocalTime().getNano();
    }

    public boolean isBefore(ChronoZonedDateTime<?> other) {
        long otherEpochSec;
        long thisEpochSec = this.toEpochSecond();
        return thisEpochSec < (otherEpochSec = other.toEpochSecond()) || thisEpochSec == otherEpochSec && this.toLocalTime().getNano() < other.toLocalTime().getNano();
    }

    public boolean isEqual(ChronoZonedDateTime<?> other) {
        return this.toEpochSecond() == other.toEpochSecond() && this.toLocalTime().getNano() == other.toLocalTime().getNano();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoZonedDateTime) {
            return this.compareTo((ChronoZonedDateTime)obj) == 0;
        }
        return false;
    }

    public int hashCode() {
        return this.toLocalDateTime().hashCode() ^ this.getOffset().hashCode() ^ Integer.rotateLeft(this.getZone().hashCode(), 3);
    }

    public String toString() {
        String str = this.toLocalDateTime().toString() + this.getOffset().toString();
        if (this.getOffset() != this.getZone()) {
            str = str + '[' + this.getZone().toString() + ']';
        }
        return str;
    }
}

