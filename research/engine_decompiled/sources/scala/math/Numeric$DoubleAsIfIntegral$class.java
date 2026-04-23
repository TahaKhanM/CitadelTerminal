/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.BigDecimal$;
import scala.math.Numeric;

public abstract class Numeric$DoubleAsIfIntegral$class {
    public static double quot(Numeric.DoubleAsIfIntegral $this, double x, double y) {
        return BigDecimal$.MODULE$.apply(x).quot(BigDecimal$.MODULE$.apply(y)).doubleValue();
    }

    public static double rem(Numeric.DoubleAsIfIntegral $this, double x, double y) {
        return BigDecimal$.MODULE$.apply(x).remainder(BigDecimal$.MODULE$.apply(y)).doubleValue();
    }

    public static void $init$(Numeric.DoubleAsIfIntegral $this) {
    }
}

