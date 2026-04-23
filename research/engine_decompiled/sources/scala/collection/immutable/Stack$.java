/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.Stack;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;

public final class Stack$
extends SeqFactory<Stack>
implements Serializable {
    public static final Stack$ MODULE$;

    static {
        new Stack$();
    }

    public <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Stack<A>> newBuilder() {
        return new ArrayBuffer().mapResult(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Stack<A> apply(ArrayBuffer<A> buf) {
                return new Stack<A>(buf.toList());
            }
        });
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Stack$() {
        MODULE$ = this;
    }
}

