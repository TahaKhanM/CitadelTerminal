/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.collection.TraversableViewLike;

public abstract class TraversableViewLike$Appended$class {
    public static void foreach(TraversableViewLike.Appended $this, Function1 f) {
        $this.scala$collection$TraversableViewLike$Appended$$$outer().foreach(f);
        $this.rest().foreach(f);
    }

    public static final String viewIdentifier(TraversableViewLike.Appended $this) {
        return "A";
    }

    public static void $init$(TraversableViewLike.Appended $this) {
    }
}

