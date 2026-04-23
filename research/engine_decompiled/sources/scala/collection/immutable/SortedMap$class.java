/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SortedMapLike$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Sorted;
import scala.collection.generic.Sorted$class;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$class;
import scala.collection.immutable.MapLike$class;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedMap$;
import scala.collection.immutable.SortedMap$Default$class;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;

public abstract class SortedMap$class {
    public static Builder newBuilder(SortedMap $this) {
        return SortedMap$.MODULE$.newBuilder($this.ordering());
    }

    public static SortedMap empty(SortedMap $this) {
        return SortedMap$.MODULE$.empty($this.ordering());
    }

    public static SortedMap updated(SortedMap $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static SortedSet keySet(SortedMap $this) {
        return new SortedMap.DefaultKeySortedSet($this);
    }

    public static SortedMap $plus(SortedMap $this, Tuple2 kv) {
        throw new AbstractMethodError("SortedMap.+");
    }

    public static SortedMap $plus(SortedMap $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        return $this.$plus(elem1).$plus(elem2).$plus$plus(elems);
    }

    public static SortedMap $plus$plus(SortedMap $this, GenTraversableOnce xs) {
        SortedMap sortedMap = (SortedMap)$this.repr();
        return xs.seq().$div$colon(sortedMap, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final SortedMap<A, B1> apply(SortedMap<A, B1> x$3, Tuple2<A, B1> x$4) {
                return x$3.$plus(x$4);
            }
        });
    }

    public static SortedMap filterKeys(SortedMap $this, Function1 p) {
        return new SortedMap.Default<A, B>($this, p){
            private final /* synthetic */ SortedMap $outer;
            public final Function1 p$1;

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> kv) {
                return SortedMap$Default$class.$plus(this, kv);
            }

            public SortedMap<A, B> $minus(A key) {
                return SortedMap$Default$class.$minus(this, key);
            }

            public Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder() {
                return SortedMap$class.newBuilder(this);
            }

            public SortedMap<A, B> empty() {
                return SortedMap$class.empty(this);
            }

            public <B1> SortedMap<A, B1> updated(A key, B1 value) {
                return SortedMap$class.updated(this, key, value);
            }

            public SortedSet<A> keySet() {
                return SortedMap$class.keySet(this);
            }

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return SortedMap$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return SortedMap$class.$plus$plus(this, xs);
            }

            public SortedMap<A, B> filterKeys(Function1<A, Object> p) {
                return SortedMap$class.filterKeys(this, p);
            }

            public <C> SortedMap<A, C> mapValues(Function1<B, C> f) {
                return SortedMap$class.mapValues(this, f);
            }

            public A firstKey() {
                return (A)SortedMapLike$class.firstKey(this);
            }

            public A lastKey() {
                return (A)SortedMapLike$class.lastKey(this);
            }

            public int compare(A k0, A k1) {
                return Sorted$class.compare(this, k0, k1);
            }

            public Sorted from(Object from2) {
                return Sorted$class.from(this, from2);
            }

            public Sorted until(Object until2) {
                return Sorted$class.until(this, until2);
            }

            public Sorted range(Object from2, Object until2) {
                return Sorted$class.range(this, from2, until2);
            }

            public Sorted to(Object to2) {
                return Sorted$class.to(this, to2);
            }

            public boolean hasAll(Iterator<A> j) {
                return Sorted$class.hasAll(this, j);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, B>, Tuple2<T, U>> ev) {
                return Map$class.toMap(this, ev);
            }

            public Map<A, B> seq() {
                return Map$class.seq(this);
            }

            public <B1> Map<A, B1> withDefault(Function1<A, B1> d) {
                return Map$class.withDefault(this, d);
            }

            public <B1> Map<A, B1> withDefaultValue(B1 d) {
                return Map$class.withDefaultValue(this, d);
            }

