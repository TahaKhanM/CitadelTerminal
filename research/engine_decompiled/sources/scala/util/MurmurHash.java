/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function1;
import scala.Function1$class;
import scala.collection.TraversableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.MurmurHash$;

@ScalaSignature(bytes="\u0006\u0001\t%c\u0001B\u0001\u0003\u0001\u001d\u0011!\"T;s[V\u0014\b*Y:i\u0015\t\u0019A!\u0001\u0003vi&d'\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001BE\n\u0004\u0001%i\u0001C\u0001\u0006\f\u001b\u0005!\u0011B\u0001\u0007\u0005\u0005\u0019\te.\u001f*fMB!!B\u0004\t0\u0013\tyAAA\u0005Gk:\u001cG/[8ocA\u0011\u0011C\u0005\u0007\u0001\t%\u0019\u0002\u0001)A\u0001\u0002\u000b\u0007ACA\u0001U#\t)\u0002\u0004\u0005\u0002\u000b-%\u0011q\u0003\u0002\u0002\b\u001d>$\b.\u001b8h!\tQ\u0011$\u0003\u0002\u001b\t\t\u0019\u0011I\\=)\rIardI\u0014,!\tQQ$\u0003\u0002\u001f\t\tY1\u000f]3dS\u0006d\u0017N_3ec\u0011!\u0003%\t\u0012\u000f\u0005)\t\u0013B\u0001\u0012\u0005\u0003\rIe\u000e^\u0019\u0005I\u0011*cE\u0004\u0002\u000bK%\u0011a\u0005B\u0001\u0005\u0019>tw-\r\u0003%Q%RcB\u0001\u0006*\u0013\tQC!A\u0003GY>\fG/\r\u0003%Y5rcB\u0001\u0006.\u0013\tqC!\u0001\u0004E_V\u0014G.\u001a\t\u0003\u0015AJ!!\r\u0003\u0003\tUs\u0017\u000e\u001e\u0005\tg\u0001\u0011\t\u0011)A\u0005i\u0005!1/Z3e!\tQQ'\u0003\u00027\t\t\u0019\u0011J\u001c;\t\u000ba\u0002A\u0011A\u001d\u0002\rqJg.\u001b;?)\tQD\bE\u0002<\u0001Ai\u0011A\u0001\u0005\u0006g]\u0002\r\u0001\u000e\u0005\b}\u0001\u0001\r\u0011\"\u0003@\u0003\u0005AW#\u0001\u001b\t\u000f\u0005\u0003\u0001\u0019!C\u0005\u0005\u0006)\u0001n\u0018\u0013fcR\u0011qf\u0011\u0005\b\t\u0002\u000b\t\u00111\u00015\u0003\rAH%\r\u0005\u0007\r\u0002\u0001\u000b\u0015\u0002\u001b\u0002\u0005!\u0004\u0003b\u0002%\u0001\u0001\u0004%IaP\u0001\u0002G\"9!\n\u0001a\u0001\n\u0013Y\u0015!B2`I\u0015\fHCA\u0018M\u0011\u001d!\u0015*!AA\u0002QBaA\u0014\u0001!B\u0013!\u0014AA2!\u0011\u001d\u0001\u0006\u00011A\u0005\n}\n\u0011a\u001b\u0005\b%\u0002\u0001\r\u0011\"\u0003T\u0003\u0015Yw\fJ3r)\tyC\u000bC\u0004E#\u0006\u0005\t\u0019\u0001\u001b\t\rY\u0003\u0001\u0015)\u00035\u0003\tY\u0007\u0005C\u0004Y\u0001\u0001\u0007I\u0011B-\u0002\r!\f7\u000f[3e+\u0005Q\u0006C\u0001\u0006\\\u0013\taFAA\u0004C_>dW-\u00198\t\u000fy\u0003\u0001\u0019!C\u0005?\u0006Q\u0001.Y:iK\u0012|F%Z9\u0015\u0005=\u0002\u0007b\u0002#^\u0003\u0003\u0005\rA\u0017\u0005\u0007E\u0002\u0001\u000b\u0015\u0002.\u0002\u000f!\f7\u000f[3eA!9A\r\u0001a\u0001\n\u0013y\u0014!\u00035bg\"4\u0018\r\\;f\u0011\u001d1\u0007\u00011A\u0005\n\u001d\fQ\u0002[1tQZ\fG.^3`I\u0015\fHCA\u0018i\u0011\u001d!U-!AA\u0002QBaA\u001b\u0001!B\u0013!\u0014A\u00035bg\"4\u0018\r\\;fA!)A\u000e\u0001C\u0001[\u0006)!/Z:fiR\tq\u0006C\u0003p\u0001\u0011\u0005\u0001/A\u0003baBd\u0017\u0010\u0006\u00020c\")!O\u001ca\u0001!\u0005\tA\u000fC\u0003u\u0001\u0011\u0005Q/\u0001\u0004baB,g\u000e\u001a\u000b\u0003_YDQa^:A\u0002Q\n\u0011!\u001b\u0005\u0006s\u0002!\taP\u0001\u0005Q\u0006\u001c\b\u000eC\u0003|\u0001\u0011\u0005C0\u0001\u0005iCND7i\u001c3f)\u0005!\u0004F\u0002\u0001\u007f\u0003\u0007\t9\u0001\u0005\u0002\u000b\u007f&\u0019\u0011\u0011\u0001\u0003\u0003\u0015\u0011,\u0007O]3dCR,G-\t\u0002\u0002\u0006\u0005\u0019Sk]3!i\",\u0007e\u001c2kK\u000e$\b%T;s[V\u0014\b*Y:ig\u0001Jgn\u001d;fC\u0012t\u0013EAA\u0005\u0003\u0019\u0011d&\r\u0019/a\u001d9\u0011Q\u0002\u0002\t\u0002\u0005=\u0011AC've6,(\u000fS1tQB\u00191(!\u0005\u0007\r\u0005\u0011\u0001\u0012AA\n'\r\t\t\"\u0003\u0005\bq\u0005EA\u0011AA\f)\t\ty\u0001\u0003\u0006\u0002\u001c\u0005E!\u0019!C\u0007\u0003;\tAB^5tS\ndW-T1hS\u000e,\"!a\b\u0010\u0005\u0005\u0005R\u0004BL\u001f'mD\u0011\"!\n\u0002\u0012\u0001\u0006i!a\b\u0002\u001bYL7/\u001b2mK6\u000bw-[2!\u0011)\tI#!\u0005C\u0002\u00135\u00111F\u0001\rQ&$G-\u001a8NC\u001eL7-Q\u000b\u0003\u0003[y!!a\f\u001e\tU%vg\"\u0005\n\u0003g\t\t\u0002)A\u0007\u0003[\tQ\u0002[5eI\u0016tW*Y4jG\u0006\u0003\u0003BCA\u001c\u0003#\u0011\r\u0011\"\u0004\u0002:\u0005a\u0001.\u001b3eK:l\u0015mZ5d\u0005V\u0011\u00111H\b\u0003\u0003{iBAKllL!I\u0011\u0011IA\tA\u00035\u00111H\u0001\u000eQ&$G-\u001a8NC\u001eL7M\u0011\u0011\t\u0015\u0005\u0015\u0013\u0011\u0003b\u0001\n\u001b\t9%\u0001\u0007wSNL'\r\\3NSb,'/\u0006\u0002\u0002J=\u0011\u00111J\u000f\u0005%r?\u001f\u0006C\u0005\u0002P\u0005E\u0001\u0015!\u0004\u0002J\u0005ia/[:jE2,W*\u001b=fe\u0002B!\"a\u0015\u0002\u0012\t\u0007IQBA+\u00031A\u0017\u000e\u001a3f]6K\u00070\u001a:B+\t\t9f\u0004\u0002\u0002Zu!10`\u000b\u001d\u0012%\ti&!\u0005!\u0002\u001b\t9&A\u0007iS\u0012$WM\\'jq\u0016\u0014\u0018\t\t\u0005\u000b\u0003C\n\tB1A\u0005\u000e\u0005\r\u0014\u0001\u00045jI\u0012,g.T5yKJ\u0014UCAA3\u001f\t\t9'\b\u0003l\u001d\u00104\n\"CA6\u0003#\u0001\u000bQBA3\u00035A\u0017\u000e\u001a3f]6K\u00070\u001a:CA!Q\u0011qNA\t\u0005\u0004%i!!\u001d\u0002\u0017\u0019Lg.\u00197NSb,'/M\u000b\u0003\u0003gz!!!\u001e\u001e\t\u0015]/z\u001b\u0005\n\u0003s\n\t\u0002)A\u0007\u0003g\nABZ5oC2l\u0015\u000e_3sc\u0001B!\"! \u0002\u0012\t\u0007IQBA@\u0003-1\u0017N\\1m\u001b&DXM\u001d\u001a\u0016\u0005\u0005\u0005uBAAB;\u0011\u0011-Wl\u001b\t\u0013\u0005\u001d\u0015\u0011\u0003Q\u0001\u000e\u0005\u0005\u0015\u0001\u00044j]\u0006dW*\u001b=feJ\u0002\u0003BCAF\u0003#\u0011\r\u0011\"\u0004\u0002\u000e\u0006Q1/Z3e'R\u0014\u0018N\\4\u0016\u0005\u0005=uBAAI;\u001190z j\t\u0013\u0005U\u0015\u0011\u0003Q\u0001\u000e\u0005=\u0015aC:fK\u0012\u001cFO]5oO\u0002B!\"!'\u0002\u0012\t\u0007IQBAN\u0003%\u0019X-\u001a3BeJ\f\u00170\u0006\u0002\u0002\u001e>\u0011\u0011qT\u000f\u0005y\u001dQ\u0015\rC\u0005\u0002$\u0006E\u0001\u0015!\u0004\u0002\u001e\u0006Q1/Z3e\u0003J\u0014\u0018-\u001f\u0011\t\u0015\u0005\u001d\u0016\u0011\u0003b\u0001\n\u0003\tI+\u0001\u0007ti>\u0014X\rZ'bO&\u001c\u0017)\u0006\u0002\u0002,B!!\"!,5\u0013\r\ty\u000b\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\n\u0003g\u000b\t\u0002)A\u0005\u0003W\u000bQb\u001d;pe\u0016$W*Y4jG\u0006\u0003\u0003BCA\\\u0003#\u0011\r\u0011\"\u0001\u0002*\u0006a1\u000f^8sK\u0012l\u0015mZ5d\u0005\"I\u00111XA\tA\u0003%\u00111V\u0001\u000egR|'/\u001a3NC\u001eL7M\u0011\u0011\t\u0011\u0005}\u0016\u0011\u0003C\u0001\u0003\u0003\f\u0011b\u001d;beRD\u0015m\u001d5\u0015\u0007Q\n\u0019\r\u0003\u00044\u0003{\u0003\r\u0001\u000e\u0005\b\u0003\u000f\f\t\u0002\"\u0001@\u0003-\u0019H/\u0019:u\u001b\u0006<\u0017nY!\t\u000f\u0005-\u0017\u0011\u0003C\u0001\u007f\u0005Y1\u000f^1si6\u000bw-[2C\u0011!\ty-!\u0005\u0005\u0002\u0005E\u0017AC3yi\u0016tG\rS1tQRIA'a5\u0002V\u0006e\u0017Q\u001c\u0005\u0007s\u00065\u0007\u0019\u0001\u001b\t\u000f\u0005]\u0017Q\u001aa\u0001i\u0005)a/\u00197vK\"9\u00111\\Ag\u0001\u0004!\u0014AB7bO&\u001c\u0017\tC\u0004\u0002`\u00065\u0007\u0019\u0001\u001b\u0002\r5\fw-[2C\u0011!\t\u0019/!\u0005\u0005\u0002\u0005\u0015\u0018A\u00038fqRl\u0015mZ5d\u0003R\u0019A'a:\t\u000f\u0005m\u0017\u0011\u001da\u0001i!A\u00111^A\t\t\u0003\ti/\u0001\u0006oKb$X*Y4jG\n#2\u0001NAx\u0011\u001d\ty.!;A\u0002QB\u0001\"a=\u0002\u0012\u0011\u0005\u0011Q_\u0001\rM&t\u0017\r\\5{K\"\u000b7\u000f\u001b\u000b\u0004i\u0005]\bBB=\u0002r\u0002\u0007A\u0007\u0003\u0005\u0002|\u0006EA\u0011AA\u007f\u0003%\t'O]1z\u0011\u0006\u001c\b.\u0006\u0003\u0002\u0000\n%Ac\u0001\u001b\u0003\u0002!A!1AA}\u0001\u0004\u0011)!A\u0001b!\u0015Q\u0011Q\u0016B\u0004!\r\t\"\u0011\u0002\u0003\u000b'\u0005e\b\u0015!A\u0001\u0006\u0004!\u0002f\u0001B\u00059!A!qBA\t\t\u0003\u0011\t\"\u0001\u0006tiJLgn\u001a%bg\"$2\u0001\u000eB\n\u0011!\u0011)B!\u0004A\u0002\t]\u0011!A:\u0011\t\te!q\u0004\b\u0004\u0015\tm\u0011b\u0001B\u000f\t\u00051\u0001K]3eK\u001aLAA!\t\u0003$\t11\u000b\u001e:j]\u001eT1A!\b\u0005\u0011!\u00119#!\u0005\u0005\u0002\t%\u0012!D:z[6,GO]5d\u0011\u0006\u001c\b.\u0006\u0003\u0003,\t}B#\u0002\u001b\u0003.\t\u0005\u0003\u0002\u0003B\u0018\u0005K\u0001\rA!\r\u0002\u0005a\u001c\bC\u0002B\u001a\u0005s\u0011i$\u0004\u0002\u00036)\u0019!q\u0007\u0003\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003<\tU\"a\u0004+sCZ,'o]1cY\u0016|enY3\u0011\u0007E\u0011y\u0004\u0002\u0004\u0014\u0005K\u0011\r\u0001\u0006\u0005\u0007g\t\u0015\u0002\u0019\u0001\u001b)\u000f\u0005Ea0a\u0001\u0002\b!:\u0011\u0011\u0003@\u0002\u0004\u0005\u001d\u0001fBA\u0006}\u0006\r\u0011q\u0001")
public class MurmurHash<T>
implements Function1<T, BoxedUnit> {
    private final int seed;
    private int scala$util$MurmurHash$$h;
    private int scala$util$MurmurHash$$c;
    private int scala$util$MurmurHash$$k;
    private boolean scala$util$MurmurHash$$hashed;
    private int hashvalue;

    public static <T> int symmetricHash(TraversableOnce<T> traversableOnce, int n) {
        return MurmurHash$.MODULE$.symmetricHash(traversableOnce, n);
    }

    public static int stringHash(String string2) {
        return MurmurHash$.MODULE$.stringHash(string2);
    }

    public static <T> int arrayHash(Object object) {
        return MurmurHash$.MODULE$.arrayHash(object);
    }

    public static int finalizeHash(int n) {
        return MurmurHash$.MODULE$.finalizeHash(n);
    }

    public static int nextMagicB(int n) {
        return MurmurHash$.MODULE$.nextMagicB(n);
    }

    public static int nextMagicA(int n) {
        return MurmurHash$.MODULE$.nextMagicA(n);
    }

    public static int extendHash(int n, int n2, int n3, int n4) {
        return MurmurHash$.MODULE$.extendHash(n, n2, n3, n4);
    }

    public static int startMagicB() {
        return MurmurHash$.MODULE$.startMagicB();
    }

    public static int startMagicA() {
        return MurmurHash$.MODULE$.startMagicA();
    }

    public static int startHash(int n) {
        return MurmurHash$.MODULE$.startHash(n);
    }

    public static int[] storedMagicB() {
        return MurmurHash$.MODULE$.storedMagicB();
    }

    public static int[] storedMagicA() {
        return MurmurHash$.MODULE$.storedMagicA();
    }

    @Override
    public boolean apply$mcZD$sp(double v1) {
        return Function1$class.apply$mcZD$sp(this, v1);
    }

    @Override
    public double apply$mcDD$sp(double v1) {
        return Function1$class.apply$mcDD$sp(this, v1);
    }

    @Override
    public float apply$mcFD$sp(double v1) {
        return Function1$class.apply$mcFD$sp(this, v1);
    }

    @Override
    public int apply$mcID$sp(double v1) {
        return Function1$class.apply$mcID$sp(this, v1);
    }

    @Override
    public long apply$mcJD$sp(double v1) {
        return Function1$class.apply$mcJD$sp(this, v1);
    }

    @Override
    public void apply$mcVD$sp(double v1) {
        Function1$class.apply$mcVD$sp(this, v1);
    }

    @Override
    public boolean apply$mcZF$sp(float v1) {
        return Function1$class.apply$mcZF$sp(this, v1);
    }

    @Override
    public double apply$mcDF$sp(float v1) {
        return Function1$class.apply$mcDF$sp(this, v1);
    }

    @Override
    public float apply$mcFF$sp(float v1) {
        return Function1$class.apply$mcFF$sp(this, v1);
    }

    @Override
    public int apply$mcIF$sp(float v1) {
        return Function1$class.apply$mcIF$sp(this, v1);
    }

    @Override
    public long apply$mcJF$sp(float v1) {
        return Function1$class.apply$mcJF$sp(this, v1);
    }

    @Override
    public void apply$mcVF$sp(float v1) {
        Function1$class.apply$mcVF$sp(this, v1);
    }

    @Override
    public boolean apply$mcZI$sp(int v1) {
        return Function1$class.apply$mcZI$sp(this, v1);
    }

    @Override
    public double apply$mcDI$sp(int v1) {
        return Function1$class.apply$mcDI$sp(this, v1);
    }

    @Override
    public float apply$mcFI$sp(int v1) {
        return Function1$class.apply$mcFI$sp(this, v1);
    }

    @Override
    public int apply$mcII$sp(int v1) {
        return Function1$class.apply$mcII$sp(this, v1);
    }

    @Override
    public long apply$mcJI$sp(int v1) {
        return Function1$class.apply$mcJI$sp(this, v1);
    }

    @Override
    public void apply$mcVI$sp(int v1) {
        Function1$class.apply$mcVI$sp(this, v1);
    }

    @Override
    public boolean apply$mcZJ$sp(long v1) {
        return Function1$class.apply$mcZJ$sp(this, v1);
    }

    @Override
    public double apply$mcDJ$sp(long v1) {
        return Function1$class.apply$mcDJ$sp(this, v1);
    }

    @Override
    public float apply$mcFJ$sp(long v1) {
        return Function1$class.apply$mcFJ$sp(this, v1);
    }

    @Override
    public int apply$mcIJ$sp(long v1) {
        return Function1$class.apply$mcIJ$sp(this, v1);
    }

    @Override
    public long apply$mcJJ$sp(long v1) {
        return Function1$class.apply$mcJJ$sp(this, v1);
    }

    @Override
    public void apply$mcVJ$sp(long v1) {
        Function1$class.apply$mcVJ$sp(this, v1);
    }

    @Override
    public <A> Function1<A, BoxedUnit> compose(Function1<A, T> g) {
        return Function1$class.compose(this, g);
    }

    @Override
    public <A> Function1<T, A> andThen(Function1<BoxedUnit, A> g) {
        return Function1$class.andThen(this, g);
    }

    @Override
    public String toString() {
        return Function1$class.toString(this);
    }

    public int scala$util$MurmurHash$$h() {
        return this.scala$util$MurmurHash$$h;
    }

    public void scala$util$MurmurHash$$h_$eq(int x$1) {
        this.scala$util$MurmurHash$$h = x$1;
    }

    public int scala$util$MurmurHash$$c() {
        return this.scala$util$MurmurHash$$c;
    }

    public void scala$util$MurmurHash$$c_$eq(int x$1) {
        this.scala$util$MurmurHash$$c = x$1;
    }

    public int scala$util$MurmurHash$$k() {
        return this.scala$util$MurmurHash$$k;
    }

    public void scala$util$MurmurHash$$k_$eq(int x$1) {
        this.scala$util$MurmurHash$$k = x$1;
    }

    private boolean scala$util$MurmurHash$$hashed() {
        return this.scala$util$MurmurHash$$hashed;
    }

    public void scala$util$MurmurHash$$hashed_$eq(boolean x$1) {
        this.scala$util$MurmurHash$$hashed = x$1;
    }

    private int hashvalue() {
        return this.hashvalue;
    }

    private void hashvalue_$eq(int x$1) {
        this.hashvalue = x$1;
    }

    public void reset() {
        this.scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.startHash(this.seed));
        this.scala$util$MurmurHash$$c_$eq(-1789642873);
        this.scala$util$MurmurHash$$k_$eq(718793509);
        this.scala$util$MurmurHash$$hashed_$eq(false);
    }

    @Override
    public void apply(T t) {
        this.scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(this.scala$util$MurmurHash$$h(), ScalaRunTime$.MODULE$.hash(t), this.scala$util$MurmurHash$$c(), this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(this.scala$util$MurmurHash$$c()));
        this.scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$hashed_$eq(false);
    }

    public void append(int i) {
        this.scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(this.scala$util$MurmurHash$$h(), i, this.scala$util$MurmurHash$$c(), this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(this.scala$util$MurmurHash$$c()));
        this.scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(this.scala$util$MurmurHash$$k()));
        this.scala$util$MurmurHash$$hashed_$eq(false);
    }

    public int hash() {
        if (!this.scala$util$MurmurHash$$hashed()) {
            this.hashvalue_$eq(MurmurHash$.MODULE$.finalizeHash(this.scala$util$MurmurHash$$h()));
            this.scala$util$MurmurHash$$hashed_$eq(true);
        }
        return this.hashvalue();
    }

    public int hashCode() {
        return this.hash();
    }

    public void apply$mcD$sp(double t) {
        this.apply((T)BoxesRunTime.boxToDouble(t));
    }

    public void apply$mcF$sp(float t) {
        this.apply((T)BoxesRunTime.boxToFloat(t));
    }

    public void apply$mcI$sp(int t) {
        this.apply((T)BoxesRunTime.boxToInteger(t));
    }

    public void apply$mcJ$sp(long t) {
        this.apply((T)BoxesRunTime.boxToLong(t));
    }

    public MurmurHash(int seed) {
        this.seed = seed;
        Function1$class.$init$(this);
        this.scala$util$MurmurHash$$h = MurmurHash$.MODULE$.startHash(seed);
        this.scala$util$MurmurHash$$c = -1789642873;
        this.scala$util$MurmurHash$$k = 718793509;
        this.scala$util$MurmurHash$$hashed = false;
        this.hashvalue = this.scala$util$MurmurHash$$h();
    }
}

