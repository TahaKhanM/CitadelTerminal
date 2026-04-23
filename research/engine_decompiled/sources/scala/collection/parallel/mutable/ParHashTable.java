/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.BufferSplitter;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.mutable.SizeMapUtils;
import scala.collection.parallel.mutable.SizeMapUtils$class;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015eaB\u0001\u0003!\u0003\r\ta\u0003\u0002\r!\u0006\u0014\b*Y:i)\u0006\u0014G.\u001a\u0006\u0003\u0007\u0011\tq!\\;uC\ndWM\u0003\u0002\u0006\r\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\b\u0011\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003%\tQa]2bY\u0006\u001c\u0001!F\u0002\r1\t\u001a2\u0001A\u0007\u0012!\tqq\"D\u0001\t\u0013\t\u0001\u0002B\u0001\u0004B]f\u0014VM\u001a\t\u0005%Q1\u0012%D\u0001\u0014\u0015\t\u0019a!\u0003\u0002\u0016'\tI\u0001*Y:i)\u0006\u0014G.\u001a\t\u0003/aa\u0001\u0001B\u0003\u001a\u0001\t\u0007!DA\u0001L#\tYb\u0004\u0005\u0002\u000f9%\u0011Q\u0004\u0003\u0002\b\u001d>$\b.\u001b8h!\tqq$\u0003\u0002!\u0011\t\u0019\u0011I\\=\u0011\u0005]\u0011C!B\u0012\u0001\u0005\u0004!#!B#oiJL\u0018CA\u0013)!\tqa%\u0003\u0002(\u0011\t!a*\u001e7m!\u0011\u0011\u0012FF\u0011\n\u0005)\u001a\"!\u0003%bg\",e\u000e\u001e:z\u0011\u0015a\u0003\u0001\"\u0001.\u0003\u0019!\u0013N\\5uIQ\ta\u0006\u0005\u0002\u000f_%\u0011\u0001\u0007\u0003\u0002\u0005+:LG\u000fC\u00033\u0001\u0011\u00053'A\tbY^\f\u0017p]%oSR\u001c\u0016N_3NCB,\u0012\u0001\u000e\t\u0003\u001dUJ!A\u000e\u0005\u0003\u000f\t{w\u000e\\3b]\u001a)\u0001\bAA\u0001s\tiQI\u001c;ss&#XM]1u_J,2A\u000f!k'\u00119Tb\u000f\"\u0011\u0007qjt(D\u0001\u0005\u0013\tqDA\u0001\tJi\u0016\u0014\u0018M\u00197f'Bd\u0017\u000e\u001e;feB\u0011q\u0003\u0011\u0003\u0006\u0003^\u0012\rA\u0007\u0002\u0002)B\u00111\tR\u0007\u0002\u0005%\u0011QI\u0001\u0002\r'&TX-T1q+RLGn\u001d\u0005\t\u000f^\u0012\t\u0019!C\u0005\u0011\u0006\u0019\u0011\u000e\u001a=\u0016\u0003%\u0003\"A\u0004&\n\u0005-C!aA%oi\"AQj\u000eBA\u0002\u0013%a*A\u0004jIb|F%Z9\u0015\u00059z\u0005b\u0002)M\u0003\u0003\u0005\r!S\u0001\u0004q\u0012\n\u0004\u0002\u0003*8\u0005\u0003\u0005\u000b\u0015B%\u0002\t%$\u0007\u0010\t\u0005\t)^\u0012)\u0019!C\u0005\u0011\u0006)QO\u001c;jY\"Aak\u000eB\u0001B\u0003%\u0011*\u0001\u0004v]RLG\u000e\t\u0005\t1^\u0012)\u0019!C\u0005\u0011\u0006IAo\u001c;bYNL'0\u001a\u0005\t5^\u0012\t\u0011)A\u0005\u0013\u0006QAo\u001c;bYNL'0\u001a\u0011\t\u0011q;$\u00111A\u0005\nu\u000b!!Z:\u0016\u0003\u0005B\u0001bX\u001c\u0003\u0002\u0004%I\u0001Y\u0001\u0007KN|F%Z9\u0015\u00059\n\u0007b\u0002)_\u0003\u0003\u0005\r!\t\u0005\tG^\u0012\t\u0011)Q\u0005C\u0005\u0019Qm\u001d\u0011\t\u000b\u0015<D\u0011\u00014\u0002\rqJg.\u001b;?)\u00159WN\\8q!\u0011AwgP5\u000e\u0003\u0001\u0001\"a\u00066\u0005\r-<DQ1\u0001m\u0005!IE/\u001a:SKB\u0014\u0018CA\u000e<\u0011\u00159E\r1\u0001J\u0011\u0015!F\r1\u0001J\u0011\u0015AF\r1\u0001J\u0011\u0015aF\r1\u0001\"\u0011\u001d\u0011xG1A\u0005\nM\f\u0011\"\u001b;feR\f'\r\\3\u0016\u0003Q\u00042AD;)\u0013\t1\bBA\u0003BeJ\f\u0017\u0010\u0003\u0004yo\u0001\u0006I\u0001^\u0001\u000bSR,'\u000f^1cY\u0016\u0004\u0003b\u0002>8\u0001\u0004%I\u0001S\u0001\niJ\fg/\u001a:tK\u0012Dq\u0001`\u001cA\u0002\u0013%Q0A\u0007ue\u00064XM]:fI~#S-\u001d\u000b\u0003]yDq\u0001U>\u0002\u0002\u0003\u0007\u0011\nC\u0004\u0002\u0002]\u0002\u000b\u0015B%\u0002\u0015Q\u0014\u0018M^3sg\u0016$\u0007\u0005C\u0004\u0002\u0006]2\t!a\u0002\u0002\u0015\u0015tGO]=3SR,W\u000eF\u0002@\u0003\u0013Aq!a\u0003\u0002\u0004\u0001\u0007\u0011%A\u0001f\u0011\u001d\tya\u000eD\u0001\u0003#\t1B\\3x\u0013R,'/\u0019;peRI\u0011.a\u0005\u0002\u0018\u0005m\u0011q\u0004\u0005\b\u0003+\ti\u00011\u0001J\u0003\u001dIG\r\u001f$s_6Dq!!\u0007\u0002\u000e\u0001\u0007\u0011*\u0001\u0005jIb,f\u000e^5m\u0011\u001d\ti\"!\u0004A\u0002%\u000b\u0011\u0002^8uC2\u001c\u0016N_3\t\rq\u000bi\u00011\u0001\"\u0011\u0019\t\u0019c\u000eC\u0001g\u00059\u0001.Y:OKb$\bbBA\u0014o\u0011\u0005\u0011\u0011F\u0001\u0005]\u0016DH\u000fF\u0001@\u0011\u0019\tic\u000eC\u0001[\u0005!1oY1o\u0011\u0019\t\td\u000eC\u0001\u0011\u0006I!/Z7bS:Lgn\u001a\u0005\t\u0003k9D\u0011\t\u0003\u00028\u0005\u0001B-\u001a2vO&sgm\u001c:nCRLwN\\\u000b\u0003\u0003s\u0001B!a\u000f\u0002B9\u0019a\"!\u0010\n\u0007\u0005}\u0002\"\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0007\n)E\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u007fA\u0001bBA%o\u0011\u0005\u00111J\u0001\u0004IV\u0004X#A5\t\u000f\u0005=s\u0007\"\u0001\u0002R\u0005)1\u000f\u001d7jiV\u0011\u00111\u000b\t\u0006\u0003+\n9fO\u0007\u0002\r%\u0019\u0011\u0011\f\u0004\u0003\u0007M+\u0017\u000fC\u0004\u0002^]\"I!a\u0018\u0002)\r|gN^3siR{\u0017I\u001d:bs\n+hMZ3s)\u0011\t\t'a\u001a\u0011\tI\t\u0019gP\u0005\u0004\u0003K\u001a\"aC!se\u0006L()\u001e4gKJDq!!\u001b\u0002\\\u0001\u0007\u0011%A\u0005dQ\u0006Lg\u000e[3bI\"9\u0011QN\u001c\u0005\u0012\u0005=\u0014AC2pk:$X\t\\3ngR)\u0011*!\u001d\u0002v!9\u00111OA6\u0001\u0004I\u0015\u0001\u00024s_6Da\u0001VA6\u0001\u0004I\u0005bBA=o\u0011E\u00111P\u0001\u0011G>,h\u000e\u001e\"vG.,GoU5{KN$R!SA?\u0003\u0003Cq!a \u0002x\u0001\u0007\u0011*\u0001\u0006ge>l')^2lKRDq!a!\u0002x\u0001\u0007\u0011*A\u0006v]RLGNQ;dW\u0016$\b")
public interface ParHashTable<K, Entry extends HashEntry<K, Entry>>
extends HashTable<K, Entry> {
    @Override
    public boolean alwaysInitSizeMap();

    public abstract class EntryIterator<T, IterRepr extends IterableSplitter<T>>
    implements IterableSplitter<T>,
    SizeMapUtils {
        private int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx;
        private final int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until;
        private final int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize;
        private Entry scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es;
        private final HashEntry<K, Entry>[] scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable;
        private int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed;
        public final /* synthetic */ ParHashTable $outer;
        private Signalling signalDelegate;

        @Override
        public int calcNumElems(int from2, int until2, int tableLength, int sizeMapBucketSize) {
            return SizeMapUtils$class.calcNumElems(this, from2, until2, tableLength, sizeMapBucketSize);
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
        public Seq<IterableSplitter<T>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
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
        public IterableSplitter<T> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<T> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> IterableSplitter.Mapped<S> map(Function1<T, S> f) {
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
        public int count(Function1<T, Object> p) {
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
        public <U> T min(Ordering<U> ord) {
            return (T)AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
            return (T)AugmentedIterableIterator$class.max(this, ord);
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
        public <S, That> Combiner<S, That> map2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
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
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
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

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<T> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<T> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<T> filter(Function1<T, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<T> withFilter(Function1<T, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<T> filterNot(Function1<T, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<T> takeWhile(Function1<T, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<T> dropWhile(Function1<T, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<T, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<T, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<T, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<T> find(Function1<T, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<T, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<T> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<T> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<T> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<T> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return (T)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return (T)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<T> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<T> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx;
        }

        private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx_$eq(int x$1) {
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx = x$1;
        }

        public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until;
        }

        public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize;
        }

        public Entry scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es;
        }

        private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq(Entry x$1) {
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es = x$1;
        }

        public HashEntry<K, Entry>[] scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable;
        }

        public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed;
        }

        private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed_$eq(int x$1) {
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed = x$1;
        }

        public abstract T entry2item(Entry var1);

        public abstract IterRepr newIterator(int var1, int var2, int var3, Entry var4);

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() != null;
        }

        @Override
        public T next() {
            Object res = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es();
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq((HashEntry)this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es().next());
            this.scan();
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed_$eq(this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed() + 1);
            return this.entry2item(res);
        }

        public void scan() {
            while (this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() == null && this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() < this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until()) {
                this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq(this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()]);
                this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx_$eq(this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + 1);
            }
        }

        @Override
        public int remaining() {
            return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() - this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed();
        }

        @Override
        public String debugInformation() {
            return this.buildString((Function1<Function1<String, BoxedUnit>, BoxedUnit>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ EntryIterator $outer;

                public final void apply(Function1<String, BoxedUnit> append2) {
                    append2.apply("/--------------------\\");
                    append2.apply("Parallel hash table entry iterator");
                    append2.apply(new StringBuilder().append((Object)"total hash table elements: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().tableSize())).toString());
                    append2.apply(new StringBuilder().append((Object)"pos: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx())).toString());
                    append2.apply(new StringBuilder().append((Object)"until: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).toString());
                    append2.apply(new StringBuilder().append((Object)"traversed: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed())).toString());
                    append2.apply(new StringBuilder().append((Object)"totalsize: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize())).toString());
                    append2.apply(new StringBuilder().append((Object)"current entry: ").append(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es()).toString());
                    append2.apply(new StringBuilder().append((Object)"underlying from ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx())).append((Object)" until ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).toString());
                    append2.apply(Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()).slice(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx(), this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final String apply(HashEntry<K, Entry> x) {
                            return x == null ? "n/a" : x.toString();
                        }
                    }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString(" | "));
                    append2.apply("\\--------------------/");
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public IterRepr dup() {
            return this.newIterator(this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx(), this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until(), this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize(), this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es());
        }

        @Override
        public Seq<IterableSplitter<T>> split() {
            Seq seq;
            if (this.remaining() > 1) {
                if (this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() > this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()) {
                    int divsz = (this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() - this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()) / 2;
                    int sidx = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + divsz + 1;
                    int suntil = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until();
                    HashEntry ses = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[sidx - 1];
                    int stotal = this.calcNumElems(sidx - 1, suntil, this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().table().length, this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizeMapBucketSize());
                    int fidx = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx();
                    int funtil = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + divsz;
                    Object fes = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es();
                    int ftotal = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() - stotal;
                    seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[]{this.newIterator(fidx, funtil, ftotal, fes), this.newIterator(sidx, suntil, stotal, ses)}));
                } else {
                    ArrayBuffer<T> arr = this.convertToArrayBuffer(this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es());
                    BufferSplitter<T> arrpit = new BufferSplitter<T>(arr, 0, arr.length(), this.signalDelegate());
                    seq = arrpit.split();
                }
            } else {
                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[]{this}));
            }
            return seq;
        }

        private ArrayBuffer<T> convertToArrayBuffer(Entry chainhead) {
            ArrayBuffer buff = (ArrayBuffer)ArrayBuffer$.MODULE$.apply(Nil$.MODULE$);
            for (Object curr = chainhead; curr != null; curr = (HashEntry)curr.next()) {
                buff.$plus$eq(curr);
            }
            return buff.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ EntryIterator $outer;

                public final T apply(Entry e) {
                    return this.$outer.entry2item(e);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, ArrayBuffer$.MODULE$.canBuildFrom());
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int countElems(int from2, int until2) {
            void var3_3;
            int c = 0;
            for (int idx = from2; idx < until2; ++idx) {
                for (HashEntry es = this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[idx]; es != null; es = (HashEntry)es.next()) {
                    ++c;
                }
            }
            return (int)var3_3;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int countBucketSizes(int fromBucket, int untilBucket) {
            void var3_3;
            int c = 0;
            for (int idx = fromBucket; idx < untilBucket; ++idx) {
                c += this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizemap()[idx];
            }
            return (int)var3_3;
        }

        public /* synthetic */ ParHashTable scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer() {
            return this.$outer;
        }

        public EntryIterator(ParHashTable<K, Entry> $outer, int idx, int until2, int totalsize, Entry es) {
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx = idx;
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until = until2;
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize = totalsize;
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es = es;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            SizeMapUtils$class.$init$(this);
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable = $outer.table();
            this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed = 0;
            this.scan();
        }
    }
}

