/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.HashTable;
import scala.runtime.ScalaRunTime$;
import scala.util.hashing.package$;

public abstract class HashTable$HashUtils$class {
    public static final int sizeMapBucketBitSize(HashTable.HashUtils $this) {
        return 5;
    }

    public static final int sizeMapBucketSize(HashTable.HashUtils $this) {
        return 1 << $this.sizeMapBucketBitSize();
    }

    public static int elemHashCode(HashTable.HashUtils $this, Object key) {
        return ScalaRunTime$.MODULE$.hash(key);
    }

    public static final int improve(HashTable.HashUtils $this, int hcode, int seed) {
        return Integer.rotateRight(package$.MODULE$.byteswap32(hcode), seed);
    }

    public static void $init$(HashTable.HashUtils $this) {
    }
}

