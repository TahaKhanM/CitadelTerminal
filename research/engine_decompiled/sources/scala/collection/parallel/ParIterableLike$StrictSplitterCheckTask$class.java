/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.ParIterableLike;

public abstract class ParIterableLike$StrictSplitterCheckTask$class {
    public static boolean requiresStrictSplitters(ParIterableLike.StrictSplitterCheckTask $this) {
        return false;
    }

    public static void $init$(ParIterableLike.StrictSplitterCheckTask $this) {
        if ($this.requiresStrictSplitters() && !$this.scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer().isStrictSplitterCollection()) {
            throw new UnsupportedOperationException("This collection does not provide strict splitters.");
        }
    }
}

