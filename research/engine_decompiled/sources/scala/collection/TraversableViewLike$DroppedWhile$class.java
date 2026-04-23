/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class TraversableViewLike$DroppedWhile$class {
    public static void foreach(TraversableViewLike.DroppedWhile $this, Function1 f) {
        BooleanRef go = BooleanRef.create(false);
        $this.scala$collection$TraversableViewLike$DroppedWhile$$$outer().foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike.DroppedWhile $outer;
            private final BooleanRef go$1;
            private final Function1 f$7;

            public final Object apply(A x) {
                if (!this.go$1.elem && !BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x))) {
                    this.go$1.elem = true;
                }
                return this.go$1.elem ? this.f$7.apply(x) : BoxedUnit.UNIT;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.go$1 = go$1;
                this.f$7 = f$7;
            }
        });
    }

    public static final String viewIdentifier(TraversableViewLike.DroppedWhile $this) {
        return "D";
    }

    public static void $init$(TraversableViewLike.DroppedWhile $this) {
    }
}

