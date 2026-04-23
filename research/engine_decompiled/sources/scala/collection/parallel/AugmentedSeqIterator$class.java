/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedSeqIterator;
import scala.collection.parallel.Combiner;
import scala.runtime.BoxesRunTime;

public abstract class AugmentedSeqIterator$class {
    /*
     * WARNING - void declaration
     */
    public static int prefixLength(AugmentedSeqIterator $this, Function1 pred) {
        void var2_2;
        int total2 = 0;
        boolean loop2 = true;
        while ($this.hasNext() && loop2) {
            if (BoxesRunTime.unboxToBoolean(pred.apply($this.next()))) {
                ++total2;
                continue;
            }
            loop2 = false;
        }
        return (int)var2_2;
    }

    public static int indexWhere(AugmentedSeqIterator $this, Function1 pred) {
        int i = 0;
        boolean loop2 = true;
        while ($this.hasNext() && loop2) {
            if (BoxesRunTime.unboxToBoolean(pred.apply($this.next()))) {
                loop2 = false;
                continue;
            }
            ++i;
        }
        return loop2 ? -1 : i;
    }

    /*
     * WARNING - void declaration
     */
    public static int lastIndexWhere(AugmentedSeqIterator $this, Function1 pred) {
        void var2_2;
        int pos = -1;
        int i = 0;
        while ($this.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(pred.apply($this.next()))) {
                pos = i;
            }
            ++i;
        }
        return (int)var2_2;
    }

    public static boolean corresponds(AugmentedSeqIterator $this, Function2 corr, Iterator that) {
        while ($this.hasNext() && that.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(corr.apply($this.next(), that.next()))) continue;
            return false;
        }
        return $this.hasNext() == that.hasNext();
    }

    public static Combiner reverse2combiner(AugmentedSeqIterator $this, Combiner cb) {
        if ($this.isRemainingCheap()) {
            cb.sizeHint($this.remaining());
        }
        List lst = Nil$.MODULE$;
        while ($this.hasNext()) {
            lst = lst.$colon$colon($this.next());
        }
        while (true) {
            List list2;
            Nil$ nil$ = lst;
            Nil$ nil$2 = Nil$.MODULE$;
            if (nil$ != null && ((Object)nil$).equals(nil$2)) {
                return cb;
            }
            cb.$plus$eq(list2.head());
            list2 = (List)list2.tail();
        }
    }

    public static Combiner reverseMap2combiner(AugmentedSeqIterator $this, Function1 f, Combiner cb) {
        if ($this.isRemainingCheap()) {
            cb.sizeHint($this.remaining());
        }
        List lst = Nil$.MODULE$;
        while ($this.hasNext()) {
            lst = lst.$colon$colon(f.apply($this.next()));
        }
        while (true) {
            List list2;
            Nil$ nil$ = lst;
            Nil$ nil$2 = Nil$.MODULE$;
            if (nil$ != null && ((Object)nil$).equals(nil$2)) {
                return cb;
            }
            cb.$plus$eq(list2.head());
            list2 = (List)list2.tail();
        }
    }

    public static Combiner updated2combiner(AugmentedSeqIterator $this, int index, Object elem, Combiner cb) {
        if ($this.isRemainingCheap()) {
            cb.sizeHint($this.remaining());
        }
        int j = 0;
        while ($this.hasNext()) {
            Builder builder;
            if (j == index) {
                cb.$plus$eq(elem);
                builder = $this.next();
            } else {
                builder = cb.$plus$eq($this.next());
            }
            ++j;
        }
        return cb;
    }

    public static void $init$(AugmentedSeqIterator $this) {
    }
}

