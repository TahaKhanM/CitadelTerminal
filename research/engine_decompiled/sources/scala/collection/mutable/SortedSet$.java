/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.MutableSortedSetFactory;
import scala.collection.generic.SortedSetFactory;
import scala.collection.mutable.SortedSet;
import scala.collection.mutable.TreeSet$;
import scala.math.Ordering;

public final class SortedSet$
extends MutableSortedSetFactory<SortedSet> {
    public static final SortedSet$ MODULE$;

    static {
        new SortedSet$();
    }

    public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> canBuildFrom(Ordering<A> ord) {
        return new SortedSetFactory.SortedSetCanBuildFrom<A>(this, ord);
    }

    @Override
    public <A> SortedSet<A> empty(Ordering<A> ord) {
        return TreeSet$.MODULE$.empty((Ordering)ord);
    }

    private SortedSet$() {
        MODULE$ = this;
    }
}

