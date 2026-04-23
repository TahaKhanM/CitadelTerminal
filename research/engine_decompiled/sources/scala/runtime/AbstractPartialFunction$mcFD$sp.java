/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcFD$sp;
import scala.Function1$mcFD$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcFD$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcFD$sp {
    @Override
    public float apply(double x) {
        return this.apply$mcFD$sp(x);
    }

    @Override
    public float apply$mcFD$sp(double x) {
        return BoxesRunTime.unboxToFloat(this.applyOrElse(BoxesRunTime.boxToDouble(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcFD$sp() {
        Function1$mcFD$sp$class.$init$(this);
    }
}

