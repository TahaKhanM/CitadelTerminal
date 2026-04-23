/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParSetFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.HashSetCombiner$;
import scala.collection.parallel.immutable.ParSet;

public final class ParSet$
extends ParSetFactory<ParSet> {
    public static final ParSet$ MODULE$;

    static {
        new ParSet$();
    }

    @Override
    public <T> Combiner<T, ParSet<T>> newCombiner() {
        return HashSetCombiner$.MODULE$.apply();
    }

    public <T> CanCombineFrom<ParSet<?>, T, ParSet<T>> canBuildFrom() {
        return new ParSetFactory.GenericCanCombineFrom();
    }

    private ParSet$() {
        MODULE$ = this;
    }
}

