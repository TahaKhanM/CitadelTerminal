/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcZI$sp;
import scala.Function1$mcZI$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcZI$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcZI$sp {
    @Override
    public boolean apply(int x) {
        return this.apply$mcZI$sp(x);
    }

    @Override
    public boolean apply$mcZI$sp(int x) {
        return BoxesRunTime.unboxToBoolean(this.applyOrElse(BoxesRunTime.boxToInteger(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcZI$sp() {
        Function1$mcZI$sp$class.$init$(this);
    }
}

