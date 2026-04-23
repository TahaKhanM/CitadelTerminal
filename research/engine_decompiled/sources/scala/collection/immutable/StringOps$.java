/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.immutable.StringOps;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.StringBuilder$;

public final class StringOps$ {
    public static final StringOps$ MODULE$;

    static {
        new StringOps$();
    }

    public final WrappedString thisCollection$extension(String $this) {
        return new WrappedString($this);
    }

    public final WrappedString toCollection$extension(String $this, String repr) {
        return new WrappedString(repr);
    }

    public final StringBuilder newBuilder$extension(String $this) {
        return StringBuilder$.MODULE$.newBuilder();
    }

    public final char apply$extension(String $this, int index) {
        return $this.charAt(index);
    }

    public final String slice$extension(String $this, int from2, int until2) {
        int start;
        int n = start = from2 < 0 ? 0 : from2;
        if (until2 <= start || start >= $this.length()) {
            return "";
        }
        int end = until2 > this.length$extension($this) ? this.length$extension($this) : until2;
        return $this.substring(start, end);
    }

    public final String toString$extension(String $this) {
        return $this;
    }

    public final int length$extension(String $this) {
        return $this.length();
    }

    public final WrappedString seq$extension(String $this) {
        return new WrappedString($this);
    }

    public final int hashCode$extension(String $this) {
        return $this.hashCode();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean equals$extension(String $this, Object x$1) {
        if (!(x$1 instanceof StringOps)) return false;
        boolean bl = true;
        if (!bl) return false;
        String string2 = x$1 == null ? null : ((StringOps)x$1).repr();
        String string3 = $this;
        if (string3 != null) {
            if (!string3.equals(string2)) return false;
            return true;
        }
        if (string2 == null) return true;
        return false;
    }

    private StringOps$() {
        MODULE$ = this;
    }
}

