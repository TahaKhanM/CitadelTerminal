/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.export.SpanData;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_SpanData_TimedEvent<T>
extends SpanData.TimedEvent<T> {
    private final Timestamp timestamp;
    private final T event;

    AutoValue_SpanData_TimedEvent(Timestamp timestamp, T event) {
        if (timestamp == null) {
            throw new NullPointerException("Null timestamp");
        }
        this.timestamp = timestamp;
        if (event == null) {
            throw new NullPointerException("Null event");
        }
        this.event = event;
    }

    @Override
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override
    public T getEvent() {
        return this.event;
    }

    public String toString() {
        return "TimedEvent{timestamp=" + this.timestamp + ", event=" + this.event + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SpanData.TimedEvent) {
            SpanData.TimedEvent that = (SpanData.TimedEvent)o;
            return this.timestamp.equals(that.getTimestamp()) && this.event.equals(that.getEvent());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.timestamp.hashCode();
        h *= 1000003;
        return h ^= this.event.hashCode();
    }
}

