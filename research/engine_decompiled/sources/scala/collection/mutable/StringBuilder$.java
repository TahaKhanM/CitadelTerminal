/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.mutable.StringBuilder;

public final class StringBuilder$
implements Serializable {
    public static final StringBuilder$ MODULE$;

    static {
        new StringBuilder$();
    }

    public StringBuilder newBuilder() {
        return new StringBuilder();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private StringBuilder$() {
        MODULE$ = this;
    }
}

