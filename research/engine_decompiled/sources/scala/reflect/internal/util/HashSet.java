/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

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
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.Clearable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005s!B\u0001\u0003\u0011\u0003Y\u0011a\u0002%bg\"\u001cV\r\u001e\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001\u0001\t\u0003\u00195i\u0011A\u0001\u0004\u0006\u001d\tA\ta\u0004\u0002\b\u0011\u0006\u001c\bnU3u'\ti\u0001\u0003\u0005\u0002\u0012%5\t\u0001\"\u0003\u0002\u0014\u0011\t1\u0011I\\=SK\u001aDQ!F\u0007\u0005\u0002Y\ta\u0001P5oSRtD#A\u0006\t\u000baiA\u0011A\r\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0007i\ti\u0003F\u0002\u001c\u0003_\u0001B\u0001\u0004\u000f\u0002,\u0019!aB\u0001\u0001\u001e+\tqBeE\u0002\u001d?)\u00022\u0001\u0004\u0011#\u0013\t\t#AA\u0002TKR\u0004\"a\t\u0013\r\u0001\u0011)Q\u0005\bb\u0001M\t\tA+\u0005\u0002(!A\u0011\u0011\u0003K\u0005\u0003S!\u0011AAT;mYB\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\bO\u0016tWM]5d\u0015\ty\u0003\"\u0001\u0006d_2dWm\u0019;j_:L!!\r\u0017\u0003\u0013\rcW-\u0019:bE2,\u0007\u0002C\u001a\u001d\u0005\u000b\u0007I\u0011\u0001\u001b\u0002\u000b1\f'-\u001a7\u0016\u0003U\u0002\"AN\u001d\u000f\u0005E9\u0014B\u0001\u001d\t\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005aB\u0001\u0002C\u001f\u001d\u0005\u0003\u0005\u000b\u0011B\u001b\u0002\r1\f'-\u001a7!\u0011!yDD!A!\u0002\u0013\u0001\u0015aD5oSRL\u0017\r\\\"ba\u0006\u001c\u0017\u000e^=\u0011\u0005E\t\u0015B\u0001\"\t\u0005\rIe\u000e\u001e\u0005\u0006+q!\t\u0001\u0012\u000b\u0004\u000b\u001a;\u0005c\u0001\u0007\u001dE!)1g\u0011a\u0001k!)qh\u0011a\u0001\u0001\"9\u0011\n\ba\u0001\n\u0013Q\u0015\u0001B;tK\u0012,\u0012\u0001\u0011\u0005\b\u0019r\u0001\r\u0011\"\u0003N\u0003!)8/\u001a3`I\u0015\fHC\u0001(R!\t\tr*\u0003\u0002Q\u0011\t!QK\\5u\u0011\u001d\u00116*!AA\u0002\u0001\u000b1\u0001\u001f\u00132\u0011\u0019!F\u0004)Q\u0005\u0001\u0006)Qo]3eA!9a\u000b\ba\u0001\n\u00139\u0016!\u0002;bE2,W#\u0001-\u0011\u0007EI\u0006#\u0003\u0002[\u0011\t)\u0011I\u001d:bs\"9A\f\ba\u0001\n\u0013i\u0016!\u0003;bE2,w\fJ3r)\tqe\fC\u0004S7\u0006\u0005\t\u0019\u0001-\t\r\u0001d\u0002\u0015)\u0003Y\u0003\u0019!\u0018M\u00197fA!)!\r\bC\u0005G\u0006)\u0011N\u001c3fqR\u0011\u0001\t\u001a\u0005\u0006K\u0006\u0004\r\u0001Q\u0001\u0002q\")q\r\bC\u0001\u0015\u0006!1/\u001b>f\u0011\u0015IG\u0004\"\u0001k\u0003\u0015\u0019G.Z1s)\u0005q\u0005\"\u00027\u001d\t\u0003i\u0017!\u00054j]\u0012,e\u000e\u001e:z\u001fJ,\u0006\u000fZ1uKR\u0011!E\u001c\u0005\u0006K.\u0004\rA\t\u0005\u0006ar!\t!]\u0001\nM&tG-\u00128uef$\"A\t:\t\u000b\u0015|\u0007\u0019\u0001\u0012\t\u000bQdB\u0011A;\u0002\u0011\u0005$G-\u00128uef$\"A\u0014<\t\u000b\u0015\u001c\b\u0019\u0001\u0012\t\u000badB\u0011A=\u0002\u0015\u0005$G-\u00128ue&,7\u000f\u0006\u0002Ou\")1p\u001ea\u0001y\u0006\u0011\u0001p\u001d\t\u0005{\u0006\u0005!E\u0004\u0002\u0012}&\u0011q\u0010C\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019!!\u0002\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016T!a \u0005\t\u000f\u0005%A\u0004\"\u0001\u0002\f\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\u000eI)\u0011q\u0002\t\u0002\u0014\u00199\u0011\u0011CA\u0004\u0001\u00055!\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004\u0003B?\u0002\u0016\tJA!a\u0006\u0002\u0006\tA\u0011\n^3sCR|'\u000fC\u0004\u0002\u001cq!I!!\b\u0002\u0017\u0005$Gm\u00147e\u000b:$(/\u001f\u000b\u0004\u001d\u0006}\u0001BB3\u0002\u001a\u0001\u0007!\u0005\u0003\u0004\u0002$q!IA[\u0001\nOJ|w\u000fV1cY\u0016Dq!a\n\u001d\t\u0003\nI#\u0001\u0005u_N#(/\u001b8h)\u0005)\u0004cA\u0012\u0002.\u0011)Qe\u0006b\u0001M!)qh\u0006a\u0001\u0001\"1\u0001$\u0004C\u0001\u0003g)B!!\u000e\u0002<Q1\u0011qGA\u001f\u0003\u007f\u0001B\u0001\u0004\u000f\u0002:A\u00191%a\u000f\u0005\r\u0015\n\tD1\u0001'\u0011\u0019\u0019\u0014\u0011\u0007a\u0001k!1q(!\rA\u0002\u0001\u0003")
public class HashSet<T>
extends Set<T>
implements Clearable {
    private final String label;
    private final int initialCapacity;
    private int used;
    private Object[] scala$reflect$internal$util$HashSet$$table;

    public String label() {
        return this.label;
    }

    private int used() {
        return this.used;
    }

    private void used_$eq(int x$1) {
        this.used = x$1;
    }

    public Object[] scala$reflect$internal$util$HashSet$$table() {
        return this.scala$reflect$internal$util$HashSet$$table;
    }

    private void scala$reflect$internal$util$HashSet$$table_$eq(Object[] x$1) {
        this.scala$reflect$internal$util$HashSet$$table = x$1;
    }

    private int index(int x) {
        return package$.MODULE$.abs(x % this.scala$reflect$internal$util$HashSet$$table().length);
    }

    public int size() {
        return this.used();
    }

    @Override
    public void clear() {
        this.used_$eq(0);
        this.scala$reflect$internal$util$HashSet$$table_$eq(new Object[this.initialCapacity]);
    }

    public T findEntryOrUpdate(T x) {
        int h = this.index(ScalaRunTime$.MODULE$.hash(x));
        Object entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        while (entry != null) {
            boolean bl = x != entry ? (x != null ? (!(x instanceof Number) ? (!(x instanceof Character) ? x.equals(entry) : BoxesRunTime.equalsCharObject((Character)x, entry)) : BoxesRunTime.equalsNumObject((Number)x, entry)) : false) : true;
            if (bl) {
                return (T)entry;
            }
            h = this.index(h + 1);
            entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        }
        this.scala$reflect$internal$util$HashSet$$table()[h] = x;
        this.used_$eq(this.used() + 1);
        if (this.used() > this.scala$reflect$internal$util$HashSet$$table().length >> 2) {
            this.growTable();
        }
        return x;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public T findEntry(T x) {
        void var3_3;
        int h = this.index(ScalaRunTime$.MODULE$.hash(x));
        Object entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        while (entry != null) {
            if (x != entry ? (x != null ? (!(x instanceof Number) ? (!(x instanceof Character) ? x.equals(entry) : BoxesRunTime.equalsCharObject((Character)x, entry)) : BoxesRunTime.equalsNumObject((Number)x, entry)) : false) : true) break;
            h = this.index(h + 1);
            entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        }
        return var3_3;
    }

    @Override
    public void addEntry(T x) {
        int h = this.index(ScalaRunTime$.MODULE$.hash(x));
        Object entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        while (entry != null) {
            boolean bl = x != entry ? (x != null ? (!(x instanceof Number) ? (!(x instanceof Character) ? x.equals(entry) : BoxesRunTime.equalsCharObject((Character)x, entry)) : BoxesRunTime.equalsNumObject((Number)x, entry)) : false) : true;
            if (bl) {
                return;
            }
            h = this.index(h + 1);
            entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        }
        this.scala$reflect$internal$util$HashSet$$table()[h] = x;
        this.used_$eq(this.used() + 1);
        if (this.used() > this.scala$reflect$internal$util$HashSet$$table().length >> 2) {
            this.growTable();
        }
    }

    public void addEntries(TraversableOnce<T> xs) {
        xs.foreach(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ HashSet $outer;

            public final void apply(T x) {
                this.$outer.addEntry(x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public Object iterator() {
        return new Iterator<T>(this){
            private int i;
            private final /* synthetic */ HashSet $outer;

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

            public <B> scala.collection.immutable.Set<B> toSet() {
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

            private int i() {
                return this.i;
            }

            private void i_$eq(int x$1) {
                this.i = x$1;
            }

            public boolean hasNext() {
                while (this.i() < this.$outer.scala$reflect$internal$util$HashSet$$table().length && this.$outer.scala$reflect$internal$util$HashSet$$table()[this.i()] == null) {
                    this.i_$eq(this.i() + 1);
                }
                return this.i() < this.$outer.scala$reflect$internal$util$HashSet$$table().length;
            }

            public T next() {
                Object object;
                if (this.hasNext()) {
                    this.i_$eq(this.i() + 1);
                    object = this.$outer.scala$reflect$internal$util$HashSet$$table()[this.i() - 1];
                } else {
                    object = null;
                }
                return (T)object;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Iterator$class.$init$(this);
                this.i = 0;
            }
        };
    }

    private void addOldEntry(T x) {
        int h = this.index(ScalaRunTime$.MODULE$.hash(x));
        Object entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        while (entry != null) {
            h = this.index(h + 1);
            entry = this.scala$reflect$internal$util$HashSet$$table()[h];
        }
        this.scala$reflect$internal$util$HashSet$$table()[h] = x;
    }

    private void growTable() {
        Object[] oldtable = this.scala$reflect$internal$util$HashSet$$table();
        int growthFactor = this.scala$reflect$internal$util$HashSet$$table().length <= this.initialCapacity ? 8 : (this.scala$reflect$internal$util$HashSet$$table().length <= this.initialCapacity * 8 ? 4 : 2);
        this.scala$reflect$internal$util$HashSet$$table_$eq(new Object[this.scala$reflect$internal$util$HashSet$$table().length * growthFactor]);
        for (int i = 0; i < oldtable.length; ++i) {
            Object entry = oldtable[i];
            if (entry == null) continue;
            this.addOldEntry(entry);
        }
    }

    public String toString() {
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("HashSet %s(%d / %d)").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.label(), BoxesRunTime.boxToInteger(this.used()), BoxesRunTime.boxToInteger(this.scala$reflect$internal$util$HashSet$$table().length)}));
    }

    public HashSet(String label, int initialCapacity) {
        this.label = label;
        this.initialCapacity = initialCapacity;
        this.used = 0;
        this.scala$reflect$internal$util$HashSet$$table = new Object[initialCapacity];
    }
}

