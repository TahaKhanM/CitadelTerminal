/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Depth$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dd\u0001B\u0001\u0003\u0005%\u0011Q\u0001R3qi\"T!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qa\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=WC2\u00042a\u0004\n\u0016\u001d\tY\u0001#\u0003\u0002\u0012\r\u00059\u0001/Y2lC\u001e,\u0017BA\n\u0015\u0005\u001dy%\u000fZ3sK\u0012T!!\u0005\u0004\u0011\u0005Y\u0001Q\"\u0001\u0002\t\u0011a\u0001!Q1A\u0005\u0002e\tQ\u0001Z3qi\",\u0012A\u0007\t\u0003\u0017mI!\u0001\b\u0004\u0003\u0007%sG\u000f\u0003\u0005\u001f\u0001\t\u0005\t\u0015!\u0003\u001b\u0003\u0019!W\r\u001d;iA!a\u0001\u0005\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005C\u00051A(\u001b8jiz\"\"!\u0006\u0012\t\u000bay\u0002\u0019\u0001\u000e\t\u000b\u0011\u0002A\u0011A\u0013\u0002\u00075\f\u0007\u0010\u0006\u0002\u0016M!)qe\ta\u0001+\u0005!A\u000f[1u\u0011\u0015I\u0003\u0001\"\u0001+\u0003\u0011!Wm\u0019:\u0015\u0005UY\u0003\"\u0002\u0017)\u0001\u0004Q\u0012!\u00018\t\u000b9\u0002A\u0011A\u0018\u0002\t%t7M\u001d\u000b\u0003+ABQ\u0001L\u0017A\u0002iAQ!\u000b\u0001\u0005\u0002I*\u0012!\u0006\u0005\u0006]\u0001!\tA\r\u0005\u0006k\u0001!\tAN\u0001\u000bSNtUmZ1uSZ,W#A\u001c\u0011\u0005-A\u0014BA\u001d\u0007\u0005\u001d\u0011un\u001c7fC:DQa\u000f\u0001\u0005\u0002Y\na![:[KJ|\u0007\"B\u001f\u0001\t\u00031\u0014AC5t\u0003:LH)\u001a9uQ\")q\b\u0001C\u0001\u0001\u000691m\\7qCJ,GC\u0001\u000eB\u0011\u00159c\b1\u0001\u0016\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0003!!xn\u0015;sS:<G#A#\u0011\u0005\u0019KeBA\u0006H\u0013\tAe!\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0015.\u0013aa\u0015;sS:<'B\u0001%\u0007\u0011\u001di\u0005!!A\u0005B9\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00025!9\u0001\u000bAA\u0001\n\u0003\n\u0016AB3rk\u0006d7\u000f\u0006\u00028%\"91kTA\u0001\u0002\u0004!\u0016a\u0001=%cA\u00111\"V\u0005\u0003-\u001a\u00111!\u00118z\u000f\u0015A&\u0001#\u0001Z\u0003\u0015!U\r\u001d;i!\t1\"LB\u0003\u0002\u0005!\u00051l\u0005\u0002[9B\u00111\"X\u0005\u0003=\u001a\u0011a!\u00118z%\u00164\u0007\"\u0002\u0011[\t\u0003\u0001G#A-\t\u000f\tT&\u0019!C\u0003G\u0006i\u0011I\\=EKB$\bNV1mk\u0016,\u0012\u0001Z\b\u0002Kv\tQ \u0003\u0004h5\u0002\u0006i\u0001Z\u0001\u000f\u0003:LH)\u001a9uQZ\u000bG.^3!\u0011\u001dI'L1A\u0005\u0006I\n\u0001\"\u00118z\t\u0016\u0004H\u000f\u001b\u0005\u0007Wj\u0003\u000bQB\u000b\u0002\u0013\u0005s\u0017\u0010R3qi\"\u0004\u0003bB7[\u0005\u0004%)AM\u0001\u00055\u0016\u0014x\u000e\u0003\u0004p5\u0002\u0006i!F\u0001\u00065\u0016\u0014x\u000e\t\u0005\u0006cj#)A]\u0001\u0006CB\u0004H.\u001f\u000b\u0003+MDQ\u0001\u00079A\u0002iA#\u0001];\u0011\u0005-1\u0018BA<\u0007\u0005\u0019Ig\u000e\\5oK\")\u0011P\u0017C\u0003u\u0006iQ.\u0019=%Kb$XM\\:j_:$\"a_?\u0015\u0005Ua\b\"B\u0014y\u0001\u0004)\u0002\"\u0002@y\u0001\u0004)\u0012!\u0002\u0013uQ&\u001c\bbBA\u00015\u0012\u0015\u00111A\u0001\u0010I\u0016\u001c'\u000fJ3yi\u0016t7/[8oaQ!\u0011QAA\u0005)\r)\u0012q\u0001\u0005\u0006Y}\u0004\rA\u0007\u0005\u0006}~\u0004\r!\u0006\u0005\b\u0003\u001bQFQAA\b\u0003=Ign\u0019:%Kb$XM\\:j_:\u0004D\u0003BA\t\u0003+!2!FA\n\u0011\u0019a\u00131\u0002a\u00015!1a0a\u0003A\u0002UAq!!\u0007[\t\u000b\tY\"A\beK\u000e\u0014H%\u001a=uK:\u001c\u0018n\u001c82)\r)\u0012Q\u0004\u0005\u0007}\u0006]\u0001\u0019A\u000b\t\u000f\u0005\u0005\"\f\"\u0002\u0002$\u0005y\u0011N\\2sI\u0015DH/\u001a8tS>t\u0017\u0007F\u0002\u0016\u0003KAaA`A\u0010\u0001\u0004)\u0002bBA\u00155\u0012\u0015\u00111F\u0001\u0015SNtUmZ1uSZ,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007]\ni\u0003\u0003\u0004\u007f\u0003O\u0001\r!\u0006\u0005\b\u0003cQFQAA\u001a\u0003AI7OW3s_\u0012*\u0007\u0010^3og&|g\u000eF\u00028\u0003kAaA`A\u0018\u0001\u0004)\u0002bBA\u001d5\u0012\u0015\u00111H\u0001\u0015SN\fe.\u001f#faRDG%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007]\ni\u0004\u0003\u0004\u007f\u0003o\u0001\r!\u0006\u0005\b\u0003\u0003RFQAA\"\u0003E\u0019w.\u001c9be\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u000b\nI\u0005F\u0002\u001b\u0003\u000fBaaJA \u0001\u0004)\u0002B\u0002@\u0002@\u0001\u0007Q\u0003C\u0004\u0002Ni#)!a\u0014\u0002%Q|7\u000b\u001e:j]\u001e$S\r\u001f;f]NLwN\u001c\u000b\u0004\t\u0006E\u0003B\u0002@\u0002L\u0001\u0007Q\u0003C\u0005\u0002Vi\u000b\t\u0011\"\u0002\u0002X\u0005\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\rq\u0015\u0011\f\u0005\u0007}\u0006M\u0003\u0019A\u000b\t\u0013\u0005u#,!A\u0005\u0006\u0005}\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\t\t'!\u001a\u0015\u0007]\n\u0019\u0007\u0003\u0005T\u00037\n\t\u00111\u0001U\u0011\u0019q\u00181\fa\u0001+\u0001")
public final class Depth
implements Ordered<Depth> {
    private final int depth;

    public static boolean equals$extension(int n, Object object) {
        return Depth$.MODULE$.equals$extension(n, object);
    }

    public static int hashCode$extension(int n) {
        return Depth$.MODULE$.hashCode$extension(n);
    }

    public static String toString$extension(int n) {
        return Depth$.MODULE$.toString$extension(n);
    }

    public static int compare$extension(int n, int n2) {
        return Depth$.MODULE$.compare$extension(n, n2);
    }

    public static boolean isAnyDepth$extension(int n) {
        return Depth$.MODULE$.isAnyDepth$extension(n);
    }

    public static boolean isZero$extension(int n) {
        return Depth$.MODULE$.isZero$extension(n);
    }

    public static boolean isNegative$extension(int n) {
        return Depth$.MODULE$.isNegative$extension(n);
    }

    public static int incr$extension1(int n) {
        return Depth$.MODULE$.incr$extension1(n);
    }

    public static int decr$extension1(int n) {
        return Depth$.MODULE$.decr$extension1(n);
    }

    public static int incr$extension0(int n, int n2) {
        return Depth$.MODULE$.incr$extension0(n, n2);
    }

    public static int decr$extension0(int n, int n2) {
        return Depth$.MODULE$.decr$extension0(n, n2);
    }

    public static int max$extension(int n, int n2) {
        return Depth$.MODULE$.max$extension(n, n2);
    }

    public static int apply(int n) {
        return Depth$.MODULE$.apply(n);
    }

    public static int Zero() {
        return Depth$.MODULE$.Zero();
    }

    public static int AnyDepth() {
        return Depth$.MODULE$.AnyDepth();
    }

    public static int AnyDepthValue() {
        return Depth$.MODULE$.AnyDepthValue();
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

    public int depth() {
        return this.depth;
    }

    public int max(int that) {
        return Depth$.MODULE$.max$extension(this.depth(), that);
    }

    public int decr(int n) {
        return Depth$.MODULE$.decr$extension0(this.depth(), n);
    }

    public int incr(int n) {
        return Depth$.MODULE$.incr$extension0(this.depth(), n);
    }

    public int decr() {
        return Depth$.MODULE$.decr$extension1(this.depth());
    }

    public int incr() {
        return Depth$.MODULE$.incr$extension1(this.depth());
    }

    public boolean isNegative() {
        return Depth$.MODULE$.isNegative$extension(this.depth());
    }

    public boolean isZero() {
        return Depth$.MODULE$.isZero$extension(this.depth());
    }

    public boolean isAnyDepth() {
        return Depth$.MODULE$.isAnyDepth$extension(this.depth());
    }

    @Override
    public int compare(int that) {
        return Depth$.MODULE$.compare$extension(this.depth(), that);
    }

    public String toString() {
        return Depth$.MODULE$.toString$extension(this.depth());
    }

    public int hashCode() {
        return Depth$.MODULE$.hashCode$extension(this.depth());
    }

    public boolean equals(Object x$1) {
        return Depth$.MODULE$.equals$extension(this.depth(), x$1);
    }

    public Depth(int depth) {
        this.depth = depth;
        Ordered$class.$init$(this);
    }
}

