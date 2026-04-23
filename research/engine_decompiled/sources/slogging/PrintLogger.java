/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.Option;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import slogging.MessageLevel;
import slogging.PrintLogger$;

@ScalaSignature(bytes="\u0006\u0001i:Q!\u0001\u0002\t\u0002\u0015\t1\u0002\u0015:j]RdunZ4fe*\t1!\u0001\u0005tY><w-\u001b8h\u0007\u0001\u0001\"AB\u0004\u000e\u0003\t1Q\u0001\u0003\u0002\t\u0002%\u00111\u0002\u0015:j]RdunZ4feN\u0011qA\u0003\t\u0003\r-I!\u0001\u0004\u0002\u0003\u001d1{wmZ3s)\u0016l\u0007\u000f\\1uK\")ab\u0002C\u0001\u001f\u00051A(\u001b8jiz\"\u0012!\u0002\u0005\u0006#\u001d!)EE\u0001\u000bY><W*Z:tC\u001e,G#B\n\u001a=\u001dJ\u0003C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"\u0001B+oSRDQA\u0007\tA\u0002m\tQ\u0001\\3wK2\u0004\"A\u0002\u000f\n\u0005u\u0011!\u0001D'fgN\fw-\u001a'fm\u0016d\u0007\"B\u0010\u0011\u0001\u0004\u0001\u0013\u0001\u00028b[\u0016\u0004\"!\t\u0013\u000f\u0005Q\u0011\u0013BA\u0012\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011QE\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\r*\u0002\"\u0002\u0015\u0011\u0001\u0004\u0001\u0013aB7fgN\fw-\u001a\u0005\u0006UA\u0001\raK\u0001\u0006G\u0006,8/\u001a\t\u0004)1r\u0013BA\u0017\u0016\u0005\u0019y\u0005\u000f^5p]B\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!a\r\u0003\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0012B\u0001\u001c\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0013QC'o\\<bE2,'B\u0001\u001c\u0016\u0001")
public final class PrintLogger {
    public static void logMessage(MessageLevel messageLevel, String string2, String string3, Option<Throwable> option) {
        PrintLogger$.MODULE$.logMessage(messageLevel, string2, string3, option);
    }

    public static void trace(String string2, String string3, Seq<Object> seq) {
        PrintLogger$.MODULE$.trace(string2, string3, seq);
    }

    public static void trace(String string2, String string3, Throwable throwable) {
        PrintLogger$.MODULE$.trace(string2, string3, throwable);
    }

    public static void trace(String string2, String string3) {
        PrintLogger$.MODULE$.trace(string2, string3);
    }

    public static void debug(String string2, String string3, Seq<Object> seq) {
        PrintLogger$.MODULE$.debug(string2, string3, seq);
    }

    public static void debug(String string2, String string3, Throwable throwable) {
        PrintLogger$.MODULE$.debug(string2, string3, throwable);
    }

    public static void debug(String string2, String string3) {
        PrintLogger$.MODULE$.debug(string2, string3);
    }

    public static void info(String string2, String string3, Seq<Object> seq) {
        PrintLogger$.MODULE$.info(string2, string3, seq);
    }

    public static void info(String string2, String string3, Throwable throwable) {
        PrintLogger$.MODULE$.info(string2, string3, throwable);
    }

    public static void info(String string2, String string3) {
        PrintLogger$.MODULE$.info(string2, string3);
    }

    public static void warn(String string2, String string3, Seq<Object> seq) {
        PrintLogger$.MODULE$.warn(string2, string3, seq);
    }

    public static void warn(String string2, String string3, Throwable throwable) {
        PrintLogger$.MODULE$.warn(string2, string3, throwable);
    }

    public static void warn(String string2, String string3) {
        PrintLogger$.MODULE$.warn(string2, string3);
    }

    public static void error(String string2, String string3, Seq<Object> seq) {
        PrintLogger$.MODULE$.error(string2, string3, seq);
    }

    public static void error(String string2, String string3, Throwable throwable) {
        PrintLogger$.MODULE$.error(string2, string3, throwable);
    }

    public static void error(String string2, String string3) {
        PrintLogger$.MODULE$.error(string2, string3);
    }

    public static boolean isTraceEnabled() {
        return PrintLogger$.MODULE$.isTraceEnabled();
    }

    public static boolean isDebugEnabled() {
        return PrintLogger$.MODULE$.isDebugEnabled();
    }

    public static boolean isInfoEnabled() {
        return PrintLogger$.MODULE$.isInfoEnabled();
    }

    public static boolean isWarnEnabled() {
        return PrintLogger$.MODULE$.isWarnEnabled();
    }

    public static boolean isErrorEnabled() {
        return PrintLogger$.MODULE$.isErrorEnabled();
    }
}

