/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AggregationData_MeanData
extends AggregationData.MeanData {
    private final double mean;
    private final long count;

    AutoValue_AggregationData_MeanData(double mean, long count2) {
        this.mean = mean;
        this.count = count2;
    }

    @Override
    public double getMean() {
        return this.mean;
    }

    @Override
    public long getCount() {
        return this.count;
    }

    public String toString() {
        return "MeanData{mean=" + this.mean + ", count=" + this.count + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AggregationData.MeanData) {
            AggregationData.MeanData that = (AggregationData.MeanData)o;
            return Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(that.getMean()) && this.count == that.getCount();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.mean) >>> 32 ^ Double.doubleToLongBits(this.mean)));
        h *= 1000003;
        h = (int)((long)h ^ (this.count >>> 32 ^ this.count));
        return h;
    }
}

