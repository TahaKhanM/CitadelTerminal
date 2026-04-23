/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcVJ$sp;
import scala.Function1$mcVJ$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcVJ$sp
extends AbstractPartialFunction<Object, BoxedUnit>
implements Function1$mcVJ$sp {
    @Override
    public void apply(long x) {
        this.apply$mcVJ$sp(x);
    }

    @Override
    public void apply$mcVJ$sp(long x) {
        this.applyOrElse(BoxesRunTime.boxToLong(x), PartialFunction$.MODULE$.empty());
    }

    public AbstractPartialFunction$mcVJ$sp() {
        Function1$mcVJ$sp$class.$init$(this);
    }
}

