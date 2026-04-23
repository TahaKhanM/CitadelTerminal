/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.BitSet;
import scala.collection.BitSetLike;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.math.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

public abstract class BitSetLike$class {
    /*
     * WARNING - void declaration
     */
    public static long[] toBitMask(BitSetLike $this) {
        void var1_1;
        long[] a = new long[$this.nwords()];
        int i = a.length;
        while (i > 0) {
            a[--i] = $this.word(i);
        }
        return var1_1;
    }

    /*
     * WARNING - void declaration
     */
    public static int size(BitSetLike $this) {
        void var1_1;
        int s2 = 0;
        int i = $this.nwords();
        while (i > 0) {
            s2 += Long.bitCount($this.word(--i));
        }
        return (int)var1_1;
    }

    public static boolean isEmpty(BitSetLike $this) {
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.until$extension0(0, $this.nwords()).forall(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ BitSetLike $outer;

            public final boolean apply(int i) {
                return this.apply$mcZI$sp(i);
            }

            public boolean apply$mcZI$sp(int i) {
                return this.$outer.word(i) == 0L;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Ordering ordering(BitSetLike $this) {
        return Ordering$Int$.MODULE$;
    }

    public static BitSetLike rangeImpl(BitSetLike $this, Option from2, Option until2) {
        long[] a = $this.toBitMask();
        int len = a.length;
        if (from2.isDefined()) {
            int pos;
            int f = BoxesRunTime.unboxToInt(from2.get());
            for (pos = 0; f >= 64 && pos < len; f -= 64, ++pos) {
                a[pos] = 0L;
            }
            if (f > 0 && pos < len) {
                a[pos] = a[pos] & ((1L << f) - 1L ^ 0xFFFFFFFFFFFFFFFFL);
            }
        }
        if (until2.isDefined()) {
            int u = BoxesRunTime.unboxToInt(until2.get());
            int w = u / 64;
            int b = u % 64;
            for (int clearw = w + 1; clearw < len; ++clearw) {
                a[clearw] = 0L;
            }
            if (w < len) {
                a[w] = a[w] & (1L << b) - 1L;
            }
        }
        return $this.fromBitMaskNoCopy(a);
    }

    public static Iterator iterator(BitSetLike $this) {
        return $this.iteratorFrom(BoxesRunTime.boxToInteger(0));
    }

    public static AbstractIterator keysIteratorFrom(BitSetLike $this, int start) {
        return new AbstractIterator<Object>($this, start){
            private int current;
            private final int end;
            private final /* synthetic */ BitSetLike $outer;

            private int current() {
                return this.current;
            }

            private void current_$eq(int x$1) {
                this.current = x$1;
            }

            private int end() {
                return this.end;
            }

            public boolean hasNext() {
                while (this.current() != this.end() && !this.$outer.contains(this.current())) {
                    this.current_$eq(this.current() + 1);
                }
                return this.current() != this.end();
            }

            /*
             * WARNING - void declaration
             */
            public int next() {
                int n;
                if (this.hasNext()) {
                    void var1_1;
                    int r = this.current();
                    this.current_$eq(this.current() + 1);
                    n = var1_1;
                } else {
                    n = BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
                }
                return n;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.current = start$1;
                this.end = $outer.nwords() * 64;
            }
        };
    }

    public static void foreach(BitSetLike $this, Function1 f) {
        for (int i = 0; i < $this.nwords(); ++i) {
            long w = $this.word(i);
            int j = i * 64;
            while (w != 0L) {
                BoxedUnit boxedUnit = (w & 1L) == 1L ? f.apply(BoxesRunTime.boxToInteger(j)) : BoxedUnit.UNIT;
                w >>>= 1;
                ++j;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BitSetLike $bar(BitSetLike $this, BitSet other) {
        int n = $this.nwords();
        Predef$ predef$ = Predef$.MODULE$;
        int n2 = other.nwords();
        package$ package$2 = package$.MODULE$;
        int len = Math.max(n, n2);
        long[] words = new long[len];
        Predef$ predef$2 = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, len, 1);
        if (range2.isEmpty()) return $this.fromBitMaskNoCopy(words);
        int i1 = range2.start();
        while (true) {
            words[i1] = $this.word(i1) | other.word(i1);
            if (i1 == range2.lastElement()) {
                return $this.fromBitMaskNoCopy(words);
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BitSetLike $amp(BitSetLike $this, BitSet other) {
        int n = $this.nwords();
        Predef$ predef$ = Predef$.MODULE$;
        int n2 = other.nwords();
        package$ package$2 = package$.MODULE$;
        int len = Math.min(n, n2);
        long[] words = new long[len];
        Predef$ predef$2 = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, len, 1);
        if (range2.isEmpty()) return $this.fromBitMaskNoCopy(words);
        int i1 = range2.start();
        while (true) {
            words[i1] = $this.word(i1) & other.word(i1);
            if (i1 == range2.lastElement()) {
                return $this.fromBitMaskNoCopy(words);
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BitSetLike $amp$tilde(BitSetLike $this, BitSet other) {
        int len = $this.nwords();
        long[] words = new long[len];
        Predef$ predef$ = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, len, 1);
        if (range2.isEmpty()) return $this.fromBitMaskNoCopy(words);
        int i1 = range2.start();
        while (true) {
            words[i1] = $this.word(i1) & (other.word(i1) ^ 0xFFFFFFFFFFFFFFFFL);
            if (i1 == range2.lastElement()) {
                return $this.fromBitMaskNoCopy(words);
            }
            i1 += range2.step();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BitSetLike $up(BitSetLike $this, BitSet other) {
        int n = $this.nwords();
        Predef$ predef$ = Predef$.MODULE$;
        int n2 = other.nwords();
        package$ package$2 = package$.MODULE$;
        int len = Math.max(n, n2);
        long[] words = new long[len];
        Predef$ predef$2 = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, len, 1);
        if (range2.isEmpty()) return $this.fromBitMaskNoCopy(words);
        int i1 = range2.start();
        while (true) {
            words[i1] = $this.word(i1) ^ other.word(i1);
            if (i1 == range2.lastElement()) {
                return $this.fromBitMaskNoCopy(words);
            }
            i1 += range2.step();
        }
    }

    public static boolean contains(BitSetLike $this, int elem) {
        return 0 <= elem && ($this.word(elem >> 6) & 1L << elem) != 0L;
    }

    public static boolean subsetOf(BitSetLike $this, BitSet other) {
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.until$extension0(0, $this.nwords()).forall(new Serializable($this, other){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ BitSetLike $outer;
            private final BitSet other$5;

            public final boolean apply(int idx) {
                return this.apply$mcZI$sp(idx);
            }

            public boolean apply$mcZI$sp(int idx) {
                return (this.$outer.word(idx) & (this.other$5.word(idx) ^ 0xFFFFFFFFFFFFFFFFL)) == 0L;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.other$5 = other$5;
            }
        });
    }

    public static StringBuilder addString(BitSetLike $this, StringBuilder sb, String start, String sep, String end) {
        sb.append(start);
        String pre = "";
        int max2 = $this.nwords() * 64;
        for (int i = 0; i != max2; ++i) {
            if (!$this.contains(i)) continue;
            sb.append(pre).append(i);
            pre = sep;
        }
        return sb.append(end);
    }

    public static String stringPrefix(BitSetLike $this) {
        return "BitSet";
    }

    public static void $init$(BitSetLike $this) {
    }
}

