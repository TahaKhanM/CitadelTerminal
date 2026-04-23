/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenMapFactory;
import scala.collection.generic.MutableMapFactory;
import scala.collection.mutable.WeakHashMap;

public final class WeakHashMap$
extends MutableMapFactory<WeakHashMap>
implements Serializable {
    public static final WeakHashMap$ MODULE$;

    static {
        new WeakHashMap$();
    }

    public <A, B> CanBuildFrom<WeakHashMap<?, ?>, Tuple2<A, B>, WeakHashMap<A, B>> canBuildFrom() {
        return new GenMapFactory.MapCanBuildFrom();
    }

    @Override
    public <A, B> WeakHashMap<A, B> empty() {
        return new WeakHashMap();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private WeakHashMap$() {
        MODULE$ = this;
    }
}

