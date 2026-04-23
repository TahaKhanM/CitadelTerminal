/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Ordering;

public abstract class Ordering$LongOrdering$class {
    public static int compare(Ordering.LongOrdering $this, long x, long y) {
        return x < y ? -1 : (x == y ? 0 : 1);
    }

    public static void $init$(Ordering.LongOrdering $this) {
    }
}

