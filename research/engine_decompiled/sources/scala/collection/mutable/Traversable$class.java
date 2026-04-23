/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.Traversable;
import scala.collection.mutable.Traversable$;

public abstract class Traversable$class {
    public static GenericCompanion companion(Traversable $this) {
        return Traversable$.MODULE$;
    }

    public static Traversable seq(Traversable $this) {
        return $this;
    }

    public static void $init$(Traversable $this) {
    }
}

