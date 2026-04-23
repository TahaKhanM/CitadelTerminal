/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.MutableSetFactory;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.Set;

public final class Set$
extends MutableSetFactory<Set> {
    public static final Set$ MODULE$;

    static {
        new Set$();
    }

    public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    @Override
    public <A> Set<A> empty() {
        return HashSet$.MODULE$.empty();
    }

    private Set$() {
        MODULE$ = this;
    }
}

