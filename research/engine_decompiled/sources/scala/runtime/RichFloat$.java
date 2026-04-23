/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.math.Numeric$FloatAsIfIntegral$;
import scala.math.Numeric$FloatIsFractional$;
import scala.math.Ordering$Float$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichFloat;

public final class RichFloat$ {
    public static final RichFloat$ MODULE$;

    static {
        new RichFloat$();
    }

    public final Numeric$FloatIsFractional$ num$extension(float $this) {
        return Numeric$FloatIsFractional$.MODULE$;
    }

    public final Ordering$Float$ ord$extension(float $this) {
        return Ordering$Float$.MODULE$;
    }

    public final Numeric$FloatAsIfIntegral$ integralNum$extension(float $this) {
        return Numeric$FloatAsIfIntegral$.MODULE$;
    }

    public final double doubleValue$extension(float $this) {
        return $this;
    }

    public final float floatValue$extension(float $this) {
        return $this;
    }

    public final long longValue$extension(float $this) {
        return (long)$this;
    }

    public final int intValue$extension(float $this) {
        return (int)$this;
    }

    public final byte byteValue$extension(float $this) {
        return (byte)$this;
    }

    public final short shortValue$extension(float $this) {
        return (short)$this;
    }

    public final boolean isWhole$extension(float $this) {
        long l = (long)$this;
        return (float)l == $this || l == Long.MAX_VALUE && $this < Float.POSITIVE_INFINITY || l == Long.MIN_VALUE && $this > Float.NEGATIVE_INFINITY;
    }

    public final boolean isValidByte$extension(float $this) {
        return (float)((byte)$this) == $this;
    }

    public final boolean isValidShort$extension(float $this) {
        return (float)((short)$this) == $this;
    }

    public final boolean isValidChar$extension(float $this) {
        return (float)((char)$this) == $this;
    }

    public final boolean isValidInt$extension(float $this) {
        int i = (int)$this;
        return (float)i == $this && i != Integer.MAX_VALUE;
    }

    public final boolean isNaN$extension(float $this) {
        return Float.isNaN($this);
    }

    public final boolean isInfinity$extension(float $this) {
        return Float.isInfinite($this);
    }

    public final boolean isPosInfinity$extension(float $this) {
        return Float.POSITIVE_INFINITY == $this;
    }

    public final boolean isNegInfinity$extension(float $this) {
        return Float.NEGATIVE_INFINITY == $this;
    }

    public final float abs$extension(float $this) {
        return package$.MODULE$.abs($this);
    }

    public final float max$extension(float $this, float that) {
        return package$.MODULE$.max($this, that);
    }

    public final float min$extension(float $this, float that) {
        return package$.MODULE$.min($this, that);
    }

    public final int signum$extension(float $this) {
        return (int)package$.MODULE$.signum($this);
    }

    public final int round$extension(float $this) {
        return package$.MODULE$.round($this);
    }

    public final float ceil$extension(float $this) {
        return (float)package$.MODULE$.ceil($this);
    }

    public final float floor$extension(float $this) {
        return (float)package$.MODULE$.floor($this);
    }

    public final float toRadians$extension(float $this) {
        return (float)package$.MODULE$.toRadians($this);
    }

    public final float toDegrees$extension(float $this) {
        return (float)package$.MODULE$.toDegrees($this);
    }

    public final int hashCode$extension(float $this) {
        return ((Object)BoxesRunTime.boxToFloat($this)).hashCode();
    }

    public final boolean equals$extension(float $this, Object x$1) {
        float f;
        boolean bl = x$1 instanceof RichFloat;
        return bl && $this == (f = ((RichFloat)x$1).self());
    }

    private RichFloat$() {
        MODULE$ = this;
    }
}

