/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AutoValue_EndSpanOptions;
import io.opencensus.trace.Status;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class EndSpanOptions {
    public static final EndSpanOptions DEFAULT = EndSpanOptions.builder().build();

    public static Builder builder() {
        return new AutoValue_EndSpanOptions.Builder().setSampleToLocalSpanStore(false);
    }

    public abstract boolean getSampleToLocalSpanStore();

    @Nullable
    public abstract Status getStatus();

    EndSpanOptions() {
    }

    public static abstract class Builder {
        public abstract Builder setStatus(Status var1);

        public abstract Builder setSampleToLocalSpanStore(boolean var1);

        public abstract EndSpanOptions build();

        Builder() {
        }
    }
}

