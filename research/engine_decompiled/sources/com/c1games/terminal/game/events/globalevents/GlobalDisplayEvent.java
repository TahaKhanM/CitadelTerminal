/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events.globalevents;

import java.util.EventObject;

public abstract class GlobalDisplayEvent
extends EventObject {
    public GlobalDisplayEvent(Object source) {
        super(source);
    }

    public abstract Object[] inPerspective(int var1);

    public abstract String name();

    public static int samePlayerToPlayerID(boolean matches) {
        if (matches) {
            return 1;
        }
        return 2;
    }
}

