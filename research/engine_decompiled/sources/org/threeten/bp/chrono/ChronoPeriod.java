/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.util.List;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;

public abstract class ChronoPeriod
implements TemporalAmount {
    public static ChronoPeriod between(ChronoLocalDate startDateInclusive, ChronoLocalDate endDateExclusive) {
        Jdk8Methods.requireNonNull(startDateInclusive, "startDateInclusive");
        Jdk8Methods.requireNonNull(endDateExclusive, "endDateExclusive");
        return startDateInclusive.until(endDateExclusive);
    }

    @Override
    public abstract long get(TemporalUnit var1);

    @Override
    public abstract List<TemporalUnit> getUnits();

    public abstract Chronology getChronology();

    public boolean isZero() {
        for (TemporalUnit unit : this.getUnits()) {
            if (this.get(unit) == 0L) continue;
            return false;
        }
        return true;
    }

    public boolean isNegative() {
        for (TemporalUnit unit : this.getUnits()) {
            if (this.get(unit) >= 0L) continue;
            return true;
        }
        return false;
    }

    public abstract ChronoPeriod plus(TemporalAmount var1);

    public abstract ChronoPeriod minus(TemporalAmount var1);

    public abstract ChronoPeriod multipliedBy(int var1);

    public ChronoPeriod negated() {
        return this.multipliedBy(-1);
    }

    public abstract ChronoPeriod normalized();

    @Override
    public abstract Temporal addTo(Temporal var1);

    @Override
    public abstract Temporal subtractFrom(Temporal var1);

    public abstract boolean equals(Object var1);

    public abstract int hashCode();

    public abstract String toString();
}

