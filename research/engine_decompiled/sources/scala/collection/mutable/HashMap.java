/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.MapLike;
import scala.collection.Set;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.DefaultEntry;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$HashUtils$class;
import scala.collection.mutable.HashTable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParHashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001\t]c\u0001B\u0001\u0003\u0001%\u0011q\u0001S1tQ6\u000b\u0007O\u0003\u0002\u0004\t\u00059Q.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001Qc\u0001\u0006\u00129M9\u0001a\u0003\u0010\"K-J\u0004\u0003\u0002\u0007\u000e\u001fmi\u0011AA\u0005\u0003\u001d\t\u00111\"\u00112tiJ\f7\r^'baB\u0011\u0001#\u0005\u0007\u0001\t\u0015\u0011\u0002A1\u0001\u0014\u0005\u0005\t\u0015C\u0001\u000b\u0019!\t)b#D\u0001\u0007\u0013\t9bAA\u0004O_RD\u0017N\\4\u0011\u0005UI\u0012B\u0001\u000e\u0007\u0005\r\te.\u001f\t\u0003!q!Q!\b\u0001C\u0002M\u0011\u0011A\u0011\t\u0005\u0019}y1$\u0003\u0002!\u0005\t\u0019Q*\u00199\u0011\u000b1\u0011sb\u0007\u0013\n\u0005\r\u0012!aB'ba2K7.\u001a\t\u0005\u0019\u0001y1\u0004\u0005\u0003\rM=A\u0013BA\u0014\u0003\u0005%A\u0015m\u001d5UC\ndW\r\u0005\u0003\rS=Y\u0012B\u0001\u0016\u0003\u00051!UMZ1vYR,e\u000e\u001e:z!\u0011aSf\f\u001a\u000e\u0003\u0011I!A\f\u0003\u0003)\r+8\u000f^8n!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f!\u0011)\u0002gD\u000e\n\u0005E2!A\u0002+va2,'\u0007\u0005\u00034o=YR\"\u0001\u001b\u000b\u0005\r)$B\u0001\u001c\u0005\u0003!\u0001\u0018M]1mY\u0016d\u0017B\u0001\u001d5\u0005)\u0001\u0016M\u001d%bg\"l\u0015\r\u001d\t\u0003+iJ!a\u000f\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011u\u0002!\u0011!Q\u0001\ny\n\u0001bY8oi\u0016tGo\u001d\t\u0005\u007f\t{\u0001F\u0004\u0002\r\u0001&\u0011\u0011IA\u0001\n\u0011\u0006\u001c\b\u000eV1cY\u0016L!a\u0011#\u0003\u0011\r{g\u000e^3oiNT!!\u0011\u0002\t\r\u0019\u0003A\u0011\u0001\u0003H\u0003\u0019a\u0014N\\5u}Q\u0011A\u0005\u0013\u0005\u0006{\u0015\u0003\rAP\u0003\u0005\u0015\u0002\u0001\u0001FA\u0003F]R\u0014\u0018\u0010C\u0003M\u0001\u0011\u0005S*A\u0003f[B$\u00180F\u0001%\u0011\u0015y\u0005\u0001\"\u0011Q\u0003\u0015\u0019G.Z1s)\u0005\t\u0006CA\u000bS\u0013\t\u0019fA\u0001\u0003V]&$\b\"B+\u0001\t\u00032\u0016\u0001B:ju\u0016,\u0012a\u0016\t\u0003+aK!!\u0017\u0004\u0003\u0007%sG\u000fC\u0003G\u0001\u0011\u00051\fF\u0001%\u0011\u0015i\u0006\u0001\"\u0011_\u0003\r\u0001\u0018M]\u000b\u0002e!)\u0001\r\u0001C!C\u0006A1m\u001c8uC&t7\u000f\u0006\u0002cKB\u0011QcY\u0005\u0003I\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003g?\u0002\u0007q\"A\u0002lKfDQ\u0001\u001b\u0001\u0005B%\fQ!\u00199qYf$\"a\u00076\t\u000b\u0019<\u0007\u0019A\b\t\u000b1\u0004A\u0011A7\u0002\u0007\u001d,G\u000f\u0006\u0002ocB\u0019Qc\\\u000e\n\u0005A4!AB(qi&|g\u000eC\u0003gW\u0002\u0007q\u0002C\u0003t\u0001\u0011\u0005C/A\bhKR|%/\u00127tKV\u0003H-\u0019;f)\rYRO\u001e\u0005\u0006MJ\u0004\ra\u0004\u0005\u0007oJ$\t\u0019\u0001=\u0002\u0019\u0011,g-Y;miZ\u000bG.^3\u0011\u0007UI8$\u0003\u0002{\r\tAAHY=oC6,g\b\u0003\u0004}\u0001\u0001&I!`\u0001\nM&tG-\u00128uef$RA`A\u0001\u0003\u0007\u0001\"a`%\u000e\u0003\u0001AQAZ>A\u0002=Aa!!\u0002|\u0001\u00049\u0016!\u00015\t\u0011\u0005%\u0001\u0001)C\u0005\u0003\u0017\t\u0001B\\8u\r>,h\u000e\u001a\u000b\u0006E\u00065\u0011q\u0002\u0005\u0007M\u0006\u001d\u0001\u0019A\b\t\u000f\u0005E\u0011q\u0001a\u0001}\u0006\tQ\r\u0003\u0005\u0002\u0016\u0001\u0001K\u0011BA\f\u0003!\tG\rZ#oiJLH#B\u000e\u0002\u001a\u0005m\u0001bBA\t\u0003'\u0001\rA \u0005\b\u0003\u000b\t\u0019\u00021\u0001X\u0011!\ty\u0002\u0001Q\u0005\n\u0005\u0005\u0012!C1eI\u0016sGO]=1)\u0015\t\u00161EA\u0013\u0011\u001d\t\t\"!\bA\u0002yDq!!\u0002\u0002\u001e\u0001\u0007q\u000bC\u0004\u0002*\u0001!\t%a\u000b\u0002\u0007A,H\u000fF\u0003o\u0003[\ty\u0003\u0003\u0004g\u0003O\u0001\ra\u0004\u0005\b\u0003c\t9\u00031\u0001\u001c\u0003\u00151\u0018\r\\;f\u0011\u001d\t)\u0004\u0001C!\u0003o\ta!\u001e9eCR,G#B)\u0002:\u0005m\u0002B\u00024\u00024\u0001\u0007q\u0002C\u0004\u00022\u0005M\u0002\u0019A\u000e\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B\u00051!/Z7pm\u0016$2A\\A\"\u0011\u00191\u0017Q\ba\u0001\u001f!9\u0011q\t\u0001\u0005\u0002\u0005%\u0013\u0001\u0003\u0013qYV\u001cH%Z9\u0015\u0007}\fY\u0005C\u0004\u0002N\u0005\u0015\u0003\u0019A\u0018\u0002\u0005-4\bbBA)\u0001\u0011\u0005\u00111K\u0001\nI5Lg.^:%KF$2a`A+\u0011\u00191\u0017q\na\u0001\u001f!9\u0011\u0011\f\u0001\u0005\u0002\u0005m\u0013\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005u\u0003\u0003\u0002\u0017\u0002`=J1!!\u0019\u0005\u0005!IE/\u001a:bi>\u0014\bbBA3\u0001\u0011\u0005\u0013qM\u0001\bM>\u0014X-Y2i+\u0011\tI'a\u001e\u0015\u0007E\u000bY\u0007\u0003\u0005\u0002n\u0005\r\u0004\u0019AA8\u0003\u00051\u0007CB\u000b\u0002r=\n)(C\u0002\u0002t\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007A\t9\bB\u0004\u0002z\u0005\r$\u0019A\n\u0003\u0003UCq!! \u0001\t\u0003\ny(\u0001\u0004lKf\u001cV\r^\u000b\u0003\u0003\u0003\u0003B\u0001LAB\u001f%\u0019\u0011Q\u0011\u0003\u0003\u0007M+G\u000fC\u0004\u0002\n\u0002!\t%a#\u0002\rY\fG.^3t+\t\ti\t\u0005\u0003-\u0003\u001f[\u0012bAAI\t\tA\u0011\n^3sC\ndW\rC\u0004\u0002\u0016\u0002!\t%a&\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0016\u0005\u0005e\u0005\u0003\u0002\u0017\u0002`=Aq!!(\u0001\t\u0003\ny*\u0001\bwC2,Xm]%uKJ\fGo\u001c:\u0016\u0005\u0005\u0005\u0006\u0003\u0002\u0017\u0002`mAq!!*\u0001\t\u0003\t9+\u0001\u0006vg\u0016\u001c\u0016N_3NCB$2!UAU\u0011\u001d\tY+a)A\u0002\t\f\u0011\u0001\u001e\u0005\b\u0003_\u0003A\u0011CAY\u00039\u0019'/Z1uK:+w/\u00128uef,B!a-\u0002<R)a0!.\u00028\"1a-!,A\u0002=A\u0001\"!\r\u0002.\u0002\u0007\u0011\u0011\u0018\t\u0004!\u0005mFaBA_\u0003[\u0013\ra\u0005\u0002\u0003\u0005FBq!!1\u0001\t\u0013\t\u0019-A\u0006xe&$Xm\u00142kK\u000e$HcA)\u0002F\"A\u0011qYA`\u0001\u0004\tI-A\u0002pkR\u0004B!a3\u0002V6\u0011\u0011Q\u001a\u0006\u0005\u0003\u001f\f\t.\u0001\u0002j_*\u0011\u00111[\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002X\u00065'AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6Dq!a7\u0001\t\u0013\ti.\u0001\u0006sK\u0006$wJ\u00196fGR$2!UAp\u0011!\t\t/!7A\u0002\u0005\r\u0018AA5o!\u0011\tY-!:\n\t\u0005\u001d\u0018Q\u001a\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007f\u0002\u0001\u0002l\u0006E\u0012\u0011\u001f\t\u0004+\u00055\u0018bAAx\r\t\u00012+\u001a:jC24VM]:j_:,\u0016\n\u0012\u0010\u0002\u0003\u001d9\u0011Q\u001f\u0002\t\u0002\u0005]\u0018a\u0002%bg\"l\u0015\r\u001d\t\u0004\u0019\u0005ehAB\u0001\u0003\u0011\u0003\tYpE\u0003\u0002z\u0006u\u0018\b\u0005\u0004\u0002\u0000\n\u0015!\u0011B\u0007\u0003\u0005\u0003Q1Aa\u0001\u0005\u0003\u001d9WM\\3sS\u000eLAAa\u0002\u0003\u0002\t\tR*\u001e;bE2,W*\u00199GC\u000e$xN]=\u0011\u00051\u0001\u0001b\u0002$\u0002z\u0012\u0005!Q\u0002\u000b\u0003\u0003oD\u0001B!\u0005\u0002z\u0012\r!1C\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0007\u0005+\u0011iC!\r\u0016\u0005\t]\u0001CCA\u0000\u00053\u0011iB!\u000b\u00034%!!1\u0004B\u0001\u00051\u0019\u0015M\u001c\"vS2$gI]8n!\u0011\u0011yB!\t\u000e\u0005\u0005e\u0018\u0002\u0002B\u0012\u0005K\u0011AaQ8mY&!!q\u0005B\u0001\u000559UM\\'ba\u001a\u000b7\r^8ssB1Q\u0003\rB\u0016\u0005_\u00012\u0001\u0005B\u0017\t\u0019\u0011\"q\u0002b\u0001'A\u0019\u0001C!\r\u0005\ru\u0011yA1\u0001\u0014!\u0019a\u0001Aa\u000b\u00030!9A*!?\u0005\u0002\t]RC\u0002B\u001d\u0005\u007f\u0011\u0019%\u0006\u0002\u0003<A1A\u0002\u0001B\u001f\u0005\u0003\u00022\u0001\u0005B \t\u0019\u0011\"Q\u0007b\u0001'A\u0019\u0001Ca\u0011\u0005\ru\u0011)D1\u0001\u0014\u0011)\u00119%!?\u0002\u0002\u0013%!\u0011J\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0003LA!!Q\nB*\u001b\t\u0011yE\u0003\u0003\u0003R\u0005E\u0017\u0001\u00027b]\u001eLAA!\u0016\u0003P\t1qJ\u00196fGR\u0004")
public class HashMap<A, B>
extends AbstractMap<A, B>
implements HashTable<A, DefaultEntry<A, B>>,
CustomParallelizable<Tuple2<A, B>, ParHashMap<A, B>>,
Serializable {
    public static final long serialVersionUID = 1L;
    private transient int _loadFactor;
    private transient HashEntry<Object, HashEntry>[] table;
    private transient int tableSize;
    private transient int threshold;
    private transient int[] sizemap;
    private transient int seedvalue;

    public static <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
        return HashMap$.MODULE$.canBuildFrom();
    }

    @Override
    public Combiner<Tuple2<A, B>, ParHashMap<A, B>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public int _loadFactor() {
        return this._loadFactor;
    }

    @Override
    public void _loadFactor_$eq(int x$1) {
        this._loadFactor = x$1;
    }

    @Override
    public HashEntry<A, DefaultEntry<A, B>>[] table() {
        return this.table;
    }

    @Override
    public void table_$eq(HashEntry<A, DefaultEntry<A, B>>[] x$1) {
        this.table = x$1;
    }

    @Override
    public int tableSize() {
        return this.tableSize;
    }

    @Override
    public void tableSize_$eq(int x$1) {
        this.tableSize = x$1;
    }

    @Override
    public int threshold() {
        return this.threshold;
    }

    @Override
    public void threshold_$eq(int x$1) {
        this.threshold = x$1;
    }

    @Override
    public int[] sizemap() {
        return this.sizemap;
    }

    @Override
    public void sizemap_$eq(int[] x$1) {
        this.sizemap = x$1;
    }

    @Override
    public int seedvalue() {
        return this.seedvalue;
    }

    @Override
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
    public void init(ObjectInputStream in, Function0<DefaultEntry<A, B>> readEntry) {
        HashTable$class.init(this, in, readEntry);
    }

    @Override
    public void serializeTo(ObjectOutputStream out, Function1<DefaultEntry<A, B>, BoxedUnit> writeEntry) {
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
    public Iterator<DefaultEntry<A, B>> entriesIterator() {
        return HashTable$class.entriesIterator(this);
    }

    @Override
    public <U> void foreachEntry(Function1<DefaultEntry<A, B>, U> f) {
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
    public boolean elemEquals(A key1, A key2) {
        return HashTable$class.elemEquals(this, key1, key2);
    }

    @Override
    public final int index(int hcode) {
        return HashTable$class.index(this, hcode);
    }

    @Override
    public void initWithContents(HashTable.Contents<A, DefaultEntry<A, B>> c) {
        HashTable$class.initWithContents(this, c);
    }

    @Override
    public HashTable.Contents<A, DefaultEntry<A, B>> hashTableContents() {
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
    public HashMap<A, B> empty() {
        return HashMap$.MODULE$.empty();
    }

    @Override
    public void clear() {
        this.clearTable();
    }

    @Override
    public int size() {
        return this.tableSize();
    }

    @Override
    public ParHashMap<A, B> par() {
        return new ParHashMap<A, B>(this.hashTableContents());
    }

    @Override
    public boolean contains(A key) {
        return this.findEntry((Object)key) != null;
    }

    @Override
    public B apply(A key) {
        DefaultEntry result2 = (DefaultEntry)this.findEntry((Object)key);
        return result2 == null ? this.default(key) : result2.value();
    }

    @Override
    public Option<B> get(A key) {
        DefaultEntry e = (DefaultEntry)this.findEntry((Object)key);
        return e == null ? None$.MODULE$ : new Some(e.value());
    }

    @Override
    public B getOrElseUpdate(A key, Function0<B> defaultValue) {
        int i = this.index(this.elemHashCode(key));
        DefaultEntry<A, B> entry = this.findEntry(key, i);
        return entry == null ? this.addEntry((DefaultEntry<A, B>)this.createNewEntry((Object)key, (Object)defaultValue.apply()), i) : entry.value();
    }

    /*
     * WARNING - void declaration
     */
    private DefaultEntry<A, B> findEntry(A key, int h) {
        void var3_3;
        DefaultEntry e = (DefaultEntry)this.table()[h];
        while (this.notFound(key, e)) {
            e = (DefaultEntry)e.next();
        }
        return var3_3;
    }

    private boolean notFound(A key, DefaultEntry<A, B> e) {
        return e != null && !this.elemEquals(e.key(), key);
    }

    private B addEntry(DefaultEntry<A, B> e, int h) {
        if (this.tableSize() >= this.threshold()) {
            this.addEntry(e);
        } else {
            this.addEntry0(e, h);
        }
        return e.value();
    }

    private void addEntry0(DefaultEntry<A, B> e, int h) {
        e.next_$eq((DefaultEntry)this.table()[h]);
        this.table()[h] = e;
        this.tableSize_$eq(this.tableSize() + 1);
        this.nnSizeMapAdd(h);
    }

    @Override
    public Option<B> put(A key, B value) {
        Option option;
        DefaultEntry e = (DefaultEntry)this.findOrAddEntry((Object)key, (Object)value);
        if (e == null) {
            option = None$.MODULE$;
        } else {
            Object v = e.value();
            e.value_$eq(value);
            option = new Some(v);
        }
        return option;
    }

    @Override
    public void update(A key, B value) {
        this.put(key, value);
    }

    @Override
    public Option<B> remove(A key) {
        DefaultEntry e = (DefaultEntry)this.removeEntry((Object)key);
        return e != null ? new Some(e.value()) : None$.MODULE$;
    }

    @Override
    public HashMap<A, B> $plus$eq(Tuple2<A, B> kv) {
        DefaultEntry e = (DefaultEntry)this.findOrAddEntry((Object)kv._1(), (Object)kv._2());
        if (e != null) {
            e.value_$eq(kv._2());
        }
        return this;
    }

    public HashMap<A, B> $minus$eq(A key) {
        this.removeEntry((Object)key);
        return this;
    }

    @Override
    public Iterator<Tuple2<A, B>> iterator() {
        return this.entriesIterator().map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final Tuple2<A, B> apply(DefaultEntry<A, B> e) {
                return new Tuple2<A, B>(e.key(), e.value());
            }
        });
    }

    @Override
    public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
        this.foreachEntry((Function1<DefaultEntry<A, B>, U>)((Object)new Serializable(this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;

            public final U apply(DefaultEntry<A, B> e) {
                return (U)this.f$1.apply(new Tuple2<A, B>(e.key(), e.value()));
            }
            {
                this.f$1 = f$1;
            }
        }));
    }

    @Override
    public Set<A> keySet() {
        return new MapLike.DefaultKeySet(this){
            private final /* synthetic */ HashMap $outer;

            public <U> void foreach(Function1<A, U> f) {
                this.$outer.foreachEntry(new Serializable(this, f){
                    public static final long serialVersionUID = 0L;
                    private final Function1 f$2;

                    public final U apply(DefaultEntry<A, B> e) {
                        return (U)this.f$2.apply(e.key());
                    }
                    {
                        this.f$2 = f$2;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    @Override
    public Iterable<B> values() {
        return new MapLike.DefaultValuesIterable(this){
            private final /* synthetic */ HashMap $outer;

            public <U> void foreach(Function1<B, U> f) {
                this.$outer.foreachEntry(new Serializable(this, f){
                    public static final long serialVersionUID = 0L;
                    private final Function1 f$3;

                    public final U apply(DefaultEntry<A, B> e) {
                        return (U)this.f$3.apply(e.value());
                    }
                    {
                        this.f$3 = f$3;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    @Override
    public Iterator<A> keysIterator() {
        return new AbstractIterator<A>(this){
            private final Iterator<DefaultEntry<A, B>> iter;

            private Iterator<DefaultEntry<A, B>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public A next() {
                return this.iter().next().key();
            }
            {
                this.iter = $outer.entriesIterator();
            }
        };
    }

    @Override
    public Iterator<B> valuesIterator() {
        return new AbstractIterator<B>(this){
            private final Iterator<DefaultEntry<A, B>> iter;

            private Iterator<DefaultEntry<A, B>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public B next() {
                return this.iter().next().value();
            }
            {
                this.iter = $outer.entriesIterator();
            }
        };
    }

    public void useSizeMap(boolean t) {
        if (t) {
            if (!this.isSizeMapDefined()) {
                this.sizeMapInitAndRebuild();
            }
        } else {
            this.sizeMapDisable();
        }
    }

    @Override
    public <B1> DefaultEntry<A, B> createNewEntry(A key, B1 value) {
        return new DefaultEntry<A, B1>(key, value);
    }

    private void writeObject(ObjectOutputStream out) {
        this.serializeTo(out, (Function1<DefaultEntry<A, B>, BoxedUnit>)((Object)new Serializable(this, out){
            public static final long serialVersionUID = 0L;
            private final ObjectOutputStream out$1;

            public final void apply(DefaultEntry<A, B> entry) {
                this.out$1.writeObject(entry.key());
                this.out$1.writeObject(entry.value());
            }
            {
                this.out$1 = out$1;
            }
        }));
    }

    private void readObject(ObjectInputStream in) {
        this.init(in, (Function0<DefaultEntry<A, B>>)((Object)new Serializable(this, in){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ HashMap $outer;
            private final ObjectInputStream in$1;

            public final DefaultEntry<A, B> apply() {
                return this.$outer.createNewEntry(this.in$1.readObject(), this.in$1.readObject());
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

    public HashMap(HashTable.Contents<A, DefaultEntry<A, B>> contents) {
        HashTable$HashUtils$class.$init$(this);
        HashTable$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        this.initWithContents(contents);
    }

    public HashMap() {
        this(null);
    }
}

