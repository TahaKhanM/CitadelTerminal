/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map;

import com.c1games.terminal.game.systems.map.structure.Coords;

public class MapBounds {
    public static final int EDGE_TOP_RIGHT = 0;
    public static final int EDGE_TOP_LEFT = 1;
    public static final int EDGE_BOTTOM_LEFT = 2;
    public static final int EDGE_BOTTOM_RIGHT = 3;
    public final int boardSize;
    public final boolean[][][] isOnEdge;
    public final Coords[][] edgeLists;
    public final boolean[][] arena;

    public MapBounds(int boardSize) {
        Coords c;
        int i;
        this.boardSize = boardSize;
        this.isOnEdge = new boolean[4][this.boardSize][this.boardSize];
        this.edgeLists = new Coords[4][this.boardSize / 2];
        for (i = 0; i < this.boardSize / 2; ++i) {
            c = new Coords(this.boardSize / 2 + i, this.boardSize - 1 - i);
            this.isOnEdge[0][c.x][c.y] = true;
            this.edgeLists[0][i] = c;
        }
        for (i = 0; i < this.boardSize / 2; ++i) {
            c = new Coords(this.boardSize / 2 - 1 - i, this.boardSize - 1 - i);
            this.isOnEdge[1][c.x][c.y] = true;
            this.edgeLists[1][i] = c;
        }
        for (i = 0; i < this.boardSize / 2; ++i) {
            c = new Coords(this.boardSize / 2 - 1 - i, i);
            this.isOnEdge[2][c.x][c.y] = true;
            this.edgeLists[2][i] = c;
        }
        for (i = 0; i < this.boardSize / 2; ++i) {
            c = new Coords(this.boardSize / 2 + i, i);
            this.isOnEdge[3][c.x][c.y] = true;
            this.edgeLists[3][i] = c;
        }
        this.arena = new boolean[this.boardSize][this.boardSize];
        for (i = 0; i < 4; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                for (int k = 0; k < this.boardSize; ++k) {
                    boolean[] blArray = this.arena[j];
                    int n = k;
                    blArray[n] = blArray[n] | this.isOnEdge[i][j][k];
                }
            }
        }
        block7: for (int y = 0; y < this.boardSize; ++y) {
            boolean toggled = false;
            for (int x = 0; x < this.boardSize; ++x) {
                if (this.arena[x][y]) {
                    if (toggled) continue block7;
                    toggled = true;
                    continue;
                }
                if (!toggled) continue;
                this.arena[x][y] = true;
            }
        }
    }

    public boolean inArena(Coords coords) {
        return coords.x >= 0 && coords.y >= 0 && coords.x < this.boardSize && coords.y < this.boardSize && this.arena[coords.x][coords.y];
    }
}

