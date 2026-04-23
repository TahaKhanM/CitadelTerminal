/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$;
import scala.runtime.BoxesRunTime;

public abstract class HashTable$class {
    public static int tableSizeSeed(HashTable $this) {
        return Integer.bitCount($this.table().length - 1);
    }

    public static int initialSize(HashTable $this) {
        return 16;
    }

    private static int initialThreshold(HashTable $this, int _loadFactor) {
        return HashTable$.MODULE$.newThreshold(_loadFactor, HashTable$class.initialCapacity($this));
    }

    private static int initialCapacity(HashTable $this) {
        return HashTable$.MODULE$.capacity($this.initialSize());
    }

    /*
     * WARNING - void declaration
     */
    public static int scala$collection$mutable$HashTable$$lastPopulatedIndex(HashTable $this) {
        void var1_1;
        for (int idx = $this.table().length - 1; $this.table()[idx] == null && idx > 0; --idx) {
        }
        return (int)var1_1;
    }

    public static void init(HashTable $this, ObjectInputStream in, Function0 readEntry) {
        in.defaultReadObject();
        $this._loadFactor_$eq(in.readInt());
        Predef$.MODULE$.assert($this._loadFactor() > 0);
        int size2 = in.readInt();
        $this.tableSize_$eq(0);
        Predef$.MODULE$.assert(size2 >= 0);
        $this.seedvalue_$eq(in.readInt());
        boolean smDefined = in.readBoolean();
        $this.table_$eq(new HashEntry[HashTable$.MODULE$.capacity(HashTable$.MODULE$.sizeForThreshold($this._loadFactor(), size2))]);
        $this.threshold_$eq(HashTable$.MODULE$.newThreshold($this._loadFactor(), $this.table().length));
        if (smDefined) {
            $this.sizeMapInit($this.table().length);
        } else {
            $this.sizemap_$eq(null);
        }
        for (int index = 0; index < size2; ++index) {
            $this.addEntry((HashEntry)readEntry.apply());
        }
    }

    public static void serializeTo(HashTable $this, ObjectOutputStream out, Function1 writeEntry) {
        out.defaultWriteObject();
        out.writeInt($this._loadFactor());
        out.writeInt($this.tableSize());
        out.writeInt($this.seedvalue());
        out.writeBoolean($this.isSizeMapDefined());
        $this.foreachEntry(writeEntry);
    }

    public static HashEntry findEntry(HashTable $this, Object key) {
        return HashTable$class.scala$collection$mutable$HashTable$$findEntry0($this, key, $this.index($this.elemHashCode(key)));
    }

    public static HashEntry scala$collection$mutable$HashTable$$findEntry0(HashTable $this, Object key, int h) {
        HashEntry e = $this.table()[h];
        HashEntry hashEntry;
        while (e != null && !$this.elemEquals(e.key(), key)) {
            hashEntry = (HashEntry)hashEntry.next();
        }
        return hashEntry;
    }

    public static void addEntry(HashTable $this, HashEntry e) {
        HashTable$class.scala$collection$mutable$HashTable$$addEntry0($this, e, $this.index($this.elemHashCode(e.key())));
    }

    public static void scala$collection$mutable$HashTable$$addEntry0(HashTable $this, HashEntry e, int h) {
        e.next_$eq($this.table()[h]);
        $this.table()[h] = e;
        $this.tableSize_$eq($this.tableSize() + 1);
        $this.nnSizeMapAdd(h);
        if ($this.tableSize() > $this.threshold()) {
            HashTable$class.resize($this, 2 * $this.table().length);
        }
    }

