/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.player;

public class PlayerMove {
    public final boolean made;
    public final long timeToMakeMove;
    public final String part1;
    public final String part2;

    private PlayerMove(boolean made, String part1, String part2, long timeToMakeMove) {
        this.made = made;
        this.part1 = part1;
        this.part2 = part2;
        this.timeToMakeMove = timeToMakeMove;
    }

    public String part1OrEmpty() {
        if (this.made) {
            return this.part1;
        }
        return "";
    }

    public String part2OrEmpty() {
        if (this.made) {
            return this.part2;
        }
        return "";
    }

    public static PlayerMove success(String part1, String part2, long timeToMakeMove) {
        return new PlayerMove(true, part1, part2, timeToMakeMove);
    }

    public static PlayerMove failure(long timeToMakeMove) {
        return new PlayerMove(false, null, null, timeToMakeMove);
    }
}

