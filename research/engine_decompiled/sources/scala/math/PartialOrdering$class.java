/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Option;
import scala.math.PartialOrdering;

public abstract class PartialOrdering$class {
    public static boolean gteq(PartialOrdering $this, Object x, Object y) {
        return $this.lteq(y, x);
    }

    public static boolean lt(PartialOrdering $this, Object x, Object y) {
        return $this.lteq(x, y) && !$this.equiv(x, y);
    }

    public static boolean gt(PartialOrdering $this, Object x, Object y) {
        return $this.gteq(x, y) && !$this.equiv(x, y);
    }

    public static boolean equiv(PartialOrdering $this, Object x, Object y) {
        return $this.lteq(x, y) && $this.lteq(y, x);
    }

    public static PartialOrdering reverse(PartialOrdering $this) {
        return new PartialOrdering<T>($this){
            private final /* synthetic */ PartialOrdering $outer;

            public boolean gteq(T x, T y) {
                return PartialOrdering$class.gteq(this, x, y);
            }

            public boolean lt(T x, T y) {
                return PartialOrdering$class.lt(this, x, y);
            }

            public boolean gt(T x, T y) {
                return PartialOrdering$class.gt(this, x, y);
            }

            public boolean equiv(T x, T y) {
                return PartialOrdering$class.equiv(this, x, y);
            }

            public PartialOrdering<T> reverse() {
                return this.$outer;
            }

            public boolean lteq(T x, T y) {
                return this.$outer.lteq(y, x);
            }

            public Option<Object> tryCompare(T x, T y) {
                return this.$outer.tryCompare(y, x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                PartialOrdering$class.$init$(this);
            }
        };
    }

    public static void $init$(PartialOrdering $this) {
    }
}

