/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.PartialFunction;
import scala.PartialFunction$;
import scala.Serializable;

public abstract class PartialFunction$class {
    public static PartialFunction orElse(PartialFunction $this, PartialFunction that) {
        return new PartialFunction.OrElse($this, that);
    }

    public static PartialFunction andThen(PartialFunction $this, Function1 k) {
        return new PartialFunction.AndThen($this, k);
    }

    public static Function1 lift(PartialFunction $this) {
        return new PartialFunction.Lifted($this);
    }

    public static Object applyOrElse(PartialFunction $this, Object x, Function1 function1) {
        return $this.isDefinedAt(x) ? $this.apply(x) : function1.apply(x);
    }

    public static Function1 runWith(PartialFunction $this, Function1 action) {
        return new Serializable($this, action){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PartialFunction $outer;
            private final Function1 action$1;

            public final boolean apply(A x) {
                boolean bl;
                B z = this.$outer.applyOrElse(x, PartialFunction$.MODULE$.scala$PartialFunction$$checkFallback());
                if (PartialFunction$.MODULE$.scala$PartialFunction$$fallbackOccurred(z)) {
                    bl = false;
                } else {
                    this.action$1.apply(z);
                    bl = true;
                }
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.action$1 = action$1;
            }
        };
    }

    public static void $init$(PartialFunction $this) {
    }
}

