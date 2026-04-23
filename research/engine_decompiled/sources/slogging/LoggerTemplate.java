/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import slogging.AbstractUnderlyingLogger;
import slogging.LoggerConfig$;
import slogging.MessageLevel;
import slogging.MessageLevel$debug$;
import slogging.MessageLevel$error$;
import slogging.MessageLevel$info$;
import slogging.MessageLevel$trace$;
import slogging.MessageLevel$warn$;

@ScalaSignature(bytes="\u0006\u0001\u00055c!B\u0001\u0003\u0003\u0003)!A\u0004'pO\u001e,'\u000fV3na2\fG/\u001a\u0006\u0002\u0007\u0005A1\u000f\\8hO&twm\u0001\u0001\u0014\u0005\u00011\u0001CA\u0004\t\u001b\u0005\u0011\u0011BA\u0005\u0003\u0005a\t%m\u001d;sC\u000e$XK\u001c3fe2L\u0018N\\4M_\u001e<WM\u001d\u0005\u0006\u0017\u0001!\t\u0001D\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u0001\"a\u0002\u0001\t\u000b=\u0001AQ\u0002\t\u0002\u00071|w\r\u0006\u0003\u0012/q)\u0003C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"\u0001B+oSRDQ\u0001\u0007\bA\u0002e\tQ\u0001\\3wK2\u0004\"a\u0002\u000e\n\u0005m\u0011!\u0001D'fgN\fw-\u001a'fm\u0016d\u0007\"B\u000f\u000f\u0001\u0004q\u0012aA:sGB\u0011qD\t\b\u0003%\u0001J!!I\n\u0002\rA\u0013X\rZ3g\u0013\t\u0019CE\u0001\u0004TiJLgn\u001a\u0006\u0003CMAQA\n\bA\u0002y\t1!\\:hQ\tq\u0001\u0006\u0005\u0002\u0013S%\u0011!f\u0005\u0002\u0007S:d\u0017N\\3\t\u000b=\u0001AQ\u0002\u0017\u0015\u000bEicf\f\u0019\t\u000baY\u0003\u0019A\r\t\u000buY\u0003\u0019\u0001\u0010\t\u000b\u0019Z\u0003\u0019\u0001\u0010\t\u000bEZ\u0003\u0019\u0001\u001a\u0002\t\u0005\u0014xm\u001d\t\u0004%M*\u0014B\u0001\u001b\u0014\u0005)a$/\u001a9fCR,GM\u0010\t\u0003%YJ!aN\n\u0003\u0007\u0005s\u0017\u0010\u000b\u0002,Q!a!\b\u0001C\u0001\u0002\u000b\u0005\t\u0011!C\u0007w\u0005a2\u000f\\8hO&tw\r\n'pO\u001e,'\u000fV3na2\fG/\u001a\u0013%Y><G#B\t={yz\u0004\"\u0002\r:\u0001\u0004I\u0002\"B\u000f:\u0001\u0004q\u0002\"\u0002\u0014:\u0001\u0004q\u0002\"\u0002!:\u0001\u0004\t\u0015!B2bkN,\u0007c\u0001\nC\t&\u00111i\u0005\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0015keB\u0001$L\u001d\t9%*D\u0001I\u0015\tIE!\u0001\u0004=e>|GOP\u0005\u0002)%\u0011AjE\u0001\ba\u0006\u001c7.Y4f\u0013\tquJA\u0005UQJ|w/\u00192mK*\u0011Aj\u0005\u0015\u0003s!BQA\u0015\u0001\u0005\u0006M\u000bQ!\u001a:s_J$2!\u0005+W\u0011\u0015)\u0016\u000b1\u0001\u001f\u0003\u0019\u0019x.\u001e:dK\")q+\u0015a\u0001=\u00059Q.Z:tC\u001e,\u0007\"\u0002*\u0001\t\u000bIF\u0003B\t[7rCQ!\u0016-A\u0002yAQa\u0016-A\u0002yAQ\u0001\u0011-A\u0002\u0011CQA\u0015\u0001\u0005\u0006y#B!E0aC\")Q+\u0018a\u0001=!)q+\u0018a\u0001=!)\u0011'\u0018a\u0001e!)1\r\u0001C\u0003I\u0006!q/\u0019:o)\r\tRM\u001a\u0005\u0006+\n\u0004\rA\b\u0005\u0006/\n\u0004\rA\b\u0005\u0006G\u0002!)\u0001\u001b\u000b\u0005#%T7\u000eC\u0003VO\u0002\u0007a\u0004C\u0003XO\u0002\u0007a\u0004C\u0003AO\u0002\u0007A\tC\u0003d\u0001\u0011\u0015Q\u000e\u0006\u0003\u0012]>\u0004\b\"B+m\u0001\u0004q\u0002\"B,m\u0001\u0004q\u0002\"B\u0019m\u0001\u0004\u0011\u0004\"\u0002:\u0001\t\u000b\u0019\u0018\u0001B5oM>$2!\u0005;v\u0011\u0015)\u0016\u000f1\u0001\u001f\u0011\u00159\u0016\u000f1\u0001\u001f\u0011\u0015\u0011\b\u0001\"\u0002x)\u0011\t\u00020\u001f>\t\u000bU3\b\u0019\u0001\u0010\t\u000b]3\b\u0019\u0001\u0010\t\u000b\u00013\b\u0019\u0001#\t\u000bI\u0004AQ\u0001?\u0015\tEihp \u0005\u0006+n\u0004\rA\b\u0005\u0006/n\u0004\rA\b\u0005\u0006cm\u0004\rA\r\u0005\b\u0003\u0007\u0001AQAA\u0003\u0003\u0015!WMY;h)\u0015\t\u0012qAA\u0005\u0011\u0019)\u0016\u0011\u0001a\u0001=!1q+!\u0001A\u0002yAq!a\u0001\u0001\t\u000b\ti\u0001F\u0004\u0012\u0003\u001f\t\t\"a\u0005\t\rU\u000bY\u00011\u0001\u001f\u0011\u00199\u00161\u0002a\u0001=!1\u0001)a\u0003A\u0002\u0011Cq!a\u0001\u0001\t\u000b\t9\u0002F\u0004\u0012\u00033\tY\"!\b\t\rU\u000b)\u00021\u0001\u001f\u0011\u00199\u0016Q\u0003a\u0001=!1\u0011'!\u0006A\u0002IBq!!\t\u0001\t\u000b\t\u0019#A\u0003ue\u0006\u001cW\rF\u0003\u0012\u0003K\t9\u0003\u0003\u0004V\u0003?\u0001\rA\b\u0005\u0007/\u0006}\u0001\u0019\u0001\u0010\t\u000f\u0005\u0005\u0002\u0001\"\u0002\u0002,Q9\u0011#!\f\u00020\u0005E\u0002BB+\u0002*\u0001\u0007a\u0004\u0003\u0004X\u0003S\u0001\rA\b\u0005\u0007\u0001\u0006%\u0002\u0019\u0001#\t\u000f\u0005\u0005\u0002\u0001\"\u0002\u00026Q9\u0011#a\u000e\u0002:\u0005m\u0002BB+\u00024\u0001\u0007a\u0004\u0003\u0004X\u0003g\u0001\rA\b\u0005\u0007c\u0005M\u0002\u0019\u0001\u001a\t\u000f\u0005}\u0002A\"\u0001\u0002B\u0005QAn\\4NKN\u001c\u0018mZ3\u0015\u0013E\t\u0019%!\u0012\u0002J\u0005-\u0003B\u0002\r\u0002>\u0001\u0007\u0011\u0004C\u0004\u0002H\u0005u\u0002\u0019\u0001\u0010\u0002\t9\fW.\u001a\u0005\u0007/\u0006u\u0002\u0019\u0001\u0010\t\r\u0001\u000bi\u00041\u0001B\u0001")
public abstract class LoggerTemplate
extends AbstractUnderlyingLogger {
    private final void log(MessageLevel level, String src, String msg) {
        this.slogging$LoggerTemplate$$log(level, src, msg, None$.MODULE$);
    }

    private final void log(MessageLevel level, String src, String msg, Seq<Object> args) {
        this.slogging$LoggerTemplate$$log(level, src, LoggerConfig$.MODULE$.argsFormatter().apply(msg, args), None$.MODULE$);
    }

    public final void slogging$LoggerTemplate$$log(MessageLevel level, String src, String msg, Option<Throwable> cause) {
        this.logMessage(level, src, msg, cause);
    }

    @Override
    public final void error(String source, String message) {
        this.log(MessageLevel$error$.MODULE$, source, message);
    }

    @Override
    public final void error(String source, String message, Throwable cause) {
        this.log(MessageLevel$error$.MODULE$, source, message, Predef$.MODULE$.genericWrapArray(new Object[]{cause}));
    }

    @Override
    public final void error(String source, String message, Seq<Object> args) {
        this.log(MessageLevel$error$.MODULE$, source, message, args);
    }

    @Override
    public final void warn(String source, String message) {
        this.log(MessageLevel$warn$.MODULE$, source, message);
    }

    @Override
    public final void warn(String source, String message, Throwable cause) {
        this.log(MessageLevel$warn$.MODULE$, source, message, Predef$.MODULE$.genericWrapArray(new Object[]{cause}));
    }

    @Override
    public final void warn(String source, String message, Seq<Object> args) {
        this.log(MessageLevel$warn$.MODULE$, source, message, args);
    }

    @Override
    public final void info(String source, String message) {
        this.log(MessageLevel$info$.MODULE$, source, message);
    }

    @Override
    public final void info(String source, String message, Throwable cause) {
        this.log(MessageLevel$info$.MODULE$, source, message, Predef$.MODULE$.genericWrapArray(new Object[]{cause}));
    }

    @Override
    public final void info(String source, String message, Seq<Object> args) {
        this.log(MessageLevel$info$.MODULE$, source, message, args);
    }

    @Override
    public final void debug(String source, String message) {
        this.log(MessageLevel$debug$.MODULE$, source, message);
    }

    @Override
    public final void debug(String source, String message, Throwable cause) {
        this.log(MessageLevel$debug$.MODULE$, source, message, Predef$.MODULE$.genericWrapArray(new Object[]{cause}));
    }

    @Override
    public final void debug(String source, String message, Seq<Object> args) {
        this.log(MessageLevel$debug$.MODULE$, source, message, args);
    }

    @Override
    public final void trace(String source, String message) {
        this.log(MessageLevel$trace$.MODULE$, source, message);
    }

    @Override
    public final void trace(String source, String message, Throwable cause) {
        this.log(MessageLevel$trace$.MODULE$, source, message, Predef$.MODULE$.genericWrapArray(new Object[]{cause}));
    }

    @Override
    public final void trace(String source, String message, Seq<Object> args) {
        this.log(MessageLevel$trace$.MODULE$, source, message, args);
    }

    public abstract void logMessage(MessageLevel var1, String var2, String var3, Option<Throwable> var4);
}

