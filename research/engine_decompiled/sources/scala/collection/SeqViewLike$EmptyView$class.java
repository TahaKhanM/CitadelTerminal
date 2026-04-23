/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.SeqViewLike;
import scala.collection.immutable.Nil$;
import scala.runtime.Nothing$;

public abstract class SeqViewLike$EmptyView$class {
    public static final int length(SeqViewLike.EmptyView $this) {
        return 0;
    }

    public static final Nothing$ apply(SeqViewLike.EmptyView $this, int n) {
        return (Nothing$)Nil$.MODULE$.apply(n);
    }

    public static void $init$(SeqViewLike.EmptyView $this) {
    }
}

