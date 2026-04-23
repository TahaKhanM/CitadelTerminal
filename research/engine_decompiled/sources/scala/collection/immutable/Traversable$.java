/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.immutable.Traversable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;

public final class Traversable$
extends GenTraversableFactory<Traversable>
implements TraversableFactory<Traversable> {
    public static final Traversable$ MODULE$;

    static {
        new Traversable$();
    }

    public <A> CanBuildFrom<Traversable<?>, A, Traversable<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Traversable<A>> newBuilder() {
        return new ListBuffer();
    }

    private Traversable$() {
        MODULE$ = this;
    }
}

