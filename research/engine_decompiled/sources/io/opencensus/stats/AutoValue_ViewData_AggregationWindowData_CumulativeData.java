/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.common.Timestamp;
import io.opencensus.stats.ViewData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_ViewData_AggregationWindowData_CumulativeData
extends ViewData.AggregationWindowData.CumulativeData {
    private final Timestamp start;
    private final Timestamp end;

    AutoValue_ViewData_AggregationWindowData_CumulativeData(Timestamp start, Timestamp end) {
        if (start == null) {
            throw new NullPointerException("Null start");
        }
        this.start = start;
        if (end == null) {
            throw new NullPointerException("Null end");
        }
        this.end = end;
    }

    @Override
    public Timestamp getStart() {
        return this.start;
    }

    @Override
    public Timestamp getEnd() {
        return this.end;
    }

    public String toString() {
        return "CumulativeData{start=" + this.start + ", end=" + this.end + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ViewData.AggregationWindowData.CumulativeData) {
            ViewData.AggregationWindowData.CumulativeData that = (ViewData.AggregationWindowData.CumulativeData)o;
            return this.start.equals(that.getStart()) && this.end.equals(that.getEnd());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.start.hashCode();
        h *= 1000003;
        return h ^= this.end.hashCode();
    }
}

