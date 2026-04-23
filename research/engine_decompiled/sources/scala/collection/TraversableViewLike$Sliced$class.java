/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.TraversableViewLike;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.NonLocalReturnControl$mcV$sp;

public abstract class TraversableViewLike$Sliced$class {
    public static int from(TraversableViewLike.Sliced $this) {
        return $this.endpoints().from();
    }

    public static int until(TraversableViewLike.Sliced $this) {
        return $this.endpoints().until();
    }

    public static void foreach(TraversableViewLike.Sliced $this, Function1 f) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Object object = new Object();
            try {
                IntRef index = IntRef.create(0);
                $this.scala$collection$TraversableViewLike$Sliced$$$outer().foreach(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableViewLike.Sliced $outer;
                    private final IntRef index$1;
                    private final Object nonLocalReturnKey2$1;
                    private final Function1 f$6;

                    public final void apply(A x) {
                        BoxedUnit boxedUnit;
                        if (this.$outer.from() <= this.index$1.elem) {
                            if (this.$outer.until() <= this.index$1.elem) {
                                throw new NonLocalReturnControl$mcV$sp(this.nonLocalReturnKey2$1, BoxedUnit.UNIT);
                            }
                            boxedUnit = this.f$6.apply(x);
                        } else {
                            boxedUnit = BoxedUnit.UNIT;
                        }
                        ++this.index$1.elem;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.index$1 = index$1;
                        this.nonLocalReturnKey2$1 = nonLocalReturnKey2$1;
                        this.f$6 = f$6;
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

    public static final String viewIdentifier(TraversableViewLike.Sliced $this) {
        return "S";
    }

    public static void $init$(TraversableViewLike.Sliced $this) {
    }
}

