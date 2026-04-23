/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ParArray$;

public abstract class SeqSplitter$class {
    /*
     * WARNING - void declaration
     */
    public static Seq splitWithSignalling(SeqSplitter $this) {
        void var1_1;
        Seq pits = $this.split();
        pits.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqSplitter $outer;

            public final void apply(SeqSplitter<T> x$9) {
                x$9.signalDelegate_$eq(this.$outer.signalDelegate());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return var1_1;
    }

    /*
     * WARNING - void declaration
     */
    public static Seq psplitWithSignalling(SeqSplitter $this, Seq sizes) {
        void var2_2;
        Seq pits = $this.psplit(sizes);
        pits.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqSplitter $outer;

            public final void apply(SeqSplitter<T> x$10) {
                x$10.signalDelegate_$eq(this.$outer.signalDelegate());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return var2_2;
    }

    public static SeqSplitter.Taken newTaken(SeqSplitter $this, int until2) {
        return new SeqSplitter.Taken($this, until2);
    }

    public static SeqSplitter take(SeqSplitter $this, int n) {
        return $this.newTaken(n);
    }

    public static SeqSplitter slice(SeqSplitter $this, int from1, int until1) {
        return $this.newSliceInternal($this.newTaken(until1), from1);
    }

    public static SeqSplitter.Mapped map(SeqSplitter $this, Function1 f) {
        return new SeqSplitter.Mapped($this, f);
    }

    public static SeqSplitter.Appended appendParSeq(SeqSplitter $this, SeqSplitter that) {
        return new SeqSplitter.Appended($this, that);
    }

    public static SeqSplitter.Zipped zipParSeq(SeqSplitter $this, SeqSplitter that) {
        return new SeqSplitter.Zipped($this, that);
    }

    public static SeqSplitter.ZippedAll zipAllParSeq(SeqSplitter $this, SeqSplitter that, Object thisElem, Object thatElem) {
        return new SeqSplitter.ZippedAll<Object, Object>($this, that, thisElem, thatElem);
    }

    public static SeqSplitter reverse(SeqSplitter $this) {
        ParArray pa = (ParArray)ParArray$.MODULE$.fromTraversables(Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[]{$this})).reverse();
        return new ParArray.ParArrayIterator($this, pa){
            private final /* synthetic */ SeqSplitter $outer;

            public SeqSplitter<T> reverse() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super(pa$1, ((ParArray)((Object)pa$1)).ParArrayIterator().$lessinit$greater$default$1(), ((ParArray)((Object)pa$1)).ParArrayIterator().$lessinit$greater$default$2(), ((ParArray)((Object)pa$1)).ParArrayIterator().$lessinit$greater$default$3());
            }
        };
    }

    public static SeqSplitter.Patched patchParSeq(SeqSplitter $this, int from2, SeqSplitter patchElems, int replaced) {
        return new SeqSplitter.Patched($this, from2, patchElems, replaced);
    }

    public static void $init$(SeqSplitter $this) {
    }
}

