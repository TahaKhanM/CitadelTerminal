/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParIterable;
import scala.collection.parallel.mutable.package$;

public final class ParIterable$
extends ParFactory<ParIterable> {
    public static final ParIterable$ MODULE$;

    static {
        new ParIterable$();
    }

    public <T> CanCombineFrom<ParIterable<?>, T, ParIterable<T>> canBuildFrom() {
        return new ParFactory.GenericCanCombineFrom(this);
    }

    @Override
    public <T> Combiner<T, ParIterable<T>> newBuilder() {
        return package$.MODULE$.ParArrayCombiner().apply();
    }

    @Override
    public <T> Combiner<T, ParIterable<T>> newCombiner() {
        return package$.MODULE$.ParArrayCombiner().apply();
    }

    private ParIterable$() {
        MODULE$ = this;
    }
}

