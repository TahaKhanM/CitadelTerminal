/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Seq$;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSeq$;

public abstract class Seq$class {
    public static GenericCompanion companion(Seq $this) {
        return Seq$.MODULE$;
    }

    public static Seq toSeq(Seq $this) {
        return $this;
    }

    public static Seq seq(Seq $this) {
        return $this;
    }

    public static Combiner parCombiner(Seq $this) {
        return ParSeq$.MODULE$.newCombiner();
    }

    public static void $init$(Seq $this) {
    }
}

