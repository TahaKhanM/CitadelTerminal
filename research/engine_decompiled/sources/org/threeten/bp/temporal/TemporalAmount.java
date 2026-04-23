/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import java.util.List;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalUnit;

public interface TemporalAmount {
    public List<TemporalUnit> getUnits();

    public long get(TemporalUnit var1);

    public Temporal addTo(Temporal var1);

    public Temporal subtractFrom(Temporal var1);
}

