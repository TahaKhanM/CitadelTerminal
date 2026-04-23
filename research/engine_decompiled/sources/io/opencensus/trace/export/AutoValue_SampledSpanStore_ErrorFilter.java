/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.Status;
import io.opencensus.trace.export.SampledSpanStore;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SampledSpanStore_ErrorFilter
extends SampledSpanStore.ErrorFilter {
    private final String spanName;
    private final Status.CanonicalCode canonicalCode;
    private final int maxSpansToReturn;

    AutoValue_SampledSpanStore_ErrorFilter(String spanName, @Nullable Status.CanonicalCode canonicalCode, int maxSpansToReturn) {
        if (spanName == null) {
            throw new NullPointerException("Null spanName");
        }
        this.spanName = spanName;
        this.canonicalCode = canonicalCode;
        this.maxSpansToReturn = maxSpansToReturn;
    }

    @Override
    public String getSpanName() {
        return this.spanName;
    }

    @Override
    @Nullable
    public Status.CanonicalCode getCanonicalCode() {
        return this.canonicalCode;
    }

    @Override
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    public String toString() {
        return "ErrorFilter{spanName=" + this.spanName + ", canonicalCode=" + (Object)((Object)this.canonicalCode) + ", maxSpansToReturn=" + this.maxSpansToReturn + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SampledSpanStore.ErrorFilter) {
            SampledSpanStore.ErrorFilter that = (SampledSpanStore.ErrorFilter)o;
            return this.spanName.equals(that.getSpanName()) && (this.canonicalCode == null ? that.getCanonicalCode() == null : this.canonicalCode.equals((Object)that.getCanonicalCode())) && this.maxSpansToReturn == that.getMaxSpansToReturn();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.spanName.hashCode();
        h *= 1000003;
        h ^= this.canonicalCode == null ? 0 : this.canonicalCode.hashCode();
        h *= 1000003;
        return h ^= this.maxSpansToReturn;
    }
}

