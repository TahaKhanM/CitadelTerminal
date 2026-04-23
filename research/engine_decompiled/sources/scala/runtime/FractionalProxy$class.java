/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Serializable;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.collection.immutable.Range;
import scala.runtime.FractionalProxy;

public abstract class FractionalProxy$class {
    public static boolean isWhole(FractionalProxy $this) {
        return false;
    }

    public static Range.Partial until(FractionalProxy $this, Object end) {
        return new Range.Partial(new Serializable($this, end){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ FractionalProxy $outer;
            private final Object end$1;

            public final NumericRange.Exclusive<T> apply(T x$1) {
                return NumericRange$.MODULE$.apply(this.$outer.self(), this.end$1, x$1, this.$outer.integralNum());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.end$1 = end$1;
            }
        });
    }

    public static NumericRange.Exclusive until(FractionalProxy $this, Object end, Object step) {
        return NumericRange$.MODULE$.apply($this.self(), end, step, $this.integralNum());
    }

    public static Range.Partial to(FractionalProxy $this, Object end) {
        return new Range.Partial(new Serializable($this, end){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ FractionalProxy $outer;
            private final Object end$2;

            public final NumericRange.Inclusive<T> apply(T x$2) {
                return NumericRange$.MODULE$.inclusive(this.$outer.self(), this.end$2, x$2, this.$outer.integralNum());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.end$2 = end$2;
            }
        });
    }

    public static NumericRange.Inclusive to(FractionalProxy $this, Object end, Object step) {
        return NumericRange$.MODULE$.inclusive($this.self(), end, step, $this.integralNum());
    }

    public static void $init$(FractionalProxy $this) {
    }
}

