/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Predef$;
import scala.collection.immutable.StringOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.StringFormat;

public final class StringFormat$ {
    public static final StringFormat$ MODULE$;

    static {
        new StringFormat$();
    }

    public final String formatted$extension(Object $this, String fmtstr) {
        return new StringOps(Predef$.MODULE$.augmentString(fmtstr)).format(Predef$.MODULE$.genericWrapArray(new Object[]{$this}));
    }

    public final int hashCode$extension(Object $this) {
        return $this.hashCode();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean equals$extension(Object $this, Object x$1) {
        Object object;
        if (!(x$1 instanceof StringFormat)) return false;
        boolean bl = true;
        if (!bl) return false;
        Object object2 = object = x$1 == null ? null : ((StringFormat)x$1).self();
        if ($this == object) {
            return true;
        }
        if ($this == null) {
            return false;
        }
        boolean bl2 = $this instanceof Number ? BoxesRunTime.equalsNumObject((Number)$this, object) : ($this instanceof Character ? BoxesRunTime.equalsCharObject((Character)$this, object) : $this.equals(object));
        if (!bl2) return false;
        return true;
    }

    private StringFormat$() {
        MODULE$ = this;
    }
}

