/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;

public final class Some$
implements Serializable {
    public static final Some$ MODULE$;

    static {
        new Some$();
    }

    public final String toString() {
        return "Some";
    }

    public <A> Some<A> apply(A x) {
        return new Some<A>(x);
    }

    public <A> Option<A> unapply(Some<A> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<A>(x$0.x());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Some$() {
        MODULE$ = this;
    }
}

