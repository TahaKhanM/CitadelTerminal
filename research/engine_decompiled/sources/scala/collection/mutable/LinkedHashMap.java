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
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.Set;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.HashTable$HashUtils$class;
import scala.collection.mutable.HashTable$class;
import scala.collection.mutable.LinkedEntry;
import scala.collection.mutable.LinkedHashMap$;
import scala.collection.mutable.LinkedHashSet;
import scala.collection.mutable.LinkedHashSet$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\t\ru!B\u0001\u0003\u0011\u0003I\u0011!\u0004'j].,G\rS1tQ6\u000b\u0007O\u0003\u0002\u0004\t\u00059Q.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0006\f\u001b\u0005\u0011a!\u0002\u0007\u0003\u0011\u0003i!!\u0004'j].,G\rS1tQ6\u000b\u0007oE\u0002\f\u001d]\u00022a\u0004\n\u0015\u001b\u0005\u0001\"BA\t\u0005\u0003\u001d9WM\\3sS\u000eL!a\u0005\t\u0003#5+H/\u00192mK6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0002\u000b+\u0019!AB\u0001\u0001\u0017+\r9R\u0004K\n\u0007+aQS&M\u001c\u0011\t)I2dJ\u0005\u00035\t\u00111\"\u00112tiJ\f7\r^'baB\u0011A$\b\u0007\u0001\t\u0015qRC1\u0001 \u0005\u0005\t\u0015C\u0001\u0011%!\t\t#%D\u0001\u0007\u0013\t\u0019cAA\u0004O_RD\u0017N\\4\u0011\u0005\u0005*\u0013B\u0001\u0014\u0007\u0005\r\te.\u001f\t\u00039!\"Q!K\u000bC\u0002}\u0011\u0011A\u0011\t\u0005\u0015-Zr%\u0003\u0002-\u0005\t\u0019Q*\u00199\u0011\u000b)q3d\n\u0019\n\u0005=\u0012!aB'ba2K7.\u001a\t\u0005\u0015UYr\u0005\u0005\u0003\u000bem!\u0014BA\u001a\u0003\u0005%A\u0015m\u001d5UC\ndW\r\u0005\u0003\u000bkm9\u0013B\u0001\u001c\u0003\u0005-a\u0015N\\6fI\u0016sGO]=\u0011\u0005\u0005B\u0014BA\u001d\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011\u0015YT\u0003\"\u0001=\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0007C\u0003?+\u0011\u0005s(A\u0003f[B$\u00180F\u00011\u0011\u0015\tU\u0003\"\u0011C\u0003\u0011\u0019\u0018N_3\u0016\u0003\r\u0003\"!\t#\n\u0005\u00153!aA%oi\u0016!q)\u0006\u00015\u0005\u0015)e\u000e\u001e:z\u0011\u001dIU\u00031A\u0005\u0012)\u000b!BZ5sgR,e\u000e\u001e:z+\u0005Y\u0005C\u0001'G\u001b\u0005)\u0002b\u0002(\u0016\u0001\u0004%\tbT\u0001\u000fM&\u00148\u000f^#oiJLx\fJ3r)\t\u00016\u000b\u0005\u0002\"#&\u0011!K\u0002\u0002\u0005+:LG\u000fC\u0004U\u001b\u0006\u0005\t\u0019A&\u0002\u0007a$\u0013\u0007\u0003\u0004W+\u0001\u0006KaS\u0001\fM&\u00148\u000f^#oiJL\b\u0005\u000b\u0002V1B\u0011\u0011%W\u0005\u00035\u001a\u0011\u0011\u0002\u001e:b]NLWM\u001c;\t\u000fq+\u0002\u0019!C\t\u0015\u0006IA.Y:u\u000b:$(/\u001f\u0005\b=V\u0001\r\u0011\"\u0005`\u00035a\u0017m\u001d;F]R\u0014\u0018p\u0018\u0013fcR\u0011\u0001\u000b\u0019\u0005\b)v\u000b\t\u00111\u0001L\u0011\u0019\u0011W\u0003)Q\u0005\u0017\u0006QA.Y:u\u000b:$(/\u001f\u0011)\u0005\u0005D\u0006\"B3\u0016\t\u00031\u0017aA4fiR\u0011qM\u001b\t\u0004C!<\u0013BA5\u0007\u0005\u0019y\u0005\u000f^5p]\")1\u000e\u001aa\u00017\u0005\u00191.Z=\t\u000b5,B\u0011\t8\u0002\u0007A,H\u000fF\u0002h_BDQa\u001b7A\u0002mAQ!\u001d7A\u0002\u001d\nQA^1mk\u0016DQa]\u000b\u0005BQ\faA]3n_Z,GCA4v\u0011\u0015Y'\u000f1\u0001\u001c\u0011\u00159X\u0003\"\u0001y\u0003!!\u0003\u000f\\;tI\u0015\fHC\u0001'z\u0011\u0015Qh\u000f1\u0001|\u0003\tYg\u000f\u0005\u0003\"yn9\u0013BA?\u0007\u0005\u0019!V\u000f\u001d7fe!2ao`A\u0003\u0003\u0013\u00012!IA\u0001\u0013\r\t\u0019A\u0002\u0002\u0015I\u0016\u0004(/Z2bi\u0016$wJ^3se&$\u0017N\\4\"\u0005\u0005\u001d\u0011\u0001P\u0016>AMDw.\u001e7eA9|G\u000f\t2fA=4XM\u001d:jI\u0012,g\u000eI:pA%$\be\u001d;bsN\u00043m\u001c8tSN$XM\u001c;!o&$\b\u000e\t9vi:\n#!a\u0003\u0002\rIr\u0013'\r\u00181\u0011\u001d\ty!\u0006C\u0001\u0003#\t\u0011\u0002J7j]V\u001cH%Z9\u0015\u00071\u000b\u0019\u0002\u0003\u0004l\u0003\u001b\u0001\ra\u0007\u0015\b\u0003\u001by\u0018qCA\u0005C\t\tI\"A .{\u0001\u001a\bn\\;mI\u0002rw\u000e\u001e\u0011cK\u0002zg/\u001a:sS\u0012$WM\u001c\u0011t_\u0002JG\u000fI:uCf\u001c\beY8og&\u001cH/\u001a8uA]LG\u000f\u001b\u0011sK6|g/\u001a\u0018\t\u000f\u0005uQ\u0003\"\u0001\u0002 \u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\"A)\u00111EA\u0013w6\tA!C\u0002\u0002(\u0011\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0004\u0007\u0003W)\u0002\"!\f\u0003\u0019\u0019KG\u000e^3sK\u0012\\U-_:\u0014\t\u0005%\u0012q\u0006\t\u0004\u0019\u0006E\u0012\u0002BA\u0016\u0003gI!a\f\u0003\t\u0017\u0005]\u0012\u0011\u0006B\u0001B\u0003%\u0011\u0011H\u0001\u0002aB1\u0011%a\u000f\u001c\u0003\u007fI1!!\u0010\u0007\u0005%1UO\\2uS>t\u0017\u0007E\u0002\"\u0003\u0003J1!a\u0011\u0007\u0005\u001d\u0011un\u001c7fC:DqaOA\u0015\t\u0003\t9\u0005\u0006\u0003\u0002J\u0005-\u0003c\u0001'\u0002*!A\u0011qGA#\u0001\u0004\tI\u0004C\u0004?\u0003S!\t%a\u0014\u0016\u0005\u0005E\u0003\u0003\u0002\u0006\u00167\u0001Bq!!\u0016\u0016\t\u0003\n9&\u0001\u0006gS2$XM]&fsN$B!!\u0017\u0002^A1\u00111EA.7\u001dJ!\u0001\f\u0003\t\u0011\u0005]\u00121\u000ba\u0001\u0003s1a!!\u0019\u0016\u0011\u0005\r$\u0001D'baB,GMV1mk\u0016\u001cX\u0003BA3\u0003[\u001aB!a\u0018\u0002hA)A*!\u001b\u0002l%!\u0011\u0011MA\u001a!\ra\u0012Q\u000e\u0003\b\u0003_\nyF1\u0001 \u0005\u0005\u0019\u0005bCA:\u0003?\u0012\t\u0011)A\u0005\u0003k\n\u0011A\u001a\t\u0007C\u0005mr%a\u001b\t\u000fm\ny\u0006\"\u0001\u0002zQ!\u00111PA?!\u0015a\u0015qLA6\u0011!\t\u0019(a\u001eA\u0002\u0005U\u0004b\u0002 \u0002`\u0011\u0005\u0013q\n\u0005\b\u0003\u0007+B\u0011IAC\u0003%i\u0017\r\u001d,bYV,7/\u0006\u0003\u0002\b\u00065E\u0003BAE\u0003\u001f\u0003r!a\t\u0002\\m\tY\tE\u0002\u001d\u0003\u001b#q!a\u001c\u0002\u0002\n\u0007q\u0004\u0003\u0005\u0002t\u0005\u0005\u0005\u0019AAI!\u0019\t\u00131H\u0014\u0002\f\u001a1\u0011QS\u000b\t\u0003/\u0013Q\u0002R3gCVdGoS3z'\u0016$8\u0003BAJ\u00033\u00032\u0001TAN\u0013\u0011\t)*a\r\t\u000fm\n\u0019\n\"\u0001\u0002 R\u0011\u0011\u0011\u0015\t\u0004\u0019\u0006M\u0005b\u0002 \u0002\u0014\u0012\u0005\u0013QU\u000b\u0003\u0003O\u0003BACAU7%\u0019\u00111\u0016\u0002\u0003\u001b1Kgn[3e\u0011\u0006\u001c\bnU3u\u0011\u001d\ty+\u0006C!\u0003c\u000baa[3z'\u0016$XCAAZ!\u0015\t\u0019#!.\u001c\u0013\r\t9\f\u0002\u0002\u0004'\u0016$\bbBA^+\u0011\u0005\u0013QX\u0001\rW\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u007f\u0003R!a\t\u0002&mAq!a1\u0016\t\u0003\n)-\u0001\bwC2,Xm]%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0007#BA\u0012\u0003K9\u0003bBAf+\u0011\u0005\u0013QZ\u0001\bM>\u0014X-Y2i+\u0011\ty-a6\u0015\u0007A\u000b\t\u000e\u0003\u0005\u0002t\u0005%\u0007\u0019AAj!\u0019\t\u00131H>\u0002VB\u0019A$a6\u0005\u000f\u0005e\u0017\u0011\u001ab\u0001?\t\tQ\u000bC\u0004\u0002^V!\t&a8\u0002\u0019\u0019|'/Z1dQ\u0016sGO]=\u0016\t\u0005\u0005\u0018\u0011\u001e\u000b\u0004!\u0006\r\b\u0002CA:\u00037\u0004\r!!:\u0011\r\u0005\nYdSAt!\ra\u0012\u0011\u001e\u0003\b\u00033\fYN1\u0001 \u0011\u001d\ti/\u0006C\t\u0003_\fab\u0019:fCR,g*Z<F]R\u0014\u00180\u0006\u0003\u0002r\u0006eH#B&\u0002t\u0006U\bBB6\u0002l\u0002\u00071\u0004C\u0004r\u0003W\u0004\r!a>\u0011\u0007q\tI\u0010B\u0004\u0002|\u0006-(\u0019A\u0010\u0003\u0005\t\u000b\u0004bBA\u0000+\u0011\u0005#\u0011A\u0001\u0006G2,\u0017M\u001d\u000b\u0002!\"9!QA\u000b\u0005\n\t\u001d\u0011aC<sSR,wJ\u00196fGR$2\u0001\u0015B\u0005\u0011!\u0011YAa\u0001A\u0002\t5\u0011aA8viB!!q\u0002B\r\u001b\t\u0011\tB\u0003\u0003\u0003\u0014\tU\u0011AA5p\u0015\t\u00119\"\u0001\u0003kCZ\f\u0017\u0002\u0002B\u000e\u0005#\u0011!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\"9!qD\u000b\u0005\n\t\u0005\u0012A\u0003:fC\u0012|%M[3diR\u0019\u0001Ka\t\t\u0011\t\u0015\"Q\u0004a\u0001\u0005O\t!!\u001b8\u0011\t\t=!\u0011F\u0005\u0005\u0005W\u0011\tBA\tPE*,7\r^%oaV$8\u000b\u001e:fC6Dc!\u0006B\u0018c\nU\u0002cA\u0011\u00032%\u0019!1\u0007\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#e$A\u0001\t\rmZA\u0011\u0001B\u001d)\u0005I\u0001b\u0002B\u001f\u0017\u0011\r!qH\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0007\u0005\u0003\u0012IF!\u0018\u0016\u0005\t\r\u0003#C\b\u0003F\t%#Q\u000bB0\u0013\r\u00119\u0005\u0005\u0002\r\u0007\u0006t')^5mI\u001a\u0013x.\u001c\t\u0005\u0005\u0017\u0012i%D\u0001\f\u0013\u0011\u0011yE!\u0015\u0003\t\r{G\u000e\\\u0005\u0004\u0005'\u0002\"!D$f]6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0004\"y\n]#1\f\t\u00049\teCA\u0002\u0010\u0003<\t\u0007q\u0004E\u0002\u001d\u0005;\"a!\u000bB\u001e\u0005\u0004y\u0002C\u0002\u0006\u0016\u0005/\u0012Y\u0006\u0003\u0004?\u0017\u0011\u0005!1M\u000b\u0007\u0005K\u0012YGa\u001c\u0016\u0005\t\u001d\u0004C\u0002\u0006\u0016\u0005S\u0012i\u0007E\u0002\u001d\u0005W\"aA\bB1\u0005\u0004y\u0002c\u0001\u000f\u0003p\u00111\u0011F!\u0019C\u0002}A\u0011Ba\u001d\f\u0003\u0003%IA!\u001e\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005o\u0002BA!\u001f\u0003\u00005\u0011!1\u0010\u0006\u0005\u0005{\u0012)\"\u0001\u0003mC:<\u0017\u0002\u0002BA\u0005w\u0012aa\u00142kK\u000e$\b")
public class LinkedHashMap<A, B>
extends AbstractMap<A, B>
implements HashTable<A, LinkedEntry<A, B>>,
Serializable {
    public static final long serialVersionUID = 1L;
    private transient LinkedEntry<A, B> firstEntry;
    private transient LinkedEntry<A, B> lastEntry;
    private transient int _loadFactor;
    private transient HashEntry<Object, HashEntry>[] table;
    private transient int tableSize;
    private transient int threshold;
    private transient int[] sizemap;
    private transient int seedvalue;

    public static <A, B> CanBuildFrom<LinkedHashMap<?, ?>, Tuple2<A, B>, LinkedHashMap<A, B>> canBuildFrom() {
        return LinkedHashMap$.MODULE$.canBuildFrom();
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
    public HashEntry<A, LinkedEntry<A, B>>[] table() {
        return this.table;
    }

    @Override
    @TraitSetter
    public void table_$eq(HashEntry<A, LinkedEntry<A, B>>[] x$1) {
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
    public void init(ObjectInputStream in, Function0<LinkedEntry<A, B>> readEntry) {
        HashTable$class.init(this, in, readEntry);
    }

    @Override
    public void serializeTo(ObjectOutputStream out, Function1<LinkedEntry<A, B>, BoxedUnit> writeEntry) {
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
    public Iterator<LinkedEntry<A, B>> entriesIterator() {
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
    public void initWithContents(HashTable.Contents<A, LinkedEntry<A, B>> c) {
        HashTable$class.initWithContents(this, c);
    }

    @Override
    public HashTable.Contents<A, LinkedEntry<A, B>> hashTableContents() {
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
    public LinkedHashMap<A, B> empty() {
        return LinkedHashMap$.MODULE$.empty();
    }

    @Override
    public int size() {
        return this.tableSize();
    }

    public LinkedEntry<A, B> firstEntry() {
        return this.firstEntry;
    }

    public void firstEntry_$eq(LinkedEntry<A, B> x$1) {
        this.firstEntry = x$1;
    }

    public LinkedEntry<A, B> lastEntry() {
        return this.lastEntry;
    }

    public void lastEntry_$eq(LinkedEntry<A, B> x$1) {
        this.lastEntry = x$1;
    }

    @Override
    public Option<B> get(A key) {
        LinkedEntry e = (LinkedEntry)this.findEntry((Object)key);
        return e == null ? None$.MODULE$ : new Some(e.value());
    }

    @Override
    public Option<B> put(A key, B value) {
        Option option;
        LinkedEntry e = (LinkedEntry)this.findOrAddEntry((Object)key, (Object)value);
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
    public Option<B> remove(A key) {
        Option option;
        LinkedEntry e = (LinkedEntry)this.removeEntry((Object)key);
        if (e == null) {
            option = None$.MODULE$;
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
            option = new Some(e.value());
        }
        return option;
    }

    @Override
    public LinkedHashMap<A, B> $plus$eq(Tuple2<A, B> kv) {
        this.put(kv._1(), kv._2());
        return this;
    }

    public LinkedHashMap<A, B> $minus$eq(A key) {
        this.remove(key);
        return this;
    }

    @Override
    public Iterator<Tuple2<A, B>> iterator() {
        return new AbstractIterator<Tuple2<A, B>>(this){
            private LinkedEntry<A, B> cur;

            private LinkedEntry<A, B> cur() {
                return this.cur;
            }

            private void cur_$eq(LinkedEntry<A, B> x$1) {
                this.cur = x$1;
            }

            public boolean hasNext() {
                return this.cur() != null;
            }

            /*
             * WARNING - void declaration
             */
            public Tuple2<A, B> next() {
                Tuple2 tuple2;
                if (this.hasNext()) {
                    void var1_1;
                    Tuple2<A, B> res = new Tuple2<A, B>(this.cur().key(), this.cur().value());
                    this.cur_$eq(this.cur().later());
                    tuple2 = var1_1;
                } else {
                    tuple2 = (Tuple2)((Object)Iterator$.MODULE$.empty().next());
                }
                return tuple2;
            }
            {
                this.cur = $outer.firstEntry();
            }
        };
    }

    @Override
    public Map<A, B> filterKeys(Function1<A, Object> p) {
        return new FilteredKeys(this, p);
    }

    @Override
    public <C> Map<A, C> mapValues(Function1<B, C> f) {
        return new MappedValues<C>(this, f);
    }

    @Override
    public Set<A> keySet() {
        return new DefaultKeySet(this);
    }

    @Override
    public Iterator<A> keysIterator() {
        return new AbstractIterator<A>(this){
            private LinkedEntry<A, B> cur;

            private LinkedEntry<A, B> cur() {
                return this.cur;
            }

            private void cur_$eq(LinkedEntry<A, B> x$1) {
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
    public Iterator<B> valuesIterator() {
        return new AbstractIterator<B>(this){
            private LinkedEntry<A, B> cur;

            private LinkedEntry<A, B> cur() {
                return this.cur;
            }

            private void cur_$eq(LinkedEntry<A, B> x$1) {
                this.cur = x$1;
            }

            public boolean hasNext() {
                return this.cur() != null;
            }

            /*
             * WARNING - void declaration
             */
            public B next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    B res = this.cur().value();
                    this.cur_$eq(this.cur().later());
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (B)nothing$;
            }
            {
                this.cur = $outer.firstEntry();
            }
        };
    }

    @Override
    public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
        for (LinkedEntry<A, B> cur = this.firstEntry(); cur != null; cur = cur.later()) {
            f.apply(new Tuple2<A, B>(cur.key(), cur.value()));
        }
    }

    @Override
    public <U> void foreachEntry(Function1<LinkedEntry<A, B>, U> f) {
        for (LinkedEntry<A, B> cur = this.firstEntry(); cur != null; cur = cur.later()) {
            f.apply(cur);
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public <B1> LinkedEntry<A, B> createNewEntry(A key, B1 value) {
        void var3_3;
        LinkedEntry<A, B1> e = new LinkedEntry<A, B1>(key, value);
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
        this.serializeTo(out, (Function1<LinkedEntry<A, B>, BoxedUnit>)((Object)new Serializable(this, out){
            public static final long serialVersionUID = 0L;
            private final ObjectOutputStream out$1;

            public final void apply(LinkedEntry<A, B> entry) {
                this.out$1.writeObject(entry.key());
                this.out$1.writeObject(entry.value());
            }
            {
                this.out$1 = out$1;
            }
        }));
    }

    private void readObject(ObjectInputStream in) {
        this.firstEntry_$eq(null);
        this.lastEntry_$eq(null);
        this.init(in, (Function0<LinkedEntry<A, B>>)((Object)new Serializable(this, in){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ LinkedHashMap $outer;
            private final ObjectInputStream in$1;

            public final LinkedEntry<A, B> apply() {
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

    public LinkedHashMap() {
        HashTable$HashUtils$class.$init$(this);
        HashTable$class.$init$(this);
        this.firstEntry = null;
        this.lastEntry = null;
    }

    public class FilteredKeys
    extends MapLike.FilteredKeys {
        @Override
        public LinkedHashMap<A, Nothing$> empty() {
            return LinkedHashMap$.MODULE$.empty();
        }

        public /* synthetic */ LinkedHashMap scala$collection$mutable$LinkedHashMap$FilteredKeys$$$outer() {
            return (LinkedHashMap)this.$outer;
        }

        public FilteredKeys(LinkedHashMap<A, B> $outer, Function1<A, Object> p) {
            super($outer, p);
        }
    }

    public class MappedValues<C>
    extends MapLike.MappedValues<C> {
        @Override
        public LinkedHashMap<A, Nothing$> empty() {
            return LinkedHashMap$.MODULE$.empty();
        }

        public /* synthetic */ LinkedHashMap scala$collection$mutable$LinkedHashMap$MappedValues$$$outer() {
            return (LinkedHashMap)this.$outer;
        }

        public MappedValues(LinkedHashMap<A, B> $outer, Function1<B, C> f) {
            super($outer, f);
        }
    }

    public class DefaultKeySet
    extends MapLike.DefaultKeySet {
        @Override
        public LinkedHashSet<A> empty() {
            return LinkedHashSet$.MODULE$.empty();
        }

        public /* synthetic */ LinkedHashMap scala$collection$mutable$LinkedHashMap$DefaultKeySet$$$outer() {
            return (LinkedHashMap)this.$outer;
        }

        public DefaultKeySet(LinkedHashMap<A, B> $outer) {
        }
    }
}

