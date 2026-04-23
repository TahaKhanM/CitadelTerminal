/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AggregationData_SumDataLong
extends AggregationData.SumDataLong {
    private final long sum;

    AutoValue_AggregationData_SumDataLong(long sum2) {
        this.sum = sum2;
    }

    @Override
    public long getSum() {
        return this.sum;
    }

    public String toString() {
        return "SumDataLong{sum=" + this.sum + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AggregationData.SumDataLong) {
            AggregationData.SumDataLong that = (AggregationData.SumDataLong)o;
            return this.sum == that.getSum();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (this.sum >>> 32 ^ this.sum));
        return h;
    }
}

