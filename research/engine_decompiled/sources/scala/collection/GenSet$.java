/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.GenSet;
import scala.collection.Set$;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.immutable.Set;
import scala.collection.mutable.Builder;

public final class GenSet$
extends GenTraversableFactory<GenSet> {
    public static final GenSet$ MODULE$;

    static {
        new GenSet$();
    }

    public <A> GenTraversableFactory.GenericCanBuildFrom<A> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Set<A>> newBuilder() {
        return Set$.MODULE$.newBuilder();
    }

    private GenSet$() {
        MODULE$ = this;
    }
}

