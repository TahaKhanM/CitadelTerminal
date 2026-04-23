/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Forced$class {
    public static Iterator iterator(IterableViewLike.Forced $this) {
        return $this.forced().iterator();
    }

    public static void $init$(IterableViewLike.Forced $this) {
    }
}

