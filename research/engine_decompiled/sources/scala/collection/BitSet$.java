/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.BitSet;
import scala.collection.Seq;
import scala.collection.generic.BitSetFactory;
import scala.collection.generic.BitSetFactory$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Builder;

public final class BitSet$
implements BitSetFactory<BitSet> {
    public static final BitSet$ MODULE$;
    private final BitSet empty;

    static {
        new BitSet$();
    }

    @Override
    public BitSet apply(Seq<Object> elems) {
        return BitSetFactory$class.apply(this, elems);
    }

    @Override
    public Object bitsetCanBuildFrom() {
        return BitSetFactory$class.bitsetCanBuildFrom(this);
    }

    @Override
    public BitSet empty() {
        return this.empty;
    }

    @Override
    public Builder<Object, scala.collection.immutable.BitSet> newBuilder() {
        return scala.collection.immutable.BitSet$.MODULE$.newBuilder();
    }

    public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
        return this.bitsetCanBuildFrom();
    }

    private BitSet$() {
        MODULE$ = this;
        BitSetFactory$class.$init$(this);
        this.empty = scala.collection.immutable.BitSet$.MODULE$.empty();
    }
}

