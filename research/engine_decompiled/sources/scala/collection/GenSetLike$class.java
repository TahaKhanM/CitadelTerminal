/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenSet;
import scala.collection.GenSetLike;
import scala.util.hashing.MurmurHash3$;

public abstract class GenSetLike$class {
    public static boolean apply(GenSetLike $this, Object elem) {
        return $this.contains(elem);
    }

    public static Object intersect(GenSetLike $this, GenSet that) {
        return $this.filter(that);
    }

    public static Object $amp(GenSetLike $this, GenSet that) {
        return $this.intersect(that);
    }

    public static Object $bar(GenSetLike $this, GenSet that) {
        return $this.union(that);
    }

    public static Object $amp$tilde(GenSetLike $this, GenSet that) {
        return $this.diff(that);
    }

    public static boolean subsetOf(GenSetLike $this, GenSet that) {
        return $this.forall(that);
    }

    public static boolean equals(GenSetLike $this, Object that) {
        GenSet genSet;
        boolean bl = that instanceof GenSet ? $this == (genSet = (GenSet)that) || genSet.canEqual($this) && $this.size() == genSet.size() && GenSetLike$class.liftedTree1$1($this, genSet) : false;
        return bl;
    }

    public static int hashCode(GenSetLike $this) {
        return MurmurHash3$.MODULE$.setHash($this.seq());
    }

    private static final boolean liftedTree1$1(GenSetLike $this, GenSet x2$1) {
        boolean bl;
        try {
            bl = $this.subsetOf(x2$1);
        }
        catch (ClassCastException classCastException) {
            bl = false;
        }
        return bl;
    }

    public static void $init$(GenSetLike $this) {
    }
}