    public static HashEntry findOrAddEntry(HashTable $this, Object key, Object value) {
        HashEntry hashEntry;
        int h = $this.index($this.elemHashCode(key));
        HashEntry e = HashTable$class.scala$collection$mutable$HashTable$$findEntry0($this, key, h);
        if (e != null) {
            hashEntry = e;
        } else {
            HashTable$class.scala$collection$mutable$HashTable$$addEntry0($this, $this.createNewEntry(key, value), h);
            hashEntry = null;
        }
        return hashEntry;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static HashEntry removeEntry(HashTable $this, Object key) {
        int h = $this.index($this.elemHashCode(key));
        HashEntry e = $this.table()[h];
        if (e == null) return null;
        if ($this.elemEquals(e.key(), key)) {
            $this.table()[h] = (HashEntry)e.next();
            $this.tableSize_$eq($this.tableSize() - 1);
            $this.nnSizeMapRemove(h);
            e.next_$eq(null);
            return e;
        }
        HashEntry e1 = (HashEntry)e.next();
        while (true) {
            HashEntry hashEntry;
            if (e1 == null || $this.elemEquals(e1.key(), key)) {
                void var2_2;
                if (e1 == null) {
                    return null;
                }
                hashEntry.next_$eq(e1.next());
                $this.tableSize_$eq($this.tableSize() - 1);
                $this.nnSizeMapRemove((int)var2_2);
                e1.next_$eq(null);
                return e1;
            }
            hashEntry = e1;
            e1 = (HashEntry)e1.next();
        }
    }

    public static Iterator entriesIterator(HashTable $this) {
        return new AbstractIterator<Entry>($this){
            private final HashEntry<A, Entry>[] iterTable;
            private int idx;
            private HashEntry<A, Entry> es;

            private HashEntry<A, Entry>[] iterTable() {
                return this.iterTable;
            }

            private int idx() {
                return this.idx;
            }

            private void idx_$eq(int x$1) {
                this.idx = x$1;
            }

            private HashEntry<A, Entry> es() {
                return this.es;
            }

            private void es_$eq(HashEntry<A, Entry> x$1) {
                this.es = x$1;
            }

            public boolean hasNext() {
                return this.es() != null;
            }

            /*
             * WARNING - void declaration
             */
            public Entry next() {
                void var1_1;
                HashEntry<A, Entry> res = this.es();
                this.es_$eq((HashEntry)this.es().next());
                while (this.es() == null && this.idx() > 0) {
                    this.idx_$eq(this.idx() - 1);
                    this.es_$eq(this.iterTable()[this.idx()]);
                }
                return var1_1;
            }
            {
                this.iterTable = $outer.table();
                this.idx = HashTable$class.scala$collection$mutable$HashTable$$lastPopulatedIndex($outer);
                this.es = this.iterTable()[this.idx()];
            }
        };
    }

    public static void foreachEntry(HashTable $this, Function1 f) {
        HashEntry<A, Entry>[] iterTable = $this.table();
        int idx = HashTable$class.scala$collection$mutable$HashTable$$lastPopulatedIndex($this);
        HashEntry es = iterTable[idx];
        block0: while (es != null) {
            HashEntry next2 = (HashEntry)es.next();
            f.apply(es);
            es = next2;
            while (true) {
                if (es != null || idx <= 0) continue block0;
                es = iterTable[--idx];
            }
            break;
        }
        return;
    }

    public static void clearTable(HashTable $this) {
        for (int i = $this.table().length - 1; i >= 0; --i) {
            $this.table()[i] = null;
        }
        $this.tableSize_$eq(0);
        $this.nnSizeMapReset(0);
    }

    private static void resize(HashTable $this, int newSize) {
        HashEntry<A, Entry>[] oldTable = $this.table();
        $this.table_$eq(new HashEntry[newSize]);
        $this.nnSizeMapReset($this.table().length);
        block0: for (int i = oldTable.length - 1; i >= 0; --i) {
            HashEntry e = oldTable[i];
            while (true) {
                HashEntry hashEntry;
                if (e == null) {
                    continue block0;
                }
                int h = $this.index($this.elemHashCode(hashEntry.key()));
                HashEntry e1 = (HashEntry)hashEntry.next();
                hashEntry.next_$eq($this.table()[h]);
                $this.table()[h] = hashEntry;
                hashEntry = e1;
                $this.nnSizeMapAdd(h);
            }
        }
        $this.threshold_$eq(HashTable$.MODULE$.newThreshold($this._loadFactor(), newSize));
    }

    public static void nnSizeMapAdd(HashTable $this, int h) {
        if ($this.sizemap() != null) {
            int[] nArray = $this.sizemap();
            int n = h >> $this.sizeMapBucketBitSize();
            nArray[n] = nArray[n] + 1;
        }
    }

    public static void nnSizeMapRemove(HashTable $this, int h) {
        if ($this.sizemap() != null) {
            int[] nArray = $this.sizemap();
            int n = h >> $this.sizeMapBucketBitSize();
            nArray[n] = nArray[n] - 1;
        }
    }

    public static void nnSizeMapReset(HashTable $this, int tableLength) {
        if ($this.sizemap() != null) {
            int nsize = $this.calcSizeMapSize(tableLength);
            if ($this.sizemap().length != nsize) {
                $this.sizemap_$eq(new int[nsize]);
            } else {
                Arrays.fill($this.sizemap(), 0);
            }
        }
    }

    public static final int totalSizeMapBuckets(HashTable $this) {
        return $this.sizeMapBucketSize() < $this.table().length ? 1 : $this.table().length / $this.sizeMapBucketSize();
    }

    public static int calcSizeMapSize(HashTable $this, int tableLength) {
        return (tableLength >> $this.sizeMapBucketBitSize()) + 1;
    }

    public static void sizeMapInit(HashTable $this, int tableLength) {
        $this.sizemap_$eq(new int[$this.calcSizeMapSize(tableLength)]);
    }

    public static void sizeMapInitAndRebuild(HashTable $this) {
        $this.sizeMapInit($this.table().length);
        int tableidx = 0;
        HashEntry<A, Entry>[] tbl = $this.table();
        int tableuntil = tbl.length < $this.sizeMapBucketSize() ? tbl.length : $this.sizeMapBucketSize();
        int totalbuckets = $this.totalSizeMapBuckets();
        for (int bucketidx = 0; bucketidx < totalbuckets; ++bucketidx) {
            int currbucketsize = 0;
            while (tableidx < tableuntil) {
                for (HashEntry e = tbl[tableidx]; e != null; e = (HashEntry)e.next()) {
                    ++currbucketsize;
                }
                ++tableidx;
            }
            $this.sizemap()[bucketidx] = currbucketsize;
            tableuntil += $this.sizeMapBucketSize();
        }
    }

    public static void printSizeMap(HashTable $this) {
        Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps($this.sizemap()).toList());
    }

