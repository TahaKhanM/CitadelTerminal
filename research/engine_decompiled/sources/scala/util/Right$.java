/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.util.Right;

public final class Right$
implements Serializable {
    public static final Right$ MODULE$;

    static {
        new Right$();
    }

    public final String toString() {
        return "Right";
    }

    public <A, B> Right<A, B> apply(B b) {
        return new Right(b);
    }

    public <A, B> Option<B> unapply(Right<A, B> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<B>(x$0.b());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Right$() {
        MODULE$ = this;
    }
}

