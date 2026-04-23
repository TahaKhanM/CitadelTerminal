/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.util.Random;

public final class Random$
extends Random {
    public static final Random$ MODULE$;

    static {
        new Random$();
    }

    @Override
    public Random javaRandomToRandom(java.util.Random r) {
        return new Random(r);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Random$() {
        MODULE$ = this;
    }
}

