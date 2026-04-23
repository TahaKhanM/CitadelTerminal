/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_RunningSpanStore_Summary
extends RunningSpanStore.Summary {
    private final Map<String, RunningSpanStore.PerSpanNameSummary> perSpanNameSummary;

    AutoValue_RunningSpanStore_Summary(Map<String, RunningSpanStore.PerSpanNameSummary> perSpanNameSummary) {
        if (perSpanNameSummary == null) {
            throw new NullPointerException("Null perSpanNameSummary");
        }
        this.perSpanNameSummary = perSpanNameSummary;
    }

    @Override
    public Map<String, RunningSpanStore.PerSpanNameSummary> getPerSpanNameSummary() {
        return this.perSpanNameSummary;
    }

    public String toString() {
        return "Summary{perSpanNameSummary=" + this.perSpanNameSummary + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RunningSpanStore.Summary) {
            RunningSpanStore.Summary that = (RunningSpanStore.Summary)o;
            return this.perSpanNameSummary.equals(that.getPerSpanNameSummary());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.perSpanNameSummary.hashCode();
    }
}

