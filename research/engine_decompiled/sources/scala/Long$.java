/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Long$
implements AnyValCompanion {
    public static final Long$ MODULE$;
    private final long MinValue;
    private final long MaxValue;

    static {
        new Long$();
    }

    public final long MinValue() {
        return Long.MIN_VALUE;
    }

    public final long MaxValue() {
        return Long.MAX_VALUE;
    }

    public Long box(long x) {
        return x;
    }

    public long unbox(Object x) {
        return (Long)x;
    }

    public String toString() {
        return "object scala.Long";
    }

    public float long2float(long x) {
        return x;
    }

    public double long2double(long x) {
        return x;
    }

    private Long$() {
        MODULE$ = this;
    }
}

