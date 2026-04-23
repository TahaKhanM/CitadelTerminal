/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate$class;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.DefaultEntry;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$HashUtils$class;
import scala.collection.mutable.HashTable$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.mutable.ParHashMap$;
import scala.collection.parallel.mutable.ParHashMapCombiner;
import scala.collection.parallel.mutable.ParHashMapCombiner$;
import scala.collection.parallel.mutable.ParHashTable;
import scala.collection.parallel.mutable.ParHashTable$class;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.ParIterable$class;
import scala.collection.parallel.mutable.ParMap$class;
import scala.collection.parallel.mutable.ParMapLike$class;
import scala.collection.parallel.mutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.TraitSetter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\t=e!B\u0001\u0003\u0001-i#A\u0003)be\"\u000b7\u000f['ba*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0005qCJ\fG\u000e\\3m\u0015\t9\u0001\"\u0001\u0006d_2dWm\u0019;j_:T\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\raq#I\n\b\u00015\t2EK\u001a:!\tqq\"D\u0001\t\u0013\t\u0001\u0002B\u0001\u0004B]f\u0014VM\u001a\t\u0005%M)\u0002%D\u0001\u0003\u0013\t!\"A\u0001\u0004QCJl\u0015\r\u001d\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007\u0011DA\u0001L#\tQR\u0004\u0005\u0002\u000f7%\u0011A\u0004\u0003\u0002\b\u001d>$\b.\u001b8h!\tqa$\u0003\u0002 \u0011\t\u0019\u0011I\\=\u0011\u0005Y\tC!\u0002\u0012\u0001\u0005\u0004I\"!\u0001,\u0011\u000b\u0011:S\u0003I\u0015\u000e\u0003\u0015R!A\n\u0004\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0001&\n\u0002\u0016\u000f\u0016tWM]5d!\u0006\u0014X*\u00199UK6\u0004H.\u0019;f!\t\u0011\u0002\u0001\u0005\u0004\u0013WU\u0001SFL\u0005\u0003Y\t\u0011!\u0002U1s\u001b\u0006\u0004H*[6f!\u0011\u0011\u0002!\u0006\u0011\u0011\t=\nT\u0003I\u0007\u0002a)\u00111AB\u0005\u0003eA\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0003\u0013iU1\u0014BA\u001b\u0003\u00051\u0001\u0016M\u001d%bg\"$\u0016M\u00197f!\u0011ys'\u0006\u0011\n\u0005a\u0002$\u0001\u0004#fM\u0006,H\u000e^#oiJL\bC\u0001\b;\u0013\tY\u0004B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003?\u0003!\u0019wN\u001c;f]R\u001c\b\u0003B C+Yr!a\f!\n\u0005\u0005\u0003\u0014!\u0003%bg\"$\u0016M\u00197f\u0013\t\u0019EI\u0001\u0005D_:$XM\u001c;t\u0015\t\t\u0005\u0007\u0003\u0004G\u0001\u0011\u0005aaR\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00055B\u0005\"B\u001fF\u0001\u0004qT\u0001\u0002&\u0001\u0001Y\u0012Q!\u00128uefDQA\u0012\u0001\u0005\u00021#\u0012!\f\u0005\u0006\u001d\u0002!\teT\u0001\r[\u0006\u00048i\\7qC:LwN\\\u000b\u0002!B\u0019A%U\u0015\n\u0005I+#AF$f]\u0016\u0014\u0018n\u0019)be6\u000b\u0007oQ8na\u0006t\u0017n\u001c8\t\u000bQ\u0003A\u0011I+\u0002\u000b\u0015l\u0007\u000f^=\u0016\u00035Baa\u0016\u0001!\n#B\u0016a\u00038fo\u000e{WNY5oKJ,\u0012!\u0017\t\u0005%i+\u0002%\u0003\u0002\\\u0005\t\u0011\u0002+\u0019:ICNDW*\u00199D_6\u0014\u0017N\\3s\u0011\u0015i\u0006\u0001\"\u0011_\u0003\r\u0019X-]\u000b\u0002]!)\u0001\r\u0001C\u0001C\u0006A1\u000f\u001d7jiR,'/F\u0001c!\t\u0019G-D\u0001\u0001\r\u0011)\u0007\u0001\u00014\u0003%A\u000b'\u000fS1tQ6\u000b\u0007/\u0013;fe\u0006$xN]\n\u0003I\u001e\u0004Ba\u00195kE&\u0011\u0011\u000e\u000e\u0002\u000e\u000b:$(/_%uKJ\fGo\u001c:\u0011\t9YW\u0003I\u0005\u0003Y\"\u0011a\u0001V;qY\u0016\u0014\u0004\u0002\u00038e\u0005\u0003\u0005\u000b\u0011B8\u0002\u000bM$\u0018M\u001d;\u0011\u00059\u0001\u0018BA9\t\u0005\rIe\u000e\u001e\u0005\tg\u0012\u0014\t\u0011)A\u0005_\u0006AQO\u001c;jY&#\u0007\u0010\u0003\u0005vI\n\u0005\t\u0015!\u0003p\u0003%!x\u000e^1m'&TX\r\u0003\u0005xI\n\u0005\t\u0015!\u00037\u0003\u0005)\u0007\"\u0002$e\t\u0003IH#\u00022{wrl\b\"\u00028y\u0001\u0004y\u0007\"B:y\u0001\u0004y\u0007\"B;y\u0001\u0004y\u0007\"B<y\u0001\u00041\u0004BB@e\t\u0003\t\t!\u0001\u0006f]R\u0014\u0018PM5uK6$2A[A\u0002\u0011\u0019\t)A a\u0001m\u0005)QM\u001c;ss\"9\u0011\u0011\u00023\u0005\u0002\u0005-\u0011a\u00038fo&#XM]1u_J$\u0012BYA\u0007\u0003#\t)\"!\u0007\t\u000f\u0005=\u0011q\u0001a\u0001_\u00069\u0011\u000e\u001a=Ge>l\u0007bBA\n\u0003\u000f\u0001\ra\\\u0001\tS\u0012DXK\u001c;jY\"9\u0011qCA\u0004\u0001\u0004y\u0017a\u0002;pi\u0006d7K\u001f\u0005\b\u00037\t9\u00011\u00017\u0003\t)7\u000fC\u0004\u0002 \u0001!\t%!\t\u0002\tML'0Z\u000b\u0002_\"9\u0011Q\u0005\u0001\u0005B\u0005\u001d\u0012!B2mK\u0006\u0014HCAA\u0015!\rq\u00111F\u0005\u0004\u0003[A!\u0001B+oSRDq!!\r\u0001\t\u0003\t\u0019$A\u0002hKR$B!!\u000e\u0002<A!a\"a\u000e!\u0013\r\tI\u0004\u0003\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005u\u0012q\u0006a\u0001+\u0005\u00191.Z=\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D\u0005\u0019\u0001/\u001e;\u0015\r\u0005U\u0012QIA$\u0011\u001d\ti$a\u0010A\u0002UAq!!\u0013\u0002@\u0001\u0007\u0001%A\u0003wC2,X\rC\u0004\u0002N\u0001!\t!a\u0014\u0002\rU\u0004H-\u0019;f)\u0019\tI#!\u0015\u0002T!9\u0011QHA&\u0001\u0004)\u0002bBA%\u0003\u0017\u0002\r\u0001\t\u0005\b\u0003/\u0002A\u0011AA-\u0003\u0019\u0011X-\\8wKR!\u0011QGA.\u0011\u001d\ti$!\u0016A\u0002UAq!a\u0018\u0001\t\u0003\t\t'\u0001\u0005%a2,8\u000fJ3r)\r\u0019\u00171\r\u0005\b\u0003K\ni\u00061\u0001k\u0003\tYg\u000fC\u0004\u0002j\u0001!\t!a\u001b\u0002\u0013\u0011j\u0017N\\;tI\u0015\fHcA2\u0002n!9\u0011QHA4\u0001\u0004)\u0002bBA9\u0001\u0011\u0005\u00131O\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0003\u0003k\u0002B!a\u001e\u0002\u00026\u0011\u0011\u0011\u0010\u0006\u0005\u0003w\ni(\u0001\u0003mC:<'BAA@\u0003\u0011Q\u0017M^1\n\t\u0005\r\u0015\u0011\u0010\u0002\u0007'R\u0014\u0018N\\4\t\u000f\u0005\u001d\u0005\u0001\"\u0005\u0002\n\u0006q1M]3bi\u0016tUm^#oiJLX\u0003BAF\u0003+#b!!$\u0002\u0010\u0006E\u0005CA2J\u0011\u001d\ti$!\"A\u0002UA\u0001\"!\u0013\u0002\u0006\u0002\u0007\u00111\u0013\t\u0004-\u0005UEaBAL\u0003\u000b\u0013\r!\u0007\u0002\u0003-FBq!a'\u0001\t\u0013\ti*A\u0006xe&$Xm\u00142kK\u000e$H\u0003BA\u0015\u0003?C\u0001\"!)\u0002\u001a\u0002\u0007\u00111U\u0001\u0004_V$\b\u0003BAS\u0003Wk!!a*\u000b\t\u0005%\u0016QP\u0001\u0003S>LA!!,\u0002(\n\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1n\u0011\u001d\t\t\f\u0001C\u0005\u0003g\u000b!B]3bI>\u0013'.Z2u)\u0011\tI#!.\t\u0011\u0005]\u0016q\u0016a\u0001\u0003s\u000b!!\u001b8\u0011\t\u0005\u0015\u00161X\u0005\u0005\u0003{\u000b9KA\tPE*,7\r^%oaV$8\u000b\u001e:fC6D\u0001\"!1\u0001\t\u0003\"\u00111Y\u0001\u0011EJ|7.\u001a8J]Z\f'/[1oiN,\"!!2\u0011\r\u0005\u001d\u0017\u0011ZAg\u001b\u00051\u0011bAAf\r\t\u00191+Z9\u0011\t\u0005=\u0017Q\u001b\b\u0004\u001d\u0005E\u0017bAAj\u0011\u00051\u0001K]3eK\u001aLA!a!\u0002X*\u0019\u00111\u001b\u0005\t\u000f\u0005m\u0007\u0001\"\u0003\u0002^\u0006Y1\r[3dW\n+8m[3u)\u0011\ty.a;\u0011\r\u0005\u0005\u0018q]A;\u001b\t\t\u0019OC\u0002\u0002f\u001a\t\u0011\"[7nkR\f'\r\\3\n\t\u0005%\u00181\u001d\u0002\u0005\u0019&\u001cH\u000fC\u0004\u0002n\u0006e\u0007\u0019A8\u0002\u0003%Dq!!=\u0001\t\u0013\t\u00190\u0001\u0006dQ\u0016\u001c7.\u00128uef$B!!>\u0003\u0002A1\u0011q_A\u007f\u0003\u001bt1ADA}\u0013\r\tY\u0010C\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tI/a@\u000b\u0007\u0005m\b\u0002C\u0004\u0002n\u0006=\b\u0019A8)\u000f\u0001\u0011)!!\u0013\u0003\fA\u0019aBa\u0002\n\u0007\t%\u0001B\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\tz\t\u0011aB\u0004\u0003\u0010\tA\tA!\u0005\u0002\u0015A\u000b'\u000fS1tQ6\u000b\u0007\u000fE\u0002\u0013\u0005'1a!\u0001\u0002\t\u0002\tU1#\u0002B\n\u0005/I\u0004\u0003\u0002\u0013\u0003\u001a%J1Aa\u0007&\u00055\u0001\u0016M]'ba\u001a\u000b7\r^8ss\"9aIa\u0005\u0005\u0002\t}AC\u0001B\t\u0011)\u0011\u0019Ca\u0005A\u0002\u0013\u0005\u0011\u0011E\u0001\u0006SR,'o\u001d\u0005\u000b\u0005O\u0011\u0019\u00021A\u0005\u0002\t%\u0012!C5uKJ\u001cx\fJ3r)\u0011\tICa\u000b\t\u0013\t5\"QEA\u0001\u0002\u0004y\u0017a\u0001=%c!A!\u0011\u0007B\nA\u0003&q.\u0001\u0004ji\u0016\u00148\u000f\t\u0005\b)\nMA\u0011\u0001B\u001b+\u0019\u00119D!\u0010\u0003BU\u0011!\u0011\b\t\u0007%\u0001\u0011YDa\u0010\u0011\u0007Y\u0011i\u0004\u0002\u0004\u0019\u0005g\u0011\r!\u0007\t\u0004-\t\u0005CA\u0002\u0012\u00034\t\u0007\u0011\u0004C\u0004X\u0005'!\tA!\u0012\u0016\r\t\u001d#Q\u000bB-+\t\u0011I\u0005\u0005\u0005\u0003L\t5#\u0011\u000bB.\u001b\u0005!\u0011b\u0001B(\t\tA1i\\7cS:,'\u000f\u0005\u0004\u000fW\nM#q\u000b\t\u0004-\tUCA\u0002\r\u0003D\t\u0007\u0011\u0004E\u0002\u0017\u00053\"aA\tB\"\u0005\u0004I\u0002C\u0002\n\u0001\u0005'\u00129\u0006\u0003\u0005\u0003`\tMA1\u0001B1\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0019\u0011\u0019Ga\u001f\u0003\u0000U\u0011!Q\r\t\nI\t\u001d$1\u000eB<\u0005\u0003K1A!\u001b&\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u0004BA!\u001c\u0003p5\u0011!1C\u0005\u0005\u0005c\u0012\u0019H\u0001\u0003D_2d\u0017b\u0001B;K\tiq)\u001a8NCB4\u0015m\u0019;pef\u0004bAD6\u0003z\tu\u0004c\u0001\f\u0003|\u00111\u0001D!\u0018C\u0002e\u00012A\u0006B@\t\u0019\u0011#Q\fb\u00013A1!\u0003\u0001B=\u0005{B!B!\"\u0003\u0014\u0005\u0005I\u0011\u0002BD\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\t%\u0005\u0003BA<\u0005\u0017KAA!$\u0002z\t1qJ\u00196fGR\u0004")
public class ParHashMap<K, V>
implements scala.collection.parallel.mutable.ParMap<K, V>,
ParHashTable<K, DefaultEntry<K, V>>,
Serializable {
    public static final long serialVersionUID = 1L;
    private transient int _loadFactor;
    private transient HashEntry<Object, HashEntry>[] table;
    private transient int tableSize;
    private transient int threshold;
    private transient int[] sizemap;
    private transient int seedvalue;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
        return ParHashMap$.MODULE$.canBuildFrom();
    }

    public static void iters_$eq(int n) {
        ParHashMap$.MODULE$.iters_$eq(n);
    }

    public static int iters() {
        return ParHashMap$.MODULE$.iters();
    }

    @Override
    public boolean alwaysInitSizeMap() {
        return ParHashTable$class.alwaysInitSizeMap(this);
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

    @Override
    public <U> scala.collection.parallel.mutable.ParMap<K, U> updated(K key, U value) {
        return ParMap$class.updated(this, key, value);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap<K, V> withDefault(Function1<K, V> d) {
        return ParMap$class.withDefault(this, d);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap<K, V> withDefaultValue(V d) {
        return ParMap$class.withDefaultValue(this, d);
    }

    @Override
    public <U> scala.collection.parallel.mutable.ParMap<K, U> $plus(Tuple2<K, U> kv) {
        return ParMapLike$class.$plus(this, kv);
    }

    @Override
    public scala.collection.parallel.mutable.ParMap $minus(Object key) {
        return ParMapLike$class.$minus(this, key);
    }

    @Override
    public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
        return super.clone();
    }

    @Override
    public Object clone() {
        return Cloneable$class.clone(this);
    }

    @Override
    public Shrinkable<K> $minus$eq(K elem1, K elem2, Seq<K> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<K> $minus$minus$eq(TraversableOnce<K> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
    }

    @Override
    public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce<Tuple2<K, V>> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public GenericCompanion<ParIterable> companion() {
        return ParIterable$class.companion(this);
    }

    @Override
    public ParIterable<Tuple2<K, V>> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public ParSeq<Tuple2<K, V>> toSeq() {
        return ParIterable$class.toSeq(this);
    }

    @Override
    public V default(K key) {
        return (V)scala.collection.parallel.ParMapLike$class.default(this, key);
    }

    @Override
    public V apply(K key) {
        return (V)scala.collection.parallel.ParMapLike$class.apply(this, key);
    }

    @Override
    public <U> U getOrElse(K key, Function0<U> function0) {
        return (U)scala.collection.parallel.ParMapLike$class.getOrElse(this, key, function0);
    }

    @Override
    public boolean contains(K key) {
        return scala.collection.parallel.ParMapLike$class.contains(this, key);
    }

    @Override
    public boolean isDefinedAt(K key) {
        return scala.collection.parallel.ParMapLike$class.isDefinedAt(this, key);
    }

    @Override
    public IterableSplitter<K> keysIterator() {
        return scala.collection.parallel.ParMapLike$class.keysIterator(this);
    }

    @Override
    public IterableSplitter<V> valuesIterator() {
        return scala.collection.parallel.ParMapLike$class.valuesIterator(this);
    }

    @Override
    public ParSet<K> keySet() {
        return scala.collection.parallel.ParMapLike$class.keySet(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<K> keys() {
        return scala.collection.parallel.ParMapLike$class.keys(this);
    }

    @Override
    public scala.collection.parallel.ParIterable<V> values() {
        return scala.collection.parallel.ParMapLike$class.values(this);
    }

    @Override
    public ParMap<K, V> filterKeys(Function1<K, Object> p) {
        return scala.collection.parallel.ParMapLike$class.filterKeys(this, p);
    }

    @Override
    public <S> ParMap<K, S> mapValues(Function1<V, S> f) {
        return scala.collection.parallel.ParMapLike$class.mapValues(this, f);
    }

    @Override
    public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
        return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
    }

    @Override
    @TraitSetter
    public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
        this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
    }

    private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
        ParHashMap parHashMap = this;
        synchronized (parHashMap) {
            if (this.ScanNode$module == null) {
                this.ScanNode$module = new ParIterableLike$ScanNode$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanNode$module;
        }
    }

    @Override
    public ParIterableLike$ScanNode$ ScanNode() {
        return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
    }

    private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
        ParHashMap parHashMap = this;
        synchronized (parHashMap) {
            if (this.ScanLeaf$module == null) {
                this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanLeaf$module;
        }
    }

    @Override
    public ParIterableLike$ScanLeaf$ ScanLeaf() {
        return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
    }

    @Override
    public void initTaskSupport() {
        ParIterableLike$class.initTaskSupport(this);
    }

    @Override
    public TaskSupport tasksupport() {
        return ParIterableLike$class.tasksupport(this);
    }

    @Override
    public void tasksupport_$eq(TaskSupport ts) {
        ParIterableLike$class.tasksupport_$eq(this, ts);
    }

    @Override
    public scala.collection.parallel.ParIterable repr() {
        return ParIterableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return ParIterableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return ParIterableLike$class.hasDefiniteSize(this);
    }

    @Override
    public boolean isEmpty() {
        return ParIterableLike$class.isEmpty(this);
    }

    @Override
    public boolean nonEmpty() {
        return ParIterableLike$class.nonEmpty(this);
    }

    @Override
    public Object head() {
        return ParIterableLike$class.head(this);
    }

    @Override
    public Option<Tuple2<K, V>> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public Object last() {
        return ParIterableLike$class.last(this);
    }

    @Override
    public Option<Tuple2<K, V>> lastOption() {
        return ParIterableLike$class.lastOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable init() {
        return ParIterableLike$class.init(this);
    }

    @Override
    public Splitter<Tuple2<K, V>> iterator() {
        return ParIterableLike$class.iterator(this);
    }

    @Override
    public scala.collection.parallel.ParIterable par() {
        return ParIterableLike$class.par(this);
    }

    @Override
    public boolean isStrictSplitterCollection() {
        return ParIterableLike$class.isStrictSplitterCollection(this);
    }

    @Override
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        return ParIterableLike$class.reuse(this, oldc, newc);
    }

    @Override
    public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
        return ParIterableLike$class.task2ops(this, tsk);
    }

    @Override
    public <R> Object wrap(Function0<R> body2) {
        return ParIterableLike$class.wrap(this, body2);
    }

    @Override
    public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
        return ParIterableLike$class.delegatedSignalling2ops(this, it);
    }

    @Override
    public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
        return ParIterableLike$class.builder2ops(this, cb);
    }

    @Override
    public <S, That> Object bf2seq(CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return ParIterableLike$class.bf2seq(this, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable sequentially(Function1 b) {
        return ParIterableLike$class.sequentially(this, b);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return ParIterableLike$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return ParIterableLike$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return ParIterableLike$class.mkString(this);
    }

    @Override
    public String toString() {
        return ParIterableLike$class.toString(this);
    }

    @Override
    public boolean canEqual(Object other) {
        return ParIterableLike$class.canEqual(this, other);
    }

    @Override
    public <U> U reduce(Function2<U, U, U> op) {
        return (U)ParIterableLike$class.reduce(this, op);
    }

    @Override
    public <U> Option<U> reduceOption(Function2<U, U, U> op) {
        return ParIterableLike$class.reduceOption(this, op);
    }

    @Override
    public <U> U fold(U z, Function2<U, U, U> op) {
        return (U)ParIterableLike$class.fold(this, z, op);
    }

    @Override
    public <S> S aggregate(Function0<S> z, Function2<S, Tuple2<K, V>, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, Tuple2<K, V>, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<Tuple2<K, V>, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, Tuple2<K, V>, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<Tuple2<K, V>, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, Tuple2<K, V>, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<Tuple2<K, V>, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.count(this, p);
    }

    @Override
    public <U> U sum(Numeric<U> num) {
        return (U)ParIterableLike$class.sum(this, num);
    }

    @Override
    public <U> U product(Numeric<U> num) {
        return (U)ParIterableLike$class.product(this, num);
    }

    @Override
    public Object min(Ordering ord) {
        return ParIterableLike$class.min(this, ord);
    }

    @Override
    public Object max(Ordering ord) {
        return ParIterableLike$class.max(this, ord);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That map(Function1<Tuple2<K, V>, S> f, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<Tuple2<K, V>, S> pf, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
        return ParIterableLike$class.find(this, p);
    }

    @Override
    public Object combinerFactory() {
        return ParIterableLike$class.combinerFactory(this);
    }

    @Override
    public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
        return ParIterableLike$class.combinerFactory(this, cbf);
    }

    @Override
    public scala.collection.parallel.ParIterable withFilter(Function1 pred) {
        return ParIterableLike$class.withFilter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filter(Function1 pred) {
        return ParIterableLike$class.filter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filterNot(Function1 pred) {
        return ParIterableLike$class.filterNot(this, pred);
    }

    @Override
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParHashMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> partition(Function1<Tuple2<K, V>, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> scala.collection.parallel.immutable.ParMap<K, ParHashMap<K, V>> groupBy(Function1<Tuple2<K, V>, K> f) {
        return ParIterableLike$class.groupBy(this, f);
    }

    @Override
    public scala.collection.parallel.ParIterable take(int n) {
        return ParIterableLike$class.take(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable drop(int n) {
        return ParIterableLike$class.drop(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable slice(int unc_from, int unc_until) {
        return ParIterableLike$class.slice(this, unc_from, unc_until);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParHashMap<K, V>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, Tuple2<K, V>, S> op, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<Tuple2<K, V>, S, S> op, CanBuildFrom<ParHashMap<K, V>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> span(Function1<Tuple2<K, V>, Object> pred) {
        return ParIterableLike$class.span(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable dropWhile(Function1 pred) {
        return ParIterableLike$class.dropWhile(this, pred);
    }

    @Override
    public <U> void copyToArray(Object xs) {
        ParIterableLike$class.copyToArray(this, xs);
    }

    @Override
    public <U> void copyToArray(Object xs, int start) {
        ParIterableLike$class.copyToArray(this, xs, start);
    }

    @Override
    public <U> void copyToArray(Object xs, int start, int len) {
        ParIterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <U> boolean sameElements(GenIterable<U> that) {
        return ParIterableLike$class.sameElements(this, that);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParHashMap<K, V>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParHashMap<K, V>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParHashMap<K, V>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
        return (That)ParIterableLike$class.toParMap(this, cbf, ev);
    }

    @Override
    public Object view() {
        return ParIterableLike$class.view(this);
    }

    @Override
    public <U> Object toArray(ClassTag<U> evidence$1) {
        return ParIterableLike$class.toArray(this, evidence$1);
    }

    @Override
    public List<Tuple2<K, V>> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<Tuple2<K, V>> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<Tuple2<K, V>> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<Tuple2<K, V>> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public Vector<Tuple2<K, V>> toVector() {
        return ParIterableLike$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, Tuple2<K, V>, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<Tuple2<K, V>, S, S> op) {
        return (S)ParIterableLike$class.$colon$bslash(this, z, op);
    }

    @Override
    public String debugInformation() {
        return ParIterableLike$class.debugInformation(this);
    }

    @Override
    public ArrayBuffer<String> debugBuffer() {
        return ParIterableLike$class.debugBuffer(this);
    }

    @Override
    public void debugclear() {
        ParIterableLike$class.debugclear(this);
    }

    @Override
    public ArrayBuffer<String> debuglog(String s2) {
        return ParIterableLike$class.debuglog(this, s2);
    }

    @Override
    public void printDebugBuffer() {
        ParIterableLike$class.printDebugBuffer(this);
    }

    @Override
    public Combiner<Tuple2<K, V>, ParHashMap<K, V>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public <P, Q> Combiner<Tuple2<P, Q>, ParHashMap<P, Q>> genericMapCombiner() {
        return GenericParMapTemplate$class.genericMapCombiner(this);
    }

    @Override
    public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParIterable<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParIterable<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<Tuple2<K, V>, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<Tuple2<K, V>, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public int hashCode() {
        return GenMapLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenMapLike$class.equals(this, that);
    }

    @Override
    public GenericParMapCompanion<ParHashMap> mapCompanion() {
        return ParHashMap$.MODULE$;
    }

    @Override
    public ParHashMap<K, V> empty() {
        return new ParHashMap<K, V>();
    }

    @Override
    public ParHashMapCombiner<K, V> newCombiner() {
        return ParHashMapCombiner$.MODULE$.apply();
    }

    @Override
    public HashMap<K, V> seq() {
        return new HashMap<K, V>(this.hashTableContents());
    }

    public ParHashMapIterator splitter() {
        return new ParHashMapIterator(this, 1, this.table().length, this.size(), (DefaultEntry)this.table()[0]);
    }

    @Override
    public int size() {
        return this.tableSize();
    }

    @Override
    public void clear() {
        this.clearTable();
    }

    @Override
    public Option<V> get(K key) {
        DefaultEntry e = (DefaultEntry)this.findEntry(key);
        return e == null ? None$.MODULE$ : new Some(e.value());
    }

    @Override
    public Option<V> put(K key, V value) {
        Option option;
        DefaultEntry e = (DefaultEntry)this.findOrAddEntry(key, value);
        if (e == null) {
            option = None$.MODULE$;
        } else {
            Object v = e.value();
            e.value_$eq(value);
            option = new Some(v);
        }
        return option;
    }

    public void update(K key, V value) {
        this.put(key, value);
    }

    public Option<V> remove(K key) {
        DefaultEntry e = (DefaultEntry)this.removeEntry(key);
        return e != null ? new Some(e.value()) : None$.MODULE$;
    }

    public ParHashMap<K, V> $plus$eq(Tuple2<K, V> kv) {
        DefaultEntry e = (DefaultEntry)this.findOrAddEntry(kv._1(), kv._2());
        if (e != null) {
            e.value_$eq(kv._2());
        }
        return this;
    }

    public ParHashMap<K, V> $minus$eq(K key) {
        this.removeEntry(key);
        return this;
    }

    @Override
    public String stringPrefix() {
        return "ParHashMap";
    }

    @Override
    public <V1> DefaultEntry<K, V> createNewEntry(K key, V1 value) {
        return new DefaultEntry<K, V1>(key, value);
    }

    private void writeObject(ObjectOutputStream out) {
        this.serializeTo(out, (Function1<DefaultEntry<K, V>, BoxedUnit>)((Object)new Serializable(this, out){
            public static final long serialVersionUID = 0L;
            private final ObjectOutputStream out$1;

            public final void apply(DefaultEntry<K, V> entry) {
                this.out$1.writeObject(entry.key());
                this.out$1.writeObject(entry.value());
            }
            {
                this.out$1 = out$1;
            }
        }));
    }

    private void readObject(ObjectInputStream in) {
        this.init(in, (Function0<DefaultEntry<K, V>>)((Object)new Serializable(this, in){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParHashMap $outer;
            private final ObjectInputStream in$1;

            public final DefaultEntry<K, V> apply() {
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

    @Override
    public Seq<String> brokenInvariants() {
        Predef$ predef$ = Predef$.MODULE$;
        IndexedSeq<List<String>> buckets = RichInt$.MODULE$.until$extension0(0, this.table().length / this.sizeMapBucketSize()).map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParHashMap $outer;

            public final List<String> apply(int i) {
                return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkBucket(i);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
        Predef$ predef$2 = Predef$.MODULE$;
        IndexedSeq<List<String>> elems = RichInt$.MODULE$.until$extension0(0, this.table().length).map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParHashMap $outer;

            public final List<String> apply(int i) {
                return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkEntry(i);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
        return ((TraversableLike)buckets.flatMap(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final List<String> apply(List<String> x) {
                return x;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus(elems.flatMap(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final List<String> apply(List<String> x) {
                return x;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom()), IndexedSeq$.MODULE$.canBuildFrom());
    }

    public List<String> scala$collection$parallel$mutable$ParHashMap$$checkBucket(int i) {
        int expected = this.sizemap()[i];
        int n = i * this.sizeMapBucketSize();
        Predef$ predef$ = Predef$.MODULE$;
        int found = BoxesRunTime.unboxToInt(RichInt$.MODULE$.until$extension0(n, (i + 1) * this.sizeMapBucketSize()).foldLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParHashMap $outer;

            public final int apply(int acc, int c) {
                return this.apply$mcIII$sp(acc, c);
            }

            public int apply$mcIII$sp(int acc, int c) {
                return acc + this.$outer.scala$collection$parallel$mutable$ParHashMap$$count$1(this.$outer.table()[c]);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        return found != expected ? List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{new StringBuilder().append((Object)"Found ").append(BoxesRunTime.boxToInteger(found)).append((Object)" elements, while sizemap showed ").append(BoxesRunTime.boxToInteger(expected)).toString()})) : Nil$.MODULE$;
    }

    public List<String> scala$collection$parallel$mutable$ParHashMap$$checkEntry(int i) {
        return this.check$1(this.table()[i], i);
    }

    public final int scala$collection$parallel$mutable$ParHashMap$$count$1(HashEntry e) {
        return e == null ? 0 : 1 + this.scala$collection$parallel$mutable$ParHashMap$$count$1((HashEntry)e.next());
    }

    private final List check$1(HashEntry e, int i$1) {
        List list2;
        block2: {
            while (true) {
                if (e == null) {
                    list2 = Nil$.MODULE$;
                    break block2;
                }
                if (this.index(this.elemHashCode((K)e.key())) != i$1) break;
                e = (HashEntry)e.next();
            }
            String string2 = new StringBuilder().append((Object)"Element ").append(e.key()).append((Object)" at ").append(BoxesRunTime.boxToInteger(i$1)).append((Object)" with ").append(BoxesRunTime.boxToInteger(this.elemHashCode((K)e.key()))).append((Object)" maps to ").append(BoxesRunTime.boxToInteger(this.index(this.elemHashCode((K)e.key())))).toString();
            list2 = this.check$1((HashEntry)e.next(), i$1).$colon$colon(string2);
        }
        return list2;
    }

    public ParHashMap(HashTable.Contents<K, DefaultEntry<K, V>> contents) {
        Parallelizable$class.$init$(this);
        GenMapLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        GenericParMapTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        scala.collection.parallel.ParMapLike$class.$init$(this);
        scala.collection.parallel.ParMap$class.$init$(this);
        ParIterable$class.$init$(this);
        Growable$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        ParMapLike$class.$init$(this);
        ParMap$class.$init$(this);
        HashTable$HashUtils$class.$init$(this);
        HashTable$class.$init$(this);
        ParHashTable$class.$init$(this);
        this.initWithContents(contents);
    }

    public ParHashMap() {
        this(null);
    }

    public class ParHashMapIterator
    extends ParHashTable.EntryIterator<Tuple2<K, V>, ParHashMapIterator> {
        @Override
        public Tuple2<K, V> entry2item(DefaultEntry<K, V> entry) {
            return new Tuple2(entry.key(), entry.value());
        }

        @Override
        public ParHashMapIterator newIterator(int idxFrom, int idxUntil, int totalSz, DefaultEntry<K, V> es) {
            return new ParHashMapIterator(this.scala$collection$parallel$mutable$ParHashMap$ParHashMapIterator$$$outer(), idxFrom, idxUntil, totalSz, es);
        }

        public /* synthetic */ ParHashMap scala$collection$parallel$mutable$ParHashMap$ParHashMapIterator$$$outer() {
            return (ParHashMap)this.$outer;
        }

        public ParHashMapIterator(ParHashMap<K, V> $outer, int start, int untilIdx, int totalSize, DefaultEntry<K, V> e) {
            super($outer, start, untilIdx, totalSize, e);
        }
    }
}

