/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcJJ$sp;
import scala.Function1$mcJJ$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcJJ$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcJJ$sp {
    @Override
    public long apply(long x) {
        return this.apply$mcJJ$sp(x);
    }

    @Override
    public long apply$mcJJ$sp(long x) {
        return BoxesRunTime.unboxToLong(this.applyOrElse(BoxesRunTime.boxToLong(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcJJ$sp() {
        Function1$mcJJ$sp$class.$init$(this);
    }
}

