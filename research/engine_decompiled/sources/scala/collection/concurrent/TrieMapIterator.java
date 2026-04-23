/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
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
import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.INode;
import scala.collection.concurrent.KVNode;
import scala.collection.concurrent.LNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;
import scala.collection.concurrent.TNode;
import scala.collection.concurrent.TrieMap;
import scala.collection.concurrent.TrieMapIterator$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005ud!B\u0001\u0003\u0001\u0011A!a\u0004+sS\u0016l\u0015\r]%uKJ\fGo\u001c:\u000b\u0005\r!\u0011AC2p]\u000e,(O]3oi*\u0011QAB\u0001\u000bG>dG.Z2uS>t'\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0016\u0007%9\"eE\u0002\u0001\u00159\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g!\ry\u0001CE\u0007\u0002\t%\u0011\u0011\u0003\u0002\u0002\t\u0013R,'/\u0019;peB!1bE\u000b\"\u0013\t!bA\u0001\u0004UkBdWM\r\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007!DA\u0001L\u0007\u0001\t\"a\u0007\u0010\u0011\u0005-a\u0012BA\u000f\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0010\n\u0005\u00012!aA!osB\u0011aC\t\u0003\u0006G\u0001\u0011\rA\u0007\u0002\u0002-\"AQ\u0005\u0001BA\u0002\u0013\u0005a%A\u0003mKZ,G.F\u0001(!\tY\u0001&\u0003\u0002*\r\t\u0019\u0011J\u001c;\t\u0011-\u0002!\u00111A\u0005\u00021\n\u0011\u0002\\3wK2|F%Z9\u0015\u00055\u0002\u0004CA\u0006/\u0013\tycA\u0001\u0003V]&$\bbB\u0019+\u0003\u0003\u0005\raJ\u0001\u0004q\u0012\n\u0004\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0015B\u0014\u0002\r1,g/\u001a7!\u0011!)\u0004A!a\u0001\n\u00131\u0014AA2u+\u00059\u0004\u0003\u0002\u001d:+\u0005j\u0011AA\u0005\u0003u\t\u0011q\u0001\u0016:jK6\u000b\u0007\u000f\u0003\u0005=\u0001\t\u0005\r\u0011\"\u0003>\u0003\u0019\u0019Go\u0018\u0013fcR\u0011QF\u0010\u0005\bcm\n\t\u00111\u00018\u0011!\u0001\u0005A!A!B\u00139\u0014aA2uA!A!\t\u0001B\u0001B\u0003%1)\u0001\u0005nkN$\u0018J\\5u!\tYA)\u0003\u0002F\r\t9!i\\8mK\u0006t\u0007\"B$\u0001\t\u0003A\u0015A\u0002\u001fj]&$h\b\u0006\u0003J\u0015.c\u0005\u0003\u0002\u001d\u0001+\u0005BQ!\n$A\u0002\u001dBQ!\u000e$A\u0002]BqA\u0011$\u0011\u0002\u0003\u00071\tC\u0004O\u0001\t\u0007I\u0011B(\u0002\u000bM$\u0018mY6\u0016\u0003A\u00032aC)T\u0013\t\u0011fAA\u0003BeJ\f\u0017\u0010E\u0002\f#R\u0003\"\u0001O+\n\u0005Y\u0013!!\u0003\"bg&\u001cgj\u001c3f\u0011\u0019A\u0006\u0001)A\u0005!\u000611\u000f^1dW\u0002BqA\u0017\u0001C\u0002\u0013%1,\u0001\u0005ti\u0006\u001c7\u000e]8t+\u0005a\u0006cA\u0006RO!1a\f\u0001Q\u0001\nq\u000b\u0011b\u001d;bG.\u0004xn\u001d\u0011\t\u000f\u0001\u0004\u0001\u0019!C\u0005M\u0005)A-\u001a9uQ\"9!\r\u0001a\u0001\n\u0013\u0019\u0017!\u00033faRDw\fJ3r)\tiC\rC\u00042C\u0006\u0005\t\u0019A\u0014\t\r\u0019\u0004\u0001\u0015)\u0003(\u0003\u0019!W\r\u001d;iA!9\u0001\u000e\u0001a\u0001\n\u0013I\u0017aB:vE&$XM]\u000b\u0002\u001d!91\u000e\u0001a\u0001\n\u0013a\u0017aC:vE&$XM]0%KF$\"!L7\t\u000fER\u0017\u0011!a\u0001\u001d!1q\u000e\u0001Q!\n9\t\u0001b];cSR,'\u000f\t\u0005\bc\u0002\u0001\r\u0011\"\u0003s\u0003\u001d\u0019WO\u001d:f]R,\u0012a\u001d\t\u0005qQ,\u0012%\u0003\u0002v\u0005\t11J\u0016(pI\u0016Dqa\u001e\u0001A\u0002\u0013%\u00010A\u0006dkJ\u0014XM\u001c;`I\u0015\fHCA\u0017z\u0011\u001d\td/!AA\u0002MDaa\u001f\u0001!B\u0013\u0019\u0018\u0001C2veJ,g\u000e\u001e\u0011\t\u000bu\u0004A\u0011\u0001@\u0002\u000f!\f7OT3yiV\t1\tC\u0004\u0002\u0002\u0001!\t!a\u0001\u0002\t9,\u0007\u0010\u001e\u000b\u0002%!9\u0011q\u0001\u0001\u0005\n\u0005%\u0011A\u0002:fC\u0012Lg\u000eF\u0002.\u0003\u0017A\u0001\"!\u0004\u0002\u0006\u0001\u0007\u0011qB\u0001\u0003S:\u0004R\u0001OA\t+\u0005J1!a\u0005\u0003\u0005\u0015Iej\u001c3f\u0011\u001d\t9\u0002\u0001C\u0005\u00033\tAb\u00195fG.\u001cVOY5uKJ$\u0012!\f\u0005\b\u0003;\u0001A\u0011BA\r\u0003)Ig.\u001b;jC2L'0\u001a\u0005\b\u0003C\u0001A\u0011AA\r\u0003\u001d\tGM^1oG\u0016Dq!!\n\u0001\t#\t9#A\u0006oK^LE/\u001a:bi>\u0014HcB%\u0002*\u00055\u0012\u0011\u0007\u0005\b\u0003W\t\u0019\u00031\u0001(\u0003\u0011yF.\u001a<\t\u000f\u0005=\u00121\u0005a\u0001o\u0005\u0019ql\u0019;\t\u000f\u0005M\u00121\u0005a\u0001\u0007\u0006Iq,\\;ti&s\u0017\u000e\u001e\u0005\b\u0003o\u0001A\u0011CA\u001d\u0003\u0015!W\u000f\u001d+p)\ri\u00131\b\u0005\b\u0003{\t)\u00041\u0001J\u0003\tIG\u000fC\u0004\u0002B\u0001!\t\"a\u0011\u0002\u0013M,(\rZ5wS\u0012,GCAA#!\u0011y\u0011q\t\b\n\u0007\u0005%CAA\u0002TKFDq!!\u0014\u0001\t\u0003\tI\"\u0001\u0006qe&tG\u000fR3ck\u001e<!\"!\u0015\u0003\u0003\u0003E\t\u0001BA*\u0003=!&/[3NCBLE/\u001a:bi>\u0014\bc\u0001\u001d\u0002V\u0019I\u0011AAA\u0001\u0012\u0003!\u0011qK\n\u0004\u0003+R\u0001bB$\u0002V\u0011\u0005\u00111\f\u000b\u0003\u0003'B!\"a\u0018\u0002VE\u0005I\u0011AA1\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU1\u00111MA=\u0003w*\"!!\u001a+\u0007\r\u000b9g\u000b\u0002\u0002jA!\u00111NA;\u001b\t\tiG\u0003\u0003\u0002p\u0005E\u0014!C;oG\",7m[3e\u0015\r\t\u0019HB\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA<\u0003[\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019A\u0012Q\fb\u00015\u001111%!\u0018C\u0002i\u0001")
public class TrieMapIterator<K, V>
implements Iterator<Tuple2<K, V>> {
    private int level;
    private TrieMap<K, V> ct;
    private final BasicNode[][] stack;
    private final int[] stackpos;
    private int depth;
    private Iterator<Tuple2<K, V>> subiter;
    private KVNode<K, V> current;

    public static <K, V> boolean $lessinit$greater$default$3() {
        return TrieMapIterator$.MODULE$.$lessinit$greater$default$3();
    }

    @Override
    public Iterator<Tuple2<K, V>> seq() {
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
    public Iterator<Tuple2<K, V>> take(int n) {
        return Iterator$class.take(this, n);
    }

    @Override
    public Iterator<Tuple2<K, V>> drop(int n) {
        return Iterator$class.drop(this, n);
    }

    @Override
    public Iterator<Tuple2<K, V>> slice(int from2, int until2) {
        return Iterator$class.slice(this, from2, until2);
    }

    @Override
    public <B> Iterator<B> map(Function1<Tuple2<K, V>, B> f) {
        return Iterator$class.map(this, f);
    }

    @Override
    public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
        return Iterator$class.$plus$plus(this, that);
    }

    @Override
    public <B> Iterator<B> flatMap(Function1<Tuple2<K, V>, GenTraversableOnce<B>> f) {
        return Iterator$class.flatMap(this, f);
    }

    @Override
    public Iterator<Tuple2<K, V>> filter(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.filter(this, p);
    }

    @Override
    public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<K, V>, B, Object> p) {
        return Iterator$class.corresponds(this, that, p);
    }

    @Override
    public Iterator<Tuple2<K, V>> withFilter(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.withFilter(this, p);
    }

    @Override
    public Iterator<Tuple2<K, V>> filterNot(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.filterNot(this, p);
    }

    @Override
    public <B> Iterator<B> collect(PartialFunction<Tuple2<K, V>, B> pf) {
        return Iterator$class.collect(this, pf);
    }

    @Override
    public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
        return Iterator$class.scanLeft(this, z, op);
    }

    @Override
    public <B> Iterator<B> scanRight(B z, Function2<Tuple2<K, V>, B, B> op) {
        return Iterator$class.scanRight(this, z, op);
    }

    @Override
    public Iterator<Tuple2<K, V>> takeWhile(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.takeWhile(this, p);
    }

    @Override
    public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> partition(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.partition(this, p);
    }

    @Override
    public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> span(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.span(this, p);
    }

    @Override
    public Iterator<Tuple2<K, V>> dropWhile(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.dropWhile(this, p);
    }

    @Override
    public <B> Iterator<Tuple2<Tuple2<K, V>, B>> zip(Iterator<B> that) {
        return Iterator$class.zip(this, that);
    }

    @Override
    public <A1> Iterator<A1> padTo(int len, A1 elem) {
        return Iterator$class.padTo(this, len, elem);
    }

    @Override
    public Iterator<Tuple2<Tuple2<K, V>, Object>> zipWithIndex() {
        return Iterator$class.zipWithIndex(this);
    }

    @Override
    public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
        return Iterator$class.zipAll(this, that, thisElem, thatElem);
    }

    @Override
    public <U> void foreach(Function1<Tuple2<K, V>, U> f) {
        Iterator$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.exists(this, p);
    }

    @Override
    public boolean contains(Object elem) {
        return Iterator$class.contains(this, elem);
    }

    @Override
    public Option<Tuple2<K, V>> find(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.find(this, p);
    }

    @Override
    public int indexWhere(Function1<Tuple2<K, V>, Object> p) {
        return Iterator$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return Iterator$class.indexOf(this, elem);
    }

    @Override
    public BufferedIterator<Tuple2<K, V>> buffered() {
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
    public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> duplicate() {
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
    public Traversable<Tuple2<K, V>> toTraversable() {
        return Iterator$class.toTraversable(this);
    }

    @Override
    public Iterator<Tuple2<K, V>> toIterator() {
        return Iterator$class.toIterator(this);
    }

    @Override
    public Stream<Tuple2<K, V>> toStream() {
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
    public List<Tuple2<K, V>> reversed() {
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
    public int count(Function1<Tuple2<K, V>, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<Tuple2<K, V>, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<Tuple2<K, V>, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<Tuple2<K, V>, B, B> op) {
        return (B)TraversableOnce$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, Tuple2<K, V>, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<Tuple2<K, V>, B, B> op) {
        return (B)TraversableOnce$class.reduceRight(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<K, V>, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<Tuple2<K, V>, B, B> op) {
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
    public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<K, V>, B> seqop, Function2<B, B, B> combop) {
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
    public Object min(Ordering cmp) {
        return TraversableOnce$class.min(this, cmp);
    }

    @Override
    public Object max(Ordering cmp) {
        return TraversableOnce$class.max(this, cmp);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return TraversableOnce$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
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
    public List<Tuple2<K, V>> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public Iterable<Tuple2<K, V>> toIterable() {
        return TraversableOnce$class.toIterable(this);
    }

    @Override
    public Seq<Tuple2<K, V>> toSeq() {
        return TraversableOnce$class.toSeq(this);
    }

    @Override
    public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
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
    public Vector<Tuple2<K, V>> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<K, V>, Col> cbf) {
        return (Col)TraversableOnce$class.to(this, cbf);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<T, U>> ev) {
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

    public int level() {
        return this.level;
    }

    public void level_$eq(int x$1) {
        this.level = x$1;
    }

    private TrieMap<K, V> ct() {
        return this.ct;
    }

    private void ct_$eq(TrieMap<K, V> x$1) {
        this.ct = x$1;
    }

    private BasicNode[][] stack() {
        return this.stack;
    }

    private int[] stackpos() {
        return this.stackpos;
    }

    private int depth() {
        return this.depth;
    }

    private void depth_$eq(int x$1) {
        this.depth = x$1;
    }

    private Iterator<Tuple2<K, V>> subiter() {
        return this.subiter;
    }

    private void subiter_$eq(Iterator<Tuple2<K, V>> x$1) {
        this.subiter = x$1;
    }

    private KVNode<K, V> current() {
        return this.current;
    }

    private void current_$eq(KVNode<K, V> x$1) {
        this.current = x$1;
    }

    @Override
    public boolean hasNext() {
        return this.current() != null || this.subiter() != null;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Tuple2<K, V> next() {
        Tuple2 tuple2;
        if (this.hasNext()) {
            void var1_1;
            Tuple2<K, V> r;
            if (this.subiter() != null) {
                r = this.subiter().next();
                this.checkSubiter();
            } else {
                r = this.current().kvPair();
                this.advance();
            }
            tuple2 = var1_1;
        } else {
            tuple2 = (Tuple2)((Object)Iterator$.MODULE$.empty().next());
        }
        return tuple2;
    }

    private void readin(INode<K, V> in) {
        MainNode<K, V> mainNode;
        block6: {
            block3: {
                block5: {
                    block4: {
                        block2: {
                            mainNode = in.gcasRead(this.ct());
                            if (!(mainNode instanceof CNode)) break block2;
                            CNode cNode = (CNode)mainNode;
                            this.depth_$eq(this.depth() + 1);
                            this.stack()[this.depth()] = cNode.array();
                            this.stackpos()[this.depth()] = -1;
                            this.advance();
                            break block3;
                        }
                        if (!(mainNode instanceof TNode)) break block4;
                        TNode tNode = (TNode)mainNode;
                        this.current_$eq(tNode);
                        break block3;
                    }
                    if (!(mainNode instanceof LNode)) break block5;
                    LNode lNode = (LNode)mainNode;
                    this.subiter_$eq(lNode.listmap().iterator());
                    this.checkSubiter();
                    break block3;
                }
                if (mainNode != null) break block6;
                this.current_$eq(null);
            }
            return;
        }
        throw new MatchError(mainNode);
    }

    private void checkSubiter() {
        if (!this.subiter().hasNext()) {
            this.subiter_$eq(null);
            this.advance();
        }
    }

    private void initialize() {
        Predef$.MODULE$.assert(this.ct().isReadOnly());
        TrieMap<K, V> qual$1 = this.ct();
        boolean x$6 = qual$1.RDCSS_READ_ROOT$default$1();
        INode<K, V> r = qual$1.RDCSS_READ_ROOT(x$6);
        this.readin(r);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void advance() {
        if (this.depth() >= 0) {
            int npos = this.stackpos()[this.depth()] + 1;
            if (npos < this.stack()[this.depth()].length) {
                this.stackpos()[this.depth()] = npos;
                BasicNode basicNode = this.stack()[this.depth()][npos];
                if (basicNode instanceof SNode) {
                    SNode sNode = (SNode)basicNode;
                    this.current_$eq(sNode);
                    return;
                } else {
                    if (!(basicNode instanceof INode)) throw new MatchError(basicNode);
                    INode iNode = (INode)basicNode;
                    this.readin(iNode);
                }
                return;
            } else {
                this.depth_$eq(this.depth() - 1);
                this.advance();
            }
            return;
        } else {
            this.current_$eq(null);
        }
    }

    public TrieMapIterator<K, V> newIterator(int _lev, TrieMap<K, V> _ct, boolean _mustInit) {
        return new TrieMapIterator<K, V>(_lev, _ct, _mustInit);
    }

    public void dupTo(TrieMapIterator<K, V> it) {
        it.level_$eq(this.level());
        super.ct_$eq(this.ct());
        super.depth_$eq(this.depth());
        super.current_$eq(this.current());
        Array$.MODULE$.copy(this.stack(), 0, super.stack(), 0, 7);
        Array$.MODULE$.copy(this.stackpos(), 0, super.stackpos(), 0, 7);
        if (this.subiter() == null) {
            super.subiter_$eq(null);
        } else {
            List lst = this.subiter().toList();
            this.subiter_$eq(lst.iterator());
            super.subiter_$eq(lst.iterator());
        }
    }

    public Seq<Iterator<Tuple2<K, V>>> subdivide() {
        Seq seq;
        if (this.subiter() != null) {
            TrieMapIterator<K, V> it = this.newIterator(this.level() + 1, this.ct(), false);
            super.depth_$eq(-1);
            super.subiter_$eq(this.subiter());
            super.current_$eq(null);
            this.subiter_$eq(null);
            this.advance();
            this.level_$eq(this.level() + 1);
            seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[]{it, this}));
        } else if (this.depth() == -1) {
            this.level_$eq(this.level() + 1);
            seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[]{this}));
        } else {
            for (int d = 0; d <= this.depth(); ++d) {
                int rem = this.stack()[d].length - 1 - this.stackpos()[d];
                if (rem <= 0) continue;
                Tuple2 tuple2 = Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])this.stack()[d]).drop(this.stackpos()[d] + 1)).splitAt(rem / 2);
                if (tuple2 != null) {
                    Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                    BasicNode[] arr1 = (BasicNode[])tuple22._1();
                    BasicNode[] arr2 = (BasicNode[])tuple22._2();
                    this.stack()[d] = arr1;
                    this.stackpos()[d] = -1;
                    TrieMapIterator<K, V> it = this.newIterator(this.level() + 1, this.ct(), false);
                    super.stack()[0] = arr2;
                    super.stackpos()[0] = -1;
                    super.depth_$eq(0);
                    it.advance();
                    this.level_$eq(this.level() + 1);
                    return (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[]{this, it}));
                }
                throw new MatchError(tuple2);
            }
            this.level_$eq(this.level() + 1);
            seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[]{this}));
        }
        return seq;
    }

    public void printDebug() {
        Predef$.MODULE$.println("ctrie iterator");
        Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps(this.stackpos()).mkString(","));
        Predef$.MODULE$.println(new StringBuilder().append((Object)"depth: ").append(BoxesRunTime.boxToInteger(this.depth())).toString());
        Predef$.MODULE$.println(new StringBuilder().append((Object)"curr.: ").append(this.current()).toString());
        Predef$.MODULE$.println(Predef$.MODULE$.refArrayOps((Object[])this.stack()).mkString("\n"));
    }

    public TrieMapIterator(int level, TrieMap<K, V> ct, boolean mustInit) {
        this.level = level;
        this.ct = ct;
        TraversableOnce$class.$init$(this);
        Iterator$class.$init$(this);
        this.stack = new BasicNode[7][];
        this.stackpos = new int[7];
        this.depth = -1;
        this.subiter = null;
        this.current = null;
        if (mustInit) {
            this.initialize();
        }
    }
}

