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
import scala.Tuple2;
import scala.collection.BufferedIterator;
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
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Splitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.Nothing$;

public final class Splitter$ {
    public static final Splitter$ MODULE$;

    static {
        new Splitter$();
    }

    public <T> Splitter<T> empty() {
        return new Splitter<T>(){

            public Iterator<T> seq() {
                return Iterator$class.seq(this);
            }

            public boolean isEmpty() {
                return Iterator$class.isEmpty(this);
            }

            public boolean isTraversableAgain() {
                return Iterator$class.isTraversableAgain(this);
            }

            public boolean hasDefiniteSize() {
                return Iterator$class.hasDefiniteSize(this);
            }

            public Iterator<T> take(int n) {
                return Iterator$class.take(this, n);
            }

            public Iterator<T> drop(int n) {
                return Iterator$class.drop(this, n);
            }

            public Iterator<T> slice(int from2, int until2) {
                return Iterator$class.slice(this, from2, until2);
            }

            public <B> Iterator<B> map(Function1<T, B> f) {
                return Iterator$class.map(this, f);
            }

            public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
                return Iterator$class.$plus$plus(this, that);
            }

            public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
                return Iterator$class.flatMap(this, f);
            }

            public Iterator<T> filter(Function1<T, Object> p) {
                return Iterator$class.filter(this, p);
            }

            public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
                return Iterator$class.corresponds(this, that, p);
            }

            public Iterator<T> withFilter(Function1<T, Object> p) {
                return Iterator$class.withFilter(this, p);
            }

            public Iterator<T> filterNot(Function1<T, Object> p) {
                return Iterator$class.filterNot(this, p);
            }

            public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
                return Iterator$class.collect(this, pf);
            }

            public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
                return Iterator$class.scanLeft(this, z, op);
            }

            public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
                return Iterator$class.scanRight(this, z, op);
            }

            public Iterator<T> takeWhile(Function1<T, Object> p) {
                return Iterator$class.takeWhile(this, p);
            }

            public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
                return Iterator$class.partition(this, p);
            }

            public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
                return Iterator$class.span(this, p);
            }

            public Iterator<T> dropWhile(Function1<T, Object> p) {
                return Iterator$class.dropWhile(this, p);
            }

            public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
                return Iterator$class.zip(this, that);
            }

            public <A1> Iterator<A1> padTo(int len, A1 elem) {
                return Iterator$class.padTo(this, len, elem);
            }

            public Iterator<Tuple2<T, Object>> zipWithIndex() {
                return Iterator$class.zipWithIndex(this);
            }

            public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
                return Iterator$class.zipAll(this, that, thisElem, thatElem);
            }

            public <U> void foreach(Function1<T, U> f) {
                Iterator$class.foreach(this, f);
            }

            public boolean forall(Function1<T, Object> p) {
                return Iterator$class.forall(this, p);
            }

            public boolean exists(Function1<T, Object> p) {
                return Iterator$class.exists(this, p);
            }

            public boolean contains(Object elem) {
                return Iterator$class.contains(this, elem);
            }

            public Option<T> find(Function1<T, Object> p) {
                return Iterator$class.find(this, p);
            }

            public int indexWhere(Function1<T, Object> p) {
                return Iterator$class.indexWhere(this, p);
            }

            public <B> int indexOf(B elem) {
                return Iterator$class.indexOf(this, elem);
            }

            public BufferedIterator<T> buffered() {
                return Iterator$class.buffered(this);
            }

            public <B> Iterator.GroupedIterator<B> grouped(int size2) {
                return Iterator$class.grouped(this, size2);
            }

            public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
                return Iterator$class.sliding(this, size2, step);
            }

            public int length() {
                return Iterator$class.length(this);
            }

            public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
                return Iterator$class.duplicate(this);
            }

            public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
                return Iterator$class.patch(this, from2, patchElems, replaced);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                Iterator$class.copyToArray(this, xs, start, len);
            }

            public boolean sameElements(Iterator<?> that) {
                return Iterator$class.sameElements(this, that);
            }

            public Traversable<T> toTraversable() {
                return Iterator$class.toTraversable(this);
            }

            public Iterator<T> toIterator() {
                return Iterator$class.toIterator(this);
            }

            public Stream<T> toStream() {
                return Iterator$class.toStream(this);
            }

            public String toString() {
                return Iterator$class.toString(this);
            }

            public <B> int sliding$default$2() {
                return Iterator$class.sliding$default$2(this);
            }

            public List<T> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<T, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, T, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, T, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<T, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, T, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<T, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
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

            public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> T min(Ordering<B> cmp) {
                return (T)TraversableOnce$class.min(this, cmp);
            }

            public <B> T max(Ordering<B> cmp) {
                return (T)TraversableOnce$class.max(this, cmp);
            }

            public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
                return (T)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
                return (T)TraversableOnce$class.minBy(this, f, cmp);
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

            public List<T> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<T> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<T> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<T> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<T> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
                return (Col)TraversableOnce$class.to(this, cbf);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public String mkString(String start, String sep, String end) {
                return TraversableOnce$class.mkString(this, start, sep, end);
            }

            public String mkString(String sep) {
                return TraversableOnce$class.mkString(this, sep);
            }

            public String mkString() {
                return TraversableOnce$class.mkString(this);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return TraversableOnce$class.addString(this, b, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public boolean hasNext() {
                return false;
            }

            public Nothing$ next() {
                return Iterator$.MODULE$.empty().next();
            }

            public Seq<Object> split() {
                return (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Splitter[]{this}));
            }
            {
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
            }
        };
    }

    private Splitter$() {
        MODULE$ = this;
    }
}

