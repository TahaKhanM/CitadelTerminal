/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.collection.Parallel;
import scala.collection.Parallelizable;
import scala.collection.parallel.Combiner;

public abstract class Parallelizable$class {
    public static Parallel par(Parallelizable $this) {
        Combiner cb = $this.parCombiner();
        $this.seq().foreach(new Serializable($this, cb){
            public static final long serialVersionUID = 0L;
            private final Combiner cb$1;

            public final Combiner<A, ParRepr> apply(A x) {
                return (Combiner)this.cb$1.$plus$eq(x);
            }
            {
                this.cb$1 = cb$1;
            }
        });
        return (Parallel)cb.result();
    }

    public static void $init$(Parallelizable $this) {
    }
}

