/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple4;

public final class Tuple4$
implements Serializable {
    public static final Tuple4$ MODULE$;

    static {
        new Tuple4$();
    }

    public final String toString() {
        return "Tuple4";
    }

    public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> apply(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    public <T1, T2, T3, T4> Option<Tuple4<T1, T2, T3, T4>> unapply(Tuple4<T1, T2, T3, T4> x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple4<T1, T2, T3, T4>>(new Tuple4<T1, T2, T3, T4>(x$0._1(), x$0._2(), x$0._3(), x$0._4()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Tuple4$() {
        MODULE$ = this;
    }
}

