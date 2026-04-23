/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.runtime.AbstractFunction1;
import scala.text.DocGroup;
import scala.text.Document;

public final class DocGroup$
extends AbstractFunction1<Document, DocGroup>
implements Serializable {
    public static final DocGroup$ MODULE$;

    static {
        new DocGroup$();
    }

    @Override
    public final String toString() {
        return "DocGroup";
    }

    @Override
    public DocGroup apply(Document doc) {
        return new DocGroup(doc);
    }

    public Option<Document> unapply(DocGroup x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Document>(x$0.doc());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private DocGroup$() {
        MODULE$ = this;
    }
}

