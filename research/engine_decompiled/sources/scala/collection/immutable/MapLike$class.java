/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.DefaultMap;
import scala.collection.immutable.DefaultMap$class;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$class;
import scala.collection.immutable.MapLike;
import scala.collection.immutable.Set;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParMap$;

public abstract class MapLike$class {
    public static Combiner parCombiner(MapLike $this) {
        return ParMap$.MODULE$.newCombiner();
    }

    public static Map updated(MapLike $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static Map $plus(MapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        return $this.$plus(elem1).$plus(elem2).$plus$plus(elems);
    }

    public static Map $plus$plus(MapLike $this, GenTraversableOnce xs) {
        Map map2 = (Map)$this.repr();
        return xs.seq().$div$colon(map2, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Map<A, B1> apply(Map<A, B1> x$2, Tuple2<A, B1> x$3) {
                return x$2.$plus(x$3);
            }
        });
    }

    public static Map filterKeys(MapLike $this, Function1 p) {
        return new DefaultMap<A, B>($this, p){

            public <B1> Map<A, B1> $plus(Tuple2<A, B1> kv) {
                return DefaultMap$class.$plus(this, kv);
            }

            public Map<A, B> $minus(A key) {
                return DefaultMap$class.$minus(this, key);
            }

            public Map<A, B> empty() {
                return Map$class.empty(this);
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

            public <B1> Map<A, B1> updated(A key, B1 value) {
                return MapLike$class.updated(this, key, value);
            }

            public <B1> Map<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return MapLike$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return MapLike$class.$plus$plus(this, xs);
            }

            public Map<A, B> filterKeys(Function1<A, Object> p) {
                return MapLike$class.filterKeys(this, p);
            }

            public <C> Map<A, C> mapValues(Function1<B, C> f) {
                return MapLike$class.mapValues(this, f);
            }

            public Set<A> keySet() {
                return MapLike$class.keySet(this);
            }

            public <C, That> That transform(Function2<A, B, C> f, CanBuildFrom<Map<A, B>, Tuple2<A, C>, That> bf) {
                return (That)MapLike$class.transform(this, f, bf);
            }

            public GenericCompanion<Iterable> companion() {
                return Iterable$class.companion(this);
            }
            {
                Traversable$class.$init$(this);
                Iterable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                DefaultMap$class.$init$(this);
            }
        };
    }

    public static Map mapValues(MapLike $this, Function1 f) {
        return new DefaultMap<A, C>($this, f){

            public <B1> Map<A, B1> $plus(Tuple2<A, B1> kv) {
                return DefaultMap$class.$plus(this, kv);
            }

            public Map<A, C> $minus(A key) {
                return DefaultMap$class.$minus(this, key);
            }

            public Map<A, C> empty() {
                return Map$class.empty(this);
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

            public <B1> Map<A, B1> updated(A key, B1 value) {
                return MapLike$class.updated(this, key, value);
            }

            public <B1> Map<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
                return MapLike$class.$plus(this, elem1, elem2, elems);
            }

            public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
                return MapLike$class.$plus$plus(this, xs);
            }

            public Map<A, C> filterKeys(Function1<A, Object> p) {
                return MapLike$class.filterKeys(this, p);
            }

            public <C> Map<A, C> mapValues(Function1<C, C> f) {
                return MapLike$class.mapValues(this, f);
            }

            public Set<A> keySet() {
                return MapLike$class.keySet(this);
            }

            public <C, That> That transform(Function2<A, C, C> f, CanBuildFrom<Map<A, C>, Tuple2<A, C>, That> bf) {
                return (That)MapLike$class.transform(this, f, bf);
            }

            public GenericCompanion<Iterable> companion() {
                return Iterable$class.companion(this);
            }
            {
                Traversable$class.$init$(this);
                Iterable$class.$init$(this);
                MapLike$class.$init$(this);
                Map$class.$init$(this);
                DefaultMap$class.$init$(this);
            }
        };
    }

    public static Set keySet(MapLike $this) {
        return new MapLike.ImmutableDefaultKeySet($this);
    }

    public static Object transform(MapLike $this, Function2 f, CanBuildFrom bf) {
        Builder b = bf.apply($this.repr());
        $this.withFilter(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<A, B> check$ifrefutable$1) {
                boolean bl = check$ifrefutable$1 != null;
                return bl;
            }
        }).foreach(new Serializable($this, b, f){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;
            private final Function2 f$2;

            public final Builder<Tuple2<A, C>, That> apply(Tuple2<A, B> x$4) {
                if (x$4 != null) {
                    return this.b$1.$plus$eq(new Tuple2<A, R>(x$4._1(), this.f$2.apply(x$4._1(), x$4._2())));
                }
                throw new MatchError(x$4);
            }
            {
                void var3_3;
                this.b$1 = b$1;
                this.f$2 = var3_3;
            }
        });
        return b.result();
    }

    public static void $init$(MapLike $this) {
    }
}

