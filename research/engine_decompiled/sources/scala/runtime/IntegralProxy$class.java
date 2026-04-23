/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.runtime.IntegralProxy;

public abstract class IntegralProxy$class {
    public static NumericRange.Exclusive until(IntegralProxy $this, Object end) {
        return NumericRange$.MODULE$.apply($this.self(), end, $this.num().one(), $this.num());
    }

    public static NumericRange.Exclusive until(IntegralProxy $this, Object end, Object step) {
        return NumericRange$.MODULE$.apply($this.self(), end, step, $this.num());
    }

    public static NumericRange.Inclusive to(IntegralProxy $this, Object end) {
        return NumericRange$.MODULE$.inclusive($this.self(), end, $this.num().one(), $this.num());
    }

    public static NumericRange.Inclusive to(IntegralProxy $this, Object end, Object step) {
        return NumericRange$.MODULE$.inclusive($this.self(), end, step, $this.num());
    }

    public static void $init$(IntegralProxy $this) {
    }
}

