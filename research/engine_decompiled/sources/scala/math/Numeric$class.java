/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$class {
    public static Object zero(Numeric $this) {
        return $this.fromInt(0);
    }

    public static Object one(Numeric $this) {
        return $this.fromInt(1);
    }

    public static Object abs(Numeric $this, Object x) {
        return $this.lt(x, $this.zero()) ? $this.negate(x) : x;
    }

    public static int signum(Numeric $this, Object x) {
        return $this.lt(x, $this.zero()) ? -1 : ($this.gt(x, $this.zero()) ? 1 : 0);
    }

    public static Numeric.Ops mkNumericOps(Numeric $this, Object lhs) {
        return new Numeric.Ops($this, lhs);
    }

    public static void $init$(Numeric $this) {
    }
}

