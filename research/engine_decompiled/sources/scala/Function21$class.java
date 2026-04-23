/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function21;
import scala.Function21$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple21;

public abstract class Function21$class {
    public static Function1 curried(Function21 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function21 $outer;

            public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, R>>>>>>>>>>>>>>>>>>>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Function21$.anonfun.curried.1 $outer;
                    private final Object x1$1;

                    public final R apply(T2 x2, T3 x3, T4 x4, T5 x5, T6 x6, T7 x7, T8 x8, T9 x9, T10 x10, T11 x11, T12 x12, T13 x13, T14 x14, T15 x15, T16 x16, T17 x17, T18 x18, T19 x19, T20 x20, T21 x21) {
                        return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21);
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

            public /* synthetic */ Function21 scala$Function21$$anonfun$$$outer() {
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

    public static Function1 tupled(Function21 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function21 $outer;

            public final R apply(Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10(), x0$1._11(), x0$1._12(), x0$1._13(), x0$1._14(), x0$1._15(), x0$1._16(), x0$1._17(), x0$1._18(), x0$1._19(), x0$1._20(), x0$1._21());
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

    public static String toString(Function21 $this) {
        return "<function21>";
    }

    public static void $init$(Function21 $this) {
    }
}

