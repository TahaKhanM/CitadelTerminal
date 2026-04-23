/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Nil$;
import scala.collection.parallel.immutable.ParHashSet$;
import scala.collection.parallel.immutable.ParSet;
import scala.collection.parallel.immutable.ParSet$;

public abstract class ParSet$class {
    public static ParSet empty(ParSet $this) {
        return (ParSet)ParHashSet$.MODULE$.apply(Nil$.MODULE$);
    }

    public static GenericCompanion companion(ParSet $this) {
        return ParSet$.MODULE$;
    }

    public static String stringPrefix(ParSet $this) {
        return "ParSet";
    }

    public static ParSet toSet(ParSet $this) {
        return $this;
    }

    public static void $init$(ParSet $this) {
    }
}

