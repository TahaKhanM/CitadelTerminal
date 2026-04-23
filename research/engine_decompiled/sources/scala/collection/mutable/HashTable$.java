/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

public final class HashTable$ {
    public static final HashTable$ MODULE$;

    static {
        new HashTable$();
    }

    public final int defaultLoadFactor() {
        return 750;
    }

    public final int loadFactorDenum() {
        return 1000;
    }

    public final int newThreshold(int _loadFactor, int size2) {
        return (int)((long)size2 * (long)_loadFactor / (long)this.loadFactorDenum());
    }

    public final int sizeForThreshold(int _loadFactor, int thr) {
        return (int)((long)thr * (long)this.loadFactorDenum() / (long)_loadFactor);
    }

    public final int capacity(int expectedSize) {
        return expectedSize == 0 ? 1 : this.powerOfTwo(expectedSize);
    }

    public int powerOfTwo(int target) {
        int c = target - 1;
        c |= c >>> 1;
        c |= c >>> 2;
        c |= c >>> 4;
        c |= c >>> 8;
        return (c | c >>> 16) + 1;
    }

    private HashTable$() {
        MODULE$ = this;
    }
}

