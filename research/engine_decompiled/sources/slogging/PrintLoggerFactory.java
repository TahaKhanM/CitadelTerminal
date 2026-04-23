/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import java.io.PrintStream;
import scala.reflect.ScalaSignature;
import slogging.MessageFormatter;
import slogging.PrintLoggerFactory$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;

@ScalaSignature(bytes="\u0006\u0001\u00055r!B\u0001\u0003\u0011\u0003)\u0011A\u0005)sS:$Hj\\4hKJ4\u0015m\u0019;pefT\u0011aA\u0001\tg2|wmZ5oO\u000e\u0001\u0001C\u0001\u0004\b\u001b\u0005\u0011a!\u0002\u0005\u0003\u0011\u0003I!A\u0005)sS:$Hj\\4hKJ4\u0015m\u0019;pef\u001c2a\u0002\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011a!E\u0005\u0003%\t\u0011q#\u00168eKJd\u00170\u001b8h\u0019><w-\u001a:GC\u000e$xN]=\t\u000bQ9A\u0011A\u000b\u0002\rqJg.\u001b;?)\u0005)\u0001\u0002D\f\b\t\u0003\u0005)\u0011!a\u0001\n\u0013A\u0012!K:m_\u001e<\u0017N\\4%!JLg\u000e\u001e'pO\u001e,'OR1di>\u0014\u0018\u0010\n\u0013`KJ\u0014xN]*ue\u0016\fW.F\u0001\u001a!\tQr$D\u0001\u001c\u0015\taR$\u0001\u0002j_*\ta$\u0001\u0003kCZ\f\u0017B\u0001\u0011\u001c\u0005-\u0001&/\u001b8u'R\u0014X-Y7\t\u0013\t:!\u0011!a\u0001\n\u0013\u0019\u0013!L:m_\u001e<\u0017N\\4%!JLg\u000e\u001e'pO\u001e,'OR1di>\u0014\u0018\u0010\n\u0013`KJ\u0014xN]*ue\u0016\fWn\u0018\u0013fcR\u0011Ae\n\t\u0003\u0017\u0015J!A\n\u0007\u0003\tUs\u0017\u000e\u001e\u0005\bQ\u0005\n\t\u00111\u0001\u001a\u0003\rAH%\r\u0005\nU\u001d\u0011\t\u0011!Q!\ne\t!f\u001d7pO\u001eLgn\u001a\u0013Qe&tG\u000fT8hO\u0016\u0014h)Y2u_JLH\u0005J0feJ|'o\u0015;sK\u0006l\u0007\u0005\u0003\u0007-\u000f\u0011\u0005\tQ!AA\u0002\u0013%\u0001$\u0001\u0015tY><w-\u001b8hIA\u0013\u0018N\u001c;M_\u001e<WM\u001d$bGR|'/\u001f\u0013%?^\f'O\\*ue\u0016\fW\u000eC\u0005/\u000f\t\u0005\t\u0019!C\u0005_\u0005a3\u000f\\8hO&tw\r\n)sS:$Hj\\4hKJ4\u0015m\u0019;pef$CeX<be:\u001cFO]3b[~#S-\u001d\u000b\u0003IABq\u0001K\u0017\u0002\u0002\u0003\u0007\u0011\u0004C\u00053\u000f\t\u0005\t\u0011)Q\u00053\u0005I3\u000f\\8hO&tw\r\n)sS:$Hj\\4hKJ4\u0015m\u0019;pef$CeX<be:\u001cFO]3b[\u0002BA\u0002N\u0004\u0005\u0002\u0003\u0015\t\u00111A\u0005\na\t\u0001f\u001d7pO\u001eLgn\u001a\u0013Qe&tG\u000fT8hO\u0016\u0014h)Y2u_JLH\u0005J0j]\u001a|7\u000b\u001e:fC6D\u0011BN\u0004\u0003\u0002\u0003\u0007I\u0011B\u001c\u0002YMdwnZ4j]\u001e$\u0003K]5oi2{wmZ3s\r\u0006\u001cGo\u001c:zI\u0011z\u0016N\u001c4p'R\u0014X-Y7`I\u0015\fHC\u0001\u00139\u0011\u001dAS'!AA\u0002eA\u0011BO\u0004\u0003\u0002\u0003\u0005\u000b\u0015B\r\u0002SMdwnZ4j]\u001e$\u0003K]5oi2{wmZ3s\r\u0006\u001cGo\u001c:zI\u0011z\u0016N\u001c4p'R\u0014X-Y7!\u00111at\u0001\"A\u0001\u0006\u0003\u0005\r\u0011\"\u0003\u0019\u0003%\u001aHn\\4hS:<G\u0005\u0015:j]RdunZ4fe\u001a\u000b7\r^8ss\u0012\"s\fZ3ck\u001e\u001cFO]3b[\"Iah\u0002B\u0001\u0002\u0004%IaP\u0001.g2|wmZ5oO\u0012\u0002&/\u001b8u\u0019><w-\u001a:GC\u000e$xN]=%I}#WMY;h'R\u0014X-Y7`I\u0015\fHC\u0001\u0013A\u0011\u001dAS(!AA\u0002eA\u0011BQ\u0004\u0003\u0002\u0003\u0005\u000b\u0015B\r\u0002UMdwnZ4j]\u001e$\u0003K]5oi2{wmZ3s\r\u0006\u001cGo\u001c:zI\u0011zF-\u001a2vON#(/Z1nA!aAi\u0002C\u0001\u0002\u000b\u0005\t\u0019!C\u00051\u0005I3\u000f\\8hO&tw\r\n)sS:$Hj\\4hKJ4\u0015m\u0019;pef$Ce\u0018;sC\u000e,7\u000b\u001e:fC6D\u0011BR\u0004\u0003\u0002\u0003\u0007I\u0011B$\u0002[MdwnZ4j]\u001e$\u0003K]5oi2{wmZ3s\r\u0006\u001cGo\u001c:zI\u0011zFO]1dKN#(/Z1n?\u0012*\u0017\u000f\u0006\u0002%\u0011\"9\u0001&RA\u0001\u0002\u0004I\u0002\"\u0003&\b\u0005\u0003\u0005\t\u0015)\u0003\u001a\u0003)\u001aHn\\4hS:<G\u0005\u0015:j]RdunZ4fe\u001a\u000b7\r^8ss\u0012\"s\f\u001e:bG\u0016\u001cFO]3b[\u0002BA\u0002T\u0004\u0005\u0002\u0003\u0015\t\u00111A\u0005\n5\u000bqe\u001d7pO\u001eLgn\u001a\u0013Qe&tG\u000fT8hO\u0016\u0014h)Y2u_JLH\u0005J0g_Jl\u0017\r\u001e;feV\ta\n\u0005\u0002\u0007\u001f&\u0011\u0001K\u0001\u0002\u0011\u001b\u0016\u001c8/Y4f\r>\u0014X.\u0019;uKJD\u0011BU\u0004\u0003\u0002\u0003\u0007I\u0011B*\u0002WMdwnZ4j]\u001e$\u0003K]5oi2{wmZ3s\r\u0006\u001cGo\u001c:zI\u0011zfm\u001c:nCR$XM]0%KF$\"\u0001\n+\t\u000f!\n\u0016\u0011!a\u0001\u001d\"Iak\u0002B\u0001\u0002\u0003\u0006KAT\u0001)g2|wmZ5oO\u0012\u0002&/\u001b8u\u0019><w-\u001a:GC\u000e$xN]=%I}3wN]7biR,'\u000f\t\u0005\u00061\u001e!)\u0001G\u0001\fKJ\u0014xN]*ue\u0016\fW\u000e\u000b\u0002X5B\u00111bW\u0005\u000392\u0011a!\u001b8mS:,\u0007\"\u00020\b\t\u000by\u0016aD3se>\u00148\u000b\u001e:fC6|F%Z9\u0015\u0005\u0011\u0002\u0007\"B1^\u0001\u0004I\u0012!A:\t\u000b\r<AQ\u0001\r\u0002\u0015]\f'O\\*ue\u0016\fW\u000e\u000b\u0002c5\")am\u0002C\u0003O\u0006qq/\u0019:o'R\u0014X-Y7`I\u0015\fHC\u0001\u0013i\u0011\u0015\tW\r1\u0001\u001a\u0011\u0015Qw\u0001\"\u0002\u0019\u0003)IgNZ8TiJ,\u0017-\u001c\u0015\u0003SjCQ!\\\u0004\u0005\u00069\fa\"\u001b8g_N#(/Z1n?\u0012*\u0017\u000f\u0006\u0002%_\")\u0011\r\u001ca\u00013!)\u0011o\u0002C\u00031\u0005YA-\u001a2vON#(/Z1nQ\t\u0001(\fC\u0003u\u000f\u0011\u0015Q/A\beK\n,xm\u0015;sK\u0006lw\fJ3r)\t!c\u000fC\u0003bg\u0002\u0007\u0011\u0004C\u0003y\u000f\u0011\u0015\u0001$A\u0006ue\u0006\u001cWm\u0015;sK\u0006l\u0007FA<[\u0011\u0015Yx\u0001\"\u0002}\u0003=!(/Y2f'R\u0014X-Y7`I\u0015\fHC\u0001\u0013~\u0011\u0015\t'\u00101\u0001\u001a\u0011\u0015yx\u0001\"\u0002N\u0003%1wN]7biR,'\u000f\u000b\u0002\u007f5\"9\u0011QA\u0004\u0005\u0006\u0005\u001d\u0011!\u00044pe6\fG\u000f^3s?\u0012*\u0017\u000fF\u0002%\u0003\u0013Aq!a\u0003\u0002\u0004\u0001\u0007a*A\u0001g\u0011\u001d\tya\u0002C!\u0003#\t1cZ3u+:$WM\u001d7zS:<Gj\\4hKJ$B!a\u0005\u0002\u001aA\u0019a!!\u0006\n\u0007\u0005]!A\u0001\tV]\u0012,'\u000f\\=j]\u001edunZ4fe\"A\u00111DA\u0007\u0001\u0004\ti\"\u0001\u0003oC6,\u0007\u0003BA\u0010\u0003Kq1aCA\u0011\u0013\r\t\u0019\u0003D\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0012\u0011\u0006\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\rB\u0002K\u0002\u0002\u000ei\u0003")
public final class PrintLoggerFactory {
    public static UnderlyingLoggerFactory apply() {
        return PrintLoggerFactory$.MODULE$.apply();
    }

