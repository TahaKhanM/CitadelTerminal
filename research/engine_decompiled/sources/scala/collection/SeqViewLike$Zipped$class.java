/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.SeqViewLike;

public abstract class SeqViewLike$Zipped$class {
    public static Seq thatSeq(SeqViewLike.Zipped $this) {
        return $this.other().seq().toSeq();
    }

    public static int length(SeqViewLike.Zipped $this) {
        return $this.thatSeq().lengthCompare($this.scala$collection$SeqViewLike$Zipped$$$outer().length()) <= 0 ? $this.thatSeq().length() : $this.scala$collection$SeqViewLike$Zipped$$$outer().length();
    }

    public static Tuple2 apply(SeqViewLike.Zipped $this, int idx) {
        return new Tuple2($this.scala$collection$SeqViewLike$Zipped$$$outer().apply(idx), $this.thatSeq().apply(idx));
    }

    public static void $init$(SeqViewLike.Zipped $this) {
    }
}

