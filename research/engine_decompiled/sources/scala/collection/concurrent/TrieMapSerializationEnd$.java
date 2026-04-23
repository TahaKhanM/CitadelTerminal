/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

public final class TrieMapSerializationEnd$
implements Product,
Serializable {
    public static final TrieMapSerializationEnd$ MODULE$;
    public static final long serialVersionUID = -7237891413820527142L;

    static {
        new TrieMapSerializationEnd$();
    }

    @Override
    public String productPrefix() {
        return "TrieMapSerializationEnd";
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
        return x$1 instanceof TrieMapSerializationEnd$;
    }

    public int hashCode() {
        return 289833389;
    }

    public String toString() {
        return "TrieMapSerializationEnd";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private TrieMapSerializationEnd$() {
        MODULE$ = this;
        Product$class.$init$(this);
    }
}

