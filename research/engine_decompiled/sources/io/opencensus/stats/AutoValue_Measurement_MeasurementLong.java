/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.stats.Measurement;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Measurement_MeasurementLong
extends Measurement.MeasurementLong {
    private final Measure.MeasureLong measure;
    private final long value;

    AutoValue_Measurement_MeasurementLong(Measure.MeasureLong measure, long value) {
        if (measure == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measure;
        this.value = value;
    }

    @Override
    public Measure.MeasureLong getMeasure() {
        return this.measure;
    }

    @Override
    public long getValue() {
        return this.value;
    }

    public String toString() {
        return "MeasurementLong{measure=" + this.measure + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Measurement.MeasurementLong) {
            Measurement.MeasurementLong that = (Measurement.MeasurementLong)o;
            return this.measure.equals(that.getMeasure()) && this.value == that.getValue();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.measure.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (this.value >>> 32 ^ this.value));
        return h;
    }
}

