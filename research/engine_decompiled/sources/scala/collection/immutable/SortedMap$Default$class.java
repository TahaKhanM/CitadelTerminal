/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedMap$;
import scala.collection.mutable.Builder;
import scala.runtime.BoxesRunTime;

public abstract class SortedMap$Default$class {
    public static SortedMap $plus(SortedMap.Default $this, Tuple2 kv) {
        Builder b = SortedMap$.MODULE$.newBuilder($this.ordering());
        b.$plus$plus$eq($this);
        b.$plus$eq(new Tuple2(kv._1(), kv._2()));
        return (SortedMap)b.result();
    }

    public static SortedMap $minus(SortedMap.Default $this, Object key) {
        Builder b = $this.newBuilder();
        $this.withFilter(new Serializable($this, key){
            public static final long serialVersionUID = 0L;
            private final Object key$1;

            public final boolean apply(Tuple2<A, B> kv) {
                Object object = this.key$1;
                A a = kv._1();
                return !(a == object ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, object) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, object) : a.equals(object)))));
            }
            {
                this.key$1 = key$1;
            }
        }).foreach(new Serializable($this, b){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;

            public final Builder<Tuple2<A, B>, SortedMap<A, B>> apply(Tuple2<A, B> kv) {
                return this.b$1.$plus$eq(kv);
            }
            {
                this.b$1 = b$1;
            }
        });
        return b.result();
    }

    public static void $init$(SortedMap.Default $this) {
    }
}

