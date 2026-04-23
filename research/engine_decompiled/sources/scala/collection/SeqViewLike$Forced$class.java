/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SeqViewLike;

public abstract class SeqViewLike$Forced$class {
    public static int length(SeqViewLike.Forced $this) {
        return $this.forced().length();
    }

    public static Object apply(SeqViewLike.Forced $this, int idx) {
        return $this.forced().apply(idx);
    }

    public static void $init$(SeqViewLike.Forced $this) {
    }
}

