/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.reflect.internal.util.FakePos;
import scala.runtime.AbstractFunction1;

public final class FakePos$
extends AbstractFunction1<String, FakePos>
implements Serializable {
    public static final FakePos$ MODULE$;

    static {
        new FakePos$();
    }

    @Override
    public final String toString() {
        return "FakePos";
    }

    @Override
    public FakePos apply(String msg) {
        return new FakePos(msg);
    }

    public Option<String> unapply(FakePos x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.msg());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private FakePos$() {
        MODULE$ = this;
    }
}

