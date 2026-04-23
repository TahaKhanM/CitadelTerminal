/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Status;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_EndSpanOptions
extends EndSpanOptions {
    private final boolean sampleToLocalSpanStore;
    private final Status status;

    private AutoValue_EndSpanOptions(boolean sampleToLocalSpanStore, @Nullable Status status) {
        this.sampleToLocalSpanStore = sampleToLocalSpanStore;
        this.status = status;
    }

    @Override
    public boolean getSampleToLocalSpanStore() {
        return this.sampleToLocalSpanStore;
    }

    @Override
    @Nullable
    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        return "EndSpanOptions{sampleToLocalSpanStore=" + this.sampleToLocalSpanStore + ", status=" + this.status + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof EndSpanOptions) {
            EndSpanOptions that = (EndSpanOptions)o;
            return this.sampleToLocalSpanStore == that.getSampleToLocalSpanStore() && (this.status == null ? that.getStatus() == null : this.status.equals(that.getStatus()));
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.sampleToLocalSpanStore ? 1231 : 1237;
        h *= 1000003;
        return h ^= this.status == null ? 0 : this.status.hashCode();
    }

    static final class Builder
    extends EndSpanOptions.Builder {
        private Boolean sampleToLocalSpanStore;
        private Status status;

        Builder() {
        }

        @Override
        public EndSpanOptions.Builder setSampleToLocalSpanStore(boolean sampleToLocalSpanStore) {
            this.sampleToLocalSpanStore = sampleToLocalSpanStore;
            return this;
        }

        @Override
        public EndSpanOptions.Builder setStatus(@Nullable Status status) {
            this.status = status;
            return this;
        }

        @Override
        public EndSpanOptions build() {
            String missing = "";
            if (this.sampleToLocalSpanStore == null) {
                missing = missing + " sampleToLocalSpanStore";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_EndSpanOptions(this.sampleToLocalSpanStore, this.status);
        }
    }
}

