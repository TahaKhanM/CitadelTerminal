/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u00012Q!\u0001\u0002\u0002\u0002\u001d\u00111#\u00112tiJ\f7\r\u001e+sCZ,'o]1cY\u0016T!a\u0001\u0003\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001C\n\u0014\u0007\u0001IQ\u0002\u0005\u0002\u000b\u00175\tA!\u0003\u0002\r\t\t1\u0011I\\=SK\u001a\u00042AD\b\u0012\u001b\u0005\u0011\u0011B\u0001\t\u0003\u0005-!&/\u0019<feN\f'\r\\3\u0011\u0005I\u0019B\u0002\u0001\u0003\u0007)\u0001!)\u0019A\u000b\u0003\u0003\u0005\u000b\"AF\r\u0011\u0005)9\u0012B\u0001\r\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0003\u000e\n\u0005m!!aA!os\")Q\u0004\u0001C\u0001=\u00051A(\u001b8jiz\"\u0012a\b\t\u0004\u001d\u0001\t\u0002")
public abstract class AbstractTraversable<A>
implements Traversable<A> {
    @Override
    public GenericCompanion<Traversable> companion() {
        return Traversable$class.companion(this);
    }

    @Override
    public Traversable<A> seq() {
        return Traversable$class.seq(this);
    }

    @Override
    public Builder<A, Traversable<A>> newBuilder() {
        return GenericTraversableTemplate$class.newBuilder(this);
    }

    @Override
    public <B> Builder<B, Traversable<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
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
    public Object repr() {
        return TraversableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return TraversableLike$class.isTraversableAgain(this);
    }

    @Override
    public Traversable<A> thisCollection() {
        return TraversableLike$class.thisCollection(this);
    }

    @Override
    public Traversable toCollection(Object repr) {
        return TraversableLike$class.toCollection(this, repr);
    }

    @Override
    public Combiner<A, ParIterable<A>> parCombiner() {
        return TraversableLike$class.parCombiner(this);
    }

    @Override
    public boolean isEmpty() {
        return TraversableLike$class.isEmpty(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return TraversableLike$class.hasDefiniteSize(this);
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That map(Function1<A, B> f, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.map(this, f, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public Object filterNot(Function1 p) {
        return TraversableLike$class.filterNot(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<Traversable<A>, Traversable<A>> partition(Function1<A, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> Map<K, Traversable<A>> groupBy(Function1<A, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return TraversableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return TraversableLike$class.exists(this, p);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return TraversableLike$class.find(this, p);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Traversable<A>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<Traversable<A>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public A head() {
        return (A)TraversableLike$class.head(this);
    }

    @Override
    public Option<A> headOption() {
        return TraversableLike$class.headOption(this);
    }

    @Override
    public Object tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public A last() {
        return (A)TraversableLike$class.last(this);
    }

    @Override
    public Option<A> lastOption() {
        return TraversableLike$class.lastOption(this);
    }

    @Override
    public Object init() {
        return TraversableLike$class.init(this);
    }

    @Override
    public Object take(int n) {
        return TraversableLike$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return TraversableLike$class.drop(this, n);
    }

    @Override
    public Object slice(int from2, int until2) {
        return TraversableLike$class.slice(this, from2, until2);
    }

    @Override
    public Object sliceWithKnownDelta(int from2, int until2, int delta) {
        return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
    }

    @Override
    public Object sliceWithKnownBound(int from2, int until2) {
        return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return TraversableLike$class.takeWhile(this, p);
    }

    @Override
    public Object dropWhile(Function1 p) {
        return TraversableLike$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<Traversable<A>, Traversable<A>> span(Function1<A, Object> p) {
        return TraversableLike$class.span(this, p);
    }

    @Override
    public Tuple2<Traversable<A>, Traversable<A>> splitAt(int n) {
        return TraversableLike$class.splitAt(this, n);
    }

    @Override
    public Iterator<Traversable<A>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<Traversable<A>> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        TraversableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public Traversable<A> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public Iterator<A> toIterator() {
        return TraversableLike$class.toIterator(this);
    }

    @Override
    public Stream<A> toStream() {
        return TraversableLike$class.toStream(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public String toString() {
        return TraversableLike$class.toString(this);
    }

    @Override
    public String stringPrefix() {
        return TraversableLike$class.stringPrefix(this);
    }

    @Override
    public Object view() {
        return TraversableLike$class.view(this);
    }

    @Override
    public TraversableView<A, Traversable<A>> view(int from2, int until2) {
        return TraversableLike$class.view(this, from2, until2);
    }

    @Override
    public FilterMonadic<A, Traversable<A>> withFilter(Function1<A, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public Parallel par() {
        return Parallelizable$class.par(this);
    }

    @Override
    public List<A> reversed() {
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
    public int count(Function1<A, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, A, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)TraversableOnce$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)TraversableOnce$class.reduceRight(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
        return TraversableOnce$class.reduceRightOption(this, op);
    }

    @Override
    public <A1> A1 reduce(Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.reduce(this, op);
    }

    @Override
    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
        return TraversableOnce$class.reduceOption(this, op);
    }

    @Override
    public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.fold(this, z, op);
    }

    @Override
    public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
        return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableOnce$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableOnce$class.product(this, num);
    }

    @Override
    public <B> A min(Ordering<B> cmp) {
        return (A)TraversableOnce$class.min(this, cmp);
    }

    @Override
    public <B> A max(Ordering<B> cmp) {
        return (A)TraversableOnce$class.max(this, cmp);
    }

    @Override
    public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
        return (A)TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
        return (A)TraversableOnce$class.minBy(this, f, cmp);
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
    public List<A> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public Iterable<A> toIterable() {
        return TraversableOnce$class.toIterable(this);
    }

    @Override
    public Seq<A> toSeq() {
        return TraversableOnce$class.toSeq(this);
    }

    @Override
    public IndexedSeq<A> toIndexedSeq() {
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
    public Vector<A> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
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

    public AbstractTraversable() {
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        Traversable$class.$init$(this);
    }
}

