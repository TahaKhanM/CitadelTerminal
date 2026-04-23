/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcJF$sp;
import scala.Function1$mcJF$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcJF$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcJF$sp {
    @Override
    public long apply(float x) {
        return this.apply$mcJF$sp(x);
    }

    @Override
    public long apply$mcJF$sp(float x) {
        return BoxesRunTime.unboxToLong(this.applyOrElse(BoxesRunTime.boxToFloat(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcJF$sp() {
        Function1$mcJF$sp$class.$init$(this);
    }
}

