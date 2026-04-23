/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.events;

import com.c1games.terminal.game.gameobject.Gameobject;
import java.util.EventObject;

public class DeathEvent
extends EventObject {
    protected Gameobject sourceGameObject;
    protected Object deathInfo;

    public DeathEvent(Object source, Gameobject sourceGameObject, Object deathInfo) {
        super(source);
        this.sourceGameObject = sourceGameObject;
        this.deathInfo = deathInfo;
    }

    public Gameobject getSourceGameObject() {
        return this.sourceGameObject;
    }

    public Object getDeathInfo() {
        return this.deathInfo;
    }
}

