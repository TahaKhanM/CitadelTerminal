/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.IntMap$Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.MapBuilder;

public final class IntMap$ {
    public static final IntMap$ MODULE$;

    static {
        new IntMap$();
    }

    public <A, B> Object canBuildFrom() {
        return new CanBuildFrom<IntMap<A>, Tuple2<Object, B>, IntMap<B>>(){

            public Builder<Tuple2<Object, B>, IntMap<B>> apply(IntMap<A> from2) {
                return this.apply();
            }

            public Builder<Tuple2<Object, B>, IntMap<B>> apply() {
                return new MapBuilder<A, B, IntMap<T>>(IntMap$.MODULE$.empty());
            }
        };
    }

    public <T> IntMap<T> empty() {
        return IntMap$Nil$.MODULE$;
    }

    public <T> IntMap<T> singleton(int key, T value) {
        return new IntMap.Tip<T>(key, value);
    }

    public <T> IntMap<T> apply(Seq<Tuple2<Object, T>> elems) {
        return elems.foldLeft(this.empty(), new Serializable(){
            public static final long serialVersionUID = 0L;

            public final IntMap<T> apply(IntMap<T> x, Tuple2<Object, T> y) {
                return x.updated(y._1$mcI$sp(), y._2());
            }
        });
    }

    private IntMap$() {
        MODULE$ = this;
    }
}

