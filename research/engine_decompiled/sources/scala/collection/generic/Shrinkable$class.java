/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Serializable;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.Shrinkable;

public abstract class Shrinkable$class {
    public static Shrinkable $minus$eq(Shrinkable $this, Object elem1, Object elem2, Seq elems) {
        $this.$minus$eq(elem1);
        $this.$minus$eq(elem2);
        return $this.$minus$minus$eq(elems);
    }

    public static Shrinkable $minus$minus$eq(Shrinkable $this, TraversableOnce xs) {
        xs.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Shrinkable $outer;

            public final Shrinkable<A> apply(A elem) {
                return this.$outer.$minus$eq(elem);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return $this;
    }

    public static void $init$(Shrinkable $this) {
    }
}

