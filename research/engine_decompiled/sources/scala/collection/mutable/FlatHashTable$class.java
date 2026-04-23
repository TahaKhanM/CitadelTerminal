/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.TraversableOnce$class;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.FlatHashTable$;
import scala.collection.mutable.HashTable$;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;

public abstract class FlatHashTable$class {
    private static final boolean tableDebug(FlatHashTable $this) {
        return false;
    }

    public static int capacity(FlatHashTable $this, int expectedSize) {
        return expectedSize == 0 ? 1 : HashTable$.MODULE$.powerOfTwo(expectedSize);
    }

    public static int initialSize(FlatHashTable $this) {
        return 32;
    }

    private static int initialCapacity(FlatHashTable $this) {
        return $this.capacity($this.initialSize());
    }

    public static int randomSeed(FlatHashTable $this) {
        return FlatHashTable$.MODULE$.seedGenerator().get().nextInt();
    }

    public static int tableSizeSeed(FlatHashTable $this) {
        return Integer.bitCount($this.table().length - 1);
    }

    public static void init(FlatHashTable $this, ObjectInputStream in, Function1 f) {
        in.defaultReadObject();
        $this._loadFactor_$eq(in.readInt());
        Predef$.MODULE$.assert($this._loadFactor() > 0);
        int size2 = in.readInt();
        $this.tableSize_$eq(0);
        Predef$.MODULE$.assert(size2 >= 0);
        $this.table_$eq(new Object[$this.capacity(FlatHashTable$.MODULE$.sizeForThreshold(size2, $this._loadFactor()))]);
        $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), $this.table().length));
        $this.seedvalue_$eq(in.readInt());
        boolean smDefined = in.readBoolean();
        if (smDefined) {
            $this.sizeMapInit($this.table().length);
        } else {
            $this.sizemap_$eq(null);
        }
        for (int index = 0; index < size2; ++index) {
            Object elem = $this.entryToElem(in.readObject());
            f.apply(elem);
            $this.addElem(elem);
        }
    }

    public static void serializeTo(FlatHashTable $this, ObjectOutputStream out) {
        out.defaultWriteObject();
        out.writeInt($this._loadFactor());
        out.writeInt($this.tableSize());
        out.writeInt($this.seedvalue());
        out.writeBoolean($this.isSizeMapDefined());
        $this.iterator().foreach(new Serializable($this, out){
            public static final long serialVersionUID = 0L;
            private final ObjectOutputStream out$1;

            public final void apply(Object x$1) {
                this.out$1.writeObject(x$1);
            }
            {
                this.out$1 = out$1;
            }
        });
    }

    public static Option findEntry(FlatHashTable $this, Object elem) {
        Object object = FlatHashTable$class.findElemImpl($this, elem);
        Option option = object == null ? None$.MODULE$ : new Some($this.entryToElem(object));
        return option;
    }

    public static boolean containsElem(FlatHashTable $this, Object elem) {
        return FlatHashTable$class.findElemImpl($this, elem) != null;
    }

    /*
     * WARNING - void declaration
     */
    private static Object findElemImpl(FlatHashTable $this, Object elem) {
        Object searchEntry = $this.elemToEntry(elem);
        int h = $this.index(searchEntry.hashCode());
        Object curEntry = $this.table()[h];
        while (curEntry != null && !(curEntry == searchEntry ? true : (curEntry == null ? false : (curEntry instanceof Number ? BoxesRunTime.equalsNumObject((Number)curEntry, searchEntry) : (curEntry instanceof Character ? BoxesRunTime.equalsCharObject((Character)curEntry, searchEntry) : curEntry.equals(searchEntry)))))) {
            void var3_3;
            var3_3 = (var3_3 + true) % $this.table().length;
            Object object = $this.table()[var3_3];
        }
        return curEntry;
    }

    public static boolean addElem(FlatHashTable $this, Object elem) {
        return $this.addEntry($this.elemToEntry(elem));
    }

    /*
     * WARNING - void declaration
     */
    public static boolean addEntry(FlatHashTable $this, Object newEntry) {
        int h = $this.index(newEntry.hashCode());
        Object curEntry = $this.table()[h];
        while (true) {
            void var2_2;
            Object object;
            if (curEntry == null) {
                $this.table()[h] = newEntry;
                $this.tableSize_$eq($this.tableSize() + 1);
                $this.nnSizeMapAdd(h);
                if ($this.tableSize() >= $this.threshold()) {
                    FlatHashTable$class.growTable($this);
                }
                return true;
            }
            if (object == newEntry ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, newEntry) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, newEntry) : object.equals(newEntry))))) {
                return false;
            }
            var2_2 = (var2_2 + true) % $this.table().length;
            object = $this.table()[var2_2];
        }
    }

    /*
     * WARNING - void declaration
     */
    public static boolean removeElem(FlatHashTable $this, Object elem) {
        if (FlatHashTable$class.tableDebug($this)) {
            FlatHashTable$class.checkConsistent($this);
        }
        Object removalEntry = $this.elemToEntry(elem);
        int h = $this.index(removalEntry.hashCode());
        Object curEntry = $this.table()[h];
        while (curEntry != null) {
            void var6_3;
            void var2_2;
            Object object;
            if (object == var2_2 ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, var2_2) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, var2_2) : object.equals(var2_2))))) {
                void h0 = var6_3;
                void h1 = (var6_3 + true) % $this.table().length;
                while (true) {
                    if ($this.table()[h1] == null) {
                        $this.table()[h0] = null;
                        $this.tableSize_$eq($this.tableSize() - 1);
                        $this.nnSizeMapRemove((int)h0);
                        if (FlatHashTable$class.tableDebug($this)) {
                            FlatHashTable$class.checkConsistent($this);
                        }
                        return true;
                    }
                    int h2 = $this.index($this.table()[h1].hashCode());
                    if (h2 != h1 && FlatHashTable$class.precedes$1($this, h2, (int)h0)) {
                        $this.table()[h0] = $this.table()[h1];
                        h0 = h1;
                    }
                    h1 = (h1 + true) % $this.table().length;
                }
            }
            var6_3 = (var6_3 + true) % $this.table().length;
            object = $this.table()[var6_3];
        }
        return false;
    }

    public static Iterator iterator(FlatHashTable $this) {
        return new AbstractIterator<A>($this){
            private int i;
            private final /* synthetic */ FlatHashTable $outer;

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                while (this.i() < this.$outer.table().length && this.$outer.table()[this.i()] == null) {
                    this.i_$eq(this.i() + 1);
                }
                return this.i() < this.$outer.table().length;
            }

            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    this.i_$eq(this.i() + 1);
                    nothing$ = this.$outer.entryToElem(this.$outer.table()[this.i() - 1]);
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i = 0;
            }
        };
    }

    private static void growTable(FlatHashTable $this) {
        Object[] oldtable = $this.table();
        $this.table_$eq(new Object[$this.table().length * 2]);
        $this.tableSize_$eq(0);
        $this.nnSizeMapReset($this.table().length);
        $this.seedvalue_$eq($this.tableSizeSeed());
        $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), $this.table().length));
        for (int i = 0; i < oldtable.length; ++i) {
            Object entry = oldtable[i];
            java.io.Serializable serializable = entry == null ? BoxedUnit.UNIT : BoxesRunTime.boxToBoolean($this.addEntry(entry));
        }
        if (FlatHashTable$class.tableDebug($this)) {
            FlatHashTable$class.checkConsistent($this);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void checkConsistent(FlatHashTable $this) {
        Predef$ predef$ = Predef$.MODULE$;
        int n = $this.table().length;
        Range$ range$ = Range$.MODULE$;
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ FlatHashTable $outer;

            public final void apply(int i) {
                this.apply$mcVI$sp(i);
            }

            public void apply$mcVI$sp(int i) {
                if (this.$outer.table()[i] == null || this.$outer.containsElem(this.$outer.entryToElem(this.$outer.table()[i]))) {
                    return;
                }
                Serializable serializable = new Serializable(this, i){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ FlatHashTable$.anonfun.checkConsistent.1 $outer;
                    public final int i$1;

                    public final String apply() {
                        return new StringBuilder().append(this.i$1).append((Object)" ").append(this.$outer.$outer.table()[this.i$1]).append((Object)" ").append((Object)Predef$.MODULE$.refArrayOps(this.$outer.$outer.table()).mkString()).toString();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i$1 = i$1;
                    }
                };
                Predef$ predef$ = Predef$.MODULE$;
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)serializable.apply()).toString());
            }

            public /* synthetic */ FlatHashTable scala$collection$mutable$FlatHashTable$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return;
        int i1 = range2.start();
        while ($this.table()[i1] == null || $this.containsElem($this.entryToElem($this.table()[i1]))) {
            if (i1 == range2.lastElement()) {
                return;
            }
            i1 += range2.step();
        }
        Serializable serializable2 = new /* invalid duplicate definition of identical inner class */;
        Predef$ predef$2 = Predef$.MODULE$;
        Object[] objectArray = serializable2.$outer.$outer.table();
        Predef$ predef$3 = Predef$.MODULE$;
        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append(i1).append((Object)" ").append(serializable2.$outer.$outer.table()[i1]).append((Object)" ").append((Object)TraversableOnce$class.mkString(new ArrayOps.ofRef<Object>(objectArray))).toString()).toString());
    }

    public static void nnSizeMapAdd(FlatHashTable $this, int h) {
        if ($this.sizemap() != null) {
            int p = h >> $this.sizeMapBucketBitSize();
            int[] nArray = $this.sizemap();
            nArray[p] = nArray[p] + 1;
        }
    }

    public static void nnSizeMapRemove(FlatHashTable $this, int h) {
        if ($this.sizemap() != null) {
            int[] nArray = $this.sizemap();
            int n = h >> $this.sizeMapBucketBitSize();
            nArray[n] = nArray[n] - 1;
        }
    }

    public static void nnSizeMapReset(FlatHashTable $this, int tableLength) {
        if ($this.sizemap() != null) {
            int nsize = $this.calcSizeMapSize(tableLength);
            if ($this.sizemap().length != nsize) {
                $this.sizemap_$eq(new int[nsize]);
            } else {
                Arrays.fill($this.sizemap(), 0);
            }
        }
    }

    public static final int totalSizeMapBuckets(FlatHashTable $this) {
        return ($this.table().length - 1) / $this.sizeMapBucketSize() + 1;
    }

    public static int calcSizeMapSize(FlatHashTable $this, int tableLength) {
        return (tableLength >> $this.sizeMapBucketBitSize()) + 1;
    }

    public static void sizeMapInit(FlatHashTable $this, int tableLength) {
        $this.sizemap_$eq(new int[$this.calcSizeMapSize(tableLength)]);
    }

    public static void sizeMapInitAndRebuild(FlatHashTable $this) {
        $this.sizeMapInit($this.table().length);
        int totalbuckets = $this.totalSizeMapBuckets();
        int tableidx = 0;
        Object[] tbl = $this.table();
        int n = $this.sizeMapBucketSize();
        Predef$ predef$ = Predef$.MODULE$;
        int tableuntil = RichInt$.MODULE$.min$extension(n, tbl.length);
        for (int bucketidx = 0; bucketidx < totalbuckets; ++bucketidx) {
            int currbucketsz = 0;
            while (tableidx < tableuntil) {
                if (tbl[tableidx] != null) {
                    ++currbucketsz;
                }
                ++tableidx;
            }
            $this.sizemap()[bucketidx] = currbucketsz;
            tableuntil += $this.sizeMapBucketSize();
        }
    }

    public static void printSizeMap(FlatHashTable $this) {
        Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps($this.sizemap()).mkString("szmap: [", ", ", "]"));
    }

    public static void printContents(FlatHashTable $this) {
        Predef$.MODULE$.println(Predef$.MODULE$.refArrayOps($this.table()).mkString("[", ", ", "]"));
    }

    public static void sizeMapDisable(FlatHashTable $this) {
        $this.sizemap_$eq(null);
    }

    public static boolean isSizeMapDefined(FlatHashTable $this) {
        return $this.sizemap() != null;
    }

    public static boolean alwaysInitSizeMap(FlatHashTable $this) {
        return false;
    }

    public static final int index(FlatHashTable $this, int hcode) {
        int improved = $this.improve(hcode, $this.seedvalue());
        int ones = $this.table().length - 1;
        return improved >>> 32 - Integer.bitCount(ones) & ones;
    }

    public static void clearTable(FlatHashTable $this) {
        for (int i = $this.table().length - 1; i >= 0; --i) {
            $this.table()[i] = null;
        }
        $this.tableSize_$eq(0);
        $this.nnSizeMapReset($this.table().length);
    }

    public static FlatHashTable.Contents hashTableContents(FlatHashTable $this) {
        return new FlatHashTable.Contents($this._loadFactor(), $this.table(), $this.tableSize(), $this.threshold(), $this.seedvalue(), $this.sizemap());
    }

    public static void initWithContents(FlatHashTable $this, FlatHashTable.Contents c) {
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

    private static final boolean precedes$1(FlatHashTable $this, int i, int j) {
        int d = $this.table().length >> 1;
        return i <= j ? j - i < d : i - j > d;
    }

    public static void $init$(FlatHashTable $this) {
        $this._loadFactor_$eq(FlatHashTable$.MODULE$.defaultLoadFactor());
        $this.table_$eq(new Object[FlatHashTable$class.initialCapacity($this)]);
        $this.tableSize_$eq(0);
        $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), FlatHashTable$class.initialCapacity($this)));
        $this.sizemap_$eq(null);
        $this.seedvalue_$eq($this.tableSizeSeed());
    }
}

