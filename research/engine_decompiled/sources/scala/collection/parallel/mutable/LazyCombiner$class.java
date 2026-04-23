/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Serializable;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Sizing;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.LazyCombiner;
import scala.runtime.BoxesRunTime;

public abstract class LazyCombiner$class {
    public static LazyCombiner $plus$eq(LazyCombiner $this, Object elem) {
        $this.lastbuff().$plus$eq((Object)elem);
        return $this;
    }

    public static Object result(LazyCombiner $this) {
        return $this.allocateAndCopy();
    }

    public static void clear(LazyCombiner $this) {
        $this.chain().clear();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Combiner combine(LazyCombiner $this, Combiner other) {
        LazyCombiner lazyCombiner;
        if ($this != other) {
            if (!(other instanceof LazyCombiner)) throw new UnsupportedOperationException("Cannot combine with combiner of different type.");
            LazyCombiner that = (LazyCombiner)other;
            lazyCombiner = $this.newLazyCombiner($this.chain().$plus$plus$eq((TraversableOnce)that.chain()));
            return lazyCombiner;
        } else {
            lazyCombiner = $this;
        }
        return lazyCombiner;
    }

    public static int size(LazyCombiner $this) {
        return BoxesRunTime.unboxToInt($this.chain().foldLeft(BoxesRunTime.boxToInteger(0), new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(int x$1, Buff x$2) {
                return x$1 + ((Sizing)x$2).size();
            }
        }));
    }

    public static void $init$(LazyCombiner $this) {
        $this.scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq((Growable)$this.chain().last());
    }
}

