/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Mapped$class {
    public static Iterator iterator(IterableViewLike.Mapped $this) {
        return $this.scala$collection$IterableViewLike$Mapped$$$outer().iterator().map($this.mapping());
    }

    public static void $init$(IterableViewLike.Mapped $this) {
    }
}

