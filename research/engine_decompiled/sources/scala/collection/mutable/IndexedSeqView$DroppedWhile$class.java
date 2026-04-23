/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.IndexedSeqView;
import scala.runtime.BoxesRunTime;

public abstract class IndexedSeqView$DroppedWhile$class {
    public static void update(IndexedSeqView.DroppedWhile $this, int idx, Object elem) {
        if (idx >= 0) {
            $this.scala$collection$mutable$IndexedSeqView$DroppedWhile$$$outer().update(idx + $this.start(), elem);
            return;
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void $init$(IndexedSeqView.DroppedWhile $this) {
    }
}

