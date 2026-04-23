/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.AbstractSet;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashEntry$class;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$HashUtils$class;
import scala.collection.mutable.HashTable$class;
import scala.collection.mutable.LinkedHashSet$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\t\rb\u0001B\u0001\u0003\u0001%\u0011Q\u0002T5oW\u0016$\u0007*Y:i'\u0016$(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)\"AC\t\u0014\u000f\u0001Y1DH\u0013*mA\u0019A\"D\b\u000e\u0003\tI!A\u0004\u0002\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV\r\u001e\t\u0003!Ea\u0001\u0001B\u0003\u0013\u0001\t\u00071CA\u0001B#\t!\u0002\u0004\u0005\u0002\u0016-5\ta!\u0003\u0002\u0018\r\t9aj\u001c;iS:<\u0007CA\u000b\u001a\u0013\tQbAA\u0002B]f\u00042\u0001\u0004\u000f\u0010\u0013\ti\"AA\u0002TKR\u0004Ba\b\u0012\u0010I5\t\u0001E\u0003\u0002\"\t\u00059q-\u001a8fe&\u001c\u0017BA\u0012!\u0005I9UM\\3sS\u000e\u001cV\r\u001e+f[Bd\u0017\r^3\u0011\u00051\u0001\u0001\u0003\u0002\u0007'\u001f!J!a\n\u0002\u0003\u000fM+G\u000fT5lKB\u0019A\u0002A\b\u0011\t1Qs\u0002L\u0005\u0003W\t\u0011\u0011\u0002S1tQR\u000b'\r\\3\u0011\u00075\u001avB\u0004\u0002\r]\u001d)qF\u0001E\u0001a\u0005iA*\u001b8lK\u0012D\u0015m\u001d5TKR\u0004\"\u0001D\u0019\u0007\u000b\u0005\u0011\u0001\u0012\u0001\u001a\u0014\u0007E\u001ad\u0007E\u0002 i\u0011J!!\u000e\u0011\u0003#5+H/\u00192mKN+GOR1di>\u0014\u0018\u0010\u0005\u0002\u0016o%\u0011\u0001H\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\u0006uE\"\taO\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003ABQ!P\u0019\u0005\u0004y\nAbY1o\u0005VLG\u000e\u001a$s_6,\"a\u0010&\u0016\u0003\u0001\u0003RaH!D\u0013.K!A\u0011\u0011\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\u0005\u0011+U\"A\u0019\n\u0005\u0019;%\u0001B\"pY2L!\u0001\u0013\u0011\u0003!\u001d+g.\u001a:jG\u000e{W\u000e]1oS>t\u0007C\u0001\tK\t\u0015\u0011BH1\u0001\u0014!\ra\u0001!\u0013\u0005\u0006\u001bF\"\tET\u0001\u0006K6\u0004H/_\u000b\u0003\u001fJ+\u0012\u0001\u0015\t\u0004\u0019\u0001\t\u0006C\u0001\tS\t\u0015\u0011BJ1\u0001\u0014\r\u0015!\u0016G\u0001\u0004V\u0005\u0015)e\u000e\u001e:z+\t1fl\u0005\u0003T/j3\u0004CA\u000bY\u0013\tIfA\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0019mkv,\u0003\u0002]\u0005\tI\u0001*Y:i\u000b:$(/\u001f\t\u0003!y#QAE*C\u0002M\u00012\u0001R*^\u0011!\t7K!b\u0001\n\u0003\u0011\u0017aA6fsV\tQ\f\u0003\u0005e'\n\u0005\t\u0015!\u0003^\u0003\u0011YW-\u001f\u0011\t\u000bi\u001aF\u0011\u00014\u0015\u0005};\u0007\"B1f\u0001\u0004i\u0006bB5T\u0001\u0004%\tA[\u0001\bK\u0006\u0014H.[3s+\u0005y\u0006b\u00027T\u0001\u0004%\t!\\\u0001\fK\u0006\u0014H.[3s?\u0012*\u0017\u000f\u0006\u0002ocB\u0011Qc\\\u0005\u0003a\u001a\u0011A!\u00168ji\"9!o[A\u0001\u0002\u0004y\u0016a\u0001=%c!1Ao\u0015Q!\n}\u000b\u0001\"Z1sY&,'\u000f\t\u0005\bmN\u0003\r\u0011\"\u0001k\u0003\u0015a\u0017\r^3s\u0011\u001dA8\u000b1A\u0005\u0002e\f\u0011\u0002\\1uKJ|F%Z9\u0015\u00059T\bb\u0002:x\u0003\u0003\u0005\ra\u0018\u0005\u0007yN\u0003\u000b\u0015B0\u0002\r1\fG/\u001a:!\u0011\u001dq\u0018'!A\u0005\n}\f1B]3bIJ+7o\u001c7wKR\u0011\u0011\u0011\u0001\t\u0005\u0003\u0007\ti!\u0004\u0002\u0002\u0006)!\u0011qAA\u0005\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0011\u0001\u00026bm\u0006LA!a\u0004\u0002\u0006\t1qJ\u00196fGRDaA\u000f\u0001\u0005\u0002\u0005MA#\u0001\u0015\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a\u0005I1m\\7qC:LwN\\\u000b\u0003\u00037\u00012aH$%\u000b\u0011!\u0006\u0001\u0001\u0017\t\u0013\u0005\u0005\u0002\u00011A\u0005\u0012\u0005\r\u0012A\u00034jeN$XI\u001c;ssV\u0011\u0011Q\u0005\t\u0005\u0003O\ti\"D\u0001\u0001\u0011%\tY\u0003\u0001a\u0001\n#\ti#\u0001\bgSJ\u001cH/\u00128uef|F%Z9\u0015\u00079\fy\u0003C\u0005s\u0003S\t\t\u00111\u0001\u0002&!A\u00111\u0007\u0001!B\u0013\t)#A\u0006gSJ\u001cH/\u00128uef\u0004\u0003\u0006BA\u0019\u0003o\u00012!FA\u001d\u0013\r\tYD\u0002\u0002\niJ\fgn]5f]RD\u0011\"a\u0010\u0001\u0001\u0004%\t\"a\t\u0002\u00131\f7\u000f^#oiJL\b\"CA\"\u0001\u0001\u0007I\u0011CA#\u00035a\u0017m\u001d;F]R\u0014\u0018p\u0018\u0013fcR\u0019a.a\u0012\t\u0013I\f\t%!AA\u0002\u0005\u0015\u0002\u0002CA&\u0001\u0001\u0006K!!\n\u0002\u00151\f7\u000f^#oiJL\b\u0005\u000b\u0003\u0002J\u0005]\u0002bBA)\u0001\u0011\u0005\u00131K\u0001\u0005g&TX-\u0006\u0002\u0002VA\u0019Q#a\u0016\n\u0007\u0005ecAA\u0002J]RDq!!\u0018\u0001\t\u0003\ty&\u0001\u0005d_:$\u0018-\u001b8t)\u0011\t\t'a\u001a\u0011\u0007U\t\u0019'C\u0002\u0002f\u0019\u0011qAQ8pY\u0016\fg\u000eC\u0004\u0002j\u0005m\u0003\u0019A\b\u0002\t\u0015dW-\u001c\u0005\b\u0003[\u0002A\u0011AA8\u0003!!\u0003\u000f\\;tI\u0015\fH\u0003BA\u0014\u0003cBq!!\u001b\u0002l\u0001\u0007q\u0002\u000b\u0005\u0002l\u0005U\u00141PA@!\r)\u0012qO\u0005\u0004\u0003s2!\u0001\u00063faJ,7-\u0019;fI>3XM\u001d:jI&tw-\t\u0002\u0002~\u0005a4&\u0010\u0011tQ>,H\u000e\u001a\u0011o_R\u0004#-\u001a\u0011pm\u0016\u0014(/\u001b3eK:\u00043o\u001c\u0011ji\u0002\u001aH/Y=tA\r|gn]5ti\u0016tG\u000fI<ji\"\u0004\u0013\r\u001a3/C\t\t\t)\u0001\u00043]E\nd\u0006\r\u0005\b\u0003\u000b\u0003A\u0011AAD\u0003%!S.\u001b8vg\u0012*\u0017\u000f\u0006\u0003\u0002(\u0005%\u0005bBA5\u0003\u0007\u0003\ra\u0004\u0015\t\u0003\u0007\u000b)(!$\u0002\u0000\u0005\u0012\u0011qR\u0001@[u\u00023\u000f[8vY\u0012\u0004cn\u001c;!E\u0016\u0004sN^3se&$G-\u001a8!g>\u0004\u0013\u000e\u001e\u0011ti\u0006L8\u000fI2p]NL7\u000f^3oi\u0002:\u0018\u000e\u001e5!e\u0016lwN^3/\u0011\u001d\t\u0019\n\u0001C!\u0003+\u000b1!\u00193e)\u0011\t\t'a&\t\u000f\u0005%\u0014\u0011\u0013a\u0001\u001f!9\u00111\u0014\u0001\u0005B\u0005u\u0015A\u0002:f[>4X\r\u0006\u0003\u0002b\u0005}\u0005bBA5\u00033\u0003\ra\u0004\u0005\b\u0003G\u0003A\u0011AAS\u0003!IG/\u001a:bi>\u0014XCAAT!\u0015\tI+a+\u0010\u001b\u0005!\u0011bAAW\t\tA\u0011\n^3sCR|'\u000fC\u0004\u00022\u0002!\t%a-\u0002\u000f\u0019|'/Z1dQV!\u0011QWAb)\rq\u0017q\u0017\u0005\t\u0003s\u000by\u000b1\u0001\u0002<\u0006\ta\r\u0005\u0004\u0016\u0003{{\u0011\u0011Y\u0005\u0004\u0003\u007f3!!\u0003$v]\u000e$\u0018n\u001c82!\r\u0001\u00121\u0019\u0003\b\u0003\u000b\fyK1\u0001\u0014\u0005\u0005)\u0006bBAe\u0001\u0011E\u00131Z\u0001\rM>\u0014X-Y2i\u000b:$(/_\u000b\u0005\u0003\u001b\f)\u000eF\u0002o\u0003\u001fD\u0001\"!/\u0002H\u0002\u0007\u0011\u0011\u001b\t\b+\u0005u\u0016QEAj!\r\u0001\u0012Q\u001b\u0003\b\u0003\u000b\f9M1\u0001\u0014\u0011\u001d\tI\u000e\u0001C\t\u00037\fab\u0019:fCR,g*Z<F]R\u0014\u00180\u0006\u0003\u0002^\u0006\u001dHCBA\u0013\u0003?\f\t\u000f\u0003\u0004b\u0003/\u0004\ra\u0004\u0005\t\u0003G\f9\u000e1\u0001\u0002f\u0006)A-^7nsB\u0019\u0001#a:\u0005\u000f\u0005%\u0018q\u001bb\u0001'\t\t!\tC\u0004\u0002n\u0002!\t%a<\u0002\u000b\rdW-\u0019:\u0015\u00039Dq!a=\u0001\t\u0013\t)0A\u0006xe&$Xm\u00142kK\u000e$Hc\u00018\u0002x\"A\u0011\u0011`Ay\u0001\u0004\tY0A\u0002pkR\u0004B!!@\u0003\u00045\u0011\u0011q \u0006\u0005\u0005\u0003\tI!\u0001\u0002j_&!!QAA\u0000\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7\t\u000f\t%\u0001\u0001\"\u0003\u0003\f\u0005Q!/Z1e\u001f\nTWm\u0019;\u0015\u00079\u0014i\u0001\u0003\u0005\u0003\u0010\t\u001d\u0001\u0019\u0001B\t\u0003\tIg\u000e\u0005\u0003\u0002~\nM\u0011\u0002\u0002B\u000b\u0003\u007f\u0014\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1nQ\u001d\u0001!\u0011\u0004B\u0010\u0005C\u00012!\u0006B\u000e\u0013\r\u0011iB\u0002\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012!\u0001")
public class LinkedHashSet<A>
extends AbstractSet<A>
implements HashTable<A, Entry<A>>,
Serializable {
    public static final long serialVersionUID = 1L;
    private transient Entry<A> firstEntry;
    private transient Entry<A> lastEntry;
    private transient int _loadFactor;
    private transient HashEntry<Object, HashEntry>[] table;
    private transient int tableSize;
    private transient int threshold;
    private transient int[] sizemap;
    private transient int seedvalue;

    public static <A> CanBuildFrom<LinkedHashSet<?>, A, LinkedHashSet<A>> canBuildFrom() {
        return LinkedHashSet$.MODULE$.canBuildFrom();
    }

    public static <A> Object setCanBuildFrom() {
        return LinkedHashSet$.MODULE$.setCanBuildFrom();
    }

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
    public HashEntry<A, Entry<A>>[] table() {
        return this.table;
    }

    @Override
    @TraitSetter
    public void table_$eq(HashEntry<A, Entry<A>>[] x$1) {
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
    public void init(ObjectInputStream in, Function0<Entry<A>> readEntry) {
        HashTable$class.init(this, in, readEntry);
    }

    @Override
    public void serializeTo(ObjectOutputStream out, Function1<Entry<A>, BoxedUnit> writeEntry) {
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
    public Iterator<Entry<A>> entriesIterator() {
        return HashTable$class.entriesIterator(this);
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
    public boolean elemEquals(A key1, A key2) {
        return HashTable$class.elemEquals(this, key1, key2);
    }

    @Override
    public final int index(int hcode) {
        return HashTable$class.index(this, hcode);
    }

    @Override
    public void initWithContents(HashTable.Contents<A, Entry<A>> c) {
        HashTable$class.initWithContents(this, c);
    }

    @Override
    public HashTable.Contents<A, Entry<A>> hashTableContents() {
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
    public int elemHashCode(A key) {
        return HashTable$HashUtils$class.elemHashCode(this, key);
    }

    @Override
    public final int improve(int hcode, int seed) {
        return HashTable$HashUtils$class.improve(this, hcode, seed);
    }

    @Override
    public GenericCompanion<LinkedHashSet> companion() {
        return LinkedHashSet$.MODULE$;
    }

    public Entry<A> firstEntry() {
        return this.firstEntry;
    }

    public void firstEntry_$eq(Entry<A> x$1) {
        this.firstEntry = x$1;
    }

    public Entry<A> lastEntry() {
        return this.lastEntry;
    }

    public void lastEntry_$eq(Entry<A> x$1) {
        this.lastEntry = x$1;
    }

    @Override
    public int size() {
        return this.tableSize();
    }

    @Override
    public boolean contains(A elem) {
        return this.findEntry((Object)elem) != null;
    }

    @Override
    public LinkedHashSet<A> $plus$eq(A elem) {
        this.add(elem);
        return this;
    }

    @Override
    public LinkedHashSet<A> $minus$eq(A elem) {
        this.remove(elem);
        return this;
    }

    @Override
    public boolean add(A elem) {
        return this.findOrAddEntry((Object)elem, (Object)null) == null;
    }

    @Override
    public boolean remove(A elem) {
        boolean bl;
        Entry e = (Entry)this.removeEntry((Object)elem);
        if (e == null) {
            bl = false;
        } else {
            if (e.earlier() == null) {
                this.firstEntry_$eq(e.later());
            } else {
                e.earlier().later_$eq(e.later());
            }
            if (e.later() == null) {
                this.lastEntry_$eq(e.earlier());
            } else {
                e.later().earlier_$eq(e.earlier());
            }
            e.earlier_$eq(null);
            e.later_$eq(null);
            bl = true;
        }
        return bl;
    }

    @Override
    public Iterator<A> iterator() {
        return new AbstractIterator<A>(this){
            private Entry<A> cur;

            private Entry<A> cur() {
                return this.cur;
            }

            private void cur_$eq(Entry<A> x$1) {
                this.cur = x$1;
            }

            public boolean hasNext() {
                return this.cur() != null;
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    A res = this.cur().key();
                    this.cur_$eq(this.cur().later());
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                this.cur = $outer.firstEntry();
            }
        };
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        for (Entry<A> cur = this.firstEntry(); cur != null; cur = cur.later()) {
            f.apply(cur.key());
        }
    }

    @Override
    public <U> void foreachEntry(Function1<Entry<A>, U> f) {
        for (Entry<A> cur = this.firstEntry(); cur != null; cur = cur.later()) {
            f.apply(cur);
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public <B> Entry<A> createNewEntry(A key, B dummy) {
        void var3_3;
        Entry<A> e = new Entry<A>(key);
        if (this.firstEntry() == null) {
            this.firstEntry_$eq(e);
        } else {
            this.lastEntry().later_$eq(e);
            e.earlier_$eq(this.lastEntry());
        }
        this.lastEntry_$eq(e);
        return var3_3;
    }

    @Override
    public void clear() {
        this.clearTable();
        this.firstEntry_$eq(null);
        this.lastEntry_$eq(null);
    }

    private void writeObject(ObjectOutputStream out) {
        this.serializeTo(out, (Function1<Entry<A>, BoxedUnit>)((Object)new Serializable(this, out){
            public static final long serialVersionUID = 0L;
            private final ObjectOutputStream out$1;

            public final void apply(Entry<A> e) {
                this.out$1.writeObject(e.key());
            }
            {
                this.out$1 = out$1;
            }
        }));
    }

    private void readObject(ObjectInputStream in) {
        this.firstEntry_$eq(null);
        this.lastEntry_$eq(null);
        this.init(in, (Function0<Entry<A>>)((Object)new Serializable(this, in){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ LinkedHashSet $outer;
            private final ObjectInputStream in$1;

            public final Entry<A> apply() {
                return this.$outer.createNewEntry(this.in$1.readObject(), (Object)null);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.in$1 = in$1;
            }
        }));
    }

    public LinkedHashSet() {
        HashTable$HashUtils$class.$init$(this);
        HashTable$class.$init$(this);
        this.firstEntry = null;
        this.lastEntry = null;
    }

    public static final class Entry<A>
    implements HashEntry<A, Entry<A>>,
    Serializable {
        private final A key;
        private Entry<A> earlier;
        private Entry<A> later;
        private Object next;

        @Override
        public Object next() {
            return this.next;
        }

        @Override
        @TraitSetter
        public void next_$eq(Object x$1) {
            this.next = x$1;
        }

        @Override
        public A key() {
            return this.key;
        }

        public Entry<A> earlier() {
            return this.earlier;
        }

        public void earlier_$eq(Entry<A> x$1) {
            this.earlier = x$1;
        }

        public Entry<A> later() {
            return this.later;
        }

        public void later_$eq(Entry<A> x$1) {
            this.later = x$1;
        }

        public Entry(A key) {
            this.key = key;
            HashEntry$class.$init$(this);
            this.earlier = null;
            this.later = null;
        }
    }
}

