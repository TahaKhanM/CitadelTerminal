/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenSeq;
import scala.collection.SeqViewLike;

public abstract class SeqViewLike$Appended$class {
    public static GenSeq restSeq(SeqViewLike.Appended $this) {
        return $this.rest().toSeq();
    }

    public static int length(SeqViewLike.Appended $this) {
        return $this.scala$collection$SeqViewLike$Appended$$$outer().length() + $this.restSeq().length();
    }

    public static Object apply(SeqViewLike.Appended $this, int idx) {
        return idx < $this.scala$collection$SeqViewLike$Appended$$$outer().length() ? $this.scala$collection$SeqViewLike$Appended$$$outer().apply(idx) : $this.restSeq().apply(idx - $this.scala$collection$SeqViewLike$Appended$$$outer().length());
    }

    public static void $init$(SeqViewLike.Appended $this) {
    }
}

