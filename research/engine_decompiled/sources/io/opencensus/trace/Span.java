/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import io.opencensus.internal.BaseMessageEventUtil;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Link;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Status;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class Span {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private final SpanContext context;
    private final Set<Options> options;
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));

    protected Span(SpanContext context, @Nullable EnumSet<Options> options) {
        this.context = Preconditions.checkNotNull(context, "context");
        this.options = options == null ? DEFAULT_OPTIONS : Collections.unmodifiableSet(EnumSet.copyOf(options));
        Preconditions.checkArgument(!context.getTraceOptions().isSampled() || this.options.contains((Object)Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    public void putAttribute(String key, AttributeValue value) {
        this.putAttributes(Collections.singletonMap(key, value));
    }

    public void putAttributes(Map<String, AttributeValue> attributes) {
        this.addAttributes(attributes);
    }

    @Deprecated
    public void addAttributes(Map<String, AttributeValue> attributes) {
        this.putAttributes(attributes);
    }

    public final void addAnnotation(String description) {
        this.addAnnotation(description, EMPTY_ATTRIBUTES);
    }

    public abstract void addAnnotation(String var1, Map<String, AttributeValue> var2);

    public abstract void addAnnotation(Annotation var1);

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        this.addMessageEvent(BaseMessageEventUtil.asMessageEvent(networkEvent));
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        this.addNetworkEvent(BaseMessageEventUtil.asNetworkEvent(messageEvent));
    }

    public abstract void addLink(Link var1);

    public void setStatus(Status status) {
    }

    public abstract void end(EndSpanOptions var1);

    public final void end() {
        this.end(EndSpanOptions.DEFAULT);
    }

    public final SpanContext getContext() {
        return this.context;
    }

    public final Set<Options> getOptions() {
        return this.options;
    }

    public static enum Options {
        RECORD_EVENTS;

    }
}

