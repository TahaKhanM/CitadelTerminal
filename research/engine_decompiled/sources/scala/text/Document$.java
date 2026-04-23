/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.text.DocBreak$;
import scala.text.DocGroup;
import scala.text.DocNest;
import scala.text.DocNil$;
import scala.text.DocText;
import scala.text.Document;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class Document$ {
    public static final Document$ MODULE$;

    static {
        new Document$();
    }

    public DocNil$ empty() {
        return DocNil$.MODULE$;
    }

    public DocBreak$ break() {
        return DocBreak$.MODULE$;
    }

    public Document text(String s2) {
        return new DocText(s2);
    }

    public Document group(Document d) {
        return new DocGroup(d);
    }

    public Document nest(int i, Document d) {
        return new DocNest(i, d);
    }

    private Document$() {
        MODULE$ = this;
    }
}

