/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SeqViewLike;

public abstract class SeqViewLike$Mapped$class {
    public static int length(SeqViewLike.Mapped $this) {
        return $this.scala$collection$SeqViewLike$Mapped$$$outer().length();
    }

    public static Object apply(SeqViewLike.Mapped $this, int idx) {
        return $this.mapping().apply($this.scala$collection$SeqViewLike$Mapped$$$outer().apply(idx));
    }

    public static void $init$(SeqViewLike.Mapped $this) {
    }
}

