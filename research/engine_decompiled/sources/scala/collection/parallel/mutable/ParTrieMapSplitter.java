/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.concurrent.TrieMap;
import scala.collection.concurrent.TrieMapIterator;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.Signalling;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.mutable.ParTrieMap;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001u4Q!\u0001\u0002\u0001\r)\u0011!\u0003U1s)JLW-T1q'Bd\u0017\u000e\u001e;fe*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0005qCJ\fG\u000e\\3m\u0015\t9\u0001\"\u0001\u0006d_2dWm\u0019;j_:T\u0011!C\u0001\u0006g\u000e\fG.Y\u000b\u0004\u0017Q\u00013c\u0001\u0001\rEA!Q\u0002\u0005\n \u001b\u0005q!BA\b\u0007\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0003#9\u0011q\u0002\u0016:jK6\u000b\u0007/\u0013;fe\u0006$xN\u001d\t\u0003'Qa\u0001\u0001B\u0003\u0016\u0001\t\u0007qCA\u0001L\u0007\u0001\t\"\u0001\u0007\u000f\u0011\u0005eQR\"\u0001\u0005\n\u0005mA!a\u0002(pi\"Lgn\u001a\t\u00033uI!A\b\u0005\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0014A\u0011)\u0011\u0005\u0001b\u0001/\t\ta\u000bE\u0002$I\u0019j\u0011\u0001B\u0005\u0003K\u0011\u0011\u0001#\u0013;fe\u0006\u0014G.Z*qY&$H/\u001a:\u0011\te9#cH\u0005\u0003Q!\u0011a\u0001V;qY\u0016\u0014\u0004\u0002\u0003\u0016\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\u0002\u00071,g\u000f\u0005\u0002\u001aY%\u0011Q\u0006\u0003\u0002\u0004\u0013:$\b\u0002C\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\u0002\u0005\r$\b\u0003B\u00072%}I!A\r\b\u0003\u000fQ\u0013\u0018.Z'ba\"AA\u0007\u0001B\u0001B\u0003%Q'\u0001\u0005nkN$\u0018J\\5u!\tIb'\u0003\u00028\u0011\t9!i\\8mK\u0006t\u0007\"B\u001d\u0001\t\u0003Q\u0014A\u0002\u001fj]&$h\b\u0006\u0003<{yz\u0004\u0003\u0002\u001f\u0001%}i\u0011A\u0001\u0005\u0006Ua\u0002\ra\u000b\u0005\u0006_a\u0002\r\u0001\r\u0005\u0006ia\u0002\r!\u000e\u0005\t\u0003\u0002A)\u0019!C\u0001\u0005\u0006IAo\u001c;bYNL'0Z\u000b\u0002W!AA\t\u0001E\u0001B\u0003&1&\u0001\u0006u_R\fGn]5{K\u0002BqA\u0012\u0001A\u0002\u0013\u0005!)\u0001\u0005ji\u0016\u0014\u0018\r^3e\u0011\u001dA\u0005\u00011A\u0005\u0002%\u000bA\"\u001b;fe\u0006$X\rZ0%KF$\"AS'\u0011\u0005eY\u0015B\u0001'\t\u0005\u0011)f.\u001b;\t\u000f9;\u0015\u0011!a\u0001W\u0005\u0019\u0001\u0010J\u0019\t\rA\u0003\u0001\u0015)\u0003,\u0003%IG/\u001a:bi\u0016$\u0007\u0005C\u0003S\u0001\u0011E3+A\u0006oK^LE/\u001a:bi>\u0014H\u0003B\u001eU-bCQ!V)A\u0002-\nAa\u00187fm\")q+\u0015a\u0001a\u0005\u0019ql\u0019;\t\u000be\u000b\u0006\u0019A\u001b\u0002\u0013}kWo\u001d;J]&$\b\"B.\u0001\t\u0003b\u0016AE:i_VdGm\u00159mSR4UO\u001d;iKJ,\"!\u00183\u0015\u0007Urf\rC\u0003`5\u0002\u0007\u0001-\u0001\u0003d_2d\u0007cA\u0012bG&\u0011!\r\u0002\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002\u0014I\u0012)QM\u0017b\u0001/\t\t1\u000bC\u0003h5\u0002\u00071&\u0001\tqCJ\fG\u000e\\3mSNlG*\u001a<fY\")\u0011\u000e\u0001C\u0001U\u0006\u0019A-\u001e9\u0016\u0003mBQ\u0001\u001c\u0001\u0005B5\fAA\\3yiR\ta\u0005C\u0003p\u0001\u0011\u0005\u0001/A\u0003ta2LG/F\u0001r!\r\u0011XO\t\b\u00033ML!\u0001\u001e\u0005\u0002\u000fA\f7m[1hK&\u0011ao\u001e\u0002\u0004'\u0016\f(B\u0001;\t\u0011\u0015I\b\u0001\"\u0011{\u0003AI7OU3nC&t\u0017N\\4DQ\u0016\f\u0007/F\u00016\u0011\u0015a\b\u0001\"\u0001C\u0003%\u0011X-\\1j]&tw\r")
public class ParTrieMapSplitter<K, V>
extends TrieMapIterator<K, V>
implements IterableSplitter<Tuple2<K, V>> {
    private final TrieMap<K, V> ct;
    private int totalsize;
    private int iterated;
    private Signalling signalDelegate;
    private volatile boolean bitmap$0;

    private int totalsize$lzycompute() {
        ParTrieMapSplitter parTrieMapSplitter = this;
        synchronized (parTrieMapSplitter) {
            if (!this.bitmap$0) {
                this.totalsize = ((ParTrieMap)this.ct.par()).size();
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.totalsize;
        }
    }

    @Override
    public Signalling signalDelegate() {
        return this.signalDelegate;
    }

    @Override
    @TraitSetter
    public void signalDelegate_$eq(Signalling x$1) {
        this.signalDelegate = x$1;
    }

    @Override
    public Seq<IterableSplitter<Tuple2<K, V>>> splitWithSignalling() {
        return IterableSplitter$class.splitWithSignalling(this);
    }

    @Override
    public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
        return IterableSplitter$class.buildString(this, closure);
    }

    @Override
    public String debugInformation() {
        return IterableSplitter$class.debugInformation(this);
    }

    @Override
    public IterableSplitter.Taken newTaken(int until2) {
        return IterableSplitter$class.newTaken(this, until2);
    }

    @Override
    public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
        return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
    }

    @Override
    public IterableSplitter<Tuple2<K, V>> take(int n) {
        return IterableSplitter$class.take(this, n);
    }

    @Override
    public IterableSplitter<Tuple2<K, V>> slice(int from1, int until1) {
        return IterableSplitter$class.slice(this, from1, until1);
    }

    @Override
    public <S> IterableSplitter.Mapped<S> map(Function1<Tuple2<K, V>, S> f) {
        return IterableSplitter$class.map(this, f);
    }

    @Override
    public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
        return IterableSplitter$class.appendParIterable(this, that);
    }

    @Override
    public <S> IterableSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
        return IterableSplitter$class.zipParSeq(this, that);
    }

    @Override
    public <S, U, R> IterableSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
        return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
    }

    @Override
    public boolean isAborted() {
        return DelegatedSignalling$class.isAborted(this);
    }

    @Override
    public void abort() {
        DelegatedSignalling$class.abort(this);
    }

    @Override
    public int indexFlag() {
        return DelegatedSignalling$class.indexFlag(this);
    }

    @Override
    public void setIndexFlag(int f) {
        DelegatedSignalling$class.setIndexFlag(this, f);
    }

    @Override
    public void setIndexFlagIfGreater(int f) {
        DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
    }

    @Override
    public void setIndexFlagIfLesser(int f) {
        DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
    }

    @Override
    public int tag() {
        return DelegatedSignalling$class.tag(this);
    }

    @Override
    public int count(Function1<Tuple2<K, V>, Object> p) {
        return AugmentedIterableIterator$class.count(this, p);
    }

    @Override
    public <U> U reduce(Function2<U, U, U> op) {
        return (U)AugmentedIterableIterator$class.reduce(this, op);
    }

    @Override
    public <U> U fold(U z, Function2<U, U, U> op) {
        return (U)AugmentedIterableIterator$class.fold(this, z, op);
    }

    @Override
    public <U> U sum(Numeric<U> num) {
        return (U)AugmentedIterableIterator$class.sum(this, num);
    }

    @Override
    public <U> U product(Numeric<U> num) {
        return (U)AugmentedIterableIterator$class.product(this, num);
    }

    @Override
    public Object min(Ordering ord) {
        return AugmentedIterableIterator$class.min(this, ord);
    }

    @Override
    public Object max(Ordering ord) {
        return AugmentedIterableIterator$class.max(this, ord);
    }

    @Override
    public <U> void copyToArray(Object array, int from2, int len) {
        AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
    }

    @Override
    public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
        return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
    }

    @Override
    public <S, That> Combiner<S, That> map2combiner(Function1<Tuple2<K, V>, S> f, Combiner<S, That> cb) {
        return AugmentedIterableIterator$class.map2combiner(this, f, cb);
    }

    @Override
    public <S, That> Combiner<S, That> collect2combiner(PartialFunction<Tuple2<K, V>, S> pf, Combiner<S, That> cb) {
        return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
    }

    @Override
    public <S, That> Combiner<S, That> flatmap2combiner(Function1<Tuple2<K, V>, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
        return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
    }

    @Override
    public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
        return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
    }

    @Override
    public <U, This> Combiner<U, This> filter2combiner(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
    }

    @Override
    public <U, This> Combiner<U, This> filterNot2combiner(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
    }

    @Override
    public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<Tuple2<K, V>, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
        return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
    }

    @Override
    public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.take2combiner(this, n, cb);
    }

    @Override
    public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
    }

    @Override
    public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
    }

    @Override
    public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
        return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
    }

    @Override
    public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<Tuple2<K, V>, Object> p, Combiner<U, This> cb) {
        return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
    }

    @Override
    public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<Tuple2<K, V>, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
        return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
    }

    @Override
    public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
        AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
    }

    @Override
    public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
        return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
    }

    @Override
    public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
        return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
    }

    @Override
    public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
        return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
    }

    @Override
    public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
        return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
    }

    public int totalsize() {
        return this.bitmap$0 ? this.totalsize : this.totalsize$lzycompute();
    }

    public int iterated() {
        return this.iterated;
    }

    public void iterated_$eq(int x$1) {
        this.iterated = x$1;
    }

    @Override
    public ParTrieMapSplitter<K, V> newIterator(int _lev, TrieMap<K, V> _ct, boolean _mustInit) {
        return new ParTrieMapSplitter<K, V>(_lev, _ct, _mustInit);
    }

    @Override
    public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
        int maxsplits = 3 + Integer.highestOneBit(parallelismLevel);
        return this.level() < maxsplits;
    }

    /*
     * WARNING - void declaration
     */
    public ParTrieMapSplitter<K, V> dup() {
        void var1_1;
        TrieMapIterator it = this.newIterator(0, (TrieMap)this.ct, false);
        this.dupTo(it);
        ((ParTrieMapSplitter)it).iterated_$eq(this.iterated());
        return var1_1;
    }

    @Override
    public Tuple2<K, V> next() {
        this.iterated_$eq(this.iterated() + 1);
        return super.next();
    }

    @Override
    public Seq<IterableSplitter<Tuple2<K, V>>> split() {
        return this.subdivide();
    }

    @Override
    public boolean isRemainingCheap() {
        return false;
    }

    @Override
    public int remaining() {
        return this.totalsize() - this.iterated();
    }

    public ParTrieMapSplitter(int lev, TrieMap<K, V> ct, boolean mustInit) {
        this.ct = ct;
        super(lev, ct, mustInit);
        RemainsIterator$class.$init$(this);
        AugmentedIterableIterator$class.$init$(this);
        DelegatedSignalling$class.$init$(this);
        IterableSplitter$class.$init$(this);
        this.iterated = 0;
    }
}

