/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.ImmutableMapFactory;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$EmptyMap$;

public final class Map$
extends ImmutableMapFactory<Map> {
    public static final Map$ MODULE$;

    static {
        new Map$();
    }

    public <A, B> CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> Map<A, B> empty() {
        return Map$EmptyMap$.MODULE$;
    }

    private Map$() {
        MODULE$ = this;
    }
}

