/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.MutableMapFactory;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;

public final class Map$
extends MutableMapFactory<Map> {
    public static final Map$ MODULE$;

    static {
        new Map$();
    }

    public <A, B> CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> Map<A, B> empty() {
        return new HashMap();
    }

    private Map$() {
        MODULE$ = this;
    }
}

