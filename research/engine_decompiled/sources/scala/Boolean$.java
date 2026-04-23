/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Boolean$
implements AnyValCompanion {
    public static final Boolean$ MODULE$;

    static {
        new Boolean$();
    }

    public Boolean box(boolean x) {
        return x;
    }

    public boolean unbox(Object x) {
        return (Boolean)x;
    }

    public String toString() {
        return "object scala.Boolean";
    }

    private Boolean$() {
        MODULE$ = this;
    }
}

