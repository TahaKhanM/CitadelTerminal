/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.parallel.immutable.HashMapCombiner;

public final class HashMapCombiner$ {
    public static final HashMapCombiner$ MODULE$;
    private final int rootbits;
    private final int rootsize;

    static {
        new HashMapCombiner$();
    }

    public <K, V> HashMapCombiner<K, V> apply() {
        return new HashMapCombiner<K, V>(){};
    }

    public int rootbits() {
        return this.rootbits;
    }

    public int rootsize() {
        return this.rootsize;
    }

    private HashMapCombiner$() {
        MODULE$ = this;
        this.rootbits = 5;
        this.rootsize = 32;
    }
}

