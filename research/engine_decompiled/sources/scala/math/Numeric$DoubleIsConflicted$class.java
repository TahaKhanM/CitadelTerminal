/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;
import scala.math.package$;

public abstract class Numeric$DoubleIsConflicted$class {
    public static double plus(Numeric.DoubleIsConflicted $this, double x, double y) {
        return x + y;
    }

    public static double minus(Numeric.DoubleIsConflicted $this, double x, double y) {
        return x - y;
    }

    public static double times(Numeric.DoubleIsConflicted $this, double x, double y) {
        return x * y;
    }

    public static double negate(Numeric.DoubleIsConflicted $this, double x) {
        return -x;
    }

    public static double fromInt(Numeric.DoubleIsConflicted $this, int x) {
        return x;
    }

    public static int toInt(Numeric.DoubleIsConflicted $this, double x) {
        return (int)x;
    }

    public static long toLong(Numeric.DoubleIsConflicted $this, double x) {
        return (long)x;
    }

    public static float toFloat(Numeric.DoubleIsConflicted $this, double x) {
        return (float)x;
    }

    public static double toDouble(Numeric.DoubleIsConflicted $this, double x) {
        return x;
    }

    public static double abs(Numeric.DoubleIsConflicted $this, double x) {
        return package$.MODULE$.abs(x);
    }

    public static void $init$(Numeric.DoubleIsConflicted $this) {
    }
}

