/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.reflect.api.Position;
import scala.reflect.macros.ReificationException;
import scala.runtime.AbstractFunction2;

public final class ReificationException$
extends AbstractFunction2<Position, String, ReificationException>
implements Serializable {
    public static final ReificationException$ MODULE$;

    static {
        new ReificationException$();
    }

    @Override
    public final String toString() {
        return "ReificationException";
    }

    @Override
    public ReificationException apply(Position pos, String msg) {
        return new ReificationException(pos, msg);
    }

    public Option<Tuple2<Position, String>> unapply(ReificationException x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Position, String>>(new Tuple2<Position, String>(x$0.pos(), x$0.msg()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ReificationException$() {
        MODULE$ = this;
    }
}

