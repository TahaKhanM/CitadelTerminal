/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Function1;
import scala.Option;
import scala.Some;
import scala.math.PartiallyOrdered;
import scala.runtime.BoxesRunTime;

public abstract class PartiallyOrdered$class {
    public static boolean $less(PartiallyOrdered $this, Object that, Function1 evidence$2) {
        Some some;
        Option<Object> option = $this.tryCompareTo(that, evidence$2);
        boolean bl = option instanceof Some && BoxesRunTime.unboxToInt((some = (Some)option).x()) < 0;
        return bl;
    }

    public static boolean $greater(PartiallyOrdered $this, Object that, Function1 evidence$3) {
        Some some;
        Option<Object> option = $this.tryCompareTo(that, evidence$3);
        boolean bl = option instanceof Some && BoxesRunTime.unboxToInt((some = (Some)option).x()) > 0;
        return bl;
    }

    public static boolean $less$eq(PartiallyOrdered $this, Object that, Function1 evidence$4) {
        Some some;
        Option<Object> option = $this.tryCompareTo(that, evidence$4);
        boolean bl = option instanceof Some && BoxesRunTime.unboxToInt((some = (Some)option).x()) <= 0;
        return bl;
    }

    public static boolean $greater$eq(PartiallyOrdered $this, Object that, Function1 evidence$5) {
        Some some;
        Option<Object> option = $this.tryCompareTo(that, evidence$5);
        boolean bl = option instanceof Some && BoxesRunTime.unboxToInt((some = (Some)option).x()) >= 0;
        return bl;
    }

    public static void $init$(PartiallyOrdered $this) {
    }
}

