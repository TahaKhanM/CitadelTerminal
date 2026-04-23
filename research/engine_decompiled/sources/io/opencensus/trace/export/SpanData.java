/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.opencensus.common.Timestamp;
import io.opencensus.internal.BaseMessageEventUtil;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.BaseMessageEvent;
import io.opencensus.trace.Link;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.Status;
import io.opencensus.trace.export.AutoValue_SpanData;
import io.opencensus.trace.export.AutoValue_SpanData_Attributes;
import io.opencensus.trace.export.AutoValue_SpanData_Links;
import io.opencensus.trace.export.AutoValue_SpanData_TimedEvent;
import io.opencensus.trace.export.AutoValue_SpanData_TimedEvents;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class SpanData {
    public static SpanData create(SpanContext context, @Nullable SpanId parentSpanId, @Nullable Boolean hasRemoteParent, String name, Timestamp startTimestamp, Attributes attributes, TimedEvents<Annotation> annotations, TimedEvents<? extends BaseMessageEvent> messageOrNetworkEvents, Links links, @Nullable Integer childSpanCount, @Nullable Status status, @Nullable Timestamp endTimestamp) {
        if (messageOrNetworkEvents == null) {
            throw new NullPointerException("Null messageOrNetworkEvents");
        }
        ArrayList messageEventsList = Lists.newArrayList();
        for (TimedEvent<? extends BaseMessageEvent> timedEvent : messageOrNetworkEvents.getEvents()) {
            BaseMessageEvent event = timedEvent.getEvent();
            if (event instanceof MessageEvent) {
                TimedEvent<? extends BaseMessageEvent> timedMessageEvent = timedEvent;
                messageEventsList.add(timedMessageEvent);
                continue;
            }
            messageEventsList.add(TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtil.asMessageEvent(event)));
        }
        TimedEvents<MessageEvent> messageEvents = TimedEvents.create(messageEventsList, messageOrNetworkEvents.getDroppedEventsCount());
        return new AutoValue_SpanData(context, parentSpanId, hasRemoteParent, name, startTimestamp, attributes, annotations, messageEvents, links, childSpanCount, status, endTimestamp);
    }

    public abstract SpanContext getContext();

    @Nullable
    public abstract SpanId getParentSpanId();

    @Nullable
    public abstract Boolean getHasRemoteParent();

    public abstract String getName();

    public abstract Timestamp getStartTimestamp();

    public abstract Attributes getAttributes();

    public abstract TimedEvents<Annotation> getAnnotations();

    @Deprecated
    public TimedEvents<NetworkEvent> getNetworkEvents() {
        TimedEvents<MessageEvent> timedEvents = this.getMessageEvents();
        ArrayList networkEventsList = Lists.newArrayList();
        for (TimedEvent<MessageEvent> timedEvent : timedEvents.getEvents()) {
            networkEventsList.add(TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtil.asNetworkEvent(timedEvent.getEvent())));
        }
        return TimedEvents.create(networkEventsList, timedEvents.getDroppedEventsCount());
    }

    public abstract TimedEvents<MessageEvent> getMessageEvents();

    public abstract Links getLinks();

    @Nullable
    public abstract Integer getChildSpanCount();

    @Nullable
    public abstract Status getStatus();

    @Nullable
    public abstract Timestamp getEndTimestamp();

    SpanData() {
    }

    @Immutable
    public static abstract class Links {
        public static Links create(List<Link> links, int droppedLinksCount) {
            return new AutoValue_SpanData_Links(Collections.unmodifiableList(new ArrayList(Preconditions.checkNotNull(links, "links"))), droppedLinksCount);
        }

        public abstract List<Link> getLinks();

        public abstract int getDroppedLinksCount();

        Links() {
        }
    }

    @Immutable
    public static abstract class Attributes {
        public static Attributes create(Map<String, AttributeValue> attributeMap, int droppedAttributesCount) {
            return new AutoValue_SpanData_Attributes(Collections.unmodifiableMap(new HashMap<String, AttributeValue>(Preconditions.checkNotNull(attributeMap, "attributeMap"))), droppedAttributesCount);
        }

        public abstract Map<String, AttributeValue> getAttributeMap();

        public abstract int getDroppedAttributesCount();

        Attributes() {
        }
    }

    @Immutable
    public static abstract class TimedEvents<T> {
        public static <T> TimedEvents<T> create(List<TimedEvent<T>> events2, int droppedEventsCount) {
            return new AutoValue_SpanData_TimedEvents(Collections.unmodifiableList(new ArrayList(Preconditions.checkNotNull(events2, "events"))), droppedEventsCount);
        }

        public abstract List<TimedEvent<T>> getEvents();

        public abstract int getDroppedEventsCount();

        TimedEvents() {
        }
    }

    @Immutable
    public static abstract class TimedEvent<T> {
        public static <T> TimedEvent<T> create(Timestamp timestamp, T event) {
            return new AutoValue_SpanData_TimedEvent<T>(timestamp, event);
        }

        public abstract Timestamp getTimestamp();

        public abstract T getEvent();

        TimedEvent() {
        }
    }
}

