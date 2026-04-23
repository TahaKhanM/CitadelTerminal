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
import scala.reflect.macros.ReificationException$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\rc\u0001B\u0001\u0003\u0001&\u0011ACU3jM&\u001c\u0017\r^5p]\u0016C8-\u001a9uS>t'BA\u0002\u0005\u0003\u0019i\u0017m\u0019:pg*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0005\u0001)\u0011R\u0003\u0005\u0002\f\u001f9\u0011A\"D\u0007\u0002\r%\u0011aBB\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0012CA\u0005Fq\u000e,\u0007\u000f^5p]*\u0011aB\u0002\t\u0003\u0019MI!\u0001\u0006\u0004\u0003\u000fA\u0013x\u000eZ;diB\u0011ABF\u0005\u0003/\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"\u0007\u0001\u0003\u0016\u0004%\tAG\u0001\u0004a>\u001cX#A\u000e\u0011\u0005qyR\"A\u000f\u000b\u0005y!\u0011aA1qS&\u0011\u0001%\b\u0002\t!>\u001c\u0018\u000e^5p]\"A!\u0005\u0001B\tB\u0003%1$\u0001\u0003q_N\u0004\u0003\u0002\u0003\u0013\u0001\u0005+\u0007I\u0011A\u0013\u0002\u00075\u001cx-F\u0001'!\t9#F\u0004\u0002\rQ%\u0011\u0011FB\u0001\u0007!J,G-\u001a4\n\u0005-b#AB*ue&twM\u0003\u0002*\r!Aa\u0006\u0001B\tB\u0003%a%\u0001\u0003ng\u001e\u0004\u0003\"\u0002\u0019\u0001\t\u0003\t\u0014A\u0002\u001fj]&$h\bF\u00023iU\u0002\"a\r\u0001\u000e\u0003\tAQ!G\u0018A\u0002mAQ\u0001J\u0018A\u0002\u0019Bqa\u000e\u0001\u0002\u0002\u0013\u0005\u0001(\u0001\u0003d_BLHc\u0001\u001a:u!9\u0011D\u000eI\u0001\u0002\u0004Y\u0002b\u0002\u00137!\u0003\u0005\rA\n\u0005\by\u0001\t\n\u0011\"\u0001>\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0010\u0016\u00037}Z\u0013\u0001\u0011\t\u0003\u0003\u001ak\u0011A\u0011\u0006\u0003\u0007\u0012\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u00153\u0011AC1o]>$\u0018\r^5p]&\u0011qI\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007bB%\u0001#\u0003%\tAS\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005Y%F\u0001\u0014@\u0011\u001di\u0005!!A\u0005B9\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A(\u0011\u0005A+V\"A)\u000b\u0005I\u001b\u0016\u0001\u00027b]\u001eT\u0011\u0001V\u0001\u0005U\u00064\u0018-\u0003\u0002,#\"9q\u000bAA\u0001\n\u0003A\u0016\u0001\u00049s_\u0012,8\r^!sSRLX#A-\u0011\u00051Q\u0016BA.\u0007\u0005\rIe\u000e\u001e\u0005\b;\u0002\t\t\u0011\"\u0001_\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u00182\u0011\u00051\u0001\u0017BA1\u0007\u0005\r\te.\u001f\u0005\bGr\u000b\t\u00111\u0001Z\u0003\rAH%\r\u0005\bK\u0002\t\t\u0011\"\u0011g\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A4\u0011\u0007!\\w,D\u0001j\u0015\tQg!\u0001\u0006d_2dWm\u0019;j_:L!\u0001\\5\u0003\u0011%#XM]1u_JDqA\u001c\u0001\u0002\u0002\u0013\u0005q.\u0001\u0005dC:,\u0015/^1m)\t\u00018\u000f\u0005\u0002\rc&\u0011!O\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\u0019W.!AA\u0002}Cq!\u001e\u0001\u0002\u0002\u0013\u0005c/\u0001\u0005iCND7i\u001c3f)\u0005I\u0006b\u0002=\u0001\u0003\u0003%\t%_\u0001\u0007KF,\u0018\r\\:\u0015\u0005AT\bbB2x\u0003\u0003\u0005\raX\u0004\by\n\t\t\u0011#\u0001~\u0003Q\u0011V-\u001b4jG\u0006$\u0018n\u001c8Fq\u000e,\u0007\u000f^5p]B\u00111G \u0004\b\u0003\t\t\t\u0011#\u0001\u0000'\u0011q\u0018\u0011A\u000b\u0011\u000f\u0005\r\u0011\u0011B\u000e'e5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000f1\u0011a\u0002:v]RLW.Z\u0005\u0005\u0003\u0017\t)AA\tBEN$(/Y2u\rVt7\r^5p]JBa\u0001\r@\u0005\u0002\u0005=A#A?\t\u0013\u0005Ma0!A\u0005F\u0005U\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003=C\u0011\"!\u0007\u007f\u0003\u0003%\t)a\u0007\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bI\ni\"a\b\t\re\t9\u00021\u0001\u001c\u0011\u0019!\u0013q\u0003a\u0001M!I\u00111\u0005@\u0002\u0002\u0013\u0005\u0015QE\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9#a\r\u0011\u000b1\tI#!\f\n\u0007\u0005-bA\u0001\u0004PaRLwN\u001c\t\u0006\u0019\u0005=2DJ\u0005\u0004\u0003c1!A\u0002+va2,'\u0007C\u0005\u00026\u0005\u0005\u0012\u0011!a\u0001e\u0005\u0019\u0001\u0010\n\u0019\t\u0013\u0005eb0!A\u0005\n\u0005m\u0012a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!!\u0010\u0011\u0007A\u000by$C\u0002\u0002BE\u0013aa\u00142kK\u000e$\b")
public class ReificationException
extends Exception
implements Product,
Serializable {
    private final Position pos;
    private final String msg;

    public static Option<Tuple2<Position, String>> unapply(ReificationException reificationException) {
        return ReificationException$.MODULE$.unapply(reificationException);
    }

    public static ReificationException apply(Position position, String string2) {
        return ReificationException$.MODULE$.apply(position, string2);
    }

    public static Function1<Tuple2<Position, String>, ReificationException> tupled() {
        return ReificationException$.MODULE$.tupled();
    }

    public static Function1<Position, Function1<String, ReificationException>> curried() {
        return ReificationException$.MODULE$.curried();
    }

    public Position pos() {
        return this.pos;
    }

    public String msg() {
        return this.msg;
    }

    public ReificationException copy(Position pos, String msg) {
        return new ReificationException(pos, msg);
    }

    public Position copy$default$1() {
        return this.pos();
    }

    public String copy$default$2() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "ReificationException";
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
        return x$1 instanceof ReificationException;
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
        if (!(x$1 instanceof ReificationException)) return false;
        boolean bl = true;
        if (!bl) return false;
        ReificationException reificationException = (ReificationException)x$1;
        Position position = this.pos();
        Position position2 = reificationException.pos();
        if (position == null) {
            if (position2 != null) {
                return false;
            }
        } else if (!position.equals(position2)) return false;
        String string2 = this.msg();
        String string3 = reificationException.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!reificationException.canEqual(this)) return false;
        return true;
    }

    public ReificationException(Position pos, String msg) {
        this.pos = pos;
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }
}

