/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.Function2;
import scala.Function3;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import slogging.LogLevel;
import slogging.LoggerConfig$;
import slogging.UnderlyingLoggerFactory;

@ScalaSignature(bytes="\u0006\u0001\u0005Uq!B\u0001\u0003\u0011\u0003)\u0011\u0001\u0004'pO\u001e,'oQ8oM&<'\"A\u0002\u0002\u0011MdwnZ4j]\u001e\u001c\u0001\u0001\u0005\u0002\u0007\u000f5\t!AB\u0003\t\u0005!\u0005\u0011B\u0001\u0007M_\u001e<WM]\"p]\u001aLwm\u0005\u0002\b\u0015A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001aDQ!E\u0004\u0005\u0002I\ta\u0001P5oSRtD#A\u0003\u0006\tQ9\u0001!\u0006\u0002\f\u0019><w-\u001b8h\u0011>|7\u000e\u0005\u0004\f-aY2DI\u0005\u0003/1\u0011\u0011BR;oGRLwN\\\u001a\u0011\u0005\u0019I\u0012B\u0001\u000e\u0003\u0005!aun\u001a'fm\u0016d\u0007C\u0001\u000f \u001d\tYQ$\u0003\u0002\u001f\u0019\u00051\u0001K]3eK\u001aL!\u0001I\u0011\u0003\rM#(/\u001b8h\u0015\tqB\u0002\u0005\u0002\fG%\u0011A\u0005\u0004\u0002\u0005+:LG/\u0002\u0003'\u000f\u00019#!D!sON4uN]7biR,'\u000fE\u0003\fQmQ3$\u0003\u0002*\u0019\tIa)\u001e8di&|gN\r\t\u0004WM2dB\u0001\u00172\u001d\ti\u0003'D\u0001/\u0015\tyC!\u0001\u0004=e>|GOP\u0005\u0002\u001b%\u0011!\u0007D\u0001\ba\u0006\u001c7.Y4f\u0013\t!TGA\u0002TKFT!A\r\u0007\u0011\u0005-9\u0014B\u0001\u001d\r\u0005\r\te.\u001f\u0005\bu\u001d\u0011\r\u0011\"\u0001<\u0003-!WMZ1vYRDun\\6\u0016\u0003q\u0002\"!P\n\u000e\u0003\u001dAaaP\u0004!\u0002\u0013a\u0014\u0001\u00043fM\u0006,H\u000e\u001e%p_.\u0004\u0003\u0002D!\b\t\u0003\u0005)\u0011!a\u0001\n\u0013\u0011\u0015aH:m_\u001e<\u0017N\\4%\u0019><w-\u001a:D_:4\u0017n\u001a\u0013%?\u001a\f7\r^8ssV\t1\t\u0005\u0002\u0007\t&\u0011QI\u0001\u0002\u0018+:$WM\u001d7zS:<Gj\\4hKJ4\u0015m\u0019;pefD\u0011bR\u0004\u0003\u0002\u0003\u0007I\u0011\u0002%\u0002GMdwnZ4j]\u001e$Cj\\4hKJ\u001cuN\u001c4jO\u0012\"sLZ1di>\u0014\u0018p\u0018\u0013fcR\u0011!%\u0013\u0005\b\u0015\u001a\u000b\t\u00111\u0001D\u0003\rAH%\r\u0005\n\u0019\u001e\u0011\t\u0011!Q!\n\r\u000b\u0001e\u001d7pO\u001eLgn\u001a\u0013M_\u001e<WM]\"p]\u001aLw\r\n\u0013`M\u0006\u001cGo\u001c:zA!aaj\u0002C\u0001\u0002\u000b\u0005\t\u0019!C\u0005\u001f\u0006i2\u000f\\8hO&tw\r\n'pO\u001e,'oQ8oM&<G\u0005J0mKZ,G.F\u0001\u0019\u0011%\tvA!AA\u0002\u0013%!+A\u0011tY><w-\u001b8hI1{wmZ3s\u0007>tg-[4%I}cWM^3m?\u0012*\u0017\u000f\u0006\u0002#'\"9!\nUA\u0001\u0002\u0004A\u0002\"C+\b\u0005\u0003\u0005\t\u0015)\u0003\u0019\u0003y\u0019Hn\\4hS:<G\u0005T8hO\u0016\u00148i\u001c8gS\u001e$Ce\u00187fm\u0016d\u0007\u0005\u0003\u0007X\u000f\u0011\u0005\tQ!AA\u0002\u0013%1(A\u0011tY><w-\u001b8hI1{wmZ3s\u0007>tg-[4%I}+'O]8s\u0011>|7\u000eC\u0005Z\u000f\t\u0005\t\u0019!C\u00055\u0006)3\u000f\\8hO&tw\r\n'pO\u001e,'oQ8oM&<G\u0005J0feJ|'\u000fS8pW~#S-\u001d\u000b\u0003EmCqA\u0013-\u0002\u0002\u0003\u0007A\bC\u0005^\u000f\t\u0005\t\u0011)Q\u0005y\u0005\u00113\u000f\\8hO&tw\r\n'pO\u001e,'oQ8oM&<G\u0005J0feJ|'\u000fS8pW\u0002BAbX\u0004\u0005\u0002\u0003\u0015\t\u00111A\u0005\n\u0001\fQe\u001d7pO\u001eLgn\u001a\u0013M_\u001e<WM]\"p]\u001aLw\r\n\u0013`CJ<7OR8s[\u0006$H/\u001a:\u0016\u0003\u0005\u0004\"!P\u0013\t\u0013\r<!\u0011!a\u0001\n\u0013!\u0017!K:m_\u001e<\u0017N\\4%\u0019><w-\u001a:D_:4\u0017n\u001a\u0013%?\u0006\u0014xm\u001d$pe6\fG\u000f^3s?\u0012*\u0017\u000f\u0006\u0002#K\"9!JYA\u0001\u0002\u0004\t\u0007\"C4\b\u0005\u0003\u0005\t\u0015)\u0003b\u0003\u0019\u001aHn\\4hS:<G\u0005T8hO\u0016\u00148i\u001c8gS\u001e$CeX1sON4uN]7biR,'\u000f\t\u0005\u0006S\u001e!\t\u0001Y\u0001\u000eCJ<7OR8s[\u0006$H/\u001a:)\u0005!\\\u0007CA\u0006m\u0013\tiGB\u0001\u0004j]2Lg.\u001a\u0005\u0006_\u001e!\t\u0001]\u0001\u000fCJ<7OR8s[\u0006$H/\u001a:`)\t\u0011\u0013\u000fC\u0003s]\u0002\u0007\u0011-A\u0001g\u0011\u0015!x\u0001\"\u0001C\u0003\u001d1\u0017m\u0019;pefD#a]6\t\u000b]<A\u0011\u0001=\u0002\u0017\u0019\f7\r^8ss~#S-\u001d\u000b\u0003EeDQA\u001d<A\u0002\rCQa_\u0004\u0005\u0002=\u000bQ\u0001\\3wK2D#A_6\t\u000by<A\u0011A@\u0002\u00131,g/\u001a7`I\u0015\fHc\u0001\u0012\u0002\u0002!1\u00111A?A\u0002a\t\u0011\u0001\u001c\u0005\u0007\u0003\u000f9A\u0011A\u001e\u0002\u000f=tWI\u001d:pe\"\u001a\u0011QA6\t\u000f\u00055q\u0001\"\u0001\u0002\u0010\u0005YqN\\#se>\u0014x\fJ3r)\r\u0011\u0013\u0011\u0003\u0005\b\u0003'\tY\u00011\u0001=\u0003!a\u0017n\u001d;f]\u0016\u0014\b")
public final class LoggerConfig {
    public static void onError_$eq(Function3<LogLevel, String, String, BoxedUnit> function3) {
        LoggerConfig$.MODULE$.onError_$eq(function3);
    }

    public static Function3<LogLevel, String, String, BoxedUnit> onError() {
        return LoggerConfig$.MODULE$.onError();
    }

    public static void level_$eq(LogLevel logLevel) {
        LoggerConfig$.MODULE$.level_$eq(logLevel);
    }

    public static LogLevel level() {
        return LoggerConfig$.MODULE$.level();
    }

    public static void factory_$eq(UnderlyingLoggerFactory underlyingLoggerFactory) {
        LoggerConfig$.MODULE$.factory_$eq(underlyingLoggerFactory);
    }

    public static UnderlyingLoggerFactory factory() {
        return LoggerConfig$.MODULE$.factory();
    }

    public static void argsFormatter_(Function2<String, Seq<Object>, String> function2) {
        LoggerConfig$.MODULE$.argsFormatter_(function2);
    }

    public static Function2<String, Seq<Object>, String> argsFormatter() {
        return LoggerConfig$.MODULE$.argsFormatter();
    }

    public static Function3<LogLevel, String, String, BoxedUnit> defaultHook() {
        return LoggerConfig$.MODULE$.defaultHook();
    }
}

