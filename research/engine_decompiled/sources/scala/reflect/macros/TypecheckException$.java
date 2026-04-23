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
import scala.reflect.macros.TypecheckException;
import scala.runtime.AbstractFunction2;

public final class TypecheckException$
extends AbstractFunction2<Position, String, TypecheckException>
implements Serializable {
    public static final TypecheckException$ MODULE$;

    static {
        new TypecheckException$();
    }

    @Override
    public final String toString() {
        return "TypecheckException";
    }

    @Override
    public TypecheckException apply(Position pos, String msg) {
        return new TypecheckException(pos, msg);
    }

    public Option<Tuple2<Position, String>> unapply(TypecheckException x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Position, String>>(new Tuple2<Position, String>(x$0.pos(), x$0.msg()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private TypecheckException$() {
        MODULE$ = this;
    }
}

