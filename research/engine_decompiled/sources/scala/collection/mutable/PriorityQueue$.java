/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.OrderedTraversableFactory;
import scala.collection.mutable.PriorityQueue;
import scala.math.Ordering;

public final class PriorityQueue$
extends OrderedTraversableFactory<PriorityQueue>
implements Serializable {
    public static final PriorityQueue$ MODULE$;

    static {
        new PriorityQueue$();
    }

    public <A> PriorityQueue<A> newBuilder(Ordering<A> ord) {
        return new PriorityQueue<A>(ord);
    }

    public <A> CanBuildFrom<PriorityQueue<?>, A, PriorityQueue<A>> canBuildFrom(Ordering<A> ord) {
        return new OrderedTraversableFactory.GenericCanBuildFrom<A>(this, ord);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private PriorityQueue$() {
        MODULE$ = this;
    }
}

