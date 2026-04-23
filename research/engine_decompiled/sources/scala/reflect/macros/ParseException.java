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
import scala.reflect.macros.ParseException$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\rc\u0001B\u0001\u0003\u0001&\u0011a\u0002U1sg\u0016,\u0005pY3qi&|gN\u0003\u0002\u0004\t\u00051Q.Y2s_NT!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001Q!#\u0006\t\u0003\u0017=q!\u0001D\u0007\u000e\u0003\u0019I!A\u0004\u0004\u0002\u000fA\f7m[1hK&\u0011\u0001#\u0005\u0002\n\u000bb\u001cW\r\u001d;j_:T!A\u0004\u0004\u0011\u00051\u0019\u0012B\u0001\u000b\u0007\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0004\f\n\u0005]1!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C\r\u0001\u0005+\u0007I\u0011\u0001\u000e\u0002\u0007A|7/F\u0001\u001c!\tar$D\u0001\u001e\u0015\tqB!A\u0002ba&L!\u0001I\u000f\u0003\u0011A{7/\u001b;j_:D\u0001B\t\u0001\u0003\u0012\u0003\u0006IaG\u0001\u0005a>\u001c\b\u0005\u0003\u0005%\u0001\tU\r\u0011\"\u0001&\u0003\ri7oZ\u000b\u0002MA\u0011qE\u000b\b\u0003\u0019!J!!\u000b\u0004\u0002\rA\u0013X\rZ3g\u0013\tYCF\u0001\u0004TiJLgn\u001a\u0006\u0003S\u0019A\u0001B\f\u0001\u0003\u0012\u0003\u0006IAJ\u0001\u0005[N<\u0007\u0005C\u00031\u0001\u0011\u0005\u0011'\u0001\u0004=S:LGO\u0010\u000b\u0004eQ*\u0004CA\u001a\u0001\u001b\u0005\u0011\u0001\"B\r0\u0001\u0004Y\u0002\"\u0002\u00130\u0001\u00041\u0003bB\u001c\u0001\u0003\u0003%\t\u0001O\u0001\u0005G>\u0004\u0018\u0010F\u00023siBq!\u0007\u001c\u0011\u0002\u0003\u00071\u0004C\u0004%mA\u0005\t\u0019\u0001\u0014\t\u000fq\u0002\u0011\u0013!C\u0001{\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001 +\u0005my4&\u0001!\u0011\u0005\u00053U\"\u0001\"\u000b\u0005\r#\u0015!C;oG\",7m[3e\u0015\t)e!\u0001\u0006b]:|G/\u0019;j_:L!a\u0012\"\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0004J\u0001E\u0005I\u0011\u0001&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t1J\u000b\u0002'\u007f!9Q\nAA\u0001\n\u0003r\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001P!\t\u0001V+D\u0001R\u0015\t\u00116+\u0001\u0003mC:<'\"\u0001+\u0002\t)\fg/Y\u0005\u0003WECqa\u0016\u0001\u0002\u0002\u0013\u0005\u0001,\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001Z!\ta!,\u0003\u0002\\\r\t\u0019\u0011J\u001c;\t\u000fu\u0003\u0011\u0011!C\u0001=\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA0c!\ta\u0001-\u0003\u0002b\r\t\u0019\u0011I\\=\t\u000f\rd\u0016\u0011!a\u00013\u0006\u0019\u0001\u0010J\u0019\t\u000f\u0015\u0004\u0011\u0011!C!M\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001h!\rA7nX\u0007\u0002S*\u0011!NB\u0001\u000bG>dG.Z2uS>t\u0017B\u00017j\u0005!IE/\u001a:bi>\u0014\bb\u00028\u0001\u0003\u0003%\ta\\\u0001\tG\u0006tW)];bYR\u0011\u0001o\u001d\t\u0003\u0019EL!A\u001d\u0004\u0003\u000f\t{w\u000e\\3b]\"91-\\A\u0001\u0002\u0004y\u0006bB;\u0001\u0003\u0003%\tE^\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011\fC\u0004y\u0001\u0005\u0005I\u0011I=\u0002\r\u0015\fX/\u00197t)\t\u0001(\u0010C\u0004do\u0006\u0005\t\u0019A0\b\u000fq\u0014\u0011\u0011!E\u0001{\u0006q\u0001+\u0019:tK\u0016C8-\u001a9uS>t\u0007CA\u001a\u007f\r\u001d\t!!!A\t\u0002}\u001cBA`A\u0001+A9\u00111AA\u00057\u0019\u0012TBAA\u0003\u0015\r\t9AB\u0001\beVtG/[7f\u0013\u0011\tY!!\u0002\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0003\u00041}\u0012\u0005\u0011q\u0002\u000b\u0002{\"I\u00111\u0003@\u0002\u0002\u0013\u0015\u0013QC\u0001\ti>\u001cFO]5oOR\tq\nC\u0005\u0002\u001ay\f\t\u0011\"!\u0002\u001c\u0005)\u0011\r\u001d9msR)!'!\b\u0002 !1\u0011$a\u0006A\u0002mAa\u0001JA\f\u0001\u00041\u0003\"CA\u0012}\u0006\u0005I\u0011QA\u0013\u0003\u001d)h.\u00199qYf$B!a\n\u00024A)A\"!\u000b\u0002.%\u0019\u00111\u0006\u0004\u0003\r=\u0003H/[8o!\u0015a\u0011qF\u000e'\u0013\r\t\tD\u0002\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\u0005U\u0012\u0011EA\u0001\u0002\u0004\u0011\u0014a\u0001=%a!I\u0011\u0011\b@\u0002\u0002\u0013%\u00111H\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0002>A\u0019\u0001+a\u0010\n\u0007\u0005\u0005\u0013K\u0001\u0004PE*,7\r\u001e")
public class ParseException
extends Exception
implements Product,
Serializable {
    private final Position pos;
    private final String msg;

    public static Option<Tuple2<Position, String>> unapply(ParseException parseException) {
        return ParseException$.MODULE$.unapply(parseException);
    }

    public static ParseException apply(Position position, String string2) {
        return ParseException$.MODULE$.apply(position, string2);
    }

    public static Function1<Tuple2<Position, String>, ParseException> tupled() {
        return ParseException$.MODULE$.tupled();
    }

    public static Function1<Position, Function1<String, ParseException>> curried() {
        return ParseException$.MODULE$.curried();
    }

    public Position pos() {
        return this.pos;
    }

    public String msg() {
        return this.msg;
    }

    public ParseException copy(Position pos, String msg) {
        return new ParseException(pos, msg);
    }

    public Position copy$default$1() {
        return this.pos();
    }

    public String copy$default$2() {
        return this.msg();
    }

    @Override
    public String productPrefix() {
        return "ParseException";
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
        return x$1 instanceof ParseException;
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
        if (!(x$1 instanceof ParseException)) return false;
        boolean bl = true;
        if (!bl) return false;
        ParseException parseException = (ParseException)x$1;
        Position position = this.pos();
        Position position2 = parseException.pos();
        if (position == null) {
            if (position2 != null) {
                return false;
            }
        } else if (!position.equals(position2)) return false;
        String string2 = this.msg();
        String string3 = parseException.msg();
        if (string2 == null) {
            if (string3 != null) {
                return false;
            }
        } else if (!string2.equals(string3)) return false;
        if (!parseException.canEqual(this)) return false;
        return true;
    }

    public ParseException(Position pos, String msg) {
        this.pos = pos;
        this.msg = msg;
        super(msg);
        Product$class.$init$(this);
    }
}

