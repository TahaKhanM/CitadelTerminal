/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Function1;
import scala.Some;
import scala.math.Ordering;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;
import scala.math.package$;

public abstract class Ordering$DoubleOrdering$class {
    public static int compare(Ordering.DoubleOrdering $this, double x, double y) {
        return Double.compare(x, y);
    }

    public static boolean lteq(Ordering.DoubleOrdering $this, double x, double y) {
        return x <= y;
    }

    public static boolean gteq(Ordering.DoubleOrdering $this, double x, double y) {
        return x >= y;
    }

    public static boolean lt(Ordering.DoubleOrdering $this, double x, double y) {
        return x < y;
    }

    public static boolean gt(Ordering.DoubleOrdering $this, double x, double y) {
        return x > y;
    }

    public static boolean equiv(Ordering.DoubleOrdering $this, double x, double y) {
        return x == y;
    }

    public static double max(Ordering.DoubleOrdering $this, double x, double y) {
        return package$.MODULE$.max(x, y);
    }

    public static double min(Ordering.DoubleOrdering $this, double x, double y) {
        return package$.MODULE$.min(x, y);
    }

    public static Ordering reverse(Ordering.DoubleOrdering $this) {
        return new Ordering.DoubleOrdering($this){
            private final /* synthetic */ Ordering.DoubleOrdering $outer;

            public boolean equiv(double x, double y) {
                return Ordering$DoubleOrdering$class.equiv(this, x, y);
            }

            public Some tryCompare(Object x, Object y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public <U> Ordering<U> on(Function1<U, Object> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public Ordering.DoubleOrdering reverse() {
                return this.$outer;
            }

            public int compare(double x, double y) {
                return this.$outer.compare(y, x);
            }

            public boolean lteq(double x, double y) {
                return this.$outer.lteq(y, x);
            }

            public boolean gteq(double x, double y) {
                return this.$outer.gteq(y, x);
            }

            public boolean lt(double x, double y) {
                return this.$outer.lt(y, x);
            }

            public boolean gt(double x, double y) {
                return this.$outer.gt(y, x);
            }

            public double min(double x, double y) {
                return this.$outer.max(x, y);
            }

            public double max(double x, double y) {
                return this.$outer.min(x, y);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
                Ordering$DoubleOrdering$class.$init$(this);
            }
        };
    }

    public static void $init$(Ordering.DoubleOrdering $this) {
    }
}

