/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParTrieMap;

public final class ParTrieMap$
extends ParMapFactory<ParTrieMap>
implements Serializable {
    public static final ParTrieMap$ MODULE$;

    static {
        new ParTrieMap$();
    }

    @Override
    public <K, V> ParTrieMap<K, V> empty() {
        return new ParTrieMap();
    }

    @Override
    public <K, V> Combiner<Tuple2<K, V>, ParTrieMap<K, V>> newCombiner() {
        return new ParTrieMap();
    }

    public <K, V> CanCombineFrom<ParTrieMap<?, ?>, Tuple2<K, V>, ParTrieMap<K, V>> canBuildFrom() {
        return new ParMapFactory.CanCombineFromMap();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParTrieMap$() {
        MODULE$ = this;
    }
}

