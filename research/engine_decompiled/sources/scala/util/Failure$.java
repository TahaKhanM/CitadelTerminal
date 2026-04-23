/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.util.Failure;

public final class Failure$
implements Serializable {
    public static final Failure$ MODULE$;

    static {
        new Failure$();
    }

    public final String toString() {
        return "Failure";
    }

    public <T> Failure<T> apply(Throwable exception) {
        return new Failure(exception);
    }

    public <T> Option<Throwable> unapply(Failure<T> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Throwable>(x$0.exception());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Failure$() {
        MODULE$ = this;
    }
}

