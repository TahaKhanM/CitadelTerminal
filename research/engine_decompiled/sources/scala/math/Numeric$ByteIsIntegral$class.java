/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$ByteIsIntegral$class {
    public static byte plus(Numeric.ByteIsIntegral $this, byte x, byte y) {
        return (byte)(x + y);
    }

    public static byte minus(Numeric.ByteIsIntegral $this, byte x, byte y) {
        return (byte)(x - y);
    }

    public static byte times(Numeric.ByteIsIntegral $this, byte x, byte y) {
        return (byte)(x * y);
    }

    public static byte quot(Numeric.ByteIsIntegral $this, byte x, byte y) {
        return (byte)(x / y);
    }

    public static byte rem(Numeric.ByteIsIntegral $this, byte x, byte y) {
        return (byte)(x % y);
    }

    public static byte negate(Numeric.ByteIsIntegral $this, byte x) {
        return -x;
    }

    public static byte fromInt(Numeric.ByteIsIntegral $this, int x) {
        return (byte)x;
    }

    public static int toInt(Numeric.ByteIsIntegral $this, byte x) {
        return x;
    }

    public static long toLong(Numeric.ByteIsIntegral $this, byte x) {
        return x;
    }

    public static float toFloat(Numeric.ByteIsIntegral $this, byte x) {
        return x;
    }

    public static double toDouble(Numeric.ByteIsIntegral $this, byte x) {
        return x;
    }

    public static void $init$(Numeric.ByteIsIntegral $this) {
    }
}

