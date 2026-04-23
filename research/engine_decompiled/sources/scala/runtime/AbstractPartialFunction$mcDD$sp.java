/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcDD$sp;
import scala.Function1$mcDD$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcDD$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcDD$sp {
    @Override
    public double apply(double x) {
        return this.apply$mcDD$sp(x);
    }

    @Override
    public double apply$mcDD$sp(double x) {
        return BoxesRunTime.unboxToDouble(this.applyOrElse(BoxesRunTime.boxToDouble(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcDD$sp() {
        Function1$mcDD$sp$class.$init$(this);
    }
}

