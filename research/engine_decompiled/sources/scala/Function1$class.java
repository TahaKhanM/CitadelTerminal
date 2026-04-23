/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Serializable;
import scala.runtime.BoxesRunTime;

public abstract class Function1$class {
    public static Function1 compose(Function1 $this, Function1 g) {
        return new Serializable($this, g){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function1 $outer;
            private final Function1 g$1;

            public final R apply(A x) {
                return this.$outer.apply(this.g$1.apply(x));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.g$1 = g$1;
            }
        };
    }

    public static Function1 andThen(Function1 $this, Function1 g) {
        return new Serializable($this, g){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Function1 $outer;
            private final Function1 g$2;

            public final A apply(T1 x) {
                return (A)this.g$2.apply(this.$outer.apply(x));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.g$2 = g$2;
            }
        };
    }

    public static String toString(Function1 $this) {
        return "<function1>";
    }

    public static boolean apply$mcZD$sp(Function1 $this, double v1) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToDouble(v1)));
    }

    public static double apply$mcDD$sp(Function1 $this, double v1) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToDouble(v1)));
    }

    public static float apply$mcFD$sp(Function1 $this, double v1) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToDouble(v1)));
    }

    public static int apply$mcID$sp(Function1 $this, double v1) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToDouble(v1)));
    }

    public static long apply$mcJD$sp(Function1 $this, double v1) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToDouble(v1)));
    }

    public static void apply$mcVD$sp(Function1 $this, double v1) {
        $this.apply(BoxesRunTime.boxToDouble(v1));
    }

    public static boolean apply$mcZF$sp(Function1 $this, float v1) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToFloat(v1)));
    }

    public static double apply$mcDF$sp(Function1 $this, float v1) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToFloat(v1)));
    }

    public static float apply$mcFF$sp(Function1 $this, float v1) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToFloat(v1)));
    }

    public static int apply$mcIF$sp(Function1 $this, float v1) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToFloat(v1)));
    }

    public static long apply$mcJF$sp(Function1 $this, float v1) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToFloat(v1)));
    }

    public static void apply$mcVF$sp(Function1 $this, float v1) {
        $this.apply(BoxesRunTime.boxToFloat(v1));
    }

    public static boolean apply$mcZI$sp(Function1 $this, int v1) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToInteger(v1)));
    }

    public static double apply$mcDI$sp(Function1 $this, int v1) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToInteger(v1)));
    }

    public static float apply$mcFI$sp(Function1 $this, int v1) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToInteger(v1)));
    }

    public static int apply$mcII$sp(Function1 $this, int v1) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToInteger(v1)));
    }

    public static long apply$mcJI$sp(Function1 $this, int v1) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToInteger(v1)));
    }

    public static void apply$mcVI$sp(Function1 $this, int v1) {
        $this.apply(BoxesRunTime.boxToInteger(v1));
    }

    public static boolean apply$mcZJ$sp(Function1 $this, long v1) {
        return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToLong(v1)));
    }

    public static double apply$mcDJ$sp(Function1 $this, long v1) {
        return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToLong(v1)));
    }

    public static float apply$mcFJ$sp(Function1 $this, long v1) {
        return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToLong(v1)));
    }

    public static int apply$mcIJ$sp(Function1 $this, long v1) {
        return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToLong(v1)));
    }

    public static long apply$mcJJ$sp(Function1 $this, long v1) {
        return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToLong(v1)));
    }

    public static void apply$mcVJ$sp(Function1 $this, long v1) {
        $this.apply(BoxesRunTime.boxToLong(v1));
    }

    public static void $init$(Function1 $this) {
    }
}

