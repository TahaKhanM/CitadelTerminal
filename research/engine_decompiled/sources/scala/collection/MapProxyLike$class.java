/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.MapLike;
import scala.collection.MapProxyLike;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.mutable.StringBuilder;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public abstract class MapProxyLike$class {
    public static Option get(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).get(key);
    }

    public static Iterator iterator(MapProxyLike $this) {
        return ((MapLike)$this.self()).iterator();
    }

    public static Map $plus(MapProxyLike $this, Tuple2 kv) {
        return ((MapLike)$this.self()).$plus(kv);
    }

    public static Map $minus(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).$minus(key);
    }

    public static boolean isEmpty(MapProxyLike $this) {
        return ((MapLike)$this.self()).isEmpty();
    }

    public static Object getOrElse(MapProxyLike $this, Object key, Function0 function0) {
        return ((MapLike)$this.self()).getOrElse(key, function0);
    }

    public static Object apply(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).apply(key);
    }

    public static boolean contains(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).contains(key);
    }

    public static boolean isDefinedAt(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).isDefinedAt(key);
    }

    public static Set keySet(MapProxyLike $this) {
        return ((MapLike)$this.self()).keySet();
    }

    public static Iterator keysIterator(MapProxyLike $this) {
        return ((MapLike)$this.self()).keysIterator();
    }

    public static Iterable keys(MapProxyLike $this) {
        return ((MapLike)$this.self()).keys();
    }

    public static Iterable values(MapProxyLike $this) {
        return ((MapLike)$this.self()).values();
    }

    public static Iterator valuesIterator(MapProxyLike $this) {
        return ((MapLike)$this.self()).valuesIterator();
    }

    public static Object default(MapProxyLike $this, Object key) {
        return ((MapLike)$this.self()).default(key);
    }

    public static Map filterKeys(MapProxyLike $this, Function1 p) {
        return ((MapLike)$this.self()).filterKeys(p);
    }

    public static Map mapValues(MapProxyLike $this, Function1 f) {
        return ((MapLike)$this.self()).mapValues(f);
    }

    public static Map updated(MapProxyLike $this, Object key, Object value) {
        return ((MapLike)$this.self()).updated(key, value);
    }

    public static Map $plus(MapProxyLike $this, Tuple2 kv1, Tuple2 kv2, Seq kvs) {
        return ((MapLike)$this.self()).$plus(kv1, kv2, kvs);
    }

    public static Map $plus$plus(MapProxyLike $this, GenTraversableOnce xs) {
        return ((MapLike)$this.self()).$plus$plus(xs);
    }

    public static Map filterNot(MapProxyLike $this, Function1 p) {
        return ((MapLike)$this.self()).filterNot(p);
    }

    public static StringBuilder addString(MapProxyLike $this, StringBuilder b, String start, String sep, String end) {
        return ((MapLike)$this.self()).addString(b, start, sep, end);
    }

    public static void $init$(MapProxyLike $this) {
    }
}

