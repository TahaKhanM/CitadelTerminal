/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Predef$;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.SeqViewLike;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public abstract class SeqViewLike$Filtered$class {
    /*
     * Enabled aggressive block sorting
     */
    public static int[] index(SeqViewLike.Filtered $this) {
        IntRef len = IntRef.create(0);
        int[] arr = new int[$this.scala$collection$SeqViewLike$Filtered$$$outer().length()];
        Predef$ predef$ = Predef$.MODULE$;
        int n = $this.scala$collection$SeqViewLike$Filtered$$$outer().length();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (!range2.isEmpty()) {
            int i1 = range2.start();
            while (true) {
                if (BoxesRunTime.unboxToBoolean($this.pred().apply($this.scala$collection$SeqViewLike$Filtered$$$outer().apply(i1)))) {
                    arr[len.elem] = i1;
                    ++len.elem;
                }
                if (i1 == range2.lastElement()) break;
                i1 += range2.step();
            }
        }
        Predef$ predef$2 = Predef$.MODULE$;
        return (int[])IndexedSeqOptimized$class.take(new ArrayOps.ofInt(arr), len.elem);
    }

    public static int length(SeqViewLike.Filtered $this) {
        return $this.index().length;
    }

    public static Object apply(SeqViewLike.Filtered $this, int idx) {
        return $this.scala$collection$SeqViewLike$Filtered$$$outer().apply($this.index()[idx]);
    }

    public static void $init$(SeqViewLike.Filtered $this) {
    }
}

