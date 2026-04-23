/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Function1;
import scala.Some;
import scala.math.Ordering;
import scala.math.PartialOrdering$class;
import scala.runtime.BoxesRunTime;

public abstract class Ordering$class {
    public static Some tryCompare(Ordering $this, Object x, Object y) {
        return new Some<Integer>(BoxesRunTime.boxToInteger($this.compare(x, y)));
    }

    public static boolean lteq(Ordering $this, Object x, Object y) {
        return $this.compare(x, y) <= 0;
    }

    public static boolean gteq(Ordering $this, Object x, Object y) {
        return $this.compare(x, y) >= 0;
    }

    public static boolean lt(Ordering $this, Object x, Object y) {
        return $this.compare(x, y) < 0;
    }

    public static boolean gt(Ordering $this, Object x, Object y) {
        return $this.compare(x, y) > 0;
    }

    public static boolean equiv(Ordering $this, Object x, Object y) {
        return $this.compare(x, y) == 0;
    }

    public static Object max(Ordering $this, Object x, Object y) {
        return $this.gteq(x, y) ? x : y;
    }

    public static Object min(Ordering $this, Object x, Object y) {
        return $this.lteq(x, y) ? x : y;
    }

    public static Ordering reverse(Ordering $this) {
        return new Ordering<T>($this){
            private final /* synthetic */ Ordering $outer;

            public Some<Object> tryCompare(T x, T y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(T x, T y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(T x, T y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(T x, T y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(T x, T y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(T x, T y) {
                return Ordering$class.equiv(this, x, y);
            }

            public T max(T x, T y) {
                return (T)Ordering$class.max(this, x, y);
            }

            public T min(T x, T y) {
                return (T)Ordering$class.min(this, x, y);
            }

            public <U> Ordering<U> on(Function1<U, T> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(T lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public Ordering<T> reverse() {
                return this.$outer;
            }

            public int compare(T x, T y) {
                return this.$outer.compare(y, x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public static Ordering on(Ordering $this, Function1 f) {
        return new Ordering<U>($this, f){
            private final /* synthetic */ Ordering $outer;
            private final Function1 f$2;

            public Some<Object> tryCompare(U x, U y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(U x, U y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(U x, U y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(U x, U y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(U x, U y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(U x, U y) {
                return Ordering$class.equiv(this, x, y);
            }

            public U max(U x, U y) {
                return (U)Ordering$class.max(this, x, y);
            }

            public U min(U x, U y) {
                return (U)Ordering$class.min(this, x, y);
            }

            public Ordering<U> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, U> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(U lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(U x, U y) {
                return this.$outer.compare(this.f$2.apply(x), this.f$2.apply(y));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$2 = f$2;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public static Ordering.Ops mkOrderingOps(Ordering $this, Object lhs) {
        return new Ordering.Ops($this, lhs);
    }

    public static void $init$(Ordering $this) {
    }
}

