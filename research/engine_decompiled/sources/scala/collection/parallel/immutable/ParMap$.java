/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Tuple2;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.HashMapCombiner$;
import scala.collection.parallel.immutable.ParHashMap;
import scala.collection.parallel.immutable.ParMap;

public final class ParMap$
extends ParMapFactory<ParMap> {
    public static final ParMap$ MODULE$;

    static {
        new ParMap$();
    }

    @Override
    public <K, V> ParMap<K, V> empty() {
        return new ParHashMap();
    }

    @Override
    public <K, V> Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
        return HashMapCombiner$.MODULE$.apply();
    }

    public <K, V> CanCombineFrom<ParMap<?, ?>, Tuple2<K, V>, ParMap<K, V>> canBuildFrom() {
        return new ParMapFactory.CanCombineFromMap();
    }

    private ParMap$() {
        MODULE$ = this;
    }
}

