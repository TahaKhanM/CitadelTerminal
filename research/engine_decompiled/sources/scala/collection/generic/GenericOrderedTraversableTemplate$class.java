/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.GenericOrderedTraversableTemplate;
import scala.collection.mutable.Builder;
import scala.math.Ordering;

public abstract class GenericOrderedTraversableTemplate$class {
    public static Builder genericOrderedBuilder(GenericOrderedTraversableTemplate $this, Ordering ord) {
        return $this.orderedCompanion().newBuilder(ord);
    }

    public static void $init$(GenericOrderedTraversableTemplate $this) {
    }
}

