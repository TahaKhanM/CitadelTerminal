/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.collection.mutable.MapLike;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParMap$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class MapLike$class {
    public static Builder newBuilder(MapLike $this) {
        return (Builder)$this.empty();
    }

    public static Combiner parCombiner(MapLike $this) {
        return ParMap$.MODULE$.newCombiner();
    }

    /*
     * WARNING - void declaration
     */
    public static Option put(MapLike $this, Object key, Object value) {
        void var3_3;
        Option r = $this.get(key);
        $this.update(key, value);
        return var3_3;
    }

    public static void update(MapLike $this, Object key, Object value) {
        $this.$plus$eq(new Tuple2<Object, Object>(key, value));
    }

    public static Map updated(MapLike $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static Map $plus(MapLike $this, Tuple2 kv) {
        return (Map)$this.clone().$plus$eq(kv);
    }

    public static Map $plus(MapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
        return (Map)$this.clone().$plus$eq(elem1).$plus$eq(elem2).$plus$plus$eq(elems);
    }

    public static Map $plus$plus(MapLike $this, GenTraversableOnce xs) {
        return (Map)$this.clone().$plus$plus$eq(xs.seq());
    }

    /*
     * WARNING - void declaration
     */
    public static Option remove(MapLike $this, Object key) {
        void var2_2;
        Option r = $this.get(key);
        $this.$minus$eq(key);
        return var2_2;
    }

    public static Map $minus(MapLike $this, Object key) {
        return (Map)$this.clone().$minus$eq((Object)key);
    }

    public static void clear(MapLike $this) {
        $this.keysIterator().foreach(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ MapLike $outer;

            public final MapLike<A, B, This> apply(A key) {
                return this.$outer.$minus$eq(key);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Object getOrElseUpdate(MapLike $this, Object key, Function0 op) {
        Option option;
        block4: {
            Object object;
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!(option instanceof Some)) break block2;
                    Some some = (Some)option;
                    object = some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                Object d = op.apply();
                $this.update(key, d);
                object = d;
            }
            return object;
        }
        throw new MatchError(option);
    }

    public static MapLike transform(MapLike $this, Function2 f) {
        $this.iterator().foreach(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ MapLike $outer;
            private final Function2 f$1;

            public final void apply(Tuple2<A, B> x0$1) {
                if (x0$1 != null) {
                    this.$outer.update(x0$1._1(), this.f$1.apply(x0$1._1(), x0$1._2()));
                    return;
                }
                throw new MatchError(x0$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$1 = f$1;
            }
        });
        return $this;
    }

    public static MapLike retain(MapLike $this, Function2 p) {
        $this.toList().withFilter(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<A, B> check$ifrefutable$1) {
                boolean bl = check$ifrefutable$1 != null;
                return bl;
            }
        }).foreach(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ MapLike $outer;
            private final Function2 p$1;

            public final Object apply(Tuple2<A, B> x$1) {
                if (x$1 != null) {
                    return BoxesRunTime.unboxToBoolean(this.p$1.apply(x$1._1(), x$1._2())) ? BoxedUnit.UNIT : this.$outer.$minus$eq(x$1._1());
                }
                throw new MatchError(x$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
            }
        });
        return $this;
    }

    public static Map clone(MapLike $this) {
        return (Map)((Growable)$this.empty()).$plus$plus$eq((TraversableOnce)$this.repr());
    }

    public static Map result(MapLike $this) {
        return (Map)$this.repr();
    }

    public static Map $minus(MapLike $this, Object elem1, Object elem2, Seq elems) {
        return (Map)$this.clone().$minus$eq((Object)elem1).$minus$eq(elem2).$minus$minus$eq(elems);
    }

    public static Map $minus$minus(MapLike $this, GenTraversableOnce xs) {
        return (Map)$this.clone().$minus$minus$eq(xs.seq());
    }

    public static void $init$(MapLike $this) {
    }
}

