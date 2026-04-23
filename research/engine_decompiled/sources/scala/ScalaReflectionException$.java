/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.None$;
import scala.Option;
import scala.ScalaReflectionException;
import scala.Serializable;
import scala.Some;
import scala.runtime.AbstractFunction1;

public final class ScalaReflectionException$
extends AbstractFunction1<String, ScalaReflectionException>
implements Serializable {
    public static final ScalaReflectionException$ MODULE$;

    static {
        new ScalaReflectionException$();
    }

    @Override
    public final String toString() {
        return "ScalaReflectionException";
    }

    @Override
    public ScalaReflectionException apply(String msg) {
        return new ScalaReflectionException(msg);
    }

    public Option<String> unapply(ScalaReflectionException x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.msg());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ScalaReflectionException$() {
        MODULE$ = this;
    }
}

