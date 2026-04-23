/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function3;
import scala.Function3$;
import scala.Function3$$anonfun$curried$1$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple3;

public abstract class Function3$class {
    public static Function1 curried(Function3 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function3 $outer;

            public final Function1<T2, Function1<T3, R>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ Function3$.anonfun.curried.1 $outer;
                    public final Object x1$1;

                    public final Function1<T3, R> apply(T2 x2) {
                        return new Serializable(this, x2){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Function3$$anonfun$curried$1$.anonfun.apply.1 $outer;
                            private final Object x2$1;

                            public final R apply(T3 x3) {
                                return this.$outer.$outer.$outer.apply(this.$outer.x1$1, this.x2$1, x3);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.x2$1 = x2$1;
                            }
                        };
                    }

                    public /* synthetic */ Function3$.anonfun.curried.1 scala$Function3$$anonfun$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.x1$1 = x1$1;
                    }
                };
            }

            public /* synthetic */ Function3 scala$Function3$$anonfun$$$outer() {
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

    public static Function1 tupled(Function3 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function3 $outer;

            public final R apply(Tuple3<T1, T2, T3> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3());
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

    public static String toString(Function3 $this) {
        return "<function3>";
    }

    public static void $init$(Function3 $this) {
    }
}

