/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SeqFactory;
import scala.collection.mutable.ArrayStack;
import scala.collection.mutable.Builder;
import scala.collection.package$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.Nothing$;

public final class ArrayStack$
extends SeqFactory<ArrayStack>
implements Serializable {
    public static final ArrayStack$ MODULE$;

    static {
        new ArrayStack$();
    }

    public <A> CanBuildFrom<ArrayStack<?>, A, ArrayStack<A>> canBuildFrom() {
        return this.ReusableCBF();
    }

    @Override
    public <A> Builder<A, ArrayStack<A>> newBuilder() {
        return new ArrayStack();
    }

    @Override
    public ArrayStack<Nothing$> empty() {
        return new ArrayStack<Nothing$>();
    }

    public <A> ArrayStack<A> apply(Seq<A> elems, ClassTag<A> evidence$1) {
        Object[] els = (Object[])elems.reverseMap(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Object apply(A x$1) {
                return x$1;
            }
        }, package$.MODULE$.breakOut(Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.AnyRef())));
        return els.length == 0 ? new ArrayStack() : new ArrayStack(els, els.length);
    }

    /*
     * WARNING - void declaration
     */
    public Object[] growArray(Object[] x) {
        void var2_2;
        Object[] y = new Object[scala.math.package$.MODULE$.max(x.length * 2, 1)];
        Array$.MODULE$.copy(x, 0, y, 0, x.length);
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public Object[] clone(Object[] x) {
        void var2_2;
        Object[] y = new Object[x.length];
        Array$.MODULE$.copy(x, 0, y, 0, x.length);
        return var2_2;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ArrayStack$() {
        MODULE$ = this;
    }
}

