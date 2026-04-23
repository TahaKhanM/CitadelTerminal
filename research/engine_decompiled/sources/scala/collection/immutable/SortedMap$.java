/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ImmutableSortedMapFactory;
import scala.collection.generic.SortedMapFactory;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.TreeMap$;
import scala.math.Ordering;

public final class SortedMap$
extends ImmutableSortedMapFactory<SortedMap> {
    public static final SortedMap$ MODULE$;

    static {
        new SortedMap$();
    }

    public <A, B> CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>> canBuildFrom(Ordering<A> ord) {
        return new SortedMapFactory.SortedMapCanBuildFrom(this, ord);
    }

    @Override
    public <A, B> SortedMap<A, B> empty(Ordering<A> ord) {
        return TreeMap$.MODULE$.empty((Ordering)ord);
    }

    private SortedMap$() {
        MODULE$ = this;
    }
}

