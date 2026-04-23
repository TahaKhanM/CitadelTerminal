/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.reflect.ScalaSignature;
import scala.reflect.io.Streamable$;

@ScalaSignature(bytes="\u0006\u0001\u00055u!B\u0001\u0003\u0011\u0003I\u0011AC*ue\u0016\fW.\u00192mK*\u00111\u0001B\u0001\u0003S>T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u0015M#(/Z1nC\ndWm\u0005\u0002\f\u001dA\u0011q\u0002E\u0007\u0002\r%\u0011\u0011C\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000bMYA\u0011\u0001\u000b\u0002\rqJg.\u001b;?)\u0005Iaa\u0002\f\f!\u0003\r\ta\u0006\u0002\u0006\u0005f$Xm]\n\u0003+9AQ!G\u000b\u0005\u0002i\ta\u0001J5oSR$C#A\u000e\u0011\u0005=a\u0012BA\u000f\u0007\u0005\u0011)f.\u001b;\t\u000b})b\u0011\u0001\u0011\u0002\u0017%t\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0002CA\u0011!EJ\u0007\u0002G)\u00111\u0001\n\u0006\u0002K\u0005!!.\u0019<b\u0013\t93EA\u0006J]B,Ho\u0015;sK\u0006l\u0007\"B\u0015\u0016\t\u0003Q\u0013A\u00027f]\u001e$\b.F\u0001,!\tyA&\u0003\u0002.\r\t!Aj\u001c8h\u0011\u0015yS\u0003\"\u00011\u00035\u0011WO\u001a4fe\u0016$\u0017J\u001c9viR\t\u0011\u0007\u0005\u0002#e%\u00111g\t\u0002\u0014\u0005V4g-\u001a:fI&s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\u0006kU!\tAN\u0001\u0006Ef$Xm\u001d\u000b\u0002oA\u0019\u0001h\u000f \u000f\u0005=I\u0014B\u0001\u001e\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0011%#XM]1u_JT!A\u000f\u0004\u0011\u0005=y\u0014B\u0001!\u0007\u0005\u0011\u0011\u0015\u0010^3\t\u000b\t+B\u0011A\"\u0002\u0017\tLH/Z:Bg&sGo\u001d\u000b\u0002\tB\u0019\u0001hO#\u0011\u0005=1\u0015BA$\u0007\u0005\rIe\u000e\u001e\u0005\u0006\u0013V!\tAS\u0001\fi>\u0014\u0015\u0010^3BeJ\f\u0017\u0010F\u0001L!\ryAJP\u0005\u0003\u001b\u001a\u0011Q!\u0011:sCf4qaT\u0006\u0011\u0002\u0007\u0005\u0001KA\u0003DQ\u0006\u00148oE\u0002O\u001dE\u0003\"AU\u000b\u000e\u0003-AQ!\u0007(\u0005\u0002iAQ!\u0016(\u0005\u0002Y\u000bQb\u0019:fCRLwN\\\"pI\u0016\u001cW#A,\u0011\u0005aSV\"A-\u000b\u0005\r1\u0011BA.Z\u0005\u0015\u0019u\u000eZ3d\u0011\u0015if\n\"\u0001_\u0003\u0015\u0019\u0007.\u0019:t)\ty&\r\u0005\u0002YA&\u0011\u0011-\u0017\u0002\u000f\u0005V4g-\u001a:fIN{WO]2f\u0011\u0015\u0019G\f1\u0001X\u0003\u0015\u0019w\u000eZ3d\u0011\u0015)g\n\"\u0001g\u0003\u0015a\u0017N\\3t)\u00059\u0007c\u0001\u001d<QB\u0011\u0011\u000e\u001c\b\u0003\u001f)L!a\u001b\u0004\u0002\rA\u0013X\rZ3g\u0013\tigN\u0001\u0004TiJLgn\u001a\u0006\u0003W\u001aAQ!\u001a(\u0005\u0002A$\"aZ9\t\u000b\r|\u0007\u0019A,\t\u000bMtE\u0011\u0001;\u0002\rI,\u0017\rZ3s)\t)\b\u0010\u0005\u0002#m&\u0011qo\t\u0002\u0012\u0013:\u0004X\u000f^*ue\u0016\fWNU3bI\u0016\u0014\b\"B2s\u0001\u00049\u0006\"\u0002>O\t\u0003Y\u0018A\u00042vM\u001a,'/\u001a3SK\u0006$WM\u001d\u000b\u0002yB\u0011!%`\u0005\u0003}\u000e\u0012aBQ;gM\u0016\u0014X\r\u001a*fC\u0012,'\u000f\u0003\u0004{\u001d\u0012\u0005\u0011\u0011\u0001\u000b\u0004y\u0006\r\u0001\"B2\u0000\u0001\u00049\u0006bBA\u0004\u001d\u0012\u0005\u0011\u0011B\u0001\fCB\u0004H.\u001f*fC\u0012,'/\u0006\u0003\u0002\f\u0005EA\u0003BA\u0007\u0003G\u0001B!a\u0004\u0002\u00121\u0001A\u0001CA\n\u0003\u000b\u0011\r!!\u0006\u0003\u0003Q\u000bB!a\u0006\u0002\u001eA\u0019q\"!\u0007\n\u0007\u0005maAA\u0004O_RD\u0017N\\4\u0011\u0007=\ty\"C\u0002\u0002\"\u0019\u00111!\u00118z\u0011!\t)#!\u0002A\u0002\u0005\u001d\u0012!\u00014\u0011\r=\tI\u0003`A\u0007\u0013\r\tYC\u0002\u0002\n\rVt7\r^5p]FBq!a\fO\t\u0003\t\t$A\u0003tYV\u0014\b\u000fF\u0001i\u0011\u001d\tyC\u0014C\u0001\u0003k!2\u0001[A\u001c\u0011\u0019\u0019\u00171\u0007a\u0001/\"9\u00111H\u0006\u0005\u0002\u0005u\u0012aB2m_NLgnZ\u000b\u0007\u0003\u007f\ty%!\u0012\u0015\t\u0005\u0005\u0013\u0011\f\u000b\u0005\u0003\u0007\nI\u0005\u0005\u0003\u0002\u0010\u0005\u0015C\u0001CA$\u0003s\u0011\r!!\u0006\u0003\u0003UC\u0001\"!\n\u0002:\u0001\u0007\u00111\n\t\b\u001f\u0005%\u0012QJA\"!\u0011\ty!a\u0014\u0005\u0011\u0005M\u0011\u0011\bb\u0001\u0003#\nB!a\u0006\u0002TA\u0019!%!\u0016\n\u0007\u0005]3EA\u0005DY>\u001cX-\u00192mK\"A\u00111LA\u001d\u0001\u0004\ti%\u0001\u0004tiJ,\u0017-\u001c\u0005\u0007k-!\t!a\u0018\u0015\u0007-\u000b\t\u0007C\u0005\u0002d\u0005uC\u00111\u0001\u0002f\u0005\u0011\u0011n\u001d\t\u0005\u001f\u0005\u001d\u0014%C\u0002\u0002j\u0019\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\b\u0003_YA\u0011AA7)\u0011\ty'a\u001d\u0015\u0007!\f\t\b\u0003\u0004d\u0003W\u0002\u001da\u0016\u0005\n\u0003G\nY\u0007\"a\u0001\u0003KBq!a\f\f\t\u0003\t9\b\u0006\u0003\u0002z\u0005uDc\u00015\u0002|!11-!\u001eA\u0004]C\u0001\"a \u0002v\u0001\u0007\u0011\u0011Q\u0001\u0004kJd\u0007\u0003BAB\u0003\u0013k!!!\"\u000b\u0007\u0005\u001dE%A\u0002oKRLA!a#\u0002\u0006\n\u0019QK\u0015'")
public final class Streamable {
    public static String slurp(URL uRL, Codec codec) {
        return Streamable$.MODULE$.slurp(uRL, codec);
    }

    public static String slurp(Function0<InputStream> function0, Codec codec) {
        return Streamable$.MODULE$.slurp(function0, codec);
    }

    public static byte[] bytes(Function0<InputStream> function0) {
        return Streamable$.MODULE$.bytes(function0);
    }

    public static <T extends Closeable, U> U closing(T t, Function1<T, U> function1) {
        return Streamable$.MODULE$.closing(t, function1);
    }

    public static interface Bytes {
        public InputStream inputStream();

        public long length();

        public BufferedInputStream bufferedInput();

        public Iterator<Object> bytes();

        public Iterator<Object> bytesAsInts();

        public byte[] toByteArray();
    }

    public static interface Chars
    extends Bytes {
        public Codec creationCodec();

        public BufferedSource chars(Codec var1);

        public Iterator<String> lines();

        public Iterator<String> lines(Codec var1);

        public InputStreamReader reader(Codec var1);

        public BufferedReader bufferedReader();

        public BufferedReader bufferedReader(Codec var1);

        public <T> T applyReader(Function1<BufferedReader, T> var1);

        public String slurp();

        public String slurp(Codec var1);
    }
}

