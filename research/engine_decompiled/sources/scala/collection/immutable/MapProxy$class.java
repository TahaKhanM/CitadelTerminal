/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

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
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableProxyLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.MapProxyLike$class;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.Set$class;
import scala.collection.SetLike$class;
import scala.collection.SetProxyLike$class;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableProxyLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Subtractable;
import scala.collection.generic.Subtractable$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map$class;
import scala.collection.immutable.MapLike$class;
import scala.collection.immutable.MapProxy;
import scala.collection.immutable.SetProxy;
import scala.collection.immutable.SetProxy$class;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParSet;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public abstract class MapProxy$class {
    public static MapProxy repr(MapProxy $this) {
        return $this;
    }

    private static MapProxy newProxy(MapProxy $this, scala.collection.immutable.Map newSelf) {
        return new MapProxy<A, Object>($this, newSelf){
            private final scala.collection.immutable.Map<A, Object> self;

            public MapProxy<A, Object> repr() {
                return MapProxy$class.repr(this);
            }

            public MapProxy<A, Object> empty() {
                return MapProxy$class.empty(this);
            }

            public <B1> MapProxy<A, B1> updated(A key, B1 value) {
                return MapProxy$class.updated(this, key, value);
            }

            public MapProxy<A, Object> $minus(A key) {
                return MapProxy$class.$minus(this, key);
            }

            public <B1> scala.collection.immutable.Map<A, B1> $plus(Tuple2<A, B1> kv) {
                return MapProxy$class.$plus(this, kv);
            }

            public <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return MapProxy$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return MapProxy$class.$plus$plus(this, xs);
            }

            public scala.collection.immutable.Set<A> keySet() {
                return MapProxy$class.keySet(this);
            }

            public scala.collection.immutable.Map<A, Object> filterKeys(Function1<A, Object> p) {
                return MapProxy$class.filterKeys(this, p);
            }

            public <C> scala.collection.immutable.Map<A, C> mapValues(Function1<Object, C> f) {
                return MapProxy$class.mapValues(this, f);
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

            public Map filterNot(Function1 p) {
                return MapProxyLike$class.filterNot(this, p);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return MapProxyLike$class.addString(this, b, start, sep, end);
            }

            public Iterator<scala.collection.immutable.Map<A, Object>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.immutable.Map<A, Object>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.immutable.Map<A, Object>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.immutable.Map<A, Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.immutable.Map<A, Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.immutable.Map<A, Object>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<Tuple2<A, Object>, scala.collection.immutable.Map<A, Object>> view(int from2, int until2) {
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

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<Tuple2<A, Object>, B> f, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<Tuple2<A, Object>, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public <B, That> That collect(PartialFunction<Tuple2<A, Object>, B> pf, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.immutable.Map<A, Object>, scala.collection.immutable.Map<A, Object>> partition(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> scala.collection.immutable.Map<K, scala.collection.immutable.Map<A, Object>> groupBy(Function1<Tuple2<A, Object>, K> f) {
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

            public <B, That> That scanLeft(B z, Function2<B, Tuple2<A, Object>, B> op, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<Tuple2<A, Object>, B, B> op, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
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

            public Tuple2<scala.collection.immutable.Map<A, Object>, scala.collection.immutable.Map<A, Object>> span(Function1<Tuple2<A, Object>, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.immutable.Map<A, Object>, scala.collection.immutable.Map<A, Object>> splitAt(int n) {
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

            public scala.collection.immutable.Map<A, Object> seq() {
                return Map$class.seq(this);
            }

            public <B1> scala.collection.immutable.Map<A, B1> withDefault(Function1<A, B1> d) {
                return Map$class.withDefault(this, d);
            }

            public <B1> scala.collection.immutable.Map<A, B1> withDefaultValue(B1 d) {
                return Map$class.withDefaultValue(this, d);
            }

            public Combiner<Tuple2<A, Object>, ParMap<A, Object>> parCombiner() {
                return MapLike$class.parCombiner(this);
            }

            public <C, That> That transform(Function2<A, Object, C> f, CanBuildFrom<scala.collection.immutable.Map<A, Object>, Tuple2<A, C>, That> bf) {
                return (That)MapLike$class.transform(this, f, bf);
            }

            public Builder<Tuple2<A, Object>, scala.collection.immutable.Map<A, Object>> newBuilder() {
                return scala.collection.MapLike$class.newBuilder(this);
            }

            public Subtractable $minus(Object elem1, Object elem2, Seq elems) {
                return Subtractable$class.$minus(this, elem1, elem2, elems);
            }

            public Subtractable $minus$minus(GenTraversableOnce xs) {
                return Subtractable$class.$minus$minus(this, xs);
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

            public GenericCompanion<scala.collection.immutable.Iterable> companion() {
                return scala.collection.immutable.Iterable$class.companion(this);
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

            public <B> Builder<B, scala.collection.immutable.Iterable<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<scala.collection.immutable.Iterable<A1>, scala.collection.immutable.Iterable<A2>> unzip(Function1<Tuple2<A, Object>, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<scala.collection.immutable.Iterable<A1>, scala.collection.immutable.Iterable<A2>, scala.collection.immutable.Iterable<A3>> unzip3(Function1<Tuple2<A, Object>, Tuple3<A1, A2, A3>> asTriple) {
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

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.immutable.Map<A, Object>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Iterator<scala.collection.immutable.Map<A, Object>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<scala.collection.immutable.Map<A, Object>> inits() {
                return TraversableLike$class.inits(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<A, Object>, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<Tuple2<A, Object>, scala.collection.immutable.Map<A, Object>> withFilter(Function1<Tuple2<A, Object>, Object> p) {
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

            public scala.collection.immutable.Map<A, Object> self() {
                return this.self;
            }
            {
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                Traversable$class.$init$(this);
                scala.collection.immutable.Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                Iterable$class.$init$(this);
                scala.collection.immutable.Iterable$class.$init$(this);
                GenMapLike$class.$init$(this);
                Function1$class.$init$(this);
                PartialFunction$class.$init$(this);
                Subtractable$class.$init$(this);
                scala.collection.MapLike$class.$init$(this);
                scala.collection.Map$class.$init$(this);
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

    public static MapProxy empty(MapProxy $this) {
        return MapProxy$class.newProxy($this, ((scala.collection.immutable.Map)$this.self()).empty());
    }

    public static MapProxy updated(MapProxy $this, Object key, Object value) {
        return MapProxy$class.newProxy($this, ((scala.collection.immutable.MapLike)$this.self()).updated(key, value));
    }

    public static MapProxy $minus(MapProxy $this, Object key) {
        return MapProxy$class.newProxy($this, (scala.collection.immutable.Map)((MapLike)$this.self()).$minus(key));
    }

    public static scala.collection.immutable.Map $plus(MapProxy $this, Tuple2 kv) {
        return MapProxy$class.newProxy($this, ((scala.collection.immutable.Map)$this.self()).$plus(kv));
    }

    public static MapProxy $plus(MapProxy $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        return MapProxy$class.newProxy($this, ((scala.collection.immutable.MapLike)$this.self()).$plus(elem1, elem2, elems));
    }

    public static MapProxy $plus$plus(MapProxy $this, GenTraversableOnce xs) {
        return MapProxy$class.newProxy($this, ((scala.collection.immutable.MapLike)$this.self()).$plus$plus(xs.seq()));
    }

    public static scala.collection.immutable.Set keySet(MapProxy $this) {
        return new SetProxy<A>($this){
            private final scala.collection.immutable.Set<A> self;

            public SetProxy<A> repr() {
                return SetProxy$class.repr(this);
            }

            public SetProxy<A> empty() {
                return SetProxy$class.empty(this);
            }

            public SetProxy<A> $plus(A elem) {
                return SetProxy$class.$plus(this, elem);
            }

            public SetProxy<A> $minus(A elem) {
                return SetProxy$class.$minus(this, elem);
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

            public Iterator<scala.collection.immutable.Set<A>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.immutable.Set<A>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.immutable.Set<A>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.immutable.Set<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.immutable.Set<A>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.immutable.Set<A>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<A, scala.collection.immutable.Set<A>> view(int from2, int until2) {
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

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public Traversable filterNot(Function1 p) {
                return TraversableProxyLike$class.filterNot(this, p);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.immutable.Set<A>, scala.collection.immutable.Set<A>> partition(Function1<A, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> scala.collection.immutable.Map<K, scala.collection.immutable.Set<A>> groupBy(Function1<A, K> f) {
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

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
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

            public Tuple2<scala.collection.immutable.Set<A>, scala.collection.immutable.Set<A>> span(Function1<A, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.immutable.Set<A>, scala.collection.immutable.Set<A>> splitAt(int n) {
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

            public <T, U> scala.collection.immutable.Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
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

            public GenericCompanion<scala.collection.immutable.Set> companion() {
                return scala.collection.immutable.Set$class.companion(this);
            }

            public scala.collection.immutable.Set<A> seq() {
                return scala.collection.immutable.Set$class.seq(this);
            }

            public Combiner<A, ParSet<A>> parCombiner() {
                return scala.collection.immutable.Set$class.parCombiner(this);
            }

            public /* synthetic */ Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
                return TraversableLike$class.map(this, f, bf);
            }

            public Builder<A, scala.collection.immutable.Set<A>> newBuilder() {
                return SetLike$class.newBuilder(this);
            }

            public Set $plus(Object elem1, Object elem2, Seq elems) {
                return SetLike$class.$plus(this, elem1, elem2, elems);
            }

            public Set $plus$plus(GenTraversableOnce elems) {
                return SetLike$class.$plus$plus(this, elems);
            }

            public Iterator<scala.collection.immutable.Set<A>> subsets(int len) {
                return SetLike$class.subsets(this, len);
            }

            public Iterator<scala.collection.immutable.Set<A>> subsets() {
                return SetLike$class.subsets(this);
            }

            public Subtractable $minus(Object elem1, Object elem2, Seq elems) {
                return Subtractable$class.$minus(this, elem1, elem2, elems);
            }

            public Subtractable $minus$minus(GenTraversableOnce xs) {
                return Subtractable$class.$minus$minus(this, xs);
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

            public <B> Builder<B, scala.collection.immutable.Set<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public <A1, A2> Tuple2<scala.collection.immutable.Set<A1>, scala.collection.immutable.Set<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return GenericTraversableTemplate$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<scala.collection.immutable.Set<A1>, scala.collection.immutable.Set<A2>, scala.collection.immutable.Set<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
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

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<scala.collection.immutable.Set<A>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public Iterator<scala.collection.immutable.Set<A>> tails() {
                return TraversableLike$class.tails(this);
            }

            public Iterator<scala.collection.immutable.Set<A>> inits() {
                return TraversableLike$class.inits(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public FilterMonadic<A, scala.collection.immutable.Set<A>> withFilter(Function1<A, Object> p) {
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

            public scala.collection.immutable.Set<A> self() {
                return this.self;
            }
            {
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                Traversable$class.$init$(this);
                scala.collection.immutable.Traversable$class.$init$(this);
                GenIterable$class.$init$(this);
                IterableLike$class.$init$(this);
                Iterable$class.$init$(this);
                scala.collection.immutable.Iterable$class.$init$(this);
                Function1$class.$init$(this);
                GenSetLike$class.$init$(this);
                GenericSetTemplate$class.$init$(this);
                GenSet$class.$init$(this);
                Subtractable$class.$init$(this);
                SetLike$class.$init$(this);
                Set$class.$init$(this);
                scala.collection.immutable.Set$class.$init$(this);
                Proxy$class.$init$(this);
                TraversableProxyLike$class.$init$(this);
                IterableProxyLike$class.$init$(this);
                SetProxyLike$class.$init$(this);
                SetProxy$class.$init$(this);
                this.self = ((scala.collection.immutable.MapLike)$outer.self()).keySet();
            }
        };
    }

    public static scala.collection.immutable.Map filterKeys(MapProxy $this, Function1 p) {
        return ((scala.collection.immutable.MapLike)$this.self()).filterKeys(p);
    }

    public static scala.collection.immutable.Map mapValues(MapProxy $this, Function1 f) {
        return ((scala.collection.immutable.MapLike)$this.self()).mapValues(f);
    }

    public static void $init$(MapProxy $this) {
    }
}

