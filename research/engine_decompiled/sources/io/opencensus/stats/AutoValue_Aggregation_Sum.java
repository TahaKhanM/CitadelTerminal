/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Aggregation_Sum
extends Aggregation.Sum {
    AutoValue_Aggregation_Sum() {
    }

    public String toString() {
        return "Sum{}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof Aggregation.Sum;
    }

    public int hashCode() {
        int h = 1;
        return h;
    }
}

