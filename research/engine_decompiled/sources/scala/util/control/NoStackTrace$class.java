/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.util.control.NoStackTrace;
import scala.util.control.NoStackTrace$;

public abstract class NoStackTrace$class {
    public static Throwable fillInStackTrace(NoStackTrace $this) {
        return NoStackTrace$.MODULE$.noSuppression() ? $this.scala$util$control$NoStackTrace$$super$fillInStackTrace() : (Throwable)((Object)$this);
    }

    public static void $init$(NoStackTrace $this) {
    }
}

