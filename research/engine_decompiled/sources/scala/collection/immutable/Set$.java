/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ImmutableSetFactory;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$EmptySet$;

public final class Set$
extends ImmutableSetFactory<Set> {
    public static final Set$ MODULE$;

    static {
        new Set$();
    }

    public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    @Override
    public Set<Object> emptyInstance() {
        return Set$EmptySet$.MODULE$;
    }

    private Set$() {
        MODULE$ = this;
    }
}

