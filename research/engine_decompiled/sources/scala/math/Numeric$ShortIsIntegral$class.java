/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$ShortIsIntegral$class {
    public static short plus(Numeric.ShortIsIntegral $this, short x, short y) {
        return (short)(x + y);
    }

    public static short minus(Numeric.ShortIsIntegral $this, short x, short y) {
        return (short)(x - y);
    }

    public static short times(Numeric.ShortIsIntegral $this, short x, short y) {
        return (short)(x * y);
    }

    public static short quot(Numeric.ShortIsIntegral $this, short x, short y) {
        return (short)(x / y);
    }

    public static short rem(Numeric.ShortIsIntegral $this, short x, short y) {
        return (short)(x % y);
    }

    public static short negate(Numeric.ShortIsIntegral $this, short x) {
        return -x;
    }

    public static short fromInt(Numeric.ShortIsIntegral $this, int x) {
        return (short)x;
    }

    public static int toInt(Numeric.ShortIsIntegral $this, short x) {
        return x;
    }

    public static long toLong(Numeric.ShortIsIntegral $this, short x) {
        return x;
    }

    public static float toFloat(Numeric.ShortIsIntegral $this, short x) {
        return x;
    }

    public static double toDouble(Numeric.ShortIsIntegral $this, short x) {
        return x;
    }

    public static void $init$(Numeric.ShortIsIntegral $this) {
    }
}

