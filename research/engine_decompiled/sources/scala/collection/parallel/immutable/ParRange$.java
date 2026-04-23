/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Serializable;
import scala.collection.immutable.Range;
import scala.collection.parallel.immutable.ParRange;

public final class ParRange$
implements Serializable {
    public static final ParRange$ MODULE$;

    static {
        new ParRange$();
    }

    public ParRange apply(int start, int end, int step, boolean inclusive2) {
        return new ParRange(inclusive2 ? new Range.Inclusive(start, end, step) : new Range(start, end, step));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParRange$() {
        MODULE$ = this;
    }
}

