/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.collection.GenTraversable;
import scala.collection.IterableViewLike;
import scala.collection.Iterator;

public abstract class IterableViewLike$Appended$class {
    public static Iterator iterator(IterableViewLike.Appended $this) {
        return $this.scala$collection$IterableViewLike$Appended$$$outer().iterator().$plus$plus(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ IterableViewLike.Appended $outer;

            public final GenTraversable<B> apply() {
                return this.$outer.rest();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static void $init$(IterableViewLike.Appended $this) {
    }
}

