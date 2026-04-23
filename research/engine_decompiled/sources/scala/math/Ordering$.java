/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import java.util.Comparator;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.math.LowPriorityOrderingImplicits;
import scala.math.LowPriorityOrderingImplicits$class;
import scala.math.Ordering;
import scala.math.Ordering$Boolean$;
import scala.math.Ordering$OptionOrdering$class;
import scala.math.Ordering$class;
import scala.math.PartialOrdering$class;
import scala.runtime.BoxesRunTime;

public final class Ordering$
implements LowPriorityOrderingImplicits,
Serializable {
    public static final Ordering$ MODULE$;

    static {
        new Ordering$();
    }

    @Override
    public <A> Ordering<A> ordered(Function1<A, Comparable<A>> evidence$1) {
        return LowPriorityOrderingImplicits$class.ordered(this, evidence$1);
    }

    @Override
    public <A> Ordering<A> comparatorToOrdering(Comparator<A> cmp) {
        return LowPriorityOrderingImplicits$class.comparatorToOrdering(this, cmp);
    }

    public <T> Ordering<T> apply(Ordering<T> ord) {
        return ord;
    }

    public <T> Ordering<T> fromLessThan(Function2<T, T, Object> cmp) {
        return new Ordering<T>(cmp){
            private final Function2 cmp$1;

            public Some<Object> tryCompare(T x, T y) {
                return Ordering$class.tryCompare(this, x, y);
            }

            public boolean equiv(T x, T y) {
                return Ordering$class.equiv(this, x, y);
            }

            public T max(T x, T y) {
                return (T)Ordering$class.max(this, x, y);
            }

            public T min(T x, T y) {
                return (T)Ordering$class.min(this, x, y);
            }

            public Ordering<T> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, T> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(T lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y)) ? -1 : (BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x)) ? 1 : 0);
            }

            public boolean lt(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
            }

            public boolean gt(T x, T y) {
                return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
            }

            public boolean gteq(T x, T y) {
                return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
            }

            public boolean lteq(T x, T y) {
                return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
            }
            {
                this.cmp$1 = cmp$1;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T, S> Ordering<T> by(Function1<T, S> f, Ordering<S> ord) {
        Serializable serializable = new Serializable(f, ord){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;
            private final Ordering ord$3;

            public final boolean apply(T x, T y) {
                return this.ord$3.lt(this.f$1.apply(x), this.f$1.apply(y));
            }
            {
                this.f$1 = f$1;
                this.ord$3 = ord$3;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    public <T> Ordering<Option<T>> Option(Ordering<T> ord) {
        return new Ordering.OptionOrdering<T>(ord){
            private final Ordering<T> optionOrdering;

            public int compare(Option<T> x, Option<T> y) {
                return Ordering$OptionOrdering$class.compare(this, x, y);
            }

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

            public Ordering<Option<T>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Option<T>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public Ordering<T> optionOrdering() {
                return this.optionOrdering;
            }
            {
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
                Ordering$OptionOrdering$class.$init$(this);
                this.optionOrdering = ord$2;
            }
        };
    }

    public <T> Ordering<Iterable<T>> Iterable(Ordering<T> ord) {
        return new Ordering<Iterable<T>>(ord){
            private final Ordering ord$1;

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

            public Ordering<Iterable<T>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Iterable<T>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Iterable<T> x, Iterable<T> y) {
                Iterator<A> xe = x.iterator();
                Iterator<A> ye = y.iterator();
                while (xe.hasNext() && ye.hasNext()) {
                    int res = this.ord$1.compare(xe.next(), ye.next());
                    if (res == 0) continue;
                    return res;
                }
                return Ordering$Boolean$.MODULE$.compare(xe.hasNext(), ye.hasNext());
            }
            {
                this.ord$1 = ord$1;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2> Ordering<Tuple2<T1, T2>> Tuple2(Ordering<T1> ord1, Ordering<T2> ord2) {
        return new Ordering<Tuple2<T1, T2>>(ord1, ord2){
            private final Ordering ord1$8;
            private final Ordering ord2$8;

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

            public Ordering<Tuple2<T1, T2>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple2<T1, T2>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple2<T1, T2> x, Tuple2<T1, T2> y) {
                int compare1 = this.ord1$8.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$8.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                return 0;
            }
            {
                this.ord1$8 = ord1$8;
                this.ord2$8 = ord2$8;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3> Ordering<Tuple3<T1, T2, T3>> Tuple3(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3) {
        return new Ordering<Tuple3<T1, T2, T3>>(ord1, ord2, ord3){
            private final Ordering ord1$7;
            private final Ordering ord2$7;
            private final Ordering ord3$7;

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

            public Ordering<Tuple3<T1, T2, T3>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple3<T1, T2, T3>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple3<T1, T2, T3> x, Tuple3<T1, T2, T3> y) {
                int compare1 = this.ord1$7.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$7.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$7.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                return 0;
            }
            {
                this.ord1$7 = ord1$7;
                this.ord2$7 = ord2$7;
                this.ord3$7 = ord3$7;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4> Ordering<Tuple4<T1, T2, T3, T4>> Tuple4(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4) {
        return new Ordering<Tuple4<T1, T2, T3, T4>>(ord1, ord2, ord3, ord4){
            private final Ordering ord1$6;
            private final Ordering ord2$6;
            private final Ordering ord3$6;
            private final Ordering ord4$6;

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

            public Ordering<Tuple4<T1, T2, T3, T4>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple4<T1, T2, T3, T4>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple4<T1, T2, T3, T4> x, Tuple4<T1, T2, T3, T4> y) {
                int compare1 = this.ord1$6.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$6.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$6.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$6.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                return 0;
            }
            {
                this.ord1$6 = ord1$6;
                this.ord2$6 = ord2$6;
                this.ord3$6 = ord3$6;
                this.ord4$6 = ord4$6;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4, T5> Ordering<Tuple5<T1, T2, T3, T4, T5>> Tuple5(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4, Ordering<T5> ord5) {
        return new Ordering<Tuple5<T1, T2, T3, T4, T5>>(ord1, ord2, ord3, ord4, ord5){
            private final Ordering ord1$5;
            private final Ordering ord2$5;
            private final Ordering ord3$5;
            private final Ordering ord4$5;
            private final Ordering ord5$5;

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

            public Ordering<Tuple5<T1, T2, T3, T4, T5>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple5<T1, T2, T3, T4, T5>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple5<T1, T2, T3, T4, T5> x, Tuple5<T1, T2, T3, T4, T5> y) {
                int compare1 = this.ord1$5.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$5.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$5.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$5.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                int compare5 = this.ord5$5.compare(x._5(), y._5());
                if (compare5 != 0) {
                    return compare5;
                }
                return 0;
            }
            {
                this.ord1$5 = ord1$5;
                this.ord2$5 = ord2$5;
                this.ord3$5 = ord3$5;
                this.ord4$5 = ord4$5;
                this.ord5$5 = ord5$5;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4, T5, T6> Ordering<Tuple6<T1, T2, T3, T4, T5, T6>> Tuple6(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4, Ordering<T5> ord5, Ordering<T6> ord6) {
        return new Ordering<Tuple6<T1, T2, T3, T4, T5, T6>>(ord1, ord2, ord3, ord4, ord5, ord6){
            private final Ordering ord1$4;
            private final Ordering ord2$4;
            private final Ordering ord3$4;
            private final Ordering ord4$4;
            private final Ordering ord5$4;
            private final Ordering ord6$4;

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

            public Ordering<Tuple6<T1, T2, T3, T4, T5, T6>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple6<T1, T2, T3, T4, T5, T6>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple6<T1, T2, T3, T4, T5, T6> x, Tuple6<T1, T2, T3, T4, T5, T6> y) {
                int compare1 = this.ord1$4.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$4.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$4.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$4.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                int compare5 = this.ord5$4.compare(x._5(), y._5());
                if (compare5 != 0) {
                    return compare5;
                }
                int compare6 = this.ord6$4.compare(x._6(), y._6());
                if (compare6 != 0) {
                    return compare6;
                }
                return 0;
            }
            {
                this.ord1$4 = ord1$4;
                this.ord2$4 = ord2$4;
                this.ord3$4 = ord3$4;
                this.ord4$4 = ord4$4;
                this.ord5$4 = ord5$4;
                this.ord6$4 = ord6$4;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4, T5, T6, T7> Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>> Tuple7(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4, Ordering<T5> ord5, Ordering<T6> ord6, Ordering<T7> ord7) {
        return new Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>>(ord1, ord2, ord3, ord4, ord5, ord6, ord7){
            private final Ordering ord1$3;
            private final Ordering ord2$3;
            private final Ordering ord3$3;
            private final Ordering ord4$3;
            private final Ordering ord5$3;
            private final Ordering ord6$3;
            private final Ordering ord7$3;

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

            public Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple7<T1, T2, T3, T4, T5, T6, T7>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple7<T1, T2, T3, T4, T5, T6, T7> x, Tuple7<T1, T2, T3, T4, T5, T6, T7> y) {
                int compare1 = this.ord1$3.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$3.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$3.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$3.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                int compare5 = this.ord5$3.compare(x._5(), y._5());
                if (compare5 != 0) {
                    return compare5;
                }
                int compare6 = this.ord6$3.compare(x._6(), y._6());
                if (compare6 != 0) {
                    return compare6;
                }
                int compare7 = this.ord7$3.compare(x._7(), y._7());
                if (compare7 != 0) {
                    return compare7;
                }
                return 0;
            }
            {
                this.ord1$3 = ord1$3;
                this.ord2$3 = ord2$3;
                this.ord3$3 = ord3$3;
                this.ord4$3 = ord4$3;
                this.ord5$3 = ord5$3;
                this.ord6$3 = ord6$3;
                this.ord7$3 = ord7$3;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> Tuple8(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4, Ordering<T5> ord5, Ordering<T6> ord6, Ordering<T7> ord7, Ordering<T8> ord8) {
        return new Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8){
            private final Ordering ord1$2;
            private final Ordering ord2$2;
            private final Ordering ord3$2;
            private final Ordering ord4$2;
            private final Ordering ord5$2;
            private final Ordering ord6$2;
            private final Ordering ord7$2;
            private final Ordering ord8$2;

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

            public Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> x, Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> y) {
                int compare1 = this.ord1$2.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$2.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$2.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$2.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                int compare5 = this.ord5$2.compare(x._5(), y._5());
                if (compare5 != 0) {
                    return compare5;
                }
                int compare6 = this.ord6$2.compare(x._6(), y._6());
                if (compare6 != 0) {
                    return compare6;
                }
                int compare7 = this.ord7$2.compare(x._7(), y._7());
                if (compare7 != 0) {
                    return compare7;
                }
                int compare8 = this.ord8$2.compare(x._8(), y._8());
                if (compare8 != 0) {
                    return compare8;
                }
                return 0;
            }
            {
                this.ord1$2 = ord1$2;
                this.ord2$2 = ord2$2;
                this.ord3$2 = ord3$2;
                this.ord4$2 = ord4$2;
                this.ord5$2 = ord5$2;
                this.ord6$2 = ord6$2;
                this.ord7$2 = ord7$2;
                this.ord8$2 = ord8$2;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> Tuple9(Ordering<T1> ord1, Ordering<T2> ord2, Ordering<T3> ord3, Ordering<T4> ord4, Ordering<T5> ord5, Ordering<T6> ord6, Ordering<T7> ord7, Ordering<T8> ord8, Ordering<T9> ord9) {
        return new Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8, ord9){
            private final Ordering ord1$1;
            private final Ordering ord2$1;
            private final Ordering ord3$1;
            private final Ordering ord4$1;
            private final Ordering ord5$1;
            private final Ordering ord6$1;
            private final Ordering ord7$1;
            private final Ordering ord8$1;
            private final Ordering ord9$1;

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

            public Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> reverse() {
                return Ordering$class.reverse(this);
            }

            public <U> Ordering<U> on(Function1<U, Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> f) {
                return Ordering$class.on(this, f);
            }

            public Ordering.Ops mkOrderingOps(Object lhs) {
                return Ordering$class.mkOrderingOps(this, lhs);
            }

            public int compare(Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> x, Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> y) {
                int compare1 = this.ord1$1.compare(x._1(), y._1());
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = this.ord2$1.compare(x._2(), y._2());
                if (compare2 != 0) {
                    return compare2;
                }
                int compare3 = this.ord3$1.compare(x._3(), y._3());
                if (compare3 != 0) {
                    return compare3;
                }
                int compare4 = this.ord4$1.compare(x._4(), y._4());
                if (compare4 != 0) {
                    return compare4;
                }
                int compare5 = this.ord5$1.compare(x._5(), y._5());
                if (compare5 != 0) {
                    return compare5;
                }
                int compare6 = this.ord6$1.compare(x._6(), y._6());
                if (compare6 != 0) {
                    return compare6;
                }
                int compare7 = this.ord7$1.compare(x._7(), y._7());
                if (compare7 != 0) {
                    return compare7;
                }
                int compare8 = this.ord8$1.compare(x._8(), y._8());
                if (compare8 != 0) {
                    return compare8;
                }
                int compare9 = this.ord9$1.compare(x._9(), y._9());
                if (compare9 != 0) {
                    return compare9;
                }
                return 0;
            }
            {
                this.ord1$1 = ord1$1;
                this.ord2$1 = ord2$1;
                this.ord3$1 = ord3$1;
                this.ord4$1 = ord4$1;
                this.ord5$1 = ord5$1;
                this.ord6$1 = ord6$1;
                this.ord7$1 = ord7$1;
                this.ord8$1 = ord8$1;
                this.ord9$1 = ord9$1;
                PartialOrdering$class.$init$(this);
                Ordering$class.$init$(this);
            }
        };
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Ordering$() {
        MODULE$ = this;
        LowPriorityOrderingImplicits$class.$init$(this);
    }
}

