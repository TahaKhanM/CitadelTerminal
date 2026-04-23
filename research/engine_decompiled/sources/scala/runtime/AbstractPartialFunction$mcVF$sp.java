/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcVF$sp;
import scala.Function1$mcVF$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcVF$sp
extends AbstractPartialFunction<Object, BoxedUnit>
implements Function1$mcVF$sp {
    @Override
    public void apply(float x) {
        this.apply$mcVF$sp(x);
    }

    @Override
    public void apply$mcVF$sp(float x) {
        this.applyOrElse(BoxesRunTime.boxToFloat(x), PartialFunction$.MODULE$.empty());
    }

    public AbstractPartialFunction$mcVF$sp() {
        Function1$mcVF$sp$class.$init$(this);
    }
}

