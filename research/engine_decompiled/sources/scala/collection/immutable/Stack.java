/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqLike$class;
import scala.collection.LinearSeqOptimized;
import scala.collection.LinearSeqOptimized$class;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.LinearSeq;
import scala.collection.immutable.LinearSeq$class;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.Stack$;
import scala.collection.immutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005mu!B\u0001\u0003\u0011\u0003I\u0011!B*uC\u000e\\'BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u000b\u00175\t!AB\u0003\r\u0005!\u0005QBA\u0003Ti\u0006\u001c7nE\u0002\f\u001dI\u00022a\u0004\n\u0015\u001b\u0005\u0001\"BA\t\u0005\u0003\u001d9WM\\3sS\u000eL!a\u0005\t\u0003\u0015M+\u0017OR1di>\u0014\u0018\u0010\u0005\u0002\u000b+\u0019!AB\u0001\u0001\u0017+\t9bd\u0005\u0004\u00161!ZcF\r\t\u00043iaR\"\u0001\u0003\n\u0005m!!aC!cgR\u0014\u0018m\u0019;TKF\u0004\"!\b\u0010\r\u0001\u00111q$\u0006CC\u0002\u0001\u0012\u0011!Q\t\u0003C\u0015\u0002\"AI\u0012\u000e\u0003\u0019I!\u0001\n\u0004\u0003\u000f9{G\u000f[5oOB\u0011!EJ\u0005\u0003O\u0019\u00111!\u00118z!\rQ\u0011\u0006H\u0005\u0003U\t\u0011\u0011\u0002T5oK\u0006\u00148+Z9\u0011\t=aC\u0004F\u0005\u0003[A\u0011!dR3oKJL7\r\u0016:bm\u0016\u00148/\u00192mKR+W\u000e\u001d7bi\u0016\u0004B!G\u0018\u001dc%\u0011\u0001\u0007\u0002\u0002\u0013\u0019&tW-\u0019:TKF|\u0005\u000f^5nSj,G\rE\u0002\u000b+q\u0001\"AI\u001a\n\u0005Q2!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002\u0003\u001c\u0016\u0005\u000b\u0007I\u0011C\u001c\u0002\u000b\u0015dW-\\:\u0016\u0003a\u00022AC\u001d\u001d\u0013\tQ$A\u0001\u0003MSN$\b\u0002\u0003\u001f\u0016\u0005\u0003\u0005\u000b\u0011\u0002\u001d\u0002\r\u0015dW-\\:!\u0011\u0015qT\u0003\"\u0005@\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0007\u0011\u0005\u0006mu\u0002\r\u0001\u000f\u0005\u0006\u0005V!\teQ\u0001\nG>l\u0007/\u00198j_:,\u0012\u0001\u0012\t\u0004\u001f\u0015#\u0012B\u0001$\u0011\u0005A9UM\\3sS\u000e\u001cu.\u001c9b]&|g\u000eC\u0003?+\u0011\u0005\u0001\nF\u00012\u0011\u0015QU\u0003\"\u0011L\u0003\u001dI7/R7qif,\u0012\u0001\u0014\t\u0003E5K!A\u0014\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0001+\u0006C!#\u0006!\u0001.Z1e+\u0005a\u0002\"B*\u0016\t\u0003\"\u0016\u0001\u0002;bS2,\u0012!\r\u0005\u0006-V!\taV\u0001\u0005aV\u001c\b.\u0006\u0002Y7R\u0011\u0011L\u0018\t\u0004\u0015UQ\u0006CA\u000f\\\t\u0015aVK1\u0001^\u0005\u0005\u0011\u0015C\u0001\u000f&\u0011\u0015yV\u000b1\u0001[\u0003\u0011)G.Z7\t\u000bY+B\u0011A1\u0016\u0005\t,G\u0003B2gQ*\u00042AC\u000be!\tiR\rB\u0003]A\n\u0007Q\fC\u0003hA\u0002\u0007A-A\u0003fY\u0016l\u0017\u0007C\u0003jA\u0002\u0007A-A\u0003fY\u0016l'\u0007C\u00037A\u0002\u00071\u000eE\u0002#Y\u0012L!!\u001c\u0004\u0003\u0015q\u0012X\r]3bi\u0016$g\bC\u0003p+\u0011\u0005\u0001/A\u0004qkND\u0017\t\u001c7\u0016\u0005E$HC\u0001:v!\rQQc\u001d\t\u0003;Q$Q\u0001\u00188C\u0002uCQA\u001e8A\u0002]\f!\u0001_:\u0011\u0007eA8/\u0003\u0002z\t\tyAK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\rC\u0003|+\u0011\u0005\u0011+A\u0002u_BDQ!`\u000b\u0005\u0002Q\u000b1\u0001]8q\u0011\u0019yX\u0003\"\u0001\u0002\u0002\u0005!\u0001o\u001c93+\t\t\u0019\u0001E\u0003#\u0003\u000ba\u0012'C\u0002\u0002\b\u0019\u0011a\u0001V;qY\u0016\u0014\u0004BBA\u0006+\u0011\u0005C+A\u0004sKZ,'o]3\t\u000f\u0005=Q\u0003\"\u0011\u0002\u0012\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\u0014A!\u0011$!\u0006\u001d\u0013\r\t9\u0002\u0002\u0002\t\u0013R,'/\u0019;pe\"9\u00111D\u000b\u0005B\u0005u\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005}\u0001\u0003BA\u0011\u0003Oq1AIA\u0012\u0013\r\t)CB\u0001\u0007!J,G-\u001a4\n\t\u0005%\u00121\u0006\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u0015b\u0001K\u0004\u0016\u0003_\t)$!\u000f\u0011\u0007\t\n\t$C\u0002\u00024\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t\t9$AA\u0018'R\f7m\u001b\u0011jg\u0002\ng\u000eI5oK2,w-\u00198uA\u0005tG\r\t9pi\u0016tG/[1mYf\u0004\u0003o\\8sYfl\u0003/\u001a:g_Jl\u0017N\\4!oJ\f\u0007\u000f]3sA\u0005\u0014x.\u001e8eA1K7\u000f\u001e\u0018!AU\u001bX\r\t'jgR\u0004\u0013N\\:uK\u0006$'\bI:uC\u000e\\\u0007\u0005];tQ\u0002B\bEY3d_6,7\u000f\t=!ui\u0002C.[:uw\u0001\u001aH/Y2l]A|\u0007\u000fI5tA1L7\u000f\u001e\u0018uC&dg&\t\u0002\u0002<\u00051!GL\u00192]ABs!FA \u0003\u000b\n9\u0005E\u0002#\u0003\u0003J1!a\u0011\u0007\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u0005\u001c[z\u000f;D\r~?\u0012\u0019q4\u0002\"\u0001\u0002LQ\t\u0011\u0002C\u0004\u0002P-!\u0019!!\u0015\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\t\u0005M\u0013QM\u000b\u0003\u0003+\u0002\u0012bDA,\u00037\n\u0019'a\u001a\n\u0007\u0005e\u0003C\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0005\u0003\u0002^\u0005}S\"A\u0006\n\u0007\u0005\u0005TI\u0001\u0003D_2d\u0007cA\u000f\u0002f\u00111q$!\u0014C\u0002\u0001\u0002BAC\u000b\u0002d!9\u00111N\u0006\u0005\u0002\u00055\u0014A\u00038fo\n+\u0018\u000e\u001c3feV!\u0011qNA@+\t\t\t\b\u0005\u0005\u0002t\u0005e\u0014QPAA\u001b\t\t)HC\u0002\u0002x\u0011\tq!\\;uC\ndW-\u0003\u0003\u0002|\u0005U$a\u0002\"vS2$WM\u001d\t\u0004;\u0005}DAB\u0010\u0002j\t\u0007\u0001\u0005\u0005\u0003\u000b+\u0005u\u0004\"CAC\u0017\u0005\u0005I\u0011BAD\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\u0005%\u0005\u0003BAF\u0003+k!!!$\u000b\t\u0005=\u0015\u0011S\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0014\u0006!!.\u0019<b\u0013\u0011\t9*!$\u0003\r=\u0013'.Z2uQ\u001dY\u0011qFA\u001b\u0003s\u0001")
public class Stack<A>
extends AbstractSeq<A>
implements LinearSeq<A>,
LinearSeqOptimized<A, Stack<A>>,
Serializable {
    public static final long serialVersionUID = 1976480595012942526L;
    private final List<A> elems;

    public static <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
        return Stack$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return Stack$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Stack$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Stack$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Stack$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Stack$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Stack$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Stack$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Stack$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Stack$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Stack$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Stack$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Stack$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Stack$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return Stack$.MODULE$.empty();
    }

    @Override
    public /* synthetic */ boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public int length() {
        return LinearSeqOptimized$class.length(this);
    }

    @Override
    public A apply(int n) {
        return (A)LinearSeqOptimized$class.apply(this, n);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        LinearSeqOptimized$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return LinearSeqOptimized$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return LinearSeqOptimized$class.exists(this, p);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return LinearSeqOptimized$class.contains(this, elem);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return LinearSeqOptimized$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)LinearSeqOptimized$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> f) {
        return (B)LinearSeqOptimized$class.reduceLeft(this, f);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)LinearSeqOptimized$class.reduceRight(this, op);
    }

    @Override
    public A last() {
        return (A)LinearSeqOptimized$class.last(this);
    }

    @Override
    public LinearSeqOptimized take(int n) {
        return LinearSeqOptimized$class.take(this, n);
    }

    @Override
    public LinearSeqOptimized drop(int n) {
        return LinearSeqOptimized$class.drop(this, n);
    }

    @Override
    public LinearSeqOptimized dropRight(int n) {
        return LinearSeqOptimized$class.dropRight(this, n);
    }

    @Override
    public LinearSeqOptimized slice(int from2, int until2) {
        return LinearSeqOptimized$class.slice(this, from2, until2);
    }

    @Override
    public LinearSeqOptimized takeWhile(Function1 p) {
        return LinearSeqOptimized$class.takeWhile(this, p);
    }

    @Override
    public Tuple2<Stack<A>, Stack<A>> span(Function1<A, Object> p) {
        return LinearSeqOptimized$class.span(this, p);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return LinearSeqOptimized$class.sameElements(this, that);
    }

    @Override
    public int lengthCompare(int len) {
        return LinearSeqOptimized$class.lengthCompare(this, len);
    }

    @Override
    public boolean isDefinedAt(int x) {
        return LinearSeqOptimized$class.isDefinedAt(this, x);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return LinearSeqOptimized$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return LinearSeqOptimized$class.lastIndexWhere(this, p, end);
    }

    @Override
    public LinearSeq<A> seq() {
        return LinearSeq$class.seq(this);
    }

    @Override
    public scala.collection.LinearSeq<A> thisCollection() {
        return LinearSeqLike$class.thisCollection(this);
    }

    @Override
    public scala.collection.LinearSeq toCollection(LinearSeqLike repr) {
        return LinearSeqLike$class.toCollection(this, repr);
    }

    @Override
    public int hashCode() {
        return LinearSeqLike$class.hashCode(this);
    }

    @Override
    public final <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return LinearSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public scala.collection.immutable.Seq<A> toSeq() {
        return Seq$class.toSeq(this);
    }

    @Override
    public Combiner<A, ParSeq<A>> parCombiner() {
        return Seq$class.parCombiner(this);
    }

    public List<A> elems() {
        return this.elems;
    }

    @Override
    public GenericCompanion<Stack> companion() {
        return Stack$.MODULE$;
    }

    @Override
    public boolean isEmpty() {
        return this.elems().isEmpty();
    }

    @Override
    public A head() {
        return this.elems().head();
    }

    @Override
    public Stack<A> tail() {
        return new Stack<A>((List)this.elems().tail());
    }

    public <B> Stack<B> push(B elem) {
        return new Stack<B>(this.elems().$colon$colon(elem));
    }

    public <B> Stack<B> push(B elem1, B elem2, Seq<B> elems) {
        return this.push(elem1).push(elem2).pushAll(elems);
    }

    public <B> Stack<B> pushAll(TraversableOnce<B> xs) {
        return xs.toIterator().$div$colon(this, new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final Stack<B> apply(Stack<B> x$3, B x$4) {
                return x$3.push(x$4);
            }
        });
    }

    public A top() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("top of empty stack");
        }
        return this.elems().head();
    }

    public Stack<A> pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("pop of empty stack");
        }
        return new Stack<A>((List)this.elems().tail());
    }

    public Tuple2<A, Stack<A>> pop2() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("pop of empty stack");
        }
        return new Tuple2<A, Stack<A>>(this.elems().head(), new Stack<A>((List)this.elems().tail()));
    }

    @Override
    public Stack<A> reverse() {
        return new Stack<A>(this.elems().reverse());
    }

    @Override
    public Iterator<A> iterator() {
        return this.elems().iterator();
    }

    @Override
    public String toString() {
        return this.elems().mkString("Stack(", ", ", ")");
    }

    public Stack(List<A> elems) {
        this.elems = elems;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        LinearSeqLike$class.$init$(this);
        scala.collection.LinearSeq$class.$init$(this);
        LinearSeq$class.$init$(this);
        LinearSeqOptimized$class.$init$(this);
    }

    public Stack() {
        this(Nil$.MODULE$);
    }
}

