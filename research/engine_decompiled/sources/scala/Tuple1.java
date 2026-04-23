/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Option;
import scala.Product$class;
import scala.Product1;
import scala.Product1$class;
import scala.Serializable;
import scala.Tuple1$;
import scala.Tuple1$mcD$sp;
import scala.Tuple1$mcI$sp;
import scala.Tuple1$mcJ$sp;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dd\u0001B\u0001\u0003\u0001\u0016\u0011a\u0001V;qY\u0016\f$\"A\u0002\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011a\u0001E\n\u0006\u0001\u001dY\u0011\u0006\f\t\u0003\u0011%i\u0011AA\u0005\u0003\u0015\t\u0011a!\u00118z%\u00164\u0007c\u0001\u0005\r\u001d%\u0011QB\u0001\u0002\t!J|G-^2ucA\u0011q\u0002\u0005\u0007\u0001\t%\t\u0002\u0001)A\u0001\n\u000b\u0007!C\u0001\u0002UcE\u00111C\u0006\t\u0003\u0011QI!!\u0006\u0002\u0003\u000f9{G\u000f[5oOB\u0011\u0001bF\u0005\u00031\t\u00111!\u00118zQ\u0015\u0001\"$H\u0011&!\tA1$\u0003\u0002\u001d\u0005\tY1\u000f]3dS\u0006d\u0017N_3ec\u0011!cd\b\u0011\u000f\u0005!y\u0012B\u0001\u0011\u0003\u0003\rIe\u000e^\u0019\u0005I\t\u001aCE\u0004\u0002\tG%\u0011AEA\u0001\u0005\u0019>tw-\r\u0003%M\u001dBcB\u0001\u0005(\u0013\tA#!\u0001\u0004E_V\u0014G.\u001a\t\u0003\u0011)J!a\u000b\u0002\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001\"L\u0005\u0003]\t\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\u0002\r\u0001\u0003\u0016\u0004%\t!M\u0001\u0003?F*\u0012A\u0004\u0005\tg\u0001\u0011\t\u0012)A\u0005\u001d\u0005\u0019q,\r\u0011\t\u000bU\u0002A\u0011\u0001\u001c\u0002\rqJg.\u001b;?)\t9\u0004\bE\u0002\t\u00019AQ\u0001\r\u001bA\u00029AQA\u000f\u0001\u0005Bm\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002yA\u0011QHQ\u0007\u0002})\u0011q\bQ\u0001\u0005Y\u0006twMC\u0001B\u0003\u0011Q\u0017M^1\n\u0005\rs$AB*ue&tw\rC\u0004F\u0001\u0005\u0005I\u0011\u0001$\u0002\t\r|\u0007/_\u000b\u0003\u000f*#\"\u0001S(\u0011\u0007!\u0001\u0011\n\u0005\u0002\u0010\u0015\u0012I\u0011\u0003\u0012Q\u0001\u0002\u0003\u0015\rA\u0005\u0015\u0006\u0015jaUJT\u0019\u0005Iyy\u0002%\r\u0003%E\r\"\u0013\u0007\u0002\u0013'O!Bq\u0001\r#\u0011\u0002\u0003\u0007\u0011\nC\u0004R\u0001E\u0005I\u0011\u0001*\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u00111KX\u000b\u0002)*\u0012a\"V\u0016\u0002-B\u0011q\u000bX\u0007\u00021*\u0011\u0011LW\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0017\u0002\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002^1\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u0013E\u0001\u0006\u0015!A\u0001\u0006\u0004\u0011\u0002&\u00020\u001bA\u0006\u0014\u0017\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)\u0011\u001d!\u0007!!A\u0005B\u0015\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001\u001f\t\u000f\u001d\u0004\u0011\u0011!C!Q\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001j!\rQWNF\u0007\u0002W*\u0011ANA\u0001\u000bG>dG.Z2uS>t\u0017B\u00018l\u0005!IE/\u001a:bi>\u0014\bb\u00029\u0001\u0003\u0003%\t!]\u0001\tG\u0006tW)];bYR\u0011!/\u001e\t\u0003\u0011ML!\u0001\u001e\u0002\u0003\u000f\t{w\u000e\\3b]\"9ao\\A\u0001\u0002\u00041\u0012a\u0001=%c!9\u0001\u0010AA\u0001\n\u0003J\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003i\u0004\"\u0001C>\n\u0005q\u0014!aA%oi\"9a\u0010AA\u0001\n\u0003z\u0018AB3rk\u0006d7\u000fF\u0002s\u0003\u0003AqA^?\u0002\u0002\u0003\u0007a\u0003K\u0004\u0001\u0003\u000b\tY!a\u0004\u0011\u0007!\t9!C\u0002\u0002\n\t\u0011Q\u0003Z3qe\u0016\u001c\u0017\r^3e\u0013:DWM]5uC:\u001cW-\t\u0002\u0002\u000e\u0005qC+\u001e9mKN\u0004s/\u001b7mA\t,\u0007%\\1eK\u00022\u0017N\\1mA%t\u0007%\u0019\u0011gkR,(/\u001a\u0011wKJ\u001c\u0018n\u001c8/C\t\t\t\"\u0001\u00043]E\nd\u0006M\u0004\n\u0003+\u0011\u0011\u0011!E\u0001\u0003/\ta\u0001V;qY\u0016\f\u0004c\u0001\u0005\u0002\u001a\u0019A\u0011AAA\u0001\u0012\u0003\tYb\u0005\u0003\u0002\u001a\u001da\u0003bB\u001b\u0002\u001a\u0011\u0005\u0011q\u0004\u000b\u0003\u0003/A\u0001BOA\r\u0003\u0003%)e\u000f\u0005\u000b\u0003K\tI\"!A\u0005\u0002\u0006\u001d\u0012!B1qa2LX\u0003BA\u0015\u0003_!B!a\u000b\u0002:A!\u0001\u0002AA\u0017!\ry\u0011q\u0006\u0003\u000b#\u0005\r\u0002\u0015!A\u0001\u0006\u0004\u0011\u0002&CA\u00185\u0005M\u0012QGA\u001cc\u0011!cd\b\u00112\t\u0011\u00123\u0005J\u0019\u0005I\u0019:\u0003\u0006C\u00041\u0003G\u0001\r!!\f\t\u0015\u0005u\u0012\u0011DA\u0001\n\u0003\u000by$A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005\u0005\u00131\n\u000b\u0005\u0003\u0007\n)\u0006E\u0003\t\u0003\u000b\nI%C\u0002\u0002H\t\u0011aa\u00149uS>t\u0007cA\b\u0002L\u0011Q\u0011#a\u000f!\u0002\u0003\u0005)\u0019\u0001\n)\u0013\u0005-#$a\u0014\u0002R\u0005M\u0013\u0007\u0002\u0013\u001f?\u0001\nD\u0001\n\u0012$IE\"AEJ\u0014)\u0011)\t9&a\u000f\u0002\u0002\u0003\u0007\u0011\u0011L\u0001\u0004q\u0012\u0002\u0004\u0003\u0002\u0005\u0001\u0003\u0013B!\"!\u0018\u0002\u001a\u0005\u0005I\u0011BA0\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\u0005\u0005\u0004cA\u001f\u0002d%\u0019\u0011Q\r \u0003\r=\u0013'.Z2u\u0001")
public class Tuple1<T1>
implements Product1<T1>,
Serializable {
    public final T1 _1;

    public static <T1> Option<T1> unapply(Tuple1<T1> tuple1) {
        return Tuple1$.MODULE$.unapply(tuple1);
    }

    public static <T1> Tuple1<T1> apply(T1 T1) {
        return Tuple1$.MODULE$.apply(T1);
    }

    @Override
    public int productArity() {
        return Product1$class.productArity(this);
    }

    @Override
    public Object productElement(int n) throws IndexOutOfBoundsException {
        return Product1$class.productElement(this, n);
    }

    @Override
    public T1 _1() {
        return this._1;
    }

    public String toString() {
        return new StringBuilder().append((Object)"(").append(this._1()).append((Object)")").toString();
    }

    public <T1> Tuple1<T1> copy(T1 _1) {
        return new Tuple1<T1>(_1);
    }

    public <T1> T1 copy$default$1() {
        return this._1();
    }

    @Override
    public String productPrefix() {
        return "Tuple1";
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Tuple1;
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
        boolean bl;
        if (this == x$1) return true;
        if (!(x$1 instanceof Tuple1)) return false;
        boolean bl2 = true;
        if (!bl2) return false;
        Tuple1 tuple1 = (Tuple1)x$1;
        T1 T1 = tuple1._1();
        T1 T12 = this._1();
        if (T12 == T1) {
            bl = true;
        } else {
            if (T12 == null) {
                return false;
            }
            bl = T12 instanceof Number ? BoxesRunTime.equalsNumObject((Number)T12, T1) : (T12 instanceof Character ? BoxesRunTime.equalsCharObject((Character)T12, T1) : T12.equals(T1));
        }
        if (!bl) return false;
        if (!tuple1.canEqual(this)) return false;
        return true;
    }

    @Override
    public double _1$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this._1());
    }

    @Override
    public int _1$mcI$sp() {
        return BoxesRunTime.unboxToInt(this._1());
    }

    @Override
    public long _1$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this._1());
    }

    public Tuple1<Object> copy$mDc$sp(double _1) {
        return new Tuple1$mcD$sp(_1);
    }

    public Tuple1<Object> copy$mIc$sp(int _1) {
        return new Tuple1$mcI$sp(_1);
    }

    public Tuple1<Object> copy$mJc$sp(long _1) {
        return new Tuple1$mcJ$sp(_1);
    }

    public <T1> double copy$default$1$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this.copy$default$1());
    }

    public <T1> int copy$default$1$mcI$sp() {
        return BoxesRunTime.unboxToInt(this.copy$default$1());
    }

    public <T1> long copy$default$1$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this.copy$default$1());
    }

    public boolean specInstance$() {
        return false;
    }

    public Tuple1(T1 _1) {
        this._1 = _1;
        Product$class.$init$(this);
        Product1$class.$init$(this);
    }
}

