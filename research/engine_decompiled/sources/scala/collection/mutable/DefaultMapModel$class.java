/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.DefaultEntry;
import scala.collection.mutable.DefaultMapModel;

public abstract class DefaultMapModel$class {
    public static Option get(DefaultMapModel $this, Object key) {
        DefaultEntry e = $this.findEntry(key);
        return e == null ? None$.MODULE$ : new Some(e.value());
    }

    public static Option put(DefaultMapModel $this, Object key, Object value) {
        Option option;
        DefaultEntry<Object, Object> e = $this.findEntry(key);
        if (e == null) {
            $this.addEntry(new DefaultEntry<Object, Object>(key, value));
            option = None$.MODULE$;
        } else {
            Object v = e.value();
            e.value_$eq(value);
            option = new Some(v);
        }
        return option;
    }

    public static DefaultMapModel $plus$eq(DefaultMapModel $this, Tuple2 kv) {
        $this.put(kv._1(), kv._2());
        return $this;
    }

    public static Iterator iterator(DefaultMapModel $this) {
        return $this.entries().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Tuple2<A, B> apply(DefaultEntry<A, B> e) {
                return new Tuple2<A, B>(e.key(), e.value());
            }
        });
    }

    public static void $init$(DefaultMapModel $this) {
    }
}

