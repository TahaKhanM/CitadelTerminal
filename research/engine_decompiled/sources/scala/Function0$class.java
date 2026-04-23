/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function0;
import scala.runtime.BoxesRunTime;

public abstract class Function0$class {
    public static String toString(Function0 $this) {
        return "<function0>";
    }

    public static boolean apply$mcZ$sp(Function0 $this) {
        return BoxesRunTime.unboxToBoolean($this.apply());
    }

    public static byte apply$mcB$sp(Function0 $this) {
        return BoxesRunTime.unboxToByte($this.apply());
    }

    public static char apply$mcC$sp(Function0 $this) {
        return BoxesRunTime.unboxToChar($this.apply());
    }

    public static double apply$mcD$sp(Function0 $this) {
        return BoxesRunTime.unboxToDouble($this.apply());
    }

    public static float apply$mcF$sp(Function0 $this) {
        return BoxesRunTime.unboxToFloat($this.apply());
    }

    public static int apply$mcI$sp(Function0 $this) {
        return BoxesRunTime.unboxToInt($this.apply());
    }

    public static long apply$mcJ$sp(Function0 $this) {
        return BoxesRunTime.unboxToLong($this.apply());
    }

    public static short apply$mcS$sp(Function0 $this) {
        return BoxesRunTime.unboxToShort($this.apply());
    }

    public static void apply$mcV$sp(Function0 $this) {
        $this.apply();
    }

    public static void $init$(Function0 $this) {
    }
}

