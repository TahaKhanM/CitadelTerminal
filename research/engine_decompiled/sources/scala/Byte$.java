/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Byte$
implements AnyValCompanion {
    public static final Byte$ MODULE$;
    private final byte MinValue;
    private final byte MaxValue;

    static {
        new Byte$();
    }

    public final byte MinValue() {
        return -128;
    }

    public final byte MaxValue() {
        return 127;
    }

    public Byte box(byte x) {
        return x;
    }

    public byte unbox(Object x) {
        return (Byte)x;
    }

    public String toString() {
        return "object scala.Byte";
    }

    public short byte2short(byte x) {
        return x;
    }

    public int byte2int(byte x) {
        return x;
    }

    public long byte2long(byte x) {
        return x;
    }

    public float byte2float(byte x) {
        return x;
    }

    public double byte2double(byte x) {
        return x;
    }

    private Byte$() {
        MODULE$ = this;
    }
}

