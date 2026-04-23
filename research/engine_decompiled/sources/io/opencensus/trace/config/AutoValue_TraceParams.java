/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.config;

import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.TraceParams;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_TraceParams
extends TraceParams {
    private final Sampler sampler;
    private final int maxNumberOfAttributes;
    private final int maxNumberOfAnnotations;
    private final int maxNumberOfMessageEvents;
    private final int maxNumberOfLinks;

    private AutoValue_TraceParams(Sampler sampler, int maxNumberOfAttributes, int maxNumberOfAnnotations, int maxNumberOfMessageEvents, int maxNumberOfLinks) {
        this.sampler = sampler;
        this.maxNumberOfAttributes = maxNumberOfAttributes;
        this.maxNumberOfAnnotations = maxNumberOfAnnotations;
        this.maxNumberOfMessageEvents = maxNumberOfMessageEvents;
        this.maxNumberOfLinks = maxNumberOfLinks;
    }

    @Override
    public Sampler getSampler() {
        return this.sampler;
    }

    @Override
    public int getMaxNumberOfAttributes() {
        return this.maxNumberOfAttributes;
    }

    @Override
    public int getMaxNumberOfAnnotations() {
        return this.maxNumberOfAnnotations;
    }

    @Override
    public int getMaxNumberOfMessageEvents() {
        return this.maxNumberOfMessageEvents;
    }

    @Override
    public int getMaxNumberOfLinks() {
        return this.maxNumberOfLinks;
    }

    public String toString() {
        return "TraceParams{sampler=" + this.sampler + ", maxNumberOfAttributes=" + this.maxNumberOfAttributes + ", maxNumberOfAnnotations=" + this.maxNumberOfAnnotations + ", maxNumberOfMessageEvents=" + this.maxNumberOfMessageEvents + ", maxNumberOfLinks=" + this.maxNumberOfLinks + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof TraceParams) {
            TraceParams that = (TraceParams)o;
            return this.sampler.equals(that.getSampler()) && this.maxNumberOfAttributes == that.getMaxNumberOfAttributes() && this.maxNumberOfAnnotations == that.getMaxNumberOfAnnotations() && this.maxNumberOfMessageEvents == that.getMaxNumberOfMessageEvents() && this.maxNumberOfLinks == that.getMaxNumberOfLinks();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.sampler.hashCode();
        h *= 1000003;
        h ^= this.maxNumberOfAttributes;
        h *= 1000003;
        h ^= this.maxNumberOfAnnotations;
        h *= 1000003;
        h ^= this.maxNumberOfMessageEvents;
        h *= 1000003;
        return h ^= this.maxNumberOfLinks;
    }

    @Override
    public TraceParams.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder
    extends TraceParams.Builder {
        private Sampler sampler;
        private Integer maxNumberOfAttributes;
        private Integer maxNumberOfAnnotations;
        private Integer maxNumberOfMessageEvents;
        private Integer maxNumberOfLinks;

        Builder() {
        }

        private Builder(TraceParams source) {
            this.sampler = source.getSampler();
            this.maxNumberOfAttributes = source.getMaxNumberOfAttributes();
            this.maxNumberOfAnnotations = source.getMaxNumberOfAnnotations();
            this.maxNumberOfMessageEvents = source.getMaxNumberOfMessageEvents();
            this.maxNumberOfLinks = source.getMaxNumberOfLinks();
        }

        @Override
        public TraceParams.Builder setSampler(Sampler sampler) {
            if (sampler == null) {
                throw new NullPointerException("Null sampler");
            }
            this.sampler = sampler;
            return this;
        }

        @Override
        public TraceParams.Builder setMaxNumberOfAttributes(int maxNumberOfAttributes) {
            this.maxNumberOfAttributes = maxNumberOfAttributes;
            return this;
        }

        @Override
        public TraceParams.Builder setMaxNumberOfAnnotations(int maxNumberOfAnnotations) {
            this.maxNumberOfAnnotations = maxNumberOfAnnotations;
            return this;
        }

        @Override
        public TraceParams.Builder setMaxNumberOfMessageEvents(int maxNumberOfMessageEvents) {
            this.maxNumberOfMessageEvents = maxNumberOfMessageEvents;
            return this;
        }

        @Override
        public TraceParams.Builder setMaxNumberOfLinks(int maxNumberOfLinks) {
            this.maxNumberOfLinks = maxNumberOfLinks;
            return this;
        }

        @Override
        TraceParams autoBuild() {
            String missing = "";
            if (this.sampler == null) {
                missing = missing + " sampler";
            }
            if (this.maxNumberOfAttributes == null) {
                missing = missing + " maxNumberOfAttributes";
            }
            if (this.maxNumberOfAnnotations == null) {
                missing = missing + " maxNumberOfAnnotations";
            }
            if (this.maxNumberOfMessageEvents == null) {
                missing = missing + " maxNumberOfMessageEvents";
            }
            if (this.maxNumberOfLinks == null) {
                missing = missing + " maxNumberOfLinks";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_TraceParams(this.sampler, this.maxNumberOfAttributes, this.maxNumberOfAnnotations, this.maxNumberOfMessageEvents, this.maxNumberOfLinks);
        }
    }
}

