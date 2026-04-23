/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.Seq;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;

public final class SeqView$ {
    public static final SeqView$ MODULE$;

    static {
        new SeqView$();
    }

    public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> canBuildFrom() {
        return new CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>>(){

            public TraversableView.NoBuilder<A> apply(TraversableView<?, ? extends Traversable<?>> from2) {
                return new TraversableView.NoBuilder<A>();
            }

            public TraversableView.NoBuilder<A> apply() {
                return new TraversableView.NoBuilder<A>();
            }
        };
    }

    private SeqView$() {
        MODULE$ = this;
    }
}

