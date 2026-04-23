/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$DroppedWhile$class {
    public static Iterator iterator(IterableViewLike.DroppedWhile $this) {
        return $this.scala$collection$IterableViewLike$DroppedWhile$$$outer().iterator().dropWhile($this.pred());
    }

    public static void $init$(IterableViewLike.DroppedWhile $this) {
    }
}

