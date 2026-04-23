/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.Status;
import io.opencensus.trace.export.SampledSpanStore;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SampledSpanStore_PerSpanNameSummary
extends SampledSpanStore.PerSpanNameSummary {
    private final Map<SampledSpanStore.LatencyBucketBoundaries, Integer> numbersOfLatencySampledSpans;
    private final Map<Status.CanonicalCode, Integer> numbersOfErrorSampledSpans;

    AutoValue_SampledSpanStore_PerSpanNameSummary(Map<SampledSpanStore.LatencyBucketBoundaries, Integer> numbersOfLatencySampledSpans, Map<Status.CanonicalCode, Integer> numbersOfErrorSampledSpans) {
        if (numbersOfLatencySampledSpans == null) {
            throw new NullPointerException("Null numbersOfLatencySampledSpans");
        }
        this.numbersOfLatencySampledSpans = numbersOfLatencySampledSpans;
        if (numbersOfErrorSampledSpans == null) {
            throw new NullPointerException("Null numbersOfErrorSampledSpans");
        }
        this.numbersOfErrorSampledSpans = numbersOfErrorSampledSpans;
    }

    @Override
    public Map<SampledSpanStore.LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans() {
        return this.numbersOfLatencySampledSpans;
    }

    @Override
    public Map<Status.CanonicalCode, Integer> getNumbersOfErrorSampledSpans() {
        return this.numbersOfErrorSampledSpans;
    }

    public String toString() {
        return "PerSpanNameSummary{numbersOfLatencySampledSpans=" + this.numbersOfLatencySampledSpans + ", numbersOfErrorSampledSpans=" + this.numbersOfErrorSampledSpans + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SampledSpanStore.PerSpanNameSummary) {
            SampledSpanStore.PerSpanNameSummary that = (SampledSpanStore.PerSpanNameSummary)o;
            return this.numbersOfLatencySampledSpans.equals(that.getNumbersOfLatencySampledSpans()) && this.numbersOfErrorSampledSpans.equals(that.getNumbersOfErrorSampledSpans());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.numbersOfLatencySampledSpans.hashCode();
        h *= 1000003;
        return h ^= this.numbersOfErrorSampledSpans.hashCode();
    }
}

