/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParHashMap;
import scala.collection.parallel.mutable.ParHashMapCombiner$;

public final class ParHashMap$
extends ParMapFactory<ParHashMap>
implements Serializable {
    public static final ParHashMap$ MODULE$;
    private int iters;

    static {
        new ParHashMap$();
    }

    public int iters() {
        return this.iters;
    }

    public void iters_$eq(int x$1) {
        this.iters = x$1;
    }

    @Override
    public <K, V> ParHashMap<K, V> empty() {
        return new ParHashMap();
    }

    @Override
    public <K, V> Combiner<Tuple2<K, V>, ParHashMap<K, V>> newCombiner() {
        return ParHashMapCombiner$.MODULE$.apply();
    }

    public <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
        return new ParMapFactory.CanCombineFromMap();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParHashMap$() {
        MODULE$ = this;
        this.iters = 0;
    }
}

