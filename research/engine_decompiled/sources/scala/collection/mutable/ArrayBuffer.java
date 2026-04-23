/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike;
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.AbstractBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.IndexedSeqView;
import scala.collection.mutable.ResizableArray;
import scala.collection.mutable.ResizableArray$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ParArray$;
import scala.compat.Platform$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005Ee\u0001B\u0001\u0003\u0001%\u00111\"\u0011:sCf\u0014UO\u001a4fe*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQ\u0011c\u0005\u0006\u0001\u0017mqR%\u000b\u00170eu\u00022\u0001D\u0007\u0010\u001b\u0005\u0011\u0011B\u0001\b\u0003\u00059\t%m\u001d;sC\u000e$()\u001e4gKJ\u0004\"\u0001E\t\r\u0001\u0011)!\u0003\u0001b\u0001'\t\t\u0011)\u0005\u0002\u00151A\u0011QCF\u0007\u0002\r%\u0011qC\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\r\t\u0019\u0011I\\=\u0011\u00071ar\"\u0003\u0002\u001e\u0005\t1!)\u001e4gKJ\u0004Ba\b\u0012\u0010I5\t\u0001E\u0003\u0002\"\t\u00059q-\u001a8fe&\u001c\u0017BA\u0012!\u0005i9UM\\3sS\u000e$&/\u0019<feN\f'\r\\3UK6\u0004H.\u0019;f!\ta\u0001\u0001\u0005\u0003\rM=A\u0013BA\u0014\u0003\u0005)\u0011UO\u001a4fe2K7.\u001a\t\u0004\u0019\u0001y\u0001\u0003\u0002\u0007+\u001f!J!a\u000b\u0002\u0003'%sG-\u001a=fIN+\u0017o\u00149uS6L'0\u001a3\u0011\t1is\u0002K\u0005\u0003]\t\u0011qAQ;jY\u0012,'\u000fE\u0002\ra=I!!\r\u0002\u0003\u001dI+7/\u001b>bE2,\u0017I\u001d:bsB!1\u0007N\b7\u001b\u0005!\u0011BA\u001b\u0005\u0005Q\u0019Uo\u001d;p[B\u000b'/\u00197mK2L'0\u00192mKB\u0019qgO\b\u000e\u0003aR!aA\u001d\u000b\u0005i\"\u0011\u0001\u00039be\u0006dG.\u001a7\n\u0005qB$\u0001\u0003)be\u0006\u0013(/Y=\u0011\u0005Uq\u0014BA \u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!\t\u0005A!b\u0001\n#\u0012\u0015aC5oSRL\u0017\r\\*ju\u0016,\u0012a\u0011\t\u0003+\u0011K!!\u0012\u0004\u0003\u0007%sG\u000f\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003D\u00031Ig.\u001b;jC2\u001c\u0016N_3!\u0011\u0015I\u0005\u0001\"\u0001K\u0003\u0019a\u0014N\\5u}Q\u0011\u0001f\u0013\u0005\u0006\u0003\"\u0003\ra\u0011\u0005\u0006\u001b\u0002!\tET\u0001\nG>l\u0007/\u00198j_:,\u0012a\u0014\t\u0004?A#\u0013BA)!\u0005A9UM\\3sS\u000e\u001cu.\u001c9b]&|g\u000eC\u0003J\u0001\u0011\u00051\u000bF\u0001)\u0011\u0015)\u0006\u0001\"\u0001W\u0003\u0015\u0019G.Z1s)\u00059\u0006CA\u000bY\u0013\tIfA\u0001\u0003V]&$\b\"B.\u0001\t\u0003b\u0016\u0001C:ju\u0016D\u0015N\u001c;\u0015\u0005]k\u0006\"\u00020[\u0001\u0004\u0019\u0015a\u00017f]\")\u0001\r\u0001C!C\u0006\u0019\u0001/\u0019:\u0016\u0003YBQa\u0019\u0001\u0005\u0002\u0011\f\u0001\u0002\n9mkN$S-\u001d\u000b\u0003K\u001al\u0011\u0001\u0001\u0005\u0006O\n\u0004\raD\u0001\u0005K2,W\u000eC\u0003j\u0001\u0011\u0005#.A\u0007%a2,8\u000f\n9mkN$S-\u001d\u000b\u0003K.DQ\u0001\u001c5A\u00025\f!\u0001_:\u0011\u0007Mrw\"\u0003\u0002p\t\tyAK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\rC\u0003r\u0001\u0011\u0005!/\u0001\b%a2,8\u000fJ3rI\r|Gn\u001c8\u0015\u0005\u0015\u001c\b\"B4q\u0001\u0004y\u0001\"B;\u0001\t\u00032\u0018a\u0005\u0013qYV\u001cH\u0005\u001d7vg\u0012*\u0017\u000fJ2pY>tGCA3x\u0011\u0015aG\u000f1\u0001n\u0011\u0015I\b\u0001\"\u0001{\u0003%Ign]3si\u0006cG\u000eF\u0002XwvDQ\u0001 =A\u0002\r\u000b\u0011A\u001c\u0005\u0006}b\u0004\ra`\u0001\u0004g\u0016\f\b\u0003B\u001a\u0002\u0002=I1!a\u0001\u0005\u0005-!&/\u0019<feN\f'\r\\3\t\u000f\u0005\u001d\u0001\u0001\"\u0011\u0002\n\u00051!/Z7pm\u0016$RaVA\u0006\u0003\u001bAa\u0001`A\u0003\u0001\u0004\u0019\u0005bBA\b\u0003\u000b\u0001\raQ\u0001\u0006G>,h\u000e\u001e\u0005\b\u0003\u000f\u0001A\u0011AA\n)\ry\u0011Q\u0003\u0005\u0007y\u0006E\u0001\u0019A\"\t\r\u0005e\u0001\u0001\"\u0001T\u0003\u0019\u0011Xm];mi\"9\u0011Q\u0004\u0001\u0005B\u0005}\u0011\u0001D:ue&tw\r\u0015:fM&DXCAA\u0011!\u0011\t\u0019#!\u000b\u000f\u0007U\t)#C\u0002\u0002(\u0019\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0016\u0003[\u0011aa\u0015;sS:<'bAA\u0014\r!:\u0001!!\r\u00028\u0005e\u0002cA\u000b\u00024%\u0019\u0011Q\u0007\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004C\u000b9aO\u001b)Qd:\b\u000f\u0005u\"\u0001#\u0001\u0002@\u0005Y\u0011I\u001d:bs\n+hMZ3s!\ra\u0011\u0011\t\u0004\u0007\u0003\tA\t!a\u0011\u0014\u000b\u0005\u0005\u0013QI\u001f\u0011\t}\t9\u0005J\u0005\u0004\u0003\u0013\u0002#AC*fc\u001a\u000b7\r^8ss\"9\u0011*!\u0011\u0005\u0002\u00055CCAA \u0011!\t\t&!\u0011\u0005\u0004\u0005M\u0013\u0001D2b]\n+\u0018\u000e\u001c3Ge>lW\u0003BA+\u0003O*\"!a\u0016\u0011\u0013}\tI&!\u0018\u0002f\u0005%\u0014bAA.A\ta1)\u00198Ck&dGM\u0012:p[B!\u0011qLA1\u001b\t\t\t%C\u0002\u0002dA\u0013AaQ8mYB\u0019\u0001#a\u001a\u0005\rI\tyE1\u0001\u0014!\u0011a\u0001!!\u001a\t\u0011\u00055\u0014\u0011\tC\u0001\u0003_\n!B\\3x\u0005VLG\u000eZ3s+\u0011\t\t(a\u001e\u0016\u0005\u0005M\u0004C\u0002\u0007.\u0003k\nI\bE\u0002\u0011\u0003o\"aAEA6\u0005\u0004\u0019\u0002\u0003\u0002\u0007\u0001\u0003kB!\"! \u0002B\u0005\u0005I\u0011BA@\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\u0005\u0005\u0005\u0003BAB\u0003\u001bk!!!\"\u000b\t\u0005\u001d\u0015\u0011R\u0001\u0005Y\u0006twM\u0003\u0002\u0002\f\u0006!!.\u0019<b\u0013\u0011\ty)!\"\u0003\r=\u0013'.Z2u\u0001")
public class ArrayBuffer<A>
extends AbstractBuffer<A>
implements Builder<A, ArrayBuffer<A>>,
ResizableArray<A>,
CustomParallelizable<A, ParArray<A>>,
Serializable {
    public static final long serialVersionUID = 1529165946227428979L;
    private final int initialSize;
    private Object[] array;
    private int size0;

    public static <A> CanBuildFrom<ArrayBuffer<?>, A, ArrayBuffer<A>> canBuildFrom() {
        return ArrayBuffer$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return ArrayBuffer$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ArrayBuffer$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ArrayBuffer$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ArrayBuffer$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ArrayBuffer$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ArrayBuffer$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ArrayBuffer$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ArrayBuffer$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ArrayBuffer$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ArrayBuffer$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ArrayBuffer$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ArrayBuffer$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ArrayBuffer$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ArrayBuffer$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ArrayBuffer$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ArrayBuffer$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ArrayBuffer$.MODULE$.empty();
    }

    @Override
    public Combiner<A, ParArray<A>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Object[] array() {
        return this.array;
    }

    @Override
    public void array_$eq(Object[] x$1) {
        this.array = x$1;
    }

    @Override
    public int size0() {
        return this.size0;
    }

    @Override
    public void size0_$eq(int x$1) {
        this.size0 = x$1;
    }

    @Override
    public int length() {
        return ResizableArray$class.length(this);
    }

    @Override
    public A apply(int idx) {
        return (A)ResizableArray$class.apply(this, idx);
    }

    @Override
    public void update(int idx, A elem) {
        ResizableArray$class.update(this, idx, elem);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        ResizableArray$class.foreach(this, f);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        ResizableArray$class.copyToArray(this, xs, start, len);
    }

    @Override
    public void reduceToSize(int sz) {
        ResizableArray$class.reduceToSize(this, sz);
    }

    @Override
    public void ensureSize(int n) {
        ResizableArray$class.ensureSize(this, n);
    }

    @Override
    public void swap(int a, int b) {
        ResizableArray$class.swap(this, a, b);
    }

    @Override
    public void copy(int m, int n, int len) {
        ResizableArray$class.copy(this, m, n, len);
    }

    @Override
    public IndexedSeq<A> seq() {
        return scala.collection.mutable.IndexedSeq$class.seq(this);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<A, NewTo> mapResult(Function1<ArrayBuffer<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
        return TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
        return IterableLike$class.reduceRight(this, op);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
        return IterableLike$class.zip(this, that, bf);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$head() {
        return IterableLike$class.head(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$last() {
        return TraversableLike$class.last(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$IndexedSeqOptimized$$super$init() {
        return TraversableLike$class.init(this);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public /* synthetic */ boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
        return SeqLike$class.endsWith(this, that);
    }

    @Override
    public boolean isEmpty() {
        return IndexedSeqOptimized$class.isEmpty(this);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return IndexedSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return IndexedSeqOptimized$class.exists(this, p);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return IndexedSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)IndexedSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)IndexedSeqOptimized$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> op) {
        return (B)IndexedSeqOptimized$class.reduceLeft(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)IndexedSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<ArrayBuffer<A>, Tuple2<A1, B>, That> bf) {
        return (That)IndexedSeqOptimized$class.zip(this, that, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<ArrayBuffer<A>, Tuple2<A1, Object>, That> bf) {
        return (That)IndexedSeqOptimized$class.zipWithIndex(this, bf);
    }

    @Override
    public Object slice(int from2, int until2) {
        return IndexedSeqOptimized$class.slice(this, from2, until2);
    }

    @Override
    public A head() {
        return (A)IndexedSeqOptimized$class.head(this);
    }

    @Override
    public Object tail() {
        return IndexedSeqOptimized$class.tail(this);
    }

    @Override
    public A last() {
        return (A)IndexedSeqOptimized$class.last(this);
    }

    @Override
    public Object init() {
        return IndexedSeqOptimized$class.init(this);
    }

    @Override
    public Object take(int n) {
        return IndexedSeqOptimized$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IndexedSeqOptimized$class.drop(this, n);
    }

    @Override
    public Object takeRight(int n) {
        return IndexedSeqOptimized$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IndexedSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public Tuple2<ArrayBuffer<A>, ArrayBuffer<A>> splitAt(int n) {
        return IndexedSeqOptimized$class.splitAt(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IndexedSeqOptimized$class.takeWhile(this, p);
    }

    @Override
    public Object dropWhile(Function1 p) {
        return IndexedSeqOptimized$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<ArrayBuffer<A>, ArrayBuffer<A>> span(Function1<A, Object> p) {
        return IndexedSeqOptimized$class.span(this, p);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IndexedSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public int lengthCompare(int len) {
        return IndexedSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return IndexedSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return IndexedSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return IndexedSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Object reverse() {
        return IndexedSeqOptimized$class.reverse(this);
    }

    @Override
    public Iterator<A> reverseIterator() {
        return IndexedSeqOptimized$class.reverseIterator(this);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that, int offset) {
        return IndexedSeqOptimized$class.startsWith(this, that, offset);
    }

    @Override
    public <B> boolean endsWith(GenSeq<B> that) {
        return IndexedSeqOptimized$class.endsWith(this, that);
    }

    @Override
    public IndexedSeq<A> thisCollection() {
        return scala.collection.mutable.IndexedSeqLike$class.thisCollection(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return scala.collection.mutable.IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public Object view() {
        return scala.collection.mutable.IndexedSeqLike$class.view(this);
    }

    @Override
    public IndexedSeqView<A, ArrayBuffer<A>> view(int from2, int until2) {
        return scala.collection.mutable.IndexedSeqLike$class.view(this, from2, until2);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
    }

    @Override
    public Iterator<A> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    @Override
    public int initialSize() {
        return this.initialSize;
    }

    @Override
    public GenericCompanion<ArrayBuffer> companion() {
        return ArrayBuffer$.MODULE$;
    }

    @Override
    public void clear() {
        this.reduceToSize(0);
    }

    @Override
    public void sizeHint(int len) {
        if (len > this.size() && len >= 1) {
            Object[] newarray = new Object[len];
            int n = this.size0();
            Object[] objectArray = this.array();
            Platform$ platform$ = Platform$.MODULE$;
            System.arraycopy(objectArray, 0, newarray, 0, n);
            this.array_$eq(newarray);
        }
    }

    @Override
    public ParArray<A> par() {
        return ParArray$.MODULE$.handoff(this.array(), this.size());
    }

    @Override
    public ArrayBuffer<A> $plus$eq(A elem) {
        this.ensureSize(this.size0() + 1);
        this.array()[this.size0()] = elem;
        this.size0_$eq(this.size0() + 1);
        return this;
    }

    @Override
    public ArrayBuffer<A> $plus$plus$eq(TraversableOnce<A> xs) {
        ArrayBuffer arrayBuffer;
        if (xs instanceof IndexedSeqLike) {
            IndexedSeqLike indexedSeqLike = (IndexedSeqLike)xs;
            int n = indexedSeqLike.length();
            this.ensureSize(this.size0() + n);
            indexedSeqLike.copyToArray(this.array(), this.size0(), n);
            this.size0_$eq(this.size0() + n);
            arrayBuffer = this;
        } else {
            arrayBuffer = (ArrayBuffer)Growable$class.$plus$plus$eq(this, xs);
        }
        return arrayBuffer;
    }

    @Override
    public ArrayBuffer<A> $plus$eq$colon(A elem) {
        this.ensureSize(this.size0() + 1);
        this.copy(0, 1, this.size0());
        this.array()[0] = elem;
        this.size0_$eq(this.size0() + 1);
        return this;
    }

    @Override
    public ArrayBuffer<A> $plus$plus$eq$colon(TraversableOnce<A> xs) {
        this.insertAll(0, xs.toTraversable());
        return this;
    }

    @Override
    public void insertAll(int n, Traversable<A> seq) {
        if (n < 0 || n > this.size0()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        int len = seq.size();
        int newSize = this.size0() + len;
        this.ensureSize(newSize);
        this.copy(n, n + len, this.size0() - n);
        seq.copyToArray(this.array(), n);
        this.size0_$eq(newSize);
    }

    @Override
    public void remove(int n, int count2) {
        boolean bl = count2 >= 0;
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            if (n < 0 || n > this.size0() - count2) {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            this.copy(n + count2, n, this.size0() - (n + count2));
            this.reduceToSize(this.size0() - count2);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"removing negative number of elements").toString());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public A remove(int n) {
        void var2_2;
        A result2 = this.apply(n);
        this.remove(n, 1);
        return var2_2;
    }

    @Override
    public ArrayBuffer<A> result() {
        return this;
    }

    @Override
    public String stringPrefix() {
        return "ArrayBuffer";
    }

    public ArrayBuffer(int initialSize) {
        this.initialSize = initialSize;
        IndexedSeqLike$class.$init$(this);
        scala.collection.mutable.IndexedSeqLike$class.$init$(this);
        IndexedSeqOptimized$class.$init$(this);
        Builder$class.$init$(this);
        IndexedSeq$class.$init$(this);
        scala.collection.mutable.IndexedSeq$class.$init$(this);
        ResizableArray$class.$init$(this);
        CustomParallelizable$class.$init$(this);
    }

    public ArrayBuffer() {
        this(16);
    }
}

