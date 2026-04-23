/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.Logger;
import slogging.UnderlyingLogger;

public final class Logger$ {
    public static final Logger$ MODULE$;

    static {
        new Logger$();
    }

    public UnderlyingLogger apply(UnderlyingLogger l) {
        return l;
    }

    public final int hashCode$extension(UnderlyingLogger $this) {
        return $this.hashCode();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean equals$extension(UnderlyingLogger $this, Object x$1) {
        Object object = x$1;
        if (!(object instanceof Logger)) return false;
        boolean bl = true;
        if (!bl) return false;
        UnderlyingLogger underlyingLogger = x$1 == null ? null : ((Logger)x$1).underlying();
        UnderlyingLogger underlyingLogger2 = $this;
        UnderlyingLogger underlyingLogger3 = underlyingLogger;
        if (underlyingLogger2 != null) {
            if (!underlyingLogger2.equals(underlyingLogger3)) return false;
            return true;
        }
        if (underlyingLogger3 == null) return true;
        return false;
    }

    private Logger$() {
        MODULE$ = this;
    }
}

