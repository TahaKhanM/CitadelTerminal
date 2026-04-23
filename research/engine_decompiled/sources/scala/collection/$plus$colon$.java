/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqLike;

public final class $plus$colon$ {
    public static final $plus$colon$ MODULE$;

    static {
        new $plus$colon$();
    }

    public <T, Coll extends SeqLike<T, Coll>> Option<Tuple2<T, Coll>> unapply(Coll t) {
        Option option;
        if (t.isEmpty()) {
            option = None$.MODULE$;
        } else {
            Object Repr = t.tail();
            Object a = Predef$.MODULE$.ArrowAssoc(t.head());
            Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
            Some some = new Some(new Tuple2(a, Repr));
            option = some;
        }
        return option;
    }

    private $plus$colon$() {
        MODULE$ = this;
    }
}

