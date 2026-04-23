/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.parallel.immutable.HashSetCombiner;

public final class HashSetCombiner$ {
    public static final HashSetCombiner$ MODULE$;
    private final int rootbits;
    private final int rootsize;

    static {
        new HashSetCombiner$();
    }

    public <T> HashSetCombiner<T> apply() {
        return new HashSetCombiner<T>(){};
    }

    public int rootbits() {
        return this.rootbits;
    }

    public int rootsize() {
        return this.rootsize;
    }

    private HashSetCombiner$() {
        MODULE$ = this;
        this.rootbits = 5;
        this.rootsize = 32;
    }
}

