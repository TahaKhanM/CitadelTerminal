/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.gax.batching.AutoValue_FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

public abstract class FlowControlSettings {
    public static FlowControlSettings getDefaultInstance() {
        return FlowControlSettings.newBuilder().build();
    }

    @Nullable
    public abstract Long getMaxOutstandingElementCount();

    @Nullable
    public abstract Long getMaxOutstandingRequestBytes();

    public abstract FlowController.LimitExceededBehavior getLimitExceededBehavior();

    public Builder toBuilder() {
        return new AutoValue_FlowControlSettings.Builder(this);
    }

    public static Builder newBuilder() {
        return new AutoValue_FlowControlSettings.Builder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Block);
    }

    public static abstract class Builder {
        public abstract Builder setMaxOutstandingElementCount(Long var1);

        public abstract Builder setMaxOutstandingRequestBytes(Long var1);

        public abstract Builder setLimitExceededBehavior(FlowController.LimitExceededBehavior var1);

        abstract FlowControlSettings autoBuild();

        public FlowControlSettings build() {
            FlowControlSettings settings = this.autoBuild();
            Preconditions.checkArgument(settings.getMaxOutstandingElementCount() == null || settings.getMaxOutstandingElementCount() > 0L, "maxOutstandingElementCount limit is disabled by default, but if set it must be set to a value greater than 0.");
            Preconditions.checkArgument(settings.getMaxOutstandingRequestBytes() == null || settings.getMaxOutstandingRequestBytes() > 0L, "maxOutstandingRequestBytes limit is disabled by default, but if set it must be set to a value greater than 0.");
            return settings;
        }
    }
}

