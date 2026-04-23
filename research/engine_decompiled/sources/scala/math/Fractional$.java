/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Serializable;

public final class Fractional$
implements Serializable {
    public static final Fractional$ MODULE$;

    static {
        new Fractional$();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Fractional$() {
        MODULE$ = this;
    }
}

