/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.GenSet;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.SetLike;
import scala.collection.TraversableLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.SetBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSet$;
import scala.runtime.BoxedUnit;

public abstract class SetLike$class {
    public static Builder newBuilder(SetLike $this) {
        return new SetBuilder($this.empty());
    }

    public static Combiner parCombiner(SetLike $this) {
        return ParSet$.MODULE$.newCombiner();
    }

    public static Seq toSeq(SetLike $this) {
        return $this.toBuffer();
    }

    /*
     * WARNING - void declaration
     */
    public static Buffer toBuffer(SetLike $this) {
        void var1_1;
        ArrayBuffer result2 = new ArrayBuffer($this.size());
        $this.copyToBuffer(result2);
        return var1_1;
    }

    public static Object map(SetLike $this, Function1 f, CanBuildFrom bf) {
        return $this.scala$collection$SetLike$$super$map(f, bf);
    }

    public static Set $plus(SetLike $this, Object elem1, Object elem2, Seq elems) {
        return $this.$plus(elem1).$plus((Object)elem2).$plus$plus(elems);
    }

    public static Set $plus$plus(SetLike $this, GenTraversableOnce elems) {
        Set set = (Set)$this.repr();
        return elems.seq().$div$colon(set, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final This apply(This x$2, A x$3) {
                return (This)x$2.$plus(x$3);
            }
        });
    }

    public static boolean isEmpty(SetLike $this) {
        return $this.size() == 0;
    }

    public static Set union(SetLike $this, GenSet that) {
        return $this.$plus$plus(that);
    }

    public static Set diff(SetLike $this, GenSet that) {
        return (Set)$this.$minus$minus(that);
    }

    public static Iterator subsets(SetLike $this, int len) {
        return len < 0 || len > $this.size() ? Iterator$.MODULE$.empty() : new SetLike.SubsetsItr($this, $this.toIndexedSeq(), len);
    }

    public static Iterator subsets(SetLike $this) {
        return new AbstractIterator<This>($this){
            private final IndexedSeq<A> elms;
            private int len;
            private Iterator<This> itr;
            private final /* synthetic */ SetLike $outer;

            private IndexedSeq<A> elms() {
                return this.elms;
            }

            private int len() {
                return this.len;
            }

            private void len_$eq(int x$1) {
                this.len = x$1;
            }

            private Iterator<This> itr() {
                return this.itr;
            }

            private void itr_$eq(Iterator<This> x$1) {
                this.itr = x$1;
            }

            public boolean hasNext() {
                return this.len() <= this.elms().size() || this.itr().hasNext();
            }

            public This next() {
                java.io.Serializable serializable;
                if (this.itr().hasNext()) {
                    serializable = BoxedUnit.UNIT;
                } else if (this.len() > this.elms().size()) {
                    serializable = Iterator$.MODULE$.empty().next();
                } else {
                    this.itr_$eq(new SetLike.SubsetsItr(this.$outer, this.elms(), this.len()));
                    this.len_$eq(this.len() + 1);
                    serializable = BoxedUnit.UNIT;
                }
                return (This)((Set)this.itr().next());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.elms = $outer.toIndexedSeq();
                this.len = 0;
                this.itr = Iterator$.MODULE$.empty();
            }
        };
    }

    public static String stringPrefix(SetLike $this) {
        return "Set";
    }

    public static String toString(SetLike $this) {
        return TraversableLike$class.toString($this);
    }

    public static void $init$(SetLike $this) {
    }
}

