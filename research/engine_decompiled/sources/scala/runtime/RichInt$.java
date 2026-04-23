/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.Ordering$Int$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;

public final class RichInt$ {
    public static final RichInt$ MODULE$;

    static {
        new RichInt$();
    }

    public final Numeric$IntIsIntegral$ num$extension(int $this) {
        return Numeric$IntIsIntegral$.MODULE$;
    }

    public final Ordering$Int$ ord$extension(int $this) {
        return Ordering$Int$.MODULE$;
    }

    public final double doubleValue$extension(int $this) {
        return $this;
    }

    public final float floatValue$extension(int $this) {
        return $this;
    }

    public final long longValue$extension(int $this) {
        return $this;
    }

    public final int intValue$extension(int $this) {
        return $this;
    }

    public final byte byteValue$extension(int $this) {
        return (byte)$this;
    }

    public final short shortValue$extension(int $this) {
        return (short)$this;
    }

    public final boolean isWhole$extension(int $this) {
        return true;
    }

    public final boolean isValidInt$extension(int $this) {
        return true;
    }

    public final boolean isValidLong$extension(int $this) {
        return true;
    }

    public final int abs$extension(int $this) {
        return package$.MODULE$.abs($this);
    }

    public final int max$extension(int $this, int that) {
        return package$.MODULE$.max($this, that);
    }

    public final int min$extension(int $this, int that) {
        return package$.MODULE$.min($this, that);
    }

    public final int signum$extension(int $this) {
        return package$.MODULE$.signum($this);
    }

    public final int round$extension(int $this) {
        return $this;
    }

    public final String toBinaryString$extension(int $this) {
        return Integer.toBinaryString($this);
    }

    public final String toHexString$extension(int $this) {
        return Integer.toHexString($this);
    }

    public final String toOctalString$extension(int $this) {
        return Integer.toOctalString($this);
    }

    public final Range until$extension0(int $this, int end) {
        return Range$.MODULE$.apply($this, end);
    }

    public final Range until$extension1(int $this, int end, int step) {
        return Range$.MODULE$.apply($this, end, step);
    }

    public final Range.Inclusive to$extension0(int $this, int end) {
        return Range$.MODULE$.inclusive($this, end);
    }

    public final Range.Inclusive to$extension1(int $this, int end, int step) {
        return Range$.MODULE$.inclusive($this, end, step);
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof RichInt;
        return bl && $this == (n = ((RichInt)x$1).self());
    }

    private RichInt$() {
        MODULE$ = this;
    }
}

