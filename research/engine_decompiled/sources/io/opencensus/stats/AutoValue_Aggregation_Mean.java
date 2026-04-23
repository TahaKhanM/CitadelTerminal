/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Aggregation_Mean
extends Aggregation.Mean {
    AutoValue_Aggregation_Mean() {
    }

    public String toString() {
        return "Mean{}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof Aggregation.Mean;
    }

    public int hashCode() {
        int h = 1;
        return h;
    }
}

