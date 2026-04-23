/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;
import scala.math.package$;

public abstract class Numeric$FloatIsConflicted$class {
    public static float plus(Numeric.FloatIsConflicted $this, float x, float y) {
        return x + y;
    }

    public static float minus(Numeric.FloatIsConflicted $this, float x, float y) {
        return x - y;
    }

    public static float times(Numeric.FloatIsConflicted $this, float x, float y) {
        return x * y;
    }

    public static float negate(Numeric.FloatIsConflicted $this, float x) {
        return -x;
    }

    public static float fromInt(Numeric.FloatIsConflicted $this, int x) {
        return x;
    }

    public static int toInt(Numeric.FloatIsConflicted $this, float x) {
        return (int)x;
    }

    public static long toLong(Numeric.FloatIsConflicted $this, float x) {
        return (long)x;
    }

    public static float toFloat(Numeric.FloatIsConflicted $this, float x) {
        return x;
    }

    public static double toDouble(Numeric.FloatIsConflicted $this, float x) {
        return x;
    }

    public static float abs(Numeric.FloatIsConflicted $this, float x) {
        return package$.MODULE$.abs(x);
    }

    public static void $init$(Numeric.FloatIsConflicted $this) {
    }
}

