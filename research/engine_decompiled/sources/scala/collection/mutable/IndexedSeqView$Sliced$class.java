/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.IndexedSeqView;
import scala.runtime.BoxesRunTime;

public abstract class IndexedSeqView$Sliced$class {
    public static int length(IndexedSeqView.Sliced $this) {
        return $this.endpoints().width();
    }

    public static void update(IndexedSeqView.Sliced $this, int idx, Object elem) {
        if (idx >= 0 && idx + $this.from() < $this.until()) {
            $this.scala$collection$mutable$IndexedSeqView$Sliced$$$outer().update(idx + $this.from(), elem);
            return;
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void $init$(IndexedSeqView.Sliced $this) {
    }
}

