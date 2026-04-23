/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Stack;
import scala.runtime.Nothing$;

public final class Stack$
extends SeqFactory<Stack>
implements Serializable {
    public static final Stack$ MODULE$;
    private final Stack<Nothing$> empty;

    static {
        new Stack$();
    }

    public <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, Stack<A>> newBuilder() {
        return new Stack.StackBuilder();
    }

    @Override
    public Stack<Nothing$> empty() {
        return this.empty;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Stack$() {
        MODULE$ = this;
        this.empty = new Stack<Nothing$>(Nil$.MODULE$);
    }
}

