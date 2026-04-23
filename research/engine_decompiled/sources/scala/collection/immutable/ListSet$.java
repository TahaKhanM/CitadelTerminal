/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.ImmutableSetFactory;
import scala.collection.immutable.ListSet;
import scala.collection.immutable.ListSet$EmptyListSet$;
import scala.collection.mutable.Builder;

public final class ListSet$
extends ImmutableSetFactory<ListSet>
implements Serializable {
    public static final ListSet$ MODULE$;

    static {
        new ListSet$();
    }

    public <A> CanBuildFrom<ListSet<?>, A, ListSet<A>> canBuildFrom() {
        return this.setCanBuildFrom();
    }

    @Override
    public <A> Builder<A, ListSet<A>> newBuilder() {
        return new ListSet.ListSetBuilder();
    }

    @Override
    public ListSet<Object> emptyInstance() {
        return ListSet$EmptyListSet$.MODULE$;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ListSet$() {
        MODULE$ = this;
    }
}

