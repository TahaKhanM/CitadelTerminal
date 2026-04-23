/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.runtime.AbstractFunction1;
import scala.text.DocText;

public final class DocText$
extends AbstractFunction1<String, DocText>
implements Serializable {
    public static final DocText$ MODULE$;

    static {
        new DocText$();
    }

    @Override
    public final String toString() {
        return "DocText";
    }

    @Override
    public DocText apply(String txt) {
        return new DocText(txt);
    }

    public Option<String> unapply(DocText x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.txt());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private DocText$() {
        MODULE$ = this;
    }
}

