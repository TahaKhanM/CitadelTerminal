/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.AutoValue_BatchingSettings;
import com.google.api.gax.batching.FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

public abstract class BatchingSettings {
    @Nullable
    public abstract Long getElementCountThreshold();

    @Nullable
    public abstract Long getRequestByteThreshold();

    @Nullable
    public abstract Duration getDelayThreshold();

    public abstract Boolean getIsEnabled();

    public abstract FlowControlSettings getFlowControlSettings();

    public static Builder newBuilder() {
        return new AutoValue_BatchingSettings.Builder().setIsEnabled(true).setElementCountThreshold(1L).setRequestByteThreshold(1L).setDelayThreshold(Duration.ofMillis(1L)).setFlowControlSettings(FlowControlSettings.newBuilder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Ignore).build());
    }

    public Builder toBuilder() {
        return new AutoValue_BatchingSettings.Builder(this);
    }

    public static abstract class Builder {
        public abstract Builder setElementCountThreshold(Long var1);

        public abstract Builder setRequestByteThreshold(Long var1);

        public abstract Builder setDelayThreshold(Duration var1);

        public abstract Builder setIsEnabled(Boolean var1);

        public abstract Builder setFlowControlSettings(FlowControlSettings var1);

        abstract BatchingSettings autoBuild();

        public BatchingSettings build() {
            BatchingSettings settings = this.autoBuild();
            Preconditions.checkArgument(settings.getElementCountThreshold() == null || settings.getElementCountThreshold() > 0L, "elementCountThreshold must be either unset or positive");
            Preconditions.checkArgument(settings.getRequestByteThreshold() == null || settings.getRequestByteThreshold() > 0L, "requestByteThreshold must be either unset or positive");
            Preconditions.checkArgument(settings.getDelayThreshold() == null || settings.getDelayThreshold().compareTo(Duration.ZERO) > 0, "delayThreshold must be either unset or positive");
            return settings;
        }
    }
}

