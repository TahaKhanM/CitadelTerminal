/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.IterableViewLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$class;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.SeqViewLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.IndexedSeqLike;
import scala.collection.mutable.IndexedSeqLike$;
import scala.collection.mutable.IndexedSeqView;
import scala.collection.mutable.IndexedSeqView$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.SeqLike;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class IndexedSeqLike$class {
    public static IndexedSeq thisCollection(IndexedSeqLike $this) {
        return (IndexedSeq)$this;
    }

    public static IndexedSeq toCollection(IndexedSeqLike $this, Object repr) {
        return (IndexedSeq)repr;
    }

    public static IndexedSeqView view(IndexedSeqLike $this) {
        return new IndexedSeqView<A, Repr>($this){
            private Repr underlying;
            private final /* synthetic */ IndexedSeqLike $outer;
            private volatile boolean bitmap$0;

            private Object underlying$lzycompute() {
                IndexedSeqLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = this.$outer.repr();
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public /* synthetic */ IndexedSeqView scala$collection$mutable$IndexedSeqView$$super$tail() {
                return (IndexedSeqView)TraversableViewLike$class.tail(this);
            }

            public IndexedSeqView.Transformed<A> newFiltered(Function1<A, Object> p) {
                return IndexedSeqView$class.newFiltered(this, p);
            }

            public IndexedSeqView.Transformed<A> newSliced(SliceInterval _endpoints) {
                return IndexedSeqView$class.newSliced(this, _endpoints);
            }

            public IndexedSeqView.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return IndexedSeqView$class.newDroppedWhile(this, p);
            }

            public IndexedSeqView.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return IndexedSeqView$class.newTakenWhile(this, p);
            }

            public IndexedSeqView.Transformed<A> newReversed() {
                return IndexedSeqView$class.newReversed(this);
            }

            public IndexedSeqView<A, Repr> filter(Function1<A, Object> p) {
                return IndexedSeqView$class.filter(this, p);
            }

            public IndexedSeqView<A, Repr> init() {
                return IndexedSeqView$class.init(this);
            }

            public IndexedSeqView<A, Repr> drop(int n) {
                return IndexedSeqView$class.drop(this, n);
            }

            public IndexedSeqView<A, Repr> take(int n) {
                return IndexedSeqView$class.take(this, n);
            }

            public IndexedSeqView<A, Repr> slice(int from2, int until2) {
                return IndexedSeqView$class.slice(this, from2, until2);
            }

            public IndexedSeqView<A, Repr> dropWhile(Function1<A, Object> p) {
                return IndexedSeqView$class.dropWhile(this, p);
            }

            public IndexedSeqView<A, Repr> takeWhile(Function1<A, Object> p) {
                return IndexedSeqView$class.takeWhile(this, p);
            }

            public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> span(Function1<A, Object> p) {
                return IndexedSeqView$class.span(this, p);
            }

            public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> splitAt(int n) {
                return IndexedSeqView$class.splitAt(this, n);
            }

            public IndexedSeqView<A, Repr> reverse() {
                return IndexedSeqView$class.reverse(this);
            }

            public IndexedSeqView<A, Repr> tail() {
                return IndexedSeqView$class.tail(this);
            }

            public <B> SeqViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return SeqViewLike$class.newForced(this, xs);
            }

            public <B> SeqViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return SeqViewLike$class.newAppended(this, that);
            }

            public <B> SeqViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return SeqViewLike$class.newMapped(this, f);
            }

            public <B> SeqViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return SeqViewLike$class.newFlatMapped(this, f);
            }

            public <B> SeqViewLike.Transformed<Tuple2<A, B>> newZipped(GenIterable<B> that) {
                return SeqViewLike$class.newZipped(this, that);
            }

            public <A1, B> SeqViewLike.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
                return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
            }

            public <B> SeqViewLike.Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
                return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
            }

            public <B> SeqViewLike.Transformed<B> newPrepended(B elem) {
                return SeqViewLike$class.newPrepended(this, elem);
            }

            public SeqViewLike.Transformed<A> newTaken(int n) {
                return SeqViewLike$class.newTaken(this, n);
            }

            public SeqViewLike.Transformed<A> newDropped(int n) {
                return SeqViewLike$class.newDropped(this, n);
            }

            public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.patch(this, from2, patch2, replaced, bf);
            }

            public <B, That> That padTo(int len, B elem, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.padTo(this, len, elem, bf);
            }

            public <B, That> That reverseMap(Function1<A, B> f, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.reverseMap(this, f, bf);
            }

            public <B, That> That updated(int index, B elem, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.updated(this, index, elem, bf);
            }

            public <B, That> That $plus$colon(B elem, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
            }

            public <B, That> That $colon$plus(B elem, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
            }

            public <B, That> That union(GenSeq<B> that, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)SeqViewLike$class.union(this, that, bf);
            }

            public SeqView diff(GenSeq that) {
                return SeqViewLike$class.diff(this, that);
            }

            public SeqView intersect(GenSeq that) {
                return SeqViewLike$class.intersect(this, that);
            }

            public SeqView sorted(Ordering ord) {
                return SeqViewLike$class.sorted(this, ord);
            }

            public SeqView sortWith(Function2 lt) {
                return SeqViewLike$class.sortWith(this, lt);
            }

            public SeqView sortBy(Function1 f, Ordering ord) {
                return SeqViewLike$class.sortBy(this, f, ord);
            }

            public Iterator<IndexedSeqView<A, Repr>> combinations(int n) {
                return SeqViewLike$class.combinations(this, n);
            }

            public Iterator<IndexedSeqView<A, Repr>> permutations() {
                return SeqViewLike$class.permutations(this);
            }

            public SeqView distinct() {
                return SeqViewLike$class.distinct(this);
            }

            public String stringPrefix() {
                return SeqViewLike$class.stringPrefix(this);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<IndexedSeqView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zip(this, that, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<IndexedSeqView<A, Repr>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableViewLike$class.zipWithIndex(this, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<IndexedSeqView<A, Repr>, Tuple2<A1, B>, That> bf) {
                return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public Iterator<IndexedSeqView<A, Repr>> grouped(int size2) {
                return IterableViewLike$class.grouped(this, size2);
            }

            public Iterator<IndexedSeqView<A, Repr>> sliding(int size2, int step) {
                return IterableViewLike$class.sliding(this, size2, step);
            }

            public Iterator<IndexedSeqView<A, Repr>> sliding(int size2) {
                return IterableViewLike$class.sliding(this, size2);
            }

            public IterableView dropRight(int n) {
                return IterableViewLike$class.dropRight(this, n);
            }

            public IterableView takeRight(int n) {
                return IterableViewLike$class.takeRight(this, n);
            }

            public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
                return (TraversableView)IndexedSeqOptimized$class.tail(this);
            }

            public String viewIdentifier() {
                return TraversableViewLike$class.viewIdentifier(this);
            }

            public String viewIdString() {
                return TraversableViewLike$class.viewIdString(this);
            }

            public String viewToString() {
                return TraversableViewLike$class.viewToString(this);
            }

            public Builder<A, IndexedSeqView<A, Repr>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That force(CanBuildFrom<Repr, B, That> bf) {
                return (That)TraversableViewLike$class.force(this, bf);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.flatMap(this, f, bf);
            }

            public <B> TraversableViewLike.Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
                return TraversableViewLike$class.flatten(this, asTraversable);
            }

            public TraversableView withFilter(Function1 p) {
                return TraversableViewLike$class.withFilter(this, p);
            }

            public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> partition(Function1<A, Object> p) {
                return TraversableViewLike$class.partition(this, p);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> Map<K, IndexedSeqView<A, Repr>> groupBy(Function1<A, K> f) {
                return TraversableViewLike$class.groupBy(this, f);
            }

            public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return TraversableViewLike$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return TraversableViewLike$class.unzip3(this, asTriple);
            }

            public TraversableView filterNot(Function1 p) {
                return TraversableViewLike$class.filterNot(this, p);
            }

            public Iterator<IndexedSeqView<A, Repr>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<IndexedSeqView<A, Repr>> tails() {
                return TraversableViewLike$class.tails(this);
            }

            public String toString() {
                return TraversableViewLike$class.toString(this);
            }

            public Seq<A> thisSeq() {
                return ViewMkString$class.thisSeq(this);
            }

            public String mkString() {
                return ViewMkString$class.mkString(this);
            }

            public String mkString(String sep) {
                return ViewMkString$class.mkString(this, sep);
            }

            public String mkString(String start, String sep, String end) {
                return ViewMkString$class.mkString(this, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return ViewMkString$class.addString(this, b, start, sep, end);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
                return TraversableOnce$class.reduceLeft(this, op);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
                return IterableLike$class.reduceRight(this, op);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
                return IterableLike$class.zip(this, that, bf);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$head() {
                return IterableLike$class.head(this);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$tail() {
                return TraversableLike$class.tail(this);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$last() {
                return TraversableLike$class.last(this);
            }

            public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$init() {
                return TraversableLike$class.init(this);
            }

            public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
                return IterableLike$class.sameElements(this, that);
            }

            public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
                return SeqLike$class.endsWith(this, that);
            }

            public boolean isEmpty() {
                return IndexedSeqOptimized$class.isEmpty(this);
            }

            public <U> void foreach(Function1<A, U> f) {
                IndexedSeqOptimized$class.foreach(this, f);
            }

            public boolean forall(Function1<A, Object> p) {
                return IndexedSeqOptimized$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return IndexedSeqOptimized$class.exists(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return IndexedSeqOptimized$class.find(this, p);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)IndexedSeqOptimized$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)IndexedSeqOptimized$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)IndexedSeqOptimized$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)IndexedSeqOptimized$class.reduceRight(this, op);
            }

            public A head() {
                return (A)IndexedSeqOptimized$class.head(this);
            }

            public A last() {
                return (A)IndexedSeqOptimized$class.last(this);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IndexedSeqOptimized$class.sameElements(this, that);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                IndexedSeqOptimized$class.copyToArray(this, xs, start, len);
            }

            public int lengthCompare(int len) {
                return IndexedSeqOptimized$class.lengthCompare(this, len);
            }

            public int segmentLength(Function1<A, Object> p, int from2) {
                return IndexedSeqOptimized$class.segmentLength(this, p, from2);
            }

            public int indexWhere(Function1<A, Object> p, int from2) {
                return IndexedSeqOptimized$class.indexWhere(this, p, from2);
            }

            public int lastIndexWhere(Function1<A, Object> p, int end) {
                return IndexedSeqOptimized$class.lastIndexWhere(this, p, end);
            }

            public Iterator<A> reverseIterator() {
                return IndexedSeqOptimized$class.reverseIterator(this);
            }

            public <B> boolean startsWith(GenSeq<B> that, int offset) {
                return IndexedSeqOptimized$class.startsWith(this, that, offset);
            }

            public <B> boolean endsWith(GenSeq<B> that) {
                return IndexedSeqOptimized$class.endsWith(this, that);
            }

            public GenericCompanion<IndexedSeq> companion() {
                return scala.collection.mutable.IndexedSeq$class.companion(this);
            }

            public IndexedSeq<A> seq() {
                return scala.collection.mutable.IndexedSeq$class.seq(this);
            }

            public IndexedSeq<A> thisCollection() {
                return IndexedSeqLike$class.thisCollection(this);
            }

            public IndexedSeq toCollection(Object repr) {
                return IndexedSeqLike$class.toCollection(this, repr);
            }

            public Object view() {
                return IndexedSeqLike$class.view(this);
            }

            public IndexedSeqView<A, IndexedSeqView<A, Repr>> view(int from2, int until2) {
                return IndexedSeqLike$class.view(this, from2, until2);
            }

            public int hashCode() {
                return scala.collection.IndexedSeqLike$class.hashCode(this);
            }

            public <A1> Buffer<A1> toBuffer() {
                return scala.collection.IndexedSeqLike$class.toBuffer(this);
            }

            public Combiner<A, ParSeq<A>> parCombiner() {
                return scala.collection.mutable.SeqLike$class.parCombiner(this);
            }

            public SeqLike<A, scala.collection.mutable.Seq<A>> transform(Function1<A, A> f) {
                return scala.collection.mutable.SeqLike$class.transform(this, f);
            }

            public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
                return super.clone();
            }

            public Object clone() {
                return Cloneable$class.clone(this);
            }

            public int size() {
                return SeqLike$class.size(this);
            }

            public <B> int indexOfSlice(GenSeq<B> that) {
                return SeqLike$class.indexOfSlice(this, that);
            }

            public <B> int indexOfSlice(GenSeq<B> that, int from2) {
                return SeqLike$class.indexOfSlice(this, that, from2);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that) {
                return SeqLike$class.lastIndexOfSlice(this, that);
            }

            public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
                return SeqLike$class.lastIndexOfSlice(this, that, end);
            }

            public <B> boolean containsSlice(GenSeq<B> that) {
                return SeqLike$class.containsSlice(this, that);
            }

            public <A1> boolean contains(A1 elem) {
                return SeqLike$class.contains(this, elem);
            }

            public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
                return SeqLike$class.corresponds(this, that, p);
            }

            public Seq<A> toSeq() {
                return SeqLike$class.toSeq(this);
            }

            public Range indices() {
                return SeqLike$class.indices(this);
            }

            public boolean isDefinedAt(int idx) {
                return GenSeqLike$class.isDefinedAt(this, idx);
            }

            public int prefixLength(Function1<A, Object> p) {
                return GenSeqLike$class.prefixLength(this, p);
            }

            public int indexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return GenSeqLike$class.indexOf(this, elem);
            }

            public <B> int indexOf(B elem, int from2) {
                return GenSeqLike$class.indexOf(this, elem, from2);
            }

            public <B> int lastIndexOf(B elem) {
                return GenSeqLike$class.lastIndexOf(this, elem);
            }

            public <B> int lastIndexOf(B elem, int end) {
                return GenSeqLike$class.lastIndexOf(this, elem, end);
            }

            public int lastIndexWhere(Function1<A, Object> p) {
                return GenSeqLike$class.lastIndexWhere(this, p);
            }

            public <B> boolean startsWith(GenSeq<B> that) {
                return GenSeqLike$class.startsWith(this, that);
            }

            public boolean equals(Object that) {
                return GenSeqLike$class.equals(this, that);
            }

            public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<Object, C> andThen(Function1<A, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<Object, Option<A>> lift() {
                return PartialFunction$class.lift(this);
            }

            public Object applyOrElse(Object x, Function1 function1) {
                return PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<Object, Object> runWith(Function1<A, U> action) {
                return PartialFunction$class.runWith(this, action);
            }

            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            public <A> Function1<A, A> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            public Iterable<A> toIterable() {
                return IterableLike$class.toIterable(this);
            }

            public Iterator<A> toIterator() {
                return IterableLike$class.toIterator(this);
            }

            public Stream<A> toStream() {
                return IterableLike$class.toStream(this);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, IndexedSeq<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public Object repr() {
                return TraversableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<IndexedSeqView<A, Repr>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Option<A> headOption() {
                return TraversableLike$class.headOption(this);
            }

            public Option<A> lastOption() {
                return TraversableLike$class.lastOption(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public scala.collection.immutable.IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public Repr underlying() {
                return (Repr)(this.bitmap$0 ? this.underlying : this.underlying$lzycompute());
            }

            public Iterator<A> iterator() {
                return this.$outer.iterator();
            }

            public int length() {
                return this.$outer.length();
            }

            public A apply(int idx) {
                return this.$outer.apply(idx);
            }

            public void update(int idx, A elem) {
                this.$outer.update(idx, elem);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                scala.collection.Traversable$class.$init$(this);
                Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                scala.collection.Iterable$class.$init$(this);
                Iterable$class.$init$(this);
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                GenSeqLike$class.$init$(this);
                GenSeq$class.$init$(this);
                SeqLike$class.$init$(this);
                Seq$class.$init$(this);
                Cloneable$class.$init$(this);
                scala.collection.mutable.SeqLike$class.$init$(this);
                scala.collection.mutable.Seq$class.$init$(this);
                scala.collection.IndexedSeqLike$class.$init$(this);
                IndexedSeq$class.$init$(this);
                IndexedSeqLike$class.$init$(this);
                scala.collection.mutable.IndexedSeq$class.$init$(this);
                IndexedSeqOptimized$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
                IterableViewLike$class.$init$(this);
                SeqViewLike$class.$init$(this);
                IndexedSeqView$class.$init$(this);
            }
        };
    }

    public static IndexedSeqView view(IndexedSeqLike $this, int from2, int until2) {
        return $this.view().slice(from2, until2);
    }

    public static void $init$(IndexedSeqLike $this) {
    }
}

