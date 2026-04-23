/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.BucketBoundaries;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_BucketBoundaries
extends BucketBoundaries {
    private final List<Double> boundaries;

    AutoValue_BucketBoundaries(List<Double> boundaries) {
        if (boundaries == null) {
            throw new NullPointerException("Null boundaries");
        }
        this.boundaries = boundaries;
    }

    @Override
    public List<Double> getBoundaries() {
        return this.boundaries;
    }

    public String toString() {
        return "BucketBoundaries{boundaries=" + this.boundaries + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BucketBoundaries) {
            BucketBoundaries that = (BucketBoundaries)o;
            return this.boundaries.equals(that.getBoundaries());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.boundaries.hashCode();
    }
}

