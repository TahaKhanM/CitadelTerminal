/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Function1;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParHashMap;
import scala.collection.parallel.mutable.ParMap;
import scala.collection.parallel.mutable.ParMap$;

public abstract class ParMap$class {
    public static Combiner newCombiner(ParMap $this) {
        return ParMap$.MODULE$.newCombiner();
    }

    public static GenericParMapCompanion mapCompanion(ParMap $this) {
        return ParMap$.MODULE$;
    }

    public static ParMap empty(ParMap $this) {
        return new ParHashMap();
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

            public final V apply(K x) {
                return (V)this.d$2;
            }
            {
                this.d$2 = d$2;
            }
        });
    }

    public static void $init$(ParMap $this) {
    }
}

