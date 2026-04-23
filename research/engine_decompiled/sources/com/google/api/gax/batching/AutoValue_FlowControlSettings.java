/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import javax.annotation.Nullable;

final class AutoValue_FlowControlSettings
extends FlowControlSettings {
    private final Long maxOutstandingElementCount;
    private final Long maxOutstandingRequestBytes;
    private final FlowController.LimitExceededBehavior limitExceededBehavior;

    private AutoValue_FlowControlSettings(@Nullable Long maxOutstandingElementCount, @Nullable Long maxOutstandingRequestBytes, FlowController.LimitExceededBehavior limitExceededBehavior) {
        this.maxOutstandingElementCount = maxOutstandingElementCount;
        this.maxOutstandingRequestBytes = maxOutstandingRequestBytes;
        if (limitExceededBehavior == null) {
            throw new NullPointerException("Null limitExceededBehavior");
        }
        this.limitExceededBehavior = limitExceededBehavior;
    }

    @Override
    @Nullable
    public Long getMaxOutstandingElementCount() {
        return this.maxOutstandingElementCount;
    }

    @Override
    @Nullable
    public Long getMaxOutstandingRequestBytes() {
        return this.maxOutstandingRequestBytes;
    }

    @Override
    public FlowController.LimitExceededBehavior getLimitExceededBehavior() {
        return this.limitExceededBehavior;
    }

    public String toString() {
        return "FlowControlSettings{maxOutstandingElementCount=" + this.maxOutstandingElementCount + ", maxOutstandingRequestBytes=" + this.maxOutstandingRequestBytes + ", limitExceededBehavior=" + (Object)((Object)this.limitExceededBehavior) + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FlowControlSettings) {
            FlowControlSettings that = (FlowControlSettings)o;
            return (this.maxOutstandingElementCount == null ? that.getMaxOutstandingElementCount() == null : this.maxOutstandingElementCount.equals(that.getMaxOutstandingElementCount())) && (this.maxOutstandingRequestBytes == null ? that.getMaxOutstandingRequestBytes() == null : this.maxOutstandingRequestBytes.equals(that.getMaxOutstandingRequestBytes())) && this.limitExceededBehavior.equals((Object)that.getLimitExceededBehavior());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.maxOutstandingElementCount == null ? 0 : this.maxOutstandingElementCount.hashCode();
        h *= 1000003;
        h ^= this.maxOutstandingRequestBytes == null ? 0 : this.maxOutstandingRequestBytes.hashCode();
        h *= 1000003;
        return h ^= this.limitExceededBehavior.hashCode();
    }

    static final class Builder
    extends FlowControlSettings.Builder {
        private Long maxOutstandingElementCount;
        private Long maxOutstandingRequestBytes;
        private FlowController.LimitExceededBehavior limitExceededBehavior;

        Builder() {
        }

        Builder(FlowControlSettings source) {
            this.maxOutstandingElementCount = source.getMaxOutstandingElementCount();
            this.maxOutstandingRequestBytes = source.getMaxOutstandingRequestBytes();
            this.limitExceededBehavior = source.getLimitExceededBehavior();
        }

        @Override
        public FlowControlSettings.Builder setMaxOutstandingElementCount(@Nullable Long maxOutstandingElementCount) {
            this.maxOutstandingElementCount = maxOutstandingElementCount;
            return this;
        }

        @Override
        public FlowControlSettings.Builder setMaxOutstandingRequestBytes(@Nullable Long maxOutstandingRequestBytes) {
            this.maxOutstandingRequestBytes = maxOutstandingRequestBytes;
            return this;
        }

        @Override
        public FlowControlSettings.Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior limitExceededBehavior) {
            this.limitExceededBehavior = limitExceededBehavior;
            return this;
        }

        @Override
        public FlowControlSettings autoBuild() {
            String missing = "";
            if (this.limitExceededBehavior == null) {
                missing = missing + " limitExceededBehavior";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_FlowControlSettings(this.maxOutstandingElementCount, this.maxOutstandingRequestBytes, this.limitExceededBehavior);
        }
    }
}

