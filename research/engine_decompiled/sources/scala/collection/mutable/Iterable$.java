/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.TraversableFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Iterable;

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
        return new ArrayBuffer();
    }

    private Iterable$() {
        MODULE$ = this;
    }
}

