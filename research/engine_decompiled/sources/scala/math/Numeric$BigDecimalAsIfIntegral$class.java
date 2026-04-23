/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.BigDecimal;
import scala.math.Numeric;

public abstract class Numeric$BigDecimalAsIfIntegral$class {
    public static BigDecimal quot(Numeric.BigDecimalAsIfIntegral $this, BigDecimal x, BigDecimal y) {
        return x.quot(y);
    }

    public static BigDecimal rem(Numeric.BigDecimalAsIfIntegral $this, BigDecimal x, BigDecimal y) {
        return x.remainder(y);
    }

    public static void $init$(Numeric.BigDecimalAsIfIntegral $this) {
    }
}

