/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.collection.Iterator;
import scala.collection.SeqViewLike;
import scala.runtime.BoxesRunTime;

public abstract class SeqViewLike$Sliced$class {
    public static int length(SeqViewLike.Sliced $this) {
        return $this.iterator().size();
    }

    public static Object apply(SeqViewLike.Sliced $this, int idx) {
        if (idx >= 0 && idx + $this.from() < $this.until()) {
            return $this.scala$collection$SeqViewLike$Sliced$$$outer().apply(idx + $this.from());
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
    }

    public static void foreach(SeqViewLike.Sliced $this, Function1 f) {
        $this.iterator().foreach(f);
    }

    public static Iterator iterator(SeqViewLike.Sliced $this) {
        return $this.scala$collection$SeqViewLike$Sliced$$$outer().iterator().drop($this.from()).take($this.endpoints().width());
    }

    public static void $init$(SeqViewLike.Sliced $this) {
    }
}

