/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$LongIsIntegral$class {
    public static long plus(Numeric.LongIsIntegral $this, long x, long y) {
        return x + y;
    }

    public static long minus(Numeric.LongIsIntegral $this, long x, long y) {
        return x - y;
    }

    public static long times(Numeric.LongIsIntegral $this, long x, long y) {
        return x * y;
    }

    public static long quot(Numeric.LongIsIntegral $this, long x, long y) {
        return x / y;
    }

    public static long rem(Numeric.LongIsIntegral $this, long x, long y) {
        return x % y;
    }

    public static long negate(Numeric.LongIsIntegral $this, long x) {
        return -x;
    }

    public static long fromInt(Numeric.LongIsIntegral $this, int x) {
        return x;
    }

    public static int toInt(Numeric.LongIsIntegral $this, long x) {
        return (int)x;
    }

    public static long toLong(Numeric.LongIsIntegral $this, long x) {
        return x;
    }

    public static float toFloat(Numeric.LongIsIntegral $this, long x) {
        return x;
    }

    public static double toDouble(Numeric.LongIsIntegral $this, long x) {
        return x;
    }

    public static void $init$(Numeric.LongIsIntegral $this) {
    }
}

