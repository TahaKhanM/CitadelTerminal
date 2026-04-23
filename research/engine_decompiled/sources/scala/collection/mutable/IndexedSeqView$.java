/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.SeqView;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Traversable;

public final class IndexedSeqView$ {
    public static final IndexedSeqView$ MODULE$;

    static {
        new IndexedSeqView$();
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

    public <A> CanBuildFrom<TraversableView<?, Object>, A, SeqView<A, Object>> arrCanBuildFrom() {
        return new CanBuildFrom<TraversableView<?, Object>, A, SeqView<A, Object>>(){

            public TraversableView.NoBuilder<A> apply(TraversableView<?, Object> from2) {
                return new TraversableView.NoBuilder<A>();
            }

            public TraversableView.NoBuilder<A> apply() {
                return new TraversableView.NoBuilder<A>();
            }
        };
    }

    private IndexedSeqView$() {
        MODULE$ = this;
    }
}

