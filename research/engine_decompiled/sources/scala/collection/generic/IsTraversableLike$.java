/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversableLike;
import scala.collection.generic.IsTraversableLike;

public final class IsTraversableLike$ {
    public static final IsTraversableLike$ MODULE$;
    private final IsTraversableLike<String> stringRepr;

    static {
        new IsTraversableLike$();
    }

    public IsTraversableLike<String> stringRepr() {
        return this.stringRepr;
    }

    public <C, A0> IsTraversableLike<C> genTraversableLikeRepr(Function1<C, GenTraversableLike<A0, C>> conv) {
        return new IsTraversableLike<C>(conv){
            private final Function1<C, GenTraversableLike<A0, C>> conversion;

            public Function1<C, GenTraversableLike<A0, C>> conversion() {
                return this.conversion;
            }
            {
                this.conversion = conv$1;
            }
        };
    }

    private IsTraversableLike$() {
        MODULE$ = this;
        this.stringRepr = new IsTraversableLike<String>(){
            private final Function1<String, GenTraversableLike<Object, String>> conversion;

            public Function1<String, GenTraversableLike<Object, String>> conversion() {
                return this.conversion;
            }
            {
                Serializable serializable = new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final String apply(String x) {
                        Predef$ predef$ = Predef$.MODULE$;
                        return x;
                    }
                };
                Predef$ predef$ = Predef$.MODULE$;
                this.conversion = (Function1)((Object)serializable);
            }
        };
    }
}

