/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Ordered;

public abstract class Ordered$class {
    public static boolean $less(Ordered $this, Object that) {
        return $this.compare(that) < 0;
    }

    public static boolean $greater(Ordered $this, Object that) {
        return $this.compare(that) > 0;
    }

    public static boolean $less$eq(Ordered $this, Object that) {
        return $this.compare(that) <= 0;
    }

    public static boolean $greater$eq(Ordered $this, Object that) {
        return $this.compare(that) >= 0;
    }

    public static int compareTo(Ordered $this, Object that) {
        return $this.compare(that);
    }

    public static void $init$(Ordered $this) {
    }
}

