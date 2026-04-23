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
import scala.Predef$;
import scala.Proxy$class;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.IterableProxyLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Set$class;
import scala.collection.SetProxyLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableProxyLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.SetLike;
import scala.collection.mutable.SetLike$class;
import scala.collection.mutable.SetProxy;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSet;
import scala.collection.script.Message;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class SetProxy$class {
    public static SetProxy repr(SetProxy $this) {
        return $this;
    }

    public static SetProxy empty(SetProxy $this) {
        return new SetProxy<A>($this){
            private final scala.collection.mutable.Set<A> self;

            public SetProxy<A> repr() {
                return SetProxy$class.repr(this);
            }

            public Object empty() {
                return SetProxy$class.empty(this);
            }

            public SetProxy<A> $plus(A elem) {
                return SetProxy$class.$plus(this, elem);
            }

            public SetProxy<A> $minus(A elem) {
                return SetProxy$class.$minus(this, elem);
            }

            public SetProxy<A> $plus$eq(A elem) {
                return SetProxy$class.$plus$eq(this, elem);
            }

            public SetProxy<A> $minus$eq(A elem) {
                return SetProxy$class.$minus$eq(this, elem);
            }

            public boolean contains(A elem) {
                return SetProxyLike$class.contains(this, elem);
            }

            public boolean isEmpty() {
                return SetProxyLike$class.isEmpty(this);
            }

            public boolean apply(A elem) {
                return SetProxyLike$class.apply(this, elem);
            }

            public Set intersect(GenSet that) {
                return SetProxyLike$class.intersect(this, that);
            }

            public Set $amp(GenSet that) {
                return SetProxyLike$class.$amp(this, that);
            }

            public Set union(GenSet that) {
                return SetProxyLike$class.union(this, that);
            }

            public Set $bar(GenSet that) {
                return SetProxyLike$class.$bar(this, that);
            }

            public Set diff(GenSet that) {
                return SetProxyLike$class.diff(this, that);
            }

            public Set $amp$tilde(GenSet that) {
                return SetProxyLike$class.$amp$tilde(this, that);
            }

            public boolean subsetOf(GenSet<A> that) {
                return SetProxyLike$class.subsetOf(this, that);
            }

            public Iterator<A> iterator() {
                return IterableProxyLike$class.iterator(this);
            }

            public Iterator<scala.collection.mutable.Set<A>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.mutable.Set<A>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.mutable.Set<A>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.mutable.Set<A>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<A, scala.collection.mutable.Set<A>> view(int from2, int until2) {
                return IterableProxyLike$class.view(this, from2, until2);
            }

            public <U> void foreach(Function1<A, U> f) {
                TraversableProxyLike$class.foreach(this, f);
            }

            public boolean nonEmpty() {
                return TraversableProxyLike$class.nonEmpty(this);
            }

            public int size() {
                return TraversableProxyLike$class.size(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableProxyLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public Traversable filterNot(Function1 p) {
                return TraversableProxyLike$class.filterNot(this, p);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> partition(Function1<A, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> Map<K, scala.collection.mutable.Set<A>> groupBy(Function1<A, K> f) {
                return TraversableProxyLike$class.groupBy(this, f);
            }

            public boolean forall(Function1<A, Object> p) {
                return TraversableProxyLike$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return TraversableProxyLike$class.exists(this, p);
            }

            public int count(Function1<A, Object> p) {
                return TraversableProxyLike$class.count(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return TraversableProxyLike$class.find(this, p);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableProxyLike$class.foldLeft(this, z, op);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableProxyLike$class.$div$colon(this, z, op);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)TraversableProxyLike$class.foldRight(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableProxyLike$class.$colon$bslash(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableProxyLike$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableProxyLike$class.reduceLeftOption(this, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)TraversableProxyLike$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableProxyLike$class.reduceRightOption(this, op);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanRight(this, z, op, bf);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableProxyLike$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableProxyLike$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableProxyLike$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableProxyLike$class.max(this, cmp);
            }

            public A head() {
                return (A)TraversableProxyLike$class.head(this);
            }

            public Option<A> headOption() {
                return TraversableProxyLike$class.headOption(this);
            }

            public Traversable tail() {
                return TraversableProxyLike$class.tail(this);
            }

            public A last() {
                return (A)TraversableProxyLike$class.last(this);
            }

            public Option<A> lastOption() {
                return TraversableProxyLike$class.lastOption(this);
            }

            public Traversable init() {
                return TraversableProxyLike$class.init(this);
            }

            public Traversable take(int n) {
                return TraversableProxyLike$class.take(this, n);
            }

            public Traversable drop(int n) {
                return TraversableProxyLike$class.drop(this, n);
            }

            public Traversable slice(int from2, int until2) {
                return TraversableProxyLike$class.slice(this, from2, until2);
            }

            public Traversable takeWhile(Function1 p) {
                return TraversableProxyLike$class.takeWhile(this, p);
            }

            public Traversable dropWhile(Function1 p) {
                return TraversableProxyLike$class.dropWhile(this, p);
            }

            public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> span(Function1<A, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.mutable.Set<A>, scala.collection.mutable.Set<A>> splitAt(int n) {
                return TraversableProxyLike$class.splitAt(this, n);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableProxyLike$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                TraversableProxyLike$class.copyToArray(this, xs, start, len);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableProxyLike$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableProxyLike$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableProxyLike$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableProxyLike$class.toList(this);
            }

            public Iterable<A> toIterable() {
                return TraversableProxyLike$class.toIterable(this);
            }

            public Seq<A> toSeq() {
                return TraversableProxyLike$class.toSeq(this);
            }

            public IndexedSeq<A> toIndexedSeq() {
                return TraversableProxyLike$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableProxyLike$class.toBuffer(this);
            }

            public Stream<A> toStream() {
                return TraversableProxyLike$class.toStream(this);
            }

            public <B> scala.collection.immutable.Set<B> toSet() {
                return TraversableProxyLike$class.toSet(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableProxyLike$class.toMap(this, ev);
            }

            public Traversable<A> toTraversable() {
                return TraversableProxyLike$class.toTraversable(this);
            }

            public Iterator<A> toIterator() {
                return TraversableProxyLike$class.toIterator(this);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableProxyLike$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableProxyLike$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableProxyLike$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableProxyLike$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableProxyLike$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableProxyLike$class.addString(this, b);
            }

            public String stringPrefix() {
                return TraversableProxyLike$class.stringPrefix(this);
            }

            public int hashCode() {
                return Proxy$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return Proxy$class.equals(this, that);
            }

            public String toString() {
                return Proxy$class.toString(this);
            }

            public GenericCompanion<scala.collection.mutable.Set> companion() {
                return scala.collection.mutable.Set$class.companion(this);
            }

            public scala.collection.mutable.Set<A> seq() {
                return scala.collection.mutable.Set$class.seq(this);
            }

            public Builder<A, scala.collection.mutable.Set<A>> newBuilder() {
                return SetLike$class.newBuilder(this);
            }

            public Combiner<A, ParSet<A>> parCombiner() {
                return SetLike$class.parCombiner(this);
            }

            public boolean add(A elem) {
                return SetLike$class.add(this, elem);
            }

            public boolean remove(A elem) {
                return SetLike$class.remove(this, elem);
            }

            public void update(A elem, boolean included) {
                SetLike$class.update(this, elem, included);
            }

            public void retain(Function1<A, Object> p) {
                SetLike$class.retain(this, p);
            }

            public void clear() {
                SetLike$class.clear(this);
            }

            public scala.collection.mutable.Set<A> clone() {
                return SetLike$class.clone(this);
            }

            public scala.collection.mutable.Set<A> result() {
                return SetLike$class.result(this);
            }

            public scala.collection.mutable.Set<A> $plus(A elem1, A elem2, Seq<A> elems) {
                return SetLike$class.$plus(this, elem1, elem2, elems);
            }

            public scala.collection.mutable.Set<A> $plus$plus(GenTraversableOnce<A> xs) {
                return SetLike$class.$plus$plus(this, xs);
            }

            public scala.collection.mutable.Set<A> $minus(A elem1, A elem2, Seq<A> elems) {
                return SetLike$class.$minus(this, elem1, elem2, elems);
            }

            public scala.collection.mutable.Set<A> $minus$minus(GenTraversableOnce<A> xs) {
                return SetLike$class.$minus$minus(this, xs);
            }

            public void $less$less(Message<A> cmd) {
                SetLike$class.$less$less(this, cmd);
            }

            public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
                return super.clone();
            }

            public Shrinkable<A> $minus$eq(A elem1, A elem2, Seq<A> elems) {
                return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
            }

            public Shrinkable<A> $minus$minus$eq(TraversableOnce<A> xs) {
                return Shrinkable$class.$minus$minus$eq(this, xs);
            }

            public void sizeHint(int size2) {
                Builder$class.sizeHint((Builder)this, size2);
            }

            public void sizeHint(TraversableLike<?, ?> coll) {
                Builder$class.sizeHint((Builder)this, coll);
            }

            public void sizeHint(TraversableLike<?, ?> coll, int delta) {
                Builder$class.sizeHint(this, coll, delta);
            }

            public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
                Builder$class.sizeHintBounded(this, size2, boundingColl);
            }

            public <NewTo> Builder<A, NewTo> mapResult(Function1<scala.collection.mutable.Set<A>, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Growable<A> $plus$plus$eq(TraversableOnce<A> xs) {
                return Growable$class.$plus$plus$eq(this, xs);
            }

            public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
                return TraversableLike$class.map(this, f, bf);
            }

            public Iterator<scala.collection.mutable.Set<A>> subsets(int len) {
                return scala.collection.SetLike$class.subsets(this, len);
            }

            public Iterator<scala.collection.mutable.Set<A>> subsets() {
                return scala.collection.SetLike$class.subsets(this);
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

            public <A> Function1<A, Object> compose(Function1<A, A> g) {
                return Function1$class.compose(this, g);
            }

            public <A> Function1<A, A> andThen(Function1<Object, A> g) {
                return Function1$class.andThen(this, g);
            }

            public Iterable<A> thisCollection() {
                return IterableLike$class.thisCollection(this);
            }

            public Iterable toCollection(Object repr) {
                return IterableLike$class.toCollection(this, repr);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, scala.collection.mutable.Set<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<scala.collection.mutable.Set<A1>, scala.collection.mutable.Set<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<scala.collection.mutable.Set<A1>, scala.collection.mutable.Set<A2>, scala.collection.mutable.Set<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return GenericTraversableTemplate$class.unzip3(this, asTriple);
            }

            public GenTraversable flatten(Function1 asTraversable) {
                return GenericTraversableTemplate$class.flatten(this, asTraversable);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.mutable.Set<A>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Iterator<scala.collection.mutable.Set<A>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<scala.collection.mutable.Set<A>> inits() {
                return TraversableLike$class.inits(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<A, scala.collection.mutable.Set<A>> withFilter(Function1<A, Object> p) {
                return TraversableLike$class.withFilter(this, p);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
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

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public scala.collection.mutable.Set<A> self() {
                return this.self;
            }
            {
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
                GenSetLike$class.$init$(this);
                GenericSetTemplate$class.$init$(this);
                GenSet$class.$init$(this);
                Subtractable$class.$init$(this);
                scala.collection.SetLike$class.$init$(this);
                Set$class.$init$(this);
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Shrinkable$class.$init$(this);
                Cloneable$class.$init$(this);
                SetLike$class.$init$(this);
                scala.collection.mutable.Set$class.$init$(this);
                Proxy$class.$init$(this);
                TraversableProxyLike$class.$init$(this);
                IterableProxyLike$class.$init$(this);
                SetProxyLike$class.$init$(this);
                SetProxy$class.$init$(this);
                this.self = (scala.collection.mutable.Set)((GenericSetTemplate)$outer.self()).empty();
            }
        };
    }

    public static SetProxy $plus(SetProxy $this, Object elem) {
        ((SetLike)$this.self()).$plus$eq(elem);
        return $this;
    }

    public static SetProxy $minus(SetProxy $this, Object elem) {
        ((SetLike)$this.self()).$minus$eq(elem);
        return $this;
    }

    public static SetProxy $plus$eq(SetProxy $this, Object elem) {
        ((SetLike)$this.self()).$plus$eq(elem);
        return $this;
    }

    public static SetProxy $minus$eq(SetProxy $this, Object elem) {
        ((SetLike)$this.self()).$minus$eq(elem);
        return $this;
    }

    public static void $init$(SetProxy $this) {
    }
}

