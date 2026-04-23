/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.config;

import com.google.common.base.Preconditions;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.AutoValue_TraceParams;
import io.opencensus.trace.samplers.Samplers;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TraceParams {
    private static final double DEFAULT_PROBABILITY = 1.0E-4;
    private static final Sampler DEFAULT_SAMPLER = Samplers.probabilitySampler(1.0E-4);
    private static final int DEFAULT_SPAN_MAX_NUM_ATTRIBUTES = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_ANNOTATIONS = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_MESSAGE_EVENTS = 128;
    private static final int DEFAULT_SPAN_MAX_NUM_LINKS = 128;
    public static final TraceParams DEFAULT = TraceParams.builder().setSampler(DEFAULT_SAMPLER).setMaxNumberOfAttributes(32).setMaxNumberOfAnnotations(32).setMaxNumberOfMessageEvents(128).setMaxNumberOfLinks(128).build();

    public abstract Sampler getSampler();

    public abstract int getMaxNumberOfAttributes();

    public abstract int getMaxNumberOfAnnotations();

    public abstract int getMaxNumberOfMessageEvents();

    @Deprecated
    public int getMaxNumberOfNetworkEvents() {
        return this.getMaxNumberOfMessageEvents();
    }

    public abstract int getMaxNumberOfLinks();

    private static Builder builder() {
        return new AutoValue_TraceParams.Builder();
    }

    public abstract Builder toBuilder();

    public static abstract class Builder {
        public abstract Builder setSampler(Sampler var1);

        public abstract Builder setMaxNumberOfAttributes(int var1);

        public abstract Builder setMaxNumberOfAnnotations(int var1);

        public abstract Builder setMaxNumberOfMessageEvents(int var1);

        @Deprecated
        public Builder setMaxNumberOfNetworkEvents(int maxNumberOfNetworkEvents) {
            return this.setMaxNumberOfMessageEvents(maxNumberOfNetworkEvents);
        }

        public abstract Builder setMaxNumberOfLinks(int var1);

        abstract TraceParams autoBuild();

        public TraceParams build() {
            TraceParams traceParams = this.autoBuild();
            Preconditions.checkArgument(traceParams.getMaxNumberOfAttributes() > 0, "maxNumberOfAttributes");
            Preconditions.checkArgument(traceParams.getMaxNumberOfAnnotations() > 0, "maxNumberOfAnnotations");
            Preconditions.checkArgument(traceParams.getMaxNumberOfMessageEvents() > 0, "maxNumberOfMessageEvents");
            Preconditions.checkArgument(traceParams.getMaxNumberOfLinks() > 0, "maxNumberOfLinks");
            return traceParams;
        }
    }
}

