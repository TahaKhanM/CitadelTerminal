/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.text.DocGroup$;
import scala.text.Document;

@ScalaSignature(bytes="\u0006\u0001\u0005Ua\u0001B\u0001\u0003\u0001\u001e\u0011\u0001\u0002R8d\u000fJ|W\u000f\u001d\u0006\u0003\u0007\u0011\tA\u0001^3yi*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001AA\u0002\u0005\t\u0003\u0013)i\u0011AA\u0005\u0003\u0017\t\u0011\u0001\u0002R8dk6,g\u000e\u001e\t\u0003\u001b9i\u0011\u0001B\u0005\u0003\u001f\u0011\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\u000e#%\u0011!\u0003\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\t)\u0001\u0011)\u001a!C\u0001+\u0005\u0019Am\\2\u0016\u0003!A\u0001b\u0006\u0001\u0003\u0012\u0003\u0006I\u0001C\u0001\u0005I>\u001c\u0007\u0005C\u0003\u001a\u0001\u0011\u0005!$\u0001\u0004=S:LGO\u0010\u000b\u00037q\u0001\"!\u0003\u0001\t\u000bQA\u0002\u0019\u0001\u0005\t\u000fy\u0001\u0011\u0011!C\u0001?\u0005!1m\u001c9z)\tY\u0002\u0005C\u0004\u0015;A\u0005\t\u0019\u0001\u0005\t\u000f\t\u0002\u0011\u0013!C\u0001G\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001\u0013+\u0005!)3&\u0001\u0014\u0011\u0005\u001dbS\"\u0001\u0015\u000b\u0005%R\u0013!C;oG\",7m[3e\u0015\tYC!\u0001\u0006b]:|G/\u0019;j_:L!!\f\u0015\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u00040\u0001\u0005\u0005I\u0011\t\u0019\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\t\u0004C\u0001\u001a8\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0011a\u0017M\\4\u000b\u0003Y\nAA[1wC&\u0011\u0001h\r\u0002\u0007'R\u0014\u0018N\\4\t\u000fi\u0002\u0011\u0011!C\u0001w\u0005a\u0001O]8ek\u000e$\u0018I]5usV\tA\b\u0005\u0002\u000e{%\u0011a\b\u0002\u0002\u0004\u0013:$\bb\u0002!\u0001\u0003\u0003%\t!Q\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0011U\t\u0005\u0002\u000e\u0007&\u0011A\t\u0002\u0002\u0004\u0003:L\bb\u0002$@\u0003\u0003\u0005\r\u0001P\u0001\u0004q\u0012\n\u0004b\u0002%\u0001\u0003\u0003%\t%S\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\nE\u0002L\u001d\nk\u0011\u0001\u0014\u0006\u0003\u001b\u0012\t!bY8mY\u0016\u001cG/[8o\u0013\tyEJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d\t\u0006!!A\u0005\u0002I\u000b\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003'Z\u0003\"!\u0004+\n\u0005U#!a\u0002\"p_2,\u0017M\u001c\u0005\b\rB\u000b\t\u00111\u0001C\u0011\u001dA\u0006!!A\u0005Be\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002y!91\fAA\u0001\n\u0003b\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003EBqA\u0018\u0001\u0002\u0002\u0013\u0005s,\u0001\u0004fcV\fGn\u001d\u000b\u0003'\u0002DqAR/\u0002\u0002\u0003\u0007!\t\u000b\u0003\u0001E\u0016<\u0007CA\u0007d\u0013\t!GA\u0001\u0006eKB\u0014XmY1uK\u0012\f\u0013AZ\u0001\u001c)\"L7\u000fI2mCN\u001c\be^5mY\u0002\u0012W\r\t:f[>4X\r\u001a\u0018\"\u0003!\faA\r\u00182c9\u0002ta\u00026\u0003\u0003\u0003E\ta[\u0001\t\t>\u001cwI]8vaB\u0011\u0011\u0002\u001c\u0004\b\u0003\t\t\t\u0011#\u0001n'\rag\u000e\u0005\t\u0005_JD1$D\u0001q\u0015\t\tH!A\u0004sk:$\u0018.\\3\n\u0005M\u0004(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oc!)\u0011\u0004\u001cC\u0001kR\t1\u000eC\u0004\\Y\u0006\u0005IQ\t/\t\u000fad\u0017\u0011!CAs\u0006)\u0011\r\u001d9msR\u00111D\u001f\u0005\u0006)]\u0004\r\u0001\u0003\u0005\by2\f\t\u0011\"!~\u0003\u001d)h.\u00199qYf$2A`A\u0002!\riq\u0010C\u0005\u0004\u0003\u0003!!AB(qi&|g\u000e\u0003\u0005\u0002\u0006m\f\t\u00111\u0001\u001c\u0003\rAH\u0005\r\u0005\n\u0003\u0013a\u0017\u0011!C\u0005\u0003\u0017\t1B]3bIJ+7o\u001c7wKR\u0011\u0011Q\u0002\t\u0004e\u0005=\u0011bAA\tg\t1qJ\u00196fGRDC\u0001\u001c2fO\u0002")
public class DocGroup
extends Document
implements Product,
Serializable {
    private final Document doc;

    public static Option<Document> unapply(DocGroup docGroup) {
        return DocGroup$.MODULE$.unapply(docGroup);
    }

    public static DocGroup apply(Document document) {
        return DocGroup$.MODULE$.apply(document);
    }

    public static <A> Function1<Document, A> andThen(Function1<DocGroup, A> function1) {
        return DocGroup$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, DocGroup> compose(Function1<A, Document> function1) {
        return DocGroup$.MODULE$.compose(function1);
    }

    public Document doc() {
        return this.doc;
    }

    public DocGroup copy(Document doc) {
        return new DocGroup(doc);
    }

    public Document copy$default$1() {
        return this.doc();
    }

    @Override
    public String productPrefix() {
        return "DocGroup";
    }

    @Override
    public int productArity() {
        return 1;
    }

    @Override
    public Object productElement(int x$1) {
        switch (x$1) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
            }
            case 0: 
        }
        return this.doc();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof DocGroup;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$._hashCode(this);
    }

    public String toString() {
        return ScalaRunTime$.MODULE$._toString(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        if (this == x$1) return true;
        if (!(x$1 instanceof DocGroup)) return false;
        boolean bl = true;
        if (!bl) return false;
        DocGroup docGroup = (DocGroup)x$1;
        Document document = this.doc();
        Document document2 = docGroup.doc();
        if (document == null) {
            if (document2 != null) {
                return false;
            }
        } else if (!document.equals(document2)) return false;
        if (!docGroup.canEqual(this)) return false;
        return true;
    }

    public DocGroup(Document doc) {
        this.doc = doc;
        Product$class.$init$(this);
    }
}

