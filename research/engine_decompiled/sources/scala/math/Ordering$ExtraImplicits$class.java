/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Function1;
import scala.Some;
import scala.collection.Iterator;
import scala.math.Ordering;
import scala.math.Ordering$Boolean$;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;

public abstract class Ordering$ExtraImplicits$class {
    public static Ordering seqDerivedOrdering(Ordering.ExtraImplicits $this, Ordering ord) {
        return new Ordering<CC>($this, ord){
            private final Ordering ord$4;

            public Some tryCompare(Object x, Object y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean lteq(Object x, Object y) {
                return Ordering$class.lteq(this, x, y);
            }

            public boolean gteq(Object x, Object y) {
                return Ordering$class.gteq(this, x, y);
            }

            public boolean lt(Object x, Object y) {
                return Ordering$class.lt(this, x, y);
            }

            public boolean gt(Object x, Object y) {
                return Ordering$class.gt(this, x, y);
            }

            public boolean equiv(Object x, Object y) {
                return Ordering$class.equiv(this, x, y);
            }

            public Object max(Object x, Object y) {
                return Ordering$class.max(this, x, y);
            }

            public Object min(Object x, Object y) {
                return Ordering$class.min(this, x, y);
            }

            public Ordering<CC> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, CC> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(CC x, CC y) {
                Iterator<A> xe = x.iterator();
                Iterator<A> ye = y.iterator();
                while (xe.hasNext() && ye.hasNext()) {
                    int res = this.ord$4.compare(xe.next(), ye.next());
                    if (res == 0) continue;
                    return res;
                }
                return Ordering$Boolean$.MODULE$.compare(xe.hasNext(), ye.hasNext());
            }
            {
                this.ord$4 = ord$4;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public static Ordering.Ops infixOrderingOps(Ordering.ExtraImplicits $this, Object x, Ordering ord) {
        return new Ordering.Ops(ord, x);
    }

    public static void $init$(Ordering.ExtraImplicits $this) {
    }
}

