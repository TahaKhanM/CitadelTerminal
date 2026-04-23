/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.concurrent.duration.Deadline$;
import scala.concurrent.duration.FiniteDuration;
import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005Ed\u0001B\u0001\u0003\u0001&\u0011\u0001\u0002R3bI2Lg.\u001a\u0006\u0003\u0007\u0011\t\u0001\u0002Z;sCRLwN\u001c\u0006\u0003\u000b\u0019\t!bY8oGV\u0014(/\u001a8u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0006\u0001)qAd\b\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007cA\b\u001859\u0011\u0001#\u0006\b\u0003#Qi\u0011A\u0005\u0006\u0003'!\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005Y1\u0011a\u00029bG.\fw-Z\u0005\u00031e\u0011qa\u0014:eKJ,GM\u0003\u0002\u0017\rA\u00111\u0004A\u0007\u0002\u0005A\u00111\"H\u0005\u0003=\u0019\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\fA%\u0011\u0011E\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tG\u0001\u0011)\u001a!C\u0001I\u0005!A/[7f+\u0005)\u0003CA\u000e'\u0013\t9#A\u0001\bGS:LG/\u001a#ve\u0006$\u0018n\u001c8\t\u0011%\u0002!\u0011#Q\u0001\n\u0015\nQ\u0001^5nK\u0002BQa\u000b\u0001\u0005\n1\na\u0001P5oSRtDC\u0001\u000e.\u0011\u0015\u0019#\u00061\u0001&\u0011\u0015y\u0003\u0001\"\u00011\u0003\u0015!\u0003\u000f\\;t)\tQ\u0012\u0007C\u00033]\u0001\u0007Q%A\u0003pi\",'\u000fC\u00035\u0001\u0011\u0005Q'\u0001\u0004%[&tWo\u001d\u000b\u00035YBQAM\u001aA\u0002\u0015BQ\u0001\u000e\u0001\u0005\u0002a\"\"!J\u001d\t\u000bI:\u0004\u0019\u0001\u000e\t\u000bm\u0002A\u0011\u0001\u0013\u0002\u0011QLW.\u001a'fMRDQ!\u0010\u0001\u0005\u0002y\n1\u0002[1t)&lW\rT3giR\tq\b\u0005\u0002\f\u0001&\u0011\u0011I\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015\u0019\u0005\u0001\"\u0001?\u0003%I7o\u0014<fe\u0012,X\rC\u0003F\u0001\u0011\u0005a)A\u0004d_6\u0004\u0018M]3\u0015\u0005\u001dS\u0005CA\u0006I\u0013\tIeAA\u0002J]RDQA\r#A\u0002iAq\u0001\u0014\u0001\u0002\u0002\u0013\u0005Q*\u0001\u0003d_BLHC\u0001\u000eO\u0011\u001d\u00193\n%AA\u0002\u0015Bq\u0001\u0015\u0001\u0012\u0002\u0013\u0005\u0011+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003IS#!J*,\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\u0013Ut7\r[3dW\u0016$'BA-\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u00037Z\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011\u001di\u0006!!A\u0005By\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A0\u0011\u0005\u0001,W\"A1\u000b\u0005\t\u001c\u0017\u0001\u00027b]\u001eT\u0011\u0001Z\u0001\u0005U\u00064\u0018-\u0003\u0002gC\n11\u000b\u001e:j]\u001eDq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011.\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001H\u0011\u001dY\u0007!!A\u0005\u00021\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002naB\u00111B\\\u0005\u0003_\u001a\u00111!\u00118z\u0011\u001d\t(.!AA\u0002\u001d\u000b1\u0001\u001f\u00132\u0011\u001d\u0019\b!!A\u0005BQ\fq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002kB\u0019a/_7\u000e\u0003]T!\u0001\u001f\u0004\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002{o\nA\u0011\n^3sCR|'\u000fC\u0004}\u0001\u0005\u0005I\u0011A?\u0002\u0011\r\fg.R9vC2$\"a\u0010@\t\u000fE\\\u0018\u0011!a\u0001[\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0001\tQ\u0006\u001c\bnQ8eKR\tq\tC\u0005\u0002\b\u0001\t\t\u0011\"\u0011\u0002\n\u0005AAo\\*ue&tw\rF\u0001`\u0011%\ti\u0001AA\u0001\n\u0003\ny!\u0001\u0004fcV\fGn\u001d\u000b\u0004\u007f\u0005E\u0001\u0002C9\u0002\f\u0005\u0005\t\u0019A7\b\u000f\u0005U!\u0001#\u0001\u0002\u0018\u0005AA)Z1eY&tW\rE\u0002\u001c\u000331a!\u0001\u0002\t\u0002\u0005m1\u0003BA\r\u0015}AqaKA\r\t\u0003\ty\u0002\u0006\u0002\u0002\u0018!A\u00111EA\r\t\u0003\t)#A\u0002o_^,\u0012AG\u0004\t\u0003S\tI\u0002c\u0001\u0002,\u0005\tB)Z1eY&tW-S:Pe\u0012,'/\u001a3\u0011\t\u00055\u0012qF\u0007\u0003\u000331\u0001\"!\r\u0002\u001a!\u0005\u00111\u0007\u0002\u0012\t\u0016\fG\r\\5oK&\u001bxJ\u001d3fe\u0016$7CBA\u0018\u0003k\tY\u0004E\u0002a\u0003oI1!!\u000fb\u0005\u0019y%M[3diB!q\"!\u0010\u001b\u0013\r\ty$\u0007\u0002\t\u001fJ$WM]5oO\"91&a\f\u0005\u0002\u0005\rCCAA\u0016\u0011\u001d)\u0015q\u0006C\u0001\u0003\u000f\"RaRA%\u0003\u001bBq!a\u0013\u0002F\u0001\u0007!$A\u0001b\u0011\u001d\ty%!\u0012A\u0002i\t\u0011A\u0019\u0005\u000b\u0003'\ny#!A\u0005\n\u0005U\u0013a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!!\u000e\t\u0015\u0005e\u0013\u0011DA\u0001\n\u0003\u000bY&A\u0003baBd\u0017\u0010F\u0002\u001b\u0003;BaaIA,\u0001\u0004)\u0003BCA1\u00033\t\t\u0011\"!\u0002d\u00059QO\\1qa2LH\u0003BA3\u0003W\u0002BaCA4K%\u0019\u0011\u0011\u000e\u0004\u0003\r=\u0003H/[8o\u0011%\ti'a\u0018\u0002\u0002\u0003\u0007!$A\u0002yIAB!\"a\u0015\u0002\u001a\u0005\u0005I\u0011BA+\u0001")
public class Deadline
implements Ordered<Deadline>,
Product,
Serializable {
    private final FiniteDuration time;

    public static Option<FiniteDuration> unapply(Deadline deadline) {
        return Deadline$.MODULE$.unapply(deadline);
    }

    public static Deadline apply(FiniteDuration finiteDuration) {
        return Deadline$.MODULE$.apply(finiteDuration);
    }

    public static Deadline now() {
        return Deadline$.MODULE$.now();
    }

    @Override
    public boolean $less(Object that) {
        return Ordered$class.$less(this, that);
    }

    @Override
    public boolean $greater(Object that) {
        return Ordered$class.$greater(this, that);
    }

    @Override
    public boolean $less$eq(Object that) {
        return Ordered$class.$less$eq(this, that);
    }

    @Override
    public boolean $greater$eq(Object that) {
        return Ordered$class.$greater$eq(this, that);
    }

    @Override
    public int compareTo(Object that) {
        return Ordered$class.compareTo(this, that);
    }

    public FiniteDuration time() {
        return this.time;
    }

    public Deadline $plus(FiniteDuration other) {
        return this.copy(this.time().$plus(other));
    }

    public Deadline $minus(FiniteDuration other) {
        return this.copy(this.time().$minus(other));
    }

    public FiniteDuration $minus(Deadline other) {
        return this.time().$minus(other.time());
    }

    public FiniteDuration timeLeft() {
        return this.$minus(Deadline$.MODULE$.now());
    }

    public boolean hasTimeLeft() {
        return !this.isOverdue();
    }

    public boolean isOverdue() {
        return this.time().toNanos() - System.nanoTime() < 0L;
    }

    @Override
    public int compare(Deadline other) {
        return this.time().compare(other.time());
    }

    public Deadline copy(FiniteDuration time) {
        return new Deadline(time);
    }

    public FiniteDuration copy$default$1() {
        return this.time();
    }

    @Override
    public String productPrefix() {
        return "Deadline";
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
        return this.time();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Deadline;
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
        if (!(x$1 instanceof Deadline)) return false;
        boolean bl = true;
        if (!bl) return false;
        Deadline deadline = (Deadline)x$1;
        FiniteDuration finiteDuration = this.time();
        FiniteDuration finiteDuration2 = deadline.time();
        if (finiteDuration == null) {
            if (finiteDuration2 != null) {
                return false;
            }
        } else if (!((Object)finiteDuration).equals(finiteDuration2)) return false;
        if (!deadline.canEqual(this)) return false;
        return true;
    }

    public Deadline(FiniteDuration time) {
        this.time = time;
        Ordered$class.$init$(this);
        Product$class.$init$(this);
    }
}

