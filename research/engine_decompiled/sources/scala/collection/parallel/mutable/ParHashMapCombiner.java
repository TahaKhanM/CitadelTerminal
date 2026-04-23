/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.mutable.DefaultEntry;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$;
import scala.collection.mutable.HashTable$HashUtils$class;
import scala.collection.mutable.HashTable$class;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.mutable.ParHashMap;
import scala.collection.parallel.mutable.ParHashMapCombiner$;
import scala.collection.parallel.mutable.ParHashMapCombiner$table$2$;
import scala.collection.parallel.package$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;
import scala.runtime.VolatileObjectRef;

@ScalaSignature(bytes="\u0006\u0001\u0005\rhAB\u0001\u0003\u0003\u0003\u0011!B\u0001\nQCJD\u0015m\u001d5NCB\u001cu.\u001c2j]\u0016\u0014(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0011A\f'/\u00197mK2T!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b+\rYa#I\n\u0004\u00011i\u0003CB\u0007\u000f!\r:C&D\u0001\u0005\u0013\tyAA\u0001\bCk\u000e\\W\r^\"p[\nLg.\u001a:\u0011\tE\u0011B\u0003I\u0007\u0002\u0011%\u00111\u0003\u0003\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005U1B\u0002\u0001\u0003\u0006/\u0001\u0011\r!\u0007\u0002\u0002\u0017\u000e\u0001\u0011C\u0001\u000e\u001e!\t\t2$\u0003\u0002\u001d\u0011\t9aj\u001c;iS:<\u0007CA\t\u001f\u0013\ty\u0002BA\u0002B]f\u0004\"!F\u0011\u0005\u000b\t\u0002!\u0019A\r\u0003\u0003Y\u0003B\u0001J\u0013\u0015A5\t!!\u0003\u0002'\u0005\tQ\u0001+\u0019:ICNDW*\u00199\u0011\t!RC\u0003I\u0007\u0002S)\u00111AB\u0005\u0003W%\u0012A\u0002R3gCVdG/\u00128uef\u0004B\u0001\n\u0001\u0015AA\u0019aF\u000f\u000b\u000f\u0005=BdB\u0001\u00198\u001d\t\tdG\u0004\u00023k5\t1G\u0003\u000251\u00051AH]8pizJ\u0011!C\u0005\u0003\u000f!I!a\u0001\u0004\n\u0005eJ\u0013!\u0003%bg\"$\u0016M\u00197f\u0013\tYDHA\u0005ICNDW\u000b^5mg*\u0011\u0011(\u000b\u0005\t}\u0001\u0011)\u0019!C\u0005\u007f\u0005yA/\u00192mK2{\u0017\r\u001a$bGR|'/F\u0001A!\t\t\u0012)\u0003\u0002C\u0011\t\u0019\u0011J\u001c;\t\u0011\u0011\u0003!\u0011!Q\u0001\n\u0001\u000b\u0001\u0003^1cY\u0016du.\u00193GC\u000e$xN\u001d\u0011\t\u000b\u0019\u0003A\u0011A$\u0002\rqJg.\u001b;?)\ta\u0003\nC\u0003?\u000b\u0002\u0007\u0001\tC\u0004K\u0001\t\u0007I\u0011B \u0002\u00159|g.\\1tW2,g\u000e\u0003\u0004M\u0001\u0001\u0006I\u0001Q\u0001\f]>tW.Y:lY\u0016t\u0007\u0005C\u0004O\u0001\t\u0007I\u0011B \u0002\u0013M,W\r\u001a<bYV,\u0007B\u0002)\u0001A\u0003%\u0001)\u0001\u0006tK\u0016$g/\u00197vK\u0002BQA\u0015\u0001\u0005\u0002M\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0003)Vk\u0011\u0001\u0001\u0005\u0006-F\u0003\r\u0001E\u0001\u0005K2,W\u000eC\u0003Y\u0001\u0011\u0005\u0011,\u0001\u0004sKN,H\u000e\u001e\u000b\u0002G\u0019!1\f\u0001\u0001]\u0005)1\u0015\u000e\u001c7CY>\u001c7n]\n\u00045v\u0003\u0007CA\t_\u0013\ty\u0006B\u0001\u0004B]f\u0014VM\u001a\t\u0005\u001b\u0005\u00045-\u0003\u0002c\t\t!A+Y:l!\t!&\f\u0003\u0005f5\n\u0005\t\u0015!\u0003g\u0003\u001d\u0011WoY6fiN\u00042!E4j\u0013\tA\u0007BA\u0003BeJ\f\u0017\u0010E\u0002k[\u001er!\u0001K6\n\u00051L\u0013AD+oe>dG.\u001a3Ck\u001a4WM]\u0005\u0003]>\u0014\u0001\"\u00168s_2dW\r\u001a\u0006\u0003Y&B\u0001\"\u001d.\u0003\u0002\u0003\u0006IA]\u0001\u0006i\u0006\u0014G.\u001a\t\u0003)N4Q\u0001\u001e\u0001\u0001\u0001U\u0014q\"\u00113eS:<\u0007*Y:i)\u0006\u0014G.Z\n\u0004gv3\b\u0003\u0002\u0015x)\u001dJ!\u0001_\u0015\u0003\u0013!\u000b7\u000f\u001b+bE2,\u0007\u0002\u0003>t\u0005\u0003\u0005\u000b\u0011\u0002!\u0002\u00119,X.\u001a7f[ND\u0001\u0002`:\u0003\u0002\u0003\u0006I\u0001Q\u0001\u0003Y\u001aD\u0001B`:\u0003\u0002\u0003\u0006I\u0001Q\u0001\u000b?N,W\r\u001a<bYV,\u0007B\u0002$t\t\u0003\t\t\u0001F\u0004s\u0003\u0007\t)!a\u0002\t\u000bi|\b\u0019\u0001!\t\u000bq|\b\u0019\u0001!\t\u000by|\b\u0019\u0001!\t\u000f\u0005-1\u000f\"\u0001\u0002\u000e\u000591/\u001a;TSj,G\u0003BA\b\u0003+\u00012!EA\t\u0013\r\t\u0019\u0002\u0003\u0002\u0005+:LG\u000fC\u0004\u0002\u0018\u0005%\u0001\u0019\u0001!\u0002\u0005MT\bbBA\u000eg\u0012\u0005\u0011QD\u0001\fS:\u001cXM\u001d;F]R\u0014\u0018\u0010\u0006\u0003\u0002 \u0005\u0015\u0002cA\t\u0002\"%\u0019\u00111\u0005\u0005\u0003\u000f\t{w\u000e\\3b]\"9\u0011qEA\r\u0001\u00049\u0013!A3\t\u000f\u0005-2\u000f\"\u0005\u0002.\u0005q1M]3bi\u0016tUm^#oiJLX\u0003BA\u0018\u0003w!RAGA\u0019\u0003kAq!a\r\u0002*\u0001\u0007A#A\u0002lKfD\u0001\"a\u000e\u0002*\u0001\u0007\u0011\u0011H\u0001\u0002qB\u0019Q#a\u000f\u0005\u000f\u0005u\u0012\u0011\u0006b\u00013\t\t\u0001\fC\u0005\u0002Bi\u0013\t\u0011)A\u0005\u0001\u00061qN\u001a4tKRD\u0011\"!\u0012[\u0005\u0003\u0005\u000b\u0011\u0002!\u0002\u000f!|w/\\1os\"1aI\u0017C\u0001\u0003\u0013\"\u0012bYA&\u0003\u001b\ny%!\u0015\t\r\u0015\f9\u00051\u0001g\u0011\u0019\t\u0018q\ta\u0001e\"9\u0011\u0011IA$\u0001\u0004\u0001\u0005bBA#\u0003\u000f\u0002\r\u0001\u0011\u0005\b1j\u0003\r\u0011\"\u0001@\u0011%\t9F\u0017a\u0001\n\u0003\tI&\u0001\u0006sKN,H\u000e^0%KF$B!a\u0004\u0002\\!I\u0011QLA+\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0004bBA15\u0002\u0006K\u0001Q\u0001\be\u0016\u001cX\u000f\u001c;!\u0011\u001d\t)G\u0017C\u0001\u0003O\nA\u0001\\3bMR!\u0011qBA5\u0011!\tY'a\u0019A\u0002\u00055\u0014\u0001\u00029sKZ\u0004B!EA8\u0001&\u0019\u0011\u0011\u000f\u0005\u0003\r=\u0003H/[8o\u0011\u001d\t)H\u0017C\u0005\u0003o\n\u0011BZ5mY\ncwnY6\u0015\u000b\u0001\u000bI(! \t\u000f\u0005m\u00141\u000fa\u0001\u0001\u0006)!\r\\8dW\"9\u0011qPA:\u0001\u0004I\u0017!B3mK6\u001c\bbBAB5\u0012\u0005\u0011QQ\u0001\u0006gBd\u0017\u000e^\u000b\u0003\u0003\u000f\u0003R!!#\u0002\u0010\u000el!!a#\u000b\u0007\u00055e!A\u0005j[6,H/\u00192mK&!\u0011\u0011SAF\u0005\u0011a\u0015n\u001d;\t\u000f\u0005U%\f\"\u0011\u0002\u0018\u0006)Q.\u001a:hKR!\u0011qBAM\u0011\u001d\tY*a%A\u0002\r\fA\u0001\u001e5bi\"9\u0011q\u0014.\u0005\u0002\u0005\u0005\u0016AE:i_VdGm\u00159mSR4UO\u001d;iKJ,\"!a\b\b\u0011\u0005\u0015&\u0001#\u0001\u0005\u0003O\u000b!\u0003U1s\u0011\u0006\u001c\b.T1q\u0007>l'-\u001b8feB\u0019A%!+\u0007\u000f\u0005\u0011\u0001\u0012\u0001\u0003\u0002,N\u0019\u0011\u0011V/\t\u000f\u0019\u000bI\u000b\"\u0001\u00020R\u0011\u0011q\u0015\u0005\u000b\u0003g\u000bIK1A\u0005\u0002\ty\u0014\u0001\u00053jg\u000e\u0014\u0018.\\5oC:$(-\u001b;t\u0011!\t9,!+!\u0002\u0013\u0001\u0015!\u00053jg\u000e\u0014\u0018.\\5oC:$(-\u001b;tA!Q\u00111XAU\u0005\u0004%\tAA \u0002\u00139,XN\u00197pG.\u001c\b\u0002CA`\u0003S\u0003\u000b\u0011\u0002!\u0002\u00159,XN\u00197pG.\u001c\b\u0005\u0003\u0006\u0002D\u0006%&\u0019!C\u0001\u0005}\n\u0001\u0003Z5tGJLW.\u001b8b]Rl\u0017m]6\t\u0011\u0005\u001d\u0017\u0011\u0016Q\u0001\n\u0001\u000b\u0011\u0003Z5tGJLW.\u001b8b]Rl\u0017m]6!\u0011)\tY-!+C\u0002\u0013\u0005!aP\u0001\u000e]>tW.Y:lY\u0016tw\r\u001e5\t\u0011\u0005=\u0017\u0011\u0016Q\u0001\n\u0001\u000baB\\8o[\u0006\u001c8\u000e\\3oORD\u0007\u0005\u0003\u0005\u0002T\u0006%F\u0011AAk\u0003\u0015\t\u0007\u000f\u001d7z+\u0019\t9.!8\u0002bV\u0011\u0011\u0011\u001c\t\u0007I\u0001\tY.a8\u0011\u0007U\ti\u000e\u0002\u0004\u0018\u0003#\u0014\r!\u0007\t\u0004+\u0005\u0005HA\u0002\u0012\u0002R\n\u0007\u0011\u0004")
public abstract class ParHashMapCombiner<K, V>
extends BucketCombiner<Tuple2<K, V>, ParHashMap<K, V>, DefaultEntry<K, V>, ParHashMapCombiner<K, V>>
implements HashTable.HashUtils<K> {
    private final int tableLoadFactor;
    private final int nonmasklen;
    private final int seedvalue;

    public static <K, V> ParHashMapCombiner<K, V> apply() {
        return ParHashMapCombiner$.MODULE$.apply();
    }

    private ParHashMapCombiner$table$2$ scala$collection$parallel$mutable$ParHashMapCombiner$$table$1$lzycompute(VolatileObjectRef x$1) {
        ParHashMapCombiner parHashMapCombiner = this;
        synchronized (parHashMapCombiner) {
            if (x$1.elem == null) {
                x$1.elem = new ParHashMapCombiner$table$2$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return (ParHashMapCombiner$table$2$)x$1.elem;
        }
    }

    @Override
    public final int sizeMapBucketBitSize() {
        return HashTable$HashUtils$class.sizeMapBucketBitSize(this);
    }

    @Override
    public final int sizeMapBucketSize() {
        return HashTable$HashUtils$class.sizeMapBucketSize(this);
    }

    @Override
    public int elemHashCode(K key) {
        return HashTable$HashUtils$class.elemHashCode(this, key);
    }

    @Override
    public final int improve(int hcode, int seed) {
        return HashTable$HashUtils$class.improve(this, hcode, seed);
    }

    private int tableLoadFactor() {
        return this.tableLoadFactor;
    }

    private int nonmasklen() {
        return this.nonmasklen;
    }

    private int seedvalue() {
        return this.seedvalue;
    }

    @Override
    public ParHashMapCombiner<K, V> $plus$eq(Tuple2<K, V> elem) {
        this.sz_$eq(this.sz() + 1);
        int hc = this.improve(this.elemHashCode(elem._1()), this.seedvalue());
        int pos = hc >>> this.nonmasklen();
        if (this.buckets()[pos] == null) {
            this.buckets()[pos] = new UnrolledBuffer(ClassTag$.MODULE$.apply(DefaultEntry.class));
        }
        this.buckets()[pos].$plus$eq(new DefaultEntry<K, V>(elem._1(), elem._2()));
        return this;
    }

    @Override
    public ParHashMap<K, V> result() {
        ParHashMap parHashMap;
        if (this.size() >= ParHashMapCombiner$.MODULE$.numblocks() * this.sizeMapBucketSize()) {
            AddingHashTable table = new AddingHashTable(this.size(), this.tableLoadFactor(), this.seedvalue());
            UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])Predef$.MODULE$.refArrayOps((Object[])this.buckets()).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final UnrolledBuffer.Unrolled<DefaultEntry<K, V>> apply(UnrolledBuffer<DefaultEntry<K, V>> b) {
                    return b != null ? b.headPtr() : null;
                }
            }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
            int insertcount = BoxesRunTime.unboxToInt(this.combinerTaskSupport().executeAndWaitResult(new FillBlocks(this, bucks, table, 0, bucks.length)));
            table.setSize(insertcount);
            HashTable.Contents c = table.hashTableContents();
            parHashMap = new ParHashMap(c);
        } else {
            VolatileObjectRef<Object> table$module = VolatileObjectRef.zero();
            for (int i = 0; i < ParHashMapCombiner$.MODULE$.numblocks(); ++i) {
                if (this.buckets()[i] == null) continue;
                this.buckets()[i].foreach(new Serializable(this, table$module){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParHashMapCombiner $outer;
                    private final VolatileObjectRef table$module$1;

                    public final void apply(DefaultEntry<K, V> elem) {
                        this.$outer.scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(this.table$module$1).insertEntry(elem);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.table$module$1 = table$module$1;
                    }
                });
            }
            parHashMap = new ParHashMap(this.scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(table$module).hashTableContents());
        }
        return parHashMap;
    }

    /*
     * Ignored method signature, as it can't be verified against descriptor
     */
    public final ParHashMapCombiner$table$2$ scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(VolatileObjectRef table$module$1) {
        return table$module$1.elem == null ? this.scala$collection$parallel$mutable$ParHashMapCombiner$$table$1$lzycompute(table$module$1) : (ParHashMapCombiner$table$2$)table$module$1.elem;
    }

    public ParHashMapCombiner(int tableLoadFactor) {
        this.tableLoadFactor = tableLoadFactor;
        super(ParHashMapCombiner$.MODULE$.numblocks());
        HashTable$HashUtils$class.$init$(this);
        this.nonmasklen = ParHashMapCombiner$.MODULE$.nonmasklength();
        this.seedvalue = 27;
    }

    public class FillBlocks
    implements Task<Object, FillBlocks> {
        private final UnrolledBuffer.Unrolled<DefaultEntry<K, V>>[] buckets;
        private final AddingHashTable table;
        private final int offset;
        private final int howmany;
        private int result;
        public final /* synthetic */ ParHashMapCombiner $outer;
        private volatile Throwable throwable;

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        @TraitSetter
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<Object> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public void signalAbort() {
            Task$class.signalAbort(this);
        }

        @Override
        public int result() {
            return this.result;
        }

        @Override
        public void result_$eq(int x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Object> prev) {
            int until2 = this.offset + this.howmany;
            this.result_$eq(0);
            for (int i = this.offset; i < until2; ++i) {
                this.result_$eq(this.result() + this.fillBlock(i, this.buckets[i]));
            }
        }

        /*
         * WARNING - void declaration
         */
        private int fillBlock(int block, UnrolledBuffer.Unrolled<DefaultEntry<K, V>> elems) {
            void var3_3;
            int insertcount = 0;
            int i = 0;
            AddingHashTable t = this.table;
            for (UnrolledBuffer.Unrolled unrolled = elems; unrolled != null; unrolled = unrolled.next()) {
                DefaultEntry[] chunkarr = (DefaultEntry[])unrolled.array();
                int chunksz = unrolled.size();
                while (i < chunksz) {
                    DefaultEntry elem = chunkarr[i];
                    if (t.insertEntry(elem)) {
                        ++insertcount;
                    }
                    ++i;
                }
                i = 0;
            }
            return (int)var3_3;
        }

        public List<FillBlocks> split() {
            int fp = this.howmany / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new FillBlocks[]{new FillBlocks(this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset, fp), new FillBlocks(this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset + fp, this.howmany - fp)}));
        }

        @Override
        public void merge(FillBlocks that) {
            this.result_$eq(this.result() + that.result());
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel());
        }

        public /* synthetic */ ParHashMapCombiner scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer() {
            return this.$outer;
        }

        public FillBlocks(ParHashMapCombiner<K, V> $outer, UnrolledBuffer.Unrolled<DefaultEntry<K, V>>[] buckets, AddingHashTable table, int offset, int howmany) {
            this.buckets = buckets;
            this.table = table;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = Integer.MIN_VALUE;
        }
    }

    public class AddingHashTable
    implements HashTable<K, DefaultEntry<K, V>> {
        private transient int _loadFactor;
        private transient HashEntry<Object, HashEntry>[] table;
        private transient int tableSize;
        private transient int threshold;
        private transient int[] sizemap;
        private transient int seedvalue;

        @Override
        public int _loadFactor() {
            return this._loadFactor;
        }

        @Override
        @TraitSetter
        public void _loadFactor_$eq(int x$1) {
            this._loadFactor = x$1;
        }

        @Override
        public HashEntry<K, DefaultEntry<K, V>>[] table() {
            return this.table;
        }

        @Override
        @TraitSetter
        public void table_$eq(HashEntry<K, DefaultEntry<K, V>>[] x$1) {
            this.table = x$1;
        }

        @Override
        public int tableSize() {
            return this.tableSize;
        }

        @Override
        @TraitSetter
        public void tableSize_$eq(int x$1) {
            this.tableSize = x$1;
        }

        @Override
        public int threshold() {
            return this.threshold;
        }

        @Override
        @TraitSetter
        public void threshold_$eq(int x$1) {
            this.threshold = x$1;
        }

        @Override
        public int[] sizemap() {
            return this.sizemap;
        }

        @Override
        @TraitSetter
        public void sizemap_$eq(int[] x$1) {
            this.sizemap = x$1;
        }

        @Override
        public int seedvalue() {
            return this.seedvalue;
        }

        @Override
        @TraitSetter
        public void seedvalue_$eq(int x$1) {
            this.seedvalue = x$1;
        }

        @Override
        public int tableSizeSeed() {
            return HashTable$class.tableSizeSeed(this);
        }

        @Override
        public int initialSize() {
            return HashTable$class.initialSize(this);
        }

        @Override
        public void init(ObjectInputStream in, Function0<DefaultEntry<K, V>> readEntry) {
            HashTable$class.init(this, in, readEntry);
        }

        @Override
        public void serializeTo(ObjectOutputStream out, Function1<DefaultEntry<K, V>, BoxedUnit> writeEntry) {
            HashTable$class.serializeTo(this, out, writeEntry);
        }

        @Override
        public HashEntry findEntry(Object key) {
            return HashTable$class.findEntry(this, key);
        }

        @Override
        public void addEntry(HashEntry e) {
            HashTable$class.addEntry(this, e);
        }

        @Override
        public HashEntry findOrAddEntry(Object key, Object value) {
            return HashTable$class.findOrAddEntry(this, key, value);
        }

        @Override
        public HashEntry removeEntry(Object key) {
            return HashTable$class.removeEntry(this, key);
        }

        @Override
        public Iterator<DefaultEntry<K, V>> entriesIterator() {
            return HashTable$class.entriesIterator(this);
        }

        @Override
        public <U> void foreachEntry(Function1<DefaultEntry<K, V>, U> f) {
            HashTable$class.foreachEntry(this, f);
        }

        @Override
        public void clearTable() {
            HashTable$class.clearTable(this);
        }

        @Override
        public void nnSizeMapAdd(int h) {
            HashTable$class.nnSizeMapAdd(this, h);
        }

        @Override
        public void nnSizeMapRemove(int h) {
            HashTable$class.nnSizeMapRemove(this, h);
        }

        @Override
        public void nnSizeMapReset(int tableLength) {
            HashTable$class.nnSizeMapReset(this, tableLength);
        }

        @Override
        public final int totalSizeMapBuckets() {
            return HashTable$class.totalSizeMapBuckets(this);
        }

        @Override
        public int calcSizeMapSize(int tableLength) {
            return HashTable$class.calcSizeMapSize(this, tableLength);
        }

        @Override
        public void sizeMapInit(int tableLength) {
            HashTable$class.sizeMapInit(this, tableLength);
        }

        @Override
        public void sizeMapInitAndRebuild() {
            HashTable$class.sizeMapInitAndRebuild(this);
        }

        @Override
        public void printSizeMap() {
            HashTable$class.printSizeMap(this);
        }

        @Override
        public void sizeMapDisable() {
            HashTable$class.sizeMapDisable(this);
        }

        @Override
        public boolean isSizeMapDefined() {
            return HashTable$class.isSizeMapDefined(this);
        }

        @Override
        public boolean alwaysInitSizeMap() {
            return HashTable$class.alwaysInitSizeMap(this);
        }

        @Override
        public boolean elemEquals(K key1, K key2) {
            return HashTable$class.elemEquals(this, key1, key2);
        }

        @Override
        public final int index(int hcode) {
            return HashTable$class.index(this, hcode);
        }

        @Override
        public void initWithContents(HashTable.Contents<K, DefaultEntry<K, V>> c) {
            HashTable$class.initWithContents(this, c);
        }

        @Override
        public HashTable.Contents<K, DefaultEntry<K, V>> hashTableContents() {
            return HashTable$class.hashTableContents(this);
        }

        @Override
        public final int sizeMapBucketBitSize() {
            return HashTable$HashUtils$class.sizeMapBucketBitSize(this);
        }

        @Override
        public final int sizeMapBucketSize() {
            return HashTable$HashUtils$class.sizeMapBucketSize(this);
        }

        @Override
        public int elemHashCode(K key) {
            return HashTable$HashUtils$class.elemHashCode(this, key);
        }

        @Override
        public final int improve(int hcode, int seed) {
            return HashTable$HashUtils$class.improve(this, hcode, seed);
        }

        public void setSize(int sz) {
            this.tableSize_$eq(sz);
        }

        public boolean insertEntry(DefaultEntry<K, V> e) {
            boolean bl;
            DefaultEntry olde;
            int h = this.index(this.elemHashCode((K)e.key()));
            DefaultEntry ce = olde = (DefaultEntry)this.table()[h];
            while (ce != null) {
                Object k = e.key();
                Object a = ce.key();
                if (a == k ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, k) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, k) : a.equals(k))))) {
                    h = -1;
                    ce = null;
                    continue;
                }
                ce = (DefaultEntry)ce.next();
            }
            if (h != -1) {
                e.next_$eq(olde);
                this.table()[h] = e;
                this.nnSizeMapAdd(h);
                bl = true;
            } else {
                bl = false;
            }
            return bl;
        }

        @Override
        public <X> Nothing$ createNewEntry(K key, X x) {
            return Predef$.MODULE$.$qmark$qmark$qmark();
        }

        public /* synthetic */ ParHashMapCombiner scala$collection$parallel$mutable$ParHashMapCombiner$AddingHashTable$$$outer() {
            return ParHashMapCombiner.this;
        }

        public AddingHashTable(int numelems, int lf, int _seedvalue) {
            if (ParHashMapCombiner.this == null) {
                throw null;
            }
            HashTable$HashUtils$class.$init$(this);
            HashTable$class.$init$(this);
            this._loadFactor_$eq(lf);
            this.table_$eq(new HashEntry[HashTable$.MODULE$.capacity(HashTable$.MODULE$.sizeForThreshold(this._loadFactor(), numelems))]);
            this.tableSize_$eq(0);
            this.seedvalue_$eq(_seedvalue);
            this.threshold_$eq(HashTable$.MODULE$.newThreshold(this._loadFactor(), this.table().length));
            this.sizeMapInit(this.table().length);
        }
    }
}

