/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function2;
import scala.collection.AbstractIterator;
import scala.collection.GenSeq;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.LinearSeq;
import scala.collection.LinearSeqLike;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.util.hashing.MurmurHash3$;

public abstract class LinearSeqLike$class {
    public static LinearSeq thisCollection(LinearSeqLike $this) {
        return (LinearSeq)$this;
    }

    public static LinearSeq toCollection(LinearSeqLike $this, LinearSeqLike repr) {
        return (LinearSeq)repr;
    }

    public static int hashCode(LinearSeqLike $this) {
        return MurmurHash3$.MODULE$.seqHash($this.seq());
    }

    public static Iterator iterator(LinearSeqLike $this) {
        return new AbstractIterator<A>($this){
            private Repr these;

            private Repr these() {
                return this.these;
            }

            private void these_$eq(Repr x$1) {
                this.these = x$1;
            }

            public boolean hasNext() {
                return !this.these().isEmpty();
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    A result2 = this.these().head();
                    this.these_$eq((LinearSeqLike)this.these().tail());
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }

            /*
             * WARNING - void declaration
             */
            public List<A> toList() {
                void var1_1;
                List<A> xs = this.these().toList();
                this.these_$eq((LinearSeqLike)this.these().take(0));
                return var1_1;
            }
            {
                this.these = $outer;
            }
        };
    }

    public static final boolean corresponds(LinearSeqLike $this, GenSeq that, Function2 p) {
        boolean bl;
        block2: {
            while (true) {
                if ($this.isEmpty()) {
                    bl = that.isEmpty();
                    break block2;
                }
                if (!that.nonEmpty() || !BoxesRunTime.unboxToBoolean(p.apply($this.head(), that.head()))) break;
                that = (GenSeq)that.tail();
                $this = (LinearSeqLike)$this.tail();
            }
            bl = false;
        }
        return bl;
    }

    public static void $init$(LinearSeqLike $this) {
    }
}

