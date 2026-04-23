/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.jdk8;

import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;

public abstract class DefaultInterfaceTemporal
extends DefaultInterfaceTemporalAccessor
implements Temporal {
    @Override
    public Temporal with(TemporalAdjuster adjuster) {
        return adjuster.adjustInto(this);
    }

    @Override
    public Temporal plus(TemporalAmount amount) {
        return amount.addTo(this);
    }

    @Override
    public Temporal minus(TemporalAmount amount) {
        return amount.subtractFrom(this);
    }

    @Override
    public Temporal minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
    }
}

