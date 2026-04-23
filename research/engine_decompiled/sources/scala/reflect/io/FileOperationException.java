/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.io.FileOperationException$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005ea\u0001B\u0001\u0003\u0001&\u0011aCR5mK>\u0003XM]1uS>tW\t_2faRLwN\u001c\u0006\u0003\u0007\u0011\t!![8\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001\u000b%U\u0001\"aC\b\u000f\u00051iQ\"\u0001\u0004\n\u000591\u0011a\u00029bG.\fw-Z\u0005\u0003!E\u0011\u0001CU;oi&lW-\u0012=dKB$\u0018n\u001c8\u000b\u000591\u0001C\u0001\u0007\u0014\u0013\t!bAA\u0004Qe>$Wo\u0019;\u0011\u000511\u0012BA\f\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!I\u0002A!f\u0001\n\u0003Q\u0012aA7tOV\t1\u0004\u0005\u0002\u001d?9\u0011A\"H\u0005\u0003=\u0019\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0011\"\u0005\u0019\u0019FO]5oO*\u0011aD\u0002\u0005\tG\u0001\u0011\t\u0012)A\u00057\u0005!Qn]4!\u0011\u0015)\u0003\u0001\"\u0001'\u0003\u0019a\u0014N\\5u}Q\u0011q%\u000b\t\u0003Q\u0001i\u0011A\u0001\u0005\u00063\u0011\u0002\ra\u0007\u0005\bW\u0001\t\t\u0011\"\u0001-\u0003\u0011\u0019w\u000e]=\u0015\u0005\u001dj\u0003bB\r+!\u0003\u0005\ra\u0007\u0005\b_\u0001\t\n\u0011\"\u00011\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\r\u0016\u00037IZ\u0013a\r\t\u0003iej\u0011!\u000e\u0006\u0003m]\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005a2\u0011AC1o]>$\u0018\r^5p]&\u0011!(\u000e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007b\u0002\u001f\u0001\u0003\u0003%\t%P\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003y\u0002\"a\u0010#\u000e\u0003\u0001S!!\u0011\"\u0002\t1\fgn\u001a\u0006\u0002\u0007\u0006!!.\u0019<b\u0013\t\u0001\u0003\tC\u0004G\u0001\u0005\u0005I\u0011A$\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003!\u0003\"\u0001D%\n\u0005)3!aA%oi\"9A\nAA\u0001\n\u0003i\u0015A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003\u001dF\u0003\"\u0001D(\n\u0005A3!aA!os\"9!kSA\u0001\u0002\u0004A\u0015a\u0001=%c!9A\u000bAA\u0001\n\u0003*\u0016a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003Y\u00032a\u0016.O\u001b\u0005A&BA-\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u00037b\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\b;\u0002\t\t\u0011\"\u0001_\u0003!\u0019\u0017M\\#rk\u0006dGCA0c!\ta\u0001-\u0003\u0002b\r\t9!i\\8mK\u0006t\u0007b\u0002*]\u0003\u0003\u0005\rA\u0014\u0005\bI\u0002\t\t\u0011\"\u0011f\u0003!A\u0017m\u001d5D_\u0012,G#\u0001%\t\u000f\u001d\u0004\u0011\u0011!C!Q\u00061Q-];bYN$\"aX5\t\u000fI3\u0017\u0011!a\u0001\u001d\u001e91NAA\u0001\u0012\u0003a\u0017A\u0006$jY\u0016|\u0005/\u001a:bi&|g.\u0012=dKB$\u0018n\u001c8\u0011\u0005!jgaB\u0001\u0003\u0003\u0003E\tA\\\n\u0004[>,\u0002\u0003\u00029t7\u001dj\u0011!\u001d\u0006\u0003e\u001a\tqA];oi&lW-\u0003\u0002uc\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\t\u000b\u0015jG\u0011\u0001<\u0015\u00031Dq\u0001_7\u0002\u0002\u0013\u0015\u00130\u0001\u0005u_N#(/\u001b8h)\u0005q\u0004bB>n\u0003\u0003%\t\t`\u0001\u0006CB\u0004H.\u001f\u000b\u0003OuDQ!\u0007>A\u0002mA\u0001b`7\u0002\u0002\u0013\u0005\u0015\u0011A\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019!!\u0003\u0011\t1\t)aG\u0005\u0004\u0003\u000f1!AB(qi&|g\u000e\u0003\u0005\u0002\fy\f\t\u00111\u0001(\u0003\rAH\u0005\r\u0005\n\u0003\u001fi\u0017\u0011!C\u0005\u0003#\t1B]3bIJ+7o\u001c7wKR\u0011\u00111\u0003\t\u0004\u007f\u0005U\u0011bAA\f\u0001\n1qJ\u00196fGR\u0004")
public class FileOperationException
extends RuntimeException
implements Product,
Serializable {
    private final String msg;

    public static Option<String> unapply(FileOperationException fileOperationException) {
        return FileOperationException$.MODULE$.unapply(fileOperationException);
    }

    public static FileOperationException apply(String string2) {
        return FileOperationException$.MODULE$.apply(string2);
    }

    public static <A> Function1<String, A> andThen(Function1<FileOperationException, A> function1) {
        return FileOperationException$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, FileOperationException> compose(Function1<A, String> function1) {
        return FileOperationException$.MODULE$.compose(function1);
    }

    public String msg() {
        return this.msg;
    }

    public FileOperationException copy(String msg) {
        return new FileOperationException(msg);
    }

    public String copy$default$1() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "FileOperationException";
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
        return x$1 instanceof FileOperationException;
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
        if (!(x$1 instanceof FileOperationException)) return false;
        boolean bl = true;
        if (!bl) return false;
        FileOperationException fileOperationException = (FileOperationException)x$1;
        String string2 = this.msg();
        String string3 = fileOperationException.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!fileOperationException.canEqual(this)) return false;
        return true;
    }

    public FileOperationException(String msg) {
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }
}

