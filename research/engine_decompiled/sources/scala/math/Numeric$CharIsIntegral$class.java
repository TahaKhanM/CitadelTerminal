/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$CharIsIntegral$class {
    public static char plus(Numeric.CharIsIntegral $this, char x, char y) {
        return (char)(x + y);
    }

    public static char minus(Numeric.CharIsIntegral $this, char x, char y) {
        return (char)(x - y);
    }

    public static char times(Numeric.CharIsIntegral $this, char x, char y) {
        return (char)(x * y);
    }

    public static char quot(Numeric.CharIsIntegral $this, char x, char y) {
        return (char)(x / y);
    }

    public static char rem(Numeric.CharIsIntegral $this, char x, char y) {
        return (char)(x % y);
    }

    public static char negate(Numeric.CharIsIntegral $this, char x) {
        return -x;
    }

    public static char fromInt(Numeric.CharIsIntegral $this, int x) {
        return (char)x;
    }

    public static int toInt(Numeric.CharIsIntegral $this, char x) {
        return x;
    }

    public static long toLong(Numeric.CharIsIntegral $this, char x) {
        return x;
    }

    public static float toFloat(Numeric.CharIsIntegral $this, char x) {
        return x;
    }

    public static double toDouble(Numeric.CharIsIntegral $this, char x) {
        return x;
    }

    public static void $init$(Numeric.CharIsIntegral $this) {
    }
}

