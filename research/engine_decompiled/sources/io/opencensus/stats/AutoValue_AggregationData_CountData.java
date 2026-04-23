/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AggregationData_CountData
extends AggregationData.CountData {
    private final long count;

    AutoValue_AggregationData_CountData(long count2) {
        this.count = count2;
    }

    @Override
    public long getCount() {
        return this.count;
    }

    public String toString() {
        return "CountData{count=" + this.count + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AggregationData.CountData) {
            AggregationData.CountData that = (AggregationData.CountData)o;
            return this.count == that.getCount();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (this.count >>> 32 ^ this.count));
        return h;
    }
}

