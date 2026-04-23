/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$FlatMapped$;

public abstract class TraversableViewLike$FlatMapped$class {
    public static void foreach(TraversableViewLike.FlatMapped $this, Function1 f) {
        $this.scala$collection$TraversableViewLike$FlatMapped$$$outer().foreach(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike.FlatMapped $outer;
            public final Function1 f$4;

            public final void apply(A x) {
                this.$outer.mapping().apply(x).seq().foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableViewLike$FlatMapped$.anonfun.foreach.3 $outer;

                    public final U apply(B y) {
                        return (U)this.$outer.f$4.apply(y);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$4 = f$4;
            }
        });
    }

    public static final String viewIdentifier(TraversableViewLike.FlatMapped $this) {
        return "N";
    }

    public static void $init$(TraversableViewLike.FlatMapped $this) {
    }
}

