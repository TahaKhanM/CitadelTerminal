/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.DebugUtils$;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.FlatHashTable$HashUtils$class;
import scala.collection.mutable.FlatHashTable$class;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSet$class;
import scala.collection.parallel.ParSetLike$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.mutable.ParFlatHashTable;
import scala.collection.parallel.mutable.ParFlatHashTable$class;
import scala.collection.parallel.mutable.ParHashSet$;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.ParIterable$class;
import scala.collection.parallel.mutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005ug\u0001B\u0001\u0003\u0001-\u0011!\u0002U1s\u0011\u0006\u001c\bnU3u\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u001dA\u0011AC2pY2,7\r^5p]*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005192c\u0002\u0001\u000e#\u0001:\u0003g\r\t\u0003\u001d=i\u0011\u0001C\u0005\u0003!!\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0014+5\t!!\u0003\u0002\u0015\u0005\t1\u0001+\u0019:TKR\u0004\"AF\f\r\u0001\u0011)\u0001\u0004\u0001b\u00013\t\tA+\u0005\u0002\u001b;A\u0011abG\u0005\u00039!\u0011qAT8uQ&tw\r\u0005\u0002\u000f=%\u0011q\u0004\u0003\u0002\u0004\u0003:L\b\u0003B\u0011%+\u0019j\u0011A\t\u0006\u0003G\u0019\tqaZ3oKJL7-\u0003\u0002&E\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f!\t\u0011\u0002\u0001E\u0003\u0013QUQ3&\u0003\u0002*\u0005\tQ\u0001+\u0019:TKRd\u0015n[3\u0011\u0007I\u0001Q\u0003E\u0002-]Ui\u0011!\f\u0006\u0003\u0007\u0019I!aL\u0017\u0003\u000f!\u000b7\u000f[*fiB\u0019!#M\u000b\n\u0005I\u0012!\u0001\u0005)be\u001ac\u0017\r\u001e%bg\"$\u0016M\u00197f!\tqA'\u0003\u00026\u0011\ta1+\u001a:jC2L'0\u00192mK\"Aq\u0007\u0001B\u0001B\u0003%\u0001(\u0001\u0005d_:$XM\u001c;t!\rID(\u0006\b\u0003YiJ!aO\u0017\u0002\u001b\u0019c\u0017\r\u001e%bg\"$\u0016M\u00197f\u0013\tidH\u0001\u0005D_:$XM\u001c;t\u0015\tYT\u0006\u0003\u0004A\u0001\u0011\u0005a!Q\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005)\u0012\u0005\"B\u001c@\u0001\u0004A\u0004\"\u0002!\u0001\t\u0003!E#\u0001\u0016\t\u000b\u0019\u0003A\u0011I$\u0002\u0013\r|W\u000e]1oS>tW#\u0001%\u000f\u0005IIu!\u0002&\u0003\u0011\u0003Y\u0015A\u0003)be\"\u000b7\u000f[*fiB\u0011!\u0003\u0014\u0004\u0006\u0003\tA\t!T\n\u0004\u0019:\u001b\u0004cA\u0011PM%\u0011\u0001K\t\u0002\u000e!\u0006\u00148+\u001a;GC\u000e$xN]=\t\u000b\u0001cE\u0011\u0001*\u0015\u0003-CQ\u0001\u0016'\u0005\u0004U\u000bAbY1o\u0005VLG\u000e\u001a$s_6,\"AV1\u0016\u0003]\u0003R!\t-[A\nL!!\u0017\u0012\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B\u00111\fX\u0007\u0002\u0019&\u0011QL\u0018\u0002\u0005\u0007>dG.\u0003\u0002`E\t\u0001r)\u001a8fe&\u001c7i\\7qC:LwN\u001c\t\u0003-\u0005$Q\u0001G*C\u0002e\u00012A\u0005\u0001a\u0011\u0015!G\n\"\u0011f\u0003)qWm\u001e\"vS2$WM]\u000b\u0003M2,\u0012a\u001a\t\u0005Q&\\W.D\u0001\u0005\u0013\tQGA\u0001\u0005D_6\u0014\u0017N\\3s!\t1B\u000eB\u0003\u0019G\n\u0007\u0011\u0004E\u0002\u0013\u0001-DQa\u001c'\u0005BA\f1B\\3x\u0007>l'-\u001b8feV\u0011\u0011\u000f^\u000b\u0002eB!\u0001.[:v!\t1B\u000fB\u0003\u0019]\n\u0007\u0011\u0004E\u0002\u0013\u0001MDqa\u001e'\u0002\u0002\u0013%\u00010A\u0006sK\u0006$'+Z:pYZ,G#A=\u0011\u0005i|X\"A>\u000b\u0005ql\u0018\u0001\u00027b]\u001eT\u0011A`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0002m\u0014aa\u00142kK\u000e$\bbBA\u0003\u0001\u0011\u0005\u0013qA\u0001\u0006K6\u0004H/_\u000b\u0002U!9\u00111\u0002\u0001\u0005B\u00055\u0011\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005=\u0001\u0003BA\t\u0003'i\u0011\u0001\u0001\u0004\u0007\u0003+\u0001\u0001!a\u0006\u0003%A\u000b'\u000fS1tQN+G/\u0013;fe\u0006$xN]\n\u0005\u0003'\tI\u0002\u0005\u0003\u0002\u0012\u0005m\u0011bAA\u000fc\tA\u0002+\u0019:GY\u0006$\b*Y:i)\u0006\u0014G.Z%uKJ\fGo\u001c:\t\u0017\u0005\u0005\u00121\u0003B\u0001B\u0003%\u00111E\u0001\u0006gR\f'\u000f\u001e\t\u0004\u001d\u0005\u0015\u0012bAA\u0014\u0011\t\u0019\u0011J\u001c;\t\u001b\u0005-\u00121\u0003B\u0001B\u0003%\u00111EA\u0017\u00035IG/\u001a:bi\u0016\u001cXK\u001c;jY&!\u0011qFA\u000e\u0003\u0015)h\u000e^5m\u00115\t\u0019$a\u0005\u0003\u0002\u0003\u0006I!a\t\u00026\u0005iAo\u001c;bY\u0016cW-\\3oiNLA!a\u000e\u0002\u001c\u0005IAo\u001c;bYNL'0\u001a\u0005\b\u0001\u0006MA\u0011AA\u001e)!\ty!!\u0010\u0002@\u0005\u0005\u0003\u0002CA\u0011\u0003s\u0001\r!a\t\t\u0011\u0005-\u0012\u0011\ba\u0001\u0003GA\u0001\"a\r\u0002:\u0001\u0007\u00111\u0005\u0005\t\u0003\u000b\n\u0019\u0002\"\u0001\u0002H\u0005Ya.Z<Ji\u0016\u0014\u0018\r^8s)!\ty!!\u0013\u0002L\u00055\u0003\u0002CA\u0011\u0003\u0007\u0002\r!a\t\t\u0011\u0005=\u00121\ta\u0001\u0003GA\u0001\"a\u0014\u0002D\u0001\u0007\u00111E\u0001\u0006i>$\u0018\r\u001c\u0005\b\u0003'\u0002A\u0011IA+\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005\r\u0002bBA-\u0001\u0011\u0005\u00111L\u0001\u0006G2,\u0017M\u001d\u000b\u0003\u0003;\u00022ADA0\u0013\r\t\t\u0007\u0003\u0002\u0005+:LG\u000fC\u0004\u0002f\u0001!\t%a\u001a\u0002\u0007M,\u0017/F\u0001,\u0011\u001d\tY\u0007\u0001C\u0001\u0003[\n\u0001\u0002\n9mkN$S-\u001d\u000b\u0005\u0003#\ty\u0007C\u0004\u0002r\u0005%\u0004\u0019A\u000b\u0002\t\u0015dW-\u001c\u0005\b\u0003k\u0002A\u0011AA<\u0003%!S.\u001b8vg\u0012*\u0017\u000f\u0006\u0003\u0002\u0012\u0005e\u0004bBA9\u0003g\u0002\r!\u0006\u0005\b\u0003{\u0002A\u0011IA@\u00031\u0019HO]5oOB\u0013XMZ5y+\t\t\t\tE\u0002{\u0003\u0007K1!!\"|\u0005\u0019\u0019FO]5oO\"9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-\u0015\u0001C2p]R\f\u0017N\\:\u0015\t\u00055\u00151\u0013\t\u0004\u001d\u0005=\u0015bAAI\u0011\t9!i\\8mK\u0006t\u0007bBA9\u0003\u000f\u0003\r!\u0006\u0005\b\u0003/\u0003A\u0011AA\u0007\u0003!\u0019\b\u000f\\5ui\u0016\u0014\bbBAN\u0001\u0011%\u0011QT\u0001\foJLG/Z(cU\u0016\u001cG\u000f\u0006\u0003\u0002^\u0005}\u0005\u0002CAQ\u00033\u0003\r!a)\u0002\u0003M\u0004B!!*\u0002,6\u0011\u0011q\u0015\u0006\u0004\u0003Sk\u0018AA5p\u0013\u0011\ti+a*\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\b\u0003c\u0003A\u0011BAZ\u0003)\u0011X-\u00193PE*,7\r\u001e\u000b\u0005\u0003;\n)\f\u0003\u0005\u00028\u0006=\u0006\u0019AA]\u0003\tIg\u000e\u0005\u0003\u0002&\u0006m\u0016\u0002BA_\u0003O\u0013\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0011\u001d\t\t\r\u0001C!\u0003\u0007\f\u0001\u0003Z3ck\u001eLeNZ8s[\u0006$\u0018n\u001c8\u0016\u0005\u0005\u0015\u0007\u0003BAd\u0003\u001bt1ADAe\u0013\r\tY\rC\u0001\u0007!J,G-\u001a4\n\t\u0005\u0015\u0015q\u001a\u0006\u0004\u0003\u0017D\u0001f\u0002\u0001\u0002T\u0006e\u00171\u001c\t\u0004\u001d\u0005U\u0017bAAl\u0011\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u0001")
public class ParHashSet<T>
implements scala.collection.parallel.mutable.ParSet<T>,
ParFlatHashTable<T>,
Serializable {
    public static final long serialVersionUID = 1L;
    private transient int _loadFactor;
    private transient Object[] table;
    private transient int tableSize;
    private transient int threshold;
    private transient int[] sizemap;
    private transient int seedvalue;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
        return ParHashSet$.MODULE$.canBuildFrom();
    }

    public static <A> Object setCanBuildFrom() {
        return ParHashSet$.MODULE$.setCanBuildFrom();
    }

    @Override
    public boolean alwaysInitSizeMap() {
        return ParFlatHashTable$class.alwaysInitSizeMap(this);
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
    public Object[] table() {
        return this.table;
    }

    @Override
    @TraitSetter
    public void table_$eq(Object[] x$1) {
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
    public int capacity(int expectedSize) {
        return FlatHashTable$class.capacity(this, expectedSize);
    }

    @Override
    public int initialSize() {
        return FlatHashTable$class.initialSize(this);
    }

    @Override
    public int randomSeed() {
        return FlatHashTable$class.randomSeed(this);
    }

    @Override
    public int tableSizeSeed() {
        return FlatHashTable$class.tableSizeSeed(this);
    }

    @Override
    public void init(ObjectInputStream in, Function1<T, BoxedUnit> f) {
        FlatHashTable$class.init(this, in, f);
    }

    @Override
    public void serializeTo(ObjectOutputStream out) {
        FlatHashTable$class.serializeTo(this, out);
    }

    @Override
    public Option<T> findEntry(T elem) {
        return FlatHashTable$class.findEntry(this, elem);
    }

    @Override
    public boolean containsElem(T elem) {
        return FlatHashTable$class.containsElem(this, elem);
    }

    @Override
    public boolean addElem(T elem) {
        return FlatHashTable$class.addElem(this, elem);
    }

    @Override
    public boolean addEntry(Object newEntry) {
        return FlatHashTable$class.addEntry(this, newEntry);
    }

    @Override
    public boolean removeElem(T elem) {
        return FlatHashTable$class.removeElem(this, elem);
    }

    @Override
    public void nnSizeMapAdd(int h) {
        FlatHashTable$class.nnSizeMapAdd(this, h);
    }

    @Override
    public void nnSizeMapRemove(int h) {
        FlatHashTable$class.nnSizeMapRemove(this, h);
    }

    @Override
    public void nnSizeMapReset(int tableLength) {
        FlatHashTable$class.nnSizeMapReset(this, tableLength);
    }

    @Override
    public final int totalSizeMapBuckets() {
        return FlatHashTable$class.totalSizeMapBuckets(this);
    }

    @Override
    public int calcSizeMapSize(int tableLength) {
        return FlatHashTable$class.calcSizeMapSize(this, tableLength);
    }

    @Override
    public void sizeMapInit(int tableLength) {
        FlatHashTable$class.sizeMapInit(this, tableLength);
    }

    @Override
    public void sizeMapInitAndRebuild() {
        FlatHashTable$class.sizeMapInitAndRebuild(this);
    }

    @Override
    public void printSizeMap() {
        FlatHashTable$class.printSizeMap(this);
    }

    @Override
    public void printContents() {
        FlatHashTable$class.printContents(this);
    }

    @Override
    public void sizeMapDisable() {
        FlatHashTable$class.sizeMapDisable(this);
    }

    @Override
    public boolean isSizeMapDefined() {
        return FlatHashTable$class.isSizeMapDefined(this);
    }

    @Override
    public final int index(int hcode) {
        return FlatHashTable$class.index(this, hcode);
    }

    @Override
    public void clearTable() {
        FlatHashTable$class.clearTable(this);
    }

    @Override
    public FlatHashTable.Contents<T> hashTableContents() {
        return FlatHashTable$class.hashTableContents(this);
    }

    @Override
    public void initWithContents(FlatHashTable.Contents<T> c) {
        FlatHashTable$class.initWithContents(this, c);
    }

    @Override
    public final int sizeMapBucketBitSize() {
        return FlatHashTable$HashUtils$class.sizeMapBucketBitSize(this);
    }

    @Override
    public final int sizeMapBucketSize() {
        return FlatHashTable$HashUtils$class.sizeMapBucketSize(this);
    }

    @Override
    public final int improve(int hcode, int seed) {
        return FlatHashTable$HashUtils$class.improve(this, hcode, seed);
    }

    @Override
    public final Object elemToEntry(T elem) {
        return FlatHashTable$HashUtils$class.elemToEntry(this, elem);
    }

    @Override
    public final T entryToElem(Object entry) {
        return (T)FlatHashTable$HashUtils$class.entryToElem(this, entry);
    }

    @Override
    public scala.collection.parallel.mutable.ParSet $plus(Object elem) {
        return scala.collection.parallel.mutable.ParSetLike$class.$plus(this, elem);
    }

    @Override
    public scala.collection.parallel.mutable.ParSet $minus(Object elem) {
        return scala.collection.parallel.mutable.ParSetLike$class.$minus(this, elem);
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
    public Shrinkable<T> $minus$eq(T elem1, T elem2, Seq<T> elems) {
        return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Shrinkable<T> $minus$minus$eq(TraversableOnce<T> xs) {
        return Shrinkable$class.$minus$minus$eq(this, xs);
    }

    @Override
    public Growable<T> $plus$eq(T elem1, T elem2, Seq<T> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<T> $plus$plus$eq(TraversableOnce<T> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public ParSet union(GenSet that) {
        return ParSetLike$class.union(this, that);
    }

    @Override
    public ParSet diff(GenSet that) {
        return ParSetLike$class.diff(this, that);
    }

    @Override
    public ParIterable<T> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public ParSeq<T> toSeq() {
        return ParIterable$class.toSeq(this);
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
        ParHashSet parHashSet = this;
        synchronized (parHashSet) {
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
        ParHashSet parHashSet = this;
        synchronized (parHashSet) {
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
    public T head() {
        return (T)ParIterableLike$class.head(this);
    }

    @Override
    public Option<T> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public T last() {
        return (T)ParIterableLike$class.last(this);
    }

    @Override
    public Option<T> lastOption() {
        return ParIterableLike$class.lastOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable init() {
        return ParIterableLike$class.init(this);
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
    public <S, That> Object bf2seq(CanBuildFrom<ParHashSet<T>, S, That> bf) {
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
    public <S> S aggregate(Function0<S> z, Function2<S, T, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, T, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<T, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, T, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<T, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<T, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<T, Object> p) {
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
    public <U> T min(Ordering<U> ord) {
        return (T)ParIterableLike$class.min(this, ord);
    }

    @Override
    public <U> T max(Ordering<U> ord) {
        return (T)ParIterableLike$class.max(this, ord);
    }

    @Override
    public <S> T maxBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public <S> T minBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That map(Function1<T, S> f, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<T, S> pf, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<T, GenTraversableOnce<S>> f, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<T, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<T, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<T> find(Function1<T, Object> p) {
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
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParHashSet<T>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParHashSet<T>, ParHashSet<T>> partition(Function1<T, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> ParMap<K, ParHashSet<T>> groupBy(Function1<T, K> f) {
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
    public Tuple2<ParHashSet<T>, ParHashSet<T>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParHashSet<T>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, T, S> op, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<T, S, S> op, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParHashSet<T>, ParHashSet<T>> span(Function1<T, Object> pred) {
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
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParHashSet<T>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParHashSet<T>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParHashSet<T>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
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
    public List<T> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<T> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<T> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<T> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<T> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public Vector<T> toVector() {
        return ParIterableLike$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.$colon$bslash(this, z, op);
    }

    @Override
    public Seq<String> brokenInvariants() {
        return ParIterableLike$class.brokenInvariants(this);
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
    public Combiner<T, ParHashSet<T>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Builder<T, ParHashSet<T>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public Combiner<T, ParHashSet<T>> newCombiner() {
        return GenericParTemplate$class.newCombiner(this);
    }

    @Override
    public <B> Combiner<B, ParHashSet<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParHashSet<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParHashSet<A1>, ParHashSet<A2>> unzip(Function1<T, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParHashSet<A1>, ParHashSet<A2>, ParHashSet<A3>> unzip3(Function1<T, Tuple3<A1, A2, A3>> asTriple) {
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
    public boolean apply(T elem) {
        return GenSetLike$class.apply(this, elem);
    }

    @Override
    public Object intersect(GenSet that) {
        return GenSetLike$class.intersect(this, that);
    }

    @Override
    public Object $amp(GenSet that) {
        return GenSetLike$class.$amp(this, that);
    }

    @Override
    public Object $bar(GenSet that) {
        return GenSetLike$class.$bar(this, that);
    }

    @Override
    public Object $amp$tilde(GenSet that) {
        return GenSetLike$class.$amp$tilde(this, that);
    }

    @Override
    public boolean subsetOf(GenSet<T> that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public boolean equals(Object that) {
        return GenSetLike$class.equals(this, that);
    }

    @Override
    public int hashCode() {
        return GenSetLike$class.hashCode(this);
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, Object> compose(Function1<A, T> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<T, A> andThen(Function1<Object, A> g) {
        return Function1$class.andThen(this, g);
    }

    public ParHashSet$ companion() {
        return ParHashSet$.MODULE$;
    }

    @Override
    public ParHashSet<T> empty() {
        return new ParHashSet<T>();
    }

    public ParHashSetIterator iterator() {
        return this.splitter();
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
    public HashSet<T> seq() {
        return new HashSet<T>(this.hashTableContents());
    }

    @Override
    public ParHashSet<T> $plus$eq(T elem) {
        this.addElem(elem);
        return this;
    }

    @Override
    public ParHashSet<T> $minus$eq(T elem) {
        this.removeElem(elem);
        return this;
    }

    @Override
    public String stringPrefix() {
        return "ParHashSet";
    }

    @Override
    public boolean contains(T elem) {
        return this.containsElem(elem);
    }

    public ParHashSetIterator splitter() {
        return new ParHashSetIterator(this, 0, this.table().length, this.size());
    }

    private void writeObject(ObjectOutputStream s2) {
        this.serializeTo(s2);
    }

    private void readObject(ObjectInputStream in) {
        this.init(in, (Function1<T, BoxedUnit>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final void apply(T x) {
            }
        }));
    }

    @Override
    public String debugInformation() {
        return DebugUtils$.MODULE$.buildString((Function1<Function1<Object, BoxedUnit>, BoxedUnit>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParHashSet $outer;

            public final void apply(Function1<Object, BoxedUnit> append2) {
                append2.apply("Parallel flat hash table set");
                append2.apply(new StringBuilder().append((Object)"No. elems: ").append(BoxesRunTime.boxToInteger(this.$outer.tableSize())).toString());
                append2.apply(new StringBuilder().append((Object)"Table length: ").append(BoxesRunTime.boxToInteger(this.$outer.table().length)).toString());
                append2.apply("Table: ");
                append2.apply(DebugUtils$.MODULE$.arrayString(this.$outer.table(), 0, this.$outer.table().length));
                append2.apply("Sizemap: ");
                append2.apply(DebugUtils$.MODULE$.arrayString(this.$outer.sizemap(), 0, this.$outer.sizemap().length));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public ParHashSet(FlatHashTable.Contents<T> contents) {
        Parallelizable$class.$init$(this);
        Function1$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        ParIterable$class.$init$(this);
        ParSetLike$class.$init$(this);
        ParSet$class.$init$(this);
        Growable$class.$init$(this);
        Shrinkable$class.$init$(this);
        Cloneable$class.$init$(this);
        scala.collection.parallel.mutable.ParSetLike$class.$init$(this);
        scala.collection.parallel.mutable.ParSet$class.$init$(this);
        FlatHashTable$HashUtils$class.$init$(this);
        FlatHashTable$class.$init$(this);
        ParFlatHashTable$class.$init$(this);
        this.initWithContents(contents);
    }

    public ParHashSet() {
        this(null);
    }

    public class ParHashSetIterator
    extends ParFlatHashTable.ParFlatHashTableIterator {
        public ParHashSetIterator newIterator(int start, int until2, int total2) {
            return new ParHashSetIterator(this.scala$collection$parallel$mutable$ParHashSet$ParHashSetIterator$$$outer(), start, until2, total2);
        }

        public /* synthetic */ ParHashSet scala$collection$parallel$mutable$ParHashSet$ParHashSetIterator$$$outer() {
            return (ParHashSet)this.$outer;
        }

        public ParHashSetIterator(ParHashSet<T> $outer, int start, int iteratesUntil, int totalElements) {
            super($outer, start, iteratesUntil, totalElements);
        }
    }
}

