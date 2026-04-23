/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.immutable.HashSet;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.HashSetCombiner$;
import scala.collection.parallel.immutable.ParHashSet;

public final class ParHashSet$
extends ParSetFactory<ParHashSet>
implements Serializable {
    public static final ParHashSet$ MODULE$;

    static {
        new ParHashSet$();
    }

    @Override
    public <T> Combiner<T, ParHashSet<T>> newCombiner() {
        return HashSetCombiner$.MODULE$.apply();
    }

    public <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
        return new ParSetFactory.GenericCanCombineFrom();
    }

    public <T> ParHashSet<T> fromTrie(HashSet<T> t) {
        return new ParHashSet<T>(t);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParHashSet$() {
        MODULE$ = this;
    }
}

