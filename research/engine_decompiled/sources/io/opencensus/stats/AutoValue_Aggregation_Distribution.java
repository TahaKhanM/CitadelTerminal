/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import io.opencensus.stats.BucketBoundaries;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Aggregation_Distribution
extends Aggregation.Distribution {
    private final BucketBoundaries bucketBoundaries;

    AutoValue_Aggregation_Distribution(BucketBoundaries bucketBoundaries) {
        if (bucketBoundaries == null) {
            throw new NullPointerException("Null bucketBoundaries");
        }
        this.bucketBoundaries = bucketBoundaries;
    }

    @Override
    public BucketBoundaries getBucketBoundaries() {
        return this.bucketBoundaries;
    }

    public String toString() {
        return "Distribution{bucketBoundaries=" + this.bucketBoundaries + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Aggregation.Distribution) {
            Aggregation.Distribution that = (Aggregation.Distribution)o;
            return this.bucketBoundaries.equals(that.getBucketBoundaries());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.bucketBoundaries.hashCode();
    }
}

