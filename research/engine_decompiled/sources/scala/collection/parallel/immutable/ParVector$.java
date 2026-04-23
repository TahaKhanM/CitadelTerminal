/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.immutable;

import scala.Serializable;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.LazyParVectorCombiner;
import scala.collection.parallel.immutable.ParVector;

public final class ParVector$
extends ParFactory<ParVector>
implements Serializable {
    public static final ParVector$ MODULE$;

    static {
        new ParVector$();
    }

    public <T> CanCombineFrom<ParVector<?>, T, ParVector<T>> canBuildFrom() {
        return new ParFactory.GenericCanCombineFrom(this);
    }

    @Override
    public <T> Combiner<T, ParVector<T>> newBuilder() {
        return this.newCombiner();
    }

    @Override
    public <T> Combiner<T, ParVector<T>> newCombiner() {
        return new LazyParVectorCombiner();
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ParVector$() {
        MODULE$ = this;
    }
}

