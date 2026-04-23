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
import scala.Proxy$class;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike$class;
import scala.collection.IterableProxyLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.MapProxyLike$class;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableProxyLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.Map$class;
import scala.collection.mutable.MapLike;
import scala.collection.mutable.MapLike$class;
import scala.collection.mutable.MapProxy;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParMap;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class MapProxy$class {
    private static MapProxy newProxy(MapProxy $this, scala.collection.mutable.Map newSelf) {
        return new MapProxy<A, Object>($this, newSelf){
            private final scala.collection.mutable.Map<A, Object> self;

            public MapProxy<A, Object> repr() {
                return MapProxy$class.repr(this);
            }

            public MapProxy<A, Object> empty() {
                return MapProxy$class.empty(this);
            }

            public <B1> MapProxy<A, B1> updated(A key, B1 value) {
                return MapProxy$class.updated(this, key, value);
            }

            public <B1> scala.collection.mutable.Map<A, B1> $plus(Tuple2<A, B1> kv) {
                return MapProxy$class.$plus(this, kv);
            }

            public <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return MapProxy$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return MapProxy$class.$plus$plus(this, xs);
            }

            public MapProxy<A, Object> $minus(A key) {
                return MapProxy$class.$minus(this, key);
            }

            public MapProxy<A, Object> $plus$eq(Tuple2<A, Object> kv) {
                return MapProxy$class.$plus$eq(this, kv);
            }

            public MapProxy<A, Object> $minus$eq(A key) {
                return MapProxy$class.$minus$eq(this, key);
            }

            public Option<Object> get(A key) {
                return MapProxyLike$class.get(this, key);
            }

            public Iterator<Tuple2<A, Object>> iterator() {
                return MapProxyLike$class.iterator(this);
            }

            public boolean isEmpty() {
                return MapProxyLike$class.isEmpty(this);
            }

            public <B1> B1 getOrElse(A key, Function0<B1> function0) {
                return (B1)MapProxyLike$class.getOrElse(this, key, function0);
            }

            public Object apply(A key) {
                return MapProxyLike$class.apply(this, key);
            }

            public boolean contains(A key) {
                return MapProxyLike$class.contains(this, key);
            }

            public boolean isDefinedAt(A key) {
                return MapProxyLike$class.isDefinedAt(this, key);
            }

            public Set<A> keySet() {
                return MapProxyLike$class.keySet(this);
            }

            public Iterator<A> keysIterator() {
                return MapProxyLike$class.keysIterator(this);
            }

            public Iterable<A> keys() {
                return MapProxyLike$class.keys(this);
            }

            public Iterable<Object> values() {
                return MapProxyLike$class.values(this);
            }

            public Iterator<Object> valuesIterator() {
                return MapProxyLike$class.valuesIterator(this);
            }

            public Object default(A key) {
                return MapProxyLike$class.default(this, key);
            }

            public Map<A, Object> filterKeys(Function1<A, Object> p) {
                return MapProxyLike$class.filterKeys(this, p);
            }

            public <C> Map<A, C> mapValues(Function1<Object, C> f) {
                return MapProxyLike$class.mapValues(this, f);
            }

            public Map filterNot(Function1 p) {
                return MapProxyLike$class.filterNot(this, p);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return MapProxyLike$class.addString(this, b, start, sep, end);
            }

            public Iterator<scala.collection.mutable.Map<A, Object>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.mutable.Map<A, Object>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.mutable.Map<A, Object>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.mutable.Map<A, Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.mutable.Map<A, Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.mutable.Map<A, Object>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<Tuple2<A, Object>, scala.collection.mutable.Map<A, Object>> view(int from2, int until2) {
                return IterableProxyLike$class.view(this, from2, until2);
            }

            public <U> void foreach(Function1<Tuple2<A, Object>, U> f) {
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

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<Tuple2<A, Object>, B> f, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<Tuple2<A, Object>, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public <B, That> That collect(PartialFunction<Tuple2<A, Object>, B> pf, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.mutable.Map<A, Object>, scala.collection.mutable.Map<A, Object>> partition(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> scala.collection.immutable.Map<K, scala.collection.mutable.Map<A, Object>> groupBy(Function1<Tuple2<A, Object>, K> f) {
                return TraversableProxyLike$class.groupBy(this, f);
            }

            public boolean forall(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.exists(this, p);
            }

            public int count(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.count(this, p);
            }

            public Option<Tuple2<A, Object>> find(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.find(this, p);
            }

            public <B> B foldLeft(B z, Function2<B, Tuple2<A, Object>, B> op) {
                return (B)TraversableProxyLike$class.foldLeft(this, z, op);
            }

            public <B> B $div$colon(B z, Function2<B, Tuple2<A, Object>, B> op) {
                return (B)TraversableProxyLike$class.$div$colon(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Tuple2<A, Object>, B, B> op) {
                return (B)TraversableProxyLike$class.foldRight(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Tuple2<A, Object>, B, B> op) {
                return (B)TraversableProxyLike$class.$colon$bslash(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Tuple2<A, Object>, B> op) {
                return (B)TraversableProxyLike$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<A, Object>, B> op) {
                return TraversableProxyLike$class.reduceLeftOption(this, op);
            }

            public <B> B reduceRight(Function2<Tuple2<A, Object>, B, B> op) {
                return (B)TraversableProxyLike$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Tuple2<A, Object>, B, B> op) {
                return TraversableProxyLike$class.reduceRightOption(this, op);
            }

            public <B, That> That scanLeft(B z, Function2<B, Tuple2<A, Object>, B> op, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<Tuple2<A, Object>, B, B> op, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanRight(this, z, op, bf);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableProxyLike$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableProxyLike$class.product(this, num);
            }

            public Object min(Ordering cmp) {
                return TraversableProxyLike$class.min(this, cmp);
            }

            public Object max(Ordering cmp) {
                return TraversableProxyLike$class.max(this, cmp);
            }

            public Object head() {
                return TraversableProxyLike$class.head(this);
            }

            public Option<Tuple2<A, Object>> headOption() {
                return TraversableProxyLike$class.headOption(this);
            }

            public Traversable tail() {
                return TraversableProxyLike$class.tail(this);
            }

            public Object last() {
                return TraversableProxyLike$class.last(this);
            }

            public Option<Tuple2<A, Object>> lastOption() {
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

            public Tuple2<scala.collection.mutable.Map<A, Object>, scala.collection.mutable.Map<A, Object>> span(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.mutable.Map<A, Object>, scala.collection.mutable.Map<A, Object>> splitAt(int n) {
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

            public List<Tuple2<A, Object>> toList() {
                return TraversableProxyLike$class.toList(this);
            }

            public Iterable<Tuple2<A, Object>> toIterable() {
                return TraversableProxyLike$class.toIterable(this);
            }

            public Seq<Tuple2<A, Object>> toSeq() {
                return TraversableProxyLike$class.toSeq(this);
            }

            public IndexedSeq<Tuple2<A, Object>> toIndexedSeq() {
                return TraversableProxyLike$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableProxyLike$class.toBuffer(this);
            }

            public Stream<Tuple2<A, Object>> toStream() {
                return TraversableProxyLike$class.toStream(this);
            }

            public <B> scala.collection.immutable.Set<B> toSet() {
                return TraversableProxyLike$class.toSet(this);
            }

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, Object>, Tuple2<T, U>> ev) {
                return TraversableProxyLike$class.toMap(this, ev);
            }

            public Traversable<Tuple2<A, Object>> toTraversable() {
                return TraversableProxyLike$class.toTraversable(this);
            }

            public Iterator<Tuple2<A, Object>> toIterator() {
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

            public scala.collection.mutable.Map<A, Object> seq() {
                return Map$class.seq(this);
            }

            public scala.collection.mutable.Map<A, Object> withDefault(Function1<A, Object> d) {
                return Map$class.withDefault(this, d);
            }

            public scala.collection.mutable.Map<A, Object> withDefaultValue(Object d) {
                return Map$class.withDefaultValue(this, d);
            }

            public Builder<Tuple2<A, Object>, scala.collection.mutable.Map<A, Object>> newBuilder() {
                return MapLike$class.newBuilder(this);
            }

            public Combiner<Tuple2<A, Object>, ParMap<A, Object>> parCombiner() {
                return MapLike$class.parCombiner(this);
            }

            public Option<Object> put(A key, Object value) {
                return MapLike$class.put(this, key, value);
            }

            public void update(A key, Object value) {
                MapLike$class.update(this, key, value);
            }

            public Option<Object> remove(A key) {
                return MapLike$class.remove(this, key);
            }

            public void clear() {
                MapLike$class.clear(this);
            }

            public Object getOrElseUpdate(A key, Function0<Object> op) {
                return MapLike$class.getOrElseUpdate(this, key, op);
            }

            public MapLike<A, Object, scala.collection.mutable.Map<A, Object>> transform(Function2<A, Object, Object> f) {
                return MapLike$class.transform(this, f);
            }

            public MapLike<A, Object, scala.collection.mutable.Map<A, Object>> retain(Function2<A, Object, Object> p) {
                return MapLike$class.retain(this, p);
            }

            public scala.collection.mutable.Map<A, Object> clone() {
                return MapLike$class.clone(this);
            }

            public scala.collection.mutable.Map<A, Object> result() {
                return MapLike$class.result(this);
            }

            public scala.collection.mutable.Map<A, Object> $minus(A elem1, A elem2, Seq<A> elems) {
                return MapLike$class.$minus(this, elem1, elem2, elems);
            }

            public scala.collection.mutable.Map<A, Object> $minus$minus(GenTraversableOnce<A> xs) {
                return MapLike$class.$minus$minus(this, xs);
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

            public <NewTo> Builder<Tuple2<A, Object>, NewTo> mapResult(Function1<scala.collection.mutable.Map<A, Object>, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Growable<Tuple2<A, Object>> $plus$plus$eq(TraversableOnce<Tuple2<A, Object>> xs) {
                return Growable$class.$plus$plus$eq(this, xs);
            }

            public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<A, C> andThen(Function1<Object, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<A, Option<Object>> lift() {
                return PartialFunction$class.lift(this);
            }

            public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
                return (B1)PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<A, Object> runWith(Function1<Object, U> action) {
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

            public <A> Function1<A, Object> compose(Function1<A, A> g) {
                return Function1$class.compose(this, g);
            }

            public GenericCompanion<scala.collection.mutable.Iterable> companion() {
                return Iterable$class.companion(this);
            }

            public Iterable<Tuple2<A, Object>> thisCollection() {
                return IterableLike$class.thisCollection(this);
            }

            public Iterable toCollection(Object repr) {
                return IterableLike$class.toCollection(this, repr);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, scala.collection.mutable.Iterable<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>> unzip(Function1<Tuple2<A, Object>, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>, scala.collection.mutable.Iterable<A3>> unzip3(Function1<Tuple2<A, Object>, Tuple3<A1, A2, A3>> asTriple) {
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

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.mutable.Map<A, Object>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Iterator<scala.collection.mutable.Map<A, Object>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<scala.collection.mutable.Map<A, Object>> inits() {
                return TraversableLike$class.inits(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<A, Object>, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<Tuple2<A, Object>, scala.collection.mutable.Map<A, Object>> withFilter(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableLike$class.withFilter(this, p);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<Tuple2<A, Object>> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public <B> Option<B> collectFirst(PartialFunction<Tuple2<A, Object>, B> pf) {
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

            public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<A, Object>, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.minBy(this, f, cmp);
            }

            public Vector<Tuple2<A, Object>> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public scala.collection.mutable.Map<A, Object> self() {
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
                GenMapLike$class.$init$(this);
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                Subtractable$class.$init$(this);
                scala.collection.MapLike$class.$init$(this);
                scala.collection.Map$class.$init$(this);
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Shrinkable$class.$init$(this);
                Cloneable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                Proxy$class.$init$(this);
                TraversableProxyLike$class.$init$(this);
                IterableProxyLike$class.$init$(this);
                MapProxyLike$class.$init$(this);
                MapProxy$class.$init$(this);
                this.self = newSelf$1;
            }
        };
    }

    public static MapProxy repr(MapProxy $this) {
        return $this;
    }

    public static MapProxy empty(MapProxy $this) {
        return new MapProxy<A, B>($this){
            private final scala.collection.mutable.Map<A, B> self;

            public MapProxy<A, B> repr() {
                return MapProxy$class.repr(this);
            }

            public MapProxy<A, B> empty() {
                return MapProxy$class.empty(this);
            }

            public <B1> MapProxy<A, B1> updated(A key, B1 value) {
                return MapProxy$class.updated(this, key, value);
            }

            public <B1> scala.collection.mutable.Map<A, B1> $plus(Tuple2<A, B1> kv) {
                return MapProxy$class.$plus(this, kv);
            }

            public <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return MapProxy$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return MapProxy$class.$plus$plus(this, xs);
            }

            public MapProxy<A, B> $minus(A key) {
                return MapProxy$class.$minus(this, key);
            }

            public MapProxy<A, B> $plus$eq(Tuple2<A, B> kv) {
                return MapProxy$class.$plus$eq(this, kv);
            }

            public MapProxy<A, B> $minus$eq(A key) {
                return MapProxy$class.$minus$eq(this, key);
            }

            public Option<B> get(A key) {
                return MapProxyLike$class.get(this, key);
            }

            public Iterator<Tuple2<A, B>> iterator() {
                return MapProxyLike$class.iterator(this);
            }

            public boolean isEmpty() {
                return MapProxyLike$class.isEmpty(this);
            }

            public <B1> B1 getOrElse(A key, Function0<B1> function0) {
                return (B1)MapProxyLike$class.getOrElse(this, key, function0);
            }

            public B apply(A key) {
                return (B)MapProxyLike$class.apply(this, key);
            }

            public boolean contains(A key) {
                return MapProxyLike$class.contains(this, key);
            }

            public boolean isDefinedAt(A key) {
                return MapProxyLike$class.isDefinedAt(this, key);
            }

            public Set<A> keySet() {
                return MapProxyLike$class.keySet(this);
            }

            public Iterator<A> keysIterator() {
                return MapProxyLike$class.keysIterator(this);
            }

            public Iterable<A> keys() {
                return MapProxyLike$class.keys(this);
            }

            public Iterable<B> values() {
                return MapProxyLike$class.values(this);
            }

            public Iterator<B> valuesIterator() {
                return MapProxyLike$class.valuesIterator(this);
            }

            public B default(A key) {
                return (B)MapProxyLike$class.default(this, key);
            }

            public Map<A, B> filterKeys(Function1<A, Object> p) {
                return MapProxyLike$class.filterKeys(this, p);
            }

            public <C> Map<A, C> mapValues(Function1<B, C> f) {
                return MapProxyLike$class.mapValues(this, f);
            }

            public Map filterNot(Function1 p) {
                return MapProxyLike$class.filterNot(this, p);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return MapProxyLike$class.addString(this, b, start, sep, end);
            }

            public Iterator<scala.collection.mutable.Map<A, B>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.mutable.Map<A, B>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.mutable.Map<A, B>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.mutable.Map<A, B>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.mutable.Map<A, B>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.mutable.Map<A, B>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<Tuple2<A, B>, scala.collection.mutable.Map<A, B>> view(int from2, int until2) {
                return IterableProxyLike$class.view(this, from2, until2);
            }

            public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
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

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<Tuple2<A, B>, B> f, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<Tuple2<A, B>, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public <B, That> That collect(PartialFunction<Tuple2<A, B>, B> pf, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.mutable.Map<A, B>, scala.collection.mutable.Map<A, B>> partition(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> scala.collection.immutable.Map<K, scala.collection.mutable.Map<A, B>> groupBy(Function1<Tuple2<A, B>, K> f) {
                return TraversableProxyLike$class.groupBy(this, f);
            }

            public boolean forall(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.forall(this, p);
            }

            public boolean exists(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.exists(this, p);
            }

            public int count(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.count(this, p);
            }

            public Option<Tuple2<A, B>> find(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.find(this, p);
            }

            public <B> B foldLeft(B z, Function2<B, Tuple2<A, B>, B> op) {
                return (B)TraversableProxyLike$class.foldLeft(this, z, op);
            }

            public <B> B $div$colon(B z, Function2<B, Tuple2<A, B>, B> op) {
                return (B)TraversableProxyLike$class.$div$colon(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Tuple2<A, B>, B, B> op) {
                return (B)TraversableProxyLike$class.foldRight(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Tuple2<A, B>, B, B> op) {
                return (B)TraversableProxyLike$class.$colon$bslash(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Tuple2<A, B>, B> op) {
                return (B)TraversableProxyLike$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<A, B>, B> op) {
                return TraversableProxyLike$class.reduceLeftOption(this, op);
            }

            public <B> B reduceRight(Function2<Tuple2<A, B>, B, B> op) {
                return (B)TraversableProxyLike$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Tuple2<A, B>, B, B> op) {
                return TraversableProxyLike$class.reduceRightOption(this, op);
            }

            public <B, That> That scanLeft(B z, Function2<B, Tuple2<A, B>, B> op, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<Tuple2<A, B>, B, B> op, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanRight(this, z, op, bf);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableProxyLike$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableProxyLike$class.product(this, num);
            }

            public Object min(Ordering cmp) {
                return TraversableProxyLike$class.min(this, cmp);
            }

            public Object max(Ordering cmp) {
                return TraversableProxyLike$class.max(this, cmp);
            }

            public Object head() {
                return TraversableProxyLike$class.head(this);
            }

            public Option<Tuple2<A, B>> headOption() {
                return TraversableProxyLike$class.headOption(this);
            }

            public Traversable tail() {
                return TraversableProxyLike$class.tail(this);
            }

            public Object last() {
                return TraversableProxyLike$class.last(this);
            }

            public Option<Tuple2<A, B>> lastOption() {
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

            public Tuple2<scala.collection.mutable.Map<A, B>, scala.collection.mutable.Map<A, B>> span(Function1<Tuple2<A, B>, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.mutable.Map<A, B>, scala.collection.mutable.Map<A, B>> splitAt(int n) {
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

            public List<Tuple2<A, B>> toList() {
                return TraversableProxyLike$class.toList(this);
            }

            public Iterable<Tuple2<A, B>> toIterable() {
                return TraversableProxyLike$class.toIterable(this);
            }

            public Seq<Tuple2<A, B>> toSeq() {
                return TraversableProxyLike$class.toSeq(this);
            }

            public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
                return TraversableProxyLike$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableProxyLike$class.toBuffer(this);
            }

            public Stream<Tuple2<A, B>> toStream() {
                return TraversableProxyLike$class.toStream(this);
            }

            public <B> scala.collection.immutable.Set<B> toSet() {
                return TraversableProxyLike$class.toSet(this);
            }

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, B>, Tuple2<T, U>> ev) {
                return TraversableProxyLike$class.toMap(this, ev);
            }

            public Traversable<Tuple2<A, B>> toTraversable() {
                return TraversableProxyLike$class.toTraversable(this);
            }

            public Iterator<Tuple2<A, B>> toIterator() {
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

            public scala.collection.mutable.Map<A, B> seq() {
                return Map$class.seq(this);
            }

            public scala.collection.mutable.Map<A, B> withDefault(Function1<A, B> d) {
                return Map$class.withDefault(this, d);
            }

            public scala.collection.mutable.Map<A, B> withDefaultValue(B d) {
                return Map$class.withDefaultValue(this, d);
            }

            public Builder<Tuple2<A, B>, scala.collection.mutable.Map<A, B>> newBuilder() {
                return MapLike$class.newBuilder(this);
            }

            public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
                return MapLike$class.parCombiner(this);
            }

            public Option<B> put(A key, B value) {
                return MapLike$class.put(this, key, value);
            }

            public void update(A key, B value) {
                MapLike$class.update(this, key, value);
            }

            public Option<B> remove(A key) {
                return MapLike$class.remove(this, key);
            }

            public void clear() {
                MapLike$class.clear(this);
            }

            public B getOrElseUpdate(A key, Function0<B> op) {
                return (B)MapLike$class.getOrElseUpdate(this, key, op);
            }

            public MapLike<A, B, scala.collection.mutable.Map<A, B>> transform(Function2<A, B, B> f) {
                return MapLike$class.transform(this, f);
            }

            public MapLike<A, B, scala.collection.mutable.Map<A, B>> retain(Function2<A, B, Object> p) {
                return MapLike$class.retain(this, p);
            }

            public scala.collection.mutable.Map<A, B> clone() {
                return MapLike$class.clone(this);
            }

            public scala.collection.mutable.Map<A, B> result() {
                return MapLike$class.result(this);
            }

            public scala.collection.mutable.Map<A, B> $minus(A elem1, A elem2, Seq<A> elems) {
                return MapLike$class.$minus(this, elem1, elem2, elems);
            }

            public scala.collection.mutable.Map<A, B> $minus$minus(GenTraversableOnce<A> xs) {
                return MapLike$class.$minus$minus(this, xs);
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

            public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1<scala.collection.mutable.Map<A, B>, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce<Tuple2<A, B>> xs) {
                return Growable$class.$plus$plus$eq(this, xs);
            }

            public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
                return PartialFunction$class.orElse(this, that);
            }

            public <C> PartialFunction<A, C> andThen(Function1<B, C> k) {
                return PartialFunction$class.andThen(this, k);
            }

            public Function1<A, Option<B>> lift() {
                return PartialFunction$class.lift(this);
            }

            public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
                return (B1)PartialFunction$class.applyOrElse(this, x, function1);
            }

            public <U> Function1<A, Object> runWith(Function1<B, U> action) {
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

            public <A> Function1<A, B> compose(Function1<A, A> g) {
                return Function1$class.compose(this, g);
            }

            public GenericCompanion<scala.collection.mutable.Iterable> companion() {
                return Iterable$class.companion(this);
            }

            public Iterable<Tuple2<A, B>> thisCollection() {
                return IterableLike$class.thisCollection(this);
            }

            public Iterable toCollection(Object repr) {
                return IterableLike$class.toCollection(this, repr);
            }

            public boolean canEqual(Object that) {
                return IterableLike$class.canEqual(this, that);
            }

            public <B> Builder<B, scala.collection.mutable.Iterable<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>> unzip(Function1<Tuple2<A, B>, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<scala.collection.mutable.Iterable<A1>, scala.collection.mutable.Iterable<A2>, scala.collection.mutable.Iterable<A3>> unzip3(Function1<Tuple2<A, B>, Tuple3<A1, A2, A3>> asTriple) {
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

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.mutable.Map<A, B>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Iterator<scala.collection.mutable.Map<A, B>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<scala.collection.mutable.Map<A, B>> inits() {
                return TraversableLike$class.inits(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<A, B>, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<Tuple2<A, B>, scala.collection.mutable.Map<A, B>> withFilter(Function1<Tuple2<A, B>, Object> p) {
                return TraversableLike$class.withFilter(this, p);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<Tuple2<A, B>> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public <B> Option<B> collectFirst(PartialFunction<Tuple2<A, B>, B> pf) {
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

            public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<A, B>, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public Object maxBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.maxBy(this, f, cmp);
            }

            public Object minBy(Function1 f, Ordering cmp) {
                return TraversableOnce$class.minBy(this, f, cmp);
            }

            public Vector<Tuple2<A, B>> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public scala.collection.mutable.Map<A, B> self() {
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
                GenMapLike$class.$init$(this);
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                Subtractable$class.$init$(this);
                scala.collection.MapLike$class.$init$(this);
                scala.collection.Map$class.$init$(this);
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Shrinkable$class.$init$(this);
                Cloneable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                Proxy$class.$init$(this);
                TraversableProxyLike$class.$init$(this);
                IterableProxyLike$class.$init$(this);
                MapProxyLike$class.$init$(this);
                MapProxy$class.$init$(this);
                this.self = ((scala.collection.mutable.Map)$outer.self()).empty();
            }
        };
    }

    public static MapProxy updated(MapProxy $this, Object key, Object value) {
        return MapProxy$class.newProxy($this, ((MapLike)$this.self()).updated(key, value));
    }

    public static scala.collection.mutable.Map $plus(MapProxy $this, Tuple2 kv) {
        return MapProxy$class.newProxy($this, ((MapLike)$this.self()).$plus(kv));
    }

    public static MapProxy $plus(MapProxy $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        return MapProxy$class.newProxy($this, ((MapLike)$this.self()).$plus(elem1, elem2, elems));
    }

    public static MapProxy $plus$plus(MapProxy $this, GenTraversableOnce xs) {
        return MapProxy$class.newProxy($this, ((MapLike)$this.self()).$plus$plus(xs.seq()));
    }

    public static MapProxy $minus(MapProxy $this, Object key) {
        return MapProxy$class.newProxy($this, ((MapLike)$this.self()).$minus(key));
    }

    public static MapProxy $plus$eq(MapProxy $this, Tuple2 kv) {
        ((MapLike)$this.self()).$plus$eq(kv);
        return $this;
    }

    public static MapProxy $minus$eq(MapProxy $this, Object key) {
        ((MapLike)$this.self()).$minus$eq(key);
        return $this;
    }

    public static void $init$(MapProxy $this) {
    }
}

