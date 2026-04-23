/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.FlowControlSettings;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

final class AutoValue_BatchingSettings
extends BatchingSettings {
    private final Long elementCountThreshold;
    private final Long requestByteThreshold;
    private final Duration delayThreshold;
    private final Boolean isEnabled;
    private final FlowControlSettings flowControlSettings;

    private AutoValue_BatchingSettings(@Nullable Long elementCountThreshold, @Nullable Long requestByteThreshold, @Nullable Duration delayThreshold, Boolean isEnabled, FlowControlSettings flowControlSettings) {
        this.elementCountThreshold = elementCountThreshold;
        this.requestByteThreshold = requestByteThreshold;
        this.delayThreshold = delayThreshold;
        if (isEnabled == null) {
            throw new NullPointerException("Null isEnabled");
        }
        this.isEnabled = isEnabled;
        if (flowControlSettings == null) {
            throw new NullPointerException("Null flowControlSettings");
        }
        this.flowControlSettings = flowControlSettings;
    }

    @Override
    @Nullable
    public Long getElementCountThreshold() {
        return this.elementCountThreshold;
    }

    @Override
    @Nullable
    public Long getRequestByteThreshold() {
        return this.requestByteThreshold;
    }

    @Override
    @Nullable
    public Duration getDelayThreshold() {
        return this.delayThreshold;
    }

    @Override
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    @Override
    public FlowControlSettings getFlowControlSettings() {
        return this.flowControlSettings;
    }

    public String toString() {
        return "BatchingSettings{elementCountThreshold=" + this.elementCountThreshold + ", requestByteThreshold=" + this.requestByteThreshold + ", delayThreshold=" + this.delayThreshold + ", isEnabled=" + this.isEnabled + ", flowControlSettings=" + this.flowControlSettings + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BatchingSettings) {
            BatchingSettings that = (BatchingSettings)o;
            return (this.elementCountThreshold == null ? that.getElementCountThreshold() == null : this.elementCountThreshold.equals(that.getElementCountThreshold())) && (this.requestByteThreshold == null ? that.getRequestByteThreshold() == null : this.requestByteThreshold.equals(that.getRequestByteThreshold())) && (this.delayThreshold == null ? that.getDelayThreshold() == null : this.delayThreshold.equals(that.getDelayThreshold())) && this.isEnabled.equals(that.getIsEnabled()) && this.flowControlSettings.equals(that.getFlowControlSettings());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.elementCountThreshold == null ? 0 : this.elementCountThreshold.hashCode();
        h *= 1000003;
        h ^= this.requestByteThreshold == null ? 0 : this.requestByteThreshold.hashCode();
        h *= 1000003;
        h ^= this.delayThreshold == null ? 0 : this.delayThreshold.hashCode();
        h *= 1000003;
        h ^= this.isEnabled.hashCode();
        h *= 1000003;
        return h ^= this.flowControlSettings.hashCode();
    }

    static final class Builder
    extends BatchingSettings.Builder {
        private Long elementCountThreshold;
        private Long requestByteThreshold;
        private Duration delayThreshold;
        private Boolean isEnabled;
        private FlowControlSettings flowControlSettings;

        Builder() {
        }

        Builder(BatchingSettings source) {
            this.elementCountThreshold = source.getElementCountThreshold();
            this.requestByteThreshold = source.getRequestByteThreshold();
            this.delayThreshold = source.getDelayThreshold();
            this.isEnabled = source.getIsEnabled();
            this.flowControlSettings = source.getFlowControlSettings();
        }

        @Override
        public BatchingSettings.Builder setElementCountThreshold(@Nullable Long elementCountThreshold) {
            this.elementCountThreshold = elementCountThreshold;
            return this;
        }

        @Override
        public BatchingSettings.Builder setRequestByteThreshold(@Nullable Long requestByteThreshold) {
            this.requestByteThreshold = requestByteThreshold;
            return this;
        }

        @Override
        public BatchingSettings.Builder setDelayThreshold(@Nullable Duration delayThreshold) {
            this.delayThreshold = delayThreshold;
            return this;
        }

        @Override
        public BatchingSettings.Builder setIsEnabled(Boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        @Override
        public BatchingSettings.Builder setFlowControlSettings(FlowControlSettings flowControlSettings) {
            this.flowControlSettings = flowControlSettings;
            return this;
        }

        @Override
        public BatchingSettings autoBuild() {
            String missing = "";
            if (this.isEnabled == null) {
                missing = missing + " isEnabled";
            }
            if (this.flowControlSettings == null) {
                missing = missing + " flowControlSettings";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_BatchingSettings(this.elementCountThreshold, this.requestByteThreshold, this.delayThreshold, this.isEnabled, this.flowControlSettings);
        }
    }
}

