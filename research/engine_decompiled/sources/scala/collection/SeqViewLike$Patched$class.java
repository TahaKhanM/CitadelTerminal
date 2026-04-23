/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Iterator;
import scala.collection.SeqViewLike;
import scala.math.package$;

public abstract class SeqViewLike$Patched$class {
    public static int scala$collection$SeqViewLike$Patched$$plen(SeqViewLike.Patched $this) {
        return $this.patch().length();
    }

    public static Iterator iterator(SeqViewLike.Patched $this) {
        return $this.scala$collection$SeqViewLike$Patched$$$outer().iterator().patch($this.from(), $this.patch().iterator(), $this.replaced());
    }

    public static int length(SeqViewLike.Patched $this) {
        int len = $this.scala$collection$SeqViewLike$Patched$$$outer().length();
        int pre = package$.MODULE$.min($this.from(), len);
        int post = package$.MODULE$.max(0, len - pre - $this.replaced());
        return pre + $this.scala$collection$SeqViewLike$Patched$$plen() + post;
    }

    public static Object apply(SeqViewLike.Patched $this, int idx) {
        int actualFrom;
        int n = actualFrom = $this.scala$collection$SeqViewLike$Patched$$$outer().lengthCompare($this.from()) < 0 ? $this.scala$collection$SeqViewLike$Patched$$$outer().length() : $this.from();
        return idx < actualFrom ? $this.scala$collection$SeqViewLike$Patched$$$outer().apply(idx) : (idx < actualFrom + $this.scala$collection$SeqViewLike$Patched$$plen() ? $this.patch().apply(idx - actualFrom) : $this.scala$collection$SeqViewLike$Patched$$$outer().apply(idx - $this.scala$collection$SeqViewLike$Patched$$plen() + $this.replaced()));
    }

    public static final String viewIdentifier(SeqViewLike.Patched $this) {
        return "P";
    }

    public static void $init$(SeqViewLike.Patched $this) {
    }
}

