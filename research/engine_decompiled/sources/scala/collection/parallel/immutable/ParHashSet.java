/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.HashSet$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TrieIterator;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSet$class;
import scala.collection.parallel.ParSetLike$class;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.ParHashSet$;
import scala.collection.parallel.immutable.ParIterable;
import scala.collection.parallel.immutable.ParIterable$class;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005=h!B\u0001\u0003\u0001-Y#A\u0003)be\"\u000b7\u000f[*fi*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0011A\f'/\u00197mK2T!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001D\f\u0014\r\u0001i\u0011\u0003I\u00142!\tqq\"D\u0001\t\u0013\t\u0001\u0002B\u0001\u0004B]f\u0014VM\u001a\t\u0004%M)R\"\u0001\u0002\n\u0005Q\u0011!A\u0002)beN+G\u000f\u0005\u0002\u0017/1\u0001A!\u0002\r\u0001\u0005\u0004I\"!\u0001+\u0012\u0005ii\u0002C\u0001\b\u001c\u0013\ta\u0002BA\u0004O_RD\u0017N\\4\u0011\u00059q\u0012BA\u0010\t\u0005\r\te.\u001f\t\u0005C\u0011*b%D\u0001#\u0015\t\u0019c!A\u0004hK:,'/[2\n\u0005\u0015\u0012#AE$f]\u0016\u0014\u0018n\u0019)beR+W\u000e\u001d7bi\u0016\u0004\"A\u0005\u0001\u0011\u000b!JSc\u000b\u0017\u000e\u0003\u0011I!A\u000b\u0003\u0003\u0015A\u000b'oU3u\u0019&\\W\rE\u0002\u0013\u0001U\u00012!L\u0018\u0016\u001b\u0005q#BA\u0002\u0007\u0013\t\u0001dFA\u0004ICND7+\u001a;\u0011\u00059\u0011\u0014BA\u001a\t\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!)\u0004A!A!\u0002\u0013a\u0013\u0001\u0002;sS\u0016Daa\u000e\u0001\u0005\u0002\tA\u0014A\u0002\u001fj]&$h\b\u0006\u0002,s!)QG\u000ea\u0001Y!)q\u0007\u0001C\u0001wQ\t1\u0006C\u0003>\u0001\u0011\u0005c(A\u0005d_6\u0004\u0018M\\5p]V\tqHE\u0002A\u0005\u00163A!\u0011\u0001\u0001\u007f\taAH]3gS:,W.\u001a8u}A\u0019\u0011e\u0011\u0014\n\u0005\u0011\u0013#\u0001E$f]\u0016\u0014\u0018nY\"p[B\fg.[8o!\r\tcIJ\u0005\u0003\u000f\n\u00121cR3oKJL7\rU1s\u0007>l\u0007/\u00198j_:DQ!\u0013\u0001\u0005B)\u000bQ!Z7qif,\u0012a\u000b\u0005\u0006\u0019\u0002!\t!T\u0001\tgBd\u0017\u000e\u001e;feV\ta\nE\u0002)\u001fVI!\u0001\u0015\u0003\u0003!%#XM]1cY\u0016\u001c\u0006\u000f\\5ui\u0016\u0014\b\"\u0002*\u0001\t\u0003\u001a\u0016aA:fcV\tA\u0006C\u0003V\u0001\u0011\u0005a+\u0001\u0004%[&tWo\u001d\u000b\u0003W]CQ\u0001\u0017+A\u0002U\t\u0011!\u001a\u0005\u00065\u0002!\taW\u0001\u0006IAdWo\u001d\u000b\u0003WqCQ\u0001W-A\u0002UAQA\u0018\u0001\u0005\u0002}\u000b\u0001bY8oi\u0006Lgn\u001d\u000b\u0003A\u000e\u0004\"AD1\n\u0005\tD!a\u0002\"p_2,\u0017M\u001c\u0005\u00061v\u0003\r!\u0006\u0005\u0006K\u0002!\tEZ\u0001\u0005g&TX-F\u0001h!\tq\u0001.\u0003\u0002j\u0011\t\u0019\u0011J\u001c;\t\u000b-\u0004A\u0011\u000b7\u0002\u000bI,Wo]3\u0016\u00075\u0014X\u000fF\u0002oor\u0004B\u0001K8ri&\u0011\u0001\u000f\u0002\u0002\t\u0007>l'-\u001b8feB\u0011aC\u001d\u0003\u0006g*\u0014\r!\u0007\u0002\u0002'B\u0011a#\u001e\u0003\u0006m*\u0014\r!\u0007\u0002\u0005)\"\fG\u000fC\u0003yU\u0002\u0007\u00110\u0001\u0003pY\u0012\u001c\u0007c\u0001\b{]&\u00111\u0010\u0003\u0002\u0007\u001fB$\u0018n\u001c8\t\u000buT\u0007\u0019\u00018\u0002\t9,wo\u0019\u0004\u0006\u007f\u0002\u0001\u0011\u0011\u0001\u0002\u0013!\u0006\u0014\b*Y:i'\u0016$\u0018\n^3sCR|'oE\u0002\u007f\u001b9C!\"!\u0002\u007f\u0005\u0003\u0007I\u0011AA\u0004\u0003\u0019!(/\u001b;feV\u0011\u0011\u0011\u0002\t\u0006\u0003\u0017\t\t\"\u0006\b\u0004\u001d\u00055\u0011bAA\b\u0011\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\n\u0003+\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003\u001fA\u0001BCA\r}\n\u0005\r\u0011\"\u0001\u0002\u001c\u0005QAO]5uKJ|F%Z9\u0015\t\u0005u\u00111\u0005\t\u0004\u001d\u0005}\u0011bAA\u0011\u0011\t!QK\\5u\u0011)\t)#a\u0006\u0002\u0002\u0003\u0007\u0011\u0011B\u0001\u0004q\u0012\n\u0004BCA\u0015}\n\u0005\t\u0015)\u0003\u0002\n\u00059AO]5uKJ\u0004\u0003\"CA\u0017}\n\u0015\r\u0011\"\u0001g\u0003\t\u0019(\u0010C\u0005\u00022y\u0014\t\u0011)A\u0005O\u0006\u00191O\u001f\u0011\t\r]rH\u0011AA\u001b)\u0019\t9$a\u000f\u0002>A\u0019\u0011\u0011\b@\u000e\u0003\u0001A\u0001\"!\u0002\u00024\u0001\u0007\u0011\u0011\u0002\u0005\b\u0003[\t\u0019\u00041\u0001h\u0011!\t\tE a\u0001\n\u00031\u0017!A5\t\u0013\u0005\u0015c\u00101A\u0005\u0002\u0005\u001d\u0013!B5`I\u0015\fH\u0003BA\u000f\u0003\u0013B\u0011\"!\n\u0002D\u0005\u0005\t\u0019A4\t\u000f\u00055c\u0010)Q\u0005O\u0006\u0011\u0011\u000e\t\u0005\u0007\u0003#rH\u0011A'\u0002\u0007\u0011,\b\u000fC\u0004\u0002Vy$I!a\u0016\u0002\u001f\u0011,\bO\u0012:p[&#XM]1u_J$B!a\u000e\u0002Z!A\u00111LA*\u0001\u0004\tI!\u0001\u0002ji\"9\u0011q\f@\u0005\u0002\u0005\u0005\u0014!B:qY&$XCAA2!\u0015\tY!!\u001aO\u0013\u0011\t9'!\u0006\u0003\u0007M+\u0017\u000fC\u0004\u0002ly$\t!!\u001c\u0002\t9,\u0007\u0010\u001e\u000b\u0002+!9\u0011\u0011\u000f@\u0005\u0002\u0005M\u0014a\u00025bg:+\u0007\u0010^\u000b\u0002A\"1\u0011q\u000f@\u0005\u0002\u0019\f\u0011B]3nC&t\u0017N\\4)\u000f\u0001\tY(!!\u0002\u0004B\u0019a\"! \n\u0007\u0005}\u0004B\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0011aB\u0004\u0002\b\nA\t!!#\u0002\u0015A\u000b'\u000fS1tQN+G\u000fE\u0002\u0013\u0003\u00173a!\u0001\u0002\t\u0002\u000555#BAF\u0003\u001f\u000b\u0004\u0003B\u0011\u0002\u0012\u001aJ1!a%#\u00055\u0001\u0016M]*fi\u001a\u000b7\r^8ss\"9q'a#\u0005\u0002\u0005]ECAAE\u0011!\tY*a#\u0005\u0002\u0005u\u0015a\u00038fo\u000e{WNY5oKJ,B!a(\u0002&V\u0011\u0011\u0011\u0015\t\u0007Q=\f\u0019+a*\u0011\u0007Y\t)\u000b\u0002\u0004\u0019\u00033\u0013\r!\u0007\t\u0005%\u0001\t\u0019\u000b\u0003\u0005\u0002,\u0006-E1AAW\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0011\ty+!1\u0016\u0005\u0005E\u0006#C\u0011\u00024\u0006]\u0016qXAb\u0013\r\t)L\t\u0002\u000f\u0007\u0006t7i\\7cS:,gI]8n!\u0011\tI,a/\u000e\u0005\u0005-\u0015bAA_\u0007\n!1i\u001c7m!\r1\u0012\u0011\u0019\u0003\u00071\u0005%&\u0019A\r\u0011\tI\u0001\u0011q\u0018\u0005\t\u0003\u000f\fY\t\"\u0001\u0002J\u0006AaM]8n)JLW-\u0006\u0003\u0002L\u0006EG\u0003BAg\u0003'\u0004BA\u0005\u0001\u0002PB\u0019a#!5\u0005\ra\t)M1\u0001\u001a\u0011!\t).!2A\u0002\u0005]\u0017!\u0001;\u0011\t5z\u0013q\u001a\u0005\u000b\u00037\fY)!A\u0005\n\u0005u\u0017a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!a8\u0011\t\u0005\u0005\u00181^\u0007\u0003\u0003GTA!!:\u0002h\u0006!A.\u00198h\u0015\t\tI/\u0001\u0003kCZ\f\u0017\u0002BAw\u0003G\u0014aa\u00142kK\u000e$\b")
public class ParHashSet<T>
implements scala.collection.parallel.immutable.ParSet<T>,
Serializable {
    public static final long serialVersionUID = 1L;
    private final HashSet<T> trie;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    public static <T> ParHashSet<T> fromTrie(HashSet<T> hashSet) {
        return ParHashSet$.MODULE$.fromTrie(hashSet);
    }

    public static <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
        return ParHashSet$.MODULE$.canBuildFrom();
    }

    public static <A> Object setCanBuildFrom() {
        return ParHashSet$.MODULE$.setCanBuildFrom();
    }

    @Override
    public String stringPrefix() {
        return scala.collection.parallel.immutable.ParSet$class.stringPrefix(this);
    }

    @Override
    public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
        return scala.collection.parallel.immutable.ParSet$class.toSet(this);
    }

    @Override
    public ParIterable<T> toIterable() {
        return ParIterable$class.toIterable(this);
    }

    @Override
    public ParSeq<T> toSeq() {
        return ParIterable$class.toSeq(this);
    }

    @Override
    public ParSet union(GenSet that) {
        return ParSetLike$class.union(this, that);
    }

    @Override
    public ParSet diff(GenSet that) {
        return ParSetLike$class.diff(this, that);
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
        ParHashSet parHashSet = this;
        synchronized (parHashSet) {
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
        ParHashSet parHashSet = this;
        synchronized (parHashSet) {
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
    public Splitter<T> iterator() {
        return ParIterableLike$class.iterator(this);
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
    public <S, That> Object bf2seq(CanBuildFrom<ParHashSet<T>, S, That> bf) {
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
    public String toString() {
        return ParIterableLike$class.toString(this);
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
    public <S, That> That map(Function1<T, S> f, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<T, S> pf, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<T, GenTraversableOnce<S>> f, CanBuildFrom<ParHashSet<T>, S, That> bf) {
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
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParHashSet<T>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<ParHashSet<T>, ParHashSet<T>> partition(Function1<T, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> ParMap<K, ParHashSet<T>> groupBy(Function1<T, K> f) {
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
    public Tuple2<ParHashSet<T>, ParHashSet<T>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParHashSet<T>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, T, S> op, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<T, S, S> op, CanBuildFrom<ParHashSet<T>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<ParHashSet<T>, ParHashSet<T>> span(Function1<T, Object> pred) {
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
    public <U> boolean sameElements(GenIterable<U> that) {
        return ParIterableLike$class.sameElements(this, that);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParHashSet<T>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zip(this, that, bf);
    }

    @Override
    public <U, That> That zipWithIndex(CanBuildFrom<ParHashSet<T>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParHashSet<T>, Tuple2<U, S>, That> bf) {
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
    public Object view() {
        return ParIterableLike$class.view(this);
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
    public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less<T, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public Vector<T> toVector() {
        return ParIterableLike$class.toVector(this);
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
    public Combiner<T, ParHashSet<T>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Builder<T, ParHashSet<T>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public Combiner<T, ParHashSet<T>> newCombiner() {
        return GenericParTemplate$class.newCombiner(this);
    }

    @Override
    public <B> Combiner<B, ParHashSet<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, ParHashSet<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<ParHashSet<A1>, ParHashSet<A2>> unzip(Function1<T, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<ParHashSet<A1>, ParHashSet<A2>, ParHashSet<A3>> unzip3(Function1<T, Tuple3<A1, A2, A3>> asTriple) {
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
    public boolean apply(T elem) {
        return GenSetLike$class.apply(this, elem);
    }

    @Override
    public Object intersect(GenSet that) {
        return GenSetLike$class.intersect(this, that);
    }

    @Override
    public Object $amp(GenSet that) {
        return GenSetLike$class.$amp(this, that);
    }

    @Override
    public Object $bar(GenSet that) {
        return GenSetLike$class.$bar(this, that);
    }

    @Override
    public Object $amp$tilde(GenSet that) {
        return GenSetLike$class.$amp$tilde(this, that);
    }

    @Override
    public boolean subsetOf(GenSet<T> that) {
        return GenSetLike$class.subsetOf(this, that);
    }

    @Override
    public boolean equals(Object that) {
        return GenSetLike$class.equals(this, that);
    }

    @Override
    public int hashCode() {
        return GenSetLike$class.hashCode(this);
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, Object> compose(Function1<A, T> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<T, A> andThen(Function1<Object, A> g) {
        return Function1$class.andThen(this, g);
    }

    @Override
    public GenericCompanion<ParHashSet> companion() {
        return ParHashSet$.MODULE$;
    }

    @Override
    public ParHashSet<T> empty() {
        return new ParHashSet<T>();
    }

    @Override
    public IterableSplitter<T> splitter() {
        return new ParHashSetIterator(this, this.trie.iterator(), this.trie.size());
    }

    @Override
    public HashSet<T> seq() {
        return this.trie;
    }

    @Override
    public ParHashSet<T> $minus(T e) {
        return new ParHashSet<T>(this.trie.$minus((Object)e));
    }

    @Override
    public ParHashSet<T> $plus(T e) {
        return new ParHashSet<T>(this.trie.$plus((Object)e));
    }

    @Override
    public boolean contains(T e) {
        return this.trie.contains(e);
    }

    @Override
    public int size() {
        return this.trie.size();
    }

    @Override
    public <S, That> Combiner<S, That> reuse(Option<Combiner<S, That>> oldc, Combiner<S, That> newc) {
        block4: {
            Combiner combiner;
            block3: {
                block2: {
                    if (!(oldc instanceof Some)) break block2;
                    Some some = (Some)oldc;
                    combiner = (Combiner)some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(oldc)) break block4;
                combiner = newc;
            }
            return combiner;
        }
        throw new MatchError(oldc);
    }

    public ParHashSet(HashSet<T> trie) {
        this.trie = trie;
        Parallelizable$class.$init$(this);
        Function1$class.$init$(this);
        GenSetLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        GenIterable$class.$init$(this);
        GenericSetTemplate$class.$init$(this);
        GenSet$class.$init$(this);
        GenericParTemplate$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        ParIterableLike$class.$init$(this);
        scala.collection.parallel.ParIterable$class.$init$(this);
        ParSetLike$class.$init$(this);
        ParSet$class.$init$(this);
        ParIterable$class.$init$(this);
        scala.collection.parallel.immutable.ParSet$class.$init$(this);
    }

    public ParHashSet() {
        this((HashSet)HashSet$.MODULE$.empty());
    }

    public class ParHashSetIterator
    implements IterableSplitter<T> {
        private Iterator<T> triter;
        private final int sz;
        private int i;
        public final /* synthetic */ ParHashSet $outer;
        private Signalling signalDelegate;

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
        public Seq<IterableSplitter<T>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
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
        public IterableSplitter.Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<T> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<T> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> IterableSplitter.Mapped<S> map(Function1<T, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> IterableSplitter.Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> IterableSplitter.ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
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
        public Iterator<T> seq() {
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
        public Iterator<T> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<T> filter(Function1<T, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<T> withFilter(Function1<T, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<T> filterNot(Function1<T, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<T> takeWhile(Function1<T, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<T> dropWhile(Function1<T, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<T, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<T, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<T, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<T> find(Function1<T, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<T, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<T> buffered() {
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
        public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<T> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<T> toStream() {
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
        public List<T> reversed() {
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
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
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
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<T> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<T> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
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
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
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

        public Iterator<T> triter() {
            return this.triter;
        }

        public void triter_$eq(Iterator<T> x$1) {
            this.triter = x$1;
        }

        public int sz() {
            return this.sz;
        }

        public int i() {
            return this.i;
        }

        public void i_$eq(int x$1) {
            this.i = x$1;
        }

        @Override
        public IterableSplitter<T> dup() {
            ParHashSetIterator parHashSetIterator;
            Iterator iterator2 = this.triter();
            if (iterator2 instanceof TrieIterator) {
                TrieIterator trieIterator = (TrieIterator)iterator2;
                parHashSetIterator = this.dupFromIterator(trieIterator.dupIterator());
            } else {
                Buffer buff = this.triter().toBuffer();
                this.triter_$eq(buff.iterator());
                parHashSetIterator = this.dupFromIterator(buff.iterator());
            }
            return parHashSetIterator;
        }

        /*
         * WARNING - void declaration
         */
        private ParHashSetIterator dupFromIterator(Iterator<T> it) {
            void var2_2;
            ParHashSetIterator phit = new ParHashSetIterator(this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), it, this.sz());
            phit.i_$eq(this.i());
            return var2_2;
        }

        @Override
        public Seq<IterableSplitter<T>> split() {
            Tuple2 tuple2;
            block7: {
                Seq seq;
                block3: {
                    Seq seq2;
                    block6: {
                        block4: {
                            Tuple2 tuple22;
                            block5: {
                                block2: {
                                    if (this.remaining() >= 2) break block2;
                                    seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParHashSetIterator[]{this}));
                                    break block3;
                                }
                                Iterator iterator2 = this.triter();
                                if (!(iterator2 instanceof TrieIterator)) break block4;
                                TrieIterator trieIterator = (TrieIterator)iterator2;
                                int previousRemaining = this.remaining();
                                tuple22 = trieIterator.split();
                                if (tuple22 == null || tuple22._1() == null) break block5;
                                Tuple3 tuple3 = new Tuple3(tuple22._1()._1(), BoxesRunTime.boxToInteger(tuple22._1()._2$mcI$sp()), tuple22._2());
                                Iterator fst = tuple3._1();
                                int fstlength = BoxesRunTime.unboxToInt(tuple3._2());
                                Iterator snd = tuple3._3();
                                int sndlength = previousRemaining - fstlength;
                                seq2 = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParHashSetIterator[]{new ParHashSetIterator(this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), fst, fstlength), new ParHashSetIterator(this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), snd, sndlength)}));
                                break block6;
                            }
                            throw new MatchError(tuple22);
                        }
                        Buffer buff = this.triter().toBuffer();
                        tuple2 = buff.splitAt(buff.length() / 2);
                        if (tuple2 == null) break block7;
                        Tuple2 tuple23 = new Tuple2(tuple2._1(), tuple2._2());
                        Buffer fp = (Buffer)tuple23._1();
                        Buffer sp2 = (Buffer)tuple23._2();
                        seq2 = ((TraversableLike)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Buffer[]{fp, sp2}))).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ ParHashSetIterator $outer;

                            public final ParHashSetIterator apply(Buffer<T> b) {
                                return new ParHashSetIterator(this.$outer.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), b.iterator(), b.length());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, Seq$.MODULE$.canBuildFrom());
                    }
                    seq = seq2;
                }
                return seq;
            }
            throw new MatchError(tuple2);
        }

        @Override
        public T next() {
            this.i_$eq(this.i() + 1);
            return this.triter().next();
        }

        @Override
        public boolean hasNext() {
            return this.i() < this.sz();
        }

        @Override
        public int remaining() {
            return this.sz() - this.i();
        }

        public /* synthetic */ ParHashSet scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer() {
            return this.$outer;
        }

        public ParHashSetIterator(ParHashSet<T> $outer, Iterator<T> triter, int sz) {
            this.triter = triter;
            this.sz = sz;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            this.i = 0;
        }
    }
}

