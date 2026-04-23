/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.runtime.AbstractFunction2;
import scala.text.DocCons;
import scala.text.Document;

public final class DocCons$
extends AbstractFunction2<Document, Document, DocCons>
implements Serializable {
    public static final DocCons$ MODULE$;

    static {
        new DocCons$();
    }

    @Override
    public final String toString() {
        return "DocCons";
    }

    @Override
    public DocCons apply(Document hd, Document tl) {
        return new DocCons(hd, tl);
    }

    public Option<Tuple2<Document, Document>> unapply(DocCons x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Document, Document>>(new Tuple2<Document, Document>(x$0.hd(), x$0.tl()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private DocCons$() {
        MODULE$ = this;
    }
}

