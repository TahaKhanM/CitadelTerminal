/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.SeqViewLike;

public abstract class SeqViewLike$Prepended$class {
    public static Iterator iterator(SeqViewLike.Prepended $this) {
        return Iterator$.MODULE$.single($this.fst()).$plus$plus(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SeqViewLike.Prepended $outer;

            public final Iterator<A> apply() {
                return this.$outer.scala$collection$SeqViewLike$Prepended$$$outer().iterator();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static int length(SeqViewLike.Prepended $this) {
        return 1 + $this.scala$collection$SeqViewLike$Prepended$$$outer().length();
    }

    public static Object apply(SeqViewLike.Prepended $this, int idx) {
        return idx == 0 ? $this.fst() : $this.scala$collection$SeqViewLike$Prepended$$$outer().apply(idx - 1);
    }

    public static final String viewIdentifier(SeqViewLike.Prepended $this) {
        return "A";
    }

    public static void $init$(SeqViewLike.Prepended $this) {
    }
}

