/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.proximity;

import com.c1games.terminal.game.systems.map.structure.Coords;

public class GridMask {
    private final int xStart;
    private final int yStart;
    private final int xLen;
    private final int yLen;
    private final boolean[][] grid;

    public GridMask(Coords from2, Coords until2) {
        this.xStart = from2.x;
        this.yStart = from2.y;
        this.xLen = until2.x - from2.x;
        this.yLen = until2.y - from2.y;
        this.grid = new boolean[this.xLen][this.yLen];
    }

    public boolean get(Coords c) {
        int x = c.x - this.xStart;
        int y = c.y - this.yStart;
        if (x >= 0 && y >= 0 && x < this.xLen && y < this.yLen) {
            return this.grid[x][y];
        }
        return false;
    }

    public void raise(Coords c) throws IndexOutOfBoundsException {
        int x = c.x - this.xStart;
        int y = c.y - this.yStart;
        if (x < 0 || y < 0 || x >= this.xLen || y >= this.yLen) {
            throw new IndexOutOfBoundsException("raise on GridMask x=" + this.xStart + ".." + (this.xStart + this.xLen) + " y=" + this.yStart + ".." + (this.yStart + this.yLen) + " at " + String.valueOf(c));
        }
        this.grid[x][y] = true;
    }

    public Coords from() {
        return new Coords(this.xStart, this.yStart);
    }

    public Coords until() {
        return new Coords(this.xStart + this.xLen, this.yStart + this.yLen);
    }

    public String toString() {
        StringBuilder s2 = new StringBuilder();
        for (int y = this.yLen - 1; y >= 0; --y) {
            for (int x = 0; x < this.xLen; ++x) {
                s2.append(' ');
                if (x == -this.xStart && y == -this.yStart) {
                    s2.append('*');
                } else {
                    s2.append(' ');
                }
                if (this.grid[x][y]) {
                    s2.append('#');
                    continue;
                }
                s2.append('_');
            }
            s2.append('\n');
        }
        return s2.toString();
    }
}

