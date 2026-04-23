/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.Set;
import scala.collection.parallel.CompositeThrowable;
import scala.runtime.AbstractFunction1;

public final class CompositeThrowable$
extends AbstractFunction1<Set<Throwable>, CompositeThrowable>
implements Serializable {
    public static final CompositeThrowable$ MODULE$;

    static {
        new CompositeThrowable$();
    }

    @Override
    public final String toString() {
        return "CompositeThrowable";
    }

    @Override
    public CompositeThrowable apply(Set<Throwable> throwables) {
        return new CompositeThrowable(throwables);
    }

    public Option<Set<Throwable>> unapply(CompositeThrowable x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Set<Throwable>>(x$0.throwables());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private CompositeThrowable$() {
        MODULE$ = this;
    }
}

