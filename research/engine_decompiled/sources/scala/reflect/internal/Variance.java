/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Variance$;

@ScalaSignature(bytes="\u0006\u0001\u0005Ue\u0001B\u0001\u0003\u0005%\u0011\u0001BV1sS\u0006t7-\u001a\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osZ\u000bG\u000e\u0003\u0005\u0010\u0001\t\u0015\r\u0011\"\u0001\u0011\u0003\u00151G.Y4t+\u0005\t\u0002CA\u0006\u0013\u0013\t\u0019bAA\u0002J]RD\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006I!E\u0001\u0007M2\fwm\u001d\u0011\t\u0019]\u0001A\u0011!A\u0001\u0002\u0003\u0005I\u0011\u0002\r\u0002\rqJg.\u001b;?)\tI2\u0004\u0005\u0002\u001b\u00015\t!\u0001C\u0003\u0010-\u0001\u0007\u0011\u0003C\u0003\u001e\u0001\u0011\u0005a$A\u0006jg\nKg/\u0019:jC:$X#A\u0010\u0011\u0005-\u0001\u0013BA\u0011\u0007\u0005\u001d\u0011un\u001c7fC:DQa\t\u0001\u0005\u0002y\t1\"[:D_Z\f'/[1oi\")Q\u0005\u0001C\u0001=\u0005Y\u0011n]%om\u0006\u0014\u0018.\u00198u\u0011\u00159\u0003\u0001\"\u0001\u001f\u0003=I7oQ8oiJ\fg/\u0019:jC:$\b\"B\u0015\u0001\t\u0003q\u0012AC5t!>\u001c\u0018\u000e^5wK\")1\u0006\u0001C\u0001Y\u0005!A%Y7q)\tIR\u0006C\u0003/U\u0001\u0007\u0011$A\u0003pi\",'\u000fC\u00031\u0001\u0011\u0005\u0011'\u0001\u0004%i&lWm\u001d\u000b\u00033IBQAL\u0018A\u0002eAQ\u0001\u000e\u0001\u0005\u0002U\nAA\u001a7jaV\t\u0011\u0004C\u00038\u0001\u0011\u0005Q'A\u0002dkRDQ!\u000f\u0001\u0005\u0002i\nab]=nE>d\u0017nY*ue&tw-F\u0001<!\ta\u0014)D\u0001>\u0015\tqt(\u0001\u0003mC:<'\"\u0001!\u0002\t)\fg/Y\u0005\u0003\u0005v\u0012aa\u0015;sS:<\u0007\"\u0002#\u0001\t\u0003*\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003mBqa\u0012\u0001\u0002\u0002\u0013\u0005\u0003*\u0001\u0005iCND7i\u001c3f)\u0005\t\u0002b\u0002&\u0001\u0003\u0003%\teS\u0001\u0007KF,\u0018\r\\:\u0015\u0005}a\u0005bB'J\u0003\u0003\u0005\rAT\u0001\u0004q\u0012\n\u0004CA\u0006P\u0013\t\u0001fAA\u0002B]f<QA\u0015\u0002\t\u0002M\u000b\u0001BV1sS\u0006t7-\u001a\t\u00035Q3Q!\u0001\u0002\t\u0002U\u001b\"\u0001\u0016,\u0011\u0005-9\u0016B\u0001-\u0007\u0005\u0019\te.\u001f*fM\")q\u0003\u0016C\u00015R\t1K\u0002\u0003])\u0006i&!C*ci\u000e{W\u000e]1u'\tYf\u000b\u0003\u0005`7\n\u0015\r\u0011\"\u00016\u0003\u00051\b\u0002C1\\\u0005\u0003\u0005\u000b\u0011B\r\u0002\u0005Y\u0004\u0003\"B\f\\\t\u0003\u0019GC\u00013g!\t)7,D\u0001U\u0011\u0015y&\r1\u0001\u001a\u0011\u0015A7\f\"\u0001j\u0003\u0015!C.Z:t)\ty\"\u000eC\u0003/O\u0002\u0007\u0011\u0003C\u0003m7\u0012\u0005Q.\u0001\u0005%OJ,\u0017\r^3s)\tyb\u000eC\u0003/W\u0002\u0007\u0011\u0003C\u0004q)\u0006\u0005I1A9\u0002\u0013M\u0013GoQ8na\u0006$HC\u00013s\u0011\u0015yv\u000e1\u0001\u001a\u0011\u0015!H\u000b\"\u0001v\u0003\u00111w\u000e\u001c3\u0015\u0005e1\b\"B<t\u0001\u0004A\u0018!\u0003<be&\fgnY3t!\rIH0\u0007\b\u0003\u0017iL!a\u001f\u0004\u0002\u000fA\f7m[1hK&\u0011QP \u0002\u0005\u0019&\u001cHO\u0003\u0002|\r!A\u0011\u0011\u0001+C\u0002\u0013\u0005Q'A\u0005CSZ\f'/[1oi\"9\u0011Q\u0001+!\u0002\u0013I\u0012A\u0003\"jm\u0006\u0014\u0018.\u00198uA!A\u0011\u0011\u0002+C\u0002\u0013\u0005Q'A\u0005D_Z\f'/[1oi\"9\u0011Q\u0002+!\u0002\u0013I\u0012AC\"pm\u0006\u0014\u0018.\u00198uA!A\u0011\u0011\u0003+C\u0002\u0013\u0005Q'A\u0007D_:$(/\u0019<be&\fg\u000e\u001e\u0005\b\u0003+!\u0006\u0015!\u0003\u001a\u00039\u0019uN\u001c;sCZ\f'/[1oi\u0002B\u0001\"!\u0007U\u0005\u0004%\t!N\u0001\n\u0013:4\u0018M]5b]RDq!!\bUA\u0003%\u0011$\u0001\u0006J]Z\f'/[1oi\u0002Bq!!\tU\t\u000b\t\u0019#A\u000bjg\nKg/\u0019:jC:$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007}\t)\u0003C\u0004\u0002(\u0005}\u0001\u0019A\r\u0002\u000b\u0011\"\b.[:\t\u000f\u0005-B\u000b\"\u0002\u0002.\u0005)\u0012n]\"pm\u0006\u0014\u0018.\u00198uI\u0015DH/\u001a8tS>tGcA\u0010\u00020!9\u0011qEA\u0015\u0001\u0004I\u0002bBA\u001a)\u0012\u0015\u0011QG\u0001\u0016SNLeN^1sS\u0006tG\u000fJ3yi\u0016t7/[8o)\ry\u0012q\u0007\u0005\b\u0003O\t\t\u00041\u0001\u001a\u0011\u001d\tY\u0004\u0016C\u0003\u0003{\t\u0011$[:D_:$(/\u0019<be&\fg\u000e\u001e\u0013fqR,gn]5p]R\u0019q$a\u0010\t\u000f\u0005\u001d\u0012\u0011\ba\u00013!9\u00111\t+\u0005\u0006\u0005\u0015\u0013\u0001F5t!>\u001c\u0018\u000e^5wK\u0012*\u0007\u0010^3og&|g\u000eF\u0002 \u0003\u000fBq!a\n\u0002B\u0001\u0007\u0011\u0004C\u0004\u0002LQ#)!!\u0014\u0002\u001d\u0011\nW\u000e\u001d\u0013fqR,gn]5p]R!\u0011qJA*)\rI\u0012\u0011\u000b\u0005\u0007]\u0005%\u0003\u0019A\r\t\u000f\u0005\u001d\u0012\u0011\na\u00013!9\u0011q\u000b+\u0005\u0006\u0005e\u0013\u0001\u0005\u0013uS6,7\u000fJ3yi\u0016t7/[8o)\u0011\tY&a\u0018\u0015\u0007e\ti\u0006\u0003\u0004/\u0003+\u0002\r!\u0007\u0005\b\u0003O\t)\u00061\u0001\u001a\u0011\u001d\t\u0019\u0007\u0016C\u0003\u0003K\naB\u001a7ja\u0012*\u0007\u0010^3og&|g\u000eF\u0002\u001a\u0003OBq!a\n\u0002b\u0001\u0007\u0011\u0004C\u0004\u0002lQ#)!!\u001c\u0002\u001b\r,H\u000fJ3yi\u0016t7/[8o)\rI\u0012q\u000e\u0005\b\u0003O\tI\u00071\u0001\u001a\u0011\u001d\t\u0019\b\u0016C\u0003\u0003k\n\u0001d]=nE>d\u0017nY*ue&tw\rJ3yi\u0016t7/[8o)\rY\u0014q\u000f\u0005\b\u0003O\t\t\b1\u0001\u001a\u0011\u001d\tY\b\u0016C\u0003\u0003{\n!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]R\u0019Q)a \t\u000f\u0005\u001d\u0012\u0011\u0010a\u00013!I\u00111\u0011+\u0002\u0002\u0013\u0015\u0011QQ\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000eF\u0002I\u0003\u000fCq!a\n\u0002\u0002\u0002\u0007\u0011\u0004C\u0005\u0002\fR\u000b\t\u0011\"\u0002\u0002\u000e\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u001f\u000b\u0019\nF\u0002 \u0003#C\u0001\"TAE\u0003\u0003\u0005\rA\u0014\u0005\b\u0003O\tI\t1\u0001\u001a\u0001")
public final class Variance {
    private final int flags;

