/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_AggregationData_SumDataDouble
extends AggregationData.SumDataDouble {
    private final double sum;

    AutoValue_AggregationData_SumDataDouble(double sum2) {
        this.sum = sum2;
    }

    @Override
    public double getSum() {
        return this.sum;
    }

    public String toString() {
        return "SumDataDouble{sum=" + this.sum + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AggregationData.SumDataDouble) {
            AggregationData.SumDataDouble that = (AggregationData.SumDataDouble)o;
            return Double.doubleToLongBits(this.sum) == Double.doubleToLongBits(that.getSum());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.sum) >>> 32 ^ Double.doubleToLongBits(this.sum)));
        return h;
    }
}

