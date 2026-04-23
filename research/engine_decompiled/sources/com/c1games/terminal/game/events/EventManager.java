/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events;

import com.c1games.terminal.game.events.EventListener;
import com.c1games.terminal.game.gameobject.Gameobject;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Hashtable;

public class EventManager {
    Hashtable<Class<?>, ArrayList<EventListener>> listeners = new Hashtable();
    Gameobject gameobject;

    public EventManager(Gameobject gameobject) {
        this.gameobject = gameobject;
    }

    public void registerListener(Class<?> eventObjectClass, EventListener listener) {
        if (EventObject.class.isAssignableFrom(eventObjectClass)) {
            if (this.listeners.containsKey(eventObjectClass)) {
                this.listeners.get(eventObjectClass).add(listener);
            } else {
                ArrayList<EventListener> nl = new ArrayList<EventListener>();
                nl.add(listener);
                this.listeners.put(eventObjectClass, nl);
            }
        } else {
            System.err.println("Cannot register event because class isn't an EventObject: " + eventObjectClass.toString());
        }
    }

    public void unRegisterListener(Class<?> eventObjectClass, EventListener listener) {
        if (EventObject.class.isAssignableFrom(eventObjectClass)) {
            if (!this.listeners.containsKey(eventObjectClass)) {
                throw new IllegalStateException("Cannot unregister event because isn't in hashtable: " + String.valueOf(eventObjectClass));
            }
        } else {
            throw new IllegalStateException("Cannot unregister event because class isn't an EventObject: " + String.valueOf(eventObjectClass));
        }
        this.listeners.get(eventObjectClass).remove(listener);
    }

    public void fireEvent(EventObject event) {
        if (this.listeners.containsKey(event.getClass())) {
            for (EventListener l : this.listeners.get(event.getClass())) {
                l.handleEvent(event);
            }
        }
        for (Gameobject c : this.gameobject.getChildren()) {
            c.eventManager.fireEvent(event);
        }
    }
}

