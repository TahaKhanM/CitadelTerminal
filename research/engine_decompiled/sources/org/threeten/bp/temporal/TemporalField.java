/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import java.util.Locale;
import java.util.Map;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

public interface TemporalField {
    public TemporalUnit getBaseUnit();

    public TemporalUnit getRangeUnit();

    public ValueRange range();

    public boolean isDateBased();

    public boolean isTimeBased();

    public boolean isSupportedBy(TemporalAccessor var1);

    public ValueRange rangeRefinedBy(TemporalAccessor var1);

    public long getFrom(TemporalAccessor var1);

    public String getDisplayName(Locale var1);

    public <R extends Temporal> R adjustInto(R var1, long var2);

    public TemporalAccessor resolve(Map<TemporalField, Long> var1, TemporalAccessor var2, ResolverStyle var3);
}

