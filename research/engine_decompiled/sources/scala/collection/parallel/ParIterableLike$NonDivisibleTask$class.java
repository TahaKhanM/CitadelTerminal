/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.ParIterableLike;
import scala.runtime.Nothing$;

public abstract class ParIterableLike$NonDivisibleTask$class {
    public static boolean shouldSplitFurther(ParIterableLike.NonDivisibleTask $this) {
        return false;
    }

    public static Nothing$ split(ParIterableLike.NonDivisibleTask $this) {
        throw new UnsupportedOperationException("Does not split.");
    }

    public static void $init$(ParIterableLike.NonDivisibleTask $this) {
    }
}

