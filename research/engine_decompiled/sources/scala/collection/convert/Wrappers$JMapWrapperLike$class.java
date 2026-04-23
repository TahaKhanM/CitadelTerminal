/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import java.util.Map;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.convert.Wrappers;
import scala.collection.mutable.Map;

public abstract class Wrappers$JMapWrapperLike$class {
    public static int size(Wrappers.JMapWrapperLike $this) {
        return $this.underlying().size();
    }

    public static Option get(Wrappers.JMapWrapperLike $this, Object k) {
        Object v = $this.underlying().get(k);
        return v == null ? ($this.underlying().containsKey(k) ? new Some<Object>(null) : None$.MODULE$) : new Some(v);
    }

    public static Wrappers.JMapWrapperLike $plus$eq(Wrappers.JMapWrapperLike $this, Tuple2 kv) {
        $this.underlying().put(kv._1(), kv._2());
        return $this;
    }

    public static Wrappers.JMapWrapperLike $minus$eq(Wrappers.JMapWrapperLike $this, Object key) {
        $this.underlying().remove(key);
        return $this;
    }

    public static Option put(Wrappers.JMapWrapperLike $this, Object k, Object v) {
        return Option$.MODULE$.apply($this.underlying().put(k, v));
    }

    public static void update(Wrappers.JMapWrapperLike $this, Object k, Object v) {
        $this.underlying().put(k, v);
    }

    public static Option remove(Wrappers.JMapWrapperLike $this, Object k) {
        return Option$.MODULE$.apply($this.underlying().remove(k));
    }

    public static Iterator iterator(Wrappers.JMapWrapperLike $this) {
        return new AbstractIterator<Tuple2<A, B>>($this){
            private final java.util.Iterator<Map.Entry<A, B>> ui;

            private java.util.Iterator<Map.Entry<A, B>> ui() {
                return this.ui;
            }

            public boolean hasNext() {
                return this.ui().hasNext();
            }

            public Tuple2<A, B> next() {
                Map.Entry<A, B> e = this.ui().next();
                return new Tuple2<A, B>(e.getKey(), e.getValue());
            }
            {
                this.ui = $outer.underlying().entrySet().iterator();
            }
        };
    }

    public static void clear(Wrappers.JMapWrapperLike $this) {
        $this.underlying().clear();
    }

    public static Map empty(Wrappers.JMapWrapperLike $this) {
        return null;
    }

    public static void $init$(Wrappers.JMapWrapperLike $this) {
    }
}

