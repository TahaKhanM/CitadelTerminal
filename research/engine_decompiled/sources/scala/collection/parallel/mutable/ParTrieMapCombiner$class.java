/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParTrieMapCombiner;

public abstract class ParTrieMapCombiner$class {
    public static Combiner combine(ParTrieMapCombiner $this, Combiner other) {
        if ($this == other) {
            return $this;
        }
        throw new UnsupportedOperationException("This shouldn't have been called in the first place.");
    }

    public static boolean canBeShared(ParTrieMapCombiner $this) {
        return true;
    }

    public static void $init$(ParTrieMapCombiner $this) {
    }
}

