/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject;

import com.c1games.terminal.game.Game;

public class UniqueId {
    String id;

    public String getId() {
        return this.id;
    }

    public UniqueId(Game game) {
        this.id = game.getNewID();
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        UniqueId other = (UniqueId)obj;
        return this.id == other.id;
    }

    public String toString() {
        return this.id;
    }
}

