/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.BigDecimal$;
import scala.math.Numeric;

public abstract class Numeric$FloatAsIfIntegral$class {
    public static float quot(Numeric.FloatAsIfIntegral $this, float x, float y) {
        return BigDecimal$.MODULE$.apply(x).quot(BigDecimal$.MODULE$.apply(y)).floatValue();
    }

    public static float rem(Numeric.FloatAsIfIntegral $this, float x, float y) {
        return BigDecimal$.MODULE$.apply(x).remainder(BigDecimal$.MODULE$.apply(y)).floatValue();
    }

    public static void $init$(Numeric.FloatAsIfIntegral $this) {
    }
}

