/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

final class ChronoPeriodImpl
extends ChronoPeriod
implements Serializable {
    private static final long serialVersionUID = 275618735781L;
    private final Chronology chronology;
    private final int years;
    private final int months;
    private final int days;

    public ChronoPeriodImpl(Chronology chronology, int years, int months, int days) {
        this.chronology = chronology;
        this.years = years;
        this.months = months;
        this.days = days;
    }

    @Override
    public long get(TemporalUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return this.years;
        }
        if (unit == ChronoUnit.MONTHS) {
            return this.months;
        }
        if (unit == ChronoUnit.DAYS) {
            return this.days;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    }

    @Override
    public Chronology getChronology() {
        return this.chronology;
    }

    @Override
    public ChronoPeriod plus(TemporalAmount amountToAdd) {
        ChronoPeriodImpl amount;
        if (amountToAdd instanceof ChronoPeriodImpl && (amount = (ChronoPeriodImpl)amountToAdd).getChronology().equals(this.getChronology())) {
            return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeAdd(this.years, amount.years), Jdk8Methods.safeAdd(this.months, amount.months), Jdk8Methods.safeAdd(this.days, amount.days));
        }
        throw new DateTimeException("Unable to add amount: " + amountToAdd);
    }

    @Override
    public ChronoPeriod minus(TemporalAmount amountToSubtract) {
        ChronoPeriodImpl amount;
        if (amountToSubtract instanceof ChronoPeriodImpl && (amount = (ChronoPeriodImpl)amountToSubtract).getChronology().equals(this.getChronology())) {
            return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeSubtract(this.years, amount.years), Jdk8Methods.safeSubtract(this.months, amount.months), Jdk8Methods.safeSubtract(this.days, amount.days));
        }
        throw new DateTimeException("Unable to subtract amount: " + amountToSubtract);
    }

    @Override
    public ChronoPeriod multipliedBy(int scalar) {
        return new ChronoPeriodImpl(this.chronology, Jdk8Methods.safeMultiply(this.years, scalar), Jdk8Methods.safeMultiply(this.months, scalar), Jdk8Methods.safeMultiply(this.days, scalar));
    }

    @Override
    public ChronoPeriod normalized() {
        if (this.chronology.range(ChronoField.MONTH_OF_YEAR).isFixed()) {
            long monthLength = this.chronology.range(ChronoField.MONTH_OF_YEAR).getMaximum() - this.chronology.range(ChronoField.MONTH_OF_YEAR).getMinimum() + 1L;
            long total2 = (long)this.years * monthLength + (long)this.months;
            int years = Jdk8Methods.safeToInt(total2 / monthLength);
            int months = Jdk8Methods.safeToInt(total2 % monthLength);
            return new ChronoPeriodImpl(this.chronology, years, months, this.days);
        }
        return this;
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology temporalChrono = temporal.query(TemporalQueries.chronology());
        if (temporalChrono != null && !this.chronology.equals(temporalChrono)) {
            throw new DateTimeException("Invalid chronology, required: " + this.chronology.getId() + ", but was: " + temporalChrono.getId());
        }
        if (this.years != 0) {
            temporal = temporal.plus(this.years, ChronoUnit.YEARS);
        }
        if (this.months != 0) {
            temporal = temporal.plus(this.months, ChronoUnit.MONTHS);
        }
        if (this.days != 0) {
            temporal = temporal.plus(this.days, ChronoUnit.DAYS);
        }
        return temporal;
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology temporalChrono = temporal.query(TemporalQueries.chronology());
        if (temporalChrono != null && !this.chronology.equals(temporalChrono)) {
            throw new DateTimeException("Invalid chronology, required: " + this.chronology.getId() + ", but was: " + temporalChrono.getId());
        }
        if (this.years != 0) {
            temporal = temporal.minus(this.years, ChronoUnit.YEARS);
        }
        if (this.months != 0) {
            temporal = temporal.minus(this.months, ChronoUnit.MONTHS);
        }
        if (this.days != 0) {
            temporal = temporal.minus(this.days, ChronoUnit.DAYS);
        }
        return temporal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl other = (ChronoPeriodImpl)obj;
            return this.years == other.years && this.months == other.months && this.days == other.days && this.chronology.equals(other.chronology);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.chronology.hashCode() + Integer.rotateLeft(this.years, 16) + Integer.rotateLeft(this.months, 8) + this.days;
    }

    @Override
    public String toString() {
        if (this.isZero()) {
            return this.chronology + " P0D";
        }
        StringBuilder buf = new StringBuilder();
        buf.append(this.chronology).append(' ').append('P');
        if (this.years != 0) {
            buf.append(this.years).append('Y');
        }
        if (this.months != 0) {
            buf.append(this.months).append('M');
        }
        if (this.days != 0) {
            buf.append(this.days).append('D');
        }
        return buf.toString();
    }
}

