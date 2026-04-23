/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.macros.TypecheckException$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\rc\u0001B\u0001\u0003\u0001&\u0011!\u0003V=qK\u000eDWmY6Fq\u000e,\u0007\u000f^5p]*\u00111\u0001B\u0001\u0007[\u0006\u001c'o\\:\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001\u000b%U\u0001\"aC\b\u000f\u00051iQ\"\u0001\u0004\n\u000591\u0011a\u00029bG.\fw-Z\u0005\u0003!E\u0011\u0011\"\u0012=dKB$\u0018n\u001c8\u000b\u000591\u0001C\u0001\u0007\u0014\u0013\t!bAA\u0004Qe>$Wo\u0019;\u0011\u000511\u0012BA\f\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!I\u0002A!f\u0001\n\u0003Q\u0012a\u00019pgV\t1\u0004\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\t\u0005\u0019\u0011\r]5\n\u0005\u0001j\"\u0001\u0003)pg&$\u0018n\u001c8\t\u0011\t\u0002!\u0011#Q\u0001\nm\tA\u0001]8tA!AA\u0005\u0001BK\u0002\u0013\u0005Q%A\u0002ng\u001e,\u0012A\n\t\u0003O)r!\u0001\u0004\u0015\n\u0005%2\u0011A\u0002)sK\u0012,g-\u0003\u0002,Y\t11\u000b\u001e:j]\u001eT!!\u000b\u0004\t\u00119\u0002!\u0011#Q\u0001\n\u0019\nA!\\:hA!)\u0001\u0007\u0001C\u0001c\u00051A(\u001b8jiz\"2A\r\u001b6!\t\u0019\u0004!D\u0001\u0003\u0011\u0015Ir\u00061\u0001\u001c\u0011\u0015!s\u00061\u0001'\u0011\u001d9\u0004!!A\u0005\u0002a\nAaY8qsR\u0019!'\u000f\u001e\t\u000fe1\u0004\u0013!a\u00017!9AE\u000eI\u0001\u0002\u00041\u0003b\u0002\u001f\u0001#\u0003%\t!P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005q$FA\u000e@W\u0005\u0001\u0005CA!G\u001b\u0005\u0011%BA\"E\u0003%)hn\u00195fG.,GM\u0003\u0002F\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u001d\u0013%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"9\u0011\nAI\u0001\n\u0003Q\u0015AD2paf$C-\u001a4bk2$HEM\u000b\u0002\u0017*\u0012ae\u0010\u0005\b\u001b\u0002\t\t\u0011\"\u0011O\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\n\u0005\u0002Q+6\t\u0011K\u0003\u0002S'\u0006!A.\u00198h\u0015\u0005!\u0016\u0001\u00026bm\u0006L!aK)\t\u000f]\u0003\u0011\u0011!C\u00011\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\f\u0005\u0002\r5&\u00111L\u0002\u0002\u0004\u0013:$\bbB/\u0001\u0003\u0003%\tAX\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ty&\r\u0005\u0002\rA&\u0011\u0011M\u0002\u0002\u0004\u0003:L\bbB2]\u0003\u0003\u0005\r!W\u0001\u0004q\u0012\n\u0004bB3\u0001\u0003\u0003%\tEZ\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\rE\u0002iW~k\u0011!\u001b\u0006\u0003U\u001a\t!bY8mY\u0016\u001cG/[8o\u0013\ta\u0017N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001dq\u0007!!A\u0005\u0002=\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003aN\u0004\"\u0001D9\n\u0005I4!a\u0002\"p_2,\u0017M\u001c\u0005\bG6\f\t\u00111\u0001`\u0011\u001d)\b!!A\u0005BY\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00023\"9\u0001\u0010AA\u0001\n\u0003J\u0018AB3rk\u0006d7\u000f\u0006\u0002qu\"91m^A\u0001\u0002\u0004yva\u0002?\u0003\u0003\u0003E\t!`\u0001\u0013)f\u0004Xm\u00195fG.,\u0005pY3qi&|g\u000e\u0005\u00024}\u001a9\u0011AAA\u0001\u0012\u0003y8\u0003\u0002@\u0002\u0002U\u0001r!a\u0001\u0002\nm1#'\u0004\u0002\u0002\u0006)\u0019\u0011q\u0001\u0004\u0002\u000fI,h\u000e^5nK&!\u00111BA\u0003\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u0005\u0007ay$\t!a\u0004\u0015\u0003uD\u0011\"a\u0005\u007f\u0003\u0003%)%!\u0006\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u0014\u0005\n\u00033q\u0018\u0011!CA\u00037\tQ!\u00199qYf$RAMA\u000f\u0003?Aa!GA\f\u0001\u0004Y\u0002B\u0002\u0013\u0002\u0018\u0001\u0007a\u0005C\u0005\u0002$y\f\t\u0011\"!\u0002&\u00059QO\\1qa2LH\u0003BA\u0014\u0003g\u0001R\u0001DA\u0015\u0003[I1!a\u000b\u0007\u0005\u0019y\u0005\u000f^5p]B)A\"a\f\u001cM%\u0019\u0011\u0011\u0007\u0004\u0003\rQ+\b\u000f\\33\u0011%\t)$!\t\u0002\u0002\u0003\u0007!'A\u0002yIAB\u0011\"!\u000f\u007f\u0003\u0003%I!a\u000f\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003{\u00012\u0001UA \u0013\r\t\t%\u0015\u0002\u0007\u001f\nTWm\u0019;")
public class TypecheckException
extends Exception
implements Product,
Serializable {
    private final Position pos;
    private final String msg;

    public static Option<Tuple2<Position, String>> unapply(TypecheckException typecheckException) {
        return TypecheckException$.MODULE$.unapply(typecheckException);
    }

    public static TypecheckException apply(Position position, String string2) {
        return TypecheckException$.MODULE$.apply(position, string2);
    }

    public static Function1<Tuple2<Position, String>, TypecheckException> tupled() {
        return TypecheckException$.MODULE$.tupled();
    }

    public static Function1<Position, Function1<String, TypecheckException>> curried() {
        return TypecheckException$.MODULE$.curried();
    }

    public Position pos() {
        return this.pos;
    }

    public String msg() {
        return this.msg;
    }

    public TypecheckException copy(Position pos, String msg) {
        return new TypecheckException(pos, msg);
    }

    public Position copy$default$1() {
        return this.pos();
    }

    public String copy$default$2() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "TypecheckException";
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
                object = this.msg();
                break;
            }
            case 0: {
                object = this.pos();
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
        return x$1 instanceof TypecheckException;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$._hashCode(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        if (this == x$1) return true;
        if (!(x$1 instanceof TypecheckException)) return false;
        boolean bl = true;
        if (!bl) return false;
        TypecheckException typecheckException = (TypecheckException)x$1;
        Position position = this.pos();
        Position position2 = typecheckException.pos();
        if (position == null) {
            if (position2 != null) {
                return false;
            }
        } else if (!position.equals(position2)) return false;
        String string2 = this.msg();
        String string3 = typecheckException.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!typecheckException.canEqual(this)) return false;
        return true;
    }

    public TypecheckException(Position pos, String msg) {
        this.pos = pos;
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }
}

