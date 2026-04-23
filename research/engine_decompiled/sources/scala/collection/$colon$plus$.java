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

public final class $colon$plus$ {
    public static final $colon$plus$ MODULE$;

    static {
        new $colon$plus$();
    }

    public <T, Coll extends SeqLike<T, Coll>> Option<Tuple2<Coll, T>> unapply(Coll t) {
        Option option;
        if (t.isEmpty()) {
            option = None$.MODULE$;
        } else {
            Object a = t.last();
            Object Repr = Predef$.MODULE$.ArrowAssoc(t.init());
            Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
            Some some = new Some(new Tuple2(Repr, a));
            option = some;
        }
        return option;
    }

    private $colon$plus$() {
        MODULE$ = this;
    }
}

