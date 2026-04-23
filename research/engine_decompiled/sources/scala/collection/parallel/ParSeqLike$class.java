/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.SeqView;
import scala.collection.TraversableOnce;
import scala.collection.generic.AtomicIndexFlag;
import scala.collection.generic.AtomicIndexFlag$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DefaultSignalling;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeqLike;
import scala.collection.parallel.ParallelCollectionImplicits$;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.immutable.Repetition;
import scala.collection.parallel.mutable.ParArray$;
import scala.collection.parallel.package$;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

public abstract class ParSeqLike$class {
    public static PreciseSplitter iterator(ParSeqLike $this) {
        return $this.splitter();
    }

    public static int size(ParSeqLike $this) {
        return $this.length();
    }

    public static int segmentLength(ParSeqLike $this, Function1 p, int from2) {
        int n;
        if (from2 >= $this.length()) {
            n = 0;
        } else {
            int realfrom = from2 < 0 ? 0 : from2;
            AtomicIndexFlag ctx = new AtomicIndexFlag($this){
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
            ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MAX_VALUE);
            n = $this.tasksupport().executeAndWaitResult(new ParSeqLike.SegmentLength($this, p, 0, (SeqSplitter)$this.delegatedSignalling2ops((DelegatedSignalling)$this.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{realfrom, $this.length() - realfrom})).apply(true)).assign(ctx)))._1$mcI$sp();
        }
        return n;
    }

    public static int indexWhere(ParSeqLike $this, Function1 p, int from2) {
        int n;
        if (from2 >= $this.length()) {
            n = -1;
        } else {
            int realfrom = from2 < 0 ? 0 : from2;
            AtomicIndexFlag ctx = new AtomicIndexFlag($this){
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
            ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MAX_VALUE);
            n = BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParSeqLike.IndexWhere($this, p, realfrom, (SeqSplitter)$this.delegatedSignalling2ops((DelegatedSignalling)$this.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{realfrom, $this.length() - realfrom})).apply(true)).assign(ctx))));
        }
        return n;
    }

    public static int lastIndexWhere(ParSeqLike $this, Function1 p, int end) {
        int n;
        if (end < 0) {
            n = -1;
        } else {
            int until2 = end >= $this.length() ? $this.length() : end + 1;
            AtomicIndexFlag ctx = new AtomicIndexFlag($this){
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
            ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MIN_VALUE);
            n = BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParSeqLike.LastIndexWhere($this, p, 0, (SeqSplitter)$this.delegatedSignalling2ops((DelegatedSignalling)$this.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{until2, $this.length() - until2})).apply(false)).assign(ctx))));
        }
        return n;
    }

    public static ParSeq reverse(ParSeqLike $this) {
        return (ParSeq)$this.tasksupport().executeAndWaitResult($this.task2ops(new ParSeqLike.Reverse($this, new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;

            public final Combiner<T, Repr> apply() {
                return this.$outer.newCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Repr apply(Combiner<T, Repr> x$4) {
                return (Repr)((ParSeq)x$4.resultWithTaskSupport());
            }
        }));
    }

    public static Object reverseMap(ParSeqLike $this, Function1 f, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParSeqLike.ReverseMap($this, f, new Serializable($this, bf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final CanBuildFrom bf$1;

            public final Combiner<S, That> apply() {
                return this.$outer.builder2ops(this.bf$1.apply(this.$outer.repr())).asCombiner();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.bf$1 = bf$1;
            }
        }, $this.splitter())).mapResult(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final That apply(Combiner<S, That> x$5) {
                return x$5.resultWithTaskSupport();
            }
        })) : package$.MODULE$.setTaskSupport(((SeqLike)$this.seq()).reverseMap(f, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static boolean startsWith(ParSeqLike $this, GenSeq that, int offset) {
        return BoxesRunTime.unboxToBoolean(ParallelCollectionImplicits$.MODULE$.traversable2ops(that).ifParSeq(new Serializable($this, offset){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final int offset$1;

            public final boolean apply(ParSeq<S> pthat) {
                boolean bl;
                if (this.offset$1 < 0 || this.offset$1 >= this.$outer.length()) {
                    bl = this.offset$1 == this.$outer.length() && pthat.length() == 0;
                } else if (pthat.length() == 0) {
                    bl = true;
                } else if (pthat.length() > this.$outer.length() - this.offset$1) {
                    bl = false;
                } else {
                    DefaultSignalling ctx = new DefaultSignalling(this){};
                    bl = BoxesRunTime.unboxToBoolean(this.$outer.tasksupport().executeAndWaitResult(new ParSeqLike.SameElements<T>(this.$outer, (SeqSplitter)this.$outer.delegatedSignalling2ops((DelegatedSignalling)this.$outer.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{this.offset$1, pthat.length()})).apply(true)).assign(ctx), pthat.splitter())));
                }
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.offset$1 = offset$1;
            }
        }).otherwise((Function0<Object>)((Object)new Serializable($this, that, offset){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final GenSeq that$2;
            private final int offset$1;

            public final boolean apply() {
                return this.apply$mcZ$sp();
            }

            public boolean apply$mcZ$sp() {
                return ((SeqLike)this.$outer.seq()).startsWith(this.that$2, this.offset$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$2 = that$2;
                this.offset$1 = offset$1;
            }
        })));
    }

    public static boolean sameElements(ParSeqLike $this, GenIterable that) {
        return BoxesRunTime.unboxToBoolean(ParallelCollectionImplicits$.MODULE$.traversable2ops(that).ifParSeq(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;

            public final boolean apply(ParSeq<U> pthat) {
                DefaultSignalling ctx = new DefaultSignalling(this){};
                return this.$outer.length() == pthat.length() && BoxesRunTime.unboxToBoolean(this.$outer.tasksupport().executeAndWaitResult(new ParSeqLike.SameElements<T>(this.$outer, (SeqSplitter)this.$outer.delegatedSignalling2ops(this.$outer.splitter()).assign(ctx), pthat.splitter())));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }).otherwise((Function0<Object>)((Object)new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ ParSeqLike $outer;
            public final GenIterable that$3;

            public final boolean apply() {
                return this.$outer.seq().sameElements(this.that$3);
            }

            public boolean apply$mcZ$sp() {
                return this.$outer.seq().sameElements(this.that$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$3 = that$3;
            }
        })));
    }

    public static boolean endsWith(ParSeqLike $this, GenSeq that) {
        return BoxesRunTime.unboxToBoolean(ParallelCollectionImplicits$.MODULE$.traversable2ops(that).ifParSeq(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final GenSeq that$4;

            public final boolean apply(ParSeq<S> pthat) {
                boolean bl;
                if (this.that$4.length() == 0) {
                    bl = true;
                } else if (this.that$4.length() > this.$outer.length()) {
                    bl = false;
                } else {
                    DefaultSignalling ctx = new DefaultSignalling(this){};
                    int tlen = this.that$4.length();
                    bl = BoxesRunTime.unboxToBoolean(this.$outer.tasksupport().executeAndWaitResult(new ParSeqLike.SameElements<T>(this.$outer, (SeqSplitter)this.$outer.delegatedSignalling2ops((DelegatedSignalling)this.$outer.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{this.$outer.length() - tlen, tlen})).apply(true)).assign(ctx), pthat.splitter())));
                }
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$4 = that$4;
            }
        }).otherwise((Function0<Object>)((Object)new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ ParSeqLike $outer;
            public final GenSeq that$4;

            public final boolean apply() {
                return ((SeqLike)this.$outer.seq()).endsWith(this.that$4);
            }

            public boolean apply$mcZ$sp() {
                return ((SeqLike)this.$outer.seq()).endsWith(this.that$4);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$4 = that$4;
            }
        })));
    }

    public static Object patch(ParSeqLike $this, int from2, GenSeq patch2, int replaced, CanBuildFrom bf) {
        Object object;
        Predef$ predef$ = Predef$.MODULE$;
        int realreplaced = RichInt$.MODULE$.min$extension(replaced, $this.length() - from2);
        if (ParallelCollectionImplicits$.MODULE$.traversable2ops(patch2).isParSeq() && $this.builder2ops(bf.apply($this.repr())).isCombiner() && $this.size() - realreplaced + patch2.size() > package$.MODULE$.MIN_FOR_COPY()) {
            ParSeq that = ParallelCollectionImplicits$.MODULE$.traversable2ops(patch2).asParSeq();
            Seq pits = $this.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{from2, replaced, $this.length() - from2 - realreplaced}));
            CombinerFactory cfactory = $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParSeqLike $outer;
                private final CanBuildFrom bf$2;

                public final Combiner<U, That> apply() {
                    return this.$outer.builder2ops(this.bf$2.apply(this.$outer.repr())).asCombiner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.bf$2 = bf$2;
                }
            }));
            ParIterableLike.Copy copystart = new ParIterableLike.Copy($this, cfactory, (IterableSplitter)pits.apply(false));
            ParIterableLike.NonDivisible copymiddle = $this.wrap((Function0)((Object)new Serializable($this, that, cfactory){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParSeqLike $outer;
                private final ParSeq that$1;
                private final CombinerFactory cfactory$1;

                public final Combiner<U, That> apply() {
                    ParIterableLike.Copy<U, That> tsk = new ParIterableLike.Copy<U, That>(this.that$1, this.cfactory$1, this.that$1.splitter());
                    return (Combiner)this.$outer.tasksupport().executeAndWaitResult(tsk);
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.that$1 = that$1;
                    this.cfactory$1 = var3_3;
                }
            }));
            ParIterableLike.Copy copyend = new ParIterableLike.Copy($this, cfactory, (IterableSplitter)pits.apply(2));
            object = $this.tasksupport().executeAndWaitResult($this.task2ops($this.task2ops($this.task2ops(copystart).parallel(copymiddle, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Combiner<U, That> apply(Combiner<U, That> x$6, Combiner<U, That> x$7) {
                    return x$6.combine(x$7);
                }
            })).parallel(copyend, new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Combiner<U, That> apply(Combiner<U, That> x$8, Combiner<U, That> x$9) {
                    return x$8.combine(x$9);
                }
            })).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<U, That> x$10) {
                    return x$10.resultWithTaskSupport();
                }
            }));
        } else {
            object = ParSeqLike$class.patch_sequential($this, from2, patch2.seq(), replaced, bf);
        }
        return object;
    }

    private static Object patch_sequential(ParSeqLike $this, int fromarg, Seq patch2, int r, CanBuildFrom bf) {
        Predef$ predef$ = Predef$.MODULE$;
        int from2 = RichInt$.MODULE$.max$extension(0, fromarg);
        Builder b = bf.apply($this.repr());
        Predef$ predef$2 = Predef$.MODULE$;
        int n = RichInt$.MODULE$.min$extension(r, $this.length() - from2);
        Predef$ predef$3 = Predef$.MODULE$;
        int repl = RichInt$.MODULE$.max$extension(n, 0);
        Seq pits = $this.splitter().psplitWithSignalling(Predef$.MODULE$.wrapIntArray(new int[]{from2, repl, $this.length() - from2 - repl}));
        b.$plus$plus$eq((TraversableOnce)pits.apply(false));
        b.$plus$plus$eq(patch2);
        b.$plus$plus$eq((TraversableOnce)pits.apply(2));
        return package$.MODULE$.setTaskSupport(b.result(), $this.tasksupport());
    }

    public static Object updated(ParSeqLike $this, int index, Object elem, CanBuildFrom bf) {
        return $this.builder2ops(bf.apply($this.repr())).isCombiner() ? $this.tasksupport().executeAndWaitResult($this.task2ops(new ParSeqLike.Updated($this, index, elem, $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final CanBuildFrom bf$3;

            public final Combiner<U, That> apply() {
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

            public final That apply(Combiner<U, That> x$11) {
                return x$11.resultWithTaskSupport();
            }
        })) : package$.MODULE$.setTaskSupport(((SeqLike)$this.seq()).updated(index, elem, $this.bf2seq(bf)), $this.tasksupport());
    }

    public static Object $plus$colon(ParSeqLike $this, Object elem, CanBuildFrom bf) {
        return $this.patch(0, (GenSeq)ParArray$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{elem})), 0, bf);
    }

    public static Object $colon$plus(ParSeqLike $this, Object elem, CanBuildFrom bf) {
        return $this.patch($this.length(), (GenSeq)ParArray$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{elem})), 0, bf);
    }

    public static Object padTo(ParSeqLike $this, int len, Object elem, CanBuildFrom bf) {
        return $this.length() < len ? $this.patch($this.length(), new Repetition<Object>(elem, len - $this.length()), 0, bf) : $this.patch($this.length(), Nil$.MODULE$, 0, bf);
    }

    public static Object zip(ParSeqLike $this, GenIterable that, CanBuildFrom bf) {
        Object object;
        if ($this.builder2ops(bf.apply($this.repr())).isCombiner() && ParallelCollectionImplicits$.MODULE$.traversable2ops(that).isParSeq()) {
            ParSeq thatseq = ParallelCollectionImplicits$.MODULE$.traversable2ops(that).asParSeq();
            int n = $this.length();
            Predef$ predef$ = Predef$.MODULE$;
            object = $this.tasksupport().executeAndWaitResult($this.task2ops(new ParSeqLike.Zip($this, RichInt$.MODULE$.min$extension(n, thatseq.length()), $this.combinerFactory((Function0)((Object)new Serializable($this, bf){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ParSeqLike $outer;
                private final CanBuildFrom bf$4;

                public final Combiner<Tuple2<U, S>, That> apply() {
                    return this.$outer.builder2ops(this.bf$4.apply(this.$outer.repr())).asCombiner();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.bf$4 = bf$4;
                }
            })), $this.splitter(), thatseq.splitter())).mapResult(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final That apply(Combiner<Tuple2<U, S>, That> x$12) {
                    return x$12.resultWithTaskSupport();
                }
            }));
        } else {
            object = $this.scala$collection$parallel$ParSeqLike$$super$zip(that, bf);
        }
        return object;
    }

    public static boolean corresponds(ParSeqLike $this, GenSeq that, Function2 p) {
        return BoxesRunTime.unboxToBoolean(ParallelCollectionImplicits$.MODULE$.traversable2ops(that).ifParSeq(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final Function2 p$1;

            public final boolean apply(ParSeq<S> pthat) {
                DefaultSignalling ctx = new DefaultSignalling(this){};
                return this.$outer.length() == pthat.length() && BoxesRunTime.unboxToBoolean(this.$outer.tasksupport().executeAndWaitResult(new ParSeqLike.Corresponds<T>(this.$outer, this.p$1, (SeqSplitter)this.$outer.delegatedSignalling2ops(this.$outer.splitter()).assign(ctx), pthat.splitter())));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
            }
        }).otherwise((Function0<Object>)((Object)new Serializable($this, that, p){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ParSeqLike $outer;
            private final GenSeq that$5;
            private final Function2 p$1;

            public final boolean apply() {
                return this.apply$mcZ$sp();
            }

            public boolean apply$mcZ$sp() {
                return ((SeqLike)this.$outer.seq()).corresponds(this.that$5, this.p$1);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.that$5 = that$5;
                this.p$1 = var3_3;
            }
        })));
    }

    public static ParSeq diff(ParSeqLike $this, GenSeq that) {
        return (ParSeq)$this.sequentially(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final GenSeq that$6;

            public final Sequential apply(Sequential x$13) {
                return (Sequential)((Seq)x$13.diff(this.that$6));
            }
            {
                this.that$6 = that$6;
            }
        });
    }

    public static ParSeq intersect(ParSeqLike $this, GenSeq that) {
        return (ParSeq)$this.sequentially(new Serializable($this, that){
            public static final long serialVersionUID = 0L;
            private final GenSeq that$7;

            public final Sequential apply(Sequential x$14) {
                return (Sequential)((Seq)x$14.intersect(this.that$7));
            }
            {
                this.that$7 = that$7;
            }
        });
    }

    public static ParSeq distinct(ParSeqLike $this) {
        return (ParSeq)$this.sequentially(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Sequential apply(Sequential x$15) {
                return (Sequential)((Seq)x$15.distinct());
            }
        });
    }

    public static String toString(ParSeqLike $this) {
        return $this.seq().mkString(new StringBuilder().append((Object)$this.stringPrefix()).append((Object)"(").toString(), ", ", ")");
    }

    public static ParSeq toSeq(ParSeqLike $this) {
        return (ParSeq)$this;
    }

    public static SeqView view(ParSeqLike $this) {
        return ((SeqLike)$this.seq()).view();
    }

    public static SeqSplitter down(ParSeqLike $this, IterableSplitter p) {
        return (SeqSplitter)p;
    }

    public static void $init$(ParSeqLike $this) {
    }
}

