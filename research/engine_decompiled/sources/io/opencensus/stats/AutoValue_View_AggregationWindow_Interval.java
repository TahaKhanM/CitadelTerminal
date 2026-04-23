/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.stats.View;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_View_AggregationWindow_Interval
extends View.AggregationWindow.Interval {
    private final Duration duration;

    AutoValue_View_AggregationWindow_Interval(Duration duration) {
        if (duration == null) {
            throw new NullPointerException("Null duration");
        }
        this.duration = duration;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    public String toString() {
        return "Interval{duration=" + this.duration + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof View.AggregationWindow.Interval) {
            View.AggregationWindow.Interval that = (View.AggregationWindow.Interval)o;
            return this.duration.equals(that.getDuration());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.duration.hashCode();
    }
}

