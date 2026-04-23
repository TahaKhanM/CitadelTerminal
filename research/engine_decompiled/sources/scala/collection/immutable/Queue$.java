/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Queue;
import scala.collection.immutable.Queue$EmptyQueue$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.ListBuffer;
import scala.runtime.Nothing$;

public final class Queue$
extends SeqFactory<Queue>
implements Serializable {
    public static final Queue$ MODULE$;

    static {
        new Queue$();
    }

    public <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Queue<A>> newBuilder() {
        return Builder$class.mapResult(new ListBuffer(), (Function1)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Queue<A> apply(List<A> x) {
                return new Queue<Nothing$>(Nil$.MODULE$, x.toList());
            }
        }));
    }

    @Override
    public <A> Queue<A> empty() {
        return Queue$EmptyQueue$.MODULE$;
    }

    @Override
    public <A> Queue<A> apply(Seq<A> xs) {
        return new Queue<Nothing$>(Nil$.MODULE$, xs.toList());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Queue$() {
        MODULE$ = this;
    }
}

