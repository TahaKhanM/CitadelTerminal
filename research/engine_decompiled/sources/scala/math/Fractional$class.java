/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Fractional;

public abstract class Fractional$class {
    public static Fractional.FractionalOps mkNumericOps(Fractional $this, Object lhs) {
        return new Fractional.FractionalOps($this, lhs);
    }

    public static void $init$(Fractional $this) {
    }
}

