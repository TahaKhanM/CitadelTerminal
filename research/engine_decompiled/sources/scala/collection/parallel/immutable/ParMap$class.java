/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.parallel.immutable.ParHashMap;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParMap$;

public abstract class ParMap$class {
    public static GenericParMapCompanion mapCompanion(ParMap $this) {
        return ParMap$.MODULE$;
    }

    public static ParMap empty(ParMap $this) {
        return new ParHashMap();
    }

    public static String stringPrefix(ParMap $this) {
        return "ParMap";
    }

    public static ParMap toMap(ParMap $this, Predef$.less.colon.less ev) {
        return $this;
    }

    public static ParMap updated(ParMap $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static ParMap withDefault(ParMap $this, Function1 d) {
        return new ParMap.WithDefault($this, d);
    }

    public static ParMap withDefaultValue(ParMap $this, Object d) {
        return new ParMap.WithDefault($this, new Serializable($this, d){
            public static final long serialVersionUID = 0L;
            private final Object d$2;

            public final U apply(K x) {
                return (U)this.d$2;
            }
            {
                this.d$2 = d$2;
            }
        });
    }

    public static void $init$(ParMap $this) {
    }
}

