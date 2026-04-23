/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import java.util.Comparator;
import scala.Function1;
import scala.Some;
import scala.math.LowPriorityOrderingImplicits;
import scala.math.Ordering;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;

public abstract class LowPriorityOrderingImplicits$class {
    public static Ordering ordered(LowPriorityOrderingImplicits $this, Function1 evidence$1) {
        return new Ordering<A>($this, evidence$1){
            private final Function1 evidence$1$1;

            public Some<Object> tryCompare(A x, A y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(A x, A y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(A x, A y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(A x, A y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(A x, A y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(A x, A y) {
                return Ordering$class.equiv(this, x, y);
            }

            public A max(A x, A y) {
                return (A)Ordering$class.max(this, x, y);
            }

            public A min(A x, A y) {
                return (A)Ordering$class.min(this, x, y);
            }

            public Ordering<A> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, A> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(A lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(A x, A y) {
                return ((Comparable)this.evidence$1$1.apply(x)).compareTo(y);
            }
            {
                this.evidence$1$1 = evidence$1$1;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public static Ordering comparatorToOrdering(LowPriorityOrderingImplicits $this, Comparator cmp) {
        return new Ordering<A>($this, cmp){
            private final Comparator cmp$2;

            public Some<Object> tryCompare(A x, A y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(A x, A y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(A x, A y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(A x, A y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(A x, A y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(A x, A y) {
                return Ordering$class.equiv(this, x, y);
            }

            public A max(A x, A y) {
                return (A)Ordering$class.max(this, x, y);
            }

            public A min(A x, A y) {
                return (A)Ordering$class.min(this, x, y);
            }

            public Ordering<A> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, A> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(A lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(A x, A y) {
                return this.cmp$2.compare(x, y);
            }
            {
                this.cmp$2 = cmp$2;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public static void $init$(LowPriorityOrderingImplicits $this) {
    }
}

