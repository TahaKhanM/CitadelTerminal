/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;
import scala.text.DocNest$;
import scala.text.Document;

@ScalaSignature(bytes="\u0006\u0001\u0005Eb\u0001B\u0001\u0003\u0001\u001e\u0011q\u0001R8d\u001d\u0016\u001cHO\u0003\u0002\u0004\t\u0005!A/\u001a=u\u0015\u0005)\u0011!B:dC2\f7\u0001A\n\u0005\u0001!a\u0001\u0003\u0005\u0002\n\u00155\t!!\u0003\u0002\f\u0005\tAAi\\2v[\u0016tG\u000f\u0005\u0002\u000e\u001d5\tA!\u0003\u0002\u0010\t\t9\u0001K]8ek\u000e$\bCA\u0007\u0012\u0013\t\u0011BA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005\u0015\u0001\tU\r\u0011\"\u0001\u0016\u0003\u0019Ig\u000eZ3oiV\ta\u0003\u0005\u0002\u000e/%\u0011\u0001\u0004\u0002\u0002\u0004\u0013:$\b\u0002\u0003\u000e\u0001\u0005#\u0005\u000b\u0011\u0002\f\u0002\u000f%tG-\u001a8uA!AA\u0004\u0001BK\u0002\u0013\u0005Q$A\u0002e_\u000e,\u0012\u0001\u0003\u0005\t?\u0001\u0011\t\u0012)A\u0005\u0011\u0005!Am\\2!\u0011\u0015\t\u0003\u0001\"\u0001#\u0003\u0019a\u0014N\\5u}Q\u00191\u0005J\u0013\u0011\u0005%\u0001\u0001\"\u0002\u000b!\u0001\u00041\u0002\"\u0002\u000f!\u0001\u0004A\u0001bB\u0014\u0001\u0003\u0003%\t\u0001K\u0001\u0005G>\u0004\u0018\u0010F\u0002$S)Bq\u0001\u0006\u0014\u0011\u0002\u0003\u0007a\u0003C\u0004\u001dMA\u0005\t\u0019\u0001\u0005\t\u000f1\u0002\u0011\u0013!C\u0001[\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001\u0018+\u0005Yy3&\u0001\u0019\u0011\u0005E2T\"\u0001\u001a\u000b\u0005M\"\u0014!C;oG\",7m[3e\u0015\t)D!\u0001\u0006b]:|G/\u0019;j_:L!a\u000e\u001a\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0004:\u0001E\u0005I\u0011\u0001\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t1H\u000b\u0002\t_!9Q\bAA\u0001\n\u0003r\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001@!\t\u0001U)D\u0001B\u0015\t\u00115)\u0001\u0003mC:<'\"\u0001#\u0002\t)\fg/Y\u0005\u0003\r\u0006\u0013aa\u0015;sS:<\u0007b\u0002%\u0001\u0003\u0003%\t!F\u0001\raJ|G-^2u\u0003JLG/\u001f\u0005\b\u0015\u0002\t\t\u0011\"\u0001L\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001T(\u0011\u00055i\u0015B\u0001(\u0005\u0005\r\te.\u001f\u0005\b!&\u000b\t\u00111\u0001\u0017\u0003\rAH%\r\u0005\b%\u0002\t\t\u0011\"\u0011T\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001+\u0011\u0007UCF*D\u0001W\u0015\t9F!\u0001\u0006d_2dWm\u0019;j_:L!!\u0017,\u0003\u0011%#XM]1u_JDqa\u0017\u0001\u0002\u0002\u0013\u0005A,\u0001\u0005dC:,\u0015/^1m)\ti\u0006\r\u0005\u0002\u000e=&\u0011q\f\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\u0001&,!AA\u00021CqA\u0019\u0001\u0002\u0002\u0013\u00053-\u0001\u0005iCND7i\u001c3f)\u00051\u0002bB3\u0001\u0003\u0003%\tEZ\u0001\ti>\u001cFO]5oOR\tq\bC\u0004i\u0001\u0005\u0005I\u0011I5\u0002\r\u0015\fX/\u00197t)\ti&\u000eC\u0004QO\u0006\u0005\t\u0019\u0001')\t\u0001aw.\u001d\t\u0003\u001b5L!A\u001c\u0003\u0003\u0015\u0011,\u0007O]3dCR,G-I\u0001q\u0003m!\u0006.[:!G2\f7o\u001d\u0011xS2d\u0007EY3!e\u0016lwN^3e]\u0005\n!/\u0001\u00043]E\nd\u0006M\u0004\bi\n\t\t\u0011#\u0001v\u0003\u001d!un\u0019(fgR\u0004\"!\u0003<\u0007\u000f\u0005\u0011\u0011\u0011!E\u0001oN\u0019a\u000f\u001f\t\u0011\u000bedh\u0003C\u0012\u000e\u0003iT!a\u001f\u0003\u0002\u000fI,h\u000e^5nK&\u0011QP\u001f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\"B\u0011w\t\u0003yH#A;\t\u000f\u00154\u0018\u0011!C#M\"I\u0011Q\u0001<\u0002\u0002\u0013\u0005\u0015qA\u0001\u0006CB\u0004H.\u001f\u000b\u0006G\u0005%\u00111\u0002\u0005\u0007)\u0005\r\u0001\u0019\u0001\f\t\rq\t\u0019\u00011\u0001\t\u0011%\tyA^A\u0001\n\u0003\u000b\t\"A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005M\u0011q\u0004\t\u0006\u001b\u0005U\u0011\u0011D\u0005\u0004\u0003/!!AB(qi&|g\u000eE\u0003\u000e\u000371\u0002\"C\u0002\u0002\u001e\u0011\u0011a\u0001V;qY\u0016\u0014\u0004\"CA\u0011\u0003\u001b\t\t\u00111\u0001$\u0003\rAH\u0005\r\u0005\n\u0003K1\u0018\u0011!C\u0005\u0003O\t1B]3bIJ+7o\u001c7wKR\u0011\u0011\u0011\u0006\t\u0004\u0001\u0006-\u0012bAA\u0017\u0003\n1qJ\u00196fGRDCA\u001e7pc\u0002")
public class DocNest
extends Document
implements Product,
Serializable {
    private final int indent;
    private final Document doc;

    public static Option<Tuple2<Object, Document>> unapply(DocNest docNest) {
        return DocNest$.MODULE$.unapply(docNest);
    }

    public static DocNest apply(int n, Document document) {
        return DocNest$.MODULE$.apply(n, document);
    }

    public static Function1<Tuple2<Object, Document>, DocNest> tupled() {
        return DocNest$.MODULE$.tupled();
    }

    public static Function1<Object, Function1<Document, DocNest>> curried() {
        return DocNest$.MODULE$.curried();
    }

    public int indent() {
        return this.indent;
    }

    public Document doc() {
        return this.doc;
    }

    public DocNest copy(int indent, Document doc) {
        return new DocNest(indent, doc);
    }

    public int copy$default$1() {
        return this.indent();
    }

    public Document copy$default$2() {
        return this.doc();
    }

    @Override
    public String productPrefix() {
        return "DocNest";
    }

    @Override
    public int productArity() {
        return 2;
    }

    @Override
    public Object productElement(int x$1) {
        Object object;
        switch (x$1) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
            }
            case 1: {
                object = this.doc();
                break;
            }
            case 0: {
                object = BoxesRunTime.boxToInteger(this.indent());
            }
        }
        return object;
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof DocNest;
    }

    public int hashCode() {
        return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, this.indent()), Statics.anyHash(this.doc())), 2);
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
        if (!(x$1 instanceof DocNest)) return false;
        boolean bl = true;
        if (!bl) return false;
        DocNest docNest = (DocNest)x$1;
        if (this.indent() != docNest.indent()) return false;
        Document document = this.doc();
        Document document2 = docNest.doc();
        if (document == null) {
            if (document2 != null) {
                return false;
            }
        } else if (!document.equals(document2)) return false;
        if (!docNest.canEqual(this)) return false;
        return true;
    }

    public DocNest(int indent, Document doc) {
        this.indent = indent;
        this.doc = doc;
        Product$class.$init$(this);
    }
}

