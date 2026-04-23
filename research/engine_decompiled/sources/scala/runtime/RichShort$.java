/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.math.Numeric$ShortIsIntegral$;
import scala.math.Ordering$Short$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichShort;

public final class RichShort$ {
    public static final RichShort$ MODULE$;

    static {
        new RichShort$();
    }

    public final Numeric$ShortIsIntegral$ num$extension(short $this) {
        return Numeric$ShortIsIntegral$.MODULE$;
    }

    public final Ordering$Short$ ord$extension(short $this) {
        return Ordering$Short$.MODULE$;
    }

    public final double doubleValue$extension(short $this) {
        return $this;
    }

    public final float floatValue$extension(short $this) {
        return $this;
    }

    public final long longValue$extension(short $this) {
        return $this;
    }

    public final int intValue$extension(short $this) {
        return $this;
    }

    public final byte byteValue$extension(short $this) {
        return (byte)$this;
    }

    public final short shortValue$extension(short $this) {
        return $this;
    }

    public final boolean isValidShort$extension(short $this) {
        return true;
    }

    public final short abs$extension(short $this) {
        return (short)package$.MODULE$.abs($this);
    }

    public final short max$extension(short $this, short that) {
        return (short)package$.MODULE$.max($this, that);
    }

    public final short min$extension(short $this, short that) {
        return (short)package$.MODULE$.min($this, that);
    }

    public final int signum$extension(short $this) {
        return package$.MODULE$.signum($this);
    }

    public final int hashCode$extension(short $this) {
        return ((Object)BoxesRunTime.boxToShort($this)).hashCode();
    }

    public final boolean equals$extension(short $this, Object x$1) {
        short s2;
        boolean bl = x$1 instanceof RichShort;
        return bl && $this == (s2 = ((RichShort)x$1).self());
    }

    private RichShort$() {
        MODULE$ = this;
    }
}

