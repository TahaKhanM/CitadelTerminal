/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public final class ListSerializeEnd$
implements Product,
Serializable {
    public static final ListSerializeEnd$ MODULE$;
    public static final long serialVersionUID = -8476791151975527571L;

    static {
        new ListSerializeEnd$();
    }

    @Override
    public String productPrefix() {
        return "ListSerializeEnd";
    }

    @Override
    public int productArity() {
        return 0;
    }

    @Override
    public Object productElement(int x$1) {
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof ListSerializeEnd$;
    }

    public int hashCode() {
        return -1720972871;
    }

    public String toString() {
        return "ListSerializeEnd";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private ListSerializeEnd$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

