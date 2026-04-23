/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.Duration;
import org.threeten.bp.temporal.Temporal;

public interface TemporalUnit {
    public Duration getDuration();

    public boolean isDurationEstimated();

    public boolean isDateBased();

    public boolean isTimeBased();

    public boolean isSupportedBy(Temporal var1);

    public <R extends Temporal> R addTo(R var1, long var2);

    public long between(Temporal var1, Temporal var2);

    public String toString();
}

