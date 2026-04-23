/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.script;

import scala.Serializable;
import scala.collection.script.Reset;

public final class Reset$
implements Serializable {
    public static final Reset$ MODULE$;

    static {
        new Reset$();
    }

    public final String toString() {
        return "Reset";
    }

    public <A> Reset<A> apply() {
        return new Reset();
    }

    public <A> boolean unapply(Reset<A> x$0) {
        return x$0 != null;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Reset$() {
        MODULE$ = this;
    }
}

