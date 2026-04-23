/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.FlatHashTable$;
import scala.collection.mutable.FlatHashTable$HashUtils$class;
import scala.collection.mutable.FlatHashTable$class;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.mutable.UnrolledBuffer$;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.mutable.ParHashMapCombiner$;
import scala.collection.parallel.mutable.ParHashSet;
import scala.collection.parallel.mutable.ParHashSetCombiner$;
import scala.collection.parallel.mutable.ParHashSetCombiner$$anon$2$;
import scala.collection.parallel.package$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\tuaAB\u0001\u0003\u0003\u0003\u0011!B\u0001\nQCJD\u0015m\u001d5TKR\u001cu.\u001c2j]\u0016\u0014(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0011A\f'/\u00197mK2T!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b+\tY!cE\u0002\u0001\u0019\u0015\u0002b!\u0004\b\u0011;\u0005\"S\"\u0001\u0003\n\u0005=!!A\u0004\"vG.,GoQ8nE&tWM\u001d\t\u0003#Ia\u0001\u0001B\u0003\u0014\u0001\t\u0007QCA\u0001U\u0007\u0001\t\"A\u0006\u000e\u0011\u0005]AR\"\u0001\u0005\n\u0005eA!a\u0002(pi\"Lgn\u001a\t\u0003/mI!\u0001\b\u0005\u0003\u0007\u0005s\u0017\u0010E\u0002\u001f?Ai\u0011AA\u0005\u0003A\t\u0011!\u0002U1s\u0011\u0006\u001c\bnU3u!\t9\"%\u0003\u0002$\u0011\t1\u0011I\\=SK\u001a\u00042A\b\u0001\u0011!\r13\u0007\u0005\b\u0003OAr!\u0001K\u0018\u000f\u0005%rcB\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0015\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002\b\u0011%\u00111AB\u0005\u0003cI\nQB\u00127bi\"\u000b7\u000f\u001b+bE2,'BA\u0002\u0007\u0013\t!TGA\u0005ICNDW\u000b^5mg*\u0011\u0011G\r\u0005\to\u0001\u0011)\u0019!C\u0005q\u0005yA/\u00192mK2{\u0017\r\u001a$bGR|'/F\u0001:!\t9\"(\u0003\u0002<\u0011\t\u0019\u0011J\u001c;\t\u0011u\u0002!\u0011!Q\u0001\ne\n\u0001\u0003^1cY\u0016du.\u00193GC\u000e$xN\u001d\u0011\t\u000b}\u0002A\u0011\u0001!\u0002\rqJg.\u001b;?)\t!\u0013\tC\u00038}\u0001\u0007\u0011\bC\u0004D\u0001\t\u0007I\u0011\u0002\u001d\u0002\u00159|g.\\1tW2,g\u000e\u0003\u0004F\u0001\u0001\u0006I!O\u0001\f]>tW.Y:lY\u0016t\u0007\u0005C\u0004H\u0001\t\u0007I\u0011\u0002\u001d\u0002\u0013M,W\r\u001a<bYV,\u0007BB%\u0001A\u0003%\u0011(\u0001\u0006tK\u0016$g/\u00197vK\u0002BQa\u0013\u0001\u0005\u00021\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0003\u001b:k\u0011\u0001\u0001\u0005\u0006\u001f*\u0003\r\u0001E\u0001\u0005K2,W\u000eC\u0003R\u0001\u0011\u0005!+\u0001\u0004sKN,H\u000e\u001e\u000b\u0002;!)A\u000b\u0001C\u0005+\u0006Y\u0001/\u0019:Q_B,H.\u0019;f+\u00051\u0006cA,Z!9\u0011\u0001\fM\u0007\u0002e%\u0011!,\u000e\u0002\t\u0007>tG/\u001a8ug\")A\f\u0001C\u0005+\u0006Y1/Z9Q_B,H.\u0019;f\r\u0011q\u0006\u0001A0\u0003'\u0005#G-\u001b8h\r2\fG\u000fS1tQR\u000b'\r\\3\u0014\u0007u\u000b\u0003\rE\u0002YCBI!A\u0019\u001a\u0003\u001b\u0019c\u0017\r\u001e%bg\"$\u0016M\u00197f\u0011!!WL!A!\u0002\u0013I\u0014\u0001\u00038v[\u0016dW-\\:\t\u0011\u0019l&\u0011!Q\u0001\ne\n!\u0001\u001c4\t\u0011!l&\u0011!Q\u0001\ne\n1\"\u001b8tK\u0016$g/\u00197vK\")q(\u0018C\u0001UR!1\u000e\\7o!\tiU\fC\u0003eS\u0002\u0007\u0011\bC\u0003gS\u0002\u0007\u0011\bC\u0003iS\u0002\u0007\u0011\bC\u0003q;\u0012\u0005\u0013/\u0001\u0005u_N#(/\u001b8h)\u0005\u0011\bCA:w\u001d\t9B/\u0003\u0002v\u0011\u00051\u0001K]3eK\u001aL!a\u001e=\u0003\rM#(/\u001b8h\u0015\t)\b\u0002C\u0003{;\u0012\u0005\u0001(A\u0006uC\ndW\rT3oORD\u0007\"\u0002?^\t\u0003i\u0018aB:fiNK'0\u001a\u000b\u0004}\u0006\r\u0001CA\f\u0000\u0013\r\t\t\u0001\u0003\u0002\u0005+:LG\u000f\u0003\u0004\u0002\u0006m\u0004\r!O\u0001\u0003gjDq!!\u0003^\t\u0003\tY!A\u0006j]N,'\u000f^#oiJLHcB\u001d\u0002\u000e\u0005E\u0011Q\u0003\u0005\b\u0003\u001f\t9\u00011\u0001:\u0003!Ign]3si\u0006#\bbBA\n\u0003\u000f\u0001\r!O\u0001\fG>lWm\u001d\"fM>\u0014X\rC\u0004\u0002\u0018\u0005\u001d\u0001\u0019A\u0011\u0002\u00119,w/\u00128uef4a!a\u0007\u0001\u0001\u0005u!A\u0003$jY2\u0014En\\2lgN)\u0011\u0011D\u0011\u0002 A9Q\"!\t\u0002&\u0005E\u0012bAA\u0012\t\t!A+Y:l!\u00199\u0012qE\u001d\u0002,%\u0019\u0011\u0011\u0006\u0005\u0003\rQ+\b\u000f\\33!\u0011A\u0016QF\u0011\n\u0007\u0005=\"G\u0001\bV]J|G\u000e\\3e\u0005V4g-\u001a:\u0011\u00075\u000bI\u0002C\u0006\u00026\u0005e!\u0011!Q\u0001\n\u0005]\u0012a\u00022vG.,Go\u001d\t\u0006/\u0005e\u00121F\u0005\u0004\u0003wA!!B!se\u0006L\bBCA \u00033\u0011\t\u0011)A\u0005W\u0006)A/\u00192mK\"Q\u00111IA\r\u0005\u000b\u0007I\u0011\u0001\u001d\u0002\r=4gm]3u\u0011)\t9%!\u0007\u0003\u0002\u0003\u0006I!O\u0001\b_\u001a47/\u001a;!\u0011)\tY%!\u0007\u0003\u0006\u0004%\t\u0001O\u0001\bQ><X.\u00198z\u0011)\ty%!\u0007\u0003\u0002\u0003\u0006I!O\u0001\tQ><X.\u00198zA!9q(!\u0007\u0005\u0002\u0005MCCCA\u0019\u0003+\n9&!\u0017\u0002\\!A\u0011QGA)\u0001\u0004\t9\u0004C\u0004\u0002@\u0005E\u0003\u0019A6\t\u000f\u0005\r\u0013\u0011\u000ba\u0001s!9\u00111JA)\u0001\u0004I\u0004\"C)\u0002\u001a\u0001\u0007I\u0011AA0+\t\t)\u0003\u0003\u0006\u0002d\u0005e\u0001\u0019!C\u0001\u0003K\n!B]3tk2$x\fJ3r)\rq\u0018q\r\u0005\u000b\u0003S\n\t'!AA\u0002\u0005\u0015\u0012a\u0001=%c!I\u0011QNA\rA\u0003&\u0011QE\u0001\be\u0016\u001cX\u000f\u001c;!\u0011!\t\t(!\u0007\u0005\u0002\u0005M\u0014\u0001\u00027fC\u001a$2A`A;\u0011!\t9(a\u001cA\u0002\u0005e\u0014\u0001\u00029sKZ\u0004RaFA>\u0003KI1!! \t\u0005\u0019y\u0005\u000f^5p]\"I\u0011\u0011QA\r\u0005\u0004%I\u0001O\u0001\nE2|7m[:ju\u0016D\u0001\"!\"\u0002\u001a\u0001\u0006I!O\u0001\u000bE2|7m[:ju\u0016\u0004\u0003\u0002CAE\u00033!I!a#\u0002\u0015\tdwnY6Ti\u0006\u0014H\u000fF\u0002:\u0003\u001bCq!a$\u0002\b\u0002\u0007\u0011(A\u0003cY>\u001c7\u000e\u0003\u0005\u0002\u0014\u0006eA\u0011BAK\u00039qW\r\u001f;CY>\u001c7n\u0015;beR$2!OAL\u0011\u001d\ty)!%A\u0002eB\u0001\"a'\u0002\u001a\u0011%\u0011QT\u0001\nM&dGN\u00117pG.$\u0002\"!\n\u0002 \u0006\u0005\u0016Q\u0015\u0005\b\u0003\u001f\u000bI\n1\u0001:\u0011!\t\u0019+!'A\u0002\u0005-\u0012!B3mK6\u001c\b\u0002CAT\u00033\u0003\r!a\u000b\u0002\u00131,g\r^8wKJ\u001c\b\u0002CAV\u00033!I!!,\u0002\u0013%t7/\u001a:u\u00032dG\u0003CA\u0013\u0003_\u000b\u0019,a.\t\u000f\u0005E\u0016\u0011\u0016a\u0001s\u0005)\u0011\r\u001e)pg\"9\u0011QWAU\u0001\u0004I\u0014!\u00032fM>\u0014X\rU8t\u0011!\t\u0019+!+A\u0002\u0005-\u0002\u0002CA^\u00033!\t!!0\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005}\u0006CBAa\u0003\u000f\f\t$\u0004\u0002\u0002D*\u0019\u0011Q\u0019\u0004\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BAe\u0003\u0007\u0014A\u0001T5ti\"A\u0011QZA\r\t\u0003\ny-A\u0003nKJ<W\rF\u0002\u007f\u0003#D\u0001\"a5\u0002L\u0002\u0007\u0011\u0011G\u0001\u0005i\"\fG\u000f\u0003\u0005\u0002X\u0006eA\u0011AAm\u0003I\u0019\bn\\;mIN\u0003H.\u001b;GkJ$\b.\u001a:\u0016\u0005\u0005m\u0007cA\f\u0002^&\u0019\u0011q\u001c\u0005\u0003\u000f\t{w\u000e\\3b]\u001eA\u00111\u001d\u0002\t\u0002\u0011\t)/\u0001\nQCJD\u0015m\u001d5TKR\u001cu.\u001c2j]\u0016\u0014\bc\u0001\u0010\u0002h\u001a9\u0011A\u0001E\u0001\t\u0005%8cAAtC!9q(a:\u0005\u0002\u00055HCAAs\u0011)\t\t0a:C\u0002\u0013\u0005!\u0001O\u0001\u0011I&\u001c8M]5nS:\fg\u000e\u001e2jiND\u0001\"!>\u0002h\u0002\u0006I!O\u0001\u0012I&\u001c8M]5nS:\fg\u000e\u001e2jiN\u0004\u0003BCA}\u0003O\u0014\r\u0011\"\u0001\u0003q\u0005Ia.^7cY>\u001c7n\u001d\u0005\t\u0003{\f9\u000f)A\u0005s\u0005Qa.^7cY>\u001c7n\u001d\u0011\t\u0015\t\u0005\u0011q\u001db\u0001\n\u0003\u0011\u0001(\u0001\teSN\u001c'/[7j]\u0006tG/\\1tW\"A!QAAtA\u0003%\u0011(A\teSN\u001c'/[7j]\u0006tG/\\1tW\u0002B!B!\u0003\u0002h\n\u0007I\u0011\u0001\u00029\u00035qwN\\7bg.dWM\\4uQ\"A!QBAtA\u0003%\u0011(\u0001\bo_:l\u0017m]6mK:<G\u000f\u001b\u0011\t\u0011\tE\u0011q\u001dC\u0001\u0005'\tQ!\u00199qYf,BA!\u0006\u0003\u001cU\u0011!q\u0003\t\u0005=\u0001\u0011I\u0002E\u0002\u0012\u00057!aa\u0005B\b\u0005\u0004)\u0002")
public abstract class ParHashSetCombiner<T>
extends BucketCombiner<T, ParHashSet<T>, Object, ParHashSetCombiner<T>>
implements FlatHashTable.HashUtils<T> {
    private final int tableLoadFactor;
    private final int nonmasklen;
    private final int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;

    public static <T> ParHashSetCombiner<T> apply() {
        return ParHashSetCombiner$.MODULE$.apply();
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

    private int tableLoadFactor() {
        return this.tableLoadFactor;
    }

    private int nonmasklen() {
        return this.nonmasklen;
    }

    public int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue() {
        return this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;
    }

    @Override
    public ParHashSetCombiner<T> $plus$eq(T elem) {
        Object entry = this.elemToEntry(elem);
        this.sz_$eq(this.sz() + 1);
        int hc = this.improve(entry.hashCode(), this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
        int pos = hc >>> this.nonmasklen();
        if (this.buckets()[pos] == null) {
            this.buckets()[pos] = new UnrolledBuffer<Object>(ClassTag$.MODULE$.AnyRef());
        }
        this.buckets()[pos].$plus$eq(entry);
        return this;
    }

    @Override
    public ParHashSet<T> result() {
        FlatHashTable.Contents<T> contents = this.size() >= ParHashSetCombiner$.MODULE$.numblocks() * this.sizeMapBucketSize() ? this.parPopulate() : this.seqPopulate();
        return new ParHashSet<T>(contents);
    }

    private FlatHashTable.Contents<T> parPopulate() {
        AddingFlatHashTable table = new AddingFlatHashTable(this.size(), this.tableLoadFactor(), this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
        Tuple2<Object, UnrolledBuffer<Object>> tuple2 = this.combinerTaskSupport().executeAndWaitResult(new FillBlocks(this, this.buckets(), table, 0, this.buckets().length));
        if (tuple2 != null) {
            Tuple2<Integer, UnrolledBuffer<Object>> tuple22 = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
            int inserted = tuple22._1$mcI$sp();
            UnrolledBuffer<Object> leftovers = tuple22._2();
            IntRef leftinserts = IntRef.create(0);
            leftovers.foreach(new Serializable(this, table, leftinserts){
                public static final long serialVersionUID = 0L;
                private final AddingFlatHashTable table$1;
                private final IntRef leftinserts$1;

                public final void apply(Object entry) {
                    this.leftinserts$1.elem += this.table$1.insertEntry(0, this.table$1.tableLength(), entry);
                }
                {
                    void var3_3;
                    this.table$1 = table$1;
                    this.leftinserts$1 = var3_3;
                }
            });
            table.setSize(leftinserts.elem + inserted);
            return table.hashTableContents();
        }
        throw new MatchError(tuple2);
    }

    private FlatHashTable.Contents<T> seqPopulate() {
        FlatHashTable tbl = new FlatHashTable<T>(this){
            private transient int _loadFactor;
            private transient Object[] table;
            private transient int tableSize;
            private transient int threshold;
            private transient int[] sizemap;
            private transient int seedvalue;

            public int _loadFactor() {
                return this._loadFactor;
            }

            @TraitSetter
            public void _loadFactor_$eq(int x$1) {
                this._loadFactor = x$1;
            }

            public Object[] table() {
                return this.table;
            }

            @TraitSetter
            public void table_$eq(Object[] x$1) {
                this.table = x$1;
            }

            public int tableSize() {
                return this.tableSize;
            }

            @TraitSetter
            public void tableSize_$eq(int x$1) {
                this.tableSize = x$1;
            }

            public int threshold() {
                return this.threshold;
            }

            @TraitSetter
            public void threshold_$eq(int x$1) {
                this.threshold = x$1;
            }

            public int[] sizemap() {
                return this.sizemap;
            }

            @TraitSetter
            public void sizemap_$eq(int[] x$1) {
                this.sizemap = x$1;
            }

            public int seedvalue() {
                return this.seedvalue;
            }

            @TraitSetter
            public void seedvalue_$eq(int x$1) {
                this.seedvalue = x$1;
            }

            public int capacity(int expectedSize) {
                return FlatHashTable$class.capacity(this, expectedSize);
            }

            public int initialSize() {
                return FlatHashTable$class.initialSize(this);
            }

            public int randomSeed() {
                return FlatHashTable$class.randomSeed(this);
            }

            public int tableSizeSeed() {
                return FlatHashTable$class.tableSizeSeed(this);
            }

            public void init(ObjectInputStream in, Function1<T, BoxedUnit> f) {
                FlatHashTable$class.init(this, in, f);
            }

            public void serializeTo(ObjectOutputStream out) {
                FlatHashTable$class.serializeTo(this, out);
            }

            public Option<T> findEntry(T elem) {
                return FlatHashTable$class.findEntry(this, elem);
            }

            public boolean containsElem(T elem) {
                return FlatHashTable$class.containsElem(this, elem);
            }

            public boolean addElem(T elem) {
                return FlatHashTable$class.addElem(this, elem);
            }

            public boolean addEntry(Object newEntry) {
                return FlatHashTable$class.addEntry(this, newEntry);
            }

            public boolean removeElem(T elem) {
                return FlatHashTable$class.removeElem(this, elem);
            }

            public Iterator<T> iterator() {
                return FlatHashTable$class.iterator(this);
            }

            public void nnSizeMapAdd(int h) {
                FlatHashTable$class.nnSizeMapAdd(this, h);
            }

            public void nnSizeMapRemove(int h) {
                FlatHashTable$class.nnSizeMapRemove(this, h);
            }

            public void nnSizeMapReset(int tableLength) {
                FlatHashTable$class.nnSizeMapReset(this, tableLength);
            }

            public final int totalSizeMapBuckets() {
                return FlatHashTable$class.totalSizeMapBuckets(this);
            }

            public int calcSizeMapSize(int tableLength) {
                return FlatHashTable$class.calcSizeMapSize(this, tableLength);
            }

            public void sizeMapInit(int tableLength) {
                FlatHashTable$class.sizeMapInit(this, tableLength);
            }

            public void sizeMapInitAndRebuild() {
                FlatHashTable$class.sizeMapInitAndRebuild(this);
            }

            public void printSizeMap() {
                FlatHashTable$class.printSizeMap(this);
            }

            public void printContents() {
                FlatHashTable$class.printContents(this);
            }

            public void sizeMapDisable() {
                FlatHashTable$class.sizeMapDisable(this);
            }

            public boolean isSizeMapDefined() {
                return FlatHashTable$class.isSizeMapDefined(this);
            }

            public boolean alwaysInitSizeMap() {
                return FlatHashTable$class.alwaysInitSizeMap(this);
            }

            public final int index(int hcode) {
                return FlatHashTable$class.index(this, hcode);
            }

            public void clearTable() {
                FlatHashTable$class.clearTable(this);
            }

            public FlatHashTable.Contents<T> hashTableContents() {
                return FlatHashTable$class.hashTableContents(this);
            }

            public void initWithContents(FlatHashTable.Contents<T> c) {
                FlatHashTable$class.initWithContents(this, c);
            }

            public final int sizeMapBucketBitSize() {
                return FlatHashTable$HashUtils$class.sizeMapBucketBitSize(this);
            }

            public final int sizeMapBucketSize() {
                return FlatHashTable$HashUtils$class.sizeMapBucketSize(this);
            }

            public final int improve(int hcode, int seed) {
                return FlatHashTable$HashUtils$class.improve(this, hcode, seed);
            }

            public final Object elemToEntry(T elem) {
                return FlatHashTable$HashUtils$class.elemToEntry(this, elem);
            }

            public final T entryToElem(Object entry) {
                return (T)FlatHashTable$HashUtils$class.entryToElem(this, entry);
            }
            {
                FlatHashTable$HashUtils$class.$init$(this);
                FlatHashTable$class.$init$(this);
                this.sizeMapInit(this.table().length);
                this.seedvalue_$eq($outer.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
                Predef$.MODULE$.refArrayOps((Object[])$outer.buckets()).withFilter(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final boolean apply(UnrolledBuffer<Object> buffer) {
                        return buffer != null;
                    }
                }).foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ $anon$2 $outer;

                    public final void apply(UnrolledBuffer<Object> buffer) {
                        buffer.foreach(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ $anon$2$$anonfun$2 $outer;

                            public final boolean apply(Object entry) {
                                return this.$outer.$outer.addEntry(entry);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        });
                    }

                    public /* synthetic */ $anon$2 scala$collection$parallel$mutable$ParHashSetCombiner$$anon$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
        };
        return tbl.hashTableContents();
    }

    public ParHashSetCombiner(int tableLoadFactor) {
        this.tableLoadFactor = tableLoadFactor;
        super(ParHashSetCombiner$.MODULE$.numblocks());
        FlatHashTable$HashUtils$class.$init$(this);
        this.nonmasklen = ParHashSetCombiner$.MODULE$.nonmasklength();
        this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue = 27;
    }

    public class FillBlocks
    implements Task<Tuple2<Object, UnrolledBuffer<Object>>, FillBlocks> {
        private final UnrolledBuffer<Object>[] buckets;
        private final AddingFlatHashTable table;
        private final int offset;
        private final int howmany;
        private Tuple2<Object, UnrolledBuffer<Object>> result;
        private final int blocksize;
        public final /* synthetic */ ParHashSetCombiner $outer;
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
        public void tryLeaf(Option<Tuple2<Object, UnrolledBuffer<Object>>> lastres) {
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

        public int offset() {
            return this.offset;
        }

        public int howmany() {
            return this.howmany;
        }

        @Override
        public Tuple2<Object, UnrolledBuffer<Object>> result() {
            return this.result;
        }

        @Override
        public void result_$eq(Tuple2<Object, UnrolledBuffer<Object>> x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<Tuple2<Object, UnrolledBuffer<Object>>> prev) {
            int totalinserts = 0;
            UnrolledBuffer<Object> leftover = new UnrolledBuffer<Object>(ClassTag$.MODULE$.AnyRef());
            for (int i = this.offset(); i < this.offset() + this.howmany(); ++i) {
                Tuple2<Object, UnrolledBuffer<Object>> tuple2 = this.fillBlock(i, this.buckets[i], leftover);
                if (tuple2 != null) {
                    Tuple2<Integer, UnrolledBuffer<Object>> tuple22 = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
                    int inserted = tuple22._1$mcI$sp();
                    UnrolledBuffer<Object> intonextblock = tuple22._2();
                    totalinserts += inserted;
                    leftover = intonextblock;
                    continue;
                }
                throw new MatchError(tuple2);
            }
            this.result_$eq(new Tuple2<Object, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(totalinserts), leftover));
        }

        private int blocksize() {
            return this.blocksize;
        }

        private int blockStart(int block) {
            return block * this.blocksize();
        }

        private int nextBlockStart(int block) {
            return (block + 1) * this.blocksize();
        }

        private Tuple2<Object, UnrolledBuffer<Object>> fillBlock(int block, UnrolledBuffer<Object> elems, UnrolledBuffer<Object> leftovers) {
            Tuple2<Object, UnrolledBuffer<Object>> tuple2;
            int beforePos = this.nextBlockStart(block);
            Tuple2<Object, UnrolledBuffer<Object>> tuple22 = tuple2 = elems == null ? new Tuple2<Object, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(0), (UnrolledBuffer<Object>)UnrolledBuffer$.MODULE$.apply(Nil$.MODULE$, ClassTag$.MODULE$.AnyRef())) : this.insertAll(-1, beforePos, elems);
            if (tuple2 != null) {
                Tuple2<Integer, UnrolledBuffer<Object>> tuple23 = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
                int elemsIn = tuple23._1$mcI$sp();
                UnrolledBuffer<Object> elemsLeft = tuple23._2();
                Tuple2<Object, UnrolledBuffer<Object>> tuple24 = this.insertAll(this.blockStart(block), beforePos, leftovers);
                if (tuple24 != null) {
                    Tuple2<Integer, UnrolledBuffer<Object>> tuple25 = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(tuple24._1$mcI$sp()), tuple24._2());
                    int leftoversIn = tuple25._1$mcI$sp();
                    UnrolledBuffer<Object> leftoversLeft = tuple25._2();
                    return new Tuple2<Object, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(elemsIn + leftoversIn), elemsLeft.concat(leftoversLeft));
                }
                throw new MatchError(tuple24);
            }
            throw new MatchError(tuple2);
        }

        private Tuple2<Object, UnrolledBuffer<Object>> insertAll(int atPos, int beforePos, UnrolledBuffer<Object> elems) {
            UnrolledBuffer<Object> leftovers = new UnrolledBuffer<Object>(ClassTag$.MODULE$.AnyRef());
            int inserted = 0;
            int i = 0;
            AddingFlatHashTable t = this.table;
            for (UnrolledBuffer.Unrolled<Object> unrolled = elems.headPtr(); unrolled != null; unrolled = unrolled.next()) {
                Object[] chunkarr = (Object[])unrolled.array();
                int chunksz = unrolled.size();
                while (i < chunksz) {
                    Object object;
                    Object entry = chunkarr[i];
                    int res = t.insertEntry(atPos, beforePos, entry);
                    if (res >= 0) {
                        inserted += res;
                        object = BoxedUnit.UNIT;
                    } else {
                        object = leftovers.$plus$eq(entry);
                    }
                    ++i;
                }
                i = 0;
            }
            return new Tuple2<Object, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(inserted), leftovers);
        }

        public List<FillBlocks> split() {
            int fp = this.howmany() / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new FillBlocks[]{new FillBlocks(this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset(), fp), new FillBlocks(this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset() + fp, this.howmany() - fp)}));
        }

        @Override
        public void merge(FillBlocks that) {
            int beforePos;
            int atPos = this.blockStart(that.offset());
            Tuple2<Object, UnrolledBuffer<Object>> tuple2 = this.insertAll(atPos, beforePos = this.blockStart(that.offset() + that.howmany()), (UnrolledBuffer)((Tuple2)this.result())._2());
            if (tuple2 != null) {
                Tuple2<Integer, UnrolledBuffer<Object>> tuple22 = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
                int inserted = tuple22._1$mcI$sp();
                UnrolledBuffer<Object> remainingLeftovers = tuple22._2();
                this.result_$eq(new Tuple2<Object, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(((Tuple2)this.result())._1$mcI$sp() + ((Tuple2)that.result())._1$mcI$sp() + inserted), remainingLeftovers.concat((UnrolledBuffer)((Tuple2)that.result())._2())));
                return;
            }
            throw new MatchError(tuple2);
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany() > package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel());
        }

        public /* synthetic */ ParHashSetCombiner scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer() {
            return this.$outer;
        }

        public FillBlocks(ParHashSetCombiner<T> $outer, UnrolledBuffer<Object>[] buckets, AddingFlatHashTable table, int offset, int howmany) {
            this.buckets = buckets;
            this.table = table;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = new Tuple2<Integer, UnrolledBuffer<Object>>(BoxesRunTime.boxToInteger(Integer.MIN_VALUE), new UnrolledBuffer<Object>(ClassTag$.MODULE$.AnyRef()));
            this.blocksize = table.tableLength() >> ParHashSetCombiner$.MODULE$.discriminantbits();
        }
    }

    public class AddingFlatHashTable
    implements FlatHashTable<T> {
        private transient int _loadFactor;
        private transient Object[] table;
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
        public Iterator<T> iterator() {
            return FlatHashTable$class.iterator(this);
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
        public boolean alwaysInitSizeMap() {
            return FlatHashTable$class.alwaysInitSizeMap(this);
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
            return FlatHashTable$HashUtils$class.entryToElem(this, entry);
        }

        public String toString() {
            Predef$ predef$ = Predef$.MODULE$;
            return new StringOps("AFHT(%s)").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.table().length)}));
        }

        public int tableLength() {
            return this.table().length;
        }

        public void setSize(int sz) {
            this.tableSize_$eq(sz);
        }

        /*
         * WARNING - void declaration
         */
        public int insertEntry(int insertAt, int comesBefore, Object newEntry) {
            int h = insertAt;
            if (insertAt == -1) {
                h = this.index(newEntry.hashCode());
            }
            Object curEntry = this.table()[h];
            while (true) {
                void var4_4;
                Object object;
                if (curEntry == null) {
                    this.table()[h] = newEntry;
                    this.nnSizeMapAdd(h);
                    return 1;
                }
                if (object == newEntry ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, newEntry) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, newEntry) : object.equals(newEntry))))) {
                    return 0;
                }
                if (++var4_4 >= comesBefore) {
                    return -1;
                }
                object = this.table()[var4_4];
            }
        }

        public /* synthetic */ ParHashSetCombiner scala$collection$parallel$mutable$ParHashSetCombiner$AddingFlatHashTable$$$outer() {
            return ParHashSetCombiner.this;
        }

        public AddingFlatHashTable(int numelems, int lf, int inseedvalue) {
            if (ParHashSetCombiner.this == null) {
                throw null;
            }
            FlatHashTable$HashUtils$class.$init$(this);
            FlatHashTable$class.$init$(this);
            this._loadFactor_$eq(lf);
            this.table_$eq(new Object[this.capacity(FlatHashTable$.MODULE$.sizeForThreshold(numelems, this._loadFactor()))]);
            this.tableSize_$eq(0);
            this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold(this._loadFactor(), this.table().length));
            this.seedvalue_$eq(inseedvalue);
            this.sizeMapInit(this.table().length);
        }
    }
}

