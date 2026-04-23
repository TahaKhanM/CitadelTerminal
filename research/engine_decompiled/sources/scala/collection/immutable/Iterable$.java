/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.immutable.Iterable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;

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
        return new ListBuffer();
    }

    private Iterable$() {
        MODULE$ = this;
    }
}

