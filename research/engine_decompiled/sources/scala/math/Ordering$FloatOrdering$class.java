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

public abstract class Ordering$FloatOrdering$class {
    public static int compare(Ordering.FloatOrdering $this, float x, float y) {
        return Float.compare(x, y);
    }

    public static boolean lteq(Ordering.FloatOrdering $this, float x, float y) {
        return x <= y;
    }

    public static boolean gteq(Ordering.FloatOrdering $this, float x, float y) {
        return x >= y;
    }

    public static boolean lt(Ordering.FloatOrdering $this, float x, float y) {
        return x < y;
    }

    public static boolean gt(Ordering.FloatOrdering $this, float x, float y) {
        return x > y;
    }

    public static boolean equiv(Ordering.FloatOrdering $this, float x, float y) {
        return x == y;
    }

    public static float max(Ordering.FloatOrdering $this, float x, float y) {
        return package$.MODULE$.max(x, y);
    }

    public static float min(Ordering.FloatOrdering $this, float x, float y) {
        return package$.MODULE$.min(x, y);
    }

    public static Ordering reverse(Ordering.FloatOrdering $this) {
        return new Ordering.FloatOrdering($this){
            private final /* synthetic */ Ordering.FloatOrdering $outer;

            public boolean equiv(float x, float y) {
                return Ordering$FloatOrdering$class.equiv(this, x, y);
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

            public Ordering.FloatOrdering reverse() {
                return this.$outer;
            }

            public int compare(float x, float y) {
                return this.$outer.compare(y, x);
            }

            public boolean lteq(float x, float y) {
                return this.$outer.lteq(y, x);
            }

            public boolean gteq(float x, float y) {
                return this.$outer.gteq(y, x);
            }

            public boolean lt(float x, float y) {
                return this.$outer.lt(y, x);
            }

            public boolean gt(float x, float y) {
                return this.$outer.gt(y, x);
            }

            public float min(float x, float y) {
                return this.$outer.max(x, y);
            }

            public float max(float x, float y) {
                return this.$outer.min(x, y);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
                Ordering$FloatOrdering$class.$init$(this);
            }
        };
    }

    public static void $init$(Ordering.FloatOrdering $this) {
    }
}

