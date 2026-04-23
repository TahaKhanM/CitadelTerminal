/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.NonLocalReturnControl$mcV$sp;

public abstract class TraversableViewLike$TakenWhile$class {
    public static void foreach(TraversableViewLike.TakenWhile $this, Function1 f) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Object object = new Object();
            try {
                $this.scala$collection$TraversableViewLike$TakenWhile$$$outer().foreach(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableViewLike.TakenWhile $outer;
                    private final Object nonLocalReturnKey3$1;
                    private final Function1 f$8;

                    public final U apply(A x) {
                        if (BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x))) {
                            return (U)this.f$8.apply(x);
                        }
                        throw new NonLocalReturnControl$mcV$sp(this.nonLocalReturnKey3$1, BoxedUnit.UNIT);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.nonLocalReturnKey3$1 = nonLocalReturnKey3$1;
                        this.f$8 = f$8;
                    }
                });
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                nonLocalReturnControl.value$mcV$sp();
            }
            return;
        }
        throw nonLocalReturnControl;
    }

    public static final String viewIdentifier(TraversableViewLike.TakenWhile $this) {
        return "T";
    }

    public static void $init$(TraversableViewLike.TakenWhile $this) {
    }
}

