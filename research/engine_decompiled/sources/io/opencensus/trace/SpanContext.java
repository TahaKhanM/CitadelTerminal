/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import io.opencensus.trace.TraceOptions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class SpanContext {
    private final TraceId traceId;
    private final SpanId spanId;
    private final TraceOptions traceOptions;
    public static final SpanContext INVALID = new SpanContext(TraceId.INVALID, SpanId.INVALID, TraceOptions.DEFAULT);

    public static SpanContext create(TraceId traceId, SpanId spanId, TraceOptions traceOptions) {
        return new SpanContext(traceId, spanId, traceOptions);
    }

    public TraceId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public TraceOptions getTraceOptions() {
        return this.traceOptions;
    }

    public boolean isValid() {
        return this.traceId.isValid() && this.spanId.isValid();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanContext)) {
            return false;
        }
        SpanContext that = (SpanContext)obj;
        return this.traceId.equals(that.traceId) && this.spanId.equals(that.spanId) && this.traceOptions.equals(that.traceOptions);
    }

    public int hashCode() {
        return Objects.hashCode(this.traceId, this.spanId, this.traceOptions);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("traceId", this.traceId).add("spanId", this.spanId).add("traceOptions", this.traceOptions).toString();
    }

    private SpanContext(TraceId traceId, SpanId spanId, TraceOptions traceOptions) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.traceOptions = traceOptions;
    }
}

