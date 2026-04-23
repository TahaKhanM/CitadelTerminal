/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.None$;
import scala.Some;
import scala.collection.GenSet;
import scala.collection.Iterator;
import scala.collection.SortedSet;
import scala.collection.SortedSetLike;
import scala.math.Ordering;

public abstract class SortedSetLike$class {
    public static SortedSet keySet(SortedSetLike $this) {
        return (SortedSet)$this.repr();
    }

    public static Object firstKey(SortedSetLike $this) {
        return $this.head();
    }

    public static Object lastKey(SortedSetLike $this) {
        return $this.last();
    }

    public static SortedSet from(SortedSetLike $this, Object from2) {
        return $this.rangeImpl(new Some<Object>(from2), None$.MODULE$);
    }

    public static SortedSet until(SortedSetLike $this, Object until2) {
        return $this.rangeImpl(None$.MODULE$, new Some<Object>(until2));
    }

    public static SortedSet range(SortedSetLike $this, Object from2, Object until2) {
        return $this.rangeImpl(new Some<Object>(from2), new Some<Object>(until2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean subsetOf(SortedSetLike $this, GenSet that) {
        if (!(that instanceof SortedSet)) return $this.scala$collection$SortedSetLike$$super$subsetOf(that);
        SortedSet sortedSet = (SortedSet)that;
        Ordering ordering = sortedSet.ordering();
        Ordering ordering2 = $this.ordering();
        if (ordering != null) {
            if (!ordering.equals(ordering2)) return $this.scala$collection$SortedSetLike$$super$subsetOf(that);
            return sortedSet.hasAll($this.iterator());
        }
        if (ordering2 == null) return sortedSet.hasAll($this.iterator());
        return $this.scala$collection$SortedSetLike$$super$subsetOf(that);
    }

    public static Iterator iteratorFrom(SortedSetLike $this, Object start) {
        return $this.keysIteratorFrom(start);
    }

    public static void $init$(SortedSetLike $this) {
    }
}

