/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Traversable;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;

public final class TraversableView$ {
    public static final TraversableView$ MODULE$;

    static {
        new TraversableView$();
    }

    public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, TraversableView<A, Traversable<?>>> canBuildFrom() {
        return new CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, TraversableView<A, Traversable<?>>>(){

            public TraversableView.NoBuilder<A> apply(TraversableView<?, ? extends Traversable<?>> from2) {
                return new TraversableView.NoBuilder<A>();
            }

            public TraversableView.NoBuilder<A> apply() {
                return new TraversableView.NoBuilder<A>();
            }
        };
    }

    private TraversableView$() {
        MODULE$ = this;
    }
}

