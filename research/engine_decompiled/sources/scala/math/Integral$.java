/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Serializable;

public final class Integral$
implements Serializable {
    public static final Integral$ MODULE$;

    static {
        new Integral$();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Integral$() {
        MODULE$ = this;
    }
}

