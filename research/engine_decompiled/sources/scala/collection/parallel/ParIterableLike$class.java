/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.DebugUtils$;
import scala.collection.GenIterable;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.generic.AtomicIndexFlag;
import scala.collection.generic.AtomicIndexFlag$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DefaultSignalling;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Growable;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParIterableLike$;
import scala.collection.parallel.ParIterableLike$BuilderOps$class;
import scala.collection.parallel.ParIterableLike$NonDivisibleTask$class;
import scala.collection.parallel.ParIterableLike$StrictSplitterCheckTask$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeq$;
import scala.collection.parallel.ParallelCollectionImplicits$;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.immutable.HashMapCombiner;
import scala.collection.parallel.immutable.HashMapCombiner$;
import scala.collection.parallel.immutable.ParHashMap;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParMap$;
import scala.collection.parallel.immutable.ParRange$;
import scala.collection.parallel.immutable.ParSet;
import scala.collection.parallel.immutable.ParSet$;
import scala.collection.parallel.package$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class ParIterableLike$class {
    public static void initTaskSupport(ParIterableLike $this) {
        $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
    }

    public static TaskSupport tasksupport(ParIterableLike $this) {
        TaskSupport taskSupport;
        TaskSupport ts = $this.scala$collection$parallel$ParIterableLike$$_tasksupport();
        if (ts == null) {
            $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
            taskSupport = package$.MODULE$.defaultTaskSupport();
        } else {
            taskSupport = ts;
        }
        return taskSupport;
    }

    public static void tasksupport_$eq(ParIterableLike $this, TaskSupport ts) {
        $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(ts);
    }

    public static ParIterable repr(ParIterableLike $this) {
        return (ParIterable)$this;
    }

    public static final boolean isTraversableAgain(ParIterableLike $this) {
        return true;
    }

    public static boolean hasDefiniteSize(ParIterableLike $this) {
        return true;
    }

    public static boolean isEmpty(ParIterableLike $this) {
        return $this.size() == 0;
    }

    public static boolean nonEmpty(ParIterableLike $this) {
        return $this.size() != 0;
    }

    public static Object head(ParIterableLike $this) {
        return $this.iterator().next();
    }

    public static Option headOption(ParIterableLike $this) {
        return $this.nonEmpty() ? new Some($this.head()) : None$.MODULE$;
    }

    public static ParIterable tail(ParIterableLike $this) {
        return $this.drop(1);
    }

    public static Object last(ParIterableLike $this) {
        ObjectRef lst = ObjectRef.create($this.head());
        $this.seq().foreach(new Serializable($this, lst){
            public static final long serialVersionUID = 0L;
            private final ObjectRef lst$1;

            public final void apply(T x) {
                this.lst$1.elem = x;
            }
            {
                this.lst$1 = lst$1;
            }
        });
        return lst.elem;
    }

    public static Option lastOption(ParIterableLike $this) {
        return $this.nonEmpty() ? new Some($this.last()) : None$.MODULE$;
    }

    public static ParIterable init(ParIterableLike $this) {
        return $this.take($this.size() - 1);
    }

    public static Splitter iterator(ParIterableLike $this) {
        return $this.splitter();
    }

    public static ParIterable par(ParIterableLike $this) {
        return $this.repr();
    }

    public static boolean isStrictSplitterCollection(ParIterableLike $this) {
        return true;
    }

    public static Combiner reuse(ParIterableLike $this, Option oldc, Combiner newc) {
        return newc;
    }

    public static ParIterableLike.TaskOps task2ops(ParIterableLike $this, ParIterableLike.StrictSplitterCheckTask tsk) {
        return new ParIterableLike.TaskOps<R, Tp>($this, tsk){
            private final /* synthetic */ ParIterableLike $outer;
            public final ParIterableLike.StrictSplitterCheckTask tsk$1;

            public <R1> ParIterableLike.ResultMapping<R, Tp, R1> mapResult(Function1<R, R1> mapping) {
                return new ParIterableLike.ResultMapping<R, Tp, R1>(this, mapping){
                    private final Function1 mapping$1;

                    public R1 map(R r) {
                        return (R1)this.mapping$1.apply(r);
                    }
                    {
                        this.mapping$1 = mapping$1;
                        super($outer.scala$collection$parallel$ParIterableLike$$anon$$$outer(), $outer.tsk$1);
                    }
                };
            }

            public <R3, R2, Tp2> ParIterableLike.SeqComposite<R, R2, R3, ParIterableLike.StrictSplitterCheckTask<R, Tp>, ParIterableLike.StrictSplitterCheckTask<R2, Tp2>> compose(ParIterableLike.StrictSplitterCheckTask<R2, Tp2> t2, Function2<R, R2, R3> resCombiner) {
                return new ParIterableLike.SeqComposite<R, R2, R3, ParIterableLike.StrictSplitterCheckTask<R, Tp>, ParIterableLike.StrictSplitterCheckTask<R2, Tp2>>(this){
                    private final Function2 resCombiner$1;

                    public R3 combineResults(R fr, R2 sr) {
                        return (R3)this.resCombiner$1.apply(fr, sr);
                    }
                    {
                        this.resCombiner$1 = resCombiner$1;
                        super($outer.scala$collection$parallel$ParIterableLike$$anon$$$outer(), $outer.tsk$1, t2$1);
                    }
                };
            }

            public <R3, R2, Tp2> ParIterableLike.ParComposite<R, R2, R3, ParIterableLike.StrictSplitterCheckTask<R, Tp>, ParIterableLike.StrictSplitterCheckTask<R2, Tp2>> parallel(ParIterableLike.StrictSplitterCheckTask<R2, Tp2> t2, Function2<R, R2, R3> resCombiner) {
                return new ParIterableLike.ParComposite<R, R2, R3, ParIterableLike.StrictSplitterCheckTask<R, Tp>, ParIterableLike.StrictSplitterCheckTask<R2, Tp2>>(this){
                    private final Function2 resCombiner$2;

                    public R3 combineResults(R fr, R2 sr) {
                        return (R3)this.resCombiner$2.apply(fr, sr);
                    }
                    {
                        this.resCombiner$2 = resCombiner$2;
                        super($outer.scala$collection$parallel$ParIterableLike$$anon$$$outer(), $outer.tsk$1, t2$2);
                    }
                };
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$$anon$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tsk$1 = tsk$1;
            }
        };
    }

    public static ParIterableLike.NonDivisible wrap(ParIterableLike $this, Function0 body2) {
        return new ParIterableLike.NonDivisible<R>($this, body2){
            private volatile R result;
            private final /* synthetic */ ParIterableLike $outer;
            private final Function0 body$1;
            private volatile Throwable throwable;

            public boolean shouldSplitFurther() {
                return ParIterableLike$NonDivisibleTask$class.shouldSplitFurther(this);
            }

            public Nothing$ split() {
                return ParIterableLike$NonDivisibleTask$class.split(this);
            }

            public boolean requiresStrictSplitters() {
                return ParIterableLike$StrictSplitterCheckTask$class.requiresStrictSplitters(this);
            }

            public Throwable throwable() {
                return this.throwable;
            }

            public void throwable_$eq(Throwable x$1) {
                this.throwable = x$1;
            }

            public Object repr() {
                return Task$class.repr(this);
            }

            public void merge(Object that) {
                Task$class.merge(this, that);
            }

            public void forwardThrowable() {
                Task$class.forwardThrowable(this);
            }

            public void tryLeaf(Option<R> lastres) {
                Task$class.tryLeaf(this, lastres);
            }

            public void tryMerge(Object t) {
                Task$class.tryMerge(this, t);
            }

            public void mergeThrowables(Task<?, ?> that) {
                Task$class.mergeThrowables(this, that);
            }

            public void signalAbort() {
                Task$class.signalAbort(this);
            }

            public void leaf(Option<R> prevr) {
                this.result_$eq(this.body$1.apply());
            }

            public R result() {
                return this.result;
            }

            public void result_$eq(R x$1) {
                this.result = x$1;
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$NonDivisibleTask$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.body$1 = body$1;
                Task$class.$init$(this);
                ParIterableLike$StrictSplitterCheckTask$class.$init$(this);
                ParIterableLike$NonDivisibleTask$class.$init$(this);
                this.result = null;
            }
        };
    }

    public static ParIterableLike.SignallingOps delegatedSignalling2ops(ParIterableLike $this, DelegatedSignalling it) {
        return new ParIterableLike.SignallingOps<PI>($this, it){
            private final DelegatedSignalling it$1;

            public PI assign(Signalling cntx) {
                this.it$1.signalDelegate_$eq(cntx);
                return (PI)this.it$1;
            }
            {
                this.it$1 = it$1;
            }
        };
    }

    public static ParIterableLike.BuilderOps builder2ops(ParIterableLike $this, Builder cb) {
        return new ParIterableLike.BuilderOps<Elem, To>($this, cb){
            private final /* synthetic */ ParIterableLike $outer;
            public final Builder cb$2;

            public <Cmb> Object ifIs(Function1<Cmb, BoxedUnit> isbody) {
                return new ParIterableLike.BuilderOps.Otherwise<Cmb>(this, isbody){
                    private final /* synthetic */ ParIterableLike$.anon.13 $outer;
                    private final Function1 isbody$1;

                    public void otherwise(Function0<BoxedUnit> notbody, ClassTag<Cmb> t) {
                        Class<?> clazz = this.$outer.cb$2.getClass();
                        Class<?> clazz2 = t.runtimeClass();
                        if (!(clazz != null ? !clazz.equals(clazz2) : clazz2 != null)) {
                            this.isbody$1.apply(this.$outer.cb$2);
                        } else {
                            notbody.apply$mcV$sp();
                        }
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.isbody$1 = isbody$1;
                    }
                };
            }

            public boolean isCombiner() {
                return this.cb$2 instanceof Combiner;
            }

            public Combiner<Elem, To> asCombiner() {
                return (Combiner)this.cb$2;
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$BuilderOps$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.cb$2 = cb$2;
                ParIterableLike$BuilderOps$class.$init$(this);
            }
        };
    }

    public static CanBuildFrom bf2seq(ParIterableLike $this, CanBuildFrom bf) {
        return new CanBuildFrom<Sequential, S, That>($this, bf){
            private final CanBuildFrom bf$1;

            public Builder<S, That> apply(Sequential from2) {
                return this.bf$1.apply((ParIterable)from2.par());
            }

            public Builder<S, That> apply() {
                return this.bf$1.apply();
            }
            {
                this.bf$1 = bf$1;
            }
        };
    }

    public static ParIterable sequentially(ParIterableLike $this, Function1 b) {
        return (ParIterable)((Parallelizable)b.apply($this.seq())).par();
    }

    public static String mkString(ParIterableLike $this, String start, String sep, String end) {
        return $this.seq().mkString(start, sep, end);
    }

    public static String mkString(ParIterableLike $this, String sep) {
        return $this.seq().mkString("", sep, "");
    }

    public static String mkString(ParIterableLike $this) {
        return $this.seq().mkString("");
    }

    public static String toString(ParIterableLike $this) {
        return $this.seq().mkString(new StringBuilder().append((Object)$this.stringPrefix()).append((Object)"(").toString(), ", ", ")");
    }

    public static boolean canEqual(ParIterableLike $this, Object other) {
        return true;
    }

    public static Object reduce(ParIterableLike $this, Function2 op) {
        return $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Reduce($this, op, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final U apply(Option<U> x$1) {
                return x$1.get();
            }
        }));
    }

    public static Option reduceOption(ParIterableLike $this, Function2 op) {
        return $this.isEmpty() ? None$.MODULE$ : new Some($this.reduce(op));
    }

    public static Object fold(ParIterableLike $this, Object z, Function2 op) {
        return $this.tasksupport().executeAndWaitResult(new ParIterableLike.Fold<Object>($this, z, op, $this.splitter()));
    }

    public static Object aggregate(ParIterableLike $this, Function0 z, Function2 seqop, Function2 combop) {
        return $this.tasksupport().executeAndWaitResult(new ParIterableLike.Aggregate($this, z, seqop, combop, $this.splitter()));
    }

    public static Object foldLeft(ParIterableLike $this, Object z, Function2 op) {
        return $this.seq().foldLeft((Object)z, op);
    }

    public static Object foldRight(ParIterableLike $this, Object z, Function2 op) {
        return $this.seq().foldRight((Object)z, op);
    }

    public static Object reduceLeft(ParIterableLike $this, Function2 op) {
        return $this.seq().reduceLeft(op);
    }

    public static Object reduceRight(ParIterableLike $this, Function2 op) {
        return $this.seq().reduceRight(op);
    }

    public static Option reduceLeftOption(ParIterableLike $this, Function2 op) {
        return $this.seq().reduceLeftOption(op);
    }

    public static Option reduceRightOption(ParIterableLike $this, Function2 op) {
        return $this.seq().reduceRightOption(op);
    }

    public static void foreach(ParIterableLike $this, Function1 f) {
        $this.tasksupport().executeAndWaitResult(new ParIterableLike.Foreach($this, f, $this.splitter()));
    }

    public static int count(ParIterableLike $this, Function1 p) {
        return BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParIterableLike.Count($this, p, $this.splitter())));
    }

    public static Object sum(ParIterableLike $this, Numeric num) {
        return $this.tasksupport().executeAndWaitResult(new ParIterableLike.Sum($this, num, $this.splitter()));
    }

    public static Object product(ParIterableLike $this, Numeric num) {
        return $this.tasksupport().executeAndWaitResult(new ParIterableLike.Product($this, num, $this.splitter()));
    }

    public static Object min(ParIterableLike $this, Ordering ord) {
        return $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Min($this, ord, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final U apply(Option<U> x$2) {
                return x$2.get();
            }
        }));
    }

    public static Object max(ParIterableLike $this, Ordering ord) {
        return $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Max($this, ord, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final U apply(Option<U> x$3) {
                return x$3.get();
            }
        }));
    }

    public static Object maxBy(ParIterableLike $this, Function1 f, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.maxBy");
        }
        return $this.reduce(new Serializable($this, f, cmp){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;
            private final Ordering cmp$1;

            public final T apply(T x, T y) {
                return this.cmp$1.gteq(this.f$1.apply(x), this.f$1.apply(y)) ? x : y;
            }
            {
                void var3_3;
                this.f$1 = f$1;
                this.cmp$1 = var3_3;
            }
        });
    }

    public static Object minBy(ParIterableLike $this, Function1 f, Ordering cmp) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.minBy");
        }
        return $this.reduce(new Serializable($this, f, cmp){
            public static final long serialVersionUID = 0L;
            private final Function1 f$2;
            private final Ordering cmp$2;

            public final T apply(T x, T y) {
                return this.cmp$2.lteq(this.f$2.apply(x), this.f$2.apply(y)) ? x : y;
            }
            {
                void var3_3;
                this.f$2 = f$2;
                this.cmp$2 = var3_3;
            }
        });
    }

    public static Object map(ParIterableLike $this, Function1 f, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Map($this, f, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike $outer;
            private final CanBuildFrom bf$2;

            public final Combiner<S, That> apply() {
                return this.$outer.builder2ops(this.bf$2.apply(this.$outer.repr())).asCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.bf$2 = bf$2;
            }
        })), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<S, That> x$4) {
                return x$4.resultWithTaskSupport();
            }
        })) : package$.MODULE$.setTaskSupport($this.seq().map(f, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static Object collect(ParIterableLike $this, PartialFunction pf, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Collect($this, pf, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike $outer;
            private final CanBuildFrom bf$3;

            public final Combiner<S, That> apply() {
                return this.$outer.builder2ops(this.bf$3.apply(this.$outer.repr())).asCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.bf$3 = bf$3;
            }
        })), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<S, That> x$5) {
                return x$5.resultWithTaskSupport();
            }
        })) : package$.MODULE$.setTaskSupport($this.seq().collect(pf, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static Object flatMap(ParIterableLike $this, Function1 f, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.FlatMap($this, f, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike $outer;
            private final CanBuildFrom bf$4;

            public final Combiner<S, That> apply() {
                return this.$outer.builder2ops(this.bf$4.apply(this.$outer.repr())).asCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.bf$4 = bf$4;
            }
        })), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<S, That> x$6) {
                return x$6.resultWithTaskSupport();
            }
        })) : package$.MODULE$.setTaskSupport($this.seq().flatMap(f, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static boolean forall(ParIterableLike $this, Function1 p) {
        return BoxesRunTime.unboxToBoolean($this.tasksupport().executeAndWaitResult(new ParIterableLike.Forall($this, p, (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(new DefaultSignalling($this){}))));
    }

    public static boolean exists(ParIterableLike $this, Function1 p) {
        return BoxesRunTime.unboxToBoolean($this.tasksupport().executeAndWaitResult(new ParIterableLike.Exists($this, p, (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(new DefaultSignalling($this){}))));
    }

    public static Option find(ParIterableLike $this, Function1 p) {
        return (Option)$this.tasksupport().executeAndWaitResult(new ParIterableLike.Find($this, p, (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(new DefaultSignalling($this){})));
    }

    public static CombinerFactory combinerFactory(ParIterableLike $this) {
        Combiner combiner = $this.newCombiner();
        combiner.combinerTaskSupport_$eq($this.tasksupport());
        return combiner.canBeShared() ? new CombinerFactory<T, Repr>($this, combiner){
            private final Combiner<T, Repr> shared;

            public Combiner<T, Repr> shared() {
                return this.shared;
            }

            public Combiner<T, Repr> apply() {
                return this.shared();
            }

            public boolean doesShareCombiners() {
                return true;
            }
            {
                this.shared = combiner$2;
            }
        } : new CombinerFactory<T, Repr>($this){
            private final /* synthetic */ ParIterableLike $outer;

            public Combiner<T, Repr> apply() {
                return this.$outer.newCombiner();
            }

            public boolean doesShareCombiners() {
                return false;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public static CombinerFactory combinerFactory(ParIterableLike $this, Function0 cbf) {
        Combiner combiner = (Combiner)cbf.apply();
        combiner.combinerTaskSupport_$eq($this.tasksupport());
        return combiner.canBeShared() ? new CombinerFactory<S, That>($this, combiner){
            private final Combiner<S, That> shared;

            public Combiner<S, That> shared() {
                return this.shared;
            }

            public Combiner<S, That> apply() {
                return this.shared();
            }

            public boolean doesShareCombiners() {
                return true;
            }
            {
                this.shared = combiner$1;
            }
        } : new CombinerFactory<S, That>($this, cbf){
            private final Function0 cbf$1;

            public Combiner<S, That> apply() {
                return (Combiner)this.cbf$1.apply();
            }

            public boolean doesShareCombiners() {
                return false;
            }
            {
                this.cbf$1 = cbf$1;
            }
        };
    }

    public static ParIterable withFilter(ParIterableLike $this, Function1 pred) {
        return $this.filter(pred);
    }

    public static ParIterable filter(ParIterableLike $this, Function1 pred) {
        return (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Filter($this, pred, $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$7) {
                return (Repr)((ParIterable)x$7.resultWithTaskSupport());
            }
        }));
    }

    public static ParIterable filterNot(ParIterableLike $this, Function1 pred) {
        return (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.FilterNot($this, pred, $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$8) {
                return (Repr)((ParIterable)x$8.resultWithTaskSupport());
            }
        }));
    }

    public static Object $plus$plus(ParIterableLike $this, GenTraversableOnce that, CanBuildFrom bf) {
        Object object;
        if (ParallelCollectionImplicits$.MODULE$.traversable2ops(that).isParallel() && ParallelCollectionImplicits$.MODULE$.factory2ops(bf).isParallel()) {
            ParIterable other = ParallelCollectionImplicits$.MODULE$.traversable2ops(that).asParIterable();
            CanCombineFrom pbf = ParallelCollectionImplicits$.MODULE$.factory2ops(bf).asParallel();
            CombinerFactory cfactory = $this.combinerFactory((Function0)((Object)new Serializable($this, pbf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final CanCombineFrom pbf$1;

                public final Combiner<U, That> apply() {
                    return this.pbf$1.apply(this.$outer.repr());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.pbf$1 = pbf$1;
                }
            }));
            ParIterableLike.Copy copythis = new ParIterableLike.Copy($this, cfactory, $this.splitter());
            ParIterableLike.NonDivisible copythat = $this.wrap((Function0)((Object)new Serializable($this, other, cfactory){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final ParIterable other$1;
                private final CombinerFactory cfactory$1;

                public final Combiner<U, That> apply() {
                    ParIterableLike.Copy<U, That> othtask = new ParIterableLike.Copy<U, That>(this.other$1, this.cfactory$1, this.other$1.splitter());
                    return (Combiner)this.$outer.tasksupport().executeAndWaitResult(othtask);
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.other$1 = other$1;
                    this.cfactory$1 = var3_3;
                }
            }));
            ParIterableLike.ResultMapping task = $this.task2ops($this.task2ops(copythis).parallel(copythat, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Combiner<U, That> apply(Combiner<U, That> x$9, Combiner<U, That> x$10) {
                    return x$9.combine(x$10);
                }
            })).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<U, That> x$11) {
                    return x$11.resultWithTaskSupport();
                }
            });
            object = $this.tasksupport().executeAndWaitResult(task);
        } else if ($this.builder2ops(bf.apply($this.repr())).isCombiner()) {
            ParIterableLike.Copy copythis = new ParIterableLike.Copy($this, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final CanBuildFrom bf$5;

                public final Combiner<U, That> apply() {
                    return this.$outer.builder2ops(this.bf$5.apply(this.$outer.repr())).asCombiner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.bf$5 = bf$5;
                }
            })), $this.splitter());
            ParIterableLike.NonDivisible copythat = $this.wrap((Function0)((Object)new Serializable($this, that, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final GenTraversableOnce that$1;
                private final CanBuildFrom bf$5;

                /*
                 * WARNING - void declaration
                 */
                public final Combiner<U, That> apply() {
                    void var1_1;
                    Combiner<Elem, To> cb = this.$outer.builder2ops(this.bf$5.apply(this.$outer.repr())).asCombiner();
                    this.that$1.seq().foreach(new Serializable(this, cb){
                        public static final long serialVersionUID = 0L;
                        private final Combiner cb$1;

                        public final Combiner<U, That> apply(U elem) {
                            return (Combiner)this.cb$1.$plus$eq(elem);
                        }
                        {
                            this.cb$1 = cb$1;
                        }
                    });
                    return var1_1;
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.that$1 = that$1;
                    this.bf$5 = var3_3;
                }
            }));
            object = $this.tasksupport().executeAndWaitResult($this.task2ops($this.task2ops(copythis).parallel(copythat, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Combiner<U, That> apply(Combiner<U, That> x$12, Combiner<U, That> x$13) {
                    return x$12.combine(x$13);
                }
            })).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<U, That> x$14) {
                    return x$14.resultWithTaskSupport();
                }
            }));
        } else {
            Builder b = bf.apply($this.repr());
            $this.splitter().copy2builder(b);
            that.seq().foreach(new Serializable($this, b){
                public static final long serialVersionUID = 0L;
                private final Builder b$1;

                public final Builder<U, That> apply(U elem) {
                    return this.b$1.$plus$eq(elem);
                }
                {
                    this.b$1 = b$1;
                }
            });
            object = package$.MODULE$.setTaskSupport(b.result(), $this.tasksupport());
        }
        return object;
    }

    public static Tuple2 partition(ParIterableLike $this, Function1 pred) {
        return (Tuple2)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Partition($this, pred, $this.combinerFactory(), $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Tuple2<Repr, Repr> apply(Tuple2<Combiner<T, Repr>, Combiner<T, Repr>> p) {
                return new Tuple2<Repr, Repr>(p._1().resultWithTaskSupport(), p._2().resultWithTaskSupport());
            }
        }));
    }

    public static ParMap groupBy(ParIterableLike $this, Function1 f) {
        ParHashMap r = (ParHashMap)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.GroupBy($this, f, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashMapCombiner<K, T> apply() {
                return HashMapCombiner$.MODULE$.apply();
            }
        }, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ ParIterableLike $outer;

            public final ParHashMap<K, Repr> apply(HashMapCombiner<K, T> rcb) {
                return rcb.groupByKey(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParIterableLike$.anonfun.8 $outer;

                    public final Combiner<T, Repr> apply() {
                        return this.$outer.$outer.combinerFactory().apply();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        return package$.MODULE$.setTaskSupport(r, $this.tasksupport());
    }

    public static ParIterable take(ParIterableLike $this, int n) {
        int actualn = $this.size() > n ? n : $this.size();
        return actualn < package$.MODULE$.MIN_FOR_COPY() ? ParIterableLike$class.take_sequential($this, actualn) : (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Take($this, actualn, $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$15) {
                return (Repr)((ParIterable)x$15.resultWithTaskSupport());
            }
        }));
    }

    private static ParIterable take_sequential(ParIterableLike $this, int n) {
        Combiner cb = $this.newCombiner();
        cb.sizeHint(n);
        IterableSplitter it = $this.splitter();
        for (int left = n; left > 0; --left) {
            cb.$plus$eq(it.next());
        }
        return (ParIterable)cb.resultWithTaskSupport();
    }

    public static ParIterable drop(ParIterableLike $this, int n) {
        int actualn = $this.size() > n ? n : $this.size();
        return $this.size() - actualn < package$.MODULE$.MIN_FOR_COPY() ? ParIterableLike$class.drop_sequential($this, actualn) : (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Drop($this, actualn, $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$16) {
                return (Repr)((ParIterable)x$16.resultWithTaskSupport());
            }
        }));
    }

    private static ParIterable drop_sequential(ParIterableLike $this, int n) {
        Iterator it = $this.splitter().drop(n);
        Combiner cb = $this.newCombiner();
        cb.sizeHint($this.size() - n);
        while (it.hasNext()) {
            cb.$plus$eq(it.next());
        }
        return (ParIterable)cb.resultWithTaskSupport();
    }

    public static ParIterable slice(ParIterableLike $this, int unc_from, int unc_until) {
        Predef$ predef$ = Predef$.MODULE$;
        int n = RichInt$.MODULE$.min$extension(unc_from, $this.size());
        Predef$ predef$2 = Predef$.MODULE$;
        int from2 = RichInt$.MODULE$.max$extension(n, 0);
        Predef$ predef$3 = Predef$.MODULE$;
        int n2 = RichInt$.MODULE$.min$extension(unc_until, $this.size());
        Predef$ predef$4 = Predef$.MODULE$;
        int until2 = RichInt$.MODULE$.max$extension(n2, from2);
        return until2 - from2 <= package$.MODULE$.MIN_FOR_COPY() ? ParIterableLike$class.slice_sequential($this, from2, until2) : (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Slice($this, from2, until2, $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$17) {
                return (Repr)((ParIterable)x$17.resultWithTaskSupport());
            }
        }));
    }

    private static ParIterable slice_sequential(ParIterableLike $this, int from2, int until2) {
        Combiner cb = $this.newCombiner();
        Iterator it = $this.splitter().drop(from2);
        for (int left = until2 - from2; left > 0; --left) {
            cb.$plus$eq(it.next());
        }
        return (ParIterable)cb.resultWithTaskSupport();
    }

    public static Tuple2 splitAt(ParIterableLike $this, int n) {
        return (Tuple2)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.SplitAt($this, n, $this.combinerFactory(), $this.combinerFactory(), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Tuple2<Repr, Repr> apply(Tuple2<Combiner<T, Repr>, Combiner<T, Repr>> p) {
                return new Tuple2<Repr, Repr>(p._1().resultWithTaskSupport(), p._2().resultWithTaskSupport());
            }
        }));
    }

    public static Object scan(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? ($this.tasksupport().parallelismLevel() > 1 ? ($this.size() > 0 ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.CreateScanTree<Object>($this, 0, $this.size(), z, op, $this.splitter())).mapResult(new Serializable($this, z, op, bf){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ ParIterableLike $outer;
            private final Object z$1;
            private final Function2 op$1;
            public final CanBuildFrom bf$6;

            public final That apply(ParIterableLike.ScanTree<U> tree) {
                return (That)this.$outer.tasksupport().executeAndWaitResult(this.$outer.task2ops(new ParIterableLike.FromScanTree<Object, That>(this.$outer, tree, this.z$1, this.op$1, this.$outer.combinerFactory((Function0)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ ParIterableLike$.anonfun.scan.1 $outer;

                    public final Combiner<U, That> apply() {
                        return this.$outer.$outer.builder2ops(this.$outer.bf$6.apply(this.$outer.$outer.repr())).asCombiner();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })))).mapResult(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final That apply(Combiner<U, That> cb) {
                        return cb.resultWithTaskSupport();
                    }
                }));
            }

            public /* synthetic */ ParIterableLike scala$collection$parallel$ParIterableLike$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                void var4_4;
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.z$1 = z$1;
                this.op$1 = var3_3;
                this.bf$6 = var4_4;
            }
        })) : package$.MODULE$.setTaskSupport(bf.apply($this.repr()).$plus$eq(z).result(), $this.tasksupport())) : package$.MODULE$.setTaskSupport($this.seq().scan((Object)z, op, $this.bf2seq(bf)), $this.tasksupport())) : package$.MODULE$.setTaskSupport($this.seq().scan((Object)z, op, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static Object scanLeft(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return package$.MODULE$.setTaskSupport($this.seq().scanLeft((Object)z, op, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static Object scanRight(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return package$.MODULE$.setTaskSupport($this.seq().scanRight((Object)z, op, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static ParIterable takeWhile(ParIterableLike $this, Function1 pred) {
        ParIterable parIterable;
        Object cbf = $this.combinerFactory();
        if (cbf.doesShareCombiners()) {
            ParSeq parseqspan = (ParSeq)$this.toSeq().takeWhile(pred);
            parIterable = (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Copy($this, $this.combinerFactory(), parseqspan.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Repr apply(Combiner<T, Repr> x$18) {
                    return (Repr)((ParIterable)x$18.resultWithTaskSupport());
                }
            }));
        } else {
            AtomicIndexFlag cntx = new AtomicIndexFlag($this){
                private final AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

                public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
                    return this.scala$collection$generic$AtomicIndexFlag$$intflag;
                }

                public void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(AtomicInteger x$1) {
                    this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
                }

                public int indexFlag() {
                    return AtomicIndexFlag$class.indexFlag(this);
                }

                public void setIndexFlag(int f) {
                    AtomicIndexFlag$class.setIndexFlag(this, f);
                }

                public void setIndexFlagIfGreater(int f) {
                    AtomicIndexFlag$class.setIndexFlagIfGreater(this, f);
                }

                public void setIndexFlagIfLesser(int f) {
                    AtomicIndexFlag$class.setIndexFlagIfLesser(this, f);
                }
                {
                    AtomicIndexFlag$class.$init$(this);
                }
            };
            ((AtomicIndexFlag)cntx).setIndexFlag(Integer.MAX_VALUE);
            parIterable = (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.TakeWhile($this, 0, pred, $this.combinerFactory(), (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(cntx))).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Repr apply(Tuple2<Combiner<T, Repr>, Object> x$19) {
                    return (Repr)((ParIterable)x$19._1().resultWithTaskSupport());
                }
            }));
        }
        return parIterable;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Tuple2 span(ParIterableLike $this, Function1 pred) {
        Tuple2 tuple2;
        Object cbf = $this.combinerFactory();
        if (cbf.doesShareCombiners()) {
            Tuple2 tuple22 = $this.toSeq().span(pred);
            if (tuple22 == null) throw new MatchError(tuple22);
            Tuple2 tuple23 = new Tuple2(tuple22._1(), tuple22._2());
            ParSeq xs = (ParSeq)tuple23._1();
            ParSeq ys = (ParSeq)tuple23._2();
            ParIterableLike.ResultMapping copyxs = $this.task2ops(new ParIterableLike.Copy($this, $this.combinerFactory(), xs.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Repr apply(Combiner<T, Repr> x$21) {
                    return (Repr)((ParIterable)x$21.resultWithTaskSupport());
                }
            });
            ParIterableLike.ResultMapping copyys = $this.task2ops(new ParIterableLike.Copy($this, $this.combinerFactory(), ys.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Repr apply(Combiner<T, Repr> x$22) {
                    return (Repr)((ParIterable)x$22.resultWithTaskSupport());
                }
            });
            ParIterableLike.ParComposite copyall = $this.task2ops(copyxs).parallel(copyys, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Tuple2<Repr, Repr> apply(Repr xr, Repr yr) {
                    return new Tuple2<Repr, Repr>(xr, yr);
                }
            });
            tuple2 = (Tuple2)$this.tasksupport().executeAndWaitResult(copyall);
            return tuple2;
        } else {
            AtomicIndexFlag cntx = new AtomicIndexFlag($this){
                private final AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

                public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
                    return this.scala$collection$generic$AtomicIndexFlag$$intflag;
                }

                public void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(AtomicInteger x$1) {
                    this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
                }

                public int indexFlag() {
                    return AtomicIndexFlag$class.indexFlag(this);
                }

                public void setIndexFlag(int f) {
                    AtomicIndexFlag$class.setIndexFlag(this, f);
                }

                public void setIndexFlagIfGreater(int f) {
                    AtomicIndexFlag$class.setIndexFlagIfGreater(this, f);
                }

                public void setIndexFlagIfLesser(int f) {
                    AtomicIndexFlag$class.setIndexFlagIfLesser(this, f);
                }
                {
                    AtomicIndexFlag$class.$init$(this);
                }
            };
            ((AtomicIndexFlag)cntx).setIndexFlag(Integer.MAX_VALUE);
            tuple2 = (Tuple2)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Span($this, 0, pred, $this.combinerFactory(), $this.combinerFactory(), (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(cntx))).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Tuple2<Repr, Repr> apply(Tuple2<Combiner<T, Repr>, Combiner<T, Repr>> p) {
                    return new Tuple2<Repr, Repr>(p._1().resultWithTaskSupport(), p._2().resultWithTaskSupport());
                }
            }));
        }
        return tuple2;
    }

    public static ParIterable dropWhile(ParIterableLike $this, Function1 pred) {
        AtomicIndexFlag cntx = new AtomicIndexFlag($this){
            private final AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

            public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
                return this.scala$collection$generic$AtomicIndexFlag$$intflag;
            }

            public void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(AtomicInteger x$1) {
                this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
            }

            public int indexFlag() {
                return AtomicIndexFlag$class.indexFlag(this);
            }

            public void setIndexFlag(int f) {
                AtomicIndexFlag$class.setIndexFlag(this, f);
            }

            public void setIndexFlagIfGreater(int f) {
                AtomicIndexFlag$class.setIndexFlagIfGreater(this, f);
            }

            public void setIndexFlagIfLesser(int f) {
                AtomicIndexFlag$class.setIndexFlagIfLesser(this, f);
            }
            {
                AtomicIndexFlag$class.$init$(this);
            }
        };
        ((AtomicIndexFlag)cntx).setIndexFlag(Integer.MAX_VALUE);
        return (ParIterable)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Span($this, 0, pred, $this.combinerFactory(), $this.combinerFactory(), (IterableSplitter)$this.delegatedSignalling2ops($this.splitter()).assign(cntx))).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Tuple2<Combiner<T, Repr>, Combiner<T, Repr>> x$23) {
                return (Repr)((ParIterable)x$23._2().resultWithTaskSupport());
            }
        }));
    }

    public static void copyToArray(ParIterableLike $this, Object xs) {
        $this.copyToArray(xs, 0);
    }

    public static void copyToArray(ParIterableLike $this, Object xs, int start) {
        $this.copyToArray(xs, start, ScalaRunTime$.MODULE$.array_length(xs) - start);
    }

    public static void copyToArray(ParIterableLike $this, Object xs, int start, int len) {
        if (len > 0) {
            $this.tasksupport().executeAndWaitResult(new ParIterableLike.CopyToArray($this, start, len, xs, $this.splitter()));
        }
    }

    public static boolean sameElements(ParIterableLike $this, GenIterable that) {
        return $this.seq().sameElements(that);
    }

    public static Object zip(ParIterableLike $this, GenIterable that, CanBuildFrom bf) {
        Object object;
        if ($this.builder2ops(bf.apply($this.repr())).isCombiner() && ParallelCollectionImplicits$.MODULE$.traversable2ops(that).isParSeq()) {
            ParSeq thatseq = ParallelCollectionImplicits$.MODULE$.traversable2ops(that).asParSeq();
            object = $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.Zip($this, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final CanBuildFrom bf$7;

                public final Combiner<Tuple2<U, S>, That> apply() {
                    return this.$outer.builder2ops(this.bf$7.apply(this.$outer.repr())).asCombiner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.bf$7 = bf$7;
                }
            })), $this.splitter(), thatseq.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<Tuple2<U, S>, That> x$24) {
                    return x$24.resultWithTaskSupport();
                }
            }));
        } else {
            object = package$.MODULE$.setTaskSupport($this.seq().zip(that, $this.bf2seq(bf)), $this.tasksupport());
        }
        return object;
    }

    public static Object zipWithIndex(ParIterableLike $this, CanBuildFrom bf) {
        return $this.zip(ParRange$.MODULE$.apply(0, $this.size(), 1, false), bf);
    }

    public static Object zipAll(ParIterableLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
        Object object;
        if ($this.builder2ops(bf.apply($this.repr())).isCombiner() && ParallelCollectionImplicits$.MODULE$.traversable2ops(that).isParSeq()) {
            ParSeq thatseq = ParallelCollectionImplicits$.MODULE$.traversable2ops(that).asParSeq();
            int n = $this.size();
            Predef$ predef$ = Predef$.MODULE$;
            object = $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.ZipAll($this, RichInt$.MODULE$.max$extension(n, thatseq.length()), thisElem, thatElem, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParIterableLike $outer;
                private final CanBuildFrom bf$8;

                public final Combiner<Tuple2<U, S>, That> apply() {
                    return this.$outer.builder2ops(this.bf$8.apply(this.$outer.repr())).asCombiner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.bf$8 = bf$8;
                }
            })), $this.splitter(), thatseq.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<Tuple2<U, S>, That> x$25) {
                    return x$25.resultWithTaskSupport();
                }
            }));
        } else {
            object = package$.MODULE$.setTaskSupport($this.seq().zipAll(that, (Object)thisElem, (Object)thatElem, $this.bf2seq(bf)), $this.tasksupport());
        }
        return object;
    }

    public static Object toParCollection(ParIterableLike $this, Function0 cbf) {
        return $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.ToParCollection($this, $this.combinerFactory(cbf), $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<U, That> x$26) {
                return x$26.resultWithTaskSupport();
            }
        }));
    }

    public static Object toParMap(ParIterableLike $this, Function0 cbf, Predef$.less.colon.less ev) {
        return $this.tasksupport().executeAndWaitResult($this.task2ops(new ParIterableLike.ToParMap($this, $this.combinerFactory(cbf), $this.splitter(), ev)).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<Tuple2<K, V>, That> x$27) {
                return x$27.resultWithTaskSupport();
            }
        }));
    }

    public static IterableView view(ParIterableLike $this) {
        return $this.seq().view();
    }

    /*
     * WARNING - void declaration
     */
    public static Object toArray(ParIterableLike $this, ClassTag evidence$1) {
        void var2_2;
        Object arr = evidence$1.newArray($this.size());
        $this.copyToArray(arr);
        return var2_2;
    }

    public static List toList(ParIterableLike $this) {
        return $this.seq().toList();
    }

    public static IndexedSeq toIndexedSeq(ParIterableLike $this) {
        return $this.seq().toIndexedSeq();
    }

    public static Stream toStream(ParIterableLike $this) {
        return $this.seq().toStream();
    }

    public static Iterator toIterator(ParIterableLike $this) {
        return $this.splitter();
    }

    public static Buffer toBuffer(ParIterableLike $this) {
        return $this.seq().toBuffer();
    }

    public static GenTraversable toTraversable(ParIterableLike $this) {
        return (GenTraversable)((Object)$this);
    }

    public static ParIterable toIterable(ParIterableLike $this) {
        return (ParIterable)$this;
    }

    public static ParSeq toSeq(ParIterableLike $this) {
        return (ParSeq)$this.toParCollection(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Combiner<T, ParSeq<T>> apply() {
                return ParSeq$.MODULE$.newCombiner();
            }
        });
    }

    public static ParSet toSet(ParIterableLike $this) {
        return (ParSet)$this.toParCollection(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Combiner<U, ParSet<U>> apply() {
                return ParSet$.MODULE$.newCombiner();
            }
        });
    }

    public static ParMap toMap(ParIterableLike $this, Predef$.less.colon.less ev) {
        return (ParMap)$this.toParMap(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Combiner<Tuple2<K, V>, ParMap<K, V>> apply() {
                return ParMap$.MODULE$.newCombiner();
            }
        }, ev);
    }

    public static Vector toVector(ParIterableLike $this) {
        return $this.to(Vector$.MODULE$.canBuildFrom());
    }

    public static Object to(ParIterableLike $this, CanBuildFrom cbf) {
        return $this.builder2ops(cbf.apply()).isCombiner() ? $this.toParCollection(new Serializable($this, cbf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike $outer;
            private final CanBuildFrom cbf$2;

            public final Combiner<T, Col> apply() {
                return this.$outer.builder2ops(this.cbf$2.apply()).asCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.cbf$2 = cbf$2;
            }
        }) : $this.seq().to(cbf);
    }

    public static int scanBlockSize(ParIterableLike $this) {
        int n = package$.MODULE$.thresholdFromSize($this.size(), $this.tasksupport().parallelismLevel()) / 2;
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.max$extension(n, 1);
    }

    public static Object $div$colon(ParIterableLike $this, Object z, Function2 op) {
        return $this.foldLeft(z, op);
    }

    public static Object $colon$bslash(ParIterableLike $this, Object z, Function2 op) {
        return $this.foldRight(z, op);
    }

    public static String debugInformation(ParIterableLike $this) {
        return new StringBuilder().append((Object)"Parallel collection: ").append($this.getClass()).toString();
    }

    public static Seq brokenInvariants(ParIterableLike $this) {
        return (Seq)Seq$.MODULE$.apply(Nil$.MODULE$);
    }

    public static ArrayBuffer debugBuffer(ParIterableLike $this) {
        return null;
    }

    public static void debugclear(ParIterableLike $this) {
        ParIterableLike parIterableLike = $this;
        synchronized (parIterableLike) {
            $this.debugBuffer().clear();
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
        }
    }

    public static ArrayBuffer debuglog(ParIterableLike $this, String s2) {
        ParIterableLike parIterableLike = $this;
        synchronized (parIterableLike) {
            Growable growable = $this.debugBuffer().$plus$eq((Object)s2);
            // ** MonitorExit[$this] (shouldn't be in output)
            return (ArrayBuffer)growable;
        }
    }

    public static void printDebugBuffer(ParIterableLike $this) {
        Predef$.MODULE$.println(DebugUtils$.MODULE$.buildString((Function1<Function1<Object, BoxedUnit>, BoxedUnit>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParIterableLike $outer;

            public final void apply(Function1<Object, BoxedUnit> append2) {
                this.$outer.debugBuffer().foreach(new Serializable(this, append2){
                    public static final long serialVersionUID = 0L;
                    private final Function1 append$1;

                    public final void apply(String s2) {
                        this.append$1.apply(s2);
                    }
                    {
                        this.append$1 = append$1;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })));
    }

    public static void $init$(ParIterableLike $this) {
        $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
    }
}

