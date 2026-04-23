/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.internal.Channelz;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.concurrent.GuardedBy;

final class ChannelTracer {
    private final Object lock = new Object();
    @GuardedBy(value="lock")
    private final Collection<Channelz.ChannelTrace.Event> events;
    private final long channelCreationTimeNanos;
    @GuardedBy(value="lock")
    private int eventsLogged;

    ChannelTracer(final int maxEvents, long channelCreationTimeNanos, String channelType) {
        Preconditions.checkArgument(maxEvents > 0, "maxEvents must be greater than zero");
        Preconditions.checkNotNull(channelType, "channelType");
        this.events = new ArrayDeque<Channelz.ChannelTrace.Event>(){

            @Override
            @GuardedBy(value="lock")
            public boolean add(Channelz.ChannelTrace.Event event) {
                if (this.size() == maxEvents) {
                    this.removeFirst();
                }
                ChannelTracer.this.eventsLogged++;
                return super.add(event);
            }
        };
        this.channelCreationTimeNanos = channelCreationTimeNanos;
        this.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription(channelType + " created").setSeverity(Channelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(channelCreationTimeNanos).build());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void reportEvent(Channelz.ChannelTrace.Event event) {
        Object object = this.lock;
        synchronized (object) {
            this.events.add(event);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void updateBuilder(Channelz.ChannelStats.Builder builder) {
        ArrayList<Channelz.ChannelTrace.Event> eventsSnapshot;
        int eventsLoggedSnapshot;
        Object object = this.lock;
        synchronized (object) {
            eventsLoggedSnapshot = this.eventsLogged;
            eventsSnapshot = new ArrayList<Channelz.ChannelTrace.Event>(this.events);
        }
        builder.setChannelTrace(new Channelz.ChannelTrace.Builder().setNumEventsLogged(eventsLoggedSnapshot).setCreationTimeNanos(this.channelCreationTimeNanos).setEvents(eventsSnapshot).build());
    }
}

