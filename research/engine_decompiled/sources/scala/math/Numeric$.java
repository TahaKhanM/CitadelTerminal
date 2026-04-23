/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Serializable;

public final class Numeric$
implements Serializable {
    public static final Numeric$ MODULE$;

    static {
        new Numeric$();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Numeric$() {
        MODULE$ = this;
    }
}

