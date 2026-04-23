/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Precedence$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015a\u0001B\u0001\u0003\u0005%\u0011!\u0002\u0015:fG\u0016$WM\\2f\u0015\t\u0019A!\u0001\u0005j]R,'O\\1m\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001aE\u0002\u0001\u00159\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PV1m!\ry!#\u0006\b\u0003\u0017AI!!\u0005\u0004\u0002\u000fA\f7m[1hK&\u00111\u0003\u0006\u0002\b\u001fJ$WM]3e\u0015\t\tb\u0001\u0005\u0002\u0017\u00015\t!\u0001\u0003\u0005\u0019\u0001\t\u0015\r\u0011\"\u0001\u001a\u0003\u0015aWM^3m+\u0005Q\u0002CA\u0006\u001c\u0013\tabAA\u0002J]RD\u0001B\b\u0001\u0003\u0002\u0003\u0006IAG\u0001\u0007Y\u00164X\r\u001c\u0011\t\u0019\u0001\u0002A\u0011!A\u0001\u0002\u0003\u0005I\u0011B\u0011\u0002\rqJg.\u001b;?)\t)\"\u0005C\u0003\u0019?\u0001\u0007!\u0004C\u0003%\u0001\u0011\u0005Q%A\u0004d_6\u0004\u0018M]3\u0015\u0005i1\u0003\"B\u0014$\u0001\u0004)\u0012\u0001\u0002;iCRDQ!\u000b\u0001\u0005B)\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002WA\u0011Af\f\b\u0003\u00175J!A\f\u0004\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]\u0019Aqa\r\u0001\u0002\u0002\u0013\u0005C'\u0001\u0005iCND7i\u001c3f)\u0005Q\u0002b\u0002\u001c\u0001\u0003\u0003%\teN\u0001\u0007KF,\u0018\r\\:\u0015\u0005aZ\u0004CA\u0006:\u0013\tQdAA\u0004C_>dW-\u00198\t\u000fq*\u0014\u0011!a\u0001{\u0005\u0019\u0001\u0010J\u0019\u0011\u0005-q\u0014BA \u0007\u0005\r\te._\u0004\u0006\u0003\nA\tAQ\u0001\u000b!J,7-\u001a3f]\u000e,\u0007C\u0001\fD\r\u0015\t!\u0001#\u0001E'\r\u0019U\t\u0013\t\u0003\u0017\u0019K!a\u0012\u0004\u0003\r\u0005s\u0017PU3g!\u0011Y\u0011JG\u000b\n\u0005)3!!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015\u00013\t\"\u0001M)\u0005\u0011\u0005b\u0002(D\u0005\u0004%IaT\u0001\n\u000bJ\u0014xN\u001d(b[\u0016,\u0012\u0001\u0015\t\u0003#Zk\u0011A\u0015\u0006\u0003'R\u000bA\u0001\\1oO*\tQ+\u0001\u0003kCZ\f\u0017B\u0001\u0019S\u0011\u0019A6\t)A\u0005!\u0006QQI\u001d:pe:\u000bW.\u001a\u0011\t\u000bi\u001bE\u0011B.\u0002\u001d%\u001c\u0018i]:jO:lWM\u001c;PaR\u0011\u0001\b\u0018\u0005\u0006;f\u0003\raK\u0001\u0005]\u0006lW\rC\u0003`\u0007\u0012%\u0001-A\u0005gSJ\u001cHo\u00115beR\u0011Q#\u0019\u0005\u0006Ez\u0003\raY\u0001\u0003G\"\u0004\"a\u00033\n\u0005\u00154!\u0001B\"iCJDQaZ\"\u0005\u0002!\fQ!\u00199qYf$\"!F5\t\u000ba1\u0007\u0019\u0001\u000e\t\u000b\u001d\u001cE\u0011A6\u0015\u0005Ua\u0007\"B/k\u0001\u0004Y\u0003\"\u00028D\t\u000by\u0017!E2p[B\f'/\u001a\u0013fqR,gn]5p]R\u0011\u0001O\u001d\u000b\u00035EDQaJ7A\u0002UAQa]7A\u0002U\tQ\u0001\n;iSNDQ!^\"\u0005\u0006Y\f!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]R\u0011!f\u001e\u0005\u0006gR\u0004\r!\u0006\u0005\bs\u000e\u000b\t\u0011\"\u0002{\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005QZ\b\"B:y\u0001\u0004)\u0002bB?D\u0003\u0003%)A`\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$2a`A\u0002)\rA\u0014\u0011\u0001\u0005\byq\f\t\u00111\u0001>\u0011\u0015\u0019H\u00101\u0001\u0016\u0001")
public final class Precedence
implements Ordered<Precedence> {
    private final int level;

    public static <A> Function1<Object, A> andThen(Function1<Precedence, A> function1) {
        return Precedence$.MODULE$.andThen(function1);
    }

    public static <A> Function1<A, Precedence> compose(Function1<A, Object> function1) {
        return Precedence$.MODULE$.compose(function1);
    }

    public static boolean equals$extension(int n, Object object) {
        return Precedence$.MODULE$.equals$extension(n, object);
    }

    public static int hashCode$extension(int n) {
        return Precedence$.MODULE$.hashCode$extension(n);
    }

    public static String toString$extension(int n) {
        return Precedence$.MODULE$.toString$extension(n);
    }

    public static int compare$extension(int n, int n2) {
        return Precedence$.MODULE$.compare$extension(n, n2);
    }

    public static int apply(String string2) {
        return Precedence$.MODULE$.apply(string2);
    }

    public static int apply(int n) {
        return Precedence$.MODULE$.apply(n);
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

    public int level() {
        return this.level;
    }

    @Override
    public int compare(int that) {
        return Precedence$.MODULE$.compare$extension(this.level(), that);
    }

    public String toString() {
        return Precedence$.MODULE$.toString$extension(this.level());
    }

    public int hashCode() {
        return Precedence$.MODULE$.hashCode$extension(this.level());
    }

    public boolean equals(Object x$1) {
        return Precedence$.MODULE$.equals$extension(this.level(), x$1);
    }

    public Precedence(int level) {
        this.level = level;
        Ordered$class.$init$(this);
    }
}

