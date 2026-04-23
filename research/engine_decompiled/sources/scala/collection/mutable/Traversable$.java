/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Traversable;

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
        return new ArrayBuffer();
    }

    private Traversable$() {
        MODULE$ = this;
    }
}

