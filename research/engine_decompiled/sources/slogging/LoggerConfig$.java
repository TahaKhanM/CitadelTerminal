/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.Function2;
import scala.Function3;
import scala.Serializable;
import scala.collection.Seq;
import scala.runtime.BoxedUnit;
import slogging.LogLevel;
import slogging.LogLevel$INFO$;
import slogging.LoggingUtils$;
import slogging.NullLoggerFactory$;
import slogging.UnderlyingLoggerFactory;

public final class LoggerConfig$ {
    public static final LoggerConfig$ MODULE$;
    private final Function3<LogLevel, String, String, BoxedUnit> defaultHook;
    private UnderlyingLoggerFactory slogging$LoggerConfig$$_factory;
    private LogLevel slogging$LoggerConfig$$_level;
    private Function3<LogLevel, String, String, BoxedUnit> slogging$LoggerConfig$$_errorHook;
    private Function2<String, Seq<Object>, String> slogging$LoggerConfig$$_argsFormatter;

    static {
        new LoggerConfig$();
    }

    public Function3<LogLevel, String, String, BoxedUnit> defaultHook() {
        return this.defaultHook;
    }

    public UnderlyingLoggerFactory slogging$LoggerConfig$$_factory() {
        return this.slogging$LoggerConfig$$_factory;
    }

    private void slogging$LoggerConfig$$_factory_$eq(UnderlyingLoggerFactory x$1) {
        this.slogging$LoggerConfig$$_factory = x$1;
    }

    public LogLevel slogging$LoggerConfig$$_level() {
        return this.slogging$LoggerConfig$$_level;
    }

    private void slogging$LoggerConfig$$_level_$eq(LogLevel x$1) {
        this.slogging$LoggerConfig$$_level = x$1;
    }

    public Function3<LogLevel, String, String, BoxedUnit> slogging$LoggerConfig$$_errorHook() {
        return this.slogging$LoggerConfig$$_errorHook;
    }

    private void slogging$LoggerConfig$$_errorHook_$eq(Function3<LogLevel, String, String, BoxedUnit> x$1) {
        this.slogging$LoggerConfig$$_errorHook = x$1;
    }

    public Function2<String, Seq<Object>, String> slogging$LoggerConfig$$_argsFormatter() {
        return this.slogging$LoggerConfig$$_argsFormatter;
    }

    private void slogging$LoggerConfig$$_argsFormatter_$eq(Function2<String, Seq<Object>, String> x$1) {
        this.slogging$LoggerConfig$$_argsFormatter = x$1;
    }

    public Function2<String, Seq<Object>, String> argsFormatter() {
        return this.slogging$LoggerConfig$$_argsFormatter();
    }

    public synchronized void argsFormatter_(Function2<String, Seq<Object>, String> f) {
        this.slogging$LoggerConfig$$_argsFormatter_$eq(f);
    }

    public UnderlyingLoggerFactory factory() {
        return this.slogging$LoggerConfig$$_factory();
    }

    public synchronized void factory_$eq(UnderlyingLoggerFactory f) {
        this.slogging$LoggerConfig$$_factory_$eq(f);
    }

    public LogLevel level() {
        return this.slogging$LoggerConfig$$_level();
    }

    public synchronized void level_$eq(LogLevel l) {
        this.slogging$LoggerConfig$$_level_$eq(l);
    }

    public Function3<LogLevel, String, String, BoxedUnit> onError() {
        return this.slogging$LoggerConfig$$_errorHook();
    }

    public synchronized void onError_$eq(Function3<LogLevel, String, String, BoxedUnit> listener) {
        this.slogging$LoggerConfig$$_errorHook_$eq(listener);
    }

    private LoggerConfig$() {
        MODULE$ = this;
        this.defaultHook = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply(LogLevel x$1, String x$2, String x$3) {
            }
        };
        this.slogging$LoggerConfig$$_factory = NullLoggerFactory$.MODULE$;
        this.slogging$LoggerConfig$$_level = LogLevel$INFO$.MODULE$;
        this.slogging$LoggerConfig$$_errorHook = this.defaultHook();
        this.slogging$LoggerConfig$$_argsFormatter = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply(String msg, Seq<Object> args) {
                return LoggingUtils$.MODULE$.argsBracketFormat(msg, args);
            }
        };
    }
}

