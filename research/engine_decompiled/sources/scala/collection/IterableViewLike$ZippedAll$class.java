/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$ZippedAll$class {
    public static final String viewIdentifier(IterableViewLike.ZippedAll $this) {
        return "Z";
    }

    public static Iterator iterator(IterableViewLike.ZippedAll $this) {
        return $this.scala$collection$IterableViewLike$ZippedAll$$$outer().iterator().zipAll($this.other().iterator(), $this.thisElem(), $this.thatElem());
    }

    public static void $init$(IterableViewLike.ZippedAll $this) {
    }
}

