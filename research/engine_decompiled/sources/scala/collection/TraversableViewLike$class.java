/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$Appended$class;
import scala.collection.TraversableViewLike$DroppedWhile$class;
import scala.collection.TraversableViewLike$Filtered$class;
import scala.collection.TraversableViewLike$FlatMapped$class;
import scala.collection.TraversableViewLike$Forced$class;
import scala.collection.TraversableViewLike$Mapped$class;
import scala.collection.TraversableViewLike$Sliced$class;
import scala.collection.TraversableViewLike$TakenWhile$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SliceInterval;
import scala.collection.generic.SliceInterval$;
import scala.collection.immutable.Map;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;

public abstract class TraversableViewLike$class {
    public static String viewIdentifier(TraversableViewLike $this) {
        return "";
    }

    public static String viewIdString(TraversableViewLike $this) {
        return "";
    }

    public static String viewToString(TraversableViewLike $this) {
        return new StringBuilder().append((Object)$this.stringPrefix()).append((Object)$this.viewIdString()).append((Object)"(...)").toString();
    }

    public static String stringPrefix(TraversableViewLike $this) {
        return "TraversableView";
    }

    public static Builder newBuilder(TraversableViewLike $this) {
        throw new UnsupportedOperationException(Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd($this), ".newBuilder"));
    }

    public static Object force(TraversableViewLike $this, CanBuildFrom bf) {
        Builder b = bf.apply($this.underlying());
        b.$plus$plus$eq($this);
        return b.result();
    }

    public static Object $plus$plus(TraversableViewLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
        return $this.newAppended(xs.seq().toTraversable());
    }

    public static Object map(TraversableViewLike $this, Function1 f, CanBuildFrom bf) {
        return $this.newMapped(f);
    }

    public static Object collect(TraversableViewLike $this, PartialFunction pf, CanBuildFrom bf) {
        return $this.filter(new Serializable($this, pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$1;

            public final boolean apply(A x) {
                return this.pf$1.isDefinedAt(x);
            }
            {
                this.pf$1 = pf$1;
            }
        }).map(pf, bf);
    }

    public static Object flatMap(TraversableViewLike $this, Function1 f, CanBuildFrom bf) {
        return $this.newFlatMapped(f);
    }

    public static TraversableViewLike.Transformed flatten(TraversableViewLike $this, Function1 asTraversable) {
        return $this.newFlatMapped(asTraversable);
    }

    public static TraversableView scala$collection$TraversableViewLike$$asThis(TraversableViewLike $this, TraversableViewLike.Transformed xs) {
        return xs;
    }

    public static TraversableViewLike.Transformed newForced(TraversableViewLike $this, Function0 xs) {
        return new TraversableViewLike.Forced<B>($this, xs){
            private final GenSeq<B> forced;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Forced$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Forced$class.viewIdentifier(this);
            }

            public GenSeq<B> forced() {
                return this.forced;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Forced$$$outer() {
                return this.$outer;
            }
            {
                GenSeq forced;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.forced = forced = (GenSeq)xs$2.apply();
                super($outer);
                TraversableViewLike$Forced$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newAppended(TraversableViewLike $this, GenTraversable that) {
        return new TraversableViewLike.Appended<B>($this, that){
            private final GenTraversable<B> rest;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Appended$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Appended$class.viewIdentifier(this);
            }

            public GenTraversable<B> rest() {
                return this.rest;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Appended$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.rest = that$1;
                super($outer);
                TraversableViewLike$Appended$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newMapped(TraversableViewLike $this, Function1 f) {
        return new TraversableViewLike.Mapped<B>($this, f){
            private final Function1<A, B> mapping;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$Mapped$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Mapped$class.viewIdentifier(this);
            }

            public Function1<A, B> mapping() {
                return this.mapping;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Mapped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.mapping = f$1;
                super($outer);
                TraversableViewLike$Mapped$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newFlatMapped(TraversableViewLike $this, Function1 f) {
        return new TraversableViewLike.FlatMapped<B>($this, f){
            private final Function1<A, GenTraversableOnce<B>> mapping;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<B, U> f) {
                TraversableViewLike$FlatMapped$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$FlatMapped$class.viewIdentifier(this);
            }

            public Function1<A, GenTraversableOnce<B>> mapping() {
                return this.mapping;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$FlatMapped$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.mapping = f$2;
                super($outer);
                TraversableViewLike$FlatMapped$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newFiltered(TraversableViewLike $this, Function1 p) {
        return new TraversableViewLike.Filtered($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<A, U> f) {
                TraversableViewLike$Filtered$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Filtered$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Filtered$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$1;
                super($outer);
                TraversableViewLike$Filtered$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newSliced(TraversableViewLike $this, SliceInterval _endpoints) {
        return new TraversableViewLike.Sliced($this, _endpoints){
            private final SliceInterval endpoints;
            private final /* synthetic */ TraversableViewLike $outer;

            public int from() {
                return TraversableViewLike$Sliced$class.from(this);
            }

            public int until() {
                return TraversableViewLike$Sliced$class.until(this);
            }

            public <U> void foreach(Function1<A, U> f) {
                TraversableViewLike$Sliced$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$Sliced$class.viewIdentifier(this);
            }

            public SliceInterval endpoints() {
                return this.endpoints;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Sliced$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.endpoints = _endpoints$1;
                super($outer);
                TraversableViewLike$Sliced$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newDroppedWhile(TraversableViewLike $this, Function1 p) {
        return new TraversableViewLike.DroppedWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<A, U> f) {
                TraversableViewLike$DroppedWhile$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$DroppedWhile$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$DroppedWhile$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$2;
                super($outer);
                TraversableViewLike$DroppedWhile$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newTakenWhile(TraversableViewLike $this, Function1 p) {
        return new TraversableViewLike.TakenWhile($this, p){
            private final Function1<A, Object> pred;
            private final /* synthetic */ TraversableViewLike $outer;

            public <U> void foreach(Function1<A, U> f) {
                TraversableViewLike$TakenWhile$class.foreach(this, f);
            }

            public final String viewIdentifier() {
                return TraversableViewLike$TakenWhile$class.viewIdentifier(this);
            }

            public Function1<A, Object> pred() {
                return this.pred;
            }

            public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$TakenWhile$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.pred = p$3;
                super($outer);
                TraversableViewLike$TakenWhile$class.$init$(this);
            }
        };
    }

    public static TraversableViewLike.Transformed newTaken(TraversableViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
    }

    public static TraversableViewLike.Transformed newDropped(TraversableViewLike $this, int n) {
        return $this.newSliced(SliceInterval$.MODULE$.apply(n, Integer.MAX_VALUE));
    }

    public static TraversableView filter(TraversableViewLike $this, Function1 p) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p));
    }

    public static TraversableView withFilter(TraversableViewLike $this, Function1 p) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p));
    }

    public static Tuple2 partition(TraversableViewLike $this, Function1 p) {
        return new Tuple2<TraversableView, TraversableView>(TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p)), TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$4;

            public final boolean apply(A x$1) {
                return !BoxesRunTime.unboxToBoolean(this.p$4.apply(x$1));
            }
            {
                this.p$4 = p$4;
            }
        })));
    }

    public static TraversableView init(TraversableViewLike $this) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(0, $this.size() - 1)));
    }

    public static TraversableView drop(TraversableViewLike $this, int n) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newDropped(n));
    }

    public static TraversableView take(TraversableViewLike $this, int n) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newTaken(n));
    }

    public static TraversableView slice(TraversableViewLike $this, int from2, int until2) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(from2, until2)));
    }

    public static TraversableView dropWhile(TraversableViewLike $this, Function1 p) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newDroppedWhile(p));
    }

    public static TraversableView takeWhile(TraversableViewLike $this, Function1 p) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newTakenWhile(p));
    }

    public static Tuple2 span(TraversableViewLike $this, Function1 p) {
        return new Tuple2<TraversableView, TraversableView>(TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newTakenWhile(p)), TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newDroppedWhile(p)));
    }

    public static Tuple2 splitAt(TraversableViewLike $this, int n) {
        return new Tuple2<TraversableView, TraversableView>(TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newTaken(n)), TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newDropped(n)));
    }

    public static Object scanLeft(TraversableViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return $this.newForced(new Serializable($this, z, op){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike $outer;
            private final Object z$1;
            private final Function2 op$1;

            public final Seq<B> apply() {
                return this.$outer.thisSeq().scanLeft(this.z$1, this.op$1, Seq$.MODULE$.canBuildFrom());
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.z$1 = z$1;
                this.op$1 = var3_3;
            }
        });
    }

    public static Object scanRight(TraversableViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
        return $this.newForced(new Serializable($this, z, op){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike $outer;
            private final Object z$2;
            private final Function2 op$2;

            public final Seq<B> apply() {
                return this.$outer.thisSeq().scanRight(this.z$2, this.op$2, Seq$.MODULE$.canBuildFrom());
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.z$2 = z$2;
                this.op$2 = var3_3;
            }
        });
    }

    public static Map groupBy(TraversableViewLike $this, Function1 f) {
        return $this.thisSeq().groupBy(f).mapValues(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike $outer;

            public final This apply(Seq<A> xs) {
                return (This)TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis(this.$outer, this.$outer.newForced(new Serializable(this, xs){
                    public static final long serialVersionUID = 0L;
                    private final Seq xs$1;

                    public final Seq<A> apply() {
                        return this.xs$1;
                    }
                    {
                        this.xs$1 = xs$1;
                    }
                }));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Tuple2 unzip(TraversableViewLike $this, Function1 asPair) {
        return new Tuple2($this.newMapped(new Serializable($this, asPair){
            public static final long serialVersionUID = 0L;
            private final Function1 asPair$1;

            public final A1 apply(A x) {
                return (A1)((Tuple2)this.asPair$1.apply(x))._1();
            }
            {
                this.asPair$1 = asPair$1;
            }
        }), $this.newMapped(new Serializable($this, asPair){
            public static final long serialVersionUID = 0L;
            private final Function1 asPair$1;

            public final A2 apply(A x) {
                return (A2)((Tuple2)this.asPair$1.apply(x))._2();
            }
            {
                this.asPair$1 = asPair$1;
            }
        }));
    }

    public static Tuple3 unzip3(TraversableViewLike $this, Function1 asTriple) {
        return new Tuple3($this.newMapped(new Serializable($this, asTriple){
            public static final long serialVersionUID = 0L;
            private final Function1 asTriple$1;

            public final A1 apply(A x) {
                return (A1)((Tuple3)this.asTriple$1.apply(x))._1();
            }
            {
                this.asTriple$1 = asTriple$1;
            }
        }), $this.newMapped(new Serializable($this, asTriple){
            public static final long serialVersionUID = 0L;
            private final Function1 asTriple$1;

            public final A2 apply(A x) {
                return (A2)((Tuple3)this.asTriple$1.apply(x))._2();
            }
            {
                this.asTriple$1 = asTriple$1;
            }
        }), $this.newMapped(new Serializable($this, asTriple){
            public static final long serialVersionUID = 0L;
            private final Function1 asTriple$1;

            public final A3 apply(A x) {
                return (A3)((Tuple3)this.asTriple$1.apply(x))._3();
            }
            {
                this.asTriple$1 = asTriple$1;
            }
        }));
    }

    public static TraversableView filterNot(TraversableViewLike $this, Function1 p) {
        return TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$5;

            public final boolean apply(A a) {
                return !BoxesRunTime.unboxToBoolean(this.p$5.apply(a));
            }
            {
                this.p$5 = p$5;
            }
        }));
    }

    public static Iterator inits(TraversableViewLike $this) {
        return $this.thisSeq().inits().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike $outer;

            public final This apply(Seq<A> as) {
                return (This)this.$outer.newForced(new Serializable(this, as){
                    public static final long serialVersionUID = 0L;
                    private final Seq as$1;

                    public final Seq<A> apply() {
                        return this.as$1;
                    }
                    {
                        this.as$1 = as$1;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Iterator tails(TraversableViewLike $this) {
        return $this.thisSeq().tails().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableViewLike $outer;

            public final This apply(Seq<A> as) {
                return (This)this.$outer.newForced(new Serializable(this, as){
                    public static final long serialVersionUID = 0L;
                    private final Seq as$2;

                    public final Seq<A> apply() {
                        return this.as$2;
                    }
                    {
                        this.as$2 = as$2;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static TraversableView tail(TraversableViewLike $this) {
        return $this.isEmpty() ? $this.scala$collection$TraversableViewLike$$super$tail() : TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis($this, $this.newDropped(1));
    }

    public static String toString(TraversableViewLike $this) {
        return $this.viewToString();
    }

    public static void $init$(TraversableViewLike $this) {
    }
}

