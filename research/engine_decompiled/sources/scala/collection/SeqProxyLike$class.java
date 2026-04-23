/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Serializable;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.SeqProxyLike;
import scala.collection.SeqView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;

public abstract class SeqProxyLike$class {
    public static int size(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).size();
    }

    public static Seq toSeq(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).toSeq();
    }

    public static int length(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).length();
    }

    public static Object apply(SeqProxyLike $this, int idx) {
        return ((Function1)$this.self()).apply(BoxesRunTime.boxToInteger(idx));
    }

    public static int lengthCompare(SeqProxyLike $this, int len) {
        return ((SeqLike)$this.self()).lengthCompare(len);
    }

    public static boolean isDefinedAt(SeqProxyLike $this, int x) {
        return ((GenSeqLike)$this.self()).isDefinedAt(x);
    }

    public static int segmentLength(SeqProxyLike $this, Function1 p, int from2) {
        return ((SeqLike)$this.self()).segmentLength(p, from2);
    }

    public static int prefixLength(SeqProxyLike $this, Function1 p) {
        return ((GenSeqLike)$this.self()).prefixLength(p);
    }

    public static int indexWhere(SeqProxyLike $this, Function1 p) {
        return ((GenSeqLike)$this.self()).indexWhere(p);
    }

    public static int indexWhere(SeqProxyLike $this, Function1 p, int from2) {
        return ((SeqLike)$this.self()).indexWhere(p, from2);
    }

    public static int indexOf(SeqProxyLike $this, Object elem) {
        return ((GenSeqLike)$this.self()).indexOf(elem);
    }

    public static int indexOf(SeqProxyLike $this, Object elem, int from2) {
        return ((GenSeqLike)$this.self()).indexOf(elem, from2);
    }

    public static int lastIndexOf(SeqProxyLike $this, Object elem) {
        return ((GenSeqLike)$this.self()).lastIndexOf(elem);
    }

    public static int lastIndexOf(SeqProxyLike $this, Object elem, int end) {
        return ((SeqLike)$this.self()).lastIndexWhere(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$1;

            public final boolean apply(A x$1) {
                Object object = this.elem$1;
                return object == x$1 ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, x$1) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, x$1) : object.equals(x$1))));
            }
            {
                this.elem$1 = elem$1;
            }
        }, end);
    }

    public static int lastIndexWhere(SeqProxyLike $this, Function1 p) {
        return ((SeqLike)$this.self()).lastIndexWhere(p, $this.length() - 1);
    }

    public static int lastIndexWhere(SeqProxyLike $this, Function1 p, int end) {
        return ((GenSeqLike)$this.self()).lastIndexWhere(p);
    }

    public static Seq reverse(SeqProxyLike $this) {
        return (Seq)((SeqLike)$this.self()).reverse();
    }

    public static Object reverseMap(SeqProxyLike $this, Function1 f, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).reverseMap(f, bf);
    }

    public static Iterator reverseIterator(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).reverseIterator();
    }

    public static boolean startsWith(SeqProxyLike $this, GenSeq that, int offset) {
        return ((SeqLike)$this.self()).startsWith(that, offset);
    }

    public static boolean startsWith(SeqProxyLike $this, GenSeq that) {
        return ((GenSeqLike)$this.self()).startsWith(that);
    }

    public static boolean endsWith(SeqProxyLike $this, GenSeq that) {
        return ((SeqLike)$this.self()).endsWith(that);
    }

    public static int indexOfSlice(SeqProxyLike $this, GenSeq that) {
        return ((SeqLike)$this.self()).indexOfSlice(that);
    }

    public static int indexOfSlice(SeqProxyLike $this, GenSeq that, int from2) {
        return ((SeqLike)$this.self()).indexOfSlice(that);
    }

    public static int lastIndexOfSlice(SeqProxyLike $this, GenSeq that) {
        return ((SeqLike)$this.self()).lastIndexOfSlice(that);
    }

    public static int lastIndexOfSlice(SeqProxyLike $this, GenSeq that, int end) {
        return ((SeqLike)$this.self()).lastIndexOfSlice(that, end);
    }

    public static boolean containsSlice(SeqProxyLike $this, GenSeq that) {
        return ((SeqLike)$this.self()).indexOfSlice(that) != -1;
    }

    public static boolean contains(SeqProxyLike $this, Object elem) {
        return ((SeqLike)$this.self()).contains(elem);
    }

    public static Object union(SeqProxyLike $this, GenSeq that, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).union(that, bf);
    }

    public static Seq diff(SeqProxyLike $this, GenSeq that) {
        return (Seq)((SeqLike)$this.self()).diff(that);
    }

    public static Seq intersect(SeqProxyLike $this, GenSeq that) {
        return (Seq)((SeqLike)$this.self()).intersect(that);
    }

    public static Seq distinct(SeqProxyLike $this) {
        return (Seq)((SeqLike)$this.self()).distinct();
    }

    public static Object patch(SeqProxyLike $this, int from2, GenSeq patch2, int replaced, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).patch(from2, patch2, replaced, bf);
    }

    public static Object updated(SeqProxyLike $this, int index, Object elem, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).updated(index, elem, bf);
    }

    public static Object $plus$colon(SeqProxyLike $this, Object elem, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).$plus$colon(elem, bf);
    }

    public static Object $colon$plus(SeqProxyLike $this, Object elem, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).$colon$plus(elem, bf);
    }

    public static Object padTo(SeqProxyLike $this, int len, Object elem, CanBuildFrom bf) {
        return ((SeqLike)$this.self()).padTo(len, elem, bf);
    }

    public static boolean corresponds(SeqProxyLike $this, GenSeq that, Function2 p) {
        return ((SeqLike)$this.self()).corresponds(that, p);
    }

    public static Seq sortWith(SeqProxyLike $this, Function2 lt) {
        return (Seq)((SeqLike)$this.self()).sortWith(lt);
    }

    public static Seq sortBy(SeqProxyLike $this, Function1 f, Ordering ord) {
        return (Seq)((SeqLike)$this.self()).sortBy(f, ord);
    }

    public static Seq sorted(SeqProxyLike $this, Ordering ord) {
        return (Seq)((SeqLike)$this.self()).sorted(ord);
    }

    public static Range indices(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).indices();
    }

    public static SeqView view(SeqProxyLike $this) {
        return ((SeqLike)$this.self()).view();
    }

    public static SeqView view(SeqProxyLike $this, int from2, int until2) {
        return ((SeqLike)$this.self()).view(from2, until2);
    }

    public static void $init$(SeqProxyLike $this) {
    }
}

