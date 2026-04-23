/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet$;

public abstract class Set$class {
    public static GenericCompanion companion(Set $this) {
        return Set$.MODULE$;
    }

    public static Set toSet(Set $this) {
        Builder sb = Set$.MODULE$.newBuilder();
        $this.foreach(new Serializable($this, sb){
            public static final long serialVersionUID = 0L;
            private final Builder sb$1;

            public final Builder<B, Set<B>> apply(A x$1) {
                return this.sb$1.$plus$eq(x$1);
            }
            {
                this.sb$1 = sb$1;
            }
        });
        return (Set)sb.result();
    }

    public static Set seq(Set $this) {
        return $this;
    }

    public static Combiner parCombiner(Set $this) {
        return ParSet$.MODULE$.newCombiner();
    }

    public static void $init$(Set $this) {
    }
}

