/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.math.Numeric$ByteIsIntegral$;
import scala.math.Ordering$Byte$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichByte;

public final class RichByte$ {
    public static final RichByte$ MODULE$;

    static {
        new RichByte$();
    }

    public final Numeric$ByteIsIntegral$ num$extension(byte $this) {
        return Numeric$ByteIsIntegral$.MODULE$;
    }

    public final Ordering$Byte$ ord$extension(byte $this) {
        return Ordering$Byte$.MODULE$;
    }

    public final double doubleValue$extension(byte $this) {
        return $this;
    }

    public final float floatValue$extension(byte $this) {
        return $this;
    }

    public final long longValue$extension(byte $this) {
        return $this;
    }

    public final int intValue$extension(byte $this) {
        return $this;
    }

    public final byte byteValue$extension(byte $this) {
        return $this;
    }

    public final short shortValue$extension(byte $this) {
        return $this;
    }

    public final boolean isValidByte$extension(byte $this) {
        return true;
    }

    public final byte abs$extension(byte $this) {
        return (byte)package$.MODULE$.abs($this);
    }

    public final byte max$extension(byte $this, byte that) {
        return (byte)package$.MODULE$.max($this, that);
    }

    public final byte min$extension(byte $this, byte that) {
        return (byte)package$.MODULE$.min($this, that);
    }

    public final int signum$extension(byte $this) {
        return package$.MODULE$.signum($this);
    }

    public final int hashCode$extension(byte $this) {
        return ((Object)BoxesRunTime.boxToByte($this)).hashCode();
    }

    public final boolean equals$extension(byte $this, Object x$1) {
        byte by2;
        boolean bl = x$1 instanceof RichByte;
        return bl && $this == (by2 = ((RichByte)x$1).self());
    }

    private RichByte$() {
        MODULE$ = this;
    }
}

