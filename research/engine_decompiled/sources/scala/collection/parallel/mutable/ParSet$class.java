/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Nil$;
import scala.collection.parallel.mutable.ParHashSet$;
import scala.collection.parallel.mutable.ParSet;
import scala.collection.parallel.mutable.ParSet$;

public abstract class ParSet$class {
    public static GenericCompanion companion(ParSet $this) {
        return ParSet$.MODULE$;
    }

    public static ParSet empty(ParSet $this) {
        return (ParSet)ParHashSet$.MODULE$.apply(Nil$.MODULE$);
    }

    public static void $init$(ParSet $this) {
    }
}

