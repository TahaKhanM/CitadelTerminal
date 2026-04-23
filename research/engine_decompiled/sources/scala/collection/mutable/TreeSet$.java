/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.MutableSortedSetFactory;
import scala.collection.mutable.TreeSet;
import scala.math.Ordering;

public final class TreeSet$
extends MutableSortedSetFactory<TreeSet>
implements Serializable {
    public static final TreeSet$ MODULE$;

    static {
        new TreeSet$();
    }

    @Override
    public <A> TreeSet<A> empty(Ordering<A> ordering) {
        return new TreeSet<A>(ordering);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private TreeSet$() {
        MODULE$ = this;
    }
}