    public static boolean equals$extension(int n, Object object) {
        return Variance$.MODULE$.equals$extension(n, object);
    }

    public static int hashCode$extension(int n) {
        return Variance$.MODULE$.hashCode$extension(n);
    }

    public static String toString$extension(int n) {
        return Variance$.MODULE$.toString$extension(n);
    }

    public static String symbolicString$extension(int n) {
        return Variance$.MODULE$.symbolicString$extension(n);
    }

    public static int cut$extension(int n) {
        return Variance$.MODULE$.cut$extension(n);
    }

    public static int flip$extension(int n) {
        return Variance$.MODULE$.flip$extension(n);
    }

    public static int $times$extension(int n, int n2) {
        return Variance$.MODULE$.$times$extension(n, n2);
    }

    public static int $amp$extension(int n, int n2) {
        return Variance$.MODULE$.$amp$extension(n, n2);
    }

    public static boolean isPositive$extension(int n) {
        return Variance$.MODULE$.isPositive$extension(n);
    }

    public static boolean isContravariant$extension(int n) {
        return Variance$.MODULE$.isContravariant$extension(n);
    }

    public static boolean isInvariant$extension(int n) {
        return Variance$.MODULE$.isInvariant$extension(n);
    }

    public static boolean isCovariant$extension(int n) {
        return Variance$.MODULE$.isCovariant$extension(n);
    }

