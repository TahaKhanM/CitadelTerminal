/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.parallel.immutable.Repetition;

public final class package$ {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    public <T> Repetition<T> repetition(T elem, int len) {
        return new Repetition<T>(elem, len);
    }

    private package$() {
        MODULE$ = this;
    }
}

