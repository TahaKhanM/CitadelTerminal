/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.FatalError$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005ea\u0001B\u0001\u0003\u0001&\u0011!BR1uC2,%O]8s\u0015\t\u0019A!\u0001\u0005j]R,'O\\1m\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001\u0015I)\u0002CA\u0006\u0010\u001d\taQ\"D\u0001\u0007\u0013\tqa!A\u0004qC\u000e\\\u0017mZ3\n\u0005A\t\"!C#yG\u0016\u0004H/[8o\u0015\tqa\u0001\u0005\u0002\r'%\u0011AC\u0002\u0002\b!J|G-^2u!\taa#\u0003\u0002\u0018\r\ta1+\u001a:jC2L'0\u00192mK\"A\u0011\u0004\u0001BK\u0002\u0013\u0005!$A\u0002ng\u001e,\u0012a\u0007\t\u00039}q!\u0001D\u000f\n\u0005y1\u0011A\u0002)sK\u0012,g-\u0003\u0002!C\t11\u000b\u001e:j]\u001eT!A\b\u0004\t\u0011\r\u0002!\u0011#Q\u0001\nm\tA!\\:hA!)Q\u0005\u0001C\u0001M\u00051A(\u001b8jiz\"\"aJ\u0015\u0011\u0005!\u0002Q\"\u0001\u0002\t\u000be!\u0003\u0019A\u000e\t\u000f-\u0002\u0011\u0011!C\u0001Y\u0005!1m\u001c9z)\t9S\u0006C\u0004\u001aUA\u0005\t\u0019A\u000e\t\u000f=\u0002\u0011\u0013!C\u0001a\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A\u0019+\u0005m\u00114&A\u001a\u0011\u0005QJT\"A\u001b\u000b\u0005Y:\u0014!C;oG\",7m[3e\u0015\tAd!\u0001\u0006b]:|G/\u0019;j_:L!AO\u001b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0004=\u0001\u0005\u0005I\u0011I\u001f\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005q\u0004CA E\u001b\u0005\u0001%BA!C\u0003\u0011a\u0017M\\4\u000b\u0003\r\u000bAA[1wC&\u0011\u0001\u0005\u0011\u0005\b\r\u0002\t\t\u0011\"\u0001H\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005A\u0005C\u0001\u0007J\u0013\tQeAA\u0002J]RDq\u0001\u0014\u0001\u0002\u0002\u0013\u0005Q*\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u00059\u000b\u0006C\u0001\u0007P\u0013\t\u0001fAA\u0002B]fDqAU&\u0002\u0002\u0003\u0007\u0001*A\u0002yIEBq\u0001\u0016\u0001\u0002\u0002\u0013\u0005S+A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u00051\u0006cA,[\u001d6\t\u0001L\u0003\u0002Z\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005mC&\u0001C%uKJ\fGo\u001c:\t\u000fu\u0003\u0011\u0011!C\u0001=\u0006A1-\u00198FcV\fG\u000e\u0006\u0002`EB\u0011A\u0002Y\u0005\u0003C\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0004S9\u0006\u0005\t\u0019\u0001(\t\u000f\u0011\u0004\u0011\u0011!C!K\u0006A\u0001.Y:i\u0007>$W\rF\u0001I\u0011\u001d9\u0007!!A\u0005B!\fa!Z9vC2\u001cHCA0j\u0011\u001d\u0011f-!AA\u00029;qa\u001b\u0002\u0002\u0002#\u0005A.\u0001\u0006GCR\fG.\u0012:s_J\u0004\"\u0001K7\u0007\u000f\u0005\u0011\u0011\u0011!E\u0001]N\u0019Qn\\\u000b\u0011\tA\u001c8dJ\u0007\u0002c*\u0011!OB\u0001\beVtG/[7f\u0013\t!\u0018OA\tBEN$(/Y2u\rVt7\r^5p]FBQ!J7\u0005\u0002Y$\u0012\u0001\u001c\u0005\bq6\f\t\u0011\"\u0012z\u0003!!xn\u0015;sS:<G#\u0001 \t\u000fml\u0017\u0011!CAy\u0006)\u0011\r\u001d9msR\u0011q% \u0005\u00063i\u0004\ra\u0007\u0005\t\u007f6\f\t\u0011\"!\u0002\u0002\u00059QO\\1qa2LH\u0003BA\u0002\u0003\u0013\u0001B\u0001DA\u00037%\u0019\u0011q\u0001\u0004\u0003\r=\u0003H/[8o\u0011!\tYA`A\u0001\u0002\u00049\u0013a\u0001=%a!I\u0011qB7\u0002\u0002\u0013%\u0011\u0011C\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0002\u0014A\u0019q(!\u0006\n\u0007\u0005]\u0001I\u0001\u0004PE*,7\r\u001e")
public class FatalError
extends Exception
implements Product,
Serializable {
    private final String msg;

    public static Option<String> unapply(FatalError fatalError) {
        return FatalError$.MODULE$.unapply(fatalError);
    }

    public static FatalError apply(String string2) {
        return FatalError$.MODULE$.apply(string2);
    }

    public static <A> Function1<String, A> andThen(Function1<FatalError, A> function1) {
        return FatalError$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, FatalError> compose(Function1<A, String> function1) {
        return FatalError$.MODULE$.compose(function1);
    }

    public String msg() {
        return this.msg;
    }

    public FatalError copy(String msg) {
        return new FatalError(msg);
    }

    public String copy$default$1() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "FatalError";
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
        return x$1 instanceof FatalError;
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
        if (!(x$1 instanceof FatalError)) return false;
        boolean bl = true;
        if (!bl) return false;
        FatalError fatalError = (FatalError)x$1;
        String string2 = this.msg();
        String string3 = fatalError.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!fatalError.canEqual(this)) return false;
        return true;
    }

    public FatalError(String msg) {
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }
}

