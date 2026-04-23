/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.structure;

import com.c1games.terminal.game.systems.map.structure.Vec2;
import com.c1games.terminal.util.Until;
import java.util.Objects;

public class Coords {
    public static final Coords ORIGIN = new Coords(0, 0);
    public final int x;
    public final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 toFloat() {
        return new Vec2(this.x, this.y);
    }

    public Iterable<Coords> until(Coords end) {
        return () -> new Until(this.x, this.y, end.x, end.y);
    }

    public Iterable<Coords> through(Coords end) {
        return () -> new Until(this.x, this.y, end.x + 1, end.y + 1);
    }

    public Coords plus(Coords other) {
        return new Coords(this.x + other.x, this.y + other.y);
    }

    public Coords minus(Coords other) {
        return new Coords(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Coords coords = (Coords)o;
        return this.x == coords.x && this.y == coords.y;
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
