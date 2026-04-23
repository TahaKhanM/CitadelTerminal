/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.BitSetFactory;
import scala.collection.generic.BitSetFactory$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowingBuilder;

public final class BitSet$
implements BitSetFactory<BitSet>,
Serializable {
    public static final BitSet$ MODULE$;

    static {
        new BitSet$();
    }

    @Override
    public scala.collection.BitSet apply(Seq elems) {
        return BitSetFactory$class.apply(this, elems);
    }

    @Override
    public Object bitsetCanBuildFrom() {
        return BitSetFactory$class.bitsetCanBuildFrom(this);
    }

    @Override
    public BitSet empty() {
        return new BitSet();
    }

    @Override
    public Builder<Object, BitSet> newBuilder() {
        return new GrowingBuilder<Object, BitSet>(this.empty());
    }

    public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
        return this.bitsetCanBuildFrom();
    }

    public BitSet fromBitMask(long[] elems) {
        int len = elems.length;
        long[] a = new long[len];
        Array$.MODULE$.copy(elems, 0, a, 0, len);
        return new BitSet(a);
    }

    public BitSet fromBitMaskNoCopy(long[] elems) {
        return new BitSet(elems);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private BitSet$() {
        MODULE$ = this;
        BitSetFactory$class.$init$(this);
    }
}

