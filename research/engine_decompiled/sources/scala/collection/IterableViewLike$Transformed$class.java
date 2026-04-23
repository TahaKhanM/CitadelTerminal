/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.collection.IterableViewLike;

public abstract class IterableViewLike$Transformed$class {
    public static void foreach(IterableViewLike.Transformed $this, Function1 f) {
        $this.iterator().foreach(f);
    }

    public static String toString(IterableViewLike.Transformed $this) {
        return $this.viewToString();
    }

    public static boolean isEmpty(IterableViewLike.Transformed $this) {
        return !$this.iterator().hasNext();
    }

    public static void $init$(IterableViewLike.Transformed $this) {
    }
}

