/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SeqViewLike;
import scala.runtime.BoxesRunTime;

public abstract class SeqViewLike$TakenWhile$class {
    public static int len(SeqViewLike.TakenWhile $this) {
        return $this.scala$collection$SeqViewLike$TakenWhile$$$outer().prefixLength($this.pred());
    }

    public static int length(SeqViewLike.TakenWhile $this) {
        return $this.len();
    }

    public static Object apply(SeqViewLike.TakenWhile $this, int idx) {
        if (idx < $this.len()) {
            return $this.scala$collection$SeqViewLike$TakenWhile$$$outer().apply(idx);
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void $init$(SeqViewLike.TakenWhile $this) {
    }
}

