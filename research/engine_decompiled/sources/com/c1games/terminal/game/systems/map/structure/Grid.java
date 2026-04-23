/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.structure;

import com.c1games.terminal.game.systems.map.structure.Coords;
import com.c1games.terminal.util.Until;

public class Grid<T> {
    public final int xLen;
    public final int yLen;
    private final Object[][] objects;

    public Grid(int xLen, int yLen) {
        this.xLen = xLen;
        this.yLen = yLen;
        this.objects = new Object[xLen][yLen];
    }

    public boolean indexInBounds(Coords c) {
        return c.x >= 0 && c.y >= 0 && c.x < this.xLen && c.y < this.yLen;
    }

    public T get(Coords c) {
        return (T)this.objects[c.x][c.y];
    }

    public T getIfInBounds(Coords c) {
        if (this.indexInBounds(c)) {
            return this.get(c);
        }
        return null;
    }

    public void set(Coords c, T elem) {
        this.objects[c.x][c.y] = elem;
    }

    public boolean setIfInBounds(Coords c, T elem) {
        if (this.indexInBounds(c)) {
            this.set(c, elem);
            return true;
        }
        return false;
    }

    public Iterable<Coords> allCoords() {
        return () -> new Until(0, 0, this.xLen, this.yLen);
    }
}
