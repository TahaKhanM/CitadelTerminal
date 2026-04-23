/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.AutoValue_Link;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Link {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();

    public static Link fromSpanContext(SpanContext context, Type type) {
        return new AutoValue_Link(context.getTraceId(), context.getSpanId(), type, EMPTY_ATTRIBUTES);
    }

    public static Link fromSpanContext(SpanContext context, Type type, Map<String, AttributeValue> attributes) {
        return new AutoValue_Link(context.getTraceId(), context.getSpanId(), type, Collections.unmodifiableMap(new HashMap<String, AttributeValue>(attributes)));
    }

    public abstract TraceId getTraceId();

    public abstract SpanId getSpanId();

    public abstract Type getType();

    public abstract Map<String, AttributeValue> getAttributes();

    Link() {
    }

    public static enum Type {
        CHILD_LINKED_SPAN,
        PARENT_LINKED_SPAN;

    }
}

