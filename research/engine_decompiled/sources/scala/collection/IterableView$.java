/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Traversable;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;

public final class IterableView$ {
    public static final IterableView$ MODULE$;

    static {
        new IterableView$();
    }

    public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, IterableView<A, Iterable<?>>> canBuildFrom() {
        return new CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, IterableView<A, Iterable<?>>>(){

            public TraversableView.NoBuilder<A> apply(TraversableView<?, ? extends Traversable<?>> from2) {
                return new TraversableView.NoBuilder<A>();
            }

            public TraversableView.NoBuilder<A> apply() {
                return new TraversableView.NoBuilder<A>();
            }
        };
    }

    private IterableView$() {
        MODULE$ = this;
    }
}

