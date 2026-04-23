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
import scala.reflect.macros.ParseException;
import scala.runtime.AbstractFunction2;

public final class ParseException$
extends AbstractFunction2<Position, String, ParseException>
implements Serializable {
    public static final ParseException$ MODULE$;

    static {
        new ParseException$();
    }

    @Override
    public final String toString() {
        return "ParseException";
    }

    @Override
    public ParseException apply(Position pos, String msg) {
        return new ParseException(pos, msg);
    }

    public Option<Tuple2<Position, String>> unapply(ParseException x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Position, String>>(new Tuple2<Position, String>(x$0.pos(), x$0.msg()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParseException$() {
        MODULE$ = this;
    }
}

