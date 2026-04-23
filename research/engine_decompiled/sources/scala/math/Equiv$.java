/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import java.util.Comparator;
import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.math.Equiv;
import scala.math.LowPriorityEquiv;
import scala.math.LowPriorityEquiv$class;
import scala.runtime.BoxesRunTime;

public final class Equiv$
implements LowPriorityEquiv,
Serializable {
    public static final Equiv$ MODULE$;

    static {
        new Equiv$();
    }

    @Override
    public <T> Equiv<T> universalEquiv() {
        return LowPriorityEquiv$class.universalEquiv(this);
    }

    public <T> Equiv<T> reference() {
        return new Equiv<T>(){

            public boolean equiv(T x, T y) {
                return x == y;
            }
        };
    }

    public <T> Equiv<T> universal() {
        return new Equiv<T>(){

            public boolean equiv(T x, T y) {
                return x == y ? true : (x == null ? false : (x instanceof Number ? BoxesRunTime.equalsNumObject((Number)x, y) : (x instanceof Character ? BoxesRunTime.equalsCharObject((Character)x, y) : x.equals(y))));
            }
        };
    }

    public <T> Equiv<T> fromComparator(Comparator<T> cmp) {
        return new Equiv<T>(cmp){
            private final Comparator cmp$1;

            public boolean equiv(T x, T y) {
                return this.cmp$1.compare(x, y) == 0;
            }
            {
                this.cmp$1 = cmp$1;
            }
        };
    }

    public <T> Equiv<T> fromFunction(Function2<T, T, Object> cmp) {
        return new Equiv<T>(cmp){
            private final Function2 cmp$2;

            public boolean equiv(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
            }
            {
                this.cmp$2 = cmp$2;
            }
        };
    }

    public <T, S> Equiv<T> by(Function1<T, S> f, Equiv<S> evidence$1) {
        Serializable serializable = new Serializable(f, evidence$1){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;
            private final Equiv evidence$1$1;

            public final boolean apply(T x, T y) {
                Equiv equiv = this.evidence$1$1;
                Predef$ predef$ = Predef$.MODULE$;
                return equiv.equiv(this.f$1.apply(x), this.f$1.apply(y));
            }
            {
                this.f$1 = f$1;
                this.evidence$1$1 = evidence$1$1;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    public <T> Equiv<T> apply(Equiv<T> evidence$2) {
        Predef$ predef$ = Predef$.MODULE$;
        return evidence$2;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Equiv$() {
        MODULE$ = this;
        LowPriorityEquiv$class.$init$(this);
    }
}

