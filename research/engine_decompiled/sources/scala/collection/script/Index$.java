/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.script;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.script.Index;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;

public final class Index$
extends AbstractFunction1<Object, Index>
implements Serializable {
    public static final Index$ MODULE$;

    static {
        new Index$();
    }

    @Override
    public final String toString() {
        return "Index";
    }

    @Override
    public Index apply(int n) {
        return new Index(n);
    }

    public Option<Object> unapply(Index x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Integer>(BoxesRunTime.boxToInteger(x$0.n()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Index$() {
        MODULE$ = this;
    }
}

