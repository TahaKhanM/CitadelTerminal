/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.generic.Subtractable;

public abstract class Subtractable$class {
    public static Subtractable $minus(Subtractable $this, Object elem1, Object elem2, Seq elems) {
        return $this.$minus(elem1).$minus((Object)elem2).$minus$minus(elems);
    }

    public static Subtractable $minus$minus(Subtractable $this, GenTraversableOnce xs) {
        Object Repr = $this.repr();
        return (Subtractable)xs.seq().$div$colon(Repr, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Repr x$2, A x$3) {
                return x$2.$minus(x$3);
            }
        });
    }

    public static void $init$(Subtractable $this) {
    }
}

