/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AggregationData_DistributionData
extends AggregationData.DistributionData {
    private final double mean;
    private final long count;
    private final double min;
    private final double max;
    private final double sumOfSquaredDeviations;
    private final List<Long> bucketCounts;

    AutoValue_AggregationData_DistributionData(double mean, long count2, double min2, double max2, double sumOfSquaredDeviations, List<Long> bucketCounts) {
        this.mean = mean;
        this.count = count2;
        this.min = min2;
        this.max = max2;
        this.sumOfSquaredDeviations = sumOfSquaredDeviations;
        if (bucketCounts == null) {
            throw new NullPointerException("Null bucketCounts");
        }
        this.bucketCounts = bucketCounts;
    }

    @Override
    public double getMean() {
        return this.mean;
    }

    @Override
    public long getCount() {
        return this.count;
    }

    @Override
    public double getMin() {
        return this.min;
    }

    @Override
    public double getMax() {
        return this.max;
    }

    @Override
    public double getSumOfSquaredDeviations() {
        return this.sumOfSquaredDeviations;
    }

    @Override
    public List<Long> getBucketCounts() {
        return this.bucketCounts;
    }

    public String toString() {
        return "DistributionData{mean=" + this.mean + ", count=" + this.count + ", min=" + this.min + ", max=" + this.max + ", sumOfSquaredDeviations=" + this.sumOfSquaredDeviations + ", bucketCounts=" + this.bucketCounts + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AggregationData.DistributionData) {
            AggregationData.DistributionData that = (AggregationData.DistributionData)o;
            return Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(that.getMean()) && this.count == that.getCount() && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(that.getMin()) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(that.getMax()) && Double.doubleToLongBits(this.sumOfSquaredDeviations) == Double.doubleToLongBits(that.getSumOfSquaredDeviations()) && this.bucketCounts.equals(that.getBucketCounts());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.mean) >>> 32 ^ Double.doubleToLongBits(this.mean)));
        h *= 1000003;
        h = (int)((long)h ^ (this.count >>> 32 ^ this.count));
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.min) >>> 32 ^ Double.doubleToLongBits(this.min)));
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.max) >>> 32 ^ Double.doubleToLongBits(this.max)));
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.sumOfSquaredDeviations) >>> 32 ^ Double.doubleToLongBits(this.sumOfSquaredDeviations)));
        h *= 1000003;
        return h ^= this.bucketCounts.hashCode();
    }
}

