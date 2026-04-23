/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Proxy;

public abstract class Proxy$class {
    public static int hashCode(Proxy $this) {
        return $this.self().hashCode();
    }

    public static boolean equals(Proxy $this, Object that) {
        boolean bl = that == null ? false : that == $this || that == $this.self() || that.equals($this.self());
        return bl;
    }

    public static String toString(Proxy $this) {
        return String.valueOf($this.self());
    }

    public static void $init$(Proxy $this) {
    }
}

