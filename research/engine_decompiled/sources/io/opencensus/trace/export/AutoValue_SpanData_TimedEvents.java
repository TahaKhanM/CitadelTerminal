/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.SpanData;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SpanData_TimedEvents<T>
extends SpanData.TimedEvents<T> {
    private final List<SpanData.TimedEvent<T>> events;
    private final int droppedEventsCount;

    AutoValue_SpanData_TimedEvents(List<SpanData.TimedEvent<T>> events2, int droppedEventsCount) {
        if (events2 == null) {
            throw new NullPointerException("Null events");
        }
        this.events = events2;
        this.droppedEventsCount = droppedEventsCount;
    }

    @Override
    public List<SpanData.TimedEvent<T>> getEvents() {
        return this.events;
    }

    @Override
    public int getDroppedEventsCount() {
        return this.droppedEventsCount;
    }

    public String toString() {
        return "TimedEvents{events=" + this.events + ", droppedEventsCount=" + this.droppedEventsCount + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SpanData.TimedEvents) {
            SpanData.TimedEvents that = (SpanData.TimedEvents)o;
            return this.events.equals(that.getEvents()) && this.droppedEventsCount == that.getDroppedEventsCount();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.events.hashCode();
        h *= 1000003;
        return h ^= this.droppedEventsCount;
    }
}

