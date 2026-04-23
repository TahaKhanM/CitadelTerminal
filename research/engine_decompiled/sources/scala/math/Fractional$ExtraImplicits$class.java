/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Fractional;

public abstract class Fractional$ExtraImplicits$class {
    public static Fractional.FractionalOps infixFractionalOps(Fractional.ExtraImplicits $this, Object x, Fractional num) {
        return new Fractional.FractionalOps(num, x);
    }

    public static void $init$(Fractional.ExtraImplicits $this) {
    }
}

