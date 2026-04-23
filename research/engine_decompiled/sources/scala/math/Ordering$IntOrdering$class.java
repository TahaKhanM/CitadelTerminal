/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Ordering;

public abstract class Ordering$IntOrdering$class {
    public static int compare(Ordering.IntOrdering $this, int x, int y) {
        return x < y ? -1 : (x == y ? 0 : 1);
    }

    public static void $init$(Ordering.IntOrdering $this) {
    }
}

