/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.ImmutableMapFactory;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListMap$EmptyListMap$;

public final class ListMap$
extends ImmutableMapFactory<ListMap>
implements Serializable {
    public static final ListMap$ MODULE$;

    static {
        new ListMap$();
    }

    public <A, B> CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> ListMap<A, B> empty() {
        return ListMap$EmptyListMap$.MODULE$;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ListMap$() {
        MODULE$ = this;
    }
}

