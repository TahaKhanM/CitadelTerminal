/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.script;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.script.Location;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public final class End$
extends Location
implements Product,
Serializable {
    public static final End$ MODULE$;

    static {
        new End$();
    }

    @Override
    public String productPrefix() {
        return "End";
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
        return x$1 instanceof End$;
    }

    public int hashCode() {
        return 69819;
    }

    public String toString() {
        return "End";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private End$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

