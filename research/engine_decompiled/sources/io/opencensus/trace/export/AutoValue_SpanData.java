/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.Status;
import io.opencensus.trace.export.SpanData;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SpanData
extends SpanData {
    private final SpanContext context;
    private final SpanId parentSpanId;
    private final Boolean hasRemoteParent;
    private final String name;
    private final Timestamp startTimestamp;
    private final SpanData.Attributes attributes;
    private final SpanData.TimedEvents<Annotation> annotations;
    private final SpanData.TimedEvents<MessageEvent> messageEvents;
    private final SpanData.Links links;
    private final Integer childSpanCount;
    private final Status status;
    private final Timestamp endTimestamp;

    AutoValue_SpanData(SpanContext context, @Nullable SpanId parentSpanId, @Nullable Boolean hasRemoteParent, String name, Timestamp startTimestamp, SpanData.Attributes attributes, SpanData.TimedEvents<Annotation> annotations, SpanData.TimedEvents<MessageEvent> messageEvents, SpanData.Links links, @Nullable Integer childSpanCount, @Nullable Status status, @Nullable Timestamp endTimestamp) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        this.context = context;
        this.parentSpanId = parentSpanId;
        this.hasRemoteParent = hasRemoteParent;
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
        if (startTimestamp == null) {
            throw new NullPointerException("Null startTimestamp");
        }
        this.startTimestamp = startTimestamp;
        if (attributes == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = attributes;
        if (annotations == null) {
            throw new NullPointerException("Null annotations");
        }
        this.annotations = annotations;
        if (messageEvents == null) {
            throw new NullPointerException("Null messageEvents");
        }
        this.messageEvents = messageEvents;
        if (links == null) {
            throw new NullPointerException("Null links");
        }
        this.links = links;
        this.childSpanCount = childSpanCount;
        this.status = status;
        this.endTimestamp = endTimestamp;
    }

    @Override
    public SpanContext getContext() {
        return this.context;
    }

    @Override
    @Nullable
    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    @Override
    @Nullable
    public Boolean getHasRemoteParent() {
        return this.hasRemoteParent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Timestamp getStartTimestamp() {
        return this.startTimestamp;
    }

    @Override
    public SpanData.Attributes getAttributes() {
        return this.attributes;
    }

    @Override
    public SpanData.TimedEvents<Annotation> getAnnotations() {
        return this.annotations;
    }

    @Override
    public SpanData.TimedEvents<MessageEvent> getMessageEvents() {
        return this.messageEvents;
    }

    @Override
    public SpanData.Links getLinks() {
        return this.links;
    }

    @Override
    @Nullable
    public Integer getChildSpanCount() {
        return this.childSpanCount;
    }

    @Override
    @Nullable
    public Status getStatus() {
        return this.status;
    }

    @Override
    @Nullable
    public Timestamp getEndTimestamp() {
        return this.endTimestamp;
    }

    public String toString() {
        return "SpanData{context=" + this.context + ", parentSpanId=" + this.parentSpanId + ", hasRemoteParent=" + this.hasRemoteParent + ", name=" + this.name + ", startTimestamp=" + this.startTimestamp + ", attributes=" + this.attributes + ", annotations=" + this.annotations + ", messageEvents=" + this.messageEvents + ", links=" + this.links + ", childSpanCount=" + this.childSpanCount + ", status=" + this.status + ", endTimestamp=" + this.endTimestamp + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SpanData) {
            SpanData that = (SpanData)o;
            return this.context.equals(that.getContext()) && (this.parentSpanId == null ? that.getParentSpanId() == null : this.parentSpanId.equals(that.getParentSpanId())) && (this.hasRemoteParent == null ? that.getHasRemoteParent() == null : this.hasRemoteParent.equals(that.getHasRemoteParent())) && this.name.equals(that.getName()) && this.startTimestamp.equals(that.getStartTimestamp()) && this.attributes.equals(that.getAttributes()) && this.annotations.equals(that.getAnnotations()) && this.messageEvents.equals(that.getMessageEvents()) && this.links.equals(that.getLinks()) && (this.childSpanCount == null ? that.getChildSpanCount() == null : this.childSpanCount.equals(that.getChildSpanCount())) && (this.status == null ? that.getStatus() == null : this.status.equals(that.getStatus())) && (this.endTimestamp == null ? that.getEndTimestamp() == null : this.endTimestamp.equals(that.getEndTimestamp()));
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.context.hashCode();
        h *= 1000003;
        h ^= this.parentSpanId == null ? 0 : this.parentSpanId.hashCode();
        h *= 1000003;
        h ^= this.hasRemoteParent == null ? 0 : this.hasRemoteParent.hashCode();
        h *= 1000003;
        h ^= this.name.hashCode();
        h *= 1000003;
        h ^= this.startTimestamp.hashCode();
        h *= 1000003;
        h ^= this.attributes.hashCode();
        h *= 1000003;
        h ^= this.annotations.hashCode();
        h *= 1000003;
        h ^= this.messageEvents.hashCode();
        h *= 1000003;
        h ^= this.links.hashCode();
        h *= 1000003;
        h ^= this.childSpanCount == null ? 0 : this.childSpanCount.hashCode();
        h *= 1000003;
        h ^= this.status == null ? 0 : this.status.hashCode();
        h *= 1000003;
        return h ^= this.endTimestamp == null ? 0 : this.endTimestamp.hashCode();
    }
}

