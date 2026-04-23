/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Iterable;
import scala.collection.Iterable$;
import scala.collection.generic.GenericCompanion;

public abstract class Iterable$class {
    public static GenericCompanion companion(Iterable $this) {
        return Iterable$.MODULE$;
    }

    public static Iterable seq(Iterable $this) {
        return $this;
    }

    public static void $init$(Iterable $this) {
    }
}

