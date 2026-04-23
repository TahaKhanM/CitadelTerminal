/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;

public interface TemporalAccessor {
    public boolean isSupported(TemporalField var1);

    public ValueRange range(TemporalField var1);

    public int get(TemporalField var1);

    public long getLong(TemporalField var1);

    public <R> R query(TemporalQuery<R> var1);
}

