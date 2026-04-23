/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.Serializable;
import scala.sys.SystemProperties$;

public final class NoStackTrace$
implements Serializable {
    public static final NoStackTrace$ MODULE$;
    private boolean _noSuppression;

    static {
        new NoStackTrace$();
    }

    public final boolean noSuppression() {
        return this._noSuppression();
    }

    private final boolean _noSuppression() {
        return this._noSuppression;
    }

    private final void _noSuppression_$eq(boolean x$1) {
        this._noSuppression = x$1;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private NoStackTrace$() {
        MODULE$ = this;
        this._noSuppression = false;
        this._noSuppression_$eq(SystemProperties$.MODULE$.noTraceSupression().value());
    }
}

