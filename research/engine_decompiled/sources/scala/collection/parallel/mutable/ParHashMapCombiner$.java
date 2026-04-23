/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.parallel.mutable.ParHashMapCombiner;

public final class ParHashMapCombiner$ {
    public static final ParHashMapCombiner$ MODULE$;
    private final int discriminantbits;
    private final int numblocks;
    private final int discriminantmask;
    private final int nonmasklength;

    static {
        new ParHashMapCombiner$();
    }

    public int discriminantbits() {
        return this.discriminantbits;
    }

    public int numblocks() {
        return this.numblocks;
    }

    public int discriminantmask() {
        return this.discriminantmask;
    }

    public int nonmasklength() {
        return this.nonmasklength;
    }

    public <K, V> ParHashMapCombiner<K, V> apply() {
        return new ParHashMapCombiner<K, V>(){};
    }

    private ParHashMapCombiner$() {
        MODULE$ = this;
        this.discriminantbits = 5;
        this.numblocks = 1 << this.discriminantbits();
        this.discriminantmask = (1 << this.discriminantbits()) - 1;
        this.nonmasklength = 32 - this.discriminantbits();
    }
}

