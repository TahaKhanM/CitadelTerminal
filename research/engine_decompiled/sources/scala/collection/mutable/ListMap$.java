/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.MutableMapFactory;
import scala.collection.mutable.ListMap;

public final class ListMap$
extends MutableMapFactory<ListMap>
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
        return new ListMap();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ListMap$() {
        MODULE$ = this;
    }
}

