/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$TakenWhile$class {
    public static Iterator iterator(IterableViewLike.TakenWhile $this) {
        return $this.scala$collection$IterableViewLike$TakenWhile$$$outer().iterator().takeWhile($this.pred());
    }

    public static void $init$(IterableViewLike.TakenWhile $this) {
    }
}

