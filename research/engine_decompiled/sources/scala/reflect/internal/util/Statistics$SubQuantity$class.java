/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.internal.util.Statistics;

public abstract class Statistics$SubQuantity$class {
    public static void $init$(Statistics.SubQuantity $this) {
        $this.underlying().children().$plus$eq((Object)$this);
    }
}

