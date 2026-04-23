/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Float$
implements AnyValCompanion {
    public static final Float$ MODULE$;
    private final float MinPositiveValue;
    private final float NaN;
    private final float PositiveInfinity;
    private final float NegativeInfinity;
    private final float MinValue;
    private final float MaxValue;

    static {
        new Float$();
    }

    public final float MinPositiveValue() {
        return Float.MIN_VALUE;
    }

    public final float NaN() {
        return Float.NaN;
    }

    public final float PositiveInfinity() {
        return Float.POSITIVE_INFINITY;
    }

    public final float NegativeInfinity() {
        return Float.NEGATIVE_INFINITY;
    }

    public final float MinValue() {
        return this.MinValue;
    }

    public final float MaxValue() {
        return Float.MAX_VALUE;
    }

    public Float box(float x) {
        return Float.valueOf(x);
    }

    public float unbox(Object x) {
        return ((Float)x).floatValue();
    }

    public String toString() {
        return "object scala.Float";
    }

    public double float2double(float x) {
        return x;
    }

    private Float$() {
        MODULE$ = this;
        this.MinValue = -Float.MAX_VALUE;
    }
}

