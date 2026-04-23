/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
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
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayStack;
import scala.collection.mutable.ArrayStack$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Leaf$;
import scala.collection.mutable.Node;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\t3A!\u0001\u0002\u0005\u0013\tY\u0011I\u0016'Ji\u0016\u0014\u0018\r^8s\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005))2c\u0001\u0001\f\u001fA\u0011A\"D\u0007\u0002\r%\u0011aB\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0007A\t2#D\u0001\u0005\u0013\t\u0011BA\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\t!R\u0003\u0004\u0001\u0005\u000bY\u0001!\u0019A\f\u0003\u0003\u0005\u000b\"\u0001G\u000e\u0011\u00051I\u0012B\u0001\u000e\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0004\u000f\n\u0005u1!aA!os\"Aq\u0004\u0001B\u0001B\u0003%\u0001%\u0001\u0003s_>$\bcA\u0011#'5\t!!\u0003\u0002$\u0005\t!aj\u001c3f\u0011\u0015)\u0003\u0001\"\u0001'\u0003\u0019a\u0014N\\5u}Q\u0011q\u0005\u000b\t\u0004C\u0001\u0019\u0002\"B\u0010%\u0001\u0004\u0001\u0003b\u0002\u0016\u0001\u0005\u0004%\taK\u0001\u0006gR\f7m[\u000b\u0002YA\u0019\u0011%\f\u0011\n\u00059\u0012!AC!se\u0006L8\u000b^1dW\"1\u0001\u0007\u0001Q\u0001\n1\naa\u001d;bG.\u0004\u0003\"\u0002\u001a\u0001\t\u0013\u0019\u0014\u0001\u00033jm\u0016dUM\u001a;\u0015\u0003Q\u0002\"\u0001D\u001b\n\u0005Y2!\u0001B+oSRDQ\u0001\u000f\u0001\u0005\nM\n1\"\u001a8hC\u001e,'+[4ii\")!\b\u0001C!w\u00059\u0001.Y:OKb$X#\u0001\u001f\u0011\u00051i\u0014B\u0001 \u0007\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u0011\u0001\u0005B\u0005\u000bAA\\3yiR\t1\u0003")
public class AVLIterator<A>
implements Iterator<A> {
    private final ArrayStack<Node<A>> stack;

    @Override
    public Iterator<A> seq() {
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
    public Iterator<A> take(int n) {
        return Iterator$class.take(this, n);
    }

    @Override
    public Iterator<A> drop(int n) {
        return Iterator$class.drop(this, n);
    }

    @Override
    public Iterator<A> slice(int from2, int until2) {
        return Iterator$class.slice(this, from2, until2);
    }

    @Override
    public <B> Iterator<B> map(Function1<A, B> f) {
        return Iterator$class.map(this, f);
    }

    @Override
    public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
        return Iterator$class.$plus$plus(this, that);
    }

    @Override
    public <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> f) {
        return Iterator$class.flatMap(this, f);
    }

    @Override
    public Iterator<A> filter(Function1<A, Object> p) {
        return Iterator$class.filter(this, p);
    }

    @Override
    public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<A, B, Object> p) {
        return Iterator$class.corresponds(this, that, p);
    }

    @Override
    public Iterator<A> withFilter(Function1<A, Object> p) {
        return Iterator$class.withFilter(this, p);
    }

    @Override
    public Iterator<A> filterNot(Function1<A, Object> p) {
        return Iterator$class.filterNot(this, p);
    }

    @Override
    public <B> Iterator<B> collect(PartialFunction<A, B> pf) {
        return Iterator$class.collect(this, pf);
    }

    @Override
    public <B> Iterator<B> scanLeft(B z, Function2<B, A, B> op) {
        return Iterator$class.scanLeft(this, z, op);
    }

    @Override
    public <B> Iterator<B> scanRight(B z, Function2<A, B, B> op) {
        return Iterator$class.scanRight(this, z, op);
    }

    @Override
    public Iterator<A> takeWhile(Function1<A, Object> p) {
        return Iterator$class.takeWhile(this, p);
    }

    @Override
    public Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> p) {
        return Iterator$class.partition(this, p);
    }

    @Override
    public Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> p) {
        return Iterator$class.span(this, p);
    }

    @Override
    public Iterator<A> dropWhile(Function1<A, Object> p) {
        return Iterator$class.dropWhile(this, p);
    }

    @Override
    public <B> Iterator<Tuple2<A, B>> zip(Iterator<B> that) {
        return Iterator$class.zip(this, that);
    }

    @Override
    public <A1> Iterator<A1> padTo(int len, A1 elem) {
        return Iterator$class.padTo(this, len, elem);
    }

    @Override
    public Iterator<Tuple2<A, Object>> zipWithIndex() {
        return Iterator$class.zipWithIndex(this);
    }

    @Override
    public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
        return Iterator$class.zipAll(this, that, thisElem, thatElem);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        Iterator$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return Iterator$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return Iterator$class.exists(this, p);
    }

    @Override
    public boolean contains(Object elem) {
        return Iterator$class.contains(this, elem);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return Iterator$class.find(this, p);
    }

    @Override
    public int indexWhere(Function1<A, Object> p) {
        return Iterator$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return Iterator$class.indexOf(this, elem);
    }

    @Override
    public BufferedIterator<A> buffered() {
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
    public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
        return Iterator$class.duplicate(this);
    }

    @Override
    public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
        return Iterator$class.patch(this, from2, patchElems, replaced);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        Iterator$class.copyToArray(this, xs, start, len);
    }

    @Override
    public boolean sameElements(Iterator<?> that) {
        return Iterator$class.sameElements(this, that);
    }

    @Override
    public Traversable<A> toTraversable() {
        return Iterator$class.toTraversable(this);
    }

    @Override
    public Iterator<A> toIterator() {
        return Iterator$class.toIterator(this);
    }

    @Override
    public Stream<A> toStream() {
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
    public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
        return (Col)TraversableOnce$class.to(this, cbf);
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

    public ArrayStack<Node<A>> stack() {
        return this.stack;
    }

    private void diveLeft() {
        while (true) {
            if (Leaf$.MODULE$.equals(((Node)this.stack().head()).left())) {
                return;
            }
            Node left = (Node)((Node)this.stack().head()).left();
            this.stack().push(left);
        }
    }

    private void engageRight() {
        if (Leaf$.MODULE$.equals(((Node)this.stack().head()).right())) {
            this.stack().pop();
        } else {
            Node right = (Node)((Node)this.stack().head()).right();
            this.stack().pop();
            this.stack().push(right);
            this.diveLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !this.stack().isEmpty();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public A next() {
        void var1_1;
        if (this.stack().isEmpty()) {
            throw new NoSuchElementException();
        }
        Object result2 = ((Node)this.stack().head()).data();
        this.engageRight();
        return var1_1;
    }

    public AVLIterator(Node<A> root) {
        TraversableOnce$class.$init$(this);
        Iterator$class.$init$(this);
        this.stack = ArrayStack$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Node[]{root}), ClassTag$.MODULE$.apply(Node.class));
        this.diveLeft();
    }
}

