/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
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
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.GenMap;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.IndexedSeqLike;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$;
import scala.collection.TraversableLike$$anonfun$scala$collection$TraversableLike$;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map$;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.collection.package$;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParIterable$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public abstract class TraversableLike$class {
    public static Object repr(TraversableLike $this) {
        return $this;
    }

    public static final boolean isTraversableAgain(TraversableLike $this) {
        return true;
    }

    public static Traversable thisCollection(TraversableLike $this) {
        return (Traversable)$this;
    }

    public static Traversable toCollection(TraversableLike $this, Object repr) {
        return (Traversable)repr;
    }

    public static Combiner parCombiner(TraversableLike $this) {
        return ParIterable$.MODULE$.newCombiner();
    }

    public static boolean isEmpty(TraversableLike $this) {
        BooleanRef result2 = BooleanRef.create(true);
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, result2){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final BooleanRef result$1;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.isEmpty.1 $outer;

                    public final Nothing$ apply(A x) {
                        this.$outer.result$1.elem = false;
                        return Traversable$.MODULE$.breaks().break();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$1 = result$1;
            }
        }));
        return result2.elem;
    }

    public static boolean hasDefiniteSize(TraversableLike $this) {
        return true;
    }

    public static Object $plus$plus(TraversableLike $this, GenTraversableOnce that, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        if (that instanceof IndexedSeqLike) {
            b.sizeHint($this, that.seq().size());
        }
        b.$plus$plus$eq($this.thisCollection());
        b.$plus$plus$eq(that.seq());
        return b.result();
    }

    public static Object $plus$plus$colon(TraversableLike $this, TraversableOnce that, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        if (that instanceof IndexedSeqLike) {
            b.sizeHint($this, that.size());
        }
        b.$plus$plus$eq(that);
        b.$plus$plus$eq($this.thisCollection());
        return b.result();
    }

    public static Object $plus$plus$colon(TraversableLike $this, Traversable that, CanBuildFrom bf) {
        return that.$plus$plus($this.seq(), package$.MODULE$.breakOut(bf));
    }

    public static Object map(TraversableLike $this, Function1 f, CanBuildFrom bf) {
        Builder b = TraversableLike$class.builder$1($this, bf);
        $this.foreach(new Serializable($this, b, f){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;
            private final Function1 f$4;

            public final Builder<B, That> apply(A x) {
                return this.b$1.$plus$eq(this.f$4.apply(x));
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.f$4 = var3_3;
            }
        });
        return b.result();
    }

    public static Object flatMap(TraversableLike $this, Function1 f, CanBuildFrom bf) {
        Builder b = TraversableLike$class.builder$2($this, bf);
        $this.foreach(new Serializable($this, b, f){
            public static final long serialVersionUID = 0L;
            private final Builder b$2;
            private final Function1 f$5;

            public final Builder<B, That> apply(A x) {
                return (Builder)this.b$2.$plus$plus$eq(((GenTraversableOnce)this.f$5.apply(x)).seq());
            }
            {
                void var3_3;
                this.b$2 = b$2;
                this.f$5 = var3_3;
            }
        });
        return b.result();
    }

    private static Object filterImpl(TraversableLike $this, Function1 p, boolean isFlipped) {
        Builder b = $this.newBuilder();
        $this.foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final Function1 p$1;
            private final boolean isFlipped$1;
            private final Builder b$3;

            public final Object apply(A x) {
                return BoxesRunTime.unboxToBoolean(this.p$1.apply(x)) != this.isFlipped$1 ? this.b$3.$plus$eq(x) : BoxedUnit.UNIT;
            }
            {
                this.p$1 = p$1;
                this.isFlipped$1 = isFlipped$1;
                this.b$3 = b$3;
            }
        });
        return b.result();
    }

    public static Object filter(TraversableLike $this, Function1 p) {
        return TraversableLike$class.filterImpl($this, p, false);
    }

    public static Object filterNot(TraversableLike $this, Function1 p) {
        return TraversableLike$class.filterImpl($this, p, true);
    }

    public static Object collect(TraversableLike $this, PartialFunction pf, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        $this.foreach(pf.runWith(new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final Builder b$4;

            public final Builder<B, That> apply(B x$1) {
                return this.b$4.$plus$eq(x$1);
            }
            {
                this.b$4 = b$4;
            }
        }));
        return b.result();
    }

    public static Tuple2 partition(TraversableLike $this, Function1 p) {
        Builder l = $this.newBuilder();
        Builder r = $this.newBuilder();
        $this.foreach(new Serializable($this, l, r, p){
            public static final long serialVersionUID = 0L;
            private final Builder l$1;
            private final Builder r$1;
            private final Function1 p$2;

            public final Builder<A, Repr> apply(A x) {
                return (BoxesRunTime.unboxToBoolean(this.p$2.apply(x)) ? this.l$1 : this.r$1).$plus$eq(x);
            }
            {
                void var4_4;
                void var3_3;
                this.l$1 = l$1;
                this.r$1 = var3_3;
                this.p$2 = var4_4;
            }
        });
        return new Tuple2(l.result(), r.result());
    }

    public static Map groupBy(TraversableLike $this, Function1 f) {
        GenMap m = Map$.MODULE$.empty();
        $this.foreach(new Serializable($this, (scala.collection.mutable.Map)m, f){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            private final scala.collection.mutable.Map m$1;
            private final Function1 f$6;

            public final Builder<A, Repr> apply(A elem) {
                R key = this.f$6.apply(elem);
                Builder bldr = (Builder)this.m$1.getOrElseUpdate(key, new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.groupBy.1 $outer;

                    public final Builder<A, Repr> apply() {
                        return this.$outer.$outer.newBuilder();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
                return bldr.$plus$eq(elem);
            }

            public /* synthetic */ TraversableLike scala$collection$TraversableLike$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.m$1 = m$1;
                this.f$6 = var3_3;
            }
        });
        Builder b = scala.collection.immutable.Map$.MODULE$.newBuilder();
        m.withFilter(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<K, Builder<A, Repr>> check$ifrefutable$1) {
                boolean bl = check$ifrefutable$1 != null;
                return bl;
            }
        }).foreach(new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final Builder b$5;

            public final Builder<Tuple2<K, Repr>, Map<K, Repr>> apply(Tuple2<K, Builder<A, Repr>> x$2) {
                if (x$2 != null) {
                    return this.b$5.$plus$eq(new Tuple2<K, Repr>(x$2._1(), x$2._2().result()));
                }
                throw new MatchError(x$2);
            }
            {
                this.b$5 = b$5;
            }
        });
        return (Map)b.result();
    }

    public static boolean forall(TraversableLike $this, Function1 p) {
        BooleanRef result2 = BooleanRef.create(true);
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, result2, p){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final BooleanRef result$2;
            public final Function1 p$3;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.forall.1 $outer;

                    public final void apply(A x) {
                        if (BoxesRunTime.unboxToBoolean(this.$outer.p$3.apply(x))) {
                            return;
                        }
                        this.$outer.result$2.elem = false;
                        throw Traversable$.MODULE$.breaks().break();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$2 = result$2;
                this.p$3 = var3_3;
            }
        }));
        return result2.elem;
    }

    public static boolean exists(TraversableLike $this, Function1 p) {
        BooleanRef result2 = BooleanRef.create(false);
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, result2, p){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final BooleanRef result$3;
            public final Function1 p$4;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.exists.1 $outer;

                    public final void apply(A x) {
                        if (BoxesRunTime.unboxToBoolean(this.$outer.p$4.apply(x))) {
                            this.$outer.result$3.elem = true;
                            throw Traversable$.MODULE$.breaks().break();
                        }
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$3 = result$3;
                this.p$4 = var3_3;
            }
        }));
        return result2.elem;
    }

    public static Option find(TraversableLike $this, Function1 p) {
        ObjectRef<None$> result2 = ObjectRef.create(None$.MODULE$);
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, result2, p){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final ObjectRef result$4;
            public final Function1 p$5;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.find.1 $outer;

                    public final void apply(A x) {
                        if (BoxesRunTime.unboxToBoolean(this.$outer.p$5.apply(x))) {
                            this.$outer.result$4.elem = new Some<A>(x);
                            throw Traversable$.MODULE$.breaks().break();
                        }
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$4 = result$4;
                this.p$5 = var3_3;
            }
        }));
        return (Option)result2.elem;
    }

    public static Object scan(TraversableLike $this, Object z, Function2 op, CanBuildFrom cbf) {
        return $this.scanLeft(z, op, cbf);
    }

    public static Object scanLeft(TraversableLike $this, Object z, Function2 op, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        b.sizeHint($this, 1);
        ObjectRef<Object> acc = ObjectRef.create(z);
        b.$plus$eq(acc.elem);
        $this.foreach(new Serializable($this, b, acc, op){
            public static final long serialVersionUID = 0L;
            private final Builder b$6;
            private final ObjectRef acc$1;
            private final Function2 op$1;

            public final Builder<B, That> apply(A x) {
                this.acc$1.elem = this.op$1.apply(this.acc$1.elem, x);
                return this.b$6.$plus$eq(this.acc$1.elem);
            }
            {
                void var4_4;
                void var3_3;
                this.b$6 = b$6;
                this.acc$1 = var3_3;
                this.op$1 = var4_4;
            }
        });
        return b.result();
    }

    public static Object scanRight(TraversableLike $this, Object z, Function2 op, CanBuildFrom bf) {
        WrappedArray wrappedArray = Predef$.MODULE$.genericWrapArray(new Object[]{z});
        List$ list$ = List$.MODULE$;
        ObjectRef scanned = ObjectRef.create(wrappedArray.toList());
        ObjectRef<Object> acc = ObjectRef.create(z);
        Serializable serializable = new Serializable($this, scanned, acc, op){
            public static final long serialVersionUID = 0L;
            public final ObjectRef scanned$1;
            public final ObjectRef acc$2;
            public final Function2 op$2;

            public final void apply(A x) {
                this.acc$2.elem = this.op$2.apply(x, this.acc$2.elem);
                this.scanned$1.elem = ((List)this.scanned$1.elem).$colon$colon(this.acc$2.elem);
            }
            {
                void var4_4;
                void var3_3;
                this.scanned$1 = scanned$1;
                this.acc$2 = var3_3;
                this.op$2 = var4_4;
            }
        };
        List these1 = $this.reversed();
        while (true) {
            if (these1.isEmpty()) {
                Builder b = bf.apply($this.repr());
                Serializable serializable2 = new Serializable($this, b){
                    public static final long serialVersionUID = 0L;
                    public final Builder b$7;

                    public final Builder<B, That> apply(B elem) {
                        return this.b$7.$plus$eq(elem);
                    }
                    {
                        this.b$7 = b$7;
                    }
                };
                List these2 = (List)scanned.elem;
                while (true) {
                    if (these2.isEmpty()) {
                        return b.result();
                    }
                    Object a = these2.head();
                    serializable2.b$7.$plus$eq(a);
                    these2 = (List)these2.tail();
                }
            }
            Object a = these1.head();
            Object t = serializable.acc$2.elem = serializable.op$2.apply(a, serializable.acc$2.elem);
            List list2 = (List)serializable.scanned$1.elem;
            serializable.scanned$1.elem = new $colon$colon(t, list2);
            these1 = (List)these1.tail();
        }
    }

    public static Object head(TraversableLike $this) {
        ObjectRef<TraversableLike$.anonfun.2> result2 = ObjectRef.create(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Nothing$ apply() {
                throw new NoSuchElementException();
            }
        });
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, result2){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final ObjectRef result$5;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.head.1 $outer;

                    public final Nothing$ apply(A x) {
                        this.$outer.result$5.elem = new Serializable(this, x){
                            public static final long serialVersionUID = 0L;
                            private final Object x$5;

                            public final A apply() {
                                return (A)this.x$5;
                            }
                            {
                                this.x$5 = x$5;
                            }
                        };
                        return Traversable$.MODULE$.breaks().break();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$5 = result$5;
            }
        }));
        return ((Function0)result2.elem).apply();
    }

    public static Option headOption(TraversableLike $this) {
        return $this.isEmpty() ? None$.MODULE$ : new Some($this.head());
    }

    public static Object tail(TraversableLike $this) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.tail");
        }
        return $this.drop(1);
    }

    public static Object last(TraversableLike $this) {
        ObjectRef lst = ObjectRef.create($this.head());
        $this.foreach(new Serializable($this, lst){
            public static final long serialVersionUID = 0L;
            private final ObjectRef lst$1;

            public final void apply(A x) {
                this.lst$1.elem = x;
            }
            {
                this.lst$1 = lst$1;
            }
        });
        return lst.elem;
    }

    public static Option lastOption(TraversableLike $this) {
        return $this.isEmpty() ? None$.MODULE$ : new Some($this.last());
    }

    public static Object init(TraversableLike $this) {
        if ($this.isEmpty()) {
            throw new UnsupportedOperationException("empty.init");
        }
        ObjectRef lst = ObjectRef.create($this.head());
        BooleanRef follow = BooleanRef.create(false);
        Builder b = $this.newBuilder();
        b.sizeHint($this, -1);
        $this.foreach(new Serializable($this, lst, follow, b){
            public static final long serialVersionUID = 0L;
            private final ObjectRef lst$2;
            private final BooleanRef follow$1;
            private final Builder b$8;

            public final void apply(A x) {
                Object object;
                if (this.follow$1.elem) {
                    object = this.b$8.$plus$eq(this.lst$2.elem);
                } else {
                    this.follow$1.elem = true;
                    object = BoxedUnit.UNIT;
                }
                this.lst$2.elem = x;
            }
            {
                void var4_4;
                void var3_3;
                this.lst$2 = lst$2;
                this.follow$1 = var3_3;
                this.b$8 = var4_4;
            }
        });
        return b.result();
    }

    public static Object take(TraversableLike $this, int n) {
        return $this.slice(0, n);
    }

    public static Object drop(TraversableLike $this, int n) {
        Object object;
        if (n <= 0) {
            Builder b = $this.newBuilder();
            b.sizeHint($this);
            object = ((Builder)b.$plus$plus$eq($this.thisCollection())).result();
        } else {
            object = $this.sliceWithKnownDelta(n, Integer.MAX_VALUE, -n);
        }
        return object;
    }

    public static Object slice(TraversableLike $this, int from2, int until2) {
        return $this.sliceWithKnownBound(scala.math.package$.MODULE$.max(from2, 0), until2);
    }

    public static Object scala$collection$TraversableLike$$sliceInternal(TraversableLike $this, int from2, int until2, Builder b) {
        IntRef i = IntRef.create(0);
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final int from$1;
            public final int until$1;
            public final Builder b$9;
            public final IntRef i$1;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$$anonfun$scala$collection$TraversableLike$.sliceInternal.1 $outer;

                    public final void apply(A x) {
                        Object object = this.$outer.i$1.elem >= this.$outer.from$1 ? this.$outer.b$9.$plus$eq(x) : BoxedUnit.UNIT;
                        ++this.$outer.i$1.elem;
                        if (this.$outer.i$1.elem >= this.$outer.until$1) {
                            throw Traversable$.MODULE$.breaks().break();
                        }
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.from$1 = from$1;
                this.until$1 = until$1;
                this.b$9 = b$9;
                this.i$1 = i$1;
            }
        }));
        return b.result();
    }

    public static Object sliceWithKnownDelta(TraversableLike $this, int from2, int until2, int delta) {
        Object object;
        Builder b = $this.newBuilder();
        if (until2 <= from2) {
            object = b.result();
        } else {
            b.sizeHint($this, delta);
            object = TraversableLike$class.scala$collection$TraversableLike$$sliceInternal($this, from2, until2, b);
        }
        return object;
    }

    public static Object sliceWithKnownBound(TraversableLike $this, int from2, int until2) {
        Object object;
        Builder b = $this.newBuilder();
        if (until2 <= from2) {
            object = b.result();
        } else {
            b.sizeHintBounded(until2 - from2, $this);
            object = TraversableLike$class.scala$collection$TraversableLike$$sliceInternal($this, from2, until2, b);
        }
        return object;
    }

    public static Object takeWhile(TraversableLike $this, Function1 p) {
        Builder b = $this.newBuilder();
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, b, p){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final Builder b$10;
            public final Function1 p$6;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.takeWhile.1 $outer;

                    public final Builder<A, Repr> apply(A x) {
                        if (BoxesRunTime.unboxToBoolean(this.$outer.p$6.apply(x))) {
                            return this.$outer.b$10.$plus$eq(x);
                        }
                        throw Traversable$.MODULE$.breaks().break();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.b$10 = b$10;
                this.p$6 = var3_3;
            }
        }));
        return b.result();
    }

    public static Object dropWhile(TraversableLike $this, Function1 p) {
        Builder b = $this.newBuilder();
        BooleanRef go = BooleanRef.create(false);
        $this.foreach(new Serializable($this, b, go, p){
            public static final long serialVersionUID = 0L;
            private final Builder b$11;
            private final BooleanRef go$1;
            private final Function1 p$7;

            public final Object apply(A x) {
                if (!this.go$1.elem && !BoxesRunTime.unboxToBoolean(this.p$7.apply(x))) {
                    this.go$1.elem = true;
                }
                return this.go$1.elem ? this.b$11.$plus$eq(x) : BoxedUnit.UNIT;
            }
            {
                void var4_4;
                void var3_3;
                this.b$11 = b$11;
                this.go$1 = var3_3;
                this.p$7 = var4_4;
            }
        });
        return b.result();
    }

    public static Tuple2 span(TraversableLike $this, Function1 p) {
        Builder l = $this.newBuilder();
        Builder r = $this.newBuilder();
        BooleanRef toLeft = BooleanRef.create(true);
        $this.foreach(new Serializable($this, l, r, toLeft, p){
            public static final long serialVersionUID = 0L;
            private final Builder l$2;
            private final Builder r$2;
            private final BooleanRef toLeft$1;
            private final Function1 p$8;

            public final Builder<A, Repr> apply(A x) {
                this.toLeft$1.elem = this.toLeft$1.elem && BoxesRunTime.unboxToBoolean(this.p$8.apply(x));
                return (this.toLeft$1.elem ? this.l$2 : this.r$2).$plus$eq(x);
            }
            {
                void var5_5;
                void var4_4;
                void var3_3;
                this.l$2 = l$2;
                this.r$2 = var3_3;
                this.toLeft$1 = var4_4;
                this.p$8 = var5_5;
            }
        });
        return new Tuple2(l.result(), r.result());
    }

    public static Tuple2 splitAt(TraversableLike $this, int n) {
        Builder l = $this.newBuilder();
        Builder r = $this.newBuilder();
        l.sizeHintBounded(n, $this);
        if (n >= 0) {
            r.sizeHint($this, -n);
        }
        IntRef i = IntRef.create(0);
        $this.foreach(new Serializable($this, l, r, i, n){
            public static final long serialVersionUID = 0L;
            private final Builder l$3;
            private final Builder r$3;
            private final IntRef i$2;
            private final int n$1;

            public final void apply(A x) {
                (this.i$2.elem < this.n$1 ? this.l$3 : this.r$3).$plus$eq(x);
                ++this.i$2.elem;
            }
            {
                void var4_4;
                void var3_3;
                this.l$3 = l$3;
                this.r$3 = var3_3;
                this.i$2 = var4_4;
                this.n$1 = n$1;
            }
        });
        return new Tuple2(l.result(), r.result());
    }

    public static Iterator tails(TraversableLike $this) {
        return TraversableLike$class.iterateUntilEmpty($this, (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Traversable<A> apply(Traversable<A> x$3) {
                return (Traversable)x$3.tail();
            }
        }));
    }

    public static Iterator inits(TraversableLike $this) {
        return TraversableLike$class.iterateUntilEmpty($this, (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Traversable<A> apply(Traversable<A> x$4) {
                return (Traversable)x$4.init();
            }
        }));
    }

    public static void copyToArray(TraversableLike $this, Object xs, int start, int len) {
        IntRef i = IntRef.create(start);
        int n = start + len;
        Predef$ predef$ = Predef$.MODULE$;
        int end = RichInt$.MODULE$.min$extension(n, ScalaRunTime$.MODULE$.array_length(xs));
        Traversable$.MODULE$.breaks().breakable((Function0<BoxedUnit>)((Object)new Serializable($this, i, end, xs){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraversableLike $outer;
            public final IntRef i$3;
            public final int end$1;
            public final Object xs$1;

            public final void apply() {
                this.$outer.foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TraversableLike$.anonfun.copyToArray.1 $outer;

                    public final void apply(A x) {
                        if (this.$outer.i$3.elem >= this.$outer.end$1) {
                            throw Traversable$.MODULE$.breaks().break();
                        }
                        ScalaRunTime$.MODULE$.array_update(this.$outer.xs$1, this.$outer.i$3.elem, x);
                        ++this.$outer.i$3.elem;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public void apply$mcV$sp() {
                this.$outer.foreach(new /* invalid duplicate definition of identical inner class */);
            }
            {
                void var4_4;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$3 = i$3;
                this.end$1 = end$1;
                this.xs$1 = var4_4;
            }
        }));
    }

    public static Traversable toTraversable(TraversableLike $this) {
        return $this.thisCollection();
    }

    public static Iterator toIterator(TraversableLike $this) {
        return $this.toStream().iterator();
    }

    public static Stream toStream(TraversableLike $this) {
        return $this.toBuffer().toStream();
    }

    public static Object to(TraversableLike $this, CanBuildFrom cbf) {
        Builder b = cbf.apply();
        b.sizeHint($this);
        b.$plus$plus$eq($this.thisCollection());
        return b.result();
    }

    public static String toString(TraversableLike $this) {
        return $this.mkString(new StringBuilder().append((Object)$this.stringPrefix()).append((Object)"(").toString(), ", ", ")");
    }

    /*
     * WARNING - void declaration
     */
    public static String stringPrefix(TraversableLike $this) {
        void var1_1;
        int idx2;
        String string2 = $this.repr().getClass().getName();
        int idx1 = string2.lastIndexOf(46);
        if (idx1 != -1) {
            string2 = string2.substring(idx1 + 1);
        }
        if ((idx2 = string2.indexOf(36)) != -1) {
            string2 = string2.substring(0, idx2);
        }
        return var1_1;
    }

    public static TraversableView view(TraversableLike $this) {
        return new TraversableView<A, Repr>($this){
            private Repr underlying;
            private final /* synthetic */ TraversableLike $outer;
            private volatile boolean bitmap$0;

            private Object underlying$lzycompute() {
                TraversableLike$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.underlying = this.$outer.repr();
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.underlying;
                }
            }

            public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
                return (TraversableView)TraversableLike$class.tail(this);
            }

            public String viewIdentifier() {
                return TraversableViewLike$class.viewIdentifier(this);
            }

            public String viewIdString() {
                return TraversableViewLike$class.viewIdString(this);
            }

            public String viewToString() {
                return TraversableViewLike$class.viewToString(this);
            }

            public String stringPrefix() {
                return TraversableViewLike$class.stringPrefix(this);
            }

            public Builder<A, TraversableView<A, Repr>> newBuilder() {
                return TraversableViewLike$class.newBuilder(this);
            }

            public <B, That> That force(CanBuildFrom<Repr, B, That> bf) {
                return (That)TraversableViewLike$class.force(this, bf);
            }

            public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
            }

            public <B, That> That map(Function1<A, B> f, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.map(this, f, bf);
            }

            public <B, That> That collect(PartialFunction<A, B> pf, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.collect(this, pf, bf);
            }

            public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> f, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.flatMap(this, f, bf);
            }

            public <B> TraversableViewLike.Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> asTraversable) {
                return TraversableViewLike$class.flatten(this, asTraversable);
            }

            public <B> TraversableViewLike.Transformed<B> newForced(Function0<GenSeq<B>> xs) {
                return TraversableViewLike$class.newForced(this, xs);
            }

            public <B> TraversableViewLike.Transformed<B> newAppended(GenTraversable<B> that) {
                return TraversableViewLike$class.newAppended(this, that);
            }

            public <B> TraversableViewLike.Transformed<B> newMapped(Function1<A, B> f) {
                return TraversableViewLike$class.newMapped(this, f);
            }

            public <B> TraversableViewLike.Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> f) {
                return TraversableViewLike$class.newFlatMapped(this, f);
            }

            public TraversableViewLike.Transformed<A> newFiltered(Function1<A, Object> p) {
                return TraversableViewLike$class.newFiltered(this, p);
            }

            public TraversableViewLike.Transformed<A> newSliced(SliceInterval _endpoints) {
                return TraversableViewLike$class.newSliced(this, _endpoints);
            }

            public TraversableViewLike.Transformed<A> newDroppedWhile(Function1<A, Object> p) {
                return TraversableViewLike$class.newDroppedWhile(this, p);
            }

            public TraversableViewLike.Transformed<A> newTakenWhile(Function1<A, Object> p) {
                return TraversableViewLike$class.newTakenWhile(this, p);
            }

            public TraversableViewLike.Transformed<A> newTaken(int n) {
                return TraversableViewLike$class.newTaken(this, n);
            }

            public TraversableViewLike.Transformed<A> newDropped(int n) {
                return TraversableViewLike$class.newDropped(this, n);
            }

            public TraversableView<A, Repr> filter(Function1<A, Object> p) {
                return TraversableViewLike$class.filter(this, p);
            }

            public TraversableView<A, Repr> withFilter(Function1<A, Object> p) {
                return TraversableViewLike$class.withFilter(this, p);
            }

            public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> partition(Function1<A, Object> p) {
                return TraversableViewLike$class.partition(this, p);
            }

            public TraversableView<A, Repr> init() {
                return TraversableViewLike$class.init(this);
            }

            public TraversableView<A, Repr> drop(int n) {
                return TraversableViewLike$class.drop(this, n);
            }

            public TraversableView<A, Repr> take(int n) {
                return TraversableViewLike$class.take(this, n);
            }

            public TraversableView<A, Repr> slice(int from2, int until2) {
                return TraversableViewLike$class.slice(this, from2, until2);
            }

            public TraversableView<A, Repr> dropWhile(Function1<A, Object> p) {
                return TraversableViewLike$class.dropWhile(this, p);
            }

            public TraversableView<A, Repr> takeWhile(Function1<A, Object> p) {
                return TraversableViewLike$class.takeWhile(this, p);
            }

            public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> span(Function1<A, Object> p) {
                return TraversableViewLike$class.span(this, p);
            }

            public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> splitAt(int n) {
                return TraversableViewLike$class.splitAt(this, n);
            }

            public <B, That> That scanLeft(B z, Function2<B, A, B> op, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
            }

            public <B, That> That scanRight(B z, Function2<A, B, B> op, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
            }

            public <K> Map<K, TraversableView<A, Repr>> groupBy(Function1<A, K> f) {
                return TraversableViewLike$class.groupBy(this, f);
            }

            public <A1, A2> Tuple2<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> asPair) {
                return TraversableViewLike$class.unzip(this, asPair);
            }

            public <A1, A2, A3> Tuple3<TraversableViewLike.Transformed<A1>, TraversableViewLike.Transformed<A2>, TraversableViewLike.Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> asTriple) {
                return TraversableViewLike$class.unzip3(this, asTriple);
            }

            public TraversableView<A, Repr> filterNot(Function1<A, Object> p) {
                return TraversableViewLike$class.filterNot(this, p);
            }

            public Iterator<TraversableView<A, Repr>> inits() {
                return TraversableViewLike$class.inits(this);
            }

            public Iterator<TraversableView<A, Repr>> tails() {
                return TraversableViewLike$class.tails(this);
            }

            public TraversableView<A, Repr> tail() {
                return TraversableViewLike$class.tail(this);
            }

            public String toString() {
                return TraversableViewLike$class.toString(this);
            }

            public Seq<A> thisSeq() {
                return ViewMkString$class.thisSeq(this);
            }

            public String mkString() {
                return ViewMkString$class.mkString(this);
            }

            public String mkString(String sep) {
                return ViewMkString$class.mkString(this, sep);
            }

            public String mkString(String start, String sep, String end) {
                return ViewMkString$class.mkString(this, start, sep, end);
            }

            public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
                return ViewMkString$class.addString(this, b, start, sep, end);
            }

            public GenericCompanion<Traversable> companion() {
                return Traversable$class.companion(this);
            }

            public Traversable<A> seq() {
                return Traversable$class.seq(this);
            }

            public <B> Builder<B, Traversable<B>> genericBuilder() {
                return GenericTraversableTemplate$class.genericBuilder(this);
            }

            public GenTraversable transpose(Function1 asTraversable) {
                return GenericTraversableTemplate$class.transpose(this, asTraversable);
            }

            public Object repr() {
                return TraversableLike$class.repr(this);
            }

            public final boolean isTraversableAgain() {
                return TraversableLike$class.isTraversableAgain(this);
            }

            public Traversable<A> thisCollection() {
                return TraversableLike$class.thisCollection(this);
            }

            public Traversable toCollection(Object repr) {
                return TraversableLike$class.toCollection(this, repr);
            }

            public Combiner<A, ParIterable<A>> parCombiner() {
                return TraversableLike$class.parCombiner(this);
            }

            public boolean isEmpty() {
                return TraversableLike$class.isEmpty(this);
            }

            public boolean hasDefiniteSize() {
                return TraversableLike$class.hasDefiniteSize(this);
            }

            public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<TraversableView<A, Repr>, B, That> bf) {
                return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
            }

            public boolean forall(Function1<A, Object> p) {
                return TraversableLike$class.forall(this, p);
            }

            public boolean exists(Function1<A, Object> p) {
                return TraversableLike$class.exists(this, p);
            }

            public Option<A> find(Function1<A, Object> p) {
                return TraversableLike$class.find(this, p);
            }

            public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<TraversableView<A, Repr>, B, That> cbf) {
                return (That)TraversableLike$class.scan(this, z, op, cbf);
            }

            public A head() {
                return (A)TraversableLike$class.head(this);
            }

            public Option<A> headOption() {
                return TraversableLike$class.headOption(this);
            }

            public A last() {
                return (A)TraversableLike$class.last(this);
            }

            public Option<A> lastOption() {
                return TraversableLike$class.lastOption(this);
            }

            public Object sliceWithKnownDelta(int from2, int until2, int delta) {
                return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
            }

            public Object sliceWithKnownBound(int from2, int until2) {
                return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
            }

            public <B> void copyToArray(Object xs, int start, int len) {
                TraversableLike$class.copyToArray(this, xs, start, len);
            }

            public Traversable<A> toTraversable() {
                return TraversableLike$class.toTraversable(this);
            }

            public Iterator<A> toIterator() {
                return TraversableLike$class.toIterator(this);
            }

            public Stream<A> toStream() {
                return TraversableLike$class.toStream(this);
            }

            public <Col> Col to(CanBuildFrom<Nothing$, A, Col> cbf) {
                return (Col)TraversableLike$class.to(this, cbf);
            }

            public Object view() {
                return TraversableLike$class.view(this);
            }

            public TraversableView<A, TraversableView<A, Repr>> view(int from2, int until2) {
                return TraversableLike$class.view(this, from2, until2);
            }

            public Parallel par() {
                return Parallelizable$class.par(this);
            }

            public List<A> reversed() {
                return TraversableOnce$class.reversed(this);
            }

            public int size() {
                return TraversableOnce$class.size(this);
            }

            public boolean nonEmpty() {
                return TraversableOnce$class.nonEmpty(this);
            }

            public int count(Function1<A, Object> p) {
                return TraversableOnce$class.count(this, p);
            }

            public <B> Option<B> collectFirst(PartialFunction<A, B> pf) {
                return TraversableOnce$class.collectFirst(this, pf);
            }

            public <B> B $div$colon(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.$div$colon(this, z, op);
            }

            public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.$colon$bslash(this, z, op);
            }

            public <B> B foldLeft(B z, Function2<B, A, B> op) {
                return (B)TraversableOnce$class.foldLeft(this, z, op);
            }

            public <B> B foldRight(B z, Function2<A, B, B> op) {
                return (B)TraversableOnce$class.foldRight(this, z, op);
            }

            public <B> B reduceLeft(Function2<B, A, B> op) {
                return (B)TraversableOnce$class.reduceLeft(this, op);
            }

            public <B> B reduceRight(Function2<A, B, B> op) {
                return (B)TraversableOnce$class.reduceRight(this, op);
            }

            public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
                return TraversableOnce$class.reduceLeftOption(this, op);
            }

            public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
                return TraversableOnce$class.reduceRightOption(this, op);
            }

            public <A1> A1 reduce(Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.reduce(this, op);
            }

            public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
                return TraversableOnce$class.reduceOption(this, op);
            }

            public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
                return (A1)TraversableOnce$class.fold(this, z, op);
            }

            public <B> B aggregate(Function0<B> z, Function2<B, A, B> seqop, Function2<B, B, B> combop) {
                return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
            }

            public <B> B sum(Numeric<B> num) {
                return (B)TraversableOnce$class.sum(this, num);
            }

            public <B> B product(Numeric<B> num) {
                return (B)TraversableOnce$class.product(this, num);
            }

            public <B> A min(Ordering<B> cmp) {
                return (A)TraversableOnce$class.min(this, cmp);
            }

            public <B> A max(Ordering<B> cmp) {
                return (A)TraversableOnce$class.max(this, cmp);
            }

            public <B> A maxBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.maxBy(this, f, cmp);
            }

            public <B> A minBy(Function1<A, B> f, Ordering<B> cmp) {
                return (A)TraversableOnce$class.minBy(this, f, cmp);
            }

            public <B> void copyToBuffer(Buffer<B> dest) {
                TraversableOnce$class.copyToBuffer(this, dest);
            }

            public <B> void copyToArray(Object xs, int start) {
                TraversableOnce$class.copyToArray(this, xs, start);
            }

            public <B> void copyToArray(Object xs) {
                TraversableOnce$class.copyToArray(this, xs);
            }

            public <B> Object toArray(ClassTag<B> evidence$1) {
                return TraversableOnce$class.toArray(this, evidence$1);
            }

            public List<A> toList() {
                return TraversableOnce$class.toList(this);
            }

            public Iterable<A> toIterable() {
                return TraversableOnce$class.toIterable(this);
            }

            public Seq<A> toSeq() {
                return TraversableOnce$class.toSeq(this);
            }

            public IndexedSeq<A> toIndexedSeq() {
                return TraversableOnce$class.toIndexedSeq(this);
            }

            public <B> Buffer<B> toBuffer() {
                return TraversableOnce$class.toBuffer(this);
            }

            public <B> Set<B> toSet() {
                return TraversableOnce$class.toSet(this);
            }

            public Vector<A> toVector() {
                return TraversableOnce$class.toVector(this);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
                return TraversableOnce$class.toMap(this, ev);
            }

            public StringBuilder addString(StringBuilder b, String sep) {
                return TraversableOnce$class.addString(this, b, sep);
            }

            public StringBuilder addString(StringBuilder b) {
                return TraversableOnce$class.addString(this, b);
            }

            public Repr underlying() {
                return (Repr)(this.bitmap$0 ? this.underlying : this.underlying$lzycompute());
            }

            public <U> void foreach(Function1<A, U> f) {
                this.$outer.foreach(f);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                TraversableOnce$class.$init$(this);
                Parallelizable$class.$init$(this);
                TraversableLike$class.$init$(this);
                GenericTraversableTemplate$class.$init$(this);
                GenTraversable$class.$init$(this);
                Traversable$class.$init$(this);
                ViewMkString$class.$init$(this);
                TraversableViewLike$class.$init$(this);
            }
        };
    }

    public static TraversableView view(TraversableLike $this, int from2, int until2) {
        return $this.view().slice(from2, until2);
    }

    public static FilterMonadic withFilter(TraversableLike $this, Function1 p) {
        return new TraversableLike.WithFilter($this, p);
    }

    private static Iterator iterateUntilEmpty(TraversableLike $this, Function1 f) {
        Traversable traversable = $this.thisCollection();
        Iterator$ iterator$ = Iterator$.MODULE$;
        Iterator it = new AbstractIterator<T>(traversable, f){
            private boolean first;
            private T acc;
            private final Function1 f$2;

            public boolean hasNext() {
                return true;
            }

            public T next() {
                if (this.first) {
                    this.first = false;
                } else {
                    this.acc = this.f$2.apply(this.acc);
                }
                return this.acc;
            }
            {
                this.f$2 = f$2;
                this.first = true;
                this.acc = start$2;
            }
        }.takeWhile(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Traversable<A> x) {
                return !x.isEmpty();
            }
        });
        return it.$plus$plus(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Iterator<Nil$> apply() {
                return Iterator$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Nil$[]{Nil$.MODULE$}));
            }
        }).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraversableLike $outer;

            public final Repr apply(Traversable<A> x) {
                return (Repr)((Builder)this.$outer.newBuilder().$plus$plus$eq(x)).result();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    /*
     * WARNING - void declaration
     */
    private static final Builder builder$1(TraversableLike $this, CanBuildFrom bf$1) {
        void var2_2;
        Builder b = bf$1.apply($this.repr());
        b.sizeHint($this);
        return var2_2;
    }

    private static final Builder builder$2(TraversableLike $this, CanBuildFrom bf$2) {
        return bf$2.apply($this.repr());
    }

    public static void $init$(TraversableLike $this) {
    }
}

