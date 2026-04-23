/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Tuple2;
import scala.collection.SortedMap;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SortedMapFactory;
import scala.math.Ordering;

public final class SortedMap$
extends SortedMapFactory<SortedMap> {
    public static final SortedMap$ MODULE$;

    static {
        new SortedMap$();
    }

    @Override
    public <A, B> SortedMap<A, B> empty(Ordering<A> ord) {
        return scala.collection.immutable.SortedMap$.MODULE$.empty((Ordering)ord);
    }

    public <A, B> CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>> canBuildFrom(Ordering<A> ord) {
        return new SortedMapFactory.SortedMapCanBuildFrom(this, ord);
    }

    private SortedMap$() {
        MODULE$ = this;
    }
}

