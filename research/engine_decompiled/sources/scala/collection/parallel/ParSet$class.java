/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Nil$;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSet$;
import scala.collection.parallel.mutable.ParHashSet$;

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

    public static void $init$(ParSet $this) {
    }
}

