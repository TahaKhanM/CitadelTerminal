/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.Iterable;
import scala.collection.mutable.Iterable$;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParIterable$;

public abstract class Iterable$class {
    public static GenericCompanion companion(Iterable $this) {
        return Iterable$.MODULE$;
    }

    public static Combiner parCombiner(Iterable $this) {
        return ParIterable$.MODULE$.newCombiner();
    }

    public static Iterable seq(Iterable $this) {
        return $this;
    }

    public static void $init$(Iterable $this) {
    }
}

