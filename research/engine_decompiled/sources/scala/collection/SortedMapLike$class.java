/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SortedMap;
import scala.collection.SortedMap$Default$class;
import scala.collection.SortedMap$class;
import scala.collection.SortedMapLike;
import scala.collection.SortedMapLike$;
import scala.collection.SortedSet;
import scala.collection.generic.Sorted;
import scala.collection.generic.Sorted$class;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

public abstract class SortedMapLike$class {
    public static Object firstKey(SortedMapLike $this) {
        return ((Tuple2)$this.head())._1();
    }

    public static Object lastKey(SortedMapLike $this) {
        return ((Tuple2)$this.last())._1();
    }

    public static SortedSet keySet(SortedMapLike $this) {
        return new SortedMapLike.DefaultKeySortedSet($this);
    }

    public static SortedMap updated(SortedMapLike $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static SortedMap $plus(SortedMapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        ObjectRef m = ObjectRef.create($this.$plus(elem1).$plus(elem2));
        elems.foreach(new Serializable($this, m){
            public static final long serialVersionUID = 0L;
            private final ObjectRef m$1;

            public final void apply(Tuple2<A, B1> e) {
                this.m$1.elem = ((SortedMap)this.m$1.elem).$plus(e);
            }
            {
                this.m$1 = m$1;
            }
        });
        return (SortedMap)m.elem;
    }

    public static SortedMap filterKeys(SortedMapLike $this, Function1 p) {
        return new SortedMap.Default<A, B>($this, p){
            private final /* synthetic */ SortedMapLike $outer;
            public final Function1 p$1;

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> kv) {
                return SortedMap$Default$class.$plus(this, kv);
            }

            public SortedMap<A, B> $minus(A key) {
                return SortedMap$Default$class.$minus(this, key);
            }

            public SortedMap<A, B> empty() {
                return SortedMap$class.empty(this);
            }

            public Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder() {
                return SortedMap$class.newBuilder(this);
            }

            public A firstKey() {
                return (A)SortedMapLike$class.firstKey(this);
            }

            public A lastKey() {
                return (A)SortedMapLike$class.lastKey(this);
            }

            public SortedSet<A> keySet() {
                return SortedMapLike$class.keySet(this);
            }

            public <B1> SortedMap<A, B1> updated(A key, B1 value) {
                return SortedMapLike$class.updated(this, key, value);
            }

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return SortedMapLike$class.$plus(this, elem1, elem2, elems);
            }

            public SortedMap<A, B> filterKeys(Function1<A, Object> p) {
                return SortedMapLike$class.filterKeys(this, p);
            }

            public <C> SortedMap<A, C> mapValues(Function1<B, C> f) {
                return SortedMapLike$class.mapValues(this, f);
            }

            public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return SortedMapLike$class.$plus$plus(this, xs);
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

            public Ordering<A> ordering() {
                return this.$outer.ordering();
            }

            public SortedMap<A, B> rangeImpl(Option<A> from2, Option<A> until2) {
                return this.$outer.rangeImpl(from2, until2).filterKeys(this.p$1);
            }

            public Iterator<Tuple2<A, B>> iteratorFrom(A start) {
                return this.$outer.iteratorFrom(start).filter(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SortedMapLike$.anon.1 $outer;

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
                    private final /* synthetic */ SortedMapLike$.anon.1 $outer;

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
                Sorted$class.$init$(this);
                SortedMapLike$class.$init$(this);
                SortedMap$class.$init$(this);
                SortedMap$Default$class.$init$(this);
            }
        };
    }

    public static SortedMap mapValues(SortedMapLike $this, Function1 f) {
        return new SortedMap.Default<A, C>($this, f){
            private final /* synthetic */ SortedMapLike $outer;
            public final Function1 f$1;

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> kv) {
                return SortedMap$Default$class.$plus(this, kv);
            }

            public SortedMap<A, C> $minus(A key) {
                return SortedMap$Default$class.$minus(this, key);
            }

            public SortedMap<A, C> empty() {
                return SortedMap$class.empty(this);
            }

            public Builder<Tuple2<A, C>, SortedMap<A, C>> newBuilder() {
                return SortedMap$class.newBuilder(this);
            }

            public A firstKey() {
                return (A)SortedMapLike$class.firstKey(this);
            }

            public A lastKey() {
                return (A)SortedMapLike$class.lastKey(this);
            }

            public SortedSet<A> keySet() {
                return SortedMapLike$class.keySet(this);
            }

            public <B1> SortedMap<A, B1> updated(A key, B1 value) {
                return SortedMapLike$class.updated(this, key, value);
            }

            public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return SortedMapLike$class.$plus(this, elem1, elem2, elems);
            }

            public SortedMap<A, C> filterKeys(Function1<A, Object> p) {
                return SortedMapLike$class.filterKeys(this, p);
            }

            public <C> SortedMap<A, C> mapValues(Function1<C, C> f) {
                return SortedMapLike$class.mapValues(this, f);
            }

            public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return SortedMapLike$class.$plus$plus(this, xs);
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

            public Ordering<A> ordering() {
                return this.$outer.ordering();
            }

            public SortedMap<A, C> rangeImpl(Option<A> from2, Option<A> until2) {
                return this.$outer.rangeImpl(from2, until2).mapValues(this.f$1);
            }

            public Iterator<Tuple2<A, C>> iteratorFrom(A start) {
                return this.$outer.iteratorFrom(start).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SortedMapLike$.anon.2 $outer;

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
                Sorted$class.$init$(this);
                SortedMapLike$class.$init$(this);
                SortedMap$class.$init$(this);
                SortedMap$Default$class.$init$(this);
            }
        };
    }

    public static SortedMap $plus$plus(SortedMapLike $this, GenTraversableOnce xs) {
        SortedMap sortedMap = (SortedMap)$this.repr();
        return xs.seq().$div$colon(sortedMap, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final SortedMap<A, B1> apply(SortedMap<A, B1> x$2, Tuple2<A, B1> x$3) {
                return x$2.$plus(x$3);
            }
        });
    }

    public static void $init$(SortedMapLike $this) {
    }
}

