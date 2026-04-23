/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.SampledSpanStore;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SampledSpanStore_LatencyFilter
extends SampledSpanStore.LatencyFilter {
    private final String spanName;
    private final long latencyLowerNs;
    private final long latencyUpperNs;
    private final int maxSpansToReturn;

    AutoValue_SampledSpanStore_LatencyFilter(String spanName, long latencyLowerNs, long latencyUpperNs, int maxSpansToReturn) {
        if (spanName == null) {
            throw new NullPointerException("Null spanName");
        }
        this.spanName = spanName;
        this.latencyLowerNs = latencyLowerNs;
        this.latencyUpperNs = latencyUpperNs;
        this.maxSpansToReturn = maxSpansToReturn;
    }

    @Override
    public String getSpanName() {
        return this.spanName;
    }

    @Override
    public long getLatencyLowerNs() {
        return this.latencyLowerNs;
    }

    @Override
    public long getLatencyUpperNs() {
        return this.latencyUpperNs;
    }

    @Override
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    public String toString() {
        return "LatencyFilter{spanName=" + this.spanName + ", latencyLowerNs=" + this.latencyLowerNs + ", latencyUpperNs=" + this.latencyUpperNs + ", maxSpansToReturn=" + this.maxSpansToReturn + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SampledSpanStore.LatencyFilter) {
            SampledSpanStore.LatencyFilter that = (SampledSpanStore.LatencyFilter)o;
            return this.spanName.equals(that.getSpanName()) && this.latencyLowerNs == that.getLatencyLowerNs() && this.latencyUpperNs == that.getLatencyUpperNs() && this.maxSpansToReturn == that.getMaxSpansToReturn();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.spanName.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (this.latencyLowerNs >>> 32 ^ this.latencyLowerNs));
        h *= 1000003;
        h = (int)((long)h ^ (this.latencyUpperNs >>> 32 ^ this.latencyUpperNs));
        h *= 1000003;
        return h ^= this.maxSpansToReturn;
    }
}

