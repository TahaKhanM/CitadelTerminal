/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Proxy$class;
import scala.collection.immutable.NumericRange;
import scala.math.Numeric$LongIsIntegral$;
import scala.math.Ordered$class;
import scala.math.Ordering$Long$;
import scala.math.ScalaNumericAnyConversions$class;
import scala.reflect.ScalaSignature;
import scala.runtime.IntegralProxy;
import scala.runtime.IntegralProxy$class;
import scala.runtime.OrderedProxy$class;
import scala.runtime.RichLong$;
import scala.runtime.ScalaNumberProxy$class;
import scala.runtime.ScalaWholeNumberProxy$class;

@ScalaSignature(bytes="\u0006\u0001\t=a\u0001B\u0001\u0003\u0005\u001d\u0011\u0001BU5dQ2{gn\u001a\u0006\u0003\u0007\u0011\tqA];oi&lWMC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\u0005\r!\tI!\"D\u0001\u0005\u0013\tYAA\u0001\u0004B]f4\u0016\r\u001c\t\u0004\u001b9\u0001R\"\u0001\u0002\n\u0005=\u0011!!D%oi\u0016<'/\u00197Qe>D\u0018\u0010\u0005\u0002\n#%\u0011!\u0003\u0002\u0002\u0005\u0019>tw\r\u0003\u0005\u0015\u0001\t\u0015\r\u0011\"\u0001\u0016\u0003\u0011\u0019X\r\u001c4\u0016\u0003AA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I\u0001E\u0001\u0006g\u0016dg\r\t\u0005\u00063\u0001!\tAG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005ma\u0002CA\u0007\u0001\u0011\u0015!\u0002\u00041\u0001\u0011\u0011\u0015q\u0002\u0001\"\u0005 \u0003\rqW/\\\u000b\u0002A9\u0011\u0011%\f\b\u0003E)r!a\t\u0015\u000f\u0005\u0011:S\"A\u0013\u000b\u0005\u00192\u0011A\u0002\u001fs_>$h(C\u0001\u0006\u0013\tIC!\u0001\u0003nCRD\u0017BA\u0016-\u0003\u001dqU/\\3sS\u000eT!!\u000b\u0003\n\u00059z\u0013A\u0004'p]\u001eL5/\u00138uK\u001e\u0014\u0018\r\u001c\u0006\u0003W1BQ!\r\u0001\u0005\u0012I\n1a\u001c:e+\u0005\u0019dB\u0001\u001b8\u001d\t\u0011S'\u0003\u00027Y\u0005AqJ\u001d3fe&tw-\u0003\u00029s\u0005!Aj\u001c8h\u0015\t1D\u0006C\u0003<\u0001\u0011\u0005C(A\u0006e_V\u0014G.\u001a,bYV,G#A\u001f\u0011\u0005%q\u0014BA \u0005\u0005\u0019!u.\u001e2mK\")\u0011\t\u0001C!\u0005\u0006Qa\r\\8biZ\u000bG.^3\u0015\u0003\r\u0003\"!\u0003#\n\u0005\u0015#!!\u0002$m_\u0006$\b\"B$\u0001\t\u0003B\u0015!\u00037p]\u001e4\u0016\r\\;f)\u0005\u0001\u0002\"\u0002&\u0001\t\u0003Z\u0015\u0001C5oiZ\u000bG.^3\u0015\u00031\u0003\"!C'\n\u00059#!aA%oi\")\u0001\u000b\u0001C!#\u0006I!-\u001f;f-\u0006dW/\u001a\u000b\u0002%B\u0011\u0011bU\u0005\u0003)\u0012\u0011AAQ=uK\")a\u000b\u0001C!/\u0006Q1\u000f[8siZ\u000bG.^3\u0015\u0003a\u0003\"!C-\n\u0005i#!!B*i_J$\b\"\u0002/\u0001\t\u0003j\u0016aC5t-\u0006d\u0017\u000e\u001a\"zi\u0016,\u0012A\u0018\t\u0003\u0013}K!\u0001\u0019\u0003\u0003\u000f\t{w\u000e\\3b]\")!\r\u0001C!;\u0006a\u0011n\u001d,bY&$7\u000b[8si\")A\r\u0001C!;\u0006Y\u0011n\u001d,bY&$7\t[1s\u0011\u00151\u0007\u0001\"\u0011^\u0003)I7OV1mS\u0012Le\u000e\u001e\u0005\u0006Q\u0002!\t!X\u0001\fSN4\u0016\r\\5e\u0019>tw\rC\u0003k\u0001\u0011\u0005S#A\u0002bENDQ\u0001\u001c\u0001\u0005B5\f1!\\1y)\t\u0001b\u000eC\u0003pW\u0002\u0007\u0001#\u0001\u0003uQ\u0006$\b\"B9\u0001\t\u0003\u0012\u0018aA7j]R\u0011\u0001c\u001d\u0005\u0006_B\u0004\r\u0001\u0005\u0005\u0006k\u0002!\tE^\u0001\u0007g&<g.^7\u0016\u00031CQ\u0001\u001f\u0001\u0005\u0002U\tQA]8v]\u0012DCa\u001e>~\u007fB\u0011\u0011b_\u0005\u0003y\u0012\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005q\u0018a\u001d+iSN\u0004\u0013n\u001d\u0011b]\u0002Jg\u000e^3hKJ\u0004C/\u001f9fw\u0001\"\b.\u001a:fA%\u001c\bE\\8!e\u0016\f7o\u001c8!i>\u0004#o\\;oI\u0002JGO\f\u0011!!\u0016\u0014\b.\u00199tAe|W\u000fI7fC:$\b\u0005^8!G\u0006dG\u000e\t;iSN\u0004sN\u001c\u0011bA\u0019dw.\u0019;j]\u001el\u0003o\\5oi\u00022\u0018\r\\;f\u007f\u0005\u0012\u0011\u0011A\u0001\u0007e9\n\u0014G\f\u0019\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b\u0005qAo\u001c\"j]\u0006\u0014\u0018p\u0015;sS:<WCAA\u0005!\u0011\tY!!\u0005\u000f\u0007%\ti!C\u0002\u0002\u0010\u0011\ta\u0001\u0015:fI\u00164\u0017\u0002BA\n\u0003+\u0011aa\u0015;sS:<'bAA\b\t!9\u0011\u0011\u0004\u0001\u0005\u0002\u0005\u001d\u0011a\u0003;p\u0011\u0016D8\u000b\u001e:j]\u001eDq!!\b\u0001\t\u0003\t9!A\u0007u_>\u001bG/\u00197TiJLgn\u001a\u0005\t\u0003C\u0001\u0011\u0011!C!\u0017\u0006A\u0001.Y:i\u0007>$W\rC\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(\u00051Q-];bYN$2AXA\u0015\u0011)\tY#a\t\u0002\u0002\u0003\u0007\u0011QF\u0001\u0004q\u0012\n\u0004cA\u0005\u00020%\u0019\u0011\u0011\u0007\u0003\u0003\u0007\u0005s\u0017pB\u0005\u00026\t\t\t\u0011#\u0001\u00028\u0005A!+[2i\u0019>tw\rE\u0002\u000e\u0003s1\u0001\"\u0001\u0002\u0002\u0002#\u0005\u00111H\n\u0005\u0003s\ti\u0004E\u0002\n\u0003\u007fI1!!\u0011\u0005\u0005\u0019\te.\u001f*fM\"9\u0011$!\u000f\u0005\u0002\u0005\u0015CCAA\u001c\u0011!\tI%!\u000f\u0005\u0006\u0005-\u0013!\u00048v[\u0012*\u0007\u0010^3og&|g\u000eF\u0002!\u0003\u001bBq!a\u0014\u0002H\u0001\u00071$A\u0003%i\"L7\u000f\u0003\u0005\u0002T\u0005eBQAA+\u00035y'\u000f\u001a\u0013fqR,gn]5p]R\u00191'a\u0016\t\u000f\u0005=\u0013\u0011\u000ba\u00017!A\u00111LA\u001d\t\u000b\ti&A\u000be_V\u0014G.\u001a,bYV,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007q\ny\u0006C\u0004\u0002P\u0005e\u0003\u0019A\u000e\t\u0011\u0005\r\u0014\u0011\bC\u0003\u0003K\nAC\u001a7pCR4\u0016\r\\;fI\u0015DH/\u001a8tS>tGc\u0001\"\u0002h!9\u0011qJA1\u0001\u0004Y\u0002\u0002CA6\u0003s!)!!\u001c\u0002'1|gn\u001a,bYV,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007!\u000by\u0007C\u0004\u0002P\u0005%\u0004\u0019A\u000e\t\u0011\u0005M\u0014\u0011\bC\u0003\u0003k\n!#\u001b8u-\u0006dW/\u001a\u0013fqR,gn]5p]R\u00191*a\u001e\t\u000f\u0005=\u0013\u0011\u000fa\u00017!A\u00111PA\u001d\t\u000b\ti(A\ncsR,g+\u00197vK\u0012*\u0007\u0010^3og&|g\u000eF\u0002R\u0003\u007fBq!a\u0014\u0002z\u0001\u00071\u0004\u0003\u0005\u0002\u0004\u0006eBQAAC\u0003Q\u0019\bn\u001c:u-\u0006dW/\u001a\u0013fqR,gn]5p]R\u0019q+a\"\t\u000f\u0005=\u0013\u0011\u0011a\u00017!A\u00111RA\u001d\t\u000b\ti)A\u000bjgZ\u000bG.\u001b3CsR,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007y\u000by\tC\u0004\u0002P\u0005%\u0005\u0019A\u000e\t\u0011\u0005M\u0015\u0011\bC\u0003\u0003+\u000ba#[:WC2LGm\u00155peR$S\r\u001f;f]NLwN\u001c\u000b\u0004=\u0006]\u0005bBA(\u0003#\u0003\ra\u0007\u0005\t\u00037\u000bI\u0004\"\u0002\u0002\u001e\u0006)\u0012n\u001d,bY&$7\t[1sI\u0015DH/\u001a8tS>tGc\u00010\u0002 \"9\u0011qJAM\u0001\u0004Y\u0002\u0002CAR\u0003s!)!!*\u0002)%\u001ch+\u00197jI&sG\u000fJ3yi\u0016t7/[8o)\rq\u0016q\u0015\u0005\b\u0003\u001f\n\t\u000b1\u0001\u001c\u0011!\tY+!\u000f\u0005\u0006\u00055\u0016!F5t-\u0006d\u0017\u000e\u001a'p]\u001e$S\r\u001f;f]NLwN\u001c\u000b\u0004=\u0006=\u0006bBA(\u0003S\u0003\ra\u0007\u0005\t\u0003g\u000bI\u0004\"\u0002\u00026\u0006i\u0011MY:%Kb$XM\\:j_:$2\u0001EA\\\u0011\u001d\ty%!-A\u0002mA\u0001\"a/\u0002:\u0011\u0015\u0011QX\u0001\u000e[\u0006DH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005}\u00161\u0019\u000b\u0004!\u0005\u0005\u0007BB8\u0002:\u0002\u0007\u0001\u0003C\u0004\u0002P\u0005e\u0006\u0019A\u000e\t\u0011\u0005\u001d\u0017\u0011\bC\u0003\u0003\u0013\fQ\"\\5oI\u0015DH/\u001a8tS>tG\u0003BAf\u0003\u001f$2\u0001EAg\u0011\u0019y\u0017Q\u0019a\u0001!!9\u0011qJAc\u0001\u0004Y\u0002\u0002CAj\u0003s!)!!6\u0002!MLwM\\;nI\u0015DH/\u001a8tS>tGc\u0001'\u0002X\"9\u0011qJAi\u0001\u0004Y\u0002\u0002CAn\u0003s!)!!8\u0002\u001fI|WO\u001c3%Kb$XM\\:j_:$2\u0001EAp\u0011\u001d\ty%!7A\u0002mAS!!7{{~D\u0001\"!:\u0002:\u0011\u0015\u0011q]\u0001\u0019i>\u0014\u0015N\\1ssN#(/\u001b8hI\u0015DH/\u001a8tS>tG\u0003BA\u0005\u0003SDq!a\u0014\u0002d\u0002\u00071\u0004\u0003\u0005\u0002n\u0006eBQAAx\u0003U!x\u000eS3y'R\u0014\u0018N\\4%Kb$XM\\:j_:$B!!\u0003\u0002r\"9\u0011qJAv\u0001\u0004Y\u0002\u0002CA{\u0003s!)!a>\u0002/Q|wj\u0019;bYN#(/\u001b8hI\u0015DH/\u001a8tS>tG\u0003BA\u0005\u0003sDq!a\u0014\u0002t\u0002\u00071\u0004\u0003\u0006\u0002~\u0006e\u0012\u0011!C\u0003\u0003\u007f\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R\u00191J!\u0001\t\u000f\u0005=\u00131 a\u00017!Q!QAA\u001d\u0003\u0003%)Aa\u0002\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tG\u0003\u0002B\u0005\u0005\u001b!2A\u0018B\u0006\u0011)\tYCa\u0001\u0002\u0002\u0003\u0007\u0011Q\u0006\u0005\b\u0003\u001f\u0012\u0019\u00011\u0001\u001c\u0001")
public final class RichLong
implements IntegralProxy<Object> {
    private final long self;

    public static boolean equals$extension(long l, Object object) {
        return RichLong$.MODULE$.equals$extension(l, object);
    }

    public static int hashCode$extension(long l) {
        return RichLong$.MODULE$.hashCode$extension(l);
    }

    public static String toOctalString$extension(long l) {
        return RichLong$.MODULE$.toOctalString$extension(l);
    }

    public static String toHexString$extension(long l) {
        return RichLong$.MODULE$.toHexString$extension(l);
    }

    public static String toBinaryString$extension(long l) {
        return RichLong$.MODULE$.toBinaryString$extension(l);
    }

    public static long round$extension(long l) {
        return RichLong$.MODULE$.round$extension(l);
    }

    public static int signum$extension(long l) {
        return RichLong$.MODULE$.signum$extension(l);
    }

    public static long min$extension(long l, long l2) {
        return RichLong$.MODULE$.min$extension(l, l2);
    }

    public static long max$extension(long l, long l2) {
        return RichLong$.MODULE$.max$extension(l, l2);
    }

    public static long abs$extension(long l) {
        return RichLong$.MODULE$.abs$extension(l);
    }

    public static boolean isValidLong$extension(long l) {
        return RichLong$.MODULE$.isValidLong$extension(l);
    }

    public static boolean isValidInt$extension(long l) {
        return RichLong$.MODULE$.isValidInt$extension(l);
    }

    public static boolean isValidChar$extension(long l) {
        return RichLong$.MODULE$.isValidChar$extension(l);
    }

    public static boolean isValidShort$extension(long l) {
        return RichLong$.MODULE$.isValidShort$extension(l);
    }

    public static boolean isValidByte$extension(long l) {
        return RichLong$.MODULE$.isValidByte$extension(l);
    }

    public static short shortValue$extension(long l) {
        return RichLong$.MODULE$.shortValue$extension(l);
    }

    public static byte byteValue$extension(long l) {
        return RichLong$.MODULE$.byteValue$extension(l);
    }

    public static int intValue$extension(long l) {
        return RichLong$.MODULE$.intValue$extension(l);
    }

    public static long longValue$extension(long l) {
        return RichLong$.MODULE$.longValue$extension(l);
    }

    public static float floatValue$extension(long l) {
        return RichLong$.MODULE$.floatValue$extension(l);
    }

    public static double doubleValue$extension(long l) {
        return RichLong$.MODULE$.doubleValue$extension(l);
    }

    public static Ordering$Long$ ord$extension(long l) {
        return RichLong$.MODULE$.ord$extension(l);
    }

    public static Numeric$LongIsIntegral$ num$extension(long l) {
        return RichLong$.MODULE$.num$extension(l);
    }

    @Override
    public NumericRange.Exclusive until(Object end) {
        return IntegralProxy$class.until(this, end);
    }

    @Override
    public NumericRange.Exclusive until(Object end, Object step) {
        return IntegralProxy$class.until(this, end, step);
    }

    @Override
    public NumericRange.Inclusive to(Object end) {
        return IntegralProxy$class.to(this, end);
    }

    @Override
    public NumericRange.Inclusive to(Object end, Object step) {
        return IntegralProxy$class.to(this, end, step);
    }

    @Override
    public boolean isWhole() {
        return ScalaWholeNumberProxy$class.isWhole(this);
    }

    @Override
    public Object underlying() {
        return ScalaNumberProxy$class.underlying(this);
    }

    @Override
    public int compare(Object y) {
        return OrderedProxy$class.compare(this, y);
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

    @Override
    public String toString() {
        return Proxy$class.toString(this);
    }

    @Override
    public char toChar() {
        return ScalaNumericAnyConversions$class.toChar(this);
    }

    @Override
    public byte toByte() {
        return ScalaNumericAnyConversions$class.toByte(this);
    }

    @Override
    public short toShort() {
        return ScalaNumericAnyConversions$class.toShort(this);
    }

    @Override
    public int toInt() {
        return ScalaNumericAnyConversions$class.toInt(this);
    }

    @Override
    public long toLong() {
        return ScalaNumericAnyConversions$class.toLong(this);
    }

    @Override
    public float toFloat() {
        return ScalaNumericAnyConversions$class.toFloat(this);
    }

    @Override
    public double toDouble() {
        return ScalaNumericAnyConversions$class.toDouble(this);
    }

    @Override
    public int unifiedPrimitiveHashcode() {
        return ScalaNumericAnyConversions$class.unifiedPrimitiveHashcode(this);
    }

    @Override
    public boolean unifiedPrimitiveEquals(Object x) {
        return ScalaNumericAnyConversions$class.unifiedPrimitiveEquals(this, x);
    }

    @Override
    public long self() {
        return this.self;
    }

    public Numeric$LongIsIntegral$ num() {
        return RichLong$.MODULE$.num$extension(this.self());
    }

    public Ordering$Long$ ord() {
        return RichLong$.MODULE$.ord$extension(this.self());
    }

    @Override
    public double doubleValue() {
        return RichLong$.MODULE$.doubleValue$extension(this.self());
    }

    @Override
    public float floatValue() {
        return RichLong$.MODULE$.floatValue$extension(this.self());
    }

    @Override
    public long longValue() {
        return RichLong$.MODULE$.longValue$extension(this.self());
    }

    @Override
    public int intValue() {
        return RichLong$.MODULE$.intValue$extension(this.self());
    }

    @Override
    public byte byteValue() {
        return RichLong$.MODULE$.byteValue$extension(this.self());
    }

    @Override
    public short shortValue() {
        return RichLong$.MODULE$.shortValue$extension(this.self());
    }

    @Override
    public boolean isValidByte() {
        return RichLong$.MODULE$.isValidByte$extension(this.self());
    }

    @Override
    public boolean isValidShort() {
        return RichLong$.MODULE$.isValidShort$extension(this.self());
    }

    @Override
    public boolean isValidChar() {
        return RichLong$.MODULE$.isValidChar$extension(this.self());
    }

    @Override
    public boolean isValidInt() {
        return RichLong$.MODULE$.isValidInt$extension(this.self());
    }

    public boolean isValidLong() {
        return RichLong$.MODULE$.isValidLong$extension(this.self());
    }

    @Override
    public long abs() {
        return RichLong$.MODULE$.abs$extension(this.self());
    }

    @Override
    public long max(long that) {
        return RichLong$.MODULE$.max$extension(this.self(), that);
    }

    @Override
    public long min(long that) {
        return RichLong$.MODULE$.min$extension(this.self(), that);
    }

    @Override
    public int signum() {
        return RichLong$.MODULE$.signum$extension(this.self());
    }

    public long round() {
        return RichLong$.MODULE$.round$extension(this.self());
    }

    public String toBinaryString() {
        return RichLong$.MODULE$.toBinaryString$extension(this.self());
    }

    public String toHexString() {
        return RichLong$.MODULE$.toHexString$extension(this.self());
    }

    public String toOctalString() {
        return RichLong$.MODULE$.toOctalString$extension(this.self());
    }

    @Override
    public int hashCode() {
        return RichLong$.MODULE$.hashCode$extension(this.self());
    }

    @Override
    public boolean equals(Object x$1) {
        return RichLong$.MODULE$.equals$extension(this.self(), x$1);
    }

    public RichLong(long self) {
        this.self = self;
        ScalaNumericAnyConversions$class.$init$(this);
        Proxy$class.$init$(this);
        Ordered$class.$init$(this);
    }
}

