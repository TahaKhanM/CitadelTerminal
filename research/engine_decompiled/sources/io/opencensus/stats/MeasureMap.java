/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.tags.TagContext;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MeasureMap {
    public abstract MeasureMap put(Measure.MeasureDouble var1, double var2);

    public abstract MeasureMap put(Measure.MeasureLong var1, long var2);

    public abstract void record();

    public abstract void record(TagContext var1);
}

