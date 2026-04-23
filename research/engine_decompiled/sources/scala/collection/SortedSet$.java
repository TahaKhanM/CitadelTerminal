/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SortedSetFactory;
import scala.collection.immutable.SortedSet;
import scala.math.Ordering;

public final class SortedSet$
extends SortedSetFactory<scala.collection.SortedSet> {
    public static final SortedSet$ MODULE$;

    static {
        new SortedSet$();
    }

    @Override
    public <A> SortedSet<A> empty(Ordering<A> ord) {
        return scala.collection.immutable.SortedSet$.MODULE$.empty((Ordering)ord);
    }

    public <A> CanBuildFrom<scala.collection.SortedSet<?>, A, scala.collection.SortedSet<A>> canBuildFrom(Ordering<A> ord) {
        return this.newCanBuildFrom(ord);
    }

    @Override
    public <A> CanBuildFrom<scala.collection.SortedSet<?>, A, scala.collection.SortedSet<A>> newCanBuildFrom(Ordering<A> ord) {
        return super.newCanBuildFrom(ord);
    }

    private SortedSet$() {
        MODULE$ = this;
    }
}

