/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;

public abstract class Map$class {
    public static Map empty(Map $this) {
        return Map$.MODULE$.empty();
    }

    public static Map toMap(Map $this, Predef$.less.colon.less ev) {
        return $this;
    }

    public static Map seq(Map $this) {
        return $this;
    }

    public static Map withDefault(Map $this, Function1 d) {
        return new Map.WithDefault($this, d);
    }

    public static Map withDefaultValue(Map $this, Object d) {
        return new Map.WithDefault($this, new Serializable($this, d){
            public static final long serialVersionUID = 0L;
            private final Object d$2;

            public final B1 apply(A x) {
                return (B1)this.d$2;
            }
            {
                this.d$2 = d$2;
            }
        });
    }

    public static void $init$(Map $this) {
    }
}