    public static void sizeMapDisable(HashTable $this) {
        $this.sizemap_$eq(null);
    }

    public static boolean isSizeMapDefined(HashTable $this) {
        return $this.sizemap() != null;
    }

    public static boolean alwaysInitSizeMap(HashTable $this) {
        return false;
    }

    public static boolean elemEquals(HashTable $this, Object key1, Object key2) {
        return key1 == key2 ? true : (key1 == null ? false : (key1 instanceof Number ? BoxesRunTime.equalsNumObject((Number)key1, key2) : (key1 instanceof Character ? BoxesRunTime.equalsCharObject((Character)key1, key2) : key1.equals(key2))));
    }

    public static final int index(HashTable $this, int hcode) {
        return $this.table().length == 1 ? 0 : $this.improve(hcode, $this.seedvalue()) >>> Integer.numberOfLeadingZeros($this.table().length - 1);
    }

    public static void initWithContents(HashTable $this, HashTable.Contents c) {
        if (c != null) {
            $this._loadFactor_$eq(c.loadFactor());
            $this.table_$eq(c.table());
            $this.tableSize_$eq(c.tableSize());
            $this.threshold_$eq(c.threshold());
            $this.seedvalue_$eq(c.seedvalue());
            $this.sizemap_$eq(c.sizemap());
        }
        if ($this.alwaysInitSizeMap() && $this.sizemap() == null) {
            $this.sizeMapInitAndRebuild();
        }
    }

    public static HashTable.Contents hashTableContents(HashTable $this) {
        return new HashTable.Contents($this._loadFactor(), $this.table(), $this.tableSize(), $this.threshold(), $this.seedvalue(), $this.sizemap());
    }

    public static void $init$(HashTable $this) {
        $this._loadFactor_$eq(HashTable$.MODULE$.defaultLoadFactor());
        $this.table_$eq(new HashEntry[HashTable$class.initialCapacity($this)]);
        $this.tableSize_$eq(0);
        $this.threshold_$eq(HashTable$class.initialThreshold($this, $this._loadFactor()));
        $this.sizemap_$eq(null);
        $this.seedvalue_$eq($this.tableSizeSeed());
    }
}

