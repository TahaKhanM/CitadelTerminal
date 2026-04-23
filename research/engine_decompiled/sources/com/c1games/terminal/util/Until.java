/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.game.systems.map.structure.Coords;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Until
implements Iterator<Coords> {
    private final int xFrom;
    private final int yFrom;
    private final int xTo;
    private final int yTo;
    private int x;
    private int y;

    public Until(int xFrom, int yFrom, int xTo, int yTo) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        this.x = xFrom;
        this.y = yFrom;
    }

    @Override
    public boolean hasNext() {
        return this.y < this.yTo && this.x < this.xTo;
    }

    @Override
    public Coords next() {
        Coords curr = new Coords(this.x, this.y);
        ++this.x;
        if (this.x == this.xTo) {
            this.x = this.xFrom;
            if (this.y == this.yTo) {
                throw new NoSuchElementException();
            }
            ++this.y;
        }
        return curr;
    }
}

