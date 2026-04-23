/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.Either;
import scala.util.Right$;

@ScalaSignature(bytes="\u0006\u0001\u0005%c\u0001B\u0001\u0003\u0005\u001e\u0011QAU5hQRT!a\u0001\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001Qc\u0001\u0005\u00105M!\u0001!\u0003\u000f !\u0011Q1\"D\r\u000e\u0003\tI!\u0001\u0004\u0002\u0003\r\u0015KG\u000f[3s!\tqq\u0002\u0004\u0001\u0005\rA\u0001AQ1\u0001\u0012\u0005\u0005\t\u0015C\u0001\n\u0017!\t\u0019B#D\u0001\u0005\u0013\t)BAA\u0004O_RD\u0017N\\4\u0011\u0005M9\u0012B\u0001\r\u0005\u0005\r\te.\u001f\t\u0003\u001di!aa\u0007\u0001\u0005\u0006\u0004\t\"!\u0001\"\u0011\u0005Mi\u0012B\u0001\u0010\u0005\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\u0005\u0011\n\u0005\u0005\"!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C\u0012\u0001\u0005+\u0007I\u0011\u0001\u0013\u0002\u0003\t,\u0012!\u0007\u0005\tM\u0001\u0011\t\u0012)A\u00053\u0005\u0011!\r\t\u0005\u0006Q\u0001!\t!K\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005)Z\u0003\u0003\u0002\u0006\u0001\u001beAQaI\u0014A\u0002eAQ!\f\u0001\u0005\u00029\na![:MK\u001a$X#A\u0018\u0011\u0005M\u0001\u0014BA\u0019\u0005\u0005\u001d\u0011un\u001c7fC:DQa\r\u0001\u0005\u00029\nq![:SS\u001eDG\u000fC\u00046\u0001\u0005\u0005I\u0011\u0001\u001c\u0002\t\r|\u0007/_\u000b\u0004oibDC\u0001\u001d>!\u0011Q\u0001!O\u001e\u0011\u00059QD!\u0002\t5\u0005\u0004\t\u0002C\u0001\b=\t\u0015YBG1\u0001\u0012\u0011\u001d\u0019C\u0007%AA\u0002mBqa\u0010\u0001\u0012\u0002\u0013\u0005\u0001)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0007\u0005cU*F\u0001CU\tI2iK\u0001E!\t)%*D\u0001G\u0015\t9\u0005*A\u0005v]\u000eDWmY6fI*\u0011\u0011\nB\u0001\u000bC:tw\u000e^1uS>t\u0017BA&G\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006!y\u0012\r!\u0005\u0003\u00067y\u0012\r!\u0005\u0005\b\u001f\u0002\t\t\u0011\"\u0011Q\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u000b\u0005\u0002S/6\t1K\u0003\u0002U+\u0006!A.\u00198h\u0015\u00051\u0016\u0001\u00026bm\u0006L!\u0001W*\u0003\rM#(/\u001b8h\u0011\u001dQ\u0006!!A\u0005\u0002m\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u0018\t\u0003'uK!A\u0018\u0003\u0003\u0007%sG\u000fC\u0004a\u0001\u0005\u0005I\u0011A1\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011aC\u0019\u0005\bG~\u000b\t\u00111\u0001]\u0003\rAH%\r\u0005\bK\u0002\t\t\u0011\"\u0011g\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A4\u0011\u0007!\\g#D\u0001j\u0015\tQG!\u0001\u0006d_2dWm\u0019;j_:L!\u0001\\5\u0003\u0011%#XM]1u_JDqA\u001c\u0001\u0002\u0002\u0013\u0005q.\u0001\u0005dC:,\u0015/^1m)\ty\u0003\u000fC\u0004d[\u0006\u0005\t\u0019\u0001\f\t\u000fI\u0004\u0011\u0011!C!g\u0006A\u0001.Y:i\u0007>$W\rF\u0001]\u0011\u001d)\b!!A\u0005BY\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002#\"9\u0001\u0010AA\u0001\n\u0003J\u0018AB3rk\u0006d7\u000f\u0006\u00020u\"91m^A\u0001\u0002\u00041ra\u0002?\u0003\u0003\u0003E\t!`\u0001\u0006%&<\u0007\u000e\u001e\t\u0003\u0015y4q!\u0001\u0002\u0002\u0002#\u0005qp\u0005\u0003\u007f\u0003\u0003y\u0002cA\n\u0002\u0004%\u0019\u0011Q\u0001\u0003\u0003\r\u0005s\u0017PU3g\u0011\u0019Ac\u0010\"\u0001\u0002\nQ\tQ\u0010C\u0004v}\u0006\u0005IQ\t<\t\u0013\u0005=a0!A\u0005\u0002\u0006E\u0011!B1qa2LXCBA\n\u00033\ti\u0002\u0006\u0003\u0002\u0016\u0005}\u0001C\u0002\u0006\u0001\u0003/\tY\u0002E\u0002\u000f\u00033!a\u0001EA\u0007\u0005\u0004\t\u0002c\u0001\b\u0002\u001e\u001111$!\u0004C\u0002EAqaIA\u0007\u0001\u0004\tY\u0002C\u0005\u0002$y\f\t\u0011\"!\u0002&\u00059QO\\1qa2LXCBA\u0014\u0003w\t\t\u0004\u0006\u0003\u0002*\u0005M\u0002#B\n\u0002,\u0005=\u0012bAA\u0017\t\t1q\n\u001d;j_:\u00042ADA\u0019\t\u0019Y\u0012\u0011\u0005b\u0001#!Q\u0011QGA\u0011\u0003\u0003\u0005\r!a\u000e\u0002\u0007a$\u0003\u0007\u0005\u0004\u000b\u0001\u0005e\u0012q\u0006\t\u0004\u001d\u0005mBA\u0002\t\u0002\"\t\u0007\u0011\u0003C\u0005\u0002@y\f\t\u0011\"\u0003\u0002B\u0005Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\t\t\u0019\u0005E\u0002S\u0003\u000bJ1!a\u0012T\u0005\u0019y%M[3di\u0002")
public final class Right<A, B>
extends Either<A, B>
implements Product,
Serializable {
    private final B b;

    public static <A, B> Option<B> unapply(Right<A, B> right) {
        return Right$.MODULE$.unapply(right);
    }

    public static <A, B> Right<A, B> apply(B b) {
        return Right$.MODULE$.apply(b);
    }

    public B b() {
        return this.b;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    public <A, B> Right<A, B> copy(B b) {
        return new Right<A, B>(b);
    }

    public <A, B> B copy$default$1() {
        return this.b();
    }

    @Override
    public String productPrefix() {
        return "Right";
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
        return this.b();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Right;
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
        if (!(x$1 instanceof Right)) return false;
        boolean bl = true;
        if (!bl) return false;
        Right right = (Right)x$1;
        B b = right.b();
        B b2 = this.b();
        if (b2 == b) {
            return true;
        }
        if (b2 == null) {
            return false;
        }
        boolean bl2 = b2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)b2, b) : (b2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)b2, b) : b2.equals(b));
        if (!bl2) return false;
        return true;
    }

    public Right(B b) {
        this.b = b;
        Product$class.$init$(this);
    }
}

