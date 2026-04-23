/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.FakePos$;
import scala.reflect.internal.util.UndefinedPosition;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005ea\u0001B\u0001\u0003\u0001.\u0011qAR1lKB{7O\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\t)a!\u0001\u0005j]R,'O\\1m\u0015\t9\u0001\"A\u0004sK\u001adWm\u0019;\u000b\u0003%\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001\u0019A!\u0002CA\u0007\u000f\u001b\u0005\u0011\u0011BA\b\u0003\u0005E)f\u000eZ3gS:,G\rU8tSRLwN\u001c\t\u0003#Ii\u0011\u0001C\u0005\u0003'!\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\u0012+%\u0011a\u0003\u0003\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\t1\u0001\u0011)\u001a!C\u00013\u0005\u0019Qn]4\u0016\u0003i\u0001\"a\u0007\u0010\u000f\u0005Ea\u0012BA\u000f\t\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0004\t\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005uA\u0001\u0002\u0003\u0012\u0001\u0005#\u0005\u000b\u0011\u0002\u000e\u0002\t5\u001cx\r\t\u0005\u0006I\u0001!\t!J\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019:\u0003CA\u0007\u0001\u0011\u0015A2\u00051\u0001\u001b\u0011\u0015I\u0003\u0001\"\u0011+\u0003!!xn\u0015;sS:<G#\u0001\u000e\t\u000f1\u0002\u0011\u0011!C\u0001[\u0005!1m\u001c9z)\t1c\u0006C\u0004\u0019WA\u0005\t\u0019\u0001\u000e\t\u000fA\u0002\u0011\u0013!C\u0001c\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001\u001a+\u0005i\u00194&\u0001\u001b\u0011\u0005URT\"\u0001\u001c\u000b\u0005]B\u0014!C;oG\",7m[3e\u0015\tI\u0004\"\u0001\u0006b]:|G/\u0019;j_:L!a\u000f\u001c\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0004>\u0001\u0005\u0005I\u0011\t \u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005y\u0004C\u0001!F\u001b\u0005\t%B\u0001\"D\u0003\u0011a\u0017M\\4\u000b\u0003\u0011\u000bAA[1wC&\u0011q$\u0011\u0005\b\u000f\u0002\t\t\u0011\"\u0001I\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005I\u0005CA\tK\u0013\tY\u0005BA\u0002J]RDq!\u0014\u0001\u0002\u0002\u0013\u0005a*\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005=\u0013\u0006CA\tQ\u0013\t\t\u0006BA\u0002B]fDqa\u0015'\u0002\u0002\u0003\u0007\u0011*A\u0002yIEBq!\u0016\u0001\u0002\u0002\u0013\u0005c+A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u00059\u0006c\u0001-\\\u001f6\t\u0011L\u0003\u0002[\u0011\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005qK&\u0001C%uKJ\fGo\u001c:\t\u000fy\u0003\u0011\u0011!C\u0001?\u0006A1-\u00198FcV\fG\u000e\u0006\u0002aGB\u0011\u0011#Y\u0005\u0003E\"\u0011qAQ8pY\u0016\fg\u000eC\u0004T;\u0006\u0005\t\u0019A(\t\u000f\u0015\u0004\u0011\u0011!C!M\u0006A\u0001.Y:i\u0007>$W\rF\u0001J\u0011\u001dA\u0007!!A\u0005B%\fa!Z9vC2\u001cHC\u00011k\u0011\u001d\u0019v-!AA\u0002=;q\u0001\u001c\u0002\u0002\u0002#\u0005Q.A\u0004GC.,\u0007k\\:\u0011\u00055qgaB\u0001\u0003\u0003\u0003E\ta\\\n\u0004]B$\u0002\u0003B9u5\u0019j\u0011A\u001d\u0006\u0003g\"\tqA];oi&lW-\u0003\u0002ve\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\t\u000b\u0011rG\u0011A<\u0015\u00035Dq!\u000b8\u0002\u0002\u0013\u0015\u0013\u0010F\u0001@\u0011\u001dYh.!A\u0005\u0002r\fQ!\u00199qYf$\"AJ?\t\u000baQ\b\u0019\u0001\u000e\t\u0011}t\u0017\u0011!CA\u0003\u0003\tq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0004\u0005%\u0001\u0003B\t\u0002\u0006iI1!a\u0002\t\u0005\u0019y\u0005\u000f^5p]\"A\u00111\u0002@\u0002\u0002\u0003\u0007a%A\u0002yIAB\u0011\"a\u0004o\u0003\u0003%I!!\u0005\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003'\u00012\u0001QA\u000b\u0013\r\t9\"\u0011\u0002\u0007\u001f\nTWm\u0019;")
public class FakePos
extends UndefinedPosition
implements Product,
Serializable {
    private final String msg;

    public static Option<String> unapply(FakePos fakePos) {
        return FakePos$.MODULE$.unapply(fakePos);
    }

    public static FakePos apply(String string2) {
        return FakePos$.MODULE$.apply(string2);
    }

    public static <A> Function1<String, A> andThen(Function1<FakePos, A> function1) {
        return FakePos$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, FakePos> compose(Function1<A, String> function1) {
        return FakePos$.MODULE$.compose(function1);
    }

    public String msg() {
        return this.msg;
    }

    public String toString() {
        return this.msg();
    }

    public FakePos copy(String msg) {
        return new FakePos(msg);
    }

    public String copy$default$1() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "FakePos";
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
        return this.msg();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof FakePos;
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
        if (!(x$1 instanceof FakePos)) return false;
        boolean bl = true;
        if (!bl) return false;
        FakePos fakePos = (FakePos)x$1;
        String string2 = this.msg();
        String string3 = fakePos.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!fakePos.canEqual(this)) return false;
        return true;
    }

    public FakePos(String msg) {
        this.msg = msg;
        Product$class.$init$(this);
    }
}

