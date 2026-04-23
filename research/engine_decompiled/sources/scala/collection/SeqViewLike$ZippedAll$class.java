/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Predef$;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.SeqViewLike;
import scala.runtime.RichInt$;

public abstract class SeqViewLike$ZippedAll$class {
    public static Seq thatSeq(SeqViewLike.ZippedAll $this) {
        return $this.other().seq().toSeq();
    }

    public static int length(SeqViewLike.ZippedAll $this) {
        int n = $this.scala$collection$SeqViewLike$ZippedAll$$$outer().length();
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.max$extension(n, $this.thatSeq().length());
    }

    public static Tuple2 apply(SeqViewLike.ZippedAll $this, int idx) {
        return new Tuple2(idx < $this.scala$collection$SeqViewLike$ZippedAll$$$outer().length() ? $this.scala$collection$SeqViewLike$ZippedAll$$$outer().apply(idx) : $this.thisElem(), idx < $this.thatSeq().length() ? $this.thatSeq().apply(idx) : $this.thatElem());
    }

    public static void $init$(SeqViewLike.ZippedAll $this) {
    }
}

