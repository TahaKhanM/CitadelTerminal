/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Char$
implements AnyValCompanion {
    public static final Char$ MODULE$;
    private final char MinValue;
    private final char MaxValue;

    static {
        new Char$();
    }

    public final char MinValue() {
        return '\u0000';
    }

    public final char MaxValue() {
        return '\uffff';
    }

    public Character box(char x) {
        return Character.valueOf(x);
    }

    public char unbox(Object x) {
        return ((Character)x).charValue();
    }

    public String toString() {
        return "object scala.Char";
    }

    public int char2int(char x) {
        return x;
    }

    public long char2long(char x) {
        return x;
    }

    public float char2float(char x) {
        return x;
    }

    public double char2double(char x) {
        return x;
    }

    private Char$() {
        MODULE$ = this;
    }
}

