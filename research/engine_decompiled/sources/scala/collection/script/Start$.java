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

public final class Start$
extends Location
implements Product,
Serializable {
    public static final Start$ MODULE$;

    static {
        new Start$();
    }

    @Override
    public String productPrefix() {
        return "Start";
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
        return x$1 instanceof Start$;
    }

    public int hashCode() {
        return 80204866;
    }

    public String toString() {
        return "Start";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Start$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

