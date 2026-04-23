/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcZD$sp;
import scala.Function1$mcZD$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcZD$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcZD$sp {
    @Override
    public boolean apply(double x) {
        return this.apply$mcZD$sp(x);
    }

    @Override
    public boolean apply$mcZD$sp(double x) {
        return BoxesRunTime.unboxToBoolean(this.applyOrElse(BoxesRunTime.boxToDouble(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcZD$sp() {
        Function1$mcZD$sp$class.$init$(this);
    }
}

