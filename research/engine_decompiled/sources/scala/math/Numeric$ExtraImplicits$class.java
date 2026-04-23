/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Numeric;

public abstract class Numeric$ExtraImplicits$class {
    public static Numeric.Ops infixNumericOps(Numeric.ExtraImplicits $this, Object x, Numeric num) {
        return new Numeric.Ops(num, x);
    }

    public static void $init$(Numeric.ExtraImplicits $this) {
    }
}

