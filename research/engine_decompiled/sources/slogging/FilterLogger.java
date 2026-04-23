/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import slogging.AbstractUnderlyingLogger;
import slogging.FilterLogger$;
import slogging.LogLevel;
import slogging.LogLevel$DEBUG$;
import slogging.LogLevel$ERROR$;
import slogging.LogLevel$INFO$;
import slogging.LogLevel$TRACE$;
import slogging.LogLevel$WARN$;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015t!B\u0001\u0003\u0011\u0003)\u0011\u0001\u0004$jYR,'\u000fT8hO\u0016\u0014(\"A\u0002\u0002\u0011MdwnZ4j]\u001e\u001c\u0001\u0001\u0005\u0002\u0007\u000f5\t!AB\u0003\t\u0005!\u0005\u0011B\u0001\u0007GS2$XM\u001d'pO\u001e,'o\u0005\u0002\b\u0015A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001aDQ!E\u0004\u0005\u0002I\ta\u0001P5oSRtD#A\u0003\u0006\tQ9\u0001!\u0006\u0002\u0007\r&dG/\u001a:\u0011\t-1\u0002$J\u0005\u0003/1\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0005\u0017eYb$\u0003\u0002\u001b\u0019\t1A+\u001e9mKJ\u0002\"A\u0002\u000f\n\u0005u\u0011!\u0001\u0003'pO2+g/\u001a7\u0011\u0005}\u0011cBA\u0006!\u0013\t\tC\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003G\u0011\u0012aa\u0015;sS:<'BA\u0011\r!\t1a%\u0003\u0002(\u0005\t\u0001RK\u001c3fe2L\u0018N\\4M_\u001e<WM\u001d\u0005\bS\u001d\u0011\r\u0011\"\u0001+\u0003)qW\u000f\u001c7GS2$XM]\u000b\u0002WA\u0011AfE\u0007\u0002\u000f!1af\u0002Q\u0001\n-\n1B\\;mY\u001aKG\u000e^3sA!a\u0001g\u0002C\u0001\u0002\u000b\u0005\t\u0019!C\u0005U\u0005q2\u000f\\8hO&tw\r\n$jYR,'\u000fT8hO\u0016\u0014H\u0005J0gS2$XM\u001d\u0005\ne\u001d\u0011\t\u00111A\u0005\nM\n!e\u001d7pO\u001eLgn\u001a\u0013GS2$XM\u001d'pO\u001e,'\u000f\n\u0013`M&dG/\u001a:`I\u0015\fHC\u0001\u001b8!\tYQ'\u0003\u00027\u0019\t!QK\\5u\u0011\u001dA\u0014'!AA\u0002-\n1\u0001\u001f\u00132\u0011%QtA!A\u0001B\u0003&1&A\u0010tY><w-\u001b8hI\u0019KG\u000e^3s\u0019><w-\u001a:%I}3\u0017\u000e\u001c;fe\u0002BQ\u0001P\u0004\u0005\u0002)\naAZ5mi\u0016\u0014\bFA\u001e?!\tYq(\u0003\u0002A\u0019\t1\u0011N\u001c7j]\u0016DQAQ\u0004\u0005\u0002\r\u000b!BZ5mi\u0016\u0014x\fJ3r)\t!D\tC\u0003F\u0003\u0002\u00071&A\u0001g\r\u0011A!AA$\u0014\u0005\u0019C\u0005C\u0001\u0004J\u0013\tQ%A\u0001\rBEN$(/Y2u+:$WM\u001d7zS:<Gj\\4hKJD\u0001\u0002\u0014$\u0003\u0002\u0003\u0006IAH\u0001\u0005]\u0006lW\rC\u0003\u0012\r\u0012\u0005a\n\u0006\u0002P!B\u0011aA\u0012\u0005\u0006\u00196\u0003\rA\b\u0005\u0006%\u001a#\teU\u0001\u0006KJ\u0014xN\u001d\u000b\u0004iQ3\u0006\"B+R\u0001\u0004q\u0012AB:pkJ\u001cW\rC\u0003X#\u0002\u0007a$A\u0004nKN\u001c\u0018mZ3\t\u000bI3E\u0011I-\u0015\tQR6\f\u0018\u0005\u0006+b\u0003\rA\b\u0005\u0006/b\u0003\rA\b\u0005\u0006;b\u0003\rAX\u0001\u0006G\u0006,8/\u001a\t\u0003?\u001et!\u0001Y3\u000f\u0005\u0005$W\"\u00012\u000b\u0005\r$\u0011A\u0002\u001fs_>$h(C\u0001\u000e\u0013\t1G\"A\u0004qC\u000e\\\u0017mZ3\n\u0005!L'!\u0003+ie><\u0018M\u00197f\u0015\t1G\u0002C\u0003S\r\u0012\u00053\u000e\u0006\u00035Y6t\u0007\"B+k\u0001\u0004q\u0002\"B,k\u0001\u0004q\u0002\"B8k\u0001\u0004\u0001\u0018\u0001B1sON\u00042aC9t\u0013\t\u0011HB\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002\"a\u0003;\n\u0005Ud!aA!os\")qO\u0012C!q\u0006!q/\u0019:o)\r!\u0014P\u001f\u0005\u0006+Z\u0004\rA\b\u0005\u0006/Z\u0004\rA\b\u0005\u0006o\u001a#\t\u0005 \u000b\u0005iutx\u0010C\u0003Vw\u0002\u0007a\u0004C\u0003Xw\u0002\u0007a\u0004C\u0003^w\u0002\u0007a\f\u0003\u0004x\r\u0012\u0005\u00131\u0001\u000b\bi\u0005\u0015\u0011qAA\u0005\u0011\u0019)\u0016\u0011\u0001a\u0001=!1q+!\u0001A\u0002yAaa\\A\u0001\u0001\u0004\u0001\bbBA\u0007\r\u0012\u0005\u0013qB\u0001\u0005S:4w\u000eF\u00035\u0003#\t\u0019\u0002\u0003\u0004V\u0003\u0017\u0001\rA\b\u0005\u0007/\u0006-\u0001\u0019\u0001\u0010\t\u000f\u00055a\t\"\u0011\u0002\u0018Q9A'!\u0007\u0002\u001c\u0005u\u0001BB+\u0002\u0016\u0001\u0007a\u0004\u0003\u0004X\u0003+\u0001\rA\b\u0005\u0007;\u0006U\u0001\u0019\u00010\t\u000f\u00055a\t\"\u0011\u0002\"Q9A'a\t\u0002&\u0005\u001d\u0002BB+\u0002 \u0001\u0007a\u0004\u0003\u0004X\u0003?\u0001\rA\b\u0005\u0007_\u0006}\u0001\u0019\u00019\t\u000f\u0005-b\t\"\u0011\u0002.\u0005)A-\u001a2vOR)A'a\f\u00022!1Q+!\u000bA\u0002yAaaVA\u0015\u0001\u0004q\u0002bBA\u0016\r\u0012\u0005\u0013Q\u0007\u000b\bi\u0005]\u0012\u0011HA\u001e\u0011\u0019)\u00161\u0007a\u0001=!1q+a\rA\u0002yAa!XA\u001a\u0001\u0004q\u0006bBA\u0016\r\u0012\u0005\u0013q\b\u000b\bi\u0005\u0005\u00131IA#\u0011\u0019)\u0016Q\ba\u0001=!1q+!\u0010A\u0002yAaa\\A\u001f\u0001\u0004\u0001\bbBA%\r\u0012\u0005\u00131J\u0001\u0006iJ\f7-\u001a\u000b\u0006i\u00055\u0013q\n\u0005\u0007+\u0006\u001d\u0003\u0019\u0001\u0010\t\r]\u000b9\u00051\u0001\u001f\u0011\u001d\tIE\u0012C!\u0003'\"r\u0001NA+\u0003/\nI\u0006\u0003\u0004V\u0003#\u0002\rA\b\u0005\u0007/\u0006E\u0003\u0019\u0001\u0010\t\ru\u000b\t\u00061\u0001_\u0011\u001d\tIE\u0012C!\u0003;\"r\u0001NA0\u0003C\n\u0019\u0007\u0003\u0004V\u00037\u0002\rA\b\u0005\u0007/\u0006m\u0003\u0019\u0001\u0010\t\r=\fY\u00061\u0001q\u0001")
public final class FilterLogger
extends AbstractUnderlyingLogger {
    private final String name;

    public static void filter_$eq(PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> partialFunction) {
        FilterLogger$.MODULE$.filter_$eq(partialFunction);
    }

    public static PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> filter() {
        return FilterLogger$.MODULE$.filter();
    }

    public static PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> nullFilter() {
        return FilterLogger$.MODULE$.nullFilter();
    }

    @Override
    public void error(String source, String message) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$ERROR$, String>(LogLevel$ERROR$.MODULE$, this.name))).error(source, message);
    }

    @Override
    public void error(String source, String message, Throwable cause) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$ERROR$, String>(LogLevel$ERROR$.MODULE$, this.name))).error(source, message, cause);
    }

    @Override
    public void error(String source, String message, Seq<Object> args) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$ERROR$, String>(LogLevel$ERROR$.MODULE$, this.name))).error(source, message, args);
    }

    @Override
    public void warn(String source, String message) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$WARN$, String>(LogLevel$WARN$.MODULE$, this.name))).warn(source, message);
    }

    @Override
    public void warn(String source, String message, Throwable cause) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$WARN$, String>(LogLevel$WARN$.MODULE$, this.name))).warn(source, message, cause);
    }

    @Override
    public void warn(String source, String message, Seq<Object> args) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$WARN$, String>(LogLevel$WARN$.MODULE$, this.name))).warn(source, message, args);
    }

    @Override
    public void info(String source, String message) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$INFO$, String>(LogLevel$INFO$.MODULE$, this.name))).info(source, message);
    }

    @Override
    public void info(String source, String message, Throwable cause) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$INFO$, String>(LogLevel$INFO$.MODULE$, this.name))).info(source, message, cause);
    }

    @Override
    public void info(String source, String message, Seq<Object> args) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$INFO$, String>(LogLevel$INFO$.MODULE$, this.name))).info(source, message, args);
    }

    @Override
    public void debug(String source, String message) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$DEBUG$, String>(LogLevel$DEBUG$.MODULE$, this.name))).debug(source, message);
    }

    @Override
    public void debug(String source, String message, Throwable cause) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$DEBUG$, String>(LogLevel$DEBUG$.MODULE$, this.name))).debug(source, message, cause);
    }

    @Override
    public void debug(String source, String message, Seq<Object> args) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$DEBUG$, String>(LogLevel$DEBUG$.MODULE$, this.name))).debug(source, message, args);
    }

    @Override
    public void trace(String source, String message) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$TRACE$, String>(LogLevel$TRACE$.MODULE$, this.name))).trace(source, message);
    }

    @Override
    public void trace(String source, String message, Throwable cause) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$TRACE$, String>(LogLevel$TRACE$.MODULE$, this.name))).trace(source, message, cause);
    }

    @Override
    public void trace(String source, String message, Seq<Object> args) {
        ((UnderlyingLogger)FilterLogger$.MODULE$.filter().apply(new Tuple2<LogLevel$TRACE$, String>(LogLevel$TRACE$.MODULE$, this.name))).trace(source, message, args);
    }

    public FilterLogger(String name) {
        this.name = name;
    }
}

