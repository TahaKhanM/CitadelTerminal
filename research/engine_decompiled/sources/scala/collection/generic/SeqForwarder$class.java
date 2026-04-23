/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Function2;
import scala.collection.GenSeq;
import scala.collection.Iterator;
import scala.collection.generic.SeqForwarder;
import scala.collection.immutable.Range;

public abstract class SeqForwarder$class {
    public static int length(SeqForwarder $this) {
        return $this.underlying().length();
    }

    public static Object apply(SeqForwarder $this, int idx) {
        return $this.underlying().apply(idx);
    }

    public static int lengthCompare(SeqForwarder $this, int len) {
        return $this.underlying().lengthCompare(len);
    }

    public static boolean isDefinedAt(SeqForwarder $this, int x) {
        return $this.underlying().isDefinedAt(x);
    }

    public static int segmentLength(SeqForwarder $this, Function1 p, int from2) {
        return $this.underlying().segmentLength(p, from2);
    }

    public static int prefixLength(SeqForwarder $this, Function1 p) {
        return $this.underlying().prefixLength(p);
    }

    public static int indexWhere(SeqForwarder $this, Function1 p) {
        return $this.underlying().indexWhere(p);
    }

    public static int indexWhere(SeqForwarder $this, Function1 p, int from2) {
        return $this.underlying().indexWhere(p, from2);
    }

    public static int indexOf(SeqForwarder $this, Object elem) {
        return $this.underlying().indexOf(elem);
    }

    public static int indexOf(SeqForwarder $this, Object elem, int from2) {
        return $this.underlying().indexOf(elem, from2);
    }

    public static int lastIndexOf(SeqForwarder $this, Object elem) {
        return $this.underlying().lastIndexOf(elem);
    }

    public static int lastIndexOf(SeqForwarder $this, Object elem, int end) {
        return $this.underlying().lastIndexOf(elem, end);
    }

    public static int lastIndexWhere(SeqForwarder $this, Function1 p) {
        return $this.underlying().lastIndexWhere(p);
    }

    public static int lastIndexWhere(SeqForwarder $this, Function1 p, int end) {
        return $this.underlying().lastIndexWhere(p, end);
    }

    public static Iterator reverseIterator(SeqForwarder $this) {
        return $this.underlying().reverseIterator();
    }

    public static boolean startsWith(SeqForwarder $this, GenSeq that, int offset) {
        return $this.underlying().startsWith(that, offset);
    }

    public static boolean startsWith(SeqForwarder $this, GenSeq that) {
        return $this.underlying().startsWith(that);
    }

    public static boolean endsWith(SeqForwarder $this, GenSeq that) {
        return $this.underlying().endsWith(that);
    }

    public static int indexOfSlice(SeqForwarder $this, GenSeq that) {
        return $this.underlying().indexOfSlice(that);
    }

    public static int indexOfSlice(SeqForwarder $this, GenSeq that, int from2) {
        return $this.underlying().indexOfSlice(that, from2);
    }

    public static int lastIndexOfSlice(SeqForwarder $this, GenSeq that) {
        return $this.underlying().lastIndexOfSlice(that);
    }

    public static int lastIndexOfSlice(SeqForwarder $this, GenSeq that, int end) {
        return $this.underlying().lastIndexOfSlice(that, end);
    }

    public static boolean containsSlice(SeqForwarder $this, GenSeq that) {
        return $this.underlying().containsSlice(that);
    }

    public static boolean contains(SeqForwarder $this, Object elem) {
        return $this.underlying().contains(elem);
    }

    public static boolean corresponds(SeqForwarder $this, GenSeq that, Function2 p) {
        return $this.underlying().corresponds(that, p);
    }

    public static Range indices(SeqForwarder $this) {
        return $this.underlying().indices();
    }

    public static void $init$(SeqForwarder $this) {
    }
}

