/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

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
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u000114Q!\u0001\u0002\u0001\u0005!\u0011aBQ;gM\u0016\u00148\u000b\u001d7jiR,'O\u0003\u0002\u0004\t\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006,\"!\u0003\u000b\u0014\u0007\u0001Qa\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001a\u00042a\u0004\t\u0013\u001b\u0005\u0011\u0011BA\t\u0003\u0005AIE/\u001a:bE2,7\u000b\u001d7jiR,'\u000f\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00049\"!\u0001+\u0004\u0001E\u0011\u0001d\u0007\t\u0003\u0017eI!A\u0007\u0004\u0003\u000f9{G\u000f[5oOB\u00111\u0002H\u0005\u0003;\u0019\u00111!\u00118z\u0011!y\u0002A!b\u0001\n\u0013\u0001\u0013A\u00022vM\u001a,'/F\u0001\"!\r\u0011SEE\u0007\u0002G)\u0011A\u0005B\u0001\b[V$\u0018M\u00197f\u0013\t13EA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\b\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\u0002\u000f\t,hMZ3sA!A!\u0006\u0001BA\u0002\u0013%1&A\u0003j]\u0012,\u00070F\u0001-!\tYQ&\u0003\u0002/\r\t\u0019\u0011J\u001c;\t\u0011A\u0002!\u00111A\u0005\nE\n\u0011\"\u001b8eKb|F%Z9\u0015\u0005I*\u0004CA\u00064\u0013\t!dA\u0001\u0003V]&$\bb\u0002\u001c0\u0003\u0003\u0005\r\u0001L\u0001\u0004q\u0012\n\u0004\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0015\u0002\u0017\u0002\r%tG-\u001a=!\u0011!Q\u0004A!b\u0001\n\u0013Y\u0013!B;oi&d\u0007\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\u0002\rUtG/\u001b7!\u0011!q\u0004A!A!\u0002\u0013y\u0014aB0tS\u001e$W\r\u001c\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005\u0012\tqaZ3oKJL7-\u0003\u0002E\u0003\nQ1+[4oC2d\u0017N\\4\t\u000b\u0019\u0003A\u0011A$\u0002\rqJg.\u001b;?)\u0015A\u0015JS&M!\ry\u0001A\u0005\u0005\u0006?\u0015\u0003\r!\t\u0005\u0006U\u0015\u0003\r\u0001\f\u0005\u0006u\u0015\u0003\r\u0001\f\u0005\u0006}\u0015\u0003\ra\u0010\u0005\u0006\u001d\u0002!\taT\u0001\bQ\u0006\u001ch*\u001a=u+\u0005\u0001\u0006CA\u0006R\u0013\t\u0011fAA\u0004C_>dW-\u00198\t\u000bQ\u0003A\u0011A+\u0002\t9,\u0007\u0010\u001e\u000b\u0002%!)q\u000b\u0001C\u0001W\u0005I!/Z7bS:Lgn\u001a\u0005\u00063\u0002!\tAW\u0001\u0004IV\u0004X#\u0001%\t\u000bq\u0003A\u0011A/\u0002\u000bM\u0004H.\u001b;\u0016\u0003y\u00032a\u00181\u000f\u001b\u0005!\u0011BA1\u0005\u0005\r\u0019V-\u001d\u0005\u0007G\u0002!\tE\u00013\u0002!\u0011,'-^4J]\u001a|'/\\1uS>tW#A3\u0011\u0005\u0019LgBA\u0006h\u0013\tAg!\u0001\u0004Qe\u0016$WMZ\u0005\u0003U.\u0014aa\u0015;sS:<'B\u00015\u0007\u0001")
public class BufferSplitter<T>
implements IterableSplitter<T> {
    private final ArrayBuffer<T> scala$collection$parallel$BufferSplitter$$buffer;
    private int scala$collection$parallel$BufferSplitter$$index;
    private final int scala$collection$parallel$BufferSplitter$$until;
    private Signalling signalDelegate;

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

    public ArrayBuffer<T> scala$collection$parallel$BufferSplitter$$buffer() {
        return this.scala$collection$parallel$BufferSplitter$$buffer;
    }

    public int scala$collection$parallel$BufferSplitter$$index() {
        return this.scala$collection$parallel$BufferSplitter$$index;
    }

    private void scala$collection$parallel$BufferSplitter$$index_$eq(int x$1) {
        this.scala$collection$parallel$BufferSplitter$$index = x$1;
    }

    public int scala$collection$parallel$BufferSplitter$$until() {
        return this.scala$collection$parallel$BufferSplitter$$until;
    }

    @Override
    public boolean hasNext() {
        return this.scala$collection$parallel$BufferSplitter$$index() < this.scala$collection$parallel$BufferSplitter$$until();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public T next() {
        void var1_1;
        T r = this.scala$collection$parallel$BufferSplitter$$buffer().apply(this.scala$collection$parallel$BufferSplitter$$index());
        this.scala$collection$parallel$BufferSplitter$$index_$eq(this.scala$collection$parallel$BufferSplitter$$index() + 1);
        return var1_1;
    }

    @Override
    public int remaining() {
        return this.scala$collection$parallel$BufferSplitter$$until() - this.scala$collection$parallel$BufferSplitter$$index();
    }

    @Override
    public BufferSplitter<T> dup() {
        return new BufferSplitter<T>(this.scala$collection$parallel$BufferSplitter$$buffer(), this.scala$collection$parallel$BufferSplitter$$index(), this.scala$collection$parallel$BufferSplitter$$until(), this.signalDelegate());
    }

    @Override
    public Seq<IterableSplitter<T>> split() {
        Seq seq;
        if (this.remaining() > 1) {
            int divsz = (this.scala$collection$parallel$BufferSplitter$$until() - this.scala$collection$parallel$BufferSplitter$$index()) / 2;
            seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new BufferSplitter[]{new BufferSplitter<T>(this.scala$collection$parallel$BufferSplitter$$buffer(), this.scala$collection$parallel$BufferSplitter$$index(), this.scala$collection$parallel$BufferSplitter$$index() + divsz, this.signalDelegate()), new BufferSplitter<T>(this.scala$collection$parallel$BufferSplitter$$buffer(), this.scala$collection$parallel$BufferSplitter$$index() + divsz, this.scala$collection$parallel$BufferSplitter$$until(), this.signalDelegate())}));
        } else {
            seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new BufferSplitter[]{this}));
        }
        return seq;
    }

    @Override
    public String debugInformation() {
        return this.buildString((Function1<Function1<String, BoxedUnit>, BoxedUnit>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ BufferSplitter $outer;

            public final void apply(Function1<String, BoxedUnit> append2) {
                append2.apply("---------------");
                append2.apply("Buffer iterator");
                append2.apply(new StringBuilder().append((Object)"buffer: ").append(this.$outer.scala$collection$parallel$BufferSplitter$$buffer()).toString());
                append2.apply(new StringBuilder().append((Object)"index: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$BufferSplitter$$index())).toString());
                append2.apply(new StringBuilder().append((Object)"until: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$BufferSplitter$$until())).toString());
                append2.apply("---------------");
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public BufferSplitter(ArrayBuffer<T> buffer, int index, int until2, Signalling _sigdel) {
        this.scala$collection$parallel$BufferSplitter$$buffer = buffer;
        this.scala$collection$parallel$BufferSplitter$$index = index;
        this.scala$collection$parallel$BufferSplitter$$until = until2;
        TraversableOnce$class.$init$(this);
        Iterator$class.$init$(this);
        RemainsIterator$class.$init$(this);
        AugmentedIterableIterator$class.$init$(this);
        DelegatedSignalling$class.$init$(this);
        IterableSplitter$class.$init$(this);
        this.signalDelegate_$eq(_sigdel);
    }
}

