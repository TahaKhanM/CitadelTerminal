/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function5;
import scala.Function5$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple5;

public abstract class Function5$class {
    public static Function1 curried(Function5 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function5 $outer;

            public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Function5$.anonfun.curried.1 $outer;
                    private final Object x1$1;

                    public final R apply(T2 x2, T3 x3, T4 x4, T5 x5) {
                        return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5);
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

            public /* synthetic */ Function5 scala$Function5$$anonfun$$$outer() {
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

    public static Function1 tupled(Function5 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function5 $outer;

            public final R apply(Tuple5<T1, T2, T3, T4, T5> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5());
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

    public static String toString(Function5 $this) {
        return "<function5>";
    }

    public static void $init$(Function5 $this) {
    }
}

