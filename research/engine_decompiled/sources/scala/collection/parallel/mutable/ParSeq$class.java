/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.generic.GenericCompanion;
import scala.collection.parallel.mutable.ParSeq;
import scala.collection.parallel.mutable.ParSeq$;

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

