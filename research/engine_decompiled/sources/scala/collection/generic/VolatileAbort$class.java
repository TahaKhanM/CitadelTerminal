/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.VolatileAbort;

public abstract class VolatileAbort$class {
    public static boolean isAborted(VolatileAbort $this) {
        return $this.scala$collection$generic$VolatileAbort$$abortflag();
    }

    public static void abort(VolatileAbort $this) {
        $this.scala$collection$generic$VolatileAbort$$abortflag_$eq(true);
    }

    public static void $init$(VolatileAbort $this) {
        $this.scala$collection$generic$VolatileAbort$$abortflag_$eq(false);
    }
}

