/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParMapFactory;
import scala.collection.immutable.HashMap;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.HashMapCombiner$;
import scala.collection.parallel.immutable.ParHashMap;

public final class ParHashMap$
extends ParMapFactory<ParHashMap>
implements Serializable {
    public static final ParHashMap$ MODULE$;
    private AtomicInteger totalcombines;

    static {
        new ParHashMap$();
    }

    @Override
    public <K, V> ParHashMap<K, V> empty() {
        return new ParHashMap();
    }

    @Override
    public <K, V> Combiner<Tuple2<K, V>, ParHashMap<K, V>> newCombiner() {
        return HashMapCombiner$.MODULE$.apply();
    }

    public <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
        return new ParMapFactory.CanCombineFromMap();
    }

    public <K, V> ParHashMap<K, V> fromTrie(HashMap<K, V> t) {
        return new ParHashMap<K, V>(t);
    }

    public AtomicInteger totalcombines() {
        return this.totalcombines;
    }

    public void totalcombines_$eq(AtomicInteger x$1) {
        this.totalcombines = x$1;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParHashMap$() {
        MODULE$ = this;
        this.totalcombines = new AtomicInteger(0);
    }
}