    public static UnderlyingLogger getUnderlyingLogger(String string2) {
        return PrintLoggerFactory$.MODULE$.getUnderlyingLogger(string2);
    }

    public static void formatter_$eq(MessageFormatter messageFormatter) {
        PrintLoggerFactory$.MODULE$.formatter_$eq(messageFormatter);
    }

    public static MessageFormatter formatter() {
        return PrintLoggerFactory$.MODULE$.formatter();
    }

    public static void traceStream_$eq(PrintStream printStream) {
        PrintLoggerFactory$.MODULE$.traceStream_$eq(printStream);
    }

    public static PrintStream traceStream() {
        return PrintLoggerFactory$.MODULE$.traceStream();
    }

    public static void debugStream_$eq(PrintStream printStream) {
        PrintLoggerFactory$.MODULE$.debugStream_$eq(printStream);
    }

    public static PrintStream debugStream() {
        return PrintLoggerFactory$.MODULE$.debugStream();
    }

    public static void infoStream_$eq(PrintStream printStream) {
        PrintLoggerFactory$.MODULE$.infoStream_$eq(printStream);
    }

    public static PrintStream infoStream() {
        return PrintLoggerFactory$.MODULE$.infoStream();
    }

    public static void warnStream_$eq(PrintStream printStream) {
        PrintLoggerFactory$.MODULE$.warnStream_$eq(printStream);
    }

    public static PrintStream warnStream() {
        return PrintLoggerFactory$.MODULE$.warnStream();
    }

    public static void errorStream_$eq(PrintStream printStream) {
        PrintLoggerFactory$.MODULE$.errorStream_$eq(printStream);
    }

    public static PrintStream errorStream() {
        return PrintLoggerFactory$.MODULE$.errorStream();
    }
}

