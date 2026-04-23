/*
 * Decompiled with CFR 0.152.
 */
package scala;

import java.util.NoSuchElementException;
import scala.Option;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class None$
extends Option<Nothing$> {
    public static final None$ MODULE$;
    public static final long serialVersionUID = 5066590221178148012L;

    static {
        new None$();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Nothing$ get() {
        throw new NoSuchElementException("None.get");
    }

    @Override
    public String productPrefix() {
        return "None";
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
        return x$1 instanceof None$;
    }

    public int hashCode() {
        return 2433880;
    }

    public String toString() {
        return "None";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private None$() {
        MODULE$ = this;
    }
}

