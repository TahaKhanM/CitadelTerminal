/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcFJ$sp;
import scala.Function1$mcFJ$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcFJ$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcFJ$sp {
    @Override
    public float apply(long x) {
        return this.apply$mcFJ$sp(x);
    }

    @Override
    public float apply$mcFJ$sp(long x) {
        return BoxesRunTime.unboxToFloat(this.applyOrElse(BoxesRunTime.boxToLong(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcFJ$sp() {
        Function1$mcFJ$sp$class.$init$(this);
    }
}

