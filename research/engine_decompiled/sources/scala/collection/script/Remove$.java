/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.script;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.script.Location;
import scala.collection.script.Remove;

public final class Remove$
implements Serializable {
    public static final Remove$ MODULE$;

    static {
        new Remove$();
    }

    public final String toString() {
        return "Remove";
    }

    public <A> Remove<A> apply(Location location, A elem) {
        return new Remove<A>(location, elem);
    }

    public <A> Option<Tuple2<Location, A>> unapply(Remove<A> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Location, A>>(new Tuple2<Location, A>(x$0.location(), x$0.elem()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Remove$() {
        MODULE$ = this;
    }
}

