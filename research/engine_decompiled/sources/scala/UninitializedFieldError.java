/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.UninitializedFieldError$;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005]a\u0001B\u0001\u0003\u0005\u0016\u0011q#\u00168j]&$\u0018.\u00197ju\u0016$g)[3mI\u0016\u0013(o\u001c:\u000b\u0003\r\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001\r9\t\u0002CA\u0004\f\u001d\tA\u0011\"D\u0001\u0003\u0013\tQ!!A\u0004qC\u000e\\\u0017mZ3\n\u00051i!\u0001\u0005*v]RLW.Z#yG\u0016\u0004H/[8o\u0015\tQ!\u0001\u0005\u0002\t\u001f%\u0011\u0001C\u0001\u0002\b!J|G-^2u!\tA!#\u0003\u0002\u0014\u0005\ta1+\u001a:jC2L'0\u00192mK\"AQ\u0003\u0001BK\u0002\u0013\u0005a#A\u0002ng\u001e,\u0012a\u0006\t\u00031mq!\u0001C\r\n\u0005i\u0011\u0011A\u0002)sK\u0012,g-\u0003\u0002\u001d;\t11\u000b\u001e:j]\u001eT!A\u0007\u0002\t\u0011}\u0001!\u0011#Q\u0001\n]\tA!\\:hA!)\u0011\u0005\u0001C\u0001E\u00051A(\u001b8jiz\"\"a\t\u0013\u0011\u0005!\u0001\u0001\"B\u000b!\u0001\u00049\u0002\"B\u0011\u0001\t\u00031CCA\u0012(\u0011\u0015AS\u00051\u0001*\u0003\ry'M\u001b\t\u0003\u0011)J!a\u000b\u0002\u0003\u0007\u0005s\u0017\u0010C\u0004.\u0001\u0005\u0005I\u0011\u0001\u0018\u0002\t\r|\u0007/\u001f\u000b\u0003G=Bq!\u0006\u0017\u0011\u0002\u0003\u0007q\u0003C\u00042\u0001E\u0005I\u0011\u0001\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1G\u000b\u0002\u0018i-\nQ\u0007\u0005\u00027w5\tqG\u0003\u00029s\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003u\t\t!\"\u00198o_R\fG/[8o\u0013\tatGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016DqA\u0010\u0001\u0002\u0002\u0013\u0005s(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0001B\u0011\u0011IR\u0007\u0002\u0005*\u00111\tR\u0001\u0005Y\u0006twMC\u0001F\u0003\u0011Q\u0017M^1\n\u0005q\u0011\u0005b\u0002%\u0001\u0003\u0003%\t!S\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002\u0015B\u0011\u0001bS\u0005\u0003\u0019\n\u00111!\u00138u\u0011\u001dq\u0005!!A\u0005\u0002=\u000ba\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002*!\"9\u0011+TA\u0001\u0002\u0004Q\u0015a\u0001=%c!91\u000bAA\u0001\n\u0003\"\u0016a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003U\u00032AV-*\u001b\u00059&B\u0001-\u0003\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u00035^\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\b9\u0002\t\t\u0011\"\u0001^\u0003!\u0019\u0017M\\#rk\u0006dGC\u00010b!\tAq,\u0003\u0002a\u0005\t9!i\\8mK\u0006t\u0007bB)\\\u0003\u0003\u0005\r!\u000b\u0005\bG\u0002\t\t\u0011\"\u0011e\u0003!A\u0017m\u001d5D_\u0012,G#\u0001&\t\u000f\u0019\u0004\u0011\u0011!C!O\u00061Q-];bYN$\"A\u00185\t\u000fE+\u0017\u0011!a\u0001S\u001d9!NAA\u0001\u0012\u0003Y\u0017aF+oS:LG/[1mSj,GMR5fY\u0012,%O]8s!\tAANB\u0004\u0002\u0005\u0005\u0005\t\u0012A7\u0014\u00071t\u0017\u0003\u0005\u0003pe^\u0019S\"\u00019\u000b\u0005E\u0014\u0011a\u0002:v]RLW.Z\u0005\u0003gB\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82\u0011\u0015\tC\u000e\"\u0001v)\u0005Y\u0007bB<m\u0003\u0003%)\u0005_\u0001\ti>\u001cFO]5oOR\t\u0001\tC\u0004{Y\u0006\u0005I\u0011Q>\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005\rb\b\"B\u000bz\u0001\u00049\u0002b\u0002@m\u0003\u0003%\ti`\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t!a\u0002\u0011\t!\t\u0019aF\u0005\u0004\u0003\u000b\u0011!AB(qi&|g\u000e\u0003\u0005\u0002\nu\f\t\u00111\u0001$\u0003\rAH\u0005\r\u0005\n\u0003\u001ba\u0017\u0011!C\u0005\u0003\u001f\t1B]3bIJ+7o\u001c7wKR\u0011\u0011\u0011\u0003\t\u0004\u0003\u0006M\u0011bAA\u000b\u0005\n1qJ\u00196fGR\u0004")
public final class UninitializedFieldError
extends RuntimeException
implements Product,
Serializable {
    private final String msg;

    public static Option<String> unapply(UninitializedFieldError uninitializedFieldError) {
        return UninitializedFieldError$.MODULE$.unapply(uninitializedFieldError);
    }

    public static UninitializedFieldError apply(String string2) {
        return UninitializedFieldError$.MODULE$.apply(string2);
    }

    public static <A> Function1<String, A> andThen(Function1<UninitializedFieldError, A> function1) {
        return UninitializedFieldError$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, UninitializedFieldError> compose(Function1<A, String> function1) {
        return UninitializedFieldError$.MODULE$.compose(function1);
    }

    public String msg() {
        return this.msg;
    }

    public UninitializedFieldError copy(String msg) {
        return new UninitializedFieldError(msg);
    }

    public String copy$default$1() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "UninitializedFieldError";
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
        return x$1 instanceof UninitializedFieldError;
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
        if (!(x$1 instanceof UninitializedFieldError)) return false;
        boolean bl = true;
        if (!bl) return false;
        UninitializedFieldError uninitializedFieldError = (UninitializedFieldError)x$1;
        String string2 = this.msg();
        String string3 = uninitializedFieldError.msg();
        if (string2 != null) {
            if (!string2.equals(string3)) return false;
            return true;
        }
        if (string3 == null) return true;
        return false;
    }

    public UninitializedFieldError(String msg) {
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }

    public UninitializedFieldError(Object obj) {
        this(String.valueOf(obj));
    }
}

