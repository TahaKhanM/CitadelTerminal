/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.util.Left;

public final class Left$
implements Serializable {
    public static final Left$ MODULE$;

    static {
        new Left$();
    }

    public final String toString() {
        return "Left";
    }

    public <A, B> Left<A, B> apply(A a) {
        return new Left(a);
    }

    public <A, B> Option<A> unapply(Left<A, B> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<A>(x$0.a());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Left$() {
        MODULE$ = this;
    }
}

