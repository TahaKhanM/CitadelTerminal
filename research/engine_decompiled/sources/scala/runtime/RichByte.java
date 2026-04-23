/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.Proxy$class;
import scala.math.Numeric$ByteIsIntegral$;
import scala.math.Ordered$class;
import scala.math.Ordering$Byte$;
import scala.math.ScalaNumericAnyConversions$class;
import scala.reflect.ScalaSignature;
import scala.runtime.OrderedProxy$class;
import scala.runtime.RichByte$;
import scala.runtime.ScalaNumberProxy$class;
import scala.runtime.ScalaWholeNumberProxy;
import scala.runtime.ScalaWholeNumberProxy$class;

@ScalaSignature(bytes="\u0006\u0001\u00055e\u0001B\u0001\u0003\u0005\u001d\u0011\u0001BU5dQ\nKH/\u001a\u0006\u0003\u0007\u0011\tqA];oi&lWMC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\u0005\r!\tI!\"D\u0001\u0005\u0013\tYAA\u0001\u0004B]f4\u0016\r\u001c\t\u0004\u001b9\u0001R\"\u0001\u0002\n\u0005=\u0011!!F*dC2\fw\u000b[8mK:+XNY3s!J|\u00070\u001f\t\u0003\u0013EI!A\u0005\u0003\u0003\t\tKH/\u001a\u0005\t)\u0001\u0011)\u0019!C\u0001+\u0005!1/\u001a7g+\u0005\u0001\u0002\u0002C\f\u0001\u0005\u0003\u0005\u000b\u0011\u0002\t\u0002\u000bM,GN\u001a\u0011\t\u000be\u0001A\u0011\u0001\u000e\u0002\rqJg.\u001b;?)\tYB\u0004\u0005\u0002\u000e\u0001!)A\u0003\u0007a\u0001!!)a\u0004\u0001C\t?\u0005\u0019a.^7\u0016\u0003\u0001r!!I\u0017\u000f\u0005\tRcBA\u0012)\u001d\t!s%D\u0001&\u0015\t1c!\u0001\u0004=e>|GOP\u0005\u0002\u000b%\u0011\u0011\u0006B\u0001\u0005[\u0006$\b.\u0003\u0002,Y\u00059a*^7fe&\u001c'BA\u0015\u0005\u0013\tqs&\u0001\bCsR,\u0017j]%oi\u0016<'/\u00197\u000b\u0005-b\u0003\"B\u0019\u0001\t#\u0011\u0014aA8sIV\t1G\u0004\u00025o9\u0011!%N\u0005\u0003m1\n\u0001b\u0014:eKJLgnZ\u0005\u0003qe\nAAQ=uK*\u0011a\u0007\f\u0005\u0006w\u0001!\t\u0005P\u0001\fI>,(\r\\3WC2,X\rF\u0001>!\tIa(\u0003\u0002@\t\t1Ai\\;cY\u0016DQ!\u0011\u0001\u0005B\t\u000b!B\u001a7pCR4\u0016\r\\;f)\u0005\u0019\u0005CA\u0005E\u0013\t)EAA\u0003GY>\fG\u000fC\u0003H\u0001\u0011\u0005\u0003*A\u0005m_:<g+\u00197vKR\t\u0011\n\u0005\u0002\n\u0015&\u00111\n\u0002\u0002\u0005\u0019>tw\rC\u0003N\u0001\u0011\u0005c*\u0001\u0005j]R4\u0016\r\\;f)\u0005y\u0005CA\u0005Q\u0013\t\tFAA\u0002J]RDQa\u0015\u0001\u0005BQ\u000b\u0011BY=uKZ\u000bG.^3\u0015\u0003AAQA\u0016\u0001\u0005B]\u000b!b\u001d5peR4\u0016\r\\;f)\u0005A\u0006CA\u0005Z\u0013\tQFAA\u0003TQ>\u0014H\u000fC\u0003]\u0001\u0011\u0005S,A\u0006jgZ\u000bG.\u001b3CsR,W#\u00010\u0011\u0005%y\u0016B\u00011\u0005\u0005\u001d\u0011un\u001c7fC:DQA\u0019\u0001\u0005BU\t1!\u00192t\u0011\u0015!\u0007\u0001\"\u0011f\u0003\ri\u0017\r\u001f\u000b\u0003!\u0019DQaZ2A\u0002A\tA\u0001\u001e5bi\")\u0011\u000e\u0001C!U\u0006\u0019Q.\u001b8\u0015\u0005AY\u0007\"B4i\u0001\u0004\u0001\u0002\"B7\u0001\t\u0003r\u0017AB:jO:,X.F\u0001P\u0011\u001d\u0001\b!!A\u0005B9\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u0005\be\u0002\t\t\u0011\"\u0011t\u0003\u0019)\u0017/^1mgR\u0011a\f\u001e\u0005\bkF\f\t\u00111\u0001w\u0003\rAH%\r\t\u0003\u0013]L!\u0001\u001f\u0003\u0003\u0007\u0005s\u0017pB\u0004{\u0005\u0005\u0005\t\u0012A>\u0002\u0011IK7\r\u001b\"zi\u0016\u0004\"!\u0004?\u0007\u000f\u0005\u0011\u0011\u0011!E\u0001{N\u0011AP \t\u0003\u0013}L1!!\u0001\u0005\u0005\u0019\te.\u001f*fM\"1\u0011\u0004 C\u0001\u0003\u000b!\u0012a\u001f\u0005\b\u0003\u0013aHQAA\u0006\u00035qW/\u001c\u0013fqR,gn]5p]R\u0019\u0001%!\u0004\t\u000f\u0005=\u0011q\u0001a\u00017\u0005)A\u0005\u001e5jg\"9\u00111\u0003?\u0005\u0006\u0005U\u0011!D8sI\u0012*\u0007\u0010^3og&|g\u000eF\u00024\u0003/Aq!a\u0004\u0002\u0012\u0001\u00071\u0004C\u0004\u0002\u001cq$)!!\b\u0002+\u0011|WO\u00197f-\u0006dW/\u001a\u0013fqR,gn]5p]R\u0019A(a\b\t\u000f\u0005=\u0011\u0011\u0004a\u00017!9\u00111\u0005?\u0005\u0006\u0005\u0015\u0012\u0001\u00064m_\u0006$h+\u00197vK\u0012*\u0007\u0010^3og&|g\u000eF\u0002C\u0003OAq!a\u0004\u0002\"\u0001\u00071\u0004C\u0004\u0002,q$)!!\f\u0002'1|gn\u001a,bYV,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007!\u000by\u0003C\u0004\u0002\u0010\u0005%\u0002\u0019A\u000e\t\u000f\u0005MB\u0010\"\u0002\u00026\u0005\u0011\u0012N\u001c;WC2,X\rJ3yi\u0016t7/[8o)\rq\u0015q\u0007\u0005\b\u0003\u001f\t\t\u00041\u0001\u001c\u0011\u001d\tY\u0004 C\u0003\u0003{\t1CY=uKZ\u000bG.^3%Kb$XM\\:j_:$2\u0001VA \u0011\u001d\ty!!\u000fA\u0002mAq!a\u0011}\t\u000b\t)%\u0001\u000btQ>\u0014HOV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004/\u0006\u001d\u0003bBA\b\u0003\u0003\u0002\ra\u0007\u0005\b\u0003\u0017bHQAA'\u0003UI7OV1mS\u0012\u0014\u0015\u0010^3%Kb$XM\\:j_:$2AXA(\u0011\u001d\ty!!\u0013A\u0002mAq!a\u0015}\t\u000b\t)&A\u0007bEN$S\r\u001f;f]NLwN\u001c\u000b\u0004!\u0005]\u0003bBA\b\u0003#\u0002\ra\u0007\u0005\b\u00037bHQAA/\u00035i\u0017\r\u001f\u0013fqR,gn]5p]R!\u0011qLA2)\r\u0001\u0012\u0011\r\u0005\u0007O\u0006e\u0003\u0019\u0001\t\t\u000f\u0005=\u0011\u0011\fa\u00017!9\u0011q\r?\u0005\u0006\u0005%\u0014!D7j]\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002l\u0005=Dc\u0001\t\u0002n!1q-!\u001aA\u0002AAq!a\u0004\u0002f\u0001\u00071\u0004C\u0004\u0002tq$)!!\u001e\u0002!MLwM\\;nI\u0015DH/\u001a8tS>tGcA(\u0002x!9\u0011qBA9\u0001\u0004Y\u0002\"CA>y\u0006\u0005IQAA?\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u00079\u000by\bC\u0004\u0002\u0010\u0005e\u0004\u0019A\u000e\t\u0013\u0005\rE0!A\u0005\u0006\u0005\u0015\u0015\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\t9)a#\u0015\u0007y\u000bI\t\u0003\u0005v\u0003\u0003\u000b\t\u00111\u0001w\u0011\u001d\ty!!!A\u0002m\u0001")
public final class RichByte
implements ScalaWholeNumberProxy<Object> {
    private final byte self;

    public static boolean equals$extension(byte by2, Object object) {
        return RichByte$.MODULE$.equals$extension(by2, object);
    }

    public static int hashCode$extension(byte by2) {
        return RichByte$.MODULE$.hashCode$extension(by2);
    }

    public static int signum$extension(byte by2) {
        return RichByte$.MODULE$.signum$extension(by2);
    }

    public static byte min$extension(byte by2, byte by3) {
        return RichByte$.MODULE$.min$extension(by2, by3);
    }

    public static byte max$extension(byte by2, byte by3) {
        return RichByte$.MODULE$.max$extension(by2, by3);
    }

    public static byte abs$extension(byte by2) {
        return RichByte$.MODULE$.abs$extension(by2);
    }

    public static boolean isValidByte$extension(byte by2) {
        return RichByte$.MODULE$.isValidByte$extension(by2);
    }

    public static short shortValue$extension(byte by2) {
        return RichByte$.MODULE$.shortValue$extension(by2);
    }

    public static byte byteValue$extension(byte by2) {
        return RichByte$.MODULE$.byteValue$extension(by2);
    }

    public static int intValue$extension(byte by2) {
        return RichByte$.MODULE$.intValue$extension(by2);
    }

    public static long longValue$extension(byte by2) {
        return RichByte$.MODULE$.longValue$extension(by2);
    }

    public static float floatValue$extension(byte by2) {
        return RichByte$.MODULE$.floatValue$extension(by2);
    }

    public static double doubleValue$extension(byte by2) {
        return RichByte$.MODULE$.doubleValue$extension(by2);
    }

    public static Ordering$Byte$ ord$extension(byte by2) {
        return RichByte$.MODULE$.ord$extension(by2);
    }

    public static Numeric$ByteIsIntegral$ num$extension(byte by2) {
        return RichByte$.MODULE$.num$extension(by2);
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
    public boolean isValidShort() {
        return ScalaNumericAnyConversions$class.isValidShort(this);
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
    public byte self() {
        return this.self;
    }

    public Numeric$ByteIsIntegral$ num() {
        return RichByte$.MODULE$.num$extension(this.self());
    }

    public Ordering$Byte$ ord() {
        return RichByte$.MODULE$.ord$extension(this.self());
    }

    @Override
    public double doubleValue() {
        return RichByte$.MODULE$.doubleValue$extension(this.self());
    }

    @Override
    public float floatValue() {
        return RichByte$.MODULE$.floatValue$extension(this.self());
    }

    @Override
    public long longValue() {
        return RichByte$.MODULE$.longValue$extension(this.self());
    }

    @Override
    public int intValue() {
        return RichByte$.MODULE$.intValue$extension(this.self());
    }

    @Override
    public byte byteValue() {
        return RichByte$.MODULE$.byteValue$extension(this.self());
    }

    @Override
    public short shortValue() {
        return RichByte$.MODULE$.shortValue$extension(this.self());
    }

    @Override
    public boolean isValidByte() {
        return RichByte$.MODULE$.isValidByte$extension(this.self());
    }

    @Override
    public byte abs() {
        return RichByte$.MODULE$.abs$extension(this.self());
    }

    @Override
    public byte max(byte that) {
        return RichByte$.MODULE$.max$extension(this.self(), that);
    }

    @Override
    public byte min(byte that) {
        return RichByte$.MODULE$.min$extension(this.self(), that);
    }

    @Override
    public int signum() {
        return RichByte$.MODULE$.signum$extension(this.self());
    }

    @Override
    public int hashCode() {
        return RichByte$.MODULE$.hashCode$extension(this.self());
    }

    @Override
    public boolean equals(Object x$1) {
        return RichByte$.MODULE$.equals$extension(this.self(), x$1);
    }

    public RichByte(byte self) {
        this.self = self;
        ScalaNumericAnyConversions$class.$init$(this);
        Proxy$class.$init$(this);
        Ordered$class.$init$(this);
    }
}

