/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Zipped$class {
    public static Iterator iterator(IterableViewLike.Zipped $this) {
        return $this.scala$collection$IterableViewLike$Zipped$$$outer().iterator().zip($this.other().iterator());
    }

    public static final String viewIdentifier(IterableViewLike.Zipped $this) {
        return "Z";
    }

    public static void $init$(IterableViewLike.Zipped $this) {
    }
}

