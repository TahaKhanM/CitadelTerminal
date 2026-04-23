/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.reflect.internal.FatalError;
import scala.runtime.AbstractFunction1;

public final class FatalError$
extends AbstractFunction1<String, FatalError>
implements Serializable {
    public static final FatalError$ MODULE$;

    static {
        new FatalError$();
    }

    @Override
    public final String toString() {
        return "FatalError";
    }

    @Override
    public FatalError apply(String msg) {
        return new FatalError(msg);
    }

    public Option<String> unapply(FatalError x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.msg());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private FatalError$() {
        MODULE$ = this;
    }
}

