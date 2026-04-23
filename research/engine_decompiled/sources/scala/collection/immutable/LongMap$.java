/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.LongMap;
import scala.collection.immutable.LongMap$Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.MapBuilder;

public final class LongMap$ {
    public static final LongMap$ MODULE$;

    static {
        new LongMap$();
    }

    public <A, B> Object canBuildFrom() {
        return new CanBuildFrom<LongMap<A>, Tuple2<Object, B>, LongMap<B>>(){

            public Builder<Tuple2<Object, B>, LongMap<B>> apply(LongMap<A> from2) {
                return this.apply();
            }

            public Builder<Tuple2<Object, B>, LongMap<B>> apply() {
                return new MapBuilder<A, B, LongMap<T>>(LongMap$.MODULE$.empty());
            }
        };
    }

    public <T> LongMap<T> empty() {
        return LongMap$Nil$.MODULE$;
    }

    public <T> LongMap<T> singleton(long key, T value) {
        return new LongMap.Tip<T>(key, value);
    }

    public <T> LongMap<T> apply(Seq<Tuple2<Object, T>> elems) {
        return elems.foldLeft(this.empty(), new Serializable(){
            public static final long serialVersionUID = 0L;

            public final LongMap<T> apply(LongMap<T> x, Tuple2<Object, T> y) {
                return x.updated(y._1$mcJ$sp(), y._2());
            }
        });
    }

    private LongMap$() {
        MODULE$ = this;
    }
}

