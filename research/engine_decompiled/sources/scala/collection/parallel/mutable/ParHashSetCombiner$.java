/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.parallel.mutable.ParHashSetCombiner;

public final class ParHashSetCombiner$ {
    public static final ParHashSetCombiner$ MODULE$;
    private final int discriminantbits;
    private final int numblocks;
    private final int discriminantmask;
    private final int nonmasklength;

    static {
        new ParHashSetCombiner$();
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

    public <T> ParHashSetCombiner<T> apply() {
        return new ParHashSetCombiner<T>(){};
    }

    private ParHashSetCombiner$() {
        MODULE$ = this;
        this.discriminantbits = 5;
        this.numblocks = 1 << this.discriminantbits();
        this.discriminantmask = (1 << this.discriminantbits()) - 1;
        this.nonmasklength = 32 - this.discriminantbits();
    }
}

