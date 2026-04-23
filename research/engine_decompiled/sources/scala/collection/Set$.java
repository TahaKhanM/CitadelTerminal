/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Set;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SetFactory;
import scala.collection.mutable.Builder;

public final class Set$
extends SetFactory<Set> {
    public static final Set$ MODULE$;

    static {
        new Set$();
    }

    @Override
    public <A> Builder<A, scala.collection.immutable.Set<A>> newBuilder() {
        return scala.collection.immutable.Set$.MODULE$.newBuilder();
    }

    @Override
    public <A> Set<A> empty() {
        return scala.collection.immutable.Set$.MODULE$.empty();
    }

    public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    private Set$() {
        MODULE$ = this;
    }
}

