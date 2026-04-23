/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.TraversableLike$class;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.MapBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParMap$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public abstract class MapLike$class {
    public static Builder newBuilder(MapLike $this) {
        return new MapBuilder($this.empty());
    }

    public static boolean isEmpty(MapLike $this) {
        return $this.size() == 0;
    }

    public static Object getOrElse(MapLike $this, Object key, Function0 function0) {
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
                object = function0.apply();
            }
            return object;
        }
        throw new MatchError(option);
    }

    public static Object apply(MapLike $this, Object key) {
        Option option;
        block4: {
            Object object;
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!None$.MODULE$.equals(option)) break block2;
                    object = $this.default(key);
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                object = some.x();
            }
            return object;
        }
        throw new MatchError(option);
    }

    public static boolean contains(MapLike $this, Object key) {
        return $this.get(key).isDefined();
    }

    public static boolean isDefinedAt(MapLike $this, Object key) {
        return $this.contains(key);
    }

    public static Set keySet(MapLike $this) {
        return new MapLike.DefaultKeySet($this);
    }

    public static Iterator keysIterator(MapLike $this) {
        return new AbstractIterator<A>($this){
            private final Iterator<Tuple2<A, B>> iter;

            private Iterator<Tuple2<A, B>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public A next() {
                return this.iter().next()._1();
            }
            {
                this.iter = $outer.iterator();
            }
        };
    }

    public static Iterable keys(MapLike $this) {
        return $this.keySet();
    }

    public static Iterable values(MapLike $this) {
        return new MapLike.DefaultValuesIterable($this);
    }

    public static Iterator valuesIterator(MapLike $this) {
        return new AbstractIterator<B>($this){
            private final Iterator<Tuple2<A, B>> iter;

            private Iterator<Tuple2<A, B>> iter() {
                return this.iter;
            }

            public boolean hasNext() {
                return this.iter().hasNext();
            }

            public B next() {
                return this.iter().next()._2();
            }
            {
                this.iter = $outer.iterator();
            }
        };
    }

    public static Object default(MapLike $this, Object key) {
        throw new NoSuchElementException(new StringBuilder().append((Object)"key not found: ").append(key).toString());
    }

    public static Map filterKeys(MapLike $this, Function1 p) {
        return new MapLike.FilteredKeys($this, p);
    }

    public static Map mapValues(MapLike $this, Function1 f) {
        return new MapLike.MappedValues($this, f);
    }

    public static Map updated(MapLike $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static Map $plus(MapLike $this, Tuple2 kv1, Tuple2 kv2, Seq kvs) {
        return $this.$plus(kv1).$plus(kv2).$plus$plus(kvs);
    }

    public static Map $plus$plus(MapLike $this, GenTraversableOnce xs) {
        Map map2 = (Map)$this.repr();
        return xs.seq().$div$colon(map2, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Map<A, B1> apply(Map<A, B1> x$4, Tuple2<A, B1> x$5) {
                return x$4.$plus(x$5);
            }
        });
    }

    public static Map filterNot(MapLike $this, Function1 p) {
        ObjectRef<Map> res = ObjectRef.create((Map)$this.repr());
        $this.foreach(new Serializable($this, res, p){
            public static final long serialVersionUID = 0L;
            private final ObjectRef res$1;
            private final Function1 p$1;

            public final void apply(Tuple2<A, B> kv) {
                if (BoxesRunTime.unboxToBoolean(this.p$1.apply(kv))) {
                    this.res$1.elem = ((Map)this.res$1.elem).$minus(kv._1());
                }
            }
            {
                void var3_3;
                this.res$1 = res$1;
                this.p$1 = var3_3;
            }
        });
        return (Map)res.elem;
    }

    public static Seq toSeq(MapLike $this) {
        return $this.toBuffer();
    }

    /*
     * WARNING - void declaration
     */
    public static Buffer toBuffer(MapLike $this) {
        void var1_1;
        ArrayBuffer result2 = new ArrayBuffer($this.size());
        $this.copyToBuffer(result2);
        return var1_1;
    }

    public static Combiner parCombiner(MapLike $this) {
        return ParMap$.MODULE$.newCombiner();
    }

    public static StringBuilder addString(MapLike $this, StringBuilder b, String start, String sep, String end) {
        return $this.iterator().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(Tuple2<A, B> x0$1) {
                if (x0$1 != null) {
                    return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x0$1._1()), " -> ")).append(x0$1._2()).toString();
                }
                throw new MatchError(x0$1);
            }
        }).addString(b, start, sep, end);
    }

    public static String stringPrefix(MapLike $this) {
        return "Map";
    }

    public static String toString(MapLike $this) {
        return TraversableLike$class.toString($this);
    }

    public static void $init$(MapLike $this) {
    }
}

