/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ImmutableSortedMapFactory;
import scala.collection.generic.SortedMapFactory;
import scala.collection.immutable.TreeMap;
import scala.math.Ordering;

public final class TreeMap$
extends ImmutableSortedMapFactory<TreeMap>
implements Serializable {
    public static final TreeMap$ MODULE$;

    static {
        new TreeMap$();
    }

    @Override
    public <A, B> TreeMap<A, B> empty(Ordering<A> ord) {
        return new TreeMap(ord);
    }

    public <A, B> CanBuildFrom<TreeMap<?, ?>, Tuple2<A, B>, TreeMap<A, B>> canBuildFrom(Ordering<A> ord) {
        return new SortedMapFactory.SortedMapCanBuildFrom(this, ord);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private TreeMap$() {
        MODULE$ = this;
    }
}

