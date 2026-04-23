/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.RemainsIterator;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class AugmentedIterableIterator$class {
    /*
     * WARNING - void declaration
     */
    public static int count(AugmentedIterableIterator $this, Function1 p) {
        void var2_2;
        int i = 0;
        while ($this.hasNext()) {
            if (!BoxesRunTime.unboxToBoolean(p.apply($this.next()))) continue;
            ++i;
        }
        return (int)var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Object reduce(AugmentedIterableIterator $this, Function2 op) {
        void var2_2;
        Object r = $this.next();
        while ($this.hasNext()) {
            r = op.apply(r, $this.next());
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Object fold(AugmentedIterableIterator $this, Object z, Function2 op) {
        void var3_3;
        Object r = z;
        while ($this.hasNext()) {
            r = op.apply(r, $this.next());
        }
        return var3_3;
    }

    /*
     * WARNING - void declaration
     */
    public static Object sum(AugmentedIterableIterator $this, Numeric num) {
        void var2_2;
        Object r = num.zero();
        while ($this.hasNext()) {
            r = num.plus(r, $this.next());
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Object product(AugmentedIterableIterator $this, Numeric num) {
        void var2_2;
        Object r = num.one();
        while ($this.hasNext()) {
            r = num.times(r, $this.next());
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Object min(AugmentedIterableIterator $this, Ordering ord) {
        void var2_2;
        Object r = $this.next();
        while ($this.hasNext()) {
            Object curr = $this.next();
            if (!ord.lteq(curr, r)) continue;
            r = curr;
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Object max(AugmentedIterableIterator $this, Ordering ord) {
        void var2_2;
        Object r = $this.next();
        while ($this.hasNext()) {
            Object curr = $this.next();
            if (!ord.gteq(curr, r)) continue;
            r = curr;
        }
        return var2_2;
    }

    public static void copyToArray(AugmentedIterableIterator $this, Object array, int from2, int len) {
        int until2 = from2 + len;
        for (int i = from2; i < until2 && $this.hasNext(); ++i) {
            ScalaRunTime$.MODULE$.array_update(array, i, $this.next());
        }
    }

    public static Object reduceLeft(AugmentedIterableIterator $this, int howmany, Function2 op) {
        Object u = $this.next();
        for (int i = howmany - 1; i > 0 && $this.hasNext(); --i) {
            u = op.apply(u, $this.next());
        }
        return u;
    }

    public static Combiner map2combiner(AugmentedIterableIterator $this, Function1 f, Combiner cb) {
        if ($this.isRemainingCheap()) {
            cb.sizeHint($this.remaining());
        }
        while ($this.hasNext()) {
            cb.$plus$eq(f.apply($this.next()));
        }
        return cb;
    }

    public static Combiner collect2combiner(AugmentedIterableIterator $this, PartialFunction pf, Combiner cb) {
        Function1 runWith2 = pf.runWith(new Serializable($this, cb){
            public static final long serialVersionUID = 0L;
            private final Combiner cb$1;

            public final Combiner<S, That> apply(S x$1) {
                return (Combiner)this.cb$1.$plus$eq(x$1);
            }
            {
                this.cb$1 = cb$1;
            }
        });
        while ($this.hasNext()) {
            Object curr = $this.next();
            runWith2.apply(curr);
        }
        return cb;
    }

    public static Combiner flatmap2combiner(AugmentedIterableIterator $this, Function1 f, Combiner cb) {
        while ($this.hasNext()) {
            TraversableOnce traversable = ((GenTraversableOnce)f.apply($this.next())).seq();
            Growable growable = traversable instanceof Iterable ? cb.$plus$plus$eq(((Iterable)traversable).iterator()) : cb.$plus$plus$eq(traversable);
        }
        return cb;
    }

    public static Builder copy2builder(AugmentedIterableIterator $this, Builder b) {
        if ($this.isRemainingCheap()) {
            b.sizeHint($this.remaining());
        }
        while ($this.hasNext()) {
            b.$plus$eq($this.next());
        }
        return b;
    }

    public static Combiner filter2combiner(AugmentedIterableIterator $this, Function1 pred, Combiner cb) {
        while ($this.hasNext()) {
            Object curr = $this.next();
            Object object = BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? cb.$plus$eq(curr) : BoxedUnit.UNIT;
        }
        return cb;
    }

    public static Combiner filterNot2combiner(AugmentedIterableIterator $this, Function1 pred, Combiner cb) {
        while ($this.hasNext()) {
            Object curr = $this.next();
            Object object = BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? BoxedUnit.UNIT : cb.$plus$eq(curr);
        }
        return cb;
    }

    public static Tuple2 partition2combiners(AugmentedIterableIterator $this, Function1 pred, Combiner btrue, Combiner bfalse) {
        while ($this.hasNext()) {
            Object curr = $this.next();
            Builder builder = BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? btrue.$plus$eq(curr) : bfalse.$plus$eq(curr);
        }
        return new Tuple2<Combiner, Combiner>(btrue, bfalse);
    }

    public static Combiner take2combiner(AugmentedIterableIterator $this, int n, Combiner cb) {
        cb.sizeHint(n);
        for (int left = n; left > 0; --left) {
            cb.$plus$eq($this.next());
        }
        return cb;
    }

    public static Combiner drop2combiner(AugmentedIterableIterator $this, int n, Combiner cb) {
        $this.drop(n);
        if ($this.isRemainingCheap()) {
            cb.sizeHint($this.remaining());
        }
        while ($this.hasNext()) {
            cb.$plus$eq($this.next());
        }
        return cb;
    }

    public static Combiner slice2combiner(AugmentedIterableIterator $this, int from2, int until2, Combiner cb) {
        int left;
        $this.drop(from2);
        cb.sizeHint(left);
        for (left = package$.MODULE$.max(until2 - from2, 0); left > 0; --left) {
            cb.$plus$eq($this.next());
        }
        return cb;
    }

    public static Tuple2 splitAt2combiners(AugmentedIterableIterator $this, int at, Combiner before, Combiner after) {
        before.sizeHint(at);
        if ($this.isRemainingCheap()) {
            after.sizeHint($this.remaining() - at);
        }
        for (int left = at; left > 0; --left) {
            before.$plus$eq($this.next());
        }
        while ($this.hasNext()) {
            after.$plus$eq($this.next());
        }
        return new Tuple2<Combiner, Combiner>(before, after);
    }

    public static Tuple2 takeWhile2combiner(AugmentedIterableIterator $this, Function1 p, Combiner cb) {
        boolean loop2 = true;
        while ($this.hasNext() && loop2) {
            Object object;
            Object curr = $this.next();
            if (BoxesRunTime.unboxToBoolean(p.apply(curr))) {
                object = cb.$plus$eq(curr);
                continue;
            }
            loop2 = false;
            object = BoxedUnit.UNIT;
        }
        return new Tuple2<Combiner, Boolean>(cb, BoxesRunTime.boxToBoolean(loop2));
    }

    public static Tuple2 span2combiners(AugmentedIterableIterator $this, Function1 p, Combiner before, Combiner after) {
        boolean isBefore = true;
        while ($this.hasNext() && isBefore) {
            Object object;
            Object curr = $this.next();
            if (BoxesRunTime.unboxToBoolean(p.apply(curr))) {
                object = before.$plus$eq(curr);
                continue;
            }
            if ($this.isRemainingCheap()) {
                after.sizeHint($this.remaining() + 1);
            }
            after.$plus$eq(curr);
            isBefore = false;
            object = BoxedUnit.UNIT;
        }
        while ($this.hasNext()) {
            after.$plus$eq($this.next());
        }
        return new Tuple2<Combiner, Combiner>(before, after);
    }

    public static void scanToArray(AugmentedIterableIterator $this, Object z, Function2 op, Object array, int from2) {
        Object last2 = z;
        int i = from2;
        while ($this.hasNext()) {
            last2 = op.apply(last2, $this.next());
            ScalaRunTime$.MODULE$.array_update(array, i, last2);
            ++i;
        }
    }

    public static Combiner scanToCombiner(AugmentedIterableIterator $this, Object startValue, Function2 op, Combiner cb) {
        Object curr = startValue;
        while ($this.hasNext()) {
            curr = op.apply(curr, $this.next());
            cb.$plus$eq(curr);
        }
        return cb;
    }

    public static Combiner scanToCombiner(AugmentedIterableIterator $this, int howmany, Object startValue, Function2 op, Combiner cb) {
        Object curr = startValue;
        for (int left = howmany; left > 0; --left) {
            curr = op.apply(curr, $this.next());
            cb.$plus$eq(curr);
        }
        return cb;
    }

    public static Combiner zip2combiner(AugmentedIterableIterator $this, RemainsIterator otherpit, Combiner cb) {
        if ($this.isRemainingCheap() && otherpit.isRemainingCheap()) {
            int n = $this.remaining();
            Predef$ predef$ = Predef$.MODULE$;
            cb.sizeHint(RichInt$.MODULE$.min$extension(n, otherpit.remaining()));
        }
        while ($this.hasNext() && otherpit.hasNext()) {
            cb.$plus$eq(new Tuple2($this.next(), otherpit.next()));
        }
        return cb;
    }

    public static Combiner zipAll2combiner(AugmentedIterableIterator $this, RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
        if ($this.isRemainingCheap() && that.isRemainingCheap()) {
            int n = $this.remaining();
            Predef$ predef$ = Predef$.MODULE$;
            cb.sizeHint(RichInt$.MODULE$.max$extension(n, that.remaining()));
        }
        while ($this.hasNext() && that.hasNext()) {
            cb.$plus$eq(new Tuple2($this.next(), that.next()));
        }
        while ($this.hasNext()) {
            cb.$plus$eq(new Tuple2($this.next(), thatelem));
        }
        while (that.hasNext()) {
            cb.$plus$eq(new Tuple2(thiselem, that.next()));
        }
        return cb;
    }

    public static void $init$(AugmentedIterableIterator $this) {
    }
}