    public static boolean isBivariant$extension(int n) {
        return Variance$.MODULE$.isBivariant$extension(n);
    }

    public static int Invariant() {
        return Variance$.MODULE$.Invariant();
    }

    public static int Contravariant() {
        return Variance$.MODULE$.Contravariant();
    }

    public static int Covariant() {
        return Variance$.MODULE$.Covariant();
    }

    public static int Bivariant() {
        return Variance$.MODULE$.Bivariant();
    }

    public static int fold(List list2) {
        return Variance$.MODULE$.fold(list2);
    }

    public static SbtCompat SbtCompat(int n) {
        return Variance$.MODULE$.SbtCompat(n);
    }

    public int flags() {
        return this.flags;
    }

    public boolean isBivariant() {
        return Variance$.MODULE$.isBivariant$extension(this.flags());
    }

    public boolean isCovariant() {
        return Variance$.MODULE$.isCovariant$extension(this.flags());
    }

    public boolean isInvariant() {
        return Variance$.MODULE$.isInvariant$extension(this.flags());
    }

    public boolean isContravariant() {
        return Variance$.MODULE$.isContravariant$extension(this.flags());
    }

    public boolean isPositive() {
        return Variance$.MODULE$.isPositive$extension(this.flags());
    }

    public int $amp(int other) {
        return Variance$.MODULE$.$amp$extension(this.flags(), other);
    }

    public int $times(int other) {
        return Variance$.MODULE$.$times$extension(this.flags(), other);
    }

    public int flip() {
        return Variance$.MODULE$.flip$extension(this.flags());
    }

    public int cut() {
        return Variance$.MODULE$.cut$extension(this.flags());
    }

    public String symbolicString() {
        return Variance$.MODULE$.symbolicString$extension(this.flags());
    }

    public String toString() {
        return Variance$.MODULE$.toString$extension(this.flags());
    }

    public int hashCode() {
        return Variance$.MODULE$.hashCode$extension(this.flags());
    }

    public boolean equals(Object x$1) {
        return Variance$.MODULE$.equals$extension(this.flags(), x$1);
    }

    public Variance(int flags) {
        this.flags = flags;
    }

    public static class SbtCompat {
        private final int v;

        public int v() {
            return this.v;
        }

        public boolean $less(int other) {
            return this.v() < other;
        }

        public boolean $greater(int other) {
            return this.v() > other;
        }

        public SbtCompat(int v) {
            this.v = v;
        }
    }
}

