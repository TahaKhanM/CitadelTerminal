/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events;

import java.util.EventObject;

public class EnableEvent
extends EventObject {
    boolean enabled;

    public EnableEvent(Object source, boolean enabled) {
        super(source);
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }
}

