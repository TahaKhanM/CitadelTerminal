/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.GenericParMapTemplate;
import scala.collection.parallel.Combiner;

public abstract class GenericParMapTemplate$class {
    /*
     * WARNING - void declaration
     */
    public static Combiner newCombiner(GenericParMapTemplate $this) {
        void var1_1;
        Combiner cb = $this.mapCompanion().newCombiner();
        return var1_1;
    }

    /*
     * WARNING - void declaration
     */
    public static Combiner genericMapCombiner(GenericParMapTemplate $this) {
        void var1_1;
        Combiner cb = $this.mapCompanion().newCombiner();
        return var1_1;
    }

    public static void $init$(GenericParMapTemplate $this) {
    }
}

