/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.BigInt;
import scala.math.BigInt$;
import scala.math.Numeric;

public abstract class Numeric$BigIntIsIntegral$class {
    public static BigInt plus(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
        return x.$plus(y);
    }

    public static BigInt minus(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
        return x.$minus(y);
    }

    public static BigInt times(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
        return x.$times(y);
    }

    public static BigInt quot(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
        return x.$div(y);
    }

    public static BigInt rem(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
        return x.$percent(y);
    }

    public static BigInt negate(Numeric.BigIntIsIntegral $this, BigInt x) {
        return x.unary_$minus();
    }

    public static BigInt fromInt(Numeric.BigIntIsIntegral $this, int x) {
        return BigInt$.MODULE$.apply(x);
    }

    public static int toInt(Numeric.BigIntIsIntegral $this, BigInt x) {
        return x.intValue();
    }

    public static long toLong(Numeric.BigIntIsIntegral $this, BigInt x) {
        return x.longValue();
    }

    public static float toFloat(Numeric.BigIntIsIntegral $this, BigInt x) {
        return x.floatValue();
    }

    public static double toDouble(Numeric.BigIntIsIntegral $this, BigInt x) {
        return x.doubleValue();
    }

    public static void $init$(Numeric.BigIntIsIntegral $this) {
    }
}