            public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
                return MapLike$class.parCombiner(this);
            }

            public <C, That> That transform(Function2<A, B, C> f, CanBuildFrom<SortedMap<A, B>, Tuple2<A, C>, That> bf) {
                return (That)MapLike$class.transform(this, f, bf);
            }

            public GenericCompanion<Iterable> companion() {
                return Iterable$class.companion(this);
            }

            public Ordering<A> ordering() {
                return this.$outer.ordering();
            }

            public SortedMap<A, B> rangeImpl(Option<A> from2, Option<A> until2) {
                return ((SortedMap)this.$outer.rangeImpl(from2, until2)).filterKeys(this.p$1);
            }

            public Iterator<Tuple2<A, B>> iteratorFrom(A start) {
                return this.$outer.iteratorFrom(start).filter(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SortedMap$.anon.1 $outer;

                    public final boolean apply(Tuple2<A, B> x0$1) {
                        if (x0$1 != null) {
                            return BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(x0$1._1()));
                        }
                        throw new MatchError(x0$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public Iterator<A> keysIteratorFrom(A start) {
                return this.$outer.keysIteratorFrom(start).filter(this.p$1);
            }

            public Iterator<B> valuesIteratorFrom(A start) {
                return this.$outer.iteratorFrom(start).collect(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SortedMap$.anon.1 $outer;

                    public final <A1 extends Tuple2<A, B>, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        Object object = x1 != null && BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(x1._1())) ? x1._2() : function1.apply(x1);
                        return object;
                    }

                    public final boolean isDefinedAt(Tuple2<A, B> x1) {
                        boolean bl = x1 != null && BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(x1._1()));
                        return bl;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
                super($outer, p$1);
                Traversable$class.$init$(this);
                Iterable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                Sorted$class.$init$(this);
                SortedMapLike$class.$init$(this);
                scala.collection.SortedMap$class.$init$(this);
                SortedMap$class.$init$(this);
                scala.collection.SortedMap$Default$class.$init$(this);
                SortedMap$Default$class.$init$(this);
            }
        };
    }

    public static SortedMap mapValues(SortedMap $this, Function1 f) {
        return new SortedMap.Default<A, C>($this, f){
            private final /* synthetic */ SortedMap $outer;
            public final Function1 f$1;

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> kv) {
                return SortedMap$Default$class.$plus(this, kv);
            }

            public SortedMap<A, C> $minus(A key) {
                return SortedMap$Default$class.$minus(this, key);
            }

            public Builder<Tuple2<A, C>, SortedMap<A, C>> newBuilder() {
                return SortedMap$class.newBuilder(this);
            }

            public SortedMap<A, C> empty() {
                return SortedMap$class.empty(this);
            }

            public <B1> SortedMap<A, B1> updated(A key, B1 value) {
                return SortedMap$class.updated(this, key, value);
            }

            public SortedSet<A> keySet() {
                return SortedMap$class.keySet(this);
            }

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return SortedMap$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return SortedMap$class.$plus$plus(this, xs);
            }

            public SortedMap<A, C> filterKeys(Function1<A, Object> p) {
                return SortedMap$class.filterKeys(this, p);
            }

            public <C> SortedMap<A, C> mapValues(Function1<C, C> f) {
                return SortedMap$class.mapValues(this, f);
            }

            public A firstKey() {
                return (A)SortedMapLike$class.firstKey(this);
            }

            public A lastKey() {
                return (A)SortedMapLike$class.lastKey(this);
            }

            public int compare(A k0, A k1) {
                return Sorted$class.compare(this, k0, k1);
            }

            public Sorted from(Object from2) {
                return Sorted$class.from(this, from2);
            }

            public Sorted until(Object until2) {
                return Sorted$class.until(this, until2);
            }

            public Sorted range(Object from2, Object until2) {
                return Sorted$class.range(this, from2, until2);
            }

            public Sorted to(Object to2) {
                return Sorted$class.to(this, to2);
            }

            public boolean hasAll(Iterator<A> j) {
                return Sorted$class.hasAll(this, j);
            }

            public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<A, C>, Tuple2<T, U>> ev) {
                return Map$class.toMap(this, ev);
            }

            public Map<A, C> seq() {
                return Map$class.seq(this);
            }

            public <B1> Map<A, B1> withDefault(Function1<A, B1> d) {
                return Map$class.withDefault(this, d);
            }

            public <B1> Map<A, B1> withDefaultValue(B1 d) {
                return Map$class.withDefaultValue(this, d);
            }

            public Combiner<Tuple2<A, C>, ParMap<A, C>> parCombiner() {
                return MapLike$class.parCombiner(this);
            }

            public <C, That> That transform(Function2<A, C, C> f, CanBuildFrom<SortedMap<A, C>, Tuple2<A, C>, That> bf) {
                return (That)MapLike$class.transform(this, f, bf);
            }

            public GenericCompanion<Iterable> companion() {
                return Iterable$class.companion(this);
            }

            public Ordering<A> ordering() {
                return this.$outer.ordering();
            }

            public SortedMap<A, C> rangeImpl(Option<A> from2, Option<A> until2) {
                return ((SortedMap)this.$outer.rangeImpl(from2, until2)).mapValues(this.f$1);
            }

            public Iterator<Tuple2<A, C>> iteratorFrom(A start) {
                return this.$outer.iteratorFrom(start).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SortedMap$.anon.2 $outer;

                    public final Tuple2<A, C> apply(Tuple2<A, B> x0$2) {
                        if (x0$2 != null) {
                            return new Tuple2<A, R>(x0$2._1(), this.$outer.f$1.apply(x0$2._2()));
                        }
                        throw new MatchError(x0$2);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }

            public Iterator<A> keysIteratorFrom(A start) {
                return this.$outer.keysIteratorFrom(start);
            }

            public Iterator<C> valuesIteratorFrom(A start) {
                return this.$outer.valuesIteratorFrom(start).map(this.f$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$1 = f$1;
                super($outer, f$1);
                Traversable$class.$init$(this);
                Iterable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                Sorted$class.$init$(this);
                SortedMapLike$class.$init$(this);
                scala.collection.SortedMap$class.$init$(this);
                SortedMap$class.$init$(this);
                scala.collection.SortedMap$Default$class.$init$(this);
                SortedMap$Default$class.$init$(this);
            }
        };
    }

    public static void $init$(SortedMap $this) {
    }
}

