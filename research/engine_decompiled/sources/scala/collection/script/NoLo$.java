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

public final class NoLo$
extends Location
implements Product,
Serializable {
    public static final NoLo$ MODULE$;

    static {
        new NoLo$();
    }

    @Override
    public String productPrefix() {
        return "NoLo";
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
        return x$1 instanceof NoLo$;
    }

    public int hashCode() {
        return 2432836;
    }

    public String toString() {
        return "NoLo";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private NoLo$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

