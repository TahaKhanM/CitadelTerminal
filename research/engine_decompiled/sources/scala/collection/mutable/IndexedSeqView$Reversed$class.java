/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.IndexedSeqView;

public abstract class IndexedSeqView$Reversed$class {
    public static void update(IndexedSeqView.Reversed $this, int idx, Object elem) {
        $this.scala$collection$mutable$IndexedSeqView$Reversed$$$outer().update($this.scala$collection$mutable$IndexedSeqView$Reversed$$$outer().length() - 1 - idx, elem);
    }

    public static void $init$(IndexedSeqView.Reversed $this) {
    }
}

