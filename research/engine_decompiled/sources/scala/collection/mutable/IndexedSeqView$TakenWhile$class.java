/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.IndexedSeqView;
import scala.runtime.BoxesRunTime;

public abstract class IndexedSeqView$TakenWhile$class {
    public static void update(IndexedSeqView.TakenWhile $this, int idx, Object elem) {
        if (idx < $this.len()) {
            $this.scala$collection$mutable$IndexedSeqView$TakenWhile$$$outer().update(idx, elem);
            return;
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void $init$(IndexedSeqView.TakenWhile $this) {
    }
}

