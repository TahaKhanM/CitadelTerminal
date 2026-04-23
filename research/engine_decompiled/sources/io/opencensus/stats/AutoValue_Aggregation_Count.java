/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Aggregation_Count
extends Aggregation.Count {
    AutoValue_Aggregation_Count() {
    }

    public String toString() {
        return "Count{}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof Aggregation.Count;
    }

    public int hashCode() {
        int h = 1;
        return h;
    }
}

