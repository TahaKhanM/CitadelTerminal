/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.UninitializedFieldError;
import scala.runtime.AbstractFunction1;

public final class UninitializedFieldError$
extends AbstractFunction1<String, UninitializedFieldError>
implements Serializable {
    public static final UninitializedFieldError$ MODULE$;

    static {
        new UninitializedFieldError$();
    }

    @Override
    public final String toString() {
        return "UninitializedFieldError";
    }

    @Override
    public UninitializedFieldError apply(String msg) {
        return new UninitializedFieldError(msg);
    }

    public Option<String> unapply(UninitializedFieldError x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.msg());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private UninitializedFieldError$() {
        MODULE$ = this;
    }
}

