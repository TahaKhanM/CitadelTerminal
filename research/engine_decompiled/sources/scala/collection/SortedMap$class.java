/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SortedMap;
import scala.collection.immutable.SortedMap$;
import scala.collection.mutable.Builder;

public abstract class SortedMap$class {
    public static SortedMap empty(SortedMap $this) {
        return scala.collection.SortedMap$.MODULE$.empty($this.ordering());
    }

    public static Builder newBuilder(SortedMap $this) {
        return SortedMap$.MODULE$.newBuilder($this.ordering());
    }

    public static void $init$(SortedMap $this) {
    }
}

