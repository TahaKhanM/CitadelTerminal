/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqOptimized;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Builder;
import scala.math.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class IndexedSeqOptimized$class {
    public static boolean isEmpty(IndexedSeqOptimized $this) {
        return $this.length() == 0;
    }

    public static void foreach(IndexedSeqOptimized $this, Function1 f) {
        int len = $this.length();
        for (int i = 0; i < len; ++i) {
            f.apply($this.apply(i));
        }
    }

    /*
     * WARNING - void declaration
     */
    private static int prefixLengthImpl(IndexedSeqOptimized $this, Function1 p, boolean expectTrue) {
        void var3_3;
        for (int i = 0; i < $this.length() && BoxesRunTime.unboxToBoolean(p.apply($this.apply(i))) == expectTrue; ++i) {
        }
        return (int)var3_3;
    }

    public static boolean forall(IndexedSeqOptimized $this, Function1 p) {
        return IndexedSeqOptimized$class.prefixLengthImpl($this, p, true) == $this.length();
    }

    public static boolean exists(IndexedSeqOptimized $this, Function1 p) {
        return IndexedSeqOptimized$class.prefixLengthImpl($this, p, false) != $this.length();
    }

    public static Option find(IndexedSeqOptimized $this, Function1 p) {
        int i = $this.prefixLength(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$1;

            public final boolean apply(A x$1) {
                return !BoxesRunTime.unboxToBoolean(this.p$1.apply(x$1));
            }
            {
                this.p$1 = p$1;
            }
        });
        return i < $this.length() ? new Some($this.apply(i)) : None$.MODULE$;
    }

    private static Object foldl(IndexedSeqOptimized $this, int start, int end, Object z, Function2 op) {
        while (start != end) {
            z = op.apply(z, $this.apply(start));
            ++start;
        }
        return z;
    }

    private static Object foldr(IndexedSeqOptimized $this, int start, int end, Object z, Function2 op) {
        while (start != end) {
            z = op.apply($this.apply(end - 1), z);
            --end;
        }
        return z;
    }

    public static Object foldLeft(IndexedSeqOptimized $this, Object z, Function2 op) {
        return IndexedSeqOptimized$class.foldl($this, 0, $this.length(), z, op);
    }

    public static Object foldRight(IndexedSeqOptimized $this, Object z, Function2 op) {
        return IndexedSeqOptimized$class.foldr($this, 0, $this.length(), z, op);
    }

    public static Object reduceLeft(IndexedSeqOptimized $this, Function2 op) {
        return $this.length() > 0 ? IndexedSeqOptimized$class.foldl($this, 1, $this.length(), $this.apply(0), op) : $this.scala$collection$IndexedSeqOptimized$$super$reduceLeft(op);
    }

    public static Object reduceRight(IndexedSeqOptimized $this, Function2 op) {
        return $this.length() > 0 ? IndexedSeqOptimized$class.foldr($this, 0, $this.length() - 1, $this.apply($this.length() - 1), op) : $this.scala$collection$IndexedSeqOptimized$$super$reduceRight(op);
    }

    public static Object zip(IndexedSeqOptimized $this, GenIterable that, CanBuildFrom bf) {
        Object object;
        if (that instanceof IndexedSeq) {
            IndexedSeq indexedSeq = (IndexedSeq)that;
            Builder b = bf.apply($this.repr());
            int n = $this.length();
            Predef$ predef$ = Predef$.MODULE$;
            int len = RichInt$.MODULE$.min$extension(n, indexedSeq.length());
            b.sizeHint(len);
            for (int i = 0; i < len; ++i) {
                b.$plus$eq(new Tuple2($this.apply(i), indexedSeq.apply(i)));
            }
            object = b.result();
        } else {
            object = $this.scala$collection$IndexedSeqOptimized$$super$zip(that, bf);
        }
        return object;
    }

    public static Object zipWithIndex(IndexedSeqOptimized $this, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        int len = $this.length();
        b.sizeHint(len);
        for (int i = 0; i < len; ++i) {
            b.$plus$eq(new Tuple2($this.apply(i), BoxesRunTime.boxToInteger(i)));
        }
        return b.result();
    }

    public static Object slice(IndexedSeqOptimized $this, int from2, int until2) {
        int lo = package$.MODULE$.max(from2, 0);
        int hi = package$.MODULE$.min(package$.MODULE$.max(until2, 0), $this.length());
        int elems = package$.MODULE$.max(hi - lo, 0);
        Builder b = $this.newBuilder();
        b.sizeHint(elems);
        for (int i = lo; i < hi; ++i) {
            b.$plus$eq($this.apply(i));
        }
        return b.result();
    }

    public static Object head(IndexedSeqOptimized $this) {
        return $this.isEmpty() ? $this.scala$collection$IndexedSeqOptimized$$super$head() : $this.apply(0);
    }

    public static Object tail(IndexedSeqOptimized $this) {
        return $this.isEmpty() ? $this.scala$collection$IndexedSeqOptimized$$super$tail() : $this.slice(1, $this.length());
    }

    public static Object last(IndexedSeqOptimized $this) {
        return $this.length() > 0 ? $this.apply($this.length() - 1) : $this.scala$collection$IndexedSeqOptimized$$super$last();
    }

    public static Object init(IndexedSeqOptimized $this) {
        return $this.length() > 0 ? $this.slice(0, $this.length() - 1) : $this.scala$collection$IndexedSeqOptimized$$super$init();
    }

    public static Object take(IndexedSeqOptimized $this, int n) {
        return $this.slice(0, n);
    }

    public static Object drop(IndexedSeqOptimized $this, int n) {
        return $this.slice(n, $this.length());
    }

    public static Object takeRight(IndexedSeqOptimized $this, int n) {
        return $this.slice($this.length() - package$.MODULE$.max(n, 0), $this.length());
    }

    public static Object dropRight(IndexedSeqOptimized $this, int n) {
        return $this.slice(0, $this.length() - package$.MODULE$.max(n, 0));
    }

    public static Tuple2 splitAt(IndexedSeqOptimized $this, int n) {
        return new Tuple2($this.take(n), $this.drop(n));
    }

    public static Object takeWhile(IndexedSeqOptimized $this, Function1 p) {
        return $this.take($this.prefixLength(p));
    }

    public static Object dropWhile(IndexedSeqOptimized $this, Function1 p) {
        return $this.drop($this.prefixLength(p));
    }

    public static Tuple2 span(IndexedSeqOptimized $this, Function1 p) {
        return $this.splitAt($this.prefixLength(p));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean sameElements(IndexedSeqOptimized $this, GenIterable that) {
        int i;
        if (!(that instanceof IndexedSeq)) return $this.scala$collection$IndexedSeqOptimized$$super$sameElements(that);
        IndexedSeq indexedSeq = (IndexedSeq)that;
        int len = $this.length();
        if (len != indexedSeq.length()) return false;
        for (i = 0; i < len; ++i) {
            Object r = indexedSeq.apply(i);
            Object a = $this.apply(i);
            if (!(a == r ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, r) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, r) : a.equals(r)))))) break;
        }
        if (i != len) return false;
        return true;
    }

    public static void copyToArray(IndexedSeqOptimized $this, Object xs, int start, int len) {
        int i = 0;
        int j = start;
        int n = $this.length();
        Predef$ predef$ = Predef$.MODULE$;
        int n2 = RichInt$.MODULE$.min$extension(n, len);
        Predef$ predef$2 = Predef$.MODULE$;
        int end = RichInt$.MODULE$.min$extension(n2, ScalaRunTime$.MODULE$.array_length(xs) - start);
        while (i < end) {
            ScalaRunTime$.MODULE$.array_update(xs, j, $this.apply(i));
            ++i;
            ++j;
        }
    }

    public static int lengthCompare(IndexedSeqOptimized $this, int len) {
        return $this.length() - len;
    }

    public static int segmentLength(IndexedSeqOptimized $this, Function1 p, int from2) {
        int i;
        int len = $this.length();
        for (i = from2; i < len && BoxesRunTime.unboxToBoolean(p.apply($this.apply(i))); ++i) {
        }
        return i - from2;
    }

    private static int negLength(IndexedSeqOptimized $this, int n) {
        return n >= $this.length() ? -1 : n;
    }

    public static int indexWhere(IndexedSeqOptimized $this, Function1 p, int from2) {
        Predef$ predef$ = Predef$.MODULE$;
        int start = RichInt$.MODULE$.max$extension(from2, 0);
        return IndexedSeqOptimized$class.negLength($this, start + $this.segmentLength(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$2;

            public final boolean apply(A x$2) {
                return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x$2));
            }
            {
                this.p$2 = p$2;
            }
        }, start));
    }

    /*
     * WARNING - void declaration
     */
    public static int lastIndexWhere(IndexedSeqOptimized $this, Function1 p, int end) {
        void var3_3;
        for (int i = package$.MODULE$.min(end, $this.length() - 1); i >= 0 && !BoxesRunTime.unboxToBoolean(p.apply($this.apply(i))); --i) {
        }
        return (int)var3_3;
    }

    public static Object reverse(IndexedSeqOptimized $this) {
        Builder b = $this.newBuilder();
        b.sizeHint($this.length());
        int i = $this.length();
        while (0 < i) {
            b.$plus$eq($this.apply(--i));
        }
        return b.result();
    }

    public static Iterator reverseIterator(IndexedSeqOptimized $this) {
        return new AbstractIterator<A>($this){
            private int i;
            private final /* synthetic */ IndexedSeqOptimized $outer;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                return 0 < this.i();
            }

            public A next() {
                Nothing$ nothing$;
                if (0 < this.i()) {
                    this.i_$eq(this.i() - 1);
                    nothing$ = this.$outer.apply(this.i());
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i = $outer.length();
            }
        };
    }

    public static boolean startsWith(IndexedSeqOptimized $this, GenSeq that, int offset) {
        boolean bl;
        if (that instanceof IndexedSeq) {
            int j;
            IndexedSeq indexedSeq = (IndexedSeq)that;
            int i = offset;
            int thisLen = $this.length();
            int thatLen = indexedSeq.length();
            for (j = 0; i < thisLen && j < thatLen; ++i, ++j) {
                Object r = indexedSeq.apply(j);
                Object a = $this.apply(i);
                if (!(a == r ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, r) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, r) : a.equals(r)))))) break;
            }
            bl = j == thatLen;
        } else {
            int thisLen = $this.length();
            Iterator thatElems = that.iterator();
            for (int i = offset; i < thisLen && thatElems.hasNext(); ++i) {
                Object a = thatElems.next();
                Object a2 = $this.apply(i);
                if (a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a))))) {
                    continue;
                }
                return false;
            }
            bl = !thatElems.hasNext();
        }
        return bl;
    }

    public static boolean endsWith(IndexedSeqOptimized $this, GenSeq that) {
        boolean bl;
        if (that instanceof IndexedSeq) {
            boolean bl2;
            IndexedSeq indexedSeq = (IndexedSeq)that;
            int i = $this.length() - 1;
            int j = indexedSeq.length() - 1;
            if (j <= i) {
                while (j >= 0) {
                    Object r = indexedSeq.apply(j);
                    Object a = $this.apply(i);
                    if (a == r ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, r) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, r) : a.equals(r))))) {
                        --i;
                        --j;
                        continue;
                    }
                    return false;
                }
                bl2 = true;
            } else {
                bl2 = false;
            }
            bl = bl2;
        } else {
            bl = $this.scala$collection$IndexedSeqOptimized$$super$endsWith(that);
        }
        return bl;
    }

    public static void $init$(IndexedSeqOptimized $this) {
    }
}

