/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function2;
import scala.Function2$;
import scala.MatchError;
import scala.Serializable;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public abstract class Function2$class {
    public static Function1 curried(Function2 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ Function2 $outer;

            public final Function1<T2, R> apply(T1 x1) {
                return new Serializable(this, x1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Function2$.anonfun.curried.1 $outer;
                    private final Object x1$1;

                    public final R apply(T2 x2) {
                        return this.$outer.$outer.apply(this.x1$1, x2);
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

            public /* synthetic */ Function2 scala$Function2$$anonfun$$$outer() {
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

    public static Function1 tupled(Function2 $this) {
        return new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function2 $outer;

            public final R apply(Tuple2<T1, T2> x0$1) {
                if (x0$1 != null) {
                    return this.$outer.apply(x0$1._1(), x0$1._2());
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

    public static String toString(Function2 $this) {
        return "<function2>";
    }

    public static boolean apply$mcZDD$sp(Function2 $this, double v1, double v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static double apply$mcDDD$sp(Function2 $this, double v1, double v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static float apply$mcFDD$sp(Function2 $this, double v1, double v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static int apply$mcIDD$sp(Function2 $this, double v1, double v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static long apply$mcJDD$sp(Function2 $this, double v1, double v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static void apply$mcVDD$sp(Function2 $this, double v1, double v2) {
        $this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToDouble(v2));
    }

    public static boolean apply$mcZDI$sp(Function2 $this, double v1, int v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static double apply$mcDDI$sp(Function2 $this, double v1, int v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static float apply$mcFDI$sp(Function2 $this, double v1, int v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static int apply$mcIDI$sp(Function2 $this, double v1, int v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static long apply$mcJDI$sp(Function2 $this, double v1, int v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static void apply$mcVDI$sp(Function2 $this, double v1, int v2) {
        $this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToInteger(v2));
    }

    public static boolean apply$mcZDJ$sp(Function2 $this, double v1, long v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static double apply$mcDDJ$sp(Function2 $this, double v1, long v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static float apply$mcFDJ$sp(Function2 $this, double v1, long v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static int apply$mcIDJ$sp(Function2 $this, double v1, long v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static long apply$mcJDJ$sp(Function2 $this, double v1, long v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static void apply$mcVDJ$sp(Function2 $this, double v1, long v2) {
        $this.apply(BoxesRunTime.boxToDouble(v1), BoxesRunTime.boxToLong(v2));
    }

    public static boolean apply$mcZID$sp(Function2 $this, int v1, double v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static double apply$mcDID$sp(Function2 $this, int v1, double v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static float apply$mcFID$sp(Function2 $this, int v1, double v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static int apply$mcIID$sp(Function2 $this, int v1, double v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static long apply$mcJID$sp(Function2 $this, int v1, double v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static void apply$mcVID$sp(Function2 $this, int v1, double v2) {
        $this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToDouble(v2));
    }

    public static boolean apply$mcZII$sp(Function2 $this, int v1, int v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static double apply$mcDII$sp(Function2 $this, int v1, int v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static float apply$mcFII$sp(Function2 $this, int v1, int v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static int apply$mcIII$sp(Function2 $this, int v1, int v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static long apply$mcJII$sp(Function2 $this, int v1, int v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static void apply$mcVII$sp(Function2 $this, int v1, int v2) {
        $this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToInteger(v2));
    }

    public static boolean apply$mcZIJ$sp(Function2 $this, int v1, long v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static double apply$mcDIJ$sp(Function2 $this, int v1, long v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static float apply$mcFIJ$sp(Function2 $this, int v1, long v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static int apply$mcIIJ$sp(Function2 $this, int v1, long v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static long apply$mcJIJ$sp(Function2 $this, int v1, long v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static void apply$mcVIJ$sp(Function2 $this, int v1, long v2) {
        $this.apply(BoxesRunTime.boxToInteger(v1), BoxesRunTime.boxToLong(v2));
    }

    public static boolean apply$mcZJD$sp(Function2 $this, long v1, double v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static double apply$mcDJD$sp(Function2 $this, long v1, double v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static float apply$mcFJD$sp(Function2 $this, long v1, double v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static int apply$mcIJD$sp(Function2 $this, long v1, double v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static long apply$mcJJD$sp(Function2 $this, long v1, double v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2)));
    }

    public static void apply$mcVJD$sp(Function2 $this, long v1, double v2) {
        $this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToDouble(v2));
    }

    public static boolean apply$mcZJI$sp(Function2 $this, long v1, int v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static double apply$mcDJI$sp(Function2 $this, long v1, int v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static float apply$mcFJI$sp(Function2 $this, long v1, int v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static int apply$mcIJI$sp(Function2 $this, long v1, int v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static long apply$mcJJI$sp(Function2 $this, long v1, int v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2)));
    }

    public static void apply$mcVJI$sp(Function2 $this, long v1, int v2) {
        $this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToInteger(v2));
    }

    public static boolean apply$mcZJJ$sp(Function2 $this, long v1, long v2) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static double apply$mcDJJ$sp(Function2 $this, long v1, long v2) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static float apply$mcFJJ$sp(Function2 $this, long v1, long v2) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static int apply$mcIJJ$sp(Function2 $this, long v1, long v2) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static long apply$mcJJJ$sp(Function2 $this, long v1, long v2) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2)));
    }

    public static void apply$mcVJJ$sp(Function2 $this, long v1, long v2) {
        $this.apply(BoxesRunTime.boxToLong(v1), BoxesRunTime.boxToLong(v2));
    }

    public static void $init$(Function2 $this) {
    }
}

