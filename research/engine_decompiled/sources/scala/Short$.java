/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Short$
implements AnyValCompanion {
    public static final Short$ MODULE$;
    private final short MinValue;
    private final short MaxValue;

    static {
        new Short$();
    }

    public final short MinValue() {
        return Short.MIN_VALUE;
    }

    public final short MaxValue() {
        return Short.MAX_VALUE;
    }

    public Short box(short x) {
        return x;
    }

    public short unbox(Object x) {
        return (Short)x;
    }

    public String toString() {
        return "object scala.Short";
    }

    public int short2int(short x) {
        return x;
    }

    public long short2long(short x) {
        return x;
    }

    public float short2float(short x) {
        return x;
    }

    public double short2double(short x) {
        return x;
    }

    private Short$() {
        MODULE$ = this;
    }
}

