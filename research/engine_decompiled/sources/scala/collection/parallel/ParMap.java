/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenMap;
import scala.collection.GenMapLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate;
import scala.collection.generic.GenericParMapTemplate$class;
import scala.collection.generic.GenericParTemplate$class;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
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
import scala.collection.parallel.ParMap$class;
import scala.collection.parallel.ParMapLike;
import scala.collection.parallel.ParMapLike$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005Me\u0001C\u0001\u0003!\u0003\r\t!\u0003\u001a\u0003\rA\u000b'/T1q\u0015\t\u0019A!\u0001\u0005qCJ\fG\u000e\\3m\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\rQQcH\n\u0007\u0001-y\u0011%K\u0018\u0011\u00051iQ\"\u0001\u0004\n\u000591!AB!osJ+g\r\u0005\u0003\u0011#MqR\"\u0001\u0003\n\u0005I!!AB$f]6\u000b\u0007\u000f\u0005\u0002\u0015+1\u0001A!\u0002\f\u0001\u0005\u00049\"!A&\u0012\u0005aY\u0002C\u0001\u0007\u001a\u0013\tQbAA\u0004O_RD\u0017N\\4\u0011\u00051a\u0012BA\u000f\u0007\u0005\r\te.\u001f\t\u0003)}!a\u0001\t\u0001\u0005\u0006\u00049\"!\u0001,\u0011\u000b\t*3CH\u0014\u000e\u0003\rR!\u0001\n\u0003\u0002\u000f\u001d,g.\u001a:jG&\u0011ae\t\u0002\u0016\u000f\u0016tWM]5d!\u0006\u0014X*\u00199UK6\u0004H.\u0019;f!\tA\u0003!D\u0001\u0003!\rA#\u0006L\u0005\u0003W\t\u00111\u0002U1s\u0013R,'/\u00192mKB!A\"L\n\u001f\u0013\tqcA\u0001\u0004UkBdWM\r\t\u0007QA\u001abDM\u001a\n\u0005E\u0012!A\u0003)be6\u000b\u0007\u000fT5lKB!\u0001\u0006A\n\u001f!\u0011\u0001Bg\u0005\u0010\n\u0005U\"!aA'ba\")q\u0007\u0001C\u0001q\u00051A%\u001b8ji\u0012\"\u0012!\u000f\t\u0003\u0019iJ!a\u000f\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006{\u0001!\tAP\u0001\r[\u0006\u00048i\\7qC:LwN\\\u000b\u0002\u007fA\u0019!\u0005Q\u0014\n\u0005\u0005\u001b#AF$f]\u0016\u0014\u0018n\u0019)be6\u000b\u0007oQ8na\u0006t\u0017n\u001c8\t\u000b\r\u0003A\u0011\u0001#\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003IBQA\u0012\u0001\u0005B\u001d\u000bAb\u001d;sS:<\u0007K]3gSb,\u0012\u0001\u0013\t\u0003\u0013:k\u0011A\u0013\u0006\u0003\u00172\u000bA\u0001\\1oO*\tQ*\u0001\u0003kCZ\f\u0017BA(K\u0005\u0019\u0019FO]5oO\")\u0011\u000b\u0001C!%\u00069Q\u000f\u001d3bi\u0016$WCA*W)\r!\u0016l\u0017\t\u0005Q\u0001\u0019R\u000b\u0005\u0002\u0015-\u0012)q\u000b\u0015b\u00011\n\tQ+\u0005\u0002\u001f7!)!\f\u0015a\u0001'\u0005\u00191.Z=\t\u000bq\u0003\u0006\u0019A+\u0002\u000bY\fG.^3\t\u000by\u0003a\u0011A0\u0002\u000b\u0011\u0002H.^:\u0016\u0005\u0001\u001cGCA1e!\u0011A\u0003a\u00052\u0011\u0005Q\u0019G!B,^\u0005\u0004A\u0006\"B3^\u0001\u00041\u0017AA6w!\u0011aQf\u00052\b\u000b!\u0014\u0001\u0012A5\u0002\rA\u000b'/T1q!\tA#NB\u0003\u0002\u0005!\u00051n\u0005\u0002kYB\u0019!%\\\u0014\n\u00059\u001c#!\u0004)be6\u000b\u0007OR1di>\u0014\u0018\u0010C\u0003qU\u0012\u0005\u0011/\u0001\u0004=S:LGO\u0010\u000b\u0002S\")1I\u001bC\u0001gV\u0019Ao^=\u0016\u0003U\u0004B\u0001\u000b\u0001wqB\u0011Ac\u001e\u0003\u0006-I\u0014\ra\u0006\t\u0003)e$Q\u0001\t:C\u0002]AQa\u001f6\u0005\u0002q\f1B\\3x\u0007>l'-\u001b8feV)Q0a\u0002\u0002\fU\ta\u0010\u0005\u0004)\u007f\u0006\r\u0011QB\u0005\u0004\u0003\u0003\u0011!\u0001C\"p[\nLg.\u001a:\u0011\r1i\u0013QAA\u0005!\r!\u0012q\u0001\u0003\u0006-i\u0014\ra\u0006\t\u0004)\u0005-A!\u0002\u0011{\u0005\u00049\u0002C\u0002\u0015\u0001\u0003\u000b\tI\u0001C\u0004\u0002\u0012)$\u0019!a\u0005\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\r\u0005U\u0011QFA\u0019+\t\t9\u0002E\u0005#\u00033\ti\"!\u000b\u00024%\u0019\u00111D\u0012\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B!\u0011qDA\u0011\u001b\u0005Q\u0017\u0002BA\u0012\u0003K\u0011AaQ8mY&\u0019\u0011qE\u0012\u0003\u001b\u001d+g.T1q\r\u0006\u001cGo\u001c:z!\u0019aQ&a\u000b\u00020A\u0019A#!\f\u0005\rY\tyA1\u0001\u0018!\r!\u0012\u0011\u0007\u0003\u0007A\u0005=!\u0019A\f\u0011\r!\u0002\u00111FA\u0018\r\u001d\t9D[A\u0001\u0003s\u00111bV5uQ\u0012+g-Y;miV1\u00111HA!\u0003\u000f\u001aR!!\u000e\f\u0003{\u0001b\u0001\u000b\u0001\u0002@\u0005\u0015\u0003c\u0001\u000b\u0002B\u00119\u00111IA\u001b\u0005\u00049\"!A!\u0011\u0007Q\t9\u0005\u0002\u0005\u0002J\u0005UBQ1\u0001\u0018\u0005\u0005\u0011\u0005bCA'\u0003k\u0011\t\u0011)A\u0005\u0003{\t!\"\u001e8eKJd\u00170\u001b8h\u0011-\t\t&!\u000e\u0003\u0002\u0003\u0006I!a\u0015\u0002\u0003\u0011\u0004r\u0001DA+\u0003\u007f\t)%C\u0002\u0002X\u0019\u0011\u0011BR;oGRLwN\\\u0019\t\u000fA\f)\u0004\"\u0001\u0002\\Q1\u0011QLA0\u0003C\u0002\u0002\"a\b\u00026\u0005}\u0012Q\t\u0005\t\u0003\u001b\nI\u00061\u0001\u0002>!A\u0011\u0011KA-\u0001\u0004\t\u0019\u0006\u0003\u0005\u0002f\u0005UB\u0011IA4\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005%\u0004c\u0001\u0007\u0002l%\u0019\u0011Q\u000e\u0004\u0003\u0007%sG\u000f\u0003\u0005\u0002r\u0005UB\u0011AA:\u0003\r9W\r\u001e\u000b\u0005\u0003k\nY\bE\u0003\r\u0003o\n)%C\u0002\u0002z\u0019\u0011aa\u00149uS>t\u0007b\u0002.\u0002p\u0001\u0007\u0011q\b\u0005\t\u0003\u007f\n)\u0004\"\u0001\u0002\u0002\u0006A1\u000f\u001d7jiR,'/\u0006\u0002\u0002\u0004B)\u0001&!\"\u0002\n&\u0019\u0011q\u0011\u0002\u0003!%#XM]1cY\u0016\u001c\u0006\u000f\\5ui\u0016\u0014\bC\u0002\u0007.\u0003\u007f\t)\u0005\u0003\u0005\u0002\u000e\u0006UB\u0011IAH\u0003\u001d!WMZ1vYR$B!!\u0012\u0002\u0012\"9!,a#A\u0002\u0005}\u0002")
public interface ParMap<K, V>
extends GenMap<K, V>,
GenericParMapTemplate<K, V, ParMap>,
ParIterable<Tuple2<K, V>>,
ParMapLike<K, V, ParMap<K, V>, Map<K, V>> {
    @Override
    public GenericParMapCompanion<ParMap> mapCompanion();

    @Override
    public ParMap<K, V> empty();

    @Override
    public String stringPrefix();

    @Override
    public <U> ParMap<K, U> updated(K var1, U var2);

    @Override
    public <U> ParMap<K, U> $plus(Tuple2<K, U> var1);

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    public static abstract class WithDefault<A, B>
    implements ParMap<A, B> {
        private final ParMap<A, B> underlying;
        private final Function1<A, B> d;
        private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
        private volatile ParIterableLike$ScanNode$ ScanNode$module;
        private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;

        @Override
        public GenericParMapCompanion<ParMap> mapCompanion() {
            return ParMap$class.mapCompanion(this);
        }

        @Override
        public ParMap<A, B> empty() {
            return ParMap$class.empty(this);
        }

        @Override
        public String stringPrefix() {
            return ParMap$class.stringPrefix(this);
        }

        @Override
        public <U> ParMap<A, U> updated(A key, U value) {
            return ParMap$class.updated(this, key, value);
        }

        @Override
        public B apply(A key) {
            return (B)ParMapLike$class.apply(this, key);
        }

        @Override
        public <U> U getOrElse(A key, Function0<U> function0) {
            return (U)ParMapLike$class.getOrElse(this, key, function0);
        }

        @Override
        public boolean contains(A key) {
            return ParMapLike$class.contains(this, key);
        }

        @Override
        public boolean isDefinedAt(A key) {
            return ParMapLike$class.isDefinedAt(this, key);
        }

        @Override
        public IterableSplitter<A> keysIterator() {
            return ParMapLike$class.keysIterator(this);
        }

        @Override
        public IterableSplitter<B> valuesIterator() {
            return ParMapLike$class.valuesIterator(this);
        }

        @Override
        public ParSet<A> keySet() {
            return ParMapLike$class.keySet(this);
        }

        @Override
        public ParIterable<A> keys() {
            return ParMapLike$class.keys(this);
        }

        @Override
        public ParIterable<B> values() {
            return ParMapLike$class.values(this);
        }

        @Override
        public ParMap<A, B> filterKeys(Function1<A, Object> p) {
            return ParMapLike$class.filterKeys(this, p);
        }

        @Override
        public <S> ParMap<A, S> mapValues(Function1<B, S> f) {
            return ParMapLike$class.mapValues(this, f);
        }

        @Override
        public GenericCompanion<ParIterable> companion() {
            return ParIterable$class.companion(this);
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
            WithDefault withDefault = this;
            synchronized (withDefault) {
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
            WithDefault withDefault = this;
            synchronized (withDefault) {
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
        public Object head() {
            return ParIterableLike$class.head(this);
        }

        @Override
        public Option<Tuple2<A, B>> headOption() {
            return ParIterableLike$class.headOption(this);
        }

        @Override
        public ParIterable tail() {
            return ParIterableLike$class.tail(this);
        }

        @Override
        public Object last() {
            return ParIterableLike$class.last(this);
        }

        @Override
        public Option<Tuple2<A, B>> lastOption() {
            return ParIterableLike$class.lastOption(this);
        }

        @Override
        public ParIterable init() {
            return ParIterableLike$class.init(this);
        }

        @Override
        public Splitter<Tuple2<A, B>> iterator() {
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
        public <S, That> Object bf2seq(CanBuildFrom<ParMap<A, B>, S, That> bf) {
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
        public <S> S aggregate(Function0<S> z, Function2<S, Tuple2<A, B>, S> seqop, Function2<S, S, S> combop) {
            return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <S> S foldLeft(S z, Function2<S, Tuple2<A, B>, S> op) {
            return (S)ParIterableLike$class.foldLeft(this, z, op);
        }

        @Override
        public <S> S foldRight(S z, Function2<Tuple2<A, B>, S, S> op) {
            return (S)ParIterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <U> U reduceLeft(Function2<U, Tuple2<A, B>, U> op) {
            return (U)ParIterableLike$class.reduceLeft(this, op);
        }

        @Override
        public <U> U reduceRight(Function2<Tuple2<A, B>, U, U> op) {
            return (U)ParIterableLike$class.reduceRight(this, op);
        }

        @Override
        public <U> Option<U> reduceLeftOption(Function2<U, Tuple2<A, B>, U> op) {
            return ParIterableLike$class.reduceLeftOption(this, op);
        }

        @Override
        public <U> Option<U> reduceRightOption(Function2<Tuple2<A, B>, U, U> op) {
            return ParIterableLike$class.reduceRightOption(this, op);
        }

        @Override
        public <U> void foreach(Function1<Tuple2<A, B>, U> f) {
            ParIterableLike$class.foreach(this, f);
        }

        @Override
        public int count(Function1<Tuple2<A, B>, Object> p) {
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
        public <S, That> That map(Function1<Tuple2<A, B>, S> f, CanBuildFrom<ParMap<A, B>, S, That> bf) {
            return (That)ParIterableLike$class.map(this, f, bf);
        }

        @Override
        public <S, That> That collect(PartialFunction<Tuple2<A, B>, S> pf, CanBuildFrom<ParMap<A, B>, S, That> bf) {
            return (That)ParIterableLike$class.collect(this, pf, bf);
        }

        @Override
        public <S, That> That flatMap(Function1<Tuple2<A, B>, GenTraversableOnce<S>> f, CanBuildFrom<ParMap<A, B>, S, That> bf) {
            return (That)ParIterableLike$class.flatMap(this, f, bf);
        }

        @Override
        public boolean forall(Function1<Tuple2<A, B>, Object> p) {
            return ParIterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Tuple2<A, B>, Object> p) {
            return ParIterableLike$class.exists(this, p);
        }

        @Override
        public Option<Tuple2<A, B>> find(Function1<Tuple2<A, B>, Object> p) {
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
        public <U, That> That $plus$plus(GenTraversableOnce<U> that, CanBuildFrom<ParMap<A, B>, U, That> bf) {
            return (That)ParIterableLike$class.$plus$plus(this, that, bf);
        }

        @Override
        public Tuple2<ParMap<A, B>, ParMap<A, B>> partition(Function1<Tuple2<A, B>, Object> pred) {
            return ParIterableLike$class.partition(this, pred);
        }

        @Override
        public <K> scala.collection.parallel.immutable.ParMap<K, ParMap<A, B>> groupBy(Function1<Tuple2<A, B>, K> f) {
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
        public Tuple2<ParMap<A, B>, ParMap<A, B>> splitAt(int n) {
            return ParIterableLike$class.splitAt(this, n);
        }

        @Override
        public <U, That> That scan(U z, Function2<U, U, U> op, CanBuildFrom<ParMap<A, B>, U, That> bf) {
            return (That)ParIterableLike$class.scan(this, z, op, bf);
        }

        @Override
        public <S, That> That scanLeft(S z, Function2<S, Tuple2<A, B>, S> op, CanBuildFrom<ParMap<A, B>, S, That> bf) {
            return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <S, That> That scanRight(S z, Function2<Tuple2<A, B>, S, S> op, CanBuildFrom<ParMap<A, B>, S, That> bf) {
            return (That)ParIterableLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public ParIterable takeWhile(Function1 pred) {
            return ParIterableLike$class.takeWhile(this, pred);
        }

        @Override
        public Tuple2<ParMap<A, B>, ParMap<A, B>> span(Function1<Tuple2<A, B>, Object> pred) {
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
        public <U, S, That> That zip(GenIterable<S> that, CanBuildFrom<ParMap<A, B>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zip(this, that, bf);
        }

        @Override
        public <U, That> That zipWithIndex(CanBuildFrom<ParMap<A, B>, Tuple2<U, Object>, That> bf) {
            return (That)ParIterableLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <S, U, That> That zipAll(GenIterable<S> that, U thisElem, S thatElem, CanBuildFrom<ParMap<A, B>, Tuple2<U, S>, That> bf) {
            return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public <U, That> That toParCollection(Function0<Combiner<U, That>> cbf) {
            return (That)ParIterableLike$class.toParCollection(this, cbf);
        }

        @Override
        public <K, V, That> That toParMap(Function0<Combiner<Tuple2<K, V>, That>> cbf, Predef$.less.colon.less<Tuple2<A, B>, Tuple2<K, V>> ev) {
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
        public List<Tuple2<A, B>> toList() {
            return ParIterableLike$class.toList(this);
        }

        @Override
        public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
            return ParIterableLike$class.toIndexedSeq(this);
        }

        @Override
        public Stream<Tuple2<A, B>> toStream() {
            return ParIterableLike$class.toStream(this);
        }

        @Override
        public Iterator<Tuple2<A, B>> toIterator() {
            return ParIterableLike$class.toIterator(this);
        }

        @Override
        public <U> Buffer<U> toBuffer() {
            return ParIterableLike$class.toBuffer(this);
        }

        @Override
        public GenTraversable<Tuple2<A, B>> toTraversable() {
            return ParIterableLike$class.toTraversable(this);
        }

        @Override
        public ParIterable<Tuple2<A, B>> toIterable() {
            return ParIterableLike$class.toIterable(this);
        }

        @Override
        public ParSeq<Tuple2<A, B>> toSeq() {
            return ParIterableLike$class.toSeq(this);
        }

        @Override
        public <U> scala.collection.parallel.immutable.ParSet<U> toSet() {
            return ParIterableLike$class.toSet(this);
        }

        @Override
        public <K, V> scala.collection.parallel.immutable.ParMap<K, V> toMap(Predef$.less.colon.less<Tuple2<A, B>, Tuple2<K, V>> ev) {
            return ParIterableLike$class.toMap(this, ev);
        }

        @Override
        public Vector<Tuple2<A, B>> toVector() {
            return ParIterableLike$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<A, B>, Col> cbf) {
            return (Col)ParIterableLike$class.to(this, cbf);
        }

        @Override
        public int scanBlockSize() {
            return ParIterableLike$class.scanBlockSize(this);
        }

        @Override
        public <S> S $div$colon(S z, Function2<S, Tuple2<A, B>, S> op) {
            return (S)ParIterableLike$class.$div$colon(this, z, op);
        }

        @Override
        public <S> S $colon$bslash(S z, Function2<Tuple2<A, B>, S, S> op) {
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
        public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
            return CustomParallelizable$class.parCombiner(this);
        }

        @Override
        public Combiner<Tuple2<A, B>, ParMap<A, B>> newCombiner() {
            return GenericParMapTemplate$class.newCombiner(this);
        }

        @Override
        public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
            return GenericParMapTemplate$class.genericMapCombiner(this);
        }

        @Override
        public Builder<Tuple2<A, B>, ParIterable<Tuple2<A, B>>> newBuilder() {
            return GenericParTemplate$class.newBuilder(this);
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
        public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1<Tuple2<A, B>, Tuple2<A1, A2>> asPair) {
            return GenericTraversableTemplate$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1<Tuple2<A, B>, Tuple3<A1, A2, A3>> asTriple) {
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
        public int hashCode() {
            return GenMapLike$class.hashCode(this);
        }

        @Override
        public boolean equals(Object that) {
            return GenMapLike$class.equals(this, that);
        }

        @Override
        public int size() {
            return this.underlying.size();
        }

        @Override
        public Option<B> get(A key) {
            return this.underlying.get(key);
        }

        @Override
        public IterableSplitter<Tuple2<A, B>> splitter() {
            return this.underlying.splitter();
        }

        @Override
        public B default(A key) {
            return this.d.apply(key);
        }

        public WithDefault(ParMap<A, B> underlying, Function1<A, B> d) {
            this.underlying = underlying;
            this.d = d;
            Parallelizable$class.$init$(this);
            GenMapLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            GenIterable$class.$init$(this);
            GenericParTemplate$class.$init$(this);
            GenericParMapTemplate$class.$init$(this);
            CustomParallelizable$class.$init$(this);
            ParIterableLike$class.$init$(this);
            ParIterable$class.$init$(this);
            ParMapLike$class.$init$(this);
            ParMap$class.$init$(this);
        }
    }
}

