/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class TraversableViewLike$Filtered$class {
    public static void foreach(TraversableViewLike.Filtered $this, Function1 f) {
        $this.scala$collection$TraversableViewLike$Filtered$$$outer().foreach(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike.Filtered $outer;
            private final Function1 f$5;

            public final Object apply(A x) {
                return BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x)) ? this.f$5.apply(x) : BoxedUnit.UNIT;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$5 = f$5;
            }
        });
    }

    public static final String viewIdentifier(TraversableViewLike.Filtered $this) {
        return "F";
    }

    public static void $init$(TraversableViewLike.Filtered $this) {
    }
}

