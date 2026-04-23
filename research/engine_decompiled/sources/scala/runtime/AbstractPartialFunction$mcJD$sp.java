/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcJD$sp;
import scala.Function1$mcJD$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcJD$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcJD$sp {
    @Override
    public long apply(double x) {
        return this.apply$mcJD$sp(x);
    }

    @Override
    public long apply$mcJD$sp(double x) {
        return BoxesRunTime.unboxToLong(this.applyOrElse(BoxesRunTime.boxToDouble(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcJD$sp() {
        Function1$mcJD$sp$class.$init$(this);
    }
}

