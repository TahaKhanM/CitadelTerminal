/*
 * Decompiled with CFR 0.152.
 */
package scala.util.hashing;

import scala.Serializable;
import scala.util.hashing.ByteswapHashing;
import scala.util.hashing.Hashing;

public final class ByteswapHashing$
implements Serializable {
    public static final ByteswapHashing$ MODULE$;

    static {
        new ByteswapHashing$();
    }

    public <T> Hashing<T> chain(Hashing<T> h) {
        return new ByteswapHashing.Chained<T>(h);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ByteswapHashing$() {
        MODULE$ = this;
    }
}

