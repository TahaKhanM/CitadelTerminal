/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.parallel.mutable.ParSet;
import scala.collection.parallel.mutable.ParSetLike;

public abstract class ParSetLike$class {
    public static ParSet $plus(ParSetLike $this, Object elem) {
        return (ParSet)((ParSetLike)$this.clone()).$plus$eq(elem);
    }

    public static ParSet $minus(ParSetLike $this, Object elem) {
        return (ParSet)((ParSetLike)$this.clone()).$minus$eq(elem);
    }

    public static void $init$(ParSetLike $this) {
    }
}

