/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SeqViewLike;
import scala.runtime.BoxesRunTime;

public abstract class SeqViewLike$DroppedWhile$class {
    public static int start(SeqViewLike.DroppedWhile $this) {
        return $this.scala$collection$SeqViewLike$DroppedWhile$$$outer().prefixLength($this.pred());
    }

    public static int length(SeqViewLike.DroppedWhile $this) {
        return $this.scala$collection$SeqViewLike$DroppedWhile$$$outer().length() - $this.start();
    }

    public static Object apply(SeqViewLike.DroppedWhile $this, int idx) {
        if (idx >= 0) {
            return $this.scala$collection$SeqViewLike$DroppedWhile$$$outer().apply(idx + $this.start());
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void $init$(SeqViewLike.DroppedWhile $this) {
    }
}

