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
import scala.runtime.BoxesRunTime;
import scala.text.DocNest;
import scala.text.Document;

public final class DocNest$
extends AbstractFunction2<Object, Document, DocNest>
implements Serializable {
    public static final DocNest$ MODULE$;

    static {
        new DocNest$();
    }

    @Override
    public final String toString() {
        return "DocNest";
    }

    @Override
    public DocNest apply(int indent, Document doc) {
        return new DocNest(indent, doc);
    }

    public Option<Tuple2<Object, Document>> unapply(DocNest x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Tuple2<Integer, Document>>(new Tuple2<Integer, Document>(BoxesRunTime.boxToInteger(x$0.indent()), x$0.doc()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private DocNest$() {
        MODULE$ = this;
    }
}

