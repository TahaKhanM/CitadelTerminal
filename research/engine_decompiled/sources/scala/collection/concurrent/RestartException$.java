/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.util.control.ControlThrowable;
import scala.util.control.NoStackTrace$class;

public final class RestartException$
extends Throwable
implements ControlThrowable {
    public static final RestartException$ MODULE$;

    static {
        new RestartException$();
    }

    @Override
    public /* synthetic */ Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
        return super.fillInStackTrace();
    }

    @Override
    public Throwable fillInStackTrace() {
        return NoStackTrace$class.fillInStackTrace(this);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private RestartException$() {
        MODULE$ = this;
        NoStackTrace$class.$init$(this);
    }
}

