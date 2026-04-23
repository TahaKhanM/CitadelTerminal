/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_RunningSpanStore_Filter
extends RunningSpanStore.Filter {
    private final String spanName;
    private final int maxSpansToReturn;

    AutoValue_RunningSpanStore_Filter(String spanName, int maxSpansToReturn) {
        if (spanName == null) {
            throw new NullPointerException("Null spanName");
        }
        this.spanName = spanName;
        this.maxSpansToReturn = maxSpansToReturn;
    }

    @Override
    public String getSpanName() {
        return this.spanName;
    }

    @Override
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    public String toString() {
        return "Filter{spanName=" + this.spanName + ", maxSpansToReturn=" + this.maxSpansToReturn + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RunningSpanStore.Filter) {
            RunningSpanStore.Filter that = (RunningSpanStore.Filter)o;
            return this.spanName.equals(that.getSpanName()) && this.maxSpansToReturn == that.getMaxSpansToReturn();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.spanName.hashCode();
        h *= 1000003;
        return h ^= this.maxSpansToReturn;
    }
}

