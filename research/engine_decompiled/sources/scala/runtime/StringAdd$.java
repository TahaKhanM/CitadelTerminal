/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.runtime.StringAdd;

public final class StringAdd$ {
    public static final StringAdd$ MODULE$;

    static {
        new StringAdd$();
    }

    public final String $plus$extension(Object $this, String other) {
        return new StringBuilder().append((Object)String.valueOf($this)).append((Object)other).toString();
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
        if (!(x$1 instanceof StringAdd)) return false;
        boolean bl = true;
        if (!bl) return false;
        Object object2 = object = x$1 == null ? null : ((StringAdd)x$1).self();
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

    private StringAdd$() {
        MODULE$ = this;
    }
}

