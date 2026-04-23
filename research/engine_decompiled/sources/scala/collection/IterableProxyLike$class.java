/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenIterable;
import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.IterableProxyLike;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.generic.CanBuildFrom;

public abstract class IterableProxyLike$class {
    public static Iterator iterator(IterableProxyLike $this) {
        return ((IterableLike)$this.self()).iterator();
    }

    public static Iterator grouped(IterableProxyLike $this, int size2) {
        return ((IterableLike)$this.self()).grouped(size2);
    }

    public static Iterator sliding(IterableProxyLike $this, int size2) {
        return ((IterableLike)$this.self()).sliding(size2);
    }

    public static Iterator sliding(IterableProxyLike $this, int size2, int step) {
        return ((IterableLike)$this.self()).sliding(size2, step);
    }

    public static Iterable takeRight(IterableProxyLike $this, int n) {
        return (Iterable)((IterableLike)$this.self()).takeRight(n);
    }

    public static Iterable dropRight(IterableProxyLike $this, int n) {
        return (Iterable)((IterableLike)$this.self()).dropRight(n);
    }

    public static Object zip(IterableProxyLike $this, GenIterable that, CanBuildFrom bf) {
        return ((IterableLike)$this.self()).zip(that, bf);
    }

    public static Object zipAll(IterableProxyLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
        return ((IterableLike)$this.self()).zipAll(that, thisElem, thatElem, bf);
    }

    public static Object zipWithIndex(IterableProxyLike $this, CanBuildFrom bf) {
        return ((IterableLike)$this.self()).zipWithIndex(bf);
    }

    public static boolean sameElements(IterableProxyLike $this, GenIterable that) {
        return ((IterableLike)$this.self()).sameElements(that);
    }

    public static IterableView view(IterableProxyLike $this) {
        return ((IterableLike)$this.self()).view();
    }

    public static IterableView view(IterableProxyLike $this, int from2, int until2) {
        return ((IterableLike)$this.self()).view(from2, until2);
    }

    public static void $init$(IterableProxyLike $this) {
    }
}

