/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Iterable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.mutable.Builder;

public final class Iterable$
extends GenTraversableFactory<Iterable>
implements TraversableFactory<Iterable> {
    public static final Iterable$ MODULE$;

    static {
        new Iterable$();
    }

    public <A> CanBuildFrom<Iterable<?>, A, Iterable<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Iterable<A>> newBuilder() {
        return scala.collection.immutable.Iterable$.MODULE$.newBuilder();
    }

    private Iterable$() {
        MODULE$ = this;
    }
}

