/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$FlatMapped$class {
    public static Iterator iterator(IterableViewLike.FlatMapped $this) {
        return $this.scala$collection$IterableViewLike$FlatMapped$$$outer().iterator().flatMap($this.mapping());
    }

    public static void $init$(IterableViewLike.FlatMapped $this) {
    }
}

