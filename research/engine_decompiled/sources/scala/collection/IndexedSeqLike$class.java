/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqLike;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.util.hashing.MurmurHash3$;

public abstract class IndexedSeqLike$class {
    public static int hashCode(IndexedSeqLike $this) {
        return MurmurHash3$.MODULE$.seqHash($this.seq());
    }

    public static IndexedSeq thisCollection(IndexedSeqLike $this) {
        return (IndexedSeq)$this;
    }

    public static IndexedSeq toCollection(IndexedSeqLike $this, Object repr) {
        return (IndexedSeq)repr;
    }

    public static Iterator iterator(IndexedSeqLike $this) {
        return new IndexedSeqLike.Elements($this, 0, $this.length());
    }

    /*
     * WARNING - void declaration
     */
    public static Buffer toBuffer(IndexedSeqLike $this) {
        void var1_1;
        ArrayBuffer result2 = new ArrayBuffer($this.size());
        $this.copyToBuffer(result2);
        return var1_1;
    }

    public static void $init$(IndexedSeqLike $this) {
    }
}

