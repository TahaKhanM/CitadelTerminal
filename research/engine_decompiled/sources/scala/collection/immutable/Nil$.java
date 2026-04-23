/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Serializable;
import scala.collection.GenSeq;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

public final class Nil$
extends List<Nothing$>
implements Serializable {
    public static final Nil$ MODULE$;
    public static final long serialVersionUID = -8256821097970055419L;

    static {
        new Nil$();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Nothing$ head() {
        throw new NoSuchElementException("head of empty list");
    }

    @Override
    public List<Nothing$> tail() {
        throw new UnsupportedOperationException("tail of empty list");
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof GenSeq) {
            GenSeq genSeq = (GenSeq)that;
            bl = genSeq.isEmpty();
        } else {
            bl = false;
        }
        return bl;
    }

    @Override
    public String productPrefix() {
        return "Nil";
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

    private Object readResolve() {
        return MODULE$;
    }

    private Nil$() {
        MODULE$ = this;
    }
}

