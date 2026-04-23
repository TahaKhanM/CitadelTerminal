/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTimeImpl;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

final class ChronoLocalDateTimeImpl<D extends ChronoLocalDate>
extends ChronoLocalDateTime<D>
implements Temporal,
TemporalAdjuster,
Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int MINUTES_PER_DAY = 1440;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_DAY = 86400;
    private static final long MILLIS_PER_DAY = 86400000L;
    private static final long MICROS_PER_DAY = 86400000000L;
    private static final long NANOS_PER_SECOND = 1000000000L;
    private static final long NANOS_PER_MINUTE = 60000000000L;
    private static final long NANOS_PER_HOUR = 3600000000000L;
    private static final long NANOS_PER_DAY = 86400000000000L;
    private final D date;
    private final LocalTime time;

    static <R extends ChronoLocalDate> ChronoLocalDateTimeImpl<R> of(R date, LocalTime time) {
        return new ChronoLocalDateTimeImpl<R>(date, time);
    }

    private ChronoLocalDateTimeImpl(D date, LocalTime time) {
        Jdk8Methods.requireNonNull(date, "date");
        Jdk8Methods.requireNonNull(time, "time");
        this.date = date;
        this.time = time;
    }

    private ChronoLocalDateTimeImpl<D> with(Temporal newDate, LocalTime newTime) {
        if (this.date == newDate && this.time == newTime) {
            return this;
        }
        Object cd = ((ChronoLocalDate)this.date).getChronology().ensureChronoLocalDate(newDate);
        return new ChronoLocalDateTimeImpl(cd, newTime);
    }

    @Override
    public D toLocalDate() {
        return this.date;
    }

    @Override
    public LocalTime toLocalTime() {
        return this.time;
    }

    @Override
    public boolean isSupported(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isDateBased() || field2.isTimeBased();
        }
        return field2 != null && field2.isSupportedBy(this);
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
            return field2.isTimeBased() ? this.time.range(field2) : ((DefaultInterfaceTemporalAccessor)this.date).range(field2);
        }
        return field2.rangeRefinedBy(this);
    }

    @Override
    public int get(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() ? this.time.get(field2) : ((DefaultInterfaceTemporalAccessor)this.date).get(field2);
        }
        return this.range(field2).checkValidIntValue(this.getLong(field2), field2);
    }

    @Override
    public long getLong(TemporalField field2) {
        if (field2 instanceof ChronoField) {
            return field2.isTimeBased() ? this.time.getLong(field2) : this.date.getLong(field2);
        }
        return field2.getFrom(this);
    }

    @Override
    public ChronoLocalDateTimeImpl<D> with(TemporalAdjuster adjuster) {
        if (adjuster instanceof ChronoLocalDate) {
            return this.with((ChronoLocalDate)adjuster, this.time);
        }
        if (adjuster instanceof LocalTime) {
            return this.with((Temporal)this.date, (LocalTime)adjuster);
        }
        if (adjuster instanceof ChronoLocalDateTimeImpl) {
            return ((ChronoLocalDate)this.date).getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl)adjuster);
        }
        return ((ChronoLocalDate)this.date).getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl)adjuster.adjustInto(this));
    }

    @Override
    public ChronoLocalDateTimeImpl<D> with(TemporalField field2, long newValue) {
        if (field2 instanceof ChronoField) {
            if (field2.isTimeBased()) {
                return this.with((Temporal)this.date, this.time.with(field2, newValue));
            }
            return this.with(((ChronoLocalDate)this.date).with(field2, newValue), this.time);
        }
        return ((ChronoLocalDate)this.date).getChronology().ensureChronoLocalDateTime(field2.adjustInto(this, newValue));
    }

    @Override
    public ChronoLocalDateTimeImpl<D> plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case NANOS: {
                    return this.plusNanos(amountToAdd);
                }
                case MICROS: {
                    return super.plusNanos(amountToAdd % 86400000000L * 1000L);
                }
                case MILLIS: {
                    return super.plusNanos(amountToAdd % 86400000L * 1000000L);
                }
                case SECONDS: {
                    return this.plusSeconds(amountToAdd);
                }
                case MINUTES: {
                    return this.plusMinutes(amountToAdd);
                }
                case HOURS: {
                    return this.plusHours(amountToAdd);
                }
                case HALF_DAYS: {
                    return super.plusHours(amountToAdd % 256L * 12L);
                }
            }
            return this.with(((ChronoLocalDate)this.date).plus(amountToAdd, unit), this.time);
        }
        return ((ChronoLocalDate)this.date).getChronology().ensureChronoLocalDateTime(unit.addTo(this, amountToAdd));
    }

    private ChronoLocalDateTimeImpl<D> plusDays(long days) {
        return this.with(((ChronoLocalDate)this.date).plus(days, ChronoUnit.DAYS), this.time);
    }

    private ChronoLocalDateTimeImpl<D> plusHours(long hours) {
        return this.plusWithOverflow(this.date, hours, 0L, 0L, 0L);
    }

    private ChronoLocalDateTimeImpl<D> plusMinutes(long minutes) {
        return this.plusWithOverflow(this.date, 0L, minutes, 0L, 0L);
    }

    ChronoLocalDateTimeImpl<D> plusSeconds(long seconds) {
        return this.plusWithOverflow(this.date, 0L, 0L, seconds, 0L);
    }

    private ChronoLocalDateTimeImpl<D> plusNanos(long nanos) {
        return this.plusWithOverflow(this.date, 0L, 0L, 0L, nanos);
    }

    private ChronoLocalDateTimeImpl<D> plusWithOverflow(D newDate, long hours, long minutes, long seconds, long nanos) {
        if ((hours | minutes | seconds | nanos) == 0L) {
            return this.with((Temporal)newDate, this.time);
        }
        long totDays = nanos / 86400000000000L + seconds / 86400L + minutes / 1440L + hours / 24L;
        long totNanos = nanos % 86400000000000L + seconds % 86400L * 1000000000L + minutes % 1440L * 60000000000L + hours % 24L * 3600000000000L;
        long curNoD = this.time.toNanoOfDay();
        long newNoD = Jdk8Methods.floorMod(totNanos, 86400000000000L);
        LocalTime newTime = newNoD == curNoD ? this.time : LocalTime.ofNanoOfDay(newNoD);
        return this.with(((ChronoLocalDate)newDate).plus(totDays += Jdk8Methods.floorDiv(totNanos += curNoD, 86400000000000L), ChronoUnit.DAYS), newTime);
    }

    @Override
    public ChronoZonedDateTime<D> atZone(ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.ofBest(this, zoneId, null);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        ChronoLocalDateTime<?> end = ((ChronoLocalDate)this.toLocalDate()).getChronology().localDateTime(endExclusive);
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            if (f.isTimeBased()) {
                long amount = end.getLong(ChronoField.EPOCH_DAY) - this.date.getLong(ChronoField.EPOCH_DAY);
                switch (f) {
                    case NANOS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000000000L);
                        break;
                    }
                    case MICROS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000000L);
                        break;
                    }
                    case MILLIS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400000L);
                        break;
                    }
                    case SECONDS: {
                        amount = Jdk8Methods.safeMultiply(amount, 86400);
                        break;
                    }
                    case MINUTES: {
                        amount = Jdk8Methods.safeMultiply(amount, 1440);
                        break;
                    }
                    case HOURS: {
                        amount = Jdk8Methods.safeMultiply(amount, 24);
                        break;
                    }
                    case HALF_DAYS: {
                        amount = Jdk8Methods.safeMultiply(amount, 2);
                    }
                }
                return Jdk8Methods.safeAdd(amount, this.time.until(end.toLocalTime(), unit));
            }
            Object endDate = end.toLocalDate();
            if (end.toLocalTime().isBefore(this.time)) {
                endDate = ((ChronoLocalDate)endDate).minus(1L, ChronoUnit.DAYS);
            }
            return this.date.until((Temporal)endDate, unit);
        }
        return unit.between(this, end);
    }

    private Object writeReplace() {
        return new Ser(12, this);
    }

    void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.date);
        out.writeObject(this.time);
    }

    static ChronoLocalDateTime<?> readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ChronoLocalDate date = (ChronoLocalDate)in.readObject();
        LocalTime time = (LocalTime)in.readObject();
        return date.atTime(time);
    }
}

