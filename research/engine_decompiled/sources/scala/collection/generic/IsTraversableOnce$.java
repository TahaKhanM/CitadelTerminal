/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.generic.IsTraversableOnce;

public final class IsTraversableOnce$ {
    public static final IsTraversableOnce$ MODULE$;
    private final IsTraversableOnce<String> stringRepr;

    static {
        new IsTraversableOnce$();
    }

    public IsTraversableOnce<String> stringRepr() {
        return this.stringRepr;
    }

    public <C, A0> IsTraversableOnce<C> genTraversableLikeRepr(Function1<C, GenTraversableOnce<A0>> conv) {
        return new IsTraversableOnce<C>(conv){
            private final Function1<C, GenTraversableOnce<A0>> conversion;

            public Function1<C, GenTraversableOnce<A0>> conversion() {
                return this.conversion;
            }
            {
                this.conversion = conv$1;
            }
        };
    }

    private IsTraversableOnce$() {
        MODULE$ = this;
        this.stringRepr = new IsTraversableOnce<String>(){
            private final Function1<String, GenTraversableOnce<Object>> conversion;

            public Function1<String, GenTraversableOnce<Object>> conversion() {
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

