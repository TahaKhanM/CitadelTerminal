/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Proxy$class;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSet;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableProxyLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.SetLike;
import scala.collection.SetProxyLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableProxyLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.SetProxy;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;

public abstract class SetProxy$class {
    public static SetProxy repr(SetProxy $this) {
        return $this;
    }

    private static SetProxy newProxy(SetProxy $this, scala.collection.immutable.Set newSelf) {
        return new SetProxy<Object>($this, newSelf){
            private final scala.collection.immutable.Set<Object> self;

            public SetProxy<Object> repr() {
                return SetProxy$class.repr(this);
            }

            public SetProxy<Object> empty() {
                return SetProxy$class.empty(this);
            }

            public SetProxy<Object> $plus(Object elem) {
                return SetProxy$class.$plus(this, elem);
            }

            public SetProxy<Object> $minus(Object elem) {
                return SetProxy$class.$minus(this, elem);
            }

            public boolean contains(Object elem) {
                return SetProxyLike$class.contains(this, elem);
            }

            public boolean isEmpty() {
                return SetProxyLike$class.isEmpty(this);
            }

            public boolean apply(Object elem) {
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

            public boolean subsetOf(GenSet<Object> that) {
                return SetProxyLike$class.subsetOf(this, that);
            }

            public Iterator<Object> iterator() {
                return IterableProxyLike$class.iterator(this);
            }

            public Iterator<scala.collection.immutable.Set<Object>> grouped(int size2) {
                return IterableProxyLike$class.grouped(this, size2);
            }

            public Iterator<scala.collection.immutable.Set<Object>> sliding(int size2) {
                return IterableProxyLike$class.sliding(this, size2);
            }

            public Iterator<scala.collection.immutable.Set<Object>> sliding(int size2, int step) {
                return IterableProxyLike$class.sliding(this, size2, step);
            }

            public Iterable takeRight(int n) {
                return IterableProxyLike$class.takeRight(this, n);
            }

            public Iterable dropRight(int n) {
                return IterableProxyLike$class.dropRight(this, n);
            }

            public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<scala.collection.immutable.Set<Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zip(this, that, bf);
            }

            public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<scala.collection.immutable.Set<Object>, Tuple2<A1, B>, That> bf) {
                return (That)IterableProxyLike$class.zipAll(this, that, thisElem, thatElem, bf);
            }

            public <A1, That> That zipWithIndex(CanBuildFrom<scala.collection.immutable.Set<Object>, Tuple2<A1, Object>, That> bf) {
                return (That)IterableProxyLike$class.zipWithIndex(this, bf);
            }

            public <B> boolean sameElements(GenIterable<B> that) {
                return IterableProxyLike$class.sameElements(this, that);
            }

            public Object view() {
                return IterableProxyLike$class.view(this);
            }

            public IterableView<Object, scala.collection.immutable.Set<Object>> view(int from2, int until2) {
                return IterableProxyLike$class.view(this, from2, until2);
            }

            public <U> void foreach(Function1<Object, U> f) {
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

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<Object, B> f, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.map(this, f, bf);
            }

            public <B, That> That flatMap(Function1<Object, GenTraversableOnce<B>> f, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.flatMap(this, f, bf);
            }

            public Traversable filter(Function1 p) {
                return TraversableProxyLike$class.filter(this, p);
            }

            public Traversable filterNot(Function1 p) {
                return TraversableProxyLike$class.filterNot(this, p);
            }

            public <B, That> That collect(PartialFunction<Object, B> pf, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.collect(this, pf, bf);
            }

            public Tuple2<scala.collection.immutable.Set<Object>, scala.collection.immutable.Set<Object>> partition(Function1<Object, Object> p) {
                return TraversableProxyLike$class.partition(this, p);
            }

            public <K> Map<K, scala.collection.immutable.Set<Object>> groupBy(Function1<Object, K> f) {
                return TraversableProxyLike$class.groupBy(this, f);
            }

            public boolean forall(Function1<Object, Object> p) {
                return TraversableProxyLike$class.forall(this, p);
            }

            public boolean exists(Function1<Object, Object> p) {
                return TraversableProxyLike$class.exists(this, p);
            }

            public int count(Function1<Object, Object> p) {
                return TraversableProxyLike$class.count(this, p);
            }

            public Option<Object> find(Function1<Object, Object> p) {
                return TraversableProxyLike$class.find(this, p);
            }

            public <B> B foldLeft(B z, Function2<B, Object, B> op) {
                return (B)TraversableProxyLike$class.foldLeft(this, z, op);
            }

            public <B> B $div$colon(B z, Function2<B, Object, B> op) {
                return (B)TraversableProxyLike$class.$div$colon(this, z, op);
            }

            public <B> B foldRight(B z, Function2<Object, B, B> op) {
                return (B)TraversableProxyLike$class.foldRight(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<Object, B, B> op) {
                return (B)TraversableProxyLike$class.$colon$bslash(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, Object, B> op) {
                return (B)TraversableProxyLike$class.reduceLeft(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, Object, B> op) {
                return TraversableProxyLike$class.reduceLeftOption(this, op);
            }

            public <B> B reduceRight(Function2<Object, B, B> op) {
                return (B)TraversableProxyLike$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<Object, B, B> op) {
                return TraversableProxyLike$class.reduceRightOption(this, op);
            }

            public <B, That> That scanLeft(B z, Function2<B, Object, B> op, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<Object, B, B> op, CanBuildFrom<scala.collection.immutable.Set<Object>, B, That> bf) {
                return (That)TraversableProxyLike$class.scanRight(this, z, op, bf);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableProxyLike$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableProxyLike$class.product(this, num);
            }

            public <B> Object min(Ordering<B> cmp) {
                return TraversableProxyLike$class.min(this, cmp);
            }

            public <B> Object max(Ordering<B> cmp) {
                return TraversableProxyLike$class.max(this, cmp);
            }

            public Object head() {
                return TraversableProxyLike$class.head(this);
            }

            public Option<Object> headOption() {
                return TraversableProxyLike$class.headOption(this);
            }

            public Traversable tail() {
                return TraversableProxyLike$class.tail(this);
            }

            public Object last() {
                return TraversableProxyLike$class.last(this);
            }

            public Option<Object> lastOption() {
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

            public Tuple2<scala.collection.immutable.Set<Object>, scala.collection.immutable.Set<Object>> span(Function1<Object, Object> p) {
                return TraversableProxyLike$class.span(this, p);
            }

            public Tuple2<scala.collection.immutable.Set<Object>, scala.collection.immutable.Set<Object>> splitAt(int n) {
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

            public List<Object> toList() {
                return TraversableProxyLike$class.toList(this);
            }

            public Iterable<Object> toIterable() {
                return TraversableProxyLike$class.toIterable(this);
            }

            public Seq<Object> toSeq() {
                return TraversableProxyLike$class.toSeq(this);
            }

            public IndexedSeq<Object> toIndexedSeq() {
                return TraversableProxyLike$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableProxyLike$class.toBuffer(this);
            }

            public Stream<Object> toStream() {
                return TraversableProxyLike$class.toStream(this);
            }

            public <B> scala.collection.immutable.Set<B> toSet() {
                return TraversableProxyLike$class.toSet(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Object, Tuple2<T, U>> ev) {
                return TraversableProxyLike$class.toMap(this, ev);
            }

            public Traversable<Object> toTraversable() {
                return TraversableProxyLike$class.toTraversable(this);
            }

            public Iterator<Object> toIterator() {
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
                return Set$class.companion(this);
            }

            public scala.collection.immutable.Set<Object> seq() {
                return Set$class.seq(this);
            }

            public Combiner<Object, ParSet<Object>> parCombiner() {
                return Set$class.parCombiner(this);
            }

            public scala.collection.immutable.Set<Object> self() {
                return this.self;
            }
            {
                Traversable$class.$init$(this);
                Iterable$class.$init$(this);
                Set$class.$init$(this);
                Proxy$class.$init$(this);
                TraversableProxyLike$class.$init$(this);
                IterableProxyLike$class.$init$(this);
                SetProxyLike$class.$init$(this);
                SetProxy$class.$init$(this);
                this.self = newSelf$1;
            }
        };
    }

    public static SetProxy empty(SetProxy $this) {
        return SetProxy$class.newProxy($this, (scala.collection.immutable.Set)((GenericSetTemplate)$this.self()).empty());
    }

    public static SetProxy $plus(SetProxy $this, Object elem) {
        return SetProxy$class.newProxy($this, (scala.collection.immutable.Set)((SetLike)$this.self()).$plus(elem));
    }

    public static SetProxy $minus(SetProxy $this, Object elem) {
        return SetProxy$class.newProxy($this, (scala.collection.immutable.Set)((SetLike)$this.self()).$minus(elem));
    }

    public static void $init$(SetProxy $this) {
    }
}

