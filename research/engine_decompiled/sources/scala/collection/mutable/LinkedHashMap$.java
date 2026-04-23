/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.MutableMapFactory;
import scala.collection.mutable.LinkedHashMap;

public final class LinkedHashMap$
extends MutableMapFactory<LinkedHashMap>
implements Serializable {
    public static final LinkedHashMap$ MODULE$;

    static {
        new LinkedHashMap$();
    }

    public <A, B> CanBuildFrom<LinkedHashMap<?, ?>, Tuple2<A, B>, LinkedHashMap<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> LinkedHashMap<A, B> empty() {
        return new LinkedHashMap();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private LinkedHashMap$() {
        MODULE$ = this;
    }
}

