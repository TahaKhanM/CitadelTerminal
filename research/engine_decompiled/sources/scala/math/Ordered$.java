/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.math.Ordering;

public final class Ordered$ {
    public static final Ordered$ MODULE$;

    static {
        new Ordered$();
    }

    public <T> Ordered<T> orderingToOrdered(T x, Ordering<T> ord) {
        return new Ordered<T>(x, ord){
            private final Object x$1;
            private final Ordering ord$1;

            public boolean $less(T that) {
                return Ordered$class.$less(this, that);
            }

            public boolean $greater(T that) {
                return Ordered$class.$greater(this, that);
            }

            public boolean $less$eq(T that) {
                return Ordered$class.$less$eq(this, that);
            }

            public boolean $greater$eq(T that) {
                return Ordered$class.$greater$eq(this, that);
            }

            public int compareTo(T that) {
                return Ordered$class.compareTo(this, that);
            }

            public int compare(T that) {
                return this.ord$1.compare(this.x$1, that);
            }
            {
                this.x$1 = x$1;
                this.ord$1 = ord$1;
                Ordered$class.$init$(this);
            }
        };
    }

    private Ordered$() {
        MODULE$ = this;
    }
}

