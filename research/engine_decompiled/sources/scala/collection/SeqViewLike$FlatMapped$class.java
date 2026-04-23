/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Predef$;
import scala.collection.SeqViewLike;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.runtime.BoxesRunTime;

public abstract class SeqViewLike$FlatMapped$class {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int[] index(SeqViewLike.FlatMapped $this) {
        int[] index = new int[$this.scala$collection$SeqViewLike$FlatMapped$$$outer().length() + 1];
        index[0] = 0;
        Predef$ predef$ = Predef$.MODULE$;
        int n = $this.scala$collection$SeqViewLike$FlatMapped$$$outer().length();
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return index;
        int i1 = range2.start();
        while (true) {
            index[i1 + 1] = index[i1] + $this.mapping().apply($this.scala$collection$SeqViewLike$FlatMapped$$$outer().apply(i1)).seq().size();
            if (i1 == range2.lastElement()) {
                return index;
            }
            i1 += range2.step();
        }
    }

    public static int findRow(SeqViewLike.FlatMapped $this, int idx, int lo, int hi) {
        int mid = (lo + hi) / 2;
        return idx < $this.index()[mid] ? $this.findRow(idx, lo, mid - 1) : (idx >= $this.index()[mid + 1] ? $this.findRow(idx, mid + 1, hi) : mid);
    }

    public static int length(SeqViewLike.FlatMapped $this) {
        return $this.index()[$this.scala$collection$SeqViewLike$FlatMapped$$$outer().length()];
    }

    public static Object apply(SeqViewLike.FlatMapped $this, int idx) {
        if (idx < 0 || idx >= $this.length()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        int row = $this.findRow(idx, 0, $this.scala$collection$SeqViewLike$FlatMapped$$$outer().length() - 1);
        return $this.mapping().apply($this.scala$collection$SeqViewLike$FlatMapped$$$outer().apply(row)).seq().toSeq().apply(idx - $this.index()[row]);
    }

    public static void $init$(SeqViewLike.FlatMapped $this) {
    }
}

