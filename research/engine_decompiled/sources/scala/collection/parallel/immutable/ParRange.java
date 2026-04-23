/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Iterator$class;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
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
import scala.collection.parallel.immutable.ParRange$ParRangeIterator$;
import scala.collection.parallel.immutable.ParSet;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u00055g\u0001B\u0001\u0003\u0001-\u0011\u0001\u0002U1s%\u0006tw-\u001a\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u001dA\u0011AC2pY2,7\r^5p]*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001a\u0001c\u0006\t\u0003\u001b9i\u0011\u0001C\u0005\u0003\u001f!\u0011a!\u00118z%\u00164\u0007cA\t\u0013)5\t!!\u0003\u0002\u0014\u0005\t1\u0001+\u0019:TKF\u0004\"!D\u000b\n\u0005YA!aA%oiB\u0011Q\u0002G\u0005\u00033!\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001b\u0007\u0001\u0003\u0006\u0004%\t\u0001H\u0001\u0006e\u0006tw-Z\u000b\u0002;A\u0011a\u0004I\u0007\u0002?)\u00111AB\u0005\u0003C}\u0011QAU1oO\u0016D\u0001b\t\u0001\u0003\u0002\u0003\u0006I!H\u0001\u0007e\u0006tw-\u001a\u0011\t\u000b\u0015\u0002A\u0011\u0001\u0014\u0002\rqJg.\u001b;?)\t9\u0003\u0006\u0005\u0002\u0012\u0001!)1\u0004\na\u0001;!)!\u0006\u0001C!9\u0005\u00191/Z9\t\u000b1\u0002AQA\u0017\u0002\r1,gn\u001a;i+\u0005!\u0002FA\u00160!\ti\u0001'\u0003\u00022\u0011\t1\u0011N\u001c7j]\u0016DQa\r\u0001\u0005\u0006Q\nQ!\u00199qYf$\"\u0001F\u001b\t\u000bY\u0012\u0004\u0019\u0001\u000b\u0002\u0007%$\u0007\u0010\u000b\u00023_!)\u0011\b\u0001C\u0001u\u0005A1\u000f\u001d7jiR,'/F\u0001<!\taT(D\u0001\u0001\r\u0011q\u0004\u0001A \u0003!A\u000b'OU1oO\u0016LE/\u001a:bi>\u00148cA\u001f\r\u0001B\u0019\u0011I\u0011\u000b\u000e\u0003\u0011I!a\u0011\u0003\u0003\u0017M+\u0017o\u00159mSR$XM\u001d\u0005\t7u\u0012\t\u0011)A\u0005;!)Q%\u0010C\u0001\rR\u00111h\u0012\u0005\b7\u0015\u0003\n\u00111\u0001\u001e\u0011\u0015IU\b\"\u0011K\u0003!!xn\u0015;sS:<G#A&\u0011\u00051\u000bV\"A'\u000b\u00059{\u0015\u0001\u00027b]\u001eT\u0011\u0001U\u0001\u0005U\u00064\u0018-\u0003\u0002S\u001b\n11\u000b\u001e:j]\u001eDq\u0001V\u001fA\u0002\u0013%Q&A\u0002j]\u0012DqAV\u001fA\u0002\u0013%q+A\u0004j]\u0012|F%Z9\u0015\u0005a[\u0006CA\u0007Z\u0013\tQ\u0006B\u0001\u0003V]&$\bb\u0002/V\u0003\u0003\u0005\r\u0001F\u0001\u0004q\u0012\n\u0004B\u00020>A\u0003&A#\u0001\u0003j]\u0012\u0004\u0003b\u00021>\u0005\u0004%I!L\u0001\u0004Y\u0016t\u0007B\u00022>A\u0003%A#\u0001\u0003mK:\u0004\u0003\"\u00023>\t\u000bi\u0013!\u0003:f[\u0006Lg.\u001b8h\u0011\u00151W\b\"\u0002h\u0003\u001dA\u0017m\u001d(fqR,\u0012\u0001\u001b\t\u0003\u001b%L!A\u001b\u0005\u0003\u000f\t{w\u000e\\3b]\")A.\u0010C\u0003[\u0006!a.\u001a=u)\u0005!\u0002\"B8>\t\u0013a\u0012!\u0003:b]\u001e,G.\u001a4u\u0011\u0015\tX\b\"\u0001;\u0003\r!W\u000f\u001d\u0005\u0006gv\"\t\u0001^\u0001\u0006gBd\u0017\u000e^\u000b\u0002kB\u0019a/\u001f!\u000f\u000559\u0018B\u0001=\t\u0003\u001d\u0001\u0018mY6bO\u0016L!A_>\u0003\u0007M+\u0017O\u0003\u0002y\u0011!)Q0\u0010C\u0001}\u00061\u0001o\u001d9mSR$\"!^@\t\u000f\u0005\u0005A\u00101\u0001\u0002\u0004\u0005)1/\u001b>fgB!Q\"!\u0002\u0015\u0013\r\t9\u0001\u0003\u0002\u000byI,\u0007/Z1uK\u0012t\u0004bBA\u0006{\u0011\u0005\u0013QB\u0001\bM>\u0014X-Y2i+\u0011\ty!a\b\u0015\u0007a\u000b\t\u0002\u0003\u0005\u0002\u0014\u0005%\u0001\u0019AA\u000b\u0003\u00051\u0007CB\u0007\u0002\u0018Q\tY\"C\u0002\u0002\u001a!\u0011\u0011BR;oGRLwN\\\u0019\u0011\t\u0005u\u0011q\u0004\u0007\u0001\t!\t\t#!\u0003C\u0002\u0005\r\"!A+\u0012\t\u0005\u0015\u00121\u0006\t\u0004\u001b\u0005\u001d\u0012bAA\u0015\u0011\t9aj\u001c;iS:<\u0007cA\u0007\u0002.%\u0019\u0011q\u0006\u0005\u0003\u0007\u0005s\u0017\u0010C\u0004\u00024u\"\t%!\u000e\u0002\rI,G-^2f+\u0011\t9$a\u000f\u0015\t\u0005e\u0012q\b\t\u0005\u0003;\tY\u0004\u0002\u0005\u0002\"\u0005E\"\u0019AA\u001f#\r!\u00121\u0006\u0005\t\u0003\u0003\n\t\u00041\u0001\u0002D\u0005\u0011q\u000e\u001d\t\n\u001b\u0005\u0015\u0013\u0011HA\u001d\u0003sI1!a\u0012\t\u0005%1UO\\2uS>t'\u0007C\u0004\u0002Lu\"\t%!\u0014\u0002\u00195\f\u0007OM2p[\nLg.\u001a:\u0016\r\u0005=\u0013\u0011LA0)\u0019\t\t&a\u0019\u0002hA9\u0011)a\u0015\u0002X\u0005u\u0013bAA+\t\tA1i\\7cS:,'\u000f\u0005\u0003\u0002\u001e\u0005eC\u0001CA.\u0003\u0013\u0012\r!a\t\u0003\u0003M\u0003B!!\b\u0002`\u0011A\u0011\u0011MA%\u0005\u0004\t\u0019C\u0001\u0003UQ\u0006$\b\u0002CA\n\u0003\u0013\u0002\r!!\u001a\u0011\r5\t9\u0002FA,\u0011!\tI'!\u0013A\u0002\u0005E\u0013AA2c\u000f%\ti\u0007AA\u0001\u0012\u0003\ty'\u0001\tQCJ\u0014\u0016M\\4f\u0013R,'/\u0019;peB\u0019A(!\u001d\u0007\u0011y\u0002\u0011\u0011!E\u0001\u0003g\u001a2!!\u001d\r\u0011\u001d)\u0013\u0011\u000fC\u0001\u0003o\"\"!a\u001c\t\u0015\u0005m\u0014\u0011OI\u0001\n\u0003\ti(A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u000b\u0003\u0003\u007fR3!HAAW\t\t\u0019\t\u0005\u0003\u0002\u0006\u0006=UBAAD\u0015\u0011\tI)a#\u0002\u0013Ut7\r[3dW\u0016$'bAAG\u0011\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005E\u0015q\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007f\u0002\u0001\u0002\u0016\u0006m\u0015Q\u0014\t\u0004\u001b\u0005]\u0015bAAM\u0011\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u001d9\u0011\u0011\u0015\u0002\t\u0002\u0005\r\u0016\u0001\u0003)beJ\u000bgnZ3\u0011\u0007E\t)K\u0002\u0004\u0002\u0005!\u0005\u0011qU\n\u0005\u0003Kcq\u0003C\u0004&\u0003K#\t!a+\u0015\u0005\u0005\r\u0006bB\u001a\u0002&\u0012\u0005\u0011q\u0016\u000b\nO\u0005E\u0016QWA]\u0003{Cq!a-\u0002.\u0002\u0007A#A\u0003ti\u0006\u0014H\u000fC\u0004\u00028\u00065\u0006\u0019\u0001\u000b\u0002\u0007\u0015tG\rC\u0004\u0002<\u00065\u0006\u0019\u0001\u000b\u0002\tM$X\r\u001d\u0005\b\u0003\u007f\u000bi\u000b1\u0001i\u0003%Ign\u00197vg&4X\r\u0003\u0006\u0002D\u0006\u0015\u0016\u0011!C\u0005\u0003\u000b\f1B]3bIJ+7o\u001c7wKR\u0011\u0011q\u0019\t\u0004\u0019\u0006%\u0017bAAf\u001b\n1qJ\u00196fGR\u0004")
public class ParRange
implements scala.collection.parallel.immutable.ParSeq<Object>,
Serializable {
    public static final long serialVersionUID = 1L;
    private final Range range;
    private volatile ParRange$ParRangeIterator$ ParRangeIterator$module;
    private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
    private volatile ParIterableLike$ScanNode$ ScanNode$module;
    private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

    private ParRange$ParRangeIterator$ ParRangeIterator$lzycompute() {
        ParRange parRange = this;
        synchronized (parRange) {
            if (this.ParRangeIterator$module == null) {
                this.ParRangeIterator$module = new ParRange$ParRangeIterator$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ParRangeIterator$module;
        }
    }

    @Override
    public GenericCompanion<scala.collection.parallel.immutable.ParSeq> companion() {
        return scala.collection.parallel.immutable.ParSeq$class.companion(this);
    }

    @Override
    public scala.collection.parallel.immutable.ParSeq<Object> toSeq() {
        return scala.collection.parallel.immutable.ParSeq$class.toSeq(this);
    }

    @Override
    public ParIterable<Object> toIterable() {
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
    public PreciseSplitter<Object> iterator() {
        return ParSeqLike$class.iterator(this);
    }

    @Override
    public int size() {
        return ParSeqLike$class.size(this);
    }

    @Override
    public int segmentLength(Function1<Object, Object> p, int from2) {
        return ParSeqLike$class.segmentLength(this, p, from2);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p, int from2) {
        return ParSeqLike$class.indexWhere(this, p, from2);
    }

    @Override
    public int lastIndexWhere(Function1<Object, Object> p, int end) {
        return ParSeqLike$class.lastIndexWhere(this, p, end);
    }

    @Override
    public ParSeq reverse() {
        return ParSeqLike$class.reverse(this);
    }

    @Override
    public <S, That> That reverseMap(Function1<Object, S> f, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
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
    public <U, That> That patch(int from2, GenSeq<U> patch2, int replaced, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParSeqLike$class.patch(this, from2, patch2, replaced, bf);
    }

    @Override
    public <U, That> That updated(int index, U elem, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParSeqLike$class.updated(this, index, elem, bf);
    }

    @Override
    public <U, That> That $plus$colon(U elem, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParSeqLike$class.$plus$colon(this, elem, bf);
    }

    @Override
    public <U, That> That $colon$plus(U elem, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParSeqLike$class.$colon$plus(this, elem, bf);
    }

    @Override
    public <U, That> That padTo(int len, U elem, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParSeqLike$class.padTo(this, len, elem, bf);
    }

    @Override
    public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, Tuple2<U, S>, That> bf) {
        return (That)ParSeqLike$class.zip(this, that, bf);
    }

    @Override
    public <S> boolean corresponds(GenSeq<S> that, Function2<Object, S, Object> p) {
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
    public SeqSplitter<Object> down(IterableSplitter<?> p) {
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
        ParRange parRange = this;
        synchronized (parRange) {
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
        ParRange parRange = this;
        synchronized (parRange) {
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
    public Object head() {
        return ParIterableLike$class.head(this);
    }

    @Override
    public Option<Object> headOption() {
        return ParIterableLike$class.headOption(this);
    }

    @Override
    public scala.collection.parallel.ParIterable tail() {
        return ParIterableLike$class.tail(this);
    }

    @Override
    public Object last() {
        return ParIterableLike$class.last(this);
    }

    @Override
    public Option<Object> lastOption() {
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
    public <S, That> Object bf2seq(CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
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
    public <S> S aggregate(Function0<S> z, Function2<S, Object, S> seqop, Function2<S, S, S> combop) {
        return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <S> S foldLeft(S z, Function2<S, Object, S> op) {
        return (S)ParIterableLike$class.foldLeft(this, z, op);
    }

    @Override
    public <S> S foldRight(S z, Function2<Object, S, S> op) {
        return (S)ParIterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <U> U reduceLeft(Function2<U, Object, U> op) {
        return (U)ParIterableLike$class.reduceLeft(this, op);
    }

    @Override
    public <U> U reduceRight(Function2<Object, U, U> op) {
        return (U)ParIterableLike$class.reduceRight(this, op);
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, Object, U> op) {
        return ParIterableLike$class.reduceLeftOption(this, op);
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<Object, U, U> op) {
        return ParIterableLike$class.reduceRightOption(this, op);
    }

    @Override
    public <U> void foreach(Function1<Object, U> f) {
        ParIterableLike$class.foreach(this, f);
    }

    @Override
    public int count(Function1<Object, Object> p) {
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
    public Object min(Ordering ord) {
        return ParIterableLike$class.min(this, ord);
    }

    @Override
    public Object max(Ordering ord) {
        return ParIterableLike$class.max(this, ord);
    }

    @Override
    public Object maxBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.maxBy(this, f, cmp);
    }

    @Override
    public Object minBy(Function1 f, Ordering cmp) {
        return ParIterableLike$class.minBy(this, f, cmp);
    }

    @Override
    public <S, That> That map(Function1<Object, S> f, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
        return (That)ParIterableLike$class.map(this, f, bf);
    }

    @Override
    public <S, That> That collect(PartialFunction<Object, S> pf, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
        return (That)ParIterableLike$class.collect(this, pf, bf);
    }

    @Override
    public <S, That> That flatMap(Function1<Object, GenTraversableOnce<S>> f, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
        return (That)ParIterableLike$class.flatMap(this, f, bf);
    }

    @Override
    public boolean forall(Function1<Object, Object> p) {
        return ParIterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<Object, Object> p) {
        return ParIterableLike$class.exists(this, p);
    }

    @Override
    public Option<Object> find(Function1<Object, Object> p) {
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
    public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParIterableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public Tuple2<scala.collection.parallel.immutable.ParSeq<Object>, scala.collection.parallel.immutable.ParSeq<Object>> partition(Function1<Object, Object> pred) {
        return ParIterableLike$class.partition(this, pred);
    }

    @Override
    public <K> ParMap<K, scala.collection.parallel.immutable.ParSeq<Object>> groupBy(Function1<Object, K> f) {
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
    public Tuple2<scala.collection.parallel.immutable.ParSeq<Object>, scala.collection.parallel.immutable.ParSeq<Object>> splitAt(int n) {
        return ParIterableLike$class.splitAt(this, n);
    }

    @Override
    public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, U, That> bf) {
        return (That)ParIterableLike$class.scan(this, z, op, bf);
    }

    @Override
    public <S, That> That scanLeft(S z, Function2<S, Object, S> op, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
        return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <S, That> That scanRight(S z, Function2<Object, S, S> op, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, S, That> bf) {
        return (That)ParIterableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public scala.collection.parallel.ParIterable takeWhile(Function1 pred) {
        return ParIterableLike$class.takeWhile(this, pred);
    }

    @Override
    public Tuple2<scala.collection.parallel.immutable.ParSeq<Object>, scala.collection.parallel.immutable.ParSeq<Object>> span(Function1<Object, Object> pred) {
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
    public <U, That> That zipWithIndex(CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, Tuple2<U, Object>, That> bf) {
        return (That)ParIterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, Tuple2<U, S>, That> bf) {
        return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
        return (That)ParIterableLike$class.toParCollection(this, cbf);
    }

    @Override
    public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Object, Tuple2<K, V>> ev) {
        return (That)ParIterableLike$class.toParMap(this, cbf, ev);
    }

    @Override
    public <U> Object toArray(ClassTag<U> evidence$1) {
        return ParIterableLike$class.toArray(this, evidence$1);
    }

    @Override
    public List<Object> toList() {
        return ParIterableLike$class.toList(this);
    }

    @Override
    public IndexedSeq<Object> toIndexedSeq() {
        return ParIterableLike$class.toIndexedSeq(this);
    }

    @Override
    public Stream<Object> toStream() {
        return ParIterableLike$class.toStream(this);
    }

    @Override
    public Iterator<Object> toIterator() {
        return ParIterableLike$class.toIterator(this);
    }

    @Override
    public <U> Buffer<U> toBuffer() {
        return ParIterableLike$class.toBuffer(this);
    }

    @Override
    public GenTraversable<Object> toTraversable() {
        return ParIterableLike$class.toTraversable(this);
    }

    @Override
    public <U> ParSet<U> toSet() {
        return ParIterableLike$class.toSet(this);
    }

    @Override
    public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less<Object, Tuple2<K, V>> ev) {
        return ParIterableLike$class.toMap(this, ev);
    }

    @Override
    public Vector<Object> toVector() {
        return ParIterableLike$class.toVector(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, Object, Col> cbf) {
        return (Col)ParIterableLike$class.to(this, cbf);
    }

    @Override
    public int scanBlockSize() {
        return ParIterableLike$class.scanBlockSize(this);
    }

    @Override
    public <S> S $div$colon(S z, Function2<S, Object, S> op) {
        return (S)ParIterableLike$class.$div$colon(this, z, op);
    }

    @Override
    public <S> S $colon$bslash(S z, Function2<Object, S, S> op) {
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
    public Combiner<Object, scala.collection.parallel.immutable.ParSeq<Object>> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public Builder<Object, scala.collection.parallel.immutable.ParSeq<Object>> newBuilder() {
        return GenericParTemplate$class.newBuilder(this);
    }

    @Override
    public Combiner<Object, scala.collection.parallel.immutable.ParSeq<Object>> newCombiner() {
        return GenericParTemplate$class.newCombiner(this);
    }

    @Override
    public <B> Combiner<B, scala.collection.parallel.immutable.ParSeq<B>> genericBuilder() {
        return GenericParTemplate$class.genericBuilder(this);
    }

    @Override
    public <B> Combiner<B, scala.collection.parallel.immutable.ParSeq<B>> genericCombiner() {
        return GenericParTemplate$class.genericCombiner(this);
    }

    @Override
    public <A1, A2> Tuple2<scala.collection.parallel.immutable.ParSeq<A1>, scala.collection.parallel.immutable.ParSeq<A2>> unzip(Function1<Object, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<scala.collection.parallel.immutable.ParSeq<A1>, scala.collection.parallel.immutable.ParSeq<A2>, scala.collection.parallel.immutable.ParSeq<A3>> unzip3(Function1<Object, Tuple3<A1, A2, A3>> asTriple) {
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
    public int prefixLength(Function1<Object, Object> p) {
        return GenSeqLike$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<Object, Object> p) {
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
    public int lastIndexWhere(Function1<Object, Object> p) {
        return GenSeqLike$class.lastIndexWhere(this, p);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return GenSeqLike$class.startsWith(this, that);
    }

    @Override
    public <B, That> That union(GenSeq<B> that, CanBuildFrom<scala.collection.parallel.immutable.ParSeq<Object>, B, That> bf) {
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

    public Range range() {
        return this.range;
    }

    @Override
    public Range seq() {
        return this.range();
    }

    @Override
    public final int length() {
        return this.range().length();
    }

    @Override
    public final int apply(int idx) {
        return this.range().apply$mcII$sp(idx);
    }

    public ParRangeIterator splitter() {
        return new ParRangeIterator(this, this.ParRangeIterator().$lessinit$greater$default$1());
    }

    public ParRange$ParRangeIterator$ ParRangeIterator() {
        return this.ParRangeIterator$module == null ? this.ParRangeIterator$lzycompute() : this.ParRangeIterator$module;
    }

    public ParRange(Range range2) {
        this.range = range2;
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

    public class ParRangeIterator
    implements SeqSplitter<Object> {
        private final Range range;
        private int ind;
        private final int len;
        public final /* synthetic */ ParRange $outer;
        private Signalling signalDelegate;

        @Override
        public Seq<SeqSplitter<Object>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<Object>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public SeqSplitter.Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<Object> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<Object> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> SeqSplitter.Mapped<S> map(Function1<Object, S> f) {
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
        public SeqSplitter<Object> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> SeqSplitter.Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<Object, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<Object, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<Object, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<Object, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<Object, S> f, Combiner<S, That> cb) {
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
        public int count(Function1<Object, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
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
        public Object min(Ordering ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public Object max(Ordering ord) {
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
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<Object, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<Object, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<Object, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<Object, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<Object, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
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
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<Object, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<Object, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
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
        public Iterator<Object> seq() {
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
        public Iterator<Object> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<Object, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<Object> filter(Function1<Object, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Object, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<Object> withFilter(Function1<Object, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<Object> filterNot(Function1<Object, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<Object, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, Object, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<Object, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<Object> takeWhile(Function1<Object, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1<Object, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1<Object, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<Object> dropWhile(Function1<Object, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<Object, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<Object, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public boolean forall(Function1<Object, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Object, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<Object> find(Function1<Object, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<Object> buffered() {
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
        public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
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
        public Traversable<Object> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<Object> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<Object> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<Object> reversed() {
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
        public <B> Option<B> collectFirst(PartialFunction<Object, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, Object, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<Object, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, Object, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<Object, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, Object, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<Object, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, Object, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<Object, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, Object, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
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
        public List<Object> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<Object> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<Object> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<Object> toIndexedSeq() {
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
        public Vector<Object> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Object, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Object, Tuple2<T, U>> ev) {
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

        @Override
        public String toString() {
            return new StringBuilder().append((Object)"ParRangeIterator(over: ").append(this.range).append((Object)")").toString();
        }

        private int ind() {
            return this.ind;
        }

        private void ind_$eq(int x$1) {
            this.ind = x$1;
        }

        private int len() {
            return this.len;
        }

        @Override
        public final int remaining() {
            return this.len() - this.ind();
        }

        @Override
        public final boolean hasNext() {
            return this.ind() < this.len();
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public final int next() {
            int n;
            if (this.hasNext()) {
                void var1_1;
                int r = this.range.apply$mcII$sp(this.ind());
                this.ind_$eq(this.ind() + 1);
                n = var1_1;
            } else {
                n = BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
            }
            return n;
        }

        private Range rangeleft() {
            return this.range.drop(this.ind());
        }

        public ParRangeIterator dup() {
            return new ParRangeIterator(this.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), this.rangeleft());
        }

        @Override
        public Seq<SeqSplitter<Object>> split() {
            Range rleft = this.rangeleft();
            int elemleft = rleft.length();
            return elemleft < 2 ? (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParRangeIterator[]{new ParRangeIterator(this.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft)})) : (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new ParRangeIterator[]{new ParRangeIterator(this.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft.take(elemleft / 2)), new ParRangeIterator(this.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft.drop(elemleft / 2))}));
        }

        @Override
        public Seq<SeqSplitter<Object>> psplit(Seq<Object> sizes) {
            ObjectRef<Range> rleft = ObjectRef.create(this.rangeleft());
            return sizes.map(new Serializable(this, rleft){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParRangeIterator $outer;
                private final ObjectRef rleft$1;

                public final ParRangeIterator apply(int sz) {
                    Range fronttaken = ((Range)this.rleft$1.elem).take(sz);
                    this.rleft$1.elem = ((Range)this.rleft$1.elem).drop(sz);
                    return new ParRangeIterator(this.$outer.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), fronttaken);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.rleft$1 = rleft$1;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public <U> void foreach(Function1<Object, U> f) {
            Range range2 = this.range.drop(this.ind());
            if (!range2.isEmpty()) {
                int i1 = range2.start();
                while (true) {
                    f.apply$mcVI$sp(i1);
                    if (i1 == range2.lastElement()) break;
                    i1 += range2.step();
                }
            }
            this.ind_$eq(this.len());
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            void var2_2;
            U r = this.rangeleft().reduceLeft(op);
            this.ind_$eq(this.len());
            return var2_2;
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<Object, S> f, Combiner<S, That> cb) {
            while (this.hasNext()) {
                cb.$plus$eq(f.apply(BoxesRunTime.boxToInteger(this.next())));
            }
            return cb;
        }

        public /* synthetic */ ParRange scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer() {
            return this.$outer;
        }

        public ParRangeIterator(ParRange $outer, Range range2) {
            this.range = range2;
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
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
            this.ind = 0;
            this.len = range2.length();
        }
    }
}

