/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMapLike;
import scala.collection.GenSet;
import scala.collection.GenSet$class;
import scala.collection.GenSetLike;
import scala.collection.GenSetLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.Parallel;
import scala.collection.Parallelizable;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericSetTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParIterable$class;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$ScanLeaf$;
import scala.collection.parallel.ParIterableLike$ScanNode$;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParMapLike$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSet$;
import scala.collection.parallel.ParSet$class;
import scala.collection.parallel.ParSetLike$class;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001\u00055g\u0001C\u0001\u0003!\u0003\r\t!\u0003\u0015\u0003\u0015A\u000b'/T1q\u0019&\\WM\u0003\u0002\u0004\t\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!F\u0003\u000b+}\u00113f\u0005\u0003\u0001\u0017=I\u0004C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fMB)\u0001#E\n\u001fC5\tA!\u0003\u0002\u0013\t\tQq)\u001a8NCBd\u0015n[3\u0011\u0005Q)B\u0002\u0001\u0003\u0006-\u0001\u0011\ra\u0006\u0002\u0002\u0017F\u0011\u0001d\u0007\t\u0003\u0019eI!A\u0007\u0004\u0003\u000f9{G\u000f[5oOB\u0011A\u0002H\u0005\u0003;\u0019\u00111!\u00118z!\t!r\u0004\u0002\u0004!\u0001\u0011\u0015\ra\u0006\u0002\u0002-B\u0011AC\t\u0003\u0007G\u0001!)\u0019\u0001\u0013\u0003\tI+\u0007O]\t\u00031\u0015\u00122A\n\u00157\r\u00119\u0003\u0001A\u0013\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\r%\u00021CH\u0011+\u001b\u0005\u0011\u0001C\u0001\u000b,\t\u0019a\u0003\u0001\"b\u0001[\tQ1+Z9vK:$\u0018.\u00197\u0012\u0005aq#cA\u00181g\u0019!q\u0005\u0001\u0001/!\u0011\u0001\u0012g\u0005\u0010\n\u0005I\"!aA'baB)\u0001\u0003N\n\u001fU%\u0011Q\u0007\u0002\u0002\b\u001b\u0006\u0004H*[6f!\u0011Isg\u0005\u0010\n\u0005a\u0012!A\u0002)be6\u000b\u0007\u000fE\u0003*uq\n#&\u0003\u0002<\u0005\ty\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f\u0019&\\W\r\u0005\u0003\r{Mq\u0012B\u0001 \u0007\u0005\u0019!V\u000f\u001d7fe!)\u0001\t\u0001C\u0001\u0003\u00061A%\u001b8ji\u0012\"\u0012A\u0011\t\u0003\u0019\rK!\u0001\u0012\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006\r\u0002!\taR\u0001\bI\u00164\u0017-\u001e7u)\tq\u0002\nC\u0003J\u000b\u0002\u00071#A\u0002lKfDQa\u0013\u0001\u0007\u00021\u000bQ!Z7qif,\u0012!\t\u0005\u0006\u001d\u0002!\taT\u0001\u0006CB\u0004H.\u001f\u000b\u0003=ACQ!S'A\u0002MAQA\u0015\u0001\u0005\u0002M\u000b\u0011bZ3u\u001fJ,En]3\u0016\u0005Q3FcA+Z5B\u0011AC\u0016\u0003\u0006/F\u0013\r\u0001\u0017\u0002\u0002+F\u0011ad\u0007\u0005\u0006\u0013F\u0003\ra\u0005\u0005\u0007\rF#\t\u0019A.\u0011\u00071aV+\u0003\u0002^\r\tAAHY=oC6,g\bC\u0003`\u0001\u0011\u0005\u0001-\u0001\u0005d_:$\u0018-\u001b8t)\t\tG\r\u0005\u0002\rE&\u00111M\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015Ie\f1\u0001\u0014\u0011\u00151\u0007\u0001\"\u0001h\u0003-I7\u000fR3gS:,G-\u0011;\u0015\u0005\u0005D\u0007\"B%f\u0001\u0004\u0019\u0002B\u00026\u0001A\u0013%1.\u0001\u0007lKf\u001c\u0018\n^3sCR|'\u000f\u0006\u0002m_B\u0019\u0011&\\\n\n\u00059\u0014!\u0001E%uKJ\f'\r\\3Ta2LG\u000f^3s\u0011\u0015\u0001\u0018\u000e1\u0001r\u0003\u0005\u0019(F\u0001:t!\rIS\u000eP\u0016\u0002iB\u0011QO_\u0007\u0002m*\u0011q\u000f_\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u001f\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002|m\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000b)\u0004A\u0011A?\u0016\u00031Dqa \u0001!\n\u0013\t\t!\u0001\bwC2,Xm]%uKJ\fGo\u001c:\u0015\t\u0005\r\u0011Q\u0001\t\u0004S5t\u0002\"\u00029\u007f\u0001\u0004\t\bBB@\u0001\t\u0003\tI!\u0006\u0002\u0002\u0004\u00191\u0011Q\u0002\u0001\t\u0003\u001f\u0011Q\u0002R3gCVdGoS3z'\u0016$8#BA\u0006\u0017\u0005E\u0001\u0003B\u0015\u0002\u0014MI1!!\u0006\u0003\u0005\u0019\u0001\u0016M]*fi\"A\u0011\u0011DA\u0006\t\u0003\tY\"\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003;\u0001B!a\b\u0002\f5\t\u0001\u0001C\u0004`\u0003\u0017!\t!a\t\u0015\u0007\u0005\f)\u0003\u0003\u0004J\u0003C\u0001\ra\u0005\u0005\b\u0003S\tY\u0001\"\u0001~\u0003!\u0019\b\u000f\\5ui\u0016\u0014\b\u0002CA\u0017\u0003\u0017!\t!a\f\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005E\u0011\u0011\u0007\u0005\b\u0003g\tY\u00031\u0001\u0014\u0003\u0011)G.Z7\t\u0011\u0005]\u00121\u0002C\u0001\u0003s\ta\u0001J7j]V\u001cH\u0003BA\t\u0003wAq!a\r\u00026\u0001\u00071\u0003\u0003\u0005\u0002@\u0005-A\u0011IA!\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005\r\u0003c\u0001\u0007\u0002F%\u0019\u0011q\t\u0004\u0003\u0007%sG\u000f\u0003\u0005\u0002L\u0005-A\u0011IA'\u0003\u001d1wN]3bG\",B!a\u0014\u0002^Q\u0019!)!\u0015\t\u0011\u0005M\u0013\u0011\na\u0001\u0003+\n\u0011A\u001a\t\u0007\u0019\u0005]3#a\u0017\n\u0007\u0005ecAA\u0005Gk:\u001cG/[8ocA\u0019A#!\u0018\u0005\r]\u000bIE1\u0001\u0018\u0011!\t\t'a\u0003\u0005B\u0005\r\u0014aA:fcV\u0011\u0011Q\r\t\u0005!\u0005\u001d4#C\u0002\u0002j\u0011\u00111aU3u\r\u0019\ti\u0007\u0001\u0005\u0002p\t)B)\u001a4bk2$h+\u00197vKNLE/\u001a:bE2,7#BA6\u0017\u0005E\u0004\u0003B\u0015\u0002tyI1!!\u001e\u0003\u0005-\u0001\u0016M]%uKJ\f'\r\\3\t\u0011\u0005e\u00111\u000eC\u0001\u0003s\"\"!a\u001f\u0011\t\u0005}\u00111\u000e\u0005\t\u0003S\tY\u0007\"\u0001\u0002\n!A\u0011qHA6\t\u0003\n\t\u0005\u0003\u0005\u0002L\u0005-D\u0011IAB+\u0011\t))!$\u0015\u0007\t\u000b9\t\u0003\u0005\u0002T\u0005\u0005\u0005\u0019AAE!\u0019a\u0011q\u000b\u0010\u0002\fB\u0019A#!$\u0005\r]\u000b\tI1\u0001\u0018\u0011!\t\t'a\u001b\u0005\u0002\u0005EUCAAJ!\u0011\u0001\u0012Q\u0013\u0010\n\u0007\u0005]EA\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0011\u001d\tY\n\u0001C\u0001\u0003;\u000baa[3z'\u0016$XCAA\t\u0011\u001d\t\t\u000b\u0001C\u0001\u0003G\u000bAa[3zgV\u0011\u0011Q\u0015\t\u0005S\u0005M4\u0003C\u0004\u0002*\u0002!\t!a+\u0002\rY\fG.^3t+\t\t\t\bC\u0004\u00020\u0002!\t!!-\u0002\u0015\u0019LG\u000e^3s\u0017\u0016L8\u000fF\u00027\u0003gC\u0001\"!.\u0002.\u0002\u0007\u0011qW\u0001\u0002aB)A\"a\u0016\u0014C\"9\u00111\u0018\u0001\u0005\u0002\u0005u\u0016!C7baZ\u000bG.^3t+\u0011\ty,!2\u0015\t\u0005\u0005\u0017\u0011\u001a\t\u0006S]\u001a\u00121\u0019\t\u0004)\u0005\u0015GaBAd\u0003s\u0013\ra\u0006\u0002\u0002'\"A\u00111KA]\u0001\u0004\tY\r\u0005\u0004\r\u0003/r\u00121\u0019")
public interface ParMapLike<K, V, Repr extends ParMapLike<K, V, Repr, Sequential> & ParMap<K, V>, Sequential extends Map<K, V> & MapLike<K, V, Sequential>>
extends GenMapLike<K, V, Repr>,
ParIterableLike<Tuple2<K, V>, Repr, Sequential> {
    @Override
    public V default(K var1);

    public Repr empty();

    @Override
    public V apply(K var1);

    @Override
    public <U> U getOrElse(K var1, Function0<U> var2);

    @Override
    public boolean contains(K var1);

    @Override
    public boolean isDefinedAt(K var1);

    @Override
    public IterableSplitter<K> keysIterator();

    @Override
    public IterableSplitter<V> valuesIterator();

    @Override
    public ParSet<K> keySet();

    @Override
    public ParIterable<K> keys();

    @Override
    public ParIterable<V> values();

    @Override
    public ParMap<K, V> filterKeys(Function1<K, Object> var1);

    @Override
    public <S> ParMap<K, S> mapValues(Function1<V, S> var1);

    public class DefaultKeySet
    implements ParSet<K> {
        private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
        private volatile ParIterableLike$ScanNode$ ScanNode$module;
        private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

        @Override
        public ParSet<K> empty() {
            return ParSet$class.empty(this);
        }

        @Override
        public GenericCompanion<ParSet> companion() {
            return ParSet$class.companion(this);
        }

        @Override
        public String stringPrefix() {
            return ParSet$class.stringPrefix(this);
        }

        @Override
        public ParSet<K> union(GenSet<K> that) {
            return ParSetLike$class.union(this, that);
        }

        @Override
        public ParSet<K> diff(GenSet<K> that) {
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
            DefaultKeySet defaultKeySet = this;
            synchronized (defaultKeySet) {
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
            DefaultKeySet defaultKeySet = this;
            synchronized (defaultKeySet) {
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
        public ParIterable repr() {
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
        public K head() {
            return ParIterableLike$class.head(this);
        }

        @Override
        public Option<K> headOption() {
            return ParIterableLike$class.headOption(this);
        }

        @Override
        public ParIterable tail() {
            return ParIterableLike$class.tail(this);
        }

        @Override
        public K last() {
            return ParIterableLike$class.last(this);
        }

        @Override
        public Option<K> lastOption() {
            return ParIterableLike$class.lastOption(this);
        }

        @Override
        public ParIterable init() {
            return ParIterableLike$class.init(this);
        }

        @Override
        public Splitter<K> iterator() {
            return ParIterableLike$class.iterator(this);
        }

        @Override
        public ParIterable par() {
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
        public <S, That> Object bf2seq(CanBuildFrom<ParSet<K>, S, That> bf) {
            return ParIterableLike$class.bf2seq(this, bf);
        }

        @Override
        public ParIterable sequentially(Function1 b) {
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
        public <S> S aggregate(Function0<S> z, Function2<S, K, S> seqop, Function2<S, S, S> combop) {
            return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <S> S foldLeft(S z, Function2<S, K, S> op) {
            return (S)ParIterableLike$class.foldLeft(this, z, op);
        }

        @Override
        public <S> S foldRight(S z, Function2<K, S, S> op) {
            return (S)ParIterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <U> U reduceLeft(Function2<U, K, U> op) {
            return (U)ParIterableLike$class.reduceLeft(this, op);
        }

        @Override
        public <U> U reduceRight(Function2<K, U, U> op) {
            return (U)ParIterableLike$class.reduceRight(this, op);
        }

        @Override
        public <U> Option<U> reduceLeftOption(Function2<U, K, U> op) {
            return ParIterableLike$class.reduceLeftOption(this, op);
        }

        @Override
        public <U> Option<U> reduceRightOption(Function2<K, U, U> op) {
            return ParIterableLike$class.reduceRightOption(this, op);
        }

        @Override
        public int count(Function1<K, Object> p) {
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
        public <U> K min(Ordering<U> ord) {
            return ParIterableLike$class.min(this, ord);
        }

        @Override
        public <U> K max(Ordering<U> ord) {
            return ParIterableLike$class.max(this, ord);
        }

        @Override
        public <S> K maxBy(Function1<K, S> f, Ordering<S> cmp) {
            return ParIterableLike$class.maxBy(this, f, cmp);
        }

        @Override
        public <S> K minBy(Function1<K, S> f, Ordering<S> cmp) {
            return ParIterableLike$class.minBy(this, f, cmp);
        }

        @Override
        public <S, That> That map(Function1<K, S> f, CanBuildFrom<ParSet<K>, S, That> bf) {
            return (That)ParIterableLike$class.map(this, f, bf);
        }

        @Override
        public <S, That> That collect(PartialFunction<K, S> pf, CanBuildFrom<ParSet<K>, S, That> bf) {
            return (That)ParIterableLike$class.collect(this, pf, bf);
        }

        @Override
        public <S, That> That flatMap(Function1<K, GenTraversableOnce<S>> f, CanBuildFrom<ParSet<K>, S, That> bf) {
            return (That)ParIterableLike$class.flatMap(this, f, bf);
        }

        @Override
        public boolean forall(Function1<K, Object> p) {
            return ParIterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<K, Object> p) {
            return ParIterableLike$class.exists(this, p);
        }

        @Override
        public Option<K> find(Function1<K, Object> p) {
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
        public ParIterable withFilter(Function1 pred) {
            return ParIterableLike$class.withFilter(this, pred);
        }

        @Override
        public ParIterable filter(Function1 pred) {
            return ParIterableLike$class.filter(this, pred);
        }

        @Override
        public ParIterable filterNot(Function1 pred) {
            return ParIterableLike$class.filterNot(this, pred);
        }

        @Override
        public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParSet<K>, U, That> bf) {
            return (That)ParIterableLike$class.$plus$plus(this, that, bf);
        }

        @Override
        public Tuple2<ParSet<K>, ParSet<K>> partition(Function1<K, Object> pred) {
            return ParIterableLike$class.partition(this, pred);
        }

        @Override
        public <K> scala.collection.parallel.immutable.ParMap<K, ParSet<K>> groupBy(Function1<K, K> f) {
            return ParIterableLike$class.groupBy(this, f);
        }

        @Override
        public ParIterable take(int n) {
            return ParIterableLike$class.take(this, n);
        }

        @Override
        public ParIterable drop(int n) {
            return ParIterableLike$class.drop(this, n);
        }

        @Override
        public ParIterable slice(int unc_from, int unc_until) {
            return ParIterableLike$class.slice(this, unc_from, unc_until);
        }

        @Override
        public Tuple2<ParSet<K>, ParSet<K>> splitAt(int n) {
            return ParIterableLike$class.splitAt(this, n);
        }

        @Override
        public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParSet<K>, U, That> bf) {
            return (That)ParIterableLike$class.scan(this, z, op, bf);
        }

        @Override
        public <S, That> That scanLeft(S z, Function2<S, K, S> op, CanBuildFrom<ParSet<K>, S, That> bf) {
            return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <S, That> That scanRight(S z, Function2<K, S, S> op, CanBuildFrom<ParSet<K>, S, That> bf) {
            return (That)ParIterableLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public ParIterable takeWhile(Function1 pred) {
            return ParIterableLike$class.takeWhile(this, pred);
        }

        @Override
        public Tuple2<ParSet<K>, ParSet<K>> span(Function1<K, Object> pred) {
            return ParIterableLike$class.span(this, pred);
        }

        @Override
        public ParIterable dropWhile(Function1 pred) {
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
        public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParSet<K>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zip(this, that, bf);
        }

        @Override
        public <U, That> That zipWithIndex(CanBuildFrom<ParSet<K>, Tuple2<U, Object>, That> bf) {
            return (That)ParIterableLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParSet<K>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
            return (That)ParIterableLike$class.toParCollection(this, cbf);
        }

        @Override
        public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<K, Tuple2<K, V>> ev) {
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
        public List<K> toList() {
            return ParIterableLike$class.toList(this);
        }

        @Override
        public IndexedSeq<K> toIndexedSeq() {
            return ParIterableLike$class.toIndexedSeq(this);
        }

        @Override
        public Stream<K> toStream() {
            return ParIterableLike$class.toStream(this);
        }

        @Override
        public Iterator<K> toIterator() {
            return ParIterableLike$class.toIterator(this);
        }

        @Override
        public <U> Buffer<U> toBuffer() {
            return ParIterableLike$class.toBuffer(this);
        }

        @Override
        public GenTraversable<K> toTraversable() {
            return ParIterableLike$class.toTraversable(this);
        }

        @Override
        public ParIterable<K> toIterable() {
            return ParIterableLike$class.toIterable(this);
        }

        @Override
        public ParSeq<K> toSeq() {
            return ParIterableLike$class.toSeq(this);
        }

        @Override
        public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
            return ParIterableLike$class.toSet(this);
        }

        @Override
        public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<K, Tuple2<K, V>> ev) {
            return ParIterableLike$class.toMap(this, ev);
        }

        @Override
        public Vector<K> toVector() {
            return ParIterableLike$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, K, Col> cbf) {
            return (Col)ParIterableLike$class.to(this, cbf);
        }

        @Override
        public int scanBlockSize() {
            return ParIterableLike$class.scanBlockSize(this);
        }

        @Override
        public <S> S $div$colon(S z, Function2<S, K, S> op) {
            return (S)ParIterableLike$class.$div$colon(this, z, op);
        }

        @Override
        public <S> S $colon$bslash(S z, Function2<K, S, S> op) {
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
        public Combiner<K, ParSet<K>> parCombiner() {
            return CustomParallelizable$class.parCombiner(this);
        }

        @Override
        public Builder<K, ParSet<K>> newBuilder() {
            return GenericParTemplate$class.newBuilder(this);
        }

        @Override
        public Combiner<K, ParSet<K>> newCombiner() {
            return GenericParTemplate$class.newCombiner(this);
        }

        @Override
        public <B> Combiner<B, ParSet<B>> genericBuilder() {
            return GenericParTemplate$class.genericBuilder(this);
        }

        @Override
        public <B> Combiner<B, ParSet<B>> genericCombiner() {
            return GenericParTemplate$class.genericCombiner(this);
        }

        @Override
        public <A1, A2> Tuple2<ParSet<A1>, ParSet<A2>> unzip(Function1<K, Tuple2<A1, A2>> asPair) {
            return GenericTraversableTemplate$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<ParSet<A1>, ParSet<A2>, ParSet<A3>> unzip3(Function1<K, Tuple3<A1, A2, A3>> asTriple) {
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
        public boolean apply(K elem) {
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
        public boolean subsetOf(GenSet<K> that) {
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
        public <A> Function1<A, Object> compose(Function1<A, K> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public <A> Function1<K, A> andThen(Function1<Object, A> g) {
            return Function1$class.andThen(this, g);
        }

        @Override
        public boolean contains(K key) {
            return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().contains(key);
        }

        @Override
        public IterableSplitter<K> splitter() {
            return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer(), this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().splitter());
        }

        @Override
        public ParSet<K> $plus(K elem) {
            return (ParSet)((GenSetLike)((ParIterableLike)ParSet$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParSet$.MODULE$.canBuildFrom())).$plus(elem);
        }

        @Override
        public ParSet<K> $minus(K elem) {
            return (ParSet)((GenSetLike)((ParIterableLike)ParSet$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this, ParSet$.MODULE$.canBuildFrom())).$minus(elem);
        }

        @Override
        public int size() {
            return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().size();
        }

        @Override
        public <U> void foreach(Function1<K, U> f) {
            this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<K, V> check$ifrefutable$1) {
                    boolean bl = check$ifrefutable$1 != null;
                    return bl;
                }
            }).foreach(new Serializable(this, f){
                public static final long serialVersionUID = 0L;
                private final Function1 f$1;

                public final U apply(Tuple2<K, V> x$5) {
                    if (x$5 != null) {
                        return (U)this.f$1.apply(x$5._1());
                    }
                    throw new MatchError(x$5);
                }
                {
                    this.f$1 = f$1;
                }
            });
        }

        @Override
        public Set<K> seq() {
            return ((MapLike)this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().seq()).keySet();
        }

        public /* synthetic */ ParMapLike scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer() {
            return ParMapLike.this;
        }

        public DefaultKeySet() {
            if (ParMapLike.this == null) {
                throw null;
            }
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
            ParIterable$class.$init$(this);
            ParSetLike$class.$init$(this);
            ParSet$class.$init$(this);
        }
    }

    public class DefaultValuesIterable
    implements ParIterable<V> {
        private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
        private volatile ParIterableLike$ScanNode$ ScanNode$module;
        private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

        @Override
        public GenericCompanion<ParIterable> companion() {
            return ParIterable$class.companion(this);
        }

        @Override
        public String stringPrefix() {
            return ParIterable$class.stringPrefix(this);
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
            DefaultValuesIterable defaultValuesIterable = this;
            synchronized (defaultValuesIterable) {
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
            DefaultValuesIterable defaultValuesIterable = this;
            synchronized (defaultValuesIterable) {
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
        public ParIterable<V> repr() {
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
        public V head() {
            return ParIterableLike$class.head(this);
        }

        @Override
        public Option<V> headOption() {
            return ParIterableLike$class.headOption(this);
        }

        @Override
        public ParIterable<V> tail() {
            return ParIterableLike$class.tail(this);
        }

        @Override
        public V last() {
            return ParIterableLike$class.last(this);
        }

        @Override
        public Option<V> lastOption() {
            return ParIterableLike$class.lastOption(this);
        }

        @Override
        public ParIterable<V> init() {
            return ParIterableLike$class.init(this);
        }

        @Override
        public Splitter<V> iterator() {
            return ParIterableLike$class.iterator(this);
        }

        @Override
        public ParIterable<V> par() {
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
        public <S, That> Object bf2seq(CanBuildFrom<ParIterable<V>, S, That> bf) {
            return ParIterableLike$class.bf2seq(this, bf);
        }

        @Override
        public <S, That extends Parallel> ParIterable<V> sequentially(Function1<Iterable<V>, Parallelizable<S, That>> b) {
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
        public <S> S aggregate(Function0<S> z, Function2<S, V, S> seqop, Function2<S, S, S> combop) {
            return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <S> S foldLeft(S z, Function2<S, V, S> op) {
            return (S)ParIterableLike$class.foldLeft(this, z, op);
        }

        @Override
        public <S> S foldRight(S z, Function2<V, S, S> op) {
            return (S)ParIterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <U> U reduceLeft(Function2<U, V, U> op) {
            return (U)ParIterableLike$class.reduceLeft(this, op);
        }

        @Override
        public <U> U reduceRight(Function2<V, U, U> op) {
            return (U)ParIterableLike$class.reduceRight(this, op);
        }

        @Override
        public <U> Option<U> reduceLeftOption(Function2<U, V, U> op) {
            return ParIterableLike$class.reduceLeftOption(this, op);
        }

        @Override
        public <U> Option<U> reduceRightOption(Function2<V, U, U> op) {
            return ParIterableLike$class.reduceRightOption(this, op);
        }

        @Override
        public int count(Function1<V, Object> p) {
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
        public <U> V min(Ordering<U> ord) {
            return ParIterableLike$class.min(this, ord);
        }

        @Override
        public <U> V max(Ordering<U> ord) {
            return ParIterableLike$class.max(this, ord);
        }

        @Override
        public <S> V maxBy(Function1<V, S> f, Ordering<S> cmp) {
            return ParIterableLike$class.maxBy(this, f, cmp);
        }

        @Override
        public <S> V minBy(Function1<V, S> f, Ordering<S> cmp) {
            return ParIterableLike$class.minBy(this, f, cmp);
        }

        @Override
        public <S, That> That map(Function1<V, S> f, CanBuildFrom<ParIterable<V>, S, That> bf) {
            return (That)ParIterableLike$class.map(this, f, bf);
        }

        @Override
        public <S, That> That collect(PartialFunction<V, S> pf, CanBuildFrom<ParIterable<V>, S, That> bf) {
            return (That)ParIterableLike$class.collect(this, pf, bf);
        }

        @Override
        public <S, That> That flatMap(Function1<V, GenTraversableOnce<S>> f, CanBuildFrom<ParIterable<V>, S, That> bf) {
            return (That)ParIterableLike$class.flatMap(this, f, bf);
        }

        @Override
        public boolean forall(Function1<V, Object> p) {
            return ParIterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<V, Object> p) {
            return ParIterableLike$class.exists(this, p);
        }

        @Override
        public Option<V> find(Function1<V, Object> p) {
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
        public ParIterable<V> withFilter(Function1<V, Object> pred) {
            return ParIterableLike$class.withFilter(this, pred);
        }

        @Override
        public ParIterable<V> filter(Function1<V, Object> pred) {
            return ParIterableLike$class.filter(this, pred);
        }

        @Override
        public ParIterable<V> filterNot(Function1<V, Object> pred) {
            return ParIterableLike$class.filterNot(this, pred);
        }

        @Override
        public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParIterable<V>, U, That> bf) {
            return (That)ParIterableLike$class.$plus$plus(this, that, bf);
        }

        @Override
        public Tuple2<ParIterable<V>, ParIterable<V>> partition(Function1<V, Object> pred) {
            return ParIterableLike$class.partition(this, pred);
        }

        @Override
        public <K> scala.collection.parallel.immutable.ParMap<K, ParIterable<V>> groupBy(Function1<V, K> f) {
            return ParIterableLike$class.groupBy(this, f);
        }

        @Override
        public ParIterable<V> take(int n) {
            return ParIterableLike$class.take(this, n);
        }

        @Override
        public ParIterable<V> drop(int n) {
            return ParIterableLike$class.drop(this, n);
        }

        @Override
        public ParIterable<V> slice(int unc_from, int unc_until) {
            return ParIterableLike$class.slice(this, unc_from, unc_until);
        }

        @Override
        public Tuple2<ParIterable<V>, ParIterable<V>> splitAt(int n) {
            return ParIterableLike$class.splitAt(this, n);
        }

        @Override
        public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParIterable<V>, U, That> bf) {
            return (That)ParIterableLike$class.scan(this, z, op, bf);
        }

        @Override
        public <S, That> That scanLeft(S z, Function2<S, V, S> op, CanBuildFrom<ParIterable<V>, S, That> bf) {
            return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <S, That> That scanRight(S z, Function2<V, S, S> op, CanBuildFrom<ParIterable<V>, S, That> bf) {
            return (That)ParIterableLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public ParIterable<V> takeWhile(Function1<V, Object> pred) {
            return ParIterableLike$class.takeWhile(this, pred);
        }

        @Override
        public Tuple2<ParIterable<V>, ParIterable<V>> span(Function1<V, Object> pred) {
            return ParIterableLike$class.span(this, pred);
        }

        @Override
        public ParIterable<V> dropWhile(Function1<V, Object> pred) {
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
        public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParIterable<V>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zip(this, that, bf);
        }

        @Override
        public <U, That> That zipWithIndex(CanBuildFrom<ParIterable<V>, Tuple2<U, Object>, That> bf) {
            return (That)ParIterableLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParIterable<V>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
            return (That)ParIterableLike$class.toParCollection(this, cbf);
        }

        @Override
        public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<V, Tuple2<K, V>> ev) {
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
        public List<V> toList() {
            return ParIterableLike$class.toList(this);
        }

        @Override
        public IndexedSeq<V> toIndexedSeq() {
            return ParIterableLike$class.toIndexedSeq(this);
        }

        @Override
        public Stream<V> toStream() {
            return ParIterableLike$class.toStream(this);
        }

        @Override
        public Iterator<V> toIterator() {
            return ParIterableLike$class.toIterator(this);
        }

        @Override
        public <U> Buffer<U> toBuffer() {
            return ParIterableLike$class.toBuffer(this);
        }

        @Override
        public GenTraversable<V> toTraversable() {
            return ParIterableLike$class.toTraversable(this);
        }

        @Override
        public ParIterable<V> toIterable() {
            return ParIterableLike$class.toIterable(this);
        }

        @Override
        public ParSeq<V> toSeq() {
            return ParIterableLike$class.toSeq(this);
        }

        @Override
        public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
            return ParIterableLike$class.toSet(this);
        }

        @Override
        public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<V, Tuple2<K, V>> ev) {
            return ParIterableLike$class.toMap(this, ev);
        }

        @Override
        public Vector<V> toVector() {
            return ParIterableLike$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, V, Col> cbf) {
            return (Col)ParIterableLike$class.to(this, cbf);
        }

        @Override
        public int scanBlockSize() {
            return ParIterableLike$class.scanBlockSize(this);
        }

        @Override
        public <S> S $div$colon(S z, Function2<S, V, S> op) {
            return (S)ParIterableLike$class.$div$colon(this, z, op);
        }

        @Override
        public <S> S $colon$bslash(S z, Function2<V, S, S> op) {
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
        public Combiner<V, ParIterable<V>> parCombiner() {
            return CustomParallelizable$class.parCombiner(this);
        }

        @Override
        public Builder<V, ParIterable<V>> newBuilder() {
            return GenericParTemplate$class.newBuilder(this);
        }

        @Override
        public Combiner<V, ParIterable<V>> newCombiner() {
            return GenericParTemplate$class.newCombiner(this);
        }

        @Override
        public <B> Combiner<B, ParIterable<B>> genericBuilder() {
            return GenericParTemplate$class.genericBuilder(this);
        }

        @Override
        public <B> Combiner<B, ParIterable<B>> genericCombiner() {
            return GenericParTemplate$class.genericCombiner(this);
        }

        @Override
        public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<V, Tuple2<A1, A2>> asPair) {
            return GenericTraversableTemplate$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<V, Tuple3<A1, A2, A3>> asTriple) {
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
        public IterableSplitter<V> splitter() {
            return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer(), this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().splitter());
        }

        @Override
        public int size() {
            return this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().size();
        }

        @Override
        public <U> void foreach(Function1<V, U> f) {
            this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<K, V> check$ifrefutable$2) {
                    boolean bl = check$ifrefutable$2 != null;
                    return bl;
                }
            }).foreach(new Serializable(this, f){
                public static final long serialVersionUID = 0L;
                private final Function1 f$2;

                public final U apply(Tuple2<K, V> x$6) {
                    if (x$6 != null) {
                        return (U)this.f$2.apply(x$6._2());
                    }
                    throw new MatchError(x$6);
                }
                {
                    this.f$2 = f$2;
                }
            });
        }

        @Override
        public Iterable<V> seq() {
            return ((MapLike)this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().seq()).values();
        }

        public /* synthetic */ ParMapLike scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer() {
            return ParMapLike.this;
        }

        public DefaultValuesIterable() {
            if (ParMapLike.this == null) {
                throw null;
            }
            Parallelizable$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            GenIterable$class.$init$(this);
            GenericParTemplate$class.$init$(this);
            CustomParallelizable$class.$init$(this);
            ParIterableLike$class.$init$(this);
            ParIterable$class.$init$(this);
        }
    }
}

