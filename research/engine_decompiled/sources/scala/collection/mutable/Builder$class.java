/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Predef$;
import scala.Proxy$class;
import scala.collection.IndexedSeqLike;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$;
import scala.runtime.RichInt$;

public abstract class Builder$class {
    public static void sizeHint(Builder $this, int size2) {
    }

    public static void sizeHint(Builder $this, TraversableLike coll) {
        if (coll instanceof IndexedSeqLike) {
            $this.sizeHint(coll.size());
        }
    }

    public static void sizeHint(Builder $this, TraversableLike coll, int delta) {
        if (coll instanceof IndexedSeqLike) {
            $this.sizeHint(coll.size() + delta);
        }
    }

    public static void sizeHintBounded(Builder $this, int size2, TraversableLike boundingColl) {
        if (boundingColl instanceof IndexedSeqLike) {
            Predef$ predef$ = Predef$.MODULE$;
            $this.sizeHint(RichInt$.MODULE$.min$extension(size2, boundingColl.size()));
        }
    }

    public static Builder mapResult(Builder $this, Function1 f) {
        return new Builder<Elem, NewTo>($this, f){
            private final Builder<Elem, To> self;
            private final Function1 f$1;

            public int hashCode() {
                return Proxy$class.hashCode(this);
            }

            public boolean equals(Object that) {
                return Proxy$class.equals(this, that);
            }

            public String toString() {
                return Proxy$class.toString(this);
            }

            public void sizeHint(TraversableLike<?, ?> coll) {
                Builder$class.sizeHint((Builder)this, coll);
            }

            public void sizeHint(TraversableLike<?, ?> coll, int delta) {
                Builder$class.sizeHint(this, coll, delta);
            }

            public <NewTo> Builder<Elem, NewTo> mapResult(Function1<NewTo, NewTo> f) {
                return Builder$class.mapResult(this, f);
            }

            public Growable<Elem> $plus$eq(Elem elem1, Elem elem2, Seq<Elem> elems) {
                return Growable$class.$plus$eq(this, elem1, elem2, elems);
            }

            public Builder<Elem, To> self() {
                return this.self;
            }

            public Builder$.anon.1 $plus$eq(Elem x) {
                this.self().$plus$eq(x);
                return this;
            }

            public void clear() {
                this.self().clear();
            }

            public Builder$.anon.1 $plus$plus$eq(TraversableOnce<Elem> xs) {
                this.self().$plus$plus$eq(xs);
                return this;
            }

            public void sizeHint(int size2) {
                this.self().sizeHint(size2);
            }

            public void sizeHintBounded(int size2, TraversableLike<?, ?> boundColl) {
                this.self().sizeHintBounded(size2, boundColl);
            }

            public NewTo result() {
                return (NewTo)this.f$1.apply(this.self().result());
            }
            {
                this.f$1 = f$1;
                Growable$class.$init$(this);
                Builder$class.$init$(this);
                Proxy$class.$init$(this);
                this.self = $outer;
            }
        };
    }

    public static void $init$(Builder $this) {
    }
}

