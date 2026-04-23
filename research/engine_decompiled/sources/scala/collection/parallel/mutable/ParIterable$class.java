/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Serializable;
import scala.collection.generic.GenericCompanion;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.ParIterable$;
import scala.collection.parallel.mutable.ParSeq;
import scala.collection.parallel.mutable.ParSeq$;

public abstract class ParIterable$class {
    public static GenericCompanion companion(ParIterable $this) {
        return ParIterable$.MODULE$;
    }

    public static ParIterable toIterable(ParIterable $this) {
        return $this;
    }

    public static ParSeq toSeq(ParIterable $this) {
        return (ParSeq)$this.toParCollection(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Combiner<T, ParSeq<T>> apply() {
                return ParSeq$.MODULE$.newCombiner();
            }
        });
    }

    public static void $init$(ParIterable $this) {
    }
}

