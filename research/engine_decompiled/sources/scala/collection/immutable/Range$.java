/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.collection.immutable.Range;

public final class Range$
implements Serializable {
    public static final Range$ MODULE$;
    private final int MAX_PRINT;

    static {
        new Range$();
    }

    public int MAX_PRINT() {
        return this.MAX_PRINT;
    }

    public int count(int start, int end, int step, boolean isInclusive) {
        int n;
        boolean isEmpty2;
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0.");
        }
        boolean bl = start == end ? !isInclusive : (start < end ? step < 0 : (isEmpty2 = step > 0));
        if (isEmpty2) {
            n = 0;
        } else {
            long gap = (long)end - (long)start;
            long jumps = gap / (long)step;
            boolean hasStub = isInclusive || gap % (long)step != 0L;
            long result2 = jumps + (long)(hasStub ? 1 : 0);
            n = result2 > Integer.MAX_VALUE ? -1 : (int)result2;
        }
        return n;
    }

    public int count(int start, int end, int step) {
        return this.count(start, end, step, false);
    }

    public Range apply(int start, int end, int step) {
        return new Range(start, end, step);
    }

    public Range apply(int start, int end) {
        return new Range(start, end, 1);
    }

    public Range.Inclusive inclusive(int start, int end, int step) {
        return new Range.Inclusive(start, end, step);
    }

    public Range.Inclusive inclusive(int start, int end) {
        return new Range.Inclusive(start, end, 1);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Range$() {
        MODULE$ = this;
        this.MAX_PRINT = 512;
    }
}

