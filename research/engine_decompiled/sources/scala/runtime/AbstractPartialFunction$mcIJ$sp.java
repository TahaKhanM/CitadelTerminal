/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Function1$mcIJ$sp;
import scala.Function1$mcIJ$sp$class;
import scala.PartialFunction$;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxesRunTime;

public abstract class AbstractPartialFunction$mcIJ$sp
extends AbstractPartialFunction<Object, Object>
implements Function1$mcIJ$sp {
    @Override
    public int apply(long x) {
        return this.apply$mcIJ$sp(x);
    }

    @Override
    public int apply$mcIJ$sp(long x) {
        return BoxesRunTime.unboxToInt(this.applyOrElse(BoxesRunTime.boxToLong(x), PartialFunction$.MODULE$.empty()));
    }

    public AbstractPartialFunction$mcIJ$sp() {
        Function1$mcIJ$sp$class.$init$(this);
    }
}

