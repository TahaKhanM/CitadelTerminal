/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Link;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Status;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class BlankSpan
extends Span {
    public static final BlankSpan INSTANCE = new BlankSpan();

    private BlankSpan() {
        super(SpanContext.INVALID, null);
    }

    @Override
    public void putAttribute(String key, AttributeValue value) {
    }

    @Override
    public void putAttributes(Map<String, AttributeValue> attributes) {
    }

    @Override
    public void addAnnotation(String description, Map<String, AttributeValue> attributes) {
    }

    @Override
    public void addAnnotation(Annotation annotation) {
    }

    @Override
    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
    }

    @Override
    public void addMessageEvent(MessageEvent messageEvent) {
    }

    @Override
    public void addLink(Link link) {
    }

    @Override
    public void setStatus(Status status) {
    }

    @Override
    public void end(EndSpanOptions options) {
    }

    public String toString() {
        return "BlankSpan";
    }
}

