/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function4;
import scala.Function4$;
import scala.Function4$$anonfun$curried$1$;
import scala.Function4$$anonfun$curried$1$$anonfun$apply$1$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple4;

public abstract class Function4$class {
    public static Function1 curried(Function4 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function4 $outer;

            public final Function1<T2, Function1<T3, Function1<T4, R>>> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ Function4$.anonfun.curried.1 $outer;
                    public final Object x1$1;

                    public final Function1<T3, Function1<T4, R>> apply(T2 x2) {
                        return new Serializable(this, x2){
                            public static final long serialVersionUID = 0L;
                            public final /* synthetic */ Function4$$anonfun$curried$1$.anonfun.apply.1 $outer;
                            public final Object x2$1;

                            public final Function1<T4, R> apply(T3 x3) {
                                return new Serializable(this, x3){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ Function4$$anonfun$curried$1$$anonfun$apply$1$.anonfun.apply.2 $outer;
                                    private final Object x3$1;

                                    public final R apply(T4 x4) {
                                        return this.$outer.$outer.$outer.$outer.apply(this.$outer.$outer.x1$1, this.$outer.x2$1, this.x3$1, x4);
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                        this.x3$1 = x3$1;
                                    }
                                };
                            }

                            public /* synthetic */ Function4$$anonfun$curried$1$.anonfun.apply.1 scala$Function4$$anonfun$$anonfun$$anonfun$$$outer() {
                                return this.$outer;
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

                    public /* synthetic */ Function4$.anonfun.curried.1 scala$Function4$$anonfun$$anonfun$$$outer() {
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

            public /* synthetic */ Function4 scala$Function4$$anonfun$$$outer() {
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

    public static Function1 tupled(Function4 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function4 $outer;

            public final R apply(Tuple4<T1, T2, T3, T4> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4());
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

    public static String toString(Function4 $this) {
        return "<function4>";
    }

    public static void $init$(Function4 $this) {
    }
}

