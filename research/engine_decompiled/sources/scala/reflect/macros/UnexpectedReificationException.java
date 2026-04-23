/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.macros.UnexpectedReificationException$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005%d\u0001B\u0001\u0003\u0001&\u0011a$\u00168fqB,7\r^3e%\u0016Lg-[2bi&|g.\u0012=dKB$\u0018n\u001c8\u000b\u0005\r!\u0011AB7bGJ|7O\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\u0003\n\u0016!\tYqB\u0004\u0002\r\u001b5\ta!\u0003\u0002\u000f\r\u00059\u0001/Y2lC\u001e,\u0017B\u0001\t\u0012\u0005%)\u0005pY3qi&|gN\u0003\u0002\u000f\rA\u0011AbE\u0005\u0003)\u0019\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\r-%\u0011qC\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\t3\u0001\u0011)\u001a!C\u00015\u0005\u0019\u0001o\\:\u0016\u0003m\u0001\"\u0001H\u0010\u000e\u0003uQ!A\b\u0003\u0002\u0007\u0005\u0004\u0018.\u0003\u0002!;\tA\u0001k\\:ji&|g\u000e\u0003\u0005#\u0001\tE\t\u0015!\u0003\u001c\u0003\u0011\u0001xn\u001d\u0011\t\u0011\u0011\u0002!Q3A\u0005\u0002\u0015\n1!\\:h+\u00051\u0003CA\u0014+\u001d\ta\u0001&\u0003\u0002*\r\u00051\u0001K]3eK\u001aL!a\u000b\u0017\u0003\rM#(/\u001b8h\u0015\tIc\u0001\u0003\u0005/\u0001\tE\t\u0015!\u0003'\u0003\u0011i7o\u001a\u0011\t\u0011A\u0002!Q3A\u0005\u0002E\nQaY1vg\u0016,\u0012A\r\t\u0003\u0017MJ!\u0001N\t\u0003\u0013QC'o\\<bE2,\u0007\u0002\u0003\u001c\u0001\u0005#\u0005\u000b\u0011\u0002\u001a\u0002\r\r\fWo]3!\u0011\u0015A\u0004\u0001\"\u0001:\u0003\u0019a\u0014N\\5u}Q!!\bP\u001f?!\tY\u0004!D\u0001\u0003\u0011\u0015Ir\u00071\u0001\u001c\u0011\u0015!s\u00071\u0001'\u0011\u001d\u0001t\u0007%AA\u0002IBq\u0001\u0011\u0001\u0002\u0002\u0013\u0005\u0011)\u0001\u0003d_BLH\u0003\u0002\u001eC\u0007\u0012Cq!G \u0011\u0002\u0003\u00071\u0004C\u0004%\u007fA\u0005\t\u0019\u0001\u0014\t\u000fAz\u0004\u0013!a\u0001e!9a\tAI\u0001\n\u00039\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0011*\u00121$S\u0016\u0002\u0015B\u00111\nU\u0007\u0002\u0019*\u0011QJT\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0014\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002R\u0019\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000fM\u0003\u0011\u0013!C\u0001)\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A++\u0005\u0019J\u0005bB,\u0001#\u0003%\t\u0001W\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0005I&F\u0001\u001aJ\u0011\u001dY\u0006!!A\u0005Bq\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A/\u0011\u0005y\u001bW\"A0\u000b\u0005\u0001\f\u0017\u0001\u00027b]\u001eT\u0011AY\u0001\u0005U\u00064\u0018-\u0003\u0002,?\"9Q\rAA\u0001\n\u00031\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A4\u0011\u00051A\u0017BA5\u0007\u0005\rIe\u000e\u001e\u0005\bW\u0002\t\t\u0011\"\u0001m\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001c9\u0011\u00051q\u0017BA8\u0007\u0005\r\te.\u001f\u0005\bc*\f\t\u00111\u0001h\u0003\rAH%\r\u0005\bg\u0002\t\t\u0011\"\u0011u\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A;\u0011\u0007YLX.D\u0001x\u0015\tAh!\u0001\u0006d_2dWm\u0019;j_:L!A_<\u0003\u0011%#XM]1u_JDq\u0001 \u0001\u0002\u0002\u0013\u0005Q0\u0001\u0005dC:,\u0015/^1m)\rq\u00181\u0001\t\u0003\u0019}L1!!\u0001\u0007\u0005\u001d\u0011un\u001c7fC:Dq!]>\u0002\u0002\u0003\u0007Q\u000eC\u0005\u0002\b\u0001\t\t\u0011\"\u0011\u0002\n\u0005A\u0001.Y:i\u0007>$W\rF\u0001h\u0011%\ti\u0001AA\u0001\n\u0003\ny!\u0001\u0004fcV\fGn\u001d\u000b\u0004}\u0006E\u0001\u0002C9\u0002\f\u0005\u0005\t\u0019A7\b\u0013\u0005U!!!A\t\u0002\u0005]\u0011AH+oKb\u0004Xm\u0019;fIJ+\u0017NZ5dCRLwN\\#yG\u0016\u0004H/[8o!\rY\u0014\u0011\u0004\u0004\t\u0003\t\t\t\u0011#\u0001\u0002\u001cM)\u0011\u0011DA\u000f+AA\u0011qDA\u00137\u0019\u0012$(\u0004\u0002\u0002\")\u0019\u00111\u0005\u0004\u0002\u000fI,h\u000e^5nK&!\u0011qEA\u0011\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u0005\bq\u0005eA\u0011AA\u0016)\t\t9\u0002\u0003\u0006\u00020\u0005e\u0011\u0011!C#\u0003c\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002;\"Q\u0011QGA\r\u0003\u0003%\t)a\u000e\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fi\nI$a\u000f\u0002>!1\u0011$a\rA\u0002mAa\u0001JA\u001a\u0001\u00041\u0003\u0002\u0003\u0019\u00024A\u0005\t\u0019\u0001\u001a\t\u0015\u0005\u0005\u0013\u0011DA\u0001\n\u0003\u000b\u0019%A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u0013\u0011\u000b\t\u0006\u0019\u0005\u001d\u00131J\u0005\u0004\u0003\u00132!AB(qi&|g\u000e\u0005\u0004\r\u0003\u001bZbEM\u0005\u0004\u0003\u001f2!A\u0002+va2,7\u0007C\u0005\u0002T\u0005}\u0012\u0011!a\u0001u\u0005\u0019\u0001\u0010\n\u0019\t\u0013\u0005]\u0013\u0011DI\u0001\n\u0003A\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3\u0007C\u0005\u0002\\\u0005e\u0011\u0013!C\u00011\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3\u0007\u0003\u0006\u0002`\u0005e\u0011\u0011!C\u0005\u0003C\n1B]3bIJ+7o\u001c7wKR\u0011\u00111\r\t\u0004=\u0006\u0015\u0014bAA4?\n1qJ\u00196fGR\u0004")
public class UnexpectedReificationException
extends Exception
implements Product,
Serializable {
    private final Position pos;
    private final String msg;
    private final Throwable cause;

    public static Throwable apply$default$3() {
        return UnexpectedReificationException$.MODULE$.apply$default$3();
    }

    public static Throwable $lessinit$greater$default$3() {
        return UnexpectedReificationException$.MODULE$.$lessinit$greater$default$3();
    }

    public static Option<Tuple3<Position, String, Throwable>> unapply(UnexpectedReificationException unexpectedReificationException) {
        return UnexpectedReificationException$.MODULE$.unapply(unexpectedReificationException);
    }

    public static UnexpectedReificationException apply(Position position, String string2, Throwable throwable) {
        return UnexpectedReificationException$.MODULE$.apply(position, string2, throwable);
    }

    public static Function1<Tuple3<Position, String, Throwable>, UnexpectedReificationException> tupled() {
        return UnexpectedReificationException$.MODULE$.tupled();
    }

    public static Function1<Position, Function1<String, Function1<Throwable, UnexpectedReificationException>>> curried() {
        return UnexpectedReificationException$.MODULE$.curried();
    }

    public Position pos() {
        return this.pos;
    }

    public String msg() {
        return this.msg;
    }

    public Throwable cause() {
        return this.cause;
    }

    public UnexpectedReificationException copy(Position pos, String msg, Throwable cause) {
        return new UnexpectedReificationException(pos, msg, cause);
    }

    public Position copy$default$1() {
        return this.pos();
    }

    public String copy$default$2() {
        return this.msg();
    }

    public Throwable copy$default$3() {
        return this.cause();
    }

    @Override
    public String productPrefix() {
        return "UnexpectedReificationException";
    }

    @Override
    public int productArity() {
        return 3;
    }

    @Override
    public Object productElement(int x$1) {
        Object object;
        switch (x$1) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
            }
            case 2: {
                object = this.cause();
                break;
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
        return x$1 instanceof UnexpectedReificationException;
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
        if (!(x$1 instanceof UnexpectedReificationException)) return false;
        boolean bl = true;
        if (!bl) return false;
        UnexpectedReificationException unexpectedReificationException = (UnexpectedReificationException)x$1;
        Position position = this.pos();
        Position position2 = unexpectedReificationException.pos();
        if (position == null) {
            if (position2 != null) {
                return false;
            }
        } else if (!position.equals(position2)) return false;
        String string2 = this.msg();
        String string3 = unexpectedReificationException.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        Throwable throwable = this.cause();
        Throwable throwable2 = unexpectedReificationException.cause();
        if (throwable == null) {
            if (throwable2 != null) {
                return false;
            }
        } else if (!throwable.equals(throwable2)) return false;
        if (!unexpectedReificationException.canEqual(this)) return false;
        return true;
    }

    public UnexpectedReificationException(Position pos, String msg, Throwable cause) {
        this.pos = pos;
        this.msg = msg;
        this.cause = cause;
        super(msg, cause);
        Product$class.$init$(this);
    }
}

