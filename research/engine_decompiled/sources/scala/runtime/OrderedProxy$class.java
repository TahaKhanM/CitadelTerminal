/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.runtime.OrderedProxy;

public abstract class OrderedProxy$class {
    public static int compare(OrderedProxy $this, Object y) {
        return $this.ord().compare($this.self(), y);
    }

    public static void $init$(OrderedProxy $this) {
    }
}

