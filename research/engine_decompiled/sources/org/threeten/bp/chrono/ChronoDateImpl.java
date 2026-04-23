/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Serializable;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTimeImpl;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalUnit;

abstract class ChronoDateImpl<D extends ChronoLocalDate>
extends ChronoLocalDate
implements Temporal,
TemporalAdjuster,
Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    ChronoDateImpl() {
    }

    @Override
    public ChronoDateImpl<D> plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit)unit;
            switch (f) {
                case DAYS: {
                    return this.plusDays(amountToAdd);
                }
                case WEEKS: {
                    return this.plusDays(Jdk8Methods.safeMultiply(amountToAdd, 7));
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
            }
            throw new DateTimeException(unit + " not valid for chronology " + this.getChronology().getId());
        }
        return (ChronoDateImpl)this.getChronology().ensureChronoLocalDate(unit.addTo(this, amountToAdd));
    }

    abstract ChronoDateImpl<D> plusYears(long var1);

    abstract ChronoDateImpl<D> plusMonths(long var1);

    ChronoDateImpl<D> plusWeeks(long weeksToAdd) {
        return this.plusDays(Jdk8Methods.safeMultiply(weeksToAdd, 7));
    }

    abstract ChronoDateImpl<D> plusDays(long var1);

    ChronoDateImpl<D> minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
    }

    ChronoDateImpl<D> minusMonths(long monthsToSubtract) {
        return monthsToSubtract == Long.MIN_VALUE ? this.plusMonths(Long.MAX_VALUE).plusMonths(1L) : this.plusMonths(-monthsToSubtract);
    }

    ChronoDateImpl<D> minusWeeks(long weeksToSubtract) {
        return weeksToSubtract == Long.MIN_VALUE ? this.plusWeeks(Long.MAX_VALUE).plusWeeks(1L) : this.plusWeeks(-weeksToSubtract);
    }

    ChronoDateImpl<D> minusDays(long daysToSubtract) {
        return daysToSubtract == Long.MIN_VALUE ? this.plusDays(Long.MAX_VALUE).plusDays(1L) : this.plusDays(-daysToSubtract);
    }

    @Override
    public ChronoLocalDateTime<?> atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        ChronoLocalDate end = this.getChronology().date(endExclusive);
        if (unit instanceof ChronoUnit) {
            return LocalDate.from(this).until(end, unit);
        }
        return unit.between(this, end);
    }

    @Override
    public ChronoPeriod until(ChronoLocalDate endDate) {
        throw new UnsupportedOperationException("Not supported in ThreeTen backport");
    }
}

