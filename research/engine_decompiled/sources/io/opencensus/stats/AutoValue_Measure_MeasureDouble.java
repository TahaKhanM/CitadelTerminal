/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Measure;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Measure_MeasureDouble
extends Measure.MeasureDouble {
    private final String name;
    private final String description;
    private final String unit;

    AutoValue_Measure_MeasureDouble(String name, String description, String unit) {
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
        if (description == null) {
            throw new NullPointerException("Null description");
        }
        this.description = description;
        if (unit == null) {
            throw new NullPointerException("Null unit");
        }
        this.unit = unit;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getUnit() {
        return this.unit;
    }

    public String toString() {
        return "MeasureDouble{name=" + this.name + ", description=" + this.description + ", unit=" + this.unit + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Measure.MeasureDouble) {
            Measure.MeasureDouble that = (Measure.MeasureDouble)o;
            return this.name.equals(that.getName()) && this.description.equals(that.getDescription()) && this.unit.equals(that.getUnit());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.name.hashCode();
        h *= 1000003;
        h ^= this.description.hashCode();
        h *= 1000003;
        return h ^= this.unit.hashCode();
    }
}

