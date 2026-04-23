/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcDI$sp;
import scala.Function1$mcDI$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcDI$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcDI$sp {
    @Override
    public double apply(int x) {
        return this.apply$mcDI$sp(x);
    }

    @Override
    public double apply$mcDI$sp(int x) {
        return BoxesRunTime.unboxToDouble(this.applyOrElse(BoxesRunTime.boxToInteger(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcDI$sp() {
        Function1$mcDI$sp$class.$init$(this);
    }
}

