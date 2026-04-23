/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function12;
import scala.Function12$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple12;

public abstract class Function12$class {
    public static Function1 curried(Function12 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function12 $outer;

            public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, R>>>>>>>>>>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Function12$.anonfun.curried.1 $outer;
                    private final Object x1$1;

                    public final R apply(T2 x2, T3 x3, T4 x4, T5 x5, T6 x6, T7 x7, T8 x8, T9 x9, T10 x10, T11 x11, T12 x12) {
                        return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12);
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

            public /* synthetic */ Function12 scala$Function12$$anonfun$$$outer() {
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

    public static Function1 tupled(Function12 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function12 $outer;

            public final R apply(Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10(), x0$1._11(), x0$1._12());
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

    public static String toString(Function12 $this) {
        return "<function12>";
    }

    public static void $init$(Function12 $this) {
    }
}

