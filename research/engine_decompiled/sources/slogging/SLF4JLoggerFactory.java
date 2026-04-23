/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import org.slf4j.Logger;
import scala.collection.Seq;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import slogging.AbstractUnderlyingLogger;
import slogging.SLF4JLoggerFactory$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;

@ScalaSignature(bytes="\u0006\u0001\u0005ur!B\u0001\u0003\u0011\u0003)\u0011AE*M\rRREj\\4hKJ4\u0015m\u0019;pefT\u0011aA\u0001\tg2|wmZ5oO\u000e\u0001\u0001C\u0001\u0004\b\u001b\u0005\u0011a!\u0002\u0005\u0003\u0011\u0003I!AE*M\rRREj\\4hKJ4\u0015m\u0019;pef\u001c2a\u0002\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011a!E\u0005\u0003%\t\u0011q#\u00168eKJd\u00170\u001b8h\u0019><w-\u001a:GC\u000e$xN]=\t\u000bQ9A\u0011A\u000b\u0002\rqJg.\u001b;?)\u0005)\u0001\"B\f\b\t\u0003B\u0012aE4fiVsG-\u001a:ms&tw\rT8hO\u0016\u0014HCA\r\u001d!\t1!$\u0003\u0002\u001c\u0005\t\u0001RK\u001c3fe2L\u0018N\\4M_\u001e<WM\u001d\u0005\u0006;Y\u0001\rAH\u0001\u0005]\u0006lW\r\u0005\u0002 E9\u00111\u0002I\u0005\u0003C1\ta\u0001\u0015:fI\u00164\u0017BA\u0012%\u0005\u0019\u0019FO]5oO*\u0011\u0011\u0005\u0004\u0004\u0005M\u001d\u0001qEA\u0006T\u0019\u001a#$\nT8hO\u0016\u00148CA\u0013)!\t1\u0011&\u0003\u0002+\u0005\tA\u0012IY:ue\u0006\u001cG/\u00168eKJd\u00170\u001b8h\u0019><w-\u001a:\t\u00111*#\u0011!Q\u0001\n5\n\u0011\u0001\u001c\t\u0003]Mj\u0011a\f\u0006\u0003aE\nQa\u001d7gi)T\u0011AM\u0001\u0004_J<\u0017B\u0001\u001b0\u0005\u0019aunZ4fe\")A#\nC\u0001mQ\u0011q'\u000f\t\u0003q\u0015j\u0011a\u0002\u0005\u0006YU\u0002\r!\f\u0005\u0006w\u0015\"\t\u0005P\u0001\u0006KJ\u0014xN\u001d\u000b\u0004{\u0001\u0013\u0005CA\u0006?\u0013\tyDB\u0001\u0003V]&$\b\"B!;\u0001\u0004q\u0012AB:pkJ\u001cW\rC\u0003Du\u0001\u0007a$A\u0004nKN\u001c\u0018mZ3\t\u000bm*C\u0011I#\u0015\tu2u\t\u0013\u0005\u0006\u0003\u0012\u0003\rA\b\u0005\u0006\u0007\u0012\u0003\rA\b\u0005\u0006\u0013\u0012\u0003\rAS\u0001\u0006G\u0006,8/\u001a\t\u0003\u0017Ns!\u0001T)\u000f\u00055\u0003V\"\u0001(\u000b\u0005=#\u0011A\u0002\u001fs_>$h(C\u0001\u000e\u0013\t\u0011F\"A\u0004qC\u000e\\\u0017mZ3\n\u0005Q+&!\u0003+ie><\u0018M\u00197f\u0015\t\u0011F\u0002C\u0003<K\u0011\u0005s\u000b\u0006\u0003>1fS\u0006\"B!W\u0001\u0004q\u0002\"B\"W\u0001\u0004q\u0002\"B.W\u0001\u0004a\u0016\u0001B1sON\u00042aC/`\u0013\tqFB\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002\"a\u00031\n\u0005\u0005d!aA!os\")1-\nC!I\u0006!q/\u0019:o)\riTM\u001a\u0005\u0006\u0003\n\u0004\rA\b\u0005\u0006\u0007\n\u0004\rA\b\u0005\u0006G\u0016\"\t\u0005\u001b\u000b\u0005{%T7\u000eC\u0003BO\u0002\u0007a\u0004C\u0003DO\u0002\u0007a\u0004C\u0003JO\u0002\u0007!\nC\u0003dK\u0011\u0005S\u000e\u0006\u0003>]>\u0004\b\"B!m\u0001\u0004q\u0002\"B\"m\u0001\u0004q\u0002\"B.m\u0001\u0004a\u0006\"\u0002:&\t\u0003\u001a\u0018\u0001B5oM>$2!\u0010;v\u0011\u0015\t\u0015\u000f1\u0001\u001f\u0011\u0015\u0019\u0015\u000f1\u0001\u001f\u0011\u0015\u0011X\u0005\"\u0011x)\u0011i\u00040\u001f>\t\u000b\u00053\b\u0019\u0001\u0010\t\u000b\r3\b\u0019\u0001\u0010\t\u000b%3\b\u0019\u0001&\t\u000bI,C\u0011\t?\u0015\tujhp \u0005\u0006\u0003n\u0004\rA\b\u0005\u0006\u0007n\u0004\rA\b\u0005\u00067n\u0004\r\u0001\u0018\u0005\b\u0003\u0007)C\u0011IA\u0003\u0003\u0015!WMY;h)\u0015i\u0014qAA\u0005\u0011\u0019\t\u0015\u0011\u0001a\u0001=!11)!\u0001A\u0002yAq!a\u0001&\t\u0003\ni\u0001F\u0004>\u0003\u001f\t\t\"a\u0005\t\r\u0005\u000bY\u00011\u0001\u001f\u0011\u0019\u0019\u00151\u0002a\u0001=!1\u0011*a\u0003A\u0002)Cq!a\u0001&\t\u0003\n9\u0002F\u0004>\u00033\tY\"!\b\t\r\u0005\u000b)\u00021\u0001\u001f\u0011\u0019\u0019\u0015Q\u0003a\u0001=!11,!\u0006A\u0002qCq!!\t&\t\u0003\n\u0019#A\u0003ue\u0006\u001cW\rF\u0003>\u0003K\t9\u0003\u0003\u0004B\u0003?\u0001\rA\b\u0005\u0007\u0007\u0006}\u0001\u0019\u0001\u0010\t\u000f\u0005\u0005R\u0005\"\u0011\u0002,Q9Q(!\f\u00020\u0005E\u0002BB!\u0002*\u0001\u0007a\u0004\u0003\u0004D\u0003S\u0001\rA\b\u0005\u0007\u0013\u0006%\u0002\u0019\u0001&\t\u000f\u0005\u0005R\u0005\"\u0011\u00026Q9Q(a\u000e\u0002:\u0005m\u0002BB!\u00024\u0001\u0007a\u0004\u0003\u0004D\u0003g\u0001\rA\b\u0005\u00077\u0006M\u0002\u0019\u0001/")
public final class SLF4JLoggerFactory {
    public static UnderlyingLoggerFactory apply() {
        return SLF4JLoggerFactory$.MODULE$.apply();
    }

