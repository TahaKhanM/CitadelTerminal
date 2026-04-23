/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Serializable;
import scala.collection.LinearSeq;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;

public abstract class Growable$class {
    public static Growable $plus$eq(Growable $this, Object elem1, Object elem2, Seq elems) {
        return $this.$plus$eq(elem1).$plus$eq(elem2).$plus$plus$eq(elems);
    }

    public static Growable $plus$plus$eq(Growable $this, TraversableOnce xs) {
        if (xs instanceof LinearSeq) {
            LinearSeq linearSeq = (LinearSeq)xs;
            Growable$class.loop$1($this, linearSeq);
        } else {
            xs.foreach(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Growable $outer;

                public final Growable<A> apply(A elem) {
                    return this.$outer.$plus$eq(elem);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }
        return $this;
    }

    private static final void loop$1(Growable $this, LinearSeq xs) {
        while (xs.nonEmpty()) {
            $this.$plus$eq(xs.head());
            xs = (LinearSeq)xs.tail();
        }
    }

    public static void $init$(Growable $this) {
    }
}

