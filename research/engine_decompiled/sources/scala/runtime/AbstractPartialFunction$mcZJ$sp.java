/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcZJ$sp;
import scala.Function1$mcZJ$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcZJ$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcZJ$sp {
    @Override
    public boolean apply(long x) {
        return this.apply$mcZJ$sp(x);
    }

    @Override
    public boolean apply$mcZJ$sp(long x) {
        return BoxesRunTime.unboxToBoolean(this.applyOrElse(BoxesRunTime.boxToLong(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcZJ$sp() {
        Function1$mcZJ$sp$class.$init$(this);
    }
}

