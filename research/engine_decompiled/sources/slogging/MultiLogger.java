/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.Serializable;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import slogging.LogLevel$DEBUG$;
import slogging.LogLevel$ERROR$;
import slogging.LogLevel$INFO$;
import slogging.LogLevel$TRACE$;
import slogging.LogLevel$WARN$;
import slogging.LoggerConfig$;
import slogging.MultiLogger$;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001\u0005=c\u0001B\u0001\u0003\u0005\u0015\u00111\"T;mi&dunZ4fe*\t1!\u0001\u0005tY><w-\u001b8h\u0007\u0001\u00192\u0001\u0001\u0004\r!\t9!\"D\u0001\t\u0015\u0005I\u0011!B:dC2\f\u0017BA\u0006\t\u0005\u0019\te.\u001f*fMB\u0011QBD\u0007\u0002\u0005%\u0011qB\u0001\u0002\u0011+:$WM\u001d7zS:<Gj\\4hKJD\u0001\"\u0005\u0001\u0003\u0002\u0003\u0006IAE\u0001\bY><w-\u001a:t!\r\u00192\u0004\u0004\b\u0003)eq!!\u0006\r\u000e\u0003YQ!a\u0006\u0003\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0011B\u0001\u000e\t\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001H\u000f\u0003\u0011%#XM]1cY\u0016T!A\u0007\u0005\t\u000b}\u0001A\u0011\u0001\u0011\u0002\rqJg.\u001b;?)\t\t#\u0005\u0005\u0002\u000e\u0001!)\u0011C\ba\u0001%!)A\u0005\u0001C\u0001K\u0005q\u0011n]#se>\u0014XI\\1cY\u0016$W#\u0001\u0014\u0011\u0005\u001d9\u0013B\u0001\u0015\t\u0005\u001d\u0011un\u001c7fC:D#a\t\u0016\u0011\u0005\u001dY\u0013B\u0001\u0017\t\u0005\u0019Ig\u000e\\5oK\")a\u0006\u0001C\u0001K\u0005i\u0011n],be:,e.\u00192mK\u0012D#!\f\u0016\t\u000bE\u0002A\u0011A\u0013\u0002\u001b%\u001c\u0018J\u001c4p\u000b:\f'\r\\3eQ\t\u0001$\u0006C\u00035\u0001\u0011\u0005Q%\u0001\bjg\u0012+'-^4F]\u0006\u0014G.\u001a3)\u0005MR\u0003\"B\u001c\u0001\t\u0003)\u0013AD5t)J\f7-Z#oC\ndW\r\u001a\u0015\u0003m)BQA\u000f\u0001\u0005Bm\nQ!\u001a:s_J$2\u0001P I!\t9Q(\u0003\u0002?\u0011\t!QK\\5u\u0011\u0015\u0001\u0015\b1\u0001B\u0003\u0019\u0019x.\u001e:dKB\u0011!)\u0012\b\u0003\u000f\rK!\u0001\u0012\u0005\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\t\"AQ!S\u001dA\u0002\u0005\u000bq!\\3tg\u0006<W\rC\u0003;\u0001\u0011\u00053\n\u0006\u0003=\u00196s\u0005\"\u0002!K\u0001\u0004\t\u0005\"B%K\u0001\u0004\t\u0005\"B(K\u0001\u0004\u0001\u0016!B2bkN,\u0007CA\nR\u0013\t\u0011VDA\u0005UQJ|w/\u00192mK\")!\b\u0001C!)R!A(\u0016,X\u0011\u0015\u00015\u000b1\u0001B\u0011\u0015I5\u000b1\u0001B\u0011\u0015A6\u000b1\u0001Z\u0003\u0011\t'oZ:\u0011\u0007\u001dQF,\u0003\u0002\\\u0011\tQAH]3qK\u0006$X\r\u001a \u0011\u0005\u001di\u0016B\u00010\t\u0005\r\te.\u001f\u0005\u0006A\u0002!\t%Y\u0001\u0005o\u0006\u0014h\u000eF\u0002=E\u000eDQ\u0001Q0A\u0002\u0005CQ!S0A\u0002\u0005CQ\u0001\u0019\u0001\u0005B\u0015$B\u0001\u00104hQ\")\u0001\t\u001aa\u0001\u0003\")\u0011\n\u001aa\u0001\u0003\")q\n\u001aa\u0001!\")\u0001\r\u0001C!UR!Ah\u001b7n\u0011\u0015\u0001\u0015\u000e1\u0001B\u0011\u0015I\u0015\u000e1\u0001B\u0011\u0015A\u0016\u000e1\u0001Z\u0011\u0015y\u0007\u0001\"\u0011q\u0003\u0011IgNZ8\u0015\u0007q\n(\u000fC\u0003A]\u0002\u0007\u0011\tC\u0003J]\u0002\u0007\u0011\tC\u0003p\u0001\u0011\u0005C\u000f\u0006\u0003=kZ<\b\"\u0002!t\u0001\u0004\t\u0005\"B%t\u0001\u0004\t\u0005\"B(t\u0001\u0004\u0001\u0006\"B8\u0001\t\u0003JH\u0003\u0002\u001f{wrDQ\u0001\u0011=A\u0002\u0005CQ!\u0013=A\u0002\u0005CQ\u0001\u0017=A\u0002eCQA \u0001\u0005B}\fQ\u0001Z3ck\u001e$R\u0001PA\u0001\u0003\u0007AQ\u0001Q?A\u0002\u0005CQ!S?A\u0002\u0005CaA \u0001\u0005B\u0005\u001dAc\u0002\u001f\u0002\n\u0005-\u0011Q\u0002\u0005\u0007\u0001\u0006\u0015\u0001\u0019A!\t\r%\u000b)\u00011\u0001B\u0011\u0019y\u0015Q\u0001a\u0001!\"1a\u0010\u0001C!\u0003#!r\u0001PA\n\u0003+\t9\u0002\u0003\u0004A\u0003\u001f\u0001\r!\u0011\u0005\u0007\u0013\u0006=\u0001\u0019A!\t\ra\u000by\u00011\u0001Z\u0011\u001d\tY\u0002\u0001C!\u0003;\tQ\u0001\u001e:bG\u0016$R\u0001PA\u0010\u0003CAa\u0001QA\r\u0001\u0004\t\u0005BB%\u0002\u001a\u0001\u0007\u0011\tC\u0004\u0002\u001c\u0001!\t%!\n\u0015\u000fq\n9#!\u000b\u0002,!1\u0001)a\tA\u0002\u0005Ca!SA\u0012\u0001\u0004\t\u0005BB(\u0002$\u0001\u0007\u0001\u000bC\u0004\u0002\u001c\u0001!\t%a\f\u0015\u000fq\n\t$a\r\u00026!1\u0001)!\fA\u0002\u0005Ca!SA\u0017\u0001\u0004\t\u0005B\u0002-\u0002.\u0001\u0007\u0011lB\u0004\u0002:\tA\t!a\u000f\u0002\u00175+H\u000e^5M_\u001e<WM\u001d\t\u0004\u001b\u0005ubAB\u0001\u0003\u0011\u0003\tydE\u0002\u0002>\u0019AqaHA\u001f\t\u0003\t\u0019\u0005\u0006\u0002\u0002<!A\u0011qIA\u001f\t\u0003\tI%A\u0003baBd\u0017\u0010F\u0002\"\u0003\u0017Bq!EA#\u0001\u0004\ti\u0005E\u0002\b52\u0001")
public final class MultiLogger
implements UnderlyingLogger {
    private final Iterable<UnderlyingLogger> loggers;

    public static MultiLogger apply(Seq<UnderlyingLogger> seq) {
        return MultiLogger$.MODULE$.apply(seq);
    }

    @Override
    public boolean isErrorEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$ERROR$.MODULE$);
    }

    @Override
    public boolean isWarnEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$WARN$.MODULE$);
    }

    @Override
    public boolean isInfoEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$INFO$.MODULE$);
    }

    @Override
    public boolean isDebugEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$DEBUG$.MODULE$);
    }

    @Override
    public boolean isTraceEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$TRACE$.MODULE$);
    }

    @Override
    public void error(String source, String message) {
        this.loggers.foreach(new Serializable(this, source, message){
            public static final long serialVersionUID = 0L;
            private final String source$1;
            private final String message$1;

            public final void apply(UnderlyingLogger x$1) {
                x$1.error(this.source$1, this.message$1);
            }
            {
                this.source$1 = source$1;
                this.message$1 = message$1;
            }
        });
    }

    @Override
    public void error(String source, String message, Throwable cause) {
        this.loggers.foreach(new Serializable(this, source, message, cause){
            public static final long serialVersionUID = 0L;
            private final String source$2;
            private final String message$2;
            private final Throwable cause$1;

            public final void apply(UnderlyingLogger x$2) {
                x$2.error(this.source$2, this.message$2, this.cause$1);
            }
            {
                this.source$2 = source$2;
                this.message$2 = message$2;
                this.cause$1 = cause$1;
            }
        });
    }

    @Override
    public void error(String source, String message, Seq<Object> args) {
        this.loggers.foreach(new Serializable(this, source, message, args){
            public static final long serialVersionUID = 0L;
            private final String source$3;
            private final String message$3;
            private final Seq args$1;

            public final void apply(UnderlyingLogger x$3) {
                x$3.error(this.source$3, this.message$3, this.args$1);
            }
            {
                this.source$3 = source$3;
                this.message$3 = message$3;
                this.args$1 = args$1;
            }
        });
    }

    @Override
    public void warn(String source, String message) {
        this.loggers.foreach(new Serializable(this, source, message){
            public static final long serialVersionUID = 0L;
            private final String source$4;
            private final String message$4;

            public final void apply(UnderlyingLogger x$4) {
                x$4.warn(this.source$4, this.message$4);
            }
            {
                this.source$4 = source$4;
                this.message$4 = message$4;
            }
        });
    }

    @Override
    public void warn(String source, String message, Throwable cause) {
        this.loggers.foreach(new Serializable(this, source, message, cause){
            public static final long serialVersionUID = 0L;
            private final String source$5;
            private final String message$5;
            private final Throwable cause$2;

            public final void apply(UnderlyingLogger x$5) {
                x$5.warn(this.source$5, this.message$5, this.cause$2);
            }
            {
                this.source$5 = source$5;
                this.message$5 = message$5;
                this.cause$2 = cause$2;
            }
        });
    }

    @Override
    public void warn(String source, String message, Seq<Object> args) {
        this.loggers.foreach(new Serializable(this, source, message, args){
            public static final long serialVersionUID = 0L;
            private final String source$6;
            private final String message$6;
            private final Seq args$2;

            public final void apply(UnderlyingLogger x$6) {
                x$6.warn(this.source$6, this.message$6, this.args$2);
            }
            {
                this.source$6 = source$6;
                this.message$6 = message$6;
                this.args$2 = args$2;
            }
        });
    }

    @Override
    public void info(String source, String message) {
        this.loggers.foreach(new Serializable(this, source, message){
            public static final long serialVersionUID = 0L;
            private final String source$7;
            private final String message$7;

            public final void apply(UnderlyingLogger x$7) {
                x$7.info(this.source$7, this.message$7);
            }
            {
                this.source$7 = source$7;
                this.message$7 = message$7;
            }
        });
    }

    @Override
    public void info(String source, String message, Throwable cause) {
        this.loggers.foreach(new Serializable(this, source, message, cause){
            public static final long serialVersionUID = 0L;
            private final String source$8;
            private final String message$8;
            private final Throwable cause$3;

            public final void apply(UnderlyingLogger x$8) {
                x$8.info(this.source$8, this.message$8, this.cause$3);
            }
            {
                this.source$8 = source$8;
                this.message$8 = message$8;
                this.cause$3 = cause$3;
            }
        });
    }

    @Override
    public void info(String source, String message, Seq<Object> args) {
        this.loggers.foreach(new Serializable(this, source, message, args){
            public static final long serialVersionUID = 0L;
            private final String source$9;
            private final String message$9;
            private final Seq args$3;

            public final void apply(UnderlyingLogger x$9) {
                x$9.info(this.source$9, this.message$9, this.args$3);
            }
            {
                this.source$9 = source$9;
                this.message$9 = message$9;
                this.args$3 = args$3;
            }
        });
    }

    @Override
    public void debug(String source, String message) {
        this.loggers.foreach(new Serializable(this, source, message){
            public static final long serialVersionUID = 0L;
            private final String source$10;
            private final String message$10;

            public final void apply(UnderlyingLogger x$10) {
                x$10.debug(this.source$10, this.message$10);
            }
            {
                this.source$10 = source$10;
                this.message$10 = message$10;
            }
        });
    }

    @Override
    public void debug(String source, String message, Throwable cause) {
        this.loggers.foreach(new Serializable(this, source, message, cause){
            public static final long serialVersionUID = 0L;
            private final String source$11;
            private final String message$11;
            private final Throwable cause$4;

            public final void apply(UnderlyingLogger x$11) {
                x$11.debug(this.source$11, this.message$11, this.cause$4);
            }
            {
                this.source$11 = source$11;
                this.message$11 = message$11;
                this.cause$4 = cause$4;
            }
        });
    }

    @Override
    public void debug(String source, String message, Seq<Object> args) {
        this.loggers.foreach(new Serializable(this, source, message, args){
            public static final long serialVersionUID = 0L;
            private final String source$12;
            private final String message$12;
            private final Seq args$4;

            public final void apply(UnderlyingLogger x$12) {
                x$12.debug(this.source$12, this.message$12, this.args$4);
            }
            {
                this.source$12 = source$12;
                this.message$12 = message$12;
                this.args$4 = args$4;
            }
        });
    }

    @Override
    public void trace(String source, String message) {
        this.loggers.foreach(new Serializable(this, source, message){
            public static final long serialVersionUID = 0L;
            private final String source$13;
            private final String message$13;

            public final void apply(UnderlyingLogger x$13) {
                x$13.trace(this.source$13, this.message$13);
            }
            {
                this.source$13 = source$13;
                this.message$13 = message$13;
            }
        });
    }

    @Override
    public void trace(String source, String message, Throwable cause) {
        this.loggers.foreach(new Serializable(this, source, message, cause){
            public static final long serialVersionUID = 0L;
            private final String source$14;
            private final String message$14;
            private final Throwable cause$5;

            public final void apply(UnderlyingLogger x$14) {
                x$14.trace(this.source$14, this.message$14, this.cause$5);
            }
            {
                this.source$14 = source$14;
                this.message$14 = message$14;
                this.cause$5 = cause$5;
            }
        });
    }

    @Override
    public void trace(String source, String message, Seq<Object> args) {
        this.loggers.foreach(new Serializable(this, source, message, args){
            public static final long serialVersionUID = 0L;
            private final String source$15;
            private final String message$15;
            private final Seq args$5;

            public final void apply(UnderlyingLogger x$15) {
                x$15.trace(this.source$15, this.message$15, this.args$5);
            }
            {
                this.source$15 = source$15;
                this.message$15 = message$15;
                this.args$5 = args$5;
            }
        });
    }

    public MultiLogger(Iterable<UnderlyingLogger> loggers) {
        this.loggers = loggers;
    }
}

