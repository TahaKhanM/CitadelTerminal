/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.FlatHashTable$NullSentinel$;
import scala.util.hashing.package$;

public abstract class FlatHashTable$HashUtils$class {
    public static final int sizeMapBucketBitSize(FlatHashTable.HashUtils $this) {
        return 5;
    }

    public static final int sizeMapBucketSize(FlatHashTable.HashUtils $this) {
        return 1 << $this.sizeMapBucketBitSize();
    }

    public static final int improve(FlatHashTable.HashUtils $this, int hcode, int seed) {
        return Integer.rotateRight(package$.MODULE$.byteswap32(hcode), seed);
    }

    public static final Object elemToEntry(FlatHashTable.HashUtils $this, Object elem) {
        return elem == null ? FlatHashTable$NullSentinel$.MODULE$ : elem;
    }

    public static final Object entryToElem(FlatHashTable.HashUtils $this, Object entry) {
        return entry == FlatHashTable$NullSentinel$.MODULE$ ? null : entry;
    }

    public static void $init$(FlatHashTable.HashUtils $this) {
    }
}

