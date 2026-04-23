/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenIterable;
import scala.collection.Iterable;
import scala.collection.Iterable$;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.mutable.Builder;

public final class GenIterable$
extends GenTraversableFactory<GenIterable> {
    public static final GenIterable$ MODULE$;

    static {
        new GenIterable$();
    }

    public <A> GenTraversableFactory.GenericCanBuildFrom<A> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Iterable<A>> newBuilder() {
        return Iterable$.MODULE$.newBuilder();
    }

    private GenIterable$() {
        MODULE$ = this;
    }
}

