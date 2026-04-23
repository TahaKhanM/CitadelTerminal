/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.LinearSeq;
import scala.collection.LinearSeqOptimized;
import scala.collection.SeqLike;
import scala.collection.mutable.Builder;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

public abstract class LinearSeqOptimized$class {
    /*
     * WARNING - void declaration
     */
    public static int length(LinearSeqOptimized $this) {
        LinearSeqOptimized these = $this;
        boolean len = false;
        void var2_2;
        while (!these.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            ++var2_2;
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return (int)var2_2;
    }

    public static Object apply(LinearSeqOptimized $this, int n) {
        Object rest = $this.drop(n);
        if (n < 0 || rest.isEmpty()) {
            throw new IndexOutOfBoundsException(String.valueOf(BoxesRunTime.boxToInteger(n)));
        }
        return rest.head();
    }

    public static void foreach(LinearSeqOptimized $this, Function1 f) {
        LinearSeqOptimized these = $this;
        while (!these.isEmpty()) {
            f.apply(these.head());
            these = (LinearSeqOptimized)these.tail();
        }
        return;
    }

    public static boolean forall(LinearSeqOptimized $this, Function1 p) {
        LinearSeqOptimized these = $this;
        while (true) {
            LinearSeqOptimized linearSeqOptimized;
            if (these.isEmpty()) {
                return true;
            }
            if (!BoxesRunTime.unboxToBoolean(p.apply(linearSeqOptimized.head()))) break;
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return false;
    }

    public static boolean exists(LinearSeqOptimized $this, Function1 p) {
        LinearSeqOptimized these = $this;
        while (!these.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            if (BoxesRunTime.unboxToBoolean(p.apply(linearSeqOptimized.head()))) {
                return true;
            }
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return false;
    }

    public static boolean contains(LinearSeqOptimized $this, Object elem) {
        LinearSeqOptimized these = $this;
        while (!these.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            Object a = linearSeqOptimized.head();
            if (a == elem ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, elem) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, elem) : a.equals(elem))))) {
                return true;
            }
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return false;
    }

    public static Option find(LinearSeqOptimized $this, Function1 p) {
        LinearSeqOptimized these = $this;
        while (!these.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            if (BoxesRunTime.unboxToBoolean(p.apply(linearSeqOptimized.head()))) {
                return new Some(linearSeqOptimized.head());
            }
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return None$.MODULE$;
    }

    public static Object foldLeft(LinearSeqOptimized $this, Object z, Function2 op) {
        Object acc = z;
        LinearSeqOptimized these = $this;
        Object r;
        while (!these.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            r = op.apply(r, linearSeqOptimized.head());
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return r;
    }

    public static Object foldRight(LinearSeqOptimized $this, Object z, Function2 op) {
        return $this.isEmpty() ? z : op.apply($this.head(), ((LinearSeqOptimized)$this.tail()).foldRight(z, op));
    }

    public static Object reduceLeft(LinearSeqOptimized $this, Function2 f) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.reduceLeft");
        }
        return ((LinearSeqOptimized)$this.tail()).foldLeft($this.head(), f);
    }

    public static Object reduceRight(LinearSeqOptimized $this, Function2 op) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("Nil.reduceRight");
        }
        return ((SeqLike)$this.tail()).isEmpty() ? $this.head() : op.apply($this.head(), ((LinearSeqOptimized)$this.tail()).reduceRight(op));
    }

    public static Object last(LinearSeqOptimized $this) {
        if ($this.isEmpty()) {
            throw new NoSuchElementException();
        }
        LinearSeqOptimized these = $this;
        LinearSeqOptimized nx = (LinearSeqOptimized)$this.tail();
        while (!nx.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            void var1_1 = linearSeqOptimized;
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return these.head();
    }

    public static LinearSeqOptimized take(LinearSeqOptimized $this, int n) {
        Builder b = $this.newBuilder();
        int i = 0;
        LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
        while (!these.isEmpty() && i < n) {
            ++i;
            b.$plus$eq(these.head());
            these = (LinearSeqOptimized)these.tail();
        }
        return (LinearSeqOptimized)b.result();
    }

    /*
     * WARNING - void declaration
     */
    public static LinearSeqOptimized drop(LinearSeqOptimized $this, int n) {
        void var2_2;
        LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
        for (int count2 = n; !these.isEmpty() && count2 > 0; --count2) {
            these = (LinearSeqOptimized)these.tail();
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static LinearSeqOptimized dropRight(LinearSeqOptimized $this, int n) {
        Builder b = $this.newBuilder();
        LinearSeqOptimized these = $this;
        Object lead = $this.drop(n);
        while (!lead.isEmpty()) {
            LinearSeqOptimized linearSeqOptimized;
            LinearSeqOptimized linearSeqOptimized2;
            void var2_2;
            var2_2.$plus$eq(linearSeqOptimized2.head());
            linearSeqOptimized2 = (LinearSeqOptimized)linearSeqOptimized2.tail();
            linearSeqOptimized = (LinearSeqOptimized)linearSeqOptimized.tail();
        }
        return (LinearSeqOptimized)b.result();
    }

    public static LinearSeqOptimized slice(LinearSeqOptimized $this, int from2, int until2) {
        LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
        Predef$ predef$ = Predef$.MODULE$;
        int count2 = RichInt$.MODULE$.max$extension(from2, 0);
        if (until2 <= count2) {
            return (LinearSeqOptimized)$this.newBuilder().result();
        }
        Builder b = $this.newBuilder();
        int sliceElems = until2 - count2;
        while (these.nonEmpty() && count2 > 0) {
            these = (LinearSeqOptimized)these.tail();
            --count2;
        }
        while (these.nonEmpty() && sliceElems > 0) {
            --sliceElems;
            b.$plus$eq(these.head());
            these = (LinearSeqOptimized)these.tail();
        }
        return (LinearSeqOptimized)b.result();
    }

    public static LinearSeqOptimized takeWhile(LinearSeqOptimized $this, Function1 p) {
        Builder b = $this.newBuilder();
        LinearSeqOptimized these = $this;
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            b.$plus$eq(these.head());
            these = (LinearSeqOptimized)these.tail();
        }
        return (LinearSeqOptimized)b.result();
    }

    public static Tuple2 span(LinearSeqOptimized $this, Function1 p) {
        LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
        Builder b = $this.newBuilder();
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            b.$plus$eq(these.head());
            these = (LinearSeqOptimized)these.tail();
        }
        return new Tuple2(b.result(), these);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean sameElements(LinearSeqOptimized $this, GenIterable that) {
        if (!(that instanceof LinearSeq)) return $this.scala$collection$LinearSeqOptimized$$super$sameElements(that);
        LinearSeq linearSeq = (LinearSeq)that;
        if ($this == linearSeq) return true;
        LinearSeqOptimized these = $this;
        LinearSeq those = linearSeq;
        while (!these.isEmpty() && !those.isEmpty()) {
            Object a = those.head();
            Object a2 = these.head();
            if (!(a2 == a ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a)))))) break;
            these = (LinearSeqOptimized)these.tail();
            those = (LinearSeq)those.tail();
        }
        if (!these.isEmpty()) return false;
        if (!those.isEmpty()) return false;
        return true;
    }

    public static int lengthCompare(LinearSeqOptimized $this, int len) {
        return len < 0 ? 1 : LinearSeqOptimized$class.loop$1($this, 0, $this, len);
    }

    public static boolean isDefinedAt(LinearSeqOptimized $this, int x) {
        return x >= 0 && $this.lengthCompare(x) > 0;
    }

    /*
     * WARNING - void declaration
     */
    public static int segmentLength(LinearSeqOptimized $this, Function1 p, int from2) {
        void var3_3;
        int i = 0;
        Object these = $this.drop(from2);
        while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            ++i;
            these = (LinearSeqOptimized)these.tail();
        }
        return (int)var3_3;
    }

    public static int indexWhere(LinearSeqOptimized $this, Function1 p, int from2) {
        int i = from2;
        Object these = $this.drop(from2);
        while (these.nonEmpty()) {
            if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
                return i;
            }
            ++i;
            these = (LinearSeqOptimized)these.tail();
        }
        return -1;
    }

    public static int lastIndexWhere(LinearSeqOptimized $this, Function1 p, int end) {
        LinearSeqOptimized these = $this;
        int last2 = -1;
        for (int i = 0; !these.isEmpty() && i <= end; ++i) {
            if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
                last2 = i;
            }
            these = (LinearSeqOptimized)these.tail();
        }
        return last2;
    }

    private static final int loop$1(LinearSeqOptimized $this, int i, LinearSeqOptimized xs, int len$1) {
        while (true) {
            block6: {
                int n;
                block5: {
                    block4: {
                        if (i != len$1) break block4;
                        n = xs.isEmpty() ? 0 : 1;
                        break block5;
                    }
                    if (!xs.isEmpty()) break block6;
                    n = -1;
                }
                return n;
            }
            xs = (LinearSeqOptimized)xs.tail();
            ++i;
        }
    }

    public static void $init$(LinearSeqOptimized $this) {
    }
}

