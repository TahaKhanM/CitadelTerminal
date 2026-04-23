/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.immutable.VectorIterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.AugmentedSeqIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeq$class;
import scala.collection.parallel.ParSeqLike$class;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.SeqSplitter$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.ParIterable;
import scala.collection.parallel.immutable.ParIterable$class;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParSet;
import scala.collection.parallel.immutable.ParVector$;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005d\u0001B\u0001\u0003\u0001-\u0011\u0011\u0002U1s-\u0016\u001cGo\u001c:\u000b\u0005\r!\u0011!C5n[V$\u0018M\u00197f\u0015\t)a!\u0001\u0005qCJ\fG\u000e\\3m\u0015\t9\u0001\"\u0001\u0006d_2dWm\u0019;j_:T\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\taqc\u0005\u0004\u0001\u001bE\u0001s%\r\t\u0003\u001d=i\u0011\u0001C\u0005\u0003!!\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0014+5\t!!\u0003\u0002\u0015\u0005\t1\u0001+\u0019:TKF\u0004\"AF\f\r\u0001\u00111\u0001\u0004\u0001CC\u0002e\u0011\u0011\u0001V\t\u00035u\u0001\"AD\u000e\n\u0005qA!a\u0002(pi\"Lgn\u001a\t\u0003\u001dyI!a\b\u0005\u0003\u0007\u0005s\u0017\u0010\u0005\u0003\"IU1S\"\u0001\u0012\u000b\u0005\r2\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003K\t\u0012!cR3oKJL7\rU1s)\u0016l\u0007\u000f\\1uKB\u0011!\u0003\u0001\t\u0006Q%*2\u0006L\u0007\u0002\t%\u0011!\u0006\u0002\u0002\u000b!\u0006\u00148+Z9MS.,\u0007c\u0001\n\u0001+A\u0019QfL\u000b\u000e\u00039R!a\u0001\u0004\n\u0005Ar#A\u0002,fGR|'\u000f\u0005\u0002\u000fe%\u00111\u0007\u0003\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tk\u0001\u0011\t\u0011)A\u0005Y\u00051a/Z2u_JDQa\u000e\u0001\u0005\u0002a\na\u0001P5oSRtDCA\u0016:\u0011\u0015)d\u00071\u0001-\u0011\u0015Y\u0004\u0001\"\u0011=\u0003%\u0019w.\u001c9b]&|g.F\u0001>\u001d\t\u0011bhB\u0003@\u0005!\u0005\u0001)A\u0005QCJ4Vm\u0019;peB\u0011!#\u0011\u0004\u0006\u0003\tA\tAQ\n\u0004\u0003\u000e\u000b\u0004cA\u0011EM%\u0011QI\t\u0002\u000b!\u0006\u0014h)Y2u_JL\b\"B\u001cB\t\u00039E#\u0001!\t\u000b%\u000bE1\u0001&\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\u0005-3V#\u0001'\u0011\u000b\u0005ju*V,\n\u00059\u0013#AD\"b]\u000e{WNY5oK\u001a\u0013x.\u001c\t\u0003!Fk\u0011!Q\u0005\u0003%N\u0013AaQ8mY&\u0011AK\t\u0002\u0011\u000f\u0016tWM]5d\u0007>l\u0007/\u00198j_:\u0004\"A\u0006,\u0005\u000baA%\u0019A\r\u0011\u0007I\u0001Q\u000bC\u0003Z\u0003\u0012\u0005!,\u0001\u0006oK^\u0014U/\u001b7eKJ,\"a\u00171\u0016\u0003q\u0003B\u0001K/`C&\u0011a\f\u0002\u0002\t\u0007>l'-\u001b8feB\u0011a\u0003\u0019\u0003\u00061a\u0013\r!\u0007\t\u0004%\u0001y\u0006\"B2B\t\u0003!\u0017a\u00038fo\u000e{WNY5oKJ,\"!\u001a5\u0016\u0003\u0019\u0004B\u0001K/hSB\u0011a\u0003\u001b\u0003\u00061\t\u0014\r!\u0007\t\u0004%\u00019\u0007bB6B\u0003\u0003%I\u0001\\\u0001\fe\u0016\fGMU3t_24X\rF\u0001n!\tq7/D\u0001p\u0015\t\u0001\u0018/\u0001\u0003mC:<'\"\u0001:\u0002\t)\fg/Y\u0005\u0003i>\u0014aa\u00142kK\u000e$\b\"B\u001c\u0001\t\u00031H#A\u0016\t\u000ba\u0004A\u0011A=\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005UQ\b\"B>x\u0001\u0004a\u0018aA5eqB\u0011a\"`\u0005\u0003}\"\u00111!\u00138u\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u0007\ta\u0001\\3oORDW#\u0001?\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n\u0005A1\u000f\u001d7jiR,'/\u0006\u0002\u0002\fA!\u0001&!\u0004\u0016\u0013\r\ty\u0001\u0002\u0002\f'\u0016\f8\u000b\u001d7jiR,'\u000fC\u0004\u0002\u0014\u0001!\t%!\u0006\u0002\u0007M,\u0017/F\u0001-\u0011\u001d\tI\u0002\u0001C!\u0003+\t\u0001\u0002^8WK\u000e$xN\u001d\u0004\u0007\u0003;\u0001\u0001!a\b\u0003#A\u000b'OV3di>\u0014\u0018\n^3sCR|'o\u0005\u0004\u0002\u001c\u0005\u0005\u00121\u0002\t\u0005[\u0005\rR#C\u0002\u0002&9\u0012aBV3di>\u0014\u0018\n^3sCR|'\u000f\u0003\u0006\u0002*\u0005m!\u0011!Q\u0001\nq\faaX:uCJ$\bBCA\u0017\u00037\u0011\t\u0011)A\u0005y\u0006!q,\u001a8e\u0011\u001d9\u00141\u0004C\u0001\u0003c!b!a\r\u00028\u0005e\u0002\u0003BA\u001b\u00037i\u0011\u0001\u0001\u0005\b\u0003S\ty\u00031\u0001}\u0011\u001d\ti#a\fA\u0002qD\u0001\"!\u0010\u0002\u001c\u0011\u0005\u00111A\u0001\ne\u0016l\u0017-\u001b8j]\u001eD\u0001\"!\u0011\u0002\u001c\u0011\u0005\u0011\u0011B\u0001\u0004IV\u0004\b\u0002CA#\u00037!\t!a\u0012\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005%\u0003CBA&\u0003\u001b\n\u0019$D\u0001\u0007\u0013\r\tyE\u0002\u0002\u0004'\u0016\f\b\u0002CA*\u00037!\t!!\u0016\u0002\rA\u001c\b\u000f\\5u)\u0011\tI%a\u0016\t\u0011\u0005e\u0013\u0011\u000ba\u0001\u00037\nQa]5{KN\u0004BADA/y&\u0019\u0011q\f\u0005\u0003\u0015q\u0012X\r]3bi\u0016$g\b")
public class ParVector<T>
implements scala.collection.parallel.immutable.ParSeq<T>,
Serializable {
    private final Vector<T> vector;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <T> CanCombineFrom<ParVector<?>, T, ParVector<T>> canBuildFrom() {
        return ParVector$.MODULE$.canBuildFrom();
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ParVector$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ParVector$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ParVector$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ParVector$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ParVector$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ParVector$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ParVector$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ParVector$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ParVector$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ParVector$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ParVector$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ParVector$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ParVector$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ParVector$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ParVector$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ParVector$.MODULE$.empty();
    }

    @Override
    public scala.collection.parallel.immutable.ParSeq<T> toSeq() {
        return scala.collection.parallel.immutable.ParSeq$class.toSeq(this);
    }

    @Override
    public ParIterable<T> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public String toString() {
        return ParSeq$class.toString(this);
    }

    @Override
    public String stringPrefix() {
        return ParSeq$class.stringPrefix(this);
    }

    @Override
    public /* synthetic */ Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
        return ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public PreciseSplitter<T> iterator() {
        return ParSeqLike$class.iterator(this);
    }

    @Override
    public int size() {
        return ParSeqLike$class.size(this);
    }

    @Override
    public int segmentLength(Function1<T, Object> p, int from2) {
        return ParSeqLike$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<T, Object> p, int from2) {
        return ParSeqLike$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<T, Object> p, int end) {
        return ParSeqLike$class.lastIndexWhere(this, p, end);
    }

    @Override
    public ParSeq reverse() {
        return ParSeqLike$class.reverse(this);
    }

    @Override
    public <S, That> That reverseMap(Function1<T, S> f, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParSeqLike$class.reverseMap(this, f, bf);
    }

    @Override
    public <S> boolean startsWith(GenSeq<S> that, int offset) {
        return ParSeqLike$class.startsWith(this, that, offset);
    }

    @Override
    public <U> boolean sameElements(GenIterable<U> that) {
        return ParSeqLike$class.sameElements(this, that);
    }

    @Override
    public <S> boolean endsWith(GenSeq<S> that) {
        return ParSeqLike$class.endsWith(this, that);
    }

    @Override
    public <U, That> That patch(int from2, GenSeq<U> patch2, int replaced, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParSeqLike$class.patch(this, from2, patch2, replaced, bf);
    }

    @Override
    public <U, That> That updated(int index, U elem, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParSeqLike$class.updated(this, index, elem, bf);
    }

    @Override
    public <U, That> That $plus$colon(U elem, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParSeqLike$class.$plus$colon(this, elem, bf);
    }

    @Override
    public <U, That> That $colon$plus(U elem, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParSeqLike$class.$colon$plus(this, elem, bf);
    }

    @Override
    public <U, That> That padTo(int len, U elem, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParSeqLike$class.padTo(this, len, elem, bf);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParVector<T>, Tuple2<U, S>, That> bf) {
        return (That)ParSeqLike$class.zip(this, that, bf);
    }

    @Override
    public <S> boolean corresponds(GenSeq<S> that, Function2<T, S, Object> p) {
        return ParSeqLike$class.corresponds(this, that, p);
    }

    @Override
    public ParSeq diff(GenSeq that) {
        return ParSeqLike$class.diff(this, that);
    }

    @Override
    public ParSeq intersect(GenSeq that) {
        return ParSeqLike$class.intersect(this, that);
    }

    @Override
    public ParSeq distinct() {
        return ParSeqLike$class.distinct(this);
    }

    @Override
    public Object view() {
        return ParSeqLike$class.view(this);
    }

    @Override
    public SeqSplitter<T> down(IterableSplitter<?> p) {
        return ParSeqLike$class.down(this, p);
    }

    @Override
    public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
        return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
    }

    @Override
    @TraitSetter
    public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
        this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
    }

    private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
        ParVector parVector = this;
        synchronized (parVector) {
            if (this.ScanNode$module == null) {
                this.ScanNode$module = new ParIterableLike$ScanNode$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanNode$module;
        }
    }

    @Override
    public ParIterableLike$ScanNode$ ScanNode() {
        return this.ScanNode$module == null ? this.ScanNode$lzycompute() : this.ScanNode$module;
    }

    private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
        ParVector parVector = this;
        synchronized (parVector) {
            if (this.ScanLeaf$module == null) {
                this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScanLeaf$module;
        }
    }

    @Override
    public ParIterableLike$ScanLeaf$ ScanLeaf() {
        return this.ScanLeaf$module == null ? this.ScanLeaf$lzycompute() : this.ScanLeaf$module;
    }

    @Override
    public void initTaskSupport() {
        ParIterableLike$class.initTaskSupport(this);
    }

    @Override
    public TaskSupport tasksupport() {
        return ParIterableLike$class.tasksupport(this);
    }

    @Override
    public void tasksupport_$eq(TaskSupport ts) {
        ParIterableLike$class.tasksupport_$eq(this, ts);
    }

    @Override
    public scala.collection.parallel.ParIterable repr() {
        return ParIterableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return ParIterableLike$class.isTraversableAgain(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return ParIterableLike$class.hasDefiniteSize(this);
    }

    @Override
    public boolean isEmpty() {
        return ParIterableLike$class.isEmpty(this);
    }

    @Override
    public boolean nonEmpty() {
        return ParIterableLike$class.nonEmpty(this);
    }

    @Override
    public T head() {
        return (T)ParIterableLike$class.head(this);
    }

    @Override
    public Option<T> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public T last() {
        return (T)ParIterableLike$class.last(this);
    }

    @Override
    public Option<T> lastOption() {
        return ParIterableLike$class.lastOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable init() {
        return ParIterableLike$class.init(this);
    }

    @Override
    public scala.collection.parallel.ParIterable par() {
        return ParIterableLike$class.par(this);
    }

    @Override
    public boolean isStrictSplitterCollection() {
        return ParIterableLike$class.isStrictSplitterCollection(this);
    }

    @Override
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        return ParIterableLike$class.reuse(this, oldc, newc);
    }

    @Override
    public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask<R, Tp> tsk) {
        return ParIterableLike$class.task2ops(this, tsk);
    }

    @Override
    public <R> Object wrap(Function0<R> body2) {
        return ParIterableLike$class.wrap(this, body2);
    }

    @Override
    public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(PI it) {
        return ParIterableLike$class.delegatedSignalling2ops(this, it);
    }

    @Override
    public <Elem, To> Object builder2ops(Builder<Elem, To> cb) {
        return ParIterableLike$class.builder2ops(this, cb);
    }

    @Override
    public <S, That> Object bf2seq(CanBuildFrom<ParVector<T>, S, That> bf) {
        return ParIterableLike$class.bf2seq(this, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable sequentially(Function1 b) {
        return ParIterableLike$class.sequentially(this, b);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return ParIterableLike$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return ParIterableLike$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return ParIterableLike$class.mkString(this);
    }

    @Override
    public boolean canEqual(Object other) {
        return ParIterableLike$class.canEqual(this, other);
    }

    @Override
    public <U> U reduce(Function2<U, U, U> op) {
        return (U)ParIterableLike$class.reduce(this, op);
    }

    @Override
    public <U> Option<U> reduceOption(Function2<U, U, U> op) {
        return ParIterableLike$class.reduceOption(this, op);
    }

    @Override
    public <U> U fold(U z, Function2<U, U, U> op) {
        return (U)ParIterableLike$class.fold(this, z, op);
    }

    @Override
    public <S> S aggregate(Function0<S> z, Function2<S, T, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, T, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<T, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, T, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<T, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<T, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<T, Object> p) {
        return ParIterableLike$class.count(this, p);
    }

    @Override
    public <U> U sum(Numeric<U> num) {
        return (U)ParIterableLike$class.sum(this, num);
    }

    @Override
    public <U> U product(Numeric<U> num) {
        return (U)ParIterableLike$class.product(this, num);
    }

    @Override
    public <U> T min(Ordering<U> ord) {
        return (T)ParIterableLike$class.min(this, ord);
    }

    @Override
    public <U> T max(Ordering<U> ord) {
        return (T)ParIterableLike$class.max(this, ord);
    }

    @Override
    public <S> T maxBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public <S> T minBy(Function1<T, S> f, Ordering<S> cmp) {
        return (T)ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That map(Function1<T, S> f, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<T, S> pf, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<T, GenTraversableOnce<S>> f, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<T, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<T, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<T> find(Function1<T, Object> p) {
        return ParIterableLike$class.find(this, p);
    }

    @Override
    public Object combinerFactory() {
        return ParIterableLike$class.combinerFactory(this);
    }

    @Override
    public <S, That> Object combinerFactory(Function0<Combiner<S, That>> cbf) {
        return ParIterableLike$class.combinerFactory(this, cbf);
    }

    @Override
    public scala.collection.parallel.ParIterable withFilter(Function1 pred) {
        return ParIterableLike$class.withFilter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filter(Function1 pred) {
        return ParIterableLike$class.filter(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable filterNot(Function1 pred) {
        return ParIterableLike$class.filterNot(this, pred);
    }

    @Override
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParVector<T>, ParVector<T>> partition(Function1<T, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> ParMap<K, ParVector<T>> groupBy(Function1<T, K> f) {
        return ParIterableLike$class.groupBy(this, f);
    }

    @Override
    public scala.collection.parallel.ParIterable take(int n) {
        return ParIterableLike$class.take(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable drop(int n) {
        return ParIterableLike$class.drop(this, n);
    }

    @Override
    public scala.collection.parallel.ParIterable slice(int unc_from, int unc_until) {
        return ParIterableLike$class.slice(this, unc_from, unc_until);
    }

    @Override
    public Tuple2<ParVector<T>, ParVector<T>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParVector<T>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, T, S> op, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<T, S, S> op, CanBuildFrom<ParVector<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParVector<T>, ParVector<T>> span(Function1<T, Object> pred) {
        return ParIterableLike$class.span(this, pred);
    }

    @Override
    public scala.collection.parallel.ParIterable dropWhile(Function1 pred) {
        return ParIterableLike$class.dropWhile(this, pred);
    }

    @Override
    public <U> void copyToArray(Object xs) {
        ParIterableLike$class.copyToArray(this, xs);
    }

    @Override
    public <U> void copyToArray(Object xs, int start) {
        ParIterableLike$class.copyToArray(this, xs, start);
    }

    @Override
    public <U> void copyToArray(Object xs, int start, int len) {
        ParIterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParVector<T>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParVector<T>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return (That)ParIterableLike$class.toParMap(this, cbf, ev);
    }

    @Override
    public <U> Object toArray(ClassTag<U> evidence$1) {
        return ParIterableLike$class.toArray(this, evidence$1);
    }

    @Override
    public List<T> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<T> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<T> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<T> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<T> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, T, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<T, S, S> op) {
        return (S)ParIterableLike$class.$colon$bslash(this, z, op);
    }

    @Override
    public String debugInformation() {
        return ParIterableLike$class.debugInformation(this);
    }

    @Override
    public Seq<String> brokenInvariants() {
        return ParIterableLike$class.brokenInvariants(this);
    }

    @Override
    public ArrayBuffer<String> debugBuffer() {
        return ParIterableLike$class.debugBuffer(this);
    }

    @Override
    public void debugclear() {
        ParIterableLike$class.debugclear(this);
    }

    @Override
    public ArrayBuffer<String> debuglog(String s2) {
        return ParIterableLike$class.debuglog(this, s2);
    }

    @Override
    public void printDebugBuffer() {
        ParIterableLike$class.printDebugBuffer(this);
    }

    @Override
    public Combiner<T, ParVector<T>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Builder<T, ParVector<T>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public Combiner<T, ParVector<T>> newCombiner() {
        return GenericParTemplate$class.newCombiner(this);
    }

    @Override
    public <B> Combiner<B, ParVector<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParVector<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParVector<A1>, ParVector<A2>> unzip(Function1<T, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParVector<A1>, ParVector<A2>, ParVector<A3>> unzip3(Function1<T, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public boolean isDefinedAt(int idx) {
        return GenSeqLike$class.isDefinedAt(this, idx);
    }

    @Override
    public int prefixLength(Function1<T, Object> p) {
        return GenSeqLike$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<T, Object> p) {
        return GenSeqLike$class.indexWhere(this, p);
    }

    @Override
    public <B> int indexOf(B elem) {
        return GenSeqLike$class.indexOf(this, elem);
    }

    @Override
    public <B> int indexOf(B elem, int from2) {
        return GenSeqLike$class.indexOf(this, elem, from2);
    }

    @Override
    public <B> int lastIndexOf(B elem) {
        return GenSeqLike$class.lastIndexOf(this, elem);
    }

    @Override
    public <B> int lastIndexOf(B elem, int end) {
        return GenSeqLike$class.lastIndexOf(this, elem, end);
    }

    @Override
    public int lastIndexWhere(Function1<T, Object> p) {
        return GenSeqLike$class.lastIndexWhere(this, p);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return GenSeqLike$class.startsWith(this, that);
    }

    @Override
    public <B, That> That union(GenSeq<B> that, CanBuildFrom<ParVector<T>, B, That> bf) {
        return (That)GenSeqLike$class.union(this, that, bf);
    }

    @Override
    public int hashCode() {
        return GenSeqLike$class.hashCode(this);
    }

    @Override
    public boolean equals(Object that) {
        return GenSeqLike$class.equals(this, that);
    }

    public ParVector$ companion() {
        return ParVector$.MODULE$;
    }

    @Override
    public T apply(int idx) {
        return this.vector.apply(idx);
    }

    @Override
    public int length() {
        return this.vector.length();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public SeqSplitter<T> splitter() {
        void var1_1;
        ParVectorIterator pit = new ParVectorIterator(this.vector.startIndex(), this.vector.endIndex());
        this.vector.initIterator(pit);
        return var1_1;
    }

    @Override
    public Vector<T> seq() {
        return this.vector;
    }

    @Override
    public Vector<T> toVector() {
        return this.vector;
    }

    public ParVector(Vector<T> vector) {
        this.vector = vector;
        Parallelizable$class.$init$(this);
        GenSeqLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenSeq$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        ParSeqLike$class.$init$(this);
        ParSeq$class.$init$(this);
        ParIterable$class.$init$(this);
        scala.collection.parallel.immutable.ParSeq$class.$init$(this);
    }

    public ParVector() {
        this((Vector)Vector$.MODULE$.apply(Nil$.MODULE$));
    }

    public class ParVectorIterator
    extends VectorIterator<T>
    implements SeqSplitter<T> {
        private Signalling signalDelegate;

        @Override
        public Seq<SeqSplitter<T>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public SeqSplitter.Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<T> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<T> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> SeqSplitter.Mapped<S> map(Function1<T, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> SeqSplitter.Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> SeqSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> SeqSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<T> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> SeqSplitter.Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<T, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public <S> boolean shouldSplitFurther(scala.collection.parallel.ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<T, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public <U> T min(Ordering<U> ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public int remaining() {
            return this.remainingElementCount();
        }

        @Override
        public SeqSplitter<T> dup() {
            return new ParVector(this.remainingVector()).splitter();
        }

        @Override
        public Seq<ParVectorIterator> split() {
            int rem = this.remaining();
            return rem >= 2 ? this.psplit(Predef$.MODULE$.wrapIntArray(new int[]{rem / 2, rem - rem / 2})) : (Seq<ParVectorIterator>)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParVectorIterator[]{this}));
        }

        @Override
        public Seq<ParVectorIterator> psplit(Seq<Object> sizes) {
            ObjectRef remvector = ObjectRef.create(this.remainingVector());
            ArrayBuffer splitted = new ArrayBuffer();
            sizes.foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final ObjectRef remvector$1;
                private final ArrayBuffer splitted$1;

                public final void apply(int sz) {
                    this.apply$mcVI$sp(sz);
                }

                public void apply$mcVI$sp(int sz) {
                    this.splitted$1.$plus$eq(((Vector)this.remvector$1.elem).take(sz));
                    this.remvector$1.elem = ((Vector)this.remvector$1.elem).drop(sz);
                }
                {
                    this.remvector$1 = remvector$1;
                    this.splitted$1 = splitted$1;
                }
            });
            return splitted.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final ParVectorIterator apply(Vector<T> v) {
                    return (ParVectorIterator)new ParVector<T>(v).splitter();
                }
            }, ArrayBuffer$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ ParVector scala$collection$parallel$immutable$ParVector$ParVectorIterator$$$outer() {
            return ParVector.this;
        }

        public ParVectorIterator(int _start, int _end) {
            if (ParVector.this == null) {
                throw null;
            }
            super(_start, _end);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }
}

