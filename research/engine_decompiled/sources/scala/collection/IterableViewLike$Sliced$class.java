/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Sliced$class {
    public static Iterator iterator(IterableViewLike.Sliced $this) {
        return $this.scala$collection$IterableViewLike$Sliced$$$outer().iterator().slice($this.from(), $this.until());
    }

    public static void $init$(IterableViewLike.Sliced $this) {
    }
}

