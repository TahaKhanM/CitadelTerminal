/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParHashSet;
import scala.collection.parallel.mutable.ParHashSetCombiner$;

public final class ParHashSet$
extends ParSetFactory<ParHashSet>
implements Serializable {
    public static final ParHashSet$ MODULE$;

    static {
        new ParHashSet$();
    }

    public <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
        return new ParSetFactory.GenericCanCombineFrom();
    }

    @Override
    public <T> Combiner<T, ParHashSet<T>> newBuilder() {
        return this.newCombiner();
    }

    @Override
    public <T> Combiner<T, ParHashSet<T>> newCombiner() {
        return ParHashSetCombiner$.MODULE$.apply();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParHashSet$() {
        MODULE$ = this;
    }
}

