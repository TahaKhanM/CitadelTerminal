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
import scala.collection.script.Update;

public final class Update$
implements Serializable {
    public static final Update$ MODULE$;

    static {
        new Update$();
    }

    public final String toString() {
        return "Update";
    }

    public <A> Update<A> apply(Location location, A elem) {
        return new Update<A>(location, elem);
    }

    public <A> Option<Tuple2<Location, A>> unapply(Update<A> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Location, A>>(new Tuple2<Location, A>(x$0.location(), x$0.elem()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Update$() {
        MODULE$ = this;
    }
}

