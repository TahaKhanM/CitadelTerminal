/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenTraversable;
import scala.collection.Traversable;
import scala.collection.Traversable$;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.mutable.Builder;

public final class GenTraversable$
extends GenTraversableFactory<GenTraversable> {
    public static final GenTraversable$ MODULE$;

    static {
        new GenTraversable$();
    }

    public <A> GenTraversableFactory.GenericCanBuildFrom<A> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Traversable<A>> newBuilder() {
        return Traversable$.MODULE$.newBuilder();
    }

    private GenTraversable$() {
        MODULE$ = this;
    }
}

