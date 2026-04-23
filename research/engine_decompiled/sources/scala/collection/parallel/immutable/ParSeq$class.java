/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.parallel.immutable.ParSeq;
import scala.collection.parallel.immutable.ParSeq$;

public abstract class ParSeq$class {
    public static GenericCompanion companion(ParSeq $this) {
        return ParSeq$.MODULE$;
    }

    public static ParSeq toSeq(ParSeq $this) {
        return $this;
    }

    public static void $init$(ParSeq $this) {
    }
}

