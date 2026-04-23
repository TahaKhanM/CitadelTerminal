/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.StringContext;
import scala.reflect.internal.Depth;
import scala.runtime.BoxesRunTime;

public final class Depth$ {
    public static final Depth$ MODULE$;
    private final int AnyDepthValue;
    private final int AnyDepth;
    private final int Zero;

    static {
        new Depth$();
    }

    public final int AnyDepthValue() {
        return -3;
    }

    public final int AnyDepth() {
        return this.AnyDepth;
    }

    public final int Zero() {
        return this.Zero;
    }

    public final int apply(int depth) {
        return depth < -3 ? this.AnyDepth() : depth;
    }

    public final int max$extension(int $this, int that) {
        return new Depth($this).$less(new Depth(that)) ? that : $this;
    }

    public final int decr$extension0(int $this, int n) {
        int n2;
        if (this.isAnyDepth$extension($this)) {
            n2 = $this;
        } else {
            int n3 = $this - n;
            Depth$ depth$ = this;
            n2 = n3 < -3 ? depth$.AnyDepth() : n3;
        }
        return n2;
    }

    public final int incr$extension0(int $this, int n) {
        int n2;
        if (this.isAnyDepth$extension($this)) {
            n2 = $this;
        } else {
            int n3 = $this + n;
            Depth$ depth$ = this;
            n2 = n3 < -3 ? depth$.AnyDepth() : n3;
        }
        return n2;
    }

    public final int decr$extension1(int $this) {
        return this.decr$extension0($this, 1);
    }

    public final int incr$extension1(int $this) {
        return this.incr$extension0($this, 1);
    }

    public final boolean isNegative$extension(int $this) {
        return $this < 0;
    }

    public final boolean isZero$extension(int $this) {
        return $this == 0;
    }

    public final boolean isAnyDepth$extension(int $this) {
        return $this == this.AnyDepth();
    }

    public final int compare$extension(int $this, int that) {
        return $this < that ? -1 : ($this == that ? 0 : 1);
    }

    public final String toString$extension(int $this) {
        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Depth(", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger($this)}));
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof Depth;
        return bl && $this == (n = ((Depth)x$1).depth());
    }

    private Depth$() {
        MODULE$ = this;
        this.AnyDepth = -3;
        this.Zero = 0;
    }
}

