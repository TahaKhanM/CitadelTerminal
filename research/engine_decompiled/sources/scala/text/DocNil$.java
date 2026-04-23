/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.text.Document;

public final class DocNil$
extends Document
implements Product,
Serializable {
    public static final DocNil$ MODULE$;

    static {
        new DocNil$();
    }

    @Override
    public String productPrefix() {
        return "DocNil";
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
        return x$1 instanceof DocNil$;
    }

    public int hashCode() {
        return 2052320729;
    }

    public String toString() {
        return "DocNil";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private DocNil$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

