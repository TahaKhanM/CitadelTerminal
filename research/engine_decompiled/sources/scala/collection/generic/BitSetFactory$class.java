/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Serializable;
import scala.collection.BitSet;
import scala.collection.Seq;
import scala.collection.generic.BitSetFactory;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Builder;
import scala.runtime.BoxesRunTime;

public abstract class BitSetFactory$class {
    public static BitSet apply(BitSetFactory $this, Seq elems) {
        Object Coll = $this.empty();
        return (BitSet)elems.$div$colon(Coll, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Coll apply(Coll x$2, int x$3) {
                return (Coll)((BitSet)x$2.$plus((Integer)BoxesRunTime.boxToInteger(x$3)));
            }
        });
    }

    public static CanBuildFrom bitsetCanBuildFrom(BitSetFactory $this) {
        return new CanBuildFrom<Coll, Object, Coll>($this){
            private final /* synthetic */ BitSetFactory $outer;

            public Builder<Object, Coll> apply(Coll from2) {
                return this.$outer.newBuilder();
            }

            public Builder<Object, Coll> apply() {
                return this.$outer.newBuilder();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public static void $init$(BitSetFactory $this) {
    }
}

