/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;

public interface Temporal
extends TemporalAccessor {
    public boolean isSupported(TemporalUnit var1);

    public Temporal with(TemporalAdjuster var1);

    public Temporal with(TemporalField var1, long var2);

    public Temporal plus(TemporalAmount var1);

    public Temporal plus(long var1, TemporalUnit var3);

    public Temporal minus(TemporalAmount var1);

    public Temporal minus(long var1, TemporalUnit var3);

    public long until(Temporal var1, TemporalUnit var2);
}

