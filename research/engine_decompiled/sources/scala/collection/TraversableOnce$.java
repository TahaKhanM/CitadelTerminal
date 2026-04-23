/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.collection.TraversableOnce;

public final class TraversableOnce$ {
    public static final TraversableOnce$ MODULE$;

    static {
        new TraversableOnce$();
    }

    public <A> TraversableOnce.ForceImplicitAmbiguity alternateImplicit(TraversableOnce<A> trav) {
        return new TraversableOnce.ForceImplicitAmbiguity();
    }

    public <A, CC> TraversableOnce.FlattenOps<A> flattenTraversableOnce(TraversableOnce<CC> travs, Function1<CC, TraversableOnce<A>> ev) {
        return new TraversableOnce.FlattenOps<A>(this.MonadOps(travs).map(ev));
    }

    public <A> TraversableOnce.OnceCanBuildFrom<A> OnceCanBuildFrom() {
        return new TraversableOnce.OnceCanBuildFrom();
    }

    public <A> TraversableOnce.MonadOps<A> MonadOps(TraversableOnce<A> trav) {
        return new TraversableOnce.MonadOps<A>(trav);
    }

    private TraversableOnce$() {
        MODULE$ = this;
    }
}

