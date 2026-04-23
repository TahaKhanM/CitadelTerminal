/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_RunningSpanStore_PerSpanNameSummary
extends RunningSpanStore.PerSpanNameSummary {
    private final int numRunningSpans;

    AutoValue_RunningSpanStore_PerSpanNameSummary(int numRunningSpans) {
        this.numRunningSpans = numRunningSpans;
    }

    @Override
    public int getNumRunningSpans() {
        return this.numRunningSpans;
    }

    public String toString() {
        return "PerSpanNameSummary{numRunningSpans=" + this.numRunningSpans + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RunningSpanStore.PerSpanNameSummary) {
            RunningSpanStore.PerSpanNameSummary that = (RunningSpanStore.PerSpanNameSummary)o;
            return this.numRunningSpans == that.getNumRunningSpans();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.numRunningSpans;
    }
}

