/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import scala.collection.convert.Wrappers;

public abstract class Wrappers$IterableWrapperTrait$class {
    public static int size(Wrappers.IterableWrapperTrait $this) {
        return $this.underlying().size();
    }

    public static Wrappers.IteratorWrapper iterator(Wrappers.IterableWrapperTrait $this) {
        return new Wrappers.IteratorWrapper($this.scala$collection$convert$Wrappers$IterableWrapperTrait$$$outer(), $this.underlying().iterator());
    }

    public static boolean isEmpty(Wrappers.IterableWrapperTrait $this) {
        return $this.underlying().isEmpty();
    }

    public static void $init$(Wrappers.IterableWrapperTrait $this) {
    }
}

