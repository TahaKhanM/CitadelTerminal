/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;

public abstract class TraversableViewLike$Mapped$class {
    public static void foreach(TraversableViewLike.Mapped $this, Function1 f) {
        $this.scala$collection$TraversableViewLike$Mapped$$$outer().foreach(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike.Mapped $outer;
            private final Function1 f$3;

            public final U apply(A x) {
                return (U)this.f$3.apply(this.$outer.mapping().apply(x));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$3 = f$3;
            }
        });
    }

    public static final String viewIdentifier(TraversableViewLike.Mapped $this) {
        return "M";
    }

    public static void $init$(TraversableViewLike.Mapped $this) {
    }
}

