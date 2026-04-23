/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcIF$sp;
import scala.Function1$mcIF$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcIF$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcIF$sp {
    @Override
    public int apply(float x) {
        return this.apply$mcIF$sp(x);
    }

    @Override
    public int apply$mcIF$sp(float x) {
        return BoxesRunTime.unboxToInt(this.applyOrElse(BoxesRunTime.boxToFloat(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcIF$sp() {
        Function1$mcIF$sp$class.$init$(this);
    }
}

