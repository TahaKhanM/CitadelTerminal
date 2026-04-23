/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Filtered$class {
    public static Iterator iterator(IterableViewLike.Filtered $this) {
        return $this.scala$collection$IterableViewLike$Filtered$$$outer().iterator().filter($this.pred());
    }

    public static void $init$(IterableViewLike.Filtered $this) {
    }
}

