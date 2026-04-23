/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
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
import scala.collection.IndexedSeqLike$class;
import scala.collection.IndexedSeqOptimized$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike$class;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.AbstractSeq;
import scala.collection.mutable.ArraySeq$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.IndexedSeq;
import scala.collection.mutable.IndexedSeqOptimized;
import scala.collection.mutable.IndexedSeqView;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ParArray$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u00055d\u0001B\u0001\u0003\u0001%\u0011\u0001\"\u0011:sCf\u001cV-\u001d\u0006\u0003\u0007\u0011\tq!\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b#M9\u0001aC\u000e\u001fK%\"\u0004c\u0001\u0007\u000e\u001f5\t!!\u0003\u0002\u000f\u0005\tY\u0011IY:ue\u0006\u001cGoU3r!\t\u0001\u0012\u0003\u0004\u0001\u0005\u000bI\u0001!\u0019A\n\u0003\u0003\u0005\u000b\"\u0001\u0006\r\u0011\u0005U1R\"\u0001\u0004\n\u0005]1!a\u0002(pi\"Lgn\u001a\t\u0003+eI!A\u0007\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\r9=I!!\b\u0002\u0003\u0015%sG-\u001a=fIN+\u0017\u000f\u0005\u0003 E=!S\"\u0001\u0011\u000b\u0005\u0005\"\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003G\u0001\u0012!dR3oKJL7\r\u0016:bm\u0016\u00148/\u00192mKR+W\u000e\u001d7bi\u0016\u0004\"\u0001\u0004\u0001\u0011\t11s\u0002K\u0005\u0003O\t\u00111#\u00138eKb,GmU3r\u001fB$\u0018.\\5{K\u0012\u00042\u0001\u0004\u0001\u0010!\u0011Q3fD\u0017\u000e\u0003\u0011I!\u0001\f\u0003\u0003)\r+8\u000f^8n!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f!\rq#gD\u0007\u0002_)\u00111\u0001\r\u0006\u0003c\u0011\t\u0001\u0002]1sC2dW\r\\\u0005\u0003g=\u0012\u0001\u0002U1s\u0003J\u0014\u0018-\u001f\t\u0003+UJ!A\u000e\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011a\u0002!Q1A\u0005Be\na\u0001\\3oORDW#\u0001\u001e\u0011\u0005UY\u0014B\u0001\u001f\u0007\u0005\rIe\u000e\u001e\u0005\t}\u0001\u0011\t\u0011)A\u0005u\u00059A.\u001a8hi\"\u0004\u0003\"\u0002!\u0001\t\u0003\t\u0015A\u0002\u001fj]&$h\b\u0006\u0002)\u0005\")\u0001h\u0010a\u0001u!)A\t\u0001C!\u000b\u0006I1m\\7qC:LwN\\\u000b\u0002\rB\u0019qd\u0012\u0013\n\u0005!\u0003#\u0001E$f]\u0016\u0014\u0018nY\"p[B\fg.[8o\u0011\u001dQ\u0005A1A\u0005\u0002-\u000bQ!\u0019:sCf,\u0012\u0001\u0014\t\u0004+5{\u0015B\u0001(\u0007\u0005\u0015\t%O]1z!\t)\u0002+\u0003\u0002R\r\t1\u0011I\\=SK\u001aDaa\u0015\u0001!\u0002\u0013a\u0015AB1se\u0006L\b\u0005C\u0003V\u0001\u0011\u0005c+A\u0002qCJ,\u0012!\f\u0005\u00061\u0002!\t!W\u0001\u0006CB\u0004H.\u001f\u000b\u0003\u001fiCQaW,A\u0002i\n1!\u001b3y\u0011\u0015i\u0006\u0001\"\u0001_\u0003\u0019)\b\u000fZ1uKR\u0019qLY2\u0011\u0005U\u0001\u0017BA1\u0007\u0005\u0011)f.\u001b;\t\u000bmc\u0006\u0019\u0001\u001e\t\u000b\u0011d\u0006\u0019A\b\u0002\t\u0015dW-\u001c\u0005\u0006M\u0002!\teZ\u0001\bM>\u0014X-Y2i+\tAw\u000e\u0006\u0002`S\")!.\u001aa\u0001W\u0006\ta\r\u0005\u0003\u0016Y>q\u0017BA7\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0011_\u0012)\u0001/\u001ab\u0001'\t\tQ\u000bC\u0003s\u0001\u0011\u00053/A\u0006d_BLHk\\!se\u0006LXC\u0001;z)\u0011yV\u000f @\t\u000bY\f\b\u0019A<\u0002\u0005a\u001c\bcA\u000bNqB\u0011\u0001#\u001f\u0003\u0006uF\u0014\ra\u001f\u0002\u0002\u0005F\u0011q\u0002\u0007\u0005\u0006{F\u0004\rAO\u0001\u0006gR\f'\u000f\u001e\u0005\u0006\u007fF\u0004\rAO\u0001\u0004Y\u0016t\u0007bBA\u0002\u0001\u0011\u0005\u0013QA\u0001\u0006G2|g.\u001a\u000b\u0002Q!:\u0001!!\u0003\u0002\u0010\u0005E\u0001cA\u000b\u0002\f%\u0019\u0011Q\u0002\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004C\u000b={IG\u0013JD:\b\u000f\u0005U!\u0001#\u0001\u0002\u0018\u0005A\u0011I\u001d:bsN+\u0017\u000fE\u0002\r\u000331a!\u0001\u0002\t\u0002\u0005m1#BA\r\u0003;!\u0004\u0003B\u0010\u0002 \u0011J1!!\t!\u0005)\u0019V-\u001d$bGR|'/\u001f\u0005\b\u0001\u0006eA\u0011AA\u0013)\t\t9\u0002\u0003\u0005\u0002*\u0005eA1AA\u0016\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0011\ti#a\u0010\u0016\u0005\u0005=\u0002#C\u0010\u00022\u0005U\u0012QHA!\u0013\r\t\u0019\u0004\t\u0002\r\u0007\u0006t')^5mI\u001a\u0013x.\u001c\t\u0005\u0003o\tI$\u0004\u0002\u0002\u001a%\u0019\u00111H$\u0003\t\r{G\u000e\u001c\t\u0004!\u0005}BA\u0002\n\u0002(\t\u00071\u0003\u0005\u0003\r\u0001\u0005u\u0002\u0002CA#\u00033!\t!a\u0012\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0003\u0002J\u0005MSCAA&!\u001da\u0011QJA)\u0003+J1!a\u0014\u0003\u0005\u001d\u0011U/\u001b7eKJ\u00042\u0001EA*\t\u0019\u0011\u00121\tb\u0001'A!A\u0002AA)\u0011)\tI&!\u0007\u0002\u0002\u0013%\u00111L\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0002^A!\u0011qLA5\u001b\t\t\tG\u0003\u0003\u0002d\u0005\u0015\u0014\u0001\u00027b]\u001eT!!a\u001a\u0002\t)\fg/Y\u0005\u0005\u0003W\n\tG\u0001\u0004PE*,7\r\u001e")
public class ArraySeq<A>
extends AbstractSeq<A>
implements IndexedSeq<A>,
IndexedSeqOptimized<A, ArraySeq<A>>,
CustomParallelizable<A, ParArray<A>>,
Serializable {
    public static final long serialVersionUID = 1530165946227428979L;
    private final int length;
    private final Object[] array;

    public static <A> CanBuildFrom<ArraySeq<?>, A, ArraySeq<A>> canBuildFrom() {
        return ArraySeq$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return ArraySeq$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ArraySeq$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ArraySeq$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ArraySeq$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ArraySeq$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ArraySeq$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ArraySeq$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ArraySeq$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ArraySeq$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ArraySeq$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ArraySeq$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ArraySeq$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ArraySeq$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ArraySeq$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ArraySeq$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ArraySeq$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ArraySeq$.MODULE$.empty();
    }

    @Override
    public Combiner<A, ParArray<A>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
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
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<ArraySeq<A>, Tuple2<A1, B>, That> bf) {
        return (That)IndexedSeqOptimized$class.zip(this, that, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<ArraySeq<A>, Tuple2<A1, Object>, That> bf) {
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
    public Tuple2<ArraySeq<A>, ArraySeq<A>> splitAt(int n) {
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
    public Tuple2<ArraySeq<A>, ArraySeq<A>> span(Function1<A, Object> p) {
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
    public IndexedSeq<A> seq() {
        return scala.collection.mutable.IndexedSeq$class.seq(this);
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
    public IndexedSeqView<A, ArraySeq<A>> view(int from2, int until2) {
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
    public int length() {
        return this.length;
    }

    @Override
    public GenericCompanion<ArraySeq> companion() {
        return ArraySeq$.MODULE$;
    }

    public Object[] array() {
        return this.array;
    }

    @Override
    public ParArray<A> par() {
        return ParArray$.MODULE$.handoff(this.array(), this.length());
    }

    @Override
    public A apply(int idx) {
        if (idx >= this.length()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        return (A)this.array()[idx];
    }

    @Override
    public void update(int idx, A elem) {
        if (idx >= this.length()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        this.array()[idx] = elem;
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        for (int i = 0; i < this.length(); ++i) {
            f.apply(this.array()[i]);
        }
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        Predef$ predef$ = Predef$.MODULE$;
        int n = RichInt$.MODULE$.min$extension(len, ScalaRunTime$.MODULE$.array_length(xs) - start);
        Predef$ predef$2 = Predef$.MODULE$;
        int len1 = RichInt$.MODULE$.min$extension(n, this.length());
        Array$.MODULE$.copy(this.array(), 0, xs, start, len1);
    }

    @Override
    public ArraySeq<A> clone() {
        Object[] cloned = (Object[])this.array().clone();
        return new ArraySeq<A>(this, cloned){
            private final Object[] array;

            public Object[] array() {
                return this.array;
            }
            {
                this.array = cloned$1;
            }
        };
    }

    public ArraySeq(int length) {
        this.length = length;
        IndexedSeqLike$class.$init$(this);
        IndexedSeq$class.$init$(this);
        scala.collection.mutable.IndexedSeqLike$class.$init$(this);
        scala.collection.mutable.IndexedSeq$class.$init$(this);
        IndexedSeqOptimized$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        this.array = new Object[length];
    }
}

