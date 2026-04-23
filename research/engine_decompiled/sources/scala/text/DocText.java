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
import scala.text.DocText$;
import scala.text.Document;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005b\u0001B\u0001\u0003\u0001\u001e\u0011q\u0001R8d)\u0016DHO\u0003\u0002\u0004\t\u0005!A/\u001a=u\u0015\u0005)\u0011!B:dC2\f7\u0001A\n\u0005\u0001!a\u0001\u0003\u0005\u0002\n\u00155\t!!\u0003\u0002\f\u0005\tAAi\\2v[\u0016tG\u000f\u0005\u0002\u000e\u001d5\tA!\u0003\u0002\u0010\t\t9\u0001K]8ek\u000e$\bCA\u0007\u0012\u0013\t\u0011BA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005\u0015\u0001\tU\r\u0011\"\u0001\u0016\u0003\r!\b\u0010^\u000b\u0002-A\u0011qC\u0007\b\u0003\u001baI!!\u0007\u0003\u0002\rA\u0013X\rZ3g\u0013\tYBD\u0001\u0004TiJLgn\u001a\u0006\u00033\u0011A\u0001B\b\u0001\u0003\u0012\u0003\u0006IAF\u0001\u0005ib$\b\u0005C\u0003!\u0001\u0011\u0005\u0011%\u0001\u0004=S:LGO\u0010\u000b\u0003E\r\u0002\"!\u0003\u0001\t\u000bQy\u0002\u0019\u0001\f\t\u000f\u0015\u0002\u0011\u0011!C\u0001M\u0005!1m\u001c9z)\t\u0011s\u0005C\u0004\u0015IA\u0005\t\u0019\u0001\f\t\u000f%\u0002\u0011\u0013!C\u0001U\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A\u0016+\u0005Ya3&A\u0017\u0011\u00059\u001aT\"A\u0018\u000b\u0005A\n\u0014!C;oG\",7m[3e\u0015\t\u0011D!\u0001\u0006b]:|G/\u0019;j_:L!\u0001N\u0018\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u00047\u0001\u0005\u0005I\u0011I\u001c\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011a\u0017M\\4\u000b\u0003u\nAA[1wC&\u00111D\u000f\u0005\b\u0001\u0002\t\t\u0011\"\u0001B\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0011\u0005CA\u0007D\u0013\t!EAA\u0002J]RDqA\u0012\u0001\u0002\u0002\u0013\u0005q)\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005![\u0005CA\u0007J\u0013\tQEAA\u0002B]fDq\u0001T#\u0002\u0002\u0003\u0007!)A\u0002yIEBqA\u0014\u0001\u0002\u0002\u0013\u0005s*A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005\u0001\u0006cA)U\u00116\t!K\u0003\u0002T\t\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005U\u0013&\u0001C%uKJ\fGo\u001c:\t\u000f]\u0003\u0011\u0011!C\u00011\u0006A1-\u00198FcV\fG\u000e\u0006\u0002Z9B\u0011QBW\u0005\u00037\u0012\u0011qAQ8pY\u0016\fg\u000eC\u0004M-\u0006\u0005\t\u0019\u0001%\t\u000fy\u0003\u0011\u0011!C!?\u0006A\u0001.Y:i\u0007>$W\rF\u0001C\u0011\u001d\t\u0007!!A\u0005B\t\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002q!9A\rAA\u0001\n\u0003*\u0017AB3rk\u0006d7\u000f\u0006\u0002ZM\"9AjYA\u0001\u0002\u0004A\u0005\u0006\u0002\u0001iW6\u0004\"!D5\n\u0005)$!A\u00033faJ,7-\u0019;fI\u0006\nA.A\u000eUQ&\u001c\be\u00197bgN\u0004s/\u001b7mA\t,\u0007E]3n_Z,GML\u0011\u0002]\u00061!GL\u00192]A:q\u0001\u001d\u0002\u0002\u0002#\u0005\u0011/A\u0004E_\u000e$V\r\u001f;\u0011\u0005%\u0011haB\u0001\u0003\u0003\u0003E\ta]\n\u0004eR\u0004\u0002\u0003B;y-\tj\u0011A\u001e\u0006\u0003o\u0012\tqA];oi&lW-\u0003\u0002zm\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\t\u000b\u0001\u0012H\u0011A>\u0015\u0003EDq!\u0019:\u0002\u0002\u0013\u0015#\rC\u0004\u007fe\u0006\u0005I\u0011Q@\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\t\n\t\u0001C\u0003\u0015{\u0002\u0007a\u0003C\u0005\u0002\u0006I\f\t\u0011\"!\u0002\b\u00059QO\\1qa2LH\u0003BA\u0005\u0003\u001f\u0001B!DA\u0006-%\u0019\u0011Q\u0002\u0003\u0003\r=\u0003H/[8o\u0011%\t\t\"a\u0001\u0002\u0002\u0003\u0007!%A\u0002yIAB\u0011\"!\u0006s\u0003\u0003%I!a\u0006\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u00033\u00012!OA\u000e\u0013\r\tiB\u000f\u0002\u0007\u001f\nTWm\u0019;)\tID7.\u001c")
public class DocText
extends Document
implements Product,
Serializable {
    private final String txt;

    public static Option<String> unapply(DocText docText) {
        return DocText$.MODULE$.unapply(docText);
    }

    public static DocText apply(String string2) {
        return DocText$.MODULE$.apply(string2);
    }

    public static <A> Function1<String, A> andThen(Function1<DocText, A> function1) {
        return DocText$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, DocText> compose(Function1<A, String> function1) {
        return DocText$.MODULE$.compose(function1);
    }

    public String txt() {
        return this.txt;
    }

    public DocText copy(String txt) {
        return new DocText(txt);
    }

    public String copy$default$1() {
        return this.txt();
    }

    @Override
    public String productPrefix() {
        return "DocText";
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
        return this.txt();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof DocText;
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
        if (!(x$1 instanceof DocText)) return false;
        boolean bl = true;
        if (!bl) return false;
        DocText docText = (DocText)x$1;
        String string2 = this.txt();
        String string3 = docText.txt();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!docText.canEqual(this)) return false;
        return true;
    }

    public DocText(String txt) {
        this.txt = txt;
        Product$class.$init$(this);
    }
}

