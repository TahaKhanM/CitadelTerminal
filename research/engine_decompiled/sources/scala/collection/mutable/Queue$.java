/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.MutableList;
import scala.collection.mutable.Queue;

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
        return new MutableList().mapResult(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Queue<A> apply(MutableList<A> x$1) {
                return x$1.toQueue();
            }
        });
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Queue$() {
        MODULE$ = this;
    }
}

