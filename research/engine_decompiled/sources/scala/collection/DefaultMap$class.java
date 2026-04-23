/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.DefaultMap;
import scala.collection.Map;
import scala.collection.Map$;
import scala.collection.TraversableOnce;
import scala.collection.mutable.Builder;
import scala.runtime.BoxesRunTime;

public abstract class DefaultMap$class {
    public static Map $plus(DefaultMap $this, Tuple2 kv) {
        Builder b = Map$.MODULE$.newBuilder();
        b.$plus$plus$eq($this);
        b.$plus$eq(new Tuple2(kv._1(), kv._2()));
        return (Map)b.result();
    }

    public static Map $minus(DefaultMap $this, Object key) {
        Builder b = $this.newBuilder();
        b.$plus$plus$eq((TraversableOnce)$this.filter(new Serializable($this, key){
            public static final long serialVersionUID = 0L;
            private final Object key$1;

            public final boolean apply(Tuple2<A, B> x$1) {
                Object object = this.key$1;
                A a = x$1._1();
                return !(object == a ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, a) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, a) : object.equals(a)))));
            }
            {
                this.key$1 = key$1;
            }
        }));
        return (Map)b.result();
    }

    public static void $init$(DefaultMap $this) {
    }
}

