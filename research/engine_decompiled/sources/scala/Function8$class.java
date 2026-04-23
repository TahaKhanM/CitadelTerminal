/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function8;
import scala.Function8$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple8;

public abstract class Function8$class {
    public static Function1 curried(Function8 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function8 $outer;

            public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, R>>>>>>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Function8$.anonfun.curried.1 $outer;
                    private final Object x1$1;

                    public final R apply(T2 x2, T3 x3, T4 x4, T5 x5, T6 x6, T7 x7, T8 x8) {
                        return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.x1$1 = x1$1;
                    }
                }.curried();
            }

            public /* synthetic */ Function8 scala$Function8$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public static Function1 tupled(Function8 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function8 $outer;

            public final R apply(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8());
                }
                throw new MatchError(x0$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public static String toString(Function8 $this) {
        return "<function8>";
    }

    public static void $init$(Function8 $this) {
    }
}

