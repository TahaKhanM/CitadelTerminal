/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Tuple2;
import scala.collection.Map;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.MapFactory;

public final class Map$
extends MapFactory<Map> {
    public static final Map$ MODULE$;

    static {
        new Map$();
    }

    @Override
    public <A, B> scala.collection.immutable.Map<A, B> empty() {
        return scala.collection.immutable.Map$.MODULE$.empty();
    }

    public <A, B> CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom(this);
    }

    private Map$() {
        MODULE$ = this;
    }
}

