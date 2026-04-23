/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Integral;

public abstract class Integral$ExtraImplicits$class {
    public static Integral.IntegralOps infixIntegralOps(Integral.ExtraImplicits $this, Object x, Integral num) {
        return new Integral.IntegralOps(num, x);
    }

    public static void $init$(Integral.ExtraImplicits $this) {
    }
}

