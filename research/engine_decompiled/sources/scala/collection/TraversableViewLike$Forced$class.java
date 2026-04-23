/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.collection.TraversableViewLike;

public abstract class TraversableViewLike$Forced$class {
    public static void foreach(TraversableViewLike.Forced $this, Function1 f) {
        $this.forced().foreach(f);
    }

    public static final String viewIdentifier(TraversableViewLike.Forced $this) {
        return "C";
    }

    public static void $init$(TraversableViewLike.Forced $this) {
    }
}

