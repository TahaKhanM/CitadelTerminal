/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.common.Timestamp;
import io.opencensus.stats.ViewData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_ViewData_AggregationWindowData_IntervalData
extends ViewData.AggregationWindowData.IntervalData {
    private final Timestamp end;

    AutoValue_ViewData_AggregationWindowData_IntervalData(Timestamp end) {
        if (end == null) {
            throw new NullPointerException("Null end");
        }
        this.end = end;
    }

    @Override
    public Timestamp getEnd() {
        return this.end;
    }

    public String toString() {
        return "IntervalData{end=" + this.end + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ViewData.AggregationWindowData.IntervalData) {
            ViewData.AggregationWindowData.IntervalData that = (ViewData.AggregationWindowData.IntervalData)o;
            return this.end.equals(that.getEnd());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.end.hashCode();
    }
}

