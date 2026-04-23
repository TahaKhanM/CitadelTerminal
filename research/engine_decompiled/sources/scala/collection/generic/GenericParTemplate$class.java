/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;

public abstract class GenericParTemplate$class {
    public static Builder newBuilder(GenericParTemplate $this) {
        return $this.newCombiner();
    }

    /*
     * WARNING - void declaration
     */
    public static Combiner newCombiner(GenericParTemplate $this) {
        void var1_1;
        Combiner cb = ((GenericParCompanion)((Object)$this.companion())).newCombiner();
        return var1_1;
    }

    public static Combiner genericBuilder(GenericParTemplate $this) {
        return $this.genericCombiner();
    }

    /*
     * WARNING - void declaration
     */
    public static Combiner genericCombiner(GenericParTemplate $this) {
        void var1_1;
        Combiner cb = ((GenericParCompanion)((Object)$this.companion())).newCombiner();
        return var1_1;
    }

    public static void $init$(GenericParTemplate $this) {
    }
}

