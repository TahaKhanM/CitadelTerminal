/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Proxy$class;
import scala.math.Numeric$ShortIsIntegral$;
import scala.math.Ordered$class;
import scala.math.Ordering$Short$;
import scala.math.ScalaNumericAnyConversions$class;
import scala.reflect.ScalaSignature;
import scala.runtime.OrderedProxy$class;
import scala.runtime.RichShort$;
import scala.runtime.ScalaNumberProxy$class;
import scala.runtime.ScalaWholeNumberProxy;
import scala.runtime.ScalaWholeNumberProxy$class;

@ScalaSignature(bytes="\u0006\u0001\u00055e\u0001B\u0001\u0003\u0005\u001d\u0011\u0011BU5dQNCwN\u001d;\u000b\u0005\r!\u0011a\u0002:v]RLW.\u001a\u0006\u0002\u000b\u0005)1oY1mC\u000e\u00011c\u0001\u0001\t\u0019A\u0011\u0011BC\u0007\u0002\t%\u00111\u0002\u0002\u0002\u0007\u0003:Lh+\u00197\u0011\u00075q\u0001#D\u0001\u0003\u0013\ty!AA\u000bTG\u0006d\u0017m\u00165pY\u0016tU/\u001c2feB\u0013x\u000e_=\u0011\u0005%\t\u0012B\u0001\n\u0005\u0005\u0015\u0019\u0006n\u001c:u\u0011!!\u0002A!b\u0001\n\u0003)\u0012\u0001B:fY\u001a,\u0012\u0001\u0005\u0005\t/\u0001\u0011\t\u0011)A\u0005!\u0005)1/\u001a7gA!)\u0011\u0004\u0001C\u00015\u00051A(\u001b8jiz\"\"a\u0007\u000f\u0011\u00055\u0001\u0001\"\u0002\u000b\u0019\u0001\u0004\u0001\u0002\"\u0002\u0010\u0001\t#y\u0012a\u00018v[V\t\u0001E\u0004\u0002\"[9\u0011!E\u000b\b\u0003G!r!\u0001J\u0014\u000e\u0003\u0015R!A\n\u0004\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0011BA\u0015\u0005\u0003\u0011i\u0017\r\u001e5\n\u0005-b\u0013a\u0002(v[\u0016\u0014\u0018n\u0019\u0006\u0003S\u0011I!AL\u0018\u0002\u001fMCwN\u001d;Jg&sG/Z4sC2T!a\u000b\u0017\t\u000bE\u0002A\u0011\u0003\u001a\u0002\u0007=\u0014H-F\u00014\u001d\t!tG\u0004\u0002#k%\u0011a\u0007L\u0001\t\u001fJ$WM]5oO&\u0011\u0001(O\u0001\u0006'\"|'\u000f\u001e\u0006\u0003m1BQa\u000f\u0001\u0005Bq\n1\u0002Z8vE2,g+\u00197vKR\tQ\b\u0005\u0002\n}%\u0011q\b\u0002\u0002\u0007\t>,(\r\\3\t\u000b\u0005\u0003A\u0011\t\"\u0002\u0015\u0019dw.\u0019;WC2,X\rF\u0001D!\tIA)\u0003\u0002F\t\t)a\t\\8bi\")q\t\u0001C!\u0011\u0006IAn\u001c8h-\u0006dW/\u001a\u000b\u0002\u0013B\u0011\u0011BS\u0005\u0003\u0017\u0012\u0011A\u0001T8oO\")Q\n\u0001C!\u001d\u0006A\u0011N\u001c;WC2,X\rF\u0001P!\tI\u0001+\u0003\u0002R\t\t\u0019\u0011J\u001c;\t\u000bM\u0003A\u0011\t+\u0002\u0013\tLH/\u001a,bYV,G#A+\u0011\u0005%1\u0016BA,\u0005\u0005\u0011\u0011\u0015\u0010^3\t\u000be\u0003A\u0011\t.\u0002\u0015MDwN\u001d;WC2,X\rF\u0001\u0011\u0011\u0015a\u0006\u0001\"\u0011^\u00031I7OV1mS\u0012\u001c\u0006n\u001c:u+\u0005q\u0006CA\u0005`\u0013\t\u0001GAA\u0004C_>dW-\u00198\t\u000b\t\u0004A\u0011I\u000b\u0002\u0007\u0005\u00147\u000fC\u0003e\u0001\u0011\u0005S-A\u0002nCb$\"\u0001\u00054\t\u000b\u001d\u001c\u0007\u0019\u0001\t\u0002\tQD\u0017\r\u001e\u0005\u0006S\u0002!\tE[\u0001\u0004[&tGC\u0001\tl\u0011\u00159\u0007\u000e1\u0001\u0011\u0011\u0015i\u0007\u0001\"\u0011o\u0003\u0019\u0019\u0018n\u001a8v[V\tq\nC\u0004q\u0001\u0005\u0005I\u0011\t(\u0002\u0011!\f7\u000f[\"pI\u0016DqA\u001d\u0001\u0002\u0002\u0013\u00053/\u0001\u0004fcV\fGn\u001d\u000b\u0003=RDq!^9\u0002\u0002\u0003\u0007a/A\u0002yIE\u0002\"!C<\n\u0005a$!aA!os\u001e9!PAA\u0001\u0012\u0003Y\u0018!\u0003*jG\"\u001c\u0006n\u001c:u!\tiAPB\u0004\u0002\u0005\u0005\u0005\t\u0012A?\u0014\u0005qt\bCA\u0005\u0000\u0013\r\t\t\u0001\u0002\u0002\u0007\u0003:L(+\u001a4\t\reaH\u0011AA\u0003)\u0005Y\bbBA\u0005y\u0012\u0015\u00111B\u0001\u000e]VlG%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007\u0001\ni\u0001C\u0004\u0002\u0010\u0005\u001d\u0001\u0019A\u000e\u0002\u000b\u0011\"\b.[:\t\u000f\u0005MA\u0010\"\u0002\u0002\u0016\u0005iqN\u001d3%Kb$XM\\:j_:$2aMA\f\u0011\u001d\ty!!\u0005A\u0002mAq!a\u0007}\t\u000b\ti\"A\u000be_V\u0014G.\u001a,bYV,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007q\ny\u0002C\u0004\u0002\u0010\u0005e\u0001\u0019A\u000e\t\u000f\u0005\rB\u0010\"\u0002\u0002&\u0005!b\r\\8biZ\u000bG.^3%Kb$XM\\:j_:$2AQA\u0014\u0011\u001d\ty!!\tA\u0002mAq!a\u000b}\t\u000b\ti#A\nm_:<g+\u00197vK\u0012*\u0007\u0010^3og&|g\u000eF\u0002I\u0003_Aq!a\u0004\u0002*\u0001\u00071\u0004C\u0004\u00024q$)!!\u000e\u0002%%tGOV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004\u001d\u0006]\u0002bBA\b\u0003c\u0001\ra\u0007\u0005\b\u0003waHQAA\u001f\u0003M\u0011\u0017\u0010^3WC2,X\rJ3yi\u0016t7/[8o)\r!\u0016q\b\u0005\b\u0003\u001f\tI\u00041\u0001\u001c\u0011\u001d\t\u0019\u0005 C\u0003\u0003\u000b\nAc\u001d5peR4\u0016\r\\;fI\u0015DH/\u001a8tS>tGc\u0001.\u0002H!9\u0011qBA!\u0001\u0004Y\u0002bBA&y\u0012\u0015\u0011QJ\u0001\u0017SN4\u0016\r\\5e'\"|'\u000f\u001e\u0013fqR,gn]5p]R\u0019a,a\u0014\t\u000f\u0005=\u0011\u0011\na\u00017!9\u00111\u000b?\u0005\u0006\u0005U\u0013!D1cg\u0012*\u0007\u0010^3og&|g\u000eF\u0002\u0011\u0003/Bq!a\u0004\u0002R\u0001\u00071\u0004C\u0004\u0002\\q$)!!\u0018\u0002\u001b5\f\u0007\u0010J3yi\u0016t7/[8o)\u0011\ty&a\u0019\u0015\u0007A\t\t\u0007\u0003\u0004h\u00033\u0002\r\u0001\u0005\u0005\b\u0003\u001f\tI\u00061\u0001\u001c\u0011\u001d\t9\u0007 C\u0003\u0003S\nQ\"\\5oI\u0015DH/\u001a8tS>tG\u0003BA6\u0003_\"2\u0001EA7\u0011\u00199\u0017Q\ra\u0001!!9\u0011qBA3\u0001\u0004Y\u0002bBA:y\u0012\u0015\u0011QO\u0001\u0011g&<g.^7%Kb$XM\\:j_:$2aTA<\u0011\u001d\ty!!\u001dA\u0002mA\u0011\"a\u001f}\u0003\u0003%)!! \u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004\u001d\u0006}\u0004bBA\b\u0003s\u0002\ra\u0007\u0005\n\u0003\u0007c\u0018\u0011!C\u0003\u0003\u000b\u000b\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\u001d\u00151\u0012\u000b\u0004=\u0006%\u0005\u0002C;\u0002\u0002\u0006\u0005\t\u0019\u0001<\t\u000f\u0005=\u0011\u0011\u0011a\u00017\u0001")
public final class RichShort
implements ScalaWholeNumberProxy<Object> {
    private final short self;

    public static boolean equals$extension(short s2, Object object) {
        return RichShort$.MODULE$.equals$extension(s2, object);
    }

    public static int hashCode$extension(short s2) {
        return RichShort$.MODULE$.hashCode$extension(s2);
    }

    public static int signum$extension(short s2) {
        return RichShort$.MODULE$.signum$extension(s2);
    }

    public static short min$extension(short s2, short s3) {
        return RichShort$.MODULE$.min$extension(s2, s3);
    }

    public static short max$extension(short s2, short s3) {
        return RichShort$.MODULE$.max$extension(s2, s3);
    }

    public static short abs$extension(short s2) {
        return RichShort$.MODULE$.abs$extension(s2);
    }

    public static boolean isValidShort$extension(short s2) {
        return RichShort$.MODULE$.isValidShort$extension(s2);
    }

    public static short shortValue$extension(short s2) {
        return RichShort$.MODULE$.shortValue$extension(s2);
    }

    public static byte byteValue$extension(short s2) {
        return RichShort$.MODULE$.byteValue$extension(s2);
    }

    public static int intValue$extension(short s2) {
        return RichShort$.MODULE$.intValue$extension(s2);
    }

    public static long longValue$extension(short s2) {
        return RichShort$.MODULE$.longValue$extension(s2);
    }

    public static float floatValue$extension(short s2) {
        return RichShort$.MODULE$.floatValue$extension(s2);
    }

    public static double doubleValue$extension(short s2) {
        return RichShort$.MODULE$.doubleValue$extension(s2);
    }

    public static Ordering$Short$ ord$extension(short s2) {
        return RichShort$.MODULE$.ord$extension(s2);
    }

    public static Numeric$ShortIsIntegral$ num$extension(short s2) {
        return RichShort$.MODULE$.num$extension(s2);
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
    public boolean isValidByte() {
        return ScalaNumericAnyConversions$class.isValidByte(this);
    }

    @Override
    public boolean isValidInt() {
        return ScalaNumericAnyConversions$class.isValidInt(this);
    }

    @Override
    public boolean isValidChar() {
        return ScalaNumericAnyConversions$class.isValidChar(this);
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
    public short self() {
        return this.self;
    }

    public Numeric$ShortIsIntegral$ num() {
        return RichShort$.MODULE$.num$extension(this.self());
    }

    public Ordering$Short$ ord() {
        return RichShort$.MODULE$.ord$extension(this.self());
    }

    @Override
    public double doubleValue() {
        return RichShort$.MODULE$.doubleValue$extension(this.self());
    }

    @Override
    public float floatValue() {
        return RichShort$.MODULE$.floatValue$extension(this.self());
    }

    @Override
    public long longValue() {
        return RichShort$.MODULE$.longValue$extension(this.self());
    }

    @Override
    public int intValue() {
        return RichShort$.MODULE$.intValue$extension(this.self());
    }

    @Override
    public byte byteValue() {
        return RichShort$.MODULE$.byteValue$extension(this.self());
    }

    @Override
    public short shortValue() {
        return RichShort$.MODULE$.shortValue$extension(this.self());
    }

    @Override
    public boolean isValidShort() {
        return RichShort$.MODULE$.isValidShort$extension(this.self());
    }

    @Override
    public short abs() {
        return RichShort$.MODULE$.abs$extension(this.self());
    }

    @Override
    public short max(short that) {
        return RichShort$.MODULE$.max$extension(this.self(), that);
    }

    @Override
    public short min(short that) {
        return RichShort$.MODULE$.min$extension(this.self(), that);
    }

    @Override
    public int signum() {
        return RichShort$.MODULE$.signum$extension(this.self());
    }

    @Override
    public int hashCode() {
        return RichShort$.MODULE$.hashCode$extension(this.self());
    }

    @Override
    public boolean equals(Object x$1) {
        return RichShort$.MODULE$.equals$extension(this.self(), x$1);
    }

    public RichShort(short self) {
        this.self = self;
        ScalaNumericAnyConversions$class.$init$(this);
        Proxy$class.$init$(this);
        Ordered$class.$init$(this);
    }
}

