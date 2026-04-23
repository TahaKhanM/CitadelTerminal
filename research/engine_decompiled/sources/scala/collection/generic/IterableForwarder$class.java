/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.GenIterable;
import scala.collection.Iterator;
import scala.collection.generic.IterableForwarder;

public abstract class IterableForwarder$class {
    public static Iterator iterator(IterableForwarder $this) {
        return $this.underlying().iterator();
    }

    public static boolean sameElements(IterableForwarder $this, GenIterable that) {
        return $this.underlying().sameElements(that);
    }

    public static void $init$(IterableForwarder $this) {
    }
}

