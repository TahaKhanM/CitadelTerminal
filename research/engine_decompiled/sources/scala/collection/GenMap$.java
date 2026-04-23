/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Tuple2;
import scala.collection.GenMap;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;

public final class GenMap$
extends GenMapFactory<GenMap> {
    public static final GenMap$ MODULE$;

    static {
        new GenMap$();
    }

    @Override
    public <A, B> Map<A, B> empty() {
        return Map$.MODULE$.empty();
    }

    public <A, B> CanBuildFrom<GenMap<?, ?>, Tuple2<A, B>, GenMap<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom(this);
    }

    private GenMap$() {
        MODULE$ = this;
    }
}

