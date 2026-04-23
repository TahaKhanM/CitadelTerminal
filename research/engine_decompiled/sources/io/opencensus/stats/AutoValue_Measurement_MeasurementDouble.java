/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.stats.Measurement;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Measurement_MeasurementDouble
extends Measurement.MeasurementDouble {
    private final Measure.MeasureDouble measure;
    private final double value;

    AutoValue_Measurement_MeasurementDouble(Measure.MeasureDouble measure, double value) {
        if (measure == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measure;
        this.value = value;
    }

    @Override
    public Measure.MeasureDouble getMeasure() {
        return this.measure;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public String toString() {
        return "MeasurementDouble{measure=" + this.measure + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Measurement.MeasurementDouble) {
            Measurement.MeasurementDouble that = (Measurement.MeasurementDouble)o;
            return this.measure.equals(that.getMeasure()) && Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.getValue());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.measure.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.value) >>> 32 ^ Double.doubleToLongBits(this.value)));
        return h;
    }
}

