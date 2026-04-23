/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.AnyValCompanion;

public final class Double$
implements AnyValCompanion {
    public static final Double$ MODULE$;
    private final double MinPositiveValue;
    private final double NaN;
    private final double PositiveInfinity;
    private final double NegativeInfinity;
    private final double MinValue;
    private final double MaxValue;

    static {
        new Double$();
    }

    public final double MinPositiveValue() {
        return Double.MIN_VALUE;
    }

    public final double NaN() {
        return Double.NaN;
    }

    public final double PositiveInfinity() {
        return Double.POSITIVE_INFINITY;
    }

    public final double NegativeInfinity() {
        return Double.NEGATIVE_INFINITY;
    }

    public final double MinValue() {
        return this.MinValue;
    }

    public final double MaxValue() {
        return Double.MAX_VALUE;
    }

    public Double box(double x) {
        return x;
    }

    public double unbox(Object x) {
        return (Double)x;
    }

    public String toString() {
        return "object scala.Double";
    }

    private Double$() {
        MODULE$ = this;
        this.MinValue = -Double.MAX_VALUE;
    }
}