    public static UnderlyingLogger getUnderlyingLogger(String string2) {
        return SLF4JLoggerFactory$.MODULE$.getUnderlyingLogger(string2);
    }

    public static class SLF4JLogger
    extends AbstractUnderlyingLogger {
        private final Logger l;

        @Override
        public void error(String source, String message) {
            this.l.error(message);
        }

        @Override
        public void error(String source, String message, Throwable cause) {
            this.l.error(message, cause);
        }

        @Override
        public void error(String source, String message, Seq<Object> args) {
            this.l.error(message, (Object[])args.toArray(ClassTag$.MODULE$.AnyRef()));
        }

        @Override
        public void warn(String source, String message) {
            this.l.warn(message);
        }

        @Override
        public void warn(String source, String message, Throwable cause) {
            this.l.warn(message, cause);
        }

        @Override
        public void warn(String source, String message, Seq<Object> args) {
            this.l.warn(message, (Object[])args.toArray(ClassTag$.MODULE$.AnyRef()));
        }

        @Override
        public void info(String source, String message) {
            this.l.info(message);
        }

        @Override
        public void info(String source, String message, Throwable cause) {
            this.l.info(message, cause);
        }

        @Override
        public void info(String source, String message, Seq<Object> args) {
            this.l.info(message, (Object[])args.toArray(ClassTag$.MODULE$.AnyRef()));
        }

        @Override
        public void debug(String source, String message) {
            this.l.debug(message);
        }

        @Override
        public void debug(String source, String message, Throwable cause) {
            this.l.debug(message, cause);
        }

        @Override
        public void debug(String source, String message, Seq<Object> args) {
            this.l.debug(message, (Object[])args.toArray(ClassTag$.MODULE$.AnyRef()));
        }

        @Override
        public void trace(String source, String message) {
            this.l.trace(message);
        }

        @Override
        public void trace(String source, String message, Throwable cause) {
            this.l.trace(message, cause);
        }

        @Override
        public void trace(String source, String message, Seq<Object> args) {
            this.l.trace(message, (Object[])args.toArray(ClassTag$.MODULE$.AnyRef()));
        }

        public SLF4JLogger(Logger l) {
            this.l = l;
        }
    }
}

