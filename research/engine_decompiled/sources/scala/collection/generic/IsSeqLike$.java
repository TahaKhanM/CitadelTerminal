/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.SeqLike;
import scala.collection.generic.IsSeqLike;

public final class IsSeqLike$ {
    public static final IsSeqLike$ MODULE$;
    private final IsSeqLike<String> stringRepr;

    static {
        new IsSeqLike$();
    }

    public IsSeqLike<String> stringRepr() {
        return this.stringRepr;
    }

    public <C, A0> IsSeqLike<C> seqLikeRepr(Function1<C, SeqLike<A0, C>> conv) {
        return new IsSeqLike<C>(conv){
            private final Function1<C, SeqLike<A0, C>> conversion;

            public Function1<C, SeqLike<A0, C>> conversion() {
                return this.conversion;
            }
            {
                this.conversion = conv$1;
            }
        };
    }

    private IsSeqLike$() {
        MODULE$ = this;
        this.stringRepr = new IsSeqLike<String>(){
            private final Function1<String, SeqLike<Object, String>> conversion;

            public Function1<String, SeqLike<Object, String>> conversion() {
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

