/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.systems.map.path;

public final class CoordQueue {
    private int[] data = new int[200];
    private int start = 0;
    private int end = 0;
    private int size = 0;
    public int currX = -559038737;
    public int currY = -558907665;

    public void push(int x, int y) {
        if ((this.size + 1) * 2 >= this.data.length) {
            int[] newData = new int[this.data.length * 2];
            for (int i = 0; i < this.size * 2; ++i) {
                newData[i] = this.data[(i + this.start) % this.data.length];
            }
            this.data = newData;
            this.start = 0;
            this.end = this.size * 2;
        }
        this.data[this.end] = x;
        this.data[this.end + 1] = y;
        this.end = (this.end + 2) % this.data.length;
        ++this.size;
    }

    public boolean next() {
        if (this.start != this.end) {
            this.currX = this.data[this.start];
            this.currY = this.data[this.start + 1];
            this.start = (this.start + 2) % this.data.length;
            --this.size;
            return true;
        }
        return false;
    }

    public void drain() {
        this.start = 0;
        this.end = 0;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public Iter iter() {
        return new Iter();
    }

    public final class Iter {
        private int iterStart;
        public int currIterY;
        public int currIterX;

        public Iter() {
            this.iterStart = CoordQueue.this.start;
            this.currIterY = -559038737;
            this.currIterX = -558907665;
        }

        public boolean next() {
            if (CoordQueue.this.start != CoordQueue.this.end) {
                this.currIterX = CoordQueue.this.data[this.iterStart];
                this.currIterY = CoordQueue.this.data[this.iterStart + 1];
                this.iterStart = (this.iterStart + 2) % CoordQueue.this.data.length;
                return true;
            }
            return false;
        }
    }
}
