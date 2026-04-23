/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.BigDecimal;
import scala.math.BigDecimal$;
import scala.math.Numeric;

public abstract class Numeric$BigDecimalIsConflicted$class {
    public static BigDecimal plus(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
        return x.$plus(y);
    }

    public static BigDecimal minus(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
        return x.$minus(y);
    }

    public static BigDecimal times(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
        return x.$times(y);
    }

    public static BigDecimal negate(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
        return x.unary_$minus();
    }

    public static BigDecimal fromInt(Numeric.BigDecimalIsConflicted $this, int x) {
        return BigDecimal$.MODULE$.apply(x);
    }

    public static int toInt(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
        return x.intValue();
    }

    public static long toLong(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
        return x.longValue();
    }

    public static float toFloat(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
        return x.floatValue();
    }

    public static double toDouble(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
        return x.doubleValue();
    }

    public static void $init$(Numeric.BigDecimalIsConflicted $this) {
    }
}

