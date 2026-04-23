/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Array$;
import scala.Function1;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.BitSetFactory;
import scala.collection.generic.BitSetFactory$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.BitSet$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;

public final class BitSet$
implements BitSetFactory<BitSet>,
Serializable {
    public static final BitSet$ MODULE$;
    private final BitSet empty;

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
        return this.empty;
    }

    @Override
    public Builder<Object, BitSet> newBuilder() {
        return new Builder<Object, BitSet>(){
            private final scala.collection.mutable.BitSet b;

            public void sizeHint(int size2) {
                Builder$class.sizeHint((Builder)this, size2);
            }

            public void sizeHint(TraversableLike<?, ?> coll) {
                Builder$class.sizeHint((Builder)this, coll);
            }

            public void sizeHint(TraversableLike<?, ?> coll, int delta) {
                Builder$class.sizeHint(this, coll, delta);
            }

            public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
                Builder$class.sizeHintBounded(this, size2, boundingColl);
            }

            public <NewTo> Builder<Object, NewTo> mapResult(Function1<BitSet, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Growable<Object> $plus$plus$eq(TraversableOnce<Object> xs) {
                return Growable$class.$plus$plus$eq(this, xs);
            }

            public anon.1 $plus$eq(int x) {
                this.b.$plus$eq(x);
                return this;
            }

            public void clear() {
                this.b.clear();
            }

            public BitSet result() {
                return this.b.toImmutable();
            }
            {
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                this.b = new scala.collection.mutable.BitSet();
            }
        };
    }

    public CanBuildFrom<BitSet, Object, BitSet> canBuildFrom() {
        return this.bitsetCanBuildFrom();
    }

    public BitSet fromBitMask(long[] elems) {
        BitSet bitSet;
        int len = elems.length;
        if (len == 0) {
            bitSet = this.empty();
        } else if (len == 1) {
            bitSet = new BitSet.BitSet1(elems[0]);
        } else if (len == 2) {
            bitSet = new BitSet.BitSet2(elems[0], elems[1]);
        } else {
            long[] a = new long[len];
            Array$.MODULE$.copy(elems, 0, a, 0, len);
            bitSet = new BitSet.BitSetN(a);
        }
        return bitSet;
    }

    public BitSet fromBitMaskNoCopy(long[] elems) {
        int len = elems.length;
        return len == 0 ? this.empty() : (len == 1 ? new BitSet.BitSet1(elems[0]) : (len == 2 ? new BitSet.BitSet2(elems[0], elems[1]) : new BitSet.BitSetN(elems)));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private BitSet$() {
        MODULE$ = this;
        BitSetFactory$class.$init$(this);
        this.empty = new BitSet.BitSet1(0L);
    }
}

