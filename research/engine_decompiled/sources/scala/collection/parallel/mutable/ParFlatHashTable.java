/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.DebugUtils$;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$;
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
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
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
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005baB\u0001\u0003!\u0003\r\ta\u0003\u0002\u0011!\u0006\u0014h\t\\1u\u0011\u0006\u001c\b\u000eV1cY\u0016T!a\u0001\u0003\u0002\u000f5,H/\u00192mK*\u0011QAB\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011q\u0001C\u0001\u000bG>dG.Z2uS>t'\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011A\u0002G\n\u0004\u00015\t\u0002C\u0001\b\u0010\u001b\u0005A\u0011B\u0001\t\t\u0005\u0019\te.\u001f*fMB\u0019!\u0003\u0006\f\u000e\u0003MQ!a\u0001\u0004\n\u0005U\u0019\"!\u0004$mCRD\u0015m\u001d5UC\ndW\r\u0005\u0002\u001811\u0001A!B\r\u0001\u0005\u0004Q\"!\u0001+\u0012\u0005mq\u0002C\u0001\b\u001d\u0013\ti\u0002BA\u0004O_RD\u0017N\\4\u0011\u00059y\u0012B\u0001\u0011\t\u0005\r\te.\u001f\u0005\u0006E\u0001!\taI\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0011\u0002\"AD\u0013\n\u0005\u0019B!\u0001B+oSRDQ\u0001\u000b\u0001\u0005B%\n\u0011#\u00197xCf\u001c\u0018J\\5u'&TX-T1q+\u0005Q\u0003C\u0001\b,\u0013\ta\u0003BA\u0004C_>dW-\u00198\u0007\u000b9\u0002\u0011\u0011A\u0018\u00031A\u000b'O\u00127bi\"\u000b7\u000f\u001b+bE2,\u0017\n^3sCR|'o\u0005\u0003.\u001bA\"\u0004cA\u00193-5\tA!\u0003\u00024\t\t\u0001\u0012\n^3sC\ndWm\u00159mSR$XM\u001d\t\u0003kYj\u0011AA\u0005\u0003o\t\u0011AbU5{K6\u000b\u0007/\u0016;jYND\u0001\"O\u0017\u0003\u0002\u0004%\tAO\u0001\u0004S\u0012DX#A\u001e\u0011\u00059a\u0014BA\u001f\t\u0005\rIe\u000e\u001e\u0005\t\u007f5\u0012\t\u0019!C\u0001\u0001\u00069\u0011\u000e\u001a=`I\u0015\fHC\u0001\u0013B\u0011\u001d\u0011e(!AA\u0002m\n1\u0001\u001f\u00132\u0011!!UF!A!B\u0013Y\u0014\u0001B5eq\u0002B\u0001BR\u0017\u0003\u0006\u0004%\tAO\u0001\u0006k:$\u0018\u000e\u001c\u0005\t\u00116\u0012\t\u0011)A\u0005w\u00051QO\u001c;jY\u0002B\u0001BS\u0017\u0003\u0006\u0004%\tAO\u0001\ni>$\u0018\r\\:ju\u0016D\u0001\u0002T\u0017\u0003\u0002\u0003\u0006IaO\u0001\u000bi>$\u0018\r\\:ju\u0016\u0004\u0003\"\u0002(.\t\u0003y\u0015A\u0002\u001fj]&$h\b\u0006\u0003Q%N#\u0006CA).\u001b\u0005\u0001\u0001\"B\u001dN\u0001\u0004Y\u0004\"\u0002$N\u0001\u0004Y\u0004\"\u0002&N\u0001\u0004Y\u0004B\u0002,.A\u0003&1(A\u0005ue\u00064XM]:fI\"1\u0001,\fQ\u0001\ne\u000b\u0011\"\u001b;feR\f'\r\\3\u0011\u00079QV\"\u0003\u0002\\\u0011\t)\u0011I\u001d:bs\"1Q,\fQ\u0005\n\r\nAa]2b]\")q,\fD\u0001A\u0006Ya.Z<Ji\u0016\u0014\u0018\r^8s)\u0011\u0001\u0014m\u00193\t\u000b\tt\u0006\u0019A\u001e\u0002\u000b%tG-\u001a=\t\u000b\u0019s\u0006\u0019A\u001e\t\u000b)s\u0006\u0019A\u001e\t\u000b\u0019lC\u0011\u0001\u001e\u0002\u0013I,W.Y5oS:<\u0007\"\u00025.\t\u0003I\u0013a\u00025bg:+\u0007\u0010\u001e\u0005\u0006U6\"\ta[\u0001\u0005]\u0016DH\u000fF\u0001\u0017\u0011\u0015iW\u0006\"\u0001o\u0003\r!W\u000f]\u000b\u0002a!)\u0001/\fC\u0001c\u0006)1\u000f\u001d7jiV\t!\u000fE\u0002tmBr!A\u0004;\n\u0005UD\u0011a\u00029bG.\fw-Z\u0005\u0003ob\u00141aU3r\u0015\t)\b\u0002C\u0003{[\u0011\u000530\u0001\teK\n,x-\u00138g_Jl\u0017\r^5p]V\tA\u0010E\u0002~\u0003\u0003q!A\u0004@\n\u0005}D\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0004\u0005\u0015!AB*ue&twM\u0003\u0002\u0000\u0011!9\u0011\u0011B\u0017\u0005\u0012\u0005-\u0011AC2pk:$X\t\\3ngR)1(!\u0004\u0002\u0012!9\u0011qBA\u0004\u0001\u0004Y\u0014\u0001\u00024s_6DaARA\u0004\u0001\u0004Y\u0004bBA\u000b[\u0011E\u0011qC\u0001\u0011G>,h\u000e\u001e\"vG.,GoU5{KN$RaOA\r\u0003;Aq!a\u0007\u0002\u0014\u0001\u00071(\u0001\u0006ge>l'-^2lKRDq!a\b\u0002\u0014\u0001\u00071(A\u0006v]RLGNY;dW\u0016$\b")
public interface ParFlatHashTable<T>
extends FlatHashTable<T> {
    @Override
    public boolean alwaysInitSizeMap();

    public abstract class ParFlatHashTableIterator
    implements IterableSplitter<T>,
    SizeMapUtils {
        private int idx;
        private final int until;
        private final int totalsize;
        public int scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed;
        public final Object[] scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable;
        public final /* synthetic */ ParFlatHashTable $outer;
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
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
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
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
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

        public int idx() {
            return this.idx;
        }

        public void idx_$eq(int x$1) {
            this.idx = x$1;
        }

        public int until() {
            return this.until;
        }

        public int totalsize() {
            return this.totalsize;
        }

        private void scan() {
            while (this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable[this.idx()] == null) {
                this.idx_$eq(this.idx() + 1);
            }
        }

        public abstract IterableSplitter<T> newIterator(int var1, int var2, int var3);

        @Override
        public int remaining() {
            return this.totalsize() - this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed;
        }

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed < this.totalsize();
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public T next() {
            Nothing$ nothing$;
            if (this.hasNext()) {
                void var1_1;
                Object r = this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().entryToElem(this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable[this.idx()]);
                ++this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed;
                this.idx_$eq(this.idx() + 1);
                if (this.hasNext()) {
                    this.scan();
                }
                nothing$ = var1_1;
            } else {
                nothing$ = Iterator$.MODULE$.empty().next();
            }
            return nothing$;
        }

        @Override
        public IterableSplitter<T> dup() {
            return this.newIterator(this.idx(), this.until(), this.totalsize());
        }

        @Override
        public Seq<IterableSplitter<T>> split() {
            Seq seq;
            if (this.remaining() > 1) {
                int divpt = (this.until() + this.idx()) / 2;
                int fstidx = this.idx();
                int fsttotal = this.calcNumElems(this.idx(), divpt, this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable.length, this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizeMapBucketSize());
                IterableSplitter fstit = this.newIterator(fstidx, divpt, fsttotal);
                int snduntil = this.until();
                int sndtotal = this.remaining() - fsttotal;
                IterableSplitter sndit = this.newIterator(divpt, snduntil, sndtotal);
                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[]{fstit, sndit}));
            } else {
                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParFlatHashTableIterator[]{this}));
            }
            return seq;
        }

        @Override
        public String debugInformation() {
            return this.buildString((Function1<Function1<String, BoxedUnit>, BoxedUnit>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParFlatHashTableIterator $outer;

                public final void apply(Function1<String, BoxedUnit> append2) {
                    append2.apply("Parallel flat hash table iterator");
                    append2.apply("---------------------------------");
                    append2.apply(new StringBuilder().append((Object)"Traversed/total: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed)).append((Object)" / ").append(BoxesRunTime.boxToInteger(this.$outer.totalsize())).toString());
                    append2.apply(new StringBuilder().append((Object)"Table idx/until: ").append(BoxesRunTime.boxToInteger(this.$outer.idx())).append((Object)" / ").append(BoxesRunTime.boxToInteger(this.$outer.until())).toString());
                    append2.apply(new StringBuilder().append((Object)"Table length: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable.length)).toString());
                    append2.apply("Table: ");
                    append2.apply(DebugUtils$.MODULE$.arrayString(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable, 0, this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable.length));
                    append2.apply("Sizemap: ");
                    append2.apply(DebugUtils$.MODULE$.arrayString(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap(), 0, this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap().length));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int countElems(int from2, int until2) {
            void var3_3;
            int count2 = 0;
            for (int i = from2; i < until2; ++i) {
                if (this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable[i] == null) continue;
                ++count2;
            }
            return (int)var3_3;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public int countBucketSizes(int frombucket, int untilbucket) {
            void var3_3;
            int count2 = 0;
            for (int i = frombucket; i < untilbucket; ++i) {
                count2 += this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap()[i];
            }
            return (int)var3_3;
        }

        public /* synthetic */ ParFlatHashTable scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer() {
            return this.$outer;
        }

        public ParFlatHashTableIterator(ParFlatHashTable<T> $outer, int idx, int until2, int totalsize) {
            this.idx = idx;
            this.until = until2;
            this.totalsize = totalsize;
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
            this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed = 0;
            this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable = $outer.table();
            if (this.hasNext()) {
                this.scan();
            }
        }
    }
}

