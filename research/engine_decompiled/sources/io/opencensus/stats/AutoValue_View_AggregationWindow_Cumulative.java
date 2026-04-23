/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.View;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_View_AggregationWindow_Cumulative
extends View.AggregationWindow.Cumulative {
    AutoValue_View_AggregationWindow_Cumulative() {
    }

    public String toString() {
        return "Cumulative{}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof View.AggregationWindow.Cumulative;
    }

    public int hashCode() {
        int h = 1;
        return h;
    }
}

