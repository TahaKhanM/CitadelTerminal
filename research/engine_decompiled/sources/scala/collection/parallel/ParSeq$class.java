/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.generic.GenericCompanion;
import scala.collection.parallel.ParIterableLike$class;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeq$;

public abstract class ParSeq$class {
    public static GenericCompanion companion(ParSeq $this) {
        return ParSeq$.MODULE$;
    }

    public static String toString(ParSeq $this) {
        return ParIterableLike$class.toString($this);
    }

    public static String stringPrefix(ParSeq $this) {
        return $this.getClass().getSimpleName();
    }

    public static void $init$(ParSeq $this) {
    }
}

