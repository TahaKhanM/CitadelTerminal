/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.ScalaNumericAnyConversions;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public abstract class ScalaNumericAnyConversions$class {
    public static char toChar(ScalaNumericAnyConversions $this) {
        return (char)$this.intValue();
    }

    public static byte toByte(ScalaNumericAnyConversions $this) {
        return $this.byteValue();
    }

    public static short toShort(ScalaNumericAnyConversions $this) {
        return $this.shortValue();
    }

    public static int toInt(ScalaNumericAnyConversions $this) {
        return $this.intValue();
    }

    public static long toLong(ScalaNumericAnyConversions $this) {
        return $this.longValue();
    }

    public static float toFloat(ScalaNumericAnyConversions $this) {
        return $this.floatValue();
    }

    public static double toDouble(ScalaNumericAnyConversions $this) {
        return $this.doubleValue();
    }

    public static boolean isValidByte(ScalaNumericAnyConversions $this) {
        return $this.isWhole() && $this.toInt() == $this.toByte();
    }

    public static boolean isValidShort(ScalaNumericAnyConversions $this) {
        return $this.isWhole() && $this.toInt() == $this.toShort();
    }

    public static boolean isValidInt(ScalaNumericAnyConversions $this) {
        return $this.isWhole() && $this.toLong() == (long)$this.toInt();
    }

    public static boolean isValidChar(ScalaNumericAnyConversions $this) {
        return $this.isWhole() && $this.toInt() >= 0 && $this.toInt() <= 65535;
    }

    public static int unifiedPrimitiveHashcode(ScalaNumericAnyConversions $this) {
        long lv = $this.toLong();
        return lv >= Integer.MIN_VALUE && lv <= Integer.MAX_VALUE ? (int)lv : ScalaRunTime$.MODULE$.hash((Object)BoxesRunTime.boxToLong(lv));
    }

    public static boolean unifiedPrimitiveEquals(ScalaNumericAnyConversions $this, Object x) {
        boolean bl;
        if (x instanceof Character) {
            char c = BoxesRunTime.unboxToChar(x);
            bl = $this.isValidChar() && $this.toInt() == c;
        } else if (x instanceof Byte) {
            byte by2 = BoxesRunTime.unboxToByte(x);
            bl = $this.isValidByte() && $this.toByte() == by2;
        } else if (x instanceof Short) {
            short s2 = BoxesRunTime.unboxToShort(x);
            bl = $this.isValidShort() && $this.toShort() == s2;
        } else if (x instanceof Integer) {
            int n = BoxesRunTime.unboxToInt(x);
            bl = $this.isValidInt() && $this.toInt() == n;
        } else if (x instanceof Long) {
            long l = BoxesRunTime.unboxToLong(x);
            bl = $this.toLong() == l;
        } else if (x instanceof Float) {
            float f = BoxesRunTime.unboxToFloat(x);
            bl = $this.toFloat() == f;
        } else if (x instanceof Double) {
            double d = BoxesRunTime.unboxToDouble(x);
            bl = $this.toDouble() == d;
        } else {
            bl = false;
        }
        return bl;
    }

    public static void $init$(ScalaNumericAnyConversions $this) {
    }
}

