/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Link;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_Link
extends Link {
    private final TraceId traceId;
    private final SpanId spanId;
    private final Link.Type type;
    private final Map<String, AttributeValue> attributes;

    AutoValue_Link(TraceId traceId, SpanId spanId, Link.Type type, Map<String, AttributeValue> attributes) {
        if (traceId == null) {
            throw new NullPointerException("Null traceId");
        }
        this.traceId = traceId;
        if (spanId == null) {
            throw new NullPointerException("Null spanId");
        }
        this.spanId = spanId;
        if (type == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type;
        if (attributes == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = attributes;
    }

    @Override
    public TraceId getTraceId() {
        return this.traceId;
    }

    @Override
    public SpanId getSpanId() {
        return this.spanId;
    }

    @Override
    public Link.Type getType() {
        return this.type;
    }

    @Override
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return "Link{traceId=" + this.traceId + ", spanId=" + this.spanId + ", type=" + (Object)((Object)this.type) + ", attributes=" + this.attributes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Link) {
            Link that = (Link)o;
            return this.traceId.equals(that.getTraceId()) && this.spanId.equals(that.getSpanId()) && this.type.equals((Object)that.getType()) && this.attributes.equals(that.getAttributes());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.traceId.hashCode();
        h *= 1000003;
        h ^= this.spanId.hashCode();
        h *= 1000003;
        h ^= this.type.hashCode();
        h *= 1000003;
        return h ^= this.attributes.hashCode();
    }
}

