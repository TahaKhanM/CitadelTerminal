/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.internal.util.UndefinedPosition;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public final class NoPosition$
extends UndefinedPosition
implements Product,
Serializable {
    public static final NoPosition$ MODULE$;

    static {
        new NoPosition$();
    }

    @Override
    public String productPrefix() {
        return "NoPosition";
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
        return x$1 instanceof NoPosition$;
    }

    public int hashCode() {
        return -394684886;
    }

    public String toString() {
        return "NoPosition";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private NoPosition$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

