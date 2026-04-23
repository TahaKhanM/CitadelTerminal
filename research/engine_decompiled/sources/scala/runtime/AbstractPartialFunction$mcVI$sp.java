/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcVI$sp;
import scala.Function1$mcVI$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcVI$sp
extends AbstractPartialFunction<Object, BoxedUnit>
implements Function1$mcVI$sp {
    @Override
    public void apply(int x) {
        this.apply$mcVI$sp(x);
    }

    @Override
    public void apply$mcVI$sp(int x) {
        this.applyOrElse(BoxesRunTime.boxToInteger(x), PartialFunction$.MODULE$.empty());
    }

    public AbstractPartialFunction$mcVI$sp() {
        Function1$mcVI$sp$class.$init$(this);
    }
}

