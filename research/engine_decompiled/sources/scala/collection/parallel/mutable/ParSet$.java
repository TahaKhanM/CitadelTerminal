/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParHashSet$;
import scala.collection.parallel.mutable.ParSet;

public final class ParSet$
extends ParSetFactory<ParSet> {
    public static final ParSet$ MODULE$;

    static {
        new ParSet$();
    }

    public <T> CanCombineFrom<ParSet<?>, T, ParSet<T>> canBuildFrom() {
        return new ParSetFactory.GenericCanCombineFrom();
    }

    @Override
    public <T> Combiner<T, ParSet<T>> newBuilder() {
        return ParHashSet$.MODULE$.newBuilder();
    }

    @Override
    public <T> Combiner<T, ParSet<T>> newCombiner() {
        return ParHashSet$.MODULE$.newCombiner();
    }

    private ParSet$() {
        MODULE$ = this;
    }
}

