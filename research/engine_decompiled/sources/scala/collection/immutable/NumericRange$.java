/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Range$;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Numeric$BigDecimalAsIfIntegral$;
import scala.math.Numeric$BigIntIsIntegral$;
import scala.math.Numeric$ByteIsIntegral$;
import scala.math.Numeric$CharIsIntegral$;
import scala.math.Numeric$DoubleAsIfIntegral$;
import scala.math.Numeric$FloatAsIfIntegral$;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.Numeric$LongIsIntegral$;
import scala.math.Numeric$ShortIsIntegral$;
import scala.math.Ordering;
import scala.math.Ordering$BigDecimal$;
import scala.math.Ordering$BigInt$;
import scala.math.Ordering$Byte$;
import scala.math.Ordering$Char$;
import scala.math.Ordering$Double$;
import scala.math.Ordering$Float$;
import scala.math.Ordering$Int$;
import scala.math.Ordering$Long$;
import scala.math.Ordering$Short$;
import scala.runtime.BoxesRunTime;

public final class NumericRange$
implements Serializable {
    public static final NumericRange$ MODULE$;
    private final Map<Numeric<?>, Ordering<?>> defaultOrdering;

    static {
        new NumericRange$();
    }

    public <T> int count(T start, T end, T step, boolean isInclusive, Integral<T> num) {
        int n;
        Object zero = num.zero();
        boolean upward = num.lt(start, end);
        boolean posStep = num.gt(step, zero);
        if (step == zero ? true : (step == null ? false : (step instanceof Number ? BoxesRunTime.equalsNumObject((Number)step, zero) : (step instanceof Character ? BoxesRunTime.equalsCharObject((Character)step, zero) : step.equals(zero))))) {
            throw new IllegalArgumentException("step cannot be 0.");
        }
        if (start == end ? true : (start == null ? false : (start instanceof Number ? BoxesRunTime.equalsNumObject((Number)start, end) : (start instanceof Character ? BoxesRunTime.equalsCharObject((Character)start, end) : start.equals(end))))) {
            n = isInclusive ? 1 : 0;
        } else if (upward != posStep) {
            n = 0;
        } else {
            Object object;
            int endside;
            int stepint;
            Object t;
            int endint;
            Object t2;
            int startint = num.toInt(start);
            Object t3 = num.fromInt(startint);
            if ((start == t3 ? true : (start == null ? false : (start instanceof Number ? BoxesRunTime.equalsNumObject((Number)start, t3) : (start instanceof Character ? BoxesRunTime.equalsCharObject((Character)start, t3) : start.equals(t3))))) && (end == (t2 = num.fromInt(endint = num.toInt(end))) ? true : (end == null ? false : (end instanceof Number ? BoxesRunTime.equalsNumObject((Number)end, t2) : (end instanceof Character ? BoxesRunTime.equalsCharObject((Character)end, t2) : end.equals(t2))))) && (step == (t = num.fromInt(stepint = num.toInt(step))) ? true : (step == null ? false : (step instanceof Number ? BoxesRunTime.equalsNumObject((Number)step, t) : (step instanceof Character ? BoxesRunTime.equalsCharObject((Character)step, t) : step.equals(t)))))) {
                return isInclusive ? Range$.MODULE$.inclusive(startint, endint, stepint).length() : Range$.MODULE$.apply(startint, endint, stepint).length();
            }
            Object one = num.one();
            Object limit = num.fromInt(Integer.MAX_VALUE);
            int startside = num.signum(start);
            if (startside * (endside = num.signum(end)) >= 0) {
                T diff2 = num.minus(end, start);
                Object quotient = this.check$1(num.quot(diff2, step), num, limit);
                Object remainder = num.minus(diff2, num.times(quotient, step));
                object = !isInclusive && (zero == remainder ? true : (zero == null ? false : (zero instanceof Number ? BoxesRunTime.equalsNumObject((Number)zero, remainder) : (zero instanceof Character ? BoxesRunTime.equalsCharObject((Character)zero, remainder) : zero.equals(remainder))))) ? quotient : this.check$1(num.plus(quotient, one), num, limit);
            } else {
                Object object2;
                Object negone = num.fromInt(-1);
                Object startlim = posStep ? negone : one;
                Object startdiff = num.minus(startlim, start);
                Object startq = this.check$1(num.quot(startdiff, step), num, limit);
                Object waypointA = (startq == zero ? true : (startq == null ? false : (startq instanceof Number ? BoxesRunTime.equalsNumObject((Number)startq, zero) : (startq instanceof Character ? BoxesRunTime.equalsCharObject((Character)startq, zero) : startq.equals(zero))))) ? start : num.plus(start, num.times(startq, step));
                T waypointB = num.plus(waypointA, step);
                if (num.lt(waypointB, end) != upward) {
                    object2 = isInclusive && (waypointB == end ? true : (waypointB == null ? false : (waypointB instanceof Number ? BoxesRunTime.equalsNumObject((Number)waypointB, end) : (waypointB instanceof Character ? BoxesRunTime.equalsCharObject((Character)waypointB, end) : waypointB.equals(end))))) ? num.plus(startq, num.fromInt(2)) : num.plus(startq, one);
                } else {
                    T last2;
                    T enddiff = num.minus(end, waypointB);
                    Object endq = this.check$1(num.quot(enddiff, step), num, limit);
                    Object object3 = last2 = (endq == zero ? true : (endq == null ? false : (endq instanceof Number ? BoxesRunTime.equalsNumObject((Number)endq, zero) : (endq instanceof Character ? BoxesRunTime.equalsCharObject((Character)endq, zero) : endq.equals(zero))))) ? waypointB : num.plus(waypointB, num.times(endq, step));
                    object2 = num.plus(startq, num.plus(endq, !isInclusive && (last2 == end ? true : (last2 == null ? false : (last2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)last2, end) : (last2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)last2, end) : last2.equals(end))))) ? one : num.fromInt(2)));
                }
                object = this.check$1(object2, num, limit);
            }
            n = num.toInt(object);
        }
        return n;
    }

    public <T> NumericRange.Exclusive<T> apply(T start, T end, T step, Integral<T> num) {
        return new NumericRange.Exclusive<T>(start, end, step, num);
    }

    public <T> NumericRange.Inclusive<T> inclusive(T start, T end, T step, Integral<T> num) {
        return new NumericRange.Inclusive<T>(start, end, step, num);
    }

    public Map<Numeric<?>, Ordering<?>> defaultOrdering() {
        return this.defaultOrdering;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private final Object check$1(Object t, Integral num$1, Object limit$1) {
        if (num$1.gt(t, limit$1)) {
            throw new IllegalArgumentException("More than Int.MaxValue elements.");
        }
        return t;
    }

    private NumericRange$() {
        MODULE$ = this;
        Tuple2[] tuple2Array = new Tuple2[9];
        Ordering$BigInt$ ordering$BigInt$ = Ordering$BigInt$.MODULE$;
        Numeric$BigIntIsIntegral$ numeric$BigIntIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$BigIntIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<Numeric$BigIntIsIntegral$, Ordering$BigInt$>(numeric$BigIntIsIntegral$, ordering$BigInt$);
        Ordering$Int$ ordering$Int$ = Ordering$Int$.MODULE$;
        Numeric$IntIsIntegral$ numeric$IntIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$IntIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<Numeric$IntIsIntegral$, Ordering$Int$>(numeric$IntIsIntegral$, ordering$Int$);
        Ordering$Short$ ordering$Short$ = Ordering$Short$.MODULE$;
        Numeric$ShortIsIntegral$ numeric$ShortIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$ShortIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<Numeric$ShortIsIntegral$, Ordering$Short$>(numeric$ShortIsIntegral$, ordering$Short$);
        Ordering$Byte$ ordering$Byte$ = Ordering$Byte$.MODULE$;
        Numeric$ByteIsIntegral$ numeric$ByteIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$ByteIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<Numeric$ByteIsIntegral$, Ordering$Byte$>(numeric$ByteIsIntegral$, ordering$Byte$);
        Ordering$Char$ ordering$Char$ = Ordering$Char$.MODULE$;
        Numeric$CharIsIntegral$ numeric$CharIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$CharIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<Numeric$CharIsIntegral$, Ordering$Char$>(numeric$CharIsIntegral$, ordering$Char$);
        Ordering$Long$ ordering$Long$ = Ordering$Long$.MODULE$;
        Numeric$LongIsIntegral$ numeric$LongIsIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$LongIsIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<Numeric$LongIsIntegral$, Ordering$Long$>(numeric$LongIsIntegral$, ordering$Long$);
        Ordering$Float$ ordering$Float$ = Ordering$Float$.MODULE$;
        Numeric$FloatAsIfIntegral$ numeric$FloatAsIfIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$FloatAsIfIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<Numeric$FloatAsIfIntegral$, Ordering$Float$>(numeric$FloatAsIfIntegral$, ordering$Float$);
        Ordering$Double$ ordering$Double$ = Ordering$Double$.MODULE$;
        Numeric$DoubleAsIfIntegral$ numeric$DoubleAsIfIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$DoubleAsIfIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$8 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[7] = new Tuple2<Numeric$DoubleAsIfIntegral$, Ordering$Double$>(numeric$DoubleAsIfIntegral$, ordering$Double$);
        Ordering$BigDecimal$ ordering$BigDecimal$ = Ordering$BigDecimal$.MODULE$;
        Numeric$BigDecimalAsIfIntegral$ numeric$BigDecimalAsIfIntegral$ = Predef$.MODULE$.ArrowAssoc(Numeric$BigDecimalAsIfIntegral$.MODULE$);
        Predef$ArrowAssoc$ predef$ArrowAssoc$9 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[8] = new Tuple2<Numeric$BigDecimalAsIfIntegral$, Ordering$BigDecimal$>(numeric$BigDecimalAsIfIntegral$, ordering$BigDecimal$);
        this.defaultOrdering = (Map)Map$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
    }
}

