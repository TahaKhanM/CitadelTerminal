/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SortedSet;
import scala.collection.SortedSet$;

public abstract class SortedSet$class {
    public static SortedSet empty(SortedSet $this) {
        return SortedSet$.MODULE$.empty($this.ordering());
    }

    public static void $init$(SortedSet $this) {
    }
}

